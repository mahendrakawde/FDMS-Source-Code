/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.aldorsolutions.dashboard.struts.form.acct;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * MyEclipse Struts Creation date: 04-30-2007
 * 
 * XDoclet definition:
 * 
 * @struts.form name="acctCheckApprovalEditForm"
 */
public class AcctCheckApprovalEditForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5106265255071923576L;

	private long approvalStatus;

	private long masterID = 0;

	private String checkNumber;

	private String payee;

	private String locationName;

	private String locationAddr;
	
	private String locationCitySt;

	private String checkDate;

	private String checkAmount;

	private String checkAmountLong;

	private String memo;

	private String signature;
	
	private String approvalTimestamp;
	
	private boolean priorApproval = false;

	private int vitalsID;
	
	private String vitalsName;
	
	private String vitalsCaseNumber;
	
	private boolean check1099;
	
	private String check1099Amount;
	
	private boolean authorizationNeeded;

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return the checkAmount
	 */
	public String getCheckAmount() {
		return checkAmount;
	}

	/**
	 * @return the checkAmountLong
	 */
	public String getCheckAmountLong() {
		return checkAmountLong;
	}

	/**
	 * @return the checkDate
	 */
	public String getCheckDate() {
		return checkDate;
	}

	/**
	 * @return the checkNumber
	 */
	public String getCheckNumber() {
		return checkNumber;
	}

	/**
	 * @return the locationAddr
	 */
	public String getLocationAddr() {
		return locationAddr;
	}

	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @return the masterID
	 */
	public long getMasterID() {
		return masterID;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @return the payee
	 */
	public String getPayee() {
		return payee;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param checkAmount
	 *            the checkAmount to set
	 */
	public void setCheckAmount(String checkAmount) {
		this.checkAmount = checkAmount;
	}

	/**
	 * @param checkAmountLong
	 *            the checkAmountLong to set
	 */
	public void setCheckAmountLong(String checkAmountLong) {
		this.checkAmountLong = checkAmountLong;
	}

	/**
	 * @param checkDate
	 *            the checkDate to set
	 */
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	/**
	 * @param checkNumber
	 *            the checkNumber to set
	 */
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	/**
	 * @param locationAddr
	 *            the locationAddr to set
	 */
	public void setLocationAddr(String locationAddr) {
		this.locationAddr = locationAddr;
	}

	/**
	 * @param locationName
	 *            the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * @param masterID
	 *            the masterID to set
	 */
	public void setMasterID(long masterID) {
		this.masterID = masterID;
	}

	/**
	 * @param memo
	 *            the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @param payee
	 *            the payee to set
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	}

	/**
	 * @param signature
	 *            the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return the locationCitySt
	 */
	public String getLocationCitySt() {
		return locationCitySt;
	}

	/**
	 * @param locationCitySt the locationCitySt to set
	 */
	public void setLocationCitySt(String locationCitySt) {
		this.locationCitySt = locationCitySt;
	}

	/**
	 * @return the approvalStatus
	 */
	public long getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * @param approvalStatus the approvalStatus to set
	 */
	public void setApprovalStatus(long approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * @return the priorApproval
	 */
	public boolean isPriorApproval() {
		return priorApproval;
	}

	/**
	 * @param priorApproval the priorApproval to set
	 */
	public void setPriorApproval(boolean priorApproval) {
		this.priorApproval = priorApproval;
	}

	/**
	 * @return the approvalTimestamp
	 */
	public String getApprovalTimestamp() {
		return approvalTimestamp;
	}

	/**
	 * @param approvalTimestamp the approvalTimestamp to set
	 */
	public void setApprovalTimestamp(String approvalTimestamp) {
		this.approvalTimestamp = approvalTimestamp;
	}

	/**
	 * @return the check1099
	 */
	public boolean isCheck1099() {
		return check1099;
	}

	/**
	 * @return the check1099Amount
	 */
	public String getCheck1099Amount() {
		return check1099Amount;
	}

	/**
	 * @return the vitalsID
	 */
	public int getVitalsID() {
		return vitalsID;
	}

	/**
	 * @param check1099 the check1099 to set
	 */
	public void setCheck1099(boolean check1099) {
		this.check1099 = check1099;
	}

	/**
	 * @param check1099Amount the check1099Amount to set
	 */
	public void setCheck1099Amount(String check1099Amount) {
		this.check1099Amount = check1099Amount;
	}

	/**
	 * @param vitalsID the vitalsID to set
	 */
	public void setVitalsID(int vitalsID) {
		this.vitalsID = vitalsID;
	}

	/**
	 * @return the vitalsCaseNumber
	 */
	public String getVitalsCaseNumber() {
		return vitalsCaseNumber;
	}

	/**
	 * @return the vitalsName
	 */
	public String getVitalsName() {
		return vitalsName;
	}

	/**
	 * @param vitalsCaseNumber the vitalsCaseNumber to set
	 */
	public void setVitalsCaseNumber(String vitalsCaseNumber) {
		this.vitalsCaseNumber = vitalsCaseNumber;
	}

	/**
	 * @param vitalsName the vitalsName to set
	 */
	public void setVitalsName(String vitalsName) {
		this.vitalsName = vitalsName;
	}

	/**
	 * @return the authorizationNeeded
	 */
	public boolean isAuthorizationNeeded() {
		return authorizationNeeded;
	}

	/**
	 * @param authorizationNeeded the authorizationNeeded to set
	 */
	public void setAuthorizationNeeded(boolean authorizationNeeded) {
		this.authorizationNeeded = authorizationNeeded;
	}
	
}