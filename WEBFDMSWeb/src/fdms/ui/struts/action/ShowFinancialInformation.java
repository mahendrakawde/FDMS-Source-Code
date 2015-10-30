package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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

import com.aldorassist.webfdms.dao.ComboDataDAO;
import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.ComboDataDTO;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbChargeItem;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbInvOnHand;
import com.aldorsolutions.webfdms.beans.DbLocale;
import com.aldorsolutions.webfdms.beans.DbLocaleConfig;
import com.aldorsolutions.webfdms.beans.DbLocaleConfigType;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPreneed;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.InventorySold;
import com.aldorsolutions.webfdms.beans.SalesTaxes;
import com.aldorsolutions.webfdms.beans.TransactionhistoryDAO;
import com.aldorsolutions.webfdms.beans.TransactionhistoryDTO;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.MailUtil;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialInformationForm;

public class ShowFinancialInformation extends Action {

	private Logger logger = Logger.getLogger(ShowFinancialInformation.class
			.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		java.util.TreeMap financialInformationLineItems;
		DbVitalsDeceased dbVitalsDeceased = null;
		DbFinancialSummary finan = null;
		DbVitalsFirstCall firstcall = null;
		DbPreneed preneed = null;
		DbLocation chapel = null;
		LocaleDTO alocale = null;
		InventorySold soldData = null;
		ArrayList salesDescList = null;

		FinancialInformationForm financialInformation = null;

		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		int vitalsid = 0;
		if (sessionUser == null) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.login.invalid"));
		} else {
			vitalsid = SessionHelpers.getVitalsIdFromSession(request,
					sessionUser);
			if (vitalsid < 1) {
				// AppLog.warning("ShowFinancial. Invalid VitalsID to process:"+vitalsid);
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.ui.nocase"));
			}
		}

		// Check For Existing Session Objects
		// Database Access Logic
		DatabaseTransaction t = null;

		try {
			t = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);
			FdmsDb fdmsdb = FdmsDb.getInstance();
			Map financialInformationMap = (HashMap) fdmsdb.getChargesForCase(t,
					vitalsid);

			// we get the transactionhistory to check if the saleTax is blance
			// with the charge table.
			// If it is not balance, we are going to block any operation send
			// out email to support and fix any problem before the user can
			// operate the financial again.
			TransactionhistoryDAO transDao = new TransactionhistoryDAO(
					sessionUser);
			ArrayList<TransactionhistoryDTO> transes = new ArrayList<TransactionhistoryDTO>();
			transes = transDao.getTransactionhistory(vitalsid);
			int tranStateTax = 0;
			int tranLocalTax = 0;
			int tranArticleTax = 0;
			boolean saleTaxBalance = true;

			if (transes.size() > 0) {
				for (TransactionhistoryDTO tran : transes) {
					if (tran.getDeleteTransaction().compareToIgnoreCase("Y") != 0) {
						if (tran.getChargeType() == SalesTaxes.STATE_TAX_ID) { // statetax
							tranStateTax = tranStateTax
									+ tran.getAmountOfTran();
						}
						if (tran.getChargeType() == SalesTaxes.LOCAL_TAX_ID) { // localtax
							tranLocalTax = tranLocalTax
									+ tran.getAmountOfTran();
						}
						if (tran.getChargeType() == SalesTaxes.ARTICLE_TAX_ID) { // articletax
							tranArticleTax = tranArticleTax
									+ tran.getAmountOfTran();
						}
					}
				}
			} else {
				saleTaxBalance = true;
			}

			// get data from transactionhistory for checking if it is posted to
			// the table. if it is we are going to make that they cannot change
			// the name of item.
			DbHistory[] dbTranHist = null;
			dbTranHist = FdmsDb.getInstance().getHistoryForCase(t, vitalsid);

