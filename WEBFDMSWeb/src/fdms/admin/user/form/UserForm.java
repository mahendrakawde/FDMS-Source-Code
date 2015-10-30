/*
 * UserForm.java
 *
 * Created on February 5, 2005, 1:35 PM
 */

package fdms.admin.user.form;

/**
 * 
 * @author Guadalupe Garcia
 */

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorsolutions.webfdms.security.bean.SecurityManagerBean;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;

public class UserForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7427176632543873044L;

	private String userId = "0";

	private String name;

	private String password;

	private String regionId = "0";

	private boolean administrator = false;

	private boolean atneed = false;

	private boolean preneed = false;

	private boolean financial = false;

	private boolean payments = false;

	private boolean acctsRec = false;

	private boolean forms = false;

	private boolean reports = false;

	private boolean deleteCases = false;

	private String initials;

	private boolean inventory = false;

	private boolean viewOnly = false;
	
	private boolean accountingInterface = false;
	
	private boolean easyPayment= false;
	
	private boolean priceDescriptionFinancial = false;
	
	//added by haranesh patel
	private boolean eregisterbook = false;
	
	/**
	 * @return the eregisterbook
	 */
	public boolean isEregisterbook() {
		return eregisterbook;
	}

	/**
	 * @param eregisterbook the eregisterbook to set
	 */
	public void setEregisterbook(boolean eregisterbook) {
		this.eregisterbook = eregisterbook;
	}
	
	

	/**
	 * @return the priceDescriptionFinancial
	 */
	public boolean isPriceDescriptionFinancial() {
		return priceDescriptionFinancial;
	}

	/**
	 * @param priceDescriptionFinancial the priceDescriptionFinancial to set
	 */
	public void setPriceDescriptionFinancial(boolean priceDescriptionFinancial) {
		this.priceDescriptionFinancial = priceDescriptionFinancial;
	}



	private boolean speedData = false;
	
	private boolean arrangerManager = false;
	
	private boolean formsAvailable = false;
	
	private boolean glSalesAccount = false;
	
	private boolean adjustFinancial = false;
	
	private boolean bank = false;
	
	private boolean dashboardReport = false;
	
	private boolean FdmsNetwork = false;
	
	private boolean FdmsDashboard = false;
	
	private boolean FdmsWebservice = false;
	
	private boolean FddeWebservice = false;

	private String dbUrl;

	private String firstName;

	public String lastName;

	private String email;

	private String dbUsername;

	private String dbPassword;

	private String caseListOrder;

	private String caseListLimit = "10";

	private String[] locationIds = null;

	private ArrayList locations = new ArrayList();

	private int companyID = 0;

	private ArrayList companies = new ArrayList();
	
	private ArrayList roles = new ArrayList();

	private boolean userLocked = false;
	
	private boolean fdmsAdmin = false;

	private boolean userLockedState = false;

	private Timestamp lockoutTimestamp = null;

	private ArrayList locales = new ArrayList();

	private String[] localeIds;
	
	private String[] userRoles;

	private String localeMapJavascript;
	
	private String dbLookup = null;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public void setAtneed(boolean atneed) {
		this.atneed = atneed;
	}

	public void setPreneed(boolean preneed) {
		this.preneed = preneed;
	}

	public void setFinancial(boolean financial) {
		this.financial = financial;
	}

	public void setPayments(boolean payments) {
		this.payments = payments;
	}

	public void setAcctsRec(boolean acctsRec) {
		this.acctsRec = acctsRec;
	}

	public void setForms(boolean forms) {
		this.forms = forms;
	}

	public void setReports(boolean reports) {
		this.reports = reports;
	}

	public void setDeleteCases(boolean deleteCases) {
		this.deleteCases = deleteCases;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public void setInventory(boolean inventory) {
		this.inventory = inventory;
	}

	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
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

	public void setCaseListLimit(String caseListLimit) {
		this.caseListLimit = caseListLimit;
	}

	public void setLocationIds(String[] locationIds) {
		this.locationIds = locationIds;
	}

	public void setLocations(ArrayList locations) {
		this.locations = locations;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public void setUserLocked(boolean userLocked) {
		this.userLocked = userLocked;
	}

	public void setCompanies(ArrayList companies) {
		this.companies = companies;
	}

	public void setLockoutTimestamp(Timestamp lockTmstmp) {
		this.lockoutTimestamp = lockTmstmp;
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getRegionId() {
		return regionId;
	}

	public boolean getAdministrator() {
		return administrator;
	}

	public boolean getAtneed() {
		return atneed;
	}

	public boolean getPreneed() {
		return preneed;
	}

	public boolean getFinancial() {
		return financial;
	}

	public boolean getPayments() {
		return payments;
	}

	public boolean getAcctsRec() {
		return acctsRec;
	}

	public boolean getForms() {
		return forms;
	}

	public boolean getReports() {
		return reports;
	}

	public boolean getDeleteCases() {
		return deleteCases;
	}

	public String getInitials() {
		return initials;
	}

	public boolean getInventory() {
		return inventory;
	}

	public boolean getViewOnly() {
		return viewOnly;
	}

	public String getDbUrl() {
		return dbUrl;
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

	public String getCaseListLimit() {
		return caseListLimit;
	}

	public String[] getLocationIds() {
		return locationIds;
	}

	public ArrayList getLocations() {
		return locations;
	}

	public int getCompanyID() {
		return companyID;
	}

	public ArrayList getCompanies() {
		return companies;
	}

	public boolean getUserLocked() {
		return userLocked;
	}

	public Timestamp getLockoutTimestamp() {
		return lockoutTimestamp;
	}
	
	public String getDbLookup() {
		return dbLookup;
	}

	public void setDbLookup(String dbLookup) {
		this.dbLookup = dbLookup;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		userId = "0";
		name = null;
		password = null;
		regionId = "0";
		administrator = false;
		atneed = false;
		preneed = false;
		financial = false;
		payments = false;
		acctsRec = false;
		forms = false;
		reports = false;
		deleteCases = false;
		initials = null;
		inventory = false;
		viewOnly = false;
		accountingInterface = false;
		easyPayment =false;
		eregisterbook = false;
		speedData = false;
		adjustFinancial = false;
		bank = false;
		dashboardReport = false;
		arrangerManager = false;
		formsAvailable = false;
		dbUrl = null;
		firstName = null;
		lastName = null;
		email = null;
		dbUsername = null;
		dbPassword = null;
		caseListOrder = "DeathDateKey";
		caseListLimit = "10";
		locationIds = null;
		locations = new ArrayList();
		companyID = 0;
		companies = new ArrayList();
		userLocked = false;
		fdmsAdmin = false;
		lockoutTimestamp = null;
		locales = new ArrayList();
		localeIds = null;
		dbLookup = null;
		localeMapJavascript = null;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ArrayList <String> formErrors = new ArrayList <String> ();
		ActionErrors errors = new ActionErrors();

		MessageResourcesFactory messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory
				.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");

		if (name == null || name.trim().equals("")) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.required", resources
					.getMessage("userForm.username")));
			formErrors.add("name");
		}

		if (password == null || password.trim().equals("")) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.required", resources
					.getMessage("userForm.password")));
			formErrors.add("password");
		} else {

			if ((companyID > 0) && ("***SAME***".equals(password) == false)) {

				SecurityManagerBean mbSecurity = new SecurityManagerBean();

				SecurityConfigDTO secureDTO = mbSecurity.getSecurityConfig(companyID);

				if (mbSecurity.isPasswordComplexityValid(name, password, secureDTO) == false) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.password.complexity"));
					formErrors.add("password");
				}

			}

		}

		if (firstName == null || firstName.trim().equals("")) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.required", resources
					.getMessage("userForm.firstName")));
			formErrors.add("firstName");
		}

		if (lastName == null || lastName.trim().equals("")) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.required", resources
					.getMessage("userForm.lastName")));
			formErrors.add("lastName");
		}

		if (regionId == null || regionId.trim().equals("")) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.required", resources
					.getMessage("userForm.regionId")));
			formErrors.add("regionId");
		} else {
			int regionIdi = 0;
			try {
				regionIdi = Integer.parseInt(regionId);
			} catch (NumberFormatException e) {
				// unable to parse int from String;
			}
			if (regionIdi <= 0) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.int", resources
						.getMessage("userForm.regionId")));
				formErrors.add("regionId");
			}
		}

