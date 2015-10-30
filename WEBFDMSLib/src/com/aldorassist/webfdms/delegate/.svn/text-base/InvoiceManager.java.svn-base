	/**
 *  modify CJongs 030309
 */
package com.aldorassist.webfdms.delegate;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessages;

import com.aldorassist.webfdms.dao.InvoiceDAO;

import com.aldorassist.webfdms.dao.InvoiceItemDAO;
import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.InvoiceDTO;
import com.aldorassist.webfdms.model.InvoiceTransactionHistoryDTO;

import com.aldorassist.webfdms.model.InvoiceItemDTO;
import com.aldorassist.webfdms.model.LocationDTO;

import com.aldorassist.webfdms.model.notpersisted.InvoiceInventoryItemLinePO;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistory;
import com.aldorsolutions.webfdms.beans.DbApMaster;
import com.aldorsolutions.webfdms.beans.DbApVendor;
import com.aldorsolutions.webfdms.beans.DbBankAccount;
import com.aldorsolutions.webfdms.beans.DbInvoice;
import com.aldorsolutions.webfdms.beans.DbInvoiceTransHist;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.checkwriter.dao.BankAccountDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApMasterDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.checkwriter.model.BankAccountDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.CheckPrinter;
import com.aldorsolutions.webfdms.util.DateUtility;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

/**
 * @author David Rollins
 *
 */
public class InvoiceManager {

    private static Logger logger = Logger.getLogger(InvoiceManager.class.getName());
	
//	public static double getCostTotal (InvoiceInventoryItemLinePO item, double taxPercent) {
//		return (  InvoiceManager.calculateCostAmount( item.getUnitCost(), item.getItemQuant(), 
//						item.isTaxable(), taxPercent ) );
//	}
//	
//	public static double calculateCostAmount ( double cost, int quant, boolean taxable, double taxPrcnt ) {
//		double calc = cost * quant;
//		return calculateCostAmount ( calc, taxable, taxPrcnt );
//	}	
//	
//
//	public static double calculateCostAmount ( double totalCost, boolean taxable, double taxPrcnt ) {
//		double calc = totalCost;
//		
//		if ( taxable ) {
//			calc += calc * (taxPrcnt/100.0);
//		}
//		
//		calc = FormatNumber.roundDoubleDollars(calc);
//		return ( calc );
//	}	   
    
	public static double getCostTotal (InvoiceInventoryItemLinePO item) {
		return (  InvoiceManager.calculateCostAmount( item.getUnitCost(), item.getItemQuant() ) );
	}
	
	public static double calculateCostAmount ( double cost, int quant ) {
		double calc = cost * quant;
		return FormatNumber.roundDoubleDollars(calc);
	}	
	
	public static double calculateCostAmount ( double totalCost ) {
	double calc = totalCost;
	
	calc = FormatNumber.roundDoubleDollars(calc);
	return ( calc );
}	   	
	
	
	public boolean checkNumberAlreadyExists (DbUserSession user, int checkNumber) {
		
		boolean checkExists = false;
		DatabaseTransaction t = null;	
    	
        try {
            // Make sure this check number not already used
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);

            DbApMaster [] checks = FdmsDb.getInstance().getApCheckSet(t, user.getRegion(),0,checkNumber,true,null);
            if (checks.length>0){
            	checkExists = true;
            }
        } catch (Exception e) {
            logger.error("Error : ", e);
            checkExists = true;
        } finally {
            if (t != null) {
				t.closeConnection();
				t = null;
			}
        }
        
