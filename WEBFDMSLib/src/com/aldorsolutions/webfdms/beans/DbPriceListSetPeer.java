package com.aldorsolutions.webfdms.beans;

/**
 * DbPriceListSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them.
 * Creation date: (11/26/2001 11:16:20 AM)
 * @author: 
 */
public class DbPriceListSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbPriceList";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Cases are retrieved.
 * If a VITALS KEY is passed, then all records for that case
 * are provided.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	if( h == null ) { // If no query criteria are specified
		return "SELECT * from pricelist";
	}
	else {
		String tmp = "SELECT * from pricelist";

		if( h.containsKey(DbPriceListPeer.VERSION) ) {
			tmp += " WHERE PriceListVersion = '"+
			h.get(DbPriceListPeer.VERSION).toString()
			+"'";
		}
		if( h.containsKey(DbPriceListPeer.GPLKEY) ) {
			tmp += " AND GPLKey = '"+
			h.get(DbPriceListPeer.GPLKEY).toString() +
			"'";
		}
		if( h.containsKey(DbPriceListPeer.LOCALEID) ) {
			tmp += " AND LocaleID = "+
			h.get(DbPriceListPeer.LOCALEID).toString();
		}
		tmp += " ORDER BY InvoiceSeqNo, ContractLineNumber";
		return tmp;
	}
}
}
