package com.aldorsolutions.webfdms.webservice;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class QBVitalsRecordInfo {

	private Logger logger = Logger.getLogger(QBVitalsRecordInfo.class);

	private String startOfTrans = null;

	private String endOfTrans = null;

	private String customerXML = null;

	private String invoicesXML = null;

	private String paymentsXML = null;

	private boolean customerDataSent = false;

	private boolean invoiceDataSent = false;

	private boolean paymentDataSent = false;
	
	private boolean customerDataProcessed = false;

	private boolean invoiceDataProcessed = false;

	private boolean paymentDataProcessed = false;
	
	private ArrayList <Integer> paymentTransactionIds = null;
	
	private ArrayList <Integer> invoiceTransactionIds = null;

	public String getCustomerDataToSend() {
		StringBuilder sb = new StringBuilder();
		sb.append(startOfTrans);
		sb.append(customerXML);
		sb.append(endOfTrans);
		return (sb.toString());
	}

	public String getInvoiceDataToSend() {
		StringBuilder sb = new StringBuilder();
		sb.append(startOfTrans);
		sb.append(invoicesXML);
		sb.append(endOfTrans);
		return (sb.toString());
	}

	public String getPaymentsDataToSend() {
		StringBuilder sb = new StringBuilder();
		sb.append(startOfTrans);
		sb.append(paymentsXML);
		sb.append(endOfTrans);
		return (sb.toString());
	}

	public boolean hasDataToSend() {
		if ( isCustomerDataSent() && isInvoiceDataSent() && isPaymentDataSent() ) {
			return (false);
		}
		
		return ( true );
	}

	public boolean isCustomerDataSent() {

		if (customerXML == null
				|| (customerXML != null && customerXML.length() == 0)) {
			customerDataSent = true;
		}

		return customerDataSent;
	}

	public void setCustomerDataSent(boolean customerDataSent) {
		this.customerDataSent = customerDataSent;
	}

//	public String getCustomerXML() {
//		return customerXML;
//	}

	public void setCustomerXML(String customerXML) {
		this.customerXML = customerXML;
	}

	public String getEndOfTrans() {
		return endOfTrans;
	}

	public void setEndOfTrans(String endOfTrans) {
		this.endOfTrans = endOfTrans;
	}

	public boolean isInvoiceDataSent() {
		if (invoicesXML == null
				|| (invoicesXML != null && invoicesXML.length() == 0)) {
			invoiceDataSent = true;
		}

		return invoiceDataSent;
	}

	public void setInvoiceDataSent(boolean invoiceDataSent) {
		this.invoiceDataSent = invoiceDataSent;
	}

//	public String getInvoicesXML() {
//		return invoicesXML;
//	}

	public void setInvoicesXML(String invoicesXML) {
		this.invoicesXML = invoicesXML;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public boolean isPaymentDataSent() {
		if (paymentsXML == null
				|| (paymentsXML != null && paymentsXML.length() == 0)) {
			paymentDataSent = true;
		}

		return paymentDataSent;
	}

	public void setPaymentDataSent(boolean paymentDataSent) {
		this.paymentDataSent = paymentDataSent;
	}

//	public String getPaymentsXML() {
//		return paymentsXML;
//	}

	public void setPaymentsXML(String paymentsXML) {
		this.paymentsXML = paymentsXML;
	}

	public String getStartOfTrans() {
		return startOfTrans;
	}

	public void setStartOfTrans(String startOfTrans) {
		this.startOfTrans = startOfTrans;
	}

	public ArrayList <Integer> getInvoiceTransactionIds() {
		return invoiceTransactionIds;
	}

	public void setInvoiceTransactionIds(ArrayList <Integer> invoiceTransactionIds) {
		this.invoiceTransactionIds = invoiceTransactionIds;
	}

	public ArrayList <Integer> getPaymentTransactionIds() {
		return paymentTransactionIds;
	}

	public void setPaymentTransactionIds(ArrayList <Integer> paymentTransactionIds) {
		this.paymentTransactionIds = paymentTransactionIds;
	}

	public boolean isCustomerDataProcessed() {
		return customerDataProcessed;
	}

	public void setCustomerDataProcessed(boolean customerDataProcessed) {
		this.customerDataProcessed = customerDataProcessed;
	}

	public boolean isInvoiceDataProcessed() {
		return invoiceDataProcessed;
	}

	public void setInvoiceDataProcessed(boolean invoiceDataProcessed) {
		this.invoiceDataProcessed = invoiceDataProcessed;
	}

	public boolean isPaymentDataProcessed() {
		return paymentDataProcessed;
	}

	public void setPaymentDataProcessed(boolean paymentDataProcessed) {
		this.paymentDataProcessed = paymentDataProcessed;
	}
	
	
}