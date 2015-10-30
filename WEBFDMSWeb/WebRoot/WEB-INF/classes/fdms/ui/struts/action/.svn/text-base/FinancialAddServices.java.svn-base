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

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.custom.FinancialAddServicesLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialAddServicesForm;
import fdms.ui.struts.form.FinancialInformationForm;
import fdms.ui.struts.form.InventoryForm;

import com.aldorsolutions.webfdms.beans.DbSpeedData;

public class FinancialAddServices extends Action {

	private Logger logger = Logger.getLogger(FinancialAddServices.class
			.getName());

	/**
	 * This Action is called from FinancialInformation.JSP when the operator
	 * wants to add new items from the price list to the current case's
	 * contract. FinancialAddServices.JSP is used to show a list of available
	 * service items from which the operator chooses.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
		// AppLog.trace("AddServices Action starting. form:");

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;
		java.util.ArrayList addServicesList = new java.util.ArrayList();
		// added by haranesh patel
		FinancialAddServicesForm financialAddServicesForm = (FinancialAddServicesForm) actionForm;
		String category = (String) session.getAttribute("category");
		session.removeAttribute("category");
		if (category == null) {
			category = "";
		}
		// java.util.ArrayList addServicesPriceList = new java.util.ArrayList();
		int vitalsid = 0;
		if (sessionUser == null) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.login.invalid"));
		} else {
			vitalsid = SessionHelpers.getVitalsIdFromSession(request,
					sessionUser);
			if (vitalsid < 1) {
				// AppLog.warning("AddServices. Invalid VitalsID to process:"+vitalsid);
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.ui.nocase"));
			}
		}
		// If errors found, bail out and return to input page
		if (!errors.isEmpty()) {
			// AppLog.info("AddServices Invoking forward mapping getInput() ");
			saveErrors(request, errors);
			return (new ActionForward(mapping.getInput()));
		}

		// added by haranesh patel

		DbSpeedData[] dbSpeedData = FdmsDb.getInstance().getSpeedData(
				sessionUser.getDbLookup(), sessionUser.getRegion(), "PLCATGRY");
		// AppLog.trace("dbSpeedData Length is "+ dbSpeedData.length );
		ArrayList dbCategoryList = new java.util.ArrayList();

		for (int i = 0; i < dbSpeedData.length; i++) {
			String listLabel = CsvTable.getField(dbSpeedData[i].getData(), 1);
			String listLabe2 = CsvTable.getField(dbSpeedData[i].getData(), 2);

			dbCategoryList.add(new OptionsList(listLabel, listLabe2));
		}
		// finish

		// Database Access Logic
		try {
			t = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);
			FinancialInformationForm fiform = (FinancialInformationForm) session
					.getAttribute("financialInformation");
			PriceListManager plm = new PriceListManager();
			DbPriceList dbPriceList[] = plm.getPriceListForVersion(t, fiform
					.getPriceListVersion(), sessionUser.getRegion());
			// AppLog.trace("AddServices. Got Price List items:"+dbPriceList.length);
			if (!category.equals("All")) {
				for (int i = 0; i < dbPriceList.length; i++) {
				
					if (category.equals(dbPriceList[i].getCategory())) {

						// AppLog.trace("AddServices add to collection:"+i+":"+dbPriceList[i].getContrDescr());
						// Check if this price list item is "Active" to
						// determine if should be in list of available items
						// Also, if first character of GPL key is 'S' or 'C'
						// indicates a Loewen section or cover header
						// Also, skip contract line#0 which means section
						// headers for non-Loewen sites
						char firstchar = FormatString
								.getFirstChar(dbPriceList[i].getGPLkey());
						// AppLog.trace("AddServices first char:"+i+":"+String.valueOf(firstchar)+" active="+dbPriceList[i].getActive()+dbPriceList[i].getContrLine());
						if (dbPriceList[i].getActive() == 'Y'
								&& firstchar != 'S' && firstchar != 'C'
								&& dbPriceList[i].getContrLine() != 0
								&& dbPriceList[i].getPackage() != 'Y') {
							// AppLog.trace("AddServices active item:"+i+":"+dbPriceList[i].getContrDescr());
							FinancialAddServicesLineItem fasLineItem = new com.aldorsolutions.webfdms.beans.custom.FinancialAddServicesLineItem(
									dbPriceList[i]);
							// addServicesPriceList.add(i,fasLineItem);
							// AppLog.trace("AddServices adding to list:"+i+":"+dbPriceList[i].getContrDescr());
							addServicesList.add(new OptionsList(fasLineItem
									.getItemId(), fasLineItem
									.getItemDescription()
									+ " " + fasLineItem.getItemPrice()));
							// AppLog.trace("AddServices done with loop:"+i);
						}
					}
				}
			}
			if (category.equals("All") || category.equals("")) {
				for (int i = 0; i < dbPriceList.length; i++) {
					

					// AppLog.trace("AddServices add to collection:"+i+":"+dbPriceList[i].getContrDescr());
					// Check if this price list item is "Active" to determine if
					// should be in list of available items
					// Also, if first character of GPL key is 'S' or 'C'
					// indicates a Loewen section or cover header
					// Also, skip contract line#0 which means section headers
					// for non-Loewen sites
					char firstchar = FormatString.getFirstChar(dbPriceList[i]
							.getGPLkey());
					// AppLog.trace("AddServices first char:"+i+":"+String.valueOf(firstchar)+" active="+dbPriceList[i].getActive()+dbPriceList[i].getContrLine());
					if (dbPriceList[i].getActive() == 'Y' && firstchar != 'S'
							&& firstchar != 'C'
							&& dbPriceList[i].getContrLine() != 0
							&& dbPriceList[i].getPackage() != 'Y') {
						// AppLog.trace("AddServices active item:"+i+":"+dbPriceList[i].getContrDescr());
						FinancialAddServicesLineItem fasLineItem = new com.aldorsolutions.webfdms.beans.custom.FinancialAddServicesLineItem(
								dbPriceList[i]);
						// addServicesPriceList.add(i,fasLineItem);
						// AppLog.trace("AddServices adding to list:"+i+":"+dbPriceList[i].getContrDescr());
						addServicesList.add(new OptionsList(fasLineItem
								.getItemId(), fasLineItem.getItemDescription()
								+ " " + fasLineItem.getItemPrice()));
						// AppLog.trace("AddServices done with loop:"+i);
					}
				}

			}

		} catch (PersistenceException pe) {
			// AppLog.criticalError("Persistence Exception in AddServices do.Perform. "+pe.getCause());
			logger.error("PersistenceException in doPerform() : " + pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.PersistenceException", pe.getCause()));

		} catch (Exception pe) {
			// AppLog.criticalError("Exception in AddServices .doPerform. "+pe);
			// pe.printStackTrace();
			logger.error("Error in doPerform() : ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.GeneralException", pe.getMessage()));
		} finally {
			if (t != null) {
				t.closeConnection();
				t = null;
			}
		}

		// Set Server Objects in Scope

		session.setAttribute("inventory", financialAddServicesForm);
		session.setAttribute("dbCategoryList", dbCategoryList);

		request.setAttribute("addServicesList", addServicesList);
		// request.setAttribute("addServicesPriceList",addServicesPriceList);
		return mapping.findForward("financialAddServices");
	}
}
