package com.aldorsolutions.fdms.to.service;

import java.util.ArrayList;
import java.util.List;

import com.aldorsolutions.fdms.to.common.*;
import com.aldorsolutions.webfdms.beans.DbUserSession;

public class FdmsUserInfo {
	
	private int id ;
	private String userName = "" ;
	private String firstName = "";
	private String lastName = "";
	private int companyId;
	private int regionId;
	private int locationId;
	private List<FdmsLocale> locales = new ArrayList<FdmsLocale>();

	
	public FdmsUserInfo (){
	}
	
	public FdmsUserInfo (DbUserSession user){
		setId(user.getId());
		setUserName(user.getUserName());
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setRegionId(user.getRegion());
		setLocationId(user.getLocationId());	
		setCompanyId(user.getCompanyID());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public List<FdmsLocale> getLocales() {
		return locales;
	}

	public void setLocales(List<FdmsLocale> locales) {
		this.locales = locales;
	}
	
	public void addLocale(FdmsLocale locale){
		locales.add(locale);
	}

}
