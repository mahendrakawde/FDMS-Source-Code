package com.aldorsolutions.webfdms.beans;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;

public class DbBookMark extends Persistent {
	
	private static Logger logger = Logger.getLogger(DbBookMark.class.getName());
	static private final DbBookMarkPeer peer = new DbBookMarkPeer();
	
	private String name;
	private String description;
	private String link;
	private short order;
	
	
	public DbBookMark() {
		setOrder((short) -9999);
	}
	
	public DbBookMark(String name, String description, String link, 
			int locationId, int locale) {
		setName(name);
		setDescription(description);
		setLink(link);
		setOrder((short) -9999);
		setNew();
	}
	
	protected PersistentPeer getPersistentPeer() {
		// TODO Auto-generated method stub
		return peer;
		
	}

	/**
	 * Move data from hashtable and copy into class variables Peer object
	 * restores from database to hashtable.
	 */
	public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(DbBookMarkPeer.ID).toString()));
		name = data.get(DbBookMarkPeer.NAME).toString();
		description = data.get(DbBookMarkPeer.DESCRIPTION).toString();
		link = data.get(DbBookMarkPeer.LINK).toString();
	//	locationId = FormatNumber.parseInteger((String)data.get(DbBookMarkPeer.LOCATIONID).toString());
	//	locale = FormatNumber.parseInteger(data.get(DbBookMarkPeer.LOCALE).toString());
		order = FormatNumber.parseShort((String) data.get(DbBookMarkPeer.ORDER).toString());
	
	}
	
/*	public void restore(Transaction t, Hashtable data) throws PersistenceException {
		// TODO Auto-generated method stub

	}
*/
	public void setId(Hashtable h) {
		// TODO Auto-generated method stub

	}

	public boolean isLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the locale
	 */


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the order
	 */
	public short getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(short order) {
		this.order = order;
	}

}
