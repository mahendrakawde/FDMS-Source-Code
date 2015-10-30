package com.aldorsolutions.webfdms.beans;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;

import com.aldorassist.webfdms.model.InvoiceDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApMasterDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.DateUtility;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * DbApMaster - This class represents the information
 * for one AP check.
 * toBePrinted property is set to "V" to indicate a voided check.
 * TranType property: "A" for expense account distribution.
 * Creation date: (4/30/2002 2:42:35 PM) modify 030309
 * @author: 
 */
public class DbApMaster extends Persistent
{
	static private final DbApMasterPeer peer = new DbApMasterPeer();

	private int invoiceID;
	private int vendorID;
	private int locationID;
	private int userID;
	private String invoiceNumber;
	private Date invoiceDate;
	private Date dueDate;
	private String memo;
	private String handwritten;
	private String toBePrinted;
	private int discountAmount;
	private long checkNumber;
	private int balance;
	private int localeID;
	private Date checkDate;
	private DatabaseTransaction trans;
	private int invoiceTotal;
	private String voidedCode;
	private int vitalsID;
	private boolean check1099;
	private int check1099Amount;
	private long approvalStatus;
	private long approvedBy;
	private Timestamp approvedTmstmp;
	private String vendorName;
	private String voidedComment;	
	




	public String getVoidedComment() {
		return voidedComment;
	}



	public void setVoidedComment(String voidedComment) {
		this.voidedComment = voidedComment;
	}



	public String getVendorName() {
		return vendorName;
	}



	public void setVendorName(String vendorName) {
		modify();
		this.vendorName = vendorName;
	}



public double calculateDiscountAmount(InvoiceDTO invoice, Date startDate) {
  	double discountAmount = 0.0;
  	Date currDate = null;
  	
	  if ( invoice.isDiscountFlag() ) {
	      Date discDate = invoice.getDiscountDueDate();
	      
	      if (startDate == null) {
	      	currDate = DateUtility.getCurrentDate();
	      } else {
	      	currDate = startDate;
	      }
	      currDate = FormatDate.addToDate(currDate, -1, 0);
	      	// If so the current date has to be before the discount date for the 
	      	// discount to apply
 				if ( discDate != null && currDate.before(discDate) ) {
						// Set the discount date
					discountAmount = invoice.getDiscountAmount();
				}
// use this discount if they don't care the discount due date.				
//	      discountAmount = invoice.getDiscountAmount();
	  }
	  
	  return discountAmount;
	}
	
	
	
