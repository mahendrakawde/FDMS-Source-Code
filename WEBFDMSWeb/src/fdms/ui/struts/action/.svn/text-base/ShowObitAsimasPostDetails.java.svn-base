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

import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbObituary;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVisitations;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.visitation.bean.VisitationManagerBean;

import fdms.ui.struts.form.ObitAsimasForm;

/**
 * Shows Obituary details required posted to ASIMAS. 
 * @author Srini Kotha
 *
 */
public class ShowObitAsimasPostDetails extends Action{
	
	private Logger logger = Logger.getLogger(ShowObitAsimasPostDetails.class.getName());

	/**
	 * Gobal Action, this action shows obituary details
	 * required by ASIMAS. 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
		ActionErrors errors = new ActionErrors();
	   	DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		DatabaseTransaction t = null;
		DbObituary obituary = null;
		DbVitalsDeceased deceased = null;
		DbLocation location = null;
		DbCase caseinfo = null;
		DbServices srv = null;
		ArrayList visitations = null;
		int vitalsid=0;
		ObitAsimasForm obitAsimasForm = (ObitAsimasForm) form;
		
		if (sessionUser == null) {
			   errors.add(
		                ActionErrors.GLOBAL_ERROR,
		                new ActionError("error.login.invalid")
		                );
		           
			} else {
		            vitalsid=SessionHelpers.getVitalsIdFromSession(request, sessionUser);
		            if (vitalsid < 1){
		                   errors.add(
		                    ActionErrors.GLOBAL_ERROR,
		                    new ActionError("error.ui.nocase")
		                    );
		            }
			}
 
		try{
			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
			obituary = FdmsDb.getInstance().getObituary(t, vitalsid);
			deceased = FdmsDb.getInstance().getVitalsDeceased(t,vitalsid);
			caseinfo = FdmsDb.getInstance().getCase(t, vitalsid);
			location = FdmsDb.getInstance().getLocation(t, caseinfo.getChapelNumber());
			srv		 	= FdmsDb.getInstance().getServices(t,vitalsid);
			VisitationManagerBean visitationManager = new VisitationManagerBean();
			visitations = visitationManager.getVisitationsForID(vitalsid, sessionUser);
	        	                
		} catch(PersistenceException pe) { 
			logger.error("Persistence Exception in ShowObitAsimasPostDetails.doPerform. " + pe);
			errors.add(
	                    ActionErrors.GLOBAL_ERROR,
	                    new ActionError("error.PersistenceException",pe.getCause())
	                    );
		} catch(Exception pe) { 
			logger.error("Exception in  ShowObitAsimasPostDetails .doPerform. ", pe);
			errors.add(
	                    ActionErrors.GLOBAL_ERROR,
	                    new ActionError("error.GeneralException",pe.getMessage())
	                    );
	        } finally {
	            if (t != null) t.closeConnection();
	        }
	        if(srv != null)
	        	obitAsimasForm.setSrv(srv);
	        visitations = getPublicVisitations(visitations);
			if(visitations != null && visitations.size() > 0){
				
				obitAsimasForm.setVisitation1((DbVisitations)visitations.get(0));
			}
			if(visitations != null && visitations.size() > 1){
				obitAsimasForm.setVisitation2((DbVisitations)visitations.get(1));
			}
			if(visitations != null && visitations.size() > 2){
				obitAsimasForm.setVisitation3((DbVisitations)visitations.get(2));
			}
	        populateForm( obitAsimasForm,  obituary, deceased);
	        
	        obitAsimasForm.setUrl(location.getWebsite());
	        
	        return mapping.findForward("obitasimaspostdetails");
	}
	
	/**
	 * Populates obituary details
	 * @param form
	 * @param obituary
	 * @param deceased
	 * @param srv
	 * @param visitation
	 */
	public void populateForm(ObitAsimasForm form, DbObituary obituary, 
				DbVitalsDeceased deceased ){
		
		if(obituary != null){
			form.setObituaryText(obituary.getObitNotice());
			form.setAsimasId(obituary.getAsimasId());
		}else{
			form.setObituaryText("");
		}
		if(deceased != null){
			form.setFirstName(deceased.getFirstName());
			form.setLastName(deceased.getLastName());
			form.setDateOfBirth(FormatDate.MDYtoMMDDYYYY(deceased.getDateOfBirth()));
			form.setDateOfDeath(FormatDate.MDYtoMMDDYYYY(deceased.getDateOfDeath()));
			form.setServiceDate(FormatDate.MDYtoMMDDYYYY(deceased.getBurialDate()));
		}else{
			form.setFirstName("");
			form.setLastName("");
			form.setDateOfBirth("");
			form.setDateOfDeath("");
			form.setServiceDate("");
		}
		
	}
	
	/**
	 * Gets public visitations only
	 * @param visitations list of visitations
	 * @return list of public visitations.
	 */
	public ArrayList getPublicVisitations(ArrayList visitations){
		ArrayList publicVisitations = new ArrayList();
		for(int i=0; visitations != null && i <  visitations.size(); i++){
			if(((DbVisitations)visitations.get(i)).getPrivateVisitation().equalsIgnoreCase("No")){
				publicVisitations.add(visitations.get(i));
			}
		}
		return publicVisitations;
	}
}
