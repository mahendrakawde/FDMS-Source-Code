package com.aldorsolutions.fdms.to.fdmscase;

import java.util.Date;

public class FundDepository {
	private String fundType = "";
	private String fundsWith = "";
	private Date startedDate;
	private Date maturityDate ;
	private String account = "";
	private Float estIntRate ;
	private Date lastPaymentDate ;

	private Float faceValue ;
	private Float contractAmount ;
	private Float ytdPaid ;
	private Float totalPaid ;
	private Float ytdInterest ;
	private Float totalInterest ;
	private Float managementFee ;
	private Float commission ;
	private Float lastPaymentAmount ;
	
	public FundDepository(){}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getFundsWith() {
		return fundsWith;
	}

	public void setFundsWith(String fundsWit) {
		this.fundsWith = fundsWit;
	}

	public Date getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(Date dateStarted) {
		this.startedDate = dateStarted;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturity) {
		this.maturityDate = maturity;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Float getEstIntRate() {
		return estIntRate;
	}

	public void setEstIntRate(Float estIntRate) {
		this.estIntRate = estIntRate;
	}

	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	public Float getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(Float faceValue) {
		this.faceValue = faceValue;
	}

	public Float getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(Float contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Float getYTDPaid() {
		return ytdPaid;
	}

	public void setYTDPaid(Float ytdPaid) {
		this.ytdPaid = ytdPaid;
	}

	public Float getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(Float totalPaid) {
		this.totalPaid = totalPaid;
	}

	public Float getYTDInterest() {
		return ytdInterest;
	}

	public void setYTDInterest(Float ytdInterest) {
		this.ytdInterest = ytdInterest;
	}

	public Float getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(Float totalInterest) {
		this.totalInterest = totalInterest;
	}

	public Float getManagementFee() {
		return managementFee;
	}

	public void setManagementFee(Float managementFee) {
		this.managementFee = managementFee;
	}

	public Float getCommission() {
		return commission;
	}

	public void setCommission(Float commission) {
		this.commission = commission;
	}

	public Float getLastPaymentAmount() {
		return lastPaymentAmount;
	}

	public void setLastPaymentAmount(Float lastPaymentAmount) {
		this.lastPaymentAmount = lastPaymentAmount;
	}
	
	
}
