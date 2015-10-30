package com.aldorsolutions.webfdms.beans;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * DbPnContracts - This class represents the summary information
 * for one Pre-need Contract.
 * Creation date: (2/03/2003 2:42:35 PM)
 * @author: 
 */
public class DbPnContract extends com.aldorsolutions.webfdms.database.Persistent
{
    
        private Logger logger = Logger.getLogger(DbPnContract.class.getName());
	static private final DbPnContractPeer peer = new DbPnContractPeer();
	public static final int TYPE_GUAR_TAXABLE_BOND = 1;
	public static final int TYPE_GUAR_TAXABLE_STOCK = 2;
	public static final int TYPE_NONGUAR_CD = 3;
	public static final int TYPE_GUAR_TAXFREE_BOND = 4;
	public static final int TYPE_GUAR_TAXPAID_BOND = 5;
	public static final int TYPE_GUAR_TAXPAID_STOCK= 6;

	public static final String CANCEL_BEFORE_DEATH = "B";
	public static final String CANCEL_AFTER_DEATH = "A";
	public static final String CANCEL_PARTIAL_WITHDRAWAL = "P";

	public static final int CONTRACT_STATUS_ACTIVE = 10;
	public static final int CONTRACT_STATUS_CANCELLED = 11;
	public static final int CONTRACT_STATUS_SERVICED = 12;

