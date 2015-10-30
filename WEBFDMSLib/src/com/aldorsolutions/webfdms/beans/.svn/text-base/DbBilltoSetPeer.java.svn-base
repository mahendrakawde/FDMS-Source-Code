package com.aldorsolutions.webfdms.beans;

/**
 * DbBilltoSetPeer provides the runtime class name for the Bill-to objects
 * it is responsible for as well as the SQL for restoring them.
 * Creation date: (11/24/2001 3:57:54 PM)
 * @author: 
 */
public class DbBilltoSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one USER class to be restored.
 * Later, we may have more than one USER class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbBillto";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Cases are retrieved.
 * @param h a Hashtable containing the customer ID
 * @return the SELECT SQL to get the accounts from the database with

 */
public String getSql(java.util.Hashtable h) {
	if( h == null ) { // If no query criteria are specified
		return "SELECT * FROM billtoparties WHERE DeleteCode <> 'D'";
	}
	else {
		String tmp = "SELECT * FROM billtoparties WHERE DeleteCode <> 'D' "; 
		
		if( h.containsKey(DbBilltoPeer.VITALSID) ) {
			tmp += " AND VitalsID="+h.get(DbBilltoPeer.VITALSID).toString();
		}
		return tmp;
	}
}
}
