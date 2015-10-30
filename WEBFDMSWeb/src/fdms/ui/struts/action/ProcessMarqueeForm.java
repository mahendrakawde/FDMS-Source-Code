package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fdms.ui.struts.form.MarqueeForm;


public class ProcessMarqueeForm extends Action {
    
    private Logger logger = Logger.getLogger(ProcessMarqueeForm.class.getName());
    
    public ActionForward execute(ActionMapping mapping,
        ActionForm actionForm,
        HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException {
        
        MarqueeForm form = (MarqueeForm) actionForm;
        ActionForward actionForward = mapping.findForward("closeWindow");
        fdms.ui.struts.DisplayMarquee displayMarquee = new fdms.ui.struts.DisplayMarquee();
        
        if (form.getSubmitButton() == null || form.getSubmitButton().equals("cancel")) {
            return actionForward;
        }
        
        if (form.getMarqueeText() != null && form.getMarqueeText().trim().length() > 0 &&
        form.getSubmitButton().equals("save")) {
            // Save our Marqee Message in the application scope.
            displayMarquee.setDisplayMarquee(true);
            displayMarquee.setMarqueeText(form.getMarqueeText());
            servlet.getServletContext().setAttribute("MarqueeDisplay",displayMarquee);
        } else {
            // Remove our Marquee Message from the application scope.
            servlet.getServletContext().removeAttribute("MarqueeDisplay");
        }
        
        return actionForward;
        
    }
}
