/*
 * Bug fixes
 * 
 * Ticket #  Description     			Date   			Author
 * ------------------------------------------------------------------
 * 5536   	Fix inventory Cost field 	19-Apr-2012  	Jigar Mistry
 */
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

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbInvChapelIndex;
import com.aldorsolutions.webfdms.beans.DbInvHistory;
import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbInvOnHand;
import com.aldorsolutions.webfdms.beans.DbLocale;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.InventorySold;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.DateUtility;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.InventoryCatalogForm;

public class ProcessInventoryCatalog extends Action {

	private Logger logger = Logger.getLogger(ProcessInventoryCatalog.class
			.getName());
	private ArrayList formErrors;

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		formErrors = new ArrayList();
		InventoryCatalogForm form = (InventoryCatalogForm) actionForm;
		ActionErrors errors = new ActionErrors();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;
		DbLocation[] dbLocation = null;
		LocaleDTO userLocale = null;
		DbInvMaster dbInvMaster = null;

		String directive = form.getDirective();

		try {
			t = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);
			logger.debug("Show the directive : " + form.getDirective());

			dbLocation = FdmsDb.getInstance().getLocationsForRegion(t,
					sessionUser.getRegion());
			userLocale = FdmsDb.getInstance().getLocale(
					sessionUser.getDbLookup(), sessionUser.getRegion());

			if (directive.equals("add")) {
				dbInvMaster = new DbInvMaster();
				dbInvMaster.setNew();
				validateData(t, form, errors, sessionUser);
				if (errors.isEmpty()) {
					if (!setInvMaster(form, dbInvMaster, userLocale)) {
						// AppLog.criticalError("Exception in ProcessInventoryCatalog.setInvMaster. ");
						errors
								.add(
										ActionErrors.GLOBAL_ERROR,
										new ActionError(
												"error in ProcessInventoryCatalog.setInvMaster."));
					} else {
						t.addPersistent(dbInvMaster);
						t.save();
						t.closeConnection();

						t = (DatabaseTransaction) DatabaseTransaction
								.getTransaction(sessionUser);
						updateInvChapelIndex(t, form, dbInvMaster, dbLocation);
						t.save();
					}
				}
			}

