/*
 * ProcessLogout.java
 *
 * Created on February 1, 2005, 9:10 AM
 */

package fdms.admin.login.action;

/**
 *
 * @author Guadalupe Garcia
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ProcessLogout extends Action {
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) 
            throws ServletException, IOException {
        
            HttpSession session = request.getSession();
            session.invalidate();
            
        return mapping.findForward("login");
    }
    
}
