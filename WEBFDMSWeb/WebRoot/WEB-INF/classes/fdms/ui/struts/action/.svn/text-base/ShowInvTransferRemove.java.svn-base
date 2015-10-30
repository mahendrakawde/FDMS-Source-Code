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
import fdms.ui.struts.form.InventoryTransferRemoveForm;

public class ShowInvTransferRemove extends Action {

    private Logger logger = Logger.getLogger(ShowInvTransferRemove.class.getName());

/**
 * Called from the InventoryCatalog.jsp, this action handles the
 * Remove submit button.
 * @return ActionForward
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
	DbInvOnHand[] dbInvOnHand = null;
	InventoryTransferRemoveForm inventoryTransferRemove = new InventoryTransferRemoveForm();
	java.util.ArrayList invOnHandDisplayList = new java.util.ArrayList();
	String currentlySelectedItem = "0";

	//AppLog.trace("Inventory item ID is " +form.getInventoryMasterId());

	if (request.getParameter("inventoryOnHandItem") == null) {
	   currentlySelectedItem = "0";
	   //AppLog.trace("Coming into InvTransferRemove from InventoryCatalog form.");
	} else {
	   currentlySelectedItem = request.getParameter("inventoryOnHandItem");
	   //AppLog.trace("Coming into InvTransferRemove from InvTransferRemoveform with id: " +currentlySelectedItem);
	}    
	 
	//Database Access Logic
	try {
	   t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);

	   // Defaults from invMaster
	   dbInvMaster = FdmsDb.getInstance().getInvMaster(t, FormatNumber.parseInteger(form.getInventoryMasterId()));
	   inventoryTransferRemove.setInventoryMasterId(String.valueOf(dbInvMaster.getId()));
	   inventoryTransferRemove.setItemName(dbInvMaster.getCItemName());
	   inventoryTransferRemove.setItemDescription(dbInvMaster.getCDescription());
	   inventoryTransferRemove.setInventoryType(dbInvMaster.getCStockType());
	   inventoryTransferRemove.setSaveButtonOn("true");

	   // InvOnHand List 
	   dbInvOnHand = FdmsDb.getInstance().getInvOnHandForItem(t, dbInvMaster.getId());
	   //AppLog.trace("dbInvOnHand length is:  "+ dbInvOnHand.length);
	  
	   //Populate the Form Objects                              
	   for (int i=0; i < dbInvOnHand.length; i++) {
	       
	      // Serial Items
		  if (dbInvMaster.getCStockType().equals("#")) {
			 invOnHandDisplayList.add(new OptionsList(String.valueOf(dbInvOnHand[i].getId()), dbInvOnHand[i].getCSerialNumber() +" "+dbInvOnHand[i].getCDateIn() +" "+dbInvOnHand[i].getCLocation())); 
			 if (dbInvOnHand[i].getId() == FormatNumber.parseInteger(currentlySelectedItem)) {
				inventoryTransferRemove.setInventoryItemOnHandId(String.valueOf(dbInvOnHand[i].getId()));
				inventoryTransferRemove.setSerialNumber(dbInvOnHand[i].getCSerialNumber());
				inventoryTransferRemove.setLocation(dbInvOnHand[i].getCLocation());
				inventoryTransferRemove.setPurchaseReference(dbInvOnHand[i].getCInvoiceNumber());
				inventoryTransferRemove.setQuantity(String.valueOf((int)dbInvOnHand[i].getQuantity()));
				inventoryTransferRemove.setNotes(dbInvOnHand[i].getCNotes());
				if (dbInvOnHand[i].getCShowRoom() == 'Y') {
	               inventoryTransferRemove.setInShowroom(true);
				} else {
	               inventoryTransferRemove.setInShowroom(false);
				}              
				inventoryTransferRemove.setPrice(FormatCurrency.toCurrency(dbInvMaster.getLPrice()));
				//inventoryTransferRemove.setTransferDate(FormatDate.getCurrentDateFormatedMMDDYYYY().replace('/','-'));
				inventoryTransferRemove.setTransferDate(FormatDate.getCurrentDateFormatedMMDDYYYY());
				inventoryTransferRemove.setSaveButtonOn("false");
			 }
		  }

		  // Stock items
		  if (dbInvMaster.getCStockType().equals("S")) {
			 invOnHandDisplayList.add(new OptionsList(String.valueOf(dbInvOnHand[i].getId()), "Qty: " +dbInvOnHand[i].getQuantity() +" Date: "+dbInvOnHand[i].getCDateIn() +" Location: "+dbInvOnHand[i].getCLocation())); 
			 inventoryTransferRemove.setInventoryItemOnHandId(String.valueOf(dbInvOnHand[i].getId()));
			 inventoryTransferRemove.setSerialNumber(dbInvOnHand[i].getCSerialNumber());
			 inventoryTransferRemove.setLocation(dbInvOnHand[i].getCLocation());
			 inventoryTransferRemove.setPurchaseReference(dbInvOnHand[i].getCInvoiceNumber());
			 inventoryTransferRemove.setQuantity("0");
			 inventoryTransferRemove.setNotes(dbInvOnHand[i].getCNotes());
			 if (dbInvOnHand[i].getCShowRoom() == 'Y') {
	            inventoryTransferRemove.setInShowroom(true);
			 } else {
	            inventoryTransferRemove.setInShowroom(false);
			 }
			 inventoryTransferRemove.setPrice(FormatCurrency.toCurrency(dbInvMaster.getLPrice()));
			 //inventoryTransferRemove.setTransferDate(FormatDate.getCurrentDateFormatedMMDDYYYY().replace('/','-'));
			 inventoryTransferRemove.setTransferDate(FormatDate.getCurrentDateFormatedMMDDYYYY());
			 inventoryTransferRemove.setSaveButtonOn("false");
		  }
	   }
	   
	   if (dbInvOnHand.length == 0) {
		  invOnHandDisplayList.add(new OptionsList("0", "No Inventory On Hand information found"));
		  //AppLog.trace("Setting display to No Data Found.");
	   }
	   
	   //AppLog.trace("Finished setting inventoryTransferRemove form bean");
	   
	} catch (PersistenceException pe) { 
	   logger.error("Persistence Exception in ShowInvTransferRemove.doPerform. ", pe);
	   errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
	} catch (Exception pe) { 
	   logger.error("Exception in ShowInvTransferRemove.doPerform. ", pe);
	   errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
        } finally {
            if (t != null) t.closeConnection();
        }
	  
	//Set Form Bean Into Scope
	session.setAttribute("inventoryTransferRemove", inventoryTransferRemove);
	session.setAttribute("invOnHandDisplayList", invOnHandDisplayList);
	//AppLog.trace("Set inventoryTransferRemove form bean and invOnHandDisplayList into session scope");
		
	ActionForward actionForward = mapping.findForward("inventoryItemTransferRemove");
        
	if (!errors.isEmpty()) {
	   //AppLog.info("ShowInvTransferRemove invoking forward mapping getInput()");
	   saveErrors(request, errors);
	   actionForward = new ActionForward(mapping.getInput());
	}         
	 
	return  actionForward;
	
  }  
}
