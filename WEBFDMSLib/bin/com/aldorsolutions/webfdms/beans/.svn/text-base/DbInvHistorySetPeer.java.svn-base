package com.aldorsolutions.webfdms.beans;

/**
 * DbInvHistorySetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (12/24/2001 11:16:20 AM)
 * @author: 
 */
public class DbInvHistorySetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbInvHistory";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Active records are retrieved.
 * If a "Delete Code" is passed, then all inactive records
 * are provided.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	if( h == null ) { 
		// If no query criteria are specified select all records
		return "SELECT * FROM invhistory";
	}
	else {
		String tmp = "SELECT * FROM invhistory";

		if( h.containsKey(DbInvHistoryPeer.MASTERID) ) {
			// Get records for one ID
			tmp += " WHERE MasterID = "+h.get(DbInvHistoryPeer.MASTERID).toString();
		}
		
		return tmp;
	}
}
}
