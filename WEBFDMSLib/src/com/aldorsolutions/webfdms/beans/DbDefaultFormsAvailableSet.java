package com.aldorsolutions.webfdms.beans;

/**
 * A DbFormsAvailableSet represents all forms available to print
 * The peer class provides the class name and SQL for restoring
 * Creation date: (11/26/2001 11:14:33 AM)
 * @author: 
 */
public class DbDefaultFormsAvailableSet extends com.aldorsolutions.webfdms.database.PersistentSet {
	final static DbDefaultFormsAvailableSetPeer peer= new DbDefaultFormsAvailableSetPeer();
/**
 * getPersistentSetPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentSetPeer getPersistentSetPeer() {
	return peer;
}
}
