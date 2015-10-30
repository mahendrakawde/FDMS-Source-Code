package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * This class contains one donation for one case.
 * Creation date: (1/25/01 3:07:21 PM)
 * @author: R. Davidson
 */
public class DbDonations extends com.aldorsolutions.webfdms.database.Persistent {
		
	static private final DbDonationsPeer peer = new DbDonationsPeer();

	private int vitalsID;
	private short fileVersion;
	private java.lang.String honorific;
	private java.lang.String firstName;
	private java.lang.String lastName;
	private java.lang.String addr1;
	private java.lang.String addr2;
	private java.lang.String addr3;
	private java.lang.String addr4;
	private java.lang.String city;
	private java.lang.String state;
	private java.lang.String postalCode;
	private java.lang.String charityName;
	private int donationAmount;
	private java.lang.String paymentType;
	private int histTranNo;
/**
 * ChargeCollection constructor comment.
 */
public DbDonations() {
	super();
	//setNew();
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:46:42 AM)
 * @return java.lang.String
 */
public java.lang.String getAddr1() {
	return addr1;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:46:59 AM)
 * @return java.lang.String
 */
public java.lang.String getAddr2() {
	return addr2;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:47:32 AM)
 * @return java.lang.String
 */
public java.lang.String getAddr3() {
	return addr3;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:47:41 AM)
 * @return java.lang.String
 */
public java.lang.String getAddr4() {
	return addr4;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:48:22 AM)
 * @return java.lang.String
 */
public java.lang.String getCharityName() {
	return charityName;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:47:50 AM)
 * @return java.lang.String
 */
public java.lang.String getCity() {
	return city;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:48:33 AM)
 * @return int
 */
public int getDonationAmount() {
	return donationAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2001 2:48:52 PM)
 * @return short
 */
public short getFileVersion() {
	return fileVersion;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:46:10 AM)
 * @return java.lang.String
 */
public java.lang.String getFirstName() {
	return firstName;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:49:07 AM)
 * @return int
 */
public int getHistTranNo() {
	return histTranNo;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:46:01 AM)
 * @return java.lang.String
 */
public java.lang.String getHonorific() {
	return honorific;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:46:29 AM)
 * @return java.lang.String
 */
public java.lang.String getLastName() {
	return lastName;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:48:52 AM)
 * @return java.lang.String
 */
public java.lang.String getPaymentType() {
	return paymentType;
}
/**
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
	return peer;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:48:11 AM)
 * @return java.lang.String
 */
public java.lang.String getPostalCode() {
	return postalCode;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:48:01 AM)
 * @return java.lang.String
 */
public java.lang.String getState() {
	return state;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:18:42 PM)
 * @return int
 */
public int getVitalsID() {
	return vitalsID;
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
	setId(				FormatNumber.parseInteger(data.get(DbDonationsPeer.IDENTITY).toString()));
	setVitalsID(		FormatNumber.parseInteger(data.get(DbDonationsPeer.VITALSID).toString()));
	setHonorific(		(String)data.get(DbDonationsPeer.HONORIFIC));
	setFirstName(		(String)data.get(DbDonationsPeer.FIRSTNAME));
	setLastName(		(String)data.get(DbDonationsPeer.LASTNAME));
	setAddr1(			(String)data.get(DbDonationsPeer.ADDR1));
	setAddr2(			(String)data.get(DbDonationsPeer.ADDR2));
	setAddr3(			(String)data.get(DbDonationsPeer.ADDR3));
	setAddr4(			(String)data.get(DbDonationsPeer.ADDR4));
	setCity(			(String)data.get(DbDonationsPeer.CITY));
	setState(			(String)data.get(DbDonationsPeer.STATE));
	setPostalCode(		(String)data.get(DbDonationsPeer.POSTALCODE));
	setCharityName(		(String)data.get(DbDonationsPeer.CHARITY));
	setDonationAmount(	FormatNumber.parseInteger(data.get(DbDonationsPeer.DONATIONAMT).toString()));
	setPaymentType(		(String)data.get(DbDonationsPeer.PAYMENTTYPE));
	setHistTranNo(		FormatNumber.parseInteger(data.get(DbDonationsPeer.HISTTRANNO).toString()));
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:46:42 AM)
 * @param newAddr1 java.lang.String
 */
public void setAddr1(java.lang.String newAddr1) {
	addr1 = newAddr1;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:46:59 AM)
 * @param newAddr2 java.lang.String
 */
public void setAddr2(java.lang.String newAddr2) {
	modify();
	addr2 = newAddr2;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:47:32 AM)
 * @param newAddr3 java.lang.String
 */
public void setAddr3(java.lang.String newAddr3) {
	modify();
	addr3 = newAddr3;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:47:41 AM)
 * @param newAddr4 java.lang.String
 */
public void setAddr4(java.lang.String newAddr4) {
	modify();
	addr4 = newAddr4;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:48:22 AM)
 * @param newCharityName java.lang.String
 */
public void setCharityName(java.lang.String newCharityName) {
	modify();
	charityName = newCharityName;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:47:50 AM)
 * @param newCity java.lang.String
 */
public void setCity(java.lang.String newCity) {
	modify();
	city = newCity;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:48:33 AM)
 * @param newDonationAmount int
 */
public void setDonationAmount(int newDonationAmount) {
	modify();
	donationAmount = newDonationAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (11/25/2001 2:48:52 PM)
 * @param newFileVersion short
 */
public void setFileVersion(short newFileVersion) {
	modify();
	fileVersion = newFileVersion;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:46:10 AM)
 * @param newFirstName java.lang.String
 */
public void setFirstName(java.lang.String newFirstName) {
	modify();
	firstName = newFirstName;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:49:07 AM)
 * @param newHistTranNo int
 */
public void setHistTranNo(int newHistTranNo) {
	modify();
	histTranNo = newHistTranNo;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:46:01 AM)
 * @param newHonorific java.lang.String
 */
public void setHonorific(java.lang.String newHonorific) {
	modify();
	honorific = newHonorific;
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
	setId(((Integer)h.get(DbDonationsPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:46:29 AM)
 * @param newLastName java.lang.String
 */
public void setLastName(java.lang.String newLastName) {
	modify();
	lastName = newLastName;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:48:52 AM)
 * @param newPaymentType java.lang.String
 */
public void setPaymentType(java.lang.String newPaymentType) {
	modify();
	paymentType = newPaymentType;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:48:11 AM)
 * @param newPostalCode java.lang.String
 */
public void setPostalCode(java.lang.String newPostalCode) {
	modify();
	postalCode = newPostalCode;
}
/**
 * Insert the method's description here.
 * Creation date: (11/30/2001 6:48:01 AM)
 * @param newState java.lang.String
 */
public void setState(java.lang.String newState) {
	modify();
	state = newState;
}
/**
 * Insert the method's description here.
 * Creation date: (1/25/01 3:18:42 PM)
 * @param newVitalsID int
 */
public void setVitalsID(int newVitalsID) {
	modify();
	vitalsID = newVitalsID;
}
}
