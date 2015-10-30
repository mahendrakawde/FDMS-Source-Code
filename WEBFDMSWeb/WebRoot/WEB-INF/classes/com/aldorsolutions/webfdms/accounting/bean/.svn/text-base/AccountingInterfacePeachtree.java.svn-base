package com.aldorsolutions.webfdms.accounting.bean;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

import com.aldorsolutions.webfdms.accounting.model.AccountingInterfaceDTO;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbApDistribution;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistory;
import com.aldorsolutions.webfdms.beans.DbApMaster;
import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbFinancialSummary;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbInvoiceTransHist;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.DbVitalsInformant;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.InventorySold;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;


/**
 * Workfile: AccountingInterfacePeachtree.java
 * Date: Nov 7, 2005 6:58:49 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class AccountingInterfacePeachtree implements AccountingInterface {
	
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
        filename.append(".csv");
        
        // Construct the file FILE
        filepath.append(filename);
        
        return filepath.toString();
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
        DbCase histcase = null;
		DbVitalsFirstCall firstcall = null;
		DbFinancialSummary finan = null;
    	
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
    	
	        int numTrans = accountingInterfaceDTO.getSalesTranList().size() + 1; // plus one for summary transaction
	        writeTran(
	        		accountingInterfaceDTO.getFileName(), 
	        		accountingInterfaceDTO.getTranDate(), 
	        		accountingInterfaceDTO.getTransactionReference(), 
	        		accountingInterfaceDTO.getArAcct(), 
	        		description, 
	        		accountingInterfaceDTO.getSummaryTotal(), 
	        		accountingInterfaceDTO.getGroupId(), 
	                numTrans);
	        
	        java.util.Iterator mylist = accountingInterfaceDTO.getSalesTranList().iterator();
	        while (mylist.hasNext()){
	            hist = (DbHistory)mylist.next();
	            histcase = FdmsDb.getInstance().getCase(t, hist.getLMainKey());
	            firstcall = FdmsDb.getInstance().getVitalsFirstCall(t, hist.getLMainKey());
	            finan = FdmsDb.getInstance().getFinancial(t, hist.getLMainKey());
	            
				// Calculate default GL account for display purposes only at this point
				// GL will be recaclulated when and if saving financial page.
				// If DbCharge object already has a GL account then use it instead of the default.
				if (hist.getCHistGLAcct() == null
						|| hist.getCHistGLAcct().compareTo("        ") <= 0) {
					InventorySold soldData = new InventorySold();
					FdmsDb.getInstance().getDefaultGlSalesAccts(t, soldData, dbUser.getRegion(),
							histcase.getChapelNumber(), String.valueOf(hist.getCHistItemType()),
							finan.getCFinSaleType(), firstcall.getDispositionCode());
					hist.setCHistGLAcct(soldData.getAcctRevenue());
				}
      
	            writeTran(
	            		accountingInterfaceDTO.getFileName(), 
	            		accountingInterfaceDTO.getTranDate(), 
	            		accountingInterfaceDTO.getTransactionReference(), 
	                    hist.getCHistGLAcct(), 
	                    hist.getCHistDesc(), 
	                    -hist.getLHistAmount(), 
	                    accountingInterfaceDTO.getGroupId(),
	                    numTrans);
	        }
        
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
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(dbUser);
            // check if need to get AR account from location
            if (accountingInterfaceDTO.getArAcct() == null){
                histcase = FdmsDb.getInstance().getCase(t, dbHistory.getLMainKey());
                location = FdmsDb.getInstance().getLocation(t, histcase.getChapelNumber());
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
            
            accountingInterfaceDTO.getSalesTranList().add(dbHistory);
            accountingInterfaceDTO.setSummaryTotal(
            		dbHistory.getLHistAmount() + accountingInterfaceDTO.getSummaryTotal());
            
            
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
        DbCase  caseinfo = null;
        DbVitalsDeceased vitals = null;
        String  refcode=null;
        DatabaseTransaction t = null;
        String  aracct = null;
        DbVitalsInformant inform = null;
        String custname = null;
        int chapelId = 0;
        
		DbVitalsFirstCall firstcall = null;
		DbFinancialSummary finan = null;       
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(dbUser);
            // get AR account from location
            caseinfo = FdmsDb.getInstance().getCase(t, dbHistory.getLMainKey());
            chapelId = caseinfo.getChapelNumber();
            location = FdmsDb.getInstance().getLocation(t, chapelId);
            inform = FdmsDb.getInstance().getVitalsInformant(t, dbHistory.getLMainKey());
            vitals = FdmsDb.getInstance().getVitalsDeceased(t, dbHistory.getLMainKey());
            
            if (vitals != null && inform != null){
                custname = vitals.getDecFullName();
            } else {
                custname = "CASEID:"+String.valueOf(caseinfo.getCaseCode());
                result = false;
                logger.debug("InterfaceAccounting: unable to fetch vitals for: "+custname);
            }
            // make reference code
            // use either case code or contract code
            if (caseinfo!=null && caseinfo.getCaseCode().compareTo(" ")>0){
                refcode = caseinfo.getCaseCode();
            } else if (caseinfo!=null){
                refcode = caseinfo.getContractCode();
            } else refcode = "CASEID"+String.valueOf(dbHistory.getLMainKey());
            
            // Determine A/R account
            if (location!=null){
                aracct = location.getArAcct();
            } else{
                aracct = "BadLoc#";
            }	
            
            firstcall = FdmsDb.getInstance().getVitalsFirstCall(t, dbHistory.getLMainKey());
            finan = FdmsDb.getInstance().getFinancial(t, dbHistory.getLMainKey());
			// Calculate default GL account for display purposes only at this point
			// GL will be recaclulated when and if saving financial page.
			// If DbCharge object already has a GL account then use it instead of the default.
			if (dbHistory.getCHistGLAcct() == null
					|| dbHistory.getCHistGLAcct().compareTo("        ") <= 0) {
				InventorySold soldData = new InventorySold();
				FdmsDb.getInstance().getDefaultGlSalesAccts(t, soldData, dbUser.getRegion(),
						chapelId, String.valueOf(dbHistory.getCHistItemType()),
						finan.getCFinSaleType(), firstcall.getDispositionCode());
				dbHistory.setCHistGLAcct(soldData.getAcctRevenue());
			}           
            
            // Write credit to sales account
            writeTran(fileName, 
                      dbHistory.getCHistDate(), 
                      refcode, 
                      dbHistory.getCHistGLAcct(), 
                      dbHistory.getCHistDesc(), 
                      -dbHistory.getLHistAmount(), 
                      dbHistory.getLMainKey(), 
                      2);
            
            // Write debit side unless this is a "C" or "I" transaction type
            if (dbHistory.getCHistSPF()!='C' && dbHistory.getCHistSPF()!='I'){
                writeTran(
                		fileName, 
                        dbHistory.getCHistDate(), 
                        refcode, 
                        aracct, 
                        dbHistory.getCHistDesc(), 
                        dbHistory.getLHistAmount(), 
                        dbHistory.getLMainKey(), 
                        2);
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
        //DbLocation location = null;
        String refcode = null;
        DatabaseTransaction t = null;
        //String cashacct = null;
                 
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(dbUser);
            // get AR account from location
            //location = FdmsDb.getInstance().getLocation(t, dbHistory.getLocationId());
            //if (location != null){
            //    cashacct = location.getCashAcct();
            //} else {
            //    logger.debug("InterfaceAccounting.writeMiscCashReceipt, Invalid Location ID for hist transaction#"+dbHistory.getId());
            //    cashacct = "BadLoc#";
            //}		
            
            // Write credit to sales account
                    
            
            writeTran(
            		fileName, 
                    dbHistory.getCHistDate(), 
                    refcode, 
                    dbHistory.getCHistGLAcct(), 
                    dbHistory.getCHistDesc(), 
                    -dbHistory.getLHistAmount(), 
                    dbHistory.getLocationId(), 
                    2);
            // Write debit side to cash account
            writeTran(
            		fileName, 
                    dbHistory.getCHistDate(), 
                    refcode, 
                    dbHistory.getCHistARacct(), 
                    dbHistory.getCHistDesc(), 
                    dbHistory.getLHistAmount(), 
                    dbHistory.getLocationId(), 
                    2);          
            
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
        DbApAccount acct = null;
        DatabaseTransaction t = null;
        String cashacct = null;
        String expacct = null;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(dbUser);
            // get check master
            apmaster = FdmsDb.getInstance().getApCheck(t, checkHistory.getApMasterID());
            if (apmaster == null){
                //AppLog.error("InterfaceAccounting.writeApExpense, Invalid Ap Master ID for detail transaction#"+check.getId());
                return false;
            }
            
            // get CASH account from location
            location = FdmsDb.getInstance().getLocation(t, apmaster.getLocationID());
            // Save case account
            if (location !=null){
                cashacct = location.getCashAcct();
            } else {
                cashacct ="BadLoc#";
                //AppLog.error("InterfaceAccounting.writeApExpense, Invalid Location ID for detail transaction#"+check.getId());
            }
// comment this one out because it is the right value of apaccountnumber.            
//            // get expense account record
//            acct = FdmsDb.getInstance().getApAccount(t, Integer.valueOf(checkHistory.getApAccountNumber()));
//            if (acct != null){
//                expacct = acct.getAccountNumber();
//            } else {
//                expacct = "BadAcct#";
//                //AppLog.error("InterfaceAccounting.writeApExpense, Invalid Acct ID for detail transaction#"+check.getId());
//            }
            if (checkHistory.getApAccountNumber().length() > 0) {
            	expacct = checkHistory.getApAccountNumber();
            }
            else {
            	expacct = "BadAcct#";
            }
            
            
            // Write debit to expense account
            writeTran(
            		fileName, 
                    apmaster.getCheckDate(), 
                    String.valueOf(apmaster.getCheckNumber()), 
                    expacct, 
                    checkHistory.getMemo(), 
                    checkHistory.getAmount(), 
                    apmaster.getLocationID(), 
                    2);
            // Write credit side to cash account
            writeTran(
            		fileName, 
                    apmaster.getCheckDate(), 
                    String.valueOf(apmaster.getCheckNumber()), 
                    cashacct, 
                    apmaster.getMemo(), 
                    -checkHistory.getAmount(), 
                    apmaster.getLocationID(), 
                    2);
            
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
	

	public boolean closeFile(String fileName) {
		return true;
	}
	
	private boolean writeTran( 
			String fileName, 
            java.util.Date sDate, 
            String sRef, 
            String sGlacct, 
            String sDescr, 
            int sAmount, 
            int sTranID, 
            int sNumDist) {
		
        StringBuffer gltran = new StringBuffer();
        String amount = null;
        // Format amount from integer
        NumberFormat numform;
        DecimalFormat decform = null;
        numform = NumberFormat.getCurrencyInstance(); // can specify locale like: Locale.US
        decform = (DecimalFormat)numform;
        decform.applyPattern("#0.##; -#0.##");
        amount = decform.format((double)sAmount/100.0);
        /*
        // Transaction Date
        gltran.append(FormatDate.convertDateToMM_DD_YYYY(sDate));
        gltran.append(",");
        // Reference
        gltran.append(sRef);
        gltran.append(",");
        //Date clear in bank rec (not used)
        gltran.append(",");
        //Number of ditributions
        gltran.append(sNumDist);
        gltran.append(",");
        // GL account
        gltran.append(sGlacct);
        gltran.append(",");
        // Description
        gltran.append(sDescr);
        gltran.append(",");
        // Tran amount
        gltran.append(amount);
        gltran.append(",");
        // Job ID
        gltran.append(",");
        // Reimburable Expense
        gltran.append(",");
        // Transaction period
        gltran.append(",");
        // Transaction Number (not used on import)
        */
        
        sbAppend (gltran, FormatDate.convertDateToMM_DD_YYYY(sDate) ); // Transaction Date
        sbAppend (gltran, sRef ); // Reference
        sbAppend (gltran, "" ); // Date clear in bank rec (not used)
        sbAppend (gltran, Integer.toString(sNumDist) ); //Number of ditributions
        sbAppend (gltran, sGlacct); // GL account
        sbAppend (gltran, sDescr); // Description
        sbAppend (gltran, amount); // Tran amount
        sbAppend (gltran, ""); // Job ID
        sbAppend (gltran, ""); // Reimburable Expense
        sbAppend (gltran, ""); // Transaction period
        sbAppend (gltran, "", true);// Transaction Number (not used on import)
        
        // Write to disk file
        return AccountingInterfaceUtil.addTranToDiskFile(fileName, gltran.toString());		
	}

	private void sbAppend ( StringBuffer sb, String value ) {
		sbAppend ( sb, value, false );
	}
	
	private void sbAppend ( StringBuffer sb, String value, boolean last ) {
		
		if ( value != null ) {
			
			for ( int i = 0; i < value.length(); i++ ) {
				char c = value.charAt(i);
				if ( c != ',' ) {
					sb.append(c);
				}
				else {
					sb.append(' ');
				}
			}
		} 
		
		if ( last ) {
			sb.append("");
		} else {
			sb.append(",");
		}
	}
}
