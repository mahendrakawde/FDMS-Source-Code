package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbArrangers - Represents a funeral director or arranger.
 * Creation date: (1/1/2002 8:12:33 AM)
 * @author: 
 */

public class DbArrangers extends Persistent {
	static private final DbArrangersPeer peer = new DbArrangersPeer();
	private java.lang.String name;
	private java.lang.String licenseNumber;
	private short arrangerNumber;
	private java.lang.String socialSecurityNo;
	private int locale;
	private java.lang.String deleteCode;
	private java.lang.String pnLicenseNumber;
	private java.lang.String burialLicenseNumber;
	private java.lang.String embalmerLicenseNumber;
	private boolean isCounselor;
	private int commissionLevel;
	 private boolean isManagerForCommission;
	  private int managerCommissionLevel;
	
		

	public boolean isManagerForCommission() {
		return isManagerForCommission;
	}
	public void setManagerForCommission(boolean isManagerForCommission) {
		this.isManagerForCommission = isManagerForCommission;
		modify();
	}
	public int getManagerCommissionLevel() {
		return managerCommissionLevel;
	}
	public void setManagerCommissionLevel(int managerCommissionLevel) {
		this.managerCommissionLevel = managerCommissionLevel;
		modify();
	}
	/**
	 * DbArrangers constructor comment.
	 */
	public DbArrangers() {
		super();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (1/1/2002 8:20:53 AM)
	 * @return short
	 */
	public short getArrangerNumber() {
		return arrangerNumber;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (9/11/2002 4:28:38 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getDeleteCode() {
		return deleteCode;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (1/1/2002 8:20:32 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getLicenseNumber() {
		return licenseNumber;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (1/1/2002 8:21:18 AM)
	 * @return int
	 */
	public int getLocale() {
		return locale;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (1/1/2002 8:19:57 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getName() {
		return name;
	}
	/**
	 * getPersistentPeer method comment.
	 */
	protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
		return peer;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (1/1/2002 8:21:10 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getSocialSecurityNo() {
		return socialSecurityNo;
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
	public void restore(com.aldorsolutions.webfdms.database.Transaction t, java.util.Hashtable data) throws com.aldorsolutions.webfdms.database.PersistenceException {
		setId(				FormatNumber.parseInteger(data.get(DbArrangersPeer.IDENTITY).toString()));
		name				= data.get(DbArrangersPeer.NAME).toString();
		licenseNumber		= data.get(DbArrangersPeer.LICENSENO).toString();
		arrangerNumber		= FormatNumber.parseShort(data.get(DbArrangersPeer.ARRANGERNO).toString());
		socialSecurityNo	= data.get(DbArrangersPeer.SSN).toString();
		locale				= FormatNumber.parseInteger(data.get(DbArrangersPeer.LOCALE).toString());
		deleteCode			= data.get(DbArrangersPeer.DELETECODE).toString();
		pnLicenseNumber     = data.get(DbArrangersPeer.PNLICENSENUMBER).toString();
		burialLicenseNumber = data.get(DbArrangersPeer.BURIALLICENSENUMBER).toString();
		embalmerLicenseNumber = data.get(DbArrangersPeer.EMBALMERLICENSENUMBER).toString();
		isCounselor			= (FormatNumber.parseInteger(data.get(DbArrangersPeer.ISCOUNSELOR).toString()) == 1);
		commissionLevel		= FormatNumber.parseInteger(data.get(DbArrangersPeer.COMMISSIONLEVEL).toString());
		isManagerForCommission = (FormatNumber.parseInteger(data.get(DbArrangersPeer.ISMANAGERFORCOMMISSION).toString()) == 1);
		managerCommissionLevel = FormatNumber.parseInteger(data.get(DbArrangersPeer.MANAGERCOMMISSIONLEVEL).toString());
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (1/1/2002 8:20:53 AM)
	 * @param newArrangerNumber short
	 */
	public void setArrangerNumber(short newArrangerNumber) {
		arrangerNumber = newArrangerNumber;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (9/11/2002 4:28:38 PM)
	 * @param newDeleteCode java.lang.String
	 */
	public void setDeleteCode(java.lang.String newDeleteCode) {
		deleteCode = newDeleteCode;
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
		setId(((Integer)h.get(DbArrangersPeer.IDENTITY)).intValue());
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (1/1/2002 8:20:32 AM)
	 * @param newLicenseNumber java.lang.String
	 */
	public void setLicenseNumber(java.lang.String newLicenseNumber) {
		licenseNumber = newLicenseNumber;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (1/1/2002 8:21:18 AM)
	 * @param newLocale int
	 */
	public void setLocale(int newLocale) {
		locale = newLocale;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (1/1/2002 8:19:57 AM)
	 * @param newName java.lang.String
	 */
	public void setName(java.lang.String newName) {
		name = newName;
		modify();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (1/1/2002 8:21:10 AM)
	 * @param newSocialSecurityNo java.lang.String
	 */
	public void setSocialSecurityNo(java.lang.String newSocialSecurityNo) {
		socialSecurityNo = newSocialSecurityNo;
		modify();
	}
	/**
	 * @return Returns the burialLicenseNumber.
	 */
	public java.lang.String getBurialLicenseNumber() {
		return burialLicenseNumber;
	}
	/**
	 * @param burialLicenseNumber The burialLicenseNumber to set.
	 */
	public void setBurialLicenseNumber(java.lang.String burialLicenseNumber) {
		this.burialLicenseNumber = burialLicenseNumber;
	}
	/**
	 * @return Returns the pnLicenseNumber.
	 */
	public java.lang.String getPnLicenseNumber() {
		return pnLicenseNumber;
	}
	/**
	 * @param pnLicenseNumber The pnLicenseNumber to set.
	 */
	public void setPnLicenseNumber(java.lang.String pnLicenseNumber) {
		this.pnLicenseNumber = pnLicenseNumber;
	}
	/**
	 * @return Returns the embalmerLicenseNumber.
	 */
	public java.lang.String getEmbalmerLicenseNumber() {
		return embalmerLicenseNumber;
	}
	/**
	 * @param embalmerLicenseNumber The embalmerLicenseNumber to set.
	 */
	public void setEmbalmerLicenseNumber(java.lang.String embalmerLicenseNumber) {
		this.embalmerLicenseNumber = embalmerLicenseNumber;
	}
	/**
	 * @return Returns true if this arranger is a pre-need counselor.
	 */
	public boolean getIsCounselor() {
		return isCounselor;
	}
	/**
	 * @return Returns true if this arranger is a pre-need counselor.
	 */
	public void setIsCounselor(boolean isCounselor) {
		this.isCounselor = isCounselor;
	}
	public int getCommissionLevel() {
		return commissionLevel;
	}
	public void setCommissionLevel(int commissionLevel) {
		this.commissionLevel = commissionLevel;
		modify();
	}

	
	
	
}
