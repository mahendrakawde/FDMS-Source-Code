package com.aldorsolutions.webfdms.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.dao.InvoiceItemDAO;
import com.aldorassist.webfdms.dao.LocaleDAO;
import com.aldorassist.webfdms.dao.ObituaryFuneralSyncDAO;
import com.aldorassist.webfdms.dao.PresentationImageDAO;
import com.aldorassist.webfdms.dao.SpeedDataDAO;
import com.aldorassist.webfdms.model.InvoiceItemDTO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorassist.webfdms.model.PresentationImageDTO;
import com.aldorassist.webfdms.model.SMSSchedulingDTO;
import com.aldorassist.webfdms.model.SMSStoreProcDTO;
import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.beans.cache.SpeedDataCache;
import com.aldorsolutions.webfdms.beans.cache.Cache.Entry;
import com.aldorsolutions.webfdms.beans.comparators.SpeedDataComparator;
import com.aldorsolutions.webfdms.beans.custom.FinanceCharge;
import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.beans.display.FuneralSyncListBean;
import com.aldorsolutions.webfdms.beans.inventory.DbInvVersion;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabasePeer;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentI;
import com.aldorsolutions.webfdms.reporting.dao.SMSSchedulingDAO;
import com.aldorsolutions.webfdms.reporting.dao.SMSStoreProcDAO;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.DAO;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webfdms.util.JulianDay;
import com.aldorsolutions.webfdms.util.OptionsList;
import com.aldorsolutions.webfdms.util.TaxType;
import com.aldorsolutions.webfdms.util.TempFinanceCharges;
import com.aldorsolutions.webfdms.util.UtilSingleton;
import com.callfire.SMSService.ArrayOfString;
import com.callfire.SMSService.ObjectFactory;
import com.callfire.SMSService.SMSService;


/**
 * Application server for FDMS data base. All FDMS data classes should be
 * instantiated from "get" methods of this class so proper initialization can be
 * done. 5/17/01- Changed to a "Singleton" pattern. Use the getInstance() method
 * to get the single instance of this object. Creation date: (6/29/00 8:56:19
 * AM)
 * 
 * @author: R. Davidson
 * update : 03/03/09
 */
public class FdmsDb {

	private static FdmsDb instance = new FdmsDb();

	// constants used in postFinancial()
	public static int FINANCIAL_SAVE_UNPOSTED = 0;

	public static int FINANCIAL_FIRST_POST = 1;

	public static int FINANCIAL_POST_ADJUST = 2;
    public static int FINANCIAL_SALE_TYPE_CHANGE = 3;

	private static Logger logger = Logger.getLogger(FdmsDb.class.getName());
	
	DbLocaleConfigType localeConfigTypes [] = null;

	/**
	 * Constructor is private to suport "Singleton" pattern. Obtain an instance
	 * of this class via the getInstance() method.
	 */
	private FdmsDb() {

		super();
		//initLogs();
		//initDB();
	}

	/**
	 * Add a transaction to the financial history. Creation date: (4/25/2002
	 * 3:29:15 PM)
	 * 
	 * @return int
	 * @param t
	 *            DatabaseTransaction
	 * @param type
	 *            java.lang.String
	 * @param description
	 *            java.lang.String
	 * @param amount
	 *            int
	 * @param glAcct
	 *            java.lang.String
	 * @exception PersistenceException
	 *                The exception description.
	 */
	public int addHistorySaleTran(DatabaseTransaction t, int vitalsID, int type, String description, int amount,
			String glAcct, int exemptAmount, String taxCode, Date trandate, String spfCode, String categoryType,
			String invName, int invSeqno, int invOnHandID, int priceListID, int costofsale, int action, int taxAmount, int sequence)
			throws PersistenceException {

		DbCase caseinfo = FdmsDb.getInstance().getCase(t, vitalsID);
		if (caseinfo == null) {
			throw new PersistenceException("addHistorySaleTran: " + "Invalid vitals-ID : " + vitalsID);
		}

		DbLocation chapel = FdmsDb.getInstance().getLocation(t, caseinfo.getChapelNumber());
		if (chapel == null) {
			throw new PersistenceException("addHistorySaleTran: " + "Invalid chapel location specified for case : "
					+ vitalsID);
		}

		// remove these persistents from the transaction since they are not
		// being saved
		t.removePersistent(chapel);
		t.removePersistent(caseinfo);

		//get servicedate to transaction
		DbVitalsDeceased deceased = null;
		deceased = FdmsDb.getInstance().getVitalsDeceased(t, vitalsID);
		//String serviceDate = deceased.getDateOfBurial();
		String serviceDate = deceased.getServiceDateKey();
		long createdTimestamp = System.currentTimeMillis();

		
		
		DbHistory hist = new DbHistory();
		hist.setNew();
		t.addPersistent(hist);
		hist.setLMainKey(vitalsID);
		hist.setIHistType((short) type);
		hist.setCHistDesc(description);
		hist.setLHistAmount(amount);
		hist.setCHistGLAcct(glAcct);
		hist.setCHistARacct(chapel.getArAcct());
		hist.setLHistExempt(exemptAmount);
		hist.setCHistTaxCode(taxCode);
		hist.setCHistDate(trandate);
		hist.setCHistSPF(spfCode.charAt(0));
		hist.setCHistItemType(categoryType);
		hist.setCHistInvItemName(invName);
		hist.setLHistInvSeqNo(invSeqno);
		hist.setPriceListID(priceListID);
		hist.setInvOnHandID(invOnHandID);
		hist.setLHistCostOfSale(costofsale);
		hist.setTaxAmount(taxAmount);
		hist.setLocationId(caseinfo.getChapelNumber());
		hist.setServiceDate(serviceDate);
		hist.setDatetimeOfTrans(createdTimestamp);
		if (action == FINANCIAL_FIRST_POST) {
			hist.setCHistOriginalPosting('Y');
		} else {
			hist.setCHistOriginalPosting('N');
		}
		hist.setSequence(sequence);

		return 0;
	}

	
	/**
	 * Add Data to SpeedData Table for a given category and locale. Creation
	 * date: (1/20/02 12:08:05 PM)
	 * 
	 * @param user
	 *            com.aldorsolutions.webfdms.beans.DbUserSession user object to retrieve transaction
	 *            and locale
	 * @param category
	 *            java.lang.String SpeedData TabCategory to select
	 * @param data
	 *            String
	 */
	public void addSpeedDataRow(DbUserSession user, String category, String data, int sortSequence) {
		
		DatabaseTransaction t = null;
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			DbSpeedData newRow = new DbSpeedData();
			newRow.setNew();
			newRow.setCategory(category);
			newRow.setData(data);
			newRow.setLocale(user.getRegion());
			
			int locationId = 0;

			if (user.getLocalizedSpeedData()) {

				locationId = user.getLocationId();

				if (user.getCurrentCaseID() > 0) {
					DbCase dbCase = FdmsDb.getInstance().getCase(t, user.getCurrentCaseID());
					if (dbCase != null) {
						locationId = dbCase.getChapelNumber(); 
					}
				}

				DbSpeedData[] speedDataOptionsLocation = FdmsDb.getInstance().getSpeedDataLocation(user.getDbLookup(), user.getRegion(), 
						locationId, category, null);
				
				if ((speedDataOptionsLocation == null || speedDataOptionsLocation.length == 0) && (locationId > 0)) {
					locationId = 0;
				}
				
			}
			
			newRow.setLocationId(locationId);
			
			newRow.setSortSequence(sortSequence);
			t.addPersistent(newRow);
			t.save();
			
			SpeedDataCache.getInstance().setRefresh(user.getDbLookup());
		} catch (PersistenceException e) {
			logger.error("getSpeedDataOptionList Exception:", e);
		}
		finally {
			t.closeConnection();
			t = null;
		}
		
	}

	/**
	 * Calculate the state local and article sales tax amounts for a collection of
	 * charges. Creation date: (2/28/2003 3:49:31 PM)
	 * 
	 * @param user com.aldorsolutions.webfdms.beans.DbUser
	 * @param param Map
	 */
	@SuppressWarnings("unchecked")
//	public void calculateSalesTax(DbUser user, Map charges, String version, int vitalsid) throws Exception {
//
//		// the total cost of the casket and vault for tennessee tax purposes
//		logger.debug("calculateSalesTax starting.");
//
//		FinancialInformationLineItem aCharge = null;
//		FinancialInformationLineItem stateCharge = null;
//		FinancialInformationLineItem localCharge = null;
//		FinancialInformationLineItem articleCharge = null;
//
//		Iterator myIterator = charges.values().iterator();
//		// get initialized collection of tax codes and rates
//		SalesTaxes taxlist = new SalesTaxes(user);
//		// Iterate through every charge in collection check its tax code
//		// and whether we are calculating on total or individually
//		while (myIterator.hasNext()) {
//			aCharge = (FinancialInformationLineItem) myIterator.next();
//			// Skip charges flagged for delettion
//			
//			DbChargeItem chargeItem = aCharge.getDbChargeItem();
//
//		
//			if (aCharge.getItemDeletion() == 1) {
//				
////				TaxType taxType = taxlist.addSaleAmt(aCharge.getItemTaxCode(), chargeItem.getAmount(),
////						chargeItem.getExemptAmount());
////				
////				int taxAmount = taxlist.calculateItemTaxes(taxType);
////				chargeItem.setTaxAmount(taxAmount);
//				
//				
//				continue;
//			}
//			
//
//			
//			// skip 98 and 99 sales tax lines
//			if (chargeItem.getType() == SalesTaxes.LOCAL_TAX_ID) {
//				localCharge = aCharge;
//				continue;
//			}
//			if (chargeItem.getType() == SalesTaxes.STATE_TAX_ID) {
//				stateCharge = aCharge;
//				continue;
//			}
//			if (chargeItem.getType() == SalesTaxes.ARTICLE_TAX_ID) {
//				articleCharge = aCharge;
//				continue;
//			}
//			
//			
//			// process this charge item through the sales tax object
//				TaxType taxType = taxlist.addSaleAmt(aCharge.getItemTaxCode(), chargeItem.getAmount(),
//						chargeItem.getExemptAmount());
//				
//				int taxAmount = taxlist.calculateItemTaxes(taxType);
//				chargeItem.setTaxAmount(taxAmount);
//				
//				logger.debug( chargeItem.getDescription() + 
//						" Price: " + aCharge.getItemPrice() + 
//						" Tax Code: " + chargeItem.getTaxCode() + 
//						" Exempt Amount " + chargeItem.getExemptAmount() + 
//						" Tax Amount: " + chargeItem.getTaxAmount() 
//					);
//			
//			
//		}
//
//		// Get tax amounts
//		int stateTax = taxlist.calculateStateSalesTax();
//		logger.debug("State tax : " + stateTax);
//
//		int localTax = taxlist.calculateLocalSalesTax();
//		logger.debug("Local tax : " + localTax);
//
//		int articleTax = taxlist.calculateArticleSalesTax();
//		logger.debug("Article tax : " + articleTax);
//
//		// if we have a state sales tax amount, make sure line 99 is in the
//		// charge collection
//		if (stateTax != 0 && (stateCharge == null)) {
//			// add charge 99
//			stateCharge = taxlist.addStateTaxCharge(charges, user, version, vitalsid);
//		}
//		// if we have a local sales tax amount, make sure line 98 is in the
//		// charge collection
//		if (localTax != 0 && (localCharge == null)) {
//			// add charge 98
//			localCharge = taxlist.addLocalTaxCharge(charges, user, version, vitalsid);
//		}
//		// if we have a article sales tax amount, make sure line 97 is in the
//		// charge collection
//		if (articleTax != 0 && (articleCharge == null)) {
//			// add charge 97
//			articleCharge = taxlist.addArticleTaxCharge(charges, user, version, vitalsid);
//		}
//		// if there is a state tax, set the amount (which may be zero)
//		if (stateCharge != null) {
//			stateCharge.getDbChargeItem().setAmount(stateTax);
//			stateCharge.setModifiedItem(true);
//		}
//		// ditto for local tax
//		if (localCharge != null) {
//			localCharge.getDbChargeItem().setAmount(localTax);
//			localCharge.setModifiedItem(true);
//		}
//		// oh why don't we do it for article tax also
//		if (articleCharge != null) {
//			articleCharge.getDbChargeItem().setAmount(articleTax);
//			articleCharge.setModifiedItem(true);
//		}
//	}

//	public void calculateSalesTax(DbUserSession sessionUser, Map charges, String version, int vitalsid ) throws Exception {
//
//		// the total cost of the casket and vault for tennessee tax purposes
//		logger.debug("calculateSalesTax starting.");
//
//		FinancialInformationLineItem aCharge = null;
//		FinancialInformationLineItem stateCharge = null;
//		FinancialInformationLineItem localCharge = null;
//		FinancialInformationLineItem articleCharge = null;
//
//		Iterator myIterator = charges.values().iterator();
//		// get initialized collection of tax codes and rates
//		DbUser user = (DbUser) sessionUser;
//		SalesTaxes taxlist = new SalesTaxes(user);
//		// Iterate through every charge in collection check its tax code
//		// and whether we are calculating on total or individually
//		
//		//get data from charges table.
////		DatabaseTransaction t = null;
////		t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
////		FdmsDb fdmsdb = FdmsDb.getInstance();
////		Map financialInformationMap = (HashMap) fdmsdb.getChargesForCase(t, vitalsid);
////		Iterator fiIterator = financialInformationMap.values().iterator();
//		
//		TransactionhistoryDAO transDao = new TransactionhistoryDAO(user);
//		ArrayList <TransactionhistoryDTO> transes = new ArrayList <TransactionhistoryDTO>();
//		transes = transDao.getTransactionhistory(vitalsid);
//		boolean postedTran = false;
//		DatabaseTransaction t = null;
//
//		try { // check if there is deposit posted to transaction history but not posted case
//
//			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
//			DbFinancialSummary finan = getFinancial(t, vitalsid);
//			if (finan.getIARsentBox() == 0){ // not posted case.
//				postedTran  = false;
//			}else {
//				postedTran = true;
//			}
//			
//			
//		} catch (PersistenceException pe) {
//			logger.error("Persistence Exception in ShowFinancial.doPerform. " + pe);
//			
//		} catch (Exception pe) {
//			logger.error("Exception in  ShowFinancial .doPerform. ", pe);
//			
//		} finally {
//			if (t != null)
//				t.closeConnection();
//		}
//		
//		while (myIterator.hasNext()) {
//			aCharge = (FinancialInformationLineItem) myIterator.next();
//			// Skip charges flagged for delettion
//			
//			DbChargeItem chargeItem = aCharge.getDbChargeItem();
//				
//			if (aCharge.getItemDeletion() == 1) {		
//				continue;
//			}
//	
//			// skip 98 and 99 sales tax lines
//			if (chargeItem.getType() == SalesTaxes.LOCAL_TAX_ID) {
//				localCharge = aCharge;
//				continue;
//			}
//			if (chargeItem.getType() == SalesTaxes.STATE_TAX_ID) {
//				stateCharge = aCharge;
//				continue;
//			}
//			if (chargeItem.getType() == SalesTaxes.ARTICLE_TAX_ID) {
//				articleCharge = aCharge;
//				continue;
//			}
//			
////			if (financialInformationMap.containsKey(chargeItem.getSequenceNumber())) {
////				continue;
////			}
//			// process this charge item through the sales tax object
//			// check trans size because if it is not posted case we need to calculate them.
//			if (aCharge.isModifiedItem() || aCharge.isNewItem() || transes.size() <= 0 || !postedTran) {
//			
//				TaxType taxType = taxlist.addSaleAmt(aCharge.getItemTaxCode(), chargeItem.getAmount(),
//						chargeItem.getExemptAmount());
//				
//				int taxAmount = taxlist.calculateItemTaxes(taxType);
//				chargeItem.setTaxAmount(taxAmount);
//				
//				logger.debug( chargeItem.getDescription() + 
//						" Price: " + aCharge.getItemPrice() + 
//						" Tax Code: " + chargeItem.getTaxCode() + 
//						" Exempt Amount " + chargeItem.getExemptAmount() + 
//						" Tax Amount: " + chargeItem.getTaxAmount() 
//					);
//			
//			}
//			
//		}
//
//
//		int tranStateTax = 0;
//		int tranLocalTax = 0;
//		int tranArticleTax = 0;
//		//we didn't calculate the old items so we need to get tax from transactionhistory.
//		for(TransactionhistoryDTO tran: transes) {
//			if (tran.getChargeType() == SalesTaxes.STATE_TAX_ID) { //statetax
//				tranStateTax = tranStateTax + tran.getAmountOfTran(); 
//			}
//			if (tran.getChargeType() == SalesTaxes.LOCAL_TAX_ID) { //localtax
//				tranLocalTax = tranLocalTax + tran.getAmountOfTran();
//			}
//			if (tran.getChargeType() == SalesTaxes.ARTICLE_TAX_ID) { //articletax
//				tranArticleTax = tranArticleTax + tran.getAmountOfTran();
//			}
//		}
//		
//		// calculate the delete item for tax if it is posted case.
//		SalesTaxes taxDelete = new SalesTaxes(user);
//		if (transes.size() > 0 && (tranStateTax > 0 || tranLocalTax > 0 || tranArticleTax > 0)) {
//			 myIterator = charges.values().iterator();
//			 
//			 while (myIterator.hasNext()) {
//					aCharge = (FinancialInformationLineItem) myIterator.next();
//					// Skip charges flagged for delettion
//					
//					DbChargeItem chargeItem = aCharge.getDbChargeItem();
//						
//					if (aCharge.getItemDeletion() == 1 && (aCharge.isModifiedItem() && !aCharge.isNewItem())) {	
//						TaxType taxType = taxDelete.addSaleAmt(aCharge.getItemTaxCode(), chargeItem.getAmount(),
//								chargeItem.getExemptAmount());
//						
//						int taxAmount = taxlist.calculateItemTaxes(taxType);
//						chargeItem.setTaxAmount(taxAmount);
//						
//					}
//			 }
//		}
//		
//		// Get tax amounts
//		int stateTax = taxlist.calculateStateSalesTax() + tranStateTax - taxDelete.calculateStateSalesTax();
//		logger.debug("State tax : " + stateTax);
//
//		int localTax = taxlist.calculateLocalSalesTax() + tranLocalTax - taxDelete.calculateLocalSalesTax();
//		logger.debug("Local tax : " + localTax);
//
//		int articleTax = taxlist.calculateArticleSalesTax() + tranArticleTax - taxDelete.calculateArticleSalesTax();
//		logger.debug("Article tax : " + articleTax);
//
//		// if we have a state sales tax amount, make sure line 99 is in the
//		// charge collection
//		if (stateTax != 0 && (stateCharge == null)) {
//			// add charge 99
//			stateCharge = taxlist.addStateTaxCharge(charges, user, version, vitalsid);
//		}
//		// if we have a local sales tax amount, make sure line 98 is in the
//		// charge collection
//		if (localTax != 0 && (localCharge == null)) {
//			// add charge 98
//			localCharge = taxlist.addLocalTaxCharge(charges, user, version, vitalsid);
//		}
//		// if we have a article sales tax amount, make sure line 97 is in the
//		// charge collection
//		if (articleTax != 0 && (articleCharge == null)) {
//			// add charge 97
//			articleCharge = taxlist.addArticleTaxCharge(charges, user, version, vitalsid);
//		}
//		// if there is a state tax, set the amount (which may be zero)
//		if (stateCharge != null) {
//			stateCharge.getDbChargeItem().setAmount(stateTax);
//			stateCharge.setModifiedItem(true);
//		}
//		// ditto for local tax
//		if (localCharge != null) {
//			localCharge.getDbChargeItem().setAmount(localTax);
//			localCharge.setModifiedItem(true);
//		}
//		// oh why don't we do it for article tax also
//		if (articleCharge != null) {
//			articleCharge.getDbChargeItem().setAmount(articleTax);
//			articleCharge.setModifiedItem(true);
//		}
//	}
//	
		
	
	
	
	public void calculateSalesTax(DbUserSession sessionUser, Map charges, String version, int vitalsid ) throws Exception {

		// the total cost of the casket and vault for tennessee tax purposes
		logger.debug("calculateSalesTax starting.");

		FinancialInformationLineItem aCharge = null;
		FinancialInformationLineItem stateCharge = null;
		FinancialInformationLineItem localCharge = null;
		FinancialInformationLineItem articleCharge = null;

//		Iterator myIterator = charges.values().iterator();
		// get initialized collection of tax codes and rates
		DbUser user = (DbUser) sessionUser;
		SalesTaxes taxlist = new SalesTaxes(user);
		// Iterate through every charge in collection check its tax code
		// and whether we are calculating on total or individually
		
		//get data from charges table.
		
		TransactionhistoryDAO transDao = new TransactionhistoryDAO(user);
		ArrayList <TransactionhistoryDTO> transes = new ArrayList <TransactionhistoryDTO>();
		transes = transDao.getTransactionhistory(vitalsid);
		boolean postedTran = false;
		DatabaseTransaction t = null;

		try { // check if there is deposit posted to transaction history but not posted case

			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			DbFinancialSummary finan = getFinancial(t, vitalsid);
			if (finan == null || finan.getIARsentBox() == 0){ // not posted case.
				postedTran  = false;
			}else {
				postedTran = true;
			}
			
			
		} catch (PersistenceException pe) {
			logger.error("Persistence Exception in ShowFinancial.doPerform. " + pe);
			
		} catch (Exception pe) {
			logger.error("Exception in  ShowFinancial .doPerform. ", pe);
			
		} finally {
			if (t != null)
				t.closeConnection();
		}
		
//		//loop getting charge ID of tax, we will set the charge table having only one of each kind of taxes
////		int stateTax = 0;
////		int localTax = 0;
////		int articleTax = 0;
//		boolean hasStateTax = false;
//		boolean hasLocalTax = false;
//		boolean hasArticleTax = false;
//		Iterator myIteratorForTax = charges.values().iterator();
//		while (myIteratorForTax.hasNext()) {
////			aFinanItem = (FinancialInformationLineItem) myIteratorForTax.next();
//			aCharge = (FinancialInformationLineItem) myIteratorForTax.next();
//			DbChargeItem chargeItem = aCharge.getDbChargeItem();
//			int chargeID = chargeItem.getId();
//			if (chargeID > 0 && chargeItem.getType() == 99) {	
//				hasStateTax = true;
//				stateCharge = aCharge;
////				stateTax = aCharge.getAmount();
//			}
//			if (chargeID > 0 && chargeItem.getType() == 98) {
//				hasLocalTax = true;
//				localCharge = aCharge;
////				localTax = aCharge.getAmount();
//			}
//			if (chargeID > 0 && chargeItem.getType() == 97) {
//				hasArticleTax = true;
//				articleCharge = aCharge;
////				articleTax = aCharge.getAmount();
//			}
//		}
		//loop getting charge ID of tax, we will set the charge table having only one of each kind of taxes
		int newStateTax = 0;
		int newLocalTax = 0;
		int newArticleTax = 0;
		boolean hasStateTax = false;
		boolean hasLocalTax = false;
		boolean hasArticleTax = false;
		Iterator myIteratorForTax = charges.values().iterator();
		while (myIteratorForTax.hasNext()) {
//			aFinanItem = (FinancialInformationLineItem) myIteratorForTax.next();
			aCharge = (FinancialInformationLineItem) myIteratorForTax.next();
			DbChargeItem chargeItem = aCharge.getDbChargeItem();
			int chargeID = chargeItem.getId();
			if (chargeID > 0 && chargeItem.getType() == 99 && chargeItem.getAmount() > 0 && aCharge.getItemDeletion() != 1) {	
				hasStateTax = true;
				stateCharge = aCharge;
//				stateTax = aCharge.getAmount();
			}
			if (chargeID > 0 && chargeItem.getType() == 98 && chargeItem.getAmount() > 0 && aCharge.getItemDeletion() != 1) {
				hasLocalTax = true;
				localCharge = aCharge;
//				localTax = aCharge.getAmount();
			}
			if (chargeID > 0 && chargeItem.getType() == 97 && chargeItem.getAmount() > 0 && aCharge.getItemDeletion() != 1) {
				hasArticleTax = true;
				articleCharge = aCharge;
//				articleTax = aCharge.getAmount();
			}
		}
		
		int countState = 0;
		int countLocal = 0;
		int countArticle = 0;
		myIteratorForTax = charges.values().iterator();
		while (myIteratorForTax.hasNext()) {
			aCharge = (FinancialInformationLineItem) myIteratorForTax.next();
			DbChargeItem chargeItem = aCharge.getDbChargeItem();
//			int chargeID = chargeItem.getId();
			
			if (chargeItem.getType() == 99 && aCharge.getItemDeletion() != 1) {
				countState = countState + 1;
			}
			if (chargeItem.getType() == 98 && aCharge.getItemDeletion() != 1) {
				countLocal = countLocal + 1;
			}
			if (chargeItem.getType() == 97 && aCharge.getItemDeletion() != 1) {
				countArticle = countArticle + 1;
			}
			if (chargeItem.getType() == 99 && !hasStateTax && aCharge.getItemDeletion() != 1) {	
				hasStateTax = true;
				stateCharge = aCharge;
//				stateTax = aCharge.getAmount();
			}
			if (chargeItem.getType() == 98 && !hasLocalTax && aCharge.getItemDeletion() != 1) {
				hasLocalTax = true;
				localCharge = aCharge;
//				localTax = aCharge.getAmount();
			}
			if (chargeItem.getType() == 97 && !hasArticleTax && aCharge.getItemDeletion() != 1) {
				hasArticleTax = true;
				articleCharge = aCharge;
//				articleTax = aCharge.getAmount();
			}
		}		
		
		Iterator myIterator = charges.values().iterator();
		while (myIterator.hasNext()) {
			aCharge = (FinancialInformationLineItem) myIterator.next();
			// Skip charges flagged for delettion
			
			DbChargeItem chargeItem = aCharge.getDbChargeItem();
				
			if (aCharge.getItemDeletion() == 1) {		
				continue;
			}
	
			// skip 98 and 99 sales tax lines
			if (chargeItem.getType() == SalesTaxes.LOCAL_TAX_ID  && (localCharge.getDbChargeItem().getSequenceNumber() != chargeItem.getSequenceNumber())) {
				continue;
			}
			if (chargeItem.getType() == SalesTaxes.STATE_TAX_ID && (stateCharge.getDbChargeItem().getSequenceNumber() != chargeItem.getSequenceNumber())) {
				continue;
			}
			if (chargeItem.getType() == SalesTaxes.ARTICLE_TAX_ID && (articleCharge.getDbChargeItem().getSequenceNumber() != chargeItem.getSequenceNumber())) {
				continue;
			}			
			
			
//			if (financialInformationMap.containsKey(chargeItem.getSequenceNumber())) {
//				continue;
//			}
			// process this charge item through the sales tax object
			// check trans size because if it is not posted case we need to calculate them.
			if (aCharge.isModifiedItem() || aCharge.isNewItem() || transes.size() <= 0 || !postedTran) {
			
				TaxType taxType = taxlist.addSaleAmt(aCharge.getItemTaxCode(), chargeItem.getAmount(),
						chargeItem.getExemptAmount());
				
				int taxAmount = taxlist.calculateItemTaxes(taxType);
				chargeItem.setTaxAmount(taxAmount);
				
				logger.debug( chargeItem.getDescription() + 
						" Price: " + aCharge.getItemPrice() + 
						" Tax Code: " + chargeItem.getTaxCode() + 
						" Exempt Amount " + chargeItem.getExemptAmount() + 
						" Tax Amount: " + chargeItem.getTaxAmount() 
					);
			
			}
			
		}


		int tranStateTax = 0;
		int tranLocalTax = 0;
		int tranArticleTax = 0;
		//we didn't calculate the old items so we need to get tax from transactionhistory.
		for(TransactionhistoryDTO tran: transes) {
			if (tran.getChargeType() == SalesTaxes.STATE_TAX_ID) { //statetax
				tranStateTax = tranStateTax + tran.getAmountOfTran(); 
			}
			if (tran.getChargeType() == SalesTaxes.LOCAL_TAX_ID) { //localtax
				tranLocalTax = tranLocalTax + tran.getAmountOfTran();
			}
			if (tran.getChargeType() == SalesTaxes.ARTICLE_TAX_ID) { //articletax
				tranArticleTax = tranArticleTax + tran.getAmountOfTran();
			}
		}
		
		// calculate the delete item for tax if it is posted case.
		SalesTaxes taxDelete = new SalesTaxes(user);
		if (transes.size() > 0 && (tranStateTax > 0 || tranLocalTax > 0 || tranArticleTax > 0)) {
			 myIterator = charges.values().iterator();
			 
			 while (myIterator.hasNext()) {
					aCharge = (FinancialInformationLineItem) myIterator.next();
					// Skip charges flagged for delettion
					
					DbChargeItem chargeItem = aCharge.getDbChargeItem();
						
					if (aCharge.getItemDeletion() == 1 && (aCharge.isModifiedItem() && !aCharge.isNewItem())) {	
						TaxType taxType = taxDelete.addSaleAmt(aCharge.getItemTaxCode(), chargeItem.getAmount(),
								chargeItem.getExemptAmount());
						
						int taxAmount = taxlist.calculateItemTaxes(taxType);
						chargeItem.setTaxAmount(taxAmount);
						
					}
			 }
		}
		
		// Get tax amounts
		int stateTax = taxlist.calculateStateSalesTax() + tranStateTax - taxDelete.calculateStateSalesTax();
		logger.debug("State tax : " + stateTax);

		int localTax = taxlist.calculateLocalSalesTax() + tranLocalTax - taxDelete.calculateLocalSalesTax();
		logger.debug("Local tax : " + localTax);

		int articleTax = taxlist.calculateArticleSalesTax() + tranArticleTax - taxDelete.calculateArticleSalesTax();
		logger.debug("Article tax : " + articleTax);

		// if we have a state sales tax amount, make sure line 99 is in the
		// charge collection
		if (stateTax != 0 && (stateCharge == null)) {
			// add charge 99
			stateCharge = taxlist.addStateTaxCharge(charges, user, version, vitalsid);
		}
		// if we have a local sales tax amount, make sure line 98 is in the
		// charge collection
		if (localTax != 0 && (localCharge == null)) {
			// add charge 98
			localCharge = taxlist.addLocalTaxCharge(charges, user, version, vitalsid);
		}
		// if we have a article sales tax amount, make sure line 97 is in the
		// charge collection
		if (articleTax != 0 && (articleCharge == null)) {
			// add charge 97
			articleCharge = taxlist.addArticleTaxCharge(charges, user, version, vitalsid);
		}
		// if there is a state tax, set the amount (which may be zero)
		if (stateCharge != null) {
//			!if (countState != 1 || stateTax != 0){
				stateCharge.getDbChargeItem().setAmount(stateTax);
//			}
			stateCharge.setModifiedItem(true);
		}
		// ditto for local tax
		if (localCharge != null) {
			localCharge.getDbChargeItem().setAmount(localTax);
			localCharge.setModifiedItem(true);
		}
		// oh why don't we do it for article tax also
		if (articleCharge != null) {
			articleCharge.getDbChargeItem().setAmount(articleTax);
			articleCharge.setModifiedItem(true);
		}
	}
	
	public void calculateSalesTaxPost(DbUserSession sessionUser, Map charges, String version, int vitalsid ) throws Exception {

		// the total cost of the casket and vault for tennessee tax purposes
		logger.debug("calculateSalesTax starting.");

		FinancialInformationLineItem aCharge = null;
		FinancialInformationLineItem stateCharge = null;
		FinancialInformationLineItem localCharge = null;
		FinancialInformationLineItem articleCharge = null;


		//loop getting charge ID of tax, we will set the charge table having only one of each kind of taxes
		int newStateTax = 0;
		int newLocalTax = 0;
		int newArticleTax = 0;
		boolean hasStateTax = false;
		boolean hasLocalTax = false;
		boolean hasArticleTax = false;
		Iterator myIteratorForTax = charges.values().iterator();

		while (myIteratorForTax.hasNext()) {
//			aFinanItem = (FinancialInformationLineItem) myIteratorForTax.next();
			aCharge = (FinancialInformationLineItem) myIteratorForTax.next();
			DbChargeItem chargeItem = aCharge.getDbChargeItem();
			int chargeID = chargeItem.getId();
			if (chargeID > 0 && chargeItem.getType() == 99 && chargeItem.getAmount() > 0 && aCharge.getItemDeletion() != 1) {	
				hasStateTax = true;
				stateCharge = aCharge;
//				stateTax = aCharge.getAmount();
			}
			if (chargeID > 0 && chargeItem.getType() == 98 && chargeItem.getAmount() > 0 && aCharge.getItemDeletion() != 1) {
				hasLocalTax = true;
				localCharge = aCharge;
//				localTax = aCharge.getAmount();
			}
			if (chargeID > 0 && chargeItem.getType() == 97 && chargeItem.getAmount() > 0 && aCharge.getItemDeletion() != 1) {
				hasArticleTax = true;
				articleCharge = aCharge;
//				articleTax = aCharge.getAmount();
			}
		}
		
		
		myIteratorForTax = charges.values().iterator();
		while (myIteratorForTax.hasNext()) {
			aCharge = (FinancialInformationLineItem) myIteratorForTax.next();
			DbChargeItem chargeItem = aCharge.getDbChargeItem();
//			int chargeID = chargeItem.getId();
			if (chargeItem.getType() == 99 && !hasStateTax && aCharge.getItemDeletion() != 1) {	
				hasStateTax = true;
				stateCharge = aCharge;
//				stateTax = aCharge.getAmount();
			}
			if (chargeItem.getType() == 98 && !hasLocalTax && aCharge.getItemDeletion() != 1) {
				hasLocalTax = true;
				localCharge = aCharge;
//				localTax = aCharge.getAmount();
			}
			if (chargeItem.getType() == 97 && !hasArticleTax && aCharge.getItemDeletion() != 1) {
				hasArticleTax = true;
				articleCharge = aCharge;
//				articleTax = aCharge.getAmount();
			}
		}
		
		
		Iterator myIteratorForTax1 = charges.values().iterator();
		while (myIteratorForTax1.hasNext()) {
//			aFinanItem = (FinancialInformationLineItem) myIteratorForTax.next();
			aCharge = (FinancialInformationLineItem) myIteratorForTax1.next();
			DbChargeItem chargeItem = aCharge.getDbChargeItem();
			int chargeID = chargeItem.getId();

			if ( (stateCharge != null) && chargeItem.getType() == 99 && (stateCharge.getDbChargeItem().getSequenceNumber() != chargeItem.getSequenceNumber()) && aCharge.getItemDeletion() != 1) {	
				newStateTax = newStateTax + chargeItem.getAmount();		
//				charges.remove(chargeItem.getSequenceNumber());
				aCharge.setItemDeletion(1);
//				myIteratorForTax1 = charges.values().iterator();
			}
			if ( (localCharge != null) && chargeItem.getType() == 98 && (localCharge.getDbChargeItem().getSequenceNumber() != chargeItem.getSequenceNumber()) && aCharge.getItemDeletion() != 1) {
				newLocalTax = newLocalTax + chargeItem.getAmount();
//				charges.remove(chargeItem.getSequenceNumber());
				aCharge.setItemDeletion(1);
//				myIteratorForTax1 = charges.values().iterator();
			}
			if ( (articleCharge != null) && chargeItem.getType() == 97 && (articleCharge.getDbChargeItem().getSequenceNumber() != chargeItem.getSequenceNumber()) && aCharge.getItemDeletion() != 1) {
				newArticleTax = newArticleTax + chargeItem.getAmount();
//				charges.remove(chargeItem.getSequenceNumber());
				aCharge.setItemDeletion(1);
//				myIteratorForTax1 = charges.values().iterator();
			}
//			if (chargeID <= 0 && chargeItem.getType() == 99 && !hasStateTax) {	
//				stateCharge = aCharge;	
//			}
//			if (chargeID > 0 && chargeItem.getType() == 98 && !hasLocalTax) {
//				localCharge = aCharge;
//			}
//			if (chargeID > 0 && chargeItem.getType() == 97 && !hasArticleTax) {
//				articleCharge = aCharge;
//			}
	
		}		
		// if there is a state tax, set the amount (which may be zero)
		if (stateCharge != null) {
			stateCharge.getDbChargeItem().setAmount(stateCharge.getDbChargeItem().getAmount() + newStateTax);
			stateCharge.setModifiedItem(true);
		}
		// ditto for local tax
		if (localCharge != null) {
			localCharge.getDbChargeItem().setAmount(localCharge.getDbChargeItem().getAmount() + newLocalTax);
			localCharge.setModifiedItem(true);
		}
		// oh why don't we do it for article tax also
		if (articleCharge != null) {
			articleCharge.getDbChargeItem().setAmount(articleCharge.getDbChargeItem().getAmount() + newArticleTax);
			articleCharge.setModifiedItem(true);
		}
	}	
	
	

	/**
	 * Check to see if the unique information for the case is already in use.
	 * This method takes the dbCase which has been set with the Id, localeId,
	 * and a string value of the name of the comparison field (ex:
	 * contractNumber). Creation date: (10/22/02 1:44:44 PM)
	 * 
	 * @param DatabaseTransaction t
	 * @param DbCase dbCase
	 * @param String checkField
	 * @return boolean
	 */
	public boolean checkCaseExists(DatabaseTransaction t, DbCase dbCase, String checkField) {

		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbCase thisCase = new com.aldorsolutions.webfdms.beans.DbCase();
		boolean caseExists = false;

		try {
			//AppLog.trace("FdmsDb::checkCaseExits checkField: "+checkField +"
			// working case = " +dbCase.getId());
			com.aldorsolutions.webfdms.beans.DbCaseSet caseset = new com.aldorsolutions.webfdms.beans.DbCaseSet();

			// Put User's Locale in hashtable for selection criteria
			h.put(DbCasePeer.LOCALE, String.valueOf(dbCase.getLocale()));

			// Look specififed comparison field used to check for duplicates
			if (checkField != null) {
				// Add the field and logic that you want to check for duplicates
				// in here...
				if (checkField.equals(DbCasePeer.CONTRACTCODE)) {
					h.put(DbCasePeer.CONTRACTCODE, dbCase.getContractCode());
					// AppLog.trace("FdmsDb::checkCaseExits starting. checkField
					// value: "+dbCase.getContractCode());

				}
				if (checkField.equals(DbCasePeer.CASECODE)) {
					h.put(DbCasePeer.CASECODE, dbCase.getCaseCode());
					// AppLog.trace("FdmsDb::checkCaseExits checkField value:
					// "+dbCase.getCaseCode());
				}
			}

			caseset.restore(t, h);
			PersistentI[] obs = caseset.getPersistents();

			// This trick is needed to make a Persistent[] into a DbCase
			for (int i = 0; i < obs.length; i++) {
				thisCase = (com.aldorsolutions.webfdms.beans.DbCase) obs[i];
				if (thisCase.getId() != dbCase.getId()) {
					// This checkField value already exists in another DbCase.
					// AppLog.trace("FdmsDb::checkCaseExits.checkField used in =
					// " +thisCase.getId());
//					caseExists = true;
//					break;
					
					// add this one to check that the casecode and the contractcode should not be duplicate on the locale level
					if (checkField != null) {
						if (checkField.equals(DbCasePeer.CONTRACTCODE)) {
							if (thisCase.getLocale() == dbCase.getLocale() && (thisCase.getVoidedContractCode() == null || thisCase.getVoidedContractCode().compareToIgnoreCase("V") != 0) ) {
								caseExists = true;
								break;
							}
						} else if (checkField.equals(DbCasePeer.CASECODE)) {
							if (thisCase.getLocale() == dbCase.getLocale() && (thisCase.getVoidedContractCode() == null || thisCase.getVoidedContractCode().compareToIgnoreCase("V") != 0)) {
								caseExists = true;
								break;
							}
						} else {
							caseExists = true;
							break;
						}
					}else {  
						caseExists = true;
						break;
					}
					
				}
			}
			return caseExists;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getCaseSet Persistence Exception:", e);
			return caseExists;
		}
	}

	/**
	 * Code to perform when this object is garbage collected.
	 * 
	 * Any exception thrown by a finalize method causes the finalization to
	 * halt. But otherwise, it is ignored.
	 */
	protected void finalize() throws Throwable {
		// Insert code to finalize the receiver here.
		// This implementation simply forwards the message to super. You may
		// replace or supplement this.
		super.finalize();
	}

	/**
	 * Add finance charge to a specified case. Creation date: (4/10/2003
	 * 10:29:30 AM)
	 * 
	 * @param user com.aldorsolutions.webfdms.beans.DbUser User identifing database and region to select
	 * @param aCharge FinanceCharge gives case, component, and amount
	 * @exception PersistenceException The exception description.
	 */
	public static void financeChargesApply(DbUser user, FinanceCharge aCharge, Date tranDate)
			throws PersistenceException {
		
		DatabaseTransaction t = null;
		DbComponent[] acomp = null;
		DbLocation chapel = null;
		DbCase caseinfo = null;
		DbFinancialSummary aFinan = null;

		//AppLog.trace("financeChargesApply starting");
		try {

			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			caseinfo = FdmsDb.getInstance().getCase(t, aCharge.getVitalsId());
			if (caseinfo == null) {
				throw new PersistenceException("Invalid Case ID:" + aCharge.getVitalsId());
			}
			chapel = FdmsDb.getInstance().getLocation(t, caseinfo.getChapelNumber());
			if (chapel == null) {
				throw new PersistenceException("Invalid Chapel Location for case:" + aCharge.getVitalsId());
			}
			aFinan = FdmsDb.getInstance().getFinancial(t, aCharge.getVitalsId());
			if (aFinan == null) {
				throw new PersistenceException("FinanceChargesCalculate Financial row Not Found for case:"
						+ aCharge.getVitalsId());
			}

			// update previous and current financial charges lines
			FdmsDb.financeChargesUpdateChargesTable(t, user, aCharge.getVitalsId(), aCharge.getFinanceChargeAmount(),
					tranDate);

			// update component,
			acomp = FdmsDb.getInstance().getComponentsForID(t, aCharge.getVitalsId(), aCharge.getComponentCode());
			if (acomp == null || acomp.length < 1) {
				throw new PersistenceException("No component " + aCharge.getComponentCode() + " for case "
						+ aCharge.getVitalsId());
			}
			t.addPersistent(acomp[0]);
			acomp[0].setSaleAmt(acomp[0].getSaleAmt() + aCharge.getFinanceChargeAmount());

			//update transaction history change current financial charge to previous financial charge.
			FdmsDb.getInstance().financeChargesUpdateTransactionhistoryTable(t,user,aCharge.getVitalsId(),tranDate);
			
			// add transaction history
			FdmsDb.getInstance().addHistorySaleTran(t, aCharge.getVitalsId(), DbChargeItem.CURRENT_FINANCE_CHARGE_ID,
					"Finance Charge", aCharge.getFinanceChargeAmount(), chapel.getFinanceChargeAcct(), 0, "",
					tranDate, "F", " ", "", 0, 0, 0, 0, FdmsDb.FINANCIAL_POST_ADJUST, 0, 0);

			//update the previouse financial charge.
			aFinan.setFinChargePreviousCalc(aFinan.getFClastRunDate());
			aFinan.setPreviousFinanceCharge(aFinan.getLLastFinanCharge());
			
			// update financial "last finance charge amount"-- add to if
			// tranDate=last-run-date
			// update FClastRunDate and
			int todaysFinanCharge = 0;
			String tranDateYMD = FormatDate.convertDateToYYYYMMDD(tranDate);
			if (tranDateYMD.compareTo(aFinan.getFClastRunDate()) == 0) {
				todaysFinanCharge = aFinan.getLLastFinanCharge();
			}
			aFinan.setLLastFinanCharge(todaysFinanCharge + aCharge.getFinanceChargeAmount());
			aFinan.setFClastRunDate(FormatDate.convertDateToYYYYMMDD(tranDate));
			aFinan.setLFinTotalCharges(aFinan.getLFinTotalCharges() + aCharge.getFinanceChargeAmount());
			
			// update all above with one transaction
			t.save();
		}
	    finally {
	    	if ( t != null ) {
	    		t.closeConnection();
	    		t = null;
	    	}
	    }

	}

	/**
	 * Calculate finance charges for a single case. Creation date: (4/7/2003
	 * 10:29:30 AM)
	 * 
	 * @return ArrayList Collection of finance charges by component for specified case
	 * @param toDate Date Date through which finance charges are calculated
	 * @param param com.aldorsolutions.webfdms.beans.DbUser User identifing database and region to select 
	 * @exception PersistenceException The exception description.
	 */
	public static ArrayList<FinanceCharge> financeChargesCalculate(Calendar fromDateDefault, Calendar thruDate, DbUser user, int caseID)
			throws PersistenceException {

		logger.debug("Entering financeChargesCalculate()");
		DatabaseTransaction t = null;
		DbLocation aLocation = null;
		DbVitalsDeceased aDeceased = null;
		DbVitalsInformant aInformant = null;
		DbCase aCase = null;
		DbFinancialSummary aFinan = null;
		DbVitalsFirstCall aFirstCall = null;
		ArrayList<FinanceCharge> chargeList = new ArrayList<FinanceCharge>();
		DbComponent[] complist;
		PaymentComponentAvailableSet compSet = null;
		FinanceCharge aCharge = null;

		SimpleDateFormat formattermdy = new SimpleDateFormat("MMddyyyy");
		SimpleDateFormat formatterymd = new SimpleDateFormat("yyyyMMdd");
		Calendar caseDueDate = Calendar.getInstance();
		Calendar caseFromDate = Calendar.getInstance();
		Calendar caseFcDue = Calendar.getInstance();
		Calendar caseLastRunDate = Calendar.getInstance();

		logger.debug("financeChargesCalculate starting for ID:" + caseID);
		if (caseID < 1) {
			return chargeList; // empty list
		}

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			compSet = new PaymentComponentAvailableSet(user.getRegion(), user.getDbLookup());
	
			aFirstCall = FdmsDb.getInstance().getVitalsFirstCall(t, caseID);
			aCase = FdmsDb.getInstance().getCase(t, caseID);
			if (aCase == null) {
				//AppLog.error("FinanceChargesCalculate Case Not Found");
				return chargeList;
			}
			aDeceased = FdmsDb.getInstance().getVitalsDeceased(t, caseID);
			aInformant = FdmsDb.getInstance().getVitalsInformant(t, caseID);
			aLocation = FdmsDb.getInstance().getLocation(t, aCase.getChapelNumber());
			if (aLocation == null) {
				//AppLog.error("FinanceChargesCalculate LOCATION Not Found for
				// case.");
				return chargeList;
			}
			aFinan = FdmsDb.getInstance().getFinancial(t, caseID);
			if (aFinan == null) {
				//AppLog.error("FinanceChargesCalculate Financial table row Not
				// Found for case");
				return chargeList;
			}
	
			// Verify this case is subject to finance charges
			// make sure case is not voided
			if (aCase.getVoidedContractCode().equalsIgnoreCase("V")) {
				return chargeList;
			}
			// make sure case is posted and has rate and applicable to finance
			// charges
			if (aFinan.getFFinServiceChargeRate() <= 0 || aFinan.getIARsentBox() == 0
					|| aFinan.getIFinServiceChargesBox() == 0) {
				return chargeList;
			}
			// Convert case due date to calendar
			java.util.Date parseDueDate = null;
			try {
				parseDueDate = formattermdy.parse(aFinan.getCFinDueDate());
			} catch (ParseException e) {
				logger.error("FinanceChargesCandidates Exception parsing financial due date:" + aFinan.getCFinDueDate()
						+ "\n" + e, e);
				return chargeList;
			}
	
			caseDueDate.setTime(parseDueDate);
			// Verify the case is past due
			if (caseDueDate.after(thruDate)) {
				//AppLog.trace("Case not yet past due date.");
				return chargeList;
			}
			// verify this case's finance charge due date is past
			java.util.Date parseInterestFrom = null;
			try {
				parseInterestFrom = formattermdy.parse(aFirstCall.getInterestFromDate());
			} catch (ParseException e) {
				logger.error("FinanceChargesCandidates Exception parsing Interest from date:"
						+ aFirstCall.getInterestFromDate() + "\n" + e);
				return chargeList;
			}
			caseFcDue.setTime(parseInterestFrom);
			if (caseFcDue.after(thruDate)) {
				//AppLog.trace("finance charges not yet due.");
				return chargeList;
			}
			// Initialize case-finance-charge-start-date to default from date
			caseFromDate.setTime(fromDateDefault.getTime());
			// Determine if this case's last run date (last time finance charges
			// run)
			// is after the default from date provided to this method.
			java.util.Date parseFromDate = null;
			try {
				parseFromDate = formatterymd.parse(aFinan.getFClastRunDate());
			} catch (ParseException e) {
				logger.error("FinanceChargesCandidates Exception parsing Last Run date:" + aFinan.getFClastRunDate() + "\n"
						+ e);
				// Set to default starting date
				parseFromDate = new Date(fromDateDefault.getTime().getTime());
			}
			caseLastRunDate.setTime(parseFromDate);
			if (caseLastRunDate.after(caseFromDate)) {
				caseFromDate.setTime(caseLastRunDate.getTime());
			}
			// Also adjust case starting date if interest-start-date after
			// default-start-date
			if (caseFromDate.before(caseFcDue)) {
				caseFromDate.setTime(caseFcDue.getTime());
				//AppLog.trace("Adjusting starting date to: "+formatterymd.format(
				// caseFromDate.getTime()));
			}
			// calculate number of elapsed days
			long elapsedDays = (thruDate.getTime().getTime() - caseFromDate.getTime().getTime()) / (60 * 60 * 24 * 1000);
	
			// compute the daily finance charge rate by dividing monthly rate
			double dailyRate = aFinan.getFFinServiceChargeRate() * 12.0 / 365.0;
			//AppLog.trace("Elapsed days="+elapsedDays+" daily rate:"+dailyRate);
	
			// Get components for this case and loop through components looking for
			// those subject to finance charges.
			complist = FdmsDb.getInstance().getComponentsForID(t, caseID);
			if (complist == null || complist.length < 1) {
				//AppLog.error("FinanceChargesCalculate: No Components found for
				// this case");
				return chargeList;
			}
			for (int i = 0; i < complist.length; i++) {
				// skip this component if sale amount less than paid amount
				if (complist[i].getSaleAmt() <= complist[i].getPaidAmt()) {
					continue;
				}
				// skip this component if not subject to finance charges
				if (!compSet.isFinanceChargesApplicable(complist[i].getCode())) {
					continue;
				}
				int currFinanceCharge = (int) (0.5 + (dailyRate * elapsedDays
						* (complist[i].getSaleAmt() - complist[i].getPaidAmt()) / 100));
				if (currFinanceCharge <= 0) {
					continue;
				}
				// construct a finance charge candidate object for this
				// case/component
				String billName = aInformant.getFname() + " " + aInformant.getLname();
				String saleDate = FormatDate.MDYtoMMDDYYYY(aFinan.getCFinSaleDate());
				String paidDate = FormatDate.MDYtoMMDDYYYY(aFinan.getCLastPmtDate());
				String balance = FormatCurrency.toCurrency(complist[i].getSaleAmt() - complist[i].getPaidAmt());
				aCharge = new FinanceCharge(caseID, aDeceased.getDecFullName(), billName, saleDate, paidDate, balance,
						currFinanceCharge, complist[i].getCode(), complist[i].getDescription());
				chargeList.add(aCharge);
			}
	
			// update previous and current financial charges lines
			// for each component, update component, add transaction history
			// update financial totals
			// update FClastRunDate
			// }
			return chargeList;
			
		} 
		finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
	}

	/**
	 * Build a collection of finance charges for cases subject to such. Creation
	 * date: (4/7/2003 10:29:30 AM)
	 * 
	 * @return fdms.ui.struts.TempFinanceCharges Collection of finance charge candidates
	 * @param toDate Date Date through which finance charges are calculated
	 * @param param com.aldorsolutions.webfdms.beans.DbUser User identifing database and region to select
	 * @exception PersistenceException The exception description.
	 */
	public static TempFinanceCharges financeChargesCandidates(Date fromDate, Date toDate, DbUser user)
			throws PersistenceException {

		logger.debug("Entering financeChargesCandidates()");
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		DatabaseTransaction t = null;
		TempFinanceCharges fcCandidates = new TempFinanceCharges();

		Calendar thruDate = Calendar.getInstance();
		thruDate.setTime(toDate);
		Calendar fromDateDefault = Calendar.getInstance();
		fromDateDefault.setTime(fromDate);

		//String sql = "select * from financialdata where CalcFinanceChrgYN>0
		// and FinanceChargeRate > 0 and SentToAccounting > 0 and TotalCharges >
		// TotalPaidToDate";
		StringBuffer sql = new StringBuffer();
		sql.append("select financialdata.VitalsMasterKey,CalcFinanceChrgYN, FinanceChargeRate, SentToAccounting, ");
		sql.append("totalCharges, TotalPaidToDate, LocaleNumber from financialdata,vitalstats where ");
		sql.append("financialdata.VitalsMasterKey = vitalstats.VitalsMasterKey and CalcFinanceChrgYN>0 ");
		sql.append("and FinanceChargeRate > 0 and SentToAccounting > 0 and TotalCharges > TotalPaidToDate ");
		sql.append("and LocaleNumber = ");

		int caseID = 0;

		//AppLog.trace("financeChargesCandidates starting");
		// get thru date
		fromDateDefault.setTime(fromDate);
		thruDate.setTime(toDate);
		// add four hours to thru date so daylight savings time does not become
		// a factor
		thruDate.add(Calendar.HOUR, 4);

		ArrayList<FinanceCharge> caseCharges;
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);

			// Get list of cases subject to finance charges
			connection = t.getConnection();
			stmt = connection.createStatement();
			logger.debug("SQL : " + sql.toString() + user.getRegion());
			rs = stmt.executeQuery(sql.toString() + user.getRegion());

			// Process list of cases
			while (rs.next()) {
				caseID = rs.getInt(DbFinancialSummaryPeer.CASE_ID);
				logger.debug("FinanceChargesCandidates processing " + caseID);
				if (caseID < 1) {
					continue;
				}
				caseCharges = financeChargesCalculate(fromDateDefault, thruDate, user, caseID);
				fcCandidates.add(caseCharges);
			}
		} catch (SQLException e) {
			logger.error("FinanceChargesCandidates SQLException: ", e);
		} finally {
			if (t != null) {
				t.closeConnection();
				t = null;
			}
		}

		return fcCandidates;
	}

	/**
	 * Update charges table for specified case with given amount. Finance
	 * charges balance-forwarded from "current" to "previous" IF tranDate is not
	 * equal to financial-FC-last-run-date. Creation date: (4/10/2003 10:29:30
	 * AM)
	 * 
	 * @param param com.aldorsolutions.webfdms.beans.DbUser User identifing database and region to select
	 * @param fcAmount int finance charge amount
	 * @param caseID int Case identifier
	 * @exception PersistenceException The exception description.
	 */
	public static void financeChargesUpdateChargesTable(DatabaseTransaction t, DbUser user, int caseID, int fcAmount,
			Date tranDate) throws PersistenceException {
		DbChargeItem currCharge = null;
		DbChargeItem prevCharge = null;
		HashMap<Integer, DbChargeItem> chargeMap = null;
		DbChargeItem acharge = null;
		DbCase caseinfo = null;
		DbLocation chapel = null;
		DbFinancialSummary aFinan = null;
		Iterator<DbChargeItem> myIterator = null;
		int currAmount = 0;

		caseinfo = FdmsDb.getInstance().getCase(t, caseID);
		if (caseinfo == null) {
			throw new PersistenceException("Invalid Case ID:" + caseID);
		}
		chapel = FdmsDb.getInstance().getLocation(t, caseinfo.getChapelNumber());
		chargeMap = FdmsDb.getInstance().getChargesForCase(t, caseID);
		aFinan = FdmsDb.getInstance().getFinancial(t, caseID);
		if (aFinan == null) {
			throw new PersistenceException("FinanceChargesCalculate Financial row Not Found for case:" + caseID);
		}
		// Determine if adding to current-finance-charge record or rolling
		// current to previous
		boolean accumulateInsteadOfRoll = false;
		int todaysFinanCharge = 0;
		String tranDateYMD = FormatDate.convertDateToYYYYMMDD(tranDate);
		if (tranDateYMD.compareTo(aFinan.getFClastRunDate()) == 0) {
			accumulateInsteadOfRoll = true;
		}

		myIterator = chargeMap.values().iterator();
		// find current-finance-charge record and previous-finance-charge record
		while (myIterator.hasNext()) {
			acharge = myIterator.next();
			if (acharge.getType() == DbChargeItem.CURRENT_FINANCE_CHARGE_ID) {
				currCharge = acharge;
				currAmount = acharge.getAmount();
				if (accumulateInsteadOfRoll) {
					todaysFinanCharge = acharge.getAmount();
				}
			}
			if (acharge.getType() == DbChargeItem.PREVIOUS_FINANCE_CHARGE_ID) {
				prevCharge = acharge;
			}
		}
		// If there already was a current charge amount, add to the previous
		// charge record
		// unless processing additional components on the same date
		if (currAmount != 0 && !accumulateInsteadOfRoll) {
			if (prevCharge == null) {
				// need to add a previous-finance-charge record
				prevCharge = new DbChargeItem(caseID, DbChargeItem.PREVIOUS_FINANCE_CHARGE_ID, 0,
						DbChargeItem.PREVIOUS_FINANCE_CHARGE_ID, "Previous Finance Charges", 0, chapel
								.getFinanceChargeAcct(), 0, "", 'F', "", " ", 0, 0, 0,false,0);
				prevCharge.setNew();
			}
			t.addPersistent(prevCharge);
			prevCharge.setAmount(prevCharge.getAmount() + currAmount);
		}
		// Update last finance charge record, Add if not present
		if (currCharge == null) {
			// Add a new current charge
			currCharge = new DbChargeItem(caseID, DbChargeItem.CURRENT_FINANCE_CHARGE_ID, 0,
					DbChargeItem.CURRENT_FINANCE_CHARGE_ID, "Last Finance Charge", 0, chapel.getFinanceChargeAcct(), 0,
					"", 'F', "", " ", 0, 0, 0,false,0);
			currCharge.setNew();
		}
		t.addPersistent(currCharge);
		currCharge.setAmount(fcAmount + todaysFinanCharge);
		currCharge.setDescription("Last Finance Charge " + FormatDate.convertDateToMM_DD_YYYY(tranDate));
		// t.save(); saved in calling method
	}

	/**
	 * Update transactionhistory table for specified case. Finance
	 * charges balance-forwarded from "current" to "previous" IF tranDate is not
	 * equal to financial-FC-last-run-date. Creation date: (Chai: 06/11/09 10:29:30
	 * AM)
	 * 
	 */
	public static void financeChargesUpdateTransactionhistoryTable(DatabaseTransaction t, DbUser user, int caseID, Date tranDate) throws PersistenceException {
		TransactionhistoryDAO transDao = new TransactionhistoryDAO(user);
		ArrayList <TransactionhistoryDTO> transes = new ArrayList <TransactionhistoryDTO>();
		transes = transDao.getTransactionhistory(caseID);
		String tranDateYMD = FormatDate.convertDateToYYYYMMDD(tranDate);
		for(TransactionhistoryDTO tran: transes) {
			String dateYMD = FormatDate.convertDateToYYYYMMDD(tran.getDateOfTran());
			if ((tran.getChargeType() == DbChargeItem.CURRENT_FINANCE_CHARGE_ID) && (dateYMD.compareToIgnoreCase(tranDateYMD) != 0)) {
				DbHistory hist = FdmsDb.getInstance().getHistory(t, tran.getTranHistID());
				hist.setIHistType((short)DbChargeItem.PREVIOUS_FINANCE_CHARGE_ID);
				t.addPersistent(hist);
			}
		}
	}
	
	/**
	 * Create an instanace of the ApAccount class representing one GL expense
	 * account. Creation date: (4/30/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbApAccount
	 */
	public DbApAccount getApAccount(DatabaseTransaction t, int recid) {
		DbApAccount p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbApAccount) DbApAccount.getPersistent(t, recid, "com.aldorsolutions.webfdms.beans.DbApAccount");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getApAccount Persistence Exception:", e);
			return null;
		}
	}

	/**
	 * Create a list of ApAccounts objects for a Locale or location Creation
	 * date: (12/31/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbArrangers[]
	 */
	public DbApAccount[] getApAccounts(DatabaseTransaction t, int locale, int location) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbApAccount[] list;
		try {
			DbApAccountSet set = new DbApAccountSet();
			// retrieve all accounts for a given locale
			// locale of zero means all locales.
			if (locale > 0) {
				h.put(DbApAccountPeer.LOCALEID, new Integer(locale));
			}
			// retrieve all accounts for a given chapel location
			if (location > 0) {
				h.put(DbApAccountPeer.LOCATIONID, new Integer(location));
			}
			h.put(DbApAccountPeer.GROUPBYIDNAME, new Boolean(false));
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbApAccount[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbApAccount) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getApAccounts Persistence Exception:", e);
			return null;
		}
	}
	public DbApAccount[] getApAccounts(DatabaseTransaction t, int locale, int location, boolean groupbyIdName) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbApAccount[] list;
		try {
			DbApAccountSet set = new DbApAccountSet();
			// retrieve all accounts for a given locale
			// locale of zero means all locales.
			if (locale > 0) {
				h.put(DbApAccountPeer.LOCALEID, new Integer(locale));
			}
			// retrieve all accounts for a given chapel location
			if (location > 0) {
				h.put(DbApAccountPeer.LOCATIONID, new Integer(location));
			}
				h.put(DbApAccountPeer.GROUPBYIDNAME, new Boolean(groupbyIdName));
				
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbApAccount[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbApAccount) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getApAccounts Persistence Exception:", e);
			return null;
		}
	}
	
	public DbApAccount[] getApAccounts(DatabaseTransaction t, String AccountNumber, String Description) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbApAccount[] list;
		try {
			DbApAccountSet set = new DbApAccountSet();
			// retrieve all accounts for a given locale
			// locale of zero means all locales.
			if (AccountNumber != null && AccountNumber.length() > 0) {
				h.put(DbApAccountPeer.ACCTNO, AccountNumber);
			}
			// retrieve all accounts for a given chapel location
			if (Description != null && Description.length() > 0) {
				h.put(DbApAccountPeer.DESCRIPTION, Description);
			}
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbApAccount[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbApAccount) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getApAccounts Persistence Exception:", e);
			return null;
		}
	}
	
	
	/**
	 * Create an instanace of the ApMaster class representing one AP Check.
	 * Creation date: (4/30/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbApMaster
	 */
	public DbApMaster getApCheck(DatabaseTransaction t, int recid) {
		DbApMaster p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbApMaster) DbApMaster.getPersistent(t, recid, "com.aldorsolutions.webfdms.beans.DbApMaster");
			t.addPersistent(p); // make the persistent part of the transaction
			p.setTrans(t);
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getApMaster Persistence Exception:", e);
			return null;
		}
	}

	public DbApDistribution getApDistribution(DatabaseTransaction t, int recid) {
		DbApDistribution p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbApDistribution) DbApDistribution.getPersistent(t, recid, "com.aldorsolutions.webfdms.beans.DbApDistribution");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getApMaster Persistence Exception:", e);
			return null;
		}
	}

	
	/**
	 * Create an instanace of the ApMaster class representing one AP Check.
	 * Creation date: (4/30/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbApMaster
	 */
	public DbInvoice getInvoice(DatabaseTransaction t, int invoiceID) {
		DbInvoice p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbInvoice) DbInvoice.getPersistent(t, invoiceID, "com.aldorsolutions.webfdms.beans.DbInvoice");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getApMaster Persistence Exception:", e);
			return null;
		}
	}
	
	public DbInvoiceItems getInvoiceItem(DatabaseTransaction t, int invoiceItemID) {
		DbInvoiceItems p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbInvoiceItems) DbInvoice.getPersistent(t, invoiceItemID, "com.aldorsolutions.webfdms.beans.DbInvoiceItems");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getApMaster Persistence Exception:", e);
			return null;
		}
	}

	/**
	 * Create a list of ApMaster objects for a Locale or location Creation date:
	 * (4/30/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbApMaster[]
	 */
	public DbApMaster[] getApCheckSet(DatabaseTransaction t, int locale, int location, long checknumber,
			boolean includeVoids, Date fromDate) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbApMaster[] list;
		try {
			DbApMasterSet set = new DbApMasterSet();
			// retrieve all accounts for a given locale
			// locale of zero means all locales.
			if (locale > 0) {
				h.put(DbApMasterPeer.LOCALEID, new Integer(locale));
			}
			// retrieve all accounts for a given chapel location
			if (location > 0) {
				h.put(DbApMasterPeer.LOCATIONID, new Integer(location));
			}
			// retrieve a specific check number
			if (checknumber > 0) {
				h.put(DbApMasterPeer.CHECKNUMBER, new Long(checknumber));
			}
			// Include voids? default is to omit voids unless this key is
			// present
			if (includeVoids) {
				h.put(DbApMasterPeer.VOIDEDCODE, "V");
			}
			
			if (fromDate != null) {
				h.put(DbApMasterPeer.CHECKDATE, fromDate);
			}
			
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbApMaster[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbApMaster) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getApMaster Persistence Exception:", e);
			return null;
		}
	}

	/**
	 * Create an instanace of the ApVendor class representing one Vendor.
	 * Creation date: (4/30/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbApVendor
	 */
	public DbApVendor getApVendor(DatabaseTransaction t, int recid) {
		DbApVendor p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbApVendor) DbApVendor.getPersistent(t, recid, "com.aldorsolutions.webfdms.beans.DbApVendor");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//   logger.error("FdmsDb::getApVendor Persistence Exception:", e);
			return null;
		}
	}

	/**
	 * Create a list of ApVendor objects for a Locale. Creation date: (12/31/01
	 * 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbVendor[]
	 */
	public DbApVendor[] getApVendorSet(DatabaseTransaction t, int locale, int locationid) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbApVendor[] list;
		try {
			DbApVendorSet set = new DbApVendorSet();
			// retrieve all accounts for a given locale
			// locale of zero means all locales.
			if (locale > 0) {
				h.put(DbApVendorPeer.LOCALE, new Integer(locale));
			}
			// retrieve all accounts for a given locationID
			if (locationid > 0) {
				h.put(DbApVendorPeer.LOCATIONID, new Integer(locationid));
			}
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbApVendor[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbApVendor) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getApVendor Persistence Exception:", e);
			return null;
		}
	}

	/**
	 * Create an instanace of the DbArranger class for one specific record.
	 * Creation date: (2/4/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbArrangers
	 */
	public DbArrangers getArranger(DatabaseTransaction t, int identity) {
		DbArrangers p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbArrangers) Persistent.getPersistent(t, identity, "com.aldorsolutions.webfdms.beans.DbArrangers");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getArranger Persistence Exception:", e);
			return null;
		}
	}

	/**
	 * Checks for the existance of this Arranger by passing a DbArrangers
	 * instance with pre-set values to check against. If returns true, the
	 * arranger already exists in the database. Creation date: (9/09/02 10:47:17
	 * AM)
	 * 
	 * @return boolean
	 */
	public boolean getArrangerExists(DbUserSession sessionUser, DbArrangers dbArranger) {

		boolean arrangerExists = false;
		DatabaseTransaction t = null;
		com.aldorsolutions.webfdms.beans.DbArrangers[] list = null;
		String[] fieldNames = null;
		String[] fieldValues = null;
		int fieldcount = 0;

		logger.debug("Called getArrangerExists()");

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);

			// initialize the 2 arrays...
			if (dbArranger.getBurialLicenseNumber() != null)
				fieldcount++;
			if (dbArranger.getEmbalmerLicenseNumber() != null)
				fieldcount++;
			if (dbArranger.getLicenseNumber() != null)
				fieldcount++;
			if (dbArranger.getPnLicenseNumber() != null)
				fieldcount++;
			if (dbArranger.getSocialSecurityNo() != null)
				fieldcount++;

			fieldNames = new String[fieldcount];
			fieldValues = new String[fieldcount];

			int fc = 0;
			if (dbArranger.getBurialLicenseNumber() != null) {
				fieldNames[fc] = "BurialLicenseNumber";
				fieldValues[fc] = dbArranger.getBurialLicenseNumber();
				fc++;
			}
			;

			if (dbArranger.getEmbalmerLicenseNumber() != null) {
				fieldNames[fc] = "EmbalmerLicenseNumber";
				fieldValues[fc] = dbArranger.getEmbalmerLicenseNumber();
				fc++;
			}
			;

			if (dbArranger.getLicenseNumber() != null) {
				fieldNames[fc] = "LicenseNumber";
				fieldValues[fc] = dbArranger.getLicenseNumber();
				fc++;
			}
			;

			if (dbArranger.getPnLicenseNumber() != null) {
				fieldNames[fc] = "PnLicenseNumber";
				fieldValues[fc] = dbArranger.getPnLicenseNumber();
				fc++;
			}
			;

			if (dbArranger.getSocialSecurityNo() != null) {
				fieldNames[fc] = "SocSecNo";
				fieldValues[fc] = dbArranger.getSocialSecurityNo();
				fc++;
			}
			;

			// For as long as needed, keep screening for the requested values.
			for (fc = 0; (fc < fieldcount) && !arrangerExists; fc++) {
				com.aldorsolutions.webfdms.beans.DbArrangerSet dbArrangers = new com.aldorsolutions.webfdms.beans.DbArrangerSet();
				Hashtable <String, Object> h = new Hashtable<String, Object> ();
				h.put("Locale", String.valueOf(dbArranger.getLocale()));
				h.put(fieldNames[fc], fieldValues[fc]);
				dbArrangers.restore(t, h);
				PersistentI[] obs = dbArrangers.getPersistents();
				list = new com.aldorsolutions.webfdms.beans.DbArrangers[obs.length];
				// This trick is needed to make a Persistent[] into a
				// DbArrangers[]
				logger.debug("Searching for:" + fieldNames[fc] + " = " + fieldValues[fc]);
				for (int i = 0; i < obs.length; i++) {
					list[i] = (com.aldorsolutions.webfdms.beans.DbArrangers) obs[i];
					logger.debug("BurialLicenseNumber" + " = " + list[i].getBurialLicenseNumber());
					logger.debug("EmbalmerLicenseNumber" + " = " + list[i].getEmbalmerLicenseNumber());
					logger.debug("LicenseNumber" + " = " + list[i].getLicenseNumber());
					logger.debug("PnLicenseNumber" + " = " + list[i].getPnLicenseNumber());
					logger.debug("SocSecNo" + " = " + list[i].getSocialSecurityNo());
					if (list[i].getId() != dbArranger.getId()) {
						logger.debug("list[i].getId()" + " = " + list[i].getId());
						logger.debug("dbArranger.getId()" + " = " + dbArranger.getId());
						arrangerExists = true;
					}
				}
			}

			return arrangerExists;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getArrangerExists Persistence Exception:",
			// e);
			return arrangerExists;
		}
		finally {
			if (t != null) {
				t.closeConnection();
				t = null;
			}
		}
	}

	/**
	 * Create a list of Arrangers objects for a Locale Creation date: (12/31/01
	 * 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbArrangers[]
	 */
	public DbArrangers[] getArrangers(DatabaseTransaction t, int locale) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		h.put(DbArrangersPeer.LOCALE, new Integer(locale));
		return getArrangersByType(t, h);
	}
	
	public DbArrangers[] getInactiveArrangers(DatabaseTransaction t, int locale) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		h.put(DbArrangersPeer.LOCALE, new Integer(locale));
		h.put(DbArrangersPeer.DELETECODE, "D");

		return getArrangersByType(t, h);
	}
	/*
	 * Create a list of Arrangers objects from a hash of qualifications. This allows
	 * for filtering of arrangers using license numbers. A license number of "**"
	 * retrieves all arrangers having non-NULL numbers. A license number of "*null*"
	 * retrieves all arrangers having a null number.
	 * Creation date: (11/30/05 9:25 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbArrangers[]
	 * */

	public DbArrangers[] getArrangersByType(DatabaseTransaction t, Hashtable <String, Object> h ) {
		try {
			com.aldorsolutions.webfdms.beans.DbArrangers[] list;
			com.aldorsolutions.webfdms.beans.DbArrangerSet set = new com.aldorsolutions.webfdms.beans.DbArrangerSet();
			// retrieve all inactive items
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbArrangers[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbArrangers) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getArrangersByType Persistence Exception:", e);
			return null;
		}
	}

	/**
	 * Create an instanace of the Bill-to class for one specific record Creation
	 * date: (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbBillto
	 */
	public DbBillto getBillto(DatabaseTransaction t, int identity) {
		DbBillto p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbBillto) Persistent.getPersistent(t, identity, "com.aldorsolutions.webfdms.beans.DbBillto");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getBillto Persistence Exception:", e);
			return null;
		}
	}

	/**
	 * Create an instanace of the Bill-to class for one specific record Creation
	 * date: (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbBillto
	 */
	public DbBillto[] getBilltoForID(DatabaseTransaction t, int vitalsid) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbBillto[] billtolist;
		try {
			com.aldorsolutions.webfdms.beans.DbBilltoSet billtos = new com.aldorsolutions.webfdms.beans.DbBilltoSet();
			h.put(DbBilltoPeer.VITALSID, new Integer(vitalsid));
			billtos.restore(t, h);
			PersistentI[] obs = billtos.getPersistents();
			billtolist = new com.aldorsolutions.webfdms.beans.DbBillto[obs.length];
			// This trick is needed to make a Persistent[] into a Billto[]
			for (int i = 0; i < obs.length; i++) {
				billtolist[i] = (com.aldorsolutions.webfdms.beans.DbBillto) obs[i];
			}
			return billtolist;
		} catch (PersistenceException e) {
			//   logger.error("FdmsDb::getBilltoForID Persistence Exception:", e);
			return null;
		}
	}

	/**
	 * Create an instanace of the Case class for one specific record Creation
	 * date: (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbBillto
	 */
	public DbCase getCase(DatabaseTransaction t, int vitalsid) {
		DbCase p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbCase) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbCase");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getCase Persistence Exception:", e);
			return null;
		}
	}

	/**
	 * Create an instanace of the Charges class for one record and return it
	 * Creation date: (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbCharges
	 */
	public DbChargeItem getChargeItem(DatabaseTransaction t, int identity) {
		DbChargeItem p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbChargeItem) Persistent.getPersistent(t, identity, "com.aldorsolutions.webfdms.beans.DbChargeItem");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getBillto Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Retrieve all charges for a specified vitals-ID Creation date: (2/1/01
	 * 2:20:50 PM)
	 * 
	 * @return HashMap collection of all charges
	 * @param vitalsid
	 *            int Vitals-ID for the case
	 */
	public ArrayList<InvoiceItemDTO> getChargesForInvoice( DbUserSession user , int invoiceID) {
		ArrayList<InvoiceItemDTO> returnList = new ArrayList<InvoiceItemDTO>();
		
		InvoiceItemDAO itemDAO = new InvoiceItemDAO(user);
		ArrayList <InvoiceItemDTO> invoiceItems = itemDAO.getInvoiceItems(invoiceID);
		returnList.addAll(invoiceItems);
		
		
		return returnList;
	}
	
	/**
	 * Retrieve all charges for a specified vitals-ID Creation date: (2/1/01
	 * 2:20:50 PM)
	 * 
	 * @return HashMap collection of all charges
	 * @param vitalsid
	 *            int Vitals-ID for the case
	 */
	public HashMap<Integer, DbChargeItem> getChargesForCase(DatabaseTransaction t, int vitalsid) {
		int uniqueSeqNo = 0;
		HashMap<Integer, DbChargeItem> chargeSet = new HashMap<Integer, DbChargeItem>();
		Hashtable <String, Object> h = new Hashtable<String, Object> ();

		try {
			com.aldorsolutions.webfdms.beans.DbChargeItemSet chargeset = new com.aldorsolutions.webfdms.beans.DbChargeItemSet();
			h.put(DbChargeItemPeer.VITALSID, new Integer(vitalsid));
			chargeset.restore(t, h);
			PersistentI[] obs = chargeset.getPersistents();
			//caselist = new com.aldorsolutions.webfdms.beans.DbCase[obs.length];
			// This trick is needed to make a Persistent[] into a Cases[]
			com.aldorsolutions.webfdms.beans.DbChargeItem acharge;
			for (int i = 0; i < obs.length; i++) {
				acharge = (com.aldorsolutions.webfdms.beans.DbChargeItem) obs[i];
				// Make sure we have a unique sequence number
				uniqueSeqNo = acharge.getSequenceNumber();
				while (chargeSet.containsKey(new Integer(uniqueSeqNo))) {
					uniqueSeqNo += 5;
					acharge.setSequenceNumber(uniqueSeqNo);
				}
				chargeSet.put(new Integer(uniqueSeqNo), acharge);
			}
			return chargeSet;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getChargesForCase Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Retrieve charges for a specified vitals-ID of a specified type Creation
	 * date: (4/10/03 2:20:50 PM)
	 * 
	 * @return DbChargeItem[] collection of all charges of the specified type
	 * @param vitalsid
	 *            int Vitals-ID for the case
	 */
	public DbChargeItem[] getChargesForCaseOfType(DatabaseTransaction t, int vitalsid, int type) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbChargeItem[] chargelist;

		try {
			com.aldorsolutions.webfdms.beans.DbChargeItemSet chargeset = new com.aldorsolutions.webfdms.beans.DbChargeItemSet();
			h.put(DbChargeItemPeer.VITALSID, new Integer(vitalsid));
			h.put(DbChargeItemPeer.CHARGETYPE, new Integer(type));
			chargeset.restore(t, h);
			PersistentI[] obs = chargeset.getPersistents();
			//caselist = new com.aldorsolutions.webfdms.beans.DbCase[obs.length];
			// This trick is needed to make a Persistent[] into a Cases[]
			chargelist = new com.aldorsolutions.webfdms.beans.DbChargeItem[obs.length];
			for (int i = 0; i < obs.length; i++) {
				chargelist[i] = (com.aldorsolutions.webfdms.beans.DbChargeItem) obs[i];
			}
			return chargelist;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getChargesForCaseOfType Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the Payment Component class and return it Creation
	 * date: (7/26/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbComponent
	 */
	public DbComponent getComponent(DatabaseTransaction t, int recordid) {
		logger.debug("Entering getComponent()");
		DbComponent p = null;
		try {
			logger.debug("RecordId : " + recordid);
			p = (com.aldorsolutions.webfdms.beans.DbComponent) Persistent.getPersistent(t, recordid, "com.aldorsolutions.webfdms.beans.DbComponent");
			t.addPersistent(p); // make the persistent part of the transaction
		} catch (PersistenceException e) {
			logger.error("FdmsDb::getComponent Persistence Exception:", e);
		}

		logger.debug("Returning dbComponent");

		return p;
	}

	/**
	 * Create a set of Payment Components for one specific case Creation date:
	 * (11/26/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbComponent[]
	 */
	public DbComponent[] getComponentsForID(DatabaseTransaction t, int vitalsid)
			throws PersistenceException {
		return getComponentsForID(t, vitalsid, null);
	}

	/**
	 * Fetch a Payment Component for one specific case with a specified
	 * component code. Component code can be null in which case all components
	 * for the vitals-ID are returned. Returns an array however there should be
	 * only one row if component code is specified. Creation date: (6/26/02
	 * 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbComponent[]
	 */
	public DbComponent[] getComponentsForID(DatabaseTransaction t, int vitalsid, String componentcode)
			throws PersistenceException {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbComponent[] complist;
		//  try {
		com.aldorsolutions.webfdms.beans.DbComponentSet compSet = new com.aldorsolutions.webfdms.beans.DbComponentSet();
		h.put(DbComponentPeer.CASE_ID, new Integer(vitalsid));
		if (componentcode != null) {
			h.put(DbComponentPeer.CODE, componentcode);
		}
		compSet.restore(t, h);
		PersistentI[] obs = compSet.getPersistents();
		complist = new com.aldorsolutions.webfdms.beans.DbComponent[obs.length];
		// This trick is needed to make a Persistent[] into a Component[]
		for (int i = 0; i < obs.length; i++) {
			complist[i] = (com.aldorsolutions.webfdms.beans.DbComponent) obs[i];
		}
		return complist;
		/*
		 * } catch (PersistenceException e){
		 * AppLog.warning("FdmsDb::getComponentsForID Persistence
		 * Exception:"+e.getCause()); return null; }
		 */
	}

	/**
	 * Create an instanace of the Custom class for one specific record Creation
	 * date: (11/27/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbBillto
	 */
	public DbCustom getCustom(DatabaseTransaction t, int vitalsid) {
		DbCustom p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbCustom) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbCustom");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {

			return null;
		}
	}

	/**
	 * Create a list of all default forms available.
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbFormsAvailable[].
	 */
	public DbDefaultFormsAvailable[] getDefaultFormsAvailable(DatabaseTransaction t) {
		com.aldorsolutions.webfdms.beans.DbDefaultFormsAvailable[] list;
		try {
			com.aldorsolutions.webfdms.beans.DbDefaultFormsAvailableSet plSet = new com.aldorsolutions.webfdms.beans.DbDefaultFormsAvailableSet();
			plSet.restore(t, null);
			PersistentI[] obs = plSet.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbDefaultFormsAvailable[obs.length];
			// This trick is needed to make a Persistent[] into a PackageList[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbDefaultFormsAvailable) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getDefaultFormsAvailable Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * From glDefaultAcct table, retrieve the default accounts and fill into the
	 * InventorySold object for any GL accounts that are blank.. Creation date:
	 * (6/3/2003 4:18:31 PM)
	 * 
	 * @return com.aldorsolutions.webfdms.databaseInventorySold - same as one passed in with the default
	 *         GL accounts
	 * @param t
	 *            DatabaseTransaction
	 * @param soldData
	 *            com.aldorsolutions.webfdms.databaseInventorySold - partially filled in object, GL accts
	 *            may be blank
	 * @param locale
	 *            int
	 * @param locationID
	 *            int
	 * @param category
	 *            java.lang.String
	 * @param saleType
	 *            java.lang.String
	 * @param disposition
	 *            java.lang.String
	 * @exception PersistenceException
	 *                The exception description.
	 */
	public InventorySold getDefaultGlSalesAccts(DatabaseTransaction t, InventorySold soldData, int locale,
			int locationID, String category, String saletype, String disposition)
			throws PersistenceException {

		DbGlAcctDefault[] accts = null;

		// From the database, get the default GL records for these criteria
		accts = getGlAcctDefaultSet(t, locale, locationID, category, disposition, saletype);
		if (accts != null && accts.length > 0) {
			if (soldData.getAcctCostOfSale() == null || soldData.getAcctCostOfSale().length() < 1) {
				soldData.setAcctCostOfSale(accts[0].getInvCogsAcct());
			}

			if (soldData.getAcctInvAsset() == null || soldData.getAcctInvAsset().length() < 1) {
				soldData.setAcctInvAsset(accts[0].getInvAssetAcct());
			}

			if (soldData.getAcctRevenue() == null || soldData.getAcctRevenue().length() < 1) {
				soldData.setAcctRevenue(accts[0].getRevenueAcct());
			}
		}
		return soldData;
	}

	/**
	 * Create a set of DefaultPriceList entries. Creation date: (8/26/2002
	 * 12:13:37 PM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbDefaultPriceList[]
	 */
	public DbDefaultPriceList[] getDefaultPriceListRecords(DatabaseTransaction t) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbDefaultPriceList[] defaultPriceList;
		try {
			com.aldorsolutions.webfdms.beans.DbDefaultPriceListSet dplSet = new com.aldorsolutions.webfdms.beans.DbDefaultPriceListSet();
			h.put("", "");
			dplSet.restore(t, h);
			PersistentI[] obs = dplSet.getPersistents();
			defaultPriceList = new com.aldorsolutions.webfdms.beans.DbDefaultPriceList[obs.length];
			// This trick is needed to make a Persistent[] into a
			// DbDefaultPriceList[]
			for (int i = 0; i < obs.length; i++) {
				defaultPriceList[i] = (com.aldorsolutions.webfdms.beans.DbDefaultPriceList) obs[i];
			}
			return defaultPriceList;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getDefaultPriceList Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Get the defaultpricelist version from the defaultpricelist table.
	 * Creation date: (9/3/2002 2:37:21 PM)
	 * 
	 * @return String version name
	 */
	public String getDefaultPriceListVersion(DatabaseTransaction t) {
		String version = new String(" ");
		ResultSet rs;
		Statement stmt;
		Connection connection;
		try {
			connection = ((DatabaseTransaction) t).getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT DISTINCT PriceListVersion FROM defaultpricelist order by PriceListVersion");
			while (rs.next()) {
				// Just grab the first pricelistversion
				version = (rs.getString("PriceListVersion"));
				break;
			}

			return version;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getPriceListVersions SQLException:",e);
			return null;
		} catch (SQLException e) {
			//  logger.error("FdmsDb::getDefaultPriceListVersion
			// SQLException:",e);
			return version;
		}
	}

	/**
	 * Create all of the DefaultSpeedData objects. Creation date: (7/15/02
	 * 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbSpeedData[]
	 */
	public DbDefaultSpeedData[] getDefaultSpeedData(DatabaseTransaction t) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbDefaultSpeedData[] list;
		try {
			com.aldorsolutions.webfdms.beans.DbDefaultSpeedDataSet set = new com.aldorsolutions.webfdms.beans.DbDefaultSpeedDataSet();
			// retrieve all items
			h.put(" ", " ");
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbDefaultSpeedData[obs.length];
			// This trick is needed to make a Persistent[] into a
			// DbDefaultSpeedData[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbDefaultSpeedData) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getDefaultSpeedData Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the Donation class for one specific record
	 * Creation date: (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbBillto
	 */
	public DbDonations getDonations(DatabaseTransaction t, int identity) {
		DbDonations p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbDonations) Persistent.getPersistent(t, identity, "com.aldorsolutions.webfdms.beans.DbDonations");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getDonations Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the Bill-to class for one specific record Creation
	 * date: (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbBillto
	 */
	public DbDonations[] getDonationsForID(DatabaseTransaction t, int vitalsid) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbDonations[] donationlist;
		try {
			com.aldorsolutions.webfdms.beans.DbDonationSet donations = new com.aldorsolutions.webfdms.beans.DbDonationSet();
			h.put(DbDonationsPeer.VITALSID, new Integer(vitalsid));
			donations.restore(t, h);
			PersistentI[] obs = donations.getPersistents();
			donationlist = new com.aldorsolutions.webfdms.beans.DbDonations[obs.length];
			// This trick is needed to make a Persistent[] into a Billto[]
			for (int i = 0; i < obs.length; i++) {
				donationlist[i] = (com.aldorsolutions.webfdms.beans.DbDonations) obs[i];
			}
			return donationlist;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getDonationsForID Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Calculate the user's expiration date. Takes a DbUser instance to retrieve
	 * the locale record for the user's associated locale. Creation date:
	 * (9/12/02 10:41:17 AM)
	 * 
	 * @return int
	 */
	public int getExpirationInterval(DbUserSession user) {

		int expirationInterval = 0;

		try {
			LocaleDAO localeDAO = new LocaleDAO();
			LocaleDTO locale = localeDAO.getLocale(user.getRegion(), user.getDbLookup());
			
			// Get the expiration date.
			if (locale == null) {
				expirationInterval = -999;
				return ( expirationInterval );
			}
			
			// Calculate the number of days before the user's access expires
			java.util.Date expireDate = locale.getAccessExpirationDate();
			if (locale != null && expireDate != null && expireDate.toString().trim().length() > 0) {
				JulianDay currentDate = new JulianDay();
				JulianDay expirationDate = new JulianDay(expireDate.getTime());
				expirationInterval = (int) (expirationDate.getJDN() - currentDate.getJDN());
			}
		} catch (Exception e) {
			logger.error("FdmsDb::getExpirationInterval Exception:",e);
		}
		
		return expirationInterval;
	}

	/**
	 * Create an instanace of the Financial class and return it Creation date:
	 * (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbFinancial
	 */
	public DbFinancialSummary getFinancial(DatabaseTransaction t, int vitalsid) {
		DbFinancialSummary p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbFinancialSummary) DbFinancialSummary.getPersistent(t, vitalsid,
					"com.aldorsolutions.webfdms.beans.DbFinancialSummary");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getFinancial Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the DbSecurityConfig class and return it Creation
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbSecurityConfig
	 */
	public DbSecurityConfig getSecurityConfig(DatabaseTransaction t, long companyID) {
		DbSecurityConfig p = null;
		try {
			p = (DbSecurityConfig) Persistent.getPersistent(t,(int) companyID, "com.aldorsolutions.webfdms.beans.DbSecurityConfig");
			t.addPersistent(p); 
		} 
		catch (PersistenceException e) {
			return null;
		}
		return p;
	}

	/**
	 * Create an instanace of the Forms Available class for a form-id and return
	 * it. Creation date: (1/17/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbFormsAvailable
	 */
	public DbFormsAvailable getFormsAvailable(DatabaseTransaction t, int formid) {
		DbFormsAvailable p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbFormsAvailable) DbFormsAvailable.getPersistent(t, formid, "com.aldorsolutions.webfdms.beans.DbFormsAvailable");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getFormsAvailable Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Create a list of ALL forms available for a given locale regardless of
	 * category. Creation date: (12/26/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbFormsAvailable[].
	 */
	public DbFormsAvailable[] getFormsAvailableForLocale(DatabaseTransaction t, int locale) {

		return getFormsAvailableForLocale(t, locale, -1);
	}

	/**
	 * Create a list of forms available for a given locale and report category.
	 * Category can be: 0 = forms for one specific case 1 = At-Need reports 2 =
	 * Pre-Need reports 3 = Financial reports 4 = Donation reports 5 = price
	 * list reports Creation date: (2/20/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbFormsAvailable[].
	 */
	public DbFormsAvailable[] getFormsAvailableForLocale(DatabaseTransaction t, int locale, int category) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbFormsAvailable[] list;
		try {
			com.aldorsolutions.webfdms.beans.DbFormsAvailableSet plSet = new com.aldorsolutions.webfdms.beans.DbFormsAvailableSet();
			h.put(DbFormsAvailablePeer.LOCALE, new Integer(locale));
			if (category != -1) { // -1 means all categories
				h.put(DbFormsAvailablePeer.CATEGORY, new Integer(category)); // category
				// zero
				// is
				// FORMS
				// for
				// one
				// specific
				// case
			}
			plSet.restore(t, h);
			PersistentI[] obs = plSet.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbFormsAvailable[obs.length];
			// This trick is needed to make a Persistent[] into a PackageList[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbFormsAvailable) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			logger.error("FdmsDb::getFormsAvailableForLocale Persistence Exception:", e);
			return null;
		}
	}
	
	/**
	 * Create a list of all forms available for reportname
	 * list reports Creation date: (06/17/09 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbFormsAvailable[].
	 */
	public DbFormsAvailable[] getFormsAvailableForReportName(DatabaseTransaction t, String reportName) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbFormsAvailable[] list;
		try {
			com.aldorsolutions.webfdms.beans.DbFormsAvailableSet plSet = new com.aldorsolutions.webfdms.beans.DbFormsAvailableSet();
			h.put(DbFormsAvailablePeer.REPORTNAME, reportName);
			plSet.restore(t, h);
			PersistentI[] obs = plSet.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbFormsAvailable[obs.length];
			// This trick is needed to make a Persistent[] into a PackageList[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbFormsAvailable) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			logger.error("FdmsDb::getFormsAvailableForReportName Persistence Exception:", e);
			return null;
		}
	}	
	
	
	/**
	 * Create a list of forms PRINTED for a case and optionally for a specific
	 * form. Creation date: (1/17/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbFormsPrinted[].
	 */
	public DbFormsPrinted[] getFormsPrintedForCase(DatabaseTransaction t, int vitalsid, int formid) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbFormsPrinted[] list;
		try {
			com.aldorsolutions.webfdms.beans.DbFormsPrintedSet plSet = new com.aldorsolutions.webfdms.beans.DbFormsPrintedSet();
			h.put(DbFormsPrintedPeer.CASEID, new Integer(vitalsid));
			if (formid > 0)
				h.put(DbFormsPrintedPeer.FORMID, new Integer(formid));
			plSet.restore(t, h);
			PersistentI[] obs = plSet.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbFormsPrinted[obs.length];
			// This trick is needed to make a Persistent[] into a PackageList[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbFormsPrinted) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getFormsPrintedForCase Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the DbGlAcctDefault class representing a set of GL
	 * accounts for a specific category, location, sale type, and disposition.
	 * Creation date: (5/28/03 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbApAccount
	 */
	public DbGlAcctDefault getGlAccountDefault(DatabaseTransaction t, int recid) {
		DbGlAcctDefault p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbGlAcctDefault) DbGlAcctDefault.getPersistent(t, recid, "com.aldorsolutions.webfdms.beans.DbGlAcctDefault");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getGlAcctDefault Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Create a list of ApAccounts objects for a Locale or location Creation
	 * date: (12/31/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbArrangers[]
	 */
	public DbGlAcctDefault[] getGlAcctDefaultSet(DatabaseTransaction t, int locale, int location, String category,
			String disposition, String saletype) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbGlAcctDefault[] list;
		try {
			DbGlAcctDefaultSet set = new DbGlAcctDefaultSet();
			// retrieve all accounts for a given locale
			// locale of zero means all locales.
			if (locale > 0) {
				h.put(DbGlAcctDefaultPeer.LOCALE, new Integer(locale));
			}
			// retrieve all accounts for a given chapel location
			if (location > 0) {
				h.put(DbGlAcctDefaultPeer.LOCATIONID, new Integer(location));
			}
			// retrieve specific category
			if (category != null && category.length() > 0) {
				h.put(DbGlAcctDefaultPeer.CATEGORY, category);
			}
			// retrieve specific disposition
			if (disposition != null && disposition.length() > 0) {
				h.put(DbGlAcctDefaultPeer.DISPOSITION, disposition);
			}
			// retrieve specific sale type
			if (saletype != null && saletype.length() > 0) {
				h.put(DbGlAcctDefaultPeer.SALETYPE, saletype);
			}
			// run the SQL to get this set of accounts
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbGlAcctDefault[obs.length];
			// This trick is needed to make a Persistent[] into a Db[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbGlAcctDefault) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getGlAcctDefaultSet Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the History class and return it Creation date:
	 * (7/13/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbHistory
	 */
	public DbHistory getHistory(DatabaseTransaction t, int identity) {
		DbHistory p;
		try {
			p = (DbHistory) Persistent.getPersistent(t, identity, "com.aldorsolutions.webfdms.beans.DbHistory");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getBillto Persistence Exception:",e);
			return null;
		}
		return p;
	}

	/**
	 * Create an instanace of the Bill-to class for one specific record Creation
	 * date: (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbBillto
	 */
	public DbHistory[] getHistoryForCase(DatabaseTransaction t, int vitalsid) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbHistory[] histlist;
		try {
			com.aldorsolutions.webfdms.beans.DbHistorySet history = new com.aldorsolutions.webfdms.beans.DbHistorySet();
			h.put(DbHistoryPeer.VITALSID, new Integer(vitalsid));
			history.restore(t, h);
			PersistentI[] obs = history.getPersistents();
			histlist = new com.aldorsolutions.webfdms.beans.DbHistory[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				histlist[i] = (com.aldorsolutions.webfdms.beans.DbHistory) obs[i];
			}
			return histlist;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getHistoryForCase Persistence
			// Exception:",e);
			return null;
		}
	}

	public DbHistory[] getHistoryForBank(DatabaseTransaction t, int locationId, Date startDate, Date endDate) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbHistory[] histlist;
		try {
			com.aldorsolutions.webfdms.beans.DbHistorySet history = new com.aldorsolutions.webfdms.beans.DbHistorySet();
			h.put(DbHistoryPeer.SPFCODE, "B");
			if (locationId != 0){
				h.put(DbHistoryPeer.LOCATIONID, new Integer(locationId));
			}
			if (startDate != null)  {
				h.put(DbHistoryPeer.STARTDATEOFTRAN, startDate);
			}
			if (endDate != null) {
				h.put(DbHistoryPeer.ENDDATEOFTRAN, endDate);
			}
			history.restore(t, h);
			PersistentI[] obs = history.getPersistents();
			histlist = new com.aldorsolutions.webfdms.beans.DbHistory[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				histlist[i] = (com.aldorsolutions.webfdms.beans.DbHistory) obs[i];
			}
			return histlist;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getHistoryForCase Persistence
			// Exception:",e);
			return null;
		}
	}
	
	/**
	 * Return the one and only instance of this class Creation date: (5/17/01
	 * 11:45:08 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.databaseFdmsDb
	 */
	public static FdmsDb getInstance() {
		return instance;
	}

	/**
	 * Retrieve one Inventory History transaction. Creation date: (1/31/02
	 * 9:11:17 AM)
	 * 
	 * @param int
	 *            identity
	 * @return com.aldorsolutions.webfdms.beans.DbInvHistory
	 */
	public DbInvHistory getInvHistory(DatabaseTransaction t, int identity) {
		com.aldorsolutions.webfdms.beans.DbInvHistory p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbInvHistory) DbInvHistory.getPersistent(t, identity, "com.aldorsolutions.webfdms.beans.DbInvHistory");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getInvHistory Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create a list of InvHistory objects for a MasterID Creation date:
	 * (12/31/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbInvHistory[]
	 */
	public DbInvHistory[] getInvHistoryForItem(DatabaseTransaction t, int masterid) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbInvHistory[] list;
		try {
			com.aldorsolutions.webfdms.beans.DbInvHistorySet set = new com.aldorsolutions.webfdms.beans.DbInvHistorySet();
			// retrieve all inactive items
			h.put(DbInvHistoryPeer.MASTERID, new Integer(masterid));
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbInvHistory[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbInvHistory) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getHistoryForMasterID Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Determine if a given Inventory Master item is availabel to a given
	 * location. If it is, a DbInvChapelIndex object is returned. If not, null
	 * is returned. Creation date: (1/29/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbInvChapelIndex
	 */
	public DbInvChapelIndex getInvItemForLocation(DatabaseTransaction t, int locationId, int invItemId) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbInvChapelIndex[] list;
		try {
			com.aldorsolutions.webfdms.beans.DbInvChapelIndexSet set = new com.aldorsolutions.webfdms.beans.DbInvChapelIndexSet();
			// retrieve records for matching location and item
			h.put(DbInvChapelIndexPeer.LOCATION, new Integer(locationId));
			h.put(DbInvChapelIndexPeer.ITEM, new Integer(invItemId));
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbInvChapelIndex[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbInvChapelIndex) obs[i];
			}
			if (list.length > 1) {
				//AppLog.error("getInvItemForLocation: multiple
				// matches:"+locationId+" "+invItemId);
			}
			if (list.length > 0) {
				t.addPersistent(list[0]);
				return list[0]; // should be no more than one match
			}

			return null;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getInvItemForLocation Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the InvMaster class for one inventory item and
	 * return it Creation date: (12/29/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbInvMaster
	 */
	public DbInvMaster getInvMaster(DatabaseTransaction t, int itemid) {
		DbInvMaster p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbInvMaster) DbInvMaster.getPersistent(t, itemid, "com.aldorsolutions.webfdms.beans.DbInvMaster");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//   logger.error("FdmsDb::getInvMaster Persistence Exception:",e);
			return null;
		}
	}

	
	/**
	 * Create an instanace of the PriceList class for one price list item and
	 * return it 
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbPriceList
	 */
	public DbPriceList getPriceList(DatabaseTransaction t, int itemid) {
		DbPriceList p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbPriceList) DbPriceList.getPersistent(t, itemid, "com.aldorsolutions.webfdms.beans.DbPriceList");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//   logger.error("FdmsDb::getInvMaster Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create a list of InvMaster objects for all active or inactive records if
	 * "deletecode" parameter ='D' then retrieve all inactive items if parameter
	 * is 'A' then retrieve all active items otherwies retreive both active and
	 * inactive items Creation date: (12/29/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbInvMaster[]
	 */
	public DbInvMaster[] getInvMasterActive(DatabaseTransaction t, String deletecode, int locale) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbInvMaster[] list;
		try {
			com.aldorsolutions.webfdms.beans.DbInvMasterSet set = new com.aldorsolutions.webfdms.beans.DbInvMasterSet();
			// store locale to select
			h.put(DbInvMasterPeer.LOCALE, new Integer(locale));
			// store active/inactive selection
			// deletecode of null means to select both inactive and active in
			// which case
			// we omit deletecode from the hash table
			if (deletecode != null) {
				h.put(DbInvMasterPeer.DELETECODE, deletecode);
			}
			set.restore(t, h);
			/*
			 * if (deletecode=='D') { // retrieve all inactive items
			 * h.put(DbInvMasterPeer.DELETECODE, new String("D"));
			 * set.restore(t,h); } else if (deletecode=='A'){ // retrieve all
			 * active items set.restore(t,h); } else { // retreive both active
			 * and inactive items set.restore(t,h); // empty hashtable }
			 */
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbInvMaster[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbInvMaster) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			logger.error("FdmsDb::getHistoryForCase PersistenceException:",e);
			return null;
		}
	}

	/**
	 * Create a list of InvMaster objects for a given location (chapel). Only
	 * "active" items are returned. Creation date: (4/10/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbInvMaster[]
	 */
	public DbInvMaster[] getInvMasterForLocation(DatabaseTransaction t, int locationId) {
		Hashtable <String, String> h = new Hashtable <String, String> ();
		DbInvMaster[] list;
		
		try {
			DbInvMasterSet set = new DbInvMasterSet();
			// retrieve all active items for location
			h.put("CHAPEL", String.valueOf(locationId));
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new DbInvMaster[obs.length];
			// This trick is needed to make a Persistent[] into a DbInvMaster]
			
			for (int i = 0; i < obs.length; i++) {
				list[i] = (DbInvMaster) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			//   logger.error("FdmsDb::getInvMasterForLocation Persistence
			// Exception:", e);
			return null;
		}
	}

	/**
	 * Return an Inventory Master Object for an item selected by item name only.
	 * The item may be inactive. Null is returned if no object found with that
	 * name. Creation date: (7/19/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbInvMaster
	 */
	public DbInvMaster getInvMasterForName(DatabaseTransaction t, String itemname, int locale) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();

		try {
			com.aldorsolutions.webfdms.beans.DbInvMasterSet set = new com.aldorsolutions.webfdms.beans.DbInvMasterSet();
			// store locale to select
			h.put(DbInvMasterPeer.LOCALE, new Integer(locale));
			// retrieve all active items for location
			h.put(DbInvMasterPeer.ITEMNAME, itemname);
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			// if nothing found, return NULL
			if (obs.length < 1) {
				return null;
			}
			return (com.aldorsolutions.webfdms.beans.DbInvMaster) obs[0]; // can be only one
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getInvMasterForName Persistence
			// Exception:", e);
			return null;
		}
	}

	/**
	 * Create an instanace of the InvOnHand class by ID Creation date: (12/29/01
	 * 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbInvOnHand
	 */
	public DbInvOnHand getInvOnHand(DatabaseTransaction t, int itemid) {
		DbInvOnHand p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbInvOnHand) DbInvOnHand.getPersistent(t, itemid, "com.aldorsolutions.webfdms.beans.DbInvOnHand");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getInvOnHand Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create a list of InvOnHand objects for a MasterID. Creation date:
	 * (2/11/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbInvOnHand[]
	 */
	public DbInvOnHand[] getInvOnHandForItem(DatabaseTransaction t, int masterid) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbInvOnHand[] list;
		try {
			//AppLog.trace("getInvOnHandForItem: "+masterid);
			com.aldorsolutions.webfdms.beans.DbInvOnHandSet set = new com.aldorsolutions.webfdms.beans.DbInvOnHandSet();
			// retrieve all for given Master-ID
			h.put(DbInvOnHandPeer.MASTERID, new Integer(masterid));
			
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbInvOnHand[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbInvOnHand) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getOnHandForItem Persistence
			// Exception:",e);
			return null;
		}
	}
	
	/**
	 * Create a list of InvOnHand objects for a MasterID. Creation date:
	 * (2/11/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbInvOnHand[]
	 */
	public DbInvOnHand[] getInvOnHandForItem(DatabaseTransaction t, int masterid, int serialNo) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbInvOnHand[] list;
		try {
			//AppLog.trace("getInvOnHandForItem: "+masterid);
			com.aldorsolutions.webfdms.beans.DbInvOnHandSet set = new com.aldorsolutions.webfdms.beans.DbInvOnHandSet();
			// retrieve all for given Master-ID
			h.put(DbInvOnHandPeer.MASTERID, new Integer(masterid));
			h.put(DbInvOnHandPeer.SERIALNUMBER, new Integer(serialNo));
			h.put(DbInvOnHandPeer.QUANTITY, new Integer(-1));
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbInvOnHand[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbInvOnHand) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getOnHandForItem Persistence
			// Exception:",e);
			return null;
		}
	}

	public DbInvVersion getInvVersion(DatabaseTransaction t, int invVersionID) {
		DbInvVersion p;
		try {
			p = (DbInvVersion) DbInvVersion.getPersistent(t, invVersionID, "com.aldorsolutions.webfdms.databaseinventory.DbInvVersion");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			logger.error("FdmsDb::getInvVersion Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the Locale class and return it. Creation date:
	 * (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbLocale
	 */
	public LocaleDTO getLocale(String dbLookup, int locale) {
		try {
			LocaleDAO localeDAO = new LocaleDAO();
			return ( localeDAO.getLocale(locale, dbLookup) );
		} catch (Exception e) {
			logger.error("FdmsDb::getLocale Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the Locale class and return its name. Creation
	 * date: (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbLocale
	 */
	public String getLocaleName(String dbLookup, int locale) {
		
		String localeName = null;
		LocaleDTO localeDTO = getLocale(dbLookup, locale);
		
		if ( localeDTO != null ) {
			localeName = localeDTO.getName();
		}
		
		return ( localeName );
	}

	/**
	 * Create an instanace of the Location class and return its name. Creation
	 * date: (10/25/05 9:33 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbLocation
	 */
	public String getLocationName(DatabaseTransaction t, int location) {
		DbLocation p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbLocation) DbLocation.getPersistent(t, location, "com.aldorsolutions.webfdms.beans.DbLocation");
			return p.getName();
		} catch (PersistenceException e) {
			//   logger.error("FdmsDb::getFinancial Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the Locale class locking the row for updates.
	 * Creation date: (6/21/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbLocale
	 */
	public DbLocale getLocaleWithLock(DatabaseTransaction t, int locale) throws SQLException, PersistenceException {
		DbLocale p;
		p = new DbLocale();
		p.setId(locale);
		((DatabasePeer) p.getPersistentPeer()).restoreWithLock(p, t);
		t.addPersistent(p);
		return p;
	}

	/**
	 * Create an instanace of the Location class representing one chapel
	 * location. Creation date: (2/2/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbLocation
	 */
	public DbLocation getLocation(DatabaseTransaction t, int chapelid) {
		DbLocation p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbLocation) DbLocation.getPersistent(t, chapelid, "com.aldorsolutions.webfdms.beans.DbLocation");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getLocation Persistence Exception:",e);
			return null;
		}
	}
	/**
	 * Create an instanace of the bankaccount class representing one bankaccount
	 * Creation date: (11/10/09 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbBankAccount
	 */
	public DbBankAccount getBankAccount(DatabaseTransaction t, int bankId) {
		DbBankAccount p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbBankAccount) DbBankAccount.getPersistent(t, bankId, "com.aldorsolutions.webfdms.beans.DbBankAccount");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getLocation Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Checks for the existance of this Location by passing a DbLocation
	 * instance with pre-set values to check against. If returns true, the
	 * location already exists in the database. Creation date: (9/04/02 10:54:17
	 * AM)
	 * 
	 * @return boolean
	 */
	public boolean getLocationExists(DbUserSession sessionUser, DbLocation dbLocation) {

		boolean homeExists = false;
		DatabaseTransaction t = null;
		com.aldorsolutions.webfdms.beans.DbLocation[] list = null;

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);

			if (dbLocation.getState() != null && dbLocation.getLicenseNumber() != null) {
				com.aldorsolutions.webfdms.beans.DbLocationSet dbLocations = new com.aldorsolutions.webfdms.beans.DbLocationSet();
				Hashtable <String, Object> h = new Hashtable<String, Object> ();
				h.put("State", dbLocation.getState());
				h.put("LicenseNumber", dbLocation.getLicenseNumber());
				dbLocations.restore(t, h);
				PersistentI[] obs = dbLocations.getPersistents();
				list = new com.aldorsolutions.webfdms.beans.DbLocation[obs.length];
				// This trick is needed to make a Persistent[] into a
				// DbLocation[]
				for (int i = 0; i < obs.length; i++) {
					list[i] = (com.aldorsolutions.webfdms.beans.DbLocation) obs[i];
					if (list[i].getId() != dbLocation.getId()) {
						homeExists = true;
					}
				}
			}

			if (dbLocation.getName() != null) {
				com.aldorsolutions.webfdms.beans.DbLocationSet dbLocations = new com.aldorsolutions.webfdms.beans.DbLocationSet();
				Hashtable <String, Object> h = new Hashtable<String, Object> ();
				h.put("Name", dbLocation.getName());
				dbLocations.restore(t, h);
				PersistentI[] obs = dbLocations.getPersistents();
				list = new com.aldorsolutions.webfdms.beans.DbLocation[obs.length];
				// This trick is needed to make a Persistent[] into a
				// DbLocation[]
				for (int i = 0; i < obs.length; i++) {
					list[i] = (com.aldorsolutions.webfdms.beans.DbLocation) obs[i];
					if (list[i].getId() != dbLocation.getId()) {
						homeExists = true;
					}
				}
			}

			return homeExists;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getUser Persistence Exception:",e);
			return homeExists;
		} finally {
			if (t != null) {
				t.closeConnection();
				t = null;
			}
		}
	}

	/**
	 * Create a set of Location (chapel) objects for a given locale (region)
	 * Creation date: (1/26/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbLocation[]
	 */
	public ArrayList <LocaleDTO> getLocalesForCompany(String dbLookup, long companyID) {
		
		ArrayList <LocaleDTO> elements = new ArrayList <LocaleDTO> ();
		try {
			LocaleDAO localeDAO = new LocaleDAO();
			elements = localeDAO.getLocales(true, companyID, dbLookup);
		} catch (Exception e) {
			logger.error("FdmsDb::getLocalesForCompany Persistence Exception:", e);
			return null;
		}
		
		return elements;
	}

	/**
	 * Create a set of Location (chapel) objects for a given locale (region)
	 * Creation date: (1/26/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbLocation[]
	 */
	public int getLocaleConfigValueForLocale(DatabaseTransaction t, long localeID, int configType) {
		DbLocaleConfig[] configs = getLocaleConfigForLocale(t, localeID);
		return ( getLocaleConfigValueForLocale ( t, configs, localeID, configType ) );
	}
	

	/**
	 * Create a set of Location (chapel) objects for a given locale (region)
	 * Creation date: (1/26/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbLocation[]
	 */
	public int getLocaleConfigValueForLocale(DatabaseTransaction t, DbLocaleConfig[] configs, long localeID, int configType) {
		
		DbLocaleConfigType[] types = getLocaleConfigTypes(t);
		
		for (int i = 0; i < types.length; i++) {
			DbLocaleConfigType type = (DbLocaleConfigType) types[i];
			
			boolean found = false;
			for (int x = 0; x < configs.length; x++) {
				DbLocaleConfig config = (DbLocaleConfig) configs[x];
				
				if (config.getLocaleConfigTypeID() == configType) {
					found = true;
					return (config.getValue());
				}
			}
			
			if ( (found == false) && (configType == type.getId()) ) {
				return (type.getDefaultValue());
			}
			
		}
		
		return ( -1 );
			
	}
	
	public DbLocaleConfig[] getLocaleConfigForLocale(DatabaseTransaction t, long localeID) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		DbLocaleConfig[] list;
		
		try {
			DbLocaleConfigSet set = new DbLocaleConfigSet();
			h.put(DbLocaleConfigPeer.LOCALEID, new Long(localeID));
			set.restore(t, h);
			
			PersistentI[] obs = set.getPersistents();
			list = new DbLocaleConfig[obs.length];
			
			// This trick is needed to make a Persistent[] into a DbLocaleConfig[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (DbLocaleConfig) obs[i];
			}
			
			return list;
			
		} catch (PersistenceException e) {
			logger.error("FdmsDb::getLocaleConfigForLocale Persistence Exception:", e);
			return null;
		} catch (Exception e) {
			logger.error("FdmsDb::getLocaleConfigForLocale Persistence Exception:", e);
			return null;
		}
	}
	
	public DbLocaleConfigType [] getLocaleConfigTypes(DatabaseTransaction t) {
		
		if ( localeConfigTypes == null ) {

			try {
				DbLocaleConfigTypeSet configTypeSet =  new DbLocaleConfigTypeSet();
				configTypeSet.restore(t, null);
				PersistentI[] configObjs = configTypeSet.getPersistents();
				
				DbLocaleConfigType ctList [] = new DbLocaleConfigType[configObjs.length];
				
				for (int i = 0; i < configObjs.length; i++) {
					ctList[i] = (DbLocaleConfigType) configObjs[i];
				}
				
				localeConfigTypes = ctList;
				
				return ctList;
				
			} catch (PersistenceException e) {
				logger.error("FdmsDb::getLocaleConfigTypes Persistence Exception:", e);
				return null;
			} catch (Exception e) {
				logger.error("FdmsDb::getLocaleConfigTypes Persistence Exception:", e);
				return null;
			}
		} else {
			return ( localeConfigTypes );
		}
	}

	/**
	 * Create a set of Location (chapel) objects for a given locale (region)
	 * Creation date: (1/26/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbLocation[]
	 */
	public DbLocation[] getLocationsForCompany(DatabaseTransaction t, int companyID) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbLocation[] list;
		try {
			com.aldorsolutions.webfdms.beans.DbLocationSet set = new com.aldorsolutions.webfdms.beans.DbLocationSet();
			h.put(DbLocationPeer.COMPANYNO, new Integer(companyID));
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbLocation[obs.length];
			// This trick is needed to make a Persistent[] into a Billto[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbLocation) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			logger.error("FdmsDb::getLocationsForRegion Persistence Exception:", e);
			return null;
		} catch (Exception e) {
			logger.error("FdmsDb::getLocationsForRegion Persistence Exception:", e);
			return null;
		}
	}

    /**
     * Make a list of WebFDMS authorized users and add the list to the current request.
     * Creation date: (3/11/2002 4:32:13 PM)
     */
    public DbUser[] getUsersForCompany(DatabaseTransaction t, int companyID) {
    	
    	Hashtable <String, Object> h = new Hashtable<String, Object> ();
		DbUser[] list;
		try {
			DbUserSet set = new DbUserSet();
			h.put(DbUserPeer.COMPANYID, new Integer(companyID));
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new DbUser[obs.length];
			
			// This trick is needed to make a Persistent[] into a DbUser[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (DbUser) obs[i];
			}
			
			return list;
			
		} 
		catch (Exception e) {
			logger.error("FdmsDb::getUsersForCompany Exception:", e);
			return null;
		}
		
    }

	/**
	 * Create a set of Location (chapel) objects for a given locale (region)
	 * Creation date: (1/26/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbLocation[]
	 */
	public DbLocation[] getLocationsForRegion(DatabaseTransaction t, int regionid) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbLocation[] list;
		try {
			com.aldorsolutions.webfdms.beans.DbLocationSet set = new com.aldorsolutions.webfdms.beans.DbLocationSet();
			h.put(DbLocationPeer.LOCALE, new Integer(regionid));
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbLocation[obs.length];
			// This trick is needed to make a Persistent[] into a Billto[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbLocation) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			logger.error("FdmsDb::getLocationsForRegion Persistence Exception:", e);
			return null;
		} catch (Exception e) {
			logger.error("FdmsDb::getLocationsForRegion Persistence Exception:", e);
			return null;
		}
	}

	/**
	 * Create an instanace of the Memorial class representing one memorial
	 * folder verse. Creation date: (10/08/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbMemorial
	 */
	public DbMemorial getMemorial(DatabaseTransaction t, int recid) {
		DbMemorial p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbMemorial) DbMemorial.getPersistent(t, recid, "com.aldorsolutions.webfdms.beans.DbMemorial");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//   logger.error("FdmsDb::getMemorial Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create a list of Memorial objects for a Locale. Creation date: (10/08/02
	 * 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbMemorial[]
	 */
	public DbMemorial[] getMemorialSet(DatabaseTransaction t, int locale) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbMemorial[] list;
		try {
			DbMemorialSet set = new DbMemorialSet();
			// retrieve all for a given locale
			// locale of zero means all locales.
			if (locale > 0) {
				h.put(DbMemorialPeer.LOCALE, new Integer(locale));
			}
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbMemorial[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbMemorial) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getMemorialSet Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the Obituary class and return it Creation date:
	 * (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbCharges
	 */
	public DbObituary getObituary(DatabaseTransaction t, int vitalsid) {
		DbObituary p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbObituary) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbObituary");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getObituary Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * This class represents one component of a package of price list items.
	 * Create an instanace of the DbPackageList class and return it. Creation
	 * date: (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbPackageList
	 */
	public DbPackageList getPackage(DatabaseTransaction t, int packageid) {
		DbPackageList p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbPackageList) Persistent.getPersistent(t, packageid, "com.aldorsolutions.webfdms.beans.DbPackageList");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getPackage Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create a set of Package List entries for one version/GPL Package key
	 * Creation date: (12/26/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbPriceList[] in order by sequence number
	 */
	public DbPackageList[] getPackageListForKey(DatabaseTransaction t, String version, int pkgId) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbPackageList[] list;
		try {
			com.aldorsolutions.webfdms.beans.DbPackageListSet plSet = new com.aldorsolutions.webfdms.beans.DbPackageListSet();
			h.put(DbPackageListPeer.VERSION, version);
			h.put(DbPackageListPeer.PKGPLID, new Integer(pkgId));
			h.put(DbPackageListPeer.SEQUENCENUMBER, DbPackageListPeer.SEQUENCENUMBER);
			plSet.restore(t, h);
			PersistentI[] obs = plSet.getPersistents();
			list = new com.aldorsolutions.webfdms.beans.DbPackageList[obs.length];
			// This trick is needed to make a Persistent[] into a PackageList[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (com.aldorsolutions.webfdms.beans.DbPackageList) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getPackageListForKey Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Retrieve Pall Bearers and Honorary Pall Bearers stored as survivors. Pall
	 * bearers have "sequence numbers" between 1005 and 1020. Creation date:
	 * (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbSurvivor[]
	 */
	public DbSurvivor[] getPallBearers(DatabaseTransaction t, int vitalsid) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		com.aldorsolutions.webfdms.beans.DbSurvivor[] survivorlist;
		try {
			com.aldorsolutions.webfdms.beans.DbSurvivorSet survivorSet = new com.aldorsolutions.webfdms.beans.DbSurvivorSet();
			h.put(DbSurvivorPeer.CASE_ID, new Integer(vitalsid));
			h.put(DbSurvivorPeer.PALLBEARERS, new Integer(1005));
			survivorSet.restore(t, h);
			PersistentI[] obs = survivorSet.getPersistents();
			survivorlist = new com.aldorsolutions.webfdms.beans.DbSurvivor[obs.length];
			// This trick is needed to make a Persistent[] into a Billto[]
			for (int i = 0; i < obs.length; i++) {
				survivorlist[i] = (com.aldorsolutions.webfdms.beans.DbSurvivor) obs[i];
			}
			return survivorlist;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getPallBearers Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the PnCharge class representing one itemized
	 * charge. Creation date: (2/3/03 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbPnCharge
	 */
	public DbPnCharge getPnCharge(DatabaseTransaction t, int recid) {
		DbPnCharge p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbPnCharge) DbPnCharge.getPersistent(t, recid, "com.aldorsolutions.webfdms.beans.DbPnCharge");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getPnCharge Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the PnContract class representing one Pre-need
	 * contract. Creation date: (2/3/03 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbPnContract
	 */
	public DbPnContract getPnContract(DatabaseTransaction t, int recid) {
		DbPnContract p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbPnContract) DbPnContract.getPersistent(t, recid, "com.aldorsolutions.webfdms.beans.DbPnContract");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getPnContract Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the DbPreneed class and return it for a specified
	 * vitals ID. Creation date: (1/06/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbPreneed
	 */
	public DbPreneed getPreneed(DatabaseTransaction t, int vitalsid) {
		DbPreneed p;
		try {
			p = (DbPreneed) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbPreneed");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getPreneed Persistence Exception:",e);
			return null;
		}
		return p;

	}

	public DbPreneedDisbursement getPreneedDisbursement(DatabaseTransaction t, int disbursementId) {
		DbPreneedDisbursement p;
		try {
			p = (DbPreneedDisbursement) Persistent.getPersistent(t, disbursementId, "com.aldorsolutions.webfdms.beans.DbPreneedDisbursement");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getPreneed Persistence Exception:",e);
			return null;
		}
		return p;
	}

	/**
	 * Create an instanace of the Services class and return it Creation date:
	 * (6/29/00 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbCharges
	 */
	public DbServices getServices(DatabaseTransaction t, int vitalsid) {
		DbServices p;
		try {
			p = (com.aldorsolutions.webfdms.beans.DbServices) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbServices");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getServices Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create an array of SpeedData objects for a Locale and category. Creation
	 * date: (12/31/01 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbSpeedData[]
	 */
	public DbSpeedData[] getSpeedData(String dblookup, int locale, String category) {

		return getSpeedData(dblookup, locale, category, null);
	}

	/**
	 * Create an array of SpeedData objects for a given Locale, category, and
	 * data search string. Creation date: (7/15/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbSpeedData[]
	 */
	public DbSpeedData[] getSpeedData(String dblookup, int locale, String category, String searchData) {

		return getSpeedDataLocation( dblookup, locale, -1,  category, searchData);
	}
	
	
	/**
	 * Create an array of SpeedData objects for a given Locale, category, and
	 * data search string. Creation date: (7/15/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbSpeedData[]
	 */
	public String getSpeedDataIgnoreCase(String dblookup, int locale, String category, String searchData) {
	  DbSpeedData[] sData = null;
	  String speedString = null;
	  boolean priceListFound=false;
	  String result=null;
	  
	  sData = getSpeedDataLocation( dblookup, locale, -1,  category, searchData);

	  if (sData != null && sData.length > 0){
	  	for (int c=0; c < sData.length; c++) {
	  		speedString = String.copyValueOf(sData[c].getData().toCharArray(), 0, sData[c].getData().indexOf(','));
	  		if (searchData.compareToIgnoreCase(speedString) == 0) {
	  			result = CsvTable.getField(sData[c].getData(), 2).trim();
	  			priceListFound=true;
	  			break;
	  		}
	   	}
	  		// if we did not find the pricelist then use a default
	  	if (priceListFound == false) {
	  		result = CsvTable.getField(sData[0].getData(), 2).trim();
	  	}
	  }
  	
  	return result;
	}


	/**
	 * Create an array of SpeedData objects for a given Locale, category, and
	 * data search string. Creation date: (7/15/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbSpeedData[]
	 */
	public DbPasswordLog[] getPasswordLogSet(DatabaseTransaction t, String userID, String password) {

		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		DbPasswordLog[] list;
		try {
			DbPasswordLogSet set = new DbPasswordLogSet();
			// retrieve all inactive items
			
			h.put(DbPasswordLogPeer.USERID, userID);
			
			if (password != null) {
				h.put(DbPasswordLogPeer.PASSWORD, password);
			}
			
			set.restore(t, h);
			
			PersistentI[] obs = set.getPersistents();
			list = new DbPasswordLog[obs.length];
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list[i] = (DbPasswordLog) obs[i];
			}
			return list;
		} catch (PersistenceException e) {
			logger.error("FdmsDb::getPasswordLogSet Persistence Exception:",e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public DbSpeedData[] getSpeedDataLocation(String dblookup, int locale, 
			int locationId, String category, String searchData) {
		
		DbSpeedData[] list;
		List <Object> filterList = new ArrayList <Object> ();
		if(category != null){
			filterList.add(SpeedDataCache.getInstance().makeCategoryFilter(category));
		}
		if(locale >= 0){
			filterList.add(SpeedDataCache.getInstance().makeLocaleFilter(Integer.toString(locale)));
		}
		if(searchData != null){
			filterList.add(SpeedDataCache.getInstance().makeDataFilter(searchData));
		}
		if(locationId >= 0){
			filterList.add(SpeedDataCache.getInstance().makeLocationFilter(Integer.toString(locationId)));
		}
		Entry[] filter =  new Entry[filterList.size()];
		filterList.toArray(filter);
		
		List <Object> dataList = SpeedDataCache.getInstance().getCache(filter, dblookup);
		
		SpeedDataComparator sdc = new SpeedDataComparator();
		TreeSet<DbSpeedData> sortedSet = new TreeSet <DbSpeedData> (sdc);
		
		for ( int i = 0; i < dataList.size(); i++ ) {
			sortedSet.add( (DbSpeedData) dataList.get(i) );
		}
		
		list = new DbSpeedData[sortedSet.size()];
		
		int i = 0;
		for ( DbSpeedData speedData : sortedSet ) {
			list[i++] = speedData;
		}
		
		return list;
	
	}

	/**
	 * Create a list of SpeedData Categories used in the DbPriceList table.
	 * Creation date: (1/21/02 9:11:17 AM)
	 * 
	 * @return ArrayList Category names
	 */
	public ArrayList <String> getSpeedDataCategories(DatabaseTransaction t) {
		ArrayList <String>cats = new ArrayList <String>();
		ResultSet rs;
		Statement stmt;
		Connection connection;
		try {
			connection = ((DatabaseTransaction) t).getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT DISTINCT TabCategory FROM speeddata order by TabCategory");
			while (rs.next()) {
				cats.add(rs.getString("TabCategory"));
			}

			return cats;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getPriceListVersions SQLException:",e);
			return null;
		} catch (SQLException e) {
			// logger.error("FdmsDb::getSpeedDataCategories SQLException:",e);
			return null;
		}
	}

	/**
	 * Get all SpeedData rows for a given category and locale. Return HTML
	 * "OPTION" statements for each row. Creation date: (12/20/00 12:08:05 PM)
	 * 
	 * @return java.lang.StringBuffer HTML "option" tags
	 * @param com.aldorsolutions.webfdms.beans.DbUserSession
	 *            user object to retrieve transaction and locale
	 * @param category
	 *            java.lang.String SpeedData TabCategory to select
	 * @param fieldNumber
	 *            int - specifies the field to be used as "value"
	 */
	public StringBuffer getSpeedDataOptionList(DbUserSession user, String category, int fieldNumber) {
		StringBuffer outBuf = new StringBuffer(100);
		String thisLine;
		String field1;
		DbSpeedData[] list;
		list = getSpeedData(user.getDbLookup(), user.getRegion(), category);

		for (int i = 0; i < list.length; i++) {
			// Remove characters offensive to HTML
			thisLine = CsvTable.replaceWithHtmlEntities(list[i].getData());
			// check if "value" is needed
			if (fieldNumber > 0) {
				// Get specific field from line for 'value'
				field1 = CsvTable.getField(thisLine, fieldNumber);
				outBuf.append("<option value=\"");
				outBuf.append(field1);
				outBuf.append("\" >");
			} else {
				outBuf.append("<option>");
			}
			outBuf.append(thisLine);
			outBuf.append("</option>\n");
		}
		
		return outBuf;
	}

	/**
	 * Get all SpeedData rows for a given category and locale. Return HTML
	 * "OPTION" statements for each row. Creation date: (12/20/00 12:08:05 PM)
	 * 
	 * @return java.lang.StringBuffer HTML "option" tags
	 * @param com.aldorsolutions.webfdms.beans.DbUserSession
	 *            user object to retrieve transaction and locale
	 * @param category
	 *            java.lang.String SpeedData TabCategory to select
	 * @param fieldNumber
	 *            int - specifies the field to be used as "value"
	 */
	public StringBuffer getSpeedDataOptionList(DbUserSession user, String category, int fieldNumber, String numberYesNo) {

		StringBuffer outBuf = new StringBuffer(100);
		String thisLine;
		int thisNumber;
		String field1;
		DbSpeedData[] list;
		DatabaseTransaction t = null;

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);

			int locationId = 0;
			if (user.getCurrentCaseID() > 0) {
				DbCase dbCase = this.getCase(t, user.getCurrentCaseID());
				locationId = dbCase.getChapelNumber();
			} else {
				locationId = user.getLocationId();
			}

			if (locationId == 0)
				list = getSpeedData(user.getDbLookup(), user.getRegion(), category);
			else {
				list = getSpeedDataLocation(user.getDbLookup(), user.getRegion(), locationId, category, null);
				if (list.length == 0)
					list = getSpeedData(user.getDbLookup(), user.getRegion(), category);
			}

			for (int i = 0; i < list.length; i++) {
				// Remove characters offensive to HTML
				thisLine = CsvTable.replaceWithHtmlEntities(list[i].getData());
				thisNumber = list[i].getId();
				// check if "value" is needed
				if (fieldNumber > 0) {
					// Get specific field from line for 'value'
					// special case of fileNumber==999 means to substitute the
					// sequence number for the option-value attribute.
					if (fieldNumber == 999) {
						field1 = Integer.toString(list[i].getSortSequence());
					} else {
						// Otherwise get specified CSV field# from data line as
						// VALUE
						field1 = CsvTable.getField(thisLine, fieldNumber);
					}
					outBuf.append("<option value=\"");
					outBuf.append(field1);
					if (numberYesNo != null && numberYesNo.equals("Yes")) {
						outBuf.append("\" id=\"");
						outBuf.append(String.valueOf(thisNumber));
					}
					outBuf.append("\">");
				} else {
					if (numberYesNo != null && numberYesNo.equals("Yes")) {
						outBuf.append("<option id=\"");
						outBuf.append(String.valueOf(thisNumber));
						outBuf.append("\">");
					} else {
						outBuf.append("<option>");
					}
				}
				outBuf.append(thisLine);
				outBuf.append("</option>\n");
			}
		} catch (PersistenceException e) {
			//  logger.error("getSpeedDataOptionList Exception:",e);
			return new StringBuffer("Exception:" + e);
		} finally {
			if (t != null) {
				t.closeConnection();
				t = null;
			}
		}

		return outBuf;
	}

	/**
	 * Create an instanace of the DbSpeedData class and return it for a single
	 * row. Creation date: (1/29/02 9:11:17 AM)
	 * 
	 * @return com.aldorsolutions.webfdms.beans.DbSpeedData
	 */
	public DbSpeedData getSpeedDataRow(DatabaseTransaction t, int recid) {
		DbSpeedData p;
		try {
			p = (DbSpeedData) Persistent.getPersistent(t, recid, "com.aldorsolutions.webfdms.beans.DbSpeedData");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getSpeedData Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Creates a collection of speeddata data of the State list. Creation date:
	 * (9/04/02 10:54:17 AM)
	 * 
	 * @return ArrayList
	 */
	//keep this but don't know why they are using the state list in the webfdmsdata1 it should not use this any more.
	public ArrayList <OptionsList> getStateList() {
		ArrayList <OptionsList>stateList = new ArrayList<OptionsList>();
		//String dbLookup = UtilSingleton.getInstance().getFdmsDBLookup();
		//String dbLookup = UtilSingleton.getInstance().getCommonDBLookup();
		String dbLookup = DAO.DB_FDMSCOMMON;
		try {
			
			SpeedDataDAO sdDao = new SpeedDataDAO (dbLookup);
			
			ArrayList <DbSpeedData> speedDataList = sdDao.getSpeedData(dbLookup, "States", 0, 0, null);
			
			for ( DbSpeedData data : speedDataList ) {
				String listValue = CsvTable.getField(data.getData(), 2);
				String listLabel = CsvTable.getField(data.getData(), 1);
				stateList.add(new OptionsList(listValue, listLabel));
			}
			
		} catch (PersistenceException e) {
			logger.error("FdmsDb::getStateList Persistence Exception:", e);
			//stateList = null;
		} 

		return stateList;
	}
	
//	public ArrayList <OptionsList> getStateList(String dbLookup) {
//		ArrayList <OptionsList>stateList = new ArrayList<OptionsList>();
//		//String dbLookup = UtilSingleton.getInstance().getFdmsDBLookup();
//		
//		try {
//			
//			SpeedDataDAO sdDao = new SpeedDataDAO (dbLookup);
//			
//			ArrayList <DbSpeedData> speedDataList = sdDao.getSpeedData(dbLookup, "States", 0, 0, null);
//			
//			for ( DbSpeedData data : speedDataList ) {
//				String listValue = CsvTable.getField(data.getData(), 2);
//				String listLabel = CsvTable.getField(data.getData(), 1);
//				stateList.add(new OptionsList(listValue, listLabel));
//			}
//			
//		} catch (PersistenceException e) {
//			logger.error("FdmsDb::getStateList Persistence Exception:", e);
//			stateList = null;
//		} 
//
//		return stateList;
//	}	
	

	/**
	 * Create an instanace of the Survivor class for one survivor and return it
	 * Creation date: (6/29/00 9:11:17 AM)
	 * 
	 * @return DbSurvivor
	 */
	public DbSurvivor getSurvivor(DatabaseTransaction t, int recid) {
		DbSurvivor p;
		try {
			p = (DbSurvivor) Persistent.getPersistent(t, recid, "com.aldorsolutions.webfdms.beans.DbSurvivor");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getSurvivor Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create a set of Survivors for one specific case Creation date: (11/26/01
	 * 9:11:17 AM)
	 * 
	 * @return DbSurvivors[]
	 */
	public DbSurvivor[] getSurvivorsForID(DatabaseTransaction t, int vitalsid) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		DbSurvivor[] survivorlist;
		try {
			DbSurvivorSet survivorSet = new DbSurvivorSet();
			h.put(DbSurvivorPeer.CASE_ID, new Integer(vitalsid));
			survivorSet.restore(t, h);
			PersistentI[] obs = survivorSet.getPersistents();
			survivorlist = new DbSurvivor[obs.length];
			// This trick is needed to make a Persistent[] into a Billto[]
			for (int i = 0; i < obs.length; i++) {
				survivorlist[i] = (DbSurvivor) obs[i];
			}
			return survivorlist;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getSurvivorForID Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Create a set of Survivors for one specific case Creation date: (11/26/01
	 * 9:11:17 AM)
	 * 
	 * @return DbSurvivors[]
	 */
	public DbSurvivor[] getSurvivorsForID(DatabaseTransaction t, int vitalsid, String orderBy) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		DbSurvivor[] survivorlist;
		try {
			DbSurvivorSet survivorSet = new DbSurvivorSet();
			h.put(DbSurvivorPeer.CASE_ID, new Integer(vitalsid));
			h.put("ORDER BY", new String(orderBy));
			survivorSet.restore(t, h);
			PersistentI[] obs = survivorSet.getPersistents();
			survivorlist = new DbSurvivor[obs.length];
			// This trick is needed to make a Persistent[] into a Billto[]
			for (int i = 0; i < obs.length; i++) {
				survivorlist[i] = (DbSurvivor) obs[i];
			}
			return survivorlist;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getSurvivorForID Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Create a set of Survivors for a search string Creation date: (1/06/02
	 * 9:11:17 AM)
	 * 
	 * @return DbSurvivors[]
	 */
	public DbSurvivor[] getSurvivorsForName(DatabaseTransaction t, String searchname, int locationId, int locale,
			int maxrows) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		DbSurvivor[] survivorlist;
		try {
			DbSurvivorSet survivorSet = new DbSurvivorSet();
			if (searchname != null) {
				String[] splittedSearchText = searchname.split(",");
				
				if(splittedSearchText.length > 1) {
					h.put(DbSurvivorPeer.LASTNAME, FormatString.escapeQuotes(splittedSearchText[0].trim()));
					h.put(DbSurvivorPeer.FIRSTNAME, FormatString.escapeQuotes(splittedSearchText[1].trim()));
				} else {
					h.put(DbSurvivorPeer.LASTNAME, FormatString.escapeQuotes(searchname.trim()));
				}
				h.put(DbCasePeer.LOCALE, new Integer(locale));

				if (locationId > 0)
					h.put(DbCasePeer.CHAPELNUMBER, new Integer(locationId));
			}
			if (maxrows > 0) {
				h.put("MaxRows", String.valueOf(maxrows));
			}

			survivorSet.restore(t, h);
			PersistentI[] obs = survivorSet.getPersistents();
			survivorlist = new DbSurvivor[obs.length];
			// This trick is needed to make a Persistent[] into a Billto[]
			for (int i = 0; i < obs.length; i++) {
				survivorlist[i] = (DbSurvivor) obs[i];
			}
			return survivorlist;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getSurvivorForName Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Create a survivor for a case with a specic sequence number. Creation
	 * date: (3/20/02 9:11:17 AM)
	 * 
	 * @return DbSurvivors[]
	 */
	public DbSurvivor[] getSurvivorsForSequenceNo(DatabaseTransaction t, int vitalsid, int seqno) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		DbSurvivor[] survivorlist;
		try {
			DbSurvivorSet survivorSet = new DbSurvivorSet();
			h.put(DbSurvivorPeer.CASE_ID, new Integer(vitalsid));
			h.put(DbSurvivorPeer.SEQNUMBER, new Integer(seqno));
			survivorSet.restore(t, h);
			PersistentI[] obs = survivorSet.getPersistents();
			survivorlist = new DbSurvivor[obs.length];
			// This trick is needed to make a Persistent[] into a Billto[]
			for (int i = 0; i < obs.length; i++) {
				survivorlist[i] = (DbSurvivor) obs[i];
			}
			return survivorlist;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getSurvivorForSequenceNo Persistence
			// Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the DbUser class representing one User. Creation
	 * date: (4/30/02 9:11:17 AM)
	 * 
	 * @return DbUserSession
	 */
	public DbUserSession getUser(DatabaseTransaction t, int recid) {
		DbUser p;
		try {
			p = (DbUser) DbUser.getPersistent(t, recid, "com.aldorsolutions.webfdms.beans.DbUser");
			t.addPersistent(p); // make the persistent part of the transaction
			return p;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getUser Persistence Exception:",e);
			return null;
		}
	}

	/**
	 * Create an instanace of the DbUser class representing one user. The user
	 * is retreived by the column name that is sent and the value for that
	 * column. Note this persistent is not added to any transaction
	 * automatically. Creation date: (5/16/02 9:11:17 AM)
	 * 
	 * @return DbUser
	 */
	public DbUserSession getUser(String colName, String colValue) {
		DbUser p = null;
		DatabaseTransaction t = null;
		Properties dbProps = new Properties();
		dbProps.put("user", "webfdms");
		dbProps.put("password", "webapp");

		String userLookup = UtilSingleton.getInstance().getUserDBLookup();
        
		try {
			t = new DatabaseTransaction (userLookup, 0, 0, 0);
			DbUserSet users = new DbUserSet();
			Hashtable <String, Object> h = new Hashtable<String, Object> ();
			h.put(colName, colValue);
			users.restore(t, h);
			PersistentI[] obs = users.getPersistents();
			if (obs.length >= 1) {
				p = (DbUser) obs[0];
			}
			return p;
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getUser Persistence Exception:",e);
			return null;
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
	}

	/**
	 * Create an instanace of the Veterans class and return it Creation date:
	 * (12/23/01 9:11:17 AM)
	 * 
	 * @return DbVeteran
	 */
	public DbVeteran getVeteran(DatabaseTransaction t, int vitalsid) {
		DbVeteran p = null;

		try {
			p = (DbVeteran) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbVeteran");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getCustom Persistence Exception:",e);
			return null;
		}

		return p;
	}

	/**
	 * Create an instanace of the VitalsDeceased class and return it Creation
	 * date: (1/06/02 9:11:17 AM)
	 * 
	 * @return DbVeteran
	 */
	public DbVitalsDeceased getVitalsDeceased(DatabaseTransaction t, int vitalsid) {
		DbVitalsDeceased p = null;
		try {
			p = (DbVitalsDeceased) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbVitalsDeceased");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getVitalsDeceased Persistence
			// Exception:",e);
			return null;
		}
		return p;
	}

	/**
	 * Create an instanace of the FunderalSyncListBean class and return it Creation
	 * date: (1/06/02 9:11:17 AM)
	 * 
	 * @return FunderalSyncListBean
	 */
	public ArrayList <FuneralSyncListBean> getFuneralSyncCases(String dbLookup, int clientId, boolean onlyUnposted) {
		ArrayList <FuneralSyncListBean> beans = new ArrayList<FuneralSyncListBean>();
		
		try {
			
			ObituaryFuneralSyncDAO OFSyncDao = new ObituaryFuneralSyncDAO (dbLookup);
			
			beans = OFSyncDao.getObituaryFuneralSync(dbLookup,clientId, onlyUnposted);
			
		} catch (PersistenceException e) {
			logger.error("FdmsDb::getFuneralSyncCases Persistence Exception:", e);
			beans = null;
		} 

	return beans;
	}
//	public FuneralSyncListBean[] getFuneralSyncCases(DatabaseTransaction t, int clentId) {
//		Hashtable <String, Object> h = new Hashtable<String, Object> ();
//		FuneralSyncListBean[] list;
//		
//		try {
//			
//			ObituaryFuneralSyncSet set = new ObituaryFuneralSyncSet();
//			h.put(ObituaryFuneralSyncPeer.CLIENTID, new Long(clentId));
//			set.restore(t, h);
//			
//			PersistentI[] obs = set.getPersistents();
//			list = new FuneralSyncListBean[obs.length];
//			
//			// This trick is needed to make a Persistent[] into a DbLocaleConfig[]
//			for (int i = 0; i < obs.length; i++) {
//				list[i] = (FuneralSyncListBean) obs[i];
//			}
//			
//			return list;
//			
//		} catch (PersistenceException e) {
//			logger.error("FdmsDb::getFuneralSyncCases Persistence Exception:", e);
//			return null;
//		} catch (Exception e) {
//			logger.error("FdmsDb::getFuneralSyncCases Persistence Exception:", e);
//			return null;
//		}
//	}
	/**
	 * Create an instanace of the FuneralSyncToVitals class and return it Creation
	 * date: (1/06/02 9:11:17 AM)
	 * 
	 * @return DbVeteran
	 */
	public DbFuneralSyncToVitals getVitalsFromFuneralSync(DatabaseTransaction t, int vitalsid) {
		DbFuneralSyncToVitals p = null;
		try {
			p = (DbFuneralSyncToVitals) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbFuneralSyncToVitals");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getVitalsDeceased Persistence
			// Exception:",e);
			return null;
		}
		return p;
	}
	/**
	 * Create an instanace of the VitalsFirstCall class and return it Creation
	 * date: (1/06/02 9:11:17 AM)
	 * 
	 * @return DbVeteran
	 */
	public DbVitalsFirstCall getVitalsFirstCall(DatabaseTransaction t, int vitalsid) {
		DbVitalsFirstCall p;
		try {
			p = (DbVitalsFirstCall) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbVitalsFirstCall");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getVitalsFirstCall Persistence
			// Exception:",e);
			return null;
		}
		return p;
	}

	/**
	 * Create an instanace of the VitalsInformant class and return it Creation
	 * date: (1/06/02 9:11:17 AM)
	 * 
	 * @return DbVeteran
	 */
	public DbVitalsInformant getVitalsInformant(DatabaseTransaction t, int vitalsid) {
		DbVitalsInformant p = null;
		try {
			p = (DbVitalsInformant) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbVitalsInformant");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getVitalsInformant Persistence
			// Exception:",e);
			return null;
		}
		return p;
	}

	/**
	 * Create an instanace of the VitalsMedical class which has cause of death
	 * and physician data about the deceased. Creation date: (1/06/02 9:11:17
	 * AM)
	 * 
	 * @return DbVeteran
	 */
	public DbVitalsMedical getVitalsMedical(DatabaseTransaction t, int vitalsid) {
		DbVitalsMedical p = null;
		try {
			p = (DbVitalsMedical) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbVitalsMedical");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getVitalsMedical Persistence
			// Exception:",e);
			return null;
		}
		return p;
	}

	/**
	 * Create an instanace of the VitalsNextKin class and return it Creation
	 * date: (3/04/02 9:11:17 AM
	 * 
	 * @return DbVitalsNextKin
	 */
	public DbVitalsNextKin getVitalsNextKin(DatabaseTransaction t, int vitalsid) {
		DbVitalsNextKin p = null;
		try {
			p = (DbVitalsNextKin) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbVitalsNextKin");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getVitalsNextKin Persistence
			// Exception:",e);
			return null;
		}
		return p;
	}

	public DbVitalsExecutor getVitalsExecutor(DatabaseTransaction t, int vitalsid) {
		DbVitalsExecutor p = null;
		try {
			p = (DbVitalsExecutor) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbVitalsExecutor");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			logger.error("Exception in getVitalsExecutor() ", e);
			//  logger.error("FdmsDb::getVitalsNextKin Persistence
			// Exception:",e);

			return null;
		}
		return p;
	}

	/**
	 * Create an instanace of the VitalsSchedule class and return it. Creation
	 * date: (1/06/02 9:11:17 AM)
	 * 
	 * @return DbVitalsSchedule
	 */
	public DbVitalsSchedule getVitalsSchedule(DatabaseTransaction t, int vitalsid) {
		DbVitalsSchedule p = null;
		try {
			p = (DbVitalsSchedule) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbVitalsSchedule");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getVitalsSchedule Persistence
			// Exception:",e);
			return null;
		}
		return p;
	}

	/**
	 * Create an instanace of the VitalsSpouse class for one case and return it.
	 * Creation date: (1/22/02 9:11:17 AM)
	 * 
	 * @return DbVitalsSpouse
	 */
	public DbVitalsSpouse getVitalsSpouse(DatabaseTransaction t, int vitalsid) {
		DbVitalsSpouse p;
		try {
			p = (DbVitalsSpouse) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbVitalsSpouse");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			// logger.error("FdmsDb::getVitalsSpouse Persistence Exception:",e);
			return null;
		}
		return p;
	}

	/**
	 * Handle the processing for returning a sold inventory item. Add the
	 * quantity back to on hand count. Add an inventory history transaction.c
	 * Creation date: (10/9/2002 11:39:23 AM)
	 * 
	 * @return InventorySold Information about item sold
	 * @param t
	 *            DatabaseTransaction
	 * @param masterID
	 *            int - Identifier of inventory master to sell
	 * @param onHandID
	 *            int - Identifier of inventory on hand record to sell
	 * @param qty
	 *            int - Quantity to sell
	 * @param tranType
	 *            java.lang.String - Inventory history transaction type
	 * @param tranDate
	 *            java.lang.String - Transaction date
	 * @param reference
	 *            java.lang.String - reference or invoice number
	 * @param location
	 *            java.lang.String - inventory location
	 * @param price
	 *            int - price sold for
	 * @param descr
	 *            java.lang.String - memo or description of transaction
	 * @exception PersistenceException
	 *                The exception description.
	 */
	public InventorySold inventoryReturn(DatabaseTransaction t, int masterID, int onHandID, int qty, String tranType,
			String tranDate, String reference, String location, int price, String descr, int vitalsID,
			InventorySold soldData) throws PersistenceException {

		logger.debug("*** Entering inventoryReturn() ***");

		DbInvHistory dbInvHistory = new DbInvHistory();
		dbInvHistory.setNew();
		DbInvMaster item = null;
		DbInvOnHand onhand = null;
		int costofsale = 0;

		try {
			// Fetch inventory master item
			item = getInvMaster(t, masterID);
			onhand = getInvOnHand(t, onHandID);
			if (item == null) { // || onhand == null - can't do this because of
				// non-stocked
				throw new PersistenceException("Invalid inventory item specified for sale: " + masterID);
			}
			// Serial numered item must have on hand selected already
			if (item.getCStockType().equals(DbInvMaster.STOCK_TYPE_SERIAL) && onhand == null) {
				throw new PersistenceException(
						"Serial numbered item must have onhand transaction in order to return it: "
								+ item.getCDescription());
			}
			// Default cost for non-stocked items
			costofsale = item.getLCost();
			// Serial and stocked Items - processing done just for these types
			// of inventory
			if (item.getCStockType().equals(DbInvMaster.STOCK_TYPE_SERIAL)
					|| item.getCStockType().equals(DbInvMaster.STOCK_TYPE_STOCKED)) {
				if (onhand == null) {
					throw new PersistenceException("No inventory on hand record for:" + item.getCDescription()
							+ ". Cannot return item to inventory.");
				}
				onhand.setQuantity(onhand.getQuantity() + qty);
				costofsale = onhand.getCost();
				dbInvHistory.setCSerialNumber(onhand.getCSerialNumber());
				dbInvHistory.setLSequenceNumber(onhand.getId());
				location = onhand.getCLocation();
			} else {
				dbInvHistory.setCSerialNumber("");
				if (location == null) {
					location = "";
				}
			}
			// Check for override of default GL accounts from inventory master
			// object
			// Note that soldData is passed into this method and should have
			// default GL accounts already present
			if (item.getCGLcostAcct() != null && item.getCGLcostAcct().length() > 0) {
				soldData.setAcctCostOfSale(item.getCGLcostAcct());
			}
			if (item.getCGLassetAcct() != null && item.getCGLassetAcct().length() > 0) {
				soldData.setAcctInvAsset(item.getCGLassetAcct());
			}
			if (item.getCGLsalesAcct() != null && item.getCGLsalesAcct().length() > 0) {
				soldData.setAcctRevenue(item.getCGLsalesAcct());
			}
			dbInvHistory.setCItemName(item.getCItemName());
			dbInvHistory.setCTranType(tranType);
			dbInvHistory.setCTransactionDate(tranDate);
			dbInvHistory.setCReferenceNumber(reference);
			dbInvHistory.setCLocation(location);
			dbInvHistory.setLQuantity(qty);
			dbInvHistory.setLSellingPrice(price);
			dbInvHistory.setLPurchaseCost(costofsale);
			dbInvHistory.setCGLsalesAcct(soldData.getAcctRevenue());
			dbInvHistory.setCGLcostAcct(soldData.getAcctCostOfSale());
			dbInvHistory.setCGLassetAcct(soldData.getAcctInvAsset());
			dbInvHistory.setCDescription(descr);
			dbInvHistory.setMasterId(masterID);
			dbInvHistory.setLVitalsNumber(vitalsID);
			t.addPersistent(dbInvHistory);
			// fill soldData object to return to caller
			//soldData.setAcctCostOfSale( item.getCGLcostAcct());
			//soldData.setAcctInvAsset( item.getCGLassetAcct());
			soldData.setCost(costofsale);
			soldData.setItemName(item.getCItemName());
			soldData.setSerialNumber(dbInvHistory.getCSerialNumber());
			soldData.setStockType(item.getCStockType());
		} catch (PersistenceException e) {
			throw e;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return soldData;
	}

	/**
	 * Handle the processing for selling an inventory item. Remove the item from
	 * the quantity on hand count and add an inventory history transaction.
	 * Creation date: (10/9/2002 11:39:23 AM)
	 * 
	 * @return int Cost of sale amount.
	 * @param t
	 *            DatabaseTransaction
	 * @param masterID
	 *            int - Identifier of inventory master to sell
	 * @param onHandID
	 *            int - Identifier of inventory on hand record to sell
	 * @param qty
	 *            int - Quantity to sell
	 * @param tranType
	 *            java.lang.String - Inventory history transaction type
	 * @param tranDate
	 *            java.lang.String - Transaction date
	 * @param reference
	 *            java.lang.String - reference or invoice number
	 * @param location
	 *            java.lang.String - inventory location
	 * @param price
	 *            int - price sold for
	 * @param descr
	 *            java.lang.String - memo or description of transaction
	 * @exception PersistenceException
	 *                The exception description.
	 */
	
	public InventorySold inventorySell(DatabaseTransaction t, int masterID, int onHandID, int qty, String tranType,
			String tranDate, String reference, String location, int price, String descr, int vitalsID,
			InventorySold soldData) throws PersistenceException{
		
		return inventorySell( t,  masterID,  onHandID,  qty,  tranType,
				 tranDate,  reference,  location,  price,  descr,  vitalsID,
				 soldData,"");
	}
	
	public InventorySold inventorySell(DatabaseTransaction t, int masterID, int onHandID, int qtyToRemove, String tranType,
			String tranDate, String reference, String location, int price, String descr, int vitalsID,
			InventorySold soldData, String reason) throws PersistenceException {
		//AppLog.trace("InventorySell method starting");
		//DbInvHistory dbInvHistory = null;
		DbInvHistory dbInvHistory = new DbInvHistory();
		dbInvHistory.setNew();
		DbInvMaster item = null;
		DbInvOnHand onhand = null;
		DbInvOnHand[] onHandForItem = null;

		int costofsale = 0;
		//InventorySold soldData = new InventorySold();
		soldData.setOnhandID(onHandID); // default for serial#, set for stocked
		// type later
		try {
			// Fetch inventory master item
			item = getInvMaster(t, masterID);
			onhand = getInvOnHand(t, onHandID);
			if (item == null) { // || onhand == null - can't do this because of
				// non-stocked
				//AppLog.trace("InventorySell: invalid masterID: "+masterID);
				throw new PersistenceException("Invalid inventory item specified for sale: " + masterID);
			}
			logger.debug("InventorySell: Selling: " + masterID + " " + item.getCDescription() + " onhand:" + onHandID
					+ " " + onhand);
			// Serial numered item must have on hand selected already
			if (item.getCStockType().equals(DbInvMaster.STOCK_TYPE_SERIAL) && onhand == null) {
				//AppLog.trace("InventorySell: Invalid or missing onhand ID:
				// "+onHandID);
				throw new PersistenceException("Serial numbered item not yet selected for: " + item.getCDescription());
			}
			/*
			 * 
			 * Code Added by Chai for Qty on Hand value but for below code we got error on onhand.getQuantity() is null b/c onhand object is null 
			int quantity = onhand.getQuantity();
			int counter = 0;
			
			do {
				
				dbInvHistory = new DbInvHistory();
				dbInvHistory.setNew();
				
				if (item.getCStockType().equals(DbInvMaster.STOCK_TYPE_STOCKED)) {
					//AppLog.trace("InventorySell: retrieving on hand record for
					// stocked item: "+masterID);
					onHandForItem = getInvOnHandForItem(t, masterID);
					if (onHandForItem.length > 0) {
						onHandID = onHandForItem[counter].getId();
						soldData.setOnhandID(onHandID);
						onhand = onHandForItem[counter];
						
						quantity = onhand.getQuantity();
						counter++;
						
						t.addPersistent(onhand);
					} else {
						//AppLog.error("InventorySell: ERROR no on-hand records for
						// stocked item: "+masterID+" "+item.getCDescription());
					}
				}
				
				// Default cost for non-stocked items
				costofsale = item.getLCost();
				// Serial and stocked Items - processing done just for these types
				// of inventory
				
				if (item.getCStockType().equals(DbInvMaster.STOCK_TYPE_SERIAL)
						|| item.getCStockType().equals(DbInvMaster.STOCK_TYPE_STOCKED)) {
					if (onhand == null) {
						throw new PersistenceException("No inventory on hand for:" + item.getCDescription());
					}
					
					// If quantity to remove is greater than quantity then require to iterate this process.
					if( (quantity - qtyToRemove) < 0 ) {
						onhand.setQuantity(0);
					} else {
						onhand.setQuantity(quantity - qtyToRemove);
					}

					costofsale = onhand.getCost();
					dbInvHistory.setCSerialNumber(onhand.getCSerialNumber());
					dbInvHistory.setLSequenceNumber(onhand.getId());
					// Default location
					if (location == null || location.length() < 1) {
						location = onhand.getCLocation();
					}
				} else {
					//AppLog.trace("InventorySell: processing non-stocked item:
					// "+item.getCStockType());
					dbInvHistory.setCSerialNumber("");
					// Default location
					if (location == null) {
						location = "";
					}
				}
				
				// Check for override of default GL accounts from inventory master
				// object
				// Note that soldData is passed into this method and should have
				// default GL accounts already present
				if (item.getCGLcostAcct() != null && item.getCGLcostAcct().length() > 0) {
					soldData.setAcctCostOfSale(item.getCGLcostAcct());
				}
				if (item.getCGLassetAcct() != null && item.getCGLassetAcct().length() > 0) {
					soldData.setAcctInvAsset(item.getCGLassetAcct());
				}
				if (item.getCGLsalesAcct() != null && item.getCGLsalesAcct().length() > 0) {
					soldData.setAcctRevenue(item.getCGLsalesAcct());
				}
				soldData.setCost(costofsale);
				soldData.setItemName(item.getCItemName());
				soldData.setSerialNumber(dbInvHistory.getCSerialNumber());
				soldData.setStockType(item.getCStockType());
				
				//AppLog.trace("InventorySell: adding inventory history
				// transaction");
				dbInvHistory.setCItemName(item.getCItemName());
				dbInvHistory.setCTranType(tranType);
				dbInvHistory.setCTransactionDate(tranDate);
				dbInvHistory.setCReferenceNumber(reference);
				dbInvHistory.setCLocation(location);
				dbInvHistory.setLQuantity(-qtyToRemove); // sale transacitons are negative
				// quantity
				dbInvHistory.setLSellingPrice(price);
				dbInvHistory.setLPurchaseCost(costofsale);
				dbInvHistory.setCGLsalesAcct(soldData.getAcctRevenue());
				dbInvHistory.setCGLcostAcct(soldData.getAcctCostOfSale());
				dbInvHistory.setCGLassetAcct(soldData.getAcctInvAsset());
				dbInvHistory.setCDescription(descr);
				dbInvHistory.setReason(reason);
				dbInvHistory.setMasterId(masterID);
				dbInvHistory.setLVitalsNumber(vitalsID);
				// if quantity sold is not zero, then add to transaction so will be
				// saved to database.
				// if quantity is zero, all we really want is the soldData object
				// populated.
				if (qtyToRemove != 0) {
					t.addPersistent(dbInvHistory);
				}
				
				qtyToRemove -= quantity;
			} while(qtyToRemove > 0);
			*/
			
			// If stocked item, get the one and only on-hand record for that
			// item.
			if (item.getCStockType().equals(DbInvMaster.STOCK_TYPE_STOCKED)) {
				//AppLog.trace("InventorySell: retrieving on hand record for
				// stocked item: "+masterID);
				onHandForItem = getInvOnHandForItem(t, masterID);
				if (onHandForItem.length > 0) {
					onHandID = onHandForItem[0].getId();
					soldData.setOnhandID(onHandID);
					onhand = onHandForItem[0];
					t.addPersistent(onhand);
				} else {
					//AppLog.error("InventorySell: ERROR no on-hand records for
					// stocked item: "+masterID+" "+item.getCDescription());
				}
			}
			// Default cost for non-stocked items
			costofsale = item.getLCost();
			// Serial and stocked Items - processing done just for these types
			// of inventory
			if (item.getCStockType().equals(DbInvMaster.STOCK_TYPE_SERIAL)
					|| item.getCStockType().equals(DbInvMaster.STOCK_TYPE_STOCKED)) {
				if (onhand == null) {
					throw new PersistenceException("No inventory on hand for:" + item.getCDescription());
				}
				//AppLog.trace("InventorySell: reducing qty on
				// hand:"+onhand.getQuantity()+" onhandID:"+onhand.getId());
				onhand.setQuantity(onhand.getQuantity() - qtyToRemove);
				costofsale = onhand.getCost();
				dbInvHistory.setCSerialNumber(onhand.getCSerialNumber());
				dbInvHistory.setLSequenceNumber(onhand.getId());
				// Default location
				if (location == null || location.length() < 1) {
					location = onhand.getCLocation();
				}
			} else {
				//AppLog.trace("InventorySell: processing non-stocked item:
				// "+item.getCStockType());
				dbInvHistory.setCSerialNumber("");
				// Default location
				if (location == null) {
					location = "";
				}
			}
			// Check for override of default GL accounts from inventory master
			// object
			// Note that soldData is passed into this method and should have
			// default GL accounts already present
			if (item.getCGLcostAcct() != null && item.getCGLcostAcct().length() > 0) {
				soldData.setAcctCostOfSale(item.getCGLcostAcct());
			}
			if (item.getCGLassetAcct() != null && item.getCGLassetAcct().length() > 0) {
				soldData.setAcctInvAsset(item.getCGLassetAcct());
			}
			if (item.getCGLsalesAcct() != null && item.getCGLsalesAcct().length() > 0) {
				soldData.setAcctRevenue(item.getCGLsalesAcct());
			}
			soldData.setCost(costofsale);
			soldData.setItemName(item.getCItemName());
			soldData.setSerialNumber(dbInvHistory.getCSerialNumber());
			soldData.setStockType(item.getCStockType());

			//AppLog.trace("InventorySell: adding inventory history
			// transaction");
			dbInvHistory.setCItemName(item.getCItemName());
			dbInvHistory.setCTranType(tranType);
			dbInvHistory.setCTransactionDate(tranDate);
			dbInvHistory.setCReferenceNumber(reference);
			dbInvHistory.setCLocation(location);
			dbInvHistory.setLQuantity(-qtyToRemove); // sale transacitons are negative
			// quantity
			dbInvHistory.setLSellingPrice(price);
			dbInvHistory.setLPurchaseCost(costofsale);
			dbInvHistory.setCGLsalesAcct(soldData.getAcctRevenue());
			dbInvHistory.setCGLcostAcct(soldData.getAcctCostOfSale());
			dbInvHistory.setCGLassetAcct(soldData.getAcctInvAsset());
			dbInvHistory.setCDescription(descr);
			dbInvHistory.setReason(reason);
			dbInvHistory.setMasterId(masterID);
			dbInvHistory.setLVitalsNumber(vitalsID);
			// if quantity sold is not zero, then add to transaction so will be
			// saved to database.
			// if quantity is zero, all we really want is the soldData object
			// populated.
			if (qtyToRemove != 0) {
				t.addPersistent(dbInvHistory);
			}
		} catch (PersistenceException e) {
			throw e;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		//AppLog.trace("InventorySold method ended");
		return soldData;
	}
	
	public void removeInventorySell(DatabaseTransaction t, int masterID,
			int qtyToRemove, String tranType, String tranDate,
			String reference, String location, int invOnHandId, String descr,
			int vitalsID, String reason) throws PersistenceException {
		
		DbInvHistory dbInvHistory = new DbInvHistory();
		dbInvHistory.setNew();
		DbInvMaster item = null;
		DbInvOnHand onhand = null;

		int costofsale = 0;
		
		try {
			item = getInvMaster(t, masterID);
			onhand = getInvOnHand(t, invOnHandId);
			if (item == null) {
				throw new PersistenceException("Invalid inventory item specified for sale: " + masterID);
			}
			
			logger.debug("InventorySell: Selling: " + masterID + " "
					+ item.getCDescription() + " onhand: " + invOnHandId + " "
					+ onhand);

			if ((item.getCStockType().equals("#")) && (onhand == null)) {
				throw new PersistenceException(
						"Serial numbered item not yet selected for: "
								+ item.getCDescription());
			}

			if (item.getCStockType().equals("S")) {
				t.addPersistent(onhand);
			}

			costofsale = item.getLCost();

			if ((item.getCStockType().equals("#")) || (item.getCStockType().equals("S"))) {
				if (onhand == null) {
					throw new PersistenceException("No inventory on hand for:"
							+ item.getCDescription());
				}

				onhand.setQuantity(onhand.getQuantity() - qtyToRemove);
				costofsale = onhand.getCost();
				dbInvHistory.setCSerialNumber(onhand.getCSerialNumber());
				dbInvHistory.setLSequenceNumber(onhand.getId());

				if ((location == null) || (location.length() < 1)) {
					location = onhand.getCLocation();
				}
			} else {
				dbInvHistory.setCSerialNumber("");

				if (location == null) {
					location = "";
				}

			}

			dbInvHistory.setCItemName(item.getCItemName());
			dbInvHistory.setCTranType(tranType);
			dbInvHistory.setCTransactionDate(tranDate);
			dbInvHistory.setCReferenceNumber(reference);
			dbInvHistory.setCLocation(location);
			dbInvHistory.setLQuantity(-qtyToRemove);

			dbInvHistory.setLPurchaseCost(costofsale);

			if ((item.getCGLcostAcct() != null) && (item.getCGLcostAcct().length() > 0)) {
				dbInvHistory.setCGLcostAcct(item.getCGLcostAcct());
			}
			if ((item.getCGLassetAcct() != null) && (item.getCGLassetAcct().length() > 0)) {
				dbInvHistory.setCGLassetAcct(item.getCGLassetAcct());
			}
			if ((item.getCGLsalesAcct() != null) && (item.getCGLsalesAcct().length() > 0)) {
				dbInvHistory.setCGLsalesAcct(item.getCGLsalesAcct());
			}

			dbInvHistory.setCDescription(descr);
			dbInvHistory.setReason(reason);
			dbInvHistory.setMasterId(masterID);
			dbInvHistory.setLVitalsNumber(vitalsID);

			if (qtyToRemove != 0)
				t.addPersistent(dbInvHistory);
			
		} catch (PersistenceException e) {
			throw e;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * Determine if a specified case has been "posted" which means the charges
	 * are more or less finalized and have been "sent to accounting". When a
	 * case is posted, history transactions are created and payments can be
	 * applied to the case. Creation date: (5/17/01 11:31:36 AM)
	 * 
	 * @return boolean
	 * @param vitalsID
	 *            int
	 */
	public boolean isCasePosted(DatabaseTransaction t, int vitalsID) {
		DbFinancialSummary finan = getFinancial(t, vitalsID);

		if (finan != null && finan.getIARsentBox() != 0){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Determine if a contract charge can be deleted from a case Creation date:
	 * (5/17/01 11:27:07 AM)
	 * 
	 * @return boolean
	 * @param aCharge
	 *            com.aldorsolutions.webfdms.databaseChargeItem
	 */
	public boolean isChargeDeletable(DatabaseTransaction t, DbChargeItem aCharge) {

		// if Record Number is zero then the charge has not been actually added
		// to the data file so can be deleted
		if (aCharge.getRecordNumber() < 1)
			return true;

		// if case is not posted, then charge can be deleted
		if (isCasePosted(t, aCharge.getVitalsID()) == false)
			return true;

		// ToDo: translate decision code from fdfinan.cpp

		return false;
	}

	/**
	 * Void a payment by marking the specified history record as voided and
	 * adding an adjusting history transaction. Creation date: (6/28/2002
	 * 12:06:40 PM)
	 * 
	 * @param user
	 *            DbSessionUser - user object identifying who the operator is
	 * @param histID
	 *            int - ID of transaction history payment to be voided
	 * @return int - ID of adjusting transaction added to history
	 * @exception PersistenceException
	 *                Indicates database could not be updated.
	 */
	public int paymentVoid(DbUserSession user, int tranID, String comment, Date datePaid) throws PersistenceException {
		DatabaseTransaction t = null;
		
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);

			//AppLog.trace("paymentVoid starting for transaction: "+tranID);

			// get transaction date in format needed by DbHistory
			
//			Calendar gc = Calendar.getInstance();
//			Date datePaid = new Date(gc.getTimeInMillis());

			// Fetch history transaction to void
			DbHistory payment = FdmsDb.getInstance().getHistory(t, tranID);
			if (payment == null) {
				//AppLog.warning("PaymentVoid - PersistenceException fetching
				// payment history tran:"+tranID);
				throw new PersistenceException("Error accessing payment history.");
			}
			int vitalsID = payment.getLMainKey();

			// Make sure payment is not already voided
			if (payment.getCHistDeleteTran() == 'V' || payment.getCHistDeleteTran() == 'A') {
				throw new PersistenceException("This payment was previously voided.");
			}
			payment.setCHistDeleteTran('V');
			long createdTimestamp = System.currentTimeMillis();
			payment.setDatetimeOfTrans(createdTimestamp);
			// Check for blank component code in payment history and substitute
			// default code
			String compCode = payment.getCHistPmtComponent();
			if (compCode.compareTo("   ") <= 0) {
				//AppLog.trace("paymentVoid. getting default component since
				// history has blank code");
				// Get default component for user's region
				PaymentComponentAvailableSet compsAvailable = new PaymentComponentAvailableSet(user.getRegion(), user.getDbLookup());
				PaymentComponentAvailable defComp = compsAvailable.getDefaultComponent();
				compCode = defComp.getCode();
			}
			
			DbVitalsDeceased deceased = null;
			deceased = FdmsDb.getInstance().getVitalsDeceased(t, vitalsID);
			String serviceDate = deceased.getServiceDateKey();

			
			// Add payment transaction to history
			DbHistory hist = new DbHistory();
			hist.setNew();
			t.addPersistent(hist);
			hist.setLMainKey(vitalsID);
			hist.setIHistType(payment.getIHistType());
			hist.setCHistDesc("Adjustment for voided payment");
			hist.setLHistAmount(-(payment.getLHistAmount())); // reverse sign
			hist.setCHistGLAcct(payment.getCHistGLAcct());
			hist.setCHistARacct(payment.getCHistARacct());
			hist.setLHistExempt(0);
			hist.setCHistDate( datePaid );
			hist.setCHistSPF('P');
			hist.setLHistReceiptNo(payment.getLHistReceiptNo());
			hist.setCHistManualReceipt(payment.getCHistManualReceipt());
			hist.setCHistPayMethod(payment.getCHistPayMethod());
			hist.setCHistDatePaid(payment.getCHistDatePaid());
			hist.setCHistOriginalPosting('N');
			hist.setCHistDeleteTran('A');
			hist.setCHistPmtComponent(compCode);
			hist.setLocationId(payment.getLocationId());
			hist.setServiceDate(serviceDate);
			hist.setDatetimeOfTrans(createdTimestamp);
			hist.setComment("tranID="+tranID+", Tran Date="+payment.getCHistDate()+", "+comment);
			
			// Adjust component for this case
			DbComponent component = null;
			DbComponent[] comps = FdmsDb.getInstance().getComponentsForID(t, vitalsID, compCode);
			if (comps == null || comps.length < 1) {
				// if no component found to adjust, then adjust the default
				// component for this case.
				DbComponentSet compset = new DbComponentSet(t, vitalsID);
				component = compset.getDefaultComponent(user.getRegion(), user.getDbLookup());
			} else {
				component = comps[0];
			}
			// Do not update component if it is the "financed" component
			if (component.getType().compareTo("F") != 0) {
				component.setPaidAmt(component.getPaidAmt() + payment.getLHistAmount());
				t.addPersistent(component);
			}
			// Update total paid in financial object
			DbFinancialSummary finan = FdmsDb.getInstance().getFinancial(t, vitalsID);
			if (finan == null) {
				//AppLog.warning("PaymentVoid - No financial record found for
				// ID:"+vitalsID);
				throw new PersistenceException("Invalid case ID found:" + vitalsID);
			}
			if (component.getType().compareTo("F") != 0) {
				finan.setLTotalPaidToDate(finan.getLTotalPaidToDate() + payment.getLHistAmount());
			}
			// Now store changes to all tables through the same transaction
			t.save();
			//AppLog.trace("PaymentVoid: finished save, ID:"+hist.getId());
			return hist.getId();
			
		} finally {
			if (t != null) {
				t.closeConnection();
				t = null;
			}
		}
	}

	
	
	
	/**
	 * Save financial information to database. This method handles the complex
	 * task updating the various tables with consistent financial information.
	 * Charges table is updated or inserted from a MAP of charge items.
	 * TranHistory table will have adjustment transactions added. Financial
	 * table has total charges updated. This method calls
	 * FdmsDb.addHistorySaleTran() for adding to the transaction history file.
	 * Creation date: (4/5/2002 12:06:40 PM)
	 * 
	 * @param t
	 *            DatabaseTransaction Existing transaction
	 *            possibly with other Persistents to be saved
	 * @param action
	 *            int 0=save unposted 1=first time postinging 2=adjusting
	 *            already posted case
	 * @param finan
	 *            DbFinancialSummary contains current and perhasp not
	 *            yet updated financial summary data
	 * @param charges
	 *            Map collection of current contract charges for this
	 *            case.
	 * @exception PersistenceException
	 *                Indicates database could not be updated.           
	 *                
	 */
	
	
//	public void postFinancial(DatabaseTransaction t, int action, DbFinancialSummary finan, Map charges,
//			DbVitalsFirstCall firstcall, LocaleDTO locale, String dblookup) throws PersistenceException {
//
//		FinancialInformationLineItem aFinanItem = null;
//		DbChargeItem aCharge = null;
//		DbChargeItem dbCharge = null;
//		DbCase decCase = null;
//		InventorySold soldData = null;
//		int totalCharges = 0;
//
//		if (action != FINANCIAL_SAVE_UNPOSTED && action != FINANCIAL_FIRST_POST && action != FINANCIAL_POST_ADJUST) {
//			throw (new PersistenceException("Invalid Post Action Specified."));
//		}
//
//		//AppLog.trace("postFinancial starting action: "+action);
//		// Calculate "Transaction Date"
//		SimpleDateFormat formatmdy = new SimpleDateFormat("MMddyyyy");
//		SimpleDateFormat formatyymmdd = new SimpleDateFormat("yyyyMMdd");
////		String trandateymd = formatter.format( new Date(Calendar.getInstance().getTimeInMillis() ) );
//		String trandatemdy = formatmdy.format( new Date(Calendar.getInstance().getTimeInMillis() ) );
//		
//		Date trandate = new Date(Calendar.getInstance().getTimeInMillis() );
//		
//		// get case information
//		decCase = getCase(t, finan.getId());
//		
//		// if first time posting, transaction date is the sale date
//		if (action == FINANCIAL_FIRST_POST) {
//			try {
//				trandatemdy = finan.getCFinSaleDate();
//				logger.debug( "trandatemdy: " + trandatemdy );
//				
//				if ( trandatemdy == null ) {
//					trandate = new Date ( formatyymmdd.parse( decCase.getSaleDate()).getTime() );
//				} else {
//					trandate = new Date ( formatmdy.parse(finan.getCFinSaleDate()).getTime() );
//				}
//				
////				trandateymd = FormatDate.convertUnformattedMDYtoYMD(trandatemdy);
//			} catch (Exception e) {
//				if ( locale.isConfigShowFinancing() == false ) {
//					throw new PersistenceException("Invalid Sale Date", e);
//				}
//			}
//		}
//
//		// Loop through collection of charges
//		Iterator myIterator = charges.values().iterator();
//		while (myIterator.hasNext()) {
//			aFinanItem = (FinancialInformationLineItem) myIterator.next();
//			aCharge = aFinanItem.getDbChargeItem();
//			int chargeID = aCharge.getId();
///*
//			logger.debug("Processing chargeId : " + chargeID);
//			logger.debug("Charge description : " + aCharge.getDescription());
//			logger.debug("Charge onHandId : " + aCharge.getInvOnHandID());
//			logger.debug("Charge taxAmount : " + aCharge.getTaxAmount());
//*/
//			if (aFinanItem.getItemDeletion() != 0 && chargeID <= 0) {
//				logger.debug("Post processing: deleted a charge added this session. No action taken.");
//				continue;
//			}
//
//			// Processing that must occur for each charge record unless it is
//			// deleted
//			if (aFinanItem.getItemDeletion() == 0) {
//				totalCharges += aCharge.getAmount();
//			}
//
//			// Check if charge is modified or deleted
//			// but if first posting, we want to process it.
//			if (!aFinanItem.isModifiedItem() && !aFinanItem.isNewItem() && action != FINANCIAL_FIRST_POST) {
//				//AppLog.trace("Post processing: not changed.");
//				// nothing to do if charge not changed
//				continue;
//			}
//			// Check if this charge ID is to be updated or inserted
//			// If ID is zero then this is a new charge line to insert
//
//			if (chargeID <= 0) {
//				// Prepare to add a new charge object
//				logger.debug("Post procesing: Add charge.");
//				dbCharge = new DbChargeItem();
//				dbCharge.setNew();
//				t.addPersistent(dbCharge);
//				// These columns are set for new charges and cannot be changed.
//				dbCharge.setVitalsID(finan.getId());
//				dbCharge.setPriceListId(aCharge.getPriceListId());
//				dbCharge.setInvItemName(aCharge.getInvItemName());
//				dbCharge.setInvSeqNo(aCharge.getInvSeqNo()); // inventory master
//				// ID
//				dbCharge.setInvOnHandID(aCharge.getInvOnHandID()); // inventory
//				// on-hand
//				// record ID
//				dbCharge.setTaxAmount(aCharge.getTaxAmount());
//				dbCharge.setFromPackage(aCharge.isFromPackage());
//				chargeID = dbCharge.getId();
//			} else {
//				// retrieve from DBMS
//				logger.debug("Post processing: update charge.");
//				dbCharge = FdmsDb.getInstance().getChargeItem(t, chargeID);
//				dbCharge.setTaxAmount(aCharge.getTaxAmount());
//				if (dbCharge == null) {
//					throw (new PersistenceException("Post. Missing Charge item#" + chargeID));
//				}
//			}
//			// Set default soldData object.
//			// The remainder of this object will be filled out by the
//			// getDefaultGlSalesAccts() method
////			soldData = new InventorySold();
////			soldData.setAcctRevenue(aCharge.getGlAcct());
////			getDefaultGlSalesAccts(t, soldData, decCase.getLocale(), decCase.getChapelNumber(), String.valueOf(aCharge.getItemCategoryType()), finan.getCFinSaleType(), firstcall.getDispositionCode());
//			
//			soldData = new InventorySold();
////			soldData.setAcctRevenue(aCharge.getGlAcct());
//			getDefaultGlSalesAccts(t, soldData, decCase.getLocale(), decCase.getChapelNumber(), String.valueOf(aCharge.getItemCategoryType()), finan.getCFinSaleType(), firstcall.getDispositionCode());
//			
//			if ( (aCharge.getGlAcct() == null) ) {
//				if (soldData.getAcctRevenue()== null) {
//					aCharge.setGlAcct("");
//				}else {
//					aCharge.setGlAcct(soldData.getAcctRevenue());
//				}
//			} else if ( (aCharge.getGlAcct().length() <= 1) || (aCharge.getGlAcct().compareToIgnoreCase("") == 0) ||  (aCharge.getGlAcct().compareTo("        ")== 0) ) {
//				if (soldData.getAcctRevenue()== null) {
//					aCharge.setGlAcct("");
//				}else {
//					aCharge.setGlAcct(soldData.getAcctRevenue());
//				}
//			}
//			else {
//			}
//			
//			//AppLog.trace("postFinancial soldData
//			// rev:"+soldData.getAcctRevenue()+"
//			// cogs:"+soldData.getAcctCostOfSale());
//			// Check if deleting charge
//			if (aFinanItem.getItemDeletion() != 0) {
//				logger.debug("Post processing. Deleting charge.");
//				dbCharge.remove();
//				// Adjust cost of sale transactions and add back to inventory if
//				// deleteing an inventory item
//				if (action == FINANCIAL_POST_ADJUST) {
//
//					if (dbCharge.getInvSeqNo() > 0) {
//						//AppLog.trace("Returning merchandise to Inventory for
//						// ItemID:"+dbCharge.getInvSeqNo()+"
//						// onhandID:"+dbCharge.getInvOnHandID());
//						inventoryReturn(t, dbCharge.getInvSeqNo(), dbCharge.getInvOnHandID(), 1,
//								DbInvHistory.TRAN_TYPE_ADJUSTMENT, trandatemdy, decCase.getContractCode(), "", dbCharge
//										.getAmount(), "Deleted from case and returned to inv.", finan.getId(), soldData);
//						aFinanItem.setCostOfSale(soldData.getCost());
//					}
//
//					// if deleted item, adjust history with negative original
//					// charge amount
//					if (aFinanItem.getPreviousAmount() != 0) {
//						FdmsDb.getInstance().addHistorySaleTran(t, finan.getId(), aCharge.getType(),
//								aCharge.getDescription(), -(aFinanItem.getPreviousAmount()), aCharge.getGlAcct(),
//								aCharge.getExemptAmount(), aCharge.getTaxCode(), trandate,
//								String.valueOf(aCharge.getSpfCode()), aCharge.getItemCategoryType(),
//								aCharge.getInvItemName(), aCharge.getInvSeqNo(), aCharge.getInvOnHandID(),
//								aCharge.getPriceListId(), -(aFinanItem.getCostOfSale()), action, -(aCharge.getTaxAmount()), aCharge.getSequenceNumber());
//					}
//
//					if (dbCharge.getInvSeqNo() > 0 && soldData.getStockType() != DbInvMaster.STOCK_TYPE_NONSTOCKED) {
//						// Adjust Transaction History for DELETED Inventory cost
//						// of sales
//						// add history transaction for "Cost of sale"
//						FdmsDb.getInstance().addHistorySaleTran(t, finan.getId(), dbCharge.getType(),
//								aCharge.getDescription(), (soldData.getCost()), soldData.getAcctCostOfSale(), 0, "",
//								trandate, DbHistory.HIST_SPF_COSTSALE, aCharge.getItemCategoryType(),
//								aCharge.getInvItemName(), aCharge.getInvSeqNo(), aCharge.getInvOnHandID(),
//								aCharge.getPriceListId(), dbCharge.getAmount(), action, aCharge.getTaxAmount(),  aCharge.getSequenceNumber());
//
//						// add history transaction for "Inventory Asset"
//						FdmsDb.getInstance().addHistorySaleTran(t, finan.getId(), dbCharge.getType(),
//								aCharge.getDescription(), -(soldData.getCost()), soldData.getAcctInvAsset(), 0, "",
//								trandate, DbHistory.HIST_SPF_INVASSET, aCharge.getItemCategoryType(),
//								aCharge.getInvItemName(), aCharge.getInvSeqNo(), aCharge.getInvOnHandID(),
//								aCharge.getPriceListId(), dbCharge.getAmount(), action, -(aCharge.getTaxAmount()), aCharge.getSequenceNumber());
//					}
//				}
//				// terminate the loop for this charge being deleted
//				continue;
//			}
//			// Proceed with updating or adding charges
//			//AppLog.trace("postFinancial updating dbCharge:
//			// "+aCharge.getDescription()+" GL:"+aCharge.getGlAcct()+"
//			// len:"+aCharge.getGlAcct().length());
//			dbCharge.setAmount(aCharge.getAmount());
//			dbCharge.setDescription(aCharge.getDescription());
//			dbCharge.setExemptAmount(aCharge.getExemptAmount());
//			dbCharge.setGlAcct(aCharge.getGlAcct());
//			dbCharge.setItemCategoryType(aCharge.getItemCategoryType());
//			dbCharge.setOrigPrice(aCharge.getOrigPrice());
//			dbCharge.setSequenceNumber(aCharge.getSequenceNumber());
//			dbCharge.setSpfCode(aCharge.getSpfCode());
//			dbCharge.setTaxCode(aCharge.getTaxCode());
//			dbCharge.setType(aCharge.getType());
//
//			// can change this only if case is not already posted (or adding a
//			// new item).
//			if (action == FINANCIAL_FIRST_POST) {
//				dbCharge.setInvOnHandID(aCharge.getInvOnHandID()); // inventory
//				// on-hand
//				// record ID
//			}
//
//			// Determine "quantity" sold. Qty is 1 if first time selling this
//			// item
//			// or Qty is zero if updating a posted item.
//			int qtySold = 0;
//			if (action == FINANCIAL_FIRST_POST || (action == FINANCIAL_POST_ADJUST && aFinanItem.isNewItem())) {
//				qtySold = 1;
//
//			}
//			// Set Inventory info into soldData object.
//			soldData.setStockType(DbInvMaster.STOCK_TYPE_NONSTOCKED);
//			soldData.setOnhandID(dbCharge.getInvOnHandID());
//
//			// Remove from Inventory and get cost of sale
//			// if first time posting or if already posted and processing an
//			// added merchandise item.
//			int merchcost = 0;
//			if (dbCharge.getInvSeqNo() > 0
//					&& (action == FINANCIAL_FIRST_POST || (action == FINANCIAL_POST_ADJUST && aFinanItem.isNewItem()))) {
//				logger.debug("postFinancial Selling inventory item : " + dbCharge.getInvSeqNo());
//				inventorySell(t, dbCharge.getInvSeqNo(), dbCharge.getInvOnHandID(), qtySold,
//						DbInvHistory.TRAN_TYPE_FDMS_SALE, trandatemdy, decCase.getContractCode(), "", dbCharge
//								.getAmount(), decCase.getFirstName() + " " + decCase.getLastName(), finan.getId(),
//						soldData);
//				merchcost = soldData.getCost();
//				if (soldData.getStockType().equals(DbInvMaster.STOCK_TYPE_STOCKED)) {
//					// just one on-hand record for stocked types and it is
//					// returned from inventorySell method
//					dbCharge.setInvOnHandID(soldData.getOnhandID());
//				}
//			}
//			// Adjust charge object for default revenue and inventory cost
//			// accounts
//			// only if posting. Don't update GL account if saving without
//			// posting
//			if (action != FINANCIAL_SAVE_UNPOSTED) {
//				//chai
////				dbCharge.setGlAcct(soldData.getAcctRevenue());
//				dbCharge.setGlAcct(aCharge.getGlAcct());
//				
//			}
//			aFinanItem.setCostOfSale(merchcost);
//
//			// Adjust Transaction History for charges - Output_History(hWnd);
//			int amount = 0;
//			if (action == FINANCIAL_FIRST_POST || action == FINANCIAL_POST_ADJUST) {
//				amount = aCharge.getAmount() - aFinanItem.getPreviousAmount();
//				// if first time post or item added this session, then ignore
//				// previous amount
//				if (action == FINANCIAL_FIRST_POST || aFinanItem.isNewItem()) {
//					amount = aCharge.getAmount();
//				}
//				// TODO: feature of override description and amount
//
//				// TODO: need page to adjust component sale amounts
//
//				logger.debug("postFinancial Add Tran Hist sale transaction. for : " + aCharge.getDescription()
//						+ " / amount=" + amount + " gl=" + aCharge.getGlAcct());
//
//				if (amount != 0) {
//					FdmsDb.getInstance().addHistorySaleTran(t, finan.getId(), dbCharge.getType(),
//							dbCharge.getDescription(), amount, dbCharge.getGlAcct(), dbCharge.getExemptAmount(),
//							dbCharge.getTaxCode(), trandate, String.valueOf(dbCharge.getSpfCode()),
//							dbCharge.getItemCategoryType(), dbCharge.getInvItemName(), dbCharge.getInvSeqNo(),
//							dbCharge.getInvOnHandID(), aCharge.getPriceListId(), aFinanItem.getCostOfSale(), action, 
//							dbCharge.getTaxAmount(), dbCharge.getSequenceNumber());
//				}
//				// TODO: Adjust Transaction History for Payment Components
//
//				// Adjust Transaction History for Inventory cost of sales if
//				// serial# or stocked inventory item
//				// if first time posting the contract and it is an inventory
//				// item
//				// or if adjusting a posted contract and processing a new
//				// inventory item added this session.
//				if ((action == FINANCIAL_FIRST_POST && dbCharge.getInvSeqNo() > 0 && soldData.getStockType().compareTo(
//						DbInvMaster.STOCK_TYPE_NONSTOCKED) != 0)
//						|| (action == FINANCIAL_POST_ADJUST && dbCharge.getInvSeqNo() > 0
//								&& soldData.getStockType().compareTo(DbInvMaster.STOCK_TYPE_NONSTOCKED) != 0 && aFinanItem
//								.isNewItem())) {
//					//AppLog.trace("postFinancial Add history trans for cost of
//					// sale.
//					// "+soldData.getStockType()+"::"+DbInvMaster.STOCK_TYPE_NONSTOCKED);
//					// add history transaction for "Cost of sale"
//					FdmsDb.getInstance().addHistorySaleTran(t, finan.getId(), dbCharge.getType(),
//							dbCharge.getDescription(), -(soldData.getCost()), soldData.getAcctCostOfSale(), 0, "",
//							trandate, DbHistory.HIST_SPF_COSTSALE, dbCharge.getItemCategoryType(),
//							dbCharge.getInvItemName(), dbCharge.getInvSeqNo(), dbCharge.getInvOnHandID(), 
//							aCharge.getPriceListId(), amount, action, dbCharge.getTaxAmount(), dbCharge.getSequenceNumber());
//					// add history transaction for "Inventory Asset"
//					FdmsDb.getInstance().addHistorySaleTran(t, finan.getId(), dbCharge.getType(),
//							dbCharge.getDescription(), soldData.getCost(), soldData.getAcctInvAsset(), 0, "",
//							trandate, DbHistory.HIST_SPF_INVASSET, dbCharge.getItemCategoryType(),
//							dbCharge.getInvItemName(), dbCharge.getInvSeqNo(), dbCharge.getInvOnHandID(), 
//							aCharge.getPriceListId(), amount, action, -(dbCharge.getTaxAmount()), dbCharge.getSequenceNumber() );
//				}
//			}
//		} // ----- End of loop for each charge in collection -----
//		// Update total charges in financial object
//		finan.setLFinTotalCharges(totalCharges);
//		// Adjust default component for this case so sum matches total charges
//		DbComponentSet compset = new DbComponentSet(t, finan.getId());
//		compset.adjustSalesSumTo(t, totalCharges, dblookup);
//
//		// Now store changes to all tables through the same transaction
//		t.save();
//		logger.debug("postFinancial: finished save");
//	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public void postFinancial( DatabaseTransaction t, int action, DbFinancialSummary finan, Map charges,
			DbVitalsFirstCall firstcall, LocaleDTO locale, String dblookup) throws PersistenceException {

		FinancialInformationLineItem aFinanItem = null;
		DbChargeItem aCharge = null;
		DbChargeItem dbCharge = null;
		DbCase decCase = null;
		InventorySold soldData = null;
		int totalCharges = 0;
		int vitalsID = finan.getId();
		boolean rm9998 = false; //remove previouse finance
		boolean rm9999 = false; //remove last finance charge.

		if (action != FINANCIAL_SAVE_UNPOSTED && action != FINANCIAL_FIRST_POST && action != FINANCIAL_POST_ADJUST) {
			throw (new PersistenceException("Invalid Post Action Specified."));
		}

		//AppLog.trace("postFinancial starting action: "+action);
		// Calculate "Transaction Date"
		SimpleDateFormat formatmdy = new SimpleDateFormat("MMddyyyy");
		SimpleDateFormat formatyymmdd = new SimpleDateFormat("yyyyMMdd");
//		String trandateymd = formatter.format( new Date(Calendar.getInstance().getTimeInMillis() ) );
		String trandatemdy = formatmdy.format( new Date(Calendar.getInstance().getTimeInMillis() ) );
		
		Date trandate = new Date(Calendar.getInstance().getTimeInMillis() );
		
		// get case information
		decCase = getCase(t, finan.getId());
		
		// if first time posting, transaction date is the sale date
		if (action == FINANCIAL_FIRST_POST) {
			try {
				trandatemdy = finan.getCFinSaleDate();
				logger.debug( "trandatemdy: " + trandatemdy );
				
				if ( trandatemdy == null ) {
					trandate = new Date ( formatyymmdd.parse( decCase.getSaleDate()).getTime() );
				} else {
					trandate = new Date ( formatmdy.parse(finan.getCFinSaleDate()).getTime() );
				}
				
//				trandateymd = FormatDate.convertUnformattedMDYtoYMD(trandatemdy);
			} catch (Exception e) {
				if ( locale.isConfigShowFinancing() == false ) {
					throw new PersistenceException("Invalid Sale Date", e);
				}
			}
		}

		int stateTax = 0;
		int localTax = 0;
		int articleTax = 0;
		boolean hasStateTax = false;
		boolean hasLocalTax = false;
		boolean hasArticleTax = false;
		
//		//loop getting charge ID of tax, we will set the charge table having only one of each kind of taxes
//		Iterator myIteratorForTax = charges.values().iterator();
//		while (myIteratorForTax.hasNext()) {
//			aFinanItem = (FinancialInformationLineItem) myIteratorForTax.next();
//			aCharge = aFinanItem.getDbChargeItem();
//			int chargeID = aCharge.getId();
//			if (chargeID > 0 && aCharge.getType() == 99) {
//				hasStateTax = true;
//				stateTax = aCharge.getAmount();
//			}
//			if (chargeID > 0 && aCharge.getType() == 98) {
//				hasLocalTax = true;
//				localTax = aCharge.getAmount();
//			}
//			if (chargeID > 0 && aCharge.getType() == 97) {
//				hasArticleTax = true;
//				articleTax = aCharge.getAmount();
//			}
//		}
		

		// Loop through collection of charges
		Iterator myIterator = charges.values().iterator();
		while (myIterator.hasNext()) {
			aFinanItem = (FinancialInformationLineItem) myIterator.next();
			aCharge = aFinanItem.getDbChargeItem();
			int chargeID = aCharge.getId();
/*
			logger.debug("Processing chargeId : " + chargeID);
			logger.debug("Charge description : " + aCharge.getDescription());
			logger.debug("Charge onHandId : " + aCharge.getInvOnHandID());
			logger.debug("Charge taxAmount : " + aCharge.getTaxAmount());
*/
			if (aFinanItem.getItemDeletion() != 0 && chargeID <= 0) {
				logger.debug("Post processing: deleted a charge added this session. No action taken.");
				continue;
			}

			// Processing that must occur for each charge record unless it is
			// deleted
			if (aFinanItem.getItemDeletion() == 0) {
				totalCharges += aCharge.getAmount();
			}

			// Check if charge is modified or deleted
			// but if first posting, we want to process it.
			if (!aFinanItem.isModifiedItem() && !aFinanItem.isNewItem() && action != FINANCIAL_FIRST_POST) {
				//AppLog.trace("Post processing: not changed.");
				// nothing to do if charge not changed
				continue;
			}
			// Check if this charge ID is to be updated or inserted
			// If ID is zero then this is a new charge line to insert

			if (chargeID <= 0) {
				// Prepare to add a new charge object
				logger.debug("Post procesing: Add charge.");
				dbCharge = new DbChargeItem();
				dbCharge.setNew();
				t.addPersistent(dbCharge);
//				if (!hasStateTax && aCharge.getType() == 99) {
//					t.addPersistent(dbCharge);
//					hasStateTax = true;
//				}
//				else if (!hasLocalTax && aCharge.getType() == 98) {
//					t.addPersistent(dbCharge);
//					hasLocalTax = true;
//				}
//				else if (!hasArticleTax && aCharge.getType() == 97) {
//					t.addPersistent(dbCharge);
//					hasArticleTax = true;
//				}
//				else if (aCharge.getType() != 97 && aCharge.getType() != 98 && aCharge.getType() != 99) {
//					t.addPersistent(dbCharge);
//				}
				// These columns are set for new charges and cannot be changed.
				dbCharge.setVitalsID(finan.getId());
				dbCharge.setPriceListId(aCharge.getPriceListId());
				dbCharge.setInvItemName(aCharge.getInvItemName());
				dbCharge.setInvSeqNo(aCharge.getInvSeqNo()); // inventory master
				// ID
				dbCharge.setInvOnHandID(aCharge.getInvOnHandID()); // inventory
				// on-hand
				// record ID
				dbCharge.setTaxAmount(aCharge.getTaxAmount());
				dbCharge.setFromPackage(aCharge.isFromPackage());
				dbCharge.setPackageId(aCharge.getPackageId());
				chargeID = dbCharge.getId();
				
//				// collect the amount of tax
//				if (aCharge.getType() == 99) {
//					stateTax = stateTax + aCharge.getAmount();
//					if (hasStateTax){
//						dbCharge.remove();
//					}
//				}
//				if (aCharge.getType() == 98) {
//					localTax = localTax + aCharge.getAmount();
//					if (hasLocalTax){
//						dbCharge.remove();
//					}
//				}
//				if (aCharge.getType() == 97) {
//					articleTax = articleTax + aCharge.getAmount();
//					if(hasArticleTax){
//						dbCharge.remove();
//					}
//				}
				
				
			} else {
				// retrieve from DBMS
				logger.debug("Post processing: update charge.");
				dbCharge = FdmsDb.getInstance().getChargeItem(t, chargeID);
				if (aCharge == null ) {
					dbCharge.setTaxAmount(0);
				}else {
					dbCharge.setTaxAmount(aCharge.getTaxAmount());
				}
				if (dbCharge == null) {
					throw (new PersistenceException("Post. Missing Charge item#" + chargeID));
				}
			}
			// Set default soldData object.
			// The remainder of this object will be filled out by the
			// getDefaultGlSalesAccts() method
//			soldData = new InventorySold();
//			soldData.setAcctRevenue(aCharge.getGlAcct());
//			getDefaultGlSalesAccts(t, soldData, decCase.getLocale(), decCase.getChapelNumber(), String.valueOf(aCharge.getItemCategoryType()), finan.getCFinSaleType(), firstcall.getDispositionCode());
			
			soldData = new InventorySold();
//			soldData.setAcctRevenue(aCharge.getGlAcct());
			getDefaultGlSalesAccts(t, soldData, decCase.getLocale(), decCase.getChapelNumber(), String.valueOf(aCharge.getItemCategoryType()), finan.getCFinSaleType(), firstcall.getDispositionCode());
			
			if ( (aCharge.getGlAcct() == null) ) {
				if (soldData.getAcctRevenue()== null) {
					aCharge.setGlAcct("");
				}else {
					aCharge.setGlAcct(soldData.getAcctRevenue());
				}
			} else if ( (aCharge.getGlAcct().length() <= 1) || (aCharge.getGlAcct().compareToIgnoreCase("") == 0) ||  (aCharge.getGlAcct().compareTo("        ")== 0) ) {
				if (soldData.getAcctRevenue()== null) {
					aCharge.setGlAcct("");
				}else {
					aCharge.setGlAcct(soldData.getAcctRevenue());
				}
			}
			else {
			}
			
			//AppLog.trace("postFinancial soldData
			// rev:"+soldData.getAcctRevenue()+"
			// cogs:"+soldData.getAcctCostOfSale());
			// Check if deleting charge
			if (aFinanItem.getItemDeletion() != 0) {
				logger.debug("Post processing. Deleting charge.");
				dbCharge.remove();
				
				if (dbCharge.getType() == 9998) {
					rm9998 = true;
				}
				if (dbCharge.getType() == 9999) {
					rm9999 = true;
				}
				
				// Adjust cost of sale transactions and add back to inventory if
				// deleteing an inventory item
				if (action == FINANCIAL_POST_ADJUST) {

					if (dbCharge.getInvSeqNo() > 0) {
						//AppLog.trace("Returning merchandise to Inventory for
						// ItemID:"+dbCharge.getInvSeqNo()+"
						// onhandID:"+dbCharge.getInvOnHandID());
						inventoryReturn(t, dbCharge.getInvSeqNo(), dbCharge.getInvOnHandID(), 1,
								DbInvHistory.TRAN_TYPE_ADJUSTMENT, trandatemdy, decCase.getContractCode(), "", dbCharge
										.getAmount(), "Deleted from case and returned to inv.", finan.getId(), soldData);
						aFinanItem.setCostOfSale(soldData.getCost());
					}

					// if deleted item, adjust history with negative original
					// charge amount
					if (aFinanItem.getPreviousAmount() != 0) {
						FdmsDb.getInstance().addHistorySaleTran(t, finan.getId(), aCharge.getType(),
								aCharge.getDescription(), -(aFinanItem.getPreviousAmount()), aCharge.getGlAcct(),
								aCharge.getExemptAmount(), aCharge.getTaxCode(), trandate,
								String.valueOf(aCharge.getSpfCode()), aCharge.getItemCategoryType(),
								aCharge.getInvItemName(), aCharge.getInvSeqNo(), aCharge.getInvOnHandID(),
								aCharge.getPriceListId(), -(aFinanItem.getCostOfSale()), action, -(aCharge.getTaxAmount()), aCharge.getSequenceNumber());
					}

					if (dbCharge.getInvSeqNo() > 0 && soldData.getStockType() != DbInvMaster.STOCK_TYPE_NONSTOCKED) {
						// Adjust Transaction History for DELETED Inventory cost
						// of sales
						// add history transaction for "Cost of sale"
						FdmsDb.getInstance().addHistorySaleTran(t, finan.getId(), dbCharge.getType(),
								aCharge.getDescription(), (soldData.getCost()), soldData.getAcctCostOfSale(), 0, "",
								trandate, DbHistory.HIST_SPF_COSTSALE, aCharge.getItemCategoryType(),
								aCharge.getInvItemName(), aCharge.getInvSeqNo(), aCharge.getInvOnHandID(),
								aCharge.getPriceListId(), dbCharge.getAmount(), action, aCharge.getTaxAmount(),  aCharge.getSequenceNumber());

						// add history transaction for "Inventory Asset"
						FdmsDb.getInstance().addHistorySaleTran(t, finan.getId(), dbCharge.getType(),
								aCharge.getDescription(), -(soldData.getCost()), soldData.getAcctInvAsset(), 0, "",
								trandate, DbHistory.HIST_SPF_INVASSET, aCharge.getItemCategoryType(),
								aCharge.getInvItemName(), aCharge.getInvSeqNo(), aCharge.getInvOnHandID(),
								aCharge.getPriceListId(), dbCharge.getAmount(), action, -(aCharge.getTaxAmount()), aCharge.getSequenceNumber());
					}
				}
				// terminate the loop for this charge being deleted
				continue;
			}
			// Proceed with updating or adding charges
			//AppLog.trace("postFinancial updating dbCharge:
			// "+aCharge.getDescription()+" GL:"+aCharge.getGlAcct()+"
			// len:"+aCharge.getGlAcct().length());
			dbCharge.setAmount(aCharge.getAmount());
			dbCharge.setDescription(aCharge.getDescription());
			dbCharge.setExemptAmount(aCharge.getExemptAmount());
			dbCharge.setGlAcct(aCharge.getGlAcct());
			dbCharge.setItemCategoryType(aCharge.getItemCategoryType());
			dbCharge.setOrigPrice(aCharge.getOrigPrice());
			dbCharge.setSequenceNumber(aCharge.getSequenceNumber());
			dbCharge.setSpfCode(aCharge.getSpfCode());
			dbCharge.setTaxCode(aCharge.getTaxCode());
			dbCharge.setType(aCharge.getType());

			// can change this only if case is not already posted (or adding a
			// new item).
		//	if (action == FINANCIAL_FIRST_POST ) {
				dbCharge.setInvOnHandID(aCharge.getInvOnHandID()); // inventory
				// on-hand
				// record ID
		//	}

			// Determine "quantity" sold. Qty is 1 if first time selling this
			// item
			// or Qty is zero if updating a posted item.
			int qtySold = 0;
			if (action == FINANCIAL_FIRST_POST || (action == FINANCIAL_POST_ADJUST && aFinanItem.isNewItem())) {
				qtySold = 1;

			}
			// Set Inventory info into soldData object.
			soldData.setStockType(DbInvMaster.STOCK_TYPE_NONSTOCKED);
			soldData.setOnhandID(dbCharge.getInvOnHandID());

			// Remove from Inventory and get cost of sale
			// if first time posting or if already posted and processing an
			// added merchandise item.
			int merchcost = 0;
			if (dbCharge.getInvSeqNo() > 0
					&& (action == FINANCIAL_FIRST_POST || (action == FINANCIAL_POST_ADJUST && aFinanItem.isNewItem()))) {
				logger.debug("postFinancial Selling inventory item : " + dbCharge.getInvSeqNo());
				inventorySell(t, dbCharge.getInvSeqNo(), dbCharge.getInvOnHandID(), qtySold,
						DbInvHistory.TRAN_TYPE_FDMS_SALE, trandatemdy, decCase.getContractCode(), "", dbCharge
								.getAmount(), decCase.getFirstName() + " " + decCase.getLastName(), finan.getId(),
						soldData);
				merchcost = soldData.getCost();
				if (soldData.getStockType().equals(DbInvMaster.STOCK_TYPE_STOCKED)) {
					// just one on-hand record for stocked types and it is
					// returned from inventorySell method
					dbCharge.setInvOnHandID(soldData.getOnhandID());
				}
			}
			// Adjust charge object for default revenue and inventory cost
			// accounts
			// only if posting. Don't update GL account if saving without
			// posting
			if (action != FINANCIAL_SAVE_UNPOSTED) {
				//chai
//				dbCharge.setGlAcct(soldData.getAcctRevenue());
				dbCharge.setGlAcct(aCharge.getGlAcct());
				
			}
			aFinanItem.setCostOfSale(merchcost);

			// Adjust Transaction History for charges - Output_History(hWnd);
			int amount = 0;
			if (action == FINANCIAL_FIRST_POST || action == FINANCIAL_POST_ADJUST) {
				amount = aCharge.getAmount() - aFinanItem.getPreviousAmount();
				// if first time post or item added this session, then ignore
				// previous amount
				if (action == FINANCIAL_FIRST_POST || aFinanItem.isNewItem()) {
					amount = aCharge.getAmount();
				}
				// TODO: feature of override description and amount

				// TODO: need page to adjust component sale amounts

				logger.debug("postFinancial Add Tran Hist sale transaction. for : " + aCharge.getDescription()
						+ " / amount=" + amount + " gl=" + aCharge.getGlAcct());

				if (amount != 0) {
					FdmsDb.getInstance().addHistorySaleTran(t, finan.getId(), dbCharge.getType(),
							dbCharge.getDescription(), amount, dbCharge.getGlAcct(), dbCharge.getExemptAmount(),
							dbCharge.getTaxCode(), trandate, String.valueOf(dbCharge.getSpfCode()),
							dbCharge.getItemCategoryType(), dbCharge.getInvItemName(), dbCharge.getInvSeqNo(),
							dbCharge.getInvOnHandID(), aCharge.getPriceListId(), aFinanItem.getCostOfSale(), action, 
							dbCharge.getTaxAmount(), dbCharge.getSequenceNumber());
				}
				// TODO: Adjust Transaction History for Payment Components

				// Adjust Transaction History for Inventory cost of sales if
				// serial# or stocked inventory item
				// if first time posting the contract and it is an inventory
				// item
				// or if adjusting a posted contract and processing a new
				// inventory item added this session.
				if ((action == FINANCIAL_FIRST_POST && dbCharge.getInvSeqNo() > 0 && soldData.getStockType().compareTo(
						DbInvMaster.STOCK_TYPE_NONSTOCKED) != 0)
						|| (action == FINANCIAL_POST_ADJUST && dbCharge.getInvSeqNo() > 0
								&& soldData.getStockType().compareTo(DbInvMaster.STOCK_TYPE_NONSTOCKED) != 0 && aFinanItem
								.isNewItem())) {
					//AppLog.trace("postFinancial Add history trans for cost of
					// sale.
					// "+soldData.getStockType()+"::"+DbInvMaster.STOCK_TYPE_NONSTOCKED);
					// add history transaction for "Cost of sale"
					FdmsDb.getInstance().addHistorySaleTran(t, finan.getId(), dbCharge.getType(),
							dbCharge.getDescription(), -(soldData.getCost()), soldData.getAcctCostOfSale(), 0, "",
							trandate, DbHistory.HIST_SPF_COSTSALE, dbCharge.getItemCategoryType(),
							dbCharge.getInvItemName(), dbCharge.getInvSeqNo(), dbCharge.getInvOnHandID(), 
							aCharge.getPriceListId(), amount, action, dbCharge.getTaxAmount(), dbCharge.getSequenceNumber());
					// add history transaction for "Inventory Asset"
					FdmsDb.getInstance().addHistorySaleTran(t, finan.getId(), dbCharge.getType(),
							dbCharge.getDescription(), soldData.getCost(), soldData.getAcctInvAsset(), 0, "",
							trandate, DbHistory.HIST_SPF_INVASSET, dbCharge.getItemCategoryType(),
							dbCharge.getInvItemName(), dbCharge.getInvSeqNo(), dbCharge.getInvOnHandID(), 
							aCharge.getPriceListId(), amount, action, -(dbCharge.getTaxAmount()), dbCharge.getSequenceNumber() );
				}
			}
		} // ----- End of loop for each charge in collection -----
		// Update total charges in financial object
		finan.setLFinTotalCharges(totalCharges);
		// Adjust default component for this case so sum matches total charges
		DbComponentSet compset = new DbComponentSet(t, finan.getId());
		compset.adjustSalesSumTo(t, totalCharges, dblookup);

//		//loop getting charge ID of tax, we will set the charge table having only one of each kind of taxes
//		int newStateTax = 0;
//		int newLocalTax = 0;
//		int newArticleTax = 0;
//		if (hasStateTax || hasLocalTax || hasArticleTax) {
//			Iterator myIteratorForTax1 = charges.values().iterator();
//			while (myIteratorForTax1.hasNext()) {
//				aFinanItem = (FinancialInformationLineItem) myIteratorForTax1.next();
//				aCharge = aFinanItem.getDbChargeItem();
//				int chargeID = aCharge.getId();
//				if (hasStateTax && aCharge.getType() == 99 && aFinanItem.isNewItem()) {
//					newStateTax = newStateTax + aCharge.getAmount();			
//				}
//				if (hasLocalTax && aCharge.getType() == 98 && aFinanItem.isNewItem()) {
//					newLocalTax = newLocalTax + aCharge.getAmount();			
//				}
//				if (hasArticleTax && aCharge.getType() == 99 && aFinanItem.isNewItem()) {
//					newArticleTax = newArticleTax + aCharge.getAmount();			
//				}
//			}
//		}
//		
//		//loop getting charge ID of tax, we will set the charge table having only one of each kind of taxes
//		if (hasStateTax || hasLocalTax || hasArticleTax) {
//			Iterator myIteratorForTax2 = charges.values().iterator();
//			while (myIteratorForTax2.hasNext()) {
//				aFinanItem = (FinancialInformationLineItem) myIteratorForTax2.next();
//				aCharge = aFinanItem.getDbChargeItem();
//				int chargeID = aCharge.getId();
//				if (hasStateTax && aCharge.getType() == 99 && !aFinanItem.isNewItem()) {
//					dbCharge = FdmsDb.getInstance().getChargeItem(t, chargeID);
//					dbCharge.setAmount(newStateTax + aCharge.getAmount());
//				}
//				if (hasLocalTax && aCharge.getType() == 98 && !aFinanItem.isNewItem()) {
//					dbCharge = FdmsDb.getInstance().getChargeItem(t, chargeID);
//					dbCharge.setAmount(newStateTax + aCharge.getAmount());
//				}
//				if (hasArticleTax && aCharge.getType() == 97 && !aFinanItem.isNewItem()) {
//					dbCharge = FdmsDb.getInstance().getChargeItem(t, chargeID);
//					dbCharge.setAmount(newStateTax + aCharge.getAmount());
//				}
//			}
//		}		
		
		
		// Now store changes to all tables through the same transaction
		
		//set the financialdata back if we have removed the financial charge out.
		if (rm9999) {
			finan.setFClastRunDate(finan.getFinChargePreviousCalc());
			finan.setLLastFinanCharge(finan.getPreviousFinanceCharge());
			finan.setFinChargePreviousCalc("");
			finan.setPreviousFinanceCharge(0);
		}
		if (rm9998) {
			finan.setFClastRunDate("");
			finan.setLLastFinanCharge(0);
			finan.setFinChargePreviousCalc("");
			finan.setPreviousFinanceCharge(0);
		}
		
		
		t.save();
		
//		if (action == FINANCIAL_POST_ADJUST) {
//			// Now we are going to do the adjust in the financial data.
//			DbFinancialSummary aFinan = FdmsDb.getInstance().getFinancial(t, vitalsID);
//			//t.addPersistent(aFinan);
//			if (aFinan.getIFinServiceChargesBox() == 1 && (aFinan.getLFinTotalCharges() != aFinan.getLTotalPaidToDate())) { 
//				TransactionhistoryDAO transDao = new TransactionhistoryDAO(companyID, userID);
//				ArrayList <TransactionhistoryDTO> currentTranses = new ArrayList <TransactionhistoryDTO>();
//				currentTranses = transDao.getTransactionhistoryChargeType(vitalsID,DbChargeItem.CURRENT_FINANCE_CHARGE_ID);
//				ArrayList <TransactionhistoryDTO> previousTranses = new ArrayList <TransactionhistoryDTO>();
//				previousTranses = transDao.getTransactionhistoryChargeType(vitalsID,DbChargeItem.PREVIOUS_FINANCE_CHARGE_ID);
//				int sumCurrent = 0;
//				int sumPrevious = 0;
//				for(TransactionhistoryDTO tran: currentTranses) {
//					sumCurrent = sumCurrent + tran.getAmountOfTran();
//				}
//				for(TransactionhistoryDTO tran: previousTranses) {
//					sumPrevious = sumPrevious + tran.getAmountOfTran();
//				}
//			
//				if (currentTranses.size() > 0 || previousTranses.size() > 0) {
//					if (currentTranses.size() > 1) {
//						if (sumCurrent == 0 && sumPrevious == 0) {
//							//set the last financail charge date to due date.
//							DbFinancialSummary saveFinan = FdmsDb.getInstance().getFinancial(t, vitalsID);
//							saveFinan.setFClastRunDate("");
//							saveFinan.setLLastFinanCharge(0);
//							t.addPersistent(saveFinan);
//						}
//						if (sumCurrent == 0 && sumPrevious > 0) {
//							int lastDelete = 0;
//							for(TransactionhistoryDTO tran: previousTranses) {
//								if (tran.getAmountOfTran() < 0) {
//									lastDelete = tran.getAmountOfTran();
//									continue;
//								}
//								if (tran.getAmountOfTran() > 0 && (tran.getAmountOfTran() == (lastDelete * (-1)))){
//									continue;
//								}
//								if (tran.getAmountOfTran() > 0) {
//									DbFinancialSummary saveFinan = FdmsDb.getInstance().getFinancial(t, vitalsID);
//									saveFinan.setFClastRunDate(FormatDate.convertDateToYYYYMMDD((java.util.Date) tran.getDateOfTran()));
//									saveFinan.setLLastFinanCharge(tran.getAmountOfTran());
//									t.addPersistent(saveFinan);
//									break;
//								}
//							}
//						}
//					}
//				}
//			}
//			t.save();
//		}
		logger.debug("postFinancial: finished save");
	}


	/**
	 * Roll back of payment should only take place for unposted cases.
	 * 
	 * @param t Transaction Object
	 * @param user User Object
	 * @param caseID ID of current case
	 * @param transHistId Transaction ID to be rolled back
	 * @param pmtcompID Component Id to be rolled back
	 * @return transaction History ID
	 * @return postAction Action status of post
	 * @throws PersistenceException
	 */
	public int rollbackPayment(DatabaseTransaction t, DbUserSession user, int caseID, 
			int transHistId, int pmtcompID, int postAction, boolean commit ) 
	throws PersistenceException {

		logger.debug("rollbackPayment starting for transHistId: " + transHistId);
		
		if ( postAction == FdmsDb.FINANCIAL_POST_ADJUST ) {
			throw new PersistenceException("rollbackPayment: Cannot rollback payment once posted:" + caseID);
		}
		
		DbHistory dbHistory = FdmsDb.getInstance().getHistory(t, transHistId);
        
		// historical amount to be rolled back.  
		// history elements saved negative element
		int amount = dbHistory.getLHistAmount();
		
		// Add payment transaction to history
		DbCase caseinfo = FdmsDb.getInstance().getCase(t, caseID);
		if (caseinfo == null) {
			throw new PersistenceException("rollbackPayment: Invalid vitals-ID:" + caseID);
		}

		DbLocation chapel = FdmsDb.getInstance().getLocation(t, caseinfo.getChapelNumber());

		if (chapel == null) {
			throw new PersistenceException("rollbackPayment: Invalid chapel location specified for case:" + caseID);
		}
		
		// remove these persistents from the transaction since they are not
		// being saved
		t.removePersistent(chapel);
		t.removePersistent(caseinfo);

		// Adjust component for this case
		DbComponent comp = FdmsDb.getInstance().getComponent(t, pmtcompID);
		if (comp == null) {
			logger.debug("rollbackPayment - invalid component ID passed in.");
			throw new PersistenceException("Invalid component ID specified");
		}

		logger.debug("Setting paid amount");
		comp.setPaidAmt(comp.getPaidAmt() + amount);
		
		// Update total paid in financial object
		DbFinancialSummary finan = FdmsDb.getInstance().getFinancial(t, caseID);
		if (finan == null) {
			logger.debug("rollbackPayment - invalid vitals case ID passed in");
			throw new PersistenceException("Invalid case ID specified");
		}

		finan.setLTotalPaidToDate(finan.getLTotalPaidToDate() + amount);
		finan.setLLastPmtAmount(-amount);
		finan.setCLastPmtResp(comp.getDescription());
		
		dbHistory.remove();		
		
		if ( commit ) {
			// Now store changes to all tables through the same transaction
			t.save();
		}
		
		logger.debug("rollbackPayment: finished saved id:" + transHistId );
		return ( transHistId );
			
	}
	
	
	/**
	 * Apply a payment to a case. This method handles the complex task updating
	 * the various tables with consistent financial information when a payment
	 * on account is posted. TranHistory table will have a payment transaction
	 * added. Financial table has total payments updated. Components payment
	 * amount will be updated Creation date: (6/24/2002 12:06:40 PM)
	 * 
	 * @param t
	 *            DatabaseTransaction Existing transaction
	 *            possibly with other Persistents to be saved
	 * @param user
	 *            DbSessionUser - user object identifying who the operator is
	 * @param caseID
	 *            int - Vitals master key for case
	 * @param payee
	 *            String - Description/name of person paying
	 * @param amount
	 *            int - Amount of payment
	 * @param glacct
	 *            String - General Ledger cash (debit) account
	 * @param depdate
	 *            String- Deposit Date
	 * @param datepaid
	 *            String - Date payment made by payee
	 * @param cashtype
	 *            int - 0=cash, 1-9 adjustment
	 * @param manualrec
	 *            String - operator assigned receipt number
	 * @param paymethod
	 *            String - Payment method
	 * @param pmtcompID
	 *            int - Identifier for component record to be updated
	 * @return int - ID of added payment history transaction
	 * @exception PersistenceException
	 *                Indicates database could not be updated.
	 */
	public int postPayment(DatabaseTransaction t, DbUserSession user, int caseID, String payee, int amount,
			String glacct, Date depdate, Date datepaid, String manualrec, String paymethod, int pmtcompID, 
			short chargeType, boolean reverseAmount) throws PersistenceException {

		int reverse = 1;
		
		// In some cases we want to reverse the amount.  If that is the case set the value to -1
		// so it will be subtracted.
		if (reverseAmount) {
			reverse = -1;
		}

		logger.debug("postPayment starting for case: " + caseID);

		// Change date paid to format needed by DbHistory
		String datepaidMDY = null;
		try {
			datepaidMDY = FormatDate.convertDateToMMDDYYYY(datepaid);
		} catch (Exception e) {
			throw new PersistenceException("Invalid date of payment", e);
		}
		logger.debug("Date paid : " + datepaid);

		// Adjust component for this case
		DbComponent comp = FdmsDb.getInstance().getComponent(t, pmtcompID);
		if (comp == null) {
			logger.debug("postPayment - invalid component ID passed in.");
			throw new PersistenceException("Invalid component ID specified");
		}

		logger.debug("Setting paid amount");
		comp.setPaidAmt(comp.getPaidAmt() + (amount * reverse));
		// Calculate if this is a cash payment or adjustment.
		// Cash GL account is determined from the first entry from the paytype
		// category.
		int cashtype = 0;
		DbSpeedData[] typelist = null;
		typelist = FdmsDb.getInstance().getSpeedData( user.getDbLookup(), user.getRegion(), "PAYTYPES");

		// check first entry for cash account
		String cashacct = CsvTable.getField(typelist[0].getData(), 2);
		if (cashacct.compareTo(glacct) != 0) {
			cashtype = 1; // does not match first entry
		}

		logger.debug("postPayment. cash acct:" + cashacct + "  GL acct:" + glacct + " cash type:" + cashtype);

		// assign receipt number. If cash type == 0 then use regular receipt
		// number
		// If cash type > 0 then user non-cash receipt number
		int receiptno = 0;
		DbLocale userlocale = null;
		DatabaseTransaction tlocale = null;
		boolean errorLocale = false;
		try {
			tlocale = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			userlocale = FdmsDb.getInstance().getLocaleWithLock(tlocale, user.getRegion());

			if (userlocale == null) {
				throw new SQLException("No locale for user region.");
			}

			if (cashtype <= 0) {
				receiptno = userlocale.getNextReceiptNo();
				userlocale.setNextReceiptNo(receiptno + 1);
			} else {
				receiptno = userlocale.getNextNonCashNo();
				userlocale.setNextNonCashNo(receiptno + 1);
			}

			tlocale.addPersistent(userlocale);
			tlocale.save();
			logger.debug("postPayment: next receipt number=" + receiptno);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1205) {
				logger.debug("postPayment - DbLocale locked from updating." + user.getRegion());
				throw new PersistenceException("User locale temporarily locked. Try again.");
			}

			logger.debug("postPayment - invalid region for user" + user.getUserName());
			if ( tlocale != null ) {
				tlocale.closeConnection();
				tlocale = null;
			}
			throw new PersistenceException("Invalid region for user", e);
		} 
		catch (Exception err) {
			logger.debug("postPayment - invalid region for user" + user.getUserName());
			if ( tlocale != null ) {
				tlocale.closeConnection();
				tlocale = null;
			}
			throw new PersistenceException("Invalid region for user", err);
		}
		finally {
			if ( tlocale != null ) {
				tlocale.closeConnection();
				tlocale = null;
			}
		}

		// Update total paid in financial object
		DbFinancialSummary finan = FdmsDb.getInstance().getFinancial(t, caseID);
		if (finan == null) {
			logger.debug("postPayment - invalid vitals case ID passed in");
			throw new PersistenceException("Invalid case ID specified");
		}

		finan.setLTotalPaidToDate(finan.getLTotalPaidToDate() + (amount * reverse));
		finan.setCLastPmtDate(datepaidMDY);
		finan.setCLastPmtMemo(payee);
		finan.setLLastPmtAmount((amount * reverse));
		finan.setLLastReceiptNo(receiptno);
		finan.setCLastPmtResp(comp.getDescription());

		// Add payment transaction to history
		DbCase caseinfo = FdmsDb.getInstance().getCase(t, caseID);
		if (caseinfo == null) {
			throw new PersistenceException("postPayment: Invalid vitals-ID:" + caseID);
		}

		DbLocation chapel = FdmsDb.getInstance().getLocation(t, caseinfo.getChapelNumber());

		if (chapel == null) {
			throw new PersistenceException("postPayment: Invalid chapel location specified for case:" + caseID);
		}

		// remove these persistents from the transaction since they are not
		// being saved
		try {
			t.removePersistent(chapel);
		}
		catch (Exception err) {
			throw new PersistenceException("postPayment: Invalid chapel location specified for case:" + caseID);
		}
		try {
			t.removePersistent(caseinfo);
		}
		catch (Exception err) {
			throw new PersistenceException("postPayment: Invalid vitals-ID:" + caseID);
		}
		//get servicedate to transaction
		DbVitalsDeceased deceased = null;
		deceased = FdmsDb.getInstance().getVitalsDeceased(t, caseID);
		//String serviceDate = deceased.getDateOfBurial();
		String serviceDate = deceased.getServiceDateKey();
		long createdTimestamp = System.currentTimeMillis();
		
		DbHistory hist = new DbHistory();
		hist.setNew();
		t.addPersistent(hist);
		hist.setLMainKey(caseID);
		hist.setIHistType(chargeType);
		hist.setCHistDesc(payee);
		hist.setLHistAmount(-(amount * reverse));
		hist.setCHistGLAcct(glacct);
		hist.setCHistARacct(chapel.getArAcct());
		hist.setLHistExempt(0);
		hist.setCHistDate(depdate);
		hist.setCHistSPF('P');
		hist.setLHistReceiptNo(receiptno);
		hist.setCHistManualReceipt(manualrec);
		hist.setCHistPayMethod(paymethod);
		hist.setCHistDatePaid(datepaid);
		hist.setCHistOriginalPosting('N');
		hist.setCHistPmtComponent(comp.getCode());
		hist.setLocationId(caseinfo.getChapelNumber());
		hist.setServiceDate(serviceDate);
		hist.setDatetimeOfTrans(createdTimestamp);
		
		
		// Now store changes to all tables through the same transaction
		try {
			t.save();
		}
		catch (Exception err) {
			throw new PersistenceException("postPayment: Error Transactionhostory" + err);
		}
		logger.debug("postPayment: finished saved id:" + hist.getId() );
		return ( hist.getId() );
			
	}
	
	/**
	 * Create reversing Transaction History records. Creation date: (11/13/02
	 * 9:11:17 AM)
	 * 
	 * @return void
	 */
	public void reverseHistoryForCase(DatabaseTransaction t, DbCase dbCase) {

		InventorySold soldData = null;

		// get transaction date in format needed by DbHistory
		
		Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

		// Deal with the Transaction History Records.
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		DbHistory[] histlist;
		try {
			DbHistorySet history = new DbHistorySet();
			h.put(DbHistoryPeer.VITALSID, dbCase.getVitalsID());
			history.restore(t, h);
			PersistentI[] obs = history.getPersistents();
			histlist = new DbHistory[obs.length];

			// Create new 'reversal' history records.
			for (int i = 0; i < obs.length; i++) {
				histlist[i] = (DbHistory) obs[i];
				// Remember that the following DOES NOT COPY the object.
				// reverseHistory is just a pointer to obs[i] and histlist[i].
				// there is just one object.
				DbHistory reverseHistory = (DbHistory) obs[i];
				reverseHistory.setNew();
				reverseHistory.setId(0);
				reverseHistory.setLHistAmount(histlist[i].getLHistAmount() * -1);
				reverseHistory.setLHistCostOfSale(histlist[i].getLHistCostOfSale() * -1);
				reverseHistory.setLHistExempt(histlist[i].getLHistExempt() * -1);
				reverseHistory.setCHistDeleteTran('Y');
				reverseHistory.setCHistDate( currentDate );
				reverseHistory.setCHistPosted(' ');
				reverseHistory.setCHistOriginalPosting('N');
				reverseHistory.setLInterfaceSeqNo(0);
				reverseHistory.setExported(' ');
				t.addPersistent(reverseHistory);
				/*
				 * reverseHistory.setCHistAcctPeriod(histlist[i].getCHistAcctPeriod());
				 * reverseHistory.setCHistARacct(histlist[i].getCHistARacct());
				 * reverseHistory.setCHistDatePaid(histlist[i].getCHistDatePaid());
				 * reverseHistory.setCHistDesc(histlist[i].getCHistDesc());
				 * reverseHistory.setCHistGLAcct(histlist[i].getCHistGLAcct());
				 * reverseHistory.setCHistInvItemName(histlist[i].getCHistInvItemName());
				 * reverseHistory.setCHistItemType(histlist[i].getCHistItemType());
				 * reverseHistory.setCHistManualReceipt(histlist[i].getCHistManualReceipt());
				 * reverseHistory.setCHistPayMethod(histlist[i].getCHistPayMethod());
				 * reverseHistory.setCHistPmtComponent(histlist[i].getCHistPmtComponent());
				 * reverseHistory.setCHistSPF(histlist[i].getCHistSPF());
				 * reverseHistory.setCHistTaxCode(histlist[i].getCHistTaxCode());
				 * reverseHistory.setCHistUserInit(histlist[i].getCHistUserInit());
				 * reverseHistory.setClaimNumber(histlist[i].getClaimNumber());
				 * reverseHistory.setDepositBatchNumber(histlist[i].getDepositBatchNumber());
				 * reverseHistory.setIHistType(histlist[i].getIHistType());
				 * reverseHistory.setIHistVersion(histlist[i].getIHistVersion());
				 * reverseHistory.setLHistInvSeqNo(histlist[i].getLHistInvSeqNo());
				 * reverseHistory.setLHistReceiptNo(histlist[i].getLHistReceiptNo());
				 * reverseHistory.setLHistTransactionNo(histlist[i].getLHistTransactionNo());
				 * reverseHistory.setLInterfaceSeqNo(histlist[i].getLInterfaceSeqNo());
				 * reverseHistory.setLMainKey(histlist[i].getLMainKey());
				 * reverseHistory.setLocationId(histlist[i].getLocationId());
				 * reverseHistory.setOrigChargeAmt(histlist[i].getOrigChargeAmt());
				 * reverseHistory.setOrigChargeDescr(histlist[i].getOrigChargeDescr());
				 * reverseHistory.setDateModified(FormatDate.convertDateToMMDDYYYY(new
				 * Date())); reverseHistory.setTimeModified(new
				 * Date().getTime()); t.addPersistent(reverseHistory);
				 */

				// If inventory item, it has to be restocked back into
				// inventory.
				if (histlist[i].getCHistSPF() == 'S' && histlist[i].getLHistInvSeqNo() > 0) {
					//AppLog.trace("Return inventory item on voided contract:
					// "+histlist[i].getCHistDesc()+"
					// amount:"+histlist[i].getLHistAmount());
					soldData = new InventorySold();
					soldData.setAcctRevenue(histlist[i].getCHistGLAcct());
					// Tran history file should contain a "C" COGS reocrd
					// immediately following the "S"
					// and an "I" Inventory record next after the "C".
					// So, get those GL accounts loaded into soldData for
					// inventoryReturn();
					if (i + 2 < obs.length) {
						DbHistory ctran = (DbHistory) obs[i + 1];
						DbHistory itran = (DbHistory) obs[i + 2];
						if (ctran.getCHistSPF() == 'C') {
							soldData.setAcctCostOfSale(ctran.getCHistGLAcct());
							//AppLog.trace("Got cogs tran
							// GL:"+ctran.getCHistGLAcct());
						}
						if (itran.getCHistSPF() == 'I') {
							soldData.setAcctInvAsset(itran.getCHistGLAcct());
							//AppLog.trace("got 'I' tran
							// GL:"+itran.getCHistGLAcct());
						}
					}
					inventoryReturn(t, histlist[i].getLHistInvSeqNo(), histlist[i].getInvOnHandID(), 1,
							DbInvHistory.TRAN_TYPE_ADJUSTMENT, FormatDate.convertDateToMMDDYYYY(currentDate), dbCase
									.getContractCode(), "", -(histlist[i].getLHistAmount()),
							"Voided case and returned to inv.", dbCase.getVitalsID().intValue(), soldData);
				}

			}

		} catch (PersistenceException pe) {
			//  logger.error("FdmsDb::reverseHistoryForCase Persistence
			// Exception:" ,pe);
		} catch (Exception e) {
			// logger.error("FdmsDb::reverseHistoryForCase.Exception: " ,e);
		}

	}

	/**
	 * Update the financialdata.DateInvoiceSent when printing Statements.
	 * Creation date: (11/22/2002 2:00:17 PM)
	 * 
	 * @return boolean true if successful
	 */
	public boolean setDateInvoiceSent(DbUserSession sessionUser, String fromDate, String toDate, String locationString) {

		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		DatabaseTransaction t = null;
		DbFinancialSummary dbFinancialSummary = null;

		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser);
			DbFinancialStatementSet dbFinancialStatementSet = new DbFinancialStatementSet();
			h.put("fromDate", new String(fromDate));
			h.put("toDate", new String(toDate));
			h.put("localeString", String.valueOf(sessionUser.getRegion()));
			h.put("locationString", new String(locationString));
			dbFinancialStatementSet.restore(t, h);

			// Update financialdata.DateInvoiceSent from persistence
			PersistentI[] obs = dbFinancialStatementSet.getPersistents();

			// This trick is needed to make a Persistent[] into a
			// DbFinancialSummary[]
			for (int i = 0; i < obs.length; i++) {
				dbFinancialSummary = (DbFinancialSummary) obs[i];
				try {
					dbFinancialSummary.setCFinDateInvoiceSent(FormatDate.convertToDateMMDDYYYY(FormatDate
							.getCurrentDateFormatedMMDDYYYY()));
				} catch (Exception e) {
				}
				t.addPersistent(dbFinancialSummary);
			}

			// Save the updated financialdata records
			t.save();
			return true;

		} catch (PersistenceException e) {
			return false;
		}
		finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}

	}

	/**
	 * Set a given form to "completed" for a given case. Creation date: (2/18/02
	 * 9:11:17 AM)
	 * 
	 * @return boolean true if successful
	 */
	public boolean setFormPrintedForCase(DbUserSession user, int vitalsid, int formid) {
		Date currdate;
		Time currtime;
		DbFormsPrinted oneForm;
		Calendar cal = Calendar.getInstance();
		currdate = new Date(cal.getTime().getTime());
		currtime = new Time(cal.getTime().getTime());
		DatabaseTransaction t = null;
		
		try {
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			DbFormsPrinted[] list = getFormsPrintedForCase(t, vitalsid, formid);
			if (list.length < 1) {
				// Add
				oneForm = new DbFormsPrinted();
				oneForm.setNew();
				t.addPersistent(oneForm);
				oneForm.setCaseId(vitalsid);
				oneForm.setFormId(formid);
			} else {
				// update
				oneForm = list[0];
			}
			oneForm.setCompleted(true);
			oneForm.setCompletedDate(currdate);
			oneForm.setCompletedTime(currtime);
			t.save();
		} catch (PersistenceException e) {
			//  logger.error("setPrintedForCase: Persistence Exception. ", e);
			return false;
		} finally {
			if ( t != null ) {
				t.closeConnection();
				t = null;
			}
		}
		return true;
	}

	/**
	 * This method returns a DbVeteran object with the default values set from
	 * the the dbVitals (deceased, spouse, nextkin, firstcall, etc...) data. It
	 * is called when there isn't an existing DbVeteran record for this
	 * vitalsId. Creation date: (10/2/2002 9:38:33 AM)
	 * 
	 * @return DbVeteran
	 * @param dbVitalsDeceased
	 *            DbVitalsDeceased
	 */
	public DbVeteran setVeteranFromVitals(DatabaseTransaction t, int vitalsId) {

		DbVitalsDeceased dbVitals = FdmsDb.getInstance().getVitalsDeceased(t, vitalsId);
		DbVitalsInformant dbInformant = FdmsDb.getInstance().getVitalsInformant(t, dbVitals.getId());
		DbVitalsFirstCall dbFirstCall = FdmsDb.getInstance().getVitalsFirstCall(t, dbVitals.getId());
		DbFinancialSummary finan = null;
		DbServices dbServices = null;

		try {
			dbServices = FdmsDb.getInstance().getServices(t, dbVitals.getId());
			finan = FdmsDb.getInstance().getFinancial(t, dbVitals.getId());
		} catch (Exception e) {
		}

		// Set the DbVeteran values
		DbVeteran dbVeteran = new DbVeteran();
		dbVeteran.setNew();
		dbVeteran.setId(dbVitals.getId());
		dbVeteran.setLMilitaryMainKey(dbVitals.getId());
		try {
			dbVeteran.setFlag_ApplicantDate(FormatDate.convertToDateMMDDYYYY(FormatDate
					.getCurrentDateFormatedMMDDYYYY()));
			dbVeteran.setBen2_Box32date(FormatDate.convertToDateMMDDYYYY(FormatDate.getCurrentDateFormatedMMDDYYYY()));
		} catch (Exception e) {
		}
		// construct an informant full name with appropriate spaces
		StringBuffer informantName = new StringBuffer(dbInformant.getFname());
		if (dbInformant.getMname().length() > 0) {
			informantName.append(" " + dbInformant.getMname());
		}
		if (dbInformant.getLname().length() > 0) {
			informantName.append(" " + dbInformant.getLname());
		}
		dbVeteran
				.setFlag_VetName(dbVitals.getDecLName() + ", " + dbVitals.getDecFName() + " " + dbVitals.getDecMName()); // Last,
		// First,
		// Middle
		dbVeteran.setHead_Fname(dbVitals.getDecFName());
		dbVeteran.setHead_Mname(dbVitals.getDecMName());
		dbVeteran.setHead_Lname(dbVitals.getDecLName());
		dbVeteran.setHead_Suffix(dbVitals.getSuffix());
		dbVeteran.setBen1_Box8OtherName(dbVitals.getDecMaiden());
		dbVeteran.setFlag_DeathDate(dbVitals.getDateOfDeath());
		dbVeteran.setFlag_BirthDate(dbVitals.getDateOfBirth());
		dbVeteran.setFlag_SocSecNo(dbVitals.getSSNo());
		dbVeteran.setBen1_PlaceBirth(dbVitals.getBirthPlace());
		
		dbVeteran.setBen1_DateEntered1(dbVitals.getWarFromDate());
		dbVeteran.setBen1_SepSrvcDate1(dbVitals.getWarToDate());
		dbVeteran.setFlag_EnlistmentDate(dbVitals.getWarFromDate());
		dbVeteran.setFlag_DischargeDate(dbVitals.getWarToDate());
		
		dbVeteran.setBen1_dayPhone(dbInformant.getPhone());
		dbVeteran.setBen1_nightPhone(dbInformant.getPhone());
		dbVeteran.setHead_ApplicantPhone(dbInformant.getPhone());
		dbVeteran.setBen2_Box30bName(informantName.toString());
		dbVeteran.setBen1_ClaimantName(informantName.toString());
		dbVeteran.setFlag_PersonReceiveFlag(informantName.toString());
		dbVeteran.setHead_ApplicantNameAddr(informantName.toString());
		dbVeteran.setBen1_ClaimantStreet(dbInformant.getStreet());
		dbVeteran.setBen1_ClaimantCityStZip(dbInformant.getCity() + ", " + dbInformant.getState() + " "
				+ dbInformant.getZip());
		dbVeteran.setFlag_ApplicantAddr1(dbInformant.getStreet());
		dbVeteran.setFlag_ApplicantAddr2(dbInformant.getCity() + ", " + dbInformant.getState() + " "
				+ dbInformant.getZip());
		dbVeteran.setBen2_Box31Address(dbInformant.getStreet() + "\n" + dbInformant.getCity() + ", "
				+ dbInformant.getState() + " " + dbInformant.getZip());
		dbVeteran.setFlag_ReceiveAddr1(dbInformant.getStreet());
		dbVeteran.setFlag_ReceiveAddr2(dbInformant.getCity() + ", " + dbInformant.getState() + " "
				+ dbInformant.getZip());
		dbVeteran.setHead_ApplicantNameAddr(dbInformant.getFname() + " " + dbInformant.getMname() + " "
				+ dbInformant.getLname() + "\n" + dbInformant.getStreet() + "\n" + dbInformant.getCity() + ", "
				+ dbInformant.getState() + " " + dbInformant.getZip());
		dbVeteran.setFlag_ApplicantRelation(dbInformant.getRelated());
		dbVeteran.setFlag_ReceiveRelation(dbInformant.getRelated());
		dbVeteran.setBen2_Box33Relationship(dbInformant.getRelated());
		dbVeteran.setHead_Relationship(dbInformant.getRelated());
		dbVeteran.setFlag_BurialDate(dbVitals.getDateOfBurial());
		dbVeteran.setBen1_BurialDate(dbVitals.getDateOfBurial());
		dbVeteran.setBen1_PlaceDeath(dbFirstCall.getPlaceDeath());

		/*
		 * dbVeteran.setFlag_SerialNumber(dbVitals.getVetSerialNumber());
		 * 
		 * if (dbVitals.getWarFromDate() != null &&
		 * dbVitals.getWarFromDate().trim().length() > 0 &&
		 * dbVitals.getWarToDate() != null &&
		 * dbVitals.getWarToDate().trim().length() > 0) {
		 * dbVeteran.setFlag_OtherServBox(Short.parseShort("1"));
		 * dbVeteran.setHead_WarOther(Short.parseShort("1"));
		 * dbVeteran.setHead_WarSpecify("From " +dbVitals.getWarFromDate() +" To "
		 * +dbVitals.getWarToDate()); }
		 * 
		 * if (dbVitals.getMilitaryOrganizatn() != null &&
		 * dbVitals.getMilitaryOrganizatn().trim().length > 0) { if
		 * (dbVitals.getMilitaryOrganizatn().toUpperCase().substring(0,2).equals("NA")) {
		 * dbVeteran.setHead_BranchNA(Short.parseShort("1"));
		 * dbVeteran.setFlag_NavyBox(Short.parseShort("1")); } else { if
		 * (dbVitals.getMilitaryOrganizatn().toUpperCase().substring(0,2).equals("AI")) {
		 * dbVeteran.setHead_BranchAF(Short.parseShort("1"));
		 * dbVeteran.setFlag_AirForceBox(Short.parseShort("1")); } else { if
		 * (dbVitals.getMilitaryOrganizatn().toUpperCase().substring(0,2).equals("MA")) {
		 * dbVeteran.setHead_BranchMC(Short.parseShort("1"));
		 * dbVeteran.setFlag_MarineBox(Short.parseShort("1")); } else { if
		 * (dbVitals.getMilitaryOrganizatn().toUpperCase().substring(0,2).equals("ME")) {
		 * dbVeteran.setHead_BranchMM(Short.parseShort("1"));
		 * dbVeteran.setFlag_OtherBranchBox(Short.parseShort("1")); } else { if
		 * (dbVitals.getMilitaryOrganizatn().toUpperCase().substring(0,2).equals("CO")) {
		 * dbVeteran.setHead_BranchCG(Short.parseShort("1"));
		 * dbVeteran.setFlag_CoastGaurdBox(Short.parseShort("1")); } else { if
		 * (dbVitals.getMilitaryOrganizatn().toUpperCase().substring(0,2).equals("AR")) {
		 * if (dbVitals.getMilitaryOrganizatn().equals("ARMY")) {
		 * dbVeteran.setHead_BranchAR(Short.parseShort("1"));
		 * dbVeteran.setFlag_ArmyBox(Short.parseShort("1")); } else {
		 * dbVeteran.setHead_BranchAC(Short.parseShort("1"));
		 * dbVeteran.setFlag_OtherBranchBox(Short.parseShort("1")); } } else {
		 * dbVeteran.setHead_BranchOther(Short.parseShort("1"));
		 * dbVeteran.setHead_BranchSpecify(dbVitals.getMilitaryOrganizatn());
		 * dbVeteran.setFlag_OtherBranchBox(Short.parseShort("1")); } } } } } } }
		 * else { if (dbVitals.getInArmedForces().equals("Y")) {
		 * dbVeteran.setHead_BranchOther(Short.parseShort("1"));
		 * dbVeteran.setHead_BranchSpecify(dbVitals.getMilitaryOrganizatn());
		 * dbVeteran.setFlag_OtherBranchBox(Short.parseShort("1")); } }
		 */

		// ServiceData
		if (dbServices != null) {
			if(dbServices.getCSrvCem()!= null && !"".equals(dbServices.getCSrvCem())){
				dbVeteran.setFlag_BurialPlace(dbServices.getCSrvCem());
			}
			if(dbServices.getCSrvCemStreet() != null && !"".equals(dbServices.getCSrvCemStreet())){
				dbVeteran.setFlag_BurialStreet(dbServices.getCSrvCemStreet());
			}
			if(dbServices.getCSrvCemCity()!= null && !"".equals(dbServices.getCSrvCemCity())){
				dbVeteran.setFlag_BurialCity(dbServices.getCSrvCemCity());
			}
			if(dbServices.getCSrvCemState()!= null && !"".equals(dbServices.getCSrvCemState())){
				dbVeteran.setFlag_BurialState(dbServices.getCSrvCemState());
			}
			if(dbServices.getCSrvCemZip()!= null && !"".equals(dbServices.getCSrvCemZip())){
				dbVeteran.setFlag_BurialZipCode(dbServices.getCSrvCemZip());
			}
		//	dbVeteran.setFlag_BurialPlace(dbServices.getCSrvCem() + "\n" + dbServices.getCSrvCemCity() + " "
		//			+ dbServices.getCSrvCemState());
			if (dbServices.getCSrvCem() == null || dbServices.getCSrvCem().trim().length() == 0) {
				dbVeteran.setHead_Box2Yes(Short.parseShort("1"));
				dbVeteran.setBen1_Box10BurialPlace(dbServices.getCSrvCremainsDisp());
				dbVeteran.setBen2_Box22Place1(dbServices.getCSrvCremainsDisp());
			} else {
				dbVeteran.setHead_Box2No(Short.parseShort("1"));
				dbVeteran.setBen1_Box10BurialPlace(dbServices.getCSrvCem() + "\n" + dbServices.getCSrvCemStreet()
						+ "\n" + dbServices.getCSrvCemCity() + " " + dbServices.getCSrvCemState() + " "
						+ dbServices.getCSrvCemZip());
				dbVeteran.setBen2_Box22Place1(dbServices.getCSrvCem());
				dbVeteran.setBen2_Box22Place2(dbServices.getCSrvCemStreet() + "\n" + dbServices.getCSrvCemCity() + " "
						+ dbServices.getCSrvCemState() + " " + dbServices.getCSrvCemZip());
			}

			//dbVeteran.setHead_GraveID(dbServices.getCSrvCemGrave());
			dbVeteran.setHead_GraveID("");
			dbVeteran.setHead_GraveSection(dbServices.getCSrvCemSection());
			//dbVeteran.setHead_GraveNumber(dbServices.getCSrvCemSpace());
			dbVeteran.setHead_GraveNumber(dbServices.getCSrvCemGrave());
			dbVeteran.setHead_ConsigneeNameAddr(dbServices.getCSrvCem() + "\n" + dbServices.getCSrvCemStreet() + "\n"
					+ dbServices.getCSrvCemCity() + " " + dbServices.getCSrvCemState() + " "
					+ dbServices.getCSrvCemZip());
			dbVeteran.setHead_CemPhone(dbServices.getCSrvCemPhone());
			dbVeteran.setHead_CemNameAddr(dbServices.getCSrvCem() + "\n" + dbServices.getCSrvCemStreet() + "\n"
					+ dbServices.getCSrvCemCity() + " " + dbServices.getCSrvCemState() + " "
					+ dbServices.getCSrvCemZip());
		}
		// Financial summary data
		if (finan != null) {
			dbVeteran.setBen1_Box16TotBurialExp(finan.getLFinTotalCharges());
			dbVeteran.setBen1_Box16AmountPaid(finan.getLTotalPaidToDate());
		}
		return dbVeteran;
	}

	/**
	 * Void the Contract: void DbCase (vitalstats), DbSurviviors (survivors),
	 * and create reversing Transaction History records. Creation date:
	 * (11/13/02 9:11:17 AM)
	 * 
	 * @return void
	 */
	public void voidCase(DatabaseTransaction t, int vitalsid) throws PersistenceException {

		// Set the Vitals Voided Code.
		DbCase dbCase = FdmsDb.getInstance().getCase(t, vitalsid);

		if (dbCase.getVoidedContractCode() != null && dbCase.getVoidedContractCode().equals("V")) {
			//AppLog.trace("FdmsDb.voidCase.PersistenceException: Case - "
			// +vitalsid +" is already voided.");
			throw (new PersistenceException("FdmsDb.voidCase.PersistenceException: Case: " + dbCase.getId()
					+ " is already voided."));
		}

		//AppLog.trace("Voiding Case: " +dbCase.getId() +" - "
		// +dbCase.getFirstName() +dbCase.getLastName());
		dbCase.setVoidedContractCode("V");
		try {
			String currentDate = FormatDate.convertToDateYYYYMMDD(FormatDate.getCurrentDateFormatedMMDDYYYY());
			dbCase.setVoidedDate(currentDate);
		}catch (Exception e){
			
		}
		t.addPersistent(dbCase);

		// Set the Survivors Voided Code.
		DbSurvivor[] dbSurvivor = FdmsDb.getInstance().getSurvivorsForID(t, vitalsid);
		for (int i = 0; i < dbSurvivor.length; i++) {
			dbSurvivor[i].setCSurvivorVoided("V");
			t.addPersistent(dbSurvivor[i]);
		}

		// Create Reversing Entries for the Transaction History Records.
		FdmsDb.getInstance().reverseHistoryForCase(t, dbCase);

	}

	public int getDepositIdForCase(DatabaseTransaction t, int vitalsId) {
		int transHistId = 0;
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection conn = null;
		try {
			conn = t.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT TranHistID from transactionhistory ");
			sql.append("WHERE (VitalsMasterKey = ?) ");
			sql.append("    AND (ChargeType = 9000)");
			sql.append("    AND (DeleteTransaction != 'Y')");

			statement = conn.prepareStatement(sql.toString());
			statement.setInt(1, vitalsId);
			rs = statement.executeQuery();

			if (rs.next()) {
				transHistId = rs.getInt(1);
			}

		} catch (Exception e) {
			logger.error("Exception getDepositForCase() : ", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.error("SQL Exception in getDepositForCase() : ", e);
			}
		}

		return transHistId;
	}

	public int getComponentId(DatabaseTransaction t, int vitalsId) {
		logger.debug("Entering getComponentId()");
		int componentId = 0;
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection conn = null;

		try {
			logger.debug("Creating connection");
			conn = t.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT PmntComponentID from pmntcomponents ");
			sql.append("WHERE (VitalsID = ?) ");
			sql.append("    AND (Code = '001')");

			logger.debug("SQL : " + sql);
			statement = conn.prepareStatement(sql.toString());
			statement.setInt(1, vitalsId);
			rs = statement.executeQuery();

			if (rs.next()) {
				componentId = rs.getInt(1);
			}

		} catch (Exception e) {
			logger.error("Exception getComponentId() : ", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.error("SQL Exception in getComponentId() : ", e);
			}
		}

		return componentId;
	}

	/**
	 * Iterates throug arrayList of locationId's and adds Location name found in
	 * db
	 * 
	 * @param userLocales
	 * @param user
	 */
	public void getUserLocationNames(DatabaseTransaction t, ArrayList userLocations, DbUserSession user) {

		logger.debug("Entering getUserLocationNames()");
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			Iterator it = userLocations.iterator();
			conn = t.getConnection();

			while (it.hasNext()) {
				UserLocationDTO userLocationDto = (UserLocationDTO) it.next();
				logger.debug("LocationId : " + userLocationDto.getLocationId());

				StringBuffer sql = new StringBuffer();
				sql.append("SELECT Name, Locale, InactiveCode FROM locations ");
				sql.append("WHERE (LocationID = ?)");

				statement = conn.prepareStatement(sql.toString());
				statement.setLong(1, userLocationDto.getLocationId());
				rs = statement.executeQuery();
				if (rs.next()) {
					String locationName = rs.getString(1);
					long regionId = rs.getLong(2);
					String inactiveCode = rs.getString(3);

					if ((inactiveCode == null) || (!inactiveCode.equals("D"))) {
						userLocationDto.setName(locationName);
						userLocationDto.setRegionId(regionId);
					} else {
						it.remove();
					}

				} else {
					it.remove();
				}

				logger.debug("location name found : " + userLocationDto.getName());
				statement.clearParameters();
			}

		} catch (SQLException e) {
			logger.error("SQL Exception in getUserLocationNames() : ", e);
		} catch (Exception e) {
			logger.error("Exception getUserLocationNames() : ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				
				if (statement != null) {
					statement.close();
					statement = null;
				}
					
			} catch (SQLException e) {
				logger.error("SQL Exception in closing resources : ", e);
			}
		}
	}

	public int getLocationRegionId(ArrayList userLocations, String locationId) {

		int regionId = 0;
		String regionIdStr = null;
		Iterator it = userLocations.iterator();
		int locID = Integer.parseInt(locationId);

		while (it.hasNext()) {
			UserLocationDTO userLocationDto = (UserLocationDTO) it.next();
			logger.debug("RegionId : " + userLocationDto.getRegionId());
			if (userLocationDto.getLocationId() ==locID) {
				return ( (int) userLocationDto.getRegionId() );
			}
		}
		
		return regionId;
	}

	/**
	 * 
	 * @param t
	 * @param externalAppId
	 * @param vitalsMasterKey
	 * @return
	 */
	public long getExternalVitalsId(DatabaseTransaction t, String externalAppId, int vitalsMasterKey) {

		long externalVitalsId = 0L;

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			conn = t.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ExternalVitalsId ");
			sql.append(" FROM vitalsexternalapp_xref ");
			sql.append("WHERE (ExternalAppId = ?) ");
			sql.append("	AND (VitalsMasterKey = ?)");

			logger.debug("SQL : " + sql);
			statement = conn.prepareStatement(sql.toString());
			statement.setString(1, externalAppId);
			statement.setLong(2, vitalsMasterKey);
			rs = statement.executeQuery();

			if (rs.next())
				externalVitalsId = rs.getLong(1);

		} catch (PersistenceException e) {
			logger.error("PersistenceException in getExternalVitalsId() : ", e);
		} catch (SQLException e) {
			logger.error("SQLException in getExternalVitalsId() : ", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				logger.error("SQL Exception in closing resources : ", e);
			}
		}

		return externalVitalsId;
	}

	/**
	 * Creates a TreeMap of preneed disbursements for vitalsMasterKey
	 * 
	 * @param t
	 * @param vitalsMasterKey
	 * @return
	 */
	public TreeMap getPreneedDisbursements(DatabaseTransaction t, int vitalsMasterKey) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		TreeMap map = new TreeMap();
		DbPreneedDisbursement pnDisbursement = null;
		try {
			DbPreneedDisbursementSet pnDisbursements = new DbPreneedDisbursementSet();
			h.put(DbPreneedDisbursementPeer.VITALSMASTERKEY, new Integer(vitalsMasterKey));
			pnDisbursements.restore(t, h);
			PersistentI[] obs = pnDisbursements.getPersistents();

			for (int i = 0; i < obs.length; i++) {
				pnDisbursement = (DbPreneedDisbursement) obs[i];
				map.put(Integer.toString(pnDisbursement.getDisbursementId()), pnDisbursement);
			}
		} catch (PersistenceException e) {
			logger.error("PersistenceException in getPreneedDisbursements() : ", e);
		}

		return map;
	}

	/**
	 * 
	 * @param userId
	 */
	public void updateUserTOS(DbUserSession sessionUser) {

		PreparedStatement statement = null;
		Connection conn = null;
		DatabaseTransaction t = null;

		try {
			String userLookup = UtilSingleton.getInstance().getUserDBLookup();
			t = (DatabaseTransaction) DatabaseTransaction.getTransaction(sessionUser, userLookup);

			conn = t.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE usersecurity SET TOS='Y' ");
			sql.append("WHERE (UserID = ?)");

			statement = conn.prepareStatement(sql.toString());
			statement.setInt(1, sessionUser.getId() );
			logger.debug("SQL : " + sql);
			statement.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			logger.error("Exception updateUserTOS() : ", e);
		} finally {
	            try {
	                if (statement != null) statement.close();
	            } catch (SQLException e) {
	                logger.error("SQL Exception in closing prepared statement : ", e);
	            }
	            
	            try {
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                logger.error("SQL Exception in closing connection : ", e);
	            }

				if ( t != null ) {
					t.closeConnection();
					t = null;
				}
	            
		}

	}

	/**
	 * Create an instanace of the DbPreneed class and return it for a specified
	 * vitals ID. Creation date: (1/06/02 9:11:17 AM)
	 * 
	 * @return DbCemAtneed
	 */
	public DbCemProperty getCemProperty(DatabaseTransaction t, int vitalsid) {
		DbCemProperty p;
		try {
			p = (DbCemProperty) Persistent.getPersistent(t, vitalsid, "com.aldorsolutions.webfdms.beans.DbCemAtneed");
			t.addPersistent(p); // make the persistent part of the transaction
			// could do some initialization here or getting specific recods
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getCemAtneed Persistence Exception:",e);
			return null;
		}
		return p;

	}

	/**
	 * Create a list of BookMark objects for a Locale or location Creation
	 * date: 
	 * 
	 * @return ArrayList
	 */
	public ArrayList<DbBookMark> getBookMarks(DatabaseTransaction t, int locale, int location) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		ArrayList<DbBookMark> list;
		try {
			DbBookMarkSet set = new DbBookMarkSet();
			if(locale != 0) {
				h.put(DbBookMarkLocationPeer.LOCALEID, Integer.toString(locale));
			}
			if(location > 0) {
				h.put(DbBookMarkLocationPeer.LOCATIONID, Integer.toString(location));
			}
			
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new ArrayList<DbBookMark>();
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++) {
				list.add( (DbBookMark) obs[i]);
			}
			return list;
		} catch (PersistenceException e) {
			logger.error("FdmsDb::getBookMarks Persistence Exception:", e);
			return null;
		}
	}
	
	public DbBookMark getBookMark(DatabaseTransaction t, int bookMarkId){
		DbBookMark bookMark = null;
		try {
			bookMark = (DbBookMark) DbBookMark.getPersistent(t, bookMarkId, "com.aldorsolutions.webfdms.beans.DbBookMark");
			return bookMark;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getBookMark Persistence Exception:", e);
			return null;
		}
	}
	
	/**
	 * Create a list of BookMarkLocation objects for a given bookmark
	 * date: 
	 * 
	 * @return ArrayList
	 */
	public ArrayList<DbBookMarkLocation> getBookMarksLocations(DatabaseTransaction t, int bookmarkId) {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		ArrayList<DbBookMarkLocation> list;
		try {
			DbBookMarkLocationSet set = new DbBookMarkLocationSet();
			if(bookmarkId > 0){
				h.put(DbBookMarkLocationPeer.BOOKMARKID, new Integer(bookmarkId).toString());
			}
			set.restore(t, h);
			PersistentI[] obs = set.getPersistents();
			list = new ArrayList<DbBookMarkLocation>();
			// This trick is needed to make a Persistent[] into a DbHistory[]
			for (int i = 0; i < obs.length; i++){
				list.add( (DbBookMarkLocation) obs[i]);
			}
			return list;
		} catch (PersistenceException e) {
			//  logger.error("FdmsDb::getBookMarksLocations Persistence Exception:", e);
			return null;
		}
	}
	

	public ArrayList <DbApVendor> getVendors (DatabaseTransaction t) throws PersistenceException {
		
		ArrayList <DbApVendor> vendorList = new ArrayList <DbApVendor> ();
		DbApVendorSet vendorSet = new DbApVendorSet();
		vendorSet.restore(t);
		PersistentI[] obs = vendorSet.getPersistents();
		
		// This trick is needed to make a Persistent[] into a DbHistory[]
		for (int i = 0; i < obs.length; i++) {
			vendorList.add( (DbApVendor) obs[i] );
		}
		
		return ( vendorList );		
	}
	
	public ArrayList <ApVendorDTO> getVendorsByRegion (DbUserSession user, DatabaseTransaction t, int region) throws PersistenceException {
		
		ApVendorDAO vendorDao = new ApVendorDAO(user);
		ArrayList <ApVendorDTO> vendorList = vendorDao.getApVendorByLocale(true,user.getRegion());
		return ( vendorList );		
	}
	
	public ArrayList <ApVendorDTO> getVendorsByRegion (DbUserSession user, DatabaseTransaction t, int region, boolean status) throws PersistenceException {
		
		ApVendorDAO vendorDao = new ApVendorDAO(user);
		ArrayList <ApVendorDTO> vendorList = vendorDao.getApVendorByLocale(status,user.getRegion());
		return ( vendorList );		
	}
	
	public ArrayList <UserDTO> getUsers (long companyID) throws PersistenceException {
		UserManagerBean userMgr = new UserManagerBean ();
		ArrayList <UserDTO> userList = userMgr.getUsers(UserDAO.ACTIVE);
		return ( userList );		
	}
	
	public UserDTO getUser (long userID) throws PersistenceException {
		UserManagerBean userMgr = new UserManagerBean ();
		return ( userMgr.getUser(userID) );		
	}
	

	/**
	 * Retrieve the list of expense account distributions. Creation date:
	 * (5/2/2002 4:00:22 PM)
	 * 
	 * @return DbApDistribution[]
	 */
	public DbApDistribution[] getDistributions(DatabaseTransaction trans, int apMasterID) throws PersistenceException {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		DbApDistribution[] list;
		if (trans == null) {
			// AppLog.error("DbApMaster::getDistributions transaction is null,
			// cannot proceed.");
			throw new PersistenceException("Transaction is no longer valid.");
		}
		DbApDistributionSet set = new DbApDistributionSet();
		h.put(DbApDistributionPeer.MASTERID, new Integer(apMasterID));
		set.restore(trans, h);
		PersistentI[] obs = set.getPersistents();
		list = new DbApDistribution[obs.length];
		// This trick is needed to make a Persistent[] into a DbHistory[]
		for (int i = 0; i < obs.length; i++) {
			list[i] = (DbApDistribution) obs[i];
		}
		return list;
	}
	
	public DbApDistributionHistory[] getDistributionsHist(DatabaseTransaction trans, int apMasterID) throws PersistenceException {
		Hashtable <String, Object> h = new Hashtable<String, Object> ();
		DbApDistributionHistory[] list;
		if (trans == null) {
			// AppLog.error("DbApMaster::getDistributions transaction is null,
			// cannot proceed.");
			throw new PersistenceException("Transaction is no longer valid.");
		}
		DbApDistributionHistorySet set = new DbApDistributionHistorySet();
		h.put(DbApDistributionHistoryPeer.APMASTERID, new Integer(apMasterID));
		set.restore(trans, h);
		PersistentI[] obs = set.getPersistents();
		list = new DbApDistributionHistory[obs.length];
		// This trick is needed to make a Persistent[] into a DbHistory[]
		for (int i = 0; i < obs.length; i++) {
			list[i] = (DbApDistributionHistory) obs[i];
		}
		return list;
	}
	
	
	public DbArrangers getArrangerInactive(DatabaseTransaction t, int locale, int srrangerID ){
		
		DbArrangers[] inactive = FdmsDb.getInstance().getInactiveArrangers(t, locale);
		DbArrangers arranger = null;
        for (int i=0;  inactive != null && i < inactive.length; i++) {
            if(inactive[i].getId()== srrangerID){
            	arranger = inactive[i];
            	break;
            }
        }
        return arranger;
	}
	
	public void transferCaseToNewLocation(int vitalsId, int localeId, int locationId, DbUserSession sessionUser, 
			int directorId, boolean isFacilitySame, String facilityName, String facilityStreet, String facilityCity, 
			String facilityState, String facilityZip, String facilityPhone, String facilityLicense, DatabaseTransaction t){
		DbCase theCase = null;
		DbVitalsFirstCall firstCall = null;
//		DatabaseTransaction t = null;
		DbLocation dbLocation = null;
//		try {
//			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
			theCase 	= FdmsDb.getInstance().getCase(t,vitalsId);
			theCase.setLocale(localeId);
			theCase.setChapelNumber(locationId);
			
			dbLocation = FdmsDb.getInstance().getLocation(t, locationId);
			theCase.setChapelLocation(dbLocation.getName());
			
			firstCall	= FdmsDb.getInstance().getVitalsFirstCall(t,vitalsId);
			DbArrangers dbArranger = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getArranger(t, directorId);
			if (dbArranger != null){
				firstCall.setArrangerName(dbArranger.getName());
				firstCall.setArrangerID(dbArranger.getId());
				firstCall.setDirectorLicense(dbArranger.getLicenseNumber());
			}
			
			if (isFacilitySame) {
				firstCall.setFacilitySameAsChapel("Y");
				firstCall.setFacilityName(dbLocation.getName());
				firstCall.setFacilityStreet(dbLocation.getAddr1());
				firstCall.setFacilityCityStZip(dbLocation.getCity() + ", " + dbLocation.getState() + ", "
						+ dbLocation.getZip());
				firstCall.setFacilityLicenseNo(dbLocation.getLicenseNumber());
				firstCall.setFacilityPhone(FormatString.formatPhone(dbLocation.getPhone()));
			} else {
				firstCall.setFacilitySameAsChapel("N");
				firstCall.setFacilityName(facilityName);
				firstCall.setFacilityStreet(facilityStreet);
				String tmpStr = "";
				if ( facilityState.length() == 2) {
					tmpStr = facilityState.toUpperCase();
				}
				else {
					tmpStr = facilityState;   	
				}
				
				firstCall.setFacilityCityStZip(facilityCity + ", " + tmpStr + ", "
						+ facilityZip.toUpperCase());
				firstCall.setFacilityPhone(FormatString.formatPhone(facilityPhone));
				firstCall.setFacilityLicenseNo(facilityLicense);
				
			}
			firstCall.setChapelName(dbLocation.getName());
			
			updateHistoryLocationTran(sessionUser,t,vitalsId,locationId);
			t.addPersistent(theCase);
			t.addPersistent(firstCall);
			
//		} catch(PersistenceException pe) {
//            logger.error("Persistence Exception in FdmsDb.transferCaseToNewLocation.do Perform. " + pe);
//        } catch(Exception pe) {
//            logger.error("Exception in FdmsDb.transferCaseToNewLocation.do Perform. ", pe);
//        } finally {
//            if (t != null) {
//            	t.closeConnection();
//            	t = null;
//            }
//        }	
	}
	
	public void updateHistoryLocationTran(DbUserSession sessionUser,DatabaseTransaction t, int vitalsId, int locationId){
		DbUser user = (DbUser) sessionUser;
		TransactionhistoryDAO transDao = new TransactionhistoryDAO(user);
		ArrayList <TransactionhistoryDTO> transes = new ArrayList <TransactionhistoryDTO>();
		transes = transDao.getTransactionhistory(vitalsId);
		for(TransactionhistoryDTO tran: transes) {
			DbHistory dbHistory = FdmsDb.getInstance().getHistory(t, tran.getTranHistID());
			dbHistory.setLocationId(locationId);
			t.addPersistent(dbHistory);
		}
	}
	
	public void SMSCommonNoTimerGenerator(CompanyDTO company, int scheduleID) {


		SMSSchedulingDTO smsSche = new SMSSchedulingDTO();
		SMSSchedulingDAO smsScheDAo = new SMSSchedulingDAO(DAO.DB_FDMSCOMMON);
		smsSche = smsScheDAo.getSMSCommonScheduling(scheduleID);

		if ((smsSche != null) && ( (smsSche.getStatus().compareToIgnoreCase("Q") == 0) || (smsSche.getStatus().compareToIgnoreCase("R") == 0)) ) {

			// get in to run the scheduling we need to update Q to R and set the starting time
			smsSche.setStatus("R");
			long startingTimestamp = System.currentTimeMillis();
			smsSche.setStartDateTime(startingTimestamp);
			try {
				smsScheDAo.updateSMSCommonScheduling(smsSche);
			}catch (Exception pe) {
					pe.printStackTrace();
			}
			//
//			   sendSMS(smsSche,company);
			 try{
				 
				
				 
				 String msg = "";
//		    	 msg = getSMSSql(smsSche.getSMSSqlId(),smsSche.getDbLookup());
				 msg = "test";
		    		
		         String message = msg;
		         message = smsSche.getStartMessage()+" "+message+" "+smsSche.getEndMessage();
		         
				//this is for callfire (www.callfire.com).	
					SMSService service = new SMSService();
					ArrayOfString numbers = new ObjectFactory().createArrayOfString();
					//numbers.getString().add("9405942322,HI Chai!");
					numbers.getString().add(smsSche.getAreaCode()+smsSche.getPhone()+","+message);
					String key = "d8b7227f26bbdf34fa40498f7aac2e5a1f880320";
					long compaignid = service.getSMSServiceHttpPort().sendSMSCampaign(key, numbers, "hi");
				//end callfire	 
				 
			
			}catch (Exception e){
				logger.error("FdmsDb::SMSCommonNoTimerGenerator Persistence Exception:", e);
				int a = 0;
		        	
		    }
				//we finish up the task.
				
				smsSche.setStatus("S");
				long createdTimestamp = System.currentTimeMillis();
				smsSche.setStopDateTime(createdTimestamp);
				
					
					
			}else {
				smsSche.setStatus("E");
				long createdTimestamp = System.currentTimeMillis();
				smsSche.setStopDateTime(createdTimestamp);
			}
			
			try {
				smsScheDAo.updateSMSCommonScheduling(smsSche);
			}catch (Exception pe) {
	
			}
			
	}
	
	public String getSMSSql(int sqlId, String DbLookup, int locationId){
	   String sql = "";
	   
	   SMSStoreProcDAO smsStoreProcDao = new SMSStoreProcDAO(DAO.DB_FDMSCOMMON);
	   SMSStoreProcDTO smsDto = new SMSStoreProcDTO();
	   smsDto = smsStoreProcDao.getSMSStroreProc(sqlId);
	   
	   java.util.Date rDate = new java.util.Date();
	   java.util.Date fDate = new java.util.Date();
	   java.util.Date tDate = new java.util.Date();
	   String strFDate = FormatDate.convertDateToMMDDYYYY(rDate);
	   String strTDate = FormatDate.convertDateToMMDDYYYY(rDate);
	   String strRDate = FormatDate.convertDateToMMDDYYYY(rDate);
	   int field = 9999;
		if (smsDto.getType().compareToIgnoreCase("D") == 0) {
			field = -1;
			strRDate = FormatDate.addToDateMMDDYYYY(strRDate, -1, 0);
			try {
				strRDate = FormatDate.convertMMddyyyyToYYYY_MM_DD(strRDate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (smsDto.getType().compareToIgnoreCase("W") == 0) {
			field = -7;
			strRDate = FormatDate.addToDateMMDDYYYY(strRDate, -7, 0);
			try {
				strRDate = FormatDate.convertMMddyyyyToYYYY_MM_DD(strRDate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			strTDate = FormatDate.addToDateMMDDYYYY(strRDate, -1, 0);
			try {
				strTDate = FormatDate.convertMMddyyyyToYYYY_MM_DD(strTDate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (smsDto.getType().compareToIgnoreCase("M") == 0) {
			field = 0;
			int mm = FormatDate.getMonthFromMMDDYYYY(strRDate);
			int yyyy = FormatDate.getYearFromMMDDYYYY(strRDate);
			if (mm == 1){
				mm = 12;
				yyyy = yyyy-1;
			}else {
				mm = mm -1;
			}
			strRDate = String.valueOf(yyyy)+"-"+String.valueOf(mm)+"%";
			
		} 
//		if(field==9999){
//			strRDate = FormatDate.addToDateMMDDYYYY(strRDate, field, (field == 0? 1: 0));
//			strFDate = FormatDate.addToDateMMDDYYYY(strRDate, field, (field == 0? 1: 0));
//			strTDate = FormatDate.addToDateMMDDYYYY(strRDate, field, (field == 0? -1: 0));
//			rDate = FormatDate.convertMMDDYYYYToDateYYMMDD(strRDate);
//			fDate = FormatDate.convertMMDDYYYYToDateYYMMDD(strFDate);
//			tDate = FormatDate.convertMMDDYYYYToDateYYMMDD(strTDate);
//			strRDate = FormatDate.convertDateToYYYY_MM_DD(rDate);
//			strFDate = FormatDate.convertDateToYYYY_MM_DD(fDate);
//			strTDate = FormatDate.convertDateToYYYY_MM_DD(tDate);
//		}
	   
		sql = smsStoreProcDao.getSMSStroreProc(smsDto.getSqlScript(),smsDto.getType(),strRDate,strTDate,DbLookup, locationId);
	   
	   
	   return sql;
	}
	
	public ArrayList <SMSSchedulingDTO> getSMSList() {
		ArrayList <SMSSchedulingDTO> smsScheList = null;
			SMSSchedulingDAO smsScheDAo = new SMSSchedulingDAO(DAO.DB_FDMSCOMMON);
			smsScheList = smsScheDAo.getSMSSchedulingCommonQ();
		return smsScheList;
	}
	
	/**
	 * This will return all presentation images list, these images' information
	 * is stored in the common database.
	 * 
	 * @return
	 */
	public List<PresentationImageDTO> getAllPresentationImageList() {
		List<PresentationImageDTO> imageList = new ArrayList<PresentationImageDTO>();

		String dbLookup = DAO.DB_FDMSCOMMON;
		
		try {
			
			PresentationImageDAO imageDao = new PresentationImageDAO(dbLookup);
			
			imageList = imageDao.getAllPresentationImages();
			
		} catch (Exception e) {
			logger.error("FdmsDb::getAllPresentationImageList Exception:", e);
			imageList = null;
		} 

		return imageList;
	}
}