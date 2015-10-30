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

import com.aldorsolutions.webfdms.beans.DbBillto;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.EditChecks;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.FinancialBillToPartiesAddChangeForm;

public class ProcessFinancialBillToPartiesAddChange extends Action {

	private Logger logger = Logger.getLogger(ProcessFinancialBillToPartiesAddChange.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

		FinancialBillToPartiesAddChangeForm form = (FinancialBillToPartiesAddChangeForm) actionForm;
		ActionErrors errors = new ActionErrors();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;
		DbBillto dbBillTo = null;
		FdmsDb fdmsdb = null;
		int vitalsid = 0;
		if (sessionUser == null) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
		} else {
			vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
			if (vitalsid < 1) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
			}
		}
		String directive = form.getDirective();
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			fdmsdb = FdmsDb.getInstance();
			if (directive.equals("cancel")) {
				//do nothing
			}
			else {
				if (directive.equals("add")) {
					dbBillTo = new DbBillto();
					dbBillTo.setNew();
					dbBillTo.setVitalsID(vitalsid);
					t.addPersistent(dbBillTo);
				} else if (directive.equals("change")) {
					dbBillTo = fdmsdb.getBillto(t, FormatNumber.parseInteger(form.getBillToPartyId()));
				} else if (directive.equals("delete")) {
					dbBillTo = fdmsdb.getBillto(t, FormatNumber.parseInteger(form.getBillToPartyId()));
					dbBillTo.setDeleteCode("D");
				} else {
					// AppLog.error("ProcessFinancialBilltopartiesAddChange
					// unrecognized directive");
				}
				dbBillTo.setHonorific(form.getBillToPartyTitle());
				dbBillTo.setRelation(form.getBillToPartyRelation());
				dbBillTo.setFirstName(form.getBillToPartyFirstName());
				dbBillTo.setLastName(form.getBillToPartyLastName());
				dbBillTo.setStreet1(form.getBillToPartyAddress1());
				dbBillTo.setStreet2(form.getBillToPartyAddress2());
				dbBillTo.setStreet3(form.getBillToPartyAddress3());
				dbBillTo.setStreet4(form.getBillToPartyAddress4());
				dbBillTo.setCity(form.getBillToPartyCity());
				dbBillTo.setState(form.getBillToPartyState());
				dbBillTo.setZip(form.getBillToPartyZipCode());
				dbBillTo.setEmailAddress(form.getBillToPartyEMailAddress());
				dbBillTo.setLanguage(form.getBillToPartyLanguage());
				dbBillTo.setHomePhone(FormatString.formatPhone(form.getBillToPartyHomePhone()));
				dbBillTo.setWorkPhone(FormatString.formatPhone(form.getBillToPartyWorkPhone()));
				dbBillTo.setCellPhone(FormatString.formatPhone(form.getBillToPartyCellPhone()));
				dbBillTo.setSocialSecurityNo(EditChecks.editSocSecNo(form.getBillToPartySocialSecurityNumber()));
				dbBillTo.setCashSale(SessionHelpers.convertBoolToYn(form.getBillToPartyCashSale()));
				dbBillTo.setRefused(SessionHelpers.convertBoolToYn(form.getBillToPartyRefused()));
				dbBillTo.setContractSigner(SessionHelpers.convertBoolToYn(form.getBillToPartyContractSigner()));
				dbBillTo.setSendInvoice(SessionHelpers.convertBoolToYn(form.getBillToPartyReceiveInvoice()));
				// clean up
				t.save();
			}
		} catch (PersistenceException pe) {
			logger.error("PersistenceException in doPerform() : " + pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
		} catch (Exception pe) {
			logger.error("Error in doPerform() : ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
		} finally {
			if (t != null) {
				try {
                    t.closeConnection();
                    t = null;
                } catch (Exception e) {
					logger.error("Error in closeConnection() : ", e);
				}
			}
		}

		ActionForward actionForward = mapping.findForward("financialBillToParties");
		if (!errors.isEmpty()) {
			// forward mapping getInput() ");
			saveErrors(request, errors);
			actionForward = new ActionForward(mapping.getInput());
		}
		return actionForward;
	}

}
