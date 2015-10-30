package fdms.ui.struts.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
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

import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbSurvivorPeer;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.SurvivorsAddChangeForm;
import fdms.ui.struts.form.SurvivorsForm;

public class ShowSurvivorsAddChange extends Action {

	private Logger logger = Logger.getLogger(ShowSurvivorsAddChange.class.getName());

	private ArrayList formErrors;

	/**
	 * ShowSurvivorsAddChange.doPerform This class responds to the choice of
	 * survivor made by the user. It fetches the selected survivor and displays
	 * the information on the next JSP page.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.debug("ShowSurvivorsAddChange.doPerform ");

		formErrors = new ArrayList();
		SurvivorsForm form = (SurvivorsForm) actionForm;
		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;
		FdmsDb fdmsdb = null;
		DbSurvivor[] survivorarray = null;
		ArrayList survivorsList = new ArrayList();
		SurvivorsAddChangeForm survivorsAddChange = null;
		int vitalsid = 0;
		ActionForward actionForward = null;
		String submitType = form.getSubmitbutton();

		logger.debug("ShowSurvivorsAddChange.submitType = " + submitType);

		vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
		if (vitalsid < 1) {
			logger.debug("ShowSurvivorsAddChange. Invalid VitalsID to process: " + vitalsid);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
		}

		if (form.isArrayChanged()) {
			setSequence(sessionUser, vitalsid, form, errors);
		}

		if (submitType.equals("exit")) {
			actionForward = mapping.findForward("showCaseOverview");
			return actionForward;
		}

		if (!errors.isEmpty()) {
			logger.debug("ShowSurvivorsAddChange Invoking forward mapping getInput() ");
			saveErrors(request, errors);
			actionForward = new ActionForward(mapping.getInput());
			return actionForward;
		}

		// Populate Form Bean Properties
		survivorsAddChange = new SurvivorsAddChangeForm();
		survivorsAddChange.setDirective(form.getSubmitbutton());
		survivorsAddChange.setDeceasedFullName(form.getDeceasedFullName());

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			fdmsdb = FdmsDb.getInstance();
			survivorarray = fdmsdb.getSurvivorsForID(t, vitalsid);

			// Populate the SurvivorsForm object
			if (survivorarray != null) {
				logger.debug("survivorarray length is:" + survivorarray.length);
				// Iterate Through result DB object and store as a Session
				// Variable
				for (int i = 0; i < survivorarray.length; i++) {
					DbSurvivor dbs = (DbSurvivor) survivorarray[i];
					if (dbs.getISeqKey() > 1 && dbs.getISeqKey() < 1000) {
						com.aldorsolutions.webfdms.util.OptionsList dssd = new com.aldorsolutions.webfdms.util.OptionsList(new Integer(dbs.getId())
								.toString(), dbs.getCSurvivorFName() + " " + dbs.getCSurvivorLName() + " ("
								+ dbs.getCSurvivorRelated() + ")");
						survivorsList.add(dssd);
					}
				}
			}

			if (survivorsList.size() > 0) {
				form.setEmptySet(false);
			} else {
				form.setEmptySet(true);
			}

			if (submitType.equals("change") || submitType.equals("delete")) {
				if (form.getSurvivor() == null || form.getSurvivor().trim().length() == 0) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tables.noselect"));
					formErrors.add("survivor");
				} else {
					logger.debug("ShowSurvivorsAddChange: Survivor Id : " + form.getSurvivor());
					DbSurvivor dbSurvivor = fdmsdb.getSurvivor(t, FormatNumber.parseInteger(form.getSurvivor()));
					survivorsAddChange.setSurvivorId(FormatNumber.parseInteger(form.getSurvivor()));
					survivorsAddChange.setStateOfDeath(dbSurvivor.getCSurvivorState());
					survivorsAddChange.setRelationship(dbSurvivor.getCSurvivorRelated());
					survivorsAddChange.setInformantSalutation(dbSurvivor.getCSurvivorSalutation());
					survivorsAddChange.setInformantFirstName(dbSurvivor.getCSurvivorFName());
					survivorsAddChange.setInformantMiddleName(dbSurvivor.getCSurvivorMName());
					survivorsAddChange.setInformantLastName(dbSurvivor.getCSurvivorLName());
					survivorsAddChange.setInformantSuffix(dbSurvivor.getCSurvivorSuffix());
					survivorsAddChange.setInformantMaidenName(dbSurvivor.getCSurvivorMaidenName());
					survivorsAddChange.setInformantDisplayName(dbSurvivor.getCSurvivorDisplayName());
					survivorsAddChange.setInformantStreet(dbSurvivor.getCSurvivorAddr());
					survivorsAddChange.setInformantStreet2(dbSurvivor.getCSurvivorAddr2());
					survivorsAddChange.setCityOfDeath(dbSurvivor.getCSurvivorCity());
					survivorsAddChange.setInformantPhone(FormatString.formatPhone(dbSurvivor.getCSurvivorPhone()));
					survivorsAddChange.setInformantPhone2(FormatString.formatPhone(dbSurvivor.getCSurvivorPhone2()));
					survivorsAddChange.setZipCodeOfDeath(dbSurvivor.getCSurvivorZip());
					survivorsAddChange.setInformantEmail(dbSurvivor.getCSurvivorEmail());
					survivorsAddChange.setInformantLiving(dbSurvivor.getCSurvivorLiving());
					if (dbSurvivor.getCSurvivorPNLead() != null && dbSurvivor.getCSurvivorPNLead().equals("Y")) {
						survivorsAddChange.setInformantPNLead(true);
					} else {
						survivorsAddChange.setInformantPNLead(false);
					}
				}
			}

		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in ShowSurvivorsAddChange. " + pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));

		} catch (Exception pe) {
			logger.error("Exception in  ShowSurvivorsAddChange. ", pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException", pe.getMessage()));
		} finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}

		// Set form object into scope session.
		session.setAttribute("survivorsAddChange", survivorsAddChange);
		request.setAttribute("survivorsList", survivorsList);

		// Action Forward Logic
		actionForward = mapping.findForward("survivorsAddChange");

		if (!errors.isEmpty()) {
			logger.debug("ShowSurvivorsAddChange Invoking forward mapping getInput()");
			saveErrors(request, errors);
			request.setAttribute("formErrors", formErrors);
			actionForward = new ActionForward(mapping.getInput());
		}

		return actionForward;

	}

	/**
	 * Insert the method's description here. Creation date: (10/31/2002 1:20:39
	 * PM)
	 * 
	 * @param form
	 *            fdms.ui.struts.form.SurvivorsForm
	 * @param errors
	 *            org.apache.struts.action.ActionErrors
	 */
	public void setSequence(DbUserSession sessionUser, int vitalsId, SurvivorsForm form, ActionErrors errors) {

		DbSurvivor[] dbSurvivors = null;
		DbSurvivor dbSurvivor = null;
		DatabaseTransaction t = null;
		boolean continueLoop = true;
		int i = 1;
		int startSequence = 0;

		try {
			// Get the current Survivors List
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			dbSurvivors = FdmsDb.getInstance().getSurvivorsForID(t, vitalsId, DbSurvivorPeer.SEQNUMBER);

			// Get the lowest sequence number
			for (int j = 0; j < dbSurvivors.length; j++) {
				dbSurvivor = dbSurvivors[j];
				if (dbSurvivor.getISeqKey() > 1 && dbSurvivor.getISeqKey() < 1000) {
					startSequence = dbSurvivor.getISeqKey();
					break;
				}
			}

			while (continueLoop) {
				String survivorId = CsvTable.getField(form.getSurvivorsArray(), i);
				if (survivorId != null && survivorId.trim().length() > 0) {
					dbSurvivor = FdmsDb.getInstance().getSurvivor(t, Integer.parseInt(survivorId));
					logger.debug("Changing " + dbSurvivor.getId());
					logger.debug("Sequence from " + dbSurvivor.getISeqKey());
					logger.debug("To " + startSequence);
					dbSurvivor.setISeqKey(Short.parseShort(String.valueOf(startSequence)));
					i++;
					startSequence++;
				} else {
					continueLoop = false;
					t.save();
				}
			}

		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in ShowSurvivorsAddChange.setSequence " + pe);
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException", pe.getCause()));
		} finally {
			if ( t != null ) {
				t.closeConnection();
			}
		}

		return;

	}
}
