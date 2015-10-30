package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * DbApVendor - This class represents one vendor to which checks are written.
 * Creation date: (5/3/2002 2:42:35 PM)
 * @author: 
 */
public class DbApVendor extends com.aldorsolutions.webfdms.database.Persistent
{
	static private final DbApVendorPeer peer = new DbApVendorPeer();

	private String vendorCode;
	private String name;
	private String addr1;
	private String addr2;
	private String cityState;
	private String postalCode;
	private String contactName;
	private String phone;
	private String emailAddr;
	private int defaultAcctID;
	private String notes;
	private int locale;
	private String deleteCode;
	private int locationID;
	private String vendorState;
	private String phone2;
	private String vendorCountry;
	private String fax;
	private String accountNumber;
	private Float discountPercentage;
	private int discountIfInDays;
	private int discountDueInDays;
	private String vendor1099Type;
	private Float vendor1099Payment;
	private String taxID;
	private String approvedVendor;
	private Float creditLimit;
	private boolean internalVendor;
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:45:26 AM)
 * @return String
 */
public String getAddr1() {
	return addr1;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:45:36 AM)
 * @return String
 */
public String getAddr2() {
	return addr2;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:45:51 AM)
 * @return String
 */
public String getCityState() {
	return cityState;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:46:20 AM)
 * @return String
 */
public String getContactName() {
	return contactName;
}

/**
 * Insert the method's description here.
 * Creation date: (1/3/2003 2:51:31 PM)
 * @return String
 */
public String getDeleteCode() {
	return deleteCode;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:46:42 AM)
 * @return String
 */
public String getEmailAddr() {
	return emailAddr;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 11:05:56 AM)
 * @return int
 */
public int getLocale() {
	return locale;
}
/**
 * Insert the method's description here.
 * Creation date: (1/3/2003 4:31:01 PM)
 * @return int
 */
public int getLocationID() {
	return locationID;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:45:13 AM)
 * @return String
 */
public String getName() {
	return name;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:50:23 AM)
 * @return String
 */
public String getNotes() {
	return notes;
}
/**
 * getPersistentPeer method comment.
 */
protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
	return peer;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:46:28 AM)
 * @return String
 */
public String getPhone() {
	return phone;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:46:08 AM)
 * @return String
 */
public String getPostalCode() {
	return postalCode;
}

/**
 * Insert the method's description here.
 * Creation date: (9/14/2007 4:20:08 PM)
 * @return String
 */
public String getVendorState() {
	return vendorState;
}
/**
 * Insert the method's description here.
 * Creation date: (9/14/2007 4:20:08 PM)
 * @return String
 */
public String getPhone2() {
	return phone2;
}
/**
 * Insert the method's description here.
 * Creation date: (9/14/2007 4:20:08 PM)
 * @return String
 */
public String getVendorCountry() {
	return vendorCountry;
}

/**
 * Insert the method's description here.
 * Creation date: (9/14/2007 4:20:08 PM)
 * @return String
 */
public String getFax() {
	return fax;
}
/**
 * Insert the method's description here.
 * Creation date: (9/14/2007 4:20:08 PM)
 * @return String
 */
