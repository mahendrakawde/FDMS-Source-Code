package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.util.FormatNumber;


public class ShowIntroduction extends Action {
	
    private Logger logger = Logger.getLogger(ShowIntroduction.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
    	
    	
    	String active = request.getParameter("active");
    	String preneedOnly = request.getParameter("preneedOnly");
    	    	
    	if (active != null) {
    		logger.error("Request active = " + active);
    		logger.error("Session active = " + (String) request.getSession().getAttribute("SHOW_ACTIVE_CASES"));
    		try {
    			int activei = FormatNumber.parseInteger(active);

    			if ((!"Y".equals(preneedOnly)) && (!"N".equals(preneedOnly))) preneedOnly = "N";
    			
    			logger.debug("Preneed Only = " + preneedOnly);
    			
    			if ((activei == 0) || (activei == 1)) {
    				request.getSession().setAttribute(Constants.SHOW_ACTIVE_CASES, Integer.toString(activei));
    				request.getSession().setAttribute(Constants.SHOW_PRENEED_ONLY, preneedOnly);
    				request.getSession().setAttribute(Constants.USER_START_INDEX, new Integer(1));  
    			}
    		} catch (Exception e) {
    			logger.error("Exception in perform() : ", e);
    		}
    	}
    	
    	return mapping.findForward("introduction");    	
    }

}
