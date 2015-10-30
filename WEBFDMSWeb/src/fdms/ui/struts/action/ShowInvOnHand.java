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
import fdms.ui.struts.form.InventoryOnHandForm;

public class ShowInvOnHand extends Action {

	private Logger logger = Logger.getLogger(ShowInvOnHand.class.getName());

	/**
	 * Called from the InventoryCatalog.jsp, this action handles the View On
	 * Hand submit button.
	 * 
	 * @return ActionForward
	 * 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

		InventoryCatalogForm form = (InventoryCatalogForm) actionForm;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;
		DbInvMaster dbInvMaster = null;
		DbInvOnHand[] dbInvOnHand = null;
		InventoryOnHandForm inventoryOnHand = new InventoryOnHandForm();
		java.util.ArrayList invOnHandDisplayList = new java.util.ArrayList();
		String currentlySelectedItem = "0";

		if (sessionUser == null) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
		}

		// AppLog.trace("Inventory item ID is " +form.getInventoryMasterId());

		if (request.getParameter("inventoryOnHandItem") == null) {
			currentlySelectedItem = "0";
			// AppLog.trace("Coming into ViewOnHand from InventoryCatalog
			// form.");
		} else {
			currentlySelectedItem = request.getParameter("inventoryOnHandItem");
			// AppLog.trace("Coming into ViewOnHand from InventoryOnHand form
			// with id: " +currentlySelectedItem);
		}

		// Database Access Logic
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);

			// Defaults from invMaster
			dbInvMaster = FdmsDb.getInstance().getInvMaster(t, FormatNumber.parseInteger(form.getInventoryMasterId()));
			inventoryOnHand.setInventoryMasterId(String.valueOf(dbInvMaster.getId()));
			inventoryOnHand.setItemName(dbInvMaster.getCItemName());
			inventoryOnHand.setItemDescription(dbInvMaster.getCDescription());
			inventoryOnHand.setInventoryType(dbInvMaster.getCStockType());
			inventoryOnHand.setSaveButtonOn("true");

			// InvOnHand List
			dbInvOnHand = FdmsDb.getInstance().getInvOnHandForItem(t, dbInvMaster.getId());
			// AppLog.trace("dbInvOnHand length is: "+ dbInvOnHand.length);

			// Populate the Form Objects
			for (int i = 0; i < dbInvOnHand.length; i++) {

				// Serial Items
				if (dbInvMaster.getCStockType().equals("#")) {
					invOnHandDisplayList.add(new OptionsList(String.valueOf(dbInvOnHand[i].getId()), dbInvOnHand[i]
							.getCSerialNumber()
							+ " " + dbInvOnHand[i].getCDateIn() + " " + dbInvOnHand[i].getCLocation()));
					if (dbInvOnHand[i].getId() == FormatNumber.parseInteger(currentlySelectedItem)) {
						inventoryOnHand.setInventoryItemOnHandId(String.valueOf(dbInvOnHand[i].getId()));
						inventoryOnHand.setSerialNumber(dbInvOnHand[i].getCSerialNumber());
						inventoryOnHand.setLocation(dbInvOnHand[i].getCLocation());
						inventoryOnHand.setReferenceNumber(dbInvOnHand[i].getCInvoiceNumber());
						inventoryOnHand.setQuantity(String.valueOf((int) dbInvOnHand[i].getQuantity()));
						inventoryOnHand.setNotes(dbInvOnHand[i].getCNotes());
						if (dbInvOnHand[i].getCShowRoom() == 'Y') {
							inventoryOnHand.setInShowroom(true);
						} else {
							inventoryOnHand.setInShowroom(false);
						}
						inventoryOnHand.setCost(FormatCurrency.toCurrency(dbInvOnHand[i].getCost()));
						inventoryOnHand.setDateIn(FormatDate.MDYtoMMDDYYYY(dbInvOnHand[i].getCDateIn()).replace('/',
								'-'));
						inventoryOnHand.setSaveButtonOn("false");
					}
				}

				// Stock items
				if (dbInvMaster.getCStockType().equals("S")) {
					invOnHandDisplayList.add(new OptionsList(String.valueOf(dbInvOnHand[i].getId()), "Qty: "
							+ dbInvOnHand[i].getQuantity() + " Date: " + dbInvOnHand[i].getCDateIn() + " Location: "
							+ dbInvOnHand[i].getCLocation()));
					inventoryOnHand.setInventoryItemOnHandId(String.valueOf(dbInvOnHand[i].getId()));
					inventoryOnHand.setSerialNumber(dbInvOnHand[i].getCSerialNumber());
					inventoryOnHand.setLocation(dbInvOnHand[i].getCLocation());
					inventoryOnHand.setReferenceNumber(dbInvOnHand[i].getCInvoiceNumber());
					inventoryOnHand.setQuantity(String.valueOf((int) dbInvOnHand[i].getQuantity()));
					inventoryOnHand.setNotes(dbInvOnHand[i].getCNotes());
					if (dbInvOnHand[i].getCShowRoom() == 'Y') {
						inventoryOnHand.setInShowroom(true);
					} else {
						inventoryOnHand.setInShowroom(false);
					}
					inventoryOnHand.setCost(FormatCurrency.toCurrency(dbInvOnHand[i].getCost()));
					inventoryOnHand.setDateIn(FormatDate.MDYtoMMDDYYYY(dbInvOnHand[i].getCDateIn()).replace('/', '-'));
					inventoryOnHand.setSaveButtonOn("false");
				}
			}

			if (dbInvOnHand.length == 0) {
				invOnHandDisplayList.add(new OptionsList("0", "No Inventory On Hand information found"));
				// AppLog.trace("Setting display to No Data Found.");
			}

			// AppLog.trace("Finished setting inventoryOnHand form bean");

		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in ShowInvOnHand.doPerform. " + pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
		} catch (Exception pe) {
			logger.error("Exception in ShowInvOnHand.doPerform. ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
		} finally {
			if (t != null)
				t.closeConnection();
		}

		// Set Form Bean Into Scope
		session.setAttribute("inventoryOnHand", inventoryOnHand);
		session.setAttribute("invOnHandDisplayList", invOnHandDisplayList);
		// AppLog.trace("Set inventoryOnHand form bean and invOnHandDisplayList
		// into session scope");

		ActionForward actionForward = mapping.findForward("inventoryOnHand");
		if (!errors.isEmpty()) {
			// AppLog.info("ShowInvOnHand invoking forward mapping getInput()");
			saveErrors(request, errors);
			actionForward = new ActionForward(mapping.getInput());
		}

		return actionForward;

	}
}
