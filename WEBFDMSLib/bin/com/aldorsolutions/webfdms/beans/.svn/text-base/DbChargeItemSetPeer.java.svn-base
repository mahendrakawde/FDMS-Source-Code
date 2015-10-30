package com.aldorsolutions.webfdms.beans;

/**
 * DbChargeItemSetPeer provides the runtime class name for the DbChargeItemSet objects
 * it is responsible for as well as the SQL for restoring them.
 * Creation date: (11/1/2001 11:16:20 AM)
 * @author: 
 */
public class DbChargeItemSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbChargeItem";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Cases are retrieved.
 * If a LOCATION-REGION is passed, then all accounts for that REGION
 * are provided.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	if( h == null ) { // If no query criteria are specified
		return "SELECT * from charges";
	}
	else {
		String tmp = "SELECT * from charges WHERE VitalsMasterKey = ";

		if( h.containsKey(DbChargeItemPeer.VITALSID) ) {
			tmp += h.get(DbChargeItemPeer.VITALSID).toString();
		}
		
		if( h.containsKey(DbChargeItemPeer.CHARGETYPE) ) {
			tmp += " and ChargeType="+h.get(DbChargeItemPeer.CHARGETYPE).toString();
		}

		return tmp;
	}
}
}
