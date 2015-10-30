package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;

/**
 * DbInvOnHand - This class represents the on-hand quantity data of one Inventory item
 * or for one serial numbered item.
 * Creation date: (12/27/2001 2:42:35 PM)
 * @author: 
 */
public class DbInvOnHand  extends com.aldorsolutions.webfdms.database.Persistent
{
	static private final DbInvOnHandPeer peer = new DbInvOnHandPeer();

	private String	cItemName;	//  Converted from char[15]
	private int	lSequenceNumber;
	private String	cSerialNumber;	//  Converted from char[15]
	private char	cShowRoom;
	private String	cDateIn;	//  Converted from char[8]
	private String	cInvoiceNumber;	//  Converted from char[10]
	private String	cLocation;	//  Converted from char[25]
	private int	lQuantity;
	private int	lCost;
	private String	cNotes;	//  Converted from char[100]
	private BTRIEVE_DATE	DateModified;
	private BTRIEVE_TIME	TimeModified;

	private int masterId;
	public String getCDateIn()
	{
	return cDateIn;
	}
	public String getCInvoiceNumber()
	{
	return cInvoiceNumber;
	}
	public String getCItemName()
	{
	return cItemName;
	}
	public String getCLocation()
	{
	return cLocation;
	}
	public String getCNotes()
	{
	return cNotes;
	}
	public int getCost()
	{
	return lCost;
	}
	public String getCSerialNumber()
	{
	return cSerialNumber;
	}
	public char getCShowRoom()
	{
	return cShowRoom;
	}
	public BTRIEVE_DATE getDateModified()
	{
	return DateModified;
	}
/**
 * Insert the method's description here.
 * Creation date: (12/30/2001 6:38:29 PM)
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
	public int getQuantity()
	{
	return lQuantity;
	}
	public int getSequenceNumber()
	{
	return lSequenceNumber;
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
	setId(	FormatNumber.parseInteger(data.get(DbInvOnHandPeer.IDENTITY).toString()));
	setCItemName(		(String)data.get(DbInvOnHandPeer.ITEMNAME));
	setSequenceNumber(	FormatNumber.parseInteger(data.get(DbInvOnHandPeer.ITEMSEQNO).toString()));
	setCSerialNumber(	(String)data.get(DbInvOnHandPeer.SERIALNUMBER));
	setCShowRoom(		FormatString.getFirstChar(data.get(DbInvOnHandPeer.SHOWROOM).toString()));
	setCDateIn(			(String)data.get(DbInvOnHandPeer.DATEIN));
	setCInvoiceNumber(	(String)data.get(DbInvOnHandPeer.INVOICENUMBER));
	setCLocation(		(String)data.get(DbInvOnHandPeer.LOCATION));
	setQuantity(		FormatNumber.parseInteger(data.get(DbInvOnHandPeer.QUANTITY).toString()));
	setCost(			FormatNumber.parseInteger(data.get(DbInvOnHandPeer.COST).toString()));
	setCNotes(			(String)data.get(DbInvOnHandPeer.NOTES));
	setMasterId(		FormatNumber.parseInteger(data.get(DbInvOnHandPeer.MASTERID).toString()));
}
	public void setCDateIn(String lcl_arg0)
	{
	cDateIn = lcl_arg0;
	modify();
	}
	public void setCInvoiceNumber(String lcl_arg0)
	{
	cInvoiceNumber = lcl_arg0;
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
	public void setCNotes(String lcl_arg0)
	{
	cNotes = lcl_arg0;
	modify();
	}
	public void setCost(int lcl_arg0)
	{
	lCost = lcl_arg0;
	modify();
	}
	public void setCSerialNumber(String lcl_arg0)
	{
	cSerialNumber = lcl_arg0;
	modify();
	}
	public void setCShowRoom(char lcl_arg0)
	{
	cShowRoom = lcl_arg0;
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
	//if (h.containsKey(peer.NAME)){
	//	getPersistentPeer().restore(this, t);
	//}
	setId(((Integer)h.get(DbInvOnHandPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (12/30/2001 6:38:29 PM)
 * @param newMasterId int
 */
public void setMasterId(int newMasterId) {
	masterId = newMasterId;
	modify();
}
	public void setQuantity(int lcl_arg0)
	{
	lQuantity = lcl_arg0;
	modify();
	}
	public void setSequenceNumber(int lcl_arg0)
	{
	lSequenceNumber = lcl_arg0;
	modify();
	}
	public void setTimeModified(BTRIEVE_TIME lcl_arg0)
	{
	TimeModified = lcl_arg0;
	}
}
