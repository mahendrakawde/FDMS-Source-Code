package fdms.ui.struts.action;

import java.sql.Timestamp;

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

import fdms.ui.struts.form.LogonPasswordExpirationForm;

public class ShowLogonPasswordExpiration extends Action {
    
    private Logger logger = Logger.getLogger(ShowLogonPasswordExpiration.class.getName());

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
        
        logger.debug("*** Entering perform() in ShowLogonPasswordExpiration ***");
        
        HttpSession session = request.getSession();
        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
        
        SecurityManagerBean mbSecurity = new SecurityManagerBean ();
        SecurityConfigDTO dtoSecurity = mbSecurity.getSecurityConfig(user.getCompanyID());
        
        int expireDays = dtoSecurity.getPasswordExpirationInDays();
        boolean expireEnforced = dtoSecurity.isPasswordExpirationEnforced();
        
        if ( expireEnforced ) {
        	Timestamp passwordSetDate = user.getPasswordTimestamp();
        	
        	long passwordExpiresInDays = SecurityManagerBean.calculatePasswordExpirationInDays ( expireDays, passwordSetDate );
        	
        	LogonPasswordExpirationForm lpeForm = new LogonPasswordExpirationForm();
            lpeForm.setUserID(user.getId());
            lpeForm.setUsername(user.getUserName());
            lpeForm.setExpiresInDays(passwordExpiresInDays);
            
            request.setAttribute(mapping.getName(), lpeForm);
            
            return ( mapping.findForward("passwordExpirationForm") );
        }
        else {
        	return ( mapping.findForward("introduction") );
        }
      
    }

}