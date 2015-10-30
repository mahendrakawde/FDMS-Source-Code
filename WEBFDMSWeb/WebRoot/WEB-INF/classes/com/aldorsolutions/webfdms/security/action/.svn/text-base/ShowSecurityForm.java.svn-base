package com.aldorsolutions.webfdms.security.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.form.SecurityConfigForm;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;

/**
*
* @author drollins
*/
public class ShowSecurityForm extends Action {
    
    private Logger logger = Logger.getLogger(ShowSecurityForm.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
            int companyIDL = 0;
            
            String companyID = (String) request.getAttribute("companyID");
            
            if (companyID != null) {
                try {
                    companyIDL = Integer.parseInt(companyID);
                } catch (NumberFormatException e) {
                    // unable to parse long from String
                }
            }
            
            SecurityConfigForm form = new SecurityConfigForm();
            
            if (companyIDL > 0L) {
                try {
                    SecurityManagerBean smBean = new SecurityManagerBean();
                    SecurityConfigDTO security = smBean.getSecurityConfig(companyIDL);
                    form.setCompanyID( companyIDL );
                    
                    if (security != null) {
                        form.setSecurityConfigID(security.getSecurityConfigID());
                        form.setMinPasswordLength(Integer.toString(security.getMinPasswordLength()));
                        form.setMaxPasswordLength(Integer.toString(security.getMaxPasswordLength()));
                        form.setNumberRequired(security.isNumberRequired());
                        form.setSymbolRequired(security.isSymbolRequired());
                        form.setMixedCaseRequired(security.isMixedCaseRequired());
                        form.setAdjacentNumberAllowed(security.isAdjacentNumberAllowed());
                        form.setFailedLoginLockout(security.isFailedLoginLockout());
                        form.setPasswordContainsUserNameAllowed(security.isPasswordContainsUserNameAllowed());
                        form.setPasswordExpirationEnforced(security.isPasswordExpirationEnforced());
                        form.setPasswordExpirationInDays(Integer.toString(security.getPasswordExpirationInDays()));
                        form.setPasswordExpirationWarningInDays(Integer.toString(security.getPasswordExpirationWarningInDays()));
                        form.setFailedLoginAttemptsAllowed(Integer.toString(security.getFailedLoginAttemptsAllowed()));
                        form.setLockoutDuration(Integer.toString(security.getLockoutDuration()));
                        form.setPasswordNotRepeated(security.isPasswordNotRepeated());
                        form.setVendorCodeLength(String.valueOf(security.getVendorCodeLength()));
                        form.setCommissionLevel(String.valueOf(security.getCommissionLevel()));
                        form.setFuneralSyncId(String.valueOf(security.getFuneralSyncId()));
                        form.setSmsNumberOfTime(security.getSmsNumberOfTime());
                        form.setSmsFirstname(security.getSmsFirstname());
                        form.setSmsLastname(security.getSmsLastname());
                        form.setSmsAreaCode(security.getSmsAreaCode());
                        form.setSmsPhone(security.getSmsPhone());
                        form.setSmsGreeting(security.getSmsGreeting());
                        form.setTtvNumberOfTime(security.getTtvNumberOfTime());
                        form.setTtvFirstname(security.getTtvFirstname());
                        form.setTtvLastname(security.getTtvLastname());
                        form.setTtvAreaCode(security.getTtvAreaCode());
                        form.setTtvPhone(security.getTtvPhone());
                        form.setTtvGreeting(security.getTtvGreeting());
                        form.setUrlERegisterBook(security.getERegisterBookURL());
                        form.setObitConnectionUrl(security.getObitConnectURL());
                        form.setLifefilesUserName(security.getLifefilesUserName());
                    }
                    
                } catch (Exception e) {
                    logger.error("Error in perform() : ", e);
                }
            } 
            
            request.setAttribute(mapping.getName(), form);
            
        return mapping.findForward("securityForm");
    }
    
}
