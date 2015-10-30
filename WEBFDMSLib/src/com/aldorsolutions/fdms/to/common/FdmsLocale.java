package com.aldorsolutions.fdms.to.common;

import java.util.ArrayList;
import java.util.List;

import com.aldorassist.webfdms.model.LocaleDTO;

public class FdmsLocale {
	
	private int id ;
	private String name = "" ;
	private List<FdmsLocation> locations = new ArrayList<FdmsLocation>();
	
	public FdmsLocale(){}
	
	public FdmsLocale(LocaleDTO locale){
		setId(locale.getLocaleID());
		setName(locale.getName());
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
	public List<FdmsLocation> getLocations() {
		return locations;
	}

	public void setLocations(List<FdmsLocation> locations) {
		this.locations = locations;
	}
	public void addLocation(FdmsLocation location){
		locations.add(location);
	}
	
}