	/**
	 * Add an expense account distribution for this check
	 * Creation date: (5/2/2002 4:23:08 PM)
	 * @param apAccountID int
	 * @param amount int
	 * @param memo String
	 * @return boolean  TRUE if successful
	 */
	public void addDistribution(int apAccountID, int amount, String memo, String detailType) 
	throws PersistenceException
	{
		if (trans==null){
			//AppLog.error("DbApMaster::addDistributions transaction is null, cannot proceed.");
			throw new PersistenceException("Transaction no longer valid.");
		}
		if (getId() < 1){
			//AppLog.error("DbApMaster::addDistributions Cannot add distributions until master is inserted.");
			throw new PersistenceException("Cannot add distributions until DbApMaster is inserted.");
		}
		
		DbApDistribution expense = new DbApDistribution();
		expense.setNew();
		expense.setMasterID(getId());
		expense.setAccountID(apAccountID);
		expense.setAmount(amount);
		expense.setMemo(memo);
		expense.setTranType("A");
		trans.addPersistent(expense);
		
			// Record the DistributionHistory as a line item transaction.  We are not going to 
			// record the discount because it will apply to the entire invoice.
    DbApAccount account = FdmsDb.getInstance().getApAccount(trans, apAccountID);
		DbApDistributionHistory expenseHistory = new DbApDistributionHistory();

		expenseHistory.setNew();
		expenseHistory.setApMasterID(getId());
		expenseHistory.setApAccountNumber(account.getAccountNumber());
		expenseHistory.setAmount(amount);
		expenseHistory.setDiscountAmount(0);
		expenseHistory.setType(detailType);
		expenseHistory.setMemo(memo);
		trans.addPersistent(expenseHistory);

		setInvoiceTotal( getInvoiceTotal() + amount );
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:07:35 PM)
	 * @return int
	 */
	public int getBalance() {
		return balance;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (5/2/2002 12:03:26 PM)
	 * @return Date
	 */
	public Date getCheckDate() {
		return checkDate;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:07:26 PM)
	 * @return int
	 */
	public long getCheckNumber() {
		return checkNumber;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:06:55 PM)
	 * @return int
	 */
	public int getDiscountAmount() {
		return discountAmount;
		}
	
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:05:46 PM)
	 * @return Date
	 */
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:06:10 PM)
	 * @return String
	 */
	public String getHandwritten() {
		return handwritten;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:05:27 PM)
	 * @return Date
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 2:57:55 PM)
	 * @return String
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (5/8/2002 12:05:23 PM)
	 * @return int
	 */
	public int getInvoiceTotal() {
		return invoiceTotal;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:40:11 PM)
	 * @return int
	 */
	public int getLocaleID() {
		return localeID;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 2:57:23 PM)
	 * @return int
	 */
	public int getLocationID() {
		return locationID;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:05:58 PM)
	 * @return String
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * getPersistentPeer method comment.
	 */
	protected PersistentPeer getPersistentPeer() {
		return peer;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:06:33 PM)
	 * @return String
	 */
	public String getToBePrinted() {
		return toBePrinted;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (5/2/2002 4:33:29 PM)
	 * @return DatabaseTransaction
	 */
	DatabaseTransaction getTrans() {
		return trans;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 2:57:34 PM)
	 * @return int
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 2:57:09 PM)
	 * @return int
	 */
	public int getVendorID() {
		return vendorID;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (2/13/2003 2:49:52 PM)
	 * @return String
	 */
	public String getVoidedCode() {
		return voidedCode;
	}
	/**
	 * isLocked method comment.
	 */
	public boolean isLocked() {
		return false;
	}
		/**
		 * Override the remove() method since we are not allowing
		 * physical removal.
		 * Instead we code the check as "voided"
		 */
		public synchronized void remove(){
			setToBePrinted("V");
			setBalance(0);
			setApprovalStatus(ApMasterDTO.APPROVAL_STATUS_REJECTED);
//			setInvoiceTotal(0);
			setVoidedCode("V");
			// TODO: add adjusting transactions for each expense account distribution
		}
	/**
	 * Move data from hashtable and copy into class variables
	 * Peer object restores from database to hashtable.
	 */
	public void restore(Transaction t, Hashtable data) throws PersistenceException {
		setId(			FormatNumber.parseInteger(data.get(DbApMasterPeer.IDENTITY).toString()));
		invoiceID   = FormatNumber.parseInteger(data.get(DbApMasterPeer.INVOICEID).toString());
		vendorID		= FormatNumber.parseInteger(data.get(DbApMasterPeer.VENDORID).toString());
		locationID		= FormatNumber.parseInteger(data.get(DbApMasterPeer.LOCATIONID).toString());
		localeID		= FormatNumber.parseInteger(data.get(DbApMasterPeer.LOCALEID).toString());
		userID			= FormatNumber.parseInteger(data.get(DbApMasterPeer.USERID).toString());
		invoiceNumber	= data.get(DbApMasterPeer.INVOICENO).toString();
		try {	invoiceDate		= (Date)data.get(DbApMasterPeer.INVOICEDATE);}
		catch (ClassCastException e){ invoiceDate = null;}
		try {dueDate			= (Date)data.get(DbApMasterPeer.DUEDATE);}
		catch (ClassCastException e){ dueDate = null;}
		memo			= data.get(DbApMasterPeer.MEMO).toString();
		handwritten		= data.get(DbApMasterPeer.HANDWRITTEN).toString();
		toBePrinted		= data.get(DbApMasterPeer.TOBEPRINTED).toString();
		discountAmount	= FormatNumber.parseInteger(data.get(DbApMasterPeer.DISCOUNTAMT).toString());
		checkNumber		= FormatNumber.parseLong(data.get(DbApMasterPeer.CHECKNUMBER).toString());
		balance			= FormatNumber.parseInteger(data.get(DbApMasterPeer.BALANCE).toString());
		try {checkDate		= (Date)data.get(DbApMasterPeer.CHECKDATE);}
		catch (ClassCastException e){ checkDate = null;}
		invoiceTotal	= FormatNumber.parseInteger(data.get(DbApMasterPeer.INVOICETOTAL).toString());
		voidedCode		= data.get(DbApMasterPeer.VOIDEDCODE).toString();
		vitalsID		= FormatNumber.parseInteger(data.get(DbApMasterPeer.ASSOCIATEDVITALSID).toString());
		check1099		= (FormatNumber.parseInteger(data.get(DbApMasterPeer.CHECK1099).toString()) == 1);
		check1099Amount	= FormatNumber.parseInteger(data.get(DbApMasterPeer.CHECK1099AMOUNT).toString());
		approvalStatus	= FormatNumber.parseInteger(data.get(DbApMasterPeer.APPROVALSTATUS).toString());
		approvedBy		= FormatNumber.parseInteger(data.get(DbApMasterPeer.APPROVEDBY).toString());
		approvedTmstmp  = FormatDate.parseTimestamp(data.get(DbApMasterPeer.APPROVEDTMSTMP));
		vendorName			= data.get(DbApMasterPeer.VENDORNAME).toString();
		voidedComment			= data.get(DbApMasterPeer.VOIDEDCOMMENT).toString();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:07:35 PM)
	 * @param newBalance int
	 */
	public void setBalance(int newBalance) {
		balance = newBalance;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (5/2/2002 12:03:26 PM)
	 * @param newCheckDate Date
	 */
	public void setCheckDate(Date newCheckDate) {
		checkDate = newCheckDate;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:07:26 PM)
	 * @param newCheckNumber int
	 */
	public void setCheckNumber(long newCheckNumber) {
		checkNumber = newCheckNumber;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:06:55 PM)
	 * @param newDiscountAmount int
	 */
	public void setDiscountAmount(int newDiscountAmount) {
		discountAmount = newDiscountAmount;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:05:46 PM)
	 * @param newDueDate Date
	 */
	public void setDueDate(Date newDueDate) {
		dueDate = newDueDate;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:06:10 PM)
	 * @param newHandwritten String
	 */
	public void setHandwritten(String newHandwritten) {
		handwritten = newHandwritten;
		modify();
	}
	/**
	 * Get the ID key for this object from the hashtable.
	 * DbUser objects can be accessed by "Name"
	 * So, first see if "Name" is being used for restoring
	 * or if ID# is being used.
	 */
	public void setId(Hashtable h) {
		//if (h.containsKey(peer.NAME)){
		//	getPersistentPeer().restore(this, t);
		//}
		setId(((Integer)h.get(DbApMasterPeer.IDENTITY)).intValue());
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:05:27 PM)
	 * @param newInvoiceDate Date
	 */
	public void setInvoiceDate(Date newInvoiceDate) {
		invoiceDate = newInvoiceDate;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 2:57:55 PM)
	 * @param newInvoiceNumber String
	 */
	public void setInvoiceNumber(String newInvoiceNumber) {
		invoiceNumber = newInvoiceNumber;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (5/8/2002 12:05:23 PM)
	 * @param newInvoiceTotal int
	 */
	public void setInvoiceTotal(int newInvoiceTotal) {
		invoiceTotal = newInvoiceTotal;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:40:11 PM)
	 * @param newLocaleID int
	 */
	public void setLocaleID(int newLocaleID) {
		localeID = newLocaleID;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 2:57:23 PM)
	 * @param newLocationID int
	 */
	public void setLocationID(int newLocationID) {
		locationID = newLocationID;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:05:58 PM)
	 * @param newMemo String
	 */
	public void setMemo(String newMemo) {
		memo = newMemo;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 3:06:33 PM)
	 * @param newToBePrinted String
	 */
	public void setToBePrinted(String newToBePrinted) {
		toBePrinted = newToBePrinted;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (5/2/2002 4:33:29 PM)
	 * @param newTrans DatabaseTransaction
	 */
	void setTrans(DatabaseTransaction newTrans) {
		trans = newTrans;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 2:57:34 PM)
	 * @param newUserID int
	 */
	public void setUserID(int newUserID) {
		userID = newUserID;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/30/2002 2:57:09 PM)
	 * @param newVendorID int
	 */
	public void setVendorID(int newVendorID) {
		vendorID = newVendorID;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (2/13/2003 2:49:52 PM)
	 * @param newVoidedCode String
	 */
	public void setVoidedCode(String newVoidedCode) {
		voidedCode = newVoidedCode;
		modify();
	}
	/**
	 * @return the check1099
	 */
	public boolean isCheck1099() {
		return check1099;
	}
	/**
	 * @return the check1099Amount
	 */
	public int getCheck1099Amount() {
		return check1099Amount;
	}
	/**
	 * @return the vitalsID
	 */
	public int getVitalsID() {
		return vitalsID;
	}
	/**
	 * @param check1099 the check1099 to set
	 */
	public void setCheck1099(boolean check1099) {
		this.check1099 = check1099;
		modify();
	}
	/**
	 * @param check1099Amount the check1099Amount to set
	 */
	public void setCheck1099Amount(int check1099Amount) {
		this.check1099Amount = check1099Amount;
		modify();
	}
	/**
	 * @param vitalsID the vitalsID to set
	 */
	public void setVitalsID(int vitalsID) {
		this.vitalsID = vitalsID;
		modify();
	}
	/**
	 * @return the approvalStatus
	 */
	public long getApprovalStatus() {
		return approvalStatus;
	}
	/**
	 * @return the approvedBy
	 */
	public long getApprovedBy() {
		return approvedBy;
	}
	/**
	 * @return the approvedTmstmp
	 */
	public Timestamp getApprovedTmstmp() {
		return approvedTmstmp;
	}
	/**
	 * @param approvalStatus the approvalStatus to set
	 */
	public void setApprovalStatus(long approvalStatus) {
	modify();
	this.approvalStatus = approvalStatus;
	}
	/**
	 * @param approvedBy the approvedBy to set
	 */
	public void setApprovedBy(long approvedBy) {
	modify();
		this.approvedBy = approvedBy;
	}
	/**
	 * @param approvedTmstmp the approvedTmstmp to set
	 */
	public void setApprovedTmstmp(Timestamp approvedTmstmp) {
	modify();
		this.approvedTmstmp = approvedTmstmp;
	}
	/**
	 * @return the invoiceID
	 */
	public int getInvoiceID() {
	return invoiceID;
	}
	/**
	 * @param invoiceID the invoiceID to set
	 */
	public void setInvoiceID(int invoiceID) {
	modify();
	this.invoiceID = invoiceID;
	}
	
}
