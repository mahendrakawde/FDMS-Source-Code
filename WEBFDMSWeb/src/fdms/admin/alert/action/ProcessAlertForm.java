/*
 * ProcessAlertForm.java
 *
 * Created on February 1, 2005, 8:31 AM
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

public class ProcessAlertForm extends Action {
    
    private Logger logger = Logger.getLogger(ProcessAlertForm.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) 
            throws ServletException, IOException {
        
        AlertForm form = (AlertForm) actionForm;
        
        int seconds = 0;
        
        try {
            seconds = Integer.parseInt(form.getSeconds());
        } catch (NumberFormatException e) {
            // unable to parse int from String
        }
        
        AlertDTO alert = new AlertDTO(
                form.getMessageType(),
                form.getMessage(),
                form.getViewable(),
                seconds);
        
        try {
            new AlertManagerBean().updateAlert(alert);
            
            request.setAttribute("message", "Alert successfully updated.");
        } catch (Exception e) {
            logger.error("Exception in perform() : ", e);
        }
        
        return mapping.findForward("alertForm");
    }
    
}
