package com.aldorsolutions.webfdms.beans;

/**
 * DbSpeedDataSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (1/1/2002 11:16:20 AM)
 * @author: 
 */
public class DbSpeedDataSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbSpeedData";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Active records are retrieved.
 * If a "Locale" is passed, then all Arrangers for a locale code
 * are provided.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	if( h == null ) { 
		// If no query criteria are specified select all records
		return "SELECT * FROM speeddata";
	}
	else {
		String tmp = "SELECT * FROM speeddata";
		
		if (h.containsKey(DbSpeedDataPeer.LOCATIONID))
			// Get records for one Location
			tmp += " WHERE (LocationId = " + h.get(DbSpeedDataPeer.LOCATIONID).toString()+")";
		else 
			// Get records for one Locale
			tmp += " WHERE (Locale = 0 OR Locale = "
				+ h.get(DbSpeedDataPeer.LOCALE).toString()+") "
				+ "AND (LocationId = 0)";

		if( h.containsKey(DbSpeedDataPeer.CATEGORY) ) {
			// Get records for one Category
			tmp += " AND TabCategory = '"+h.get(DbSpeedDataPeer.CATEGORY).toString()
				+"'";
		}
		if( h.containsKey(DbSpeedDataPeer.DATA) ) {
			// Search based on a string of chars in the data
			tmp += " AND TabData LIKE '%"+h.get(DbSpeedDataPeer.DATA).toString()
				+"%'";
		}
		tmp += " ORDER BY SortSequence ASC, TabData ASC";
		// prior to 7/17/03 order included "Locale DESC" as the primary sequence
		// but replaced that idea with the SortSequence added column. 
		return tmp;
	}
}
}
