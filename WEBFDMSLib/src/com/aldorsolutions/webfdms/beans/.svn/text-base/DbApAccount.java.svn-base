package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;


/**
 * DbApAccount - This class represents one expense account for
 * WebFDMS Check Writing.
 * Creation date: (4/30/2002 2:42:35 PM)
 * @author: 
 */
public class DbApAccount extends Persistent {
	
	static private final DbApAccountPeer peer = new DbApAccountPeer();

	private java.lang.String accountNumber;
	private java.lang.String description;
	private int localeID;
	private int locationID;
/**
 * Insert the method's description here.
 * Creation date: (4/30/2002 11:19:09 AM)
 * @return java.lang.String
 */
public java.lang.String getAccountNumber() {
	return accountNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (4/30/2002 11:19:25 AM)
 * @return java.lang.String
 */
public java.lang.String getDescription() {
	return description;
}
/**
 * Insert the method's description here.
 * Creation date: (4/30/2002 11:19:40 AM)
 * @return int
 */
public int getLocaleID() {
	return localeID;
}
/**
 * Insert the method's description here.
 * Creation date: (4/30/2002 11:19:49 AM)
 * @return int
 */
public int getLocationID() {
	return locationID;
}
/**
 * getPersistentPeer method comment.
 */
protected PersistentPeer getPersistentPeer() {
	return peer;
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
	setId(			FormatNumber.parseInteger(data.get(DbApAccountPeer.IDENTITY).toString()));
	accountNumber 	= (String)data.get(DbApAccountPeer.ACCTNO);
	description 	= (String)data.get(DbApAccountPeer.DESCRIPTION);
	localeID	 	= FormatNumber.parseInteger(data.get(DbApAccountPeer.LOCALEID).toString());
	locationID	 	= FormatNumber.parseInteger(data.get(DbApAccountPeer.LOCATIONID).toString());
}
/**
 * Insert the method's description here.
 * Creation date: (4/30/2002 11:19:09 AM)
 * @param newAccountNumber java.lang.String
 */
public void setAccountNumber(java.lang.String newAccountNumber) {
	accountNumber = newAccountNumber;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (4/30/2002 11:19:25 AM)
 * @param newDescription java.lang.String
 */
public void setDescription(java.lang.String newDescription) {
	description = newDescription;
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
	setId(((Integer)h.get(DbApAccountPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (4/30/2002 11:19:40 AM)
 * @param newLocaleID int
 */
public void setLocaleID(int newLocaleID) {
	modify();
	localeID = newLocaleID;
}
/**
 * Insert the method's description here.
 * Creation date: (4/30/2002 11:19:49 AM)
 * @param newLocationID int
 */
public void setLocationID(int newLocationID) {
	modify();
	locationID = newLocationID;
}
/**
 * Insert the method's description here.
 * Creation date: (5/6/2002 2:24:08 PM)
 * @return java.lang.String
 */
public String toString() {
	return getAccountNumber()+" "+getDescription();
}
}