	private int contractID;
	private int contractNumber;
	private String customContractNumber;
	private String certificateNumber;
	private int contractType;
	private java.lang.String installment;
	private java.lang.String certifiedIrrevocable;
	private java.lang.String taxpayer;
	private java.lang.String send1099To;
	private int bankID;
	private java.util.Date contractDate;
	private double commissionRate;
	private double discountRate;
	private double refundRate;
	private int packageID;
	private int totalCharges;
	private int downpayment;
	private int contractNumberPayments;
	private java.util.Date dateFirstPmtDue;
	private double interestRate;
	private int totalFinanceCharge;
	private int monthlyPmtAmount;
	private int numberPmtsMade;
	private int totalPaidToDate;
	private java.util.Date lastPmtDate;
	private int lastPmtAmount;
	private int lastPmtCommAmount;
	private java.lang.String lastPmtMemo;
	private java.lang.String lastPmtFundsForCode;
	private java.lang.String lastPmtCommBox;
	private java.lang.String fulfillmentContactName;
	private java.lang.String fulfillmentContactPhone;
	private java.util.Date cancellationDate;
	private java.lang.String cancellationType;
	private int partialWithdrawalAmount;
	private java.lang.String cancellationFundsName;
	private java.lang.String cancellationFundsAddr;
	private java.lang.String cancellationFundsCity;
	private java.lang.String cancellationFundsState;
	private java.lang.String cancellationFundsZip;
	private java.lang.String notes;
	private int vitalsID;
	private java.lang.String annualStmt;
	private java.lang.String priceListVersion;
	private java.lang.String cancellationAcknowledge;
	private int status;
/**
 * Add an itemized charge to this contract
 * Creation date: (2/3/2003 4:23:08 PM)
 * @return boolean  TRUE if successful
 */
public void addCharge(DatabaseTransaction trans, int type, String descr, int amount, char spf, int priceID, int invID, char category, int seqno) 
throws PersistenceException
{
	if (trans==null){
		//AppLog.error("DbPnContract::addCharge transaction is null, cannot proceed.");
		throw new PersistenceException("Transaction no longer valid.");
	}
	if (getId() < 1){
		//AppLog.error("DbPnContract::addCharge Cannot add charge until this contract is inserted.");
		throw new PersistenceException("Cannot add charge until this contract is inserted.");
	}
	
	DbPnCharge charge = new DbPnCharge();
	charge.setNew();
	charge.setContractID( getId() );
	charge.setType(	type);
	charge.setDescription( descr);
	charge.setAmount(amount);
	charge.setSpfCode(spf);
	charge.setPriceListId( priceID);
	charge.setInvSeqNo(		invID);
	charge.setItemCategoryType( category);
	charge.setSequenceNumber( seqno);
	trans.addPersistent(charge);
	trans.addPersistent(this);
	setTotalCharges( getTotalCharges() + amount );
}
/**
 * Add a payment to this contract
 * Creation date: (2/3/2003 4:23:08 PM)
 * @return boolean  TRUE if successful
 */
public void addPayment(DatabaseTransaction trans, java.util.Date date, int amount, int commAmount, String memo, String fundCode, String commCode) 
throws PersistenceException
{
	if (trans==null){
		//AppLog.error("DbPnContract::addPayment transaction is null, cannot proceed.");
		throw new PersistenceException("Transaction no longer valid.");
	}
	if (getId() < 1){
		//AppLog.error("DbPnContract::addPayment Cannot add payments until this contract is inserted.");
		throw new PersistenceException("Cannot add payment until this contract is inserted.");
	}
	
	DbPnPayment pmt = new DbPnPayment();
	pmt.setNew();
	pmt.setContractID( getId() );
	pmt.setDate(	date);
	pmt.setAmount(	amount);
	pmt.setCommissionAmount(	commAmount);
	pmt.setMemo(	memo);
	pmt.setFundsForCode(	fundCode);
	pmt.setCommissionSentBox(	commCode);

	trans.addPersistent(pmt);
	trans.addPersistent(this);
	setTotalPaidToDate( getTotalPaidToDate() + amount );
}
/**
 * Insert the method's description here.
 * Creation date: (2/7/2003 2:31:17 PM)
 * @return java.lang.String
 */
public java.lang.String getAnnualStmt() {
	return annualStmt;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:41:26 AM)
 * @return int
 */
public int getBankID() {
	return bankID;
}
	/**
	 * @return Returns the certificateNumber.
	 */
	public String getCertificateNumber() {
		return certificateNumber;
	}
	/**
	 * @param certificateNumber The certificateNumber to set.
	 */
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
/**
 * Insert the method's description here.
 * Creation date: (4/30/2003 4:09:13 PM)
 * @return java.lang.String
 */
public java.lang.String getCancellationAcknowledge() {
	return cancellationAcknowledge;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:48:15 AM)
 * @return java.util.Date
 */
public java.util.Date getCancellationDate() {
	return cancellationDate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:49:15 AM)
 * @return java.lang.String
 */
public java.lang.String getCancellationFundsAddr() {
	return cancellationFundsAddr;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:49:43 AM)
 * @return java.lang.String
 */
public java.lang.String getCancellationFundsCity() {
	return cancellationFundsCity;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:49:02 AM)
 * @return java.lang.String
 */
public java.lang.String getCancellationFundsName() {
	return cancellationFundsName;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:50:09 AM)
 * @return java.lang.String
 */
public java.lang.String getCancellationFundsState() {
	return cancellationFundsState;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:50:23 AM)
 * @return java.lang.String
 */
public java.lang.String getCancellationFundsZip() {
	return cancellationFundsZip;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:48:27 AM)
 * @return java.lang.String
 */
public java.lang.String getCancellationType() {
	return cancellationType;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:40:49 AM)
 * @return java.lang.String
 */
public java.lang.String getCertifiedIrrevocable() {
	return certifiedIrrevocable;
}
/**
 * Retrieve all charges for this contract.
 * Creation date: (2/3/03 2:20:50 PM)
 * @return java.util.HashMap collection of all charges
 * @param vitalsid int Vitals-ID for the case
 */
public java.util.ArrayList getChargeSet(DatabaseTransaction t) 
throws PersistenceException
{
	java.util.ArrayList chargeList = new java.util.ArrayList();
	java.util.Hashtable h = new java.util.Hashtable();

	if (t==null){
		//AppLog.error("DbPnContract::getChargeSet transaction is null, cannot proceed.");
		throw new PersistenceException("Transaction no longer valid.");
	}
	if (getId() < 1){
		//AppLog.error("DbPnContract::getChargeSet Cannot add charge until this contract is inserted.");
		throw new PersistenceException("Cannot add charge until this contract is inserted.");
	}

	try {
		com.aldorsolutions.webfdms.beans.DbPnChargeSet chargeset = new com.aldorsolutions.webfdms.beans.DbPnChargeSet();
		h.put(DbPnChargePeer.CONTRACTID,new Integer(getId()));
		chargeset.restore(t,h);
		com.aldorsolutions.webfdms.database.PersistentI[] obs = chargeset.getPersistents();
		com.aldorsolutions.webfdms.beans.DbPnCharge acharge;
		for(int i=0; i<obs.length; i++) {
			acharge = (com.aldorsolutions.webfdms.beans.DbPnCharge)obs[i];
			chargeList.add(acharge);	
		}
		return chargeList;
	}
	catch (PersistenceException e){
	 	logger.error("DbPnContract.getChargeSet Persistence Exception:", e);
	    return null;
	}
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:42:06 AM)
 * @return double
 */
public double getCommissionRate() {
	return commissionRate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:41:53 AM)
 * @return java.util.Date
 */
public java.util.Date getContractDate() {
	return contractDate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:39:31 AM)
 * @return int
 */
public int getContractID() {
	return contractID;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:39:45 AM)
 * @return int
 */
public int getContractNumber() {
	return contractNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:43:31 AM)
 * @return int
 */
public int getContractNumberPayments() {
	return contractNumberPayments;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:40:04 AM)
 * @return int
 */
public int getContractType() {
	return contractType;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:43:58 AM)
 * @return java.util.Date
 */
public java.util.Date getDateFirstPmtDue() {
	return dateFirstPmtDue;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:42:20 AM)
 * @return double
 */
public double getDiscountRate() {
	return discountRate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:43:03 AM)
 * @return int
 */
public int getDownpayment() {
	return downpayment;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:47:48 AM)
 * @return java.lang.String
 */
public java.lang.String getFulfillmentContactName() {
	return fulfillmentContactName;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:48:02 AM)
 * @return java.lang.String
 */
public java.lang.String getFulfillmentContactPhone() {
	return fulfillmentContactPhone;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:40:28 AM)
 * @return java.lang.String
 */
public java.lang.String getInstallment() {
	return installment;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:44:18 AM)
 * @return double
 */
public double getInterestRate() {
	return interestRate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:46:07 AM)
 * @return int
 */
public int getLastPmtAmount() {
	return lastPmtAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:46:29 AM)
 * @return int
 */
public int getLastPmtCommAmount() {
	return lastPmtCommAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:47:33 AM)
 * @return java.lang.String
 */
public java.lang.String getLastPmtCommBox() {
	return lastPmtCommBox;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:45:53 AM)
 * @return java.util.Date
 */
public java.util.Date getLastPmtDate() {
	return lastPmtDate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:47:15 AM)
 * @return java.lang.String
 */
public java.lang.String getLastPmtFundsForCode() {
	return lastPmtFundsForCode;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:46:52 AM)
 * @return java.lang.String
 */
public java.lang.String getLastPmtMemo() {
	return lastPmtMemo;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:44:47 AM)
 * @return int
 */
public int getMonthlyPmtAmount() {
	return monthlyPmtAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:50:32 AM)
 * @return java.lang.String
 */
public java.lang.String getNotes() {
	return notes;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:45:12 AM)
 * @return int
 */
public int getNumberPmtsMade() {
	return numberPmtsMade;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:42:41 AM)
 * @return int
 */
public int getPackageID() {
	return packageID;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:48:41 AM)
 * @return int
 */
public int getPartialWithdrawalAmount() {
	return partialWithdrawalAmount;
}
/**
 * Retrieve all payments for this contract.
 * Creation date: (2/3/03 2:20:50 PM)
 * @return java.util.HashMap collection of all charges
 * @param vitalsid int Vitals-ID for the case
 */
public java.util.ArrayList getPaymentSet(DatabaseTransaction t) 
throws PersistenceException
{
	java.util.ArrayList pmtList = new java.util.ArrayList();
	java.util.Hashtable h = new java.util.Hashtable();

	if (t==null){
		//AppLog.error("DbPnContract::getPaymentSet transaction is null, cannot proceed.");
		throw new PersistenceException("Transaction no longer valid.");
	}
	if (getId() < 1){
		//AppLog.error("DbPnContract::getPaymentSet Cannot retrieve set until this contract is inserted.");
		throw new PersistenceException("Cannot retrieve set until this contract is inserted.");
	}

	try {
		com.aldorsolutions.webfdms.beans.DbPnPaymentSet pmtset = new com.aldorsolutions.webfdms.beans.DbPnPaymentSet();
		h.put(DbPnPaymentPeer.CONTRACTID,new Integer(getId()));
		pmtset.restore(t,h);
		com.aldorsolutions.webfdms.database.PersistentI[] obs = pmtset.getPersistents();
		com.aldorsolutions.webfdms.beans.DbPnPayment apmt;
		for(int i=0; i<obs.length; i++) {
			apmt = (com.aldorsolutions.webfdms.beans.DbPnPayment)obs[i];
			pmtList.add(apmt);	
		}
		return pmtList;
	}
	catch (PersistenceException e){
	 	logger.error("DbPnContract.getPaymentSet Persistence Exception:", e);
	    return null;
	}
}
/**
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
	return peer;
}
/**
 * Insert the method's description here.
 * Creation date: (2/7/2003 3:21:16 PM)
 * @return java.lang.String
 */
public java.lang.String getPriceListVersion() {
	return priceListVersion;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:42:32 AM)
 * @return double
 */
public double getRefundRate() {
	return refundRate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:41:16 AM)
 * @return java.lang.String
 */
public java.lang.String getSend1099To() {
	return send1099To;
}
/**
 * Status is calculated according to the following rules.
 * "Cancelled"  if a cancellation date is present and not a partial withdrawal.
 * "Serviced" if vitals-pnstatus is set to "serviced". This is done when the user completes 
 *  the "fulfillment" operation a.k.a convert pre-need to at-need.
 * "Active" if none of the above.
 * Creation date: (7/29/2003 9:20:55 AM)
 * @return int
 */
public int getStatus(DatabaseTransaction t) {
	status = CONTRACT_STATUS_ACTIVE;

	// Determine if contract has been cancelled
	if (getCancellationDate()!=null 
		&& getCancellationType().compareTo(CANCEL_PARTIAL_WITHDRAWAL)!= 0){
		status = CONTRACT_STATUS_CANCELLED;
		return status;
	}
	// Determine if contract has been serviced
	DbPreneed dbPreNeed = FdmsDb.getInstance().getPreneed(t, getVitalsID());
	if (dbPreNeed.getStatus().compareTo(DbPreneed.SERVICED)==0){
		status = CONTRACT_STATUS_SERVICED;
		return status;
	}
	return status;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:41:02 AM)
 * @return java.lang.String
 */
public java.lang.String getTaxpayer() {
	return taxpayer;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:42:53 AM)
 * @return int
 */
public int getTotalCharges() {
	return totalCharges;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:44:34 AM)
 * @return int
 */
public int getTotalFinanceCharge() {
	return totalFinanceCharge;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:45:33 AM)
 * @return int
 */
public int getTotalPaidToDate() {
	return totalPaidToDate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 3:31:07 PM)
 * @return int
 */
public int getVitalsID() {
	return vitalsID;
}
/**
 * isLocked method comment.
 */
public boolean isLocked() {
	return false;
}
/**
 * Move data from hashtable and copy into class variables
 * Peer object restores from database to hashtable.
 */
public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
	setId(			FormatNumber.parseInteger(data.get(DbPnContractPeer.IDENTITY).toString()));
	contractID		= FormatNumber.parseInteger(data.get(DbPnContractPeer.IDENTITY).toString());
	vitalsID		= FormatNumber.parseInteger(data.get(DbPnContractPeer.VITALSID).toString());
	contractNumber	= FormatNumber.parseInteger(data.get(DbPnContractPeer.CONTRACTNO).toString());
	customContractNumber = data.get(DbPnContractPeer.CUSTOMCONTRACTNUMBER).toString();
	certificateNumber = data.get(DbPnContractPeer.CERTIFICATENUMBER).toString();
	contractType	= FormatNumber.parseInteger(data.get(DbPnContractPeer.CONTRACTTYPE).toString());
	annualStmt		= data.get(DbPnContractPeer.ANNUALSTMT).toString();
	installment		= data.get(DbPnContractPeer.INSTALLMENT).toString();
	certifiedIrrevocable = data.get(DbPnContractPeer.IRREVOCABLE).toString();
	taxpayer			= data.get(DbPnContractPeer.TAXPAYER).toString();
	send1099To			= data.get(DbPnContractPeer.SEND1099TO).toString();
	bankID				= FormatNumber.parseInteger(data.get(DbPnContractPeer.BANKID).toString());
	try {	contractDate		= (java.util.Date)data.get(DbPnContractPeer.CONTRACTDATE);}
	catch (ClassCastException e){ contractDate = null;}
	commissionRate		= FormatNumber.parseDouble(data.get(DbPnContractPeer.COMMRATE));
	discountRate		= FormatNumber.parseDouble(data.get(DbPnContractPeer.DISCOUNTRATE));
	refundRate			= FormatNumber.parseDouble(data.get(DbPnContractPeer.REFUND));
	packageID			= FormatNumber.parseInteger(data.get(DbPnContractPeer.PACKAGEID).toString());
	totalCharges		= FormatNumber.parseInteger(data.get(DbPnContractPeer.TOTALCHARGES).toString());
	downpayment			= FormatNumber.parseInteger(data.get(DbPnContractPeer.DOWNPAYMENT).toString());
	contractNumberPayments= FormatNumber.parseInteger(data.get(DbPnContractPeer.CONTRNOPMTS).toString());
	try { dateFirstPmtDue			= (java.util.Date)data.get(DbPnContractPeer.DATEFIRSTPMT);}
	catch (ClassCastException e){ dateFirstPmtDue = null;}
	interestRate		= FormatNumber.parseDouble(data.get(DbPnContractPeer.INTERESTRATE));
	totalFinanceCharge	= FormatNumber.parseInteger(data.get(DbPnContractPeer.TOTFINCHARGE).toString());
	monthlyPmtAmount	= FormatNumber.parseInteger(data.get(DbPnContractPeer.MONTHLYPMT).toString());
	numberPmtsMade		= FormatNumber.parseInteger(data.get(DbPnContractPeer.NOPMTSMADE).toString());
	totalPaidToDate		= FormatNumber.parseInteger(data.get(DbPnContractPeer.TOTPAIDTODATE).toString());
	try { lastPmtDate		= (java.util.Date)data.get(DbPnContractPeer.LASTPMTDATE);}
	catch (ClassCastException e){ lastPmtDate = null;}
	lastPmtAmount		= FormatNumber.parseInteger(data.get(DbPnContractPeer.LASTPMTAMT).toString());
	lastPmtCommAmount	= FormatNumber.parseInteger(data.get(DbPnContractPeer.LASTPMTCOMM).toString());
	lastPmtMemo			= data.get(DbPnContractPeer.LASTPMTMEMO).toString();
	lastPmtFundsForCode	= data.get(DbPnContractPeer.LASTPMTFUNDBX).toString();
	lastPmtCommBox		= data.get(DbPnContractPeer.LASTPMTCOMMBX).toString();
	fulfillmentContactName = data.get(DbPnContractPeer.FCONTACTNAME).toString();
	fulfillmentContactPhone = data.get(DbPnContractPeer.FCONTACTPHONE).toString();
	try { cancellationDate			= (java.util.Date)data.get(DbPnContractPeer.CANCELDATE);}
	catch (ClassCastException e){ cancellationDate = null;}
	cancellationType		= data.get(DbPnContractPeer.CANCELTYPE).toString();
	partialWithdrawalAmount	= FormatNumber.parseInteger(data.get(DbPnContractPeer.WITHDRAWALAMT).toString());
	cancellationAcknowledge = data.get(DbPnContractPeer.CANCELACK).toString();
	cancellationFundsName	= data.get(DbPnContractPeer.CANCELNAME).toString();
	cancellationFundsAddr	= data.get(DbPnContractPeer.CANCELADDR).toString();
	cancellationFundsCity	= data.get(DbPnContractPeer.CANCELCITY).toString();
	cancellationFundsState	= data.get(DbPnContractPeer.CANCELSTATE).toString();
	cancellationFundsZip	= data.get(DbPnContractPeer.CANCELZIP).toString();
	notes					= data.get(DbPnContractPeer.NOTES).toString();
	priceListVersion		= data.get(DbPnContractPeer.PRICELISTVER).toString();
}
/**
 * Insert the method's description here.
 * Creation date: (2/7/2003 2:31:17 PM)
 * @param newAnnualStmt java.lang.String
 */
