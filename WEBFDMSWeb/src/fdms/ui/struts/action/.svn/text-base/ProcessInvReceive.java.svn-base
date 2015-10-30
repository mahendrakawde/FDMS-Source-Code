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

import fdms.ui.struts.form.InventoryReceiveForm;

public class ProcessInvReceive extends Action {

	private Logger logger = Logger.getLogger(ProcessInvReceive.class.getName());

	private ArrayList formErrors;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

		formErrors = new ArrayList();
		InventoryReceiveForm form = (InventoryReceiveForm) actionForm;
		ActionErrors errors = new ActionErrors();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;
		DbInvOnHand dbInvOnHand = null;

		if (form.getDirective().equals("exit")) {
			ActionForward actionForward = mapping.findForward("showInventoryCatalog");
			if (!errors.isEmpty()) {
				// AppLog.info("ProcessInvReceive Invoking forward mapping
				// getInput()");
				saveErrors(request, errors);
				actionForward = new ActionForward(mapping.getInput());
			}
			return actionForward;
		}

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			// AppLog.trace("Show the directive: "+form.getDirective());

			if (form.getDirective().equals("save")) {
				validateData(form, errors, sessionUser);
				if (errors.isEmpty()) {
					if (form.getInventoryItemOnHandId() == null) {
						dbInvOnHand = new DbInvOnHand();
						dbInvOnHand.setNew();
						t.addPersistent(dbInvOnHand);
					} else {
						dbInvOnHand = FdmsDb.getInstance().getInvOnHand(t,
								FormatNumber.parseInteger(form.getInventoryItemOnHandId()));
					}
					setInvOnHand(t, form, dbInvOnHand, errors);
					setInvHistory(t, form, errors);
					if (errors.isEmpty()) {
						t.save();
					} else {
						// AppLog.criticalError("Exception in
						// ProcessInvReceive.setInvReceive. ");
					}
				} else {
					// AppLog.trace("Validation Errors in ProcessInvOnHand,
					// returning to InventoryReceive form.");
				}
			}
		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in ProcessInvReceive.doPerform. " + pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
		} catch (Exception pe) {
			logger.error("Exception in ProcessInvReceive.doPerform. ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
		} finally {
			if (t != null) {
				try {
					t.closeConnection();
				} catch (Exception e) {
					logger.error("Error in closeConnection() : ", e);
				}
			}
		}

		// Action Forward Logic
		logger.debug("Before setting actionForward");
		ActionForward actionForward = mapping.findForward("showInvReceive");

		if (!errors.isEmpty()) {
			logger.debug("ProcessInvReceive Invoking forward mapping getInput()");
			request.setAttribute("formErrors", formErrors);
			saveErrors(request, errors);
			actionForward = new ActionForward(mapping.getInput());
		}

		logger.debug("Leaving ProcessInvReceive");
		return actionForward;
	}

	/**
	 * Called from ProcessInvReceive, this Method sets the InvHistory record
	 * from the InventoryOnHand form values. If am error occurs, the error is
	 * stored in the errors collection. Creation date: (7/26/2002 1:19:30 PM)
	 */

	public void setInvHistory(DatabaseTransaction t, fdms.ui.struts.form.InventoryReceiveForm form, ActionErrors errors) {

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
			dbInvHistory.setCTranType("R");

			errorField = "TransactionDate";
			dbInvHistory.setCTransactionDate(FormatDate.convertToDateMMDDYYYY(form.getDateIn().replace('-', '/')));

			errorField = "ReferenceNo";
			dbInvHistory.setCReferenceNumber(form.getReferenceNumber());

			errorField = "Location";
			dbInvHistory.setCLocation(form.getLocation());

			errorField = "Quantity";
			dbInvHistory.setLQuantity(Integer.parseInt(form.getQuantity()));

			errorField = "Cost";
			dbInvHistory.setLPurchaseCost(FormatNumber.parseInteger(String.valueOf(FormatCurrency
					.convertToCurrency(form.getCost()))));

			errorField = "Description";
			dbInvHistory.setCDescription(form.getTransferDescription());

			errorField = "MasterId";
			dbInvHistory.setMasterId(FormatNumber.parseInteger(form.getInventoryMasterId()));

			t.addPersistent(dbInvHistory);

		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.set" + errorField));
		}

		return;

	}

	/**
	 * Called from ProcessInvReceive, this Method sets the InvOnHand record from
	 * the InventoryReceive form values. It returns a boolean of setStatus. If
	 * setStatus is true, the dbInvOnHand is set successfully. If setStatus is
	 * false, an error occured. Creation date: (7/22/2002 2:14:04 PM)
	 */

	public void setInvOnHand(DatabaseTransaction t, fdms.ui.struts.form.InventoryReceiveForm form,
			DbInvOnHand dbInvOnHand, ActionErrors errors) {

		String errorField = new String();

		try {
			// Populate invOnHand fields from the inventoryReceiveForm form
			// values
			errorField = "ItemName";
			dbInvOnHand.setCItemName(form.getItemName());

			errorField = "SerialNo";
			dbInvOnHand.setCSerialNumber(form.getSerialNumber());

			errorField = "InShowroom";
			dbInvOnHand.setCShowRoom('N');
			if (form.getInShowroom() == true) {
				dbInvOnHand.setCShowRoom('Y');
			}

			errorField = "DateIn";
			dbInvOnHand.setCDateIn(FormatDate.convertToDateMMDDYYYY(form.getDateIn()));

			errorField = "ReferenceNo";
			dbInvOnHand.setCInvoiceNumber(form.getReferenceNumber());

			errorField = "Location";
			dbInvOnHand.setCLocation(form.getLocation());

			errorField = "Quantity";
			if (form.getInventoryType().equals("#")) {
				dbInvOnHand.setQuantity(Integer.parseInt(form.getQuantity()));
			} else {
				if (form.getInventoryItemOnHandId() == null) {
					dbInvOnHand.setQuantity(Integer.parseInt(form.getQuantity()));
				} else {
					dbInvOnHand.setQuantity(dbInvOnHand.getQuantity() + Integer.parseInt(form.getQuantity()));
				}
			}

			errorField = "Cost";
			if (form.getCost() != null) {
				dbInvOnHand.setCost(FormatNumber.parseInteger(String.valueOf(FormatCurrency.convertToCurrency(form
						.getCost()))));
			}

			errorField = "Notes";
			if (form.getNotes() != null) {
				dbInvOnHand.setCNotes(form.getNotes().trim());
			}

			errorField = "MasterId";
			dbInvOnHand.setMasterId(Integer.parseInt(form.getInventoryMasterId()));

		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.set" + errorField));
		}

		return;

	}

	/**
	 * Called from ProcessInvReceive, this Method validates the data from the
	 * InventoryReceive form. If a validation error occurs, the error is stored
	 * in the errors collection. Creation date: (7/22/2002 2:15:10 PM)
	 */

	public void validateData(fdms.ui.struts.form.InventoryReceiveForm form, ActionErrors errors, DbUserSession sessionUser) {

		try {
			// Location
			if (form.getLocation() == null || form.getLocation().trim().equals("")) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullLocation"));
				formErrors.add("location");
			} else {
				if (form.getLocation().trim().equals("--Select--")) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullLocation"));
					formErrors.add("location");
				}
			}

			// Serial No
			if (form.getInventoryType().equals("#")) {
				if (form.getSerialNumber() == null || form.getSerialNumber().trim().equals("")) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullSerialNo"));
					formErrors.add("inventoryType");
				}

				DatabaseTransaction t = null;
				DbInvOnHand[] dbInvOnHand = null;
				
				try {
					t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
					dbInvOnHand = FdmsDb.getInstance().getInvOnHandForItem(t, 
											FormatNumber.parseInteger(form.getInventoryMasterId()), 
											FormatNumber.parseInteger(form.getSerialNumber()));
					
					 if(dbInvOnHand != null) {
							for(DbInvOnHand invOnHand : dbInvOnHand) {
								if(form.getSerialNumber().equals(invOnHand.getCSerialNumber())) {
									errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.duplicateSerialNo"));
									formErrors.add("inventoryType");
								}
							}
						}
				} catch (PersistenceException pe) {
					logger.error("Persistence Exception in ProcessInvReceive.validateData. " + pe);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
				} catch (Exception pe) {
					logger.error("Exception in ProcessInvReceive.validateData. ", pe);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
				} finally {
					if (t != null) {
						try {
							t.closeConnection();
						} catch (Exception e) {
							logger.error("Error in closeConnection() : ", e);
						}
					}
				}
			}

			// Date In
			if (form.getDateIn() == null || form.getDateIn().trim().equals("")) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullDateIn"));
				formErrors.add("dateIn");
			} else {
				try {
					FormatDate.convertToDate(form.getDateIn());
				} catch (Exception de) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.invalidDateIn"));
					formErrors.add("dateIn");
				}
			}

			// Edit Numerics
			try {
				// Cost is Required
				if (form.getCost() == null || form.getCost().trim().equals("")) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullCost"));
					formErrors.add("cost");
				} else {
					if (FormatCurrency.convertToCurrency(form.getCost()) == 0) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.invalidCost"));
						formErrors.add("cost");
					}
				}
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.invalidCost"));
				formErrors.add("cost");
			}

			try {
				// Quantity is required
				if (form.getQuantity() == null || form.getQuantity().trim().equals("")) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.nullQuantity"));
					formErrors.add("quantity");
				} else {
					if (FormatNumber.parseInteger(form.getQuantity()) <= 0) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.invalidQuantity"));
						formErrors.add("quantity");
					}
				}
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.inventory.invalidQuantity"));
			}

		} catch (Exception e) {
			logger.error("Catching errors in ProcessInvReceive.validateData. ", e);
		}
	}
}
