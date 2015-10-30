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

import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.UtilSingleton;

import fdms.ui.struts.form.CaseListOptionsForm;

public class ProcessCemListOptions extends Action {
    
    private Logger logger = Logger.getLogger(ProcessCaseListOptions.class.getName());

    /**
     * Called from the CaseListOptions.jsp, this action handles the
     * submit buttons.  The submit buttons are as follows:
     * 1) save: saves Case List display options in the session.
     * 2) exit: saves the default Case List display options in the session.
     *
     * @return ActionForward
     * Created 08/16/02 11:38AM
     */
    
    public ActionForward execute(ActionMapping mapping,
                                        ActionForm actionForm,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        
        CaseListOptionsForm form = (CaseListOptionsForm) actionForm;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        DatabaseTransaction t = null;
        
        //Action Forward Logic
        ActionForward actionForward = mapping.findForward("returnShowCaseList");
        
        // Get Form Directive
        String submitType = form.getSubmitbutton();
        // AppLog.trace("ProcessCaseListOptions Submit = " +submitType);        
        
        if (submitType.equals("exit")) {
            if (form.getOrderby() == null || form.getOrderby().trim().equals("")) {
                form.setOrderby("DeathDateKey");
            }
            if (form.getPerScreen() == null || form.getPerScreen().trim().equals("")) {
                form.setPerScreen("10");
            } else {
                if (Integer.parseInt(form.getPerScreen()) < 1 || Integer.parseInt(form.getPerScreen()) > 100) {
                    form.setPerScreen("10");
                }
            }
        } else {
            // Validate the form values
            if (form.getOrderby() == null || form.getOrderby().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caseListOptions.nullOrderby"));
            }
            if (form.getPerScreen() == null || form.getPerScreen().trim().equals("")) {
                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caseListOptions.nullPerScreen"));
            } else {
                if (Integer.parseInt(form.getPerScreen()) < 1 || Integer.parseInt(form.getPerScreen()) > 100) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.caseListOptions.invalidPerScreen"));
                }
            }
        }
        
        // Save Case List Options into User's record
        try {
            
           // t = (com.aldorsolutions.webfdms.database.DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
        	
        	String userLookup = UtilSingleton.getInstance().getUserDBLookup();
        	
            t = new DatabaseTransaction ( userLookup, sessionUser );
            t.setReadOnly(false);
            DbUser dbUser = (DbUser)FdmsDb.getInstance().getUser(t, sessionUser.getId());
            int caseNumPerScreen = FormatNumber.parseInteger(form.getPerScreen());
            dbUser.setCaseListPerScreen(caseNumPerScreen);
            dbUser.setCaseListSortOrder(form.getOrderby());
            dbUser.setCaseListDisplayPreneed(FormatString.booleanToYN(form.getDisplayPreneed()));
            dbUser.setCaseListDisplayVoided(FormatString.booleanToYN(form.getDisplayVoided()));

            t.save();
                        
            sessionUser.setCaseListDisplayPreneed(FormatString.booleanToYN(form.getDisplayPreneed()));
            sessionUser.setCaseListDisplayVoided(FormatString.booleanToYN(form.getDisplayVoided()));
            sessionUser.setCaseListPerScreen(caseNumPerScreen);
            sessionUser.setCaseListSortOrder(form.getOrderby());
            session.setAttribute("USER_START_INDEX", new Integer(1));
            
        } catch(PersistenceException pe) {
            logger.error("PersistenceException in doPerform() : " + pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.PersistenceException",pe.getCause()));
        } catch(Exception pe) {
            logger.error("Error in doPerform() : ", pe);
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",pe.getMessage()));
        } finally {
            if (t != null) {
                try {
                    t.closeConnection();
                    t = null;
                }  catch (Exception e) {
                    logger.error("Error in closeConnection() : ", e);
                }
            }
        }
        
        if (!errors.isEmpty() ) {
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput());
            return actionForward;
        }
        
        session.removeAttribute("caseList");
        return actionForward;
        
    }
}
