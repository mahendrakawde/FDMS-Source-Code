package com.aldorassist.webfdms.model;

import java.sql.Date;

public class ShowReportSchedulingDTO {

	private int schedulingID;
	private String formName; //state report name
	private String localeName;
	private String locationName;
	private String fromDate;
	private String toDate;
	private String runDate;
	private String reportName; //the result .pdf, .doc, .xml
	private String emailTo;
	private String emailCC;
	private String shortName;
	private String rmName;
	private String udName;
	private String status;
	/**
	 * @return the udName
	 */
	public String getUdName() {
		return udName;
	}
	/**
	 * @param udName the udName to set
	 */
	public void setUdName(String udName) {
		this.udName = udName;
	}
	/**
	 * @return the rmName
	 */
	public String getRmName() {
		return rmName;
	}
	/**
	 * @param rmName the rmName to set
	 */
	public void setRmName(String rmName) {
		this.rmName = rmName;
	}
	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**
	 * @return the emailTo
	 */
	public String getEmailTo() {
		return emailTo;
	}
	/**
	 * @param emailTo the emailTo to set
	 */
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	/**
	 * @return the emailCC
	 */
	public String getEmailCC() {
		return emailCC;
	}
	/**
	 * @param emailCC the emailCC to set
	 */
	public void setEmailCC(String emailCC) {
		this.emailCC = emailCC;
	}


	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}
	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}
	/**
	 * @return the localeName
	 */
	public String getLocaleName() {
		return localeName;
	}
	/**
	 * @param localeName the localeName to set
	 */
	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}
	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}
	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the runDate
	 */
	public String getRunDate() {
		return runDate;
	}
	/**
	 * @param runDate the runDate to set
	 */
	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}
	/**
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}
	/**
	 * @param reportName the reportName to set
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	/**
	 * @return the schedulingID
	 */
	public int getSchedulingID() {
		return schedulingID;
	}
	/**
	 * @param schedulingID the schedulingID to set
	 */
	public void setSchedulingID(int schedulingID) {
		this.schedulingID = schedulingID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}