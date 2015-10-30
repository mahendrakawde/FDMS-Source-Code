package com.aldorsolutions.fdms.to.fdmscase;

import java.util.Date;

import com.aldorsolutions.fdms.to.common.*;

public class DeceasedPerson extends Person {
	private String memorialName = "";
	private String placeOfDeath = "";
	private Address placeOfDeathAddress = Address.getDefault();
	private String locDeathLicenseNumber = "";
	private String localityOfDeath = "";
	private Date deathDate ;
	private Date serviceDate;
	private Date dispositionDate;
	
	public DeceasedPerson(){}

	public String getMemorialName() {
		return memorialName;
	}

	public void setMemorialName(String memorialName) {
		this.memorialName = memorialName;
	}

	public String getPlaceOfDeath() {
		return placeOfDeath;
	}

	public void setPlaceOfDeath(String placeOfDeath) {
		this.placeOfDeath = placeOfDeath;
	}

	public Address getPlaceOfDeathAddress() {
		return placeOfDeathAddress;
	}

	public void setPlaceOfDeathAddress(Address placeOfDeathAddress) {
		this.placeOfDeathAddress = placeOfDeathAddress;
	}

	public String getLocDeathLicenseNumber() {
		return locDeathLicenseNumber;
	}

	public void setLocDeathLicenseNumber(String locDeathLicenseNumber) {
		this.locDeathLicenseNumber = locDeathLicenseNumber;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public Date getDispositionDate() {
		return dispositionDate;
	}

	public void setDispositionDate(Date dispositionDate) {
		this.dispositionDate = dispositionDate;
	}

	public String getLocalityOfDeath() {
		return localityOfDeath;
	}

	public void setLocalityOfDeath(String localityOfDeath) {
		this.localityOfDeath = localityOfDeath;
	}
	
}
