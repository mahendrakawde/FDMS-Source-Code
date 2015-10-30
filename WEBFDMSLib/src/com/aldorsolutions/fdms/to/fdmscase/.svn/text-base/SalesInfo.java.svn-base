package com.aldorsolutions.fdms.to.fdmscase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesInfo {
	private Date saleDate;
	private String services = "";
	private String casket = "";
	private String vault = "";
	private String urn = "";
	private Float serviceSale;
	private Float casketSale;
	private Float urnSale;
	private Float vaultSale;
	private Float other;
	private Float gstAmount;
	private List<Disbursement> additionalDisbursements = new ArrayList<Disbursement>(8);
	
	public SalesInfo(){}
	


	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getCasket() {
		return casket;
	}

	public void setCasket(String casket) {
		this.casket = casket;
	}

	public String getVault() {
		return vault;
	}

	public void setVault(String vault) {
		this.vault = vault;
	}

	public String getUrn() {
		return urn;
	}

	public void setUrn(String urn) {
		this.urn = urn;
	}

	public Float getServiceSale() {
		return serviceSale;
	}

	public void setServiceSale(Float serviceSale) {
		this.serviceSale = serviceSale;
	}

	public Float getCasketSale() {
		return casketSale;
	}

	public void setCasketSale(Float casketSale) {
		this.casketSale = casketSale;
	}

	public Float getUrnSale() {
		return urnSale;
	}

	public void setUrnSale(Float urnSale) {
		this.urnSale = urnSale;
	}

	public Float getVaultSale() {
		return vaultSale;
	}

	public void setVaultSale(Float vaultSale) {
		this.vaultSale = vaultSale;
	}

	public Float getOther() {
		return other;
	}

	public void setOther(Float other) {
		this.other = other;
	}

	public Float getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(Float gstAmount) {
		this.gstAmount = gstAmount;
	}

	public Float getTotalSale() {
		return (serviceSale + casketSale + urnSale + vaultSale + other + gstAmount);
	}
	
	public void addDisbursement(String disbursement, float amount){
		additionalDisbursements.add(new Disbursement(disbursement, amount));
	}
	
	public void clearDisbursements(){
		additionalDisbursements.clear();
	}
	
	public List<Disbursement> getAdditionalDisbursements(){
		return additionalDisbursements;
	}
	
	public class Disbursement{
		
		private String disbursement;
		private float amount;
		
		public Disbursement(String disbursement, float amount) {
			super();
			this.disbursement = disbursement;
			this.amount = amount;
		}

		public String getDisbursement() {
			return disbursement;
		}

		public float getAmount() {
			return amount;
		}
		
	}
}
