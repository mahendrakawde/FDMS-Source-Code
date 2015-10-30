package com.aldorassist.webfdms.model;

import java.sql.Date;

public class CreditcardDTO {

	private int id;
	private String passKey;
	private String firstname;
	private String lastname;
	private String cardNumber;
	private String expirationDate;
	private String track1;
	private String track2;
	private String cvv;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String referanceId;
	private String amount;
	private String user;
	private String recNo;
	private long tranDate;
	private int merchandiseId;
	private int vitalsId;
	private String responseCard; 
	private String errorDetail; 
	private String approvalCode;
	
	
	public String getResponseCard() {
		return responseCard;
	}
	public void setResponseCard(String responseCard) {
		this.responseCard = responseCard;
	}
	public String getErrorDetail() {
		return errorDetail;
	}
	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}
	public String getApprovalCode() {
		return approvalCode;
	}
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	public int getMerchandiseId() {
		return merchandiseId;
	}
	public void setMerchandiseId(int merchandiseId) {
		this.merchandiseId = merchandiseId;
	}
	public int getVitalsId() {
		return vitalsId;
	}
	public void setVitalsId(int vitalsId) {
		this.vitalsId = vitalsId;
	}
	public long getTranDate() {
		return tranDate;
	}
	public void setTranDate(long tranDate) {
		this.tranDate = tranDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassKey() {
		return passKey;
	}
	public void setPassKey(String passKey) {
		this.passKey = passKey;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getTrack1() {
		return track1;
	}
	public void setTrack1(String track1) {
		this.track1 = track1;
	}
	public String getTrack2() {
		return track2;
	}
	public void setTrack2(String track2) {
		this.track2 = track2;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getReferanceId() {
		return referanceId;
	}
	public void setReferanceId(String referanceId) {
		this.referanceId = referanceId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getRecNo() {
		return recNo;
	}
	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}
	
	
	
}