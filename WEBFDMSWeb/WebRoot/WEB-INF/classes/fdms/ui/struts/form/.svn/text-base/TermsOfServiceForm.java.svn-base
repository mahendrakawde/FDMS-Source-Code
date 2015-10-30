package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Workfile: TermsOfServiceForm.java Date: Nov 15, 2005 4:24:23 PM Author:
 * Guadalupe Garcia
 * 
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class TermsOfServiceForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5212277071000479324L;

	private Logger logger = Logger.getLogger(TermsOfServiceForm.class.getName());

	private boolean agree = false;

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	public boolean getAgree() {
		return agree;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		logger.debug("Resetting TermsOfServiceForm");
		// this.agree = false;
	}
}
