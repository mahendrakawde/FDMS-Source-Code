package com.aldorsolutions.webfdms.sms.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import org.apache.struts.util.LabelValueBean;

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorassist.webfdms.model.SMSStoreProcDTO;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.locale.bean.LocaleManagerBean;
import com.aldorsolutions.webfdms.reporting.dao.SMSStoreProcDAO;
import com.aldorsolutions.webfdms.sms.form.SMSForm;
import com.aldorsolutions.webfdms.util.DAO;

import fdms.ui.struts.form.MovingCaseForm;

/**
*
* @author drollins
*/
public class ShowSMSForm extends Action {
    
    private Logger logger = Logger.getLogger(ShowSMSForm.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        	
            int localeIDL = 0;
            int companyIDL = 0;
            
            SMSForm form = (SMSForm) actionForm;
            if (form == null){
            	form = new SMSForm();
            }
            String localeID = request.getParameter("localeID");
            String companyID = request.getParameter("companyID");
            
            if (localeID != null) {
                try {
                    localeIDL = Integer.parseInt(localeID);
                } catch (NumberFormatException e) {
                    // unable to parse long from String
                }
            }
            
            if (companyID != null) {
                try {
                	companyIDL = Integer.parseInt(companyID);
                } catch (NumberFormatException e) {
                    // unable to parse long from String
                }
            }
            
            HttpSession session = request.getSession();
            
            HashMap companyMap = (HashMap) session.getAttribute("companies");        
            form.setCompanyMap(companyMap);
            
    		form.setLocaleID(0);
    		form.setLocationID(0);
    		form.setSmsID(1);
    	
    		form.setRequestType("N");
    		form.setToDate("");  
    		form.setFromDate("") ;  
    		form.setRunDate("");
    		form.setRepeatType("S");
    		form.setRepeatNumber(0);
    		form.setHH(0);
    		form.setMM(0);
    		form.setCompanyID(0);
    		form.setFirstname("");
    		form.setLastname("");
    		form.setAreaCode("");
    		form.setPhonenumber("");
    		form.setCountryCode("1");
    		
//    	       ArrayList companies = new CompanyManagerBean().getCompanies(true);
//    	        HashMap companySel = new HashMap();
//    	        CompanyDTO companyDto = null;            
//    	        
//    	        for (int i = 0; i < companies.size(); i++) {
//    	        	companyDto = (CompanyDTO) companies.get(i);
//    	        	
//    	        	companySel.put(Integer.toString(companyDto.getCompanyID()), companyDto.getName());
//    	        }
//    	        
//    	        session.setAttribute("companies", companySel );   
    		
//    	   SMSStoreProcDAO smsStoreProcDao = new SMSStoreProcDAO(DAO.DB_FDMSCOMMON);
// 	       ArrayList <SMSStoreProcDTO> sqlStrings = new ArrayList <SMSStoreProcDTO> ();
// 	       sqlStrings = smsStoreProcDao.getSMSStroreProc();
//	       HashMap sqlSel = new HashMap();     
//	        
//	        for (SMSStoreProcDTO sqlDto : sqlStrings) {
//	        	sqlSel.put(Integer.toString(sqlDto.getId()), sqlDto.getName());
//	        }
//	        
//	        session.setAttribute("sqls", sqlSel );  
//	        HashMap smsNameMap = (HashMap) session.getAttribute("sqls");        
//            form.setSmsNameMap(smsNameMap);
    		
            form.setSqls(getSqls(form, session));
            if (localeIDL > 0L) {
                try {
                    LocaleManagerBean cmBean = new LocaleManagerBean(companyIDL);
                    LocaleDTO locale = cmBean.getLocale(localeIDL);
                    if (locale != null) {                        
                        form.setLocaleID(locale.getLocaleID());
                        
                        SimpleDateFormat sdf = new SimpleDateFormat ("MM/dd/yyyy");
                        

                        int totalLocations = 0;
                        ArrayList <LocationDTO> locationList = new ArrayList <LocationDTO> ();
                        
                        try {          
                        	LocationDAO locationDAO = new LocationDAO(companyIDL, 0);
                        	
                        	ArrayList locations = locationDAO.getLocations(companyIDL, false);

                        	if (locations != null && locations.size() > 0) {
                        		
                        		for ( int i = 0; i < locations.size(); i++ ) {
                        			LocationDTO location = (LocationDTO) locations.get(i);
                        			
                        			if ( locale.getLocaleID() == location.getLocaleID() ) {
                        				locationList.add(location);
                        			}
                        			
                        		}
                        		
                        		totalLocations += locations.size();
                        	}
                            	
                        } catch (Exception e) {
                            logger.error("Exception in perform() : ", e);
                        }

                        request.setAttribute("locations", locationList);
                        session.setAttribute("totalLocations", Integer.toString(totalLocations) );
                        
                        
                    }
                } catch (Exception e) {
                    logger.error("Error in perform() : ", e);
                }
            } 
            
        request.setAttribute(mapping.getName(), form);
//        request.setAttribute("smsForm",form);
        return mapping.findForward("smsFormJsp");
    }
    
    private void setForm(SMSForm form) {
		form.setLocaleID(0);
		form.setLocationID(0);
		form.setSmsID(1);
	
		form.setRequestType("N");
		form.setToDate("");  
		form.setFromDate("") ;  
		form.setRunDate("");
		form.setRepeatType("S");
		form.setRepeatNumber(0);
		form.setHH(0);
		form.setMM(0);
		form.setCompanyID(0);
		form.setFirstname("");
		form.setLastname("");
		form.setAreaCode("");
		form.setPhonenumber("");
		form.setCountryCode("1");
		
	}

	private ArrayList <LabelValueBean> getSqls(SMSForm form, HttpSession session) {
		
		ArrayList <LabelValueBean> array = new ArrayList <LabelValueBean> ();
		
		SMSStoreProcDAO smsStoreProcDao = new SMSStoreProcDAO(DAO.DB_FDMSCOMMON);
	    ArrayList <SMSStoreProcDTO> sqlStrings = new ArrayList <SMSStoreProcDTO> ();
	    
	    sqlStrings = smsStoreProcDao.getSMSStroreProc();
	    form.setSqlStrings(sqlStrings);
	    session.setAttribute("sqlStrings", sqlStrings);
	        
	        for (SMSStoreProcDTO sqlDto : sqlStrings) {

	        	array.add(new LabelValueBean(Integer.toString(sqlDto.getId()), sqlDto.getName()));
	        }
		
		return ( array );		
	}   
      
    
}