			if (directive.equals("change")) {
				dbInvMaster = FdmsDb.getInstance().getInvMaster(t,
						FormatNumber.parseInteger(form.getInventoryMasterId()));

				// Inactive the Inventory Item
				if (request.getParameter("submitbutton").equals("inactivate")) {
					if (dbInvMaster.getCDeleteCode().equals("D")) {
						dbInvMaster.setCDeleteCode(" ");
					} else {
						dbInvMaster.setCDeleteCode("D");
						// now we need to go to all record in the invonhand and
						// set the quantity to 0 of all records
						// also in the invhistory need to set the left of them
						// out.
//						DbInvOnHand[] dbInvOnHand = null;
//						dbInvOnHand = FdmsDb.getInstance().getInvOnHandForItem(
//								t, dbInvMaster.getId());
//						String trandatemdy = FormatDate.convertToDateMMDDYYYY(FormatDate
//								.getCurrentDateFormatedMMDDYYYY());
//						InventorySold	soldData = new InventorySold();
//						if (dbInvOnHand != null && dbInvOnHand.length > 0) {
//							for (int i = 0; i < dbInvOnHand.length; i++) {
//								FdmsDb.getInstance().inventorySell(
//										t,
//										FormatNumber.parseInteger(form
//												.getInventoryMasterId()),
//												dbInvOnHand[i].getId(),
//										(dbInvOnHand[i].getQuantity()),
//										DbInvHistory.TRAN_TYPE_INV_SALE,
//										trandatemdy,
//										"",
//										"Inactive Archive", 0,
//										"Inactive Item", 0,
//										soldData, "I");
//							}
//							for (int i = 0; i < dbInvOnHand.length; i++) {
//								DbInvOnHand onhand = null;
//								onhand = FdmsDb.getInstance().getInvOnHand(t, dbInvOnHand[i].getId());
//								onhand.setQuantity(0);
//								t.addPersistent(onhand);
//							}
//						}

					}
					updateInvChapelIndex(t, form, dbInvMaster,
							dbLocation);
					t.save();
				} else {
					validateData(t, form, errors, sessionUser);
					if (errors.isEmpty()) {
						if (!setInvMaster(form, dbInvMaster, userLocale)) {
							// AppLog.criticalError("Exception in ProcessInventoryCatalog.setInvMaster. ");
							errors
									.add(
											ActionErrors.GLOBAL_ERROR,
											new ActionError(
													"error in ProcessInventoryCatalog.setInvMaster."));
						} else {
							dbInvMaster.setDateModified(DateUtility
									.getCurrentDate());
							dbInvMaster.setTimeModified(DateUtility
									.getCurrentTime());

							updateInvChapelIndex(t, form, dbInvMaster,
									dbLocation);
							t.save();
						}
					}
				}
			}
		} catch (PersistenceException pe) {
			logger.error(
					"Persistence Exception in ShowInventoryCatalog.doPerform. "
							+ pe, pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.PersistenceException", pe.getCause()));
		} catch (Exception pe) {
			logger.error("Exception in ShowInventoryCatalog.doPerform. ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.GeneralException", pe.getMessage()));
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
		ActionForward actionForward = mapping.findForward("showInventory");
		if (!errors.isEmpty()) {
			logger
					.debug("ProcessInventoryCatalog Invoking forward mapping getInput()");
			saveErrors(request, errors);
			request.setAttribute("formErrors", formErrors);
			actionForward = new ActionForward(mapping.getInput());
		}
		return actionForward;
	}

	/**
	 * Called from ProcessInventoryCatalog, this Method sets the InvMaster
	 * record from the InventoryCatalog form values. It returns a boolean of
	 * setStatus. if setStatus is true, the dbInvMaster is set successfully. If
	 * setStatus is false, an error occured. Creation date: (7/16/2002 3:12:04
	 * PM)
	 */
	public boolean setInvMaster(InventoryCatalogForm form,
			DbInvMaster dbInvMaster, LocaleDTO userLocale) {

		boolean setStatus = true;
		String errorField = null;

		try {
			// Populate invMaster fields from the inventoryCatalog form values
			dbInvMaster.setImageUrl(form.getImageURL());
			errorField = "Product Category";
			dbInvMaster.setCProductLine(form.getProductCategory());
			errorField = "Item Name";
			dbInvMaster.setCItemName(form.getItemName().trim());
			errorField = "Item Description";
			dbInvMaster.setCDescription(form.getItemDescription().trim());
			errorField = "Notes";
			dbInvMaster.setNotes(form.getItemDescriptionNotes().trim());
			errorField = "Inventory Type";
			dbInvMaster.setCStockType(" ");
			if (form.getInventoryType().equals("stockedItem")) {
				dbInvMaster.setCStockType("S");
			}
			if (form.getInventoryType().equals("serialNumbered")) {
				dbInvMaster.setCStockType("#");
			}
			if (form.getInventoryType().equals("nonStockedItem")) {
				dbInvMaster.setCStockType("N");
			}
			errorField = "In Showroom";
			dbInvMaster.setCShowRoom("N");
			if (form.getInShowroom() == true) {
				dbInvMaster.setCShowRoom("Y");
			}
			errorField = "Supplier";
			dbInvMaster.setCSupplierCode(form.getSupplier());
			errorField = "Tax Code";
			dbInvMaster.setCTaxCode(form.getTaxCode());
			errorField = "Tax Exempt";
			if ((form.getTaxExemptAmount() != null)
					&& (!form.getTaxExemptAmount().trim().equals(""))) {
				dbInvMaster.setFTaxExempt(FormatNumber.parseFloat(String
						.valueOf(FormatCurrency.convertToCurrency(form
								.getTaxExemptAmount()))) / 100);
			}
			errorField = "Wholesale Cost";
			dbInvMaster.setLCost(FormatNumber.parseInteger(String
					.valueOf(FormatCurrency.convertToCurrency(form
							.getWholesaleCost()))));
			errorField = "Selling Price";
			dbInvMaster.setLPrice(FormatNumber.parseInteger(String
					.valueOf(FormatCurrency.convertToCurrency(form
							.getPriceSelling()))));
			errorField = "Minimum on Hand";
			dbInvMaster.setIMinimumOnHand(FormatNumber.parseShort(form
					.getMinimumOnHand()));
			errorField = "Reorder Quantity";
			dbInvMaster.setLReorderQuantity(FormatNumber.parseInteger(form
					.getReorderQuantity()));
			errorField = "Casket Type";
			dbInvMaster.setCasketType(form.getCasketType());
			errorField = "Casket Usage";
			dbInvMaster.setCasketUse(form.getCasketUsage());
			errorField = "Interior Description";
			dbInvMaster.setInterior(form.getInteriorDescription());
			errorField = "Exterior Categoryt";
			dbInvMaster.setExtCode(form.getCasketExt());
			errorField = "Exterior Description";
			dbInvMaster.setExterior(form.getExteriorDescription().trim());
			errorField = "Contract Line No";
			dbInvMaster.setContractLineNo(FormatNumber.parseShort(form
					.getContractLineNo()));
			errorField = "Sequence No";
			dbInvMaster.setSequenceNo(FormatNumber.parseInteger(form
					.getSequenceNo()));
			errorField = "Locale Number";
			dbInvMaster.setLocale(userLocale.getLocaleID());
			errorField = "Delete Code";
			dbInvMaster.setCDeleteCode(" ");
			errorField = "GL accounts";
			dbInvMaster.setAccountDescCDID(FormatNumber.parseLong(form
					.getAccountDescID()));
			errorField = "Account Description CDID";
			dbInvMaster.setCGLsalesAcct(form.getSalesGLAcct());
			dbInvMaster.setCGLcostAcct(form.getCostGLAcct());
			dbInvMaster.setCGLassetAcct(form.getAssetGLAcct());
			// dbInvMaster.setCommissionable(form.getCommissionable());
		} catch (Exception e) {
			logger.error("Catching exception in setInvMaster ", e);
			setStatus = false;
		}
		return setStatus;
	}

	/**
	 * Called from ProcessInventoryCatalog, this Method sets the InvChapelIndex
	 * records from the InventoryCatalog form values. Creation date: (7/16/2002
	 * 3:12:04 PM)
	 */
	public void updateInvChapelIndex(DatabaseTransaction t,
			fdms.ui.struts.form.InventoryCatalogForm form,
			DbInvMaster dbInvMaster, DbLocation[] dbLocation) {

		DbInvChapelIndex dbInvChapelIndex = null;
		String[] itemsToIncludeOnChapelGPL = form
				.getItemsToIncludeOnChapelGPL();

		for (int i = 0; i < dbLocation.length; i++) {
			boolean existSelected = false;
			try {
				dbInvChapelIndex = FdmsDb.getInstance().getInvItemForLocation(
						t, dbLocation[i].getId(), dbInvMaster.getId());
				for (int j = 0; j < itemsToIncludeOnChapelGPL.length; j++) {
					// If the database row is in the select list, set the
					// existSelected flag
					if (String.valueOf(dbInvChapelIndex.getLocationNumber())
							.equals(itemsToIncludeOnChapelGPL[j])) {
						existSelected = true;
						break;
					}
				}
				// Remove any rows that are in the database but weren't selected
				if (!existSelected) {
					dbInvChapelIndex.remove();
				}
			} catch (NullPointerException npe) {
				// If the row doesn't exist in the table, but it is selected,
				// add a new row to the table
				for (int j = 0; j < itemsToIncludeOnChapelGPL.length; j++) {
					if (String.valueOf(dbLocation[i].getId()).equals(
							itemsToIncludeOnChapelGPL[j])) {
						dbInvChapelIndex = new DbInvChapelIndex();
						dbInvChapelIndex.setNew();
						dbInvChapelIndex.setItemNumber(dbInvMaster.getId());
						dbInvChapelIndex.setLocationNumber(FormatNumber
								.parseInteger(itemsToIncludeOnChapelGPL[j]));
						t.addPersistent(dbInvChapelIndex);
						break;
					}
				}
			}
		}
	}

	/**
	 * Called from ProcessInventoryCatalog, this Method validates the data from
	 * the InventoryCatalog form. If a validation error occurs, the error is
	 * stored in the errors collection. Creation date: (7/17/2002 10:44:10 AM)
	 */

	public void validateData(DatabaseTransaction t,
			fdms.ui.struts.form.InventoryCatalogForm form, ActionErrors errors,
			DbUserSession sessionUser) {

		int checkInt = 0;

		try {
			// ProductLine
			if (form.getProductCategory().trim() == null
					|| form.getProductCategory().trim().equals("--Select--")) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.inventory.nullProductCategory"));
				formErrors.add("productCategory");
			}

			// ItemName
			if (form.getItemName().trim() == null
					|| form.getItemName().trim().equals("")) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.inventory.nullItemName"));
				formErrors.add("itemName");
			} else {
				if (form.getItemName().trim().indexOf(" ") > -1) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
							"error.inventory.spacesInItemName"));
					formErrors.add("itemName");
				} else {
					form.setItemName(form.getItemName().trim().toUpperCase());
					DbInvMaster chkItemName = FdmsDb.getInstance()
							.getInvMasterForName(t, form.getItemName(),
									sessionUser.getRegion());
					if (chkItemName != null) {
						if (!form.getInventoryMasterId().trim().equals(
								String.valueOf(chkItemName.getId()).trim())) {
							errors.add(ActionErrors.GLOBAL_ERROR,
									new ActionError(
											"error.inventory.dupItemName"));
							formErrors.add("itemName");
						}
					}

				}
			}

			// Description
			if (form.getItemDescription().trim() == null
					|| form.getItemDescription().trim().equals("")) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.inventory.nullItemDescription"));
				formErrors.add("itemDescription");
			}

			// StockType
			if (form.getInventoryType() == null
					|| form.getInventoryType().trim().length() == 0) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.inventory.nullStockType"));
				formErrors.add("inventoryType");
			}

			// Edit Numerics
			try {
				// Cost is Required
				if (form.getWholesaleCost().trim() == null
						|| form.getWholesaleCost().trim().equals("")) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
							"error.inventory.nullCost"));
					formErrors.add("wholesaleCost");
				}
				 // Ticket - 5536 (Fix inventory Cost field), 19-Apr-2012 : Start
				/*
				else {
					if (FormatCurrency.convertToCurrency(form
							.getWholesaleCost()) == 0) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
								"error.inventory.invalidCost"));
						formErrors.add("wholesaleCost");
					}
				}*/
				// Ticket - 5536 (Fix inventory Cost field), 19-Apr-2012 : End
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.inventory.invalidCost"));
				formErrors.add("wholesaleCost");
			}

			try {
				// Sequence No is not required, but must be numeric
				if (form.getSequenceNo() != null
						&& (!form.getSequenceNo().trim().equals(""))) {
					checkInt = Integer.parseInt(form.getSequenceNo());
				}
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.inventory.invalidSequenceNumber"));
				formErrors.add("sequenceNo");
			}

			try {
				// Price is Required
				if (form.getPriceSelling().trim() == null
						|| form.getPriceSelling().trim().equals("")) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
							"error.inventory.nullPrice"));
					formErrors.add("priceSelling");
				} else {
					if (FormatCurrency
							.convertToCurrency(form.getPriceSelling()) == 0) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
								"error.inventory.invalidPrice"));
						formErrors.add("priceSelling");
					}
				}
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.inventory.invalidPrice"));
				formErrors.add("priceSelling");
			}

			try {
				// Account Description might be required.
				CompanyOptionsManager coMgr = new CompanyOptionsManager();
				// Now check to see if this options is turned on for this
				// customer
				int acctDescription = coMgr.getCompanyOptionValueForCompany(
						sessionUser.getCompanyID(),
						CompanyOptionsDTO.COMPANY_OPTION_ACCOUNT_DESCRIPTIONS);
				// If account descriptions are turned on then load them
				if (acctDescription == 1) {
					// is Required
					if (form.getAccountDescID().trim() == null
							|| form.getAccountDescID().trim().equals("")) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
								"error.inventory.invalidAccountDescription"));
						formErrors.add("accountDescID");
					} else {
						if (FormatCurrency.convertToCurrency(form
								.getAccountDescID()) == 0) {
							errors
									.add(
											ActionErrors.GLOBAL_ERROR,
											new ActionError(
													"error.inventory.invalidAccountDescription"));
							formErrors.add("accountDescID");
						}
					}
				}
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.inventory.invalidAccountDescription"));
				formErrors.add("accountDescID");
			}

			try {
				// Contract Line No is not required, but must be numeric
				if (form.getContractLineNo() != null
						&& (!form.getContractLineNo().trim().equals(""))) {
					checkInt = Short.parseShort(form.getContractLineNo());
				}
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.inventory.invalidContractLineNumber"));
				formErrors.add("contractLineNo");
			}

			try {
				// MinimumOnHand is not required, but must be numeric
				if (form.getMinimumOnHand() != null
						&& (!form.getMinimumOnHand().trim().equals(""))) {
					checkInt = Integer.parseInt(form.getMinimumOnHand());
				}
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.inventory.invalidMinimumOnHand"));
				formErrors.add("minimumOnHand");
			}

			try {
				// TaxExemptAmount is not required, but must be numeric
				if (form.getTaxExemptAmount() != null
						&& (!form.getTaxExemptAmount().trim().equals(""))) {
					checkInt = FormatCurrency.convertToCurrency(form
							.getTaxExemptAmount());
				}
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.inventory.invalidTaxExempt"));
				formErrors.add("taxExemptAmount");
			}

			try {
				// Sequence No is not required, but must be numeric
				if (form.getReorderQuantity() != null
						&& (!form.getReorderQuantity().trim().equals(""))) {
					checkInt = Integer.parseInt(form.getReorderQuantity());
				}
			} catch (Exception e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.inventory.invalidReorderQuantity"));
				formErrors.add("reorderQuantity");
			}

			// Convert dropdowns from "--Select--"
			if (form.getSupplier().trim().equals("--Select--")) {
				form.setSupplier(" ");
			}
			if (form.getTaxCode().trim().equals("--Select--")) {
				form.setTaxCode(" ");
			}
			if (form.getCasketType().trim().equals("--Select--")) {
				form.setCasketType(" ");
			}
			if (form.getCasketUsage().trim().equals("--Select--")) {
				form.setCasketUsage(" ");
			}
			if (form.getInteriorDescription().trim().equals("--Select--")) {
				form.setInteriorDescription(" ");
			}
			if (form.getCasketExt().trim().equals("--Select--")) {
				form.setCasketExt(" ");
			}
		} catch (Exception e) {
			logger
					.error(
							"Catching errors in ProcessInventoryCatalog.do validateData. ",
							e);
		}
	}
}
