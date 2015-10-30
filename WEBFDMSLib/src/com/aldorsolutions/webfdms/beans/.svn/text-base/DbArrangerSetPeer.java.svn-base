package com.aldorsolutions.webfdms.beans;

import java.util.Enumeration;

/**
 * DbArrangerSetPeer provides the runtime class name for the Persistent objects
 * it is responsible for as well as the SQL for restoring them
 * Creation date: (1/1/2002 11:16:20 AM)
 * @author: 
 */
public class DbArrangerSetPeer extends com.aldorsolutions.webfdms.database.DatabaseSetPeer {
	/**
	 * At this time, there is only one Case class to be restored.
	 * Later, we may have an at-need and pre-need class and this method
	 * would determine which to return.
	 */
	public String getPersistentClass(java.util.Hashtable h) {
		return "com.aldorsolutions.webfdms.beans.DbArrangers";
	}
	/**
	 * Provides the proper SQL SELECT statement based on the values
	 * that come from a database row stored in the Hashtable.  If the
	 * Hashtable is null, then all Active records are retrieved.
	 * If a "Locale" is passed, then all Arrangers for a locale code
	 * are provided. The special values "**" and "*null*" allow for 
	 * filtering by non-NULL and NULL qualifications respectively.
	 * 
	 * @param h a Hashtable containing the selection criteria
	 * @return the SELECT SQL to get the cases from the database with
	 */
	public String getSql(java.util.Hashtable h) {
		
		String tmp = new String("SELECT * FROM arrangers WHERE (DeleteCode is null or DeleteCode <>'D')");
		if(h.containsKey(DbArrangersPeer.DELETECODE)){
			tmp = new String("SELECT * FROM arrangers WHERE "+DbArrangersPeer.DELETECODE+"='"+
					h.get(DbArrangersPeer.DELETECODE).toString() +"'");
		}
			
		
		if (h != null ) {
			Enumeration e = h.keys();
			
				while (e.hasMoreElements() )
				{
					String name = e.nextElement().toString();
					String value = h.get(name).toString();
					
					if ((name == DbArrangersPeer.BURIALLICENSENUMBER) ||
						(name == DbArrangersPeer.EMBALMERLICENSENUMBER) ||
						(name == DbArrangersPeer.LICENSENO) ||
						(name == DbArrangersPeer.LOCALE) ||
						(name == DbArrangersPeer.PNLICENSENUMBER) ||
						(name == DbArrangersPeer.SSN) ||
						(name == DbArrangersPeer.ISCOUNSELOR) )
						
					{
						if (value.matches("\\*\\*"))			//any non-empty value will do
							tmp += " AND " + name + " IS NOT NULL" +
								   " AND LENGTH(TRIM(" + name + "))>0";
						else if (value.matches("\\*null\\*"))	//empty values only
							tmp +=  " AND " + name + " IS NULL" +
							 " OR LENGTH(TRIM(" + name + "))=0";
						
						else									//whatever was passed in only
							tmp += " AND " +name + " = " + value;
					}
				}
			
	   }
		
	   tmp += " ORDER BY Name";
			
	   return tmp;
	}
}