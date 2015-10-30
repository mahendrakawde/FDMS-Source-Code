package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbInvChapelIndex - This class represents which locations are
 * authorized to use which Inventory master items.
 * Creation date: (12/27/2001 2:42:35 PM)
 * @author: 
 */
public class DbInvChapelIndex extends com.aldorsolutions.webfdms.database.Persistent {
	static private final DbInvChapelIndexPeer peer = new DbInvChapelIndexPeer();

	private int itemNumber;
	private int locationNumber;
/**
 * DbInvChapelIndex constructor comment.
 */
public DbInvChapelIndex() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (12/30/2001 7:55:49 PM)
 * @return int
 */
public int getItemNumber() {
	return itemNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (12/30/2001 7:56:05 PM)
 * @return int
 */
public int getLocationNumber() {
	return locationNumber;
}
/**
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
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
	setId(			FormatNumber.parseInteger(data.get(DbInvChapelIndexPeer.IDENTITY).toString()));
	itemNumber =	FormatNumber.parseInteger(data.get(DbInvChapelIndexPeer.ITEM).toString());
	locationNumber=	FormatNumber.parseInteger(data.get(DbInvChapelIndexPeer.LOCATION).toString());
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
	setId(((Integer)h.get(DbInvChapelIndexPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (12/30/2001 7:55:49 PM)
 * @param newItemNumber int
 */
public void setItemNumber(int newItemNumber) {
	itemNumber = newItemNumber;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (12/30/2001 7:56:05 PM)
 * @param newLocationNumber int
 */
public void setLocationNumber(int newLocationNumber) {
	locationNumber = newLocationNumber;
	modify();
}
}
