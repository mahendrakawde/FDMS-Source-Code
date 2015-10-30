package com.aldorsolutions.webfdms.locations.form;

import java.util.ArrayList;

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
public class LocationForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4733453717984189609L;

	private Logger logger = Logger.getLogger(LocationForm.class.getName());

	private int locationID = 0;

	private String name;

	private String companyNumber;

	private String stdServiceCharge;

	private String packageVersion;

	private String inactiveCode;

	private String priceListVersion;

	private String localeNumber;

	private String cashAcct;

	private String arAcct;

	private String financeChargeAcct;

	private String apAcct;

	private String discountAcct;

	private String nextCheckNumber;

	private String discountHandlingCode;

	private String cashBalance;

	private String addr1;

	private String addr2;

	private String addr3;

	private String city;

	private String state;

	private String zip;

	private String county;

	private String phone;

	private String phoneAlternate;

	private String licenseNumber;

	private String preferenceGenericVitals;

	private String managerName;

	private String undepositedFundsAcct;

	private boolean useUndepositedFundsAcct;

	private String method;
	private String onetimeVendorCode;
	private String funeralSyncLocationId;
	
//added by haranesh patel
	private String[] selectedLocationOptions = null;
	
	/**
	 * @return the selectedLocationOptions
	 */
	public String[] getSelectedLocationOptions() {
		return selectedLocationOptions;
	}

	/**
	 * @param selectedLocationOptions the selectedLocationOptions to set
	 */
	public void setSelectedLocationOptions(String[] selectedLocationOptions) {
		this.selectedLocationOptions = selectedLocationOptions;
	}

	public String getOnetimeVendorCode() {
		return onetimeVendorCode;
	}

	public void setOnetimeVendorCode(String onetimeVendorCode) {
		this.onetimeVendorCode = onetimeVendorCode;
	}

	public String getFuneralSyncLocationId() {
		return funeralSyncLocationId;
	}

	public void setFuneralSyncLocationId(String funeralSyncLocationId) {
		this.funeralSyncLocationId = funeralSyncLocationId;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getAddr3() {
		return addr3;
	}

	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}

	public String getApAcct() {
		return apAcct;
	}

	public void setApAcct(String apAcct) {
		this.apAcct = apAcct;
	}

	public String getArAcct() {
		return arAcct;
	}

	public void setArAcct(String arAcct) {
		this.arAcct = arAcct;
	}

	public String getCashAcct() {
		return cashAcct;
	}

	public void setCashAcct(String cashAcct) {
		this.cashAcct = cashAcct;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDiscountAcct() {
		return discountAcct;
	}

	public void setDiscountAcct(String discountAcct) {
		this.discountAcct = discountAcct;
	}

	public String getDiscountHandlingCode() {
		return discountHandlingCode;
	}

	public void setDiscountHandlingCode(String discountHandlingCode) {
		this.discountHandlingCode = discountHandlingCode;
	}

	public String getFinanceChargeAcct() {
		return financeChargeAcct;
	}

	public void setFinanceChargeAcct(String financeChargeAcct) {
		this.financeChargeAcct = financeChargeAcct;
	}

	public String getInactiveCode() {
		return inactiveCode;
	}

	public void setInactiveCode(String inactiveCode) {
		this.inactiveCode = inactiveCode;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageVersion() {
		return packageVersion;
	}

	public void setPackageVersion(String packageVersion) {
		this.packageVersion = packageVersion;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneAlternate() {
		return phoneAlternate;
	}

	public void setPhoneAlternate(String phoneAlternate) {
		this.phoneAlternate = phoneAlternate;
	}

	public String getPreferenceGenericVitals() {
		return preferenceGenericVitals;
	}

	public void setPreferenceGenericVitals(String preferenceGenericVitals) {
		this.preferenceGenericVitals = preferenceGenericVitals;
	}

	public String getPriceListVersion() {
		return priceListVersion;
	}

	public void setPriceListVersion(String priceListVersion) {
		this.priceListVersion = priceListVersion;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUndepositedFundsAcct() {
		return undepositedFundsAcct;
	}

	public void setUndepositedFundsAcct(String undepositedFundsAcct) {
		this.undepositedFundsAcct = undepositedFundsAcct;
	}

	public boolean isUseUndepositedFundsAcct() {
		return useUndepositedFundsAcct;
	}

	public void setUseUndepositedFundsAcct(boolean useUndepositedFundsAcct) {
		this.useUndepositedFundsAcct = useUndepositedFundsAcct;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(String cashBalance) {
		this.cashBalance = cashBalance;
	}

	public String getLocaleNumber() {
		return localeNumber;
	}

	public void setLocaleNumber(String localeNumber) {
		this.localeNumber = localeNumber;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public String getNextCheckNumber() {
		return nextCheckNumber;
	}

	public void setNextCheckNumber(String nextCheckNumber) {
		this.nextCheckNumber = nextCheckNumber;
	}

	public String getStdServiceCharge() {
		return stdServiceCharge;
	}

	public void setStdServiceCharge(String stdServiceCharge) {
		this.stdServiceCharge = stdServiceCharge;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		locationID = 0;
		name = "";
		companyNumber = "";
		stdServiceCharge = "";
		packageVersion = "";
		inactiveCode = "";
		priceListVersion = "";
		localeNumber = "";
		cashAcct = "";
		arAcct = "";
		financeChargeAcct = "";
		apAcct = "";
		discountAcct = "";
		nextCheckNumber = "";
		discountHandlingCode = "";
		cashBalance = "";
		addr1 = "";
		addr2 = "";
		addr3 = "";
		city = "";
		state = "";
		zip = "";
		county = "";
		phone = "";
		phoneAlternate = "";
		licenseNumber = "";
		preferenceGenericVitals = "";
		managerName = "";
		undepositedFundsAcct = "";
		useUndepositedFundsAcct = false;
		method = "";
		
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ArrayList formErrors = new ArrayList();
		ActionErrors errors = new ActionErrors();

		MessageResourcesFactory messageFactory = null;
		
		messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");
		
		if (name == null || name.trim().equals("")) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.required", 
					resources.getMessage("locationForm.name")));
			formErrors.add("name");
		}

		if (localeNumber == null || localeNumber.trim().equals("")) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.required", 
					resources.getMessage("locationForm.localeID")));
			formErrors.add("localeID");
		} else {
			try {
				Integer.parseInt(localeNumber);
			} catch (NumberFormatException nfe) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.required", 
						resources.getMessage("locationForm.localeID")));
				formErrors.add("localeNumber");
			}
		}
		  

		if (formErrors.size() > 0) {
			request.setAttribute("formErrors", formErrors);
		}

		return errors;
	}

}