public void setAnnualStmt(java.lang.String newAnnualStmt) {
	annualStmt = newAnnualStmt;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:41:26 AM)
 * @param newBankID int
 */
public void setBankID(int newBankID) {
	bankID = newBankID;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (4/30/2003 4:09:13 PM)
 * @param newCancellationAcknowledge java.lang.String
 */
public void setCancellationAcknowledge(java.lang.String newCancellationAcknowledge) {
	cancellationAcknowledge = newCancellationAcknowledge;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:48:15 AM)
 * @param newCancellationDate java.util.Date
 */
public void setCancellationDate(java.util.Date newCancellationDate) {
	modify();
	cancellationDate = newCancellationDate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:49:15 AM)
 * @param newCancellatinoFundsAddr java.lang.String
 */
public void setCancellationFundsAddr(java.lang.String newCancellatinoFundsAddr) {
	modify();
	cancellationFundsAddr = newCancellatinoFundsAddr;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:49:43 AM)
 * @param newCancellationFundsCity java.lang.String
 */
public void setCancellationFundsCity(java.lang.String newCancellationFundsCity) {
	modify();
	cancellationFundsCity = newCancellationFundsCity;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:49:02 AM)
 * @param newCancellatinFundsName java.lang.String
 */
public void setCancellationFundsName(java.lang.String newCancellatinFundsName) {
	modify();
	cancellationFundsName = newCancellatinFundsName;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:50:09 AM)
 * @param newCancellationFundsState java.lang.String
 */
public void setCancellationFundsState(java.lang.String newCancellationFundsState) {
	modify();
	cancellationFundsState = newCancellationFundsState;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:50:23 AM)
 * @param newCancellationFundsZip java.lang.String
 */
public void setCancellationFundsZip(java.lang.String newCancellationFundsZip) {
	modify();
	cancellationFundsZip = newCancellationFundsZip;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:48:27 AM)
 * @param newCancellationType java.lang.String
 */
public void setCancellationType(java.lang.String newCancellationType) {
	modify();
	cancellationType = newCancellationType;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:40:49 AM)
 * @param newCertifiedIrrevocable java.lang.String
 */
public void setCertifiedIrrevocable(java.lang.String newCertifiedIrrevocable) {
	modify();
	certifiedIrrevocable = newCertifiedIrrevocable;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:42:06 AM)
 * @param newCommissionRate double
 */
public void setCommissionRate(double newCommissionRate) {
	modify();
	commissionRate = newCommissionRate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:41:53 AM)
 * @param newContractDate java.util.Date
 */
public void setContractDate(java.util.Date newContractDate) {
	modify();
	contractDate = newContractDate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:39:31 AM)
 * @param newContractID int
 */
public void setContractID(int newContractID) {
	modify();
	contractID = newContractID;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:39:45 AM)
 * @param newContractNumber int
 */
public void setContractNumber(int newContractNumber) {
	modify();
	contractNumber = newContractNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:43:31 AM)
 * @param newContractNumberPayments int
 */
public void setContractNumberPayments(int newContractNumberPayments) {
	modify();
	contractNumberPayments = newContractNumberPayments;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:40:04 AM)
 * @param newContractType int
 */
public void setContractType(int newContractType) {
	modify();
	contractType = newContractType;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:43:58 AM)
 * @param newDateFirstPmtDue java.util.Date
 */
public void setDateFirstPmtDue(java.util.Date newDateFirstPmtDue) {
	modify();
	dateFirstPmtDue = newDateFirstPmtDue;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:42:20 AM)
 * @param newDiscountRate double
 */
public void setDiscountRate(double newDiscountRate) {
	modify();
	discountRate = newDiscountRate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:43:03 AM)
 * @param newDownpayment int
 */
public void setDownpayment(int newDownpayment) {
	modify();
	downpayment = newDownpayment;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:47:48 AM)
 * @param newFulfillmentContactName java.lang.String
 */
public void setFulfillmentContactName(java.lang.String newFulfillmentContactName) {
	modify();
	fulfillmentContactName = newFulfillmentContactName;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:48:02 AM)
 * @param newFulfillmentContactPhone java.lang.String
 */
public void setFulfillmentContactPhone(java.lang.String newFulfillmentContactPhone) {
	modify();
	fulfillmentContactPhone = newFulfillmentContactPhone;
}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(DbPnContractPeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbPnContractPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:40:28 AM)
 * @param newInstallment java.lang.String
 */
public void setInstallment(java.lang.String newInstallment) {
	modify();
	installment = newInstallment;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:44:18 AM)
 * @param newInterestRate double
 */
public void setInterestRate(double newInterestRate) {
	modify();
	interestRate = newInterestRate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:46:07 AM)
 * @param newLastPmtAmount int
 */
public void setLastPmtAmount(int newLastPmtAmount) {
	modify();
	lastPmtAmount = newLastPmtAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:46:29 AM)
 * @param newLastPmtCommAmount int
 */
public void setLastPmtCommAmount(int newLastPmtCommAmount) {
	modify();
	lastPmtCommAmount = newLastPmtCommAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:47:33 AM)
 * @param newLastPmtCommBox java.lang.String
 */
public void setLastPmtCommBox(java.lang.String newLastPmtCommBox) {
	modify();
	lastPmtCommBox = newLastPmtCommBox;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:45:53 AM)
 * @param newLastPmtDate java.util.Date
 */
public void setLastPmtDate(java.util.Date newLastPmtDate) {
	modify();
	lastPmtDate = newLastPmtDate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:47:15 AM)
 * @param newLastPmtFundsForCode java.lang.String
 */
public void setLastPmtFundsForCode(java.lang.String newLastPmtFundsForCode) {
	modify();
	lastPmtFundsForCode = newLastPmtFundsForCode;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:46:52 AM)
 * @param newLastPmtMemo java.lang.String
 */
public void setLastPmtMemo(java.lang.String newLastPmtMemo) {
	modify();
	lastPmtMemo = newLastPmtMemo;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:44:47 AM)
 * @param newMonthlyPmtAmount int
 */
public void setMonthlyPmtAmount(int newMonthlyPmtAmount) {
	modify();
	monthlyPmtAmount = newMonthlyPmtAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:50:32 AM)
 * @param newNotes java.lang.String
 */
public void setNotes(java.lang.String newNotes) {
	modify();
	notes = newNotes;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:45:12 AM)
 * @param newNumberPmtsMade int
 */
public void setNumberPmtsMade(int newNumberPmtsMade) {
	modify();
	numberPmtsMade = newNumberPmtsMade;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:42:41 AM)
 * @param newPackageID int
 */
public void setPackageID(int newPackageID) {
	modify();
	packageID = newPackageID;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:48:41 AM)
 * @param newPartialWithdrawalAmount int
 */
public void setPartialWithdrawalAmount(int newPartialWithdrawalAmount) {
	modify();
	partialWithdrawalAmount = newPartialWithdrawalAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/7/2003 3:21:16 PM)
 * @param newPriceListVersion java.lang.String
 */
public void setPriceListVersion(java.lang.String newPriceListVersion) {
	priceListVersion = newPriceListVersion;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:42:32 AM)
 * @param newRefundRate double
 */
public void setRefundRate(double newRefundRate) {
	modify();
	refundRate = newRefundRate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:41:16 AM)
 * @param newSend1099To java.lang.String
 */
public void setSend1099To(java.lang.String newSend1099To) {
	modify();
	send1099To = newSend1099To;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:41:02 AM)
 * @param newTaxpayer java.lang.String
 */
public void setTaxpayer(java.lang.String newTaxpayer) {
	taxpayer = newTaxpayer;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:42:53 AM)
 * @param newTotalCharges int
 */
public void setTotalCharges(int newTotalCharges) {
	modify();
	totalCharges = newTotalCharges;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:44:34 AM)
 * @param newTotalFinanceCharge int
 */
public void setTotalFinanceCharge(int newTotalFinanceCharge) {
	modify();
	totalFinanceCharge = newTotalFinanceCharge;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 10:45:33 AM)
 * @param newTotalPaidToDate int
 */
public void setTotalPaidToDate(int newTotalPaidToDate) {
	modify();
	totalPaidToDate = newTotalPaidToDate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 3:31:07 PM)
 * @param newVitalsID int
 */
public void setVitalsID(int newVitalsID) {
	vitalsID = newVitalsID;
	modify();
}
	/**
	 * @return Returns the customContractNumber.
	 */
	public String getCustomContractNumber() {
		return customContractNumber;
	}
	/**
	 * @param customContractNumber The customContractNumber to set.
	 */
	public void setCustomContractNumber(String customContractNumber) {
		this.customContractNumber = customContractNumber;
	}
}
