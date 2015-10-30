/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.aldorsolutions.dashboard.struts.action;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.aldorsolutions.dashboard.struts.form.LostPasswordForm;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.MailUtil;

/** 
 * MyEclipse Struts
 * Creation date: 06-08-2007
 * 
 * XDoclet definition:
 * @struts.action path="/lostPassword" name="lostPasswordForm" input="/lostPassword.jsp" scope="request" validate="true"
 */
public class LostPasswordAction extends Action {
	/*
	 * Generated Methods
	 */

	private Logger logger = Logger.getLogger(LostPasswordAction.class.getName());
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		LostPasswordForm lostPasswordForm = (LostPasswordForm) form;
		
		return ( lostPassword(mapping, lostPasswordForm, request, response) );
		
	}
	
	/**
	 * 
	 * @param mapping
	 * @param pForm
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward lostPassword ( ActionMapping mapping,
			LostPasswordForm pForm, HttpServletRequest request,
            HttpServletResponse response ) {
    	

        DbUserSession user = null;
        
        // Validate the request parameters specified by the user
        ActionMessages errors = new ActionMessages();
        String username = pForm.getUsername();
        
        user = DbUser.findUserByUserName(username);
        String email = null;
        
        if ( user == null ) {
        	errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.username.invalid"));
        	saveErrors(request, errors);
        	return (new ActionForward(mapping.getInput()));
    	} else if (user != null) {
        	email = user.getEmailAddr();
        	
        	if ( email == null ) {
        		errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("mail.email.invalid"));
        	} else {
            	try {
            		MailUtil.sendEmail(user, user.getConfigID(), email, "Password Recovery", "I can't send you your password remember");
            	} catch ( MessagingException me ) {
            		errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", me.getMessage()) );
            	}
        	}
        	
        } 
        
        user = null;
        
        // Report any errors we have discovered back to the original form
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return (new ActionForward(mapping.getInput()));
        }
        else {
        	errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("mail.message.sent", email) );
        	saveErrors(request, errors);
        	return ( mapping.findForward("dashboardLogin") );
        }
        
        
    }
	
}