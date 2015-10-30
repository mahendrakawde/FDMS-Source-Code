package com.aldorsolutions.webfdms.beans;

/**
 * DbInvChapelIndexSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (12/24/2001 11:16:20 AM)
 * @author: 
 */
public class DbInvOnHandSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbInvOnHand";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all rows are retrieved.
 * Normally, a MasterID is passed to select all on-hand for a specific
 * inventory item.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	if( h == null ) { 
		// If no query criteria are specified select all active records
		return "SELECT * FROM invonhand";
	}
	else {
		String tmp = "SELECT * FROM invonhand";

		if( h.containsKey(DbInvOnHandPeer.MASTERID) ) {
			// Get ON HAND for an item
			tmp += " WHERE Quantity > 0 AND MasterID="+h.get(DbInvOnHandPeer.MASTERID).toString();
			if(h.containsKey(DbInvOnHandPeer.QUANTITY)){
				tmp="SELECT * FROM invonhand WHERE MasterID="+h.get(DbInvOnHandPeer.MASTERID).toString();
			}
			if(h.containsKey(DbInvOnHandPeer.SERIALNUMBER)) {
				tmp += " AND SerialNumber=" + h.get(DbInvOnHandPeer.SERIALNUMBER).toString();
			}
		}
		
		return tmp;
	}
}
}
