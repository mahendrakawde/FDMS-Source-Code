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

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbComponent;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbFormsAvailable;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.reporting.crystal.bean.CrystalReportManagerBean;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.reporting.crystal.ExportReport;
import fdms.ui.struts.form.PaymentAddChangeForm;
import fdms.ui.struts.form.PaymentForm;

public class ShowPaymentAddChange extends Action {

	private Logger logger = Logger.getLogger(ShowPaymentAddChange.class.getName());

	private ArrayList formErrors;

	/**
	 * Called from payment.JSP, this action handles the submit button from its form.
	 * The form has multiple submit buttons to handle
	 * 1) entering a new payment.
	 * 2) printing a payment history report.
	 * 3) printing a receipt for a selected payment from the payment history table.
	 * 4) void a payment.
	 * 5) exiting to the case status page..
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

		//AppLog.trace("ShowPaymentAddChange. Starting action:"+form.getSubmitbutton());
		// clear print preview field.

		formErrors = new ArrayList();
		PaymentForm form = (PaymentForm) actionForm;
		form.setPreviewFile("");

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;
		PaymentAddChangeForm paymentAddForm = new PaymentAddChangeForm();
		DbComponent[] dbComponent = null;
		LocaleDTO userlocale = null;

		int vitalsid = 0;
		vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
		if (vitalsid < 1) {
			logger.debug("ShowPaymentAddChange. Invalid VitalsID to process:" + vitalsid);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
		}

		// get selected payment
		int paymentid = 0;
		logger.debug("ShowPaymentAddChange. selected Payment=" + form.getSelectedPayment());
		if (form.getSelectedPayment() != null) {
			paymentid = FormatNumber.parseInteger(form.getSelectedPayment());
			logger.debug("ShowPaymentAddChange. selected Payment ID=" + paymentid);
		}

		if (form.getSubmitbutton().compareTo("exit") == 0) {
			logger.debug("ShowPaymentAddChange exiting");
			return mapping.findForward("showCaseStatusGlobal");
		} else if (form.getSubmitbutton().compareTo("printhistory") == 0) {
			logger.debug("ShowPaymentAddChange printing history");
			// --- HANDLE PRINTING PAYMENT HISTORY REPORT ---
			try {
				t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
				// get form-ID for check form
				DbFormsAvailable[] forms = FdmsDb.getInstance().getFormsAvailableForLocale(t, sessionUser.getRegion(),
						DbFormsAvailable.PMTHISTORY_TYPE);
				if (forms.length < 1) {
					logger.debug("ShowPaymentAddChange - No payment history report found.");
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.nopmthist"));
					return (new ActionForward(mapping.getInput()));
				}

                String crystalFlag = UtilSingleton.getInstance().getProperty(sessionUser.getConfigID(), "CrystalServer.useReportingService");
                String pageName = null;
                
                if ( crystalFlag != null && "true".equals(crystalFlag) ) {
                	CrystalReportManagerBean crystalServerReport = new CrystalReportManagerBean(sessionUser.getConfigID());
    				pageName = crystalServerReport.printReport(sessionUser, forms[0].getFormId(), "", "", null, "","", true);
                }
                else
                {
                	ExportReport crystal = new ExportReport();
                    //default is OK crystal.setExportFormat("RTF");
                    pageName = crystal.printForm(sessionUser, forms[0].getFormId(), "", "", null, "", request, response, servlet.getServletContext());
                }

				form.setPreviewFile(pageName);

			} catch (PersistenceException pe) {
				logger.error("Persistence Exception in ShowPayment do.Perform. " + pe);
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getMessage()
						+ " " + pe.getCause()));
				saveErrors(request, errors);
			} finally {
				if (t != null)
					t.closeConnection();
			}

			return (new ActionForward(mapping.getInput()));
		} else if (form.getSubmitbutton().compareTo("voidPayment") == 0) {
			//--- HANDLE VOIDING PAYMENT ---
			// verify that a history item has been selected
			if (form.getSelectedPayment() == null || paymentid == 0) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.nopmtvoid"));
				formErrors.add("selectedPayment");
				request.setAttribute("formErrors", formErrors);
				saveErrors(request, errors);
				return (new ActionForward(mapping.getInput()));
			}
			
			java.util.Date checkDate = null;
			java.sql.Date voidDate = null;
	        try {
	            checkDate = FormatDate.convertToDate( form.getDateOfVoid());
	            voidDate = new java.sql.Date(checkDate.getTime());
	            
	        } catch (Exception e){
	            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.noVoidDate"));
	            formErrors.add("dateOfVoid");
	            request.setAttribute("formErrors", formErrors);
				saveErrors(request, errors);
				return (new ActionForward(mapping.getInput()));
	        } 
			
			
			
			//AppLog.trace("ShowPayment void processing:"+form.getSelectedPayment());
			try {
				FdmsDb.getInstance().paymentVoid(sessionUser, paymentid, form.getComment(),voidDate);
			} catch (PersistenceException pe) {
				logger.error("Persistence Exception in ShowPayment void processing. " + pe);
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getMessage()));
				saveErrors(request, errors);
			}
			return mapping.findForward("showPayment");

		} else if (form.getSubmitbutton().compareTo("printReceipt") == 0) {
			// --- HANDLE PRINTING A RECEIPT ---
			// verify that a history item has been selected
			if (form.getSelectedPayment() == null || paymentid == 0) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.nopmtreceipt"));
				formErrors.add("selectedPayment");
				request.setAttribute("formErrors", formErrors);
				saveErrors(request, errors);
				return (new ActionForward(mapping.getInput()));
			}
			//AppLog.trace("ShowPayment print receipt processing:"+form.getSelectedPayment());
			if (FormatNumber.parseInteger(form.getFormId()) < 1) {
				//AppLog.error("ShowPaymentAddChange - No receipt type selected.");
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
				formErrors.add("formId");
				request.setAttribute("formErrors", formErrors);
				return (new ActionForward(mapping.getInput()));
			}
			try {
				// save selected payment information to financial table
				saveLastPaymentData(sessionUser, paymentid, vitalsid);
			} catch (PersistenceException pe) {
				logger.error("Persistence Exception in ShowPaymentAddChange.saveLastPaymentData. " + pe);
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
				saveErrors(request, errors);
			}
			
			// print receipt for selected payment
			
            String crystalFlag = UtilSingleton.getInstance().getProperty(sessionUser.getConfigID(), "CrystalServer.useReportingService");
            String pageName = null;
            
            if ( crystalFlag != null && "true".equals(crystalFlag) ) {
            	CrystalReportManagerBean crystalServerReport = new CrystalReportManagerBean(sessionUser.getConfigID());
				pageName = crystalServerReport.printReport(sessionUser, FormatNumber.parseInteger(form.getFormId()),"", "", null, "", Integer.toString(paymentid), true);
            }
            else
            {
            	ExportReport crystal = new ExportReport();
            	crystal.setRecordIdSelParam(paymentid); // selection parameter
                pageName = crystal.printForm(sessionUser, FormatNumber.parseInteger(form.getFormId()), "", "", null, "", request, response, servlet.getServletContext());
            }

			//AppLog.trace("ShowPaymentAddChange.PrintReceipt sendRedirect:"+pageName);
			form.setPreviewFile(pageName);
			return (new ActionForward(mapping.getInput()));
		}
		// then action must be to add a payment.
		else if (form.getSubmitbutton().compareTo("add") != 0) {
			//AppLog.error("showPaymentAddChange unknown action.");
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", "Unknown command"));
		}


		// If errors found, bail out and return to input page
		if (!errors.isEmpty()) {
			logger.debug("ShowPaymentAddChange Invoking forward mapping getInput() ");
			saveErrors(request, errors);
			return (new ActionForward(mapping.getInput()));
		}
		// Prepare to show add-payment page
		java.util.ArrayList paymentAddChangeDisplayList = null;
		java.util.ArrayList payTypeList = null;
		java.util.ArrayList payMethodList = null;

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);

			try {
				// Do not allow payments to be entered if the case isn't posted.
				if (!com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().isCasePosted(t, vitalsid)) {
					//AppLog.error("showPaymentAddChange error: Cannot Enter Payment; Case NOT Posted.");
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.caseUnposted"));
				}
			} catch (Exception e) {
				//AppLog.error("showPaymentAddChange error: Cannot Enter Payment; Case NOT Posted.");
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.caseUnposted"));
			}

			// Do not allow payments to voided contracts.
			DbCase dbCase = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getCase(t, vitalsid);
			if (dbCase.getVoidedContractCode().equals("V")) {
				//AppLog.error("showPaymentAddChange error: Cannot Enter Payment; Case VOIDED.");
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.payment.caseVoided"));
			}

			// If errors found, bail out and return to input page
			if (!errors.isEmpty()) {
				//AppLog.trace("ShowPaymentAddChange Invoking forward mapping getInput() ");
				saveErrors(request, errors);
				return (new ActionForward(mapping.getInput()));
			}

			userlocale = FdmsDb.getInstance().getLocale(sessionUser.getDbLookup(), sessionUser.getRegion());
			paymentAddChangeDisplayList = new ArrayList();
			payTypeList = SessionHelpers.getSpeedDataOptionCollection(sessionUser, "PAYTYPES");
			payMethodList = SessionHelpers.getSpeedDataOptionCollection(sessionUser, "PAYMETHOD");
			updateCollectionRemoveDashes(payMethodList);
			
			dbComponent = FdmsDb.getInstance().getComponentsForID(t, vitalsid);
			//DbVitalsDeceased dbVitalsDeceased = fdmsdb.getVitalsDeceased( t, vitalsid );
			for (int i = 0; i < dbComponent.length; i++) {
				//if ((dbComponent[i].getSaleAmt() - dbComponent[i].getPaidAmt()) != 0) {
					String balance = FormatCurrency.toCurrency((long) (dbComponent[i].getSaleAmt() - dbComponent[i].getPaidAmt()));
					paymentAddChangeDisplayList.add(new OptionsList(String.valueOf(dbComponent[i].getId()),	dbComponent[i].getDescription() + ", " + balance));
				//}
			}
			
			//Create Form Object Passing in dbComponents List To Total Values
			paymentAddForm = new PaymentAddChangeForm();
			paymentAddForm.setDateOfDeposit(FormatDate.getCurrentDateFormatedMMDDYYYY());
			paymentAddForm.setDateOfPayment(FormatDate.getCurrentDateFormatedMMDDYYYY());
			paymentAddForm.setReceiptNumber(String.valueOf(userlocale.getNextReceiptNo()));
			// clean up

		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in ShowPayment do.Perform. " + pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getMessage() + " "
					+ pe.getCause()));
		} catch (Exception pe) {
			logger.error("Exception in  ShowPayment .doPerform. ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
		} finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}

		ActionForward actionForward = mapping.findForward("paymentAddChange");
		if (!errors.isEmpty()) {
			// AppLog.info("ShowPaymentAddChange Invoking forward mapping getInput() ");
			saveErrors(request, errors);
			actionForward = new ActionForward(mapping.getInput());
		}
		//Set Form Bean Into Scope
		session.setAttribute("paymentAddChange", paymentAddForm);
		// set component select collection into scope
		session.setAttribute("paymentAddChangeDisplayList", paymentAddChangeDisplayList);
		// set pay types select/options into session
		session.setAttribute("paytypes", payTypeList);
		// set pay methods select/options into session
		session.setAttribute("paymethods", payMethodList);
		
		//set for payment operation to prevent duplication
		//session.setAttribute("paymentOperation", "begin"); // no need this should check if it is processing.
		

		return actionForward;
	}

	/**
	 * Update the financial table fields storing information on the payment given.
	 * Creation date: (6/12/2003 2:09:55 PM)
	 * @param user com.aldorsolutions.webfdms.beans.DbUserSession
	 * @param pmtID int   transactionHistory table ID to payment record
	 * @exception PersistenceException Invalid payment ID
	 */
	public void saveLastPaymentData(DbUserSession user, int pmtID, int vitalsid)
			throws PersistenceException {

		DatabaseTransaction t = null; 
			
		try {

			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			DbFinancialSummary finan = null;
			DbHistory pmt = null;

			finan = FdmsDb.getInstance().getFinancial(t, vitalsid);
			pmt = FdmsDb.getInstance().getHistory(t, pmtID);

			if (finan == null) {
				throw new PersistenceException("saveLastPaymentData: Invalid vitals ID");
			}

			if (pmt == null || pmt.getCHistSPF() != 'P') {
				throw new PersistenceException("saveLastPaymentData: Invalid payment ID");
			}
			
			String lastPaymentDate = null;
			
			if ( pmt.getCHistDatePaid() != null ){
				lastPaymentDate = FormatDate.convertDateToMMDDYYYY(pmt.getCHistDatePaid());
			}
			
			finan.setCLastPmtDate(lastPaymentDate);
			finan.setCLastPmtMemo(pmt.getCHistDesc());
			finan.setLLastPmtAmount(-pmt.getLHistAmount());
			finan.setLLastReceiptNo(pmt.getLHistReceiptNo());
			//finan.setCLastPmtResp(    comp.getDescription());
			t.save();
		} finally {
			if ( t != null ) {
				t.closeConnection();
			}
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
}
