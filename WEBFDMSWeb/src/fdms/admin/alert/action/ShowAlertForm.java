/*
 * ShowAlertForm.java
 *
 * Created on February 1, 2005, 8:23 AM
 */

package fdms.admin.alert.action;

/**
 *
 * @author Guadalupe Garcia
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fdms.admin.alert.bean.AlertManagerBean;
import fdms.admin.alert.form.AlertForm;
import fdms.admin.alert.model.AlertDTO;

public class ShowAlertForm extends Action {
    
    private Logger logger = Logger.getLogger(ShowAlertForm.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) 
            throws ServletException, IOException {
        
        String messageType = "M";
        
        if (request.getParameter("messageType") != null && !request.getParameter("messageType").equals(""))
            messageType = request.getParameter("messageType");
        
        try {
            AlertForm form = new AlertForm();
            form.setMessageType(messageType);
            
            AlertDTO alert = new AlertManagerBean().getAlert(messageType);
            
            form.setMessage(alert.getMessage());
            
            if (alert.getViewable() != null && !alert.getViewable().equals(""))
                form.setViewable(alert.getViewable());
                                
            form.setSeconds(Integer.toString(alert.getSeconds()));
            
            request.setAttribute(mapping.getName(), form);
            
        } catch (Exception e) {
            logger.error("Exception in perform() : ", e);
        }
        
        return mapping.findForward("alertForm");
    }
    
}
