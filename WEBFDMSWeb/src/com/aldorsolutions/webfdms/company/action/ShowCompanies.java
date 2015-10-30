/*
 * ShowMain.java
 *
 * Created on February 1, 2005, 11:57 AM
 */

package com.aldorsolutions.webfdms.company.action;

/**
 * 
 * @author Guadalupe Garcia
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.common.DTOComparator;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;

public class ShowCompanies extends Action {

	private Logger logger = Logger.getLogger(ShowCompanies.class.getName());

	String method = null;

	String type = null;
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String totalCompanies = "0";
		ArrayList companies = null;
		try {

			companies = new CompanyManagerBean().getCompanies();

		} catch (Exception e) {
			logger.error("Exception in perform() : ", e);
		}

		CompanyDTO[] companiesArray = (CompanyDTO[])companies.toArray(new CompanyDTO[companies.size()]);
		method = request.getParameter("method");
		if (method == null) {
			method = "name";
		}
		type = request.getParameter("type");
		if (type == null) {
			type = Constants.ASCE_SORT_ORDER;
		}
		DTOComparator comparator = new DTOComparator(method, type);
		Arrays.sort(companiesArray, comparator);

		ArrayList<CompanyDTO> companyList = new ArrayList<CompanyDTO>();
		for (int i = 0; i < companiesArray.length; i++) {
			companyList.add(companiesArray[i]);
		}
		if (companyList != null && companyList.size() > 0) {
			request.setAttribute("companies", companyList);
			totalCompanies = Integer.toString(companyList.size());
		}
		request.setAttribute("totalCompanies", totalCompanies);

		if (Constants.ASCE_SORT_ORDER.equals(type)) {
			type = Constants.DESC_SORT_ORDER;
		} else {
			type = Constants.ASCE_SORT_ORDER;
		}
		request.setAttribute("type", type);
		request.setAttribute("method", method);

		return mapping.findForward("companies");
	}

}
