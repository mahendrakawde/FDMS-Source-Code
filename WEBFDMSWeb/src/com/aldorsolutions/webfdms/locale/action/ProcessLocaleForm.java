/*
 * ProcessLocaleForm.java
 *
 * Created on February 5, 2005, 2:20 PM
 */

package com.aldorsolutions.webfdms.locale.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.locale.bean.LocaleManagerBean;
import com.aldorsolutions.webfdms.locale.form.LocaleForm;

/**
 * 
 * @author drollins
 *
 */
public class ProcessLocaleForm extends Action {
    
    private Logger logger = Logger.getLogger(ProcessLocaleForm.class.getName());
    

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String target = "locales";
//    	String target = "companyForm";
        boolean newLocale = false;
        LocaleForm form = (LocaleForm) actionForm;
        String message = "";
        ActionErrors errors = new ActionErrors();
        MessageResources resources = this.getResources(request);
        
        String submitType = request.getParameter(mapping.getParameter());
        long localeID = form.getLocaleID();
        long companyID = form.getCompanyID();

        LocaleDTO locale = null;
        
        if ( companyID <= 0 ) {
        	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("Invalid Company ID"));
        } else {
            request.setAttribute("companyID", Long.toString(companyID) );
            request.setAttribute("localeID", Long.toString(localeID) );
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            target = "localeForm";
            return mapping.findForward(target);
        }
        
        LocaleManagerBean lmBean = new LocaleManagerBean(companyID);
        
        try {
            if (submitType.equals("Submit")) {
            	locale = getLocale(lmBean, localeID, form);                
                boolean success = false;
                                
                if (localeID > 0L) { // updating company
                    success = lmBean.updateLocale(locale);
                    if (success)  {
                        message = "Customer Locale Updated";                        
                    } else {
                        errors.add(ActionErrors.GLOBAL_ERROR,
                                new ActionError("error.companyForm.updated"));
                    }
                } else { // adding company
                    success = lmBean.addLocale(locale);
                    if (success) {
                        message = "Customer Locale Added";  
                        newLocale = true;
                    } else {
                        errors.add(ActionErrors.GLOBAL_ERROR,
                                new ActionError("error.companyForm.added"));
                    }
                }
                
            } else if (submitType.equals("Delete") && localeID > 0L) {
            	lmBean.deleteLocale(localeID);
                message = "Customer Locale Deleted";
            } 
            
        } catch (Exception e) {
            logger.error("Exception in perform() : ", e);            
        }
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            target = "localeForm";
        }
        
        return mapping.findForward(target);
    }
    
    private LocaleDTO getLocale(LocaleManagerBean lmBean, long localeID, LocaleForm form) {
        LocaleDTO locale = new LocaleDTO();
        
        if ( localeID > 0 ) {
        	try {
        		locale = lmBean.getLocale(localeID);
            } catch ( Exception pe ) {
            	logger.debug(pe.getMessage(), pe);
            }
        	
        }
        
        
        locale.setLocaleID((int)localeID);
        locale.setName(form.getName());
        locale.setAbbitID( getInt(form.getAbbitID()) );
        locale.setAtneedLicense(form.getAtneedLicense());
        locale.setAutoFillArrangeDate(form.isAutoFillArrangeDate());
        locale.setAutoFillDateOfDeath(form.isAutoFillDateOfDeath());
        locale.setDaysBeforeDue(getInt(form.getDaysBeforeDue()));
        locale.setDefaultBankID(getInt(form.getDefaultBankID()));
        locale.setDefaultCommission(getDouble(form.getDefaultCommission()));
        locale.setDefaultInflationRate(getDouble(form.getDefaultInflationRate()));
        locale.setDefaultRefundRate(getDouble(form.getDefaultRefundRate()));
        locale.setDefaultSaleDateCode(getInt(form.getDefaultSaleDateCode()));
        locale.setInterfaceType(getInt(form.getInterfaceType()));
        locale.setLocalizedSpeedData(form.getLocalizedSpeedData());
        locale.setManagerName(form.getManagerName());
        locale.setNextContractNo(getInt(form.getNextContractNo()));
        locale.setNextNonCashNo(getInt(form.getNextNonCashNo()));
        locale.setNextPreNeedNumber(getInt(form.getNextPreNeedNumber()));
        locale.setNextReceiptNo(getInt(form.getNextReceiptNo()));
        locale.setNumberOfUsers(getInt(form.getNumberOfUsers()));
        locale.setPreneedLicense(form.getPreneedLicense());
        locale.setCompanyID((int)form.getCompanyID());
        locale.setInactiveCode(form.getInactiveCode());
        locale.setCountry(form.getCountry());
        
        SimpleDateFormat sdf = new SimpleDateFormat ("MM/dd/yyyy");
        
        try {
        	java.util.Date utilDate = sdf.parse(form.getAccessExpirationDate());
        	
        	java.sql.Date sqlDate = new java.sql.Date (utilDate.getTime());
        	
        	locale.setAccessExpirationDate( sqlDate );
        } catch ( ParseException pe ) {
        	
        }
        
        try {
        	if (form.getLastFinChgDate() != null && !form.getLastFinChgDate().trim().isEmpty()) {
	        	java.util.Date utilDate = (java.util.Date) sdf.parse(form.getLastFinChgDate());
	        	java.sql.Date sqlDate = new java.sql.Date (utilDate.getTime());
	        	
	        	locale.setLastFinChgDate( sqlDate );
        	}else {
        		locale.setLastFinChgDate( null );
        	}
        } catch ( ParseException pe ) {
        	
        }
        
        
        return locale;
    }
    
    private int getInt(String value) {
    	try {
    		return ( Integer.parseInt(value) );
    	} catch ( NumberFormatException nfe ) {
    		return ( 0 );
    	}
    }
    
    private double getDouble(String value) {
    	try {
    		return ( Double.parseDouble(value) );
    	} catch ( NumberFormatException nfe ) {
    		return ( 0.0 );
    	}
    }
    
}
