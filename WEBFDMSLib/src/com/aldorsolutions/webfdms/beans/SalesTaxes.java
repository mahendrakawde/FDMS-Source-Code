package com.aldorsolutions.webfdms.beans;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.pricelist.bean.PriceListManager;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionHelpers;
import com.aldorsolutions.webfdms.util.TaxType;

/**
 * Manage data needed to calculate sales tax.
 * Creation date: (2/28/2003 4:10:40 PM) modify 030309
 * @author: 
 */
public class SalesTaxes {
    
    private Logger logger = Logger.getLogger(SalesTaxes.class.getName());
	private DbUser ourUser;
	private java.util.Map taxCodes;
	public final static java.lang.String CATEGORY_NAME = "TaxCode";
	public final static int	STATE_TAX_ID = 99;
	public final static int	LOCAL_TAX_ID = 98;
	public final static int ARTICLE_TAX_ID = 97;
	PriceListManager plm = null;
	
/**
 * SalesTaxes constructor
 * Store the user for this set of tax codes and initialize the collection
 */
public SalesTaxes(DbUser theuser) {
	super();
	setOurUser(theuser);
	initializeCollection();
	plm = new PriceListManager();
}
/**
 * Get the LOCAL sales tax price list item and add it to the charge collection.
 * Creation date: (3/3/2003 1:18:06 PM)
 * @return com.aldorsolutions.webfdms.beans.DbChargeItem
 */
public FinancialInformationLineItem addLocalTaxCharge(
    java.util.Map chargeSet,
    DbUser user,
    String version,
    int vitalsid) throws PersistenceException {
    
    DbChargeItem taxCharge = null;
    FinancialInformationLineItem collectionItem = null;
    DatabaseTransaction t =null;
    DbPriceList dbPriceList[] =null;
    int uniqueSeqNo = 0;
    
    try {

        // Get price list for this user/version and look for State sales tax line
        t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
        
        dbPriceList = plm.getPriceListForVersion(t, version, user.getRegion());
        
		DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, user.getRegion());
		int orderByContLine  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, user.getRegion(), 
				DbLocaleConfigType.CONFIG_ORDER_BY_CONT_LINE_ON_FINANCIAL_PAGE);
        
        
        for (int i=0 ; i < dbPriceList.length ; i++) {
            
            if (dbPriceList[i].getActive() == 'Y'
                && dbPriceList[i].getContrLine() == LOCAL_TAX_ID ){                               
                    
                // Got LOCAL sales tax line
                // now create a new DbCharge and add it to the collection
                taxCharge = new com.aldorsolutions.webfdms.beans.DbChargeItem(vitalsid, dbPriceList[i].getInvoiceSeqNo(), 0, 
                		(int)dbPriceList[i].getContrLine(), dbPriceList[i].getContrDescr(), (int)0, dbPriceList[i].getGlAcctNo(), 
                		(int)0,	"", 'S', "", dbPriceList[i].getCategory(), (int)0, (int)0, dbPriceList[i].getRecID(),false, (int) 0);
                
                // get valid sequence number
                uniqueSeqNo = SessionHelpers.calculateSequenceNumber(chargeSet, taxCharge);
                taxCharge.setSequenceNumber(uniqueSeqNo);
                collectionItem = new FinancialInformationLineItem(taxCharge);
                collectionItem.setModifiedItem(true); // modified this session
                collectionItem.setNewItem(true); // added this session
                
                
                if (orderByContLine == 1){
					chargeSet.put(new Integer(collectionItem.getItemTypeCode()+""+uniqueSeqNo), collectionItem);
				}else {
					chargeSet.put(new Integer(uniqueSeqNo), collectionItem);
				}
                
                
                break; // exit FOR loop
            }
        }
    } finally {
		if ( t != null ) {
			t.closeConnection();
			t = null;
		}
	}
    
    return collectionItem;
}
/**
 * Get the ARTICLE sales tax price list item and add it to the charge collection.
 * Creation date: (7/12/2005 7:16:06 PM)
 * @return com.aldorsolutions.webfdms.beans.DbChargeItem
 */
