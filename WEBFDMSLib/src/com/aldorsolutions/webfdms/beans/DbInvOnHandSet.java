package com.aldorsolutions.webfdms.beans;

/**
 * A DbInvOnHandSet represents inventory items actually held on hand in stock. 
 * The peer class provides the class name and SQL for restoring.
 * Creation date: (2/11/2002 11:14:33 AM)
 * @author: 
 */
public class DbInvOnHandSet extends com.aldorsolutions.webfdms.database.PersistentSet {
	final static DbInvOnHandSetPeer peer= new DbInvOnHandSetPeer();
/**
 * getPersistentSetPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentSetPeer getPersistentSetPeer() {
	return peer;
}
}
