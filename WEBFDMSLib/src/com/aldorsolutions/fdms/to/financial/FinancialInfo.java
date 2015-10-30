package com.aldorsolutions.fdms.to.financial;

import java.util.Date;

public class FinancialInfo {
	
	private Date invoiceDate; 
	private Date dueDate;
	private Date lastStatementDate;
	private String salesType;
	private String serviceType;
	private String source;
	private String disposition; 
	private String servicePlan;
	private String haveWeEverProvidedServicesForThisFamily;
	private String whatFuneralHomeDidFamilyPreviouslyUse; 
	private String howDidFamilyHearAboutUs;
	private String miscNotes;
	
	public FinancialInfo(){}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getLastStatementDate() {
		return lastStatementDate;
	}

	public void setLastStatementDate(Date lastStatementDate) {
		this.lastStatementDate = lastStatementDate;
	}

	public String getSalesType() {
		return salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDisposition() {
		return disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public String getServicePlan() {
		return servicePlan;
	}

	public void setServicePlan(String servicePlan) {
		this.servicePlan = servicePlan;
	}

	public String isHaveWeEverProvidedServicesForThisFamily() {
		return haveWeEverProvidedServicesForThisFamily;
	}

	public void setHaveWeEverProvidedServicesForThisFamily(
			String haveWeEverProvidedServicesForThisFamily) {
		this.haveWeEverProvidedServicesForThisFamily = haveWeEverProvidedServicesForThisFamily;
	}

	public String getWhatFuneralHomeDidFamilyPreviouslyUse() {
		return whatFuneralHomeDidFamilyPreviouslyUse;
	}

	public void setWhatFuneralHomeDidFamilyPreviouslyUse(
			String whatFuneralHomeDidFamilyPreviouslyUse) {
		this.whatFuneralHomeDidFamilyPreviouslyUse = whatFuneralHomeDidFamilyPreviouslyUse;
	}

	public String getHowDidFamilyHearAboutUs() {
		return howDidFamilyHearAboutUs;
	}

	public void setHowDidFamilyHearAboutUs(String howDidFamilyHearAboutUs) {
		this.howDidFamilyHearAboutUs = howDidFamilyHearAboutUs;
	}

	public String getMiscNotes() {
		return miscNotes;
	}

	public void setMiscNotes(String miscNotes) {
		this.miscNotes = miscNotes;
	}
	
}