public FinancialInformationLineItem addArticleTaxCharge(
    java.util.Map chargeSet,
    DbUser user,
    String version,
    int vitalsid) throws PersistenceException {
    
    DbChargeItem taxCharge = null;
    FinancialInformationLineItem collectionItem = null;
    DatabaseTransaction t = null;
    DbPriceList dbPriceList[] = null;
    int uniqueSeqNo = 0;
    
    try {

        // Get price list for this user/version and look for State sales tax line
        t = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
        dbPriceList = plm.getPriceListForVersion(t, version, user.getRegion());
        
        DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, user.getRegion());
		int orderByContLine  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, user.getRegion(), 
				DbLocaleConfigType.CONFIG_ORDER_BY_CONT_LINE_ON_FINANCIAL_PAGE);
        
        for (int i=0 ; i < dbPriceList.length ; i++) {
            
            if (dbPriceList[i].getActive() == 'Y'
                && dbPriceList[i].getContrLine() == ARTICLE_TAX_ID ){                               
                    
                // Got ARTICLE sales tax line
                // now create a new DbCharge and add it to the collection
                taxCharge = new com.aldorsolutions.webfdms.beans.DbChargeItem(vitalsid, dbPriceList[i].getInvoiceSeqNo(), 0, 
                		(int)dbPriceList[i].getContrLine(), dbPriceList[i].getContrDescr(), (int)0, dbPriceList[i].getGlAcctNo(), 
                		(int)0,	"", 'S', "", dbPriceList[i].getCategory(), (int)0, (int)0, dbPriceList[i].getRecID(),false,(int)0);
                
                // get valid sequence number
                uniqueSeqNo = SessionHelpers.calculateSequenceNumber(chargeSet, taxCharge);
                taxCharge.setSequenceNumber(uniqueSeqNo);
                collectionItem = new FinancialInformationLineItem(taxCharge);
                collectionItem.setModifiedItem(true); // modified this session
                collectionItem.setNewItem(true); // added this session
                
                if (orderByContLine == 1){
					chargeSet.put(new Integer(collectionItem.getItemTypeCode()+""+uniqueSeqNo), collectionItem);
				}else {
					 chargeSet.put(new Integer(uniqueSeqNo), collectionItem);
				}

                break; // exit FOR loop
            }
        }
    } finally {
		if ( t != null ) {
			t.closeConnection();
			t = null;
		}
	}
    
    return collectionItem;
}
/**
 * Add a sale amount to matching sales tax code item in collection.
 * Do nothing if tax code is empty.
 * Throw exception if tax code is not in collection, i.e. an invalid tax code
 * Creation date: (3/3/2003 11:09:12 AM)
 * @param code java.lang.String
 * @param saleamt int
 * @param exemptamt int
 * @exception java.lang.Exception Invalid tax code.
 */
