package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * DbFinancialStatementSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them.
 * Creation date: (11/26/2002 11:00:20 AM)
 * @author: 
 */
public class DbFinancialStatementSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
	/**
	 * Subclasses must implement this method to provide the
	 * DatabaseSetPeer with the name of a subclasses of Persistent
	 * to instantiate for each row returned from the database.
	 * @param h the Hashtable of values returned from the database query
	 * @return the name of the class to instantiate for this row
	 */
	public String getPersistentClass(java.util.Hashtable h) {
		return "com.aldorsolutions.webfdms.beans.DbFinancialSummary";
	}

	/**
	 * In this method, the Hashtable is used to pass parameters into the hardcoded
	 * select statement.  If the hashtable is null or the required keys are not
	 * found in the hashtable, the method throws a PersistenceException.
	 * @param h a Hashtable containing the selection criteria.  Hashtable required keys:
	 *    fromDate       (Beginning date (includes) of range YYYYMMDD format)
	 *    toDate         (Ending date (includes) of range YYYYMMDD format)
	 *    localeString   (User's localeNumber - must convert to int)
	 *    locationString (String of either 'ALL' or single location that must convert to int)
	 * @return the SELECT SQL to get the financialdata rows.
	 */
	public String getSql(java.util.Hashtable h) {

		String tmp = new String();
		String fromDate = new String(); // Required hashtable key
		String toDate = new String(); // Required hashtable key
		String localeString = new String(); // Required hashtable key
		String locationString = new String(); // Required hashtable key

		int locale = 0;
		int location = 0;

		// If no hashtable parameters were set.
		if (h == null) {
			//AppLog.trace("DbFinancialStatment.getSQL: hashtable is null.");
			return null;
		}

		// fromDate
		fromDate = h.get("fromDate").toString();
		if (fromDate == null || fromDate.trim().length() == 0) {
			//AppLog.trace("DbFinancialStatment.getSQL: cannot find fromDate in hashtable.");
			return null;
		}

		// toDate
		toDate = h.get("toDate").toString();
		if (toDate == null || toDate.trim().length() == 0) {
			//AppLog.trace("DbFinancialStatment.getSQL: cannot find toDate in hashtable.");
			return null;
		}

		// locale
		localeString = h.get("localeString").toString();
		if (localeString == null || localeString.trim().length() == 0) {
			//AppLog.trace("DbFinancialStatment.getSQL: cannot find locale in hashtable.");
			return null;
		} else {
			try {
				locale = FormatNumber.parseInteger(localeString);
			} catch (Exception e) {
				//AppLog.trace("DbFinancialStatment.getSQL: invalid locale in hashtable.");
				return null;
			}
		}

		// location
		locationString = h.get("locationString").toString();
		if (locationString == null || locationString.trim().length() == 0) {
			//AppLog.trace("DbFinancialStatment.getSQL: cannot find location in hashtable.");
			return null;
		} else {
			try {
				location = FormatNumber.parseInteger(locationString);
			} catch (Exception e) {
				if (!locationString.toUpperCase().equals("ALL")) {
					//AppLog.trace("DbFinancialStatment.getSQL: invalid location in hashtable.");
					return null;
				}
			}
		}

		// Build the SQL Statement.  
		tmp = "SELECT f.* " + "FROM financialdata f, {OJ billtoparties b LEFT OUTER JOIN vitalstats v "
				+ "ON b.vitalsid = v.vitalsmasterkey} WHERE SentToAccounting > 0 AND "
				+ "TotalCharges > totalPaidToDate AND f.vitalsmasterkey = v.vitalsmasterkey AND "
				+ "(v.PNPreNeedStatus IS NULL OR v.PNPreNeedStatus <> 'A')  AND "
				+ "v.voidedCode <> 'V' and v.localeNumber = " + locale + " AND " + "v.saleDateKey >= '" + fromDate
				+ "' AND " + "v.saleDateKey <= '" + toDate + "' AND f.vitalsmasterkey = b.vitalsid AND "
				+ "b.SendInvoice = 'Y' AND " + "b.DeleteCode <> 'D'";

		if (!locationString.toUpperCase().equals("ALL")) {
			tmp = tmp.concat(" AND v.chapelNumber = " + location);
		}

		//AppLog.trace("DbFinancialStatement.SQL = " +tmp);
		return tmp;

	}
}
