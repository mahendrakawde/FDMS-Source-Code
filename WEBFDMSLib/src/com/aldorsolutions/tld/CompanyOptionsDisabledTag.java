package com.aldorsolutions.tld;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.SessionHelpers;

public class CompanyOptionsDisabledTag extends BodyTagSupport {
	
	private static final String RENDER_FAIL = "fail";
	private static final String RENDER_SUCCESS = "success";
	
	private static final long serialVersionUID = 1L;
	
	static final Logger logger = Logger.getLogger(CompanyOptionsDisabledTag.class.getName());
	
	private String optionValue;
	private String render = RENDER_SUCCESS; // default to rendering on success
	
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public void setOptionValue(int optionValue) {
		this.optionValue = String.valueOf(optionValue);
	}

	public void setRender(String render) {
		if (RENDER_SUCCESS.equals(render) || RENDER_FAIL.equals(render)) {
			this.render = render;
		}
	}
	
	@Override
	public int doStartTag() {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		DbUserSession sessionUser = SessionHelpers.getUserSession(request);
		CompanyOptionsManager coMgr = new CompanyOptionsManager();
		
		// Now check to see if this options is turned on for this customer
		int optionResult = coMgr.getCompanyOptionValueForCompany(sessionUser.getCompanyID(), Integer.parseInt(this.optionValue));
		if (optionResult == 1) {
			return fail();
		} else {
			return success();
		}
	}

	private int fail() {
		if (RENDER_FAIL.equals(render)) {
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	private int success() {
		if (RENDER_SUCCESS.equals(render))
			return EVAL_BODY_INCLUDE;
		else
			return SKIP_BODY;
	}

}
