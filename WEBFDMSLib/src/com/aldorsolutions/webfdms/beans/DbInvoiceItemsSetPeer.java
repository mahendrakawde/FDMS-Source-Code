package com.aldorsolutions.webfdms.beans;

/**
 * DbInvoiceSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them Creation date:
 * (12/21/2007 11:16:20 AM)
 * 
 * @author:
 */
public class DbInvoiceItemsSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
	/**
	 * At this time, there is only one Case class to be restored. Later, we may
	 * have an at-need and pre-need class and this method would determine which to
	 * return.
	 */
	public String getPersistentClass(java.util.Hashtable h) {
		return "com.aldorsolutions.webfdms.beans.DbInvoiceItems";
	}

	/**
	 * Provides the proper SQL SELECT statement based on the values that come from
	 * a database row stored in the Hashtable. If the Hashtable is null, then all
	 * Active records are retrieved. If a "Locale" is passed, then all Checks for
	 * a locale code are provided. If VOIDEDCODE is passed then include voided
	 * checks
	 * 
	 * @param h
	 *          a Hashtable containing the selection criteria
	 * @return the SELECT SQL to get the cases from the database with
	 */
	public String getSql(java.util.Hashtable h) {
		if (h == null) {
			// If no query criteria are specified select all records
			return "SELECT * FROM invoiceitems";
		} else {
			StringBuffer tmp = new StringBuffer();
			tmp.append("SELECT * FROM invoiceitems where invoiceItemID="+h.get(DbInvoiceItemsPeer.INVOICEITEMID).toString());

			return tmp.toString();
		}
	}
}
