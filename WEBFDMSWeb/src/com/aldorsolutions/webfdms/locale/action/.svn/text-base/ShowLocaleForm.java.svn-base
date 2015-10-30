package com.aldorsolutions.webfdms.locale.action;

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

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorsolutions.webfdms.locale.bean.LocaleManagerBean;
import com.aldorsolutions.webfdms.locale.form.LocaleForm;

/**
*
* @author drollins
*/
public class ShowLocaleForm extends Action {
    
    private Logger logger = Logger.getLogger(ShowLocaleForm.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        	
            int localeIDL = 0;
            int companyIDL = 0;
            
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
            
            LocaleForm form = new LocaleForm();
            
            HttpSession session = request.getSession();
            
            HashMap companyMap = (HashMap) session.getAttribute("companies");        
            form.setCompanyMap(companyMap);
            
            if (localeIDL > 0L) {
                try {
                    LocaleManagerBean cmBean = new LocaleManagerBean(companyIDL);
                    LocaleDTO locale = cmBean.getLocale(localeIDL);
                    if (locale != null) {                        
                        form.setLocaleID(locale.getLocaleID());
                        form.setName( locale.getName() );
                        form.setAbbitID( getInt(locale.getAbbitID()) );
                        form.setAtneedLicense(locale.getAtneedLicense());
                        form.setAutoFillArrangeDate(locale.isAutoFillArrangeDate());
                        form.setAutoFillDateOfDeath(locale.isAutoFillDateOfDeath());
                        form.setDaysBeforeDue(getInt(locale.getDaysBeforeDue()));
                        form.setDefaultBankID(getInt(locale.getDefaultBankID()));
                        form.setDefaultCommission(getDouble(locale.getDefaultCommission()));
                        form.setDefaultInflationRate(getDouble(locale.getDefaultInflationRate()));
                        form.setDefaultRefundRate(getDouble(locale.getDefaultRefundRate()));
                        form.setDefaultSaleDateCode(getInt(locale.getDefaultSaleDateCode()));
                        form.setInterfaceType(getInt(locale.getInterfaceType()));
                        form.setLocalizedSpeedData(locale.isLocalizedSpeedData());
                        form.setManagerName(locale.getManagerName());
                        form.setNextContractNo(getInt(locale.getNextContractNo()));
                        form.setNextNonCashNo(getInt(locale.getNextNonCashNo()));
                        form.setNextPreNeedNumber(getInt(locale.getNextPreNeedNumber()));
                        form.setNextReceiptNo(getInt(locale.getNextReceiptNo()));
                        form.setNumberOfUsers(getInt(locale.getNumberOfUsers()));
                        form.setPreneedLicense(locale.getPreneedLicense());
                        form.setCompanyID(locale.getCompanyID());
                        form.setInactiveCode(locale.getInactiveCode());
                        form.setCountry( locale.getCountry() );
                        
                        SimpleDateFormat sdf = new SimpleDateFormat ("MM/dd/yyyy");
                        
                        form.setAccessExpirationDate( sdf.format(locale.getAccessExpirationDate())  );
                        if(locale.getLastFinChgDate() != null)
                        	form.setLastFinChgDate( sdf.format(locale.getLastFinChgDate()) );
                        

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
        
        return mapping.findForward("localeForm");
    }
    
    private String getInt(int value) {
    	return ( Integer.toString(value) );
    }
    
    private String getDouble(double value) {
    	return ( Double.toString(value) );
    }
        
}
