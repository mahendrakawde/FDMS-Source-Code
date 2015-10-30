package com.aldorsolutions.webfdms.beans;


/**
 * DbComponentSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (11/26/2001 11:16:20 AM)
 * @author: 
 */
public class DbComponentSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbComponent";
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
		return "SELECT * from pmntcomponents";
	}
	else {
		StringBuffer tmp = new StringBuffer();
		tmp.append("SELECT * from pmntcomponents WHERE ");

		if( h.containsKey(DbComponentPeer.CASE_ID) ) {
			tmp.append(DbComponentPeer.CASE_ID);
			tmp.append("=");
			tmp.append( h.get(DbComponentPeer.CASE_ID).toString() );
		}

		if( h.containsKey(DbComponentPeer.CODE) ) {
			tmp.append(" AND ");
			tmp.append(DbComponentPeer.CODE);
			tmp.append("=");
			tmp.append( h.get(DbComponentPeer.CODE).toString() );
		}
		//AppLog.trace("DbComponentSetPeer SQL:"+tmp.toString());
		return tmp.toString();
	}
}
}
