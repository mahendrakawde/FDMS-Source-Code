package com.aldorsolutions.webfdms.accounting.model;

import java.io.Serializable;

import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;


/**
 * Workfile: DeceasedDTO.java
 * Date: Nov 29, 2005 3:38:10 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class DeceasedDTO implements Serializable {
	
	final static long serialVersionUID = 1133302248213L;
	
	private long vitalsMasterKey;
	private String contractCode;
	private String deceasedFirstName;
	private String deceasedMidName;
	private String deceasedLastName;
	private String deathDateKey;
	private int arrangerId;
	private int chapelNumber;	
	private String division;
	
	public DeceasedDTO() { }


	/**
	 * @return Returns the arrangerId.
	 */
	public int getArrangerId() {
		return arrangerId;
	}


	/**
	 * @param arrangerId The arrangerId to set.
	 */
	public void setArrangerId(int arrangerId) {
		this.arrangerId = arrangerId;
	}


	/**
	 * @return Returns the chapelNumber.
	 */
	public int getChapelNumber() {
		return chapelNumber;
	}


	/**
	 * @param chapelNumber The chapelNumber to set.
	 */
	public void setChapelNumber(int chapelNumber) {
		this.chapelNumber = chapelNumber;
	}


	/**
	 * @return Returns the contractCode.
	 */
	public String getContractCode() {
		return contractCode;
	}


	/**
	 * @param contractCode The contractCode to set.
	 */
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}


	/**
	 * @return Returns the deathDateKey.
	 */
	public String getDeathDateKey() {
		return deathDateKey;
	}


	/**
	 * @param deathDateKey The deathDateKey to set.
	 */
	public void setDeathDateKey(String deathDateKey) {
		this.deathDateKey = deathDateKey;
	}


	/**
	 * @return Returns the deceasedFirstName.
	 */
	public String getDeceasedFirstName() {
		return deceasedFirstName;
	}


	/**
	 * @param deceasedFirstName The deceasedFirstName to set.
	 */
	public void setDeceasedFirstName(String deceasedFirstName) {
		this.deceasedFirstName = deceasedFirstName;
	}


	/**
	 * @return Returns the deceasedLastName.
	 */
	public String getDeceasedLastName() {
		return deceasedLastName;
	}


	/**
	 * @param deceasedLastName The deceasedLastName to set.
	 */
	public void setDeceasedLastName(String deceasedLastName) {
		this.deceasedLastName = deceasedLastName;
	}


	/**
	 * @return Returns the deceasedMidName.
	 */
	public String getDeceasedMidName() {
		return deceasedMidName;
	}


	/**
	 * @param deceasedMidName The deceasedMidName to set.
	 */
	public void setDeceasedMidName(String deceasedMidName) {
		this.deceasedMidName = deceasedMidName;
	}


	/**
	 * @return Returns the vitalsMasterKey.
	 */
	public long getVitalsMasterKey() {
		return vitalsMasterKey;
	}


	/**
	 * @param vitalsMasterKey The vitalsMasterKey to set.
	 */
	public void setVitalsMasterKey(long vitalsMasterKey) {
		this.vitalsMasterKey = vitalsMasterKey;
	}


	/**
	 * @return Returns the division.
	 */
	public String getDivision() {
		return division;
	}


	/**
	 * @param division The division to set.
	 */
	public void setDivision(String division) {
		this.division = division;
	}


	public String toXML() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("    <Deceased>\n");
		sb.append("      <VitalsId>" + vitalsMasterKey + "</VitalsId>\n");
		sb.append("      <ContractCode>" + contractCode + "</ContractCode>\n");
		sb.append("      <DeceasedFirstName>" + FormatString.nullNull(FormatString.escapeXML(deceasedFirstName)) + "</DeceasedFirstName>\n");
		sb.append("      <DeceasedMidName>" + FormatString.nullNull(FormatString.escapeXML(deceasedMidName)) + "</DeceasedMidName>\n");
		sb.append("      <DeceasedLastName>" + FormatString.nullNull(FormatString.escapeXML(deceasedLastName)) + "</DeceasedLastName>\n");
		sb.append("      <DeathDateKey>" + FormatString.nullNull(FormatDate.YMDtoMMDDYYYY(deathDateKey)) + "</DeathDateKey>\n");
		sb.append("      <ArrangerId>" + arrangerId + "</ArrangerId>\n");
		sb.append("      <ChapelNumber>" + chapelNumber + "</ChapelNumber>\n");
		sb.append("      <Division>" + FormatString.nullNull(division) + "</Division>");
		sb.append("    </Deceased>\n");
		
		return sb.toString();
	}
}
