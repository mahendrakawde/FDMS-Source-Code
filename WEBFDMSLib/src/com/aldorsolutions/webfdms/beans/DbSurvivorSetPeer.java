package com.aldorsolutions.webfdms.beans;


/**
 * DbSurvivorSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (11/26/2001 11:16:20 AM)
 * @author: 
 */
public class DbSurvivorSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbSurvivor";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Cases are retrieved.
 * If a VITALS KEY is passed, then all records for that case
 * are provided.
 * If a LASTNAME is passed then a LOCALE must also be passed and
 * cases are selected by vitals.locale greater than the passed last name.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	String tmp=null;
	
	if (h == null) { // If no query criteria are specified
			return "SELECT * from survivors";
		} else if (h.containsKey(DbSurvivorPeer.CASE_ID)) {
			tmp = "SELECT * from survivors WHERE VitalsMasterKey = ";
			tmp += h.get(DbSurvivorPeer.CASE_ID).toString();
		} else if (h.containsKey(DbSurvivorPeer.LASTNAME)) {
			
			tmp = "SELECT survivors.* from survivors,vitalstats WHERE survivors.VitalsMasterKey=vitalstats.VitalsMasterKey AND vitalstats.LocaleNumber="
				+ h.get(DbCasePeer.LOCALE).toString();
			
			if(h.get(DbSurvivorPeer.FIRSTNAME) != null & h.get(DbSurvivorPeer.FIRSTNAME) != "") {
				tmp += " AND  (LastName like '"
					+ h.get(DbSurvivorPeer.LASTNAME).toString() + "%'"
					+ " AND  FirstName like '" + h.get(DbSurvivorPeer.FIRSTNAME).toString() + "%')";
			} else {
				tmp += " AND  (LastName like '"
					+ h.get(DbSurvivorPeer.LASTNAME).toString() + "%'"
					+ " OR  FirstName like '";
				
				tmp += h.get(DbSurvivorPeer.LASTNAME).toString() + "%')";
			}
		// tmp += " AND \"Last name\" <= '";
		// tmp += h.get(DbSurvivorPeer.LASTNAME).toString()+"~'";
			
			if (h.containsKey(DbCasePeer.CHAPELNUMBER))
				tmp += " AND (vitalstats.ChapelNumber = " + h.get(DbCasePeer.CHAPELNUMBER) + ")";

		}
		if (h.containsKey(DbSurvivorPeer.PALLBEARERS)) {
			tmp += " AND SequenceNumber >= 1005 AND SequenceNumber <= 1020";
		}
		if (h.containsKey(DbSurvivorPeer.SEQNUMBER)) {
			tmp += " AND SequenceNumber = ";
			tmp += h.get(DbSurvivorPeer.SEQNUMBER).toString();
		}
		// tmp += " ORDER BY LastName, FirstName";
		if (h.containsKey("ORDER BY")) {
			tmp += " ORDER BY " + h.get("ORDER BY").toString();
		} else {
			tmp += " ORDER BY LastName, FirstName";
		}
	
	if (h.containsKey("MaxRows")){
		tmp += " LIMIT ";
		tmp += h.get("MaxRows").toString();
	}
	
	return tmp;
}
}
