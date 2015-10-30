package com.aldorsolutions.webfdms.beans;

public class DbPasswordLogSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
	/**
	 * At this time, there is only one Case class to be restored. Later, we may
	 * have an at-need and pre-need class and this method would determine which
	 * to return.
	 */
	public String getPersistentClass(java.util.Hashtable h) {
		return "com.aldorsolutions.webfdms.beans.DbPasswordLog";
	}

	/**
	 * Provides the proper SQL SELECT statement based on the values that come
	 * from a database row stored in the Hashtable. If the Hashtable is null,
	 * then all Active records are retrieved. If a "ContractID" is passed, then
	 * all itemized charges for a given contract are provided.
	 * 
	 * @param h
	 *            a Hashtable containing the selection criteria
	 * @return the SELECT SQL to get the cases from the database with
	 */
	public String getSql(java.util.Hashtable h) {
		if (h == null) {
			// If no query criteria are specified select all records
			return "SELECT * FROM passwordlog";
		} else {
			StringBuffer tmp = new StringBuffer();
			tmp.append("SELECT * FROM passwordlog");
			boolean appendWhere = false;
			
			if (h.containsKey(DbPasswordLogPeer.USERID)) {
				tmp.append(" where " + DbPasswordLogPeer.USERID + "=");
				tmp.append(h.get(DbPasswordLogPeer.USERID).toString());
				appendWhere = true;
			}
			
			if (h.containsKey(DbPasswordLogPeer.PASSWORD)) {
				
				if ( appendWhere == false ) {
					tmp.append(" where "); 
				}
				else {
					tmp.append(" and ");
				}
				
				tmp.append(DbPasswordLogPeer.PASSWORD + "= '");
				tmp.append(h.get(DbPasswordLogPeer.PASSWORD).toString() + "'");
			}
			
			tmp.append(" ORDER BY " + DbPasswordLogPeer.PASSWORDLOGID + " DESC");
			return tmp.toString();
		}
	}
}
