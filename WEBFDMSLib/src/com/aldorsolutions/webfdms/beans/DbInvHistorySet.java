package com.aldorsolutions.webfdms.beans;

/**
 * A DbInvHistorySet represents all Inventory transactions or all 
 * transactions for a give master-ID.
 * The peer class provides the class name and SQL for restoring
 * Creation date: (12/26/2001 11:14:33 AM)
 * @author: 
 */
public class DbInvHistorySet extends com.aldorsolutions.webfdms.database.PersistentSet {
	final static DbInvHistorySetPeer peer= new DbInvHistorySetPeer();
/**
 * getPersistentSetPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentSetPeer getPersistentSetPeer() {
	return peer;
}
}
