package com.aldorsolutions.webfdms.beans;

/**
 * DbLocaleConfigSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them.
 * Creation date: (11/26/2001 11:16:20 AM)
 * @author: 
 */
public class DbLocaleConfigTypeSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbLocaleConfigType";
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
		return "SELECT * from localeconfigtypes";
	}
	else {
		String tmp = "SELECT * from localeconfigtypes";

		StringBuffer whereClause = new StringBuffer();
		
		if( h.containsKey(DbLocaleConfigTypePeer.IDENTITY) ) {
			
			whereClause.append(" Where ");
			whereClause.append(DbLocaleConfigTypePeer.IDENTITY + " = " + h.get(DbLocaleConfigTypePeer.IDENTITY).toString() );
		}
		
		if( h.containsKey(DbLocaleConfigTypePeer.NAME) ) {
			
			if ( whereClause.length() > 0 ) {
				whereClause.append(" AND ");
			} else {
				whereClause.append(" Where ");
			}
			
			whereClause.append(DbLocaleConfigTypePeer.NAME + " = " + h.get(DbLocaleConfigTypePeer.NAME).toString() );
		}
		
		tmp += whereClause.toString();
		
		return tmp;
	}
}
}
