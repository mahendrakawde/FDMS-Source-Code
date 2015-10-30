package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fdms.ui.struts.form.FirstCallInformationForm;

public class ProcessNewAtNeed extends Action {
    
    private Logger logger = Logger.getLogger(ProcessNewAtNeed.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        /**@todo: implement this method */
        // throw new IllegalAccessError("Method not yet implemented");
        FirstCallInformationForm form = (FirstCallInformationForm) actionForm;
        
        return mapping.findForward("showCaseStatusGlobal");
    }
}
