package com.aldorsolutions.webfdms.beans;

/**
 * A DbLocaleConfigSet represents all regions. The peer class provides the class name
 * and SQL for restoring Creation date: (11/26/2001 11:14:33 AM)
 * 
 * @author:
 */
public class ObituaryFuneralSyncSet extends com.aldorsolutions.webfdms.database.PersistentSet {
	final static ObituaryFuneralSyncSetPeer peer = new ObituaryFuneralSyncSetPeer();

	/**
	 * getPersistentSetPeer method comment.
	 */
	protected com.aldorsolutions.webfdms.database.PersistentSetPeer getPersistentSetPeer() {
		return peer;
	}
}
