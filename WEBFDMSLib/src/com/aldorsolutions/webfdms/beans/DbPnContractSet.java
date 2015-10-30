package com.aldorsolutions.webfdms.beans;

/**
 * A DbContractSet represents all Contracts, usually for a specific case 
 * The peer class provides the class name and SQL for restoring
 * Creation date: (2/3/2003 11:14:33 AM)
 * @author: 
 */
public class DbPnContractSet extends com.aldorsolutions.webfdms.database.PersistentSet {
	final static DbPnContractSetPeer peer= new DbPnContractSetPeer();
/**
 * getPersistentSetPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentSetPeer getPersistentSetPeer() {
	return peer;
}
}
