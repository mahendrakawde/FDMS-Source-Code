package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;
/**
 * This class contains one contract charge.
 * It impliments interfaces allowing this class to be included in collections.
 * Creation date: (1/25/01 3:07:21 PM)
 * @author: R. Davidson
 */
public class DbChargeItem extends com.aldorsolutions.webfdms.database.Persistent 
	implements java.lang.Comparable, java.io.Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -1433478292310430038L;

	static private final DbChargeItemPeer peer = new DbChargeItemPeer();

	static public final int CURRENT_FINANCE_CHARGE_ID = 9999;
	static public final int PREVIOUS_FINANCE_CHARGE_ID = 9998;

	private int type;
	private java.lang.String description;
	private int recordNumber;
	private int amount;
//	private java.lang.String ArAcct;
	private java.lang.String glAcct;
	private int exemptAmount;
	private java.lang.String taxCode;
	private char spfCode;
	private java.lang.String invItemName;
	private int invSeqNo;	// key to inventory master if this is an inventory item
	private int invOnHandID;	// key to inv on-hand row for serial# inventory items
	private String itemCategoryType;
	private int origPrice;
	private int vitalsID;
	// Sequence number is for altering the order charges appear in the financial page
	private int sequenceNumber;
	private short fileVersion;
	private int priceListId;
	private int taxAmount;
    private boolean glFromInv;
    private boolean fromPackage;
    private int packageId;

/**
	 * @return the fromPackage
	 */
	public boolean isFromPackage() {
		return fromPackage;
	}
	/**
	 * @param fromPackage the fromPackage to set
	 */
	public void setFromPackage(boolean fromPackage) {
		this.fromPackage = fromPackage;
	}
/**
 * ChargeCollection constructor comment.
 */
public DbChargeItem() {
	super();
	//setNew();
}
/**
 * ChargeCollection constructor comment.
 */
