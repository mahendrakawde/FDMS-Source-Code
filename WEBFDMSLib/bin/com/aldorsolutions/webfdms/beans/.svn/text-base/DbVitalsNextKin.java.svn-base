package com.aldorsolutions.webfdms.beans;

/**
 * This class represents information for the deceased's next of kin for one
 * case. Creation date: (3/4/2002 3:48:02 PM)
 * 
 * @author:
 */
public class DbVitalsNextKin extends com.aldorsolutions.webfdms.database.Persistent {
	static private final DbVitalsNextKinPeer peer = new DbVitalsNextKinPeer();

	private String firstname;

	private String lastname;

	private String salutation;

	private String relation;

	private String sameAsInformant;

	private String city;

	private String street;

	private String state;

	private String zip;

	private String phone;

	private String cellPhone;

	private String road2;

	private String road3;
	
	private String middleName;
	
	private String email;
	
	private String nextKinPreferCommunicate;

	/**
	 * DbVitalsNextKin constructor comment.
	 */
	public DbVitalsNextKin() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:52:05
	 * PM)
	 * 
	 * @return String
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:49:53
	 * PM)
	 * 
	 * @return String
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:50:26
	 * PM)
	 * 
	 * @return String
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * getPersistentPeer method comment.
	 */
	protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
		return peer;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:25:03
	 * PM)
	 * 
	 * @return String
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:51:08
	 * PM)
	 * 
	 * @return String
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:25:18
	 * PM)
	 * 
	 * @return String
	 */
	public String getRoad2() {
		return road2;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:25:29
	 * PM)
	 * 
	 * @return String
	 */
	public String getRoad3() {
		return road3;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:50:45
	 * PM)
	 * 
	 * @return String
	 */
	public String getSalutation() {
		return salutation;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:51:47
	 * PM)
	 * 
	 * @return String
	 */
	public String getSameAsInformant() {
		return sameAsInformant;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:24:36
	 * PM)
	 * 
	 * @return String
	 */
	public String getState() {
		return state;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:52:20
	 * PM)
	 * 
	 * @return String
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:24:50
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
	 * restore method comment.
	 */
	public void restore(com.aldorsolutions.webfdms.database.Transaction t, java.util.Hashtable data)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		setId(Integer.parseInt(data.get(DbVitalsNextKinPeer.IDENTITY).toString()));
		city = data.get(DbVitalsNextKinPeer.CITY).toString();
		firstname = data.get(DbVitalsNextKinPeer.FIRSTNAME).toString();
		lastname = data.get(DbVitalsNextKinPeer.LASTNAME).toString();
		middleName = data.get(DbVitalsNextKinPeer.MIDDLENAME).toString();
		phone = data.get(DbVitalsNextKinPeer.PHONE).toString();
		cellPhone = data.get(DbVitalsNextKinPeer.CELLPHONE).toString();
		relation = data.get(DbVitalsNextKinPeer.RELATION).toString();
		road2 = data.get(DbVitalsNextKinPeer.ROAD2).toString();
		road3 = data.get(DbVitalsNextKinPeer.ROAD3).toString();
		salutation = data.get(DbVitalsNextKinPeer.SALUTATION).toString();
		state = data.get(DbVitalsNextKinPeer.STATE).toString();
		street = data.get(DbVitalsNextKinPeer.STREET).toString();
		zip = data.get(DbVitalsNextKinPeer.ZIP).toString();
		email= data.get(DbVitalsNextKinPeer.EMAIL).toString();
		nextKinPreferCommunicate= data.get(DbVitalsNextKinPeer.PREFERCOMMUNICATE).toString();
		sameAsInformant = data.get(DbVitalsNextKinPeer.SAMEINFORMANT).toString();
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:52:05
	 * PM)
	 * 
	 * @param newCity
	 *            String
	 */
	public void setCity(String newCity) {
		modify();
		city = newCity;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:49:53
	 * PM)
	 * 
	 * @param newFirstname
	 *            String
	 */
	public void setFirstname(String newFirstname) {
		modify();
		firstname = newFirstname;
	}

	/**
	 * setId method comment.
	 */
	public void setId(java.util.Hashtable h) {
		setId(((Integer) h.get(DbVitalsNextKinPeer.IDENTITY)).intValue());
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:50:26
	 * PM)
	 * 
	 * @param newLastname
	 *            String
	 */
	public void setLastname(String newLastname) {
		modify();
		lastname = newLastname;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:25:03
	 * PM)
	 * 
	 * @param newPhone
	 *            String
	 */
	public void setPhone(String newPhone) {
		modify();
		phone = newPhone;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:51:08
	 * PM)
	 * 
	 * @param newRelation
	 *            String
	 */
	public void setRelation(String newRelation) {
		modify();
		relation = newRelation;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:25:18
	 * PM)
	 * 
	 * @param newRoad2
	 *            String
	 */
	public void setRoad2(String newRoad2) {
		modify();
		road2 = newRoad2;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:25:29
	 * PM)
	 * 
	 * @param newRoad3
	 *            String
	 */
	public void setRoad3(String newRoad3) {
		modify();
		road3 = newRoad3;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:50:45
	 * PM)
	 * 
	 * @param newSalutation
	 *            String
	 */
	public void setSalutation(String newSalutation) {
		modify();
		salutation = newSalutation;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:51:47
	 * PM)
	 * 
	 * @param newSameAsInformant
	 *            String
	 */
	public void setSameAsInformant(String newSameAsInformant) {
		modify();
		sameAsInformant = newSameAsInformant;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:24:36
	 * PM)
	 * 
	 * @param newState
	 *            String
	 */
	public void setState(String newState) {
		modify();
		state = newState;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:52:20
	 * PM)
	 * 
	 * @param newStreet
	 *            String
	 */
	public void setStreet(String newStreet) {
		modify();
		street = newStreet;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:24:50
	 * PM)
	 * 
	 * @param newZip
	 *            String
	 */
	public void setZip(String newZip) {
		modify();
		zip = newZip;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
		modify();
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the nextKinPreferCommunicate
	 */
	public String getNextKinPreferCommunicate() {
		return nextKinPreferCommunicate;
	}

	/**
	 * @param nextKinPreferCommunicate the nextKinPreferCommunicate to set
	 */
	public void setNextKinPreferCommunicate(String nextKinPreferCommunicate) {
		this.nextKinPreferCommunicate = nextKinPreferCommunicate;
	}


	
	
	
}
