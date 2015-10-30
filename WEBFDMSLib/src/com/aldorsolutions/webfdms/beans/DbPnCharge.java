package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
/**
 * This class contains one Pre-Need contract charge.
 * It impliments interfaces allowing this class to be included in collections.
 * Creation date: (1/25/01 3:07:21 PM)
 * @author: R. Davidson
 */
public class DbPnCharge extends com.aldorsolutions.webfdms.database.Persistent 
	implements java.lang.Comparable, java.io.Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 5786951642009048886L;

	static private final DbPnChargePeer peer = new DbPnChargePeer();

	private int type;
	private java.lang.String description;
	private int recordNumber;
	private int amount;
	private char spfCode;
	private int priceListId;
	private int invSeqNo;	// key to inventory master if this is an inventory item
	private char itemCategoryType;
	// Sequence number is for altering the order charges appear in the financial page
	private int sequenceNumber;
	private int contractID;
	private java.lang.String taxCode;
	private int taxExempt;
/**
 * ChargeCollection constructor comment.
 */
public DbPnCharge() {
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
 * Creation date: (1/25/01 3:11:12 PM)
 * @return int
 */
public int getAmount() {
	return amount;
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
 * Creation date: (1/25/01 3:10:28 PM)
 * @return java.lang.String
 */
public java.lang.String getDescription() {
	return description;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:13:58 PM)
 * @return int
 */
public int getInvSeqNo() {
	return invSeqNo;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:14:53 PM)
 * @return char
 */
public char getItemCategoryType() {
	return itemCategoryType;
}
/**
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
	return peer;
}
/**
 * Insert the method's description here.
 * Creation date: (4/5/2002 3:02:22 PM)
 * @return int
 */
public int getPriceListId() {
	return priceListId;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:10:51 PM)
 * @return int
 */
public int getRecordNumber() {
	return recordNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:19:33 PM)
 * @return int
 */
public int getSequenceNumber() {
	return sequenceNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:13:01 PM)
 * @return java.lang.String
 */
public char getSpfCode() {
	return spfCode;
}
/**
 * Insert the method's description here.
 * Creation date: (2/11/2003 4:42:46 PM)
 * @return java.lang.String
 */
public java.lang.String getTaxCode() {
	return taxCode;
}
/**
 * Insert the method's description here.
 * Creation date: (2/11/2003 4:43:06 PM)
 * @return int
 */
public int getTaxExempt() {
	return taxExempt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:09:51 PM)
 * @return int
 */
public int getType() {
	return type;
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
	return (1000*getContractID() +  getSequenceNumber());
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
	setId(					FormatNumber.parseInteger(data.get(DbPnChargePeer.IDENTITY).toString()));
	contractID				= FormatNumber.parseInteger(data.get(DbPnChargePeer.CONTRACTID).toString());
	type					= FormatNumber.parseInteger(data.get(DbPnChargePeer.CHARGETYPE).toString());
	description				= data.get(DbPnChargePeer.DESCRIPTION).toString();
	amount 					= FormatNumber.parseInteger(data.get(DbPnChargePeer.AMOUNT ) .toString());
	sequenceNumber			= FormatNumber.parseShort(data.get(DbPnChargePeer.SEQUENCE).toString());
	spfCode					= FormatString.getFirstChar((String)data.get(DbPnChargePeer.SPFCODE));
	priceListId				= FormatNumber.parseInteger(data.get(DbPnChargePeer.PRICELISTID).toString());
	invSeqNo				= FormatNumber.parseInteger(data.get(DbPnChargePeer.INVMASTERKEY).toString());
	itemCategoryType		= FormatString.getFirstChar((String)data.get(DbPnChargePeer.CATEGORYCODE));
	taxCode					= data.get(DbPnChargePeer.TAXCODE).toString();
	taxExempt				= FormatNumber.parseInteger(data.get(DbPnChargePeer.TAXEXEMPT).toString());
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:11:12 PM)
 * @param newAmount int
 */
public void setAmount(int newAmount) {
	modify();
	amount = newAmount;
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
 * Creation date: (1/25/01 3:10:28 PM)
 * @param newDescription java.lang.String
 */
public void setDescription(java.lang.String newDescription) {
	modify();
	description = newDescription;
}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(DbPnChargePeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbPnChargePeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:13:58 PM)
 * @param newInvSeqNo int
 */
public void setInvSeqNo(int newInvSeqNo) {
	modify();
	invSeqNo = newInvSeqNo;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:14:53 PM)
 * @param newItemCategoryType char
 */
public void setItemCategoryType(char newItemCategoryType) {
	modify();
	itemCategoryType = newItemCategoryType;
}
/**
 * Insert the method's description here.
 * Creation date: (4/5/2002 3:02:22 PM)
 * @param newPriceListId int
 */
public void setPriceListId(int newPriceListId) {
	priceListId = newPriceListId;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:10:51 PM)
 * @param newRecordNumber int
 */
public void setRecordNumber(int newRecordNumber) {
	modify();
	recordNumber = newRecordNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:19:33 PM)
 * @param newSequenceNumber int
 */
public void setSequenceNumber(int newSequenceNumber) {
	modify();
	sequenceNumber = newSequenceNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:13:01 PM)
 * @param newSpfCode java.lang.String
 */
public void setSpfCode(char newSpfCode) {
	modify();
	spfCode = newSpfCode;
}
/**
 * Insert the method's description here.
 * Creation date: (2/11/2003 4:42:46 PM)
 * @param newTaxCode java.lang.String
 */
public void setTaxCode(java.lang.String newTaxCode) {
	taxCode = newTaxCode;
}
/**
 * Insert the method's description here.
 * Creation date: (2/11/2003 4:43:06 PM)
 * @param newTaxExempt int
 */
public void setTaxExempt(int newTaxExempt) {
	taxExempt = newTaxExempt;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:09:51 PM)
 * @param newType int
 */
public void setType(int newType) {
	modify();
	type = newType;
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
