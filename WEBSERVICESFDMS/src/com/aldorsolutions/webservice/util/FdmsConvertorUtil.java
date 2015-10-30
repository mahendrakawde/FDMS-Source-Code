package com.aldorsolutions.webservice.util;

import com.aldorsolutions.webservice.xsd.comm.fdms.*;

public class FdmsConvertorUtil {
	
	private static FdmsConvertorUtil instance = new FdmsConvertorUtil();
	private FdmsConvertorUtil(){}
	
	public static FdmsConvertorUtil getInstance(){
		return instance;
	}
	
	public  String getPhoneNumberString(PhoneNumberType phone) {
		return phone != null ? phone.toString():null;
	}
	
	public Float getAmountObject(MsAmountType amountType){
		Float amount = null;
		if(amountType != null){
			amount = new Float(amountType.getMsAmountType());
		}
		return amount;
	}
	
	public float getAmount(MsAmountType amountType){
		float amount = 0.00f;
		if(amountType != null){
			amount = Float.parseFloat(amountType.getMsAmountType());
		}
		return amount;
	}
	
	public MsAmountType getMsAmountType(float amount){
		MsAmountType amountType = new MsAmountType();
		amountType.setMsAmountType(Float.toString(amount));
		return amountType;
	}
	
	public String getString(ZipCodeType zipCode) {
		return zipCode != null ? zipCode.toString():null;
	}
	
	public ZipCodeType getZipCodeType(String param){
		ZipCodeType zip = null;
		if(!isStringNullOrNill(param)){
			zip = new ZipCodeType();
			zip.setZipCodeType(param);
		}
		return zip;
	}
	
	public String getTimeString(TimeType param){
		return param != null ? param.getTimeType() : null;
	}
	
	public TimeType getTimeType(String param){
		TimeType time = null;
		if(!isStringNullOrNill(param)){
			time = new TimeType();
			time.setTimeType(param);
		}
		return time;
	}
	
	public String getString(SSNType param){
		return param != null ? param.getSSNType() : null;
	}
	
	public SSNType getSSNType(String param){
		SSNType time = null;
		if(!isStringNullOrNill(param)){
			time = new SSNType();
			time.setSSNType(param);
		}
		return time;
	}
	
	private boolean isStringNullOrNill(String string){
		return string == null || string.trim().length() == 0;
	}
}
