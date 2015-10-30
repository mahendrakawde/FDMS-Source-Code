package fdms.ui.struts.action;

import java.util.ArrayList;

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

import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.InventorySold;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialInformationForm;
import fdms.ui.struts.form.FinancialSelectPackagesForm;

public class ProcessInsertPackage extends Action {
    
    private Logger logger = Logger.getLogger(ProcessInsertPackage.class.getName());
    private ArrayList formErrors;
    
    /**
     * Called from the FinancialSelectPackages.jsp, this action handles the
     * submit buttons.  The submit buttons are as follows:
     * 1) select: adds the items in the package to the FinancialLineItems
     * 2) done:   Returns to FinancialInformation.jsp
     *
     * @return ActionForward
     * Created 11/26/02 4:41PM, 030309
     */
    
    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        
        formErrors = new ArrayList();
        FinancialSelectPackagesForm form = (FinancialSelectPackagesForm) actionForm;        
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        com.aldorsolutions.webfdms.beans.DbChargeItem dbChargeItem = null;
        com.aldorsolutions.webfdms.beans.DbPackageList[] dbPackageList = null;
        String submitType = form.getSubmitButton();
        int vitalsid = 0;
        
        // See if vitalsId is set in form
        try {
            if (form.getVitalsId() != null && Integer.parseInt(form.getVitalsId()) > 0) {
                vitalsid = Integer.parseInt(form.getVitalsId());
                SessionHelpers.setVitalsIdInRequest(request, vitalsid);
            }
        } catch (Exception e) {
            logger.debug("Error : " + e);
        }
        
        // Get Vitals Case ID
        vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        if (vitalsid < 1) {
            logger.debug("ProcessInsertPackage. Invalid VitalsID to process : " + vitalsid);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
        }
        
        if (submitType.equals("exit")) {
            logger.debug("ProcessInsertPackage - exiting.");
            return mapping.findForward("financialInformation");
        } else {
            if (submitType.equals("save") && (form.getFinancialPackage() == null || form.getFinancialPackage().trim().length() == 0)) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.table.noselect"));
                formErrors.add("financialPackage");
            }
        }
        
        // If errors found, bail out and return to input page
        if (!errors.isEmpty()) {
            logger.error("ProcessInsertPackage Invoking forward mapping getInput().");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            //return (new ActionForward(mapping.getInput()));
            return mapping.findForward("showInsertPackages");
        }
        
        // Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            FinancialInformationForm financialInfo = (FinancialInformationForm)session.getAttribute("financialInformation");
            java.util.TreeMap chargeSet = financialInfo.getLineItems();
            dbPackageList = FdmsDb.getInstance().getPackageListForKey(t, form.getPriceListVersion(), FormatNumber.parseInteger(form.getFinancialPackage()));
            
            DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, sessionUser.getRegion());
			int orderByContLine  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
					DbLocaleConfigType.CONFIG_ORDER_BY_CONT_LINE_ON_FINANCIAL_PAGE);
			
            // Process the selected package
            for (int i=0; i < dbPackageList.length; i++) {
                logger.debug("Processing Insert Package items" +i);
                int priceId = dbPackageList[i].getPriceListId();
                //Retreive the pricelist record for this item
                PriceListManager plm = new PriceListManager();
                com.aldorsolutions.webfdms.beans.DbPriceList priceList = plm.getPriceListItem(t, priceId );
                
                if (priceList == null) {
                    logger.debug("Inserting Package Items : Received invalid price list table ID: " + priceId);
                }
                else {
                	String status = String.valueOf(priceList.getActive());
                	
                	if (status.compareToIgnoreCase("N") == 0) {
                		logger.debug("Inserting Package Items : The item is not active: " + priceId);
                	}
                	else {
	                    //Construct a new DbChargeItem object for this DbPriceList
	                    dbChargeItem = new com.aldorsolutions.webfdms.beans.DbChargeItem(
	                    		vitalsid, 
	                    		priceList.getInvoiceSeqNo(), 
	                    		0, 
	                    		(int)priceList.getContrLine(), 
	                    		priceList.getContrDescr(),
	                    		(int)priceList.getPrice(),
	                    		priceList.getGlAcctNo(),
	                    		(int)priceList.getTaxExempt(),
	                    		priceList.getTaxCode(),
	                    		'S',
	                    		"",
	                    		priceList.getCategory(),
	                    		(int)0,
	                    		(int)priceList.getPrice(),
	                    		priceList.getRecID(),
	                    		true,
	                    		FormatNumber.parseInteger(form.getFinancialPackage()));
	                    
	                    // get valid sequence number
	                    int uniqueSeqNo = SessionHelpers.calculateSequenceNumber(chargeSet, dbChargeItem);
	                    dbChargeItem.setSequenceNumber(uniqueSeqNo);
	                    
	                    if ( (dbChargeItem.getGlAcct().length() <= 1) || (dbChargeItem.getGlAcct().compareToIgnoreCase("") == 0) || (dbChargeItem.getGlAcct() == null) || (dbChargeItem.getGlAcct().compareTo("        ")== 0) ) {
	                    	FdmsDb fdmsdb = FdmsDb.getInstance();
	                    	DbCase caseinfo = fdmsdb.getCase(t, vitalsid);
	                    	DbFinancialSummary finan = fdmsdb.getFinancial(t, vitalsid);
	                    	DbVitalsFirstCall firstcall  = fdmsdb.getVitalsFirstCall(t, vitalsid);
	                    	InventorySold soldData = new InventorySold();
	                    	try {
								FdmsDb.getInstance().getDefaultGlSalesAccts(t, soldData, sessionUser.getRegion(),
										caseinfo.getChapelNumber(), String.valueOf(dbChargeItem.getItemCategoryType()),
										finan.getCFinSaleType(), firstcall.getDispositionCode());
								dbChargeItem.setGlAcct(soldData.getAcctRevenue());
	                    	}
	                    	catch (Exception pe){
	                    		// this mean the soldData is null then we are setting glacct as blank
	                    		dbChargeItem.setGlAcct("");
	                    	}
	                    }
	                    
	                    FinancialInformationLineItem collectionItem = new FinancialInformationLineItem(dbChargeItem);
	                    collectionItem.setModifiedItem(true); // added this session
	                    collectionItem.setNewItem(true);
	                    
	                    if (orderByContLine == 1){	
	                    	chargeSet.put(new Integer(collectionItem.getItemTypeCode()+""+uniqueSeqNo), collectionItem);
                        }
                        else {
                        	chargeSet.put(new Integer(uniqueSeqNo), collectionItem);
                        }
	                    
	                    
                	}
                }
                
            }
            
            // Sum new total of all charges in collection
            int totalcharges = SessionHelpers.sumCharges( chargeSet );
            financialInfo.setTotalCharge(FormatCurrency.toCurrency((long)totalcharges));
            financialInfo.setSaveNeeded(true);
            
            FdmsDb.getInstance().calculateSalesTax(
                sessionUser,
                chargeSet,
                financialInfo.getPriceListVersion(),
                vitalsid);           

        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ProcessInsertPackage do.Perform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in ProcessInsertPackage.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
                try {
                    t.closeConnection();
                }  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }
        
        if (!errors.isEmpty()) {
            logger.error("ProcessInsertPackage Invoking forward mapping getInput().");
            saveErrors(request, errors);
            request.setAttribute("formErrors", formErrors);
            //return (new ActionForward(mapping.getInput()));
            return mapping.findForward("showInsertPackages");
        }
        
        //AppLog.trace("ProcessInsertPackage - completed.");
        return mapping.findForward("financialInformation");
        // return mapping.findForward("showFinancialInformation");
    }
}
