package com.aldorsolutions.webfdms.accounting.bean;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorsolutions.webfdms.accounting.dao.AccountingInterfaceDAO;
import com.aldorsolutions.webfdms.accounting.model.AccountingInterfaceDTO;
import com.aldorsolutions.webfdms.accounting.model.CustomerDTO;
import com.aldorsolutions.webfdms.accounting.model.DeceasedDTO;
import com.aldorsolutions.webfdms.accounting.model.GLAccountDefaultDTO;
import com.aldorsolutions.webfdms.accounting.model.ItemDTO;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistory;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbInvoiceTransHist;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;


/**
 * Workfile: AccountingInterfaceGreatPlains.java
 * Date: 10/05/2006 
 * Author: David Rollins
 *
 * Copyright 2006. FDMS Network, All Rights Reserved
 */
public class AccountingInterfaceGreatPlains implements AccountingInterface {
	
	final private static Logger logger = Logger.getLogger(AccountingInterfaceGreatPlains.class.getName());
	
	private SimpleDateFormat batchIDFormat = null;
	private SimpleDateFormat tmstmpFormat = null;
	private SimpleDateFormat slashFormat = null;
	private Date currentDate = null;

	private HashMap <String, LocationDTO> locations = null;
	private HashMap <String, ArrayList> glAccounts = null;
	
	
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

    	batchIDFormat = new SimpleDateFormat("MMddyy");
    	slashFormat = new SimpleDateFormat("MM/dd/yyyy");
    	tmstmpFormat = new SimpleDateFormat ("ssSSS");
    	currentDate = Calendar.getInstance().getTime();
    	locations = new HashMap <String, LocationDTO> ();
		glAccounts = new HashMap <String, ArrayList> ();
    	
        // Construct file name
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_Hmmss");
        filename.append( formatter.format(currentDate));
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
    

	public boolean initializeExport(String intFile, int intType) {
		
		String header = "BatchID|AcctNo|Debit|Credit|Desc|Ref|Trxref|Trxdate\n";
		
		return AccountingInterfaceUtil.addTranToDiskFile(intFile, header);
	}	
	
    public boolean writeSummaryTran(
    		DbUser dbUser,
    		AccountingInterfaceDTO accountingInterfaceDTO,
			ActionErrors errors) {
    	return true;
    }
    
	public boolean writeSalesTran(
			DbHistory dbHistory,
			DbUser dbUser,
			AccountingInterfaceDTO accountingInterfaceDTO) {
		

    	try {
    		AccountingInterfaceDAO accountingInterfaceDAO = new AccountingInterfaceDAO();
			DeceasedDTO deceasedDTO = accountingInterfaceDAO.getDeceased(
					dbUser.getDbLookup(), 
					accountingInterfaceDTO.getCaseId());
			
			// start transaction set for vitals case
			
			if (deceasedDTO == null) {
				deceasedDTO = new DeceasedDTO(); 
			}
			
			// AccountingInterfaceUtil.addTranToDiskFile(accountingInterfaceDTO.getFileName(), deceasedDTO.toXML());
				
			CustomerDTO customerDTO = accountingInterfaceDAO.getCustomer(
					dbUser.getDbLookup(), 
					accountingInterfaceDTO.getCaseId());
			
			if (customerDTO == null) {
				customerDTO = new CustomerDTO();
				customerDTO.setVitalsId(accountingInterfaceDTO.getCaseId());
			}
			
			ArrayList items = accountingInterfaceDAO.getTransactions(
					dbUser.getDbLookup(), 
					accountingInterfaceDTO.getCaseId());
			
			if (items != null) {
				Iterator it = items.iterator();
				while (it.hasNext()) {
					ItemDTO itemDTO = (ItemDTO) it.next();
					
					StringBuffer transRow = new StringBuffer();
					
					String deceasedInfo = deceasedDTO.getContractCode() + " " + deceasedDTO.getDeceasedLastName() + ", " + 
						deceasedDTO.getDeceasedFirstName();
										

					if ( deceasedDTO != null && deceasedDTO.getChapelNumber() <= 0 ) {
						logger.error("Invalid Chapel for Item: " + itemDTO.getTranHistId() + 
								";  locID= " + deceasedDTO.getChapelNumber() + "; VitalsKey = " + 
								deceasedDTO.getVitalsMasterKey() );
						return (false);
					}
					
					LocationDTO location = null;
					
					if ( locations.get( Integer.toString ( deceasedDTO.getChapelNumber() ) ) != null ){
						location = (LocationDTO) locations.get( Integer.toString(deceasedDTO.getChapelNumber()) );
					} 
					else {
						LocationDAO locationDAO = new LocationDAO (dbUser);
						location = locationDAO.getLocation(deceasedDTO.getChapelNumber()); 
						locations.put(Integer.toString(deceasedDTO.getChapelNumber()), location);
					}
					
					if ( location == null ) {
						logger.error("Invalid Location for Item: " + itemDTO.getTranHistId() + 
								";  locID= " + deceasedDTO.getChapelNumber() + "; VitalsKey = " + 
								deceasedDTO.getVitalsMasterKey() );
						return (false);
					}
					
					
					
					String batchID = "FDMS" + batchIDFormat.format(currentDate);
					String accountNo = createAccountNumber ( itemDTO, location, accountingInterfaceDAO, dbUser);
					Timestamp tmstmp = new Timestamp (System.currentTimeMillis());
					
					
					sbAppend (transRow, batchID);
					sbAppend (transRow, accountNo );
					sbAppend (transRow, getDebitValue(itemDTO) );
					sbAppend (transRow, getCreditValue(itemDTO) );
					sbAppend (transRow, itemDTO.getDescription() );
					sbAppend (transRow, tmstmpFormat.format(tmstmp) );
					sbAppend (transRow, deceasedInfo );
					sbAppend (transRow, slashFormat.format(itemDTO.getDateOfTran()), true );
					
					AccountingInterfaceUtil.addTranToDiskFile( accountingInterfaceDTO.getFileName(), transRow.toString() );
				}
			}
			
    	} catch (Exception e) {
    		logger.error("Exception in writeSummaryTran() : ", e);
    	}
    	
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

		batchIDFormat = null;
		tmstmpFormat = null;
		slashFormat = null;
		currentDate = null;

		locations = null;
		glAccounts = null;
		
		return (true);
	}
	
