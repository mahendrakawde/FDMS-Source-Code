package com.aldorsolutions.webfdms.beans;

import java.sql.Timestamp;
import java.util.Hashtable;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;

public class WSGreeting extends Persistent {
	
	static final WSGreetingPeer peer = new WSGreetingPeer();
	private Timestamp dateTime ;
	private String greeting;
	@Override
	protected PersistentPeer getPersistentPeer() {
		
		return peer;
	}

	@Override
	public void restore(Transaction t, Hashtable data)
			throws PersistenceException {
		setDateTime((Timestamp)data.get(peer.DATE_TIME));
		setGreeting((String)data.get(peer.GREETING));
	}

	@Override
	public void setId(Hashtable h) {
		if(getId() == -1){
			setId(((Integer)h.get(peer.INDEXID)).intValue());
		}

	}

	@Override
	public boolean isLocked() {
		return false;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

}
