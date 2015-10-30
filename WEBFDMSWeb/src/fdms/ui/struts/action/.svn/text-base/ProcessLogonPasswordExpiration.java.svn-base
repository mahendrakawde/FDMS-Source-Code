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
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.LogonPasswordExpirationForm;

public class ProcessLogonPasswordExpiration extends Action {
    
    private Logger logger = Logger.getLogger(ProcessLogonPasswordExpiration.class.getName());

    /**
     * Called from login.jsp, this action either verifies a user has access to the system
     * or logs into demo database or allows setting up a free trial.
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        logger.debug("*** Entering perform() in ProcessLogonPasswordExpiration ***");
            
        LogonPasswordExpirationForm pForm = (LogonPasswordExpirationForm) form;
        
        HttpSession session = request.getSession();
        ActionErrors errors = new ActionErrors();
        
        DbUserSession userSession = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
        DbUser user = (DbUser) userSession;
        
        String submitType = new String(pForm.getSubmitButton());
        logger.debug("Submit type : " + submitType);
        
        if ( user == null ) {
        	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.mismatch"));
            saveErrors(request, errors);
            return (mapping.findForward("logon"));
        }
        
        if ( submitType == null || submitType.trim().equals("") ) {
        	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.mismatch"));
            saveErrors(request, errors);
            return (mapping.findForward(mapping.getInput()));
        }
        
        // See if this user is already logged into the system.
        if (pForm.getSubmitButton().equals("Yes")) {            
            return mapping.findForward("changePasswordForm");
        }
        else {
        	return mapping.findForward("introduction");
        }
        
    }

}
