package com.aldorsolutions.webfdms.beans.inventory;

import java.util.Hashtable;

import com.aldorsolutions.webfdms.database.DatabaseSetPeer;

/**
 * @author David Rollins
 * Date: Feb 23, 2007
 * File: DbInvVersionSelSetPeer.java	
 * 
 */
public class DbInvVersionSelSetPeer extends DatabaseSetPeer {
	
	/* (non-Javadoc)
	 * @see com.aldorsolutions.webfdms.database.DatabaseSetPeer#getPersistentClass(Hashtable)
	 */
	public String getPersistentClass(Hashtable h) {
		return "com.aldorsolutions.webfdms.beans.inventory.DbInvVersionSel";
	}

	/* (non-Javadoc)
	 * @see com.aldorsolutions.webfdms.database.DatabaseSetPeer#getSql(Hashtable)
	 */
	public String getSql(Hashtable h) {
		
		boolean needand = false;
		if (h == null) {
			// If no query criteria are specified select all active records
			return "SELECT * FROM invversionsel ";
		} else {
			
			StringBuffer tmp = new StringBuffer();
			tmp.append("SELECT * FROM invversionsel Where ");
			if (h.containsKey(DbInvVersionSelPeer.LOCALEID)) {
				tmp.append(DbInvVersionSelPeer.LOCALEID + " = ");
				tmp.append(h.get(DbInvVersionSelPeer.LOCALEID).toString());
				needand = true;
			}
			
			if (h.containsKey(DbInvVersionSelPeer.INVVERSIONID)) {
				if (needand) {
					tmp.append(" AND ");
				}
				tmp.append(DbInvVersionSelPeer.INVVERSIONID + " = ");
				tmp.append(h.get(DbInvVersionSelPeer.INVVERSIONID).toString());
				needand = true;
			}
			
			if (h.containsKey(DbInvVersionSelPeer.LOCATIONID)) {
				if (needand) {
					tmp.append(" AND ");
				}
				tmp.append(DbInvVersionSelPeer.LOCATIONID + " = ");
				tmp.append(h.get(DbInvVersionSelPeer.LOCATIONID).toString());
				needand = true;
			}
			
			if (h.containsKey(DbInvVersionSelPeer.COMPANYID)) {
				if (needand) {
					tmp.append(" AND ");
				}
				tmp.append(DbInvVersionSelPeer.COMPANYID + " = ");
				tmp.append(h.get(DbInvVersionSelPeer.COMPANYID).toString());
				needand = true;
			}
			
			return tmp.toString();
		}
	}
}
