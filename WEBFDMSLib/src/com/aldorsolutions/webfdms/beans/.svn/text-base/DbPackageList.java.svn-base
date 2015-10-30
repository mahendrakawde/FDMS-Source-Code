package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.FormatString;

/**
 * Services Package Price List Class represents one package. Creation date:
 * (12/25/01 1:43:40 PM)
 * 
 * @author: R Davidson
 */
public class DbPackageList extends com.aldorsolutions.webfdms.database.Persistent {
	static private final DbPackageListPeer peer = new DbPackageListPeer();
	
	static public final int RECORD_TYPE_SERVICE = 1;
	static public final int RECORD_TYPE_MERCHANDISE = 2;

	private String Version; // Converted from char[13]

	// private String PkgKey; // Converted from char[9]
	private char IncludedCode;

	private short SeqNumber;

	// private String ServKey; // Converted from char[9]
	private String ExtraDescr; // Converted from char[40]

	private int pkgPriceListId;

	private int priceListId;

	private int recordType;

	private long invMasterID;

	public String getExtraDescr() {
		return ExtraDescr;
	}

	public char getIncludedCode() {
		return IncludedCode;
	}

	/**
	 * getPersistentPeer method comment.
	 */
	protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
		return peer;
	}

	/**
	 * Insert the method's description here. Creation date: (8/6/2002 8:33:50
	 * PM)
	 * 
	 * @return int
	 */
	public int getPkgPriceListId() {
		return pkgPriceListId;
	}

	/**
	 * Insert the method's description here. Creation date: (8/6/2002 8:34:07
	 * PM)
	 * 
	 * @return int
	 */
	public int getPriceListId() {
		return priceListId;
	}

	public short getSeqNumber() {
		return SeqNumber;
	}

	public String getVersion() {
		return Version;
	}

	/**
	 * isLocked method comment.
	 */
	public boolean isLocked() {
		return false;
	}

	/**
	 * Move data from hashtable and copy into class variables Peer object
	 * restores from database to hashtable.
	 */
	public void restore(Transaction t, java.util.Hashtable data) throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(DbPackageListPeer.IDENTITY).toString()));
		Version = data.get(DbPackageListPeer.VERSION).toString();
		pkgPriceListId = FormatNumber.parseInteger(data.get(DbPackageListPeer.PKGPLID).toString());
		IncludedCode = FormatString.getFirstChar(data.get(DbPackageListPeer.INCLUDED).toString());
		SeqNumber = FormatNumber.parseShort(data.get(DbPackageListPeer.SEQUENCENUMBER).toString());
		priceListId = FormatNumber.parseInteger(data.get(DbPackageListPeer.PRICELISTID).toString());
		ExtraDescr = data.get(DbPackageListPeer.DESCRIPTION).toString();
		recordType = FormatNumber.parseInteger(data.get(DbPackageListPeer.RECORDTYPE).toString());
		invMasterID = FormatNumber.parseLong(data.get(DbPackageListPeer.INVMASTERID).toString());
	}

	public void setExtraDescr(String lcl_arg0) {
		ExtraDescr = lcl_arg0;
		modify();
	}

	/**
	 * Get the ID key for this object from the hashtable. DbUser objects can be
	 * accessed by "Name" So, first see if "Name" is being used for restoring or
	 * if ID# is being used.
	 */
	public void setId(java.util.Hashtable h) {
		setId(((Integer) h.get(DbPackageListPeer.IDENTITY)).intValue());
	}

	public void setIncludedCode(char lcl_arg0) {
		IncludedCode = lcl_arg0;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (8/6/2002 8:33:50
	 * PM)
	 * 
	 * @param newPkgPriceListId
	 *            int
	 */
	public void setPkgPriceListId(int newPkgPriceListId) {
		pkgPriceListId = newPkgPriceListId;
	}

	/**
	 * Insert the method's description here. Creation date: (8/6/2002 8:34:07
	 * PM)
	 * 
	 * @param newPriceListId
	 *            int
	 */
	public void setPriceListId(int newPriceListId) {
		priceListId = newPriceListId;
	}

	public void setSeqNumber(short lcl_arg0) {
		SeqNumber = lcl_arg0;
		modify();
	}

	public void setVersion(String lcl_arg0) {
		Version = lcl_arg0;
		modify();
	}

	/**
	 * @return the invMasterID
	 */
	public long getInvMasterID() {
		return invMasterID;
	}

	/**
	 * @return the recordType
	 */
	public int getRecordType() {
		return recordType;
	}

	/**
	 * @param invMasterID
	 *            the invMasterID to set
	 */
	public void setInvMasterID(long invMasterID) {
		this.invMasterID = invMasterID;
		modify();
	}

	/**
	 * @param recordType
	 *            the recordType to set
	 */
	public void setRecordType(int recordType) {
		this.recordType = recordType;
		modify();
	}

}
