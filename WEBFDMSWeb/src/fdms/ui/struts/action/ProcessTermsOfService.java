package fdms.ui.struts.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.util.SessionHelpers;

import fdms.ui.struts.form.TermsOfServiceForm;

/**
 * Workfile: ProcessTermsOfService.java
 * Date: Nov 15, 2005 7:24:06 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class ProcessTermsOfService extends Action {

    private Logger logger = Logger.getLogger(ProcessTermsOfService.class.getName());
    private ArrayList formErrors;

    public ActionForward execute(
    		ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
    	
    	logger.debug("Entering perform()");
    	
    	formErrors = new ArrayList();
    	ActionErrors errors = new ActionErrors();
    	TermsOfServiceForm form = (TermsOfServiceForm) actionForm;
    	
    	if (!form.getAgree()) {
    		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.tos"));
    		saveErrors(request, errors);
    		formErrors.add("agree");
    		request.setAttribute("formErrors", formErrors);
    		return new ActionForward(mapping.getInput());
    	} else {
    		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
    		FdmsDb.getInstance().updateUserTOS(sessionUser);
    		logger.debug("UserId : " + sessionUser.getId());
    		sessionUser.setTos("Y");
    	}
    	
    	return mapping.findForward("introduction");
    }

}
