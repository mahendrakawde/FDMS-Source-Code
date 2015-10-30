/*
 * UserDTO.java
 *
 * Created on February 4, 2005, 8:54 PM
 */

package com.aldorsolutions.webfdms.admin.user.model;

/**
 * 
 * @author Guadalupe Garcia
 */

import java.io.Serializable;
import java.sql.Timestamp;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7929215910109431307L;

	private long userId;

	private String name;

	private String password;

	private int regionId;

	private int administrator;

	private int atneed;

	private int preneed;

	private int financial;

	private int payments;

	private int acctsRec;

	private int forms;

	private int reports;

	private int deleteCases;

	private String initials;

	private int inventory;

	private int viewOnly;
	
	private int accountingInterface;
	
	private int easyPayment;
	
	//added by  haranesh patel
	private int eregisterbook;
	
	private int priceDescriptionFinancial;
	
	/**
	 * @return the eregisterbook
	 */
	public int getEregisterbook() {
		return eregisterbook;
	}

	/**
	 * @param eregisterbook the eregisterbook to set
	 */
	public void setEregisterbook(int eregisterbook) {
		this.eregisterbook = eregisterbook;
	}

	
	/**
	 * @return the priceDescriptionFinancial
	 */
	public int getPriceDescriptionFinancial() {
		return priceDescriptionFinancial;
	}

	/**
	 * @param priceDescriptionFinancial the priceDescriptionFinancial to set
	 */
	public void setPriceDescriptionFinancial(int priceDescriptionFinancial) {
		this.priceDescriptionFinancial = priceDescriptionFinancial;
	}



	private int speedData;
	
	private int arrangerManager;
	
	private int formsAvailable;
	
	private int glSalesAccount;

	private String dbUrl;
	
	private String dbLookup;

	private String deleteCode;

	private String firstName;

	private String lastName;

	private String email;

	private String dbUsername;

	private String dbPassword;

	private String caseListOrder;

	private int caseListLimit;

	private String[] locationIds;

	private int companyID;

	private boolean userLocked;
	
	private int fdmsAdmin;

	private boolean changePassword;

	private Timestamp lockoutTmstmp;
	
	private boolean accountingVacationFlag;
	
	private long accountingVacationUserID;
	
	private boolean userLimitOverride;
	
	private double limitInternalVendor;
	
	private double limitExternalVendor;
	
	private int adjustFinancial;
	
	private int bank;
	
	private int enableFinancialChange;
	
	private int dashboardReport;
	
	private int fdmsNetwork;
	private int fdmsDashboard;
	private int fdmsWebservice;
	private int fddeWebservice;
		
	/** Creates a new instance of UserDTO */
	public UserDTO() {
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public void setAdministrator(int administrator) {
		this.administrator = administrator;
	}

	public void setAtneed(int atneed) {
		this.atneed = atneed;
	}

	public void setPreneed(int preneed) {
		this.preneed = preneed;
	}

	public void setFinancial(int financial) {
		this.financial = financial;
	}

	public void setPayments(int payments) {
		this.payments = payments;
	}

	public void setAcctsRec(int acctsRec) {
		this.acctsRec = acctsRec;
	}

	public void setForms(int forms) {
		this.forms = forms;
	}

	public void setReports(int reports) {
		this.reports = reports;
	}

	public void setDeleteCases(int deleteCases) {
		this.deleteCases = deleteCases;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public void setViewOnly(int viewOnly) {
		this.viewOnly = viewOnly;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public void setDeleteCode(String deleteCode) {
		this.deleteCode = deleteCode;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public void setCaseListOrder(String caseListOrder) {
		this.caseListOrder = caseListOrder;
	}

	public void setCaseListLimit(int caseListLimit) {
		this.caseListLimit = caseListLimit;
	}

	public void setLocationIds(String[] locationIds) {
		this.locationIds = locationIds;
	}

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public void setLockoutTmstmp(Timestamp lockoutTmstmp) {
		this.lockoutTmstmp = lockoutTmstmp;
	}

	public void setUserLocked(boolean userLocked) {
		this.userLocked = userLocked;
	}

	public long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public int getRegionId() {
		return regionId;
	}

	public int getAdministrator() {
		return administrator;
	}

	public int getAtneed() {
		return atneed;
	}

	public int getPreneed() {
		return preneed;
	}

	public int getFinancial() {
		return financial;
	}

	public int getPayments() {
		return payments;
	}

	public int getAcctsRec() {
		return acctsRec;
	}

	public int getForms() {
		return forms;
	}

	public int getReports() {
		return reports;
	}

	public int getDeleteCases() {
		return deleteCases;
	}

	public String getInitials() {
		return initials;
	}

	public int getInventory() {
		return inventory;
	}

	public int getViewOnly() {
		return viewOnly;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public String getDeleteCode() {
		return deleteCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public String getCaseListOrder() {
		return caseListOrder;
	}

	public int getCaseListLimit() {
		return caseListLimit;
	}

	public String[] getLocationIds() {
		return locationIds;
	}

	public boolean isChangePassword() {
		return changePassword;
	}

	public int getCompanyID() {
		return companyID;
	}

	public Timestamp getLockoutTmstmp() {
		return lockoutTmstmp;
	}

	public boolean isUserLocked() {
		return userLocked;
	}

	public String getDbLookup() {
		return dbLookup;
	}

	public void setDbLookup(String dbLookup) {
		this.dbLookup = dbLookup;
	}

	/**
	 * @return the accountingVacationFlag
	 */
	public boolean isAccountingVacationFlag() {
		return accountingVacationFlag;
	}

	/**
	 * @return the accountingVacationUserID
	 */
	public long getAccountingVacationUserID() {
		return accountingVacationUserID;
	}

	/**
	 * @param accountingVacationFlag the accountingVacationFlag to set
	 */
	public void setAccountingVacationFlag(boolean accountingVacationFlag) {
		this.accountingVacationFlag = accountingVacationFlag;
	}

	/**
	 * @param accountingVacationUserID the accountingVacationUserID to set
	 */
	public void setAccountingVacationUserID(long accountingVacationUserID) {
		this.accountingVacationUserID = accountingVacationUserID;
	}

	/**
	 * @return the limitExternalVendor
	 */
	public double getLimitExternalVendor() {
		return limitExternalVendor;
	}

	/**
	 * @return the limitInternalVendor
	 */
	public double getLimitInternalVendor() {
		return limitInternalVendor;
	}

	/**
	 * @return the userLimitOverride
	 */
	public boolean isUserLimitOverride() {
		return userLimitOverride;
	}

	/**
	 * @param limitExternalVendor the limitExternalVendor to set
	 */
	public void setLimitExternalVendor(double limitExternalVendor) {
		this.limitExternalVendor = limitExternalVendor;
	}

	/**
	 * @param limitInternalVendor the limitInternalVendor to set
	 */
	public void setLimitInternalVendor(double limitInternalVendor) {
		this.limitInternalVendor = limitInternalVendor;
	}

	/**
	 * @param userLimitOverride the userLimitOverride to set
	 */
	public void setUserLimitOverride(boolean userLimitOverride) {
		this.userLimitOverride = userLimitOverride;
	}

	/**
	 * @return the accountingInterface
	 */
	public int getAccountingInterface() {
	return accountingInterface;
	}

	/**
	 * @param accountingInterface the accountingInterface to set
	 */
	public void setAccountingInterface(int accountingInterface) {
	this.accountingInterface = accountingInterface;
	}

	/**
	 * @return the speedData
	 */
	public int getSpeedData() {
	return speedData;
	}

	/**
	 * @param speedData the speedData to set
	 */
	public void setSpeedData(int speedData) {
	this.speedData = speedData;
	}

	/**
	 * @return the arrangerManager
	 */
	public int getArrangerManager() {
	return arrangerManager;
	}

	/**
	 * @param arrangerManager the arrangerManager to set
	 */
	public void setArrangerManager(int arrangerManager) {
	this.arrangerManager = arrangerManager;
	}

	/**
	 * @return the formsAvailable
	 */
	public int getFormsAvailable() {
	return formsAvailable;
	}

	/**
	 * @param formsAvailable the formsAvailable to set
	 */
	public void setFormsAvailable(int formsAvailable) {
	this.formsAvailable = formsAvailable;
	}

	/**
	 * @return the glSalesAccount
	 */
	public int getGlSalesAccount() {
	return glSalesAccount;
	}

	/**
	 * @param glSalesAccount the glSalesAccount to set
	 */
	public void setGlSalesAccount(int glSalesAccount) {
	this.glSalesAccount = glSalesAccount;
	}

	public int getAdjustFinancial() {
		return adjustFinancial;
	}

	public void setAdjustFinancial(int adjustFinancial) {
		this.adjustFinancial = adjustFinancial;
	}

	public int getBank() {
		return bank;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}

	public int getFdmsAdmin() {
		return fdmsAdmin;
	}

	public void setFdmsAdmin(int fdmsAdmin) {
		this.fdmsAdmin = fdmsAdmin;
	}

	public int getFdmsNetwork() {
		return fdmsNetwork;
	}

	public void setFdmsNetwork(int fdmsNetwork) {
		this.fdmsNetwork = fdmsNetwork;
	}

	public int getFdmsDashboard() {
		return fdmsDashboard;
	}

	public void setFdmsDashboard(int fdmsDashboard) {
		this.fdmsDashboard = fdmsDashboard;
	}

	public int getFdmsWebservice() {
		return fdmsWebservice;
	}

	public void setFdmsWebservice(int fdmsWebservice) {
		this.fdmsWebservice = fdmsWebservice;
	}

	public int getFddeWebservice() {
		return fddeWebservice;
	}

	public void setFddeWebservice(int fddeWebservice) {
		this.fddeWebservice = fddeWebservice;
	}

	public int getDashboardReport() {
		return dashboardReport;
	}

	public void setDashboardReport(int dashboardReport) {
		this.dashboardReport = dashboardReport;
	}

	/**
	 * @return the easyPayment
	 */
	public int getEasyPayment() {
		return easyPayment;
	}

	/**
	 * @param easyPayment the easyPayment to set
	 */
	public void setEasyPayment(int easyPayment) {
		this.easyPayment = easyPayment;
	}

	public int getEnableFinancialChange() {
		return enableFinancialChange;
	}

	public void setEnableFinancialChange(int enableFinancialChange) {
		this.enableFinancialChange = enableFinancialChange;
	}

	
	
}