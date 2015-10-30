package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
	
import com.aldorsolutions.webfdms.beans.DbInvHistory;
import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbInvOnHand;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.InventorySold;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.InventoryTransferRemoveForm;

public class ProcessInvTransferRemove extends Action {
    
    private Logger logger = Logger.getLogger(ProcessInvTransferRemove.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        
        InventoryTransferRemoveForm form = (InventoryTransferRemoveForm) actionForm;
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbInvOnHand dbInvOnHand = null;
        DbInvOnHand[] dbInvOnHandList = null;
        DbInvMaster dbInvMaster = null;
        InventorySold	soldData = new InventorySold();
        
        int invOnHandTotal = 0;
        
        if (form.getDirective().equals("redisplay")) {
            ActionForward actionForward = mapping.findForward("showInvTransferRemove");
            if (!errors.isEmpty()) {
                //AppLog.info("ProcessInvTransferRemove Invoking forward mapping getInput()");
                saveErrors(request, errors);
                actionForward = new ActionForward(mapping.getInput());
            }
            return  actionForward;
        }
        
        if (form.getDirective().equals("exit")) {
            ActionForward actionForward = mapping.findForward("showInventoryCatalog");
            if (!errors.isEmpty()) {
                //AppLog.info("ProcessInvTransferRemove Invoking forward mapping getInput()");
                saveErrors(request, errors);
                actionForward = new ActionForward(mapping.getInput());
            }
            return  actionForward;
        }
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            //AppLog.trace("Show the directive: "+form.getDirective());
            
            if (form.getDirective().equals("save")) {
                dbInvOnHand = FdmsDb.getInstance().getInvOnHand(t, FormatNumber.parseInteger(form.getInventoryItemOnHandId()));
                dbInvMaster = FdmsDb.getInstance().getInvMaster(t, FormatNumber.parseInteger(form.getInventoryMasterId()));
                dbInvOnHandList = FdmsDb.getInstance().getInvOnHandForItem(t, dbInvMaster.getId());
                
                for(int i = 0; i < dbInvOnHandList.length; i++) {
    				// Stock items
    				if (dbInvMaster.getCStockType().equals("S")) {
    					// Get only positive value of quantity & sum that up
    					if(dbInvOnHandList[i].getQuantity() > 0) {
    						invOnHandTotal += dbInvOnHandList[i].getQuantity();
    					}
    				} else if (dbInvMaster.getCStockType().equals("#")) {
    					invOnHandTotal += 1;
    				}
                }
                
                //validateData(form, dbInvOnHand, errors);
                validateData(form, invOnHandTotal, errors);
                if (errors.isEmpty()) {
                    //String trandatemdy = FormatDate.convertToDateMMDDYYYY(form.getTransferDate().replace('-','/'));
                    String trandatemdy = FormatDate.convertToDateMMDDYYYY(form.getTransferDate());
                    int sellamount = FormatNumber.parseInteger(String.valueOf(FormatCurrency.convertToCurrency(form.getPrice())));
                    // 11/11/02 changed to use standard SELL method in FdmsDb
                    int ctr = 0;
                    int quantity = dbInvOnHandList[ctr].getQuantity();
                    int qtyToRemove = FormatNumber.parseInteger(form.getQuantity());
                    
                    if(dbInvMaster.getCStockType().equals("#")){  // process remove for Serial Number item with single quantity
                           	FdmsDb.getInstance().removeInventorySell(t, FormatNumber.parseInteger(form.getInventoryMasterId()), qtyToRemove, 
                    			DbInvHistory.TRAN_TYPE_INV_SALE, trandatemdy, form.getTransferCreditReference(), form.getLocation(),FormatNumber.parseInteger(form.getInventoryItemOnHandId()), form.getTransferDescription(), 0, form.getReason());
	                    
                    }else{ // process remove for Stocked item with multiple Quantity
                    /*
                    FdmsDb.getInstance().inventorySell(t,FormatNumber.parseInteger(form.getInventoryMasterId()), FormatNumber.parseInteger(form.getInventoryItemOnHandId()), qtyToRemove,
                    		DbInvHistory.TRAN_TYPE_INV_SALE,trandatemdy,form.getTransferCreditReference(), form.getLocation(), sellamount, form.getTransferDescription(),0,soldData,form.getReason());
                    */
	                    do {
	                    	if( (quantity - qtyToRemove) < 0 ) {
	                    		qtyToRemove -= quantity;
	                    		
	                    		FdmsDb.getInstance().removeInventorySell(t, FormatNumber.parseInteger(form.getInventoryMasterId()), quantity, 
	                    				DbInvHistory.TRAN_TYPE_INV_SALE, trandatemdy, form.getTransferCreditReference(), form.getLocation(), dbInvOnHandList[ctr].getId(), form.getTransferDescription(), 0, form.getReason());
	                        	// Repeat selling process for second lot of item
	                        } else {
	                        	FdmsDb.getInstance().removeInventorySell(t, FormatNumber.parseInteger(form.getInventoryMasterId()), qtyToRemove, 
	                        			DbInvHistory.TRAN_TYPE_INV_SALE, trandatemdy, form.getTransferCreditReference(), form.getLocation(), dbInvOnHandList[ctr].getId(), form.getTransferDescription(), 0, form.getReason());
	    	                    
	    	                    qtyToRemove = 0;
	                        }
	                    	
	                    	ctr++;
	                    	
	                    	if(ctr < dbInvOnHandList.length) {
	                    		quantity = dbInvOnHandList[ctr].getQuantity();
	                    	}
	                    	
	                    } while(qtyToRemove > 0);
                    
                    }
                    /*
                    if( (invOnHandTotal - dbInvOnHandList[0].getQuantity()) > 0 ) {
                    	// Repeat selling process for second lot of item
                    } else {
	                    FdmsDb.getInstance().inventorySell(t,FormatNumber.parseInteger(form.getInventoryMasterId()), FormatNumber.parseInteger(form.getInventoryItemOnHandId()), FormatNumber.parseInteger(form.getQuantity()),
	                    DbInvHistory.TRAN_TYPE_INV_SALE,trandatemdy,form.getTransferCreditReference(), form.getLocation(), sellamount, form.getTransferDescription(),0,soldData,form.getReason());
                    }*/
                    //	        setInvHistory(t, form, dbInvOnHand, errors);
                    //	        setInvOnHand(t, form, dbInvOnHand, errors);
                    if (errors.isEmpty()) {
                        t.save();
                    } else {
                        //AppLog.criticalError("Exception in ProcessInvTransferRemove.setInvReceive. ");
                    }
                } else {
                    //AppLog.trace("Validation Errors in ProcessInvTransferRemove, returning to InventoryItemTransferReceive form.");
                }
            }
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessInvTransferRemove.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getMessage()));
        } catch(Exception pe) {
            logger.error("Exception in ProcessInvTransferRemove.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
        } finally {
            if (t != null) {
                try {
                    t.closeConnection();
                }  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }
        
        //Action Forward Logic
        //AppLog.trace("Before setting actionForward");
        ActionForward actionForward = mapping.findForward("showInvTransferRemove");
        if (!errors.isEmpty()) {
            //AppLog.info("ProcessInvTransferRemove Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput());
        }
        //AppLog.trace("Leaving ProcessInvTransferRemove");
        return actionForward;
    }
    /**
     * Called from ProcessInvReceive, this Method sets the InvHistory record
     * from the InventoryItemTransferRemove form values. If am error occurs,
     * the error is stored in the errors collection.
     * Creation date: (7/26/2002 11:46:45 AM)
     */
    
    public void setInvHistory(DatabaseTransaction t, fdms.ui.struts.form.InventoryTransferRemoveForm form,
    DbInvOnHand dbInvOnHand, ActionErrors errors) {
        
        DbInvHistory dbInvHistory = null;
        String errorField = new String();
        
        try {
            dbInvHistory = new DbInvHistory();
            dbInvHistory.setNew();
            
            errorField = "ItemName";
            dbInvHistory.setCItemName(form.getItemName());
            
            errorField = "SerialNo";
            dbInvHistory.setCSerialNumber(form.getSerialNumber());
            
            errorField = "TransactionType";
            dbInvHistory.setCTranType("D");
            
            errorField = "TransactionDate";
            //dbInvHistory.setCTransactionDate(FormatDate.convertToDateMMDDYYYY(form.getTransferDate().replace('-','/')));
            dbInvHistory.setCTransactionDate(FormatDate.convertToDateMMDDYYYY(form.getTransferDate()));
            
            errorField = "ReferenceNo";
            dbInvHistory.setCReferenceNumber(form.getPurchaseReference());
            
            errorField = "Location";
            dbInvHistory.setCLocation(form.getLocation());
            
            errorField = "Quantity";
            dbInvHistory.setLQuantity(Integer.parseInt(form.getQuantity()));
            
            errorField = "SellingPrice";
            dbInvHistory.setLSellingPrice(FormatNumber.parseInteger(String.valueOf(FormatCurrency.convertToCurrency(form.getPrice()))));
            
            errorField = "WholesaleCost";
            dbInvHistory.setLPurchaseCost(dbInvOnHand.getCost());
            
            errorField = "SalesAcct";
            dbInvHistory.setCGLsalesAcct(form.getSalesAccount());
            
            errorField = "Description";
            dbInvHistory.setCDescription(form.getTransferDescription());
            
            errorField = "MasterId";
            dbInvHistory.setMasterId(FormatNumber.parseInteger(form.getInventoryMasterId()));
            
            t.addPersistent(dbInvHistory);
            
        } catch (Exception e) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.set" +errorField));
        }
        
        return;
        
    }
    /**
     * Called from ProcessInvTransferRemove, this Method sets the InvOnHand record
     * from the InventoryItemTransferRemove form values. If am error occurs,
     * the error is stored in the errors collection.
     * Creation date: (7/22/2002 2:14:04 PM)
     */
    
    public void setInvOnHand(DatabaseTransaction t, fdms.ui.struts.form.InventoryTransferRemoveForm form,
    DbInvOnHand dbInvOnHand, ActionErrors errors) {
        
        String errorField = new String();
        
        // Serial Items
        if (form.getInventoryType().equals("#")) {
            try {
                dbInvOnHand.remove();
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.set" +errorField));
            }
            return;
        }
        
        // Stock Items
        try {
            errorField = "TransferDate";
            //dbInvOnHand.setCDateIn(FormatDate.convertToDateMMDDYYYY(form.getTransferDate().replace('-','/')));
            dbInvOnHand.setCDateIn(FormatDate.convertToDateMMDDYYYY(form.getTransferDate()));
            
            errorField = "Quantity";
            dbInvOnHand.setQuantity(dbInvOnHand.getQuantity() - Integer.parseInt(form.getQuantity()));
            
        } catch (Exception e) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.set" +errorField));
        }
        
        return;
        
    }
    /**
     * Called from ProcessInvTransferRemove, this Method validates the data
     * from the InventoryItemTransferRemove form. If a validation error occurs,
     * the error is stored in the errors collection.
     * Creation date: (7/22/2002 2:15:10 PM)
     */
    
    /*public void validateData(fdms.ui.struts.form.InventoryTransferRemoveForm form, DbInvOnHand dbInvOnHand,
    ActionErrors errors) {*/
    public void validateData(fdms.ui.struts.form.InventoryTransferRemoveForm form, int invOnHandTotal,
    		    ActionErrors errors) {
        
        try {
            
            // TransferDate
            if (form.getTransferDate() == null || form.getTransferDate().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullTransferDate"));
            } else {
                try {
                    //FormatDate.convertToDate(form.getTransferDate().replace('-','/'));
                	FormatDate.convertToDate(form.getTransferDate());
                } catch (Exception de) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.invalidTransferDate"));
                }
            }
            
            // TransferDescription
            if (form.getTransferDescription() == null || form.getTransferDescription().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullTranDescription"));
            }
            
            // Reason
            if (form.getReason() == null || form.getReason().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullReason"));
            }
            
            
            // Edit Numerics
            try {
                // Price is Required
                if (form.getPrice() == null || form.getPrice().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullPrice"));
                } else {
                    if (FormatCurrency.convertToCurrency(form.getPrice()) == 0) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.invalidPrice"));
                    }
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.invalidPrice"));
            }
            
            try {
                // Quantity is required
                if (form.getQuantity() == null || form.getQuantity().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullQuantity"));
                } else {
                    // Quantity can not be zero or non-numeric
                    if (FormatNumber.parseInteger(form.getQuantity()) <= 0) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.invalidQuantity"));
                    } else {
                        // Quantity can not be larger than original quantity
                        //if (Integer.parseInt(form.getQuantity()) > dbInvOnHand.getQuantity()) {
                        if (Integer.parseInt(form.getQuantity()) > invOnHandTotal) {	
                            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.quantityTooLarge"));
                        }
                    }
                }
                
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.invalidQuantity"));
            }
            
        } catch (Exception e) {
            logger.error("Catching errors in ProcessInvTransferRemove.validateData. ", e);
        }
    }
}
