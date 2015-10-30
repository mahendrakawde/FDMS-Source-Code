package com.aldorsolutions.webfdms.beans;

/**
 * DbInvChapelIndexSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (12/24/2001 11:16:20 AM)
 * @author: 
 */
public class DbInvChapelIndexSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbInvChapelIndex";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Active records are retrieved.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	if( h == null ) { 
		// If no query criteria are specified select all active records
		return "SELECT * FROM invchapelindex";
	}
	else {
		String tmp = "SELECT * FROM invchapelindex";

		if( h.containsKey(DbInvChapelIndexPeer.ITEM) ) {
			// Get locations for an item
			tmp += " WHERE ItemNumber="+h.get(DbInvChapelIndexPeer.ITEM).toString();
		}
		if( h.containsKey(DbInvChapelIndexPeer.LOCATION) ) {
			// Get items for a location
			tmp += " AND LocationNumber="+h.get(DbInvChapelIndexPeer.LOCATION).toString();
		}
		
		return tmp;
	}
}
}
