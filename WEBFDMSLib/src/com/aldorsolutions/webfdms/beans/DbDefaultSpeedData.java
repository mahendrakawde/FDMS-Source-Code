package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * Dbdefaultspeeddata - Holds lists of frequently entered information.
 * Creation date: (1/1/2002 10:31:09 AM)
 * @author: 
 */
public class DbDefaultSpeedData extends com.aldorsolutions.webfdms.database.Persistent {
	static private final DbDefaultSpeedDataPeer peer = new DbDefaultSpeedDataPeer();
	private java.lang.String category;
	private int locale;
	private java.lang.String data;
/**
 * Dbdefaultspeeddata constructor comment.
 */
public DbDefaultSpeedData() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (1/1/2002 10:36:38 AM)
 * @return java.lang.String
 */
public java.lang.String getCategory() {
	return category;
}
/**
 * Insert the method's description here.
 * Creation date: (1/1/2002 10:37:00 AM)
 * @return java.lang.String
 */
public java.lang.String getData() {
	return data;
}
/**
 * Insert the method's description here.
 * Creation date: (1/1/2002 10:36:46 AM)
 * @return int
 */
public int getLocale() {
	return locale;
}
/**
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
	return peer;
}
/**
 * isLocked method comment.
 */
public boolean isLocked() {
	return false;
}
/**
 * restore method comment.
 */
public void restore(com.aldorsolutions.webfdms.database.Transaction t, java.util.Hashtable data) throws com.aldorsolutions.webfdms.database.PersistenceException {
	setId(			FormatNumber.parseInteger(data.get(DbDefaultSpeedDataPeer.IDENTITY).toString()));
	setCategory(	(String)data.get(DbDefaultSpeedDataPeer.CATEGORY));
	setLocale(		FormatNumber.parseInteger(data.get(DbDefaultSpeedDataPeer.LOCALE).toString()));
	setData(		(String)data.get(DbDefaultSpeedDataPeer.DATA));
	
}
/**
 * Insert the method's description here.
 * Creation date: (1/1/2002 10:36:38 AM)
 * @param newCategory java.lang.String
 */
public void setCategory(java.lang.String newCategory) {
	category = newCategory;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (1/1/2002 10:37:00 AM)
 * @param newData java.lang.String
 */
public void setData(java.lang.String newData) {
	data = newData;
	modify();
}
/**
 * setId method comment.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(DbDefaultSpeedDataPeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbDefaultSpeedDataPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (1/1/2002 10:36:46 AM)
 * @param newLocale int
 */
public void setLocale(int newLocale) {
	locale = newLocale;
	modify();
}
}
