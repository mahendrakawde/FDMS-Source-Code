package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * Class represents additional vital information needed to support "First Call"
 * user interface. Creation date: (1/6/2002 9:26:03 AM)
 * 
 * @author:
 */
public class DbFuneralSyncToVitals extends com.aldorsolutions.webfdms.database.Persistent {
	static private final DbFuneralSyncToVitalsPeer peer = new DbFuneralSyncToVitalsPeer();

	
	private String decMrMrs;
	private String deceasedFirstName;
	private String deceasedMidName;
	private String deceasedLastName;
	private String deceasedMaidenName;
	private String deceasedFullName;
	private String dateOfBirth;
	private String decBirthPlace;
	private String decBirthPlaceCSV;
	private String dateOfDeath;
	private String deathDateKey;
	private String locationOfDeath;
	private String burialDate;
	private String serviceDateKey;
	private String chapelLocation;
	private int localeNumber;
	private int chapelNumber;
	private String caseCode;
	/**
	 * DbVitalsFirstCall constructor comment.
	 */
	public DbFuneralSyncToVitals() {
		super();
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
	 * setId method comment.
	 */
	public void setId(java.util.Hashtable h) {
		setId(((Integer) h.get(DbFuneralSyncToVitalsPeer.CASE_ID)).intValue());
	}

	/**
	 * restore method comment.
	 */
	public void restore(com.aldorsolutions.webfdms.database.Transaction t, java.util.Hashtable data)
			throws com.aldorsolutions.webfdms.database.PersistenceException {

		setId(FormatNumber.parseInteger(data.get(DbFuneralSyncToVitalsPeer.CASE_ID).toString()));
		
		decMrMrs= data.get(DbFuneralSyncToVitalsPeer.MRMRS).toString();
		deceasedFirstName= data.get(DbFuneralSyncToVitalsPeer.FIRSTNAME).toString();
		deceasedMidName= data.get(DbFuneralSyncToVitalsPeer.MIDNAME).toString();
		deceasedLastName= data.get(DbFuneralSyncToVitalsPeer.LASTNAME).toString();
		deceasedMaidenName= data.get(DbFuneralSyncToVitalsPeer.MAIDENNAME).toString();
		deceasedFullName= data.get(DbFuneralSyncToVitalsPeer.FULLNAME).toString();
		dateOfBirth= data.get(DbFuneralSyncToVitalsPeer.DATEOFBIRTH).toString();
		decBirthPlace= data.get(DbFuneralSyncToVitalsPeer.BIRTHPLACE).toString();
		decBirthPlaceCSV= data.get(DbFuneralSyncToVitalsPeer.BIRTHPLACECSV).toString();
		dateOfDeath= data.get(DbFuneralSyncToVitalsPeer.DATEOFDEATH).toString();
		deathDateKey= data.get(DbFuneralSyncToVitalsPeer.DEATHDATEKEY).toString();
		locationOfDeath= data.get(DbFuneralSyncToVitalsPeer.DEATHPLACE).toString();
		burialDate= data.get(DbFuneralSyncToVitalsPeer.DATEOFBURIAL).toString();
		serviceDateKey= data.get(DbFuneralSyncToVitalsPeer.SERVICEDATEKEY).toString();
		chapelLocation= data.get(DbFuneralSyncToVitalsPeer.CHAPELLOCATION).toString();
		localeNumber= FormatNumber.parseInteger(data.get(DbFuneralSyncToVitalsPeer.LOCALENUMBER).toString());
		chapelNumber= FormatNumber.parseInteger(data.get(DbFuneralSyncToVitalsPeer.CHAPELNUMBER).toString());
		caseCode= data.get(DbFuneralSyncToVitalsPeer.CASECODE).toString();
	}

	public String getDecMrMrs() {
		return decMrMrs;
	}

	public void setDecMrMrs(String decMrMrs) {
		modify();
		this.decMrMrs = decMrMrs;
	}

	public String getDeceasedFirstName() {
		return deceasedFirstName;
	}

	public void setDeceasedFirstName(String deceasedFirstName) {
		modify();
		this.deceasedFirstName = deceasedFirstName;
	}

	public String getDeceasedMidName() {
		return deceasedMidName;
	}

	public void setDeceasedMidName(String deceasedMidName) {
		modify();
		this.deceasedMidName = deceasedMidName;
	}

	public String getDeceasedLastName() {
		return deceasedLastName;
	}

	public void setDeceasedLastName(String deceasedLastName) {
		modify();
		this.deceasedLastName = deceasedLastName;
	}

	public String getDeceasedMaidenName() {
		return deceasedMaidenName;
	}

	public void setDeceasedMaidenName(String deceasedMaidenName) {
		modify();
		this.deceasedMaidenName = deceasedMaidenName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		modify();
		this.dateOfBirth = dateOfBirth;
	}

	public String getDecBirthPlace() {
		return decBirthPlace;
	}

	public void setDecBirthPlace(String decBirthPlace) {
		modify();
		this.decBirthPlace = decBirthPlace;
	}

	public String getDecBirthPlaceCSV() {
		return decBirthPlaceCSV;
	}

	public void setDecBirthPlaceCSV(String decBirthPlaceCSV) {
		modify();
		this.decBirthPlaceCSV = decBirthPlaceCSV;
	}

	public String getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(String dateOfDeath) {
		modify();
		this.dateOfDeath = dateOfDeath;
	}

	public String getLocationOfDeath() {
		return locationOfDeath;
	}

	public void setLocationOfDeath(String locationOfDeath) {
		modify();
		this.locationOfDeath = locationOfDeath;
	}

	public String getBurialDate() {
		return burialDate;
	}

	public void setBurialDate(String burialDate) {
		modify();
		this.burialDate = burialDate;
	}

	public String getServiceDateKey() {
		return serviceDateKey;
	}

	public void setServiceDateKey(String serviceDateKey) {
		modify();
		this.serviceDateKey = serviceDateKey;
	}

	public String getDeceasedFullName() {
		return deceasedFullName;
	}

	public void setDeceasedFullName(String deceasedFullName) {
		modify();
		this.deceasedFullName = deceasedFullName;
	}

	public String getDeathDateKey() {
		return deathDateKey;
	}

	public void setDeathDateKey(String deathDateKey) {
		modify();
		this.deathDateKey = deathDateKey;
	}

	public String getChapelLocation() {
		return chapelLocation;
	}

	public void setChapelLocation(String chapelLocation) {
		modify();
		this.chapelLocation = chapelLocation;
	}

	public int getLocaleNumber() {
		return localeNumber;
	}

	public void setLocaleNumber(int localeNumber) {
		modify();
		this.localeNumber = localeNumber;
	}

	public int getChapelNumber() {
		return chapelNumber;
	}

	public void setChapelNumber(int chapelNumber) {
		modify();
		this.chapelNumber = chapelNumber;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		modify();
		this.caseCode = caseCode;
	}

}
