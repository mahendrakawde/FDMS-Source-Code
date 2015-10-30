package com.aldorsolutions.webfdms.beans;

/**
 * DbLocaleConfigSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them.
 * Creation date: (11/26/2001 11:16:20 AM)
 * @author: 
 */
public class ObituaryFuneralSyncSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.ObituaryFuneralSync";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Locales are retrieved.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	if( h == null ) { // If no query criteria are specified
		return "SELECT * from obituary";
	}
	else {
		String tmp = "SELECT * from obituary";

		StringBuffer whereClause = new StringBuffer();
		
		if( h.containsKey(ObituaryFuneralSyncPeer.CLIENTID) ) {
			
			whereClause.append(" Where ");
			whereClause.append(ObituaryFuneralSyncPeer.CLIENTID + " = " + h.get(ObituaryFuneralSyncPeer.CLIENTID).toString() );
		}
		
		tmp += whereClause.toString();
		
		return tmp;
	}
}
}
