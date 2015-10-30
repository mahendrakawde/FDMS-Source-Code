package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.SessionValueKeys;


public class ProcessCancelAction extends Action {    

    public ActionForward execute(ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
                                        throws javax.servlet.ServletException, java.io.IOException {
        /**@todo: implement this method */
        //throw new IllegalAccessError("Method not yet implemented");
        ActionForward actionForward;
        HttpSession session = request.getSession();
        DbUserSession sessionUser = (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
        int vitalsid=0;
        
        // retrieve case-ID to process for user-session object
        if (vitalsid==0) {
            vitalsid = sessionUser.getCurrentCaseID();
        }
        
        if(vitalsid != 0)
            actionForward = mapping.findForward("showCaseStatusGlobal");
        else
            actionForward = mapping.findForward("caseList");
        
        return actionForward;
    }
}
