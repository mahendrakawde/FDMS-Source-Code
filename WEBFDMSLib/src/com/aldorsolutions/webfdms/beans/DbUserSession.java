package com.aldorsolutions.webfdms.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import com.aldorsolutions.webfdms.database.PersistentI;
import com.aldorsolutions.webfdms.ums.model.RolesMembershipDTO;

/**
 * Interface for one User name/security object Creation date: (11/01/01 1:43:40
 * PM)
 * 
 * @author:
 */
public interface DbUserSession extends PersistentI {

	/**
	 * Insert the method's description here. Creation date: (8/30/2002 8:07:24
	 * AM)
	 * 
	 * @return String
	 */
	public int getCaseListPerScreen();

	/**
	 * Insert the method's description here. Creation date: (8/30/2002 8:07:24
	 * AM)
	 * 
	 * @return String
	 */
	public String getCaseListSortOrder();

	/**
	 * Insert the method's description here. Creation date: (1/12/2002 5:48:44
	 * PM)
	 * 
	 * @return int
	 */
	int getCurrentCaseID();
	int getCurrentSpcID();
	public String getDataUrl();
	/**
	 * Insert the method's description here. Creation date: (8/30/2002 8:07:24
	 * AM)
	 * 
	 * @return String
	 */
	public String getEmailAddr();

	/**
	 * Insert the method's description here. Creation date: (8/30/2002 8:06:55
	 * AM)
	 * 
	 * @return String
	 */
	public String getFirstName();

	/**
	 * Insert the method's description here. Creation date: (1/12/2002 5:49:25
	 * PM)
	 * 
	 * @return String
	 */
	String getGreeting();

	public int getId();

	public String getInitials();

	/**
	 * Insert the method's description here. Creation date: (8/30/2002 8:07:09
	 * AM)
	 * 
	 * @return String
	 */
	public String getLastName();

	public String getState();

	/**
	 * Insert the method's description here. Creation date: (1/12/2002 5:47:26
	 * PM)
	 * 
	 * @return String
	 */
	String getLocationName();

	String getLocaleName();

	public String getPassword();

	public int getRegion();

	public int getLocationId();

	public short getSecurityAcctsRec();

	public short getSecurityAdmin();
	public short getSecurityBank();
	public short getSecurityEnableFinancialChange();
	public short getSecurityDashboardReport();
	
	public short getSecurityFdmsNetwork();
	public short getSecurityFdmsDashboard();
	public short getSecurityFdmsWebservice();
	public short getSecurityFddeWebservice();
	public short getSecurityFdmsAdmin();
	public short getSecurityAtneed();
	public short getSecurityCemetery();
	public short getSecurityFuneral();
	public short getSecurityDelete();

	public short getSecurityFinancial();

	public short getSecurityForms();

	public short getSecurityInventory();

	public short getSecurityPayments();

	public short getSecurityPreneed();

	public short getSecurityReports();
	
	public void setCurrentCemPropID ( int i );
	
	public int getCurrentCemPropID ( );

	public short getSecurityViewOnly();
	
	public short getSecurityAccountingInterface();
	
	public short getSecurityEasyPayment(); 
	
	//added by haranesh patel
	public short getSecurityEregisterbook();
	
	public short getSecurityPriceDescriptionFinancial();
	
	public short getSecuritySpeedData();

	public short getSecurityArrangerManagement();
	
	public short getSecurityGLSalesAccount();
	
	public short getSecurityAdjustFinancial();

	public short getSecurityFormsAvaialble();

	public boolean getLocalizedSpeedData();

	public HashMap getExternalAppConfigMap();

	public String getCaseListDisplayPreneed();

	public String getCaseListDisplayVoided();

	public String getTos();

	public boolean isChangePassword();

	public Timestamp getLockedTimestamp();

	public boolean isUserLocked();

	public int getCompanyID();

	public Timestamp getPasswordTimestamp();

	/**
	 * Insert the method's description here. Creation date: (9/26/2002 10:58:31
	 * AM)
	 * 
	 * @return String
	 */
	String getSqlPassword();

	/**
	 * Insert the method's description here. Creation date: (9/26/2002 10:57:58
	 * AM)
	 * 
	 * @return String
	 */
	String getSqlUser();

	public String getUserName();

	public void setCaseListPerScreen(int newCaseListPerScreen);

	public void setCaseListSortOrder(String newCaseListSortOrder);

	/**
	 * Insert the method's description here. Creation date: (1/12/2002 5:48:44
	 * PM)
	 * 
	 * @return int
	 */
	void setCurrentCaseID(int caseid);

