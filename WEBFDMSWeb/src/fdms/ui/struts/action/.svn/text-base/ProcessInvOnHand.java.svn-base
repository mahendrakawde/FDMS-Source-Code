package fdms.ui.struts.action;

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

import com.aldorsolutions.webfdms.beans.DbInvHistory;
import com.aldorsolutions.webfdms.beans.DbInvOnHand;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.InventoryOnHandForm;

public class ProcessInvOnHand extends Action {
    
    private Logger logger = Logger.getLogger(ProcessInvOnHand.class.getName());

    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        
        InventoryOnHandForm form = (InventoryOnHandForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbInvOnHand dbInvOnHand = null;
        
        if (form.getDirective().equals("redisplay")) {
            ActionForward actionForward = mapping.findForward("showInvOnHand");
            if (!errors.isEmpty()) {
                //AppLog.info("ProcessInvOnHand Invoking forward mapping getInput()");
                saveErrors(request, errors);
                actionForward = new ActionForward(mapping.getInput());
            }
            return  actionForward;
        }
        
        if (form.getDirective().equals("exit")) {
            ActionForward actionForward = mapping.findForward("showInventoryCatalog");
            if (!errors.isEmpty()) {
                //AppLog.info("ProcessInvOnHand Invoking forward mapping getInput()");
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
                
                validateData(form, errors);
                if (FormatCurrency.convertToCurrency(form.getCost()) != dbInvOnHand.getCost()) {
                    setInvHistory(t, form, errors);
                }
                if (errors.isEmpty()) {
                    if (setInvOnHand(form, dbInvOnHand)) {
                        t.save();
                    } else {
                        //AppLog.criticalError("Exception in ProcessInvOnHand.setInvOnHand. ");
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("ProcessInvOnHand.setInvOnHand"));
                    }
                } else {
                   // AppLog.trace("Validation Errors in ProcessInvOnHand, returning to InventoryOnHand form.");
                }
            }
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ProcessInvOnHand.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
        } catch(Exception pe) {
            logger.error("Exception in ProcessInvOnHand.doPerform. ", pe);
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
        ActionForward actionForward = mapping.findForward("showInvOnHand");
        if (!errors.isEmpty()) {
            //AppLog.info("ProcessInvOnHand Invoking forward mapping getInput() ");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput());
        }
        //AppLog.trace("Leaving ProcessInvOnHand");
        return actionForward;
    }
    /**
     * Called from ProcessInvOnHand, this Method sets the InvHistory record
     * from the InventoryOnHand form values. If am error occurs,
     * the error is stored in the errors collection.
     * Creation date: (7/26/2002 1:38:46 PM)
     */
    
    public void setInvHistory(DatabaseTransaction t, fdms.ui.struts.form.InventoryOnHandForm form, ActionErrors errors) {
        
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
            dbInvHistory.setCTranType("A");
            
            errorField = "TransactionDate";
            dbInvHistory.setCTransactionDate(FormatDate.convertToDateMMDDYYYY(form.getDateIn().replace('-','/')));
            
            errorField = "ReferenceNo";
            dbInvHistory.setCReferenceNumber(form.getReferenceNumber());
            
            errorField = "Location";
            dbInvHistory.setCLocation(form.getLocation());
            
            errorField = "Quantity";
            dbInvHistory.setLQuantity(Integer.parseInt(form.getQuantity()));
            
            errorField = "WholesaleCost";
            dbInvHistory.setLPurchaseCost(FormatNumber.parseInteger(String.valueOf(FormatCurrency.convertToCurrency(form.getCost()))));
            
            errorField = "Description";
            dbInvHistory.setCDescription("Cost Adjustment");
            
            errorField = "MasterId";
            dbInvHistory.setMasterId(FormatNumber.parseInteger(form.getInventoryMasterId()));
            
            t.addPersistent(dbInvHistory);
            
        } catch (Exception e) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.set" +errorField));
        }
        
        return;
        
    }
    /**
     * Called from ProcessInvOnHand, this Method sets the InvOnHand record
     * from the InventoryOnHand form values.  It returns a boolean of setStatus.
     * If setStatus is true, the dbInvOnHand is set successfully.  If setStatus is false,
     * an error occured.
     * Creation date: (7/19/2002 5:12:04 PM)
     */
    
    public boolean setInvOnHand(fdms.ui.struts.form.InventoryOnHandForm form, DbInvOnHand dbInvOnHand) {
        
        boolean setStatus = true;
        String errorField = new String();
        
        try {
            // Populate invOnHand fields from the inventoryOnHandForm form values
            errorField = "InShowroom";
            dbInvOnHand.setCShowRoom('N');
            if (form.getInShowroom() == true) {
                dbInvOnHand.setCShowRoom('Y');
            }
            errorField = "SerialNo";
            dbInvOnHand.setCSerialNumber(form.getSerialNumber());
            errorField = "ReferenceNo";
            dbInvOnHand.setCInvoiceNumber(form.getReferenceNumber());
            errorField = "Cost";
            if (form.getCost() != null) {
                dbInvOnHand.setCost(FormatNumber.parseInteger(String.valueOf(FormatCurrency.convertToCurrency(form.getCost()))));
            }
            errorField = "Notes";
            if (form.getNotes() != null) {
                dbInvOnHand.setCNotes(form.getNotes().trim());
            }
        } catch (Exception e) {
            logger.error("Catching exception in setInvOnHand ", e);
            setStatus = false;
        }
        return setStatus;
    }
    /**
     * Called from ProcessInvOnHand, this Method validates the data
     * from the InventoryOnHand form. If a validation error occurs,
     * the error is stored in the errors collection.
     * Creation date: (7/19/2002 5:44:10 PM)
     */
    
    public void validateData(fdms.ui.struts.form.InventoryOnHandForm form, ActionErrors errors) {
        
        try {
            // Serial No
            if (form.getInventoryType().equals("#")) {
                if (form.getSerialNumber() == null || form.getSerialNumber().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullSerialNo"));
                }
            }
            
            // Edit Numerics
            try {
                // Cost is Required
                if (form.getCost() == null || form.getCost().trim().equals("")) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullCost"));
                } else {
                    if (FormatCurrency.convertToCurrency(form.getCost()) == 0) {
                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.invalidCost"));
                    }
                }
            } catch (Exception e) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.invalidCost"));
            }
        } catch (Exception e) {
            logger.error("Catching errors in ProcessInvOnHand.validateData. ", e);
        }
    }
}
