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

import com.aldorsolutions.webfdms.beans.DbObituary;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.CaseStatusObitForm;

public class ShowCaseStatusObit extends Action {
    
    private Logger logger = Logger.getLogger(ShowCaseStatusObit.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
                                      throws javax.servlet.ServletException,
                                        java.io.IOException {
                                            
        ActionErrors errors = new ActionErrors();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        CaseStatusObitForm caseStatusObit = new CaseStatusObitForm ();
        DatabaseTransaction t = null;
        
        DbObituary obituary = null;
        int vitalsid = 0;
        
        if (sessionUser == null) {
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.login.invalid"));
        }
        
        // Get vitals-ID from request or session
        vitalsid = SessionHelpers.getVitalsIdFromSession(request, sessionUser);
        
        //Database Access Logic
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            obituary	= FdmsDb.getInstance().getObituary(t, vitalsid);
            
            // --- Populate OBITUARY section ---
            if (obituary != null){
            	caseStatusObit.setObituary(	obituary.getObitNotice() );
            }
            
        } catch(PersistenceException pe) {
            logger.error("Persistence Exception in ShowCaseStatus.doPerform. " + pe);
        } catch(Exception pe) {
            logger.error("Exception in ShowCaseStatus.doPerform. ", pe);
        } finally {
            if (t != null) t.closeConnection();
        }
        
        request.setAttribute("caseOverviewObit",caseStatusObit);
        
        return mapping.findForward("showCaseStatusObitJsp");
        
    }
    
}
