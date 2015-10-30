package com.aldorsolutions.webfdms.beans;


/**
 * This class represents the Spouse information for one case.
 * Creation date: (1/22/2002 3:59:04 PM)
 * @author: 
 */
public class DbVitalsSpouse extends com.aldorsolutions.webfdms.database.Persistent  {
	static private final DbVitalsSpousePeer peer = new DbVitalsSpousePeer();

	private String firstname;
	private String lastname;
	private String middlename;
	private java.lang.String maiden;
	private java.lang.String age;
	private String survivingSpouseAddress;
	private String survivingSpouseCity;
	private String survivingSpouseState;
	private String survivingSpouseZip;

	
	
	/**
 * DbVitalsInformant constructor comment.
 */
public DbVitalsSpouse() {
	super();
}
/**
 * Split Spouse's full maiden name into first, middle, last
 * Creation date: (9/19/2002 10:41:12 AM)
 */
public void convertSpouseFullToSplit() {
	if (getMaiden() == null) return;
	
	java.util.StringTokenizer names = new java.util.StringTokenizer(getMaiden()	);

	int namecount = names.countTokens();
	if (namecount < 1) return;
	
	if (namecount ==1){
		setFname(getMaiden());
		return;
	}
	// since more than one name, set first name to first token
	setFname(names.nextToken());

	// if exactly two names set last name to 2nd and return
	if (namecount==2){
		setLname(names.nextToken());
		return;
	}
	// otherwise put 2nd token in middle name
	setMname(names.nextToken());
	// put remaininder in last name
	StringBuffer lastname = new StringBuffer();
	boolean addspace = false;
		
	while (names.hasMoreTokens()){
		if (addspace) lastname.append(" ");
		else addspace=true;	

		lastname.append(names.nextToken());
	}
	setLname(lastname.toString());
}
/**
 * Insert the method's description here.
 * Creation date: (1/23/2002 7:18:21 AM)
 * @return java.lang.String
 */
public java.lang.String getAge() {
	return age;
}
/**
 * getFname method comment.
 */
public String getFname() {
	return firstname;
}
/**
 * Return first, middle, and last names appended together.
 * Creation date: (2/26/2002 3:04:21 PM)
 * @return java.lang.String
 */
public String getFullName() {
	StringBuffer fullname = new StringBuffer();
	fullname.append(getFname());
	fullname.append(" ");
	if (getMname().compareTo(" ")>0){
		fullname.append(getMname());
		fullname.append(" ");
	}
	fullname.append(getLname());
	
	return fullname.toString();
}
/**
 * getLname method comment.
 */
public String getLname() {
	return lastname;
}
/**
 * Insert the method's description here.
 * Creation date: (1/23/2002 7:17:50 AM)
 * @return java.lang.String
 */
public java.lang.String getMaiden() {
	return maiden;
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
 * isLocked method comment.
 */
public boolean isLocked() {
	return false;
}
/**
 * restore method comment.
 */
public void restore(com.aldorsolutions.webfdms.database.Transaction t, java.util.Hashtable data) throws com.aldorsolutions.webfdms.database.PersistenceException {
	setId(	Integer.parseInt(data.get(DbVitalsSpousePeer.IDENTITY).toString()));
	setMaiden( (String)data.get(DbVitalsSpousePeer.SPOUSEMAIDEN));
	setFname( (String)data.get(DbVitalsSpousePeer.SPOUSEFIRST));
	setLname( (String)data.get(DbVitalsSpousePeer.SPOUSELAST));
	setMname( (String)data.get(DbVitalsSpousePeer.SPOUSEMIDDLE));
	setAge( (String)data.get(DbVitalsSpousePeer.SPOUSEAGE));
	setSurvivingSpouseStreet((String)data.get(DbVitalsSpousePeer.SURVIVINGSPOUSESTREET));
	setSurvivingSpouseCity((String)data.get(DbVitalsSpousePeer.SURVIVINGSPOUSECITY));
	setSurvivingSpouseState((String)data.get(DbVitalsSpousePeer.SURVIVINGSPOUSESTATE));
	setSurvivingSpouseZip((String)data.get(DbVitalsSpousePeer.SURVIVINGSPOUSEZIP));
	
}
/**
 * Insert the method's description here.
 * Creation date: (1/23/2002 7:18:21 AM)
 * @param newAge java.lang.String
 */
public void setAge(java.lang.String newAge) {
	modify();
	if (newAge.length()>DbVitalsSpousePeer.SPOUSEAGE_LENGTH){
		age = newAge.substring(0,DbVitalsSpousePeer.SPOUSEAGE_LENGTH-1);
	}
	else
		age = newAge;
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
	setId(((Integer)h.get(DbVitalsSpousePeer.IDENTITY)).intValue());
}
/**
 * setLname method comment.
 */
public void setLname(String lcl_arg0) {
	modify();
	lastname = lcl_arg0;
}
/**
 * Insert the method's description here.
 * Creation date: (1/23/2002 7:17:50 AM)
 * @param newMaiden java.lang.String
 */
public void setMaiden(java.lang.String newMaiden) {
	modify();
	maiden = newMaiden;
}
/**
 * setMname method comment.
 */
public void setMname(String lcl_arg0) {
	middlename = lcl_arg0;
	modify();
}
	/**
	 * @return Returns the survivingSpouseAddress.
	 */
	public String getSurvivingSpouseStreet() {
		return survivingSpouseAddress;
	}
	/**
	 * @param survivingSpouseAddress The survivingSpouseAddress to set.
	 */
	public void setSurvivingSpouseStreet(String survivingSpouseAddress) {
		this.survivingSpouseAddress = survivingSpouseAddress;
	}
	/**
	 * @return Returns the survivingSpouseCity.
	 */
	public String getSurvivingSpouseCity() {
		return survivingSpouseCity;
	}
	/**
	 * @param survivingSpouseCity The survivingSpouseCity to set.
	 */
	public void setSurvivingSpouseCity(String survivingSpouseCity) {
		this.survivingSpouseCity = survivingSpouseCity;
	}
	/**
	 * @return Returns the survivingSpouseState.
	 */
	public String getSurvivingSpouseState() {
		return survivingSpouseState;
	}
	/**
	 * @param survivingSpouseState The survivingSpouseState to set.
	 */
	public void setSurvivingSpouseState(String survivingSpouseState) {
		this.survivingSpouseState = survivingSpouseState;
	}
	/**
	 * @return Returns the survivingSpouseZip.
	 */
	public String getSurvivingSpouseZip() {
		return survivingSpouseZip;
	}
	/**
	 * @param survivingSpouseZip The survivingSpouseZip to set.
	 */
	public void setSurvivingSpouseZip(String survivingSpouseZip) {
		this.survivingSpouseZip = survivingSpouseZip;
	}
}
