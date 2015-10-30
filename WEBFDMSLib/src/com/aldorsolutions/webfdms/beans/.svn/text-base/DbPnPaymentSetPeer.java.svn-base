package com.aldorsolutions.webfdms.beans;

/**
 * DbPnPaymentSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (4/30/2002 11:16:20 AM)
 * @author: 
 */
public class DbPnPaymentSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbPnPayment";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Active records are retrieved.
 * If a "ContractID" is passed, then all itemized charges for a given contract
 * are provided.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	if( h == null ) { 
		// If no query criteria are specified select all records
		return "SELECT * FROM pnpayments";
	}
	else {
		StringBuffer tmp = new StringBuffer();
		tmp.append("SELECT * FROM pnpayments"); 

		if( h.containsKey(DbPnPaymentPeer.CONTRACTID) ) {
			// Get records for one contract
			tmp.append(" where "+DbPnPaymentPeer.CONTRACTID+"=");
			tmp.append(h.get(DbPnPaymentPeer.CONTRACTID).toString());
		}
		tmp.append(" ORDER BY "+DbPnPaymentPeer.DATE+" DESC");
		return tmp.toString();
	}
}
}
