package com.aldorsolutions.webfdms.accounting.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

import com.aldorsolutions.webfdms.accounting.dao.AccountingInterfaceDAO;
import com.aldorsolutions.webfdms.accounting.model.AccountingInterfaceDTO;
import com.aldorsolutions.webfdms.accounting.model.CustomerDTO;
import com.aldorsolutions.webfdms.accounting.model.DeceasedDTO;
import com.aldorsolutions.webfdms.accounting.model.ItemDTO;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistory;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbInvoiceTransHist;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;


public class AccountingInterfaceACCPAC implements AccountingInterface {
	
	final private static Logger logger = Logger.getLogger(AccountingInterfaceACCPAC.class.getName());
	
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
        filename.append(".xml");
        
        // Construct the file FILE
        filepath.append(filename);
        
        return filepath.toString();
    }
    
    
    
	
	public boolean initializeExport(String intFile, int intType) {
		return AccountingInterfaceUtil.addTranToDiskFile(intFile, "<Transactions>\n");
	}	
	
    public boolean writeSummaryTran(
    		DbUser dbUser,
    		AccountingInterfaceDTO accountingInterfaceDTO,
			ActionErrors errors) {
    	
    	try {
    		AccountingInterfaceDAO accountingInterfaceDAO = new AccountingInterfaceDAO();
			DeceasedDTO deceasedDTO = accountingInterfaceDAO.getDeceased(
					dbUser.getDbLookup(), accountingInterfaceDTO.getCaseId());
			
			// start transaction set for vitals case
			AccountingInterfaceUtil.addTranToDiskFile(
					accountingInterfaceDTO.getFileName(), "<Transaction>\n");
			
			if (deceasedDTO == null) deceasedDTO = new DeceasedDTO(); 
			AccountingInterfaceUtil.addTranToDiskFile(accountingInterfaceDTO
					.getFileName(), deceasedDTO.toXML());
				
			CustomerDTO customerDTO = accountingInterfaceDAO.getCustomerACCPAC(
					dbUser.getDbLookup(), 
					accountingInterfaceDTO.getCaseId());
			
			if (customerDTO == null) {
				customerDTO = new CustomerDTO();
				customerDTO.setVitalsId(accountingInterfaceDTO.getCaseId());
			}
			
			AccountingInterfaceUtil.addTranToDiskFile(accountingInterfaceDTO
					.getFileName(), customerDTO.toXMLOfAccPac());
			
			ArrayList items = accountingInterfaceDAO.getTransactions(
					dbUser.getDbLookup(), 
					accountingInterfaceDTO.getCaseId());
			
			if (items != null) {
				Iterator it = items.iterator();
				while (it.hasNext()) {
					ItemDTO itemDTO = (ItemDTO) it.next();
					AccountingInterfaceUtil.addTranToDiskFile(
							accountingInterfaceDTO.getFileName(), itemDTO.toXML());
					accountingInterfaceDAO.postTransaction(
							itemDTO.getTranHistId(), 
							dbUser.getDbLookup());
				}
			}
			
			// end transaction set for vitals case
			AccountingInterfaceUtil.addTranToDiskFile(
					accountingInterfaceDTO.getFileName(), "</Transaction>\n");
    	} catch (Exception e) {
    		logger.error("Exception in writeSummaryTran() : ", e);
    	}
		
    	return true;
    }
    
	public boolean writeSalesTran(
			DbHistory dbHistory,
			DbUser dbUser,
			AccountingInterfaceDTO accountingInterfaceDTO) {
		return true;
	}
	
	public boolean writeAdjustment(
			DbHistory hist, 
			String csvFileName, 
			int ACCOUNTING_INTERFACE, 
			DbUser dbUser,
      AccountingInterfaceDTO accountingInterfaceDTO) {
		return true;
	}
	
	public boolean writeMiscCashReceipt(
            DbHistory hist,
            String fileName,
            int ACCOUNTING_INTERFACE,
            DbUser dbUser,
            AccountingInterfaceDTO accountingInterfaceDTO) {
		return true;
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
		return AccountingInterfaceUtil.addTranToDiskFile(fileName, "</Transactions>\n");
	}
	
}
