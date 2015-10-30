package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.DatabaseSetPeer;

/**
 * DbApAccountSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (4/30/2002 11:16:20 AM)
 * @author: 
 */
public class DbApAccountSetPeer extends DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbApAccount";
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
		return "SELECT * FROM apaccounts where (Inactive <> 'Y' or Inactive is null) order by AccountNumber, Description";
	}
	else if (h.containsKey(DbApAccountPeer.ACCTNO))
	{
		StringBuffer tmp = new StringBuffer();
		tmp.append("SELECT * FROM apaccounts where (Inactive <> 'Y' or Inactive is null) and ");
		tmp.append(" (AccountNumber = '");
		tmp.append(h.get(DbApAccountPeer.ACCTNO).toString());
		tmp.append("')");
		if( h.containsKey(DbApAccountPeer.DESCRIPTION) ) {
			// Get records for one chapel location
			tmp.append(" AND (Description = '");
			tmp.append(h.get(DbApAccountPeer.DESCRIPTION).toString());
			tmp.append("')");
		}
		return tmp.toString();
    }
	else if (h.containsKey(DbApAccountPeer.GROUPBYIDNAME) && h.get(DbApAccountPeer.GROUPBYIDNAME).toString().compareToIgnoreCase("true") == 0 )
	{
		StringBuffer tmp = new StringBuffer();
		tmp.append("SELECT * FROM apaccounts  where (Inactive <> 'Y' or Inactive is null) GROUP BY AccountNumber, Description ");
	    return tmp.toString();	
	}
	else {
		StringBuffer tmp = new StringBuffer();
		tmp.append("SELECT * FROM apaccounts where (Inactive <> 'Y' or Inactive is null) and (LocaleID=0");
		if( h.containsKey(DbApAccountPeer.LOCALEID) ) {
			// Get records for one Locale
			tmp.append(" OR LocaleID = ");
			tmp.append(h.get(DbApAccountPeer.LOCALEID).toString());
		}
		tmp.append(")");
		if( h.containsKey(DbApAccountPeer.LOCATIONID) ) {
			// Get records for one chapel location
			tmp.append(" AND (LocationID=0");
			tmp.append(" OR LocationID = ");
			tmp.append(h.get(DbApAccountPeer.LOCATIONID).toString());
			tmp.append(")");
		}
		
		tmp.append(" ORDER BY AccountNumber, Description ");
		return tmp.toString();
	}
}
}