public String getAccountNumber() {
	return accountNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (9/14/2007 4:20:08 PM)
 * @return String
 */
public Float getDiscountPercentage() {
	return discountPercentage;
}
/**
 * Insert the method's description here.
 * Creation date: (9/14/2007 4:20:08 PM)
 * @return String
 */
public int getDiscountIfInDays() {
	return discountIfInDays;
}
/**
 * Insert the method's description here.
 * Creation date: (9/14/2007 4:20:08 PM)
 * @return String
 */
public int getDiscountDueInDays() {
	return discountDueInDays;
}
/**
 * Insert the method's description here.
 * Creation date: (9/14/2007 4:20:08 PM)
 * @return String
 */
public String getVendor1099Type() {
	return vendor1099Type;
}
/**
 * Insert the method's description here.
 * Creation date: (9/14/2007 4:20:08 PM)
 * @return String
 */
public Float getVendor1099Payment() {
	return vendor1099Payment;
}
/**
 * Insert the method's description here.
 * Creation date: (9/14/2007 4:20:08 PM)
 * @return String
 */
public String getTaxID() {
	return taxID;
}
/**
 * Insert the method's description here.
 * Creation date: (9/14/2007 4:20:08 PM)
 * @return String
 */
public String getApprovedVendor() {
	return approvedVendor;
}
/**
 * Insert the method's description here.
 * Creation date: (9/14/2007 4:20:08 PM)
 * @return String
 */
public Float getCreditLimit() {
	return creditLimit;
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
	setId(			FormatNumber.parseInteger(data.get(DbApVendorPeer.IDENTITY).toString()));
	vendorCode  = data.get(DbApVendorPeer.VENDORCODE).toString();
	name 				= data.get(DbApVendorPeer.NAME).toString();
	locale			= FormatNumber.parseInteger(data.get(DbApVendorPeer.LOCALE).toString());
	addr1 			= data.get(DbApVendorPeer.ADDR1).toString();
	addr2 			= data.get(DbApVendorPeer.ADDR2).toString();
	cityState		= data.get(DbApVendorPeer.CITYSTATE).toString();
	postalCode		= data.get(DbApVendorPeer.POSTALCODE).toString();
	contactName		= data.get(DbApVendorPeer.CONTACTNAME).toString();
	phone			= data.get(DbApVendorPeer.PHONE).toString();
	emailAddr		= data.get(DbApVendorPeer.EMAILADDR).toString();
	defaultAcctID	= FormatNumber.parseInteger(data.get(DbApVendorPeer.DEFAULTACCTID).toString());
	notes 			= data.get(DbApVendorPeer.NOTES).toString();
	deleteCode		= data.get(DbApVendorPeer.DELETECODE).toString();
	locationID		= FormatNumber.parseInteger(data.get(DbApVendorPeer.LOCATIONID).toString());
	internalVendor  = Boolean.parseBoolean( data.get(DbApVendorPeer.INTERNALVENDOR).toString() );
	vendorState 	= data.get(DbApVendorPeer.VENDORSTATE).toString();
	phone2 			= data.get(DbApVendorPeer.PHONE2).toString();	
	vendorCountry	= data.get(DbApVendorPeer.VENDORCOUNTRY).toString();
	fax				= data.get(DbApVendorPeer.FAX).toString();
	accountNumber	= data.get(DbApVendorPeer.ACCOUNTNUMBER).toString();
	discountPercentage	= FormatNumber.parseFloat(data.get(DbApVendorPeer.DISCOUNTPERCENTAGE).toString());
	discountIfInDays	= FormatNumber.parseInteger(data.get(DbApVendorPeer.DISCOUNTIFINDAYS).toString());
	discountDueInDays	= FormatNumber.parseInteger(data.get(DbApVendorPeer.DISCOUNTDUEINDAYS).toString());
	vendor1099Type	= data.get(DbApVendorPeer.VENDOR1099TYPE).toString();
	vendor1099Payment	= FormatNumber.parseFloat(data.get(DbApVendorPeer.VENDOR1099PAYMENT).toString());
	taxID			= data.get(DbApVendorPeer.TAXID).toString();
	approvedVendor	= data.get(DbApVendorPeer.APPROVEDVENDOR).toString();
	creditLimit		= FormatNumber.parseFloat(data.get(DbApVendorPeer.CREDITLIMIT).toString());
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:45:26 AM)
 * @param newAddr1 String
 */
public void setAddr1(String newAddr1) {
	modify();
	addr1 = newAddr1;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:45:36 AM)
 * @param newAddr2 String
 */
public void setAddr2(String newAddr2) {
	modify();
	addr2 = newAddr2;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:45:51 AM)
 * @param newCityState String
 */
public void setCityState(String newCityState) {
	modify();
	cityState = newCityState;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:46:20 AM)
 * @param newContactName String
 */
public void setContactName(String newContactName) {
	modify();
	contactName = newContactName;
}

/**
 * @return the defaultAcctID
 */
public int getDefaultAcctID() {
	return defaultAcctID;
}
/**
 * @param defaultAcctID the defaultAcctID to set
 */
public void setDefaultAcctID(int defaultAcctID) {
	modify();
	this.defaultAcctID = defaultAcctID;
}
/**
 * Insert the method's description here.
 * Creation date: (1/3/2003 2:51:31 PM)
 * @param newDeleteCode String
 */
public void setDeleteCode(String newDeleteCode) {
	deleteCode = newDeleteCode;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:46:42 AM)
 * @param newEmailAddr String
 */
public void setEmailAddr(String newEmailAddr) {
	modify();
	emailAddr = newEmailAddr;
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
	setId(((Integer)h.get(DbApVendorPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 11:05:56 AM)
 * @param newLocale int
 */
public void setLocale(int newLocale) {
	locale = newLocale;
}
/**
 * Insert the method's description here.
 * Creation date: (1/3/2003 4:31:01 PM)
 * @param newLocationID int
 */
public void setLocationID(int newLocationID) {
	locationID = newLocationID;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:45:13 AM)
 * @param newName String
 */
public void setName(String newName) {
	modify();
	name = newName;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:50:23 AM)
 * @param newNotes String
 */
public void setNotes(String newNotes) {
	modify();
	notes = newNotes;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:46:28 AM)
 * @param newPhone String
 */
public void setPhone(String newPhone) {
	modify();
	phone = newPhone;
}
/**
 * Insert the method's description here.
 * Creation date: (5/3/2002 10:46:08 AM)
 * @param newPostalCode String
 */
public void setPostalCode(String newPostalCode) {
	modify();
	postalCode = newPostalCode;
}
/**
 * @return the internalVendor
 */
public boolean isInternalVendor() {
	return internalVendor;
}
/**
 * @param internalVendor the internalVendor to set
 */
public void setInternalVendor(boolean internalVendor) {
	this.internalVendor = internalVendor;
	modify();
}
/**
 * @param state the state to set
 */
public void setVendorState(String newVendorState) {
	modify();
	this.vendorState = newVendorState;

}
/**
 * @param phone2 the phone2 to set
 */
public void setPhone2(String newPhone2) {
	modify();
	this.phone2 = newPhone2;
}
/**
 * @param country the country to set
 */
public void setVendorCountry(String newVendorCountry) {
	modify();
	this.vendorCountry = newVendorCountry;
}

/**
 * @param fax the fax to set
 */
public void setFax(String newFax) {
	modify();
	this.fax = newFax;
}
/**
 * @param AccountNumber the AccountNumber to set
 */
public void setAccountNumber(String newAccountNumber) {
	modify();
	this.accountNumber = newAccountNumber;
}
/**
 * @param DiscountPercentage the DiscountPercentage to set
 */
public void setDiscountPercentage(Float newDiscountPercentage) {
	modify();
	this.discountPercentage = newDiscountPercentage;
}
/**
 * @param DiscountIfInDays the DiscountIfInDays to set
 */
public void setDiscountIfInDays(int newDiscountIfInDays) {
	modify();
	this.discountIfInDays = newDiscountIfInDays;
}
/**
 * @param DiscountDueInDays the DiscountDueInDays to set
 */
public void setDiscountDueInDays(int newDiscountDueInDays) {
	modify();
	this.discountDueInDays = newDiscountDueInDays;
}
/**
 * @param Vendor1099Type the Vendor1099Type to set
 */
public void setVendor1099Type(String newVendor1099Type) {
	modify();
	this.vendor1099Type = newVendor1099Type;
}
/**
 * @param Vendor1099Payment the Vendor1099Payment to set
 */
public void setVendor1099Payment(Float newVendor1099Payment) {
	modify();
	this.vendor1099Payment = newVendor1099Payment;
}
/**
 * @param TaxID the TaxID to set
 */
public void setTaxID(String newTaxID) {
	modify();
	this.taxID = newTaxID;
}
/**
 * @param ApprovedVendor the ApprovedVendor to set
 */
public void setApprovedVendor(String newApprovedVendor) {
	modify();
	this.approvedVendor = newApprovedVendor;
}
/**
 * @param CreditLimit the CreditLimit to set
 */
public void setCreditLimit(Float newCreditLimit) {
	modify();
	this.creditLimit = newCreditLimit;
}
/**
 * @return the vendorCode
 */
public String getVendorCode() {
return vendorCode;
}
/**
 * @param vendorCode the vendorCode to set
 */
public void setVendorCode(String vendorCode) {
modify();
this.vendorCode = vendorCode;
}


}
