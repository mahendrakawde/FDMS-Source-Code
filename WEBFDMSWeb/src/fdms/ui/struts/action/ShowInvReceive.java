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

import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbInvOnHand;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.InventoryCatalogForm;
import fdms.ui.struts.form.InventoryReceiveForm;

public class ShowInvReceive extends Action {
    
    private Logger logger = Logger.getLogger(ShowInvReceive.class.getName());
    
    /**
     * Called from the InventoryCatalog.jsp, this action handles the
     * Receive submit button.
     * @return ActionForward
     *
     */
    public ActionForward execute(ActionMapping mapping,
    ActionForm actionForm,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
        InventoryCatalogForm form = (InventoryCatalogForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        DbInvMaster dbInvMaster = null;
        DbSpeedData[] dbSpeedData = null;
        DbInvOnHand[] dbInvOnHand = null;
        InventoryReceiveForm inventoryReceive = new InventoryReceiveForm();
        java.util.ArrayList invOnHandDisplayList = new java.util.ArrayList();
        java.util.ArrayList selectList = new java.util.ArrayList();
        java.util.ArrayList invLocation = new java.util.ArrayList();
        
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        //AppLog.trace("Inventory item ID is " +form.getInventoryMasterId());
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            
            // Form Defaults
            inventoryReceive.setDateIn(FormatDate.getCurrentDateFormatedMMDDYYYY());
            inventoryReceive.setLocation("--Select--");
            
            // Defaults from invMaster
            dbInvMaster = FdmsDb.getInstance().getInvMaster(t, FormatNumber.parseInteger(form.getInventoryMasterId()));
            inventoryReceive.setInventoryMasterId(String.valueOf(dbInvMaster.getId()));
            inventoryReceive.setItemName(dbInvMaster.getCItemName());
            inventoryReceive.setItemDescription(dbInvMaster.getCDescription());
            inventoryReceive.setInventoryType(dbInvMaster.getCStockType());
            inventoryReceive.setCost(FormatCurrency.toCurrency(dbInvMaster.getLCost()));
            if (dbInvMaster.getCShowRoom().equals("Y")) {
                inventoryReceive.setInShowroom(true);
            }
            if (dbInvMaster.getCStockType().equals("#")) {
                inventoryReceive.setQuantity("1");
               // inventoryReceive.setQuantity("2");
            } else {
                inventoryReceive.setQuantity("0");
            }
            
            // invLocation List
            dbSpeedData = FdmsDb.getInstance().getSpeedData(sessionUser.getDbLookup(),sessionUser.getRegion(), "INVLOCTN");
            for (int i=0; i < dbSpeedData.length; i++) {
                invLocation.add(new OptionsList(dbSpeedData[i].getData(), dbSpeedData[i].getData()));
            }
            
            // InvOnHand List
            dbInvOnHand = FdmsDb.getInstance().getInvOnHandForItem(t, dbInvMaster.getId());
            //AppLog.trace("dbInvOnHand length is:  "+ dbInvOnHand.length);
            
            //Populate the Form Objects
            for (int i=0; i < dbInvOnHand.length; i++) {
                if (dbInvMaster.getCStockType().equals("#")) {
                    invOnHandDisplayList.add(new OptionsList(String.valueOf(dbInvOnHand[i].getId()), dbInvOnHand[i].getCSerialNumber() +" "+dbInvOnHand[i].getCDateIn() +" "+dbInvOnHand[i].getCLocation()));
                } else {
                    invOnHandDisplayList.add(new OptionsList(String.valueOf(dbInvOnHand[i].getId()), "Qty: " +dbInvOnHand[i].getQuantity() +" Date: "+dbInvOnHand[i].getCDateIn() +" Location: "+dbInvOnHand[i].getCLocation()));
                }
                if (i == (dbInvOnHand.length - 1)) {
                    inventoryReceive.setLocation(dbInvOnHand[i].getCLocation());
                    //inventoryReceive.setCost(FormatCurrency.toCurrency(dbInvOnHand[i].getCost()));
                    if (dbInvOnHand[i].getCShowRoom() == 'Y') {
                        inventoryReceive.setInShowroom(true);
                    } else {
                        inventoryReceive.setInShowroom(false);
                    }
                    if (dbInvMaster.getCStockType().equals("#")) {
//                        inventoryReceive.setDateIn(FormatDate.MDYtoMMDDYYYY(dbInvOnHand[i].getCDateIn()));
                        inventoryReceive.setReferenceNumber(dbInvOnHand[i].getCInvoiceNumber());
                   //Srini: 07-23-2007 stocked items and serialed items should behave similarly.
                   // always new row inserted in inonhand table for stocked item. so commented following 2 lines.     
               //     } else {
               //         inventoryReceive.setInventoryItemOnHandId(String.valueOf(dbInvOnHand[i].getId()));
                    }
                }
            }
            if (dbInvOnHand.length == 0) {
                invOnHandDisplayList.add(new OptionsList("0", "No Inventory On Hand information found"));
//                selectList.add(new OptionsList("--Select--", "--Select--"));
                //AppLog.trace("Setting display to No Data Found.");
            }
            //AppLog.trace("Finished setting inventoryReceive form bean");
        } catch (PersistenceException pe) {
            logger.error("Persistence Exception in ShowInvOnHand.doPerform. " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));

        } catch (Exception pe) {
            logger.error("Exception in ShowInvOnHand.doPerform. ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
        
        //Set Form Bean Into Scope
        session.setAttribute("inventoryReceive", inventoryReceive);
        session.setAttribute("invOnHandDisplayList", invOnHandDisplayList);
        session.setAttribute("invLocation", invLocation);
        session.setAttribute("selectList", selectList);
        //AppLog.trace("Set inventoryReceive form bean and invOnHandDisplayList into session scope");
        
        ActionForward actionForward = mapping.findForward("inventoryItemReceive");
        
        if (!errors.isEmpty()) {
            //AppLog.info("ShowInvReceive invoking forward mapping getInput()");
            saveErrors(request, errors);
            actionForward = new ActionForward(mapping.getInput());
        }
        
        return  actionForward;
        
    }
}
