package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbInvHistory - This class represents merchandise item receipt or sell transaction.
 * Creation date: (12/27/2001 2:42:35 PM)
 * @author: 
 */
public class DbInvHistory extends com.aldorsolutions.webfdms.database.Persistent
{
	static private final DbInvHistoryPeer peer = new DbInvHistoryPeer();
	static public final String TRAN_TYPE_FDMS_SALE 	= "S";
	static public final String TRAN_TYPE_INV_SALE 	= "D";
	static public final String TRAN_TYPE_RECEIVED 	= "R";
	static public final String TRAN_TYPE_XFERIN		= "T";
	static public final String TRAN_TYPE_XFEROUT	= "X";
	static public final String TRAN_TYPE_ADJUSTMENT	= "A";
	

	private String	cItemName;	//  Converted from char[15]
	private int		lSequenceNumber;
	private String	cSerialNumber;	//  Converted from char[15]
	private String	cTranType;
	private String	cTransactionDate;	//  Converted from char[8]
	private String	cReferenceNumber;	//  Converted from char[10]
	private int		lVitalsNumber;
	private String	cLocation;	//  Converted from char[25]
	private int		lQuantity;
	private int		lSellingPrice;
	private int		lPurchaseCost;
	private String	cGLsalesAcct;	//  Converted from char[10]
	private String	cGLassetAcct;	//  Converted from char[10]
	private String	cGLcostAcct;	//  Converted from char[10]
	private String	cDescription;	//  Converted from char[30]
	private String	cUserInit;	//  Converted from char[3]
	private BTRIEVE_DATE	DateModified;
	private BTRIEVE_TIME	TimeModified;
	private String reason;

