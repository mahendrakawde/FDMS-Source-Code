package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.LogonChangePasswordForm;

public class ShowLogonChangePassword extends Action {
    
    private Logger logger = Logger.getLogger(ShowLogonChangePassword.class.getName());

    /**
     * Called from loginChangePassword.jsp, this action either verifies a user has access to the system
     * or logs into demo database or allows setting up a free trial.
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        
        logger.debug("*** Entering perform() in ShowLogonChangePassword ***");
        
        HttpSession session = request.getSession();
        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
        
        SecurityManagerBean mbSecurity = new SecurityManagerBean ();
        SecurityConfigDTO dtoSecurity = mbSecurity.getSecurityConfig(user.getCompanyID());
        
        ArrayList patternDesc = SecurityManagerBean.getPasswordPatternDescription(dtoSecurity);
                
        LogonChangePasswordForm lChangePassForm = new LogonChangePasswordForm();
        lChangePassForm.setUserID(user.getId());
        lChangePassForm.setChangePassword(user.isChangePassword());
        lChangePassForm.setUserLocked(user.isLocked());
        lChangePassForm.setUsername(user.getUserName());
//        lChangePassForm.setPasswordPatternDesc(patternDesc);
        
        request.setAttribute(mapping.getName(), lChangePassForm);
        
        session.setAttribute("passwordPatternDesc", patternDesc);
        
        ActionForward changePassForward = null;
        changePassForward = mapping.findForward("changePasswordForm");
        
        return ( changePassForward );
    }

}