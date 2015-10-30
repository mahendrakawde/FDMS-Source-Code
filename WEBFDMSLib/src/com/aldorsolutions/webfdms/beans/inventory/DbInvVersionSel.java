package com.aldorsolutions.webfdms.beans.inventory;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;


/**
 * @author David Rollins
 * Date: Feb 23, 2007
 * File: DbInvVersionSel.java	
 * 
 */
public class DbInvVersionSel extends Persistent {
	static private final DbInvVersionSelPeer peer = new DbInvVersionSelPeer();

	private long invVersionID;

	private long localeID;

	private long locationID;

	private long companyID;

	/* (non-Javadoc)
	 * @see com.aldorsolutions.webfdms.database.Persistent#getPersistentPeer()
	 */
	protected PersistentPeer getPersistentPeer() {
		return peer;
	}

	/* (non-Javadoc)
	 * @see com.aldorsolutions.webfdms.database.PersistentI#isLocked()
	 */
	public boolean isLocked() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.aldorsolutions.webfdms.database.Persistent#restore(com.aldorsolutions.webfdms.database.Transaction, java.util.Hashtable)
	 */
	public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(DbInvVersionSelPeer.INVVERSIONSELID).toString()));
		invVersionID = FormatNumber.parseInteger(data.get(DbInvVersionSelPeer.INVVERSIONID).toString());
		localeID = FormatNumber.parseInteger(data.get(DbInvVersionSelPeer.LOCALEID).toString());
		locationID = FormatNumber.parseInteger(data.get(DbInvVersionSelPeer.LOCATIONID).toString());
		companyID = FormatNumber.parseInteger(data.get(DbInvVersionSelPeer.COMPANYID).toString());
	}

	/* (non-Javadoc)
	 * @see com.aldorsolutions.webfdms.database.Persistent#setId(java.util.Hashtable)
	 */
	public void setId(java.util.Hashtable h) {
		setId(((Long) h.get(DbInvVersionSelPeer.INVVERSIONSELID)).intValue());
	}

	/**
	 * @return the companyID
	 */
	public long getCompanyID() {
		return companyID;
	}

	/**
	 * @return the invVersionID
	 */
	public long getInvVersionID() {
		return invVersionID;
	}

	/**
	 * @return the localeID
	 */
	public long getLocaleID() {
		return localeID;
	}

	/**
	 * @return the locationID
	 */
	public long getLocationID() {
		return locationID;
	}

	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(long companyID) {
		this.companyID = companyID;
		modify();
	}

	/**
	 * @param invVersionID the invVersionID to set
	 */
	public void setInvVersionID(long invVersionID) {
		this.invVersionID = invVersionID;
		modify();
	}

	/**
	 * @param localeID the localeID to set
	 */
	public void setLocaleID(long localeID) {
		this.localeID = localeID;
		modify();
	}

	/**
	 * @param locationID the locationID to set
	 */
	public void setLocationID(long locationID) {
		this.locationID = locationID;
		modify();
	}
	
}