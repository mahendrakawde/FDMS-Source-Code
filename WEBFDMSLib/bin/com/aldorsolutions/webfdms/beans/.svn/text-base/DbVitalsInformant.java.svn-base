package com.aldorsolutions.webfdms.beans;

/**
 * This class represents the Informant related fields for one case. Creation
 * date: (11/14/2001 3:59:04 PM)
 * 
 * @author:
 */
public class DbVitalsInformant extends com.aldorsolutions.webfdms.database.Persistent implements DbVitalsInformantI {
	static private final DbVitalsInformantPeer peer = new DbVitalsInformantPeer();

	private String city;

	private String firstname;

	private String lastname;

	private String middlename;

	private String phone;

	private String cellPhone;

	private String relation;

	private String road2;

	private String road3;

	private String salutation;

	private String state;

	private String street;

	private String zip;

	private String informantEmail;

	private String informantDateSigned;

	private String informantIsBillToParty;
	
	private String informantPreferCommunicate;

	/**
	 * DbVitalsInformant constructor comment.
	 */
	public DbVitalsInformant() {
		super();
	}

	/**
	 * getCity method comment.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * getFname method comment.
	 */
	public String getFname() {
		return firstname;
	}

	/**
	 * Insert the method's description here. Creation date: (8/21/2002 2:39:36
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getInformantEmail() {
		return informantEmail;
	}

	/**
	 * getLname method comment.
	 */
	public String getLname() {
		return lastname;
	}

	/**
	 * getMname method comment.
	 */
	public String getMname() {
		return middlename;
	}

	/**
	 * getPersistentPeer method comment.
	 */
	protected com.aldorsolutions.webfdms.database.PersistentPeer getPersistentPeer() {
		return peer;
	}

	/**
	 * getPhone method comment.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * getRelated method comment.
	 */
	public String getRelated() {
		return relation;
	}

	/**
	 * getRoad2 method comment.
	 */
	public String getRoad2() {
		return road2;
	}

	/**
	 * getRoad3 method comment.
	 */
	public String getRoad3() {
		return road3;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2001 10:18:30
	 * AM)
	 * 
	 * @return int
	 */
	public String getSalutation() {
		return salutation;
	}

	/**
	 * getState method comment.
	 */
	public String getState() {
		return state;
	}

	/**
	 * getStreet method comment.
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * getZip method comment.
	 */
	public String getZip() {
		return zip;
	}

	public String getInformantDateSigned() {
		return informantDateSigned;
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
		setId(Integer.parseInt(data.get(DbVitalsInformantPeer.CASE_ID).toString()));
		setCity((String) data.get(DbVitalsInformantPeer.CITY));
		setFname((String) data.get(DbVitalsInformantPeer.FIRSTNAME));
		setLname((String) data.get(DbVitalsInformantPeer.LASTNAME));
		setMname((String) data.get(DbVitalsInformantPeer.MIDDLENAME));
		setPhone((String) data.get(DbVitalsInformantPeer.PHONE));
		setCellPhone((String) data.get(DbVitalsInformantPeer.CELLPHONE));
		setRelated((String) data.get(DbVitalsInformantPeer.RELATION));
		setRoad2((String) data.get(DbVitalsInformantPeer.ROAD2));
		setRoad3((String) data.get(DbVitalsInformantPeer.ROAD3));
		setSalutation((String) data.get(DbVitalsInformantPeer.SALUTATION));
		setState((String) data.get(DbVitalsInformantPeer.STATE));
		setStreet((String) data.get(DbVitalsInformantPeer.STREET));
		setZip((String) data.get(DbVitalsInformantPeer.ZIP));
		setInformantEmail((String) data.get(DbVitalsInformantPeer.INFORMANTEMAIL));
		setInformantDateSigned((String) data.get(DbVitalsInformantPeer.INFORMANTDATESIGNED));
		setInformantPreferCommunicate((String) data.get(DbVitalsInformantPeer.INFORMANT_PREFER_COMMUNICATE));
		informantIsBillToParty = data.get(DbVitalsInformantPeer.INFORMANT_IS_BILLTOPARTY).toString();
		
	}

	/**
	 * setCity method comment.
	 */
	public void setCity(String lcl_arg0) {
		modify();
		city = lcl_arg0;
	}

	/**
	 * setFname method comment.
	 */
	public void setFname(String lcl_arg0) {
		firstname = lcl_arg0;
		modify();
	}

	/**
	 * setId method comment.
	 */
	public void setId(java.util.Hashtable h) {
		setId(((Integer) h.get(DbVitalsInformantPeer.CASE_ID)).intValue());
	}

	/**
	 * Insert the method's description here. Creation date: (8/21/2002 2:39:36
	 * PM)
	 * 
	 * @param newInformantEmail
	 *            java.lang.String
	 */
	public void setInformantEmail(String newInformantEmail) {
		informantEmail = newInformantEmail;
	}

	/**
	 * setLname method comment.
	 */
	public void setLname(String lcl_arg0) {
		modify();
		lastname = lcl_arg0;
	}

	/**
	 * setMname method comment.
	 */
	public void setMname(String lcl_arg0) {
		middlename = lcl_arg0;
		modify();
	}

	/**
	 * setPhone method comment.
	 */
	public void setPhone(String lcl_arg0) {
		phone = lcl_arg0;
		modify();
	}

	/**
	 * setRelated method comment.
	 */
	public void setRelated(String lcl_arg0) {
		relation = lcl_arg0;
		modify();
	}

	/**
	 * setRoad2 method comment.
	 */
	public void setRoad2(String road) {
		road2 = road;
		modify();
	}

	/**
	 * setRoad3 method comment.
	 */
	public void setRoad3(String road) {
		road3 = road;
		modify();
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2001 10:18:30
	 * AM)
	 * 
	 * @param newSalutation
	 *            int
	 */
	public void setSalutation(String newSalutation) {
		salutation = newSalutation;
		modify();
	}

	/**
	 * setState method comment.
	 */
	public void setState(String lcl_arg0) {
		state = lcl_arg0;
		modify();
	}

	/**
	 * setStreet method comment.
	 */
	public void setStreet(String lcl_arg0) {
		street = lcl_arg0;
		modify();
	}

	/**
	 * setZip method comment.
	 */
	public void setZip(String lcl_arg0) {
		zip = lcl_arg0;
		modify();
	}

	public void setInformantDateSigned(String input) {
		informantDateSigned = input;
		modify();
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
		modify();
	}

	public String getInformantIsBillToParty() {
		return informantIsBillToParty;
	}

	public void setInformantIsBillToParty(String informantIsBillToParty) {
		this.informantIsBillToParty = informantIsBillToParty;
		modify();
	}

	/**
	 * @return the informantPreferCommunicate
	 */
	public String getInformantPreferCommunicate() {
		return informantPreferCommunicate;
	}

	/**
	 * @param informantPreferCommunicate the informantPreferCommunicate to set
	 */
	public void setInformantPreferCommunicate(String informantPreferCommunicate) {
		this.informantPreferCommunicate = informantPreferCommunicate;
	}

	
	
}
