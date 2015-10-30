package com.aldorsolutions.webfdms.beans;

import java.sql.Timestamp;
import java.util.Hashtable;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * DbApDistribution - This class contains the expense accounts for each check.
 * Creation date: (5/2/2002 2:42:35 PM)
 * 
 * @author:
 */
public class DbApDistributionHistory extends Persistent {
	static private final DbApDistributionHistoryPeer peer = new DbApDistributionHistoryPeer();
	
	static public final String INVOICE_ITEM 	= "L";
	static public final String INVOICE   	= "I";
	static public final String CHECK     	= "C";
		// S - is for the stub items or check items
	static public final String CHECK_ITEM	= "S";
	
	private int apDistHistID;
	private int apMasterID;
	private String apAccountNumber;
	private int amount;
	private int discountAmount;
	private String type;
	private String posted;
	private String memo;
	private Timestamp createdDTS;

	/**
	 * Insert the method's description here. Creation date: (5/2/2002 2:08:48 PM)
	 * 
	 * @return int
	 */
	public int getAmount() {
		return amount;
		
	}

	/**
	 * Insert the method's description here. Creation date: (5/2/2002 2:09:20 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getMemo() {
		return memo;
	}

	/**
	 * getPersistentPeer method comment.
	 */
	protected PersistentPeer getPersistentPeer() {
		return peer;
	}

	/**
	 * Insert the method's description here. Creation date: (5/2/2002 2:09:11 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPosted() {
		return posted;
	}

	/**
	 * isLocked method comment.
	 */
	public boolean isLocked() {
		return false;
	}

	/**
	 * Move data from hashtable and copy into class variables Peer object restores
	 * from database to hashtable.
	 */
	public void restore(Transaction t, Hashtable data) throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(DbApDistributionHistoryPeer.IDENTITY).toString()));
		apMasterID = FormatNumber.parseInteger(data.get(DbApDistributionHistoryPeer.APMASTERID).toString());
		apAccountNumber = data.get(DbApDistributionHistoryPeer.APACCOUNTNUMBER).toString();
		amount = FormatNumber.parseInteger(data.get(DbApDistributionHistoryPeer.AMOUNT).toString());
		discountAmount = FormatNumber.parseInteger(data.get(DbApDistributionHistoryPeer.DISCOUNT_AMOUNT).toString());
		type = data.get(DbApDistributionHistoryPeer.TYPE).toString();
		posted = data.get(DbApDistributionHistoryPeer.POSTED).toString();
		memo = data.get(DbApDistributionHistoryPeer.MEMO).toString();
		createdDTS = (Timestamp) data.get(DbApDistributionHistoryPeer.CREATEDDTS);
	}

	/**
	 * Insert the method's description here. Creation date: (5/2/2002 2:08:48 PM)
	 * 
	 * @param newAmount
	 *          int
	 */
	public void setAmount(int newAmount) {
		amount = newAmount;
		modify();
	}

	/**
	 * Get the ID key for this object from the hashtable. DbUser objects can be
	 * accessed by "Name" So, first see if "Name" is being used for restoring or
	 * if ID# is being used.
	 */
	public void setId(Hashtable h) {
		// if (h.containsKey(peer.NAME)){
		// getPersistentPeer().restore(this, t);
		// }
		setId(((Integer) h.get(DbApDistributionHistoryPeer.IDENTITY)).intValue());
	}

	/**
	 * Insert the method's description here. Creation date: (5/2/2002 2:09:20 PM)
	 * 
	 * @param newMemo
	 *          java.lang.String
	 */
	public void setMemo(java.lang.String newMemo) {
		memo = newMemo;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (5/2/2002 2:09:11 PM)
	 * 
	 * @param newPosted
	 *          java.lang.String
	 */
	public void setPosted(java.lang.String newPosted) {
		modify();
		posted = newPosted;
	}

	public int getApDistHistID() {
		return apDistHistID;
	}

	public void setApDistHistID(int apDistHistID) {
		modify();
		this.apDistHistID = apDistHistID;
	}

	public int getApMasterID() {
		return apMasterID;
	}

	public void setApMasterID(int apMasterID) {
		modify();
		this.apMasterID = apMasterID;
	}

	public Timestamp getCreatedDTS() {
		return createdDTS;
	}

	public void setCreatedDTS(Timestamp createdDTS) {
		modify();
		this.createdDTS = createdDTS;
	}

	/**
	 * @return the apAccountID
	 */
	public String getApAccountNumber() {
		return apAccountNumber;
	}

	/**
	 * @param apAccountID the apAccountID to set
	 */
	public void setApAccountNumber(String apAccountNumber) {
		modify();
		this.apAccountNumber = apAccountNumber;
	}

	/**
	 * @return the discountAmount
	 */
	public int getDiscountAmount() {
	return discountAmount;
	}

	/**
	 * @param discountAmount the discountAmount to set
	 */
	public void setDiscountAmount(int discountAmount) {
	this.discountAmount = discountAmount;
	}

	/**
	 * @return the type
	 */
	public String getType() {
	return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
	modify();
	this.type = type;
	}
}
