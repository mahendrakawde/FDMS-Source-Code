package com.aldorsolutions.webfdms.checkwriter.model;


/**
 * @author David Rollins 
 * Date: Apr 27, 2007 
 * File: CheckListDisplayDTO.java
 * 
 */
public class CheckListDisplayDTO extends ApMasterDTO {

	private String payee;
	
	private String location;

	private String userName;
	
	private String statusDisplay;
	
	private String contractName;
	
	private String contractNum;

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the payee
	 */
	public String getPayee() {
		return payee;
	}

	/**
	 * @return the statusDisplay
	 */
	public String getStatusDisplay() {
		return statusDisplay;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param payee the payee to set
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	}

	/**
	 * @param statusDisplay the statusDisplay to set
	 */
	public void setStatusDisplay(String statusDisplay) {
		this.statusDisplay = statusDisplay;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the contractName
	 */
	public String getContractName() {
		return contractName;
	}

	/**
	 * @return the contractNum
	 */
	public String getContractNum() {
		return contractNum;
	}

	/**
	 * @param contractName the contractName to set
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	/**
	 * @param contractNum the contractNum to set
	 */
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	
}