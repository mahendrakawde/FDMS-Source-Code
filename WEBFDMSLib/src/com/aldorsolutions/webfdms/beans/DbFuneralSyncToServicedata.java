package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * Class represents additional vital information needed to support "First Call"
 * user interface. Creation date: (1/6/2002 9:26:03 AM)
 * 
 * @author:
 */
public class DbFuneralSyncToServicedata extends com.aldorsolutions.webfdms.database.Persistent {
	static private final DbFuneralSyncToServicedataPeer peer = new DbFuneralSyncToServicedataPeer();

	private int caseId;
	private String dayofweek; 
	private String timeOfService; 
	/**
	 * DbVitalsFirstCall constructor comment.
	 */
	public DbFuneralSyncToServicedata() {
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
		setId(((Integer) h.get(DbFuneralSyncToServicedataPeer.CASE_ID)).intValue());
	}

	

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		modify();
		this.caseId = caseId;
		setId(caseId);
	}

	/**
	 * restore method comment.
	 */
	public void restore(com.aldorsolutions.webfdms.database.Transaction t, java.util.Hashtable data)
			throws com.aldorsolutions.webfdms.database.PersistenceException {

		setId(FormatNumber.parseInteger(data.get(DbFuneralSyncToServicedataPeer.CASE_ID).toString()));
		caseId= FormatNumber.parseInteger(data.get(DbFuneralSyncToServicedataPeer.CASE_ID).toString());
		dayofweek= data.get(DbFuneralSyncToServicedataPeer.DAYOFWEEK).toString();
		timeOfService= data.get(DbFuneralSyncToServicedataPeer.TIMEOFSERVICE).toString();
		
	}

	public String getDayofweek() {
		return dayofweek;
	}

	public void setDayofweek(String dayofweek) {
		modify();
		this.dayofweek = dayofweek;
	}

	public String getTimeOfService() {
		return timeOfService;
	}

	public void setTimeOfService(String timeOfService) {
		modify();
		this.timeOfService = timeOfService;
	}



}
