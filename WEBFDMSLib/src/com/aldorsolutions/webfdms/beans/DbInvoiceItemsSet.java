package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistentSet;
import com.aldorsolutions.webfdms.database.PersistentSetPeer;

/**
 * A DbInvoiceItems represents all InvoiceItems or all InvoiceItems for a given locale The
 * peer class provides the class name and SQL for restoring Creation date:
 * (12/26/2007 11:14:33 AM)
 * 
 * @author:
 */
public class DbInvoiceItemsSet extends PersistentSet {
	final static DbInvoiceItemsSetPeer peer = new DbInvoiceItemsSetPeer();

	/**
	 * getPersistentSetPeer method comment.
	 */
	protected PersistentSetPeer getPersistentSetPeer() {
		return peer;
	}
}
