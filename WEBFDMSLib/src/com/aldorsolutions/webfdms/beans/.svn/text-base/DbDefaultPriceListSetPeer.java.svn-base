package com.aldorsolutions.webfdms.beans;


/**
 * DbDefaultDefaultPriceListSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them.
 * Creation date: (11/26/2001 11:16:20 AM)
 * @author: 
 */
public class DbDefaultPriceListSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
	
   /**
	* At this time, there is only one Case class to be restored.
	* Later, we may have an at-need and pre-need class and this method
	* would determine which to return.
	*/
   public String getPersistentClass(java.util.Hashtable h) {
	  return "com.aldorsolutions.webfdms.beans.DbDefaultPriceList";
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
	  String tmp = new String("SELECT * from defaultpricelist WHERE 1=1");
	
	  if (h != null) { // If no query criteria are specified
		 if (h.containsKey(DbDefaultPriceListPeer.VERSION)) {
			tmp = tmp +" AND PriceListVersion = '" +h.get(DbDefaultPriceListPeer.VERSION).toString() +"'";
		 }
		 if (h.containsKey(DbDefaultPriceListPeer.GPLKEY)) {
			tmp = tmp +" AND GPLKey = '" +h.get(DbDefaultPriceListPeer.GPLKEY).toString() +"'";
		 }
		 if (h.containsKey(DbDefaultPriceListPeer.LOCALEID)) {
			tmp = tmp +" AND LocaleID = " +h.get(DbDefaultPriceListPeer.LOCALEID).toString();
		 }
	  }
	  tmp = tmp +" ORDER BY InvoiceSeqNo, ContractLineNumber";
	  return tmp;
   }   
}
