package com.aldorsolutions.fdms.to.vitals;

public class VitalsIdInfo {

	private String firstName;
	private String middleName;
	private String lastName;
	private int vitalsMasterKey;
	private String contractCode;
	private String caseCode;
	private int localeId;
	private int locationId;
	
	public VitalsIdInfo() {}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getVitalsMasterKey() {
		return vitalsMasterKey;
	}

	public void setVitalsMasterKey(int vitalsMasterKey) {
		this.vitalsMasterKey = vitalsMasterKey;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public int getLocaleId() {
		return localeId;
	}

	public void setLocaleId(int localeId) {
		this.localeId = localeId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	
}