        return ( checkExists ); 
	}
	
	public DbApMaster createInvoiceCheck (DbUserSession user, InvoiceDTO invoice) throws Exception {
		DbApMaster newitem = null;
		DatabaseTransaction t = null;
		DbApVendor vendor = null;
        int newitemid = 0;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            ArrayList <InvoiceItemDTO> invItems = null;

            InvoiceItemDAO invoiceItemDAO = new InvoiceItemDAO(user);
            invItems = invoiceItemDAO.getInvoiceItems(invoice.getInvoiceID());
            
            DbLocation location = FdmsDb.getInstance().getLocation(t, invoice.getLocationID()); 
            if (location == null){
            	throw new PersistenceException("Unable to find location.");
            }
            
            String handGenCheck = "N";
            
            if ( invoice.getCheckingStatus() == InvoiceDTO.CHECK_STATUS_CHECK_GEN_MANUAL ) {
            	handGenCheck = "Y";
            }
            
            newitem = new DbApMaster();
            newitem.setNew();
            newitem.setInvoiceID(invoice.getInvoiceID());
            newitem.setVendorID(  invoice.getVendorID() );
            
            //add by chai
            newitem.setInvoiceNumber(invoice.getInvoiceNumber());
            newitem.setInvoiceDate(invoice.getInvoiceDate());
            newitem.setCheckNumber(invoice.getCheckNumber());
            //end chai
                        
            newitem.setBalance(   0);
            newitem.setCheckDate( new Date ( Calendar.getInstance().getTimeInMillis() ) );
            //newitem.setCheckNumber( invoice.getCheckNumber() );
            newitem.setDiscountAmount( FormatNumber.round(invoice.getDiscountAmount()) );
            newitem.setDueDate(   invoice.getInvoiceDueDate() );
            newitem.setHandwritten( handGenCheck );
            newitem.setLocaleID(  location.getLocaleNumber() );
            newitem.setLocationID(  location.getId() );
            newitem.setMemo( invoice.getDescription() );
            newitem.setUserID( user.getId() );
            
            t.addPersistent(newitem);
            t.save();
            t = null;
            newitemid = newitem.getId();

            // if successful then ID is > 0
            if (newitemid < 1){
                throw new PersistenceException("Unable to SAVE AP Master check information to DBMS.");
            }
            // Fetch AP master just saved to DBMS
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            newitem = FdmsDb.getInstance().getApCheck(t,newitemid);
            if (newitem==null){
                throw new PersistenceException("Unable to ACCESS AP Master check information from DBMS.");
            }
            
            
            double discountAmount = 0.0;
            	// Check to see if the discount flag is set
            discountAmount = newitem.calculateDiscountAmount(invoice, null);
            
            vendor = FdmsDb.getInstance().getApVendor(t, invoice.getVendorID() );
            
	      			// Record the DistributionHistory as a invoice transaction.  We are not going to 
	      			// record the discount because it will apply to the entire invoice.
	      		DbApDistributionHistory expenseHistory = new DbApDistributionHistory();
	      		expenseHistory.setNew();
	      		expenseHistory.setApMasterID(newitem.getId());
	      		//expenseHistory.setApAccountNumber(String.valueOf(vendor.getDefaultAcct()));
	      		
		    	
		    	DbApAccount account = FdmsDb.getInstance().getApAccount(t, vendor.getDefaultAcctID());

		    	if (account == null) {
		    		expenseHistory.setApAccountNumber("");
		    	}else {
		    		expenseHistory.setApAccountNumber(account.getAccountNumber() );
		    	}
	      		
	      		
	      		
	      		expenseHistory.setDiscountAmount(FormatNumber.round(discountAmount));
	      		expenseHistory.setType(DbApDistributionHistory.INVOICE);
	      		expenseHistory.setMemo(invoice.getDescription());


            // When we get here we are going to go thru every line item and report on it
            for ( InvoiceItemDTO item : invItems ) {
            	double itemCostWTax = calculateCostAmount(item.getItemCost());
            	itemCostWTax = FormatNumber.roundDoubleDollars(itemCostWTax);
            	
            	newitem.addDistribution((int)item.getApAccountID(), FormatNumber.round(itemCostWTax), 
            													item.getItemDesc(), DbApDistributionHistory.INVOICE_ITEM);
            }

	      		expenseHistory.setAmount(newitem.getInvoiceTotal());
	      		t.addPersistent(expenseHistory);
           

            //location.setNextCheckNumber(  invoice.getCheckNumber()+1 );
            location.setCashBalance(    location.getCashBalance() - FormatNumber.round(invoice.getAmountOfInvoice()) );
            
            newitem.setCheck1099( invoice.getInvoice1099Type() > 0 );

            if ( newitem.isCheck1099() ) {
            	newitem.setCheck1099Amount(FormatNumber.round(invoice.getInvoice1099Amount()));
            }
            
            CompanyOptionsManager coMgr = new CompanyOptionsManager ();
            int checkApprovalRqd = coMgr.getCompanyOptionValueForCompany(user.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_CHECK_APPROVAL_REQUIRED);
    		
            if ( checkApprovalRqd == 1 ) {
            	// Approval Required
            	double externalLimit = location.getExternalVendorLimit();
                double internalLimit = location.getInternalVendorLimit();
                 
                if ( user.isUserLimitOverride() ) {
                	externalLimit = user.getLimitExternalVendor();
                	internalLimit = user.getLimitInternalVendor(); 
                }
                 
                double limit = 0.0;
                if ( vendor.isInternalVendor() ) {
                	limit = internalLimit;
                } else {
                 	limit = externalLimit;
                }
                
                double currLimit = limit * 100; 
    			
    			if ( FormatNumber.round(invoice.getAmountOfInvoice()) <= currLimit ) {
    				newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_UNDER_APPROVAL_LIMIT);
    				
    				// set the check to be approved by the dashboard
    				if (newitem.getInvoiceNumber().length() > 0) {
    					newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_APPROVED);
    				}
    			} else {
    				
    				LocationDAO locDAO = new LocationDAO(user);
    		        ArrayList <LocationDTO> locations = locDAO.getLocationsByAccountant(user.getCompanyID(), user.getId());
    		        boolean locationFound = false;
    		        
    		        for ( LocationDTO locationDto : locations ) {
    		        	if ( locationDto.getLocationID() == invoice.getLocationID() ) {
    		        		locationFound = true;
    		        		break;
    		        	}
     		        }
    		        
    				// If the current user the accountant for this location the check is auto approved
    		        if ( locationFound ) {
    		        	newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_APPROVED);
    		        	newitem.setApprovedBy(user.getId());
    		        	newitem.setApprovedTmstmp(new Timestamp(Calendar.getInstance().getTimeInMillis()) );
    		        } else {
    		        	newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_PENDING);
    		        }
    		        
    		        // set the check to be approved by the dashboard
    		        if (newitem.getInvoiceNumber().length() > 0) {
    					newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_APPROVED);
    				}
    		        
    			}
    			
            } else {
            	newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_NOT_REQUIRED_BY_COMPANY);
            }
            
            // Store to DBMS
            t.save();

        } finally {
            if (t != null) {
				t.closeConnection();
				t = null;
			}
        }
        
        return (newitem);
	}
	
	public DbApMaster createInvoicesCheck (DbUserSession user, InvoiceDTO invoice, HashMap<String, ArrayList <String>> invoiceMap) throws Exception {
		DbApMaster newitem = null;
		DatabaseTransaction t = null;
		DbApVendor vendor = null;
        int newitemid = 0;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
 

 
            
            DbLocation location = FdmsDb.getInstance().getLocation(t, invoice.getLocationID()); 
            if (location == null){
            	throw new PersistenceException("Unable to find location.");
            }
            
            String handGenCheck = "N";
            
            if ( invoice.getCheckingStatus() == InvoiceDTO.CHECK_STATUS_CHECK_GEN_MANUAL ) {
            	handGenCheck = "Y";
            }
            
            newitem = new DbApMaster();
            newitem.setNew();
            newitem.setInvoiceID(invoice.getInvoiceID());
            newitem.setVendorID(  invoice.getVendorID() );
            
            
            newitem.setInvoiceNumber(invoice.getInvoiceNumber());
            newitem.setInvoiceDate(invoice.getInvoiceDate());
            newitem.setCheckNumber(invoice.getCheckNumber());
                        
            newitem.setBalance(   0);
            newitem.setCheckDate( new Date ( Calendar.getInstance().getTimeInMillis() ) );
            newitem.setDiscountAmount( FormatNumber.round(invoice.getDiscountAmount()) );
            newitem.setDueDate(   invoice.getInvoiceDueDate() );
            newitem.setHandwritten( handGenCheck );
            newitem.setLocaleID(  location.getLocaleNumber() );
            newitem.setLocationID(  location.getId() );
            newitem.setMemo( invoice.getDescription() );
            newitem.setUserID( user.getId() );
           
            
            t.addPersistent(newitem);
            t.save();
            t = null;
            newitemid = newitem.getId();


            // if successful then ID is > 0
            if (newitemid < 1){
                throw new PersistenceException("Unable to SAVE AP Master check information to DBMS.");
            }
            // Fetch AP master just saved to DBMS
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            newitem = FdmsDb.getInstance().getApCheck(t,newitemid);
            if (newitem==null){
                throw new PersistenceException("Unable to ACCESS AP Master check information from DBMS.");
            }
            
            ArrayList <String> data = null;
	        data = invoiceMap.get(String.valueOf(invoice.getVendorID()));
            double discountAmount = 0.0;
            	// Check to see if the discount flag is set
            InvoiceDAO invoiceDAO = new InvoiceDAO(user);
            InvoiceDTO anInvoice = null;
            for(String invoiceID: data) {
            	
            	anInvoice = invoiceDAO.getInvoice(Long.parseLong(invoiceID));
            	discountAmount = discountAmount + newitem.calculateDiscountAmount(anInvoice, null);
            }
            vendor = FdmsDb.getInstance().getApVendor(t, invoice.getVendorID() );
            
	      			// Record the DistributionHistory as a invoice transaction.  We are not going to 
	      			// record the discount because it will apply to the entire invoice.
	      		DbApDistributionHistory expenseHistory = new DbApDistributionHistory();
	      		expenseHistory.setNew();
	      		expenseHistory.setApMasterID(newitem.getId());
	      		//expenseHistory.setApAccountNumber(String.valueOf(vendor.getDefaultAcct()));
	      		
		    	
		    	DbApAccount account = FdmsDb.getInstance().getApAccount(t, vendor.getDefaultAcctID());

		    	if (account == null) {
		    		expenseHistory.setApAccountNumber("");
		    	}else {
		    		expenseHistory.setApAccountNumber(account.getAccountNumber() );
		    	}
	      		
	      		
	      		
	      		expenseHistory.setDiscountAmount(FormatNumber.round(discountAmount));
	      		expenseHistory.setType(DbApDistributionHistory.INVOICE);
	      		expenseHistory.setMemo(invoice.getDescription());

	      		
	        InvoiceItemDAO invoiceItemDAO = new InvoiceItemDAO(user);
	        ArrayList <InvoiceItemDTO> invItems = null;
	        
			for(String invoiceID: data) {
				
		        invItems = invoiceItemDAO.getInvoiceItems(Long.valueOf(invoiceID));
		            
	            // When we get here we are going to go thru every line item and report on it
	            for ( InvoiceItemDTO item : invItems ) {
	            	double itemCostWTax = calculateCostAmount(item.getItemCost());
	            	itemCostWTax = FormatNumber.roundDoubleDollars(itemCostWTax);
	            	
	            	newitem.addDistribution((int)item.getApAccountID(), FormatNumber.round(itemCostWTax), 
	            													item.getItemDesc(), DbApDistributionHistory.INVOICE_ITEM);
	            }
			}
	      		expenseHistory.setAmount(newitem.getInvoiceTotal()- expenseHistory.getDiscountAmount());
	      		t.addPersistent(expenseHistory);
           
	      	//if there is discount apply to the check.
	      	if (expenseHistory.getDiscountAmount() > 0){
	      		expenseHistory = new DbApDistributionHistory();
	      		expenseHistory.setNew();
	      		expenseHistory.setApMasterID(newitem.getId());
	      		//expenseHistory.setApAccountNumber(String.valueOf(vendor.getDefaultAcct()));
  		    	
		    	expenseHistory.setApAccountNumber("40090");
		
	      		expenseHistory.setDiscountAmount(0);
	      		expenseHistory.setType(DbApDistributionHistory.INVOICE);
	      		expenseHistory.setMemo("Discount");

	      		expenseHistory.setAmount(FormatNumber.round(discountAmount));
	      		t.addPersistent(expenseHistory);
	      	}
	      		
	      		

            //location.setNextCheckNumber(  invoice.getCheckNumber()+1 );
            location.setCashBalance(    location.getCashBalance() - FormatNumber.round(invoice.getAmountOfInvoice()) );
            
            newitem.setCheck1099( invoice.getInvoice1099Type() > 0 );

            if ( newitem.isCheck1099() ) {
            	newitem.setCheck1099Amount(FormatNumber.round(invoice.getInvoice1099Amount()));
            }
            
            CompanyOptionsManager coMgr = new CompanyOptionsManager ();
            int checkApprovalRqd = coMgr.getCompanyOptionValueForCompany(user.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_CHECK_APPROVAL_REQUIRED);
    		
            if ( checkApprovalRqd == 1 ) {
            	// Approval Required
            	double externalLimit = location.getExternalVendorLimit();
                double internalLimit = location.getInternalVendorLimit();
                 
                if ( user.isUserLimitOverride() ) {
                	externalLimit = user.getLimitExternalVendor();
                	internalLimit = user.getLimitInternalVendor(); 
                }
                 
                double limit = 0.0;
                if ( vendor.isInternalVendor() ) {
                	limit = internalLimit;
                } else {
                 	limit = externalLimit;
                }
                
                double currLimit = limit * 100; 
    			
    			if ( FormatNumber.round(invoice.getAmountOfInvoice()) <= currLimit ) {
    				newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_UNDER_APPROVAL_LIMIT);
    				
    				// set the check to be approved by the dashboard
    				if (newitem.getInvoiceNumber().length() > 0) {
    					newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_APPROVED);
    				}
    			} else {
    				
    				LocationDAO locDAO = new LocationDAO(user);
    		        ArrayList <LocationDTO> locations = locDAO.getLocationsByAccountant(user.getCompanyID(), user.getId());
    		        boolean locationFound = false;
    		        
    		        for ( LocationDTO locationDto : locations ) {
    		        	if ( locationDto.getLocationID() == invoice.getLocationID() ) {
    		        		locationFound = true;
    		        		break;
    		        	}
     		        }
    		        
    				// If the current user the accountant for this location the check is auto approved
    		        if ( locationFound ) {
    		        	newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_APPROVED);
    		        	newitem.setApprovedBy(user.getId());
    		        	newitem.setApprovedTmstmp(new Timestamp(Calendar.getInstance().getTimeInMillis()) );
    		        } else {
    		        	newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_PENDING);
    		        }
    		        
    		        // set the check to be approved by the dashboard
    		        if (newitem.getInvoiceNumber().length() > 0) {
    					newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_APPROVED);
    				}
    		        
    			}
    			
            } else {
            	newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_NOT_REQUIRED_BY_COMPANY);
            }
            
            // Store to DBMS
            t.save();

        } finally {
            if (t != null) {
				t.closeConnection();
				t = null;
			}
        }
        
        return (newitem);
	}	

	public DbApMaster createInvoicesCheck (DbUserSession user, InvoiceDTO invoice, TreeMap<String, ArrayList <String>> invoiceMap, ApVendorDTO aVendor) throws Exception {
		DbApMaster newitem = null;
		DatabaseTransaction t = null;
		DbApVendor vendor = null;
        int newitemid = 0;
        
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
 
            DbLocation location = FdmsDb.getInstance().getLocation(t, invoice.getLocationID()); 
            if (location == null){
            	throw new PersistenceException("Unable to find location.");
            }
            
            String handGenCheck = "N";
            
            if ( invoice.getCheckingStatus() == InvoiceDTO.CHECK_STATUS_CHECK_GEN_MANUAL ) {
            	handGenCheck = "Y";
            }
            
            newitem = new DbApMaster();
            newitem.setNew();
            newitem.setInvoiceID(invoice.getInvoiceID());
            newitem.setVendorID(  invoice.getVendorID() );
            
            
            newitem.setInvoiceNumber(invoice.getInvoiceNumber());
            newitem.setInvoiceDate(invoice.getInvoiceDate());
            newitem.setCheckNumber(invoice.getCheckNumber());
                        
            newitem.setBalance(   0);
            newitem.setCheckDate( new Date ( Calendar.getInstance().getTimeInMillis() ) );
            newitem.setDiscountAmount( FormatNumber.round(invoice.getDiscountAmount()) );
            newitem.setDueDate(   invoice.getInvoiceDueDate() );
            newitem.setHandwritten( handGenCheck );
            newitem.setLocaleID(  location.getLocaleNumber() );
            newitem.setLocationID(  location.getId() );
            newitem.setMemo( invoice.getDescription() );
            newitem.setUserID( user.getId() );
            newitem.setVendorName(aVendor.getName());
            
            t.addPersistent(newitem);
            
            // set balanc on the location
            location.setCashBalance(location.getCashBalance() - ( (FormatNumber.round(invoice.getAmountOfInvoice()))- (FormatNumber.round(invoice.getDiscountAmount()))));

            //update the bank account
			BankAccountDAO bankDao = new BankAccountDAO(user);
			BankAccountDTO bankDto = new BankAccountDTO();
			bankDto = bankDao.getBankAccountByLocationId(invoice.getLocationID());
			if (bankDto != null) {
				DbBankAccount bankAccount = FdmsDb.getInstance().getBankAccount(t, bankDto.getBankAccountId());
	            int bankBalance = bankAccount.getBalance();
	            bankBalance = (bankBalance - ( (FormatNumber.round(invoice.getAmountOfInvoice()))- (FormatNumber.round(invoice.getDiscountAmount()))));
				bankAccount.setBalance(bankBalance);
				t.addPersistent(bankAccount);
			}
            t.save();
            t = null;
            newitemid = newitem.getId();


            // if successful then ID is > 0
            if (newitemid < 1){
                throw new PersistenceException("Unable to SAVE AP Master check information to DBMS.");
            }
            // Fetch AP master just saved to DBMS
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
            newitem = FdmsDb.getInstance().getApCheck(t,newitemid);
            if (newitem==null){
                throw new PersistenceException("Unable to ACCESS AP Master check information from DBMS.");
            }
            
            ArrayList <String> data = null;
	        data = invoiceMap.get(String.valueOf(invoice.getVendorID()));
            double discountAmount = 0.0;
            	// Check to see if the discount flag is set
            InvoiceDAO invoiceDAO = new InvoiceDAO(user);
            InvoiceDTO anInvoice = null;
            for(String invoiceID: data) {
            	
            	anInvoice = invoiceDAO.getInvoice(Long.parseLong(invoiceID));
            	discountAmount = discountAmount + newitem.calculateDiscountAmount(anInvoice, null);
            }
            vendor = FdmsDb.getInstance().getApVendor(t, invoice.getVendorID() );
            
	      			// Record the DistributionHistory as a invoice transaction.  We are not going to 
	      			// record the discount because it will apply to the entire invoice.
	      		DbApDistributionHistory expenseHistory = new DbApDistributionHistory();
	      		expenseHistory.setNew();
	      		expenseHistory.setApMasterID(newitem.getId());
	      		//expenseHistory.setApAccountNumber(String.valueOf(vendor.getDefaultAcct()));
	      		
		    	
		    	DbApAccount account = FdmsDb.getInstance().getApAccount(t, vendor.getDefaultAcctID());

		    	if (account == null) {
		    		expenseHistory.setApAccountNumber("");
		    	}else {
		    		expenseHistory.setApAccountNumber(account.getAccountNumber() );
		    	}
	      		
	      		
	      		
	      		expenseHistory.setDiscountAmount(FormatNumber.round(discountAmount));
	      		expenseHistory.setType(DbApDistributionHistory.INVOICE);
	      		expenseHistory.setMemo(invoice.getDescription());

	      		
	        InvoiceItemDAO invoiceItemDAO = new InvoiceItemDAO(user);
	        ArrayList <InvoiceItemDTO> invItems = null;
	        
			for(String invoiceID: data) {
				
		        invItems = invoiceItemDAO.getInvoiceItems(Long.valueOf(invoiceID));
		            
	            // When we get here we are going to go thru every line item and report on it
	            for ( InvoiceItemDTO item : invItems ) {
	            	double itemCostWTax = calculateCostAmount(item.getItemCost());
	            	itemCostWTax = FormatNumber.roundDoubleDollars(itemCostWTax);
	            	
	            	newitem.addDistribution((int)item.getApAccountID(), FormatNumber.round(itemCostWTax), 
	            													item.getItemDesc(), DbApDistributionHistory.INVOICE_ITEM);
	            }
			}
	      		expenseHistory.setAmount(newitem.getInvoiceTotal()- expenseHistory.getDiscountAmount());
	      		t.addPersistent(expenseHistory);
           
	      	//if there is discount apply to the check.
	      	if (expenseHistory.getDiscountAmount() > 0){
	      		expenseHistory = new DbApDistributionHistory();
	      		expenseHistory.setNew();
	      		expenseHistory.setApMasterID(newitem.getId());
	      		//expenseHistory.setApAccountNumber(String.valueOf(vendor.getDefaultAcct()));
  		    	
		    	expenseHistory.setApAccountNumber("40090");
		
	      		expenseHistory.setDiscountAmount(0);
	      		expenseHistory.setType(DbApDistributionHistory.INVOICE);
	      		expenseHistory.setMemo("Discount");

	      		expenseHistory.setAmount(FormatNumber.round(discountAmount));
	      		t.addPersistent(expenseHistory);
	      	}
	      		// we need to revorse the invoice that have discount back
	      		for(String invoiceID: data) {
	            	
	            	anInvoice = invoiceDAO.getInvoice(Long.parseLong(invoiceID));
	            	newitem.calculateDiscountAmount(anInvoice, null);
	            	double discount = 0.0;
	            	java.util.Date currDate = DateUtility.getCurrentDateUtil();
	            	currDate = FormatDate.addToDate(currDate, -1, 0);
	            	if ( anInvoice.getDiscountDueDate() != null && currDate.after(anInvoice.getDiscountDueDate()) ) {
	            		discount = anInvoice.getDiscountAmount();
	            	}
	            	if (discount > 0 && anInvoice.isDiscountFlag()) {
	            		DbInvoiceTransHist dbInvoiceTransHist = new DbInvoiceTransHist();
	            		dbInvoiceTransHist.setNew();
	    				dbInvoiceTransHist.setInvoiceID(anInvoice.getInvoiceID());
	    				dbInvoiceTransHist.setLocationID(anInvoice.getLocationID());
	    				dbInvoiceTransHist.setType(InvoiceTransactionHistoryDTO.INVOICE_TYPE);
	    				dbInvoiceTransHist.setDescription("Reverse Discount");
	    				dbInvoiceTransHist.setAmount(FormatNumber.round(discount));
	    				dbInvoiceTransHist.setGlAccount(anInvoice.getGlcategory());
	    				dbInvoiceTransHist.setTransactionDate(new Date ( Calendar.getInstance().getTimeInMillis() ));
	    				// Set posted to 'N' so it will be exported
	    				dbInvoiceTransHist.setPosted('N');
	    				t.addPersistent(dbInvoiceTransHist);	
	    				
	    				dbInvoiceTransHist = new DbInvoiceTransHist();
	            		dbInvoiceTransHist.setNew();
	    				dbInvoiceTransHist.setInvoiceID(anInvoice.getInvoiceID());
	    				dbInvoiceTransHist.setLocationID(anInvoice.getLocationID());
	    				dbInvoiceTransHist.setType(InvoiceTransactionHistoryDTO.LINEITEM_TYPE);
	    				dbInvoiceTransHist.setDescription("Reverse Discount");
	    				dbInvoiceTransHist.setAmount(FormatNumber.round(discount));
	    				dbInvoiceTransHist.setGlAccount("40090");
	    				dbInvoiceTransHist.setTransactionDate(new Date ( Calendar.getInstance().getTimeInMillis() ));
	    				// Set posted to 'N' so it will be exported
	    				dbInvoiceTransHist.setPosted('N');
	    				t.addPersistent(dbInvoiceTransHist);
	    				
	            	}
	            }
	      		
	      		
	      		

            //location.setNextCheckNumber(  invoice.getCheckNumber()+1 );
            
            newitem.setCheck1099( invoice.getInvoice1099Type() > 0 );

            if ( newitem.isCheck1099() ) {
            	newitem.setCheck1099Amount(FormatNumber.round(invoice.getInvoice1099Amount()));
            }
            
            CompanyOptionsManager coMgr = new CompanyOptionsManager ();
            int checkApprovalRqd = coMgr.getCompanyOptionValueForCompany(user.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_CHECK_APPROVAL_REQUIRED);
    		
            if ( checkApprovalRqd == 1 ) {
            	// Approval Required
            	double externalLimit = location.getExternalVendorLimit();
                double internalLimit = location.getInternalVendorLimit();
                 
                if ( user.isUserLimitOverride() ) {
                	externalLimit = user.getLimitExternalVendor();
                	internalLimit = user.getLimitInternalVendor(); 
                }
                 
                double limit = 0.0;
                if ( vendor.isInternalVendor() ) {
                	limit = internalLimit;
                } else {
                 	limit = externalLimit;
                }
                
                double currLimit = limit * 100; 
    			
    			if ( FormatNumber.round(invoice.getAmountOfInvoice()) <= currLimit ) {
    				newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_UNDER_APPROVAL_LIMIT);
    				
    				// set the check to be approved by the dashboard
    				if (newitem.getInvoiceNumber().length() > 0) {
    					newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_APPROVED);
    				}
    			} else {
    				
    				LocationDAO locDAO = new LocationDAO(user);
    		        ArrayList <LocationDTO> locations = locDAO.getLocationsByAccountant(user.getCompanyID(), user.getId());
    		        boolean locationFound = false;
    		        
    		        for ( LocationDTO locationDto : locations ) {
    		        	if ( locationDto.getLocationID() == invoice.getLocationID() ) {
    		        		locationFound = true;
    		        		break;
    		        	}
     		        }
    		        
    				// If the current user the accountant for this location the check is auto approved
    		        if ( locationFound ) {
    		        	newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_APPROVED);
    		        	newitem.setApprovedBy(user.getId());
    		        	newitem.setApprovedTmstmp(new Timestamp(Calendar.getInstance().getTimeInMillis()) );
    		        } else {
    		        	newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_PENDING);
    		        }
    		        
    		        // set the check to be approved by the dashboard
    		        if (newitem.getInvoiceNumber().length() > 0) {
    					newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_APPROVED);
    				}
    		        
    			}
    			
            } else {
            	newitem.setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_NOT_REQUIRED_BY_COMPANY);
            }
            
            // Store to DBMS
            t.save();

        } finally {
            if (t != null) {
				t.closeConnection();
				t = null;
			}
        }
        
        return (newitem);
	}		
	
	public ArrayList <DbApAccount> getAccountList(HttpServletRequest request, long locationID, long localeID) throws PersistenceException {
        DbApAccount[] alist	= null;
        ArrayList <DbApAccount> coll = new ArrayList <DbApAccount> ();
        DatabaseTransaction t =null;
        
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            // create select list of vendors
            alist = FdmsDb.getInstance().getApAccounts(t, (int) localeID, (int) locationID);
            
            if (alist != null) {
                for (int i=0; i<alist.length; i++){
                    coll.add( alist[i] );
                }
            }
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
		
		return ( coll );
	}
	

	public DbApAccount getAccount(HttpServletRequest request, long accountID) throws PersistenceException {
        DbApAccount returnValue = null;
        DatabaseTransaction t =null;
        
        DbUserSession sessionUser = SessionHelpers.getUserSession(request);
        try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
            returnValue = FdmsDb.getInstance().getApAccount(t, (int) accountID);
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
		
		return ( returnValue );
	}
	
	public String printInvoiceCheck (HttpServletRequest request, HttpServletResponse response, 
			ServletContext context, ActionMessages errors, int checkMasterID) {
		CheckPrinter cp = new CheckPrinter ();
		HttpSession session = request.getSession();
		DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
    String pageName = cp.printCheckForCheckWriter(request, response, context, user, errors, checkMasterID);
    return ( pageName );
	}

	
	/**
	 * Note: To use this routine the report formula must be setup in WEBFDMS to
	 * {ApMaster.MasterID} in %recid%
	 * 
	 * @param request
	 * @param response
	 * @param context
	 * @param errors
	 * @param checks This is a list of check numbers seperated by a comma
	 * @return
	 */
	public String printInvoiceChecks (HttpServletRequest request, HttpServletResponse response, 
			ServletContext context, ActionMessages errors, String checks) {
		CheckPrinter cp = new CheckPrinter ();
		HttpSession session = request.getSession();
		DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
    String pageName = cp.printCheckForCheckWriter(request, response, context, user, errors, checks);
    return ( pageName );
	}

	public void emailInvoiceCheck (HttpServletRequest request, InvoiceDTO invoice, DbApMaster apMaster, boolean approvalRequired) throws Exception {

		HttpSession session = request.getSession();
		DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);

		DatabaseTransaction t = null;
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			
			DbApVendor vendor = FdmsDb.getInstance().getApVendor(t, invoice.getVendorID() );
			DbLocation location = FdmsDb.getInstance().getLocation(t, invoice.getLocationID() );
            
			StringBuffer appURL = request.getRequestURL();
			String viewDashboard = appURL.substring(0, appURL.lastIndexOf("/") );

			String subject = "FDMS Dashboard Check Created";
			String endLine = "\r\n";
			StringBuilder message = new StringBuilder();

			//message.append("Check " + invoice.getCheckNumber() + " has been created." + endLine + endLine);
			message.append(viewDashboard + endLine + endLine);
			message.append("    Check Number: " + apMaster.getCheckNumber() + endLine);
			message.append("    Check Date: " + FormatDate.convertDateToMM_DD_YY(apMaster.getCheckDate()) + endLine);
			message.append("    Check Amount: " + FormatCurrency.toCurrency(apMaster.getInvoiceTotal()) + endLine);
			message.append("   Location Name: " + location.getName() + endLine + endLine);
			message.append("           Payee: " + vendor.getName() + endLine);
			message.append("  Vendor Address: " + vendor.getAddr1() + endLine);
			message.append("                  " + vendor.getAddr2() + endLine);
			message.append("                  " + vendor.getCityState() + endLine);
			message.append("            Memo: " + apMaster.getMemo() + endLine);
			message.append("            User: " + user.getFirstName() + " " + user.getLastName() + endLine);

			if (approvalRequired) {
				message.append("        APPROVAL IS REQUIRED " + endLine);
			} else {
				message.append("        APPROVAL NOT REQUIRED; Check under limit" + endLine);
			}

			LocationManager locMgr = new LocationManager();
			locMgr.sendLocationEmails(user, invoice.getLocationID(), subject, message.toString());

		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        
	}
	
	public void emailInvoiceStatus (HttpServletRequest request, InvoiceDTO invoice) throws Exception {

		HttpSession session = request.getSession();
		DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);

		DatabaseTransaction t = null;
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			
			DbApVendor vendor = FdmsDb.getInstance().getApVendor(t, invoice.getVendorID() );
			DbLocation location = FdmsDb.getInstance().getLocation(t, invoice.getLocationID() );
            
			StringBuffer appURL = request.getRequestURL();
			String viewDashboard = appURL.substring(0, appURL.lastIndexOf("/") );
			
			String subject = "";
			String status = "";
			if (invoice.getInvoiceStatus().compareToIgnoreCase(InvoiceDTO.INVOICE_SUBMIT)==0) {
				subject = "FDMS Dashboard Invoice Submitted";
				status = "SUBMIT";
			}
			else if (invoice.getInvoiceStatus().compareToIgnoreCase(InvoiceDTO.INVOICE_APPROVE)==0) {
				subject = "FDMS Dashboard Invoice Approved";
				status = "Approved";				
			}
			else if (invoice.getInvoiceStatus().compareToIgnoreCase(InvoiceDTO.INVOICE_DENY)==0) {
				subject = "FDMS Dashboard Invoice Denied";
			}

			
			
			String endLine = "\r\n";
			StringBuilder message = new StringBuilder();

			message.append("Invoice " + invoice.getInvoiceNumber() + " has been " + status +"."+ endLine + endLine);
			message.append(viewDashboard + endLine + endLine);
			message.append("    Invoice Number: " + invoice.getInvoiceNumber() + endLine);
			message.append("      Invoice Date: " + FormatDate.convertDateToMM_DD_YY(invoice.getInvoiceDate()) + endLine);
			message.append("  Invoice Due Date: " + FormatDate.convertDateToMM_DD_YY(invoice.getInvoiceDueDate()) + endLine);
			message.append("    Invoice Amount: " + FormatCurrency.toCurrency(invoice.getAmountOfInvoice()) + endLine);
			message.append(" Discount Due Date: " + FormatDate.convertDateToMM_DD_YY(invoice.getDiscountDueDate()) + endLine);
			message.append("   Discount Amount: " + FormatCurrency.toCurrency(invoice.getDiscountAmount()) + endLine);			
			message.append("     Location Name: " + location.getName() + endLine + endLine);
			message.append("            Vendor: " + vendor.getName() + endLine);
			message.append("    Vendor Address: " + vendor.getAddr1() + endLine);
			message.append("                  " + vendor.getAddr2() + endLine);
			message.append("                  " + vendor.getCityState() + endLine);
			message.append("            User: " + user.getFirstName() + " " + user.getLastName() + endLine);

			LocationManager locMgr = new LocationManager();
			locMgr.sendLocationEmailsByInvoiceStatus(user, invoice.getLocationID(), subject, message.toString(),invoice.getInvoiceStatus());

		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
        
	}		
	
	public ActionMessages confirmInvoicePayment(HttpServletRequest request, HttpServletResponse response, ServletContext context, 
			InvoiceDTO invoiceDTO) throws Exception {

		HttpSession session = request.getSession();
		DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
		ActionMessages errors = new ActionMessages();
		InvoiceDAO invoiceDAO = new InvoiceDAO(user);
		
		
		if (invoiceDTO.getInvoiceStatus().compareTo(InvoiceDTO.INVOICE_APPROVE)==0) {
			
//chad			DbApMaster apMaster = invMgr.createInvoiceCheck(user, invoiceDTO);		
			
			if ( invoiceDTO.isCheckCreated() == false && invoiceDTO.isInvoicePaid() == false ) {
				if ( invoiceDTO.getCheckingStatus() == InvoiceDTO.CHECK_STATUS_SAVE_INVOICE ) {
					invoiceDTO.setCheckingStatus(InvoiceDTO.CHECK_STATUS_PRINT_CHECK_NOW);
				}
					
				// add logic to create check
				invoiceDTO.setCheckCreated(true);
				invoiceDTO.setInvoicePaid(true);
				
				//chai add
				invoiceDTO.setInvoiceStatus(InvoiceDTO.INVOICE_PRINT);
				//chai end
				
				//invoiceDAO.updateInvoice(invoiceDTO);
		
	      
//chad				String checkWriterURL = invMgr.printInvoiceCheck(request, response,
//chad						context, errors, apMaster.getId());
	
//chad				session.setAttribute("checkWriterURL", checkWriterURL);
//chad				session.setAttribute("checkMasterID",(long) apMaster.getId());
				//invMgr.emailInvoiceCheck( request, invoiceDTO, apMaster,
						//		(apMaster.getApprovalStatus() == ApMasterDTO.APPROVAL_STATUS_PENDING));
				
			}
			
		}
		return ( errors );
		
	}
	
	
	/**
	 * 
	 * @param submitedInvoice
	 * @param user
	 */
	public void createInvoiceTransHistory(DatabaseTransaction t, InvoiceDTO submitedInvoice,
			DbUserSession user) {

	//InvoiceTransactionHistoryDAO invoiceTranHistDAO = null;
	//InvoiceTransactionHistoryDTO invoiceTransHistDTO = null;


	DbInvoiceTransHist dbInvoiceTransHist = null;
	InvoiceItemDAO invoiceItemDAO = null;
	ArrayList<InvoiceItemDTO> items = null;

	try {
		// Create the DAO so we can save the history
		//invoiceTranHistDAO = new InvoiceTransactionHistoryDAO(user);
		//invoiceItemDAO = new InvoiceItemDAO(user);

		// Build us a new history record
		//invoiceTransHistDTO = new InvoiceTransactionHistoryDTO();

		// Build us a new history record
		dbInvoiceTransHist = new DbInvoiceTransHist();
		dbInvoiceTransHist.setNew();

		// Now create the transaction history record for the invoice record
		dbInvoiceTransHist.setInvoiceID(submitedInvoice.getInvoiceID());
		dbInvoiceTransHist.setLocationID(submitedInvoice.getLocationID());
		dbInvoiceTransHist.setType(InvoiceTransactionHistoryDTO.INVOICE_TYPE);
		dbInvoiceTransHist.setDescription(submitedInvoice.getDescription());
		dbInvoiceTransHist.setAmount(FormatNumber.round(submitedInvoice.getAmountOfInvoice()));
		dbInvoiceTransHist.setGlAccount(submitedInvoice.getGlcategory());
		dbInvoiceTransHist.setTransactionDate(submitedInvoice.getInvoiceDate());
		// Set posted to 'N' so it will be exported
		dbInvoiceTransHist.setPosted('N');
		t.addPersistent(dbInvoiceTransHist);
		//dbInvoiceTransHist.addInvoiceHistory(invoiceTransHistDTO);

		// Now get the invoice item records
		invoiceItemDAO = new InvoiceItemDAO(user);
		items = invoiceItemDAO.getInvoiceItems(submitedInvoice.getInvoiceID());
		// Now export them to the InvoiceTransHist table
		for (InvoiceItemDTO invoiceItemDTO : items) {
			// Now create the transaction history record.
			dbInvoiceTransHist = new DbInvoiceTransHist();
			dbInvoiceTransHist.setNew();			
			dbInvoiceTransHist.setInvoiceID(submitedInvoice.getInvoiceID());
			dbInvoiceTransHist.setLocationID(submitedInvoice.getLocationID());
			dbInvoiceTransHist.setType(InvoiceTransactionHistoryDTO.LINEITEM_TYPE);
			dbInvoiceTransHist.setDescription(invoiceItemDTO.getItemDesc());
			dbInvoiceTransHist.setAmount(FormatNumber.round(invoiceItemDTO.getItemCost()));
			dbInvoiceTransHist.setGlAccount(invoiceItemDTO.getGlAcctNumber());
			dbInvoiceTransHist.setTransactionDate(submitedInvoice.getInvoiceDate());
			// Set posted to 'N' so it will be exported
			dbInvoiceTransHist.setPosted('N');
			t.addPersistent(dbInvoiceTransHist);
			//invoiceTranHistDAO.addInvoiceHistory(invoiceTransHistDTO);
		}
	} catch (Exception e) {
		e.printStackTrace();

	}
	}


	public void createInvoiceTransHistoryReversal(DatabaseTransaction t, DbInvoice submitedInvoice, DbUserSession user) {
		DbInvoiceTransHist dbInvoiceTransHist = null;
		ArrayList<InvoiceItemDTO> items = null;
		InvoiceItemDAO invoiceItemDAO = null;
	
		try {
	
			// Build us a new history record
			dbInvoiceTransHist = new DbInvoiceTransHist();
			dbInvoiceTransHist.setNew();
	
			// Now create the transaction history record for the invoice record
			dbInvoiceTransHist.setInvoiceID(submitedInvoice.getId());
			dbInvoiceTransHist.setLocationID(submitedInvoice.getLocationID());
			dbInvoiceTransHist.setType(InvoiceTransactionHistoryDTO.INVOICE_TYPE);
			dbInvoiceTransHist.setDescription(submitedInvoice.getDescription());
			
			if (submitedInvoice.getDiscountFlag()== true ){
				dbInvoiceTransHist.setAmount(-1 * (FormatNumber.round(submitedInvoice.getAmountOfInvoice())-FormatNumber.round(submitedInvoice.getDiscountAmount())));
			} else {
				dbInvoiceTransHist.setAmount(-1 * FormatNumber.round(submitedInvoice.getAmountOfInvoice()));
			}

			//dbInvoiceTransHist.setGlAccount(submitedInvoice.getGlcategory());
			dbInvoiceTransHist.setGlAccount("0");
			dbInvoiceTransHist.setTransactionDate(submitedInvoice.getInvoiceDate());
			// Set posted to 'N' so it will be exported
			dbInvoiceTransHist.setPosted('N');
			t.addPersistent(dbInvoiceTransHist);
	
			// this is for discount acccount 40090
			if (submitedInvoice.getDiscountFlag()== true ){
				dbInvoiceTransHist = new DbInvoiceTransHist();
				dbInvoiceTransHist.setNew();
		
				// Now create the transaction history record for the invoice record
				dbInvoiceTransHist.setInvoiceID(submitedInvoice.getId());
				dbInvoiceTransHist.setLocationID(submitedInvoice.getLocationID());
				dbInvoiceTransHist.setType(InvoiceTransactionHistoryDTO.INVOICE_TYPE);
				dbInvoiceTransHist.setDescription("Discount");
				dbInvoiceTransHist.setAmount(-1 * FormatNumber.round(submitedInvoice.getDiscountAmount()));
				//dbInvoiceTransHist.setGlAccount(submitedInvoice.getGlcategory());
				dbInvoiceTransHist.setGlAccount("40090");
				dbInvoiceTransHist.setTransactionDate(submitedInvoice.getInvoiceDate());
				// Set posted to 'N' so it will be exported
				dbInvoiceTransHist.setPosted('N');
				t.addPersistent(dbInvoiceTransHist);				
			}
			
			invoiceItemDAO = new InvoiceItemDAO(user);
			// Now get the invoice item records
			items = invoiceItemDAO.getInvoiceItems(submitedInvoice.getId());
			// Now export them to the InvoiceTransHist table
			for (InvoiceItemDTO invoiceItemDTO : items) {
				// Now create the transaction history record.
				dbInvoiceTransHist = new DbInvoiceTransHist();
				dbInvoiceTransHist.setNew();
				dbInvoiceTransHist.setInvoiceID(submitedInvoice.getId());
				dbInvoiceTransHist.setLocationID(submitedInvoice.getLocationID());
				dbInvoiceTransHist.setType(InvoiceTransactionHistoryDTO.LINEITEM_TYPE);
				dbInvoiceTransHist.setDescription(invoiceItemDTO.getItemDesc());
				dbInvoiceTransHist.setAmount(-1 * FormatNumber.round(invoiceItemDTO.getItemCost()));
				dbInvoiceTransHist.setGlAccount(invoiceItemDTO.getGlAcctNumber());
				dbInvoiceTransHist.setTransactionDate(submitedInvoice.getInvoiceDate());
				// Set posted to 'N' so it will be exported
				dbInvoiceTransHist.setPosted('N');
				t.addPersistent(dbInvoiceTransHist);
			}
		} catch (Exception e) {
	
		}
	}

	
	
}




