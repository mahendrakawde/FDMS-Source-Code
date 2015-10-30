package com.aldorsolutions.webfdms.security.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;

/**
 * 
 * @author drollins
 *
 */
public class SecurityConfigForm extends ActionForm {
    
	private static final long serialVersionUID = 8270046175778092025L;
	
	private long securityConfigID = 0;

	private int companyID = 0;

	private String minPasswordLength = new Integer(SecurityConfigDTO.DEFAULT_MIN_LENGTH).toString();
	
	private String maxPasswordLength = new Integer(SecurityConfigDTO.DEFAULT_MAX_LENGTH).toString();

	private boolean numberRequired = false;

	private boolean symbolRequired = false;

	private boolean mixedCaseRequired = false;

	private boolean adjacentNumberAllowed = true;

	private boolean failedLoginLockout = false;
	
	private boolean passwordContainsUserNameAllowed = false;

	private boolean passwordExpirationEnforced = false;

	private String passwordExpirationInDays = "";
	
	private String passwordExpirationWarningInDays = "";

	private String failedLoginAttemptsAllowed = "";

	private String lockoutDuration = "";

	private boolean passwordNotRepeated = false;
	
	private String vendorCodeLength = "12";
	
	private String commissionLevel = "1";
	
	private String funeralSyncId = "0";
	private int smsNumberOfTime;
	private String smsFirstname; 
	private String smsLastname; 
	private String smsAreaCode; 
	private String smsPhone; 
	private String smsGreeting; 
	private int ttvNumberOfTime; 
	private String ttvFirstname; 
	private String ttvLastname; 
	private String ttvAreaCode; 
	private String ttvPhone; 
	private String ttvGreeting;
	private String urlERegisterBook = "http://www.eregisterbook.mobi/eregisterbook/view.php";
	
	private String obitConnectionUrl;
	private String lifefilesUserName;

	public String getObitConnectionUrl() {
		return obitConnectionUrl;
	}
	public void setObitConnectionUrl(String obitConnectionUrl) {
		this.obitConnectionUrl = obitConnectionUrl;
	}
	
	public String getLifefilesUserName() {
		return lifefilesUserName;
	}
	public void setLifefilesUserName(String lifefilesUserName) {
		this.lifefilesUserName = lifefilesUserName;
	}
	
	public String getUrlERegisterBook() {
		return urlERegisterBook;
	}

	public void setUrlERegisterBook(String urlERegisterBook) {
		this.urlERegisterBook = urlERegisterBook;
	}

	public int getSmsNumberOfTime() {
		return smsNumberOfTime;
	}

	public void setSmsNumberOfTime(int smsNumberOfTime) {
		this.smsNumberOfTime = smsNumberOfTime;
	}

	public String getSmsFirstname() {
		return smsFirstname;
	}

	public void setSmsFirstname(String smsFirstname) {
		this.smsFirstname = smsFirstname;
	}

	public String getSmsLastname() {
		return smsLastname;
	}

	public void setSmsLastname(String smsLastname) {
		this.smsLastname = smsLastname;
	}

	public String getSmsAreaCode() {
		return smsAreaCode;
	}

	public void setSmsAreaCode(String smsAreaCode) {
		this.smsAreaCode = smsAreaCode;
	}

	public String getSmsPhone() {
		return smsPhone;
	}

	public void setSmsPhone(String smsPhone) {
		this.smsPhone = smsPhone;
	}

	public String getSmsGreeting() {
		return smsGreeting;
	}

	public void setSmsGreeting(String smsGreeting) {
		this.smsGreeting = smsGreeting;
	}

	public int getTtvNumberOfTime() {
		return ttvNumberOfTime;
	}

	public void setTtvNumberOfTime(int ttvNumberOfTime) {
		this.ttvNumberOfTime = ttvNumberOfTime;
	}

	public String getTtvFirstname() {
		return ttvFirstname;
	}

	public void setTtvFirstname(String ttvFirstname) {
		this.ttvFirstname = ttvFirstname;
	}

	public String getTtvLastname() {
		return ttvLastname;
	}

	public void setTtvLastname(String ttvLastname) {
		this.ttvLastname = ttvLastname;
	}

	public String getTtvAreaCode() {
		return ttvAreaCode;
	}

	public void setTtvAreaCode(String ttvAreaCode) {
		this.ttvAreaCode = ttvAreaCode;
	}

	public String getTtvPhone() {
		return ttvPhone;
	}

	public void setTtvPhone(String ttvPhone) {
		this.ttvPhone = ttvPhone;
	}

	public String getTtvGreeting() {
		return ttvGreeting;
	}

	public void setTtvGreeting(String ttvGreeting) {
		this.ttvGreeting = ttvGreeting;
	}

	public String getFuneralSyncId() {
		return funeralSyncId;
	}

