/*
 * ShowMain.java
 *
 * Created on February 1, 2005, 11:57 AM
 */

package com.aldorsolutions.webfdms.locations.action;

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

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;

public class ShowLocations extends Action {
    
    private Logger logger = Logger.getLogger(ShowLocations.class.getName());
    
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
        
        int totalLocations = 0;
        ArrayList locationList = new ArrayList();
        
        try {                  
        	
            for (int i = 0; i < companies.size(); i++) {
            	companyDto = (CompanyDTO) companies.get(i);
            	int companyID = companyDto.getCompanyID();
            	
            	LocationDAO locationDAO = new LocationDAO(companyID, 0);
            	
            	ArrayList locations = locationDAO.getLocations(true);
                
                if (locations != null && locations.size() > 0) {
                	locationList.addAll(locations);
                	totalLocations += locations.size();
                }
            	
            }
            
            
        } catch (Exception e) {
            logger.error("Exception in perform() : ", e);
        }

        request.setAttribute("locations", locationList);
        session.setAttribute("totalLocations", Integer.toString(totalLocations) );
        
        return mapping.findForward("locations");
    }
    
}