	/**
	 * Insert the method's description here. Creation date: (8/30/2002 8:07:24
	 * AM)
	 * 
	 * @param newEmailAddr
	 *            String
	 */
	public void setEmailAddr(String newEmailAddr);

	/**
	 * Insert the method's description here. Creation date: (8/30/2002 8:06:55
	 * AM)
	 * 
	 * @param newFirstName
	 *            String
	 */
	public void setFirstName(String newFirstName);

	void setId(int newId);

	public void setInitials(String newInitials);

	/**
	 * Insert the method's description here. Creation date: (8/30/2002 8:07:09
	 * AM)
	 * 
	 * @param newLastName
	 *            String
	 */
	public void setLastName(String newLastName);

	public void setRegion(int newRegion);

	public void setSecurityAcctsRec(short newSecurityAcctsRec);

	public void setSecurityAdmin(short newSecurityAdmin);
	public void setSecurityBank(short newSecurityBank);
	public void setSecurityEnableFinancialChange(short newSecurityEnableFinancialChange);
	public void setSecurityDashboardReport(short newSecurityDashboardReport);
	public void setSecurityFdmsNetwork(short newSecurityFdmsNetwork);
	public void setSecurityFdmsDashboard(short newSecurityFdmsDashboard);
	public void setSecurityFdmsWebservice(short newSecurityFdmsWebservice);
	public void setSecurityFddeWebservice(short newSecurityFddeWebservice);
	public void setSecurityFdmsAdmin(short newSecurityFdmsAdmin);
	public void setSecurityAdjustFinancial(short newSecurityAdjustFinancial);

	public void setSecurityAtneed(short newSecurityAtneed);

	public void setSecurityDelete(short newSecurityDelete);

	public void setSecurityFinancial(short newSecurityFinancial);

	public void setSecurityForms(short newSecurityForms);

	public void setSecurityInventory(short newSecurityInventory);

	public void setSecurityPayments(short newSecurityPayments);

	public void setSecurityPreneed(short newSecuirtyPreneed);

	public void setSecurityReports(short newSecurityReports);

	public void setSecurityViewOnly(short newSecurityViewOnly);
	
	public void setSecurityAccountingInterface(short newSecurityAccountingInterface);
	
	public void setSecurityEasyPayment(short newSecurityEasyPayment);
	//added by haranesh patel
	public void setSecurityEregisterbook(short newSecurityEregisterboook);
	
	public void setSecurityPriceDescriptionFinancial(short newPriceDescriptionFinancial);


	public void setLocationId(int locationId);

	public void setLocalizedSpeedData(boolean localizedSpeedData);

	public void setLoggedIn(boolean loggedIn);

	public void setState(String state);

	public void setExternalAppConfigMap(HashMap externalAppConfigMap);

	public void setCaseListDisplayPreneed(String caseListDisplayPreneed);

	public void setCaseListDisplayVoided(String caseListDisplayVoided);

	public void setTos(String tos);

	public void setCompanyID(int companyID);

	public void setChangePassword(boolean changePassword);

	public void setUserLocked(boolean userLocked);

	public void setLockedTimestamp(Timestamp lockoutTimestamp);

	public void setPasswordTimestamp(Timestamp passwordTimestamp);
	
	public String getDbLookup();

	public void setDbLookup(String dbLookup);
	
	void setCurrentSpcID(int spcid);
	
	public ArrayList <RolesMembershipDTO> getRoles();
	
	public void setRoles (ArrayList <RolesMembershipDTO> userRoles);
	
	public ArrayList <DbLocation> getUserLocations();
	
	public void setUserLocations (ArrayList <DbLocation> locations);
	
	public boolean isUserAssignedRole(long roleID);
	
	public boolean isAccountingVacationFlag();

	public long getAccountingVacationUserID();

	public Timestamp getLockoutTimestamp();

	public void setAccountingVacationFlag(boolean accountingVacationFlag);

	public void setAccountingVacationUserID(long accountingVacationUserID);
	
	public void setLockoutTimestamp(Timestamp lockoutTimestamp);

	public double getLimitExternalVendor();
	
	public double getLimitInternalVendor();
	
	public boolean isUserLimitOverride();
	
	public void setLimitExternalVendor(double limitExternalVendor);
	
	public void setLimitInternalVendor(double limitInternalVendor);
	
	public void setUserLimitOverride(boolean userLimitOverride);
	
	public long getConfigID ();
	
	public void setConfigID ( long configID );
	
	public String getLocaleCountry();
	
	public void setLocaleCountry(String country);
}