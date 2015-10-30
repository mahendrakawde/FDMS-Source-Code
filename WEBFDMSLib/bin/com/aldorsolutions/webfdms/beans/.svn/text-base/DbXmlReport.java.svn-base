package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * Represents a Bill-To person, name and address.
 * Creation date: (7/26/00 2:36:17 PM)
 * 11/22/01 changed to SQL instead of JNI
 * @author: 
 */

public class DbXmlReport extends com.aldorsolutions.webfdms.database.Persistent {
	
	static private final DbXmlReportPeer peer = new DbXmlReportPeer();

	/// variables for transaction history table
	private String cHistDate;
	private String cHistDesc;
	private String lHistAmount;
	private String payMethod;
	private int tranHistId;
	private int invMasterKey;
	
	
	/// variables for bill to parties table
	private int vitalsID;
	private java.lang.String honorific;
	private java.lang.String firstName;
	private java.lang.String lastName;
	private java.lang.String Street1;
	private java.lang.String street2;
	private java.lang.String street3;
	private java.lang.String street4;
	private java.lang.String city;
	private java.lang.String state;
	private java.lang.String zip;
	private java.lang.String homePhone;
	private java.lang.String SocialSecurityNo;
	private java.lang.String relation;
	private java.lang.String contractSigner;
	private java.lang.String modified;
	private java.lang.String language;
	private java.lang.String cashSale;
	private java.lang.String refused;
	private java.lang.String sendInvoice;
	private short seqNo;
	private java.lang.String deleteCode = " ";
	private java.lang.String workPhone;
	private short fileVersion;
	private java.lang.String county;
	private java.lang.String emailAddress;
	private String postedYn;
/**
 * Insert the method's description here.
 * Creation date: (7/26/00 2:44:26 PM)
 */
public DbXmlReport() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:13:54 PM)
 * @return char
 */
public String getCashSale() {
	return cashSale;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:08:43 PM)
 * @return java.lang.String
 */
public java.lang.String getCity() {
	return city;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:12:24 PM)
 * @return char
 */
public String getContractSigner() {
	return contractSigner;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:17:06 PM)
 * @return java.lang.String
 */
public java.lang.String getCounty() {
	return county;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:16:24 PM)
 * @return char
 */
public String getDeleteCode() {
	return deleteCode;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:17:52 PM)
 * @return java.lang.String
 */
public java.lang.String getEmailAddress() {
	return emailAddress;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:16:54 PM)
 * @return short
 */
public short getFileVersion() {
	return fileVersion;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:06:11 PM)
 * @return java.lang.String
 */
public java.lang.String getFirstName() {
	return firstName;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:10:34 PM)
 * @return java.lang.String
 */
public java.lang.String getHomePhone() {
	return homePhone;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:05:01 PM)
 * @return java.lang.String
 */
public java.lang.String getHonorific() {
	return honorific;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:13:43 PM)
 * @return java.lang.String
 */
public java.lang.String getLanguage() {
	return language;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:07:25 PM)
 * @return java.lang.String
 */
public java.lang.String getLastName() {
	return lastName;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:13:27 PM)
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
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:14:03 PM)
 * @return char
 */
public String getRefused() {
	return refused;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:12:05 PM)
 * @return java.lang.String
 */
public java.lang.String getRelation() {
	return relation;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:14:19 PM)
 * @return char
 */
public String getSendInvoice() {
	return sendInvoice;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:15:45 PM)
 * @return short
 */
public short getSeqNo() {
	return seqNo;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:10:56 PM)
 * @return java.lang.String
 */
public java.lang.String getSocialSecurityNo() {
	return SocialSecurityNo;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:08:55 PM)
 * @return java.lang.String
 */
public java.lang.String getState() {
	return state;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:07:53 PM)
 * @return java.lang.String
 */
public java.lang.String getStreet1() {
	return Street1;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:08:05 PM)
 * @return java.lang.String
 */
public java.lang.String getStreet2() {
	return street2;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:08:17 PM)
 * @return java.lang.String
 */
public java.lang.String getStreet3() {
	return street3;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:08:30 PM)
 * @return java.lang.String
 */
public java.lang.String getStreet4() {
	return street4;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:04:26 PM)
 * @return int
 */
public int getVitalsID() {
	return vitalsID;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:16:41 PM)
 * @return java.lang.String
 */
public java.lang.String getWorkPhone() {
	return workPhone;
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:10:05 PM)
 * @return java.lang.String
 */
