/*
 * ProceeLogin.java
 *
 * Created on January 31, 2005, 12:06 PM
 */

package fdms.admin.login.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fdms.admin.login.bean.LoginMangerBean;
import fdms.admin.login.form.LoginForm;

public class ProcessLogin extends Action {
    
    private Logger logger = Logger.getLogger(ProcessLogin.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String target = "failure";
        LoginForm form = (LoginForm) actionForm;
        
        String username = form.getUsername();
        String password = form.getPassword();
        
        try {
            LoginMangerBean bean = new LoginMangerBean();
            boolean isValid = bean.isValid(username, password);
            
            if (isValid) { 
                target = "success";
                request.getSession().setAttribute("FDMS_SA", Boolean.TRUE);                
            } else {
                ActionErrors errors = new ActionErrors();
                errors.add(ActionErrors.GLOBAL_ERROR,
                        new ActionError("error.password.mismatch"));
                saveErrors(request, errors);
            }
            
        } catch (Exception e) {
            logger.error("Exception in perform() : ", e);
        }
            
        return mapping.findForward(target);
    }
    
}
