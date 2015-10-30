package com.aldorsolutions.webfdms.accounting.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

import com.aldorsolutions.webfdms.accounting.dao.AccountingInterfaceDAO;
import com.aldorsolutions.webfdms.accounting.model.AccountingInterfaceDTO;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistory;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistoryPeer;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistorySet;
import com.aldorsolutions.webfdms.beans.DbApDistributionHistorySetPeer;
import com.aldorsolutions.webfdms.beans.DbApMasterPeer;
import com.aldorsolutions.webfdms.beans.DbCasePeer;
import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.beans.DbHistoryPeer;
import com.aldorsolutions.webfdms.beans.DbHistorySet;
import com.aldorsolutions.webfdms.beans.DbHistorySetPeer;
import com.aldorsolutions.webfdms.beans.DbInvoiceTransHist;
import com.aldorsolutions.webfdms.beans.DbInvoiceTransHistPeer;
import com.aldorsolutions.webfdms.beans.DbInvoiceTransHistSet;
import com.aldorsolutions.webfdms.beans.DbLocation;
import com.aldorsolutions.webfdms.beans.DbUser;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.PersistentI;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;
import com.aldorsolutions.webfdms.webservice.QBVitalsRecordInfo;


/**
 * Workfile: AccountingInterfaceManagerBean.java
 * Date: Nov 7, 2005 5:58:49 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class AccountingInterfaceManagerBean {
	
	final private static Logger logger = Logger.getLogger(AccountingInterfaceManagerBean.class.getName());
	static int exportFileSequenceNumber;
    static boolean serviceUnitPostedForCase = false;
    /*
    private DbUser dbUser;
    private int locationSelected;
    private ActionErrors errors;
    private Date dateFrom; 
    private Date dateTo;
    private boolean companyWideFile;
    private int ACCOUNTING_INTERFACE; 
	*/
	public AccountingInterfaceManagerBean() { }
	
	/**
	 * 
	 * @param ACCOUNTING_INTERFACE
	 * @param dbUser
	 * @param locationSelected
	 * @return
	 */
	public synchronized String [] createAccountingInterface(
			int ACCOUNTING_INTERFACE, DbUser dbUser, int locationSelected,
			ActionErrors errors, Date dateFrom, Date dateTo, Date monthEnd, boolean companyWideFile) {
		
		logger.debug("/** createAccountingInterface **/");
		
		AccountingInterfaceDTO aiDTO = null;
		aiDTO = createAccountingInterfaceDTO(ACCOUNTING_INTERFACE, dbUser, locationSelected, 
				errors, dateFrom, dateTo, monthEnd, companyWideFile);
		
		generateAccountingInterface(aiDTO);
		
		return (aiDTO.getInterfaceFiles());
	}
	
	public synchronized AccountingInterfaceDTO createAccountingInterfaceDTO (int ACCOUNTING_INTERFACE, 
			DbUser dbUser, int locationSelected, ActionErrors errors, Date dateFrom, Date dateTo, Date monthEnd,
			boolean companyWideFile) {
		
		AccountingInterface accountingInterface = AccountingInterfaceFactory.getAccountingInterface(ACCOUNTING_INTERFACE);
		
		String baseDir = AccountingInterfaceUtil.getFileBaseDir(dbUser);
		
		String [] interfaceFiles = accountingInterface.getInterfaceFileName(ACCOUNTING_INTERFACE, 
				locationSelected, dbUser);
		
		int count = 0;
		for ( String fileName : interfaceFiles ) {
			fileName = baseDir + fileName;
			interfaceFiles[count] = fileName;
			logger.debug("FileName : " + fileName);
			count++;
		}
		
		AccountingInterfaceDTO accountingInterfaceDTO = null;
		accountingInterfaceDTO = new AccountingInterfaceDTO();
		accountingInterfaceDTO.setInterfaceType(ACCOUNTING_INTERFACE);
		accountingInterfaceDTO.setLocationSelected(locationSelected);
		accountingInterfaceDTO.setSalesTranList(new ArrayList());
		accountingInterfaceDTO.setCompanyWideFile(companyWideFile);
		accountingInterfaceDTO.setInterfaceFiles(interfaceFiles);
		accountingInterfaceDTO.setAccountingInterface(accountingInterface);
		accountingInterfaceDTO.setDbUser(dbUser);
		accountingInterfaceDTO.setDateFrom(dateFrom);
		accountingInterfaceDTO.setDateTo(dateTo);
		accountingInterfaceDTO.setDateMonthEnd(monthEnd);
		accountingInterfaceDTO.setErrors(errors);
		
		if ( interfaceFiles.length > 0 ) {
			accountingInterfaceDTO.setFileName( interfaceFiles[0] );
		}
		
		return ( accountingInterfaceDTO );
	}
	
	public void generateAccountingInterface (AccountingInterfaceDTO aiDTO) {

		try {		
			logger.debug("Initializing export");
			
			// if ACCPAC interface, user createAccountingInterfaceXML() method and return file name to exit
			if (aiDTO.getInterfaceType() == Constants.INTERFACE_PEOPLESOFT_KEYSTONE) {
				generateAccountingInterfaceKeystone(aiDTO);
			}
			else {
				generateAccountingInterfaceStandard(aiDTO);
			}
			
		} catch (Exception e) {
			logger.error("Exception in createAccountingInterface() : ", e);
		}
	}
	
	private void generateAccountingInterfaceStandard (AccountingInterfaceDTO aiDTO) throws Exception {
		
		String fileName = "";
		String [] interfaceFiles = aiDTO.getInterfaceFiles(); 
		if ( interfaceFiles.length > 0 ) {
			fileName = interfaceFiles[0];
		} else {
			throw new Exception ( "Unable to create Interface File" );
		}
		
		AccountingInterface ai = aiDTO.getAccountingInterface();
		
		ai.initializeExport(fileName.toString(), aiDTO.getInterfaceType());
		
		if (aiDTO.getInterfaceType() == Constants.INTERFACE_ACCPAC) {
			createAccountingInterfaceXML( aiDTO, fileName );
		} else if ( aiDTO.getInterfaceType() == Constants.INTERFACE_QUICKBOOKS_XML ) {
			createAccountingInterfaceQBWebConnectXML( aiDTO, fileName );
		} else {
			populateCashSaleHistory(ai, aiDTO, fileName );
			populateMiscCashReceipt(ai, aiDTO, fileName );
			populateCheckWriteRecords(ai, aiDTO, fileName );
			populateInvoiceRecords(ai, aiDTO, fileName);
		}
		
		ai.closeFile(fileName.toString());
	}
	
	private void generateAccountingInterfaceKeystone (AccountingInterfaceDTO aiDTO) throws Exception {
		
		String [] interfaceFiles = aiDTO.getInterfaceFiles(); 
		
		if ( interfaceFiles.length < 3 ) {
			throw new Exception ( "Unable to create Interface Files for Keystone" );
		} 
		
		String glAcctsFile = interfaceFiles[AccountingInterfacePeoplesoftKeystone.KEYSTONE_GLACCTS_FILE];
		String acctsPayableFile = interfaceFiles[AccountingInterfacePeoplesoftKeystone.KEYSTONE_ACCTSPAYBL_FILE];
		String acctsRecvFile = interfaceFiles[AccountingInterfacePeoplesoftKeystone.KEYSTONE_ACCTSRECV_FILE];
		
		AccountingInterface ai = aiDTO.getAccountingInterface();
		
		ai.initializeExport(glAcctsFile, aiDTO.getInterfaceType());
		ai.initializeExport(acctsPayableFile, aiDTO.getInterfaceType());
		ai.initializeExport(acctsRecvFile, aiDTO.getInterfaceType());
		
		populateCashSaleHistory(ai, aiDTO,  acctsRecvFile);
		//populateMiscCashReceipt(ai, aiDTO,  glAcctsFile);
		//populateCheckWriteRecords(ai, aiDTO, acctsPayableFile );
		//populateInvoiceRecords(ai, aiDTO, acctsPayableFile);
		
		ai.closeFile(glAcctsFile);
		ai.closeFile(acctsPayableFile);
		ai.closeFile(acctsRecvFile);
	}
	
	/**
	 * 
	 * @param dbUser
	 * @param locationId
	 * @param fileName
	 * @param accountingInterface
	 */
	private void createAccountingInterfaceXML( AccountingInterfaceDTO aiDTO, String fileName ) {
		
		AccountingInterfaceDAO accountingInterfaceDAO = new AccountingInterfaceDAO();
		ArrayList vitalIds = null;
		
		DbUser dbUser = aiDTO.getDbUser();
		
		if ( aiDTO.isCompanyWideFile() ) {
			vitalIds = accountingInterfaceDAO.getUnpostedVitalsIdByCompany(
					dbUser.getCompanyID(), dbUser.getDbLookup() );
					
		} else {
			vitalIds = accountingInterfaceDAO.getUnpostedVitalsIds(
					dbUser.getRegion(), aiDTO.getLocationSelected(), dbUser.getDbLookup() );
		}
		
		if (vitalIds != null) {
			Iterator it = vitalIds.iterator();
			while (it.hasNext()) {
				aiDTO.setCaseId(((Integer) it.next()).intValue());
				aiDTO.setFileName(fileName);
				
				AccountingInterface ai = aiDTO.getAccountingInterface(); 
				ai.writeSummaryTran(dbUser, aiDTO, aiDTO.getErrors());
			}
		}
	}
	
	/**
	 * 
	 * @param dbUser
	 * @param locationId
	 * @param fileName
	 * @param accountingInterface
	 */
	public ArrayList <Integer> createAccountingInterfaceQBWebConnectXML( AccountingInterfaceDTO aiDTO, String fileName ) {
		
		AccountingInterfaceDAO accountingInterfaceDAO = new AccountingInterfaceDAO();
		ArrayList <Integer> vitalIds = null;
		
		DbUser dbUser = aiDTO.getDbUser();
		
		if ( aiDTO.isCompanyWideFile() ) {
			vitalIds = accountingInterfaceDAO.getUnpostedVitalsIdByCompany(
					dbUser.getCompanyID(), dbUser.getDbLookup() );
					
		} else {
			vitalIds = accountingInterfaceDAO.getUnpostedVitalsIds(
					dbUser.getRegion(), aiDTO.getLocationSelected(), dbUser.getDbLookup() );
		}
		
		return ( vitalIds );
	}
	
	/**
	 * 
	 * @param dbUser
	 * @param locationId
	 * @param fileName
	 * @param accountingInterface
	 */
	public QBVitalsRecordInfo processVitalsRecordQBWebConnectXML( AccountingInterfaceDTO aiDTO, String fileName, Integer vitalsID ) throws Exception {
		
		aiDTO.setCaseId(vitalsID);
		aiDTO.setFileName(fileName);

		AccountingInterface ai = aiDTO.getAccountingInterface();
		DbUser dbUser = aiDTO.getDbUser();
		QBVitalsRecordInfo qbSI = null;
		
		if (ai instanceof AccountingInterfaceQuickbooksXML) {
			AccountingInterfaceQuickbooksXML aiqb = (AccountingInterfaceQuickbooksXML) ai;
			qbSI = aiqb.generateQuickbooksCustomerSummaryTrans(dbUser, aiDTO, aiDTO.getErrors());
		}
		
		return ( qbSI );
	}
	
	private synchronized void populateCashSaleHistory (AccountingInterface accountingInterface, 
			AccountingInterfaceDTO accountingInterfaceDTO, String fileName) throws Exception { 
		
		DatabaseTransaction t = null;
		AccountingInterfaceDTO groupAIDTO = null;
		
		try {
			DbHistory[] dbHistory = null;
			
			String [] interfaceFiles = null;
			
			if ( accountingInterfaceDTO != null ) {
				interfaceFiles = accountingInterfaceDTO.getInterfaceFiles();
			}
			
			if ( interfaceFiles == null ) {
				interfaceFiles = new String [1];
				interfaceFiles[0] = fileName;
			}
			
			DbUser dbUser = accountingInterfaceDTO.getDbUser();
			
			DbHistorySet history = new DbHistorySet();
			Hashtable <String, Object> h = new Hashtable <String, Object> ();
			h.put(DbHistoryPeer.POSTED, "N"); // get records not yet
														  // interfaced
			if ( accountingInterfaceDTO.isCompanyWideFile() ) {
				h.put(DbHistorySetPeer.COMPANYID, Integer.toString(dbUser.getCompanyID()));
				
			} else {
				h.put(DbCasePeer.LOCALE, Integer.toString(dbUser.getRegion())); // for specified Locale
				
				// retrieve all accounts for a given chapel location
				if (accountingInterfaceDTO.getLocationSelected() > 0) {
					h.put(DbCasePeer.CHAPELNUMBER, Integer.toString(accountingInterfaceDTO.getLocationSelected()));
				}
				
			}
			
			if ( accountingInterfaceDTO.getDateFrom() != null ) {
				h.put(DbHistorySetPeer.TRANS_DATE_FROM, accountingInterfaceDTO.getDateFrom());
			}
			
			if ( accountingInterfaceDTO.getDateTo() != null ) {
				h.put(DbHistorySetPeer.TRANS_DATE_TO, accountingInterfaceDTO.getDateTo());
			}
			
			
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(dbUser);
			
			history.restore(t, h);
			PersistentI[] obs = history.getPersistents();
			dbHistory = new DbHistory[obs.length];
		
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				dbHistory[i] = (DbHistory) obs[i];
			}
			
			// Initialize for first iteration
			int groupKey = 0;
			char prevDelete = ' ';
		
			// Process list of case sale and cash history transactions
			for (int i = 0; i < dbHistory.length; i++) {
				// check for end of group processing.
				// gInterface not null if we are already processing a group
				if (groupAIDTO != null
						&& (dbHistory[i].getLMainKey() != groupKey 
							|| dbHistory[i].getCHistOriginalPosting() != 'Y' 
							|| dbHistory[i].getCHistDeleteTran() != prevDelete)
					) {
		
					logger.debug("Writing summaryTran");
					accountingInterface.writeSummaryTran(
							dbUser,
							groupAIDTO,
							accountingInterfaceDTO.getErrors());
					
					groupAIDTO = null;
				}
		
				prevDelete = dbHistory[i].getCHistDeleteTran();
				groupKey = dbHistory[i].getLMainKey();
				// Check if we are starting a group of original posted
				// transactions
				if (dbHistory[i].getCHistOriginalPosting() == 'Y') {
					// Are we starting a new group?
					if (groupAIDTO == null) {
						groupAIDTO = new AccountingInterfaceDTO();
						groupAIDTO.setInterfaceType(accountingInterfaceDTO.getInterfaceType());
						groupAIDTO.setLocationSelected(accountingInterfaceDTO.getLocationSelected());
						groupAIDTO.setFileName(fileName);
						groupAIDTO.setCaseId(dbHistory[i].getLMainKey());
						groupAIDTO.setSalesTranList(new ArrayList());
						groupAIDTO.setInterfaceFiles(interfaceFiles);
						groupAIDTO.setDateMonthEnd(accountingInterfaceDTO.getDateMonthEnd());
					}
					
					logger.debug("Writing salesTran");
					accountingInterface.writeSalesTran(dbHistory[i], dbUser, groupAIDTO);
					logger.debug("Items in TranSales : " + groupAIDTO.getSalesTranList().size());
				}
				// if not processing a group of original posted transactions...
				else {
					logger.debug("Writing adjustment");
					accountingInterface.writeAdjustment(
							dbHistory[i], fileName, accountingInterfaceDTO.getInterfaceType(), dbUser, accountingInterfaceDTO);
				}
				// set posted flag for this history transaction
				dbHistory[i].setCHistPosted('Y');
				t.addPersistent(dbHistory[i]);
			}
			// end of list, check if need to finish last group
			if (groupAIDTO != null) {
				logger.debug("Writing summaryTran");
				accountingInterface.writeSummaryTran(dbUser, groupAIDTO, accountingInterfaceDTO.getErrors());
				groupAIDTO = null;
			}
			t.save();
			
		} finally {
			if ( t != null ) {
        		t.closeConnection();
        		t = null;
        	}
		}
		
	}
	

	private synchronized void populateMiscCashReceipt (AccountingInterface accountingInterface, 
			AccountingInterfaceDTO aiDTO, String fileName) throws Exception { 
		
		DatabaseTransaction t = null;
		
		try {
			DbUser dbUser = aiDTO.getDbUser();
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(dbUser);

			DbHistory[] dbHistory = null;
			
			Hashtable <String, Object> h = new Hashtable <String, Object> ();
			// Now get list of unposted misc cash receipt transactions
			h.put(DbHistoryPeer.SPFCODE, "R");

			if ( aiDTO.isCompanyWideFile() ) {
				h.put(DbHistorySetPeer.COMPANYID, Integer.toString(dbUser.getCompanyID()));
				
			} else {
				h.put(DbCasePeer.LOCALE, Integer.toString(dbUser.getRegion())); // for specified Locale
				
				// retrieve all accounts for a given chapel location
				if (aiDTO.getLocationSelected() > 0) {
					h.put(DbHistoryPeer.LOCATIONID, Integer.valueOf(aiDTO.getLocationSelected()));
				}
				
			}

			if ( aiDTO.getDateFrom() != null ) {
				h.put(DbHistorySetPeer.TRANS_DATE_FROM, aiDTO.getDateFrom());
			}
			
			if ( aiDTO.getDateTo() != null ) {
				h.put(DbHistorySetPeer.TRANS_DATE_TO, aiDTO.getDateTo());
			}
			
			DbHistorySet history = new DbHistorySet();
			history.restore(t, h);
			PersistentI[] obs = history.getPersistents();
			dbHistory = new DbHistory[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				dbHistory[i] = (DbHistory) obs[i];
			}
			// Process list of misc cash receipts
			for (int i = 0; i < dbHistory.length; i++) {
				logger.debug("Writing miscCashReceipt");
				aiDTO.getAccountingInterface().writeMiscCashReceipt(
						dbHistory[i], fileName, aiDTO.getInterfaceType(), dbUser, aiDTO);
				// set posted flag for this history transaction
				dbHistory[i].setCHistPosted('Y');
				t.addPersistent(dbHistory[i]);
			}
		
			t.save();
			
		} finally {
			if ( t != null ) {
        		t.closeConnection();
        		t = null;
        	}
		}
		
	}
	
	private synchronized void populateCheckWriteRecords (AccountingInterface accountingInterface, 
				AccountingInterfaceDTO aiDTO, String fileName) throws Exception { 
		
		DatabaseTransaction t = null;
		boolean result = false;
		
		try {
			//-------------------------------------------------------
			// Now process all Check Writer expense transactions not yet 
			// interfaced.
			DbUser dbUser = aiDTO.getDbUser();
			
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(dbUser);
			DbApDistributionHistory[] checks;
			DbApDistributionHistorySet set = new DbApDistributionHistorySet();
			// retrieve all unposted AP checks
			Hashtable <String, Object> h = new Hashtable <String, Object> ();
			
/*			if ( aiDTO.isCompanyWideFile() ) {
				h.put(DbApDistributionHistorySetPeer.COMPANYID, Integer.toString(dbUser.getCompanyID()));
				
			} else {
				h.put(DbApMasterPeer.LOCALEID, Integer.toString(dbUser.getRegion()));
				
				// retrieve all accounts for a given chapel location
				if (aiDTO.getLocationSelected() > 0) {
					h.put(DbApMasterPeer.LOCATIONID, Integer.valueOf(aiDTO.getLocationSelected()));
				}
				
			}
			
			h.put(DbApDistributionHistoryPeer.POSTED, "N");
*/
			h.put(DbApDistributionHistoryPeer.POSTED, "N"); // get records not yet
					  // interfaced
			if (aiDTO.isCompanyWideFile() ) {
				h.put(DbApDistributionHistorySetPeer.COMPANYID, Integer.toString(dbUser.getCompanyID()));
			} else {
				h.put(DbCasePeer.LOCALE, Integer.toString(dbUser.getRegion())); // for specified Locale
			
				// retrieve all accounts for a given chapel location
				if (aiDTO.getLocationSelected() > 0) {
					h.put(DbCasePeer.CHAPELNUMBER, Integer.toString(aiDTO.getLocationSelected()));
				}
			}
			
			if (aiDTO.getDateFrom() != null ) {
				h.put(DbHistorySetPeer.TRANS_DATE_FROM, aiDTO.getDateFrom());
			}
			
			if (aiDTO.getDateTo() != null ) {
				h.put(DbHistorySetPeer.TRANS_DATE_TO, aiDTO.getDateTo());
			}

			if ( aiDTO.getDateFrom() != null ) {
				h.put(DbApDistributionHistorySetPeer.APDIST_DATE_FROM, aiDTO.getDateFrom());
			}
			
			if ( aiDTO.getDateTo() != null ) {
				h.put(DbApDistributionHistorySetPeer.APDIST_DATE_TO, aiDTO.getDateTo());
			}
			
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			checks = new DbApDistributionHistory[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				checks[i] = (DbApDistributionHistory) obs[i];
			}
			// Process list of vendor checks
			for (int i = 0; i < checks.length; i++) {
				logger.debug("Wrirting apExpense");
				result = aiDTO.getAccountingInterface().writeApExpense(checks[i], fileName,	aiDTO.getInterfaceType(), dbUser, aiDTO);
				  // If the record was exported successfully then updated the posted flag
				if (result == true) {
					// set posted flag for this history transaction
					checks[i].setPosted("Y");
					t.addPersistent(checks[i]);
				}
			}
		
			t.save();		
			
		} finally {
			if ( t != null ) {
        		t.closeConnection();
        		t = null;
        	}
		}
		
	}

	
	private synchronized void populateInvoiceRecords (AccountingInterface accountingInterface, 
			AccountingInterfaceDTO aiDTO, String fileName) throws Exception { 
	
	DatabaseTransaction t = null;
	
	try {
		//-------------------------------------------------------
		// Now process all Check Writer expense transactions not yet 
		// interfaced.
		DbUser dbUser = aiDTO.getDbUser();
		
		t = (DatabaseTransaction) DatabaseTransaction.getTransaction(dbUser);
		DbInvoiceTransHist [] invoiceTransHist;
		DbInvoiceTransHistSet set = new DbInvoiceTransHistSet();
		// retrieve all unposted AP Invoices
		Hashtable <String, Object> h = new Hashtable <String, Object> ();
		
		h.put(DbApMasterPeer.LOCALEID, Integer.toString(dbUser.getRegion()));
			
		// retrieve all accounts for a given chapel location
		if (aiDTO.getLocationSelected() > 0) {
			h.put(DbApMasterPeer.LOCATIONID, Integer.valueOf(aiDTO.getLocationSelected()));
		}
		
		h.put(DbInvoiceTransHistPeer.POSTED, "N");
	 
		set.restore(t, h);
		PersistentI[] obs = set.getPersistents();
		invoiceTransHist = new DbInvoiceTransHist[obs.length];
		// This trick is needed to make a Persistent[] into a DbHistory[]
		for (int i = 0; i < obs.length; i++) {
			invoiceTransHist[i] = (DbInvoiceTransHist) obs[i];
		}
		// Process list of vendor checks
		for (int i = 0; i < invoiceTransHist.length; i++) {
			logger.debug("Wrirting apInvoiceExpense");
			aiDTO.getAccountingInterface().writeApInvoiceExpense(invoiceTransHist[i], fileName,	
								aiDTO.getInterfaceType(), dbUser, aiDTO);
			// set posted flag for this history transaction
			invoiceTransHist[i].setPosted('Y');
			t.addPersistent(invoiceTransHist[i]);
		}
	
		t.save();		
		
	} catch ( Exception e){
		int a = 1;
		int b = 1;
		int c = 1;
		int d = 1;
	
	}finally {
		if ( t != null ) {
      		t.closeConnection();
      		t = null;
      	}
	}
	
}

	
	
	
	static String getLocationDescriptor (int locationSel, DbUser user) {
		
		StringBuilder locDesc = new StringBuilder();
		DatabaseTransaction t = null;
		try {
            if (locationSel > 0){
            	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
                DbLocation location = FdmsDb.getInstance().getLocation(t, locationSel);
                if (location != null){
                    String loc = location.getName();
                    loc = loc.replace(' ','_');
                    loc = loc.replace('"','_');
                    loc = loc.replace('\'','_');
                    locDesc.append(loc);
                } else {
                	locDesc.append("notfound");
                }
            } else {
            	locDesc.append("ALL");
            }
        } catch (PersistenceException e){
        	locDesc.append(e.toString());
        } finally {
        	if (t != null) {
        		t.closeConnection();
        		t = null;
        	}
        }
        
        return ( locDesc.toString() );
	}
	
}
