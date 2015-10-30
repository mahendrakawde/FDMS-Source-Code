package com.aldorsolutions.webfdms.beans;

import java.sql.Timestamp;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;


public class DbPasswordLog extends com.aldorsolutions.webfdms.database.Persistent {
	static private final DbPasswordLogPeer peer = new DbPasswordLogPeer();

	private long userID = 0;

	private String password = null;

	private Timestamp tmstmp = null;

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
	 * Move data from hashtable and copy into class variables Peer object
	 * restores from database to hashtable.
	 */
	public void restore(Transaction t, java.util.Hashtable data)
			throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(DbPasswordLogPeer.PASSWORDLOGID)
				.toString()));
		
		userID = FormatNumber.parseInteger(data.get(DbPasswordLogPeer.USERID).toString());
		password = (String) data.get(DbPasswordLogPeer.PASSWORD);
		tmstmp = getTimestamp ( data.get(DbPasswordLogPeer.TMSTMP) );
	}

	private Timestamp getTimestamp(Object value) {
		
		if ( value ==  null ) {
			return null;
		}
		
		String strVal = value.toString();

		if (strVal != null && strVal.equals("")) {
			return (null);
		} else {
			return ((Timestamp) value);
		}

	}
	
	/**
	 * Get the ID key for this object from the hashtable. DbUser objects can be
	 * accessed by "Name" So, first see if "Name" is being used for restoring or
	 * if ID# is being used.
	 */
	public void setId(java.util.Hashtable h) {
		setId(((Long) h.get(DbPasswordLogPeer.PASSWORDLOGID)).intValue());
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
		modify();	
	}
	
	/**
	 * @return Returns the userID.
	 */
	public long getUserID() {
		return userID;
	}

	/**
	 * @param userID The userID to set.
	 */
	public void setUserID(long userID) {
		this.userID = userID;
		modify();
	}

	/**
	 * @return Returns the passwordLogID.
	 */
	public int getPasswordLogID() {
		return getId();
	}

	/**
	 * @return Returns the tmstmp.
	 */
	public Timestamp getTmstmp() {
		return tmstmp;
	}

	/**
	 * @param tmstmp
	 *            The tmstmp to set.
	 */
	public void setTmstmp(Timestamp tmstmp) {
		this.tmstmp = tmstmp;
		modify();
	}
	
}
