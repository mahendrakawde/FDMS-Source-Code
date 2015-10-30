package com.aldorsolutions.fdms.to.common;

import java.util.ArrayList;
import java.util.List;

public class Address {
	
	private List<String> streets = new ArrayList<String>(4);
	private String city ="";
	private String county = "";
	private String stateCode = "";
	private String zip = "";
	private String country = "";
	private static Address defaultAddress = new Address();
	
	public static Address getDefault(){
		return defaultAddress;
	}
	public List<String> getStreets() {
		return streets;
	}
	public void addStreet(String street){
		if(street != null){
			streets.add(street);
		}
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
