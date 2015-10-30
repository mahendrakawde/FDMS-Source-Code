package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fdms.ui.struts.form.ReportsPrintForm;


public class ProcessReportsPrint extends Action {
    
    private Logger logger = Logger.getLogger(ProcessReportsPrint.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
    ActionForm actionForm,
    HttpServletRequest request, HttpServletResponse response)
    throws javax.servlet.ServletException, java.io.IOException {
        
        ReportsPrintForm form = (ReportsPrintForm) actionForm;
        ActionErrors errors = new ActionErrors();
        
        ActionForward actionForward = mapping.findForward("showReportsGlobal");
        
        try {
            if (form.getCategory().equals("inventory")) {
                actionForward = mapping.findForward("showInventoryGlobal");
            }
        } catch(Exception pe) {
            logger.error("Exception in ProcessReportsPrint.doPerform. ", pe);
        }
        
        if (!errors.isEmpty() ) {
            logger.debug("ProcessReportsPrint Invoking forward mapping getInput()");
            saveErrors(request, errors );
            actionForward = new ActionForward(mapping.getInput());
        }
        
        logger.debug("Leaving ProcessReportsPrint.");
        return actionForward;
    }
}
