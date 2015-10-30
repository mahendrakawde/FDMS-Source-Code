package com.aldorsolutions.fdms.to.fdmscase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleInfo {
	private Date saleDate;
	private String services = "";
	private String casket = "";
	private String vault = "";
	private String urn = "";
	private float serviceSale;
	private float casketSale;
	private float urnSale;
	private float vaultSale;
	private float other;
	private float gstAmount;
	private List<Disbursement> additionalDisbursements = new ArrayList<Disbursement>(8);
	
	public SaleInfo(){}
	


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

	public float getServiceSale() {
		return serviceSale;
	}

	public void setServiceSale(float serviceSale) {
		this.serviceSale = serviceSale;
	}

	public float getCasketSale() {
		return casketSale;
	}

	public void setCasketSale(float casketSale) {
		this.casketSale = casketSale;
	}

	public float getUrnSale() {
		return urnSale;
	}

	public void setUrnSale(float urnSale) {
		this.urnSale = urnSale;
	}

	public float getVaultSale() {
		return vaultSale;
	}

	public void setVaultSale(float vaultSale) {
		this.vaultSale = vaultSale;
	}

	public float getOther() {
		return other;
	}

	public void setOther(float other) {
		this.other = other;
	}

	public float getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(float gstAmount) {
		this.gstAmount = gstAmount;
	}

	public float getTotalSale() {
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
