/*
 * ShowMain.java
 *
 * Created on February 1, 2005, 11:57 AM
 */

package com.aldorsolutions.webfdms.locale.action;

/**
 *
 * @author Guadalupe Garcia
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.locale.bean.LocaleManagerBean;

public class ShowLocales extends Action {
    
    private Logger logger = Logger.getLogger(ShowLocales.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
                        
        HttpSession session = request.getSession();  
        
        ArrayList companies = new CompanyManagerBean().getCompanies(true);
        HashMap companySel = new HashMap();
        CompanyDTO companyDto = null;            
        
        for (int i = 0; i < companies.size(); i++) {
        	companyDto = (CompanyDTO) companies.get(i);
        	
        	companySel.put(Integer.toString(companyDto.getCompanyID()), companyDto.getName());
        }
        
        session.setAttribute("companies", companySel );        
        
        int totalLocales = 0;
        ArrayList localeList = new ArrayList();
        
        try {                  
        	
            for (int i = 0; i < companies.size(); i++) {
            	companyDto = (CompanyDTO) companies.get(i);
            	int companyID = companyDto.getCompanyID();
            	
            	ArrayList locales = new LocaleManagerBean(companyID).getLocales();
                
                if (locales != null && locales.size() > 0) {
                    localeList.addAll(locales);
                    totalLocales += locales.size();
                }
            	
            }
            
            
        } catch (Exception e) {
            logger.error("Exception in perform() : ", e);
        }

        request.setAttribute("locales", localeList);
        session.setAttribute("totalLocales", Integer.toString(totalLocales) );
        
        return mapping.findForward("locales");
    }
    
}
