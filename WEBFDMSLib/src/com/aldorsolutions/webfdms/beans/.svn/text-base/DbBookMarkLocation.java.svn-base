package com.aldorsolutions.webfdms.beans;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;


public class DbBookMarkLocation extends Persistent {
	
	private static Logger logger = Logger.getLogger(DbBookMarkLocation.class.getName());
	static private final DbBookMarkLocationPeer peer = new DbBookMarkLocationPeer();
	

	private int bookMarkId;
	private int locationId;
	private int localeId;
	
	
	
	/**
	 * @return the bookMarkId
	 */
	public int getBookMarkId() {
		return bookMarkId;
	}

	/**
	 * @param bookMarkId the bookMarkId to set
	 */
	public void setBookMarkId(int bookMarkId) {
		this.bookMarkId = bookMarkId;
	}

	/**
	 * @return the localeId
	 */
	public int getLocaleId() {
		return localeId;
	}

	/**
	 * @param localeId the localeId to set
	 */
	public void setLocaleId(int localeId) {
		this.localeId = localeId;
	}

	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public DbBookMarkLocation() {
		
	}
	
	public DbBookMarkLocation(int id, int bookmarkId, int locationId, 
			 int localeId) {
		setId(id);
		setBookMarkId(bookmarkId);
		setLocationId(locationId);
		setLocaleId(localeId);
		setNew();
	}
	
	protected PersistentPeer getPersistentPeer() {
		return peer;		
	}

	/**
	 * Move data from hashtable and copy into class variables Peer object
	 * restores from database to hashtable.
	 */
	public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(DbBookMarkLocationPeer.ID).toString()));
		bookMarkId = FormatNumber.parseInteger(data.get(DbBookMarkLocationPeer.BOOKMARKID).toString());
		locationId = FormatNumber.parseInteger(data.get(DbBookMarkLocationPeer.LOCATIONID).toString());
		localeId = FormatNumber.parseInteger(data.get(DbBookMarkLocationPeer.LOCALEID).toString());
	}
	
	public void setId(Hashtable h) {
		// TODO Auto-generated method stub

	}

	public boolean isLocked() {
		return false;
	}


}
