package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;


public class DbSecurityConfig extends com.aldorsolutions.webfdms.database.Persistent {
	
	static private final DbSecurityConfigPeer peer = new DbSecurityConfigPeer();

	public static int DEFAULT_MIN_LENGTH = 0;
	public static int DEFAULT_MAX_LENGTH = 30;
	
	public static final int LOCKOUT_DURATION_1_HOUR = 1;

	public static final int LOCKOUT_DURATION_2_HOURS = 2;

	public static final int LOCKOUT_DURATION_4_HOURS = 3;
	
	public static final int LOCKOUT_DURATION_8_HOURS = 4;
	
	public static final int LOCKOUT_DURATION_MIDNIGHT = 5;
	
	public static final int LOCKOUT_DURATION_24_HOURS = 6;

	public static final int LOCKOUT_DURATION_ADMIN_REQUIRED = 7;

	private long secureConfigID = 0;
	
	private int minPasswordLength = DEFAULT_MIN_LENGTH;
	
	private int maxPasswordLength = DEFAULT_MAX_LENGTH;

	private boolean numberRequired = false;

	private boolean symbolRequired = false;

	private boolean mixedCaseRequired = false;

	private boolean adjacentNumberAllowed = false;

	private boolean failedLoginLockout = false;
	
	private boolean passwordContainsUserNameAllowed = false;

	private boolean passwordExpirationEnforced = false;

	private int passwordExpirationInDays = 0;

	private int failedLoginAttemptsAllowed = 0;

	private int lockoutDuration = 0;

	private int passwordExpirationWarningInDays = 0;

	private boolean passwordNotRepeated = false;
	
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
	 * Move data from hashtable and copy into class variables Peer object
	 * restores from database to hashtable.
	 */
	public void restore(Transaction t, java.util.Hashtable data)
			throws PersistenceException {
		setId(FormatNumber.parseInteger(data.get(DbSecurityConfigPeer.COMPANYID)
				.toString()));
		secureConfigID = FormatNumber.parseInteger(data.get(DbSecurityConfigPeer.SECURITYCONFIGID).toString());;
    	minPasswordLength = FormatNumber.parseInteger(data.get(DbSecurityConfigPeer.MINPASSPWDLENGTH).toString());
    	maxPasswordLength = FormatNumber.parseInteger(data.get(DbSecurityConfigPeer.MAXPASSPWDLENGTH).toString());;
    	numberRequired = getChecked(data.get(DbSecurityConfigPeer.NUMBERREQUIRED));
    	symbolRequired = getChecked(data.get(DbSecurityConfigPeer.SYMBOLREQUIRED));
    	mixedCaseRequired = getChecked(data.get(DbSecurityConfigPeer.MIXEDCASEREQUIRED));
    	adjacentNumberAllowed = getChecked(data.get(DbSecurityConfigPeer.ADJACENTNUMBERALLWD));
    	failedLoginLockout = getChecked(data.get(DbSecurityConfigPeer.FAILEDLOGINLOCKOUT));
    	passwordContainsUserNameAllowed = getChecked(data.get(DbSecurityConfigPeer.PASSWDCONTAINSUSERNAMEALLWD));
    	passwordExpirationEnforced = getChecked(data.get(DbSecurityConfigPeer.PASSWDEXPIRESENFORCED));
    	passwordExpirationInDays = FormatNumber.parseInteger(data.get(DbSecurityConfigPeer.PASSWDEXPIRESINDAYS).toString());
    	passwordExpirationWarningInDays = FormatNumber.parseInteger(data.get(DbSecurityConfigPeer.PASSWDEXPIRESWARNING).toString());
    	failedLoginAttemptsAllowed = FormatNumber.parseInteger(data.get(DbSecurityConfigPeer.FAILEDLOGINATTEMPTSALLWD).toString());
    	lockoutDuration = FormatNumber.parseInteger(data.get(DbSecurityConfigPeer.LOCKOUTDURATION).toString());
    	passwordNotRepeated = getChecked(data.get(DbSecurityConfigPeer.PASSWDNOTREPEATED));
		
	}

	private boolean getChecked(Object value) {
		String strVal = value.toString();

		if (strVal != null && strVal.equals("1")) {
			return (true);
		} else {
			return (false);
		}

	}
	
	/**
	 * Get the ID key for this object from the hashtable. DbUser objects can be
	 * accessed by "Name" So, first see if "Name" is being used for restoring or
	 * if ID# is being used.
	 */
	public void setId(java.util.Hashtable h) {
		setId(((Long) h.get(DbSecurityConfigPeer.COMPANYID)).intValue());
	}

	/**
	 * @return Returns the adjacentNumberAllowed.
	 */
	public boolean isAdjacentNumberAllowed() {
		return adjacentNumberAllowed;
	}

	/**
	 * @param adjacentNumberAllowed The adjacentNumberAllowed to set.
	 */
	public void setAdjacentNumberAllowed(boolean adjacentNumberAllowed) {
		this.adjacentNumberAllowed = adjacentNumberAllowed;
		modify();
	}

	/**
	 * @return Returns the companyID.
	 */
	public long getSecureConfigID() {
		return secureConfigID;
	}

	/**
	 * @param companyID The companyID to set.
	 */
	public void setSecureConfigID(long secureConfigID) {
		this.secureConfigID = secureConfigID;
		modify();
	}

	/**
	 * @return Returns the failedLoginAttemptsAllowed.
	 */
	public int getFailedLoginAttemptsAllowed() {
		return failedLoginAttemptsAllowed;
	}

	/**
	 * @param failedLoginAttemptsAllowed The failedLoginAttemptsAllowed to set.
	 */
	public void setFailedLoginAttemptsAllowed(int failedLoginAttemptsAllowed) {
		this.failedLoginAttemptsAllowed = failedLoginAttemptsAllowed;
		modify();
	}

	/**
	 * @return Returns the failedLoginLockout.
	 */
	public boolean isFailedLoginLockout() {
		return failedLoginLockout;
	}

	/**
	 * @param failedLoginLockout The failedLoginLockout to set.
	 */
	public void setFailedLoginLockout(boolean failedLoginLockout) {
		this.failedLoginLockout = failedLoginLockout;
		modify();
	}

	/**
	 * @return Returns the lockoutDuration.
	 */
	public int getLockoutDuration() {
		return lockoutDuration;
	}

	/**
	 * @param lockoutDuration The lockoutDuration to set.
	 */
	public void setLockoutDuration(int lockoutDuration) {
		this.lockoutDuration = lockoutDuration;
		modify();
	}

	/**
	 * @return Returns the maxPasswordLength.
	 */
	public int getMaxPasswordLength() {
		return maxPasswordLength;
	}

	/**
	 * @param maxPasswordLength The maxPasswordLength to set.
	 */
	public void setMaxPasswordLength(int maxPasswordLength) {
		this.maxPasswordLength = maxPasswordLength;
		modify();
	}

	/**
	 * @return Returns the minPasswordLength.
	 */
	public int getMinPasswordLength() {
		return minPasswordLength;
	}

	/**
	 * @param minPasswordLength The minPasswordLength to set.
	 */
	public void setMinPasswordLength(int minPasswordLength) {
		this.minPasswordLength = minPasswordLength;
		modify();
	}

	/**
	 * @return Returns the mixedCaseRequired.
	 */
	public boolean isMixedCaseRequired() {
		return mixedCaseRequired;
	}

	/**
	 * @param mixedCaseRequired The mixedCaseRequired to set.
	 */
	public void setMixedCaseRequired(boolean mixedCaseRequired) {
		this.mixedCaseRequired = mixedCaseRequired;
		modify();
	}

	/**
	 * @return Returns the numberRequired.
	 */
	public boolean isNumberRequired() {
		return numberRequired;
	}

	/**
	 * @param numberRequired The numberRequired to set.
	 */
	public void setNumberRequired(boolean numberRequired) {
		this.numberRequired = numberRequired;
		modify();
	}

	/**
	 * @return Returns the passwordContainsUserNameAllowed.
	 */
	public boolean isPasswordContainsUserNameAllowed() {
		return passwordContainsUserNameAllowed;
	}

	/**
	 * @param passwordContainsUserNameAllowed The passwordContainsUserNameAllowed to set.
	 */
	public void setPasswordContainsUserNameAllowed(
			boolean passwordContainsUserNameAllowed) {
		this.passwordContainsUserNameAllowed = passwordContainsUserNameAllowed;
		modify();
	}

	/**
	 * @return Returns the passwordExpirationEnforced.
	 */
	public boolean isPasswordExpirationEnforced() {
		return passwordExpirationEnforced;
	}

	/**
	 * @param passwordExpirationEnforced The passwordExpirationEnforced to set.
	 */
	public void setPasswordExpirationEnforced(boolean passwordExpirationEnforced) {
		this.passwordExpirationEnforced = passwordExpirationEnforced;
		modify();
	}

	/**
	 * @return Returns the passwordExpirationInDays.
	 */
	public int getPasswordExpirationInDays() {
		return passwordExpirationInDays;
	}

	/**
	 * @param passwordExpirationInDays The passwordExpirationInDays to set.
	 */
	public void setPasswordExpirationInDays(int passwordExpirationInDays) {
		this.passwordExpirationInDays = passwordExpirationInDays;
		modify();
	}

	/**
	 * @return Returns the passwordExpirationWarningInDays.
	 */
	public int getPasswordExpirationWarningInDays() {
		return passwordExpirationWarningInDays;
	}

	/**
	 * @param passwordExpirationWarningInDays The passwordExpirationWarningInDays to set.
	 */
	public void setPasswordExpirationWarningInDays(
			int passwordExpirationWarningInDays) {
		this.passwordExpirationWarningInDays = passwordExpirationWarningInDays;
		modify();
	}

	/**
	 * @return Returns the symbolRequired.
	 */
	public boolean isSymbolRequired() {
		return symbolRequired;
	}

	/**
	 * @param symbolRequired The symbolRequired to set.
	 */
	public void setSymbolRequired(boolean symbolRequired) {
		this.symbolRequired = symbolRequired;
		modify();
	}

	/**
	 * @return Returns the passwordNotRepeated.
	 */
	public boolean isPasswordNotRepeated() {
		return passwordNotRepeated;
	}

	/**
	 * @param passwordNotRepeated The passwordNotRepeated to set.
	 */
	public void setPasswordNotRepeated(boolean passwordNotRepeated) {
		this.passwordNotRepeated = passwordNotRepeated;
		modify();
	}

}
