package com.aldorsolutions.webfdms.beans;

import java.text.SimpleDateFormat;


/**
 * DbHistorySetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (12/24/2001 11:16:20 AM)
 * @author: 
 */
public class DbHistorySetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
	
	public static final String TRANS_DATE_FROM = "TRANS_DATE_FROM";
	public static final String TRANS_DATE_TO = "TRANS_DATE_TO";
	static public final String COMPANYID = "CompanyID";
	
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbHistory";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Cases are retrieved.
 * If a VITALS KEY is passed, then all records for that case
 * are provided.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(java.util.Hashtable h) {
	if( h == null ) { // If no query criteria are specified
		return "SELECT * from transactionhistory WHERE DeleteTransaction <> \"Y\" ";
	}
	else {
		if( h.containsKey(DbHistoryPeer.VITALSID) ) {
			return ("SELECT * from transactionhistory WHERE DeleteTransaction <> \"Y\" and VitalsMasterKey = "+h.get(DbHistoryPeer.VITALSID).toString());
		}
		// the following selects all unposted case history transactions excluding misc cash receipts
		if (h.containsKey(DbHistoryPeer.POSTED)){
			StringBuffer sqlbuf = new StringBuffer();
			sqlbuf.append("SELECT transactionhistory.* FROM transactionhistory, vitalstats, locations ");  
			sqlbuf.append("WHERE transactionhistory.VitalsMasterKey = vitalstats.VitalsMasterKey AND "); 
			sqlbuf.append("( locations.LocationID = vitalstats.ChapelNumber AND ");
			sqlbuf.append("locations.Locale = vitalstats.LocaleNumber ) AND "); 
				// I am not sure why they are not looking at deleted transactions.  Deleted transactions
				// can occur because of Voided cases.  When a case is voided we need to reverse the voided 
				// information.  This bug was fixed because of Keystone.  If you have a problem with
				// this then know you could be affecting other customers.  What should really happen is
				// that the interface file should deal with deleted transactions.  If you don't want to 
				// export deleted transactions then that accounting interface should ignore those records.
				// Chad Lehnert 12/26/07
//			sqlbuf.append("DeleteTransaction != 'Y' and Postedyn!='Y' AND SPFcode!='R' "); 
		sqlbuf.append("Postedyn!='Y' AND SPFcode!='R' "); 

			if (h.containsKey(DbHistorySetPeer.COMPANYID)){
				sqlbuf.append("AND locations.CompanyNumber = ");
				sqlbuf.append(h.get(DbHistorySetPeer.COMPANYID).toString());
			}
			
			if (h.containsKey(DbCasePeer.LOCALE)){
				sqlbuf.append(" AND LocaleNumber=");
				sqlbuf.append(h.get(DbCasePeer.LOCALE).toString());
			}
			
			if (h.containsKey(DbCasePeer.CHAPELNUMBER)){
				sqlbuf.append(" AND ChapelNumber=");
				sqlbuf.append(h.get(DbCasePeer.CHAPELNUMBER).toString());
			}
			
			if ( h.containsKey(TRANS_DATE_FROM) ) {
				java.sql.Date sqlDate = (java.sql.Date) h.get(TRANS_DATE_FROM);
				
			    if ( sqlDate != null ) {
			    	SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
					sqlbuf.append(" AND " + DbHistoryPeer.DATEOFTRAN +">=");
					sqlbuf.append("'" + sdf.format(sqlDate) + "'");
			    }
				
			}
			

			if ( h.containsKey(TRANS_DATE_TO) ) {
				java.sql.Date sqlDate = (java.sql.Date) h.get(TRANS_DATE_TO);
				
			    if ( sqlDate != null ) {
			    	SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
					sqlbuf.append(" AND " + DbHistoryPeer.DATEOFTRAN +"<=");
					sqlbuf.append("'" + sdf.format(sqlDate) + "'");
			    }
				
			}
			
			
			sqlbuf.append(" ORDER BY transactionhistory.VitalsMasterKey, TranHistID");
			return sqlbuf.toString();
		}
		
		// the following selects all unposted misc cash receipts
		if (h.containsKey(DbHistoryPeer.SPFCODE) && h.get(DbHistoryPeer.SPFCODE).toString().compareToIgnoreCase("B") != 0){
			StringBuffer sqlbuf = new StringBuffer();
			
//   		sqlbuf.append("SELECT transactionhistory.* FROM transactionhistory, vitalstats, locations ");  
//			sqlbuf.append("WHERE transactionhistory.VitalsMasterKey = vitalstats.VitalsMasterKey AND "); 
//			sqlbuf.append("( locations.LocationID = vitalstats.ChapelNumber AND ");
//			sqlbuf.append("locations.Locale = vitalstats.LocaleNumber ) AND "); 
//			sqlbuf.append("DeleteTransaction != 'Y' and Postedyn!='Y' AND SPFcode='R' "); 
			sqlbuf.append("SELECT transactionhistory.* FROM transactionhistory, locations WHERE ");
			sqlbuf.append("Postedyn!=\"Y\" AND SPFcode=\"R\" AND transactionhistory.locationId=locations.LocationID");

			if (h.containsKey(DbHistorySetPeer.COMPANYID)){
				sqlbuf.append(" AND locations.CompanyNumber = ");
				sqlbuf.append(h.get(DbHistorySetPeer.COMPANYID).toString());
			}
			
			if (h.containsKey(DbHistoryPeer.LOCATIONID)){
				sqlbuf.append(" AND transactionhistory.locationId=");
				sqlbuf.append(h.get(DbHistoryPeer.LOCATIONID).toString());
			}
			else if (h.containsKey(DbCasePeer.LOCALE)){
				sqlbuf.append(" AND locations.Locale=");
				sqlbuf.append(h.get(DbCasePeer.LOCALE).toString());
			}

			if ( h.containsKey(TRANS_DATE_FROM) ) {
				java.sql.Date sqlDate = (java.sql.Date) h.get(TRANS_DATE_FROM);
				
			    if ( sqlDate != null ) {
			    	SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
					sqlbuf.append(" AND " + DbHistoryPeer.DATEOFTRAN +">=");
					sqlbuf.append("'" + sdf.format(sqlDate) + "'");
			    }
				
			}

			if ( h.containsKey(TRANS_DATE_TO) ) {
				java.sql.Date sqlDate = (java.sql.Date) h.get(TRANS_DATE_TO);
				
			    if ( sqlDate != null ) {
			    	SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
					sqlbuf.append(" AND " + DbHistoryPeer.DATEOFTRAN +"<=");
					sqlbuf.append("'" + sdf.format(sqlDate) + "'");
			    }
				
			}
			
			sqlbuf.append(" ORDER BY TranHistID");
			return sqlbuf.toString();
		}
		
		// find the bank transaction for deposit and fee.
		if (h.containsKey(DbHistoryPeer.SPFCODE) && h.get(DbHistoryPeer.SPFCODE).toString().compareToIgnoreCase("B") == 0){
			StringBuffer sqlbuf = new StringBuffer();
			sqlbuf.append("SELECT * FROM transactionhistory WHERE SPFcode = 'B' ");
			
			if (h.containsKey(DbHistoryPeer.LOCATIONID)) {
				sqlbuf.append(" AND (locationId = " +h.get(DbHistoryPeer.LOCATIONID).toString() +")");
			}
			if (h.containsKey(DbHistoryPeer.STARTDATEOFTRAN)) {
				sqlbuf.append(" AND (DateOfTran >= '" +h.get(DbHistoryPeer.STARTDATEOFTRAN).toString() +"')");
			}
			if (h.containsKey(DbHistoryPeer.ENDDATEOFTRAN)) {
				sqlbuf.append(" AND (DateOfTran <= '" +h.get(DbHistoryPeer.ENDDATEOFTRAN).toString() +"')");
			}
			
			sqlbuf.append(" ORDER BY DateOfTran DESC, TranHistID DESC ");
			return sqlbuf.toString();
		}
		
		
	}
	// default
	return "SELECT * from transactionhistory";
}
}
