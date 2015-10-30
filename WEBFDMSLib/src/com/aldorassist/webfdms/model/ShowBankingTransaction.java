package com.aldorassist.webfdms.model;

public class ShowBankingTransaction {

	private String type;
	private String description;
	private String tranDate;
	private String amount;
	private String status;
	private String voidTitle;
	private String tranId;
	private String checkNumber;
	private String payTo;
	
	public static final String DEPOSIT = "D";
	public static final String FEE = "F";	
	
	
	public String getPayTo() {
		return payTo;
	}
	public void setPayTo(String payTo) {
		this.payTo = payTo;
	}
	public String getCheckNumber() {
		return checkNumber;
	}
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	public String getTranId() {
		return tranId;
	}
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	public String getVoidTitle() {
		return voidTitle;
	}
	public void setVoidTitle(String voidTitle) {
		this.voidTitle = voidTitle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTranDate() {
		return tranDate;
	}
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
