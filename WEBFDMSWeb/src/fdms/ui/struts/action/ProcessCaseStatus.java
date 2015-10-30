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
import org.apache.struts.action.ActionMessages;

import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.CaseStatusForm;


public class ProcessCaseStatus extends Action {

    private Logger logger = Logger.getLogger(ProcessCaseStatus.class.getName());

    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        
        CaseStatusForm form = (CaseStatusForm) actionForm;
        
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        String submitType = form.getSubmitButton();
        ActionForward actionForward = mapping.findForward("showCaseOverview");
        int vitalsId = 0;

        // AppLog.trace("ProcessCaseStatus.submitType: "+submitType);

        // See if vitalsId is set in form
        try {
            if (form.getVitalsId() != null && Integer.parseInt(form.getVitalsId()) > 0) {
                vitalsId = Integer.parseInt(form.getVitalsId());
                // AppLog.trace("ProcessCaseStatus.doPerform.vitalsId: " +Integer.parseInt(form.getVitalsId()));
                SessionHelpers.setVitalsIdInRequest(request, vitalsId);
            }
        } catch (Exception e) {
            logger.error("Error in doPerform() : ", e);
        }

        if (vitalsId == 0) {
            vitalsId = sessionUser.getCurrentCaseID();
        }
        // AppLog.trace("ProcessCaseStatus for ID: " +vitalsId);

        if (!submitType.equals("save")) {
            return actionForward;
        }

        // Database Access
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);

            DbCase dbCase = FdmsDb.getInstance().getCase(t, vitalsId);
            if (form.isArchive()){
            	dbCase.setActiveCode(0);
            }else {
            	dbCase.setActiveCode(1);
        	}	
            t.addPersistent(dbCase);
            
            if (form.isVoidContract()) {
                FdmsDb.getInstance().voidCase(t, vitalsId);
            }

            /* Set Locked and Locked By Whom
           DbCase dbCase = (DbCase) FdmsDb.getInstance().getCase(t, vitalsId);           
           
           if (form.isLocked()) {
              if (!dbCase.isLocked()) {
                     dbCase.setLocked(1);
                     dbCase.setLockedByWhom(sessionUser.getUserName());
              }
           } else {
              if (dbCase.isLocked()) {
                     dbCase.setLocked(0);
                     dbCase.setLockedByWhom("");
              }
           }
           */

            t.save();

        } catch(PersistenceException pe) {
        	errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionError("error.PersistenceException",pe.getMessage() )  );
        	saveErrors(request, errors );
            logger.error("PersistenceException in doPerform() : " + pe);
        } catch(Exception pe) {
            logger.error("Error in doPerform() : ", pe);
        } finally {
            if (t != null) {
				t.closeConnection();
				t = null;
			}
        }

        // Return to CaseStatus
        return actionForward;

    }
}
