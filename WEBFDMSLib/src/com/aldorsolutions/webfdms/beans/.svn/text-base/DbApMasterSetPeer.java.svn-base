package com.aldorsolutions.webfdms.beans;

/**
 * DbArrangerSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (1/1/2002 11:16:20 AM)
 * @author: 
 */
public class DbApMasterSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbApMaster";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Active records are retrieved.
 * If a "Locale" is passed, then all Checks for a locale code
 * are provided.
 * If VOIDEDCODE is passed then include voided checks
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	if( h == null ) { 
		// If no query criteria are specified select all records
		return "SELECT * FROM apmaster";
	}
	else {
		StringBuffer tmp = new StringBuffer();
		tmp.append("SELECT * FROM apmaster where (LocaleID=0");

		if( h.containsKey(DbApMasterPeer.LOCALEID) ) {
			// Get records for one Locale
			tmp.append(" OR LocaleID = ");
			tmp.append(h.get(DbApMasterPeer.LOCALEID).toString());
		}
		if( h.containsKey(DbApMasterPeer.LOCATIONID) ) {
			tmp.append(") AND (LocationID=0");
			// Get records for one chapel location
			tmp.append(" OR LocationID = ");
			tmp.append(h.get(DbApMasterPeer.LOCATIONID).toString());
		}
		tmp.append(")");
		if( h.containsKey(DbApMasterPeer.CHECKNUMBER) ) {
			// Get one check record
			tmp.append(" AND "+DbApMasterPeer.CHECKNUMBER+" = ");
			tmp.append(h.get(DbApMasterPeer.CHECKNUMBER).toString());
		}
		// if voided code not in set, then omit voids
		if(!h.containsKey(DbApMasterPeer.VOIDEDCODE) ) {
			tmp.append(" AND "+DbApMasterPeer.VOIDEDCODE+" <>'V' ");
		}
		
		if( h.containsKey(DbApMasterPeer.CHECKDATE) ) {
			// Get one check record
			tmp.append(" AND "+DbApMasterPeer.CHECKDATE+" >= '");
			tmp.append(h.get(DbApMasterPeer.CHECKDATE).toString());
			tmp.append("' ");
		}
		return tmp.toString();
	}
}
}