public TaxType addSaleAmt(
    String code,
    int saleamt,
    int exemptamt) throws java.lang.Exception {
	
	TaxType taxType = null;
	
	if (code == null || code.compareTo("   ") <= 0){
            return taxType;
	}
        
	// Throw exception if tax code not found
	if (!getTaxCodes().containsKey(code)){
            throw new Exception("Undefined tax code: "+code);
	}
	
	taxType = new TaxType();
        
	// accumulate taxable sale amounts
	TaxType mytype = (TaxType)getTaxCodes().get(code);
	taxType.setArticleRate(mytype.getArticleRate());
	taxType.setLocalRate(mytype.getLocalRate());
	taxType.setStateRate(mytype.getStateRate());
	
	// add in the taxable amount for local tax
	// find out if the local sales tax should not calculate tax exemptions
	if (mytype.getLocalTaxNotExempt() == 'Y'){
		//	if the item (with exemptions not figured in) costs more than the local tax's upper limit
		if (saleamt > mytype.getLocalLimit()){
			// add the local tax's upper limit
			mytype.setLocalSaleAmount( mytype.getLocalSaleAmount() + mytype.getLocalLimit());
			taxType.setLocalSaleAmount(mytype.getLocalLimit());
		}
		else{
			// otherwise add the item's cost without exemptions
			mytype.setLocalSaleAmount( mytype.getLocalSaleAmount() + saleamt);
			taxType.setLocalSaleAmount(saleamt);
		}	
	}
	else { // the local sales tax does calculate exemptions
		// if the item (with exemptions figured in) costs more than the local tax's upper limit
		if ( (mytype.getLocalLimit() > 0) && ((saleamt - exemptamt) > mytype.getLocalLimit()) ){
			// add the local tax's upper limit
			mytype.setLocalSaleAmount( mytype.getLocalSaleAmount() + mytype.getLocalLimit());
			taxType.setLocalSaleAmount(mytype.getLocalLimit());
		}
		else{
			// otherwise add the item's cost with exemptions
			mytype.setLocalSaleAmount( mytype.getLocalSaleAmount() + saleamt - exemptamt);
			taxType.setLocalSaleAmount(saleamt - exemptamt);
		}
	}
	// add in the taxable amount for state tax
	// currently we are not calculating a state tax upperlimit
	mytype.setStateSaleAmount( mytype.getStateSaleAmount() + saleamt - exemptamt);
	taxType.setStateSaleAmount(saleamt - exemptamt);
	// add in the taxable amount for article tax
	// the article tax is only taxable from a lower limit to an upper limit
	// items costing less than the lower limit are not counted
	// items costing in between are taxable by the item's cost minus the lower limit
	// items costing more are taxable only up to the maximum amount which is the upper limit minus the lower limit
	// if the item (even with its exemptions) costs more than the article tax's upper limit
	
	if (saleamt - exemptamt > mytype.getArticleUpperLimit()){
		// add in the maximum amount
		mytype.setArticleSaleAmount( mytype.getArticleSaleAmount() + mytype.getArticleUpperLimit() - mytype.getArticleLowerLimit());
		taxType.setArticleSaleAmount(mytype.getArticleUpperLimit() - mytype.getArticleLowerLimit());
	}
	// if it (again with exemptions) costs less than the upper tax limit, but more than the lower tax limit
	else if (saleamt - exemptamt > mytype.getArticleLowerLimit()){
		// calculate the amount of tax to be added by subtracting the lower limit from the saleamt
		mytype.setArticleSaleAmount( mytype.getArticleSaleAmount() + saleamt - mytype.getArticleLowerLimit() - exemptamt); 
		taxType.setArticleSaleAmount(saleamt - mytype.getArticleLowerLimit() - exemptamt); 
	}

//	int tmpArticle = (saleamt - exemptamt) - mytype.getArticleLowerLimit();
//	if (tmpArticle <= 0 ) {
//		mytype.setArticleSaleAmount( mytype.getArticleSaleAmount() + 0);
//		taxType.setArticleSaleAmount( 0);
//	}
//	else if (tmpArticle >= mytype.getArticleUpperLimit()) {
//		mytype.setArticleSaleAmount( mytype.getArticleSaleAmount() + mytype.getArticleUpperLimit());
//		taxType.setArticleSaleAmount( mytype.getArticleUpperLimit());
//	} else {
//		mytype.setArticleSaleAmount( mytype.getArticleSaleAmount() + tmpArticle);
//		taxType.setArticleSaleAmount(tmpArticle);
//	}
	
	
	return taxType;
}
/**
 * Get the state sales tax price list item and add it to the charge collection.
 * Creation date: (3/3/2003 1:18:06 PM)
 * @return com.aldorsolutions.webfdms.beans.DbChargeItem
 */
