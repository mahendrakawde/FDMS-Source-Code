package fdms.ui.struts.action;

import java.util.TreeMap;

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

import com.aldorsolutions.webfdms.beans.DbInvOnHand;
import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.InventorySold;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialChangeChargeLineForm;
import fdms.ui.struts.form.FinancialInformationForm;


public class ProcessFinancialChangeChargeLine extends Action {
    
    private Logger logger = Logger.getLogger(ProcessFinancialChangeChargeLine.class.getName());

    /**
     * Called from FinancialChangeChargeLine.jsp. This action updates the collection
     * item with the changes from the form. Then, recalculate the total charges and
     * redisplay the main financial information page.
     */
    public ActionForward execute(
        ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
            
        FinancialChangeChargeLineForm form = (FinancialChangeChargeLineForm) actionForm;
        logger.debug("*** Entering ProcessFinancialChangeChargeLine ***");
        logger.debug("Updating ItemId : " + form.getId());
            
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DatabaseTransaction t = null;
        FinancialInformationForm financialInfo =
            (FinancialInformationForm) session.getAttribute("financialInformation");
        TreeMap items = (TreeMap) financialInfo.getLineItems();
        
        // Iterate through line item collection to find the one being updated
        //java.util.Iterator myIterator = items.values().iterator();
        int changeKey = FormatNumber.parseInteger(form.getId());
        boolean keyFound = false;
        FinancialInformationLineItem fiLineItem = null;
        //while (myIterator.hasNext() && !keyFound){
        //	fiLineItem = (FinancialInformationLineItem)myIterator.next();
        //	int itemid = FormatNumber.parseInteger(fiLineItem.getItemId());
        //	if (changeKey == itemid){
        //		keyFound=true;
        //	}
        //}
        
        if (items.containsKey(new Integer(changeKey))){
            fiLineItem = (FinancialInformationLineItem)items.get(new Integer(changeKey));
            keyFound = true;            
        }
        
        logger.debug("Key found : " + keyFound);
        
        // Check if we found our desired item or not
        // AppLog.trace("ProcessFinancialChange. found="+keyFound+" Charge Line:"+fiLineItem);
        if (!keyFound){
            logger.debug("ProcessFinancialChange ID not found in collection: "
                        + changeKey);
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.session",
                    "FinancialChange ID not found in collection: "+changeKey)
                );
        }
        
        // get user and case ID
        DbUserSession sessionUser =  SessionHelpers.getUserSession(request);
        DbInvOnHand onhand = null;
        if(sessionUser == null)	{
            errors.add(ActionErrors.GLOBAL_ERROR, 
                new ActionError("error.login.invalid"));
        }
        
        // Get serial numbered item selected ID
        int onhandID = FormatNumber.parseInteger(form.getSerialNumber());
        logger.debug("Serial Number Id : " + onhandID);
        
        // If errors found, bail out and return to input page
        if(!errors.isEmpty()){
            logger.debug("FinancialChangeCharge Errors found. returning to financial page");
            saveErrors(request, errors );
            return mapping.findForward("financialInformation");
        }
        
        // if serial numbered item, retrieve serial number selected
        try {
            logger.debug("ProcessFinancialChangeCharge selected onhand : "
                         + onhandID + " " + form.getSerialNumber());
            
            if (onhandID > 0){
                t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                onhand = FdmsDb.getInstance().getInvOnHand(t, onhandID);
                if (onhand != null){
                    logger.debug("Inventory on hand != null");
                    fiLineItem.setSerialNumber(onhand.getCSerialNumber());
                    fiLineItem.getDbChargeItem().setInvOnHandID(onhandID);
                } else {
                  logger.error("ProcessFinancialChangeChargeLine. Failed to retrieve onhand: " + onhandID);
                }
            }
        } catch(PersistenceException pe) {
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, 
                new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
                try {
                    t.closeConnection();
                    t = null;
                }  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }
        
        // If errors found, bail out and return to input page
        if( !errors.isEmpty() ){
            logger.debug("FinancialChangeCharge Invoking forward mapping getInput()");
            saveErrors(request, errors );
            return mapping.findForward("financialInformation");
        }
        
        logger.debug("Line item setting itemTypeCode : " + form.getTypeCode());
        fiLineItem.setItemTypeCode(form.getTypeCode());        
        logger.debug("Line item setting itemTypeDescription : " + form.getDescription());
        fiLineItem.setItemTypeDescription(form.getDescription());
        logger.debug("Line item setting itemTaxCode : " + form.getTaxCode());
        fiLineItem.setItemTaxCode(form.getTaxCode());
        
        try{
            logger.debug("Line item setting itemPrice : " + form.getPrice());
            fiLineItem.setItemPrice(form.getPrice());
            logger.debug("Line item setting itemExemptDollars : " + form.getExemptDollars());
            fiLineItem.setItemExemptDollars(form.getExemptDollars());
            
            // Calculate default GL account for display purposes only at this point
            // GL will be recaclulated when and if saving financial page.
            // If DbCharge object already has a GL account then use it instead of the default.
            if (fiLineItem.getDbChargeItem().getGlAcct() == null
                || fiLineItem.getDbChargeItem().getGlAcct().compareTo("        ") <=0 ){
                InventorySold soldData = new InventorySold();
                try {
                    t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
                    FdmsDb.getInstance().getDefaultGlSalesAccts(
                        t,
                        soldData,
                        sessionUser.getRegion(), 
                        financialInfo.getChapelLocation(),  
                        String.valueOf(fiLineItem.getDbChargeItem().getItemCategoryType()), 
                        financialInfo.getSaleType(), 
                        financialInfo.getDisposition());
                    
                } catch (Exception e) {
                    logger.error("Error : ", e);
                } finally {
                    if (t != null) {
                        try {
                            t.closeConnection();
                            t = null;
                        }  catch (Exception e) {
                            logger.error("Error in closeConnection() : ", e);
                        }
                    }
                }               
                fiLineItem.setItemGLAccount(soldData.getAcctRevenue() );
            }
            
            // Calculate sales tax amount
            try {
            	// not calculate sale tax if it is the saletax line
//            	if ( (fiLineItem.getItemTypeCode().compareToIgnoreCase("99") != 0) &&  
//            			(fiLineItem.getItemTypeCode().compareToIgnoreCase("98") != 0) &&
//            			(fiLineItem.getItemTypeCode().compareToIgnoreCase("97") != 0)) {
	                FdmsDb.getInstance().calculateSalesTax(
	                    sessionUser,
	                    items,
	                    financialInfo.getPriceListVersion(),
	                    fiLineItem.getDbChargeItem().getVitalsID());
//            	}
                
            } catch (Exception e){
                logger.error("Error in sales tax routine : ", e);
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",e.getMessage()));
                saveErrors(request, errors );
                return mapping.findForward("financialInformation");
            }
            
            int totalcharges = SessionHelpers.sumCharges(items);
            logger.debug("Total charges : " + totalcharges);
            financialInfo.setTotalCharge(FormatCurrency.toCurrency((long)totalcharges));
            // In case sequence number changes, we need to alter the collection key
            // for this item. Remove old key and add new one.
            int oldSeqNo = changeKey; //FormatNumber.parseInteger(fiLineItem.getItemSequenceNumber());
            int newSeqNo = 0;
            int orderByContLine  = 0;
                 
            try {
            	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            	DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, sessionUser.getRegion());
    			orderByContLine  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, sessionUser.getRegion(), 
    					DbLocaleConfigType.CONFIG_ORDER_BY_CONT_LINE_ON_FINANCIAL_PAGE);
            } catch (Exception e) {
                logger.error("Error : ", e);
            } finally {
                if (t != null) {
                    try {
                        t.closeConnection();
                        t = null;
                    }  catch (Exception e) {
                        logger.error("Error in closeConnection() : ", e);
                    }
                }
            }               	
    		try {	
            	if (orderByContLine == 1){
					newSeqNo = new Integer(form.getTypeCode()+""+form.getSequenceNumber());
				}else {
					newSeqNo = Integer.parseInt(form.getSequenceNumber());
				}
               
            } catch(Exception pe) {
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.ui.financial.seqno"));
                saveErrors(request, errors );
                return (new ActionForward(mapping.getInput()));
            }   

            logger.debug("Old Seq. # : " + oldSeqNo);
            logger.debug("New Seq. # : " + newSeqNo);
                
            if (oldSeqNo != newSeqNo){
                // replace collection entry with new sequence numb er
                // so entries get sorted correctly
                Object temp = items.remove(new Integer(oldSeqNo));                
                logger.debug("ProcessChangeCharge. Remove old seq# " + oldSeqNo
                            +"\nResult:" + temp.toString());
   
                items.put(new Integer(newSeqNo),fiLineItem);
                logger.debug("ProcessChangecharge. Added new seq# " + newSeqNo);
            }                    
            
            logger.debug("Line items setting itemSequenceNumber : " + form.getSequenceNumber());
            fiLineItem.setItemSequenceNumber(form.getSequenceNumber());
            
        } catch (Exception e) {
            logger.error("Error in doPerform() : ", e);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.financial.amount"));
        } finally {
            if (t != null) {
                t.closeConnection();
                t = null;
            }
        }
        
        logger.debug("ProcessFinancialChangeCharge ended. Modified flag : " + fiLineItem.isModifiedItem()
                    + " new flag:" + fiLineItem.isNewItem());
        
        ActionForward actionForward = mapping.findForward("financialInformation");
        
        if( !errors.isEmpty() )	   {
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput());
        }
        
        return  actionForward;
    }
}