			finan = fdmsdb.getFinancial(t, vitalsid);
			dbVitalsDeceased = fdmsdb.getVitalsDeceased(t, vitalsid);
			firstcall = fdmsdb.getVitalsFirstCall(t, vitalsid);
			preneed = fdmsdb.getPreneed(t, vitalsid);
			DbCase caseinfo = fdmsdb.getCase(t, vitalsid);
			if (firstcall == null || preneed == null) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.PersistenceException",
						"Vitals ID does not exist:" + vitalsid));
			}
			chapel = fdmsdb.getLocation(t, caseinfo.getChapelNumber());
			alocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(),
					sessionUser.getRegion());
			if (chapel == null || alocale == null) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.ui.chapel"));
			}
			// Convert the Hash to a Tree So It Can Iterate in Sequence#
			// Order
			Iterator fiIterator = financialInformationMap.values().iterator();
			DbChargeItem unsortedLineItem;
			Map sortedFinancialInformationMap = new TreeMap();
			int totalCharges = 0;
			// while (fiIterator.hasNext()) {
			// unsortedLineItem = (DbChargeItem) fiIterator.next();
			// totalCharges += unsortedLineItem.getAmount();
			// sortedFinancialInformationMap.put(new
			// Integer(unsortedLineItem.getSequenceNumber()),
			// unsortedLineItem);
			// if (unsortedLineItem.getType() == SalesTaxes.STATE_TAX_ID) {
			// //statetax
			// if (tranStateTax != unsortedLineItem.getAmount()) {
			// saleTaxBalance = false;
			// }
			// }
			// if (unsortedLineItem.getType() == SalesTaxes.LOCAL_TAX_ID) {
			// //localtax
			// if (tranLocalTax != unsortedLineItem.getAmount()) {
			// saleTaxBalance = false;
			// }
			// }
			// if (unsortedLineItem.getType() == SalesTaxes.ARTICLE_TAX_ID) {
			// //articletax
			// if (tranArticleTax != unsortedLineItem.getAmount()) {
			// saleTaxBalance = false;
			// }
			// }
			//				
			// }
			int chargeStateTax = 0;
			int chargeLocalTax = 0;
			int chargeAritcleTax = 0;

			DbLocaleConfig[] configs = FdmsDb.getInstance()
					.getLocaleConfigForLocale(t, sessionUser.getRegion());
			int orderByContLine = FdmsDb
					.getInstance()
					.getLocaleConfigValueForLocale(
							t,
							configs,
							sessionUser.getRegion(),
							DbLocaleConfigType.CONFIG_ORDER_BY_CONT_LINE_ON_FINANCIAL_PAGE);

			while (fiIterator.hasNext()) {
				unsortedLineItem = (DbChargeItem) fiIterator.next();
				totalCharges += unsortedLineItem.getAmount();

				// sortedFinancialInformationMap.put(new
				// Integer(unsortedLineItem.getSequenceNumber()),unsortedLineItem);

				// for sorted by SEQ or Cont by locale setup.
				if (orderByContLine == 1) {
					sortedFinancialInformationMap.put(new Integer(
							unsortedLineItem.getType() + ""
									+ unsortedLineItem.getSequenceNumber()),
							unsortedLineItem);
				} else {
					sortedFinancialInformationMap.put(new Integer(
							unsortedLineItem.getSequenceNumber()),
							unsortedLineItem);
				}

				if (unsortedLineItem.getType() == SalesTaxes.STATE_TAX_ID) { // statetax
					chargeStateTax = chargeStateTax
							+ unsortedLineItem.getAmount();
				}
				if (unsortedLineItem.getType() == SalesTaxes.LOCAL_TAX_ID) { // localtax
					chargeLocalTax = chargeLocalTax
							+ unsortedLineItem.getAmount();
				}
				if (unsortedLineItem.getType() == SalesTaxes.ARTICLE_TAX_ID) { // articletax
					chargeAritcleTax = chargeAritcleTax
							+ unsortedLineItem.getAmount();
				}

			}
			if (chargeStateTax != tranStateTax) {
				saleTaxBalance = false;
			}
			if (chargeLocalTax != tranLocalTax) {
				saleTaxBalance = false;
			}
			if (chargeAritcleTax != tranArticleTax) {
				saleTaxBalance = false;
			}

			if (transes.size() <= 0 || finan.getIARsentBox() == 0) {
				saleTaxBalance = true;
			}
			// Make a new Iterator for the Sorted Map
			Iterator sfiIterator = sortedFinancialInformationMap.values()
					.iterator();
			DbChargeItem sortedLineItem;
			financialInformationLineItems = new TreeMap();
			while (sfiIterator.hasNext()) {
				sortedLineItem = (DbChargeItem) sfiIterator.next();

				FinancialInformationLineItem fiLineItem = new FinancialInformationLineItem(
						sortedLineItem);
				try {
					// logger.debug( sortedLineItem.toOutString() );
					DbInvMaster dbInvMaster = FdmsDb.getInstance()
							.getInvMaster(t, sortedLineItem.getInvSeqNo());
					// logger.debug( dbInvMaster.toOutString() );

					// check if the transactionhistory has been set
					fiLineItem.setPostedToTran(false);

					if (dbTranHist.length == 0) {
						fiLineItem.setPostedToTran(false);
					} else {
						for (int k = 0; k < dbTranHist.length; k++) {

							if ((sortedLineItem.getDescription()
									.compareToIgnoreCase(
											dbTranHist[k].getCHistDesc()) == 0)
									&& (sortedLineItem.getType() == dbTranHist[k]
											.getIHistType())
									&& (sortedLineItem.getAmount() == dbTranHist[k]
											.getLHistAmount())) {
								fiLineItem.setPostedToTran(true);
								break;
							}

						}
					}
					fiLineItem.setItemPicture(dbInvMaster.getImageUrl());
					fiLineItem.setFromPackage(sortedLineItem.isFromPackage());
					// Processing for serial numbered inventory items
					if (dbInvMaster.getCStockType().equalsIgnoreCase(
							DbInvMaster.STOCK_TYPE_SERIAL)) {
						fiLineItem.setStockType(DbInvMaster.STOCK_TYPE_SERIAL);
						fiLineItem.setSerialNumber("- Select -");
						DbInvOnHand onhand = FdmsDb.getInstance().getInvOnHand(
								t,
								fiLineItem.getDbChargeItem().getInvOnHandID());
						if (onhand != null
								&& fiLineItem.getDbChargeItem()
										.getInvOnHandID() > 0) {
							fiLineItem.setSerialNumber(onhand
									.getCSerialNumber());
						}
						// if contract is not yet posted then serial number is
						// modifiable
						if (finan.getIARsentBox() == 0) {
							fiLineItem.setSerialNumberModifiable(1);
						}
					}

				} catch (Exception e) {
					fiLineItem.setItemPicture("");
				}
				// Calculate default GL account for display purposes only at
				// this point
				// GL will be recaclulated when and if saving financial page.
				// If DbCharge object already has a GL account then use it
				// instead of the default.
				if (finan != null) {
					if (sortedLineItem.getGlAcct() == null
							|| sortedLineItem.getGlAcct().compareTo("        ") <= 0) {
						soldData = new InventorySold();
						FdmsDb.getInstance().getDefaultGlSalesAccts(
								t,
								soldData,
								sessionUser.getRegion(),
								caseinfo.getChapelNumber(),
								String.valueOf(sortedLineItem
										.getItemCategoryType()),
								finan.getCFinSaleType(),
								firstcall.getDispositionCode());
						fiLineItem.setItemGLAccount(soldData.getAcctRevenue());
					}
				}
				// sort by Seq or Cont by locale setup
				if (orderByContLine == 1) {
					financialInformationLineItems.put(new Integer(fiLineItem
							.getItemTypeCode()
							+ "" + fiLineItem.getItemSequenceNumber()),
							fiLineItem);
				} else {
					financialInformationLineItems.put(new Integer(fiLineItem
							.getItemSequenceNumber()), fiLineItem);
				}

				// financialInformationLineItems.put(new
				// Integer(fiLineItem.getItemSequenceNumber()), fiLineItem);
			}

			// Populate the Account Description List object
			salesDescList = new ArrayList();

			CompanyOptionsManager coMgr = new CompanyOptionsManager();
			// Now check to see if this options is turned on for this customer
			int acctDescription = coMgr.getCompanyOptionValueForCompany(
					sessionUser.getCompanyID(),
					CompanyOptionsDTO.COMPANY_OPTION_ACCOUNT_DESCRIPTIONS);
			// If account descriptions are turned on then load them
			if (acctDescription == 1) {
				session.setAttribute("salesTypeDisplay", "display");

				salesDescList.add(new OptionsList("", ""));

				ComboDataDAO comboDataDAO = new ComboDataDAO(sessionUser);
				ArrayList accountDescriptions = comboDataDAO
						.getComboDataOptions(
								ComboDataDTO.COMBODATA_LOCAL_DEFULT,
								ComboDataDTO.COMBODATA_TYPE_SALES_TYPE);

				// Now go thru the elements and
				for (int i = 0; i < accountDescriptions.size(); i++) {
					ComboDataDTO comboData = (ComboDataDTO) accountDescriptions
							.get(i);
					salesDescList.add(new OptionsList(comboData.getValue(),
							comboData.getName()));
				}
			} else {
				session.removeAttribute("salesDescList");
			}

			// Create The Form Object
			financialInformation = (FinancialInformationForm) actionForm;

			if (financialInformation == null
					|| financialInformation.getVitalsId() != vitalsid) {
				financialInformation = new FinancialInformationForm();
			}

			boolean showFinancing = alocale.isConfigShowFinancing();

			financialInformation.setOrderByContLine((int) orderByContLine);

			financialInformation.setVitalsId(vitalsid);
			financialInformation.setTotalCharge(FormatCurrency
					.toCurrency((long) totalCharges));
			financialInformation.setDeceasedFullName(dbVitalsDeceased
					.getDecFullName());
			financialInformation.setContractCode(caseinfo.getContractCode());
			financialInformation.setDisposition(firstcall.getDispositionCode());
			financialInformation.setInterestFromDate(FormatDate
					.MDYtoMMDDYYYY(firstcall.getInterestFromDate()));
			financialInformation.setServicePlan(firstcall.getMarketingPlan());
			financialInformation.setSource(preneed.getSource());
			financialInformation.setShowFinancingSection(showFinancing);
			financialInformation.setSaleTaxBalance(saleTaxBalance);
			// send the email out if the saletax out of balance.
			if (!saleTaxBalance) {
				emailAdmin(vitalsid, sessionUser, errors);
			}

			// Populate Form Data
			if (null != chapel) {
				financialInformation.setArAccount(chapel.getArAcct());
				financialInformation.setChapelLocation(chapel.getId());
			}

			int showServicePlanInt = FdmsDb
					.getInstance()
					.getLocaleConfigValueForLocale(
							t,
							sessionUser.getRegion(),
							DbLocaleConfigType.CONFIG_FINANCE_SCREEN_SHOW_SERVICE_PLAN);

			financialInformation.setShowServicePlan((showServicePlanInt > 0));

			if (preneed.getStatus().compareToIgnoreCase("A") == 0
					|| preneed.getStatus().compareToIgnoreCase("C") == 0) {
				financialInformation.setPreNeed(true);
			} else {
				financialInformation.setPreNeed(false);
			}

			financialInformation.setPostedContract("0");

			if (finan != null) {
				financialInformation.setAdvertisingSource(finan
						.getCAdvertisingSource());
				financialInformation.setProvidedServices(finan
						.getCFamilyPreviouslyServed());
				financialInformation.setPriceListVersion(finan
						.getPriceListVersion(t));
				financialInformation.setApplyFinanceCharges((finan
						.getIFinServiceChargesBox() == 1));
				financialInformation.setSaleDate(FormatDate.MDYtoMMDDYYYY(finan
						.getCFinSaleDate()));
				if (financialInformation.getSaleDate().compareTo(" ") <= 0) {
					financialInformation.setSaleDate(FormatDate
							.MDYtoMMDDYYYY(dbVitalsDeceased.getDateOfDeath()));
					finan.setCFinSaleDate(dbVitalsDeceased.getDateOfDeath());
				}

				// financialInformation.setPostedDate(FormatDate.MDYtoMMDDYYYY(finan.getCFinSaleDate()));
				// if (financialInformation.getPostedDate().compareTo(" ") <= 0)
				// {
				// financialInformation.setPostedDate(FormatDate.MDYtoMMDDYYYY(dbVitalsDeceased.getDateOfDeath()));
				// }

				TransactionhistoryDTO tran = new TransactionhistoryDTO();
				TransactionhistoryDAO tranDao = new TransactionhistoryDAO(
						sessionUser);
				tran = tranDao.getPostedDate(vitalsid);
				String PostedDate = FormatDate
						.convertDateToMM_DD_YYYY((java.util.Date) tran
								.getDateOfTran());
				if (PostedDate != null && PostedDate.length() > 0) {
					financialInformation.setPostedDate(PostedDate);
				} else {
					financialInformation.setPostedDate("");
				}

				financialInformation.setDueDate(FormatDate.MDYtoMMDDYYYY(finan
						.getCFinDueDate()));
				financialInformation.setInterestRate(String.valueOf(finan
						.getFFinServiceChargeRate()));
				financialInformation.setPreviousFuneralHomeUsed(finan
						.getCPreviousFhUsed());
				financialInformation.setProvidedServices(finan
						.getCFamilyPreviouslyServed());
				financialInformation.setSaleType(finan.getCFinSaleType());
				financialInformation
						.setSendToAccounting((finan.getIARsentBox() != 0));
				financialInformation.setPostedContract(String.valueOf(finan
						.getIARsentBox()));
				financialInformation.setMiscNotes(finan.getCFinMiscNotes());
				financialInformation.setStmtDate(FormatDate.MDYtoMMDDYYYY(finan
						.getCFinDateInvoiceSent()));
				financialInformation.setCustomerName(dbVitalsDeceased
						.getCustomerName());
				financialInformation.setSalesDescCDID(String.valueOf(finan
						.getSalesDescCDID()));

				/*
				 * If you add a field after the case has been posted, please
				 * make sure to add the appropriate reset field below.
				 */

			} else {
				// processing that needs to be done if first time accessing
				// financial information for a case
				PriceListManager plm = new PriceListManager();
				financialInformation.setPriceListVersion(plm
						.getDefaultPriceList(t, vitalsid));
				// Check default sale date code
				if (alocale.getDefaultSaleDateCode() == DbLocale.SALE_DATE_DEATHDATE) {
					financialInformation.setSaleDate(FormatDate
							.MDYtoMMDDYYYY(dbVitalsDeceased.getDateOfDeath()));
					financialInformation.setPostedDate(FormatDate
							.MDYtoMMDDYYYY(dbVitalsDeceased.getDateOfDeath()));
				} else if (alocale.getDefaultSaleDateCode() == DbLocale.SALE_DATE_ARRDATE) {
					financialInformation.setSaleDate(FormatDate
							.MDYtoMMDDYYYY(firstcall.getArrangeDate()));
					financialInformation.setPostedDate(FormatDate
							.MDYtoMMDDYYYY(firstcall.getArrangeDate()));
				} else if (alocale.getDefaultSaleDateCode() == DbLocale.SALE_DATE_SERVDATE) {
					financialInformation.setSaleDate(FormatDate
							.MDYtoMMDDYYYY(dbVitalsDeceased.getDateOfBurial()));
					financialInformation.setPostedDate(FormatDate
							.MDYtoMMDDYYYY(dbVitalsDeceased.getDateOfBurial()));
				}
				financialInformation.setDueDate(FormatDate
						.MDYtoMMDDYYYY(FormatDate.addToDateMMDDYYYY(
								dbVitalsDeceased.getDateOfDeath(), alocale
										.getDaysBeforeDue(), 0)));
				financialInformation.setInterestFromDate(financialInformation
						.getDueDate());
				financialInformation.setCustomerName(dbVitalsDeceased
						.getDecFullName());

				financialInformation.setSendToAccounting(false);
				financialInformation.setInterestRate("");
				financialInformation.setPreviousFuneralHomeUsed("");
				financialInformation.setProvidedServices("");
				financialInformation.setSaleType("");

				DbPreneed dbPreNeed = null;
				dbPreNeed = FdmsDb.getInstance().getPreneed(t, vitalsid);
				if (dbPreNeed != null && dbPreNeed.getServiceType() != null
						&& dbPreNeed.getServiceType().trim().length() > 0) {
					financialInformation
							.setSaleType(dbPreNeed.getServiceType());

					int resetServiceType = coMgr
							.getCompanyOptionValueForCompany(
									sessionUser.getCompanyID(),
									CompanyOptionsDTO.COMPANY_OPTION_TURN_ON_RESETSERVICETYPE);
					if (resetServiceType == 1
							&& caseinfo.getPreneedStatus().compareToIgnoreCase(
									"S") == 0) {
						financialInformation.setSaleType("");
					}
				}

				financialInformation.setMiscNotes("");
				financialInformation.setStmtDate("");

				if (chapel.getTurnOnApplyFinanceCharge().compareToIgnoreCase(
						"Y") == 0) {
					financialInformation.setApplyFinanceCharges(chapel
							.getTurnOnApplyFinanceCharge()
							.equalsIgnoreCase("Y"));
					financialInformation.setInterestRate(chapel
							.getMonthlyInterestRate());
				}
				/*
				 * added by Bhavesh for ticket #5552 FPG Pre-need serviced
				 * (clear the sale type on Financial page after creation of new
				 * Pre-Need case )
				 */
				financialInformation.setSalesDescCDID("");

				/*
				 * These should match the fields coming from financing above
				 */
			}
			financialInformation
					.setLineItems((TreeMap) financialInformationLineItems);
			session.setAttribute("financialInformation", financialInformation);
			// Set collections in session
			session.setAttribute("saleTypeList", SessionHelpers
					.getSpeedDataOptionCollection(sessionUser, "SaleType"));
			session.setAttribute("sourceList", SessionHelpers
					.getSpeedDataOptionCollection(sessionUser, "ATSOURCE"));
			session.setAttribute("dispositionList", SessionHelpers
					.getSpeedDataOptionCollection(sessionUser, "DISPCODE"));
			session.setAttribute("servicePlanList", SessionHelpers
					.getSpeedDataOptionCollection(sessionUser, "MKTPLAN"));
			session.setAttribute("salesDescList", salesDescList);

			financialInformation.setVoidedContract(caseinfo
					.getVoidedContractCode());
			financialInformation.setComponentId(Integer.toString(FdmsDb
					.getInstance().getComponentId(t, vitalsid)));

			int transHistId = FdmsDb.getInstance().getDepositIdForCase(t,
					vitalsid);

			if (transHistId > 0) {
				DbHistory dbHistory = FdmsDb.getInstance().getHistory(t,
						transHistId);

				financialInformation.setDepositId(Integer.toString(dbHistory
						.getId()));
				financialInformation.setPaymentSource(dbHistory.getCHistDesc());
				financialInformation.setAmountPaid(FormatCurrency
						.toCurrency(-dbHistory.getLHistAmount()));
				financialInformation.setDateOfDeposit(FormatDate
						.convertDateToMM_DD_YYYY(dbHistory.getCHistDate()));
				financialInformation.setDateOfPayment(FormatDate
						.convertDateToMM_DD_YYYY(dbHistory.getCHistDatePaid()));
				financialInformation.setReceiptNumber(Integer
						.toString(dbHistory.getLHistReceiptNo()));
				financialInformation.setManualReceiptNumber(dbHistory
						.getCHistManualReceipt());
				financialInformation.setNonCashAdjustment(dbHistory
						.getCHistGLAcct());
				financialInformation.setMethodOfPayment(dbHistory
						.getCHistPayMethod());
				financialInformation.setVoidedDeposit(String.valueOf(dbHistory
						.getCHistDeleteTran()));
				financialInformation.setPostedDeposit(true);
				/* Make sure to reset any fields as appopriate below */

				if (dbHistory.getCHistDeleteTran() == 'V') {
					financialInformation.setVoidedContract("V");
				}

			} else {
				financialInformation.setDepositId("0");
				LocaleDTO userlocale = FdmsDb.getInstance().getLocale(
						sessionUser.getDbLookup(), sessionUser.getRegion());
				financialInformation.setDateOfDeposit(FormatDate
						.getCurrentDateFormatedMMDDYYYY());
				financialInformation.setDateOfPayment(FormatDate
						.getCurrentDateFormatedMMDDYYYY());
				financialInformation.setReceiptNumber(String.valueOf(userlocale
						.getNextReceiptNo()));
				financialInformation.setAmountPaid(null);
				financialInformation.setPaymentSource("");
				financialInformation.setManualReceiptNumber("");
				financialInformation.setNonCashAdjustment("");
				financialInformation.setMethodOfPayment("");
				financialInformation.setVoidedDeposit("");
				financialInformation.setPostedDeposit(false);

				/*
				 * These should match the fields above with the exception of the
				 * fields in the deposit Information
				 * 
				 * Deposit Amount, Payment Source, Date Paid, Deposit Date,
				 * Receipt No., Manual Receipt No, Non-Cash Adjustment, Payment
				 * Method
				 */
			}

			ArrayList payTypeList = SessionHelpers
					.getSpeedDataOptionCollection(sessionUser, "PAYTYPES");
			updateCollection(payTypeList);
			ArrayList payMethodList = SessionHelpers
					.getSpeedDataOptionCollection(sessionUser, "PAYMETHOD");
			updateCollectionRemoveDashes(payMethodList);
			session.setAttribute("paytypes", payTypeList);
			session.setAttribute("paymethods", payMethodList);

		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in ShowFinancial.doPerform. "
					+ pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.PersistenceException", pe.getCause()));
		} catch (Exception pe) {
			logger.error("Exception in  ShowFinancial .doPerform. ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.GeneralException", pe.getMessage()));
		} finally {
			if (t != null)
				t.closeConnection();
		}

		// Check for any errors so far
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}

		return mapping.findForward("financialInformation");
	}

	protected void updateCollection(ArrayList options) {
		Iterator it = options.iterator();

		while (it.hasNext()) {
			OptionsList option = (OptionsList) it.next();
			String value = CsvTable.getField(option.getListValue(), 2);
			option.setListValue(value);
		}
	}

	protected void updateCollectionRemoveDashes(ArrayList options) {
		Iterator it = options.iterator();

		while (it.hasNext()) {
			OptionsList option = (OptionsList) it.next();
			String value = option.getListValue();
			if (value.length() > 2)
				value = option.getListValue().substring(0, 2);
			option.setListValue(value);
		}
	}

	protected void emailAdmin(int vitalsid, DbUserSession sessionUser,
			ActionErrors errors) {

		CompanyManagerBean cmBean = new CompanyManagerBean();
		CompanyDTO company = cmBean.getCompany(sessionUser.getCompanyID());

		DatabaseTransaction t = null;

		try {
			t = (DatabaseTransaction) DatabaseTransaction
					.getTransaction(sessionUser);
			DbLocation dbLocation = FdmsDb.getInstance().getLocation(t,
					sessionUser.getLocationId());

			DbCase theCase = null;
			theCase = FdmsDb.getInstance().getCase(t, vitalsid);
			String caseStatus = "Active";
			if (theCase.getVoidedContractCode().equals("V")) {
				caseStatus = "Voided";
			}

			if (caseStatus.compareToIgnoreCase("Active") == 0) {

				ArrayList<String> emailArray = new ArrayList<String>();
				// emailArray.add("cjongs@aldorsolutions.com");
				emailArray.add("santosh@aldorsolutions.com");
				emailArray.add("bshah@aldorsolutions.com");
				emailArray.add("support@aldorsolutions.com");

				String subject = "Error SaleTax";
				StringBuffer message = new StringBuffer();
				String endLine = "\r\n";
				message.append(endLine + endLine);
				message.append("Automatic email: Please fix the error SaleTax "
						+ endLine);
				message
						.append("               : transactionhistory and charges out of saleTax balance "
								+ endLine);
				message
						.append("               : after fix this problem please check payment for consistance "
								+ endLine + endLine);
				message.append("vitalMasterKey : " + vitalsid + endLine);
				message.append("Case Status    : " + caseStatus + endLine
						+ endLine);
				message.append("     Companyid : " + sessionUser.getCompanyID()
						+ endLine);
				message.append("  Company Name : " + company.getName()
						+ endLine);
				message.append("       DataURL : " + company.getDataURL()
						+ endLine);
				message.append("      DBLookup : " + company.getDbLookup()
						+ endLine);
				message.append(endLine + endLine);
				message.append("     User Name : " + sessionUser.getFirstName()
						+ " " + sessionUser.getLastName() + endLine);
				message.append(" Email address : " + sessionUser.getEmailAddr()
						+ " " + endLine);
				message.append("   Locale Name : "
						+ sessionUser.getLocaleName() + " " + endLine);
				if (sessionUser.getLocationName() != null) {
					message.append(" Location Name : "
							+ sessionUser.getLocationName() + endLine);
				} else {
					message.append(" Location Name : All Locations" + endLine);
				}
				message.append(endLine + endLine);
				if (dbLocation != null) {
					message.append("Location Phone : " + dbLocation.getPhone()
							+ endLine);
					message.append("Location Phone : "
							+ dbLocation.getPhoneAlternate() + endLine);
				}
				message.append("Thank You. ");

				MailUtil.sendEmail(sessionUser, sessionUser.getConfigID(),
						emailArray, null, null, subject, message.toString());
			}
		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in ShowFinancial.doPerform. "
					+ pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.PersistenceException", pe.getCause()));
		} catch (Exception pe) {
			logger.error("Exception in  ShowFinancial .doPerform. ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.GeneralException", pe.getMessage()));
		} finally {
			if (t != null)
				t.closeConnection();
		}

	}

}
