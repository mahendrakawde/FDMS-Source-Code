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
public class DbLocaleConfigType extends com.aldorsolutions.webfdms.database.Persistent {
	
//	private Logger logger = Logger.getLogger(DbLocaleConfigType.class.getName());

	final static DbLocaleConfigTypePeer peer = new DbLocaleConfigTypePeer();

	private String name;

	private String description;
	
	private int defaultValue;
	
	static public final int CONFIG_BILL_TO_PARTY_SCREEN_SHOW_CASH_SALE = 1;
	static public final int CONFIG_BILL_TO_PARTY_SCREEN_SHOW_REFUSED = 2;
	static public final int CONFIG_SERVICE_SCREEN_SHOW_RESTORATION = 3;
	static public final int CONFIG_FINANCE_SCREEN_SHOW_SERVICE_PLAN = 4;
	static public final int CONFIG_SERVICES_SCREEN_SHOW_ADDRESS_PALLBEAR = 5;
	static public final int CONFIG_SHOW_FORMS_COMPLETED = 6;
	static public final int CONFIG_SHOW_AT_NEED_CHECKLIST = 7;
	static public final int CONFIG_SHOW_AFTER_CARE_CHECKLIST = 8;
	static public final int CONFIG_SHOW_BOOKMARKS = 9;
	static public final int CONFIG_ORDER_BY_CONT_LINE_ON_FINANCIAL_PAGE = 10;

	/**
	 * Move data from hashtable and copy into class variables Peer object
	 * restores from database to hashtable.
	 */

	public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(DbLocaleConfigTypePeer.IDENTITY).toString()));
		name = (String) data.get(DbLocaleConfigTypePeer.NAME);
		description = (String) data.get(DbLocaleConfigTypePeer.DESCRIPTION);
		defaultValue = FormatNumber.parseInteger(data.get(DbLocaleConfigTypePeer.DEFAULTVALUE).toString());
		
	}
	
	public void setId(java.util.Hashtable h) {
		setId(((Integer) h.get(DbLocaleConfigTypePeer.IDENTITY)).intValue());
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
	
	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public int getDefaultValue() {
		return defaultValue;
	}

	public void setDescription(String description) {
		this.description = description;
		modify();
	}

	public void setName(String name) {
		this.name = name;
		modify();
	}

	public void setDefaultValue(int defaultValue) {
		this.defaultValue = defaultValue;
		modify();
	}

}
