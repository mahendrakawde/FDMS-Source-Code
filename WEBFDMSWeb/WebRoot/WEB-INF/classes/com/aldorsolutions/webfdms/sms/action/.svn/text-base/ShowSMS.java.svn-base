/*
 * ShowMain.java
 *
 * Created on February 1, 2005, 11:57 AM
 */

package com.aldorsolutions.webfdms.sms.action;

/**
 *
 * @author Guadalupe Garcia
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorassist.webfdms.model.SMSSchedulingDTO;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.locale.bean.LocaleManagerBean;
import com.aldorsolutions.webfdms.reporting.SMSGenerator;
import com.aldorsolutions.webfdms.reporting.dao.SMSSchedulingDAO;
import com.aldorsolutions.webfdms.util.DAO;

public class ShowSMS extends Action {
    
    private Logger logger = Logger.getLogger(ShowSMS.class.getName());
    
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
        
        int totalSMSs = 0;
        ArrayList smsList = new ArrayList();
        SMSSchedulingDAO smsScheDAo = new SMSSchedulingDAO(DAO.DB_FDMSCOMMON);
        try {                  
        	
        	ArrayList <SMSSchedulingDTO> smsScheList = null;
			//if(intial){
				GregorianCalendar cal = new GregorianCalendar();
				smsScheList = smsScheDAo.getSMSSchedulingCommonQ();

			
			if (smsScheList.size() > 0) {
				for (SMSSchedulingDTO smsScheduling: smsScheList) {
					
					if (smsScheduling.getStatus().compareToIgnoreCase("Q") == 0 || smsScheduling.getStatus().compareToIgnoreCase("R") == 0) {
						smsList.add(smsScheduling);
					}
				}
			}
            
            
        } catch (Exception e) {
            logger.error("Exception in perform() : ", e);
        }

        request.setAttribute("smss", smsList);
        session.setAttribute("totalSMSs", Integer.toString(totalSMSs) );
        
        return mapping.findForward("smsList");
    }
    
}
