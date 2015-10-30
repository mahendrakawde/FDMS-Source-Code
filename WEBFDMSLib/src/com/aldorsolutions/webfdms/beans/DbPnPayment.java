package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * This class contains one Pre-Need contract payment.
 * It impliments interfaces allowing this class to be included in collections.
 * Creation date: (1/25/01 3:07:21 PM)
 * @author: R. Davidson
 */
public class DbPnPayment extends com.aldorsolutions.webfdms.database.Persistent 
	implements java.lang.Comparable, java.io.Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 780859691768937962L;

	static private final DbPnPaymentPeer peer = new DbPnPaymentPeer();

	private int contractID;
	private int paymentID;
	private java.util.Date date;
	private int amount;
	private int commissionAmount;
	private java.lang.String memo;
	private java.lang.String fundsForCode;
	private java.lang.String CommissionSentBox;
/**
 * ChargeCollection constructor comment.
 */
public DbPnPayment() {
	super();
	//setNew();
}
/**
 * Order for charges is based on string comparison using toString()
 * Creation date: (2/1/01 2:52:15 PM)
 * @return int negative, zero, positive 
 * @param o1 java.lang.Object
 * @param o2 java.lang.Object
 */
public int compareTo(Object o1) {

	return toString().compareTo(o1.toString());
	
}
/**
 * Compares two objects for equality. Returns a boolean that indicates
 * whether this object is equivalent to the specified object. This method
 * is used when an object is stored in a hashtable.
 * @param obj the Object to compare with
 * @return true if these Objects are equal; false otherwise.
 * @see java.util.Hashtable
 */
public boolean equals(Object obj) {
	// Insert code to compare the receiver and obj here.
	// This implementation forwards the message to super.  You may replace or supplement this.
	// NOTE: obj might be an instance of any class
	return super.equals(obj);
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:31:55 PM)
 * @return int
 */
public int getAmount() {
	return amount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:32:10 PM)
 * @return int
 */
public int getCommissionAmount() {
	return commissionAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:33:07 PM)
 * @return java.lang.String
 */
public java.lang.String getCommissionSentBox() {
	return CommissionSentBox;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 1:49:51 PM)
 * @return int
 */
public int getContractID() {
	return contractID;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:31:37 PM)
 * @return java.util.Date
 */
public java.util.Date getDate() {
	return date;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:32:44 PM)
 * @return java.lang.String
 */
public java.lang.String getFundsForCode() {
	return fundsForCode;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:32:30 PM)
 * @return java.lang.String
 */
public java.lang.String getMemo() {
	return memo;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:31:14 PM)
 * @return int
 */
public int getPaymentID() {
	return paymentID;
}
/**
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
	return peer;
}
/**
 * Generates a hash code for the receiver.
 * This method is supported primarily for
 * hash tables, such as those provided in java.util.
 * @return an integer hash code for the receiver
 * @see java.util.Hashtable
 */
public int hashCode() {
	// Insert code to generate a hash code for the receiver here.
	// This implementation forwards the message to super.  You may replace or supplement this.
	// NOTE: if two objects are equal (equals(Object) returns true) they must have the same hash code
	//return super.hashCode();
	return (1000*getContractID() +  getId());
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
	setId(					FormatNumber.parseInteger(data.get(DbPnPaymentPeer.IDENTITY).toString()));
	contractID				= FormatNumber.parseInteger(data.get(DbPnPaymentPeer.CONTRACTID).toString());
	try {	date		= (java.util.Date)data.get(DbPnPaymentPeer.DATE);}
	catch (ClassCastException e){ date = null;}
	amount					= FormatNumber.parseInteger(data.get(DbPnPaymentPeer.AMOUNT).toString());
	commissionAmount		= FormatNumber.parseInteger(data.get(DbPnPaymentPeer.COMMAMOUNT ) .toString());
	memo					= data.get(DbPnPaymentPeer.MEMO).toString();
	fundsForCode			= data.get(DbPnPaymentPeer.FUNDSCODE).toString();
	CommissionSentBox		= data.get(DbPnPaymentPeer.COMMCODE).toString();
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:31:55 PM)
 * @param newAmount int
 */
public void setAmount(int newAmount) {
	amount = newAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:32:10 PM)
 * @param newCommissionAmount int
 */
public void setCommissionAmount(int newCommissionAmount) {
	commissionAmount = newCommissionAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:33:07 PM)
 * @param newCommissionSentBox java.lang.String
 */
public void setCommissionSentBox(java.lang.String newCommissionSentBox) {
	CommissionSentBox = newCommissionSentBox;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 1:49:51 PM)
 * @param newContractID int
 */
public void setContractID(int newContractID) {
	contractID = newContractID;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:31:37 PM)
 * @param newDate java.util.Date
 */
public void setDate(java.util.Date newDate) {
	date = newDate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:32:44 PM)
 * @param newFundsForCode java.lang.String
 */
public void setFundsForCode(java.lang.String newFundsForCode) {
	fundsForCode = newFundsForCode;
}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(DbPnPaymentPeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbPnPaymentPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:32:30 PM)
 * @param newMemo java.lang.String
 */
public void setMemo(java.lang.String newMemo) {
	memo = newMemo;
}
/**
 * Insert the method's description here.
 * Creation date: (2/3/2003 4:31:14 PM)
 * @param newPaymentID int
 */
public void setPaymentID(int newPaymentID) {
	paymentID = newPaymentID;
}
/**
 * Returns a String that represents the value of this object.
 * @return a string representation of the receiver
 */
public String toString() {
	// Insert code to print the receiver here.
	// This implementation forwards the message to super. You may replace or supplement this.
	//return super.toString();
	return Integer.toString(hashCode());
}
}
