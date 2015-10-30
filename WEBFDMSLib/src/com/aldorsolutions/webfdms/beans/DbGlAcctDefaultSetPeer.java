package com.aldorsolutions.webfdms.beans;

/**
 * DbGlAcctDefaultSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (5/28/2003 11:16:20 AM)
 * @author: 
 */
public class DbGlAcctDefaultSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbGlAcctDefault";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Cases are retrieved.
 * If a locale is passed then all records for that locale or locale=0 are passed.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	if( h == null ) { // If no query criteria are specified
		return "SELECT * from glacctdefault";
	}
	else {
		// the following selects all records with locale zero or the supplied value
			StringBuffer sqlbuf = new StringBuffer();
			sqlbuf.append("SELECT * FROM glacctdefault WHERE (deleteCode IS NULL OR deleteCode<>'D') AND ("+DbGlAcctDefaultPeer.LOCALE+"=0");
			if (h.containsKey(DbGlAcctDefaultPeer.LOCALE)){
				sqlbuf.append(" OR "+DbGlAcctDefaultPeer.LOCALE+"=");
				sqlbuf.append(h.get(DbGlAcctDefaultPeer.LOCALE).toString());
			}
			sqlbuf.append(")");
			// Specific location
			if (h.containsKey(DbGlAcctDefaultPeer.LOCATIONID)){
				sqlbuf.append(" AND (");
				sqlbuf.append(DbGlAcctDefaultPeer.LOCATIONID+"= 0");
				sqlbuf.append(" OR "+DbGlAcctDefaultPeer.LOCATIONID+"=");
				sqlbuf.append(h.get(DbGlAcctDefaultPeer.LOCATIONID).toString());
				sqlbuf.append(")");
			}
			// Specific category
			if (h.containsKey(DbGlAcctDefaultPeer.CATEGORY)){
				sqlbuf.append(" AND "+DbGlAcctDefaultPeer.CATEGORY+"=");
				sqlbuf.append("'"+h.get(DbGlAcctDefaultPeer.CATEGORY).toString()+"'");
			}
			// Specific disposition
			if (h.containsKey(DbGlAcctDefaultPeer.DISPOSITION)){
				sqlbuf.append(" AND (");
				sqlbuf.append(DbGlAcctDefaultPeer.DISPOSITION+"= '*'");
				sqlbuf.append(" OR "+DbGlAcctDefaultPeer.DISPOSITION+"=");
				sqlbuf.append("'"+h.get(DbGlAcctDefaultPeer.DISPOSITION).toString()+"')");
			}
			// Specific sale type
			if (h.containsKey(DbGlAcctDefaultPeer.SALETYPE)){
				sqlbuf.append(" AND (");
				sqlbuf.append(DbGlAcctDefaultPeer.SALETYPE+"= '*'");
				sqlbuf.append(" OR "+DbGlAcctDefaultPeer.SALETYPE+"=");
				sqlbuf.append("'"+h.get(DbGlAcctDefaultPeer.SALETYPE).toString()+"')");
			}
			
			sqlbuf.append(" ORDER BY "+DbGlAcctDefaultPeer.LOCATIONID+","+DbGlAcctDefaultPeer.CATEGORY+","+DbGlAcctDefaultPeer.DISPOSITION+","+DbGlAcctDefaultPeer.SALETYPE);
			//AppLog.trace("DbGlAcctDefaultSetPeer: "+sqlbuf.toString());
			return sqlbuf.toString();
	}
}
}