public FinancialInformationLineItem addStateTaxCharge(java.util.Map chargeSet, DbUser user, String version, int vitalsid) 
	throws PersistenceException {
	DbChargeItem taxCharge = null;
	FinancialInformationLineItem collectionItem = null;
   	DatabaseTransaction t =null;
	DbPriceList dbPriceList[] =null;
	int	uniqueSeqNo = 0;
	
	try {

		// Get price list for this user/version and look for State sales tax line
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
		dbPriceList = plm.getPriceListForVersion(t, version, user.getRegion()); 
		
		DbLocaleConfig[] configs = FdmsDb.getInstance().getLocaleConfigForLocale(t, user.getRegion());
		int orderByContLine  = FdmsDb.getInstance().getLocaleConfigValueForLocale(t, configs, user.getRegion(), 
				DbLocaleConfigType.CONFIG_ORDER_BY_CONT_LINE_ON_FINANCIAL_PAGE);
		
		for(int i=0 ; i < dbPriceList.length ; i++)	{
			if (dbPriceList[i].getActive()=='Y' 
				&& dbPriceList[i].getContrLine() == STATE_TAX_ID ){
				// Got State sales tax line
				// now create a new DbCharge and add it to the collection
				taxCharge = new com.aldorsolutions.webfdms.beans.DbChargeItem( vitalsid, dbPriceList[i].getInvoiceSeqNo(), 0, 
						(int)dbPriceList[i].getContrLine(), dbPriceList[i].getContrDescr(),(int)0,dbPriceList[i].getGlAcctNo(),(int)0,
						"",'S',"",dbPriceList[i].getCategory(),(int)0,(int)0,dbPriceList[i].getRecID(),false, (int) 0);
				// get valid sequence number
				uniqueSeqNo = SessionHelpers.calculateSequenceNumber(chargeSet, taxCharge);
				taxCharge.setSequenceNumber(uniqueSeqNo);
				collectionItem = new FinancialInformationLineItem(taxCharge);
				collectionItem.setModifiedItem(true); // modified this session
				collectionItem.setNewItem(true); // added this session
				
				 if (orderByContLine == 1){
						chargeSet.put(new Integer(collectionItem.getItemTypeCode()+""+uniqueSeqNo), collectionItem);
					}else {
						chargeSet.put(new Integer(uniqueSeqNo), collectionItem);
					}
				
				
				break; // exit FOR loop
			}
		}
	} finally {
		if ( t != null ) {
			t.closeConnection();
			t = null;
		}
	}
	
	return collectionItem;
}
/**
 * Sum the taxes from our collection and return the amount.
 * Creation date: (3/3/2003 11:23:43 AM)
 * Calculate's an upper tax limit
 * @return int
 */
public int calculateLocalSalesTax() {
	int totalTax = 0;
	TaxType atype = null;
	
	java.util.Iterator myIterator = getTaxCodes().values().iterator();
	while (myIterator.hasNext()){
		atype = (TaxType)myIterator.next();
		totalTax += (int)((atype.getLocalRate() * atype.getLocalSaleAmount()/100.0)+0.5);
	}

	return totalTax;
}

/**
 * Sum the taxes from our collection and return the amount.
 * Creation date: (3/3/2003 11:23:43 AM)
 * @return int
 */
//public int calculateStateSalesTax() {
//	int totalTax = 0;
//	TaxType atype = null;
//	
//	java.util.Iterator myIterator = getTaxCodes().values().iterator();
//	while (myIterator.hasNext()){
//		atype = (TaxType)myIterator.next();
//		totalTax += (int)((atype.getStateRate() * atype.getStateSaleAmount()/100.0)+0.5);
//	}
//	
//	return totalTax;
//}