	private void sbAppend ( StringBuffer sb, String value ) {
		sbAppend ( sb, value, false );
	}
	
	private void sbAppend ( StringBuffer sb, String value, boolean last ) {
		
		if ( value != null ) {
			
			for ( int i = 0; i < value.length(); i++ ) {
				char c = value.charAt(i);
				if ( c != '|' ) {
					sb.append(c);
				}
				else {
					sb.append(' ');
				}
			}
		} 
		
		if ( last ) {
			sb.append("\n");
		} else {
			sb.append("|");
		}
	}
	
	private String getCreditValue(ItemDTO itemDTO) {
		int amount = itemDTO.getAmountOfTran();
				
		if ( amount < 0 ) {
			amount = -amount;
			return ( formatNumber(amount/100.0) );
		}
		{
			return (formatNumber(0.0));
		}
	}
	
	private String getDebitValue(ItemDTO itemDTO) {
		int amount = itemDTO.getAmountOfTran();
		
		if ( amount > 0 ) {
			return ( formatNumber(amount/100.0) );
		}
		else
		{
			return (formatNumber(0.0));
		}
	}
	
	private String formatNumber(double value) {
		DecimalFormat formatter = new DecimalFormat ("0.00");
		return ( formatter.format(value) );
	}
	
	
	
	private String createAccountNumber (ItemDTO itemDTO, LocationDTO location, AccountingInterfaceDAO accountingInterfaceDAO, DbUser dbUser) {

		GLAccountDefaultDTO glAccountDefault = null;
		GLAccountDefaultDTO glAccountLoc = null;
		ArrayList accountsList = null;
		
		if ( glAccounts.get( Long.toString( location.getLocaleID() ) ) != null ){
			accountsList = (ArrayList) glAccounts.get( Long.toString(itemDTO.getLocationId()) );
		} 
		else {
			accountsList = accountingInterfaceDAO.getGLAccountDefaults( 
					dbUser.getDbLookup(), 
					location.getLocaleID() );
			
			glAccounts.put(Long.toString(location.getLocaleID() ), accountsList);
		}
		
		if ( accountsList != null ) {
			Iterator index = accountsList.iterator();

			while (index.hasNext()) {
				GLAccountDefaultDTO glDto = (GLAccountDefaultDTO) index.next();

				String glCat = glDto.getCategory();
				String itemCat = itemDTO.getItemCategory();

				glCat = (glCat != null) ? glCat : "";
				itemCat = (itemCat != null) ? itemCat : "";

				if (glCat.equals(itemCat)) {

					if (glDto.getLocationID() > 0 && (glDto.getLocationID() == itemDTO.getLocationId())) {
						glAccountLoc = glDto;
					} else if (glDto.getLocationID() == 0) {
						glAccountDefault = glDto;
					}
				}
			}
		}
		

		String accountNo = "2-" + location.getDivision() + "-";
		
		if ( glAccountLoc != null ) {
			accountNo += glAccountLoc.getRevenueAcct();
		} else if ( glAccountDefault != null ) {
			accountNo += glAccountDefault.getRevenueAcct();;
		}
		else {
			accountNo += "NEEDGLDEFAULT";	
		}
			
		accountNo += "-" + location.getLocationNumber();
		
		return ( accountNo );
		
	}
	
	
	
}