public java.lang.String getZip() {
	return zip;
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

	setId(						Integer.parseInt(data.get(DbXmlReportPeer.IDENTITY).toString()));
	setCashSale( 				(String)data.get(DbXmlReportPeer.CASHSALE ));
	setCity(					(String)data.get(DbXmlReportPeer.CITY));
	setContractSigner(			(String)data.get(DbXmlReportPeer.CONTRACTSIGNER));
	setCounty(					(String)data.get(DbXmlReportPeer.COUNTY));
	setDeleteCode(				(String)data.get(DbXmlReportPeer.DELETECODE));
	setEmailAddress(			(String)data.get(DbXmlReportPeer.EMAILADDR));
	setFileVersion(				FormatNumber.parseShort(data.get(DbXmlReportPeer.FILEVERSION).toString()));
	setFirstName(				(String)data.get(DbXmlReportPeer.FIRSTNAME));
	setHomePhone(				(String)data.get(DbXmlReportPeer.HOMEPHONE));
	setHonorific(				(String)data.get(DbXmlReportPeer.HONORIFIC));
	setLanguage(				(String)data.get(DbXmlReportPeer.LANGUAGE));
	setLastName(				(String)data.get(DbXmlReportPeer.LASTNAME));
	setModified(				(String)data.get(DbXmlReportPeer.MODIFIEDFLAG));
	setRefused(					(String)data.get(DbXmlReportPeer.REFUSED));
	setRelation(				(String)data.get(DbXmlReportPeer.RELATION));
	setSendInvoice(				(String)data.get(DbXmlReportPeer.SENDINVOICE));
	setSeqNo(					FormatNumber.parseShort(data.get(DbXmlReportPeer.SEQNUMBER).toString()));
	setSocialSecurityNo(		(String)data.get(DbXmlReportPeer.SSNO));
	setState(					(String)data.get(DbXmlReportPeer.STATE));
	setStreet1(					(String)data.get(DbXmlReportPeer.STREET1));
	setStreet2(					(String)data.get(DbXmlReportPeer.STREET2));
	setStreet3(					(String)data.get(DbXmlReportPeer.STREET3));
	setStreet4(					(String)data.get(DbXmlReportPeer.STREET4));
	setVitalsID(				FormatNumber.parseInteger(data.get(DbXmlReportPeer.VITALSID).toString()));
	setWorkPhone(				(String)data.get(DbXmlReportPeer.WORKPHONE));
	setZip(						(String)data.get(DbXmlReportPeer.ZIP));
	setPostedYn(                (String)data.get(DbXmlReportPeer.POSTEDYN));
	setTranHistId(              FormatNumber.parseInteger(data.get(DbXmlReportPeer.TRANHISTID).toString()));
	
	
	
	/// variables for transaction table
	setCHistDate((String)data.get(DbXmlReportPeer.DATEOFTRAN));
	setCHistDesc((String)data.get(DbXmlReportPeer.DESCRIPTION));
	setLHistAmount(data.get(DbXmlReportPeer.AMOUNT).toString());
	setInvMasterKey(FormatNumber.parseInteger(data.get(DbXmlReportPeer.INVMASTERKEY).toString()));

}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:13:54 PM)
 * @param newCashSale char
 */
public void setCashSale(String newCashSale) {
	cashSale = newCashSale;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:08:43 PM)
 * @param newCity java.lang.String
 */
public void setCity(java.lang.String newCity) {
	city = newCity;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:12:24 PM)
 * @param newContractSigner char
 */
public void setContractSigner(String newContractSigner) {
	contractSigner = newContractSigner;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:17:06 PM)
 * @param newCounty java.lang.String
 */
public void setCounty(java.lang.String newCounty) {
	county = newCounty;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:16:24 PM)
 * @param newDeleteCode char
 */
public void setDeleteCode(String newDeleteCode) {
	deleteCode = newDeleteCode;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:17:52 PM)
 * @param newEmailAddress java.lang.String
 */
public void setEmailAddress(java.lang.String newEmailAddress) {
	emailAddress = newEmailAddress;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:16:54 PM)
 * @param newFileVersion short
 */
public void setFileVersion(short newFileVersion) {
	fileVersion = newFileVersion;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:06:11 PM)
 * @param newFirstName java.lang.String
 */
public void setFirstName(java.lang.String newFirstName) {
	firstName = newFirstName;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:10:34 PM)
 * @param newHomePhone java.lang.String
 */
