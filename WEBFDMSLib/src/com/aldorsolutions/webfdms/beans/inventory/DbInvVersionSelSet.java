package com.aldorsolutions.webfdms.beans.inventory;

import com.aldorsolutions.webfdms.database.PersistentSet;
import com.aldorsolutions.webfdms.database.PersistentSetPeer;

/**
 * @author David Rollins
 * Date: Feb 23, 2007
 * File: DbInvVersionSelSet.java	
 * 
 */
public class DbInvVersionSelSet extends PersistentSet {
	final static DbInvVersionSelSetPeer peer = new DbInvVersionSelSetPeer();

	/* (non-Javadoc)
	 * @see PersistentSet#getPersistentSetPeer()
	 */
	protected PersistentSetPeer getPersistentSetPeer() {
		return peer;
	}
}
