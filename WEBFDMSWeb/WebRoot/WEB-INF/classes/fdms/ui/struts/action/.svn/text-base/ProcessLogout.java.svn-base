package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;


public class ProcessLogout extends Action {
    
    private Logger logger = Logger.getLogger(ProcessLogout.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        if (sessionUser != null){
            servlet.getServletContext().removeAttribute(sessionUser.getUserName());
            session.removeAttribute(SessionValueKeys.DB_USER);
        }
        
        session.invalidate();
        
        return mapping.findForward("logon");
    }
}
