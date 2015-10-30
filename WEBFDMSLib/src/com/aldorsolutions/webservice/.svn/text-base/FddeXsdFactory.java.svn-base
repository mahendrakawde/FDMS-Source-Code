package com.aldorsolutions.webservice;

import java.util.ArrayList;
import java.util.List;

import com.aldorsolutions.webservice.xsd.comm.*;
import com.aldorsolutions.webservice.xsd.comm.fdde.*;

public class FddeXsdFactory {
	
	private static final FddeXsdFactory instance = new FddeXsdFactory();
	
	private FddeXsdFactory(){};
	
	public static FddeXsdFactory getInstance(){
		return instance;
	}
	
	public Float getDeAmountObject(DeAmountType amountType){
		Float amount = null;
		if(amountType != null){
			amount = new Float(amountType.getDeAmountType());
		}
		return amount;
	}
	
	public float getAmount(DeAmountType amountType){
		float amount = 0.00f;
		if(amountType != null){
			amount = Float.parseFloat(amountType.getDeAmountType());
		}
		return amount;
	}
	
	public DeAmountType getDeAmountType(float amount){
		DeAmountType amountType = new DeAmountType();
		amountType.setDeAmountType(Float.toString(amount));
		return amountType;
	}
	
	boolean isStringNotNullOrNill(String string){
		return string != null && string.trim().length() > 0;
	}

	public CaseIdType createCaseIdType(String caseId) {
		CaseIdType id = null;
		if(isStringNotNullOrNill(caseId)){
			id = new CaseIdType();
			id.setCaseIdType(caseId.trim());
		}
		return id;
	}

	public CaseTypeInfo createCaseTypeInfo(boolean preNeedType, String preNeedStatus) {
		CaseTypeInfo typeInfo = new CaseTypeInfo();
		if(preNeedType){
			if(PreNeedStatusType._active.equals(preNeedStatus)){
				typeInfo.setPreNeed(PreNeedStatusType.active);
			}else if(PreNeedStatusType._serviced.equals(preNeedStatus)){
				typeInfo.setPreNeed(PreNeedStatusType.serviced);
			}else if(PreNeedStatusType._canceled.equals(preNeedStatus)){
				typeInfo.setPreNeed(PreNeedStatusType.canceled);
			}
		}else{
			AtNeedType atNeed = new AtNeedType();
			atNeed.setAtNeedType("");
			typeInfo.setAtNeed(atNeed);
		}
		return typeInfo;
	}
	
	public DeCompleteNameType createDeCompleteNameType(String salutation, String firstName, String middleName, 
			String lastName, String prefix, String suffix, String maidenName){
		
		DeCompleteNameType name = new DeCompleteNameType();
		boolean nameNotSet = !setDeNameType(salutation, firstName,	middleName, lastName, name);
		
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		
		if(isStringNotNullOrNill(prefix)){
			name.setPrefix(convertor.getStrMax15Len(prefix.trim()));
			nameNotSet = false;
		}
		
		if(isStringNotNullOrNill(suffix)){
			name.setSuffix(convertor.getStrMax30Len(suffix.trim()));
			nameNotSet = false;
		}
		
		if(isStringNotNullOrNill(maidenName)){
			name.setMaidenName(convertor.getStrMax60Len(maidenName.trim()));
			nameNotSet = false;
		}
		if(nameNotSet){
			name = null;
		}
		return name;
	}
	
	public DeNameType createDeNameType(String salutation, String firstName, 
				String middleName, String lastName){
		
		DeNameType name = new DeNameType();
		if(!setDeNameType(salutation, firstName,	middleName, lastName, name)){
			name=null;
		}
		
		return name;
	}
	
	private boolean setDeNameType(String salutation, String firstName, 
				String middleName, String lastName, DeNameType name){
		
		boolean nameSet = false;
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		
		if(isStringNotNullOrNill(salutation)){
			name.setSalutation(convertor.getStrMax30Len(salutation.trim()));
			nameSet = true;
		}
		
		if(isStringNotNullOrNill(firstName)){
			name.setFirstName(convertor.getStrMax50Len(firstName.trim()));
			nameSet = true;
		}
		
		if(isStringNotNullOrNill(middleName)){
			name.setMiddleName(convertor.getStrMax50Len(middleName.trim()));
			nameSet = true;
		}
		
		if(isStringNotNullOrNill(lastName)){
			name.setLastName(convertor.getStrMax50Len(lastName.trim()));
			nameSet = true;
		}
		return nameSet;
	}

	public AddressType createAddressType(List<String> street, String city, String county, String state,	String zip) {
		
		AddressType address = new AddressType();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		boolean addressNotSet = true;
		
		if(street != null && street.size()>0){
			
			ArrayList<StrMax50Len> streets = new ArrayList<StrMax50Len>(3);
			for(int i=0; i<street.size() && i<3; i++){
				if(isStringNotNullOrNill(street.get(i))){
					streets.add(convertor.getStrMax50Len(street.get(i).trim()));
				}
			}
			if(streets.size()>0){
				address.setStreet(streets.toArray(new StrMax50Len[streets.size()]));
				addressNotSet = false;
			}
		}
		
		if(isStringNotNullOrNill(city)){
			address.setCity(convertor.getStrMax50Len(city.trim()));
			addressNotSet = false;
		}
		
		if(isStringNotNullOrNill(county)){
			address.setCounty(convertor.getStrMax40Len(county.trim()));
			addressNotSet = false;
		}
		
		if(isStringNotNullOrNill(state)){
//			System.out.println(state);
			address.setStateAbbreviation(convertor.getStrMax40Len(state.trim()));
			addressNotSet = false;
		}
		
		if(isStringNotNullOrNill(zip)){
			address.setZipCode(convertor.getStrMax40Len(zip.trim()));
			addressNotSet = false;
		}
		
		if(addressNotSet){
			address = null;
		}
		
		return address;
	}
	
	public DeExtendedAddressType createDeExtendedAddressType(List<String> street, String city, 
					String state, String zip, String country){
		
		DeExtendedAddressType address = new DeExtendedAddressType();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		boolean addressNotSet = true;
		
		if(street != null && street.size()>0){
			
			ArrayList<StrMax35Len> streets = new ArrayList<StrMax35Len>(3);
			for(int i=0; i<street.size() && i<3; i++){
				if(isStringNotNullOrNill(street.get(i))){
					streets.add(convertor.getStrMax35Len(street.get(i).trim()));
				}
			}
			if(streets.size()>0){
				address.setStreet(streets.toArray(new StrMax35Len[streets.size()]));
				addressNotSet = false;
			}
		}
		
		if(isStringNotNullOrNill(city)){
			address.setCity(convertor.getStrMax30Len(city.trim()));
			addressNotSet = false;
		}
		
		if(isStringNotNullOrNill(state)){
			address.setStateAbbreviation(convertor.getStrMax30Len(state.trim()));
			addressNotSet = false;
		}
		
		if(isStringNotNullOrNill(zip)){
			address.setZipCode(convertor.getStrMax14Len(zip.trim()));
			addressNotSet = false;
		}
		
		if(addressNotSet){
			address = null;
		}
		
		return address;
	}
	
	public DeResidenceType createDeResidenceType(String street, String city, 
			String county,String state, String zip, String country, String locality, String residenceLengthOfTime){
		
		DeResidenceType residenceAddress = new DeResidenceType();
		boolean addressNotSet = !setDeStandardAddressType(street, city, county, state, zip, country, residenceAddress);
		
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		
		if(isStringNotNullOrNill(locality)){
			residenceAddress.setLocality(convertor.getLocalityType(locality.trim()));
			addressNotSet = false;
		}
		
		if(isStringNotNullOrNill(residenceLengthOfTime)){
			residenceAddress.setResidenceLengthOfTime(convertor.getStrMax15Len(residenceLengthOfTime.trim()));
			addressNotSet = false;
		}
		if(addressNotSet){
			residenceAddress = null;
		}
		return residenceAddress;
	}

	public DeStandardAddressType createDeStandardAddressType(String street, String city, 
			String county,String state, String zip, String country){
		
		DeStandardAddressType address = new DeStandardAddressType();
		if(!setDeStandardAddressType(street, city, county, state, zip, country, address)){
			address = null;
		}
		return address;
	}
	
	private boolean setDeStandardAddressType(String street, String city, 
			String county, String state, String zip, String country, DeStandardAddressType address){
		
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		boolean addressSet = false;
		
		if(isStringNotNullOrNill(street)){
			address.setStreet(convertor.getStrMax30Len(street.trim()));
			addressSet = addressSet?addressSet:address.getStreet() != null;
		}
		
		if(isStringNotNullOrNill(city)){
			address.setCity(convertor.getStrMax50Len(city.trim()));
			addressSet = addressSet?addressSet:address.getCity() != null;
		}
		
		if(isStringNotNullOrNill(county)){
			address.setCounty(convertor.getStrMax30Len(county.trim()));
			addressSet = addressSet?addressSet:address.getCounty() != null;
		}
		
		if(isStringNotNullOrNill(state)){
			address.setStateAbbreviation(convertor.getStrMax50Len(state.trim()));
			addressSet = addressSet?addressSet:address.getStateAbbreviation() != null;
		}
		
		if(isStringNotNullOrNill(zip)){
			address.setZipCode(convertor.getStrMax14Len(zip.trim()));
			addressSet = addressSet?addressSet:address.getZipCode() != null;
		}
		
		if(isStringNotNullOrNill(country)){
			address.setCountry(convertor.getStrMax50Len(country.trim()));
			addressSet = addressSet?addressSet:address.getCountry() != null;
		}
		
		return addressSet;
	}

	public DeContactType createDeContactType(String phone, String cellPhone, String email) {
		
		DeContactType contact = new DeContactType();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		boolean contactNotSet = true;
		
		if(isStringNotNullOrNill(phone)){
			contact.setPhone(convertor.getStrMax20Len(phone.trim()));
			contactNotSet = false;
		}
		
		if(isStringNotNullOrNill(cellPhone)){
			contact.setCellPhone(convertor.getStrMax20Len(cellPhone.trim()));
			contactNotSet = false;
		}
		
		if(isStringNotNullOrNill(email)){
			contact.setEmail(convertor.getStrMax50Len(email.trim()));
			contactNotSet = false;
		}
		
		if(contactNotSet){
			contact = null;
		}
		return contact;
	}
	
}
