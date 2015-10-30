package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;

/**
 * Represents a Bill-To person, name and address. Creation date: (7/26/00
 * 2:36:17 PM) 11/22/01 changed to SQL instead of JNI
 * 
 * @author:
 */

public class DbBillto extends com.aldorsolutions.webfdms.database.Persistent {

	static private final DbBilltoPeer peer = new DbBilltoPeer();

	static public final String INFORMANT = "INF";
	
	private int vitalsID;

	private String honorific;

	private String firstName;

	private String lastName;

	private String Street1;

	private String street2;

	private String street3;

	private String street4;

	private String city;

	private String state;

	private String zip;

	private String homePhone;

	private String cellPhone;

	private String SocialSecurityNo;

	private String relation;

	private String contractSigner;

	private String modified;

	private String language;

	private String cashSale;

	private String refused;

	private String sendInvoice;

	private short seqNo;

	private String deleteCode = " ";

	private String workPhone;

	private short fileVersion;

	private String county;

	private String emailAddress;
	
	private String groupType;

	/**
	 * Insert the method's description here. Creation date: (7/26/00 2:44:26 PM)
	 */
	public DbBillto() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:13:54
	 * PM)
	 * 
	 * @return char
	 */
	public String getCashSale() {
		return cashSale;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:08:43
	 * PM)
	 * 
	 * @return String
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:12:24
	 * PM)
	 * 
	 * @return char
	 */
	public String getContractSigner() {
		return contractSigner;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:17:06
	 * PM)
	 * 
	 * @return String
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:16:24
	 * PM)
	 * 
	 * @return char
	 */
	public String getDeleteCode() {
		return deleteCode;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:17:52
	 * PM)
	 * 
	 * @return String
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:16:54
	 * PM)
	 * 
	 * @return short
	 */
	public short getFileVersion() {
		return fileVersion;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:06:11
	 * PM)
	 * 
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:10:34
	 * PM)
	 * 
	 * @return String
	 */
	public String getHomePhone() {
		return homePhone;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:05:01
	 * PM)
	 * 
	 * @return String
	 */
	public String getHonorific() {
		return honorific;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:13:43
	 * PM)
	 * 
	 * @return String
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:07:25
	 * PM)
	 * 
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:13:27
	 * PM)
	 * 
	 * @return char
	 */
	public String getModified() {
		return modified;
	}

	/**
	 * getPersistentPeer method comment.
	 */
	protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
		return peer;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:14:03
	 * PM)
	 * 
	 * @return char
	 */
	public String getRefused() {
		return refused;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:12:05
	 * PM)
	 * 
	 * @return String
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:14:19
	 * PM)
	 * 
	 * @return char
	 */
	public String getSendInvoice() {
		return sendInvoice;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:15:45
	 * PM)
	 * 
	 * @return short
	 */
	public short getSeqNo() {
		return seqNo;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:10:56
	 * PM)
	 * 
	 * @return String
	 */
	public String getSocialSecurityNo() {
		return SocialSecurityNo;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:08:55
	 * PM)
	 * 
	 * @return String
	 */
	public String getState() {
		return state;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:07:53
	 * PM)
	 * 
	 * @return String
	 */
	public String getStreet1() {
		return Street1;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:08:05
	 * PM)
	 * 
	 * @return String
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:08:17
	 * PM)
	 * 
	 * @return String
	 */
	public String getStreet3() {
		return street3;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:08:30
	 * PM)
	 * 
	 * @return String
	 */
	public String getStreet4() {
		return street4;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:04:26
	 * PM)
	 * 
	 * @return int
	 */
	public int getVitalsID() {
		return vitalsID;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:16:41
	 * PM)
	 * 
	 * @return String
	 */
	public String getWorkPhone() {
		return workPhone;
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:10:05
	 * PM)
	 * 
	 * @return String
	 */
	public String getZip() {
		return zip;
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
		setId(Integer.parseInt(data.get(DbBilltoPeer.IDENTITY).toString()));
		setCashSale((String) data.get(DbBilltoPeer.CASHSALE));
		setCity((String) data.get(DbBilltoPeer.CITY));
		setContractSigner((String) data.get(DbBilltoPeer.CONTRACTSIGNER));
		setCounty((String) data.get(DbBilltoPeer.COUNTY));
		setDeleteCode((String) data.get(DbBilltoPeer.DELETECODE));
		setEmailAddress((String) data.get(DbBilltoPeer.EMAILADDR));
		setFileVersion(Short.parseShort(data.get(DbBilltoPeer.FILEVERSION).toString()));
		setFirstName((String) data.get(DbBilltoPeer.FIRSTNAME));
		setHomePhone((String) data.get(DbBilltoPeer.HOMEPHONE));
		setHonorific((String) data.get(DbBilltoPeer.HONORIFIC));
		setLanguage((String) data.get(DbBilltoPeer.LANGUAGE));
		setLastName((String) data.get(DbBilltoPeer.LASTNAME));
		setModified((String) data.get(DbBilltoPeer.MODIFIEDFLAG));
		setRefused((String) data.get(DbBilltoPeer.REFUSED));
		setRelation((String) data.get(DbBilltoPeer.RELATION));
		setSendInvoice((String) data.get(DbBilltoPeer.SENDINVOICE));
		setSeqNo(Short.parseShort(data.get(DbBilltoPeer.SEQNUMBER).toString()));
		setSocialSecurityNo((String) data.get(DbBilltoPeer.SSNO));
		setState((String) data.get(DbBilltoPeer.STATE));
		setStreet1((String) data.get(DbBilltoPeer.STREET1));
		setStreet2((String) data.get(DbBilltoPeer.STREET2));
		setStreet3((String) data.get(DbBilltoPeer.STREET3));
		setStreet4((String) data.get(DbBilltoPeer.STREET4));
		setVitalsID(Integer.parseInt(data.get(DbBilltoPeer.VITALSID).toString()));
		setWorkPhone((String) data.get(DbBilltoPeer.WORKPHONE));
		setCellPhone((String) data.get(DbBilltoPeer.CELLPHONE));
		setZip((String) data.get(DbBilltoPeer.ZIP));
		setGroupType((String) data.get(DbBilltoPeer.GROUPTYPE));
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:13:54
	 * PM)
	 * 
	 * @param newCashSale
	 *            char
	 */
	public void setCashSale(String newCashSale) {
		cashSale = newCashSale;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:08:43
	 * PM)
	 * 
	 * @param newCity
	 *            String
	 */
	public void setCity(String newCity) {
		city = newCity;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:12:24
	 * PM)
	 * 
	 * @param newContractSigner
	 *            char
	 */
	public void setContractSigner(String newContractSigner) {
		contractSigner = newContractSigner;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:17:06
	 * PM)
	 * 
	 * @param newCounty
	 *            String
	 */
	public void setCounty(String newCounty) {
		county = newCounty;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:16:24
	 * PM)
	 * 
	 * @param newDeleteCode
	 *            char
	 */
	public void setDeleteCode(String newDeleteCode) {
		deleteCode = newDeleteCode;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:17:52
	 * PM)
	 * 
	 * @param newEmailAddress
	 *            String
	 */
	public void setEmailAddress(String newEmailAddress) {
		emailAddress = newEmailAddress;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:16:54
	 * PM)
	 * 
	 * @param newFileVersion
	 *            short
	 */
	public void setFileVersion(short newFileVersion) {
		fileVersion = newFileVersion;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:06:11
	 * PM)
	 * 
	 * @param newFirstName
	 *            String
	 */
	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:10:34
	 * PM)
	 * 
	 * @param newHomePhone
	 *            String
	 */
	public void setHomePhone(String newHomePhone) {
		homePhone = newHomePhone;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:05:01
	 * PM)
	 * 
	 * @param newHonorific
	 *            String
	 */
	public void setHonorific(String newHonorific) {
		honorific = newHonorific;
		modify();
	}

	/**
	 * Get the ID key for this object from the hashtable. DbUser objects can be
	 * accessed by "Name" So, first see if "Name" is being used for restoring or
	 * if ID# is being used.
	 */
	public void setId(java.util.Hashtable h) {
		// if (h.containsKey(peer.NAME)){
		// getPersistentPeer().restore(this, t);
		// }
		setId(((Integer) h.get(DbBilltoPeer.IDENTITY)).intValue());
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:13:43
	 * PM)
	 * 
	 * @param newLanguage
	 *            String
	 */
	public void setLanguage(String newLanguage) {
		language = newLanguage;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:07:25
	 * PM)
	 * 
	 * @param newLastName
	 *            String
	 */
	public void setLastName(String newLastName) {
		lastName = newLastName;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:13:27
	 * PM)
	 * 
	 * @param newModified
	 *            char
	 */
	public void setModified(String newModified) {
		modified = newModified;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:14:03
	 * PM)
	 * 
	 * @param newRefused
	 *            char
	 */
	public void setRefused(String newRefused) {
		refused = newRefused;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:12:05
	 * PM)
	 * 
	 * @param newRelation
	 *            String
	 */
	public void setRelation(String newRelation) {
		relation = newRelation;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:14:19
	 * PM)
	 * 
	 * @param newSendInvoice
	 *            char
	 */
	public void setSendInvoice(String newSendInvoice) {
		sendInvoice = newSendInvoice;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:15:45
	 * PM)
	 * 
	 * @param newSeqNo
	 *            short
	 */
	public void setSeqNo(short newSeqNo) {
		seqNo = newSeqNo;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:10:56
	 * PM)
	 * 
	 * @param newSocialSecurityNo
	 *            String
	 */
	public void setSocialSecurityNo(String newSocialSecurityNo) {
		SocialSecurityNo = newSocialSecurityNo;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:08:55
	 * PM)
	 * 
	 * @param newState
	 *            String
	 */
	public void setState(String newState) {
		state = newState;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:07:53
	 * PM)
	 * 
	 * @param newStreet1
	 *            String
	 */
	public void setStreet1(String newStreet1) {
		Street1 = newStreet1;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:08:05
	 * PM)
	 * 
	 * @param newStreet2
	 *            String
	 */
	public void setStreet2(String newStreet2) {
		street2 = newStreet2;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:08:17
	 * PM)
	 * 
	 * @param newStreet3
	 *            String
	 */
	public void setStreet3(String newStreet3) {
		street3 = newStreet3;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:08:30
	 * PM)
	 * 
	 * @param newStreet4
	 *            String
	 */
	public void setStreet4(String newStreet4) {
		street4 = newStreet4;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:04:26
	 * PM)
	 * 
	 * @param newVitalsID
	 *            int
	 */
	public void setVitalsID(int newVitalsID) {
		vitalsID = newVitalsID;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:16:41
	 * PM)
	 * 
	 * @param newWorkPhone
	 *            String
	 */
	public void setWorkPhone(String newWorkPhone) {
		workPhone = newWorkPhone;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/22/2001 3:10:05
	 * PM)
	 * 
	 * @param newZip
	 *            String
	 */
	public void setZip(String newZip) {
		zip = newZip;
		modify();
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
		modify();
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
		modify();
	}

}
