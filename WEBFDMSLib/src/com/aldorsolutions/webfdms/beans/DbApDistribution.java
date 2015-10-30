package com.aldorsolutions.webfdms.beans;

import java.sql.Date;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * DbApDistribution - This class contains the expense accounts for each check.
 * Creation date: (5/2/2002 2:42:35 PM)
 * @author: 
 */
public class DbApDistribution extends Persistent
{
	static private final DbApDistributionPeer peer = new DbApDistributionPeer();

	private int masterID;
	private int accountID;
	private int amount;
	private java.lang.String tranType;
	private Date printedDate;
	private java.lang.String posted;
	private java.lang.String memo;
/**
 * Insert the method's description here.
 * Creation date: (5/2/2002 2:08:37 PM)
 * @return int
 */
public int getAccountID() {
	return accountID;
}
/**
 * Insert the method's description here.
 * Creation date: (5/2/2002 2:08:48 PM)
 * @return int
 */
public int getAmount() {
	return amount;
}
/**
 * Insert the method's description here.
 * Creation date: (5/2/2002 2:08:20 PM)
 * @return int
 */
public int getMasterID() {
	return masterID;
}
/**
 * Insert the method's description here.
 * Creation date: (5/2/2002 2:09:20 PM)
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
 * Insert the method's description here.
 * Creation date: (5/2/2002 2:09:11 PM)
 * @return java.lang.String
 */
public java.lang.String getPosted() {
	return posted;
}
/**
 * Insert the method's description here.
 * Creation date: (5/2/2002 2:08:59 PM)
 * @return java.lang.String
 */
public java.lang.String getTranType() {
	return tranType;
}
public Date getPrintedDate() {
	return printedDate;
}

/**
 * isLocked method comment.
 */
public boolean isLocked() {
	return false;
}
/**
 * Move data from hashtable and copy into class variables
 * Peer object restores from database to hashtable.
 */
public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
	setId(			FormatNumber.parseInteger(data.get(DbApDistributionPeer.IDENTITY).toString()));
	masterID 		= FormatNumber.parseInteger(data.get(DbApDistributionPeer.MASTERID).toString());
	accountID 		= FormatNumber.parseInteger(data.get(DbApDistributionPeer.ACCTID).toString());
	amount	 		= FormatNumber.parseInteger(data.get(DbApDistributionPeer.AMOUNT).toString());
	tranType	 	= data.get(DbApDistributionPeer.TRANTYPE).toString();
	try {
		printedDate = (Date)data.get(DbApDistributionPeer.PRINTEDDATE);
	} catch (Exception e) {
		printedDate = null;
	}
	posted		 	= data.get(DbApDistributionPeer.POSTED).toString();
	memo		 	= data.get(DbApDistributionPeer.MEMO).toString();
}
/**
 * Insert the method's description here.
 * Creation date: (5/2/2002 2:08:37 PM)
 * @param newAccountID int
 */
public void setAccountID(int newAccountID) {
	accountID = newAccountID;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (5/2/2002 2:08:48 PM)
 * @param newAmount int
 */
public void setAmount(int newAmount) {
	amount = newAmount;
	modify();
}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(peer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbApDistributionPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (5/2/2002 2:08:20 PM)
 * @param newMasterID int
 */
public void setMasterID(int newMasterID) {
	masterID = newMasterID;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (5/2/2002 2:09:20 PM)
 * @param newMemo java.lang.String
 */
public void setMemo(java.lang.String newMemo) {
	memo = newMemo;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (5/2/2002 2:09:11 PM)
 * @param newPosted java.lang.String
 */
public void setPosted(java.lang.String newPosted) {
	modify();
	posted = newPosted;
}
/**
 * Insert the method's description here.
 * Creation date: (5/2/2002 2:08:59 PM)
 * @param newTranType java.lang.String
 */
public void setTranType(java.lang.String newTranType) {
	modify();
	tranType = newTranType;
}


public void setPrintedDate(Date printedDate) {
	modify();
	this.printedDate = printedDate;
}
}