/* CJongs - 10/19/2007 Those are not in the user validation.
  		if (dbUrl == null || dbUrl.trim().equals("")) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.required", resources
					.getMessage("userForm.dbUrl")));
			formErrors.add("dbUrl");
		}

		if (dbUsername == null || dbUsername.trim().equals("")) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.required", resources
					.getMessage("userForm.dbUsername")));
			formErrors.add("dbUsername");
		}

		if (dbPassword == null || dbPassword.trim().equals("")) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.required", resources
					.getMessage("userForm.dbPassword")));
			formErrors.add("dbPassword");
		}
*/
		if (companyID <= 0) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.required", resources
					.getMessage("userForm.companyID")));
			formErrors.add("companyID");
		}

		if (formErrors.size() > 0)
			request.setAttribute("formErrors", formErrors);

		return errors;
	}

	/**
	 * @return Returns the localeIds.
	 */
	public String[] getLocaleIds() {
		return localeIds;
	}

	/**
	 * @param localeIds
	 *            The localeIds to set.
	 */
	public void setLocaleIds(String[] localeIds) {
		this.localeIds = localeIds;
	}

	/**
	 * @return Returns the localeMapJavascript.
	 */
	public String getLocaleMapJavascript() {
		return localeMapJavascript;
	}

	/**
	 * @param localeMapJavascript
	 *            The localeMapJavascript to set.
	 */
	public void setLocaleMapJavascript(String localeMapJavascript) {
		this.localeMapJavascript = localeMapJavascript;
	}

	/**
	 * @return Returns the locales.
	 */
	public ArrayList getLocales() {
		return locales;
	}

	/**
	 * @param locales
	 *            The locales to set.
	 */
	public void setLocales(ArrayList locales) {
		this.locales = locales;
	}

	/**
	 * @return Returns the userLockedState.
	 */
	public boolean isUserLockedState() {
		return userLockedState;
	}

	/**
	 * @param userLockedState
	 *            The userLockedState to set.
	 */
	public void setUserLockedState(boolean userLockedState) {
		this.userLockedState = userLockedState;
	}

	/**
	 * @return the roles
	 */
	public ArrayList getRoles() {
		return roles;
	}

	/**
	 * @return the userRoles
	 */
	public String[] getUserRoles() {
		return userRoles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(ArrayList roles) {
		this.roles = roles;
	}

	/**
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles(String[] userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * @return the accountingInterface
	 */
	public boolean getAccountingInterface() {
		return accountingInterface;
	}

	/**
	 * @param accountingInterface the accountingInterface to set
	 */
	public void setAccountingInterface(boolean accountingInterface) {
		this.accountingInterface = accountingInterface;
	}

	/**
	 * @return the speedData
	 */
	public boolean getSpeedData() {
		return speedData;
	}

	/**
	 * @param speedData the speedData to set
	 */
	public void setSpeedData(boolean speedData) {
		this.speedData = speedData;
	}

	/**
	 * @return the arrangerManager
	 */
	public boolean getArrangerManager() {
		return arrangerManager;
	}

	/**
	 * @param arrangerManager the arrangerManager to set
	 */
	public void setArrangerManager(boolean arrangerManager) {
		this.arrangerManager = arrangerManager;
	}

	/**
	 * @return the formsAvaialable
	 */
	public boolean getFormsAvailable() {
		return formsAvailable;
	}

	/**
	 * @param formsAvaialable the formsAvaialable to set
	 */
	public void setFormsAvailable(boolean formsAvailable) {
		this.formsAvailable = formsAvailable;
	}

	/**
	 * @return the glSalesAccount
	 */
	public boolean getGlSalesAccount() {
		return glSalesAccount;
	}

	/**
	 * @param glSalesAccount the glSalesAccount to set
	 */
	public void setGlSalesAccount(boolean glSalesAccount) {
		this.glSalesAccount = glSalesAccount;
	}

	public boolean isAdjustFinancial() {
		return adjustFinancial;
	}

	public void setAdjustFinancial(boolean adjustFinancial) {
		this.adjustFinancial = adjustFinancial;
	}

	public boolean isBank() {
		return bank;
	}

	public void setBank(boolean bank) {
		this.bank = bank;
	}

	public boolean isFdmsAdmin() {
		return fdmsAdmin;
	}

	public void setFdmsAdmin(boolean fdmsAdmin) {
		this.fdmsAdmin = fdmsAdmin;
	}

	public boolean isFdmsNetwork() {
		return FdmsNetwork;
	}

	public void setFdmsNetwork(boolean fdmsNetwork) {
		FdmsNetwork = fdmsNetwork;
	}

	public boolean isFdmsDashboard() {
		return FdmsDashboard;
	}

	public void setFdmsDashboard(boolean fdmsDashboard) {
		FdmsDashboard = fdmsDashboard;
	}

	public boolean isFdmsWebservice() {
		return FdmsWebservice;
	}

	public void setFdmsWebservice(boolean fdmsWebservice) {
		FdmsWebservice = fdmsWebservice;
	}

	public boolean isFddeWebservice() {
		return FddeWebservice;
	}

	public void setFddeWebservice(boolean fddeWebservice) {
		FddeWebservice = fddeWebservice;
	}

	public boolean isDashboardReport() {
		return dashboardReport;
	}

	public void setDashboardReport(boolean dashboardReport) {
		this.dashboardReport = dashboardReport;
	}

	/**
	 * @return the easyPayment
	 */
	public boolean isEasyPayment() {
		return easyPayment;
	}

	/**
	 * @param easyPayment the easyPayment to set
	 */
	public void setEasyPayment(boolean easyPayment) {
		this.easyPayment = easyPayment;
	}

}