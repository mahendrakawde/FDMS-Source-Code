/*
 * ProcessUserForm.java
 *
 * Created on February 5, 2005, 2:20 PM
 */

package com.aldorsolutions.webfdms.security.action;

import java.io.IOException;

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

import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.form.SecurityConfigForm;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;

/**
 * 
 * @author drollins
 *
 */
public class ProcessSecurityForm extends Action {
    
    private Logger logger = Logger.getLogger(ProcessSecurityForm.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String target = "companyForm";
        SecurityConfigForm form = (SecurityConfigForm) actionForm;
        String submitType = request.getParameter(mapping.getParameter());
        int companyIDL = 0;
        long securityConfigIDL = 0;
        ActionErrors errors = new ActionErrors();
        SecurityConfigDTO sercurity = null;
        companyIDL = form.getCompanyID();
        securityConfigIDL = form.getSecurityConfigID();
        
        try {
        	SecurityManagerBean smBean = new SecurityManagerBean();
            
            if (submitType.equals("Submit")) {
            	sercurity = convertForm(companyIDL, form);                
                boolean success = false;
                                
                if (securityConfigIDL > 0L) { // updating security
                    success = smBean.updateSecurityConfig(sercurity, 0);
                    if (!success)  {
                        errors.add(ActionErrors.GLOBAL_ERROR,
                                new ActionError("error.GeneralException", "Failed to Save Security Settings"));
                    }
                } else { // adding security
                    success = smBean.addSecurityConfig(sercurity, 0);
                    if (!success) {
                        errors.add(ActionErrors.GLOBAL_ERROR,
                        		new ActionError("error.GeneralException", "Failed to Add Security Settings"));
                    }
                }
                
                //now we are update the companyid and companynumber in locales and locations table.
                success = smBean.updateCompanyIdLocales(sercurity, 0);
                if (!success) {
                    errors.add(ActionErrors.GLOBAL_ERROR,
                    		new ActionError("error.GeneralException", "Failed to Update companyId on Locales"));
                }
                
                success = smBean.updateCompanyNumberLocations(sercurity, 0);
                if (!success) {
                    errors.add(ActionErrors.GLOBAL_ERROR,
                    		new ActionError("error.GeneralException", "Failed to Update companynumber on Locations"));
                }
            } 

        } catch (Exception e) {
            logger.error("Exception in perform() : ", e);   
            errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.GeneralException",e.getMessage()));
        }
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            target = "securityForm";
        }
        
        return mapping.findForward(target);
    }
    
    private SecurityConfigDTO convertForm(int companyID, SecurityConfigForm form) {
    	SecurityConfigDTO security = new SecurityConfigDTO();
        security.setCompanyID(companyID);
        security.setSecurityConfigID(form.getSecurityConfigID());
        security.setMinPasswordLength(getInt(form.getMinPasswordLength(), 0));
        security.setMaxPasswordLength(getInt(form.getMaxPasswordLength(), 0));
        security.setNumberRequired(form.isNumberRequired());
        security.setSymbolRequired(form.isSymbolRequired());
        security.setMixedCaseRequired(form.isMixedCaseRequired());
        security.setAdjacentNumberAllowed(form.isAdjacentNumberAllowed());
        security.setFailedLoginLockout(form.isFailedLoginLockout());
        security.setFailedLoginAttemptsAllowed(getInt(form.getFailedLoginAttemptsAllowed(), 0));
        security.setLockoutDuration(getInt(form.getLockoutDuration(), 0));
        security.setPasswordExpirationInDays(getInt(form.getPasswordExpirationInDays(), 0));
        security.setPasswordExpirationWarningInDays(getInt(form.getPasswordExpirationWarningInDays(), 0));
        security.setPasswordContainsUserNameAllowed(form.isPasswordContainsUserNameAllowed());
        security.setPasswordExpirationEnforced(form.isPasswordExpirationEnforced());
        security.setPasswordNotRepeated(form.isPasswordNotRepeated());
        security.setVendorCodeLength(Integer.parseInt(form.getVendorCodeLength()));
        security.setCommissionLevel(Integer.parseInt(form.getCommissionLevel()));
        security.setFuneralSyncId(Integer.parseInt(form.getFuneralSyncId()));
        security.setSmsNumberOfTime(form.getSmsNumberOfTime());
        security.setSmsFirstname(form.getSmsFirstname());
        security.setSmsLastname(form.getSmsLastname());
        security.setSmsAreaCode(form.getSmsAreaCode());
        security.setSmsPhone(form.getSmsPhone());
        security.setSmsGreeting(form.getSmsGreeting());
        security.setTtvNumberOfTime(form.getTtvNumberOfTime());
        security.setTtvFirstname(form.getTtvFirstname());
        security.setTtvLastname(form.getTtvLastname());
        security.setTtvAreaCode(form.getTtvAreaCode());
        security.setTtvPhone(form.getTtvPhone());
        security.setTtvGreeting(form.getTtvGreeting());
        security.setERegisterBookURL(form.getUrlERegisterBook());
        security.setObitConnectURL(form.getObitConnectionUrl());
        security.setLifefilesUserName(form.getLifefilesUserName());
        
        return security;
    }
    
    private int getInt(String value, int defaultVal) {
        
    	try {
    		return (  Integer.parseInt(value) );	
        } 
        catch (NumberFormatException nfe ) {
        	return (  defaultVal );
        }
    	
    }
    
}
