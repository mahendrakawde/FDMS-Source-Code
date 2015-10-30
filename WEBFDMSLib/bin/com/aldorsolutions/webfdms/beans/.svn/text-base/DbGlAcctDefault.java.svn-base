package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbGlAcctDefaults represents the set of default GL accounts
 * for a given:
 * 		category -  	like "Services" or "Caskets"
 *		location - 		chapel locations can have different GL accounts
 *		Disposition -	such as "Burial" or "Cremation"
 *		Sale Type - 	such as "Traditional", "Ship in", etc.
 * The GL accounts available for a given set of parameters above are
 * revenue account, Inventory Asset account, Inventory cost of goods sold account.
 * Creation date: (11/26/2001 2:42:35 PM)
 * @author: 
 */
public class DbGlAcctDefault extends com.aldorsolutions.webfdms.database.Persistent
{
	static private final DbGlAcctDefaultPeer peer = new DbGlAcctDefaultPeer();

	private java.lang.String deleteCode;
	private int locationID;
	private java.lang.String category;
	private java.lang.String disposition;
	private java.lang.String saleType;
	private java.lang.String revenueAcct;
	private java.lang.String invAssetAcct;
	private java.lang.String invCogsAcct;
	private int locale;
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:52:50 PM)
 * @return java.lang.String
 */
public java.lang.String getCategory() {
	return category;
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:52:20 PM)
 * @return java.lang.String
 */
public java.lang.String getDeleteCode() {
	return deleteCode;
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:53:14 PM)
 * @return java.lang.String
 */
public java.lang.String getDisposition() {
	return disposition;
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:54:22 PM)
 * @return java.lang.String
 */
public java.lang.String getInvAssetAcct() {
	return invAssetAcct;
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:54:41 PM)
 * @return java.lang.String
 */
public java.lang.String getInvCogsAcct() {
	return invCogsAcct;
}
/**
 * Insert the method's description here.
 * Creation date: (5/28/2003 4:23:23 PM)
 * @return int
 */
public int getLocale() {
	return locale;
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:52:38 PM)
 * @return int
 */
public int getLocationID() {
	return locationID;
}
/**
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
	return peer;
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:54:08 PM)
 * @return java.lang.String
 */
public java.lang.String getRevenueAcct() {
	return revenueAcct;
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:53:27 PM)
 * @return java.lang.String
 */
public java.lang.String getSaleType() {
	return saleType;
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
	setId(			FormatNumber.parseInteger(data.get(DbGlAcctDefaultPeer.IDENTITY).toString()));
	deleteCode		= data.get(DbGlAcctDefaultPeer.DELETECODE).toString();
	locationID		= FormatNumber.parseInteger(data.get(DbGlAcctDefaultPeer.LOCATIONID).toString());
	category		= data.get(DbGlAcctDefaultPeer.CATEGORY).toString();
	disposition		= data.get(DbGlAcctDefaultPeer.DISPOSITION).toString();
	saleType		= data.get(DbGlAcctDefaultPeer.SALETYPE).toString();
	revenueAcct		= data.get(DbGlAcctDefaultPeer.REVACCT).toString();
	invAssetAcct	= data.get(DbGlAcctDefaultPeer.ASSETACCT).toString();
	invCogsAcct		= data.get(DbGlAcctDefaultPeer.COGSACCT).toString();
	locale			= FormatNumber.parseInteger(data.get(DbGlAcctDefaultPeer.LOCALE).toString());
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:52:50 PM)
 * @param newCategory java.lang.String
 */
public void setCategory(java.lang.String newCategory) {
	category = newCategory;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:52:20 PM)
 * @param newDeleteCode java.lang.String
 */
public void setDeleteCode(java.lang.String newDeleteCode) {
	deleteCode = newDeleteCode;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:53:14 PM)
 * @param newDisposition java.lang.String
 */
public void setDisposition(java.lang.String newDisposition) {
	disposition = newDisposition;
	modify();
}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(peer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbGlAcctDefaultPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:54:22 PM)
 * @param newInvAssetAcct java.lang.String
 */
public void setInvAssetAcct(java.lang.String newInvAssetAcct) {
	invAssetAcct = newInvAssetAcct;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:54:41 PM)
 * @param newInvCogsAcct java.lang.String
 */
public void setInvCogsAcct(java.lang.String newInvCogsAcct) {
	invCogsAcct = newInvCogsAcct;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (5/28/2003 4:23:23 PM)
 * @param newLocale int
 */
public void setLocale(int newLocale) {
	locale = newLocale;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:52:38 PM)
 * @param newLocationID int
 */
public void setLocationID(int newLocationID) {
	locationID = newLocationID;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:54:08 PM)
 * @param newRevenueAcct java.lang.String
 */
public void setRevenueAcct(java.lang.String newRevenueAcct) {
	revenueAcct = newRevenueAcct;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (5/27/2003 4:53:27 PM)
 * @param newSaleType java.lang.String
 */
public void setSaleType(java.lang.String newSaleType) {
	saleType = newSaleType;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (5/6/2002 2:24:08 PM)
 * @return java.lang.String
 */
public String toString() {
	return getCategory()+" "+getSaleType()+" "+getDisposition();
}
}
