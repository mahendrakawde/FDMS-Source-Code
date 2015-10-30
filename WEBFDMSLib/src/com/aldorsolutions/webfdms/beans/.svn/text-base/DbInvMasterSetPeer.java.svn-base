package com.aldorsolutions.webfdms.beans;

/**
 * DbInvMasterSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them.
 * Creation date: (12/24/2001 11:16:20 AM)
 * @author: 
 */
public class DbInvMasterSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbInvMaster";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Active records are retrieved.
 * If Hashtable is not null, then selection by locael and delete code
 * are provided.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	boolean needand = false;
	if( h == null ) { 
		// If no query criteria are specified select all active records
		return "SELECT * FROM invmaster WHERE DeleteCode<>'D' ORDER BY ItemName";
	}
	else {
		StringBuffer tmp = new StringBuffer();
		tmp.append( "SELECT * FROM invmaster Where ");
		if (h.containsKey(DbInvMasterPeer.LOCALE) ) {
			tmp.append("Locale = ");
			tmp.append(h.get(DbInvMasterPeer.LOCALE).toString());
			needand=true;
		}
		if( h.containsKey(DbInvMasterPeer.DELETECODE) ) {
			// select by active / inactive
			if (needand) {
				tmp.append(" AND ");
			}
			tmp.append( "DeleteCode ");
			if (h.get(DbInvMasterPeer.DELETECODE).toString().compareTo("D")==0){
				// select inactive
				tmp.append("='D' ");
			}
			else {
				// select active
				tmp.append("<>'D' ");
			}
			needand=true;
		}
		if (h.containsKey(DbInvMasterPeer.ITEMNAME)){
			if (needand) {
				tmp.append(" AND ");
			}
			tmp.append( " ItemName='");
			tmp.append(  h.get(DbInvMasterPeer.ITEMNAME).toString());
			tmp.append( "' ");
			needand=true;
		}
		tmp.append(" ORDER BY ItemName");
		
		// CHAPEL selection option uses InvChapelIndex table also
		if (h.containsKey("CHAPEL")){
			tmp = new StringBuffer(); // start over with completely different select statement
			String location = h.get("CHAPEL").toString();
			tmp.append( "SELECT * FROM invmaster, invchapelindex WHERE  DeleteCode<>'D' AND LocationNumber=");
			tmp.append( location );
			tmp.append( " AND ItemNumber= invmaster.Identity ORDER BY invmaster.ItemName");
		}
		return tmp.toString();
	}
}
}
