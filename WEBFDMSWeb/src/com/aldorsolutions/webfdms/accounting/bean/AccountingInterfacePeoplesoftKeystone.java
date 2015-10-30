package com.aldorsolutions.webfdms.accounting.bean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

import com.aldorassist.webfdms.dao.GlAccountTranslationDAO;
import com.aldorassist.webfdms.dao.InvoiceDAO;
import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.model.InvoiceDTO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorsolutions.webfdms.accounting.model.AccountingInterfaceDTO;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistory;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.beans.DbInvoiceTransHist;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.checkwriter.dao.ApDistributionHistoryDAO;
import com.aldorsolutions.webfdms.checkwriter.dao.ApMasterDAO;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApDistributionHistoryDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApMasterDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * Workfile: AccountingInterfaceGreatPlains.java
 * Date: 10/05/2006 
 * Author: David Rollins, CJongs
 *
 * Copyright 2006. FDMS Network, All Rights Reserved
 */
public class AccountingInterfacePeoplesoftKeystone implements AccountingInterface {

	final private static Logger logger = Logger.getLogger(AccountingInterfacePeoplesoftKeystone.class.getName());

	private SimpleDateFormat usFormat = null;
	private SimpleDateFormat usFormatMMDDYYYY = null;
	private Date currentDate = null;
	private static final String DELIMETER = "|";

	private long lineNumber = 0;

	static final int KEYSTONE_GLACCTS_FILE = 0;
	static final int KEYSTONE_ACCTSPAYBL_FILE = 1;
	static final int KEYSTONE_ACCTSRECV_FILE = 2;

	static final int DEBIT = 1;
	static final int CREDIT = -1;

	public String[] getInterfaceFileName(int ACCOUNTING_INTERFACE, int locationSelected, DbUser dbUser) {

		String[] fileNames = new String[3];

		fileNames[KEYSTONE_GLACCTS_FILE] = generateFileName(ACCOUNTING_INTERFACE, locationSelected, dbUser,
				KEYSTONE_GLACCTS_FILE);
		fileNames[KEYSTONE_ACCTSPAYBL_FILE] = generateFileName(ACCOUNTING_INTERFACE, locationSelected, dbUser,
				KEYSTONE_ACCTSPAYBL_FILE);
		fileNames[KEYSTONE_ACCTSRECV_FILE] = generateFileName(ACCOUNTING_INTERFACE, locationSelected, dbUser,
				KEYSTONE_ACCTSRECV_FILE);

		return (fileNames);

	}

	private String generateFileName(int ACCOUNTING_INTERFACE, int locationSelected, DbUser dbUser, int fileType) {

		StringBuffer filename = new StringBuffer();
		StringBuffer filepath = new StringBuffer();

		usFormat = new SimpleDateFormat("MM/dd/yy");
		usFormatMMDDYYYY = new SimpleDateFormat("MM/dd/yyyy");

		currentDate = Calendar.getInstance().getTime();

		// Construct file name
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_Hmmss");
		filename.append(formatter.format(currentDate));
		filename.append("_");

		// add location name to file name
		filename.append(AccountingInterfaceManagerBean.getLocationDescriptor(locationSelected, dbUser));

		filename.append(".");
		filename.append(locationSelected);
		filename.append(".");
		filename.append(ACCOUNTING_INTERFACE);

		switch (fileType) {
		case KEYSTONE_GLACCTS_FILE:
			filename.append("_GLACCT");
			break;
		case KEYSTONE_ACCTSPAYBL_FILE:
			filename.append("_ACCTPAY");
			break;
		case KEYSTONE_ACCTSRECV_FILE:
			filename.append("_ACCTREC");
			break;
		default:
			break;
		}

		filename.append(".txt");

		// Construct the file FILE
		filepath.append(filename);

		return filepath.toString();
	}

	public boolean initializeExport(String intFile, int intType) {
		return true;
	}

	public boolean writeSummaryTran(DbUser dbUser, AccountingInterfaceDTO accountingInterfaceDTO, ActionErrors errors) {
		return true;
	}

	//public boolean writeAdjustment(DbHistory dbHistory, String fileName, int ACCOUNTING_INTERFACE, DbUser dbUser) {