public int calculateStateSalesTax() {
	int totalTax = 0;
	TaxType atype = null;
	
	java.util.Iterator myIterator = getTaxCodes().values().iterator();
	while (myIterator.hasNext()){
		atype = (TaxType)myIterator.next();
		totalTax += (int)((atype.getStateRate() * atype.getStateSaleAmount()/100.0)+0.5);
	}
	
	return totalTax;
}
/**
 * Sum the taxes from our collection and return the amount.
 * Creation date: (7/12/2005 6:52:13 PM)
 * Calculate's an upper and lower tax limit
 * @return int
 */
public int calculateArticleSalesTax() {
	int totalTax = 0;
	TaxType atype = null;
	
	java.util.Iterator myIterator = getTaxCodes().values().iterator();
	while (myIterator.hasNext()){
		atype = (TaxType)myIterator.next();
		totalTax += (int)((atype.getArticleRate() * atype.getArticleSaleAmount()/100.0)+0.5);
	}
	return totalTax;
}
/**
 * Get the state sales tax price list item.
 * Creation date: (3/3/2003 1:18:06 PM)
 * @return com.aldorsolutions.webfdms.beans.DbPriceList
 */
public DbPriceList getLocalTaxPriceListItem(DbUser user, String version) 
	throws PersistenceException {
   	DatabaseTransaction t =null;
	DbPriceList dbPriceList[] =null;
	
	try {

		// Get price list for this user/version and look for Local sales tax line
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
		dbPriceList = plm.getPriceListForVersion(t, version, user.getRegion()); 
		for(int i=0 ; i < dbPriceList.length ; i++)	{
			if (dbPriceList[i].getActive()=='Y' 
				&& dbPriceList[i].getContrLine() == LOCAL_TAX_ID ){
				// Got State sales tax line
				return dbPriceList[i];
			}
		}
	} finally {
		if ( t != null ) {
			t.closeConnection();
			t = null;
		}
	}
	
	return null;
}
/**
 * Get the state sales tax price list item.
 * Creation date: (3/3/2003 1:18:06 PM)
 * @return com.aldorsolutions.webfdms.beans.DbPriceList
 */
public DbPriceList getArticleTaxPriceListItem(DbUser user, String version) 
	throws PersistenceException {
   	DatabaseTransaction t =null;
	DbPriceList dbPriceList[] =null;
	
	try {

		// Get price list for this user/version and look for Article sales tax line
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
		dbPriceList = plm.getPriceListForVersion(t, version, user.getRegion()); 
		for(int i=0 ; i < dbPriceList.length ; i++)	{
			if (dbPriceList[i].getActive()=='Y' 
				&& dbPriceList[i].getContrLine() == ARTICLE_TAX_ID ){
				// Got State sales tax line
				return dbPriceList[i];
			}
		}
		
	} finally {
		if ( t != null ) {
			t.closeConnection();
			t = null;
		}
	}
	
	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:12:19 PM)
 * @return com.aldorsolutions.webfdms.beans.DbUser
 */
public DbUser getOurUser() {
	return ourUser;
}
/**
 * Get the state sales tax price list item.
 * Creation date: (3/3/2003 1:18:06 PM)
 * @return com.aldorsolutions.webfdms.beans.DbPriceList
 */
public DbPriceList getStateTaxPriceListItem(DbUser user, String version) 
	throws PersistenceException {
   	DatabaseTransaction t =null;
	DbPriceList dbPriceList[] =null;
	
	try {

		// Get price list for this user/version and look for State sales tax line
		t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
		dbPriceList = plm.getPriceListForVersion(t, version, user.getRegion()); 
		for(int i=0 ; i < dbPriceList.length ; i++)	{
			if (dbPriceList[i].getActive()=='Y' 
				&& dbPriceList[i].getContrLine() == STATE_TAX_ID ){
				// Got State sales tax line
				return dbPriceList[i];
			}
		}
		
	} finally {
		if ( t != null ) {
			t.closeConnection();
			t = null;
		}
	}
	
	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:20:39 PM)
 * @return java.util.Map
 */
public java.util.Map getTaxCodes() {
	return taxCodes;
}
/**
 * Construct the collection of tax codes for this user
 * Creation date: (2/28/2003 4:13:16 PM)
 */
public void initializeCollection() {
	DatabaseTransaction t = null;
		DbSpeedData[] codes = null;
		double localrate = 0;
		double staterate = 0;
		double articlerate = 0;
		int locallimit = 0;
		int statelimit = 0;
		int articlelowerlimit = 0;
		int articleupperlimit = 0;
	
	// create empty collection for our tax data
	setTaxCodes(new HashMap());
        
	// Access database to get default list of tax methods        
	try {
            t = (DatabaseTransaction)DatabaseTransaction.getTransaction(getOurUser());
            // get array of tax codes from DbSpeedData
            codes = FdmsDb.getInstance().getSpeedData(getOurUser().getDbLookup(), getOurUser().getRegion(), SalesTaxes.CATEGORY_NAME);
            
            for (int i=0; i<codes.length; i++){
                localrate = FormatNumber.parseDouble(CsvTable.getField(codes[i].getData(), 3));
                staterate = FormatNumber.parseDouble(CsvTable.getField(codes[i].getData(), 4));
                articlerate = FormatNumber.parseDouble(CsvTable.getField(codes[i].getData(), 7));
                locallimit = FormatNumber.round(FormatNumber.parseDouble(CsvTable.getField(codes[i].getData(), 5)));
                statelimit = FormatNumber.round(FormatNumber.parseDouble(CsvTable.getField(codes[i].getData(), 6)));
                articlelowerlimit = FormatNumber.round(FormatNumber.parseDouble(CsvTable.getField(codes[i].getData(), 8)));
                articleupperlimit = FormatNumber.round(FormatNumber.parseDouble(CsvTable.getField(codes[i].getData(), 9)));
                String localtaxnotexempt = CsvTable.getField(codes[i].getData(), 10);
                String code = CsvTable.getField(codes[i].getData(), 1);
                String descr = CsvTable.getField(codes[i].getData(), 2);
                getTaxCodes().put(code, new TaxType(code, descr, localrate, staterate, articlerate, locallimit, statelimit, articlelowerlimit, articleupperlimit, localtaxnotexempt) );
            }
            
            logger.debug("Sales Taxes parsed: "+staterate);
            logger.debug("localrate Taxes parsed: "+localrate);
            logger.debug("articlerate Taxes parsed: "+articlerate);
            
            
	} catch(PersistenceException pe) { 
		logger.error("Persistence Exception in SalesTaxes.Initialize. ", pe);
	} finally {
		if ( t != null ) {
			t.closeConnection();
			t = null;
		}
	}
	
}

	public int getItemTaxAmount() {
		int taxAmount = 0;
		
		try {
			
		} catch (Exception e) {
			logger.error("Exception in getItemTaxAmount() : ", e);
		}
		
		return taxAmount;
	}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:12:19 PM)
 * @param newOurUser com.aldorsolutions.webfdms.beans.DbUser
 */
public void setOurUser(DbUser newOurUser) {
	ourUser = newOurUser;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:20:39 PM)
 * @param newTaxCodes java.util.Map
 */
public void setTaxCodes(java.util.Map newTaxCodes) {
	taxCodes = newTaxCodes;
}

	public int calculateItemTaxes(TaxType taxType) {
		int totalTax = 0;
		
		if (taxType != null) {
			try {				
				totalTax += (int)((taxType.getLocalRate() * taxType.getLocalSaleAmount()/100.0)+0.5);
				totalTax += (int)((taxType.getStateRate() * taxType.getStateSaleAmount()/100.0)+0.5);
				totalTax += (int)((taxType.getArticleRate() * taxType.getArticleSaleAmount()/100.0)+0.5);
			} catch (Exception e) {
				logger.error("Exception in calculateItemTaxes() : ", e);
			}
		}
		
		return totalTax;
	}
}