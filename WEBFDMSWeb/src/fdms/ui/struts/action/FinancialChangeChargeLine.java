package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.Iterator;

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

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbInvOnHand;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialChangeChargeLineForm;
import fdms.ui.struts.form.FinancialInformationForm;



public class FinancialChangeChargeLine extends Action {
    
    private Logger logger = Logger.getLogger(FinancialChangeChargeLine.class.getName());  
    
    /**
     * Called from FinancialInformation.JSP, this method takes a parameter
     * with the ID of the charge to be updated. A collection in the FinancialInfo form
     * is searched for the specified ID. The matching charge item is taken from the
     * collection to populate the form for FinancialChangeChargeLine.JSP
     * which allows the operator to update values for this one charge.
     * Creation date: (4/04/02 11:16:35 AM)
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
                                            
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t =null;
        DbVitalsDeceased dbVitalsDeceased = null;
        ArrayList serials = new ArrayList();
        int vitalsid= 0;
        
        if(sessionUser==null)	{
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("error.login.invalid"));
        } else {
            vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
            if (vitalsid<1){
                logger.debug("FinancialChangeCharge. Invalid VitalsID to process : " + vitalsid);
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
            }
        }
        
        logger.debug("FinancialChangeCharge processing : " + vitalsid);
        
        // Retrieve FinancialInformation form from session
        FinancialInformationForm financialInfo = (FinancialInformationForm) session.getAttribute("financialInformation");
        java.util.TreeMap items = (java.util.TreeMap)financialInfo.getLineItems();
        String itemId = (String)request.getAttribute("itemId");
        int changeKey = FormatNumber.parseInteger(itemId);
        // AppLog.trace("FinancialChangeCharge ID:"+changeKey);
        boolean keyFound=false;
        FinancialInformationLineItem fiLineItem = null;
        // Iterate through collection to find ID == changeKey
        // Key is sequence number for sorting purposes so can't use the direct get()
        //java.util.Iterator myIterator = items.values().iterator();
        //while (myIterator.hasNext() && !keyFound){
        //	fiLineItem = (FinancialInformationLineItem)myIterator.next();
        //	int itemid = FormatNumber.parseInteger(fiLineItem.getItemId());
        //	if (changeKey == itemid){
        //		keyFound=true;
        //	}
        //}
        // Changed to passing SEQ# as paramter so can get item from collection directly
        if (items.containsKey(new Integer(changeKey))){
            fiLineItem = (FinancialInformationLineItem)items.get(new Integer(changeKey));
            keyFound=true;
        }
        
        // Check if we found our desired item or not
        //AppLog.trace("FinancialChange. found="+keyFound+" Charge Line:"+fiLineItem);
        if (!keyFound){
            logger.debug("FinancialChange ID not found in collection: " + changeKey);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.session","FinancialChange ID not found in collection: "+changeKey));
        }
        
        // If errors found, bail out and return to input page
        if( !errors.isEmpty() ){
            logger.debug("FinancialChangeCharge Invoking forward mapping getInput().");
            saveErrors(request, errors );
            return (new ActionForward(mapping.getInput() ));
        }
        
        //Create Form Object
        FinancialChangeChargeLineForm financialChangeChargeLine = new FinancialChangeChargeLineForm();
        
        //Database Access Logic
        try{
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            FdmsDb fdmsdb = FdmsDb.getInstance();
            //DbFinancialSummary dbFinancialSummary = (DbFinancialSummary)fdmsdb.getFinancial(t,vitalsid);
            dbVitalsDeceased = (DbVitalsDeceased) fdmsdb.getVitalsDeceased(t, vitalsid);
            financialChangeChargeLine.setSequenceNumber( fiLineItem.getItemSequenceNumber() );
            //Unique Object Identifier from request parameter-- Will Not Be Shown on form
            financialChangeChargeLine.setId( itemId ); //fiLineItem.getItemId());
            // Type Code will be Shown with "Id" label User Can Modify
            financialChangeChargeLine.setTypeCode( fiLineItem.getItemTypeCode());
            financialChangeChargeLine.setDescription( fiLineItem.getItemTypeDescription() );
            financialChangeChargeLine.setPrice( fiLineItem.getItemPrice() );
            financialChangeChargeLine.setTaxCode( fiLineItem.getItemTaxCode());
            financialChangeChargeLine.setExemptDollars( fiLineItem.getItemExemptDollars());
            financialChangeChargeLine.setGlAccount(fiLineItem.getItemGLAccount());
            financialChangeChargeLine.setDeceasedFullName(dbVitalsDeceased.getDecFullName());
            financialChangeChargeLine.setStockType("");
            
            CompanyOptionsManager coMgr = new CompanyOptionsManager ();
            int financialDescriptionOption = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_TURN_OFF_FINANCIALDESCRIPTIONCHANGE);
            int financialPriceOption = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_TURN_OFF_FINANCIALPRICECHANGE);
            
            if (financialDescriptionOption == 1) {
            	financialChangeChargeLine.setFinancialDescriptionChange("off");
            }else {
            	financialChangeChargeLine.setFinancialDescriptionChange("on");
            }
            	
            if (financialPriceOption == 1) {
            	financialChangeChargeLine.setFinancialPriceChange("off");
            }else {
            	financialChangeChargeLine.setFinancialPriceChange("on");
            }
            
            // Can serial number be changed?
            financialChangeChargeLine.setSerialNumberModifiable("No");
            
            if (fiLineItem.getSerialNumberModifiable() > 0){
                financialChangeChargeLine.setSerialNumberModifiable("Yes");
            }
            
            //financialChangeChargeLine.setContractNumber(  dbVitalsDeceased.get);
            // make serial# collection if serial numbered Inventory item
            if ( fiLineItem.getDbChargeItem().getInvSeqNo() > 0){
                // AppLog.trace("Inventory master Item ID:"+fiLineItem.getDbChargeItem().getInvSeqNo());
                // AppLog.trace("Inventroy on-hand ID:"+fiLineItem.getDbChargeItem().getInvOnHandID());
                com.aldorsolutions.webfdms.beans.DbInvMaster dbInvMaster = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getInvMaster(t, fiLineItem.getDbChargeItem().getInvSeqNo() );
                if (dbInvMaster == null){
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException","Unable to retrieve inventory item."));
                } else if (dbInvMaster.getCStockType().equalsIgnoreCase(DbInvMaster.STOCK_TYPE_SERIAL)){
                    financialChangeChargeLine.setStockType(DbInvMaster.STOCK_TYPE_SERIAL);   
                    DbInvOnHand[] onhand = FdmsDb.getInstance().getInvOnHandForItem(t, fiLineItem.getDbChargeItem().getInvSeqNo());
                    
                    if (onhand==null){
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException","Error retrieving on-hand for: "+dbInvMaster.getCItemName()));
                    } else {
                        // AppLog.trace("Number of serial items:"+onhand.length);
                        financialChangeChargeLine.setSerialNumber(Integer.toString(fiLineItem.getDbChargeItem().getInvOnHandID()));       
                        int selectedSerial = fiLineItem.getDbChargeItem().getInvOnHandID(); 
                        
                       // CompanyOptionsManager coMgr = new CompanyOptionsManager ();
                        int qtyOption = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTIION_QTY);
                        if (qtyOption == 0) {
                        	serials.add(new OptionsList("0","- Select -"));
                        }
                        
                        for (int i=0; i<onhand.length; i++){
                            // AppLog.trace("Serial item:"+onhand[i].getCSerialNumber()+" #"+onhand[i].getId()+"::"+fiLineItem.getSerialNumber()+"|");
                        	
                        	Iterator myIterator = items.values().iterator();
                            FinancialInformationLineItem aCharge;
                            boolean hasNotSet = true;
                            while(myIterator.hasNext()) {
                            	aCharge = (FinancialInformationLineItem) myIterator.next();
                            	if (aCharge.getStockType().compareToIgnoreCase(DbInvMaster.STOCK_TYPE_SERIAL) == 0) {
                            		if (aCharge.getDbChargeItem().getInvSeqNo() == fiLineItem.getDbChargeItem().getInvSeqNo()) {
	                            		if (aCharge.getSerialNumber().compareToIgnoreCase(onhand[i].getCSerialNumber())==0){
	                            			hasNotSet = false;
	                            			
	                            			if (selectedSerial == onhand[i].getId()) {
	                            				hasNotSet = true;
	                            			}
	                            		}
                            		}
                            	}
                            	
                            }
                            if (hasNotSet){
                            	serials.add(new OptionsList(Integer.toString(onhand[i].getId()) ,onhand[i].getCSerialNumber()));
                            }

                        }
                    }
                }
            }
            
            //Put Form Bean into scopefinancialInformationLineItems
            request.setAttribute("financialChangeChargeLine", financialChangeChargeLine);
            request.setAttribute("serialNumbers", serials);
        } catch(PersistenceException pe) {
            logger.error("PersistenceException Error in doPerform() : ", pe);
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
        
        ActionForward actionForward =  mapping.findForward("financialChangeChargeLine");
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput() );
        }
        
        return  actionForward;
    }
}
