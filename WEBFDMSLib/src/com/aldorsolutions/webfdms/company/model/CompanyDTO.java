package com.aldorsolutions.webfdms.company.model;

import java.io.Serializable;

public class CompanyDTO implements Serializable{
	
	private int companyID = 0;
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
	private String dbLookup = "";
	private boolean cemeteryClient;
	private boolean funeralClient;
	private String databaseStatus;
	private long configID;

	
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
	 * @return Returns the address1.
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 The address1 to set.
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
	 * @param address2 The address2 to set.
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
	 * @param address3 The address3 to set.
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
	 * @param billingAddress1 The billingAddress1 to set.
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
	 * @param billingAddress2 The billingAddress2 to set.
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
	 * @param billingAddress3 The billingAddress3 to set.
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
	 * @param billingCity The billingCity to set.
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
	 * @param billingCountry The billingCountry to set.
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
	 * @param billingPostCode The billingPostCode to set.
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
	 * @param billingState The billingState to set.
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
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country The country to set.
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
	 * @param dataURL The dataURL to set.
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
	 * @param deleted The deleted to set.
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
	 * @param name The name to set.
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
	 * @param postCode The postCode to set.
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
	 * @param sqlPass The sqlPass to set.
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
	 * @param sqlUser The sqlUser to set.
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
	 * @param state The state to set.
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
	 * @param cemeteryClient the cemeteryClient to set
	 */
	public void setCemeteryClient(boolean cemeteryClient) {
		this.cemeteryClient = cemeteryClient;
	}
	/**
	 * @param funeralClient the funeralClient to set
	 */
	public void setFuneralClient(boolean funeralClient) {
		this.funeralClient = funeralClient;
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
	
}