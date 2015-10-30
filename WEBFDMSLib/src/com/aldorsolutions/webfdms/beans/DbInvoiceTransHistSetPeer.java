package com.aldorsolutions.webfdms.beans;

import java.text.SimpleDateFormat;

/**
 * DbInvoiceSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them Creation date:
 * (12/21/2007 11:16:20 AM)
 * 
 * @author:
 */
public class DbInvoiceTransHistSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
	/**
	 * At this time, there is only one Case class to be restored. Later, we may
	 * have an at-need and pre-need class and this method would determine which to
	 * return.
	 */
	public String getPersistentClass(java.util.Hashtable h) {
		return "com.aldorsolutions.webfdms.beans.DbInvoiceTransHist";
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
			return "SELECT * FROM invoicetranshist";
		} else {
			StringBuffer tmp = new StringBuffer();
			tmp.append("SELECT * FROM invoiceTransHist");


			// Select checks expense amounts that have not yet been interfaced
			if (h.containsKey(DbInvoiceTransHistPeer.POSTED)) {
				tmp = new StringBuffer();
				tmp.append("SELECT * FROM invoicetranshist ");
				tmp.append("WHERE invoicetranshist.posted != 'Y'");

				if (h.containsKey(DbInvoiceTransHistPeer.LOCATIONID)) {
					tmp.append(" AND invoicetranshist.LocationID=");
					tmp.append(h.get(DbInvoiceTransHistPeer.LOCATIONID).toString());
				}
			}

			return tmp.toString();
		}
	}
}
