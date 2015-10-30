package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * DbMemorial - This class represents one verse for memorial printing.
 * Creation date: (10/08/2002 2:42:35 PM)
 * @author: 
 */
public class DbMemorial extends com.aldorsolutions.webfdms.database.Persistent
{
	static private final DbMemorialPeer peer = new DbMemorialPeer();

	private java.lang.String verse;
	private java.lang.String description;
	private int localeID;
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
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
	return peer;
}
/**
 * Insert the method's description here.
 * Creation date: (4/30/2002 11:19:09 AM)
 * @return java.lang.String
 */
public java.lang.String getVerse() {
	return verse;
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
	setId(			FormatNumber.parseInteger(data.get(DbMemorialPeer.IDENTITY).toString()));
	verse 			= (String)data.get(DbMemorialPeer.VERSE);
	description 	= (String)data.get(DbMemorialPeer.DESCRIPTION);
	localeID	 	= FormatNumber.parseInteger(data.get(DbMemorialPeer.LOCALE).toString());
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
	//if (h.containsKey(DbMemorialPeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbMemorialPeer.IDENTITY)).intValue());
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
 * Creation date: (4/30/2002 11:19:09 AM)
 * @param newAccountNumber java.lang.String
 */
public void setVerse(java.lang.String newVerse) {
	verse = newVerse;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (5/6/2002 2:24:08 PM)
 * @return java.lang.String
 */
public String toString() {
	return getDescription();
}
}
