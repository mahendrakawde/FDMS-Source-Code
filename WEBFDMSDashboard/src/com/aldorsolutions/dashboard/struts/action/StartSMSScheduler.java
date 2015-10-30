/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.aldorsolutions.dashboard.struts.action;

import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.aldorsolutions.dashboard.struts.form.SendSMSForm;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.reporting.SMSSchedulerTask;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

/**
 * MyEclipse Struts Creation date: 04-30-2007
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/myAccountEdit" name="myAccountEditForm"
 *                input="/user/myAccount.jsp" scope="request"
 *                validate="true"
 */
public class StartSMSScheduler extends Action {
	Logger logger = Logger.getLogger(StartSMSScheduler.class);
	
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SendSMSForm myForm = new SendSMSForm();
		ActionMessages errors = new ActionMessages();
		

        HttpSession session =  request.getSession();
        DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
        
        Timer smsTimer = new Timer("SMSThread", true);
		smsTimer.schedule(SMSSchedulerTask.getInstance(), 10);
		
        
		return (mapping.findForward("success"));
	}


}