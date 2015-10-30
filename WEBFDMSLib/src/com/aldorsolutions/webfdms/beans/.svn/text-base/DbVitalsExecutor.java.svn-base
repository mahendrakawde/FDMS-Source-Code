package com.aldorsolutions.webfdms.beans;

/**
 * This class represents information for the executor for one case.
 * 
 * @author:
 */
public class DbVitalsExecutor extends com.aldorsolutions.webfdms.database.Persistent {
	static private final DbVitalsExecutorPeer peer = new DbVitalsExecutorPeer();

	private String executorPersonId;

	private String executorFirstName;

	private String executorLastName;

	private String executorRelation;

	private String isExecutorSame;

	private String executorCity;

	private String executorStreet;

	private String executorState;

	private String executorZip;

	private String executorPhone;

	private String executorCellPhone;

	private String executorStreet2;

	private String executorStreet3;

	private String executorEmail;

	/**
	 * DbVitalsExecutor constructor comment.
	 */
	public DbVitalsExecutor() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:52:05
	 * PM)
	 * 
	 * @return String
	 */
	public String getExecutorCity() {
		return executorCity;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:49:53
	 * PM)
	 * 
	 * @return String
	 */
	public String getExecutorFirstname() {
		return executorFirstName;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:50:26
	 * PM)
	 * 
	 * @return String
	 */
	public String getExecutorLastname() {
		return executorLastName;
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
	public String getExecutorPhone() {
		return executorPhone;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:51:08
	 * PM)
	 * 
	 * @return String
	 */
	public String getExecutorRelation() {
		return executorRelation;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:25:18
	 * PM)
	 * 
	 * @return String
	 */
	public String getExecutorStreet2() {
		return executorStreet2;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:25:29
	 * PM)
	 * 
	 * @return String
	 */
	public String getExecutorStreet3() {
		return executorStreet3;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:51:47
	 * PM)
	 * 
	 * @return String
	 */
	public String getIsExecutorSame() {
		return isExecutorSame;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:24:36
	 * PM)
	 * 
	 * @return String
	 */
	public String getExecutorState() {
		return executorState;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:52:20
	 * PM)
	 * 
	 * @return String
	 */
	public String getExecutorStreet() {
		return executorStreet;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:24:50
	 * PM)
	 * 
	 * @return String
	 */
	public String getExecutorZip() {
		return executorZip;
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

		setId(Integer.parseInt(data.get(DbVitalsExecutorPeer.VITALSMASTERKEY)
				.toString()));
		executorPersonId = data.get(DbVitalsExecutorPeer.PERSONID).toString();
		executorCity = data.get(DbVitalsExecutorPeer.EXECUTORCITY).toString();
		executorFirstName = data.get(DbVitalsExecutorPeer.EXECUTORFIRSTNAME)
				.toString();
		executorLastName = data.get(DbVitalsExecutorPeer.EXECUTORLASTNAME)
				.toString();
		executorPhone = data.get(DbVitalsExecutorPeer.EXECUTORPHONE).toString();
		executorCellPhone = data.get(DbVitalsExecutorPeer.EXECUTORCELLPHONE).toString();
		executorRelation = data.get(DbVitalsExecutorPeer.EXECUTORRELATION)
				.toString();
		executorStreet2 = data.get(DbVitalsExecutorPeer.EXECUTORSTREET2)
				.toString();
		executorStreet3 = data.get(DbVitalsExecutorPeer.EXECUTORSTREET3)
				.toString();
		executorState = data.get(DbVitalsExecutorPeer.EXECUTORSTATE).toString();
		executorStreet = data.get(DbVitalsExecutorPeer.EXECUTORSTREET)
				.toString();
		executorZip = data.get(DbVitalsExecutorPeer.EXECUTORZIP).toString();
		isExecutorSame = data.get(DbVitalsExecutorPeer.EXECUTORSAME).toString();
		executorEmail = data.get(DbVitalsExecutorPeer.EXECUTOREMAIL).toString();

	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:52:05
	 * PM)
	 * 
	 * @param newCity
	 *            String
	 */
	public void setExecutorCity(String newCity) {
		modify();
		executorCity = newCity;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:49:53
	 * PM)
	 * 
	 * @param newFirstname
	 *            String
	 */
	public void setExecutorFirstname(String newFirstname) {

		modify();
		executorFirstName = newFirstname;

	}

	/**
	 * setId method comment.
	 */
	public void setId(java.util.Hashtable h) {

		setId(((Integer) h.get(DbVitalsExecutorPeer.VITALSMASTERKEY))
				.intValue());

	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:50:26
	 * PM)
	 * 
	 * @param newLastname
	 *            String
	 */
	public void setExecutorLastname(String newLastname) {
		modify();
		executorLastName = newLastname;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:25:03
	 * PM)
	 * 
	 * @param newPhone
	 *            String
	 */
	public void setExecutorPhone(String newPhone) {
		modify();
		executorPhone = newPhone;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:51:08
	 * PM)
	 * 
	 * @param newRelation
	 *            String
	 */
	public void setExecutorRelation(String newRelation) {
		modify();
		executorRelation = newRelation;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:25:18
	 * PM)
	 * 
	 * @param newRoad2
	 *            String
	 */
	public void setExecutorStreet2(String newRoad2) {
		modify();
		executorStreet2 = newRoad2;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:25:29
	 * PM)
	 * 
	 * @param newRoad3
	 *            String
	 */
	public void setExecutorStreet3(String newRoad3) {
		modify();
		executorStreet3 = newRoad3;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:51:47
	 * PM)
	 * 
	 * @param newSameAsInformant
	 *            String
	 */
	public void setIsExecutorSame(String newSameAsInformant) {
		modify();
		isExecutorSame = newSameAsInformant;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:24:36
	 * PM)
	 * 
	 * @param newState
	 *            String
	 */
	public void setExecutorState(String newState) {
		modify();
		executorState = newState;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 3:52:20
	 * PM)
	 * 
	 * @param newStreet
	 *            String
	 */
	public void setExecutorStreet(String newStreet) {
		modify();
		executorStreet = newStreet;
	}

	/**
	 * Insert the method's description here. Creation date: (3/4/2002 4:24:50
	 * PM)
	 * 
	 * @param newZip
	 *            String
	 */
	public void setExecutorZip(String newZip) {
		modify();
		executorZip = newZip;
	}

	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("[executorFirstName=");
		strBuf.append(executorFirstName);
		strBuf.append(", executorLastName=");
		strBuf.append(executorLastName);
		strBuf.append("]");
		return strBuf.toString();
	}

	/**
	 * @return Returns the executorPersonId.
	 */
	public String getExecutorPersonId() {
		return executorPersonId;
	}

	/**
	 * @param executorPersonId
	 *            The executorPersonId to set.
	 */
	public void setExecutorPersonId(String executorPersonId) {
		this.executorPersonId = executorPersonId;
		modify();
	}

	/**
	 * @return Returns the executorEmail.
	 */
	public String getExecutorEmail() {
		return executorEmail;
	}

	/**
	 * @param executorEmail
	 *            The executorEmail to set.
	 */
	public void setExecutorEmail(String executorEmail) {
		this.executorEmail = executorEmail;
		modify();
	}

	public String getExecutorCellPhone() {
		return executorCellPhone;
	}

	public void setExecutorCellPhone(String executorCellPhone) {
		this.executorCellPhone = executorCellPhone;
		modify();
	}
	
}
