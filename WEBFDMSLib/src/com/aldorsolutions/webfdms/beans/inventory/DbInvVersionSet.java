package com.aldorsolutions.webfdms.beans.inventory;

import com.aldorsolutions.webfdms.database.PersistentSet;
import com.aldorsolutions.webfdms.database.PersistentSetPeer;

/**
 * @author David Rollins 
 * Date: Feb 23, 2007 
 * File: DbInvVersionSet.java
 * 
 */
public class DbInvVersionSet extends PersistentSet {
	final static DbInvVersionSetPeer peer = new DbInvVersionSetPeer();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aldorsolutions.webfdms.database.PersistentSet#getPersistentSetPeer()
	 */
	protected PersistentSetPeer getPersistentSetPeer() {
		return peer;
	}
}