package com.aldorsolutions.fdms.to.common;

import java.util.ArrayList;
import java.util.List;

import com.aldorsolutions.webfdms.beans.DbLocation;

public class FdmsLocation {
	
	private int id ;
	private String name = "" ;
	private Address address = Address.getDefault();
	private String license = "";
	private List<String> phones = new ArrayList<String> ();
	
	public FdmsLocation(){}
	
	public FdmsLocation(DbLocation location){
		setId(location.getId());
		setName(location.getName());
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public void addPhone(String phone){
		if(phone != null){
			phones.add(phone);
		}
	}
}
