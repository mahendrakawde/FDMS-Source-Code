package com.aldorsolutions.webfdms.beans;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import com.aldorsolutions.webfdms.database.DatabaseSetPeer;

/**
 * DbApAccountSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (4/30/2002 11:16:20 AM) modify 030309
 * @author: 
 */
public class DbApDistributionHistorySetPeer extends DatabaseSetPeer {
	
	public static final String APDIST_DATE_FROM = "APDIST_DATE_FROM";
	public static final String APDIST_DATE_TO = "APDIST_DATE_TO";

	/**
	 * Not Persisted
	 */
	static public final String COMPANYID = "CompanyID";
	
/**
 * At this time, there is only one Case class to be restored.
 * Later, we may have an at-need and pre-need class and this method
 * would determine which to return.
 */
public String getPersistentClass(Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbApDistributionHistory";
}
/**
 * Provides the proper SQL SELECT statement based on the values
 * that come from a database row stored in the Hashtable.  If the
 * Hashtable is null, then all Active records are retrieved.
 * If a "MasterID" is passed, then all expense accounts for a given check
 * are provided.
 * @param h a Hashtable containing the selection criteria
 * @return the SELECT SQL to get the cases from the database with
 */
public String getSql(Hashtable h) {
	if( h == null ) { 
		// If no query criteria are specified select all records
		return "SELECT * FROM apdistributionhistory";
	}
	else {
		StringBuffer tmp = new StringBuffer();
		tmp.append("SELECT * FROM apdistributionhistory"); 

		if( h.containsKey(DbApDistributionHistoryPeer.IDENTITY) ) {
			// Get records for one check
			tmp.append(" where apDistHistID=");
			tmp.append(h.get(DbApDistributionHistoryPeer.IDENTITY).toString());
		}

		// Select checks expense amounts that have not yet been interfaced
 		else if (h.containsKey(DbApDistributionHistoryPeer.POSTED)){
	 		tmp = new StringBuffer();
	 		tmp.append("SELECT apdistributionhistory.* FROM apdistributionhistory, apmaster, locations ");
	 		tmp.append("WHERE (locations.LocationID = apmaster.LocationID AND ");
	 		tmp.append("locations.Locale = apmaster.LocaleID ) AND ");
	 		tmp.append("(apdistributionhistory.apMasterID = apmaster.MasterID) AND "); 
	 		tmp.append("(apdistributionhistory.posted IS NULL OR apdistributionhistory.posted != 'Y') "); 
		 	
	 		if (h.containsKey(DbApDistributionHistorySetPeer.COMPANYID)){
			 	tmp.append(" AND locations.CompanyNumber = ");
			 	tmp.append(h.get(DbApDistributionHistorySetPeer.COMPANYID).toString() );
		 	}
	 		
		 	if (h.containsKey(DbApMasterPeer.LOCALEID)){
			 	tmp.append(" AND apmaster.LocaleID=");
			 	tmp.append(h.get(DbApMasterPeer.LOCALEID).toString());
		 	}
		 	if (h.containsKey(DbApMasterPeer.LOCATIONID)){
			 	tmp.append(" AND apmaster.LocationID=");
			 	tmp.append(h.get(DbApMasterPeer.LOCATIONID).toString());
		 	}
		 	
			if ( h.containsKey(APDIST_DATE_FROM) ) {
				Date sqlDate = (Date) h.get(APDIST_DATE_FROM);
				
			    if ( sqlDate != null ) {
			    	SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
			    	tmp.append(" AND " + DbApMasterPeer.CHECKDATE +">=");
			    	tmp.append("'" + sdf.format(sqlDate) + "'");
			    }
				
			}

			if ( h.containsKey(APDIST_DATE_TO) ) {
				Date sqlDate = (Date) h.get(APDIST_DATE_TO);
				
			    if ( sqlDate != null ) {
			    	SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
			    	tmp.append(" AND " + DbApMasterPeer.CHECKDATE +"<=");
			    	tmp.append("'" + sdf.format(sqlDate) + "'");
			    }
				
			}
		 	
		 	tmp.append(" ORDER BY apmaster.CheckDate");
 		}
 		else if (h.containsKey(DbApDistributionHistoryPeer.APMASTERID)){
	 		tmp = new StringBuffer();
	 		tmp.append("SELECT apdistributionhistory.* FROM apdistributionhistory ");
	 		tmp.append("WHERE apMasterID = ");
	 		tmp.append(h.get(DbApDistributionHistoryPeer.APMASTERID).toString());
		
 		}
		
		return tmp.toString();
	}
}
}
