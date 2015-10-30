package com.aldorsolutions.webfdms.accounting.bean;

import org.apache.struts.action.ActionErrors;

import com.aldorsolutions.webfdms.accounting.model.AccountingInterfaceDTO;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistory;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbInvoiceTransHist;
import com.aldorsolutions.webfdms.beans.DbUser;


/**
 * Workfile: AccountingInterface.java
 * Date: Nov 7, 2005 6:58:49 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public interface AccountingInterface {	
	
	public String [] getInterfaceFileName(
			int ACCOUNTING_INTERFACE, int locationSelected, DbUser dbUser);
	
	public boolean initializeExport(String intFile, int intType);
	
	public boolean writeSummaryTran(
			DbUser dbUser,
			AccountingInterfaceDTO accountingInterfaceDTO,
			ActionErrors errors);
	
	public boolean writeSalesTran(
			DbHistory dbHistory, 
			DbUser dbUser,
			AccountingInterfaceDTO accountingInterfaceDTO);
	
	public boolean writeAdjustment(
			DbHistory hist, 
			String fileName, 
			int ACCOUNTING_INTERFACE, 
			DbUser dbUser,
			AccountingInterfaceDTO accountingInterfaceDTO);
	
	public boolean writeMiscCashReceipt(
            DbHistory hist,
            String fileName,
            int ACCOUNTING_INTERFACE,
            DbUser dbUser,
      			AccountingInterfaceDTO accountingInterfaceDTO);
	
	public boolean writeApExpense(
            DbApDistributionHistory check,
            String fileName,
            int ACCOUNTING_INTERFACE,
            DbUser dbUser,
      			AccountingInterfaceDTO accountingInterfaceDTO);

	public boolean writeApInvoiceExpense(
			DbInvoiceTransHist invoiceTransHist,
      String fileName,
      int ACCOUNTING_INTERFACE,
      DbUser dbUser,
			AccountingInterfaceDTO accountingInterfaceDTO);

	public boolean closeFile(String fileName);
}
