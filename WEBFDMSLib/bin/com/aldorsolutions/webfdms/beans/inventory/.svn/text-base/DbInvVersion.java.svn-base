package com.aldorsolutions.webfdms.beans.inventory;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatNumber;


/**
 * @author David Rollins
 * Date: Feb 23, 2007
 * File: DbInvVersion.java	
 * 
 */
public class DbInvVersion  extends Persistent
{
	private Logger logger = Logger.getLogger(DbInvVersion.class.getName());
	
	static private final DbInvVersionPeer peer = new DbInvVersionPeer();

	private String priceListID;
	private long companyID;
	private String name;
	private String description;
	private Date invFromDate;
	private Date invToDate;
	private boolean active;
	private Timestamp timestamp; 
	
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @return the companyID
	 */
	public long getCompanyID() {
		return companyID;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the invFromDate
	 */
	public Date getInvFromDate() {
		return invFromDate;
	}

	/**
	 * @return the invToDate
	 */
	public Date getInvToDate() {
		return invToDate;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the priceListID
	 */
	public String getPriceListID() {
		return priceListID;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
		modify();
	}

	/**
	 * @param companyID the companyID to set
	 */
	public void setCompanyID(long companyID) {
		this.companyID = companyID;
		modify();
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
		modify();
	}

	/**
	 * @param invFromDate the invFromDate to set
	 */
	public void setInvFromDate(Date invFromDate) {
		this.invFromDate = invFromDate;
		modify();
	}

	/**
	 * @param invToDate the invToDate to set
	 */
	public void setInvToDate(Date invToDate) {
		this.invToDate = invToDate;
		modify();
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
		modify();
	}

	/**
	 * @param priceListID the priceListID to set
	 */
	public void setPriceListID(String priceListID) {
		this.priceListID = priceListID;
		modify();
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
		modify();
	}

	/**
	 * getPersistentPeer method comment.
	 */
	protected PersistentPeer getPersistentPeer() {
		return peer;
	}

	/**
	 * isLocked method comment.
	 */
	public boolean isLocked() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.aldorsolutions.webfdms.database.Persistent#restore(com.aldorsolutions.webfdms.database.Transaction, java.util.Hashtable)
	 */
	public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(DbInvVersionPeer.INVVERSIONID).toString()));
		priceListID = data.get(DbInvVersionPeer.PRICELISTID).toString();
		companyID = FormatNumber.parseInteger(data.get(DbInvVersionPeer.COMPANYID).toString());
		name = (String) data.get(DbInvVersionPeer.NAME);
		description = (String) data.get(DbInvVersionPeer.DESCRIPTION);
		invFromDate = FormatDate.parseDate(data.get(DbInvVersionPeer.INVFROMDATE));
		invToDate = FormatDate.parseDate(data.get(DbInvVersionPeer.INVTODATE));
		active = Boolean.valueOf( data.get(DbInvVersionPeer.ACTIVE).toString() ).booleanValue();
		timestamp = FormatDate.parseTimestamp(data.get(DbInvVersionPeer.TIMESTAMP));
	}

	/* (non-Javadoc)
	 * @see com.aldorsolutions.webfdms.database.Persistent#setId(java.util.Hashtable)
	 */
	public void setId(java.util.Hashtable h) {
		Object obj = h.get(DbInvVersionPeer.INVVERSIONID);
		setId(((Long) obj).intValue());
	}
	
}
