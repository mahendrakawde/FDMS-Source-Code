package com.aldorsolutions.webfdms.accounting.model;

import java.io.Serializable;

import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;


/**
 * Workfile: CustomerDTO.java
 * Date: Nov 29, 2005 3:47:26 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class SaleInfoDTO implements Serializable {
	
	static final long serialVersionUID = 1133301088766L;

	private String contractCode;
	private String saleDate;
	private String locationName;
	private String locationCode;
	private int vitalsMasterKey;
	
	public SaleInfoDTO() { }

	public int getVitalsMasterKey() {
		return vitalsMasterKey;
	}
	
	public void setVitalsMasterKey(int in) {
		vitalsMasterKey = in;
	}
	
	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String in) {
		contractCode = in;
	}
	
	public String getSaleDate() {
		return saleDate;
	}
	
	public void setSaleDate(String in) {
		saleDate = in;
	}

	public String getLocationName() {
		return locationName;
	}
	
	public void setLocationName(String in) {
		locationName = in;
	}
	
	public String getLocationCode() {
		return locationCode;
	}
	
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	
	public String toXML() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("    <SaleInfo>\n");		
		sb.append("    </SaleInfo>\n");
		
		return sb.toString();
	}
	
	public String toQuickbooksXML() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("    <saleinfo>\n");
		sb.append("      <reference>" + contractCode + "</reference>\n");
		sb.append("      <saledate>" + FormatDate.MDYtoMMDDYYYY(saleDate) + "</saledate>\n");
		sb.append("      <location>" + locationName + "</location>\n");
		sb.append("    </saleinfo>\n");
		
		return sb.toString();
	}
	
	private static String formatXML ( String string ) {
		return ( FormatString.nullNull(FormatString.escapeXML(string)) );
	}
	
}