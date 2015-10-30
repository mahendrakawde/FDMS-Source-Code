package com.aldorsolutions.webfdms.accounting.bean;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

import com.aldorsolutions.webfdms.accounting.model.AccountingInterfaceDTO;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistory;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbInvoiceTransHist;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;
import com.aldorsolutions.webfdms.util.FormatDate;


/**
 * Workfile: AccountingInterfacePeachtree.java
 * Date: Nov 7, 2005 6:58:49 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class AccountingInterfaceLawson implements AccountingInterface {
	
	final private static Logger logger = Logger.getLogger(AccountingInterfacePeachtree.class.getName());
	
	public String [] getInterfaceFileName(
    		int ACCOUNTING_INTERFACE, 
    		int locationSelected, 
    		DbUser dbUser) {
		
		String [] fileNames = new String [1]; 
		
		fileNames[0] = generateFileName(ACCOUNTING_INTERFACE, locationSelected, dbUser);
		return ( fileNames );
		
	}
	
    private String generateFileName(
    		int ACCOUNTING_INTERFACE, 
    		int locationSelected, 
    		DbUser dbUser) {
    	
        StringBuffer filename = new StringBuffer();
        StringBuffer filepath = new StringBuffer();
        // Get root directory for GL transaction file
        // filepath.append( getFileBaseDir(user));
        
        // Construct file name
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd_Hmmss");
        filename.append( formatter.format(new Date()));
        filename.append("_");
        // add location name to file name
        filename.append(AccountingInterfaceManagerBean.getLocationDescriptor(locationSelected, dbUser));
        
        filename.append(".");
        filename.append(locationSelected);
        filename.append(".");
        filename.append(ACCOUNTING_INTERFACE);
        filename.append(".txt");
        
        // Construct the file FILE
        filepath.append(filename);
        
        return filepath.toString();
    }	
    
    public String getDescription(DbCase caseinfo)
    {
        String description = null;

        if (caseinfo!=null && caseinfo.getCaseCode().compareTo(" ")>0){
            description = caseinfo.getCaseCode();
        } else if (caseinfo!=null){
            description = caseinfo.getContractCode();
        } else {
            description = "CASEID - caseInfo Missing";
        }
        
        return description;
    }
    
    public boolean writeSummaryTran(
            DbUser dbUser,
            AccountingInterfaceDTO accountingInterfaceDTO,
			ActionErrors errors) {
        
    	boolean result = true;
    	DatabaseTransaction t = null;
        String  aracct = null;
        DbLocation location = null;
        DbCase  caseinfo = null;
        int chapelId = 0;
        String description = null;

        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(dbUser);
            caseinfo = FdmsDb.getInstance().getCase(t, accountingInterfaceDTO.getCaseId());
            chapelId = caseinfo.getChapelNumber();
            location = FdmsDb.getInstance().getLocation(t, chapelId);

            // Determine A/R account
            if (location!=null){
                aracct = location.getArAcct();
            } else{
                aracct = "BadLoc#";
            }
            
            description = getDescription(caseinfo);
            description = description + "|Transaction Total";

            AccountingInterfaceManagerBean.exportFileSequenceNumber += 1;
            writeTran(
                accountingInterfaceDTO.getFileName(), 
                accountingInterfaceDTO.getTranDate(), 
                aracct,
                description, 
                accountingInterfaceDTO.getSummaryTotal(), 
                location.getDivision(),
                AccountingInterfaceManagerBean.exportFileSequenceNumber,
                "SJ",
                'A');
        
        } catch (PersistenceException e){
            description = e.toString();
            result = false;
        } finally {
        	if (t != null) {
        		t.closeConnection();
        		t = null;
        	}
        }	        
    	
    	return result;
    }
    

	public boolean writeSalesTran(
			DbHistory dbHistory,
			DbUser dbUser,
			AccountingInterfaceDTO accountingInterfaceDTO) {
		
	boolean result = true;
        DbCase caseinfo = null;
        DbLocation location = null;
        DbCase histcase = null;
        DatabaseTransaction t = null;
                
        try {
            t = (DatabaseTransaction) DatabaseTransaction.getTransaction(dbUser);
			histcase = FdmsDb.getInstance().getCase(t, dbHistory.getLMainKey());
			location = FdmsDb.getInstance().getLocation(t, histcase.getChapelNumber());
			// DO NOT PROCESS THIS TRANSACTION if case or location not found --
			// invalid data
			if (histcase == null || location == null) {
				return false;
			}
			caseinfo = FdmsDb.getInstance().getCase(t, accountingInterfaceDTO.getCaseId());
           
            // Check if need to save transaction date
            if (accountingInterfaceDTO.getTranDate()==null){
            	accountingInterfaceDTO.setTranDate(dbHistory.getCHistDate());
            }
            
            accountingInterfaceDTO.getSalesTranList().add(dbHistory);
            accountingInterfaceDTO.setSummaryTotal(
            		dbHistory.getLHistAmount() + accountingInterfaceDTO.getSummaryTotal());
            
            
            AccountingInterfaceManagerBean.exportFileSequenceNumber += 1;
            writeTran(
                accountingInterfaceDTO.getFileName(), 
                accountingInterfaceDTO.getTranDate(), 
                dbHistory.getCHistGLAcct(), 
                getDescription(caseinfo) + "|" + (dbHistory.getCHistDesc()),  
                -dbHistory.getLHistAmount(), 
                location.getDivision(),
                AccountingInterfaceManagerBean.exportFileSequenceNumber,
                "SJ",
                'A');
            
            String itemCategory = dbHistory.getCHistItemType();
            if ("C".equals(itemCategory) && dbHistory.getCHistSPF() == DbHistory.HIST_SPF_SALE.charAt(0)) {
                AccountingInterfaceManagerBean.exportFileSequenceNumber += 1;
                writeTran(
                    accountingInterfaceDTO.getFileName(), 
                    accountingInterfaceDTO.getTranDate(), 
                    dbHistory.getCHistGLAcct(), 
                    getDescription(caseinfo) + "|" + (dbHistory.getCHistDesc()),  
                    100, 
                    location.getDivision(),
                    AccountingInterfaceManagerBean.exportFileSequenceNumber,
                    "SJ",
                    'U');
            }
            else if ("V".equals(itemCategory) && dbHistory.getCHistSPF() == DbHistory.HIST_SPF_SALE.charAt(0)) {
                AccountingInterfaceManagerBean.exportFileSequenceNumber += 1;
                writeTran(
                    accountingInterfaceDTO.getFileName(), 
                    accountingInterfaceDTO.getTranDate(), 
                    "93300000", 
                    getDescription(caseinfo) + "|" + (dbHistory.getCHistDesc()),  
                    100, 
                    location.getDivision(),
                    AccountingInterfaceManagerBean.exportFileSequenceNumber,
                    "SJ",
                    'U');
            }
            else if ("S".equals(itemCategory) && AccountingInterfaceManagerBean.serviceUnitPostedForCase == false) {
                AccountingInterfaceManagerBean.serviceUnitPostedForCase=true;
                writeTran(
                    accountingInterfaceDTO.getFileName(), 
                    accountingInterfaceDTO.getTranDate(), 
                    dbHistory.getCHistGLAcct(), 
                    getDescription(caseinfo) + "|Services",  
                    100, 
                    location.getDivision(),
                    AccountingInterfaceManagerBean.exportFileSequenceNumber,
                    "SJ",
                    'U');
            }
            
            
        } catch (PersistenceException e){
            logger.error("InterfaceAccounting.WriteSalesTran found invalid data at hist# " 
            		+ dbHistory.getId() + ". Persistence Exception:", e);
            result = false;
        } finally {
        	if (t != null) {
        		t.closeConnection();
        		t = null;
        	}
        }	
		
		return result;
	}
    
	/**
	 * Do nothing for Peach Tree interface
	 */
	public boolean initializeExport(String intFile, int intType) {
                AccountingInterfaceManagerBean.exportFileSequenceNumber = 0;
		return true;
	}	
	
	public boolean writeAdjustment(
			DbHistory dbHistory, 
			String fileName, 
			int ACCOUNTING_INTERFACE, 
			DbUser dbUser,
      AccountingInterfaceDTO accountingInterfaceDTO) {
		
	boolean result = true;
        DbLocation location = null;
        DatabaseTransaction t = null;
        String  aracct = null;
        String  cashacct = null;
        String finacct = null;
        String discacct = null;
        DbCase  caseinfo = null;
        int chapelId = 0;
        String debitAccount = null;
        String creditAccount = null;
        String unitAccount = null;
        String saleType = null;        
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(dbUser);
            
            // get AR account from location
            caseinfo = FdmsDb.getInstance().getCase(t, dbHistory.getLMainKey());
            chapelId = caseinfo.getChapelNumber();
            location = FdmsDb.getInstance().getLocation(t, chapelId);
            
            // Determine A/R account
            if (location!=null){
                aracct = location.getArAcct();
                cashacct = location.getCashAcct();
                finacct = location.getFinanceChargeAcct();  
                discacct = location.getDiscountAcct();
            } else{
                aracct = "BadLoc#";
                cashacct = "BadLoc#";
                finacct = "BadLoc#";
            }		
                        
            char SPFCode = dbHistory.getCHistSPF();
            
            if (SPFCode == DbHistory.HIST_SPF_PAYMENT.charAt(0) || 
                SPFCode == DbHistory.HIST_SPF_VOIDCNTRT.charAt(0)) {
                    creditAccount = cashacct;
                    debitAccount = aracct;
                    saleType = "CR";
            }
            else if (SPFCode == DbHistory.HIST_SPF_FINCHRG.charAt(0)) {
                    creditAccount = aracct;
                    debitAccount = finacct;
                    saleType = "SJ";
            }
            else if (SPFCode == DbHistory.HIST_SPF_COSTSALE.charAt(0)) {
                    creditAccount = null;
                    debitAccount = dbHistory.getCHistGLAcct();
                    saleType = "SJ";
            }
            else if (SPFCode == DbHistory.HIST_SPF_INVASSET.charAt(0)) {
                    creditAccount = dbHistory.getCHistGLAcct();
                    debitAccount = null;
                    saleType = "SJ";
            }
            else if (SPFCode == DbHistory.HIST_SPF_PRICEADJ.charAt(0)) {
                    creditAccount = discacct;
                    debitAccount = aracct;
                    saleType = "SJ";
            }
            else if (SPFCode == DbHistory.HIST_SPF_SALE.charAt(0)) {
                    if ("V".equals(dbHistory.getCHistItemType()))
                        unitAccount = "93300000";
                    else if ("S".equals(dbHistory.getCHistItemType()))
                        unitAccount = null;
                    else
                        unitAccount = dbHistory.getCHistGLAcct();
                    creditAccount = dbHistory.getCHistGLAcct();
                    debitAccount = null;
                    saleType = "SJ";
            }
            else {
                    creditAccount = null;
                    debitAccount = null;
            }
            
            if (creditAccount != null) {
                AccountingInterfaceManagerBean.exportFileSequenceNumber += 1;
                writeTran(
                     fileName, 
                     dbHistory.getCHistDate(), 
                     creditAccount, 
                     getDescription(caseinfo) + "|" + (dbHistory.getCHistDesc()), 
                     -dbHistory.getLHistAmount(), 
                     location.getDivision(),
                     AccountingInterfaceManagerBean.exportFileSequenceNumber,
                     saleType,
                     'A');
            }
            if (debitAccount != null) {
                AccountingInterfaceManagerBean.exportFileSequenceNumber += 1;
                writeTran(
                        fileName, 
                        dbHistory.getCHistDate(), 
                        debitAccount, 
                        getDescription(caseinfo) + "|" + (dbHistory.getCHistDesc()), 
                        dbHistory.getLHistAmount(), 
                        location.getDivision(),
                        AccountingInterfaceManagerBean.exportFileSequenceNumber,
                        saleType,
                        'A');
            }
            if (unitAccount != null) {
                AccountingInterfaceManagerBean.exportFileSequenceNumber += 1;
                writeTran(
                    fileName, 
                    dbHistory.getCHistDate(), 
                    unitAccount, 
                    getDescription(caseinfo) + "|" + (dbHistory.getCHistDesc()),  
                    100, 
                    location.getDivision(),
                    AccountingInterfaceManagerBean.exportFileSequenceNumber,
                    "SJ",
                    'U');
            }
        } catch (Exception e) {
        	logger.error("Exception in writeAdjustment() : ", e);
        	result = false;
        } finally {
        	if (t != null) {
        		t.closeConnection();
        		t = null;
        	}
        }        
            return result;
	}
	
	public boolean writeMiscCashReceipt(
            DbHistory dbHistory,
            String fileName,
            int ACCOUNTING_INTERFACE,
            DbUser dbUser,
            AccountingInterfaceDTO accountingInterfaceDTO) {
		
		boolean result = false;
        DbLocation location = null;
        DatabaseTransaction t = null;
        String cashacct = null;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(dbUser);
            // get AR account from location
            location = FdmsDb.getInstance().getLocation(t, dbHistory.getLocationId());
            if (location != null){
                cashacct = location.getCashAcct();
            } else {
                logger.debug("InterfaceAccounting.writeMiscCashReceipt, Invalid Location ID for hist transaction#"+dbHistory.getId());
                cashacct = "BadLoc#";
            }		
            
            AccountingInterfaceManagerBean.exportFileSequenceNumber += 1;
            // Write credit to sales account
            writeTran(
                        fileName, 
                        dbHistory.getCHistDate(), 
                        cashacct, 
                        dbHistory.getCHistDesc(), 
                        dbHistory.getLHistAmount(), 
                        location.getDivision(),
                        AccountingInterfaceManagerBean.exportFileSequenceNumber,
                        "CR",
                        'A');        
            
        } catch (PersistenceException e){
        	result = false;
        } finally {
        	if (t != null) {
        		t.closeConnection();
        		t = null;
        	}
        }
		
		return result;
	}
            
            
	public boolean writeApExpense(
            DbApDistributionHistory check,
            String fileName,
            int ACCOUNTING_INTERFACE,
            DbUser dbUser,
            AccountingInterfaceDTO accountingInterfaceDTO) {
            return true;
        }
	
	
	public boolean writeApInvoiceExpense(
			DbInvoiceTransHist invoiceTransHist,
      String fileName,
      int ACCOUNTING_INTERFACE,
      DbUser dbUser,
			AccountingInterfaceDTO accountingInterfaceDTO) {
		return true;
	}
	

	public boolean closeFile(String fileName) {
		return true;
	}
	
	public static boolean writeTran(
            String fileName, 
            java.util.Date sDate, 
            String sGlacct, 
            String sDescr, 
            int sAmount, 
            String companyNumber,
            int transactionNumber,
            String sourceCode,
            char tranType) {
        
        Date today = new Date();
        StringBuffer gltran = new StringBuffer();
        String amount = null;
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat)numform;
        decform.applyPattern("#0.00; -#0.00");
        amount = decform.format((double)sAmount/100.0);
        // what gets printed will be cropped
        String printedAmount = ("                " + amount);
        // used to set end points for crop
        int amountLength = printedAmount.length();
        // crop printed amount properly
        printedAmount = printedAmount.substring(amountLength - 16, amountLength);

        // fixed "FDMS" (length 12)
        gltran.append("FDMS        ");
        gltran.append("\t");
        // sequence number auto iterates (length 6)
        gltran.append((transactionNumber + "      ").substring(0,6));
        gltran.append("\t");
        // fixed company number (length 4)
        gltran.append("2000");
        gltran.append("\t");
        // old company number and company code (length 35)
        gltran.append((companyNumber + "                                   ").substring(0,35));
        gltran.append("\t");
        // 00 followed by glaccount (length 25)
        if (sGlacct == null)
            sGlacct = "";
        gltran.append(("00" + sGlacct + "                       ").substring(0,25));
        gltran.append("\t");
        // whether it is a cash receipt or a sale (length 2)
        gltran.append(sourceCode);
        gltran.append("\t");
        // date the transaction was created (length 8)
        gltran.append((FormatDate.convertDateToYYYYMMDD(sDate) + "        ").substring(0,8));
        gltran.append("\t");
        // reference, should be left blank (length 10)
        gltran.append("          ");
        gltran.append("\t");
        // supplier name departmental description (length 30)
        if (sDescr == null)
            sDescr = "";
        gltran.append((sDescr + "                              ").substring(0,30));
        gltran.append("\t");
        // transaction type defaults to "A" (length 1)
        gltran.append(tranType);
        gltran.append("\t");
        // curency type defaults to "USD" (length 5)
        gltran.append("USD  ");
        gltran.append("\t");
        // transaction amount (length 15.2)
        gltran.append(printedAmount);
        gltran.append("\t");
        // base amount same as transaction amount (length 15.2)
        gltran.append(printedAmount);
        gltran.append("\t");
        // exchange rate defaults to "1.000000" (length 12.6)
        gltran.append("     1.000000");
        gltran.append("\t");
        // fixed "FH" (length 2)
        gltran.append("FH");
        gltran.append("\t");
        // program code defaults to "FDMS" (length 5)
        gltran.append("FDMS ");
        gltran.append("\t");
        // auto reversal defaults to "N" (length 1)
        gltran.append("N");
        gltran.append("\t");
        // the date this was posted should be todays date (length 8)
        gltran.append(FormatDate.convertDateToYYYYMMDD(today));
        gltran.append("\t");
        // to base amount same as transaction amount (length 15.2)
        gltran.append(printedAmount);
          
        
        
        // Write to disk file
        return AccountingInterfaceUtil.addTranToDiskFile(fileName, gltran.toString());	
    }

	
}
