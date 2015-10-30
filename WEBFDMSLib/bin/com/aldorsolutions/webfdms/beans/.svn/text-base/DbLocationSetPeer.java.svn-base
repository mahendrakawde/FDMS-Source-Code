package com.aldorsolutions.webfdms.beans;

/**
 * DbLocationSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (11/26/2001 11:16:20 AM)
 * @author: 
 */
public class DbLocationSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbLocation";
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
	String tmp = "SELECT * from locations WHERE (InactiveCode is null or InactiveCode<>'D')";
	
	if (h == null ) { // If no query criteria are specified
		return tmp;
	}

	if (h.containsKey(DbLocationPeer.LOCALE))  {
	   tmp += " AND Locale = " +h.get(DbLocationPeer.LOCALE).toString();
	}
	
	if (h.containsKey(DbLocationPeer.IDENTITY))  {
	   tmp += " AND " + DbLocationPeer.IDENTITY + " = " +h.get(DbLocationPeer.IDENTITY).toString();
	}


	if (h.containsKey("Name")) {
	   tmp += " AND Name = \"" +h.get("Name") +"\"";
	}

	if (h.containsKey("State")) {
	   tmp += " AND State = '" +h.get("State") +"'";
	}

	if (h.containsKey(DbLocationPeer.COMPANYNO)) {
	   tmp += " AND " + DbLocationPeer.COMPANYNO + " = '" + 
	   		  h.get(DbLocationPeer.COMPANYNO).toString() +"'";
	}
	
	if (h.containsKey("LicenseNumber")) {
	   tmp += " AND LicenseNumber = '" +h.get("LicenseNumber") +"'";
	}
    
	return tmp;
}
}
