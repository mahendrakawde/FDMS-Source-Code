package fdms.ui.struts.action;

// Cloned and modified by JO - QPRIME - SOW: F030601A Cemetery Management System
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.common.Constants;

public class ShowIntroCemetery extends Action {
	
    private Logger logger = Logger.getLogger(ShowIntroduction.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
    	
    	
    	
    	String ownList = request.getParameter("listbyown");
    	JOptionPane.showMessageDialog(null, "ownList " + ownList);    	
    	logger.error("Session listbyown = " + (String) request.getSession().getAttribute("SHOW_CEM_OWN_LIST"));
    	try {
    		if ((!"Y".equals(ownList)) && (!"N".equals(ownList))) ownList = "N";
    			
    			logger.debug("Show Owner List = " + ownList);
    			
    			request.getSession().setAttribute(Constants.SHOW_CEM_OWN_LIST, ownList);
    			request.getSession().setAttribute(Constants.USER_START_INDEX, new Integer(1));  
    			
    		} catch (Exception e) {
    			logger.error("Exception in perform() : ", e);
    		}
    	
    	
    	return mapping.findForward("introcemetery");    	
    }

}
