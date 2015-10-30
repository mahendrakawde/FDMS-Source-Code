package com.aldorsolutions.webfdms.beans;

/**
 * DbVendorSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (5/3/2002 11:16:20 AM)
 * @author: 
 */
public class DbApVendorSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbApVendor";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Active records are retrieved.
 * If a "Locale" is passed, then all Vendors for a locale code
 * are provided.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	if( h == null ) { 
		// If no query criteria are specified select all records
		return "SELECT * FROM apvendors";
	}
	else {
		StringBuffer tmp = new StringBuffer();
		tmp.append("SELECT * FROM apvendors WHERE (DeleteCode is null OR DeleteCode<>'D')");

		if( h.containsKey(DbApVendorPeer.LOCALE) ) {
			// Get records for one Locale
			tmp.append(" AND (Locale=0 OR Locale = ");
			tmp.append(h.get(DbApVendorPeer.LOCALE).toString());
			tmp.append(")");
		}
		if( h.containsKey(DbApVendorPeer.LOCATIONID) ) {
			// Get records for one Locale
			tmp.append(" AND (LocationID=0 OR LocationID = ");
			tmp.append(h.get(DbApVendorPeer.LOCATIONID).toString());
			tmp.append(")");
		}

		tmp.append(" ORDER BY ");
		tmp.append(DbApVendorPeer.NAME);
		
		return tmp.toString();
	}
}
}
