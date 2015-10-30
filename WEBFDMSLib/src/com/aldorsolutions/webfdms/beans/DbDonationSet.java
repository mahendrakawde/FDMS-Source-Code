package com.aldorsolutions.webfdms.beans;

/**
 * A DbDonationSet represents a all donation records or all for one vitals ID.
 * The peer class provides the class name and SQL for restoring
 * Creation date: (11/26/2001 11:14:33 AM)
 * @author: 
 */
public class DbDonationSet extends com.aldorsolutions.webfdms.database.PersistentSet {
	final static DbDonationSetPeer peer= new DbDonationSetPeer();
/**
 * getPersistentSetPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentSetPeer getPersistentSetPeer() {
	return peer;
}
}
