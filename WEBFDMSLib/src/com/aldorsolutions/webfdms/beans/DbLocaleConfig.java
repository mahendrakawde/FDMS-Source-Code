package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * A DbLocale represents one region location. The peer class provides the class
 * name and SQL for restoring Creation date: (11/26/2001 11:14:33 AM)
 * 
 * @author:
 */
public class DbLocaleConfig extends com.aldorsolutions.webfdms.database.Persistent {
	
//	private Logger logger = Logger.getLogger(DbLocaleConfig.class.getName());

	final static DbLocaleConfigPeer peer = new DbLocaleConfigPeer();

	private int localeID;

	private int localeConfigTypeID;

	private int value;


	/**
	 * Move data from hashtable and copy into class variables Peer object
	 * restores from database to hashtable.
	 */

	public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(DbLocaleConfigPeer.IDENTITY).toString()));
		localeID = FormatNumber.parseInteger(data.get(DbLocaleConfigPeer.LOCALEID).toString());
		localeConfigTypeID = FormatNumber.parseInteger(data.get(DbLocaleConfigPeer.LOCALECFGTYPEID).toString());
		value = FormatNumber.parseInteger(data.get(DbLocaleConfigPeer.VALUE).toString());
	}
	
	public void setId(java.util.Hashtable h) {
		setId(((Integer) h.get(DbLocaleConfigPeer.IDENTITY)).intValue());
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
		return locked;
	}

	public int getLocaleID() {
		return localeID;
	}

	public int getValue() {
		return value;
	}
	
	public int getLocaleConfigTypeID() {
		return localeConfigTypeID;
	}

	public void setLocaleID(int localeID) {
		this.localeID = localeID;
		modify();
	}

	public void setValue(int value) {
		this.value = value;
		modify();
	}

	public void setLocaleConfigTypeID(int localeConfigTypeID) {
		this.localeConfigTypeID = localeConfigTypeID;
		modify();
	}

}