public void setHomePhone(java.lang.String newHomePhone) {
	homePhone = newHomePhone;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:05:01 PM)
 * @param newHonorific java.lang.String
 */
public void setHonorific(java.lang.String newHonorific) {
	honorific = newHonorific;
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
	setId(((Integer)h.get(DbXmlReportPeer.IDENTITY)).intValue());
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:13:43 PM)
 * @param newLanguage java.lang.String
 */
public void setLanguage(java.lang.String newLanguage) {
	language = newLanguage;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:07:25 PM)
 * @param newLastName java.lang.String
 */
public void setLastName(java.lang.String newLastName) {
	lastName = newLastName;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:13:27 PM)
 * @param newModified char
 */
public void setModified(String newModified) {
	modified = newModified;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:14:03 PM)
 * @param newRefused char
 */
public void setRefused(String newRefused) {
	refused = newRefused;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:12:05 PM)
 * @param newRelation java.lang.String
 */
public void setRelation(java.lang.String newRelation) {
	relation = newRelation;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:14:19 PM)
 * @param newSendInvoice char
 */
public void setSendInvoice(String newSendInvoice) {
	sendInvoice = newSendInvoice;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:15:45 PM)
 * @param newSeqNo short
 */
public void setSeqNo(short newSeqNo) {
	seqNo = newSeqNo;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:10:56 PM)
 * @param newSocialSecurityNo java.lang.String
 */
public void setSocialSecurityNo(java.lang.String newSocialSecurityNo) {
	SocialSecurityNo = newSocialSecurityNo;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:08:55 PM)
 * @param newState java.lang.String
 */
public void setState(java.lang.String newState) {
	state = newState;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:07:53 PM)
 * @param newStreet1 java.lang.String
 */
public void setStreet1(java.lang.String newStreet1) {
	Street1 = newStreet1;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:08:05 PM)
 * @param newStreet2 java.lang.String
 */
public void setStreet2(java.lang.String newStreet2) {
	street2 = newStreet2;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:08:17 PM)
 * @param newStreet3 java.lang.String
 */
public void setStreet3(java.lang.String newStreet3) {
	street3 = newStreet3;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:08:30 PM)
 * @param newStreet4 java.lang.String
 */
public void setStreet4(java.lang.String newStreet4) {
	street4 = newStreet4;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:04:26 PM)
 * @param newVitalsID int
 */
public void setVitalsID(int newVitalsID) {
	vitalsID = newVitalsID;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:16:41 PM)
 * @param newWorkPhone java.lang.String
 */
public void setWorkPhone(java.lang.String newWorkPhone) {
	workPhone = newWorkPhone;
	modify();
}
/**
 * Insert the method's description here.
 * Creation date: (11/22/2001 3:10:05 PM)
 * @param newZip java.lang.String
 */
public void setZip(java.lang.String newZip) {
	zip = newZip;
	modify();
}

// Methods for transaction history data ////////////////////

public String getCHistDate() {
    return cHistDate;
}
public String getCHistDesc() {
    return cHistDesc;
}
public String getLHistAmount() {
    return lHistAmount;
}
public void setCHistDate(String s) {
    cHistDate = s;
}
public void setCHistDesc(String s) {
    cHistDesc = s;
}
public void setLHistAmount(String s) {
    lHistAmount = s;
}




	/**
	 * @return Returns the payMethod.
	 */
	public String getPayMethod() {
		return payMethod;
	}
	/**
	 * @param payMethod The payMethod to set.
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	/**
	 * @return Returns the postedYn.
	 */
	public String getPostedYn() {
		return postedYn;
	}
	/**
	 * @param postedYn The postedYn to set.
	 */
	public void setPostedYn(String postedYn) {
		this.postedYn = postedYn;
	}
	/**
	 * @return Returns the tranHistId.
	 */
	public int getTranHistId() {
		return tranHistId;
	}
	/**
	 * @param tranHistId The tranHistId to set.
	 */
	public void setTranHistId(int tranHistId) {
		this.tranHistId = tranHistId;
	}

    // for testing
    public String toString() {
    	    
        return("tranHistId : "+tranHistId
        		+"\nvitalsID : "+vitalsID);
		
    }



	/**
	 * @return Returns the invMasterKey.
	 */
	public int getInvMasterKey() {
		return invMasterKey;
	}
	/**
	 * @param invMasterKey The invMasterKey to set.
	 */
	public void setInvMasterKey(int invMasterKey) {
		this.invMasterKey = invMasterKey;
	}
}
