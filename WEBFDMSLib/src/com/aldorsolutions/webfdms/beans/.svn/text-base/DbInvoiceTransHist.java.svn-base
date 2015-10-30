package com.aldorsolutions.webfdms.beans;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Hashtable;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatString;


/**
 * DbInvoice - This class represents the information for one invoice
 * Creation date: (12/27/2002
 * 2:42:35 PM)
 * 
 * @author:
 */
public class DbInvoiceTransHist extends Persistent {
	static private final DbInvoiceTransHistPeer peer = new DbInvoiceTransHistPeer();


	public static final int INVOICE_TYPE = 0;
	public static final int LINEITEM_TYPE = 1;
	
	
	private int invoiceTransHistoryID;
	private int invoiceID;
	private long locationID;
	private int type;
	private String description;
	private long amount;
	private String glAccount;
	private Date transactionDate;
	private char posted;
	private Timestamp createdDTS;
	private Timestamp updatedDTS;


	protected PersistentPeer getPersistentPeer() {
		return peer;
	}

	public boolean isLocked() {
		return false;
	}
	
	/**
	 * Move data from hashtable and copy into class variables Peer object restores
	 * from database to hashtable.
	 */
	public void restore(Transaction t, Hashtable data) throws PersistenceException {

		setId(Integer.parseInt(data.get(DbInvoiceTransHistPeer.INVOICETRANSHISTORYID).toString()));
		invoiceTransHistoryID = Integer.parseInt(data.get(DbInvoiceTransHistPeer.INVOICETRANSHISTORYID).toString());
		invoiceID = Integer.parseInt(data.get(DbInvoiceTransHistPeer.INVOICEID).toString());
		locationID = Integer.parseInt(data.get(DbInvoiceTransHistPeer.LOCATIONID).toString());
		type = Integer.parseInt(data.get(DbInvoiceTransHistPeer.TYPE).toString());		
		description = data.get(DbInvoiceTransHistPeer.DESCRIPTION).toString();
		amount = Long.parseLong(data.get(DbInvoiceTransHistPeer.AMOUNT).toString());
		glAccount = data.get(DbInvoiceTransHistPeer.GLACCOUNT).toString();
		transactionDate = (Date)data.get(DbInvoiceTransHistPeer.TRANSACTIONDATE);
		posted = FormatString.getFirstChar(data.get(DbInvoiceTransHistPeer.POSTED).toString());
		try {
			createdDTS = (Timestamp)data.get(DbInvoiceTransHistPeer.CREATEDDTS);
		} catch (ClassCastException e) {
			createdDTS = null;
		}
		try {
			updatedDTS = (Timestamp)data.get(DbInvoiceTransHistPeer.UPDATEDDTS);
		} catch (ClassCastException e) {
			updatedDTS = null;
		}
	}

	public void setId(Hashtable h) {
		setId(((Integer) h.get(DbInvoiceTransHistPeer.INVOICETRANSHISTORYID)).intValue());
	}

	public int getInvoiceTransHistoryID() {
		return invoiceTransHistoryID;
	}

	public void setInvoiceTransHistoryID(int invoiceTransHistoryID) {
		this.invoiceTransHistoryID = invoiceTransHistoryID;
		modify();
	}

	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
		modify();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		modify();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		modify();
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
		modify();
	}

	public String getGlAccount() {
		return glAccount;
	}

	public void setGlAccount(String glAccount) {
		this.glAccount = glAccount;
		modify();
	}

	public char getPosted() {
		return posted;
	}

	public void setPosted(char posted) {
		this.posted = posted;
		modify();
	}

	public Timestamp getCreatedDTS() {
		return createdDTS;
	}

	public void setCreatedDTS(Timestamp createdDTS) {
		this.createdDTS = createdDTS;
		modify();
	}

	public Timestamp getUpdatedDTS() {
		return updatedDTS;
	}

	public void setUpdatedDTS(Timestamp updatedDTS) {
		this.updatedDTS = updatedDTS;
		modify();
	}

	public long getLocationID() {
		return locationID;
	}

	public void setLocationID(long locationID) {
		this.locationID = locationID;
		modify();
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
		modify();
	}

}
