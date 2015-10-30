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

import com.aldorassist.webfdms.delegate.LocationOptionsManager;
import com.aldorassist.webfdms.model.LocationOptionsDTO;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbSurvivorPeer;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsNextKin;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.SurvivorsForm;

public class ShowSurvivors extends Action {

	private Logger logger = Logger.getLogger(ShowSurvivors.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

		ActionErrors errors = new ActionErrors();
		java.util.ArrayList relativesList = new java.util.ArrayList();
		DatabaseTransaction t = null;
		DbSurvivor[] survivorarray = null;

		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		SurvivorsForm survivors = ((SurvivorsForm) actionForm);

		/*code added by Bhavesh for #5575 Relative page Issue  point 1 */
		LocationOptionsManager locationOptionManager = new LocationOptionsManager();
		int locationOptionValue = locationOptionManager.getLocationOptionValue(sessionUser.getCompanyID(),sessionUser.getLocationId(),LocationOptionsDTO.LOCATION_OPTION_SHOW_FATHER_MOTHER_INFO_FROM_ON_RELATIVE_PAGE);
		  
		int vitalsid = 0;
		if (sessionUser == null) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
		} else {
			vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
			if (vitalsid < 1) {
				//AppLog.warning("ShowSurvivors. Invalid VitalsID: " +vitalsid);
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.ui.nocase"));
			}
		}

		//Database Access Logic
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			FdmsDb fdmsdb = FdmsDb.getInstance();
			survivorarray = fdmsdb.getSurvivorsForID(t, vitalsid, DbSurvivorPeer.SEQNUMBER);
			DbVitalsDeceased dbVitalsDeceased = fdmsdb.getVitalsDeceased(t, vitalsid);
			DbVitalsNextKin nextKin = fdmsdb.getVitalsNextKin(t, vitalsid);
			((SurvivorsForm) actionForm).setSameAsInformant(nextKin.getSameAsInformant());
			((SurvivorsForm) actionForm).setLocationOptionValue(locationOptionValue);
			
			//Populate the SurvivorsForm object
			survivors.setDeceasedFullName(dbVitalsDeceased.getDecFullName());
			if (survivorarray != null) {
				//Iterate Through result DB object and store as a Session Variable
				for (int i = 0; i < survivorarray.length; i++) {
					DbSurvivor dbs = (DbSurvivor) survivorarray[i];
					if (dbs.getISeqKey() > 0 && dbs.getISeqKey() < 1000) {
						
					/*	if(!dbs.getCGroupType().equals("NK") && !dbs.getCGroupType().equals("VITAL") ) {
							relativesList.add(dbs);	
						}else if(dbs.getCGroupType().equals("NK") && nextKin.getSameAsInformant().equals("N")) {
							relativesList.add(dbs);
						}/*code added by Bhavesh for #5575 Relative page Issue point 1 
						else if (dbs.getCGroupType().equals("VITAL") && locationOptionValue == 1){
							relativesList.add(dbs);
						}*/
						
						/*if (dbs.getCSurvivorAddr().trim().compareToIgnoreCase("null") == 0 ){
							dbs.setCSurvivorAddr("");
						}*/
						relativesList.add(dbs);
					}
				}
			}

		} catch (java.lang.Exception pe) {
			logger.error("Persistence Exception in ShowSurvivors.doPerform. ", pe);
		} finally {
			if (t != null)
				t.closeConnection();
		}

		//Set object in scope
		((SurvivorsForm) actionForm).setRelativesList(relativesList);
		((SurvivorsForm) actionForm).setDbRelativesList(relativesList);
		
		

		if (!errors.isEmpty()) {
			//AppLog.info("ShowSurvivors Invoking forward mapping getInput() ");
			saveErrors(request, errors);
			return new ActionForward(mapping.getInput());
		}

		return mapping.findForward("survivors");

	}
}
