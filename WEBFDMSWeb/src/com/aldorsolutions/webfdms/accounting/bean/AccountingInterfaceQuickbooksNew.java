package com.aldorsolutions.webfdms.accounting.bean;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

import com.aldorsolutions.webfdms.accounting.model.AccountingInterfaceDTO;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbApDistribution;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistory;
import com.aldorsolutions.webfdms.beans.DbApMaster;
import com.aldorsolutions.webfdms.beans.DbApVendor;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbInvoiceTransHist;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsInformant;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;



/**
 * Workfile: AccountingInterfaceQuickbooksNew.java
 * Date: Nov 7, 2005 6:58:49 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class AccountingInterfaceQuickbooksNew implements AccountingInterface {
	
	final private static Logger logger = Logger.getLogger(AccountingInterfaceQuickbooksNew.class.getName());
	
	public String [] getInterfaceFileName(
    		int ACCOUNTING_INTERFACE, 
    		int locationSelected, 
    		DbUser dbUser) {
		
		String [] fileNames = new String [1]; 
		
		fileNames[0] = generateFileName(ACCOUNTING_INTERFACE, locationSelected, dbUser);
		return ( fileNames );
		
	}
	
    private String generateFileName(
    		int interfaceType, 
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
        filename.append(interfaceType);
        filename.append(".iif");
        
        // Construct the file FILE
        filepath.append(filename);
        
        return filepath.toString();
    }	
    
    
	public boolean initializeExport(String fileName, int intType) {
		boolean result = false;
		StringBuffer sb = new StringBuffer();

        // build string for TRNSaction line
        sb.append("!TRNS" + "\t");
        sb.append("TRNSID" +"\t");
        sb.append("TRNSTYPE" +"\t");
        sb.append("DATE" +"\t");
        sb.append("ACCNT" +"\t");
        sb.append("NAME" +"\t");
        sb.append("AMOUNT" +"\t");
        sb.append("DOCNUM" +"\t");
        sb.append("MEMO" +"\t");
        sb.append("CLEAR" +"\t");
        sb.append("TOPRINT" +"\t");
        sb.append("ADDR1" +"\t");
        sb.append("ADDR2" +"\t");
        sb.append("ADDR3" +"\t");
        sb.append("ADDR4" +"\t");
        sb.append("DUEDATE" +"\t");
        sb.append("CLASS" + "\t");

        result = AccountingInterfaceUtil.addTranToDiskFile(fileName, sb.toString());

        if (!result) return result;
        
        sb = new StringBuffer();
        // build string for SPLit line
        sb.append("!SPL" +"\t");
        sb.append("SPLID" +"\t");
        sb.append("TRNSTYPE" +"\t");
        sb.append("DATE" +"\t");
        sb.append("ACCNT" +"\t");
        sb.append("NAME" +"\t");
        sb.append("AMOUNT" +"\t");
        sb.append("DOCNUM" +"\t");
        sb.append("MEMO" +"\t");
        sb.append("CLEAR" +"\t");
        sb.append("QNTY" +"\t");
        sb.append("PRICE" +"\t");
        sb.append("INVITEM" +"\t");
        sb.append("TAXABLE" +"\t");
        sb.append("EXTRA" +"\t");
        sb.append("CLASS" + "\t");
		
		return AccountingInterfaceUtil.addTranToDiskFile(fileName, sb.toString());
	}	
	
    public boolean writeSummaryTran(
    		DbUser dbUser,
    		AccountingInterfaceDTO accountingInterfaceDTO,
			ActionErrors errors) {
    	
    	boolean result = true;
    	DatabaseTransaction t = null;
        DbVitalsDeceased vitals = null;
        DbVitalsInformant inform = null;
        String description = null;
        DbHistory hist = null;
        String taxable = null;
        String extra = null;    	
    	
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(dbUser);
            vitals = FdmsDb.getInstance().getVitalsDeceased(t, accountingInterfaceDTO.getCaseId());
            inform = FdmsDb.getInstance().getVitalsInformant(t, accountingInterfaceDTO.getCaseId());
            if (vitals!=null && inform !=null){
                description = vitals.getDecFullName();
            } else {
                description = "CASEID"+String.valueOf(accountingInterfaceDTO.getCaseId());
                result = false;
            }
    	
            ArrayList <DbHistory> cogs = new ArrayList <DbHistory> ();
            int numTrans =  accountingInterfaceDTO.getSalesTranList().size() + 1; // plus one for summary transaction
            int transid = 0;
            boolean firstCycle = true;
            java.util.Iterator mylist = accountingInterfaceDTO.getSalesTranList().iterator();
            
            while (mylist.hasNext()){
                hist = (DbHistory)mylist.next();
                String locationCode = "";
                DbLocation location = getLocation(t, dbUser, hist.getLocationId());
                
                if (location!=null){
                    if ( location.getLocationNumber() != null && location.getLocationNumber().trim().length() > 0 ) {
                        locationCode = location.getLocationNumber();	
                    } else {
                    	locationCode = Integer.toString( location.getId() );
                    }
                }
                
                if (firstCycle){
                    // write debit to A/R group header record
                    transid = (hist.getId()*10)-1;
                                    
                    writeInvTran(
                    	accountingInterfaceDTO.getFileName(),
                        transid,
                        accountingInterfaceDTO.getTranDate(),
                        accountingInterfaceDTO.getTransactionReference(),
                        accountingInterfaceDTO.getArAcct(),
                        description,
                        accountingInterfaceDTO.getSummaryTotal(),
                        description,
                        numTrans,
                        inform,
                        accountingInterfaceDTO.getTranDate(),
                        hist.getLocationId(),
                        locationCode,
                        accountingInterfaceDTO.getInterfaceType());
                    firstCycle=false;                
                }
            
	            // COGS and Inventory transactions must be handled separately
	            if (hist.getCHistSPF()=='C' || hist.getCHistSPF()=='I'){
	                cogs.add(hist);
	            } else {
	                // handle sale transactions
	                if (hist.getCHistTaxCode().compareTo("   ") > 0)
	                    taxable = "Y";
	                else
	                    taxable = "N";
	                
	                // Check if this is a TAX charge
	                if (hist.getIHistType()==99 || hist.getIHistType()==98
	                        || (hist.getCHistDesc().indexOf("Tax")>0) || (hist.getCHistDesc().indexOf("tax")>0)){
	                    extra = "AUTOSTAX";
	                } else {
	                    extra = " ";
	                }
	                transid = hist.getId()*10;
	                	
	                writeInvTranSpl(
	                	accountingInterfaceDTO.getFileName(),
	                    transid,
	                    accountingInterfaceDTO.getTranDate(),
	                    accountingInterfaceDTO.getTransactionReference(),
	                    hist.getCHistGLAcct(),
	                    hist.getCHistDesc(),
	                    hist.getLHistAmount(),
	                    description,
	                    hist.getCHistInvItemName(),
	                    taxable,
	                    extra,
	                    hist.getLocationId(),
	                    locationCode,
	                    accountingInterfaceDTO.getInterfaceType());
	            }	            	            
            }
            
            writeEndTran(accountingInterfaceDTO.getFileName()); 
        
	        // Now write General Journal entries for Inventory transactions
	        mylist = cogs.iterator();
	        while (mylist.hasNext()){
	            hist = (DbHistory)mylist.next();

                String locationCode = "";
                DbLocation location = getLocation(t, dbUser, hist.getLocationId());
                
                if (location!=null){
                    if ( location.getLocationNumber() != null && location.getLocationNumber().trim().length() > 0 ) {
                        locationCode = location.getLocationNumber();	
                    } else {
                    	locationCode = Integer.toString( location.getId() );
                    }
                }
                
	            if (hist.getCHistSPF()=='C'){
	                // For "Cost of sale" record, write 1/2 of a journal entry with TRNS record
	                // "I" transaction must be next for the last half SPL record
	                transid = hist.getId()*10;
	                writeIIFTran(
	                		accountingInterfaceDTO.getFileName(),
	                        transid,
	                        "TRNS",
	                        "General Journal",
	                        hist.getCHistDate(),
	                        accountingInterfaceDTO.getTransactionReference(),
	                        hist.getCHistGLAcct(),
	                        hist.getCHistDesc(),
	                        -hist.getLHistAmount(),
	                        description,
	                        " ",
	                        " ",
	                        " ",
	                        " ",
	                        hist.getLocationId(),
	                        locationCode,
	                        accountingInterfaceDTO.getInterfaceType());
	            } else if (hist.getCHistSPF()=='I'){
	                transid = hist.getId()*10;
	                writeIIFTran(
	                		accountingInterfaceDTO.getFileName(),
	                        transid,
	                        "SPL",
	                        "General Journal",
	                        hist.getCHistDate(),
	                        accountingInterfaceDTO.getTransactionReference(),
	                        hist.getCHistGLAcct(),
	                        hist.getCHistDesc(),
	                        -hist.getLHistAmount(),
	                        description,
	                        " ",
	                        " ",
	                        " ",
	                        " ",
	                        hist.getLocationId(),
	                        locationCode,
	                        accountingInterfaceDTO.getInterfaceType());
	                writeEndTran(accountingInterfaceDTO.getFileName());
	            }
	        }
        
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
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(dbUser);
            // check if need to get AR account from location
            if (accountingInterfaceDTO.getArAcct() == null){
                histcase = FdmsDb.getInstance().getCase(t, dbHistory.getLMainKey());
                location = getLocation(t, dbUser, histcase.getChapelNumber());
                	
                // DO NOT PROCESS THIS TRANSACTION if case or location not found -- invalid data
                if (histcase == null || location == null){
                    //AppLog.error("InterfaceAccounting.WriteSalesTran found invalid data at hist#"+hist.getId());
                    return false;
                }
                accountingInterfaceDTO.setArAcct(location.getArAcct());
            }
            
            // check if need to save reference
            if (accountingInterfaceDTO.getTransactionReference() == null){
                // use either case code or contract code
                caseinfo = FdmsDb.getInstance().getCase(t, accountingInterfaceDTO.getCaseId());
                int icontractno = FormatNumber.parseInteger(caseinfo.getContractCode());
                if (icontractno < 1) icontractno = caseinfo.getId();
                accountingInterfaceDTO.setContractNumber(icontractno);
                accountingInterfaceDTO.setCaseCode(caseinfo.getCaseCode());
                
                if (caseinfo!=null && caseinfo.getCaseCode().compareTo(" ")>0){
                	accountingInterfaceDTO.setTransactionReference(caseinfo.getCaseCode());
                } else if (caseinfo!=null){
                	accountingInterfaceDTO.setTransactionReference(caseinfo.getContractCode());
                } else accountingInterfaceDTO
                	.setTransactionReference("CASEID"+String.valueOf(accountingInterfaceDTO.getCaseId()));
            }
            // Check if need to save transaction date
            if (accountingInterfaceDTO.getTranDate()==null){
            	accountingInterfaceDTO.setTranDate(dbHistory.getCHistDate());
            }
            
            // Accumulate details because need to write header record first then these detail transactions.
            accountingInterfaceDTO.getSalesTranList().add(dbHistory);
            accountingInterfaceDTO.setSummaryTotal(dbHistory.getLHistAmount() + 
            		accountingInterfaceDTO.getSummaryTotal());
            
            
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
	
	public boolean writeAdjustment(
			DbHistory dbHistory, 
			String fileName, 
			int ACCOUNTING_INTERFACE, 
			DbUser dbUser,
      AccountingInterfaceDTO accountingInterfaceDTO) {
		
		boolean result = true;
        DbLocation location = null;
        DbCase  caseinfo = null;
        DbVitalsDeceased vitals = null;
        String  refcode=null;
        DatabaseTransaction t = null;
        String  aracct = null;
        DbVitalsInformant inform = null;
        String taxable = null;
        String extra = null;
        String custname = null;
        String locationCode = "";
        int chapelId = 0;
        int transactionID = 0;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(dbUser);
            // get AR account from location
            caseinfo = FdmsDb.getInstance().getCase(t, dbHistory.getLMainKey());
            chapelId = caseinfo.getChapelNumber();
            location = getLocation(t, dbUser, chapelId );
            inform = FdmsDb.getInstance().getVitalsInformant(t, dbHistory.getLMainKey());
            vitals = FdmsDb.getInstance().getVitalsDeceased(t, dbHistory.getLMainKey());
            
            if (vitals != null && inform != null){
                custname = vitals.getDecFullName();
            } else {
                custname = "CASEID:"+String.valueOf(caseinfo.getCaseCode());
                result = false;
                //AppLog.error("InterfaceAccounting: unable to fetch vitals for: "+hist.getLMainKey());
            }
            // make reference code
            // use either case code or contract code
            if (caseinfo!=null && caseinfo.getCaseCode().compareTo(" ")>0){
                refcode = caseinfo.getCaseCode();
            } else if (caseinfo!=null){
                refcode = caseinfo.getContractCode();
            } else {
            	refcode = "CASEID"+String.valueOf(dbHistory.getLMainKey());
            }
            
            // Determine A/R account
            if (location!=null){
                aracct = location.getArAcct();
                if ( location.getLocationNumber() != null && location.getLocationNumber().trim().length() > 0 ) {
                    locationCode = location.getLocationNumber();	
                } else {
                	locationCode = Integer.toString( location.getId() );
                }
            } else{
                aracct = "BadLoc#";
            }		
            
            // For QuickBooks, we need to determine what kind of transaction since they
            // are handled differently
            if (dbHistory.getCHistSPF()=='S' || dbHistory.getCHistSPF()=='D' || dbHistory.getCHistSPF()=='F'){
                
                // Write an INVOICE adjustment
                if (dbHistory.getCHistTaxCode().compareTo("   ") > 0) taxable = "Y";
                else taxable = "N";
                
                // Check if this is a TAX charge
                if (dbHistory.getIHistType()==99 || dbHistory.getIHistType()==98
                        || (dbHistory.getCHistDesc().indexOf("Tax")>0) || (dbHistory.getCHistDesc().indexOf("tax")>0)){
                    extra = "AUTOSTAX";
                } else {
                    extra = " ";
                }
                
                transactionID = dbHistory.getId() * 10;

            	writeInvTran(
                        fileName,
                        transactionID,
                        dbHistory.getCHistDate(),
                        refcode,
                        aracct,
                        dbHistory.getCHistDesc(),
                        dbHistory.getLHistAmount(),
                        custname,
                        2,
                        inform,
                        dbHistory.getCHistDate(),
                        chapelId,
                        locationCode,
                        ACCOUNTING_INTERFACE
                        );
                writeInvTranSpl(
                		fileName,
                        transactionID+1,
                        dbHistory.getCHistDate(),
                        refcode,dbHistory.getCHistGLAcct(),
                        dbHistory.getCHistDesc(),
                        dbHistory.getLHistAmount(),
                        custname,
                        dbHistory.getCHistInvItemName(),
                        taxable,
                        extra,
                        chapelId,
                        locationCode,
                        ACCOUNTING_INTERFACE
                        );
                writeEndTran(fileName);
            } else if (dbHistory.getCHistSPF()=='C'){
                // For "Cost of sale" record, write 1/2 of a journal entry with TRNS record
                // "I" transaction must be next for the last half SPL record
                transactionID = dbHistory.getId()*10;
                
                writeIIFTran(	
                		fileName,
                     transactionID,
                     "TRNS",
                     "General Journal",
                     dbHistory.getCHistDate(),
                     refcode,
                     dbHistory.getCHistGLAcct(),
                     dbHistory.getCHistDesc(),
                     -dbHistory.getLHistAmount(),
                     custname,
                     " ",
                     " ",
                     " ",
                     " ",
                     chapelId,
                     locationCode,
                     ACCOUNTING_INTERFACE);	
                writeEndTran(fileName);
                
            } else if (dbHistory.getCHistSPF()=='I'){
                transactionID = dbHistory.getId()*10;                
                	
                writeIIFTran(
                        fileName,
                        transactionID,
                        "SPL",
                        "General Journal",
                        dbHistory.getCHistDate(),
                        refcode,
                        dbHistory.getCHistGLAcct(),
                        dbHistory.getCHistDesc(),
                        -dbHistory.getLHistAmount(),
                        custname,
                        " ",
                        " ",
                        " ",
                        " ",
                        chapelId,
                        locationCode,
                        ACCOUNTING_INTERFACE);
                
                writeEndTran(fileName);

            } else if (dbHistory.getCHistSPF()=='P'){
                transactionID = dbHistory.getId()*10;
                
                writeIIFTran(
                        fileName,
                        transactionID,
                        "TRNS",
                        "Payment",
                        dbHistory.getCHistDate(),
                        refcode,
                        dbHistory.getCHistGLAcct(),
                        dbHistory.getCHistDesc(),
                        -dbHistory.getLHistAmount(),
                        custname,
                        " ",
                        " ",
                        " ",
                        " ",
                        chapelId,
                        locationCode,
                        ACCOUNTING_INTERFACE);
                writeIIFTran(
                        fileName,
                        transactionID+1,
                        "SPL",
                        "Payment",
                        dbHistory.getCHistDate(),
                        refcode,
                        dbHistory.getCHistARacct(),
                        dbHistory.getCHistDesc(),
                        dbHistory.getLHistAmount(),
                        custname,
                        " ",
                        " ",
                        " ",
                        " ",
                        chapelId,
                        locationCode,
                        ACCOUNTING_INTERFACE);
                writeEndTran(fileName);
            }
            // skip anything else
            
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
        String refcode = null;
        DatabaseTransaction t = null;
        String cashacct = null;
        int transactionID = 0;
        String locationCode = "";
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(dbUser);
            // get AR account from location
            location = getLocation(t, dbUser, dbHistory.getLocationId() );
            
            if (location != null){
                cashacct = location.getCashAcct();
                if ( location.getLocationNumber() != null && location.getLocationNumber().trim().length() > 0 ) {
                    locationCode = location.getLocationNumber();	
                } else {
                	locationCode = Integer.toString( location.getId() );
                }
            } else {
                logger.debug("InterfaceAccounting.writeMiscCashReceipt, Invalid Location ID for hist transaction#"+dbHistory.getId());
                cashacct = "BadLoc#";
            }	
            
            if (dbHistory.getCHistARacct() != null || dbHistory.getCHistARacct().trim().length() > 0 ) {
            	cashacct = dbHistory.getCHistARacct();
            }
            
            // make reference code using receipt number
            refcode = Integer.toString(dbHistory.getLHistReceiptNo());
            
            // Write an INVOICE adjustment
            transactionID = dbHistory.getId()*10;
            	
        	writeIIFTran(
                    fileName,
                    transactionID,
                    "TRNS",
                    "General Journal",
                    dbHistory.getCHistDate(),
                    refcode,
                    cashacct,
                    dbHistory.getCHistDesc(),
                    dbHistory.getLHistAmount(),
                    "Misc Cash Receipt",
                    " ",
                    " ",
                    " ",
                    " ",
                    dbHistory.getLocationId(),
                    locationCode,
                    ACCOUNTING_INTERFACE);
            
        	writeIIFTran(
        			fileName,
                    transactionID+1,
                    "SPL",
                    "General Journal",
                    dbHistory.getCHistDate(),
                    refcode,
                    dbHistory.getCHistGLAcct(),
                    dbHistory.getCHistDesc(),
                    -dbHistory.getLHistAmount(),
                    "Misc Cash Receipt",
                    " ",
                    " ",
                    " ",
                    " ",
                    dbHistory.getLocationId(),
                    locationCode,
                    ACCOUNTING_INTERFACE);
            
            writeEndTran(fileName);
            
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
            DbApDistributionHistory checkHistory,
            String fileName,
            int ACCOUNTING_INTERFACE,
            DbUser dbUser,
            AccountingInterfaceDTO accountingInterfaceDTO) {
		
		boolean result = true;
		
        DbLocation location = null;
        DbApMaster apmaster = null;
        DbApVendor vendor = null;
        DbApAccount acct = null;
        DatabaseTransaction t = null;
        String cashacct = null;
        String expacct = null;
        String vendorName = null;
        int chapelId = 0;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(dbUser);

            // get check master
            apmaster = FdmsDb.getInstance().getApCheck(t, checkHistory.getApMasterID());
            if (apmaster == null){
                //AppLog.error("InterfaceAccounting.writeApExpense, Invalid Ap Master ID for detail transaction#"+check.getId());
                return false;
            }
            
            // get vendor
            vendor = FdmsDb.getInstance().getApVendor(t, apmaster.getVendorID());
            if (vendor==null){
                vendorName = "vendor ID not found:"+apmaster.getVendorID();
                //AppLog.error("InterfaceAccounting.writeApExpense, Invalid Vendor ID for detail transaction#"+check.getId());
            } else {
                vendorName = vendor.getName();
            }
            
            String locationCode = "";
            // get CASH account from location
            location = FdmsDb.getInstance().getLocation(t, apmaster.getLocationID());
            // Save case account
            if (location !=null){
                cashacct = location.getCashAcct();
                chapelId = location.getId();
                
                if ( location.getLocationNumber() != null && location.getLocationNumber().trim().length() > 0 ) {
                    locationCode = location.getLocationNumber();	
                } else {
                	locationCode = Integer.toString( location.getId() );
                }
            } else {
                cashacct ="BadLoc#";
                //AppLog.error("InterfaceAccounting.writeApExpense, Invalid Location ID for detail transaction#"+check.getId());
            }
            
            // get expense account record
            acct = FdmsDb.getInstance().getApAccount(t, Integer.valueOf(checkHistory.getApAccountNumber()));
            if (acct != null){
                expacct = acct.getAccountNumber();
            } else {
                expacct = "BadAcct#";
                //AppLog.error("InterfaceAccounting.writeApExpense, Invalid Acct ID for detail transaction#"+check.getId());
            }
            
            String refcode = Long.toString(apmaster.getCheckNumber());
            long transid = apmaster.getCheckNumber()*10;
            // write debit to expense account
            	
            writeIIFTran(
                    fileName,
                    transid,
                    "TRNS",
                    "Billpmt",
                    apmaster.getCheckDate(),
                    refcode,
                    expacct,
                    checkHistory.getMemo(),
                    checkHistory.getAmount(),
                    vendorName,
                    " ",
                    " ",
                    " ",
                    " ",
                    chapelId,
                    locationCode,
                    ACCOUNTING_INTERFACE);
            // Write credit side to cash account
            writeIIFTran(
            		fileName,
                    transid,
                    "SPL",
                    "Billpmt",
                    apmaster.getCheckDate(),
                    refcode,
                    cashacct,
                    apmaster.getMemo(),
                    -checkHistory.getAmount(),
                    vendorName,
                    " ",
                    " ",
                    " ",
                    " ",
                    chapelId,
                    locationCode,
                    ACCOUNTING_INTERFACE);
                
            
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
	
	public boolean writeApInvoiceExpense(
			DbInvoiceTransHist invoiceTransHist,
      String fileName,
      int ACCOUNTING_INTERFACE,
      DbUser dbUser,
			AccountingInterfaceDTO accountingInterfaceDTO) {
		return true;
	}
	

	/**
	 * 
	 * @param fileName
	 * @param transactionID
	 * @param sDate
	 * @param sRef
	 * @param sGlacct
	 * @param sDescr
	 * @param sAmount
	 * @param custname
	 * @param sNumDist
	 * @param inform
	 * @param duedate
	 * @param chapelId
	 * @param qbVersion
	 * @return
	 */
	private boolean writeInvTran(
            String fileName,
            int transactionID,
            java.util.Date sDate,
            String sRef,
            String sGlacct,
            String sDescr,
            int sAmount,
            String custname,
            int sNumDist,
            DbVitalsInformant inform,
            Date duedate,
            int chapelId,
            String locationCode,
            int qbVersion) {
        
        StringBuffer gltran = new StringBuffer();
        String amount = null;
        
        String informName = "C/O "+inform.getFname()+" "+inform.getLname();
        String informCity = inform.getCity()+", "+inform.getState()+" "+inform.getZip();
        
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat) numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double) sAmount / 100.0);
        // Identifiers
        gltran.append("TRNS\t");
        // Trans ID
        gltran.append(transactionID);
        gltran.append("\t");
        // Trans type
        gltran.append("Invoice\t");
        // Transaction Date
        gltran.append(FormatDate.convertDateToMM_DD_YYYY(sDate));
        gltran.append("\t");
        // GL account
        gltran.append(sGlacct);
        gltran.append("\t");
        // Deceased Name (Customer Name)
        gltran.append(custname);
        gltran.append("\t");
        // Tran amount
        gltran.append(amount);
        gltran.append("\t");
        // Doc Num
        gltran.append(sRef);
        gltran.append("\t");
        // memo
        gltran.append(sDescr);
        gltran.append("\t");
        //Clear
        gltran.append(" \t");
        //to print
        gltran.append("N\t");
        // addr1
        gltran.append(informName);
        gltran.append("\t");
        // addr2
        gltran.append(inform.getStreet());
        gltran.append("\t");
        // addr3
        gltran.append(" \t");
        // addr4
        gltran.append(informCity);
        gltran.append("\t");
        // due date
        gltran.append(FormatDate.convertDateToMM_DD_YYYY(duedate));
        gltran.append("\t");
        // chapel number
        gltran.append(locationCode);
        gltran.append("\t");
      //  logger.debug(gltran.toString());
        // Write to disk file        
        return AccountingInterfaceUtil.addTranToDiskFile(fileName, gltran.toString());
    }	
    
    /**
     * 
     * @param fileName
     * @param transactionID
     * @param sDate
     * @param sRef
     * @param sGlacct
     * @param sDescr
     * @param sAmount
     * @param custname
     * @param itemname
     * @param taxable
     * @param extratax
     * @param chapelId
     * @param qbVersion
     * @return
     */
    private boolean writeInvTranSpl(
            String fileName,
            int transactionID,
            java.util.Date sDate,
            String sRef,
            String sGlacct,
            String sDescr,
            int sAmount,
            String custname,
            String itemname,
            String taxable,
            String extratax,
            int chapelId,
            String locationCode,
            int qbVersion) {

        StringBuffer gltran = new StringBuffer();
        String amount = null;
        String price = null;
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat)numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double)-sAmount/100.0); // total?
        price =decform.format((double)sAmount/100.0);  // unit price?
        // Record Identifier
        gltran.append("SPL\t");
        // Trans ID
        gltran.append(transactionID);
        gltran.append("\t");
        // Trans type
        gltran.append("Invoice\t");
        // Transaction Date
        gltran.append(  FormatDate.convertDateToMM_DD_YYYY(sDate));
        gltran.append("\t");
        // GL account
        gltran.append(  sGlacct);
        gltran.append("\t");
        // Name
        gltran.append(  custname);
        gltran.append("\t");
        // Tran amount
        gltran.append(  amount);
        gltran.append("\t");
        // Doc Num
        gltran.append(  sRef);
        gltran.append("\t");
        // Memo
        gltran.append(  sDescr);
        gltran.append("\t");
        // Clear
        gltran.append(" \t");
        // Quantity
        gltran.append("-1\t");
        // Price
        gltran.append(  price);
        gltran.append("\t");
        // Item name
        gltran.append( itemname );
        gltran.append("\t");
        // Taxable
        gltran.append( taxable );
        gltran.append("\t");
        // Extra
        gltran.append( extratax );
        gltran.append("\t");
        // due date not used in SPL
        gltran.append(" \t");
        // chapel number
        gltran.append(locationCode);
        gltran.append("\t");
       // logger.debug(gltran.toString());
        return AccountingInterfaceUtil.addTranToDiskFile(fileName, gltran.toString());
    }    
    
    /**
     * 
     * @param fileName
     * @param transactionID
     * @param iifType
     * @param journal
     * @param sDate
     * @param sRef
     * @param sGlacct
     * @param sDescr
     * @param sAmount
     * @param custname
     * @param itemname
     * @param taxable
     * @param extratax
     * @param quantity
     * @param chapelId
     * @param qbVersion
     * @return
     */
    private boolean writeIIFTran(
            String fileName,
            long transactionID,
            String iifType,
            String journal,
            java.util.Date sDate,
            String sRef,
            String sGlacct,
            String sDescr,
            int sAmount,
            String custname,
            String itemname,
            String taxable,
            String extratax,
            String quantity,
            int chapelId,
            String locationCode,
            int qbVersion) {
        
        StringBuffer gltran = new StringBuffer();
        String amount = null;
        String price = null;
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat)numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double)sAmount/100.0); // total?
        price =decform.format((double)sAmount/100.0);  // unit price?
        // Record Identifier
        gltran.append(iifType);
        gltran.append("\t");
        // transaction ID
        gltran.append(transactionID);
        gltran.append("\t");
        // "General Journal" or "Invoice" or "Payment" etc.
        gltran.append(journal);
        gltran.append("\t");
        // Transaction Date
        gltran.append(  FormatDate.convertDateToMM_DD_YYYY(sDate));
        gltran.append("\t");
        // GL account
        gltran.append(  sGlacct);
        gltran.append("\t");
        // Name
        gltran.append(  custname);
        gltran.append("\t");
        // Tran amount
        gltran.append(  amount);
        gltran.append("\t");
        // Doc Num
        gltran.append(  sRef);
        gltran.append("\t");
        // Memo
        gltran.append(  sDescr);
        gltran.append("\t");
        // Clear
        gltran.append(" \t");
        // Quantity
        gltran.append(quantity);
        gltran.append("\t");
        // Price
        gltran.append(  price);
        gltran.append("\t");
        // Item name
        gltran.append( itemname );
        gltran.append("\t");
        // Taxable
        gltran.append( taxable );
        gltran.append("\t");
        // Extra
        gltran.append( extratax );
        gltran.append("\t");
        // due date not used in SPL
        gltran.append(" \t");
        // chapel number
        gltran.append(locationCode);
        gltran.append("\t");
     //   logger.debug(gltran.toString());
        // Write to disk file
        return AccountingInterfaceUtil.addTranToDiskFile(fileName, gltran.toString());
        
    }    
    
    private boolean writeEndTran(String intfile) {
        
        // Write to disk file
    	//logger.debug("ENDTRNS\t");
        return AccountingInterfaceUtil.addTranToDiskFile(intfile, "ENDTRNS\t");
    }    
	
	public boolean closeFile(String fileName) {
		return true;
	}
	
	private DbLocation getLocation (DatabaseTransaction t, DbUser user, int locationID) {
		
		DbLocation value = null;
		
		if ( locations == null ) {
			locations = FdmsDb.getInstance().getLocationsForCompany(t, user.getCompanyID());
		}
		
		for ( DbLocation tmpLocation : locations ) {
			
			if (  tmpLocation.getId() == locationID ) {
				value =  tmpLocation; 
				break;
			}
		}
		
		return ( value );
		
	}
	
	private DbLocation [] locations = null;
	
}
