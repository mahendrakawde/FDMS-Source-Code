package com.aldorsolutions.webfdms.locale.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

/**
 * 
 * @author drollins
 *
 */
public class LocaleForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4733453717984189609L;


	private Logger logger = Logger.getLogger(LocaleForm.class.getName());
    

	private long localeID = 0;
	private String country = "";
	private String name = "";
	private String nextContractNo = "";
	private String nextReceiptNo = "";
	private String nextNonCashNo = "";
	private String lastFinChgDate = null;
	private String accessExpirationDate = null;
	private String numberOfUsers = "";
	private String nextPreNeedNumber = "";
	private String interfaceType = "";
	private String managerName = "";
	private String abbitID = "";
	private String defaultBankID = "";
	private String defaultCommission = "";
	private String defaultRefundRate = "";
	private String defaultInflationRate = "";
	private String preneedLicense = "";
	private String atneedLicense = "";
	private String defaultSaleDateCode = "";
	private String daysBeforeDue = "";
	private boolean localizedSpeedData = false;
	private boolean autoFillDateOfDeath = false;
	private boolean autoFillArrangeDate = false;
	private long companyID = 0;
	private String inactiveCode = "";
	private String method = "";
	private HashMap companyMap = new HashMap();

	/**
	 * @return the abbitID
	 */
	public String getAbbitID() {
		return abbitID;
	}

	/**
	 * @param abbitID the abbitID to set
	 */
	public void setAbbitID(String abbitID) {
		this.abbitID = abbitID;
	}

	/**
	 * @return the accessExpirationDate
	 */
	public String getAccessExpirationDate() {
		return accessExpirationDate;
	}

	/**
	 * @param accessExpirationDate the accessExpirationDate to set
	 */
	public void setAccessExpirationDate(String accessExpirationDate) {
		this.accessExpirationDate = accessExpirationDate;
	}

	/**
	 * @return the atneedLicense
	 */
	public String getAtneedLicense() {
		return atneedLicense;
	}

	/**
	 * @param atneedLicense the atneedLicense to set
	 */
	public void setAtneedLicense(String atneedLicense) {
		this.atneedLicense = atneedLicense;
	}

	/**
	 * @return the autoFillArrangeDate
	 */
	public boolean isAutoFillArrangeDate() {
		return autoFillArrangeDate;
	}

	/**
	 * @param autoFillArrangeDate the autoFillArrangeDate to set
	 */
	public void setAutoFillArrangeDate(boolean autoFillArrangeDate) {
		this.autoFillArrangeDate = autoFillArrangeDate;
	}

	/**
	 * @return the autoFillDateOfDeath
	 */
	public boolean isAutoFillDateOfDeath() {
		return autoFillDateOfDeath;
	}

	/**
	 * @param autoFillDateOfDeath the autoFillDateOfDeath to set
	 */
	public void setAutoFillDateOfDeath(boolean autoFillDateOfDeath) {
		this.autoFillDateOfDeath = autoFillDateOfDeath;
	}

	/**
	 * @return the daysBeforeDue
	 */
	public String getDaysBeforeDue() {
		return daysBeforeDue;
	}

	/**
	 * @param daysBeforeDue the daysBeforeDue to set
	 */
	public void setDaysBeforeDue(String daysBeforeDue) {
		this.daysBeforeDue = daysBeforeDue;
	}

	/**
	 * @return the defaultBankID
	 */
	public String getDefaultBankID() {
		return defaultBankID;
	}

	/**
	 * @param defaultBankID the defaultBankID to set
	 */
	public void setDefaultBankID(String defaultBankID) {
		this.defaultBankID = defaultBankID;
	}

	/**
	 * @return the defaultCommission
	 */
	public String getDefaultCommission() {
		return defaultCommission;
	}

	/**
	 * @param defaultCommission the defaultCommission to set
	 */
	public void setDefaultCommission(String defaultCommission) {
		this.defaultCommission = defaultCommission;
	}

	/**
	 * @return the defaultInflationRate
	 */
	public String getDefaultInflationRate() {
		return defaultInflationRate;
	}

	/**
	 * @param defaultInflationRate the defaultInflationRate to set
	 */
	public void setDefaultInflationRate(String defaultInflationRate) {
		this.defaultInflationRate = defaultInflationRate;
	}

	/**
	 * @return the defaultRefundRate
	 */
	public String getDefaultRefundRate() {
		return defaultRefundRate;
	}

	/**
	 * @param defaultRefundRate the defaultRefundRate to set
	 */
	public void setDefaultRefundRate(String defaultRefundRate) {
		this.defaultRefundRate = defaultRefundRate;
	}

	/**
	 * @return the defaultSaleDateCode
	 */
	public String getDefaultSaleDateCode() {
		return defaultSaleDateCode;
	}

	/**
	 * @param defaultSaleDateCode the defaultSaleDateCode to set
	 */
	public void setDefaultSaleDateCode(String defaultSaleDateCode) {
		this.defaultSaleDateCode = defaultSaleDateCode;
	}

	/**
	 * @return the interfaceType
	 */
	public String getInterfaceType() {
		return interfaceType;
	}

	/**
	 * @param interfaceType the interfaceType to set
	 */
	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	/**
	 * @return the lastFinChgDate
	 */
	public String getLastFinChgDate() {
		return lastFinChgDate;
	}

	/**
	 * @param lastFinChgDate the lastFinChgDate to set
	 */
	public void setLastFinChgDate(String lastFinChgDate) {
		this.lastFinChgDate = lastFinChgDate;
	}

	/**
	 * @return the localeID
	 */
	public long getLocaleID() {
		return localeID;
	}

	/**
	 * @param localeID the localeID to set
	 */
	public void setLocaleID(long localeID) {
		this.localeID = localeID;
	}

	/**
	 * @return the localizedSpeedData
	 */
	public boolean getLocalizedSpeedData() {
		return localizedSpeedData;
	}

	/**
	 * @param localizedSpeedData the localizedSpeedData to set
	 */
	public void setLocalizedSpeedData(boolean localizedSpeedData) {
		this.localizedSpeedData = localizedSpeedData;
	}

	/**
	 * @return the managerName
	 */
	public String getManagerName() {
		return managerName;
	}

	/**
	 * @param managerName the managerName to set
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the nextContractNo
	 */
	public String getNextContractNo() {
		return nextContractNo;
	}

	/**
	 * @param nextContractNo the nextContractNo to set
	 */
	public void setNextContractNo(String nextContractNo) {
		this.nextContractNo = nextContractNo;
	}

	/**
	 * @return the nextNonCashNo
	 */
	public String getNextNonCashNo() {
		return nextNonCashNo;
	}

	/**
	 * @param nextNonCashNo the nextNonCashNo to set
	 */
	public void setNextNonCashNo(String nextNonCashNo) {
		this.nextNonCashNo = nextNonCashNo;
	}

	/**
	 * @return the nextPreNeedNumber
	 */
	public String getNextPreNeedNumber() {
		return nextPreNeedNumber;
	}

	/**
	 * @param nextPreNeedNumber the nextPreNeedNumber to set
	 */
	public void setNextPreNeedNumber(String nextPreNeedNumber) {
		this.nextPreNeedNumber = nextPreNeedNumber;
	}

	/**
	 * @return the nextReceiptNo
	 */
	public String getNextReceiptNo() {
		return nextReceiptNo;
	}

	/**
	 * @param nextReceiptNo the nextReceiptNo to set
	 */
	public void setNextReceiptNo(String nextReceiptNo) {
		this.nextReceiptNo = nextReceiptNo;
	}

	/**
	 * @return the numberOfUsers
	 */
	public String getNumberOfUsers() {
		return numberOfUsers;
	}

	/**
	 * @param numberOfUsers the numberOfUsers to set
	 */
	public void setNumberOfUsers(String numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}

	/**
	 * @return the preneedLicense
	 */
	public String getPreneedLicense() {
		return preneedLicense;
	}

	/**
	 * @param preneedLicense the preneedLicense to set
	 */
	public void setPreneedLicense(String preneedLicense) {
		this.preneedLicense = preneedLicense;
	}

	/**
	 * @return the companyID
	 */
	public long getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(long companyID) {
		this.companyID = companyID;
	}

	/**
	 * @return the inactiveCode
	 */
	public String getInactiveCode() {
		return inactiveCode;
	}

	/**
	 * @param inactiveCode the inactiveCode to set
	 */
	public void setInactiveCode(String inactiveCode) {
		this.inactiveCode = inactiveCode;
	}
	
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the companyMap
	 */
	public HashMap getCompanyMap() {
		return companyMap;
	}

	/**
	 * @param companyMap the companyMap to set
	 */
	public void setCompanyMap(HashMap companyMap) {
		this.companyMap = companyMap;
	}
	
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
    	
		localeID = 0;
		name = "";
		nextContractNo = "";
		nextReceiptNo = "";
		nextNonCashNo = "";
		lastFinChgDate = null;
		accessExpirationDate = null;
		numberOfUsers = "";
		nextPreNeedNumber = "";
		interfaceType = "";
		managerName = "";
		abbitID = "";
		defaultBankID = "";
		defaultCommission = "";
		defaultRefundRate = "";
		defaultInflationRate = "";
		preneedLicense = "";
		atneedLicense = "";
		defaultSaleDateCode = "";
		daysBeforeDue = "";
		localizedSpeedData = false;
		autoFillDateOfDeath = false;
		autoFillArrangeDate = false;
		companyID = 0;
		inactiveCode = "";
		method = "";
		country = "";
		companyMap = new HashMap();
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ArrayList formErrors = new ArrayList();
        ActionErrors errors = new ActionErrors();
        
        MessageResourcesFactory messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
        MessageResources resources = messageFactory.createResources("ApplicationResources");
        
        if (name == null || name.trim().equals("")) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.required",
                    resources.getMessage("localeForm.name")));
            formErrors.add("name");
        }
        
        if ( companyID <= 0 ) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.int", resources.getMessage("localeForm.companyID")));
			formErrors.add("companyID");
		}	
        
        if (country == null || country.trim().equals("")) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.required",
                    resources.getMessage("localeForm.country")));
            formErrors.add("country");
        }
        

        if (accessExpirationDate == null || accessExpirationDate.trim().equals("")) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError("error.required",
                    resources.getMessage("localeForm.accessExpirationDate")));
            formErrors.add("accessExpirationDate");
        }
        else {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				
				sdf.parse(accessExpirationDate);
				
			} catch (ParseException nfe) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"error.date", resources.getMessage("localeForm.accessExpirationDate")));
				formErrors.add("accessExpirationDate");
			}
		}	
        
// comment out request by Stephanie.
//        if (lastFinChgDate == null || lastFinChgDate.trim().equals("")) {
//            errors.add(ActionErrors.GLOBAL_ERROR,
//                    new ActionError("error.required",
//                    resources.getMessage("localeForm.lastFinChgDate")));
//            formErrors.add("lastFinChgDate");
//        }
//        else {
//			try {
//				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//				
//				sdf.parse(lastFinChgDate);
//				
//			} catch (ParseException nfe) {
//				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
//						"error.date", resources.getMessage("localeForm.lastFinChgDate")));
//				formErrors.add("lastFinChgDate");
//			}
//		}	
        
      if (lastFinChgDate != null && !lastFinChgDate.trim().isEmpty()) {
     
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			sdf.parse(lastFinChgDate);
			
		} catch (ParseException nfe) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.date", resources.getMessage("localeForm.lastFinChgDate")));
			formErrors.add("lastFinChgDate");
		}
	}	
        
        
        if (formErrors.size() > 0) {
        	request.setAttribute("formErrors", formErrors);
        }
        
        return errors;
    }
    
}
