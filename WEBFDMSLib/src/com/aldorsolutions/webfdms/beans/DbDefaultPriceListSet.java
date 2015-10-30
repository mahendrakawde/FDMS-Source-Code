package com.aldorsolutions.webfdms.beans;

/**
 * A DbDefaultPriceListSet represents all Default Price List records.
 * The peer class provides the class name and SQL for restoring.
 * Creation date: (8/26/2002 12:04:33 PM)
 * @author: C. McDiarmid
 */
public class DbDefaultPriceListSet extends com.aldorsolutions.webfdms.database.PersistentSet {
	final static DbDefaultPriceListSetPeer peer= new DbDefaultPriceListSetPeer();
/**
 * getPersistentSetPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentSetPeer getPersistentSetPeer() {
	return peer;
}
}
