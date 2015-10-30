package com.aldorsolutions.webfdms.accounting.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.apache.struts.action.ActionErrors;

import com.aldorsolutions.webfdms.accounting.bean.AccountingInterface;
import com.aldorsolutions.webfdms.beans.DbUser;

/**
 * Workfile: AccountingInterfaceDTO.java
 * Date: Nov 7, 2005 6:58:49 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class AccountingInterfaceDTO implements Serializable {
	
	static final long serialVersionUID = 1136842052935L;
	
	private int tranCount;
	private int groupTotal;
	private int caseId;
	private int interfaceType;
	private int locationSelected;
	private String fileName;
	private Date tranDate;
	private int summaryTotal;
	private int groupId;
	private int numDistr;
	private String transactionReference;
	private String arAcct;
	private int contractNumber;
	private String caseCode;
	private ArrayList salesTranList;
	private String interfaceFiles [];
//	private ArrayList <Integer> transactionIDsToPost = null;
	private boolean companyWideFile = false;
	private AccountingInterface accountingInterface = null;
	private DbUser dbUser = null;
	private Date dateFrom = null;
	private Date dateTo = null;
	private Date dateMonthEnd = null;
	private ActionErrors errors = null;
	
	
	public AccountingInterfaceDTO() { 
//		transactionIDsToPost = new ArrayList <Integer> ();
		errors = new ActionErrors();
	}

	public String getArAcct() {
		return arAcct;
	}

	public void setArAcct(String arAcct) {
		this.arAcct = arAcct;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public int getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(int contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getGroupTotal() {
		return groupTotal;
	}

	public void setGroupTotal(int groupTotal) {
		this.groupTotal = groupTotal;
	}

	public int getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(int interfaceType) {
		this.interfaceType = interfaceType;
	}

	public int getLocationSelected() {
		return locationSelected;
	}

	public void setLocationSelected(int locationSelected) {
		this.locationSelected = locationSelected;
	}

	public int getNumDistr() {
		return numDistr;
	}

	public void setNumDistr(int numDistr) {
		this.numDistr = numDistr;
	}

	public ArrayList getSalesTranList() {
		return salesTranList;
	}

	public void setSalesTranList(ArrayList salesTranList) {
		this.salesTranList = salesTranList;
	}

	public int getSummaryTotal() {
		return summaryTotal;
	}

	public void setSummaryTotal(int summaryTotal) {
		this.summaryTotal = summaryTotal;
	}

	public int getTranCount() {
		return tranCount;
	}

	public void setTranCount(int tranCount) {
		this.tranCount = tranCount;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getTransactionReference() {
		return transactionReference;
	}

	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

	/**
	 * @return the interfaceFiles
	 */
	public String[] getInterfaceFiles() {
		return interfaceFiles;
	}

	/**
	 * @param interfaceFiles the interfaceFiles to set
	 */
	public void setInterfaceFiles(String[] interfaceFiles) {
		this.interfaceFiles = interfaceFiles;
	}

//	public ArrayList <Integer> getTransactionIDsToPost() {
//		return transactionIDsToPost;
//	}
//	
//	public void clearTransactionIDsToPost() {
//		transactionIDsToPost.clear();
//	}
//
//	public void setTransactionIDsToPost(ArrayList <Integer> transactionIDsToPost) {
//		this.transactionIDsToPost = transactionIDsToPost;
//	}		
//	
//	public void addTransactionIDsToPost(ArrayList <Integer> transactionIDsToPost) {
//		this.transactionIDsToPost.addAll(transactionIDsToPost);
//	}

	public boolean isCompanyWideFile() {
		return companyWideFile;
	}

	public void setCompanyWideFile(boolean companyWideFile) {
		this.companyWideFile = companyWideFile;
	}

	public AccountingInterface getAccountingInterface() {
		return accountingInterface;
	}

	public void setAccountingInterface(AccountingInterface accountingInterface) {
		this.accountingInterface = accountingInterface;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public DbUser getDbUser() {
		return dbUser;
	}

	public void setDbUser(DbUser dbUser) {
		this.dbUser = dbUser;
	}

	public ActionErrors getErrors() {
		return errors;
	}

	public void setErrors(ActionErrors errors) {
		this.errors = errors;
	}

	/**
	 * @return the monthEnd
	 */
	public Date getDateMonthEnd() {
		return dateMonthEnd;
	}

	/**
	 * @param monthEnd the monthEnd to set
	 */
	public void setDateMonthEnd(Date monthEnd) {
		this.dateMonthEnd = monthEnd;
	}
	
}