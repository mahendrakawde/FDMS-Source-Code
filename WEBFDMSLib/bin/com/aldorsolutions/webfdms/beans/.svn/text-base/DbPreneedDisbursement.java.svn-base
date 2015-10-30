package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * Workfile: DbPreneedDisbursement.java
 * Date: Nov 11, 2005 3:48:18 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class DbPreneedDisbursement extends com.aldorsolutions.webfdms.database.Persistent {
	
	final static DbPreneedDisbursementPeer peer = new DbPreneedDisbursementPeer();
	
	private int disbursementId;
	private int vitalsId;
	private String label;
	private double value;
	
	public DbPreneedDisbursement() { }

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
		modify();
	}

	public double getValue() {
		return value;
	}
	
	public String getValueFormatted() {
		return FormatCurrency.toCurrency(value);
	}

	public void setValue(double value) {
		this.value = value;
		modify();
	}

	public int getVitalsId() {
		return vitalsId;
	}

	public void setVitalsId(int vitalsId) {
		this.vitalsId = vitalsId;
		modify();
	}

	public int getDisbursementId() {
		return disbursementId;
	}

	public void setDisbursementId(int disbursementId) {
		this.disbursementId = disbursementId;
		modify();
	}

	public void setId(java.util.Hashtable h) {
		setId(((Integer)h.get(DbPreneedPeer.IDENTITY)).intValue());
	}	
	
	protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
		return peer;
	}
	
	public boolean isLocked() {
		return false;
	}

	public void restore(Transaction t, java.util.Hashtable data) 
		throws PersistenceException {
		
		setId(FormatNumber.parseInteger(data.get(DbPreneedDisbursementPeer.IDENTITY).toString()));
		setDisbursementId(FormatNumber.parseInteger(data.get(DbPreneedDisbursementPeer.IDENTITY).toString()));
		setVitalsId(FormatNumber.parseInteger(data.get(DbPreneedDisbursementPeer.VITALSMASTERKEY).toString()));
		setLabel((String)data.get(DbPreneedDisbursementPeer.LABEL));
		setValue(FormatNumber.parseDouble(data.get(DbPreneedDisbursementPeer.VALUE).toString()));
	}
}
