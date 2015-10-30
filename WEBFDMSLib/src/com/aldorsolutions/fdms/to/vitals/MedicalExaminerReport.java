package com.aldorsolutions.fdms.to.vitals;

import java.util.Date;

import com.aldorsolutions.fdms.to.common.Address;

public class MedicalExaminerReport {
	
	private Date injuryDate;
	private String injuryTime = "";
	private String injuryDescription = "";
	private boolean injuryAtWork;
	private String inerInjuryPlace = "";
	private String decedentPositionForTransportInjury = "";
	private Address location = Address.getDefault();
	
	public MedicalExaminerReport() {}

	public Date getInjuryDate() {
		return injuryDate;
	}

	public void setInjuryDate(Date injuryDate) {
		this.injuryDate = injuryDate;
	}

	public String getInjuryTime() {
		return injuryTime;
	}

	public void setInjuryTime(String injuryTime) {
		this.injuryTime = injuryTime;
	}

	public String getInjuryDescription() {
		return injuryDescription;
	}

	public void setInjuryDescription(String injuryDescription) {
		this.injuryDescription = injuryDescription;
	}

	public boolean isInjuryAtWork() {
		return injuryAtWork;
	}

	public void setInjuryAtWork(boolean injuryAtWork) {
		this.injuryAtWork = injuryAtWork;
	}

	public String getInerInjuryPlace() {
		return inerInjuryPlace;
	}

	public void setInerInjuryPlace(String inerInjuryPlace) {
		this.inerInjuryPlace = inerInjuryPlace;
	}

	public String getDecedentPositionForTransportInjury() {
		return decedentPositionForTransportInjury;
	}

	public void setDecedentPositionForTransportInjury(
			String decedentPositionForTransportInjury) {
		this.decedentPositionForTransportInjury = decedentPositionForTransportInjury;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}
}
