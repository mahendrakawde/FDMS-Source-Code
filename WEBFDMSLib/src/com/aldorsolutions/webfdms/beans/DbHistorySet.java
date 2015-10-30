package com.aldorsolutions.webfdms.beans;

/**
 * A DbHistorySet represents all history records or all for one vitals ID.
 * The peer class provides the class name and SQL for restoring
 * Creation date: (11/26/2001 11:14:33 AM)
 * @author: 
 */
public class DbHistorySet extends com.aldorsolutions.webfdms.database.PersistentSet {
	final static DbHistorySetPeer peer= new DbHistorySetPeer();
/**
 * getPersistentSetPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentSetPeer getPersistentSetPeer() {
	return peer;
}
}
