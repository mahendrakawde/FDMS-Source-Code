package com.aldorsolutions.webfdms.beans;

import java.text.SimpleDateFormat;

import com.aldorsolutions.webfdms.database.DatabaseSetPeer;

/**
 * DbApAccountSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (4/30/2002 11:16:20 AM)
 * @author: 
 */
public class DbApDistributionSetPeer extends DatabaseSetPeer {
	
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
public String getPersistentClass(java.util.Hashtable h) {
	return "com.aldorsolutions.webfdms.beans.DbApDistribution";
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
public String getSql(java.util.Hashtable h) {
	if( h == null ) { 
		// If no query criteria are specified select all records
		return "SELECT * FROM apdistribution";
	}
	else {
		StringBuffer tmp = new StringBuffer();
		tmp.append("SELECT * FROM apdistribution"); 

		if( h.containsKey(DbApDistributionPeer.MASTERID) ) {
			// Get records for one check
			tmp.append(" where MasterID=");
			tmp.append(h.get(DbApDistributionPeer.MASTERID).toString());
		}

		// Select checks expense amounts that have not yet been interfaced
		/* SELECT apdistribution.* FROM apdistribution, apmaster 
			WHERE (Posted IS NULL OR Posted!="Y") AND apdistribution.MasterID=apmaster.MasterID
 			AND LocaleID=1 and LocationID=3 AND (VoidedCode IS NULL OR VoidedCode!="V") ORDER BY CheckDate
 		*/
 		else if (h.containsKey(DbApDistributionPeer.POSTED)){
	 		tmp = new StringBuffer();
	 		tmp.append("SELECT apdistribution.* FROM apdistribution, apmaster, locations ");
	 		tmp.append("WHERE  (locations.LocationID = apmaster.LocationID AND ");
	 		tmp.append("locations.Locale = apmaster.LocaleID ) AND ");
	 		tmp.append("(apdistribution.MasterID = apmaster.MasterID) AND "); 
	 		tmp.append("(apdistribution.Posted IS NULL OR apdistribution.Posted != 'Y') AND "); 
	 		tmp.append("(apmaster.VoidedCode IS NULL OR apmaster.VoidedCode != 'V') AND ");
	 			// The following SQL will on grab those records where the checks have already been
	 			// printed
	 		tmp.append("(apdistribution.PrintedDate IS NOT NULL) ");
		 	
	 		if (h.containsKey(DbApDistributionSetPeer.COMPANYID)){
			 	tmp.append(" AND locations.CompanyNumber = ");
			 	tmp.append(h.get(DbApDistributionSetPeer.COMPANYID).toString() );
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
				java.sql.Date sqlDate = (java.sql.Date) h.get(APDIST_DATE_FROM);
				
			    if ( sqlDate != null ) {
			    	SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
			    	tmp.append(" AND " + DbApMasterPeer.CHECKDATE +">=");
			    	tmp.append("'" + sdf.format(sqlDate) + "'");
			    }
				
			}

			if ( h.containsKey(APDIST_DATE_TO) ) {
				java.sql.Date sqlDate = (java.sql.Date) h.get(APDIST_DATE_TO);
				
			    if ( sqlDate != null ) {
			    	SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
			    	tmp.append(" AND " + DbApMasterPeer.CHECKDATE +"<=");
			    	tmp.append("'" + sdf.format(sqlDate) + "'");
			    }
				
			}
		 	
		 	tmp.append(" ORDER BY CheckDate");
 		}
		
		return tmp.toString();
	}
}
}