	public boolean writeSalesTran(DbHistory dbHistory, DbUser dbUser, AccountingInterfaceDTO accountingInterfaceDTO) {
		boolean result = true;
		DbCase caseinfo = null;
		DbLocation dbLocation = null;
		DbCase dbCase = null;

		DatabaseTransaction t = null;

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(dbUser);
			// check if need to get AR account from location
			dbCase = FdmsDb.getInstance().getCase(t, dbHistory.getLMainKey());
			dbLocation = FdmsDb.getInstance().getLocation(t, dbCase.getChapelNumber());

			// DO NOT PROCESS THIS TRANSACTION if case or location not found --
			// invalid data
			if (dbCase == null || dbLocation == null) {
				// AppLog.error("InterfaceAccounting.WriteSalesTran found invalid data
				// at hist#"+hist.getId());
				return false;
			}
			accountingInterfaceDTO.setArAcct(dbLocation.getArAcct());

			// check if need to save reference
			if (accountingInterfaceDTO.getTransactionReference() == null) {
				// use either case code or contract code
				caseinfo = FdmsDb.getInstance().getCase(t, accountingInterfaceDTO.getCaseId());
				int icontractno = FormatNumber.parseInteger(caseinfo.getContractCode());
				if (icontractno < 1)
					icontractno = caseinfo.getId();
				accountingInterfaceDTO.setContractNumber(icontractno);
				accountingInterfaceDTO.setCaseCode(caseinfo.getCaseCode());

				if (caseinfo != null && caseinfo.getCaseCode().compareTo(" ") > 0) {
					accountingInterfaceDTO.setTransactionReference(caseinfo.getCaseCode());
				} else if (caseinfo != null) {
					accountingInterfaceDTO.setTransactionReference(caseinfo.getContractCode());
				} else
					accountingInterfaceDTO.setTransactionReference("CASEID" + String.valueOf(accountingInterfaceDTO.getCaseId()));
			}
			// Check if need to save transaction date
			if (accountingInterfaceDTO.getTranDate() == null) {
				accountingInterfaceDTO.setTranDate(dbHistory.getCHistDate());
			}

			result = writeARTran(dbHistory, dbUser, accountingInterfaceDTO.getDateMonthEnd(), accountingInterfaceDTO
					.getFileName());
		} catch (PersistenceException e) {
			logger.error("InterfaceAccounting.WriteSalesTran found invalid data at hist# " + dbHistory.getId()
					+ ". Persistence Exception:", e);
			result = false;
		} finally {
			if (t != null) {
				t.closeConnection();
				t = null;
			}
		}
		return result;
	}

	private boolean writeARTran(DbHistory dbHistory, DbUser dbUser, Date monthEnd, String fileName) {
		boolean result = true;
		DbInvMaster dbInvMaster = null;
		DbPriceList dbPriceList = null;
		DbCase dbCase = null;
		DbLocation dbLocation = null;
		DbFinancialSummary dbFinancialSummary = null;
		DatabaseTransaction t = null;

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(dbUser);
			dbCase = FdmsDb.getInstance().getCase(t, dbHistory.getLMainKey());
			dbLocation = FdmsDb.getInstance().getLocation(t, dbCase.getChapelNumber());
			dbFinancialSummary = FdmsDb.getInstance().getFinancial(t, dbHistory.getLMainKey());

			// Check if this is a TAX charge
			if (dbHistory.getIHistType() == 99 || dbHistory.getIHistType() == 98
					|| (dbHistory.getCHistDesc().indexOf("Tax") > 0) || (dbHistory.getCHistDesc().indexOf("tax") > 0)) {
				exportTaxItem(dbHistory, dbCase, dbLocation, monthEnd, fileName);
			} else if (dbHistory.getCHistSPF() == DbHistory.HIST_SPF_SALE.charAt(0) && dbHistory.getLHistInvSeqNo() > 0) {
				// If SPF = 'S' and invMaster > 0 then we have an inventory item
				// Get the inventory item before we export it.
				dbInvMaster = FdmsDb.getInstance().getInvMaster(t, dbHistory.getLHistInvSeqNo());
				// export it
				exportInventoryItem(dbUser, dbHistory, dbCase, dbLocation, dbFinancialSummary, dbInvMaster, monthEnd, fileName);
			} else if (dbHistory.getCHistSPF() == DbHistory.HIST_SPF_SALE.charAt(0) && dbHistory.getLHistInvSeqNo() == 0) {
				// This is a price list item
				dbPriceList = FdmsDb.getInstance().getPriceList(t, dbHistory.getPriceListID());
				exportServiceItem(dbUser, dbHistory, dbCase, dbLocation, dbFinancialSummary, dbPriceList, monthEnd, fileName);
			} else if (dbHistory.getCHistSPF() == DbHistory.HIST_SPF_PAYMENT.charAt(0)) {
				exportPaymentItem(dbHistory, dbCase, dbLocation, monthEnd, fileName);
			}
					
		} catch (PersistenceException e) {
			logger.error("InterfaceAccounting.WriteSalesTran found invalid data at hist# " + dbHistory.getId()
					+ ". Persistence Exception:", e);
			result = false;
		} finally {
			if (t != null) {
				t.closeConnection();
				t = null;
			}
		}

		return result;
	}

	
	
	private String getARPostingDate(DbHistory dbHistory) {
		String date = null;
		
	  if (dbHistory.getCHistOriginalPosting() == 'Y') {
	  	date = usFormatMMDDYYYY.format(FormatDate.convertToDateYYMMDD(dbHistory.getServiceDate()));
	  } else {
	  	date = usFormatMMDDYYYY.format(dbHistory.getCHistDate());
	  }
	  
	  return date;
	}
	
	
	private boolean exportInventoryItem(DbUser dbUser, DbHistory dbHistory, DbCase dbCase, DbLocation dbLocation,
					DbFinancialSummary financialSummary, DbInvMaster dbInvMaster, Date monthEndDate, String fileName) {
		GlAccountTranslationDAO accountTransDAO = new GlAccountTranslationDAO(dbUser);
		StringBuffer transRow = new StringBuffer();
		String exportFileDescription = null;
		String assetGLAcctNumber = null;
		String salesGLAcctNumber = null;
		String COGSGLAcctNumber = null;

		exportFileDescription = "AR";
		// Now find the sale gl account number that we are supposed to use
		assetGLAcctNumber = accountTransDAO.getAssetGLAcctNumber(dbInvMaster.getAccountDescCDID(), 
						financialSummary.getSalesDescCDID());
		sbAppend(transRow, exportFileDescription); // Transaction Type
		sbAppend(transRow, usFormat.format(monthEndDate)); // End of month entered
		sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
		sbAppend(transRow, assetGLAcctNumber); // GL Account Number
		sbAppend(transRow, dbLocation.getLocationNumber()); // Location Code
		// Multiple the amount times -1 to indicate a credit
		sbAppend(transRow, getAmountValue(CREDIT * dbHistory.getLHistCostOfSale())); // Monetary Amount
		sbAppend(transRow, dbCase.getContractCode()); // Reference "Case Number"
		sbAppend(transRow, ""); // Line description 2 -- NOT USED
		sbAppend(transRow, getARPostingDate(dbHistory)); // Transaction Date
		sbAppend(transRow, dbHistory.getCHistDesc(), true); // Line Description

		AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());

		transRow = new StringBuffer();
		// Now find the slae gl account number that we are supposed to use
		COGSGLAcctNumber = accountTransDAO.getCostOfGoodsSoldGLAcctNumber(dbInvMaster.getAccountDescCDID(),
					financialSummary.getSalesDescCDID());
		sbAppend(transRow, exportFileDescription); // Transaction Type
		sbAppend(transRow, usFormat.format(monthEndDate)); // End of month entered
		sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
		sbAppend(transRow, COGSGLAcctNumber); // GL Account Number
		sbAppend(transRow, dbLocation.getLocationNumber()); // Location Code
		// Multiple the amount times -1 to indicate a credit
		sbAppend(transRow, getAmountValue(DEBIT * dbHistory.getLHistCostOfSale())); // Monetary Amount
		sbAppend(transRow, dbCase.getContractCode()); // Reference "Case Number"
		sbAppend(transRow, ""); // Line description 2 -- NOT USED
		sbAppend(transRow, getARPostingDate(dbHistory)); // Transaction Date
		sbAppend(transRow, dbHistory.getCHistDesc(), true); // Line Description

		AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());

		transRow = new StringBuffer();
		salesGLAcctNumber = accountTransDAO.getSalesGLAcctNumber(dbInvMaster.getAccountDescCDID(), financialSummary
				.getSalesDescCDID());
		sbAppend(transRow, exportFileDescription); // Transaction Type
		sbAppend(transRow, usFormat.format(monthEndDate)); // End of month entered
		sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
		sbAppend(transRow, salesGLAcctNumber); // GL Account Number
		sbAppend(transRow, dbLocation.getLocationNumber()); // Location Code
		// Multiple the amount times -1 to indicate a credit
		sbAppend(transRow, getAmountValue(CREDIT * dbHistory.getLHistAmount())); // Monetary Amount
		sbAppend(transRow, dbCase.getContractCode()); // Reference "Case Number"
		sbAppend(transRow, ""); // Line description 2 -- NOT USED
		sbAppend(transRow, getARPostingDate(dbHistory)); // Transaction Date
		sbAppend(transRow, dbHistory.getCHistDesc(), true); // Line Description

		AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());

		transRow = new StringBuffer();
		sbAppend(transRow, exportFileDescription); // Transaction Type
		sbAppend(transRow, usFormat.format(monthEndDate)); // End of month entered
		sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
		sbAppend(transRow, "11010"); // This is the static AR GL Account Number
		sbAppend(transRow, dbLocation.getLocationNumber()); // Location Code
		// Multiple the amount times -1 to indicate a credit
		sbAppend(transRow, getAmountValue(DEBIT * dbHistory.getLHistAmount())); // Monetary Amount
		sbAppend(transRow, dbCase.getContractCode()); // Reference "Case Number"
		sbAppend(transRow, ""); // Line description 2 -- NOT USED
		sbAppend(transRow, getARPostingDate(dbHistory)); // Transaction Date
		sbAppend(transRow, dbHistory.getCHistDesc(), true); // Line Description

		AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
		return true;
	}

	private boolean exportServiceItem(DbUser dbUser, DbHistory dbHistory, DbCase dbCase, DbLocation dbLocation,
			DbFinancialSummary financialSummary, DbPriceList dbPriceList, Date monthEnd, String fileName) {
		StringBuffer transRow = new StringBuffer();
		String exportFileDescription = "AR";
		String salesGLAcctNumber = null;
		GlAccountTranslationDAO accountTransDAO = new GlAccountTranslationDAO(dbUser);
//try {
		salesGLAcctNumber = accountTransDAO.getSalesGLAcctNumber(dbPriceList.getAccountDescCDID(), financialSummary
				.getSalesDescCDID());
//} catch (Exception e) {
//	System.out.println("\n\n\n\n\n\n\n\n\ndude\n\n\n\n\n\n\n\n");
//}
		sbAppend(transRow, exportFileDescription); // Transaction Type
		sbAppend(transRow, usFormat.format(monthEnd)); // End of month entered
		sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
		sbAppend(transRow, salesGLAcctNumber); // GL Account Number
		sbAppend(transRow, dbLocation.getLocationNumber()); // Location Code
		// Multiple the amount times -1 to indicate a credit
		sbAppend(transRow, getAmountValue(CREDIT * dbHistory.getLHistAmount())); // Monetary Amount
		sbAppend(transRow, dbCase.getContractCode()); // Reference "Case Number"
		sbAppend(transRow, ""); // Line description 2 -- NOT USED
		sbAppend(transRow, getARPostingDate(dbHistory)); // Transaction Date
		sbAppend(transRow, dbHistory.getCHistDesc(), true); // Line Description

		AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());

		transRow = new StringBuffer();
		sbAppend(transRow, exportFileDescription); // Transaction Type
		sbAppend(transRow, usFormat.format(monthEnd)); // End of month entered
		sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
		sbAppend(transRow, "11010"); // This is the static AR GL Account Number
		sbAppend(transRow, dbLocation.getLocationNumber()); // Location Code
		// Multiple the amount times -1 to indicate a credit
		sbAppend(transRow, getAmountValue(DEBIT * dbHistory.getLHistAmount())); // Monetary Amount
		sbAppend(transRow, dbCase.getContractCode()); // Reference "Case Number"
		sbAppend(transRow, ""); // Line description 2 -- NOT USED
		sbAppend(transRow, getARPostingDate(dbHistory)); // Transaction Date
		sbAppend(transRow, dbHistory.getCHistDesc(), true); // Line Description

		AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
		return true;
	}

	private boolean exportTaxItem(DbHistory dbHistory, DbCase dbCase, DbLocation dbLocation, Date monthEnd,
			String fileName) {
		StringBuffer transRow = new StringBuffer();
		String exportFileDescription = "AR";

		sbAppend(transRow, exportFileDescription); // Transaction Type
		sbAppend(transRow, usFormat.format(monthEnd)); // End of month entered
		sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
		sbAppend(transRow, "20610"); // Static Sales and Use Tax GL Account Number
		sbAppend(transRow, dbLocation.getLocationNumber()); // Location Code
		// Multiple the amount times -1 to indicate a credit
		sbAppend(transRow, getAmountValue(CREDIT * dbHistory.getLHistAmount())); // Monetary Amount
		sbAppend(transRow, dbCase.getContractCode()); // Reference "Case Number"
		sbAppend(transRow, ""); // Line description 2 -- NOT USED
		sbAppend(transRow, getARPostingDate(dbHistory)); // Transaction Date
		sbAppend(transRow, dbHistory.getCHistDesc(), true); // Line Description

		AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());

		transRow = new StringBuffer();
		sbAppend(transRow, exportFileDescription); // Transaction Type
		sbAppend(transRow, usFormat.format(monthEnd)); // End of month entered
		sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
		sbAppend(transRow, "11010"); // This is the static AR GL Account Number
		sbAppend(transRow, dbLocation.getLocationNumber()); // Location Code
		// Multiple the amount times -1 to indicate a credit
		sbAppend(transRow, getAmountValue(DEBIT * dbHistory.getLHistAmount())); // Monetary Amount
		sbAppend(transRow, dbCase.getContractCode()); // Reference "Case Number"
		sbAppend(transRow, ""); // Line description 2 -- NOT USED
		sbAppend(transRow, getARPostingDate(dbHistory)); // Transaction Date
		sbAppend(transRow, dbHistory.getCHistDesc(), true); // Line Description

		AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
		return true;
	}

	
	
	
	private boolean exportPaymentItem(DbHistory dbHistory, DbCase dbCase, DbLocation dbLocation, Date monthEnd,
			String fileName) {
		StringBuffer transRow = new StringBuffer();
		String exportFileDescription = "AR";

		sbAppend(transRow, exportFileDescription); // Transaction Type
		sbAppend(transRow, usFormat.format(monthEnd)); // End of month entered
		sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
		sbAppend(transRow, dbHistory.getCHistGLAcct()); // Static Sales and Use Tax GL Account Number
		sbAppend(transRow, dbLocation.getLocationNumber()); // Location Code
		// Multiple the amount times -1 to indicate a credit
		sbAppend(transRow, getAmountValue(CREDIT * dbHistory.getLHistAmount())); // Monetary Amount
		sbAppend(transRow, dbCase.getContractCode()); // Reference "Case Number"
		sbAppend(transRow, ""); // Line description 2 -- NOT USED
		sbAppend(transRow, getARPostingDate(dbHistory)); // Transaction Date
		sbAppend(transRow, dbHistory.getCHistDesc(), true); // Line Description

		AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());

		transRow = new StringBuffer();
		sbAppend(transRow, exportFileDescription); // Transaction Type
		sbAppend(transRow, usFormat.format(monthEnd)); // End of month entered
		sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
		sbAppend(transRow, "11010"); // This is the static AR GL Account Number
		sbAppend(transRow, dbLocation.getLocationNumber()); // Location Code
		// Multiple the amount times -1 to indicate a credit
		sbAppend(transRow, getAmountValue(DEBIT * dbHistory.getLHistAmount())); // Monetary Amount
		sbAppend(transRow, dbCase.getContractCode()); // Reference "Case Number"
		sbAppend(transRow, ""); // Line description 2 -- NOT USED
		sbAppend(transRow, getARPostingDate(dbHistory)); // Transaction Date
		sbAppend(transRow, dbHistory.getCHistDesc(), true); // Line Description

		AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
		return true;
	}

	
	
	
	public boolean writeAdjustment(DbHistory dbHistory, String fileName, int ACCOUNTING_INTERFACE, DbUser dbUser,
      AccountingInterfaceDTO accountingInterfaceDTO) {
		return writeARTran(dbHistory, dbUser, accountingInterfaceDTO.getDateMonthEnd(), fileName);
	}

	public boolean writeMiscCashReceipt(DbHistory dbHistory, String fileName, int ACCOUNTING_INTERFACE, DbUser dbUser,
      AccountingInterfaceDTO accountingInterfaceDTO) {
		boolean result = true;
		StringBuffer transRow = null;
		String exportFileDescription = null;
		DatabaseTransaction t = null;
		DbLocation dbLocation = null;
		String referenceNumber = null;
		String monthEndDate = null;
		int locationCode = dbHistory.getLocationId();

		try {
			try {
				t = (DatabaseTransaction) DatabaseTransaction.getTransaction(dbUser);
				dbLocation = FdmsDb.getInstance().getLocation(t, locationCode);
				
				transRow = new StringBuffer();
				exportFileDescription = "GL";
				
				Date ref = new Date();
				referenceNumber = String.valueOf(ref.getTime());
				
				Calendar endDate = Calendar.getInstance();
				endDate.setTimeInMillis(dbHistory.getCHistDate().getTime());
				int day = endDate.getActualMaximum(Calendar.DAY_OF_MONTH);
				
					// Set the end of the month date.
				endDate.set(Calendar.DAY_OF_MONTH, day);
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
				monthEndDate = formatter.format(endDate.getTime());
				
				sbAppend(transRow, exportFileDescription); // Transaction Type
				sbAppend(transRow, monthEndDate); // End of month entered
				sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
				sbAppend(transRow, dbHistory.getCHistGLAcct()); // 
				sbAppend(transRow, dbLocation.getLocationNumber()); // Location Code
				// Multiple the amount times -1 to indicate a credit
				sbAppend(transRow, getAmountValue(CREDIT * dbHistory.getLHistAmount())); // Monetary Amount
				sbAppend(transRow, referenceNumber); // Reference "Case Number"
				sbAppend(transRow, ""); // Line description 2 -- NOT USED
				sbAppend(transRow, usFormatMMDDYYYY.format(dbHistory.getCHistDate())); // Transaction Date
				sbAppend(transRow, dbHistory.getCHistDesc(), true); // Line Description

				AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());

				transRow = new StringBuffer();
				sbAppend(transRow, exportFileDescription); // Transaction Type
				sbAppend(transRow, monthEndDate); // End of month entered
				sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
				sbAppend(transRow, dbHistory.getCHistARacct()); // This is the static GL local deposit account
				sbAppend(transRow, dbLocation.getLocationNumber()); // Location Code
				// Multiple the amount times -1 to indicate a credit
				sbAppend(transRow, getAmountValue(DEBIT * dbHistory.getLHistAmount())); // Monetary Amount
				sbAppend(transRow, referenceNumber); // Reference "Case Number"
				sbAppend(transRow, ""); // Line description 2 -- NOT USED
				sbAppend(transRow, usFormatMMDDYYYY.format(dbHistory.getCHistDate())); // Transaction Date
				sbAppend(transRow, dbHistory.getCHistDesc(), true); // Line Description

				AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
				result = true;
			} catch (PersistenceException e) {
				logger.error("InterfaceAccounting.WriteSalesTran found invalid data at hist# " + dbHistory.getId()
						+ ". Persistence Exception:", e);
				result = false;
			} finally {
				if (t != null) {
					t.closeConnection();
					t = null;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in writeSummaryTran() : ", e);
		}

		return result;

	}

	public boolean writeApExpense(DbApDistributionHistory checkHistory, String fileName, int ACCOUNTING_INTERFACE, DbUser dbUser,
      AccountingInterfaceDTO accountingInterfaceDTO) {
		String exportFileDescription = "AP";
		String locationCode = "";
		LocationDAO locationDAO = new LocationDAO(dbUser);
		ApMasterDAO apMasterDAO = new ApMasterDAO(dbUser);
		ApVendorDAO apVendorDAO = new ApVendorDAO(dbUser);
		ApMasterDTO apMaster = null;
		ApVendorDTO apVenderDTO = null;
		LocationDTO location = null;
		StringBuffer transRow = null;


		// We are not going to process line item records.  We are only interested in totals.
		if (checkHistory.getType().equalsIgnoreCase(DbApDistributionHistory.INVOICE_ITEM)) {
				// Because this is a line item we will return true.
			return true;
		}

		try {
			apMaster = apMasterDAO.getApMaster(checkHistory.getApMasterID());
			if (apMaster == null) {
				return false;
			}

			location = locationDAO.getLocation(apMaster.getLocationID());
			if (location != null) {
				locationCode = location.getLocationNumber();
			}

			apVenderDTO = apVendorDAO.getApVendor(apMaster.getVendorID());
			if (apVenderDTO == null) {
				//return false;
				apVenderDTO = new  ApVendorDTO();
				apVenderDTO.setVendorCode("NoCode("+apMaster.getVendorID()+")");
				if (apMaster.getVendorName().length()> 0) {
					apVenderDTO.setName(apMaster.getVendorName());
				}else {
					apVenderDTO.setName("NoName("+apMaster.getVendorID()+")");
				}
			}

			Date endOfMonthDate = accountingInterfaceDTO.getDateMonthEnd();
			transRow = new StringBuffer();
				// Check to see if a discount was applied 
			// This applies to checkwriter checks that are not check items
			
			if ( (checkHistory.getType().compareTo(DbApDistributionHistory.INVOICE) == 0) && (checkHistory.getApAccountNumber().compareToIgnoreCase("40090") != 0)) {
				//if (checkHistory.getDiscountAmount() == 0) {
				//if (checkHistory.getApAccountNumber().compareToIgnoreCase("40090") != 0) { // do only the check not the discount
						// No discount
				
				// we are doing this for the check
				if (checkHistory.getAmount() > 0) {
					sbAppend(transRow, exportFileDescription); // Transaction Type
					sbAppend(transRow, usFormat.format(endOfMonthDate)); // End of Month Date
					sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
					sbAppend(transRow, "10040"); // This is the static Cash GL Account Number
					sbAppend(transRow, locationCode); // Location Code
					sbAppend(transRow, getAmountValue(CREDIT * checkHistory.getAmount())); // Monetary Amount
					sbAppend(transRow, apVenderDTO.getVendorCode()); // Reference "Vendor or Case Number"
					sbAppend(transRow, String.valueOf(apMaster.getCheckNumber())); // Line description 2
					sbAppend(transRow, usFormatMMDDYYYY.format(apMaster.getCheckDate())); // Transaction Date
				// Ticket :: 5635  FPG wants to display the vendor info from apmaster table, not from apvendors
 					//sbAppend(transRow, apVenderDTO.getName(), true); // Line Description
					sbAppend(transRow, apMaster.getVendorName(), true); // Line Description
					
					AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
					
					transRow = new StringBuffer();
					sbAppend(transRow, exportFileDescription); // Transaction Type
					sbAppend(transRow, usFormat.format(endOfMonthDate)); // End of Month Date
					sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
					sbAppend(transRow, "20010"); // This is the static AP GL Account Number
					sbAppend(transRow, locationCode); // Location Code
					sbAppend(transRow, getAmountValue(DEBIT * checkHistory.getAmount())); // Monetary Amount
					sbAppend(transRow, apVenderDTO.getVendorCode()); // Reference "Vendor or Case Number"
					sbAppend(transRow, String.valueOf(apMaster.getCheckNumber())); // Line description 2
					sbAppend(transRow, usFormatMMDDYYYY.format(apMaster.getCheckDate())); // Transaction Date
				// Ticket :: 5635  FPG wants to display the vendor info from apmaster table, not from apvendors
 					//sbAppend(transRow, apVenderDTO.getName(), true); // Line Description
					sbAppend(transRow, apMaster.getVendorName(), true); // Line Description
		
					AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
				}
				else { // we are doing the voided check.
					ApDistributionHistoryDAO apDistributionHistoryDAO = new ApDistributionHistoryDAO(dbUser);
					ArrayList <ApDistributionHistoryDTO> apDistributionHistories = new ArrayList <ApDistributionHistoryDTO> ();
					apDistributionHistories = apDistributionHistoryDAO.getDistributionHistory(checkHistory.getApMasterID());
					for (ApDistributionHistoryDTO record : apDistributionHistories) {
						if (record.getAmount() < 0) {
							if ((record.getType().compareTo(DbApDistributionHistory.INVOICE) == 0) && (record.getApAccountNumber().compareToIgnoreCase("40090") != 0)) {
								transRow = new StringBuffer();
								sbAppend(transRow, exportFileDescription); // Transaction Type
								sbAppend(transRow, usFormat.format(endOfMonthDate)); // End of Month Date
								sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
								sbAppend(transRow, "10040"); // This is the static Cash GL Account Number
								sbAppend(transRow, locationCode); // Location Code
								sbAppend(transRow, getAmountValue(CREDIT * record.getAmount())); // Monetary Amount
								sbAppend(transRow, apVenderDTO.getVendorCode()); // Reference "Vendor or Case Number"
								sbAppend(transRow, String.valueOf(apMaster.getCheckNumber())); // Line description 2
								sbAppend(transRow, usFormatMMDDYYYY.format(apMaster.getCheckDate())); // Transaction Date
							// Ticket :: 5635  FPG wants to display the vendor info from apmaster table, not from apvendors
			 					//sbAppend(transRow, apVenderDTO.getName(), true); // Line Description
								sbAppend(transRow, apMaster.getVendorName(), true); // Line Description
					
								AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
							} else if ((record.getType().compareTo(DbApDistributionHistory.INVOICE) == 0) && (record.getApAccountNumber().compareToIgnoreCase("40090") == 0)) {
								transRow = new StringBuffer();
								sbAppend(transRow, exportFileDescription); // Transaction Type
								sbAppend(transRow, usFormat.format(endOfMonthDate)); // End of Month Date
								sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
								sbAppend(transRow, "40090"); // This is the static AP GL Account Number
								sbAppend(transRow, locationCode); // Location Code
								sbAppend(transRow, getAmountValue(CREDIT * record.getAmount())); // Monetary Amount
								sbAppend(transRow, apVenderDTO.getVendorCode()); // Reference "Vendor or Case Number"
								sbAppend(transRow, String.valueOf(apMaster.getCheckNumber())); // Line description 2
								sbAppend(transRow, usFormatMMDDYYYY.format(apMaster.getCheckDate())); // Transaction Date
								// Ticket :: 5635  FPG wants to display the vendor info from apmaster table, not from apvendors
			 					//sbAppend(transRow, apVenderDTO.getName(), true); // Line Description
								sbAppend(transRow, apMaster.getVendorName(), true); // Line Description
					
								AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
							}else {
								transRow = new StringBuffer();
								sbAppend(transRow, exportFileDescription); // Transaction Type
								sbAppend(transRow, usFormat.format(endOfMonthDate)); // End of Month Date
								sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
								sbAppend(transRow, record.getApAccountNumber()); // This is the static AP GL Account Number
								sbAppend(transRow, locationCode); // Location Code
								sbAppend(transRow, getAmountValue(DEBIT * record.getAmount())); // Monetary Amount
								sbAppend(transRow, apVenderDTO.getVendorCode()); // Reference "Vendor or Case Number"
								sbAppend(transRow, String.valueOf(apMaster.getCheckNumber())); // Line description 2
								sbAppend(transRow, usFormatMMDDYYYY.format(apMaster.getCheckDate())); // Transaction Date
							// Ticket :: 5635  FPG wants to display the vendor info from apmaster table, not from apvendors
			 					//sbAppend(transRow, apVenderDTO.getName(), true); // Line Description
								sbAppend(transRow, apMaster.getVendorName(), true); // Line Description
					
								AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
							}
						}
					}
				}
						// The following else applies to only invoice items.
				//} 
				
//				else {
//						// discount applied
//					transRow = new StringBuffer();
//					sbAppend(transRow, exportFileDescription); // Transaction Type
//					sbAppend(transRow, usFormat.format(endOfMonthDate)); // End of Month Date
//					sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
//					sbAppend(transRow, "40090"); // This is the static AP GL Account Number
//					sbAppend(transRow, locationCode); // Location Code
//					sbAppend(transRow, getAmountValue(CREDIT * checkHistory.getDiscountAmount())); 
//					sbAppend(transRow, apVenderDTO.getVendorCode()); // Reference "Vendor or Case Number"
//					sbAppend(transRow, String.valueOf(apMaster.getCheckNumber())); // Line description 2
//					sbAppend(transRow, usFormatMMDDYYYY.format(apMaster.getCheckDate())); // Transaction Date
//					sbAppend(transRow, apVenderDTO.getName(), true); // Line Description
//	
//					AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
//					
//					transRow = new StringBuffer();
//					sbAppend(transRow, exportFileDescription); // Transaction Type
//					sbAppend(transRow, usFormat.format(endOfMonthDate)); // End of Month Date
//					sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
//					sbAppend(transRow, "10040"); // This is the static AP GL Account Number
//					sbAppend(transRow, locationCode); // Location Code
//					sbAppend(transRow, getAmountValue(CREDIT * (checkHistory.getAmount() - checkHistory.getDiscountAmount()))); 
//					sbAppend(transRow, apVenderDTO.getVendorCode()); // Reference "Vendor or Case Number"
//					sbAppend(transRow, String.valueOf(apMaster.getCheckNumber())); // Line description 2
//					sbAppend(transRow, usFormatMMDDYYYY.format(apMaster.getCheckDate())); // Transaction Date
//					sbAppend(transRow, apVenderDTO.getName(), true); // Line Description
//	
//					AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
//				}
				
					// Check to see how we are going to record the check writer check or the invoice 
				  // payment
					// This is only for check items
	
					// This is an AP payment

		  }	
		
		
			if (checkHistory.getType().compareTo(DbApDistributionHistory.CHECK_ITEM) == 0) {
				 // This is an AP payment
				transRow = new StringBuffer();
				sbAppend(transRow, exportFileDescription); // Transaction Type
				sbAppend(transRow, usFormat.format(endOfMonthDate)); // End of Month Date
				sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
				sbAppend(transRow, checkHistory.getApAccountNumber()); // This is the AP GL Account Number
				sbAppend(transRow, locationCode); // Location Code
				sbAppend(transRow, getAmountValue(DEBIT * checkHistory.getAmount())); // Monetary Amount
				sbAppend(transRow, apVenderDTO.getVendorCode()); // Reference "Vendor or Case Number"
				sbAppend(transRow, String.valueOf(apMaster.getCheckNumber())); // Line description 2
				sbAppend(transRow, usFormatMMDDYYYY.format(apMaster.getCheckDate())); // Transaction Date
			// Ticket :: 5635  FPG wants to display the vendor info from apmaster table, not from apvendors
				//sbAppend(transRow, apVenderDTO.getName(), true); // Line Description
				sbAppend(transRow, apMaster.getVendorName(), true); // Line Description
	
				AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
					// The following else if only for invoice records.
			} else if (checkHistory.getType().compareTo(DbApDistributionHistory.CHECK) == 0){
				// No discount
				transRow = new StringBuffer();
				sbAppend(transRow, exportFileDescription); // Transaction Type
				sbAppend(transRow, usFormat.format(endOfMonthDate)); // End of Month Date
				sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
				sbAppend(transRow, "10040"); // This is the static Cash GL Account Number
				sbAppend(transRow, locationCode); // Location Code
				sbAppend(transRow, getAmountValue(CREDIT * checkHistory.getAmount())); // Monetary Amount
				sbAppend(transRow, apVenderDTO.getVendorCode()); // Reference "Vendor or Case Number"
				sbAppend(transRow, String.valueOf(apMaster.getCheckNumber())); // Line description 2
				sbAppend(transRow, usFormatMMDDYYYY.format(apMaster.getCheckDate())); // Transaction Date
			// Ticket :: 5635  FPG wants to display the vendor info from apmaster table, not from apvendors
				//sbAppend(transRow, apVenderDTO.getName(), true); // Line Description
				sbAppend(transRow, apMaster.getVendorName(), true); // Line Description
	
				AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
			}
		} catch (Exception e) {
			logger.error("Exception in writeSummaryTran() : ", e);
		}

		return true;
	}
	
	public boolean writeApInvoiceExpense(DbInvoiceTransHist invoiceTransHist,String fileName,int ACCOUNTING_INTERFACE,DbUser dbUser,AccountingInterfaceDTO accountingInterfaceDTO) {

		String exportFileDescription = "AP";
		String locationCode = "";
		ApVendorDAO apVendorDAO = new ApVendorDAO(dbUser);
		LocationDAO locationDAO = new LocationDAO(dbUser);
		InvoiceDAO invoiceDAO = new InvoiceDAO(dbUser);
		ApVendorDTO apVenderDTO = null;
		LocationDTO location = null;
		InvoiceDTO invoiceDTO = null;

		try {
			location = locationDAO.getLocation(invoiceTransHist.getLocationID());
			if (location != null) {
				locationCode = location.getLocationNumber();
			}
			
			invoiceDTO = invoiceDAO.getInvoice(invoiceTransHist.getInvoiceID());
			if (invoiceDTO == null) {
				return false;
			}
			
			apVenderDTO = apVendorDAO.getApVendor(invoiceDTO.getVendorID());
			if (apVenderDTO == null) {
				//return false;
				apVenderDTO = new  ApVendorDTO();
				apVenderDTO.setVendorCode("NoCode("+invoiceDTO.getVendorID()+")");
				apVenderDTO.setName("NoName("+invoiceDTO.getVendorID()+")");
			
			}

			if (invoiceTransHist.getType() == DbInvoiceTransHist.INVOICE_TYPE) {
				StringBuffer transRow = new StringBuffer();
				sbAppend(transRow, exportFileDescription); // Transaction Type
				sbAppend(transRow, usFormat.format(accountingInterfaceDTO.getDateMonthEnd())); // End of Month Date
				sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
				if (invoiceTransHist.getGlAccount().compareToIgnoreCase("40090") == 0) {
					// this is for discount
					sbAppend(transRow, invoiceTransHist.getGlAccount()); 
				} else {
					sbAppend(transRow, "20010"); // static GL Account Number
				}
				sbAppend(transRow, locationCode); // Location Code
				sbAppend(transRow, getAmountValue(CREDIT * invoiceTransHist.getAmount())); // Monetary Amount
				sbAppend(transRow, apVenderDTO.getVendorCode()); // Reference "Vendor or Case Number"
				sbAppend(transRow, invoiceDTO.getInvoiceNumber()); // Line description 2
				sbAppend(transRow, usFormatMMDDYYYY.format(invoiceTransHist.getTransactionDate())); // Transaction Date
				sbAppend(transRow, apVenderDTO.getName(), true); // Line Description

				AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
			} else { 
				StringBuffer transRow = new StringBuffer();
				sbAppend(transRow, exportFileDescription); // Transaction Type
				sbAppend(transRow, usFormat.format(accountingInterfaceDTO.getDateMonthEnd())); // End of Month Date
				sbAppend(transRow, String.valueOf(++lineNumber)); // Line Number
				sbAppend(transRow, invoiceTransHist.getGlAccount()); // static GL Account Number
				sbAppend(transRow, locationCode); // Location Code
				sbAppend(transRow, getAmountValue(DEBIT * invoiceTransHist.getAmount())); // Monetary Amount
				sbAppend(transRow, apVenderDTO.getVendorCode()); // Reference "Vendor or Case Number"
				sbAppend(transRow, invoiceDTO.getInvoiceNumber()); // Line description 2
				sbAppend(transRow, usFormatMMDDYYYY.format(invoiceTransHist.getTransactionDate())); // Transaction Date
				sbAppend(transRow, apVenderDTO.getName(), true); // Line Description

				AccountingInterfaceUtil.addTranToDisk(fileName, transRow.toString());
			}
		} catch (Exception e) {
			logger.error("Exception in writeSummaryTran() : ", e);
		}

		return true;
	}
	

	public boolean closeFile(String fileName) {

		usFormat = null;
		usFormatMMDDYYYY = null;
		currentDate = null;
//		yyyyMMddFormat = null;
		currentDate = null;

		return (true);
	}

	private void sbAppend(StringBuffer sb, String value) {
		sbAppend(sb, value, false);
	}

	private void sbAppend(StringBuffer sb, String value, boolean last) {

		if (value != null) {

			for (int i = 0; i < value.length(); i++) {
				char c = value.charAt(i);
				if (c != DELIMETER.charAt(0)) {
					sb.append(c);
				} else {
					sb.append(' ');
				}
			}
		}

		if (last) {
				// Linux is 0x0a  and windows is 0x0d 0x0a  
			//sb.append("\0x0d");
			sb.append("\r\n");
		} else {
			sb.append(DELIMETER);
		}
	}

	private String getAmountValue(double amount) {
		return (formatNumber(amount / 100.0));
	}

	private String formatNumber(double value) {
		DecimalFormat formatter = new DecimalFormat("0.00");
		return (formatter.format(value));
	}

	private Date getLastDayOfMonth(Date transDate) throws NullPointerException {

		Date tmpDate = new Date();
		Calendar cal = Calendar.getInstance();

		if (transDate == null) {
			throw new NullPointerException("Date passed cannot be null");
		}

		cal.setTime(transDate);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_YEAR, -(cal.get(Calendar.DAY_OF_MONTH)));

		tmpDate.setTime(cal.getTimeInMillis());

		return (tmpDate);
	}

	public static void main(String args[]) {

		Date date = new Date(Calendar.getInstance().getTimeInMillis());

		AccountingInterfacePeoplesoftKeystone acct = new AccountingInterfacePeoplesoftKeystone();
		acct.getLastDayOfMonth(date);

	}

}