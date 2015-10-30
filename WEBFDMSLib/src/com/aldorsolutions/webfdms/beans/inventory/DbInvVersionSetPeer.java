package com.aldorsolutions.webfdms.beans.inventory;

import com.aldorsolutions.webfdms.database.DatabaseSetPeer;

/**
 * @author David Rollins
 * Date: Feb 23, 2007
 * File: DbInvVersionSetPeer.java	
 * 
 */
public class DbInvVersionSetPeer extends DatabaseSetPeer {
	
	/* (non-Javadoc)
	 * @see com.aldorsolutions.webfdms.database.DatabaseSetPeer#getPersistentClass(java.util.Hashtable)
	 */
	public String getPersistentClass(java.util.Hashtable h) {
		return "com.aldorsolutions.webfdms.beans.inventory.DbInvVersion";
	}

	/* (non-Javadoc)
	 * @see com.aldorsolutions.webfdms.database.DatabaseSetPeer#getSql(java.util.Hashtable)
	 */
	public String getSql(java.util.Hashtable h) {
		boolean needand = false;
		if (h == null) {
			// If no query criteria are specified select all active records
			return "SELECT * FROM invversion ";
		} else {
			
			StringBuffer tmp = new StringBuffer();
			tmp.append("SELECT * FROM invversion Where ");
			if (h.containsKey(DbInvVersionPeer.INVVERSIONID)) {
				tmp.append(DbInvVersionPeer.INVVERSIONID + " = ");
				tmp.append(h.get(DbInvVersionPeer.INVVERSIONID).toString());
				needand = true;
			}
			
			if (h.containsKey(DbInvVersionPeer.COMPANYID)) {
				if (needand) {
					tmp.append(" AND ");
				}
				tmp.append(DbInvVersionPeer.COMPANYID + " = ");
				tmp.append(h.get(DbInvVersionPeer.COMPANYID).toString());
				needand = true;
			}
			
			if (h.containsKey(DbInvVersionPeer.PRICELISTID)) {
				if (needand) {
					tmp.append(" AND ");
				}
				tmp.append(DbInvVersionPeer.PRICELISTID + " = ");
				tmp.append(h.get(DbInvVersionPeer.PRICELISTID).toString());
				needand = true;
			}

			if (h.containsKey(DbInvVersionPeer.NAME)) {
				if (needand) {
					tmp.append(" AND ");
				}
				tmp.append(DbInvVersionPeer.NAME + " = '");
				tmp.append(h.get(DbInvVersionPeer.NAME).toString() + "'");
				needand = true;
			}
			
			tmp.append(" order by " + DbInvVersionPeer.NAME);
			
			return tmp.toString();
		}
	}
}