	public void setFuneralSyncId(String funeralSyncId) {
		this.funeralSyncId = funeralSyncId;
	}

	public String getCommissionLevel() {
		return commissionLevel;
	}

	public void setCommissionLevel(String commissionLevel) {
		this.commissionLevel = commissionLevel;
	}

	public String getVendorCodeLength() {
		return vendorCodeLength;
	}

	public void setVendorCodeLength(String vendorCodeLength) {
		this.vendorCodeLength = vendorCodeLength;
	}

	public boolean isAdjacentNumberAllowed() {
		return adjacentNumberAllowed;
	}

	public void setAdjacentNumberAllowed(boolean adjacentNumberAllowed) {
		this.adjacentNumberAllowed = adjacentNumberAllowed;
	}

	public String getFailedLoginAttemptsAllowed() {
		return failedLoginAttemptsAllowed;
	}

	public void setFailedLoginAttemptsAllowed(String failedLoginAttemptsAllowed) {
		this.failedLoginAttemptsAllowed = failedLoginAttemptsAllowed;
	}

	public boolean isFailedLoginLockout() {
		return failedLoginLockout;
	}

	public void setFailedLoginLockout(boolean failedLoginLockout) {
		this.failedLoginLockout = failedLoginLockout;
	}

	public long getSecurityConfigID() {
		return securityConfigID;
	}

	public void setSecurityConfigID(long securityConfigID) {
		this.securityConfigID = securityConfigID;
	}

	public String getLockoutDuration() {
		return lockoutDuration;
	}

	public void setLockoutDuration(String lockoutDuration) {
		this.lockoutDuration = lockoutDuration;
	}

	public String getMaxPasswordLength() {
		return maxPasswordLength;
	}

	public void setMaxPasswordLength(String maxPasswordLength) {
		this.maxPasswordLength = maxPasswordLength;
	}

	public String getMinPasswordLength() {
		return minPasswordLength;
	}

	public void setMinPasswordLength(String minPasswordLength) {
		this.minPasswordLength = minPasswordLength;
	}

	public boolean isMixedCaseRequired() {
		return mixedCaseRequired;
	}

	public void setMixedCaseRequired(boolean mixedCaseRequired) {
		this.mixedCaseRequired = mixedCaseRequired;
	}

	public boolean isNumberRequired() {
		return numberRequired;
	}

	public void setNumberRequired(boolean numberRequired) {
		this.numberRequired = numberRequired;
	}

	public boolean isPasswordContainsUserNameAllowed() {
		return passwordContainsUserNameAllowed;
	}

	public void setPasswordContainsUserNameAllowed(
			boolean passwordContainsUserNameAllowed) {
		this.passwordContainsUserNameAllowed = passwordContainsUserNameAllowed;
	}

	public boolean isPasswordExpirationEnforced() {
		return passwordExpirationEnforced;
	}

	public void setPasswordExpirationEnforced(boolean passwordExpirationEnforced) {
		this.passwordExpirationEnforced = passwordExpirationEnforced;
	}

	public String getPasswordExpirationInDays() {
		return passwordExpirationInDays;
	}

	public void setPasswordExpirationInDays(String passwordExpirationInDays) {
		this.passwordExpirationInDays = passwordExpirationInDays;
	}

	public boolean isSymbolRequired() {
		return symbolRequired;
	}

	public void setSymbolRequired(boolean symbolRequired) {
		this.symbolRequired = symbolRequired;
	}

	/**
	 * @return Returns the companyID.
	 */
	public int getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID The companyID to set.
	 */
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
    
	/**
	 * @return Returns the passwordExpirationWarningInDays.
	 */
	public String getPasswordExpirationWarningInDays() {
		return passwordExpirationWarningInDays;
	}

	/**
	 * @param passwordExpirationWarningInDays The passwordExpirationWarningInDays to set.
	 */
	public void setPasswordExpirationWarningInDays(
			String passwordExpirationWarningInDays) {
		this.passwordExpirationWarningInDays = passwordExpirationWarningInDays;
	}

	/**
	 * @return Returns the passwordNotRepeated.
	 */
	public boolean isPasswordNotRepeated() {
		return passwordNotRepeated;
	}

	/**
	 * @param passwordNotRepeated The passwordNotRepeated to set.
	 */
	public void setPasswordNotRepeated(boolean passwordNotRepeated) {
		this.passwordNotRepeated = passwordNotRepeated;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
    	securityConfigID = 0;
    	companyID = 0;
    	minPasswordLength = new Integer(SecurityConfigDTO.DEFAULT_MIN_LENGTH).toString();
    	maxPasswordLength = new Integer(SecurityConfigDTO.DEFAULT_MAX_LENGTH).toString();
    	numberRequired = false;
    	symbolRequired = false;
    	mixedCaseRequired = false;
    	adjacentNumberAllowed = false;
    	failedLoginLockout = false;
    	passwordContainsUserNameAllowed = false;
    	passwordExpirationEnforced = false;
    	passwordExpirationInDays = "";
    	passwordExpirationWarningInDays = "";
    	failedLoginAttemptsAllowed = "";
    	lockoutDuration = "";
    	passwordNotRepeated = false;
    	urlERegisterBook="http://www.eregisterbook.mobi/eregisterbook/view.php";
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ArrayList formErrors = new ArrayList();
        ActionErrors errors = new ActionErrors();
        
        MessageResourcesFactory messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
        MessageResources resources = messageFactory.createResources("ApplicationResources");
        
        if (companyID <= 0) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.required",
                    resources.getMessage("securityForm.companyID")));
            formErrors.add("companyID");
        }
        
        if (minPasswordLength == null || minPasswordLength.trim().equals("") ) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.required",
                    resources.getMessage("securityForm.minPasswordLength")));
            formErrors.add("minPasswordLength");
        }
        else {
        	
        	try {
        		Integer.parseInt(minPasswordLength);
        	} 
        	catch ( NumberFormatException nfe ) {
                errors.add(ActionErrors.GLOBAL_ERROR,
                        new ActionError("error.int",
                        resources.getMessage("securityForm.minPasswordLength")));
                formErrors.add("minPasswordLength");
        	}
        }
        
        if (maxPasswordLength == null || maxPasswordLength.trim().equals("") ) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.required",
                    resources.getMessage("securityForm.maxPasswordLength")));
            formErrors.add("maxPasswordLength");
        }
        else {
        	
        	try {
        		Integer.parseInt(maxPasswordLength);
        	} 
        	catch ( NumberFormatException nfe ) {
                errors.add(ActionErrors.GLOBAL_ERROR,
                        new ActionError("error.int",
                        resources.getMessage("securityForm.maxPasswordLength")));
                formErrors.add("maxPasswordLength");
        	}
        }
        
        if (passwordExpirationEnforced) {
        	
        	if (passwordExpirationInDays == null || passwordExpirationInDays.trim().equals("") ) {
                errors.add(ActionErrors.GLOBAL_ERROR,
                        new ActionError("error.required",
                        resources.getMessage("securityForm.passwordExpires")));
                formErrors.add("passwordExpirationInDays");
            }
            else {
            	
            	try {
            		Integer.parseInt(passwordExpirationInDays);
            	} 
            	catch ( NumberFormatException nfe ) {
                    errors.add(ActionErrors.GLOBAL_ERROR,
                            new ActionError("error.int",
                            resources.getMessage("securityForm.passwordExpires")));
                    formErrors.add("passwordExpirationInDays");
            	}
            }	
        	
        	if (passwordExpirationWarningInDays != null && passwordExpirationWarningInDays.trim().equals("") ) {
                
        		try {
            		Integer.parseInt(passwordExpirationWarningInDays);
            	} 
            	catch ( NumberFormatException nfe ) {
                    errors.add(ActionErrors.GLOBAL_ERROR,
                            new ActionError("error.int", 
                            resources.getMessage("securityForm.passwordExpireWarning")));
                    formErrors.add("passwordExpirationWarningInDays");
            	}
            }	
        }

        if (failedLoginLockout) {
        	
        	if (failedLoginAttemptsAllowed == null || failedLoginAttemptsAllowed.trim().equals("") ) {
                errors.add(ActionErrors.GLOBAL_ERROR,
                        new ActionError("error.required",
                        resources.getMessage("securityForm.loginsAllowed")));
                formErrors.add("failedLoginAttemptsAllowed");
            }
            else {
            	
            	try {
            		Integer.parseInt(failedLoginAttemptsAllowed);
            	} 
            	catch ( NumberFormatException nfe ) {
                    errors.add(ActionErrors.GLOBAL_ERROR,
                            new ActionError("error.int",
                            resources.getMessage("securityForm.loginsAllowed")));
                    formErrors.add("failedLoginAttemptsAllowed");
            	}
            }	
        	
        	if (lockoutDuration == null || lockoutDuration.trim().equals("") ) {
                errors.add(ActionErrors.GLOBAL_ERROR,
                        new ActionError("error.required",
                        resources.getMessage("securityForm.lockoutDuration")));
                formErrors.add("lockoutDuration");
            }
            else {
            	
            	try {
            		Integer.parseInt(lockoutDuration);
            	} 
            	catch ( NumberFormatException nfe ) {
                    errors.add(ActionErrors.GLOBAL_ERROR,
                            new ActionError("error.required",
                            resources.getMessage("securityForm.lockoutDuration")));
                    formErrors.add("lockoutDuration");
            	}
            }	
        }
        
        if (formErrors.size() > 0) {
        	request.setAttribute("formErrors", formErrors);
        }
        
        return errors;
    }

}
