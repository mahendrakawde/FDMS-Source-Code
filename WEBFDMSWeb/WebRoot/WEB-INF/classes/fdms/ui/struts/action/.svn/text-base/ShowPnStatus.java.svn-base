package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbArrangers;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbPnContract;
import com.aldorsolutions.webfdms.beans.DbPnContractPeer;
import com.aldorsolutions.webfdms.beans.DbPnContractSet;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.PersistentI;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;


public class ShowPnStatus extends Action {

    private Logger logger = Logger.getLogger(ShowPnStatus.class.getName());
    
/**
* This global action reads the DB and prepares the form bean and
* collections needed to show PNSTATUS.JSP.
* The case vitals ID must be a parameter.
*/
public ActionForward execute(ActionMapping mapping,
	ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	  throws javax.servlet.ServletException, java.io.IOException
  {
	ActionErrors errors = new ActionErrors();
	HttpSession session = request.getSession();
	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
	fdms.ui.struts.form.pnstatus preNeed = new fdms.ui.struts.form.pnstatus();
	DatabaseTransaction t = null;
	DbPreneed dbPreNeed = null;
	DbVitalsDeceased dbVitalsDeceased = null;
	DbVitalsFirstCall firstCall = null; 
	//com.aldorsolutions.webfdms.beans.DbLocation[] chapels = null;
	DbCase dbCase= null;
	ArrayList contracts = new ArrayList();
	ArrayList formsList = new ArrayList();
	ArrayList embalmReason1 = new ArrayList();
	ArrayList embalmReason2 = new ArrayList();
 
   	//Check for a DbUserSession
   	if (sessionUser == null) {
	   errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
	}
   	
	if (!errors.isEmpty()) {
	   //AppLog.info("ShowPnStatus Invoking forward mapping getInput().");
	   saveErrors(request, errors );
	   return mapping.findForward("showCaseList");
	}

	// Retrieve vitals ID from request parameter if available.
	// If not a parameter, check request attribute.
	// A vitalsid < 1 means we are adding a case so start with an empty JSP page.
	int vitalsid = 0;
	String idparam = request.getParameter("vitalsId");
	//AppLog.trace("ShowPnStatus: request ID:"+idparam+" attribute:"+SessionHelpers.getVitalsIdFromRequest(request));
	if (idparam != null){
		vitalsid = FormatNumber.parseInteger(idparam);
	}
	else {
		// Check Request Attribute for "vitalsId"
		vitalsid = SessionHelpers.getVitalsIdFromRequest(request);
	}
	//AppLog.trace("ShowPnStatus vitalsId = " +vitalsid);

	// Database Access Logic
	try {
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);

		// Set collections in session
		//session.setAttribute("counselorList", SessionHelpers.getSpeedDataOptionCollection(sessionUser, "Counselors"));
		SessionHelpers.setArrangerListInSession(request);

		// Create the Locations list
	  	SessionHelpers.setChapelListInSession(request);
	  	// set source list in session
		session.setAttribute("pnSourceList", SessionHelpers.getSpeedDataOptionCollection(sessionUser, "PnSource"));
		
		if (vitalsid < 1) {
		   preNeed.setDirective("add");
		   preNeed.setVitalsId("0");
		} else {
		   dbPreNeed = FdmsDb.getInstance().getPreneed(t, vitalsid);
		   //AppLog.trace("ShowPnStatus updating: " +dbPreNeed.getBuyerFirst() +" Middle: " +dbPreNeed.getBuyerMiddle() +" Last: " +dbPreNeed.getBuyerLast() +" status: " +dbPreNeed.getStatus());
		   dbVitalsDeceased = FdmsDb.getInstance().getVitalsDeceased(t, vitalsid);
		   dbCase = FdmsDb.getInstance().getCase(t, vitalsid);
	  	   firstCall = FdmsDb.getInstance().getVitalsFirstCall(t, vitalsid);
		   
		   //Populate the Form object
		   preNeed.setDirective("change");
		   preNeed.setVitalsId(String.valueOf(vitalsid));
		   preNeed.setPreNeedId("0");
		   preNeed.setArrangementDate(FormatDate.MDYtoMMDDYYYY(dbPreNeed.getArrangeDate()));
		   preNeed.setBuyerFirst(dbPreNeed.getBuyerFirst());
		   preNeed.setBuyerMiddle(dbPreNeed.getBuyerMiddle());
		   preNeed.setBuyerLast(dbPreNeed.getBuyerLast());
		   preNeed.setBuyerStreet(dbPreNeed.getBuyerStreet());
		   preNeed.setBuyerCity(dbPreNeed.getBuyerCity());
		   preNeed.setBuyerState(dbPreNeed.getBuyerState());
		   preNeed.setBuyerZipCode(dbPreNeed.getBuyerZip());
		   preNeed.setBuyerPhone(dbPreNeed.getBuyerPhone());
		   preNeed.setCoBuyerName(dbPreNeed.getCobuyer());
		   preNeed.setBuyerSsNo(FormatString.formatSocialSecurityNo(sessionUser.getLocaleCountry(), dbPreNeed.getBuyerSsNo()));
		   preNeed.setBuyerTitle(dbPreNeed.getBuyerTitle());
		   preNeed.setBuyerAptno(dbPreNeed.getBuyerAptNo());
		   preNeed.setCounselor(String.valueOf(firstCall.getArrangerID()));
		   // populate exclusively if arrangerId is inactive.
			DbArrangers arranger = FdmsDb.getInstance().getArrangerInactive(t,sessionUser.getRegion(),firstCall.getArrangerID());
			if(arranger != null ){
				List list = (List)request.getSession().getAttribute("counselorList");
				 if(list == null){
					 list = new ArrayList();
				 }
				 list.add(new OptionsList(Integer.toString(arranger.getId()),arranger.getName()));
				 request.getSession().setAttribute("counselorList",list); 
			}
		   preNeed.setSource(dbPreNeed.getSource());
		   preNeed.setBeneficiaryFirst(dbVitalsDeceased.getDecFName());
		   preNeed.setBeneficiaryMiddle(dbVitalsDeceased.getDecMName());
		   preNeed.setBeneficiaryLast(dbVitalsDeceased.getDecLName());
		   preNeed.setBeneficiaryStreet(dbVitalsDeceased.getDecResStreet());
		   preNeed.setBeneficiaryCity(dbVitalsDeceased.getDecResMailCity());
		   preNeed.setBeneficiaryState(dbVitalsDeceased.getDecResState());
		   preNeed.setBeneficiaryZipCode(dbVitalsDeceased.getDecResZip());
		   preNeed.setBeneficiaryPhone(dbVitalsDeceased.getDecResPhone());
		   preNeed.setBeneficiaryTitle(	 dbVitalsDeceased.getDecmrmrs());
		   preNeed.setBeneficiaryAptno(	 dbVitalsDeceased.getDecAptNo());
		   preNeed.setBeneficiarySocialSecurityNumber(FormatString.formatSocialSecurityNo(sessionUser.getLocaleCountry(), dbVitalsDeceased.getSSNo()));
		   preNeed.setMortuaryLocation(String.valueOf(dbCase.getChapelNumber()));
		   preNeed.setEmbalmReason(	firstCall.getEmbalmingReason());
		   preNeed.setEmbalmReason2(firstCall.getEmbalmingChargeReq());
		   // Set beneficiary same as buyer check box
		   if (dbPreNeed.getBeneSameAsBuyer().equalsIgnoreCase("Y")	){
			   preNeed.setBeneSameAsBuyer(true);
		   }
		   // Create list of contracts
		   contracts = getContractSelectList(t, vitalsid, preNeed);
		   // set status check boxes
		   if (dbPreNeed.getStatus().compareToIgnoreCase("A") == 0)
		      preNeed.setPreneedStatus("Active");
		   if (dbPreNeed.getStatus().compareToIgnoreCase("S") == 0)
		      preNeed.setPreneedStatus("Serviced");
		   if (dbPreNeed.getStatus().compareToIgnoreCase("C") == 0)
		      preNeed.setPreneedStatus("Cancelled");
		}
		// get list of Pre-need forms
		com.aldorsolutions.webfdms.beans.DbFormsAvailable[] list = FdmsDb.getInstance().getFormsAvailableForLocale(t,sessionUser.getRegion(),DbFormsAvailable.PRENEED_FORM); 
		for (int i=0; i < list.length; i++){
			int formid = list[i].getFormId();
			formsList.add( new OptionsList(Integer.toString(formid) ,list[i].getDescription()));
		}
		// create collection of embalming reasons
		embalmReason1 = SessionHelpers.getSpeedDataOptionCollection(sessionUser,"Embalm");
		// create collection of embalming charge reasons (reason #2)	
		embalmReason2 = SessionHelpers.getSpeedDataOptionCollection(sessionUser,"Embalm2");

		
	} catch(PersistenceException pe) { 
		logger.error("Persistence Exception in ShowPnStatus.doPerform. "+ pe);
	} catch(Exception pe) { 
		logger.error("Exception in ShowPnStatus.doPerform. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }

	// Check if print preview already exists in request.
 	// If not, make an empty one
 	com.aldorsolutions.webfdms.util.ReportPreview preview = (com.aldorsolutions.webfdms.util.ReportPreview)request.getAttribute("ReportPreview");
 	if (preview==null){
 		ShowCaseStatusForms.addEmptyReportPreviews(request);
 	}
	// Set the form and collections into request scope
	request.setAttribute("pnStatus", preNeed);
  	request.setAttribute("contractList", contracts);
	session.setAttribute("pnEmbReasonList",embalmReason1);
	session.setAttribute("pnEmbReason2List",embalmReason2);
  	session.setAttribute("preneedForms",formsList);
	
	if (!errors.isEmpty()) {
	   saveErrors(request, errors);
	}
	
	return mapping.findForward("PnStatusJsp");
   
  }                                              
/**
 * Make a list of pre-need contracts formatted for a select box.
 * since STRUTS automatically "escapes" ampersands, we cannot pad strings
 * and use the standard struts <html:options tags.
 * Instead, there is a string in the form to contain the set of regular
 * HTML option tages.
 * Creation date: (2/4/2003 4:32:13 PM)
 */
public static ArrayList getContractSelectList(DatabaseTransaction t, int vitalsid, fdms.ui.struts.form.pnstatus form ) 
		throws PersistenceException{
	java.util.Hashtable h = new java.util.Hashtable();
	DbPnContractSet set = new DbPnContractSet();
	DbPnContract	acontr = null;
  	ArrayList 		contracts	= new ArrayList();
	StringBuffer	optlist = new StringBuffer();
	
	// retrieve all contracts for this vitalsID
	h.put(DbPnContractPeer.VITALSID, new Integer(vitalsid));
	set.restore(t,h);
	PersistentI[] obs = set.getPersistents();
	StringBuffer displaystring;
	//AppLog.trace("getContractList count:"+obs.length);
	int j;
	for(int i=0; i<obs.length; i++) {
		acontr = (com.aldorsolutions.webfdms.beans.DbPnContract)obs[i];
		displaystring = new StringBuffer();
		displaystring.append(	FormatString.pad(Integer.toString(acontr.getContractNumber()),7,true));
			for (j=0; j<3; j++) displaystring.append("&nbsp;");
		displaystring.append(	FormatDate.convertDateToMM_DD_YYYY(acontr.getContractDate()));
		displaystring.append(	FormatString.pad(FormatCurrency.toCurrency((long)acontr.getTotalCharges()),12,true ));
		if (acontr.getStatus(t)==DbPnContract.CONTRACT_STATUS_ACTIVE){
			displaystring.append( " Active");
		}
		else if(acontr.getStatus(t)==DbPnContract.CONTRACT_STATUS_CANCELLED){
			displaystring.append( " Cancelled");
		}
		else if(acontr.getStatus(t)==DbPnContract.CONTRACT_STATUS_SERVICED){
			displaystring.append( " Serviced");
		}
			
		
		optlist.append("<option value=\""+Integer.toString(acontr.getId())+"\" ");
		if (form.getContractSelected() == null || form.getContractSelected().length()<1){
			// default selected contract to first one in list
			form.setContractSelected(Integer.toString(acontr.getId()));
			optlist.append(" SELECTED ");
		}
		optlist.append(">");
		optlist.append(displaystring.toString());
		optlist.append("</option>\n");
		
		contracts.add(new OptionsList(Integer.toString(acontr.getId()) ,displaystring.toString()));
	}
	if (obs.length==0){
		form.setContractList(" ");
	}
	else {
		form.setContractList(optlist.toString());
	}
	return contracts;
}
}
