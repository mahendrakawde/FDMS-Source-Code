package fdms.ui.struts.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fdms.ui.struts.form.FreeTrialRegistration;

public class ProcessFreeTrialAcceptance extends Action {
    
    private Logger logger = Logger.getLogger(ProcessFreeTrialAcceptance.class.getName());

    /**
     * Called from the FreeTrialAcceptance.jsp, this action handles the
     * add submit button.
     * @return ActionForward
     * Created 08/28/02 5:38PM
     */
    public ActionForward execute(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request, HttpServletResponse response)
                                    throws javax.servlet.ServletException, java.io.IOException {
        
        FreeTrialRegistration form = (FreeTrialRegistration) actionForm;
        ActionErrors errors = new ActionErrors();
        ActionForward actionForward = new ActionForward(mapping.getInput());
        Locale locale = getLocale(request);
        String submitType = form.getSubmitButton();
        
        //AppLog.info("ProcessFreeTrialAcceptance.doPerform:Submit = " +submitType);
        
        // First, if the user didn't chose to 'save', then exit back to login.jsp
        if (submitType == null || (!submitType.equals("save"))) {
            //AppLog.trace("ProcessFreeTrialAcceptance exiting to forwardLogon");
            actionForward = mapping.findForward("logon");
            return actionForward;
        }
        
        
        //Action Forward Logic
        //AppLog.info("ProcessFreeTrialAcceptance forwarding to Freetrialthankyou.");
        return mapping.findForward("Freetrialthankyou");
    }
}