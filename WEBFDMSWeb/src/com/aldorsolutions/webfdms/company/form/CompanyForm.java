package com.aldorsolutions.webfdms.company.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

import com.aldorsolutions.webfdms.common.Constants;

/**
 * 
 * @author drollins
 * 
 */
public class CompanyForm extends ActionForm {

	private Logger logger = Logger.getLogger(CompanyForm.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = -1338626235760221309L;

	private String companyID = "";

	private String name = "";

	private String address1 = "";

	private String address2 = "";

	private String address3 = "";

	private String city = "";

	private String state = "";

	private String country = "";

	private String postCode = "";

	private String billingAddress1 = "";

	private String billingAddress2 = "";

	private String billingAddress3 = "";

	private String billingCity = "";

	private String billingState = "";

	private String billingCountry = "";

	private String billingPostCode = "";

	private boolean deleted = false;

	private String dataURL = "";

	private String sqlUser = "";

	private String sqlPass = "";

	//private String dbLookup = "java:jdbc/";
	
	private String dbLookup = "java:jboss/datasources/";

	private boolean cemeteryClient = false;

	private boolean funeralClient = false;
	
	private String databaseStatus = "";
	
	private long configID;
	
	private LabelValueBean[] companyOptions = null;
	
	private LabelValueBean[] configOptions = null;
	
	private String [] selectedCompanyOptions = null;
		
	/**
	 * @return Returns the address1.
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            The address1 to set.
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return Returns the address2.
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            The address2 to set.
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return Returns the address3.
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * @param address3
	 *            The address3 to set.
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	/**
	 * @return Returns the billingAddress1.
	 */
	public String getBillingAddress1() {
		return billingAddress1;
	}

	/**
	 * @param billingAddress1
	 *            The billingAddress1 to set.
	 */
	public void setBillingAddress1(String billingAddress1) {
		this.billingAddress1 = billingAddress1;
	}

	/**
	 * @return Returns the billingAddress2.
	 */
	public String getBillingAddress2() {
		return billingAddress2;
	}

	/**
	 * @param billingAddress2
	 *            The billingAddress2 to set.
	 */
	public void setBillingAddress2(String billingAddress2) {
		this.billingAddress2 = billingAddress2;
	}

	/**
	 * @return Returns the billingAddress3.
	 */
	public String getBillingAddress3() {
		return billingAddress3;
	}

	/**
	 * @param billingAddress3
	 *            The billingAddress3 to set.
	 */
	public void setBillingAddress3(String billingAddress3) {
		this.billingAddress3 = billingAddress3;
	}

	/**
	 * @return Returns the billingCity.
	 */
	public String getBillingCity() {
		return billingCity;
	}

	/**
	 * @param billingCity
	 *            The billingCity to set.
	 */
	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	/**
	 * @return Returns the billingCountry.
	 */
	public String getBillingCountry() {
		return billingCountry;
	}

	/**
	 * @param billingCountry
	 *            The billingCountry to set.
	 */
	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}

	/**
	 * @return Returns the billingPostCode.
	 */
	public String getBillingPostCode() {
		return billingPostCode;
	}

	/**
	 * @param billingPostCode
	 *            The billingPostCode to set.
	 */
	public void setBillingPostCode(String billingPostCode) {
		this.billingPostCode = billingPostCode;
	}

	/**
	 * @return Returns the billingState.
	 */
	public String getBillingState() {
		return billingState;
	}

	/**
	 * @param billingState
	 *            The billingState to set.
	 */
	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return Returns the companyID.
	 */
	public String getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID
	 *            The companyID to set.
	 */
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return Returns the dataURL.
	 */
	public String getDataURL() {
		return dataURL;
	}

	/**
	 * @param dataURL
	 *            The dataURL to set.
	 */
	public void setDataURL(String dataURL) {
		this.dataURL = dataURL;
	}

	/**
	 * @return Returns the deleted.
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            The deleted to set.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the postCode.
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            The postCode to set.
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return Returns the sqlPass.
	 */
	public String getSqlPass() {
		return sqlPass;
	}

	/**
	 * @param sqlPass
	 *            The sqlPass to set.
	 */
	public void setSqlPass(String sqlPass) {
		this.sqlPass = sqlPass;
	}

	/**
	 * @return Returns the sqlUser.
	 */
	public String getSqlUser() {
		return sqlUser;
	}

	/**
	 * @param sqlUser
	 *            The sqlUser to set.
	 */
	public void setSqlUser(String sqlUser) {
		this.sqlUser = sqlUser;
	}

	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}

	public String getDbLookup() {
		return dbLookup;
	}

	public void setDbLookup(String dbLookup) {
		this.dbLookup = dbLookup;
	}

	/**
	 * @return the cemeteryClient
	 */
	public boolean isCemeteryClient() {
		return cemeteryClient;
	}

	/**
	 * @return the funeralClient
	 */
	public boolean isFuneralClient() {
		return funeralClient;
	}

	/**
	 * @param cemeteryClient
	 *            the cemeteryClient to set
	 */
	public void setCemeteryClient(boolean cemeteryClient) {
		this.cemeteryClient = cemeteryClient;
	}

	/**
	 * @param funeralClient
	 *            the funeralClient to set
	 */
	public void setFuneralClient(boolean funeralClient) {
		this.funeralClient = funeralClient;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		companyID = "";
		name = "";
		address1 = "";
		address2 = "";
		address3 = "";
		city = "";
		state = "";
		country = "";
		postCode = "";
		billingAddress1 = "";
		billingAddress2 = "";
		billingAddress3 = "";
		billingCity = "";
		billingState = "";
		billingCountry = "";
		billingPostCode = "";
		deleted = false;
		dataURL = "";
		sqlUser = "";
		sqlPass = "";
		dbLookup = "";
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ArrayList <String> formErrors = new ArrayList <String> ();
		ActionErrors errors = new ActionErrors();

		MessageResourcesFactory messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory
				.createFactory();
		MessageResources resources = messageFactory.createResources("ApplicationResources");
		
		if (name == null || name.trim().equals("")) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.isRequired", resources
					.getMessage("companyForm.name")));
			formErrors.add("name");
		}
		String prefix = null;
		if (dataURL == null || dataURL.trim().equals("")) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.isRequired", resources
					.getMessage("companyForm.dataURL")));
			formErrors.add("dataURL");
		} else {
			prefix = resources.getMessage(Constants.DATAURL_PREFIX);
			if (!dataURL.startsWith(prefix)) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.PREFIX_ERROR, "DB URL",
						prefix));
			}

		}

		if (dbLookup == null || dbLookup.trim().equals("")) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.isRequired", resources
					.getMessage("companyForm.dbLookup")));
			formErrors.add("dbLookup");
		} else {

			prefix = resources.getMessage(Constants.DATASOURCE_PREFIX);
			if (!dbLookup.startsWith(prefix)) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.PREFIX_ERROR, "DB LooKup",
						prefix));
			}
		}
		int id = -1;
		try {
			id = Integer.parseInt(companyID);
		} catch (NumberFormatException e) {
			// unable to parse long from String
		}
		if (id > 0) {
			if (sqlUser == null || sqlUser.trim().equals("")) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.isRequired", 
						resources.getMessage("companyForm.sqlUser")));
				formErrors.add("sqlUser");
			}

			if (sqlPass == null || sqlPass.trim().equals("")) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.isRequired", resources
						.getMessage("companyForm.sqlPass")));
				formErrors.add("sqlPass");
			}
		}
		
		long configid = getConfigID();
		
		if ( configid <= 0 ) {
			errors.add(ActionErrors.GLOBAL_ERROR, 
					new ActionError("error.isRequired", 
							resources.getMessage("companyForm.configID")));
			formErrors.add("configID");
		}
		
		if (postCode != null && postCode.trim().length() > 0) {
						
			for(int i=0;postCode!= null && i < postCode.length(); i++){
				if( !(Character.isDigit(postCode.charAt(i))) ){
					if(!( postCode.charAt(i) == '-' || postCode.charAt(i) == ' ')){ 
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.isInt", resources
								.getMessage("companyForm.postalCode")));
						formErrors.add("postCode");
						break;
					}else if( i != 5){
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.isInt", resources
								.getMessage("companyForm.postalCode")));
						formErrors.add("postCode");
						break;
					}
				}
			}
			
		}

		if (billingPostCode != null && billingPostCode.length() > 0) {
			for(int i=0;billingPostCode!= null && i < billingPostCode.length(); i++){
				if( !(Character.isDigit(billingPostCode.charAt(i))) ){
					if(!( billingPostCode.charAt(i) == '-' || billingPostCode.charAt(i) == ' ')){ 
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.isInt", resources
								.getMessage("companyForm.billingPostCode")));
						formErrors.add("billingPostCode");
						break;
					}else if(i != 5){
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.isInt", resources
								.getMessage("companyForm.billingPostCode")));
						formErrors.add("billingPostCode");
						break;
					}
				}
			}
		}

		if (formErrors.size() > 0) {
			request.setAttribute("formErrors", formErrors);
		}

		return errors;
	}

	/**
	 * @return the databaseStatus
	 */
	public String getDatabaseStatus() {
		return databaseStatus;
	}

	/**
	 * @param databaseStatus the databaseStatus to set
	 */
	public void setDatabaseStatus(String databaseStatus) {
		this.databaseStatus = databaseStatus;
	}

	/**
	 * @return the companyOptions
	 */
	public LabelValueBean[] getCompanyOptions() {
		return companyOptions;
	}

	/**
	 * @return the selectedCompanyOptions
	 */
	public String[] getSelectedCompanyOptions() {
		return selectedCompanyOptions;
	}

	/**
	 * @param companyOptions the companyOptions to set
	 */
	public void setCompanyOptions(LabelValueBean[] companyOptions) {
		this.companyOptions = companyOptions;
	}

	/**
	 * @param selectedCompanyOptions the selectedCompanyOptions to set
	 */
	public void setSelectedCompanyOptions(String[] selectedCompanyOptions) {
		this.selectedCompanyOptions = selectedCompanyOptions;
	}

	/**
	 * @return the configID
	 */
	public long getConfigID() {
		return configID;
	}

	/**
	 * @param configID the configID to set
	 */
	public void setConfigID(long configID) {
		this.configID = configID;
	}

	/**
	 * @return the configOptions
	 */
	public LabelValueBean[] getConfigOptions() {
		return configOptions;
	}

	/**
	 * @param configOptions the configOptions to set
	 */
	public void setConfigOptions(LabelValueBean[] configOptions) {
		this.configOptions = configOptions;
	}
	
}