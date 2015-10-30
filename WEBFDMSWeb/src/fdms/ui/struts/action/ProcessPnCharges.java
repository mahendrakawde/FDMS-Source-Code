package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbPnContract;
import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationAddMerchandiseLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.TempPnCharges;

import fdms.ui.struts.form.ChangePriceListForm;
import fdms.ui.struts.form.PnAddMerchandiseForm;
import fdms.ui.struts.form.PnAddServicesForm;
import fdms.ui.struts.form.PnChangeChargeLineForm;
import fdms.ui.struts.form.PnCharges;
import fdms.ui.struts.form.PnSelectPackageForm;


public class ProcessPnCharges extends Action {

    private static Logger logger = Logger.getLogger(ProcessPnCharges.class.getName());

/**
 * Remove specified charge from collection.
 * Creation date: (1/13/2003 4:03:56 PM)
 * @param errors org.apache.struts.action.ActionErrors
 * @param request javax.servlet.http.HttpServletRequest
 * @param vitalsid int
 */
private void deleteCharge(ActionErrors errors, HttpServletRequest request, int vitalsid, int contractid, int deleteid) {

   	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
   	DatabaseTransaction t =null;
	DbPnContract acontract = null;
	TempPnCharges charges = new TempPnCharges((DbUser)sessionUser, contractid);

   	// make sure our session is valid
   	if(sessionUser==null)	{
		 errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
	}
	// If errors found, bail out and return to input page
   	if( !errors.isEmpty() ){
		return ;
	}

	//Database Access Logic
	try{
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		acontract = FdmsDb.getInstance().getPnContract(t, contractid);		
		// DELETE specified charge from temp file
		charges.deleteAcharge(deleteid);
		charges.calculateSalesTax( acontract.getPriceListVersion());
		// clean up
		ProcessPnStatus.preparePnCharges(errors,request,vitalsid, contractid, (DbUser) sessionUser);
	}
	catch(PersistenceException pe) { 
		logger.error("Persistence Exception in PnCharges do.Perform. " + pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause())); 
	} 
	catch (Exception pe) {
			logger.error("Exception in PnCharges .doPerform. ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
		} finally {
			if (t != null)
				t.closeConnection();
		}
	
	
}
  public ActionForward execute(ActionMapping mapping,
	ActionForm actionForm,
	HttpServletRequest request, HttpServletResponse response)
	  throws javax.servlet.ServletException, java.io.IOException
  {
	//AppLog.trace("ProcessPnCharges beginning: "+form.getDirective()+":"+form.getVitalsId()+":"+form.getContractId());
      
      PnCharges form = (PnCharges) actionForm;
	ActionErrors errors = new ActionErrors();
	int vitalsid = FormatNumber.parseInteger(form.getVitalsId());
	int contractid = FormatNumber.parseInteger(form.getContractId());
   	DbUserSession sessionUser = SessionHelpers.getUserSession(request);

	// default next page to display
	ActionForward nextAction = mapping.findForward("showPnStatus");
	SessionHelpers.setVitalsIdInRequest(request,vitalsid);
	
	if (form.getDirective().equalsIgnoreCase("save")){
		//AppLog.trace("ProcessPnCharges handling save");
		saveChanges(errors, request, vitalsid, contractid);
	}
	else if (form.getDirective().equalsIgnoreCase("addservices")){
		//AppLog.trace("ProcessPnCharges handling addservices");
		preparePnAddServices(errors, request, vitalsid, contractid);
		nextAction = mapping.findForward("showPnAddServicesJsp");
	}
	else if (form.getDirective().equalsIgnoreCase("addmerch")){
		//AppLog.trace("ProcessPnCharges handling addmerch");
		preparePnAddMerchandise(errors, request, vitalsid, contractid);
		nextAction = mapping.findForward("showPnAddMerchandiseJsp");
	}
	else if (form.getDirective().equalsIgnoreCase("insertpackage")){
		//AppLog.trace("ProcessPnCharges handling insertpackage");
		preparePnInsertPackage(errors, request, vitalsid, contractid);
		nextAction = mapping.findForward("showPnSelectPackageJsp");
	}
	else if (form.getDirective().equalsIgnoreCase("changepricelist")){
		//AppLog.trace("ProcessPnCharges handling changepricelist");
		preparePnChangePriceList(errors, request, vitalsid, contractid);
		nextAction = mapping.findForward("showPnChangePriceListJsp");
	}
	else if (form.getDirective().equalsIgnoreCase("ChangeDistribution")){
		//AppLog.trace("ProcessPnCharges handling changeDistribution:"+form.getRemoveline());
		int changeline = FormatNumber.parseInteger(form.getRemoveline());
		preparePnChangeDistribution(errors, request, vitalsid, contractid, changeline);
		nextAction = mapping.findForward("showPnChangeChargeLineJsp");
	}
	else if (form.getDirective().equalsIgnoreCase("RemoveDistribution")){
		//AppLog.trace("ProcessPnCharges handling RemoveDistribution:"+form.getRemoveline());
		int removeline = FormatNumber.parseInteger(form.getRemoveline());
		deleteCharge(errors, request, vitalsid, contractid, removeline);
		nextAction = mapping.findForward("ShowPnChargesJspGlobal");
	}
	// not one of the above commands, so assume "cancel"
	else {
		// drop temp table
		TempPnCharges charges = new TempPnCharges((DbUser)sessionUser, contractid);
		charges.deleteTempTable();
	}
	
	if (!errors.isEmpty()) {
	   	saveErrors(request, errors);
	}
	return nextAction;
  }                                    
/**
 * Make beans needed for PnAddMerchandise.jsp and add as request attributes.
 * Creation date: (1/13/2003 4:03:56 PM)
 * @param errors org.apache.struts.action.ActionErrors
 * @param request javax.servlet.http.HttpServletRequest
 * @param vitalsid int
 */
public static void preparePnAddMerchandise(ActionErrors errors, HttpServletRequest request, int vitalsid, int contractid) {

   	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
   	DatabaseTransaction t =null;
   	DbPnContract		acontract = null;
	DbVitalsDeceased	bene = null;
	DbCase				acase = null;
	java.util.ArrayList addMerchandiseList = new java.util.ArrayList();
	java.util.ArrayList addMerchandiseImageList = new java.util.ArrayList();

   	// make sure our session is valid
   	if(sessionUser==null)	{
		 errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
	}
	// If errors found, bail out and return to input page
   	if( !errors.isEmpty() ){
		return ;
	}

	PnAddMerchandiseForm form = new PnAddMerchandiseForm();
	form.setVitalsId(Integer.toString(vitalsid));
	form.setContractId( Integer.toString(contractid));

	// Make collection for request parameter "PnAddMerchandiseList"
	// which is a list of price list items for the current price list
	//Database Access Logic
	try{
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		acontract = FdmsDb.getInstance().getPnContract(t, contractid);
		bene	  = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
		acase	  = FdmsDb.getInstance().getCase(t,vitalsid);
		if (bene == null || acase==null){
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
			throw new PersistenceException("Invalid vitals id");
		} 
		DbInvMaster[] dbInvMaster = FdmsDb.getInstance().getInvMasterForLocation(t, acase.getChapelNumber()); 
		//form.setItemImage(" ");
		boolean isImagePresent;
		for(int i=0 ; i < dbInvMaster.length ; i++) {
			FinancialInformationAddMerchandiseLineItem famLineItem = new FinancialInformationAddMerchandiseLineItem( dbInvMaster[i] );
			if (dbInvMaster[i].getImageUrl() == null || dbInvMaster[i].getImageUrl().trim().compareTo(" ")<=0) {
				isImagePresent=false;
			} else {
				isImagePresent=true;
			}
			//addMerchandisePriceList.add(i,famLineItem);
			addMerchandiseList.add(new OptionsList(famLineItem.getItemId(),famLineItem.getItemName() +" "+famLineItem.getItemDescription()+" "+famLineItem.getItemPrice()));
			//form.setItemImage(dbInvMaster[i].getImageUrl());
			addMerchandiseImageList.add(new OptionsList(new Boolean(isImagePresent).toString(), dbInvMaster[i].getImageUrl().replace('\\','/')));
		}
		// set form data to display
		form.setContractCode(		Integer.toString(acontract.getContractNumber()));
		form.setFullName(			bene.getDecFName()+" "+bene.getDecLName());
	}
	catch(PersistenceException pe) { 
		logger.error("Persistence Exception in PnAddMerch do.Perform. " + pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
	}
	catch(Exception pe) { 
		logger.error("Exception in PnAddMerch .doPerform. ", pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
	
	request.setAttribute("PnAddMerchandiseList",addMerchandiseList);
	request.setAttribute("PnAddMerchandiseImageList", addMerchandiseImageList);
	request.setAttribute("PnAddMerchandiseForm",form);
}
/**
 * Make beans needed for PnAddServices.jsp and add as request attributes.
 * Creation date: (1/13/2003 4:03:56 PM)
 * @param errors org.apache.struts.action.ActionErrors
 * @param request javax.servlet.http.HttpServletRequest
 * @param vitalsid int
 */
public static void preparePnAddServices(ActionErrors errors, HttpServletRequest request, int vitalsid, int contractid) {

   	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
   	DatabaseTransaction t =null;
   	DbPnContract		acontract = null;
	DbVitalsDeceased	bene = null;
   	java.util.ArrayList addServicesList = new java.util.ArrayList(); 

   	// make sure our session is valid
   	if(sessionUser==null)	{
		 errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
	}
	// If errors found, bail out and return to input page
   	if( !errors.isEmpty() ){
		return ;
	}

	PnAddServicesForm form = new PnAddServicesForm();
	form.setVitalsId(Integer.toString(vitalsid));
	form.setContractId( Integer.toString(contractid));

	// Make collection for request parameter "PnAddServicesList"
	// which is a list of price list items for the current price list
	//Database Access Logic
	try{
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		acontract = FdmsDb.getInstance().getPnContract(t, contractid);
// to do: get price list version from temp file		
		PriceListManager plm = new PriceListManager();
		DbPriceList dbPriceList[] = plm.getPriceListForVersion(t,acontract.getPriceListVersion(), sessionUser.getRegion()); 
		//AppLog.trace("PnAddServices. Got Price List items:"+dbPriceList.length);		
		for(int i=0 ; i < dbPriceList.length ; i++)	{
			// Check if this price list item is "Active" to determine if should be in list of available items
			// Also, if first character of GPL key is 'S' or 'C' indicates a Loewen section or cover header
			// Also, skip contract line#0 which means section headers for non-Loewen sites
			char firstchar = FormatString.getFirstChar(dbPriceList[i].getGPLkey());
			//AppLog.trace("AddServices first char:"+i+":"+String.valueOf(firstchar)+" active="+dbPriceList[i].getActive()+dbPriceList[i].getContrLine());
			if (dbPriceList[i].getActive()=='Y' 
				&& firstchar !='S' && firstchar != 'C' 
				&& dbPriceList[i].getContrLine() != 0 ){
				//AppLog.trace("AddServices active item:"+i+":"+dbPriceList[i].getContrDescr());
				addServicesList.add(new OptionsList(String.valueOf(dbPriceList[i].getId()), dbPriceList[i].getContrDescr()+" "+FormatCurrency.toCurrency((long) dbPriceList[i].getPrice() )));
			}
		}
		// set form data to display
		bene	  = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
		if (bene == null){
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
		} else {
			form.setContractCode(		Integer.toString(acontract.getContractNumber()));
			form.setFullName(			bene.getDecFName()+" "+bene.getDecLName());
		}
	}
	catch(PersistenceException pe) { 
		logger.error("Persistence Exception in PnAddServices do.Perform. " + pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
	}
	catch(Exception pe) { 
		logger.error("Exception in PnAddServices .doPerform. ", pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
	
	request.setAttribute("PnAddServicesList",addServicesList);
	request.setAttribute("PnAddServicesForm",form);
}
/**
 * Make beans needed for PnChangeChargeLine.jsp and add as request attributes.
 * Creation date: (1/13/2003 4:03:56 PM)
 * @param errors org.apache.struts.action.ActionErrors
 * @param request javax.servlet.http.HttpServletRequest
 * @param vitalsid int
 */
public static void preparePnChangeDistribution(ActionErrors errors, HttpServletRequest request, int vitalsid, int contractid, int changeid) {

   	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
   	DatabaseTransaction t =null;
	DbPnContract acontract = null;
	DbVitalsDeceased	bene = null;
	DbCase				acase =  null;
	com.aldorsolutions.webfdms.util.PnCharge acharge = null;
	TempPnCharges charges = new TempPnCharges((DbUser)sessionUser, contractid);

   	// make sure our session is valid
   	if(sessionUser==null)	{
		 errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
	}
	// If errors found, bail out and return to input page
   	if( !errors.isEmpty() ){
		return ;
	}

	PnChangeChargeLineForm form = new PnChangeChargeLineForm();
	form.setVitalsId(Integer.toString(vitalsid));
	form.setContractId( Integer.toString(contractid));

	// Make collection for request parameter "PnAddServicesList"
	// which is a list of price list items for the current price list
	//Database Access Logic
	try{
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		acontract = FdmsDb.getInstance().getPnContract(t, contractid);
		bene	  = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
		acase	  = FdmsDb.getInstance().getCase(t,vitalsid);
		acharge	  = charges.getAcharge(changeid);
		if (bene == null || acase==null){
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
			return;
		}
		if (acharge == null){
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException","Invalid charge ID"));
			return;
		}
		// set form data to display
		form.setContractCode(		Integer.toString(acontract.getContractNumber()));
		form.setFullName(			bene.getDecFName()+" "+bene.getDecLName());
		form.setLineId(				acharge.getItemID());
		form.setTypeCode(			acharge.getItemTypeCode());
		form.setDescription(		acharge.getItemTypeDescription());
		form.setPrice(				acharge.getItemPrice());
		form.setSequenceNumber(		acharge.getItemSequenceNumber());
		form.setTaxCode(			acharge.getItemTaxCode());
		form.setContractCode(		Integer.toString(acontract.getContractNumber()));
		form.setFullName(			bene.getDecFName()+" "+bene.getDecLName());	

	}
	catch(PersistenceException pe) { 
		logger.error("Persistence Exception in PnCharges do.Perform. " + pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
	}
	catch(Exception pe) { 
		logger.error("Exception in PnCharges .doPerform. ", pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
	
	request.setAttribute("PnChangeChargeLineForm",form);
}
/**
 * Make beans needed for PnChangePriceList.jsp and add as request attributes.
 * Creation date: (1/13/2003 4:03:56 PM)
 * @param errors org.apache.struts.action.ActionErrors
 * @param request javax.servlet.http.HttpServletRequest
 * @param vitalsid int
 */
public static void preparePnChangePriceList(ActionErrors errors, HttpServletRequest request, int vitalsid, int contractid) {

   	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
   	DatabaseTransaction t =null;
	DbPnContract acontract = null;
	DbVitalsDeceased	bene = null;
	DbCase				acase =  null;

   	// make sure our session is valid
   	if(sessionUser==null)	{
		 errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
	}
	// If errors found, bail out and return to input page
   	if( !errors.isEmpty() ){
		return ;
	}

   	java.util.ArrayList changePriceList = new java.util.ArrayList();

	ChangePriceListForm form = new ChangePriceListForm();
	form.setVitalsId(Integer.toString(vitalsid));
	form.setContractId( Integer.toString(contractid));

	// Make collection for request parameter "PnAddServicesList"
	// which is a list of price list items for the current price list
	//Database Access Logic
	try{
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		acontract = FdmsDb.getInstance().getPnContract(t, contractid);
		bene	  = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
		acase	  = FdmsDb.getInstance().getCase(t,vitalsid);
		if (bene == null || acase==null){
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
			return;
		}
		// set form data to display
		form.setContractCode(		Integer.toString(acontract.getContractNumber()));
		form.setFullName(			bene.getDecFName()+" "+bene.getDecLName());
		form.setPriceChange(acontract.getPriceListVersion());
		//AppLog.trace("ChangePriceList: old version:"+form.getPriceChange());
		PriceListManager plm = new PriceListManager();
		ArrayList versions = plm.getPriceListVersions(t, sessionUser.getRegion());
	    java.util.Iterator myloop = versions.listIterator();
	    while (myloop.hasNext()){
		    String aVersion = myloop.next().toString();
		    //AppLog.trace("ChangePriceList: version:"+aVersion);
		    changePriceList.add(new OptionsList(aVersion, aVersion));
		}
	}
	catch(PersistenceException pe) { 
		logger.error("Persistence Exception in PnCharges do.Perform. " + pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));    
	}
	catch(Exception pe) { 
		logger.error("Exception in PnCharges .doPerform. ", pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
	
	request.setAttribute("PnPriceLists",changePriceList);
	request.setAttribute("PnChangePriceListForm",form);
}
/**
 * Make beans needed for PnInsertPackage.jsp and add as request attributes.
 * Creation date: (1/13/2003 4:03:56 PM)
 * @param errors org.apache.struts.action.ActionErrors
 * @param request javax.servlet.http.HttpServletRequest
 * @param vitalsid int
 */
public static void preparePnInsertPackage(ActionErrors errors, HttpServletRequest request, int vitalsid, int contractid) {

   	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
   	DatabaseTransaction t =null;
	DbPnContract acontract = null;
	DbVitalsDeceased	bene = null;
	DbCase				acase =  null;

   	// make sure our session is valid
   	if(sessionUser==null)	{
		 errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
	}
	// If errors found, bail out and return to input page
   	if( !errors.isEmpty() ){
		return ;
	}

	DbPriceList[] dbPriceList = null;
	java.util.ArrayList financialPackagesList = new java.util.ArrayList();

	PnSelectPackageForm form = new PnSelectPackageForm();
	form.setVitalsId(Integer.toString(vitalsid));
	form.setContractId( Integer.toString(contractid));

	// Make collection for request parameter "PnAddServicesList"
	// which is a list of price list items for the current price list
	//Database Access Logic
	try{
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		acontract = FdmsDb.getInstance().getPnContract(t, contractid);
		bene	  = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
		acase	  = FdmsDb.getInstance().getCase(t,vitalsid);
		if (bene == null || acase==null){
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
			return;
		}
		PriceListManager plm = new PriceListManager();
	    dbPriceList = plm.getPriceListForVersion(t, acontract.getPriceListVersion(), sessionUser.getRegion());

		// set form data to display
		form.setContractCode(		Integer.toString(acontract.getContractNumber()));
		form.setFullName(			bene.getDecFName()+" "+bene.getDecLName());
		form.setPriceListVersion(	acontract.getPriceListVersion());
	   //Populate the financialPackagesList collection
	   for (int i=0; i < dbPriceList.length; i++) {
	      if (dbPriceList[i].getPackage() == 'Y') {
	         financialPackagesList.add(new OptionsList(String.valueOf(dbPriceList[i].getId()), dbPriceList[i].getContrDescr()));
	      }
	   }

	   if (financialPackagesList.size() == 0) {
	      financialPackagesList.add(new OptionsList(" ", "No Packages Available"));
	   }

	}
	catch(PersistenceException pe) { 
		logger.error("Persistence Exception in PnCharges do.Perform. " + pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));    
	}
	catch(Exception pe) { 
		logger.error("Exception in PnCharges .doPerform. ", pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
	
	request.setAttribute("PnPackagesList",financialPackagesList);
	request.setAttribute("PnSelectPackageForm",form);
}
/**
 * Save changes, additions, and deletions from temp-table to DbPnCharges.
 * Creation date: (1/13/2003 4:03:56 PM)
 * @param errors org.apache.struts.action.ActionErrors
 * @param request javax.servlet.http.HttpServletRequest
 * @param vitalsid int
 */
private void saveChanges(ActionErrors errors, HttpServletRequest request, int vitalsid, int contractid) {
	
	//AppLog.trace("ProcesPnCharges begin saveCharges for contract:"+contractid);
   	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
   	DatabaseTransaction t =null;
	DbPnContract acontract = null;
	TempPnCharges charges = new TempPnCharges((DbUser)sessionUser, contractid);

   	// make sure our session is valid
   	if(sessionUser==null)	{
		 errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
	}
	// If errors found, bail out and return to input page
   	if( !errors.isEmpty() ){
		return ;
	}

	//Database Access Logic
	try{
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
		acontract = FdmsDb.getInstance().getPnContract(t, contractid);
		charges.getPnCharges(); // needed to calculate the total charges
		acontract.setTotalCharges( charges.getTotalCharges() );
		t.save();
		charges.saveCharges();
		charges.deleteTempTable();
	}
	catch(PersistenceException pe) { 
		logger.error("Persistence Exception in PnCharges do.Perform. " + pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));    
	}
	catch(Exception pe) { 
		logger.error("Exception in PnCharges .doPerform. ", pe);
		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));     
        } finally {
            if (t != null) t.closeConnection();
        }
	
}
}