	private int masterId;
	public String getCDescription()
	{
	return cDescription;
	}
	public String getCGLassetAcct()
	{
	return cGLassetAcct;
	}
	public String getCGLcostAcct()
	{
	return cGLcostAcct;
	}
	public String getCGLsalesAcct()
	{
	return cGLsalesAcct;
	}
	public String getCItemName()
	{
	return cItemName;
	}
	public String getCLocation()
	{
	return cLocation;
	}
	public String getCReferenceNumber()
	{
	return cReferenceNumber;
	}
	public String getCSerialNumber()
	{
	return cSerialNumber;
	}
	public String getCTransactionDate()
	{
	return cTransactionDate;
	}
	public String getCTranType()
	{
	return cTranType;
	}
	public String getCUserInit()
	{
	return cUserInit;
	}
	public BTRIEVE_DATE getDateModified()
	{
	return DateModified;
	}
	public int getLPurchaseCost()
	{
	return lPurchaseCost;
	}
	public int getLQuantity()
	{
	return lQuantity;
	}
	public int getLSellingPrice()
	{
	return lSellingPrice;
	}
	public int getLSequenceNumber()
	{
	return lSequenceNumber;
	}
	public int getLVitalsNumber()
	{
	return lVitalsNumber;
	}
/**
 * Insert the method's description here.
 * Creation date: (12/31/2001 9:37:12 AM)
 * @return int
 */
public int getMasterId() {
	return masterId;
}
/**
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
	return peer;
}
	public BTRIEVE_TIME getTimeModified()
	{
	return TimeModified;
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
	setId(	FormatNumber.parseInteger(data.get(DbInvHistoryPeer.IDENTITY).toString()));
	cItemName		 = data.get(DbInvHistoryPeer.ITEMNAME).toString();
	lSequenceNumber	 =	FormatNumber.parseInteger(data.get(DbInvHistoryPeer.SEQUENCENO).toString());
	cSerialNumber	 = data.get(DbInvHistoryPeer.SERIALNO).toString();
	cTranType 		 = data.get(DbInvHistoryPeer.TRANTYPE).toString();
	cTransactionDate = data.get(DbInvHistoryPeer.TRANDATE).toString();
	cReferenceNumber = data.get(DbInvHistoryPeer.REFERENCENO).toString();
	lVitalsNumber 	 = FormatNumber.parseInteger(data.get(DbInvHistoryPeer.VITALSKEY).toString());
	cLocation		 = data.get(DbInvHistoryPeer.LOCATION).toString();
	lQuantity		 = FormatNumber.parseInteger(data.get(DbInvHistoryPeer.QUANTITY).toString());
	lSellingPrice	 = FormatNumber.parseInteger(data.get(DbInvHistoryPeer.PRICE).toString());
	lPurchaseCost	 = FormatNumber.parseInteger(data.get(DbInvHistoryPeer.COST).toString());
	cGLsalesAcct	 = data.get(DbInvHistoryPeer.SALESACCT).toString();
	cGLassetAcct	 = data.get(DbInvHistoryPeer.ASSETACCT).toString();
	cGLcostAcct		 = data.get(DbInvHistoryPeer.COSTACCT).toString();
	cDescription	 = data.get(DbInvHistoryPeer.DESCRIPTION).toString();
	cUserInit		 = data.get(DbInvHistoryPeer.USERINITIALS).toString();
	masterId		 = FormatNumber.parseInteger(data.get(DbInvHistoryPeer.MASTERID).toString());
	reason			 = data.get(DbInvHistoryPeer.REASON).toString();
}
	public void setCDescription(String lcl_arg0)
	{
	cDescription = lcl_arg0;
	modify();
	}
	public void setCGLassetAcct(String lcl_arg0)
	{
	cGLassetAcct = lcl_arg0;
	modify();
	}
	public void setCGLcostAcct(String lcl_arg0)
	{
	cGLcostAcct = lcl_arg0;
	modify();
	}
	public void setCGLsalesAcct(String lcl_arg0)
	{
	cGLsalesAcct = lcl_arg0;
	modify();
	}
	public void setCItemName(String lcl_arg0)
	{
	cItemName = lcl_arg0;
	modify();
	}
	public void setCLocation(String lcl_arg0)
	{
	cLocation = lcl_arg0;
	modify();
	}
	public void setCReferenceNumber(String lcl_arg0)
	{
	cReferenceNumber = lcl_arg0;
	modify();
	}
	public void setCSerialNumber(String lcl_arg0)
	{
	cSerialNumber = lcl_arg0;
	modify();
	}
	public void setCTransactionDate(String lcl_arg0)
	{
	cTransactionDate = lcl_arg0;
	modify();
	}
	public void setCTranType(String lcl_arg0)
	{
	cTranType = lcl_arg0;
	modify();
	}
	public void setCUserInit(String lcl_arg0)
	{
	cUserInit = lcl_arg0;
	modify();
	}
	public void setDateModified(BTRIEVE_DATE lcl_arg0)
	{
	DateModified = lcl_arg0;
	}
/**
 * Get the ID key for this object from the hashtable.
 * DbUser objects can be accessed by "Name"
 * So, first see if "Name" is being used for restoring
 * or if ID# is being used.
 */
public void setId(java.util.Hashtable h) {
	//if (h.containsKey(DbInvHistoryPeer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbInvHistoryPeer.IDENTITY)).intValue());
}
	public void setLPurchaseCost(int lcl_arg0)
	{
	lPurchaseCost = lcl_arg0;
	modify();
	}
	public void setLQuantity(int lcl_arg0)
	{
	lQuantity = lcl_arg0;
	modify();
	}
	public void setLSellingPrice(int lcl_arg0)
	{
	lSellingPrice = lcl_arg0;
	modify();
	}
	public void setLSequenceNumber(int lcl_arg0)
	{
	lSequenceNumber = lcl_arg0;
	modify();
	}
	public void setLVitalsNumber(int lcl_arg0)
	{
	lVitalsNumber = lcl_arg0;
	modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (12/31/2001 9:37:12 AM)
	 * @param newMasterId int
	 */
	public void setMasterId(int newMasterId) {
		masterId = newMasterId;
		modify();
	}
	public void setTimeModified(BTRIEVE_TIME lcl_arg0)
	{
	TimeModified = lcl_arg0;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
