package com.aldorsolutions.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aldorsolutions.webservice.xsd.comm.fdde.CaseIdType;
import com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeIdType;


public class UserLocation {
	
	private int locale;
	private int location;
	
	public UserLocation(){}
	
	public UserLocation(int locale, int location) {
		super();
		this.locale = locale;
		this.location = location;
	}
	
	
	public int getLocale() {
		return locale;
	}

	public void setLocale(int locale) {
		this.locale = locale;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	public String getFuneralHomeIdStr(){
		return getFuneralHomeIdStr(locale, location);
	}
	
	public FuneralHomeIdType getFuneralHomeIdType(){
		return getFuneralHomeIdType(locale, location);
	}
	
	public static String getListStr(List<UserLocation> userLocations, boolean isLocale){
		String listStr = "";
		
		for(UserLocation location: userLocations){
			if(listStr.length()>0){
				listStr +=",";
			}
			
			listStr += isLocale ? location.locale : location.location;
			
		}
		return listStr;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj == null || !(obj instanceof UserLocation)){
			return false;
		}
		
		UserLocation other = (UserLocation)obj;
		if(this == other){
			return true;
		}
		
		return (this.locale == other.locale) && (this.location == other.location);
		
	}
	
public boolean equals(int locale, int location) {
		
		return (this.locale == locale) && (this.location == location);
		
	}

	@Override
	public int hashCode() {
		return getFuneralHomeIdStr().hashCode();
	}

	@Override
	public String toString() {
		return "UserLocation localeId : " + locale + ", locationId : " +location + "/n";
	}
	
	public static String getFuneralHomeIdStr(int localeId, int locationId){
		return "L"+localeId+"FH"+locationId;
	}
	
	public static UserLocation getLocationFromFuneralHomeID(FuneralHomeIdType funeralHomeId){
		
		if(funeralHomeId == null){
			return null;
		}
		String fhId = funeralHomeId.getFuneralHomeIdType();
		int localeId = Integer.parseInt(getLocaleStr(fhId));
		int locationId = Integer.parseInt(getLocationStrFromFuneralHomeId(fhId));
		return new UserLocation(localeId, locationId);
		
	}
	
	public static UserLocation getLocationFromCaseID(CaseIdType caseIdType){
		
		if(caseIdType == null){
			return null;
		}
		String caseId = caseIdType.getCaseIdType();
		int localeId = Integer.parseInt(getLocaleStr(caseId));
		int locationId = Integer.parseInt(getLocationStrFromCaseId(caseId));
		return new UserLocation(localeId, locationId);
		
	}
	
	public static Map<Integer,UserLocation> getVitalsToLocationMap(CaseIdType caseIdType){
		
		if(caseIdType == null){
			return null;
		}
		UserLocation location = getLocationFromCaseID(caseIdType);
		String caseId = caseIdType.getCaseIdType();
		Integer vitalsId = Integer.valueOf(getVitalsId(caseId));
		Map<Integer, UserLocation> result = new HashMap<Integer, UserLocation>(1);
		result.put(vitalsId, location);
		return result;
	}
	
	public static String getLocaleStr(String id){
		if(id==null || id.length()<5){
			return null;
		}
		return id.substring(id.indexOf('L')+1,id.indexOf("FH"));
	}
	
	public static String getLocationStrFromFuneralHomeId(String fhId){
		if(fhId==null || fhId.length()<5){
			return null;
		}
		return fhId.substring(fhId.indexOf("FH")+2);
	}
	
	public static String getLocationStrFromCaseId(String caseId){
		if(caseId==null || caseId.length()<7){
			return null;
		}
		return caseId.substring(caseId.indexOf("FH")+2, caseId.indexOf("C"));
	}
	
	public static String getVitalsId(String caseId){
		if(caseId==null || caseId.length()<7){
			return null;
		}
		return caseId.substring(caseId.indexOf('C')+1);
	}
	
	public static String getFuneralHomeIdStr(String caseId){
		if(caseId==null || caseId.length()<7){
			return null;
		}
		return caseId.substring(0, caseId.indexOf("C"));
	}
	
	public static FuneralHomeIdType getFuneralHomeIdTypeFromCaseID(String caseId){
		return getFuneralHomeIdType(getFuneralHomeIdStr(caseId));
	}
	
	public static FuneralHomeIdType getFuneralHomeIdType(int localeId, int locationId){
		return getFuneralHomeIdType(getFuneralHomeIdStr(localeId, locationId));
	}
	
	public static FuneralHomeIdType getFuneralHomeIdType(String funeralHomeId){
		FuneralHomeIdType id = null;
		if(funeralHomeId != null && funeralHomeId.trim().length()>0){
			id = new FuneralHomeIdType();
			id.setFuneralHomeIdType(funeralHomeId.trim());
		}
		return id;
	}
}
