package com.aldorsolutions.webfdms.beans;

/**
 * DbUserSetPeer provides the runtime class name for the DbUser objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (11/1/2001 3:57:54 PM)
 * @author: 
 */

public class DbUserSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one USER class to be restored.
 * Later, we may have more than one USER class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbUser";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Cases are retrieved.
 * @param h a Hashtable containing the customer ID
 * @return the SELECT SQL to get the accounts from the database with

 */
public String getSql(java.util.Hashtable h) {
	
	String tmp = "SELECT * from usersecurity WHERE 1=1";

	if (h == null) { // If no query criteria are specified
	   return tmp;
	}

	tmp += " AND (DeleteCode is null or DeleteCode <>'D')";

	if (h.containsKey("Name")) {
	   tmp += " AND Name = '" +h.get("Name") +"'";
	}

	if (h.containsKey(DbUserPeer.COMPANYID)) {
	   tmp += " AND " +DbUserPeer.COMPANYID +" = " +h.get(DbUserPeer.COMPANYID); 
	}
	
	if (h.containsKey(DbUserPeer.REGION)) {
	   tmp += " AND " +DbUserPeer.REGION +" = " +h.get(DbUserPeer.REGION); 
	}
	
	if (h.containsKey(DbUserPeer.EMAILADDR)) {
	   tmp += " AND " +DbUserPeer.EMAILADDR +" = '" +h.get(DbUserPeer.EMAILADDR) +"'";
	}
	 
	if (h.containsKey(DbUserPeer.DATAURL)) {
	   tmp += " AND " +DbUserPeer.DATAURL +" = '" +h.get(DbUserPeer.DATAURL) +"'";
	}
	tmp += " ORDER BY Name";
	return tmp.toString();

}
}
