package com.aldorsolutions.webfdms.beans;

import java.util.Hashtable;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbSpeedData - Holds lists of frequently entered information.
 * Creation date: (1/1/2002 10:31:09 AM)
 * @author: 
 */
public class DbSpeedData extends Persistent {
		
	private String category;
	private int locale;
	private int locationId;
	private String data;
	private int sortSequence;
	
/**
 * DbSpeedData constructor comment.
 */
public DbSpeedData() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (1/1/2002 10:36:38 AM)
 * @return String
 */
public String getCategory() {
	return category;
}
/**
 * Insert the method's description here.
 * Creation date: (1/1/2002 10:37:00 AM)
 * @return String
 */
public String getData() {
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
protected PersistentPeer getPersistentPeer() {
	return new DbSpeedDataPeer();
}
/**
 * Insert the method's description here.
 * Creation date: (7/17/2003 10:05:28 AM)
 * @return int
 */
public int getSortSequence() {
	return sortSequence;
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
public void restore(Transaction t, Hashtable data) throws PersistenceException {
	setId(			FormatNumber.parseInteger(data.get(DbSpeedDataPeer.IDENTITY).toString()));
	this.category		= (String)data.get(DbSpeedDataPeer.CATEGORY);
	this.locale			= FormatNumber.parseInteger(data.get(DbSpeedDataPeer.LOCALE).toString());
	this.data			= (String)data.get(DbSpeedDataPeer.DATA);
	this.sortSequence	= 	FormatNumber.parseInteger(data.get(DbSpeedDataPeer.SORT).toString());
	this.locationId 	= FormatNumber.parseInteger(data.get(DbSpeedDataPeer.LOCATIONID).toString());
	
	if ( this.category != null ) {
		//this.category = this.category.toUpperCase();
	}
	
}
/**
 * Insert the method's description here.
 * Creation date: (1/1/2002 10:36:38 AM)
 * @param newCategory String
 */
public void setCategory(String newCategory) {
	category = newCategory;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (1/1/2002 10:37:00 AM)
 * @param newData String
 */
public void setData(String newData) {
	data = newData;
	modify();
}
/**
 * setId method comment.
 */
public void setId(Hashtable h) {
	//if (h.containsKey(DbSpeedDataPeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbSpeedDataPeer.IDENTITY)).intValue());
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
/**
 * Insert the method's description here.
 * Creation date: (7/17/2003 10:05:28 AM)
 * @param newSortSequence int
 */
public void setSortSequence(int newSortSequence) {
	sortSequence = newSortSequence;
	modify();
}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
}
