package com.aldorsolutions.webfdms.beans;

/**
 * A DbBilltoSet represents a all bill-to records or all for one vitals ID
 * The peer class provides the DbBillto class name and SQL for restoring
 * Creation date: (11/24/2001 11:14:33 AM)
 * @author: 
 */
public class DbBilltoSet extends com.aldorsolutions.webfdms.database.PersistentSet {
	final static DbBilltoSetPeer peer= new DbBilltoSetPeer();
/**
 * getPersistentSetPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentSetPeer getPersistentSetPeer() {
	return peer;
}
}