public DbChargeItem(int avitalsID, int asequenceNumber, int arecordNumber, 
				int atype, String adescription, int aamount,
				String aGlAcct, int aexemptAmount, String ataxCode,
				char aspfCode, String ainvItemName, String aitemCategoryType,
				int ainvSeqNo, int aorigPrice, int aListID, boolean aFromPackage, int aPackageId)
{
	super();
	//setNew();
	vitalsID = avitalsID;
	sequenceNumber = asequenceNumber;
	recordNumber = arecordNumber;
	type = atype; 
	description = adescription;
	amount = aamount;
//	ArAcct = aArAcct;
	glAcct = aGlAcct;
	exemptAmount = aexemptAmount;
	taxCode = ataxCode;
	spfCode = aspfCode;
	invItemName = ainvItemName;
	itemCategoryType = aitemCategoryType;
	invSeqNo = ainvSeqNo;
	origPrice = aorigPrice;
	priceListId = aListID;
	fromPackage = aFromPackage;
	packageId = aPackageId;
	
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
 * Creation date: (1/25/01 3:10:28 PM)
 * @return java.lang.String
 */
public java.lang.String getDescription() {
	return description;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:12:24 PM)
 * @return int
 */
public int getExemptAmount() {
	return exemptAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2001 2:48:52 PM)
 * @return short
 */
public short getFileVersion() {
	return fileVersion;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:12:05 PM)
 * @return java.lang.String
 */
public java.lang.String getGlAcct() {
	return glAcct;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:13:37 PM)
 * @return java.lang.String
 */
public java.lang.String getInvItemName() {
	return invItemName;
}
/**
 * Insert the method's description here.
 * Creation date: (10/25/2002 4:16:34 PM)
 * @return int
 */
public int getInvOnHandID() {
	return invOnHandID;
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
public String getItemCategoryType() {
	return itemCategoryType;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:15:11 PM)
 * @return int
 */
public int getOrigPrice() {
	return origPrice;
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
 * Creation date: (1/25/01 3:12:41 PM)
 * @return java.lang.String
 */
public java.lang.String getTaxCode() {
	if (taxCode==null)
		return "";
		
	return taxCode;
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
 * Insert the method's description here.
 * Creation date: (1/25/01 3:18:42 PM)
 * @return int
 */
public int getVitalsID() {
	return vitalsID;
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
	return (1000*getVitalsID() +  getSequenceNumber());
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
	setId(					FormatNumber.parseInteger(data.get(DbChargeItemPeer.IDENTITY).toString()));
	amount 					= FormatNumber.parseInteger(data.get(DbChargeItemPeer.AMOUNT ) .toString());
//	setArAcct(				(String)data.get(peer.ARACCT));
	description				= data.get(DbChargeItemPeer.DESCRIPTION).toString();
	exemptAmount			= FormatNumber.parseInteger(data.get(DbChargeItemPeer.TAXEXEMPTAMT).toString());
	glAcct					= data.get(DbChargeItemPeer.GLACCT).toString();
	invItemName				= data.get(DbChargeItemPeer.INVENTORYITEM).toString();
	fileVersion				= FormatNumber.parseShort(data.get(DbChargeItemPeer.FILEVERSION).toString());
	invSeqNo				= FormatNumber.parseInteger(data.get(DbChargeItemPeer.INVMASTERKEY).toString());
	itemCategoryType		= data.get(DbChargeItemPeer.CATEGORYCODE).toString();
	recordNumber			= getId();
	origPrice				= FormatNumber.parseInteger(data.get(DbChargeItemPeer.ORIGINALPRICE).toString());
	sequenceNumber			= FormatNumber.parseShort(data.get(DbChargeItemPeer.SEQUENCE).toString());
	spfCode					= FormatString.getFirstChar((String)data.get(DbChargeItemPeer.SPFCODE));
	taxCode					= data.get(DbChargeItemPeer.TAXCODE).toString();
	type					= FormatNumber.parseInteger(data.get(DbChargeItemPeer.CHARGETYPE).toString());
	vitalsID				= FormatNumber.parseInteger(data.get(DbChargeItemPeer.VITALSID).toString());
	priceListId				= FormatNumber.parseInteger(data.get(DbChargeItemPeer.PRICELISTID).toString());
	invOnHandID				= FormatNumber.parseInteger(data.get(DbChargeItemPeer.ONHANDID).toString());
	fromPackage 			= Boolean.parseBoolean(data.get(DbChargeItemPeer.FROMPACKAGE).toString());
	packageId				= FormatNumber.parseInteger(data.get(DbChargeItemPeer.PACKAGEID).toString());
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
 * Creation date: (1/25/01 3:10:28 PM)
 * @param newDescription java.lang.String
 */
public void setDescription(java.lang.String newDescription) {
	modify();
	description = newDescription;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:12:24 PM)
 * @param newExemptAmount int
 */
public void setExemptAmount(int newExemptAmount) {
	modify();
	exemptAmount = newExemptAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2001 2:48:52 PM)
 * @param newFileVersion short
 */
public void setFileVersion(short newFileVersion) {
	modify();
	fileVersion = newFileVersion;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:12:05 PM)
 * @param newGlAcct java.lang.String
 */
public void setGlAcct(java.lang.String newGlAcct) {
	modify();
	glAcct = newGlAcct;
}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(DbChargeItemPeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbChargeItemPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:13:37 PM)
 * @param newInvItemName java.lang.String
 */
public void setInvItemName(java.lang.String newInvItemName) {
	modify();
	invItemName = newInvItemName;
}
/**
 * Insert the method's description here.
 * Creation date: (10/25/2002 4:16:34 PM)
 * @param newInvOnHandID int
 */
public void setInvOnHandID(int newInvOnHandID) {
	invOnHandID = newInvOnHandID;
	modify();
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
public void setItemCategoryType(String newItemCategoryType) {
	modify();
	itemCategoryType = newItemCategoryType;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:15:11 PM)
 * @param newOrigPrice int
 */
public void setOrigPrice(int newOrigPrice) {
	modify();
	origPrice = newOrigPrice;
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
 * Creation date: (1/25/01 3:12:41 PM)
 * @param newTaxCode java.lang.String
 */
public void setTaxCode(java.lang.String newTaxCode) {
	modify();
	taxCode = newTaxCode;
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
 * Insert the method's description here.
 * Creation date: (1/25/01 3:18:42 PM)
 * @param newVitalsID int
 */
public void setVitalsID(int newVitalsID) {
	modify();
	vitalsID = newVitalsID;
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
/**
 * @return Returns the taxAmount.
 */
public int getTaxAmount() {
	return taxAmount;
}
/**
 * @param taxAmount The taxAmount to set.
 */
public void setTaxAmount(int taxAmount) {
	this.taxAmount = taxAmount;
}

public boolean getGlFromInv () {
    return glFromInv;
}

public void setGlFromInv (boolean newGlFromInv) {
    modify();
    glFromInv = newGlFromInv;
}


public String toOutString () {

	return ( "type; " + type + "\n" + 
			"description; " + description + "\n" + 
			"recordNumber; " + recordNumber + "\n" + 
			"amount; " + amount + "\n" + 
			"glAcct; " + glAcct + "\n" + 
			"exemptAmount; " + exemptAmount + "\n" + 
			"taxCode; " + taxCode + "\n" + 
			"spfCode; " + spfCode + "\n" + 
			"invItemName; " + invItemName + "\n" + 
			"invSeqNo; " + invSeqNo + "\n" + 
			"invOnHandID" + invOnHandID + "\n" + 
			"itemCategoryType; " + itemCategoryType + "\n" + 
			"origPrice; " + origPrice + "\n" + 
			"vitalsID; " + vitalsID + "\n" +
			"sequenceNumber; " + sequenceNumber + "\n" + 
			"fileVersion; " + fileVersion + "\n" + 
			"priceListId; " + priceListId + "\n" + 
			"taxAmount; " + taxAmount + "\n" + 
			"glFromInv; " + glFromInv + "\n" +
			"fromPackage; "+ fromPackage + "\n" +
			"pacakgeId; "+ packageId +"\n");
}
public int getPackageId() {
	return packageId;
}
public void setPackageId(int packageId) {
	this.packageId = packageId;
}


}
