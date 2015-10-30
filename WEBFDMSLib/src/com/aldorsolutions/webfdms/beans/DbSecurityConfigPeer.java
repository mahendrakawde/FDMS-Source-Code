package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class DbSecurityConfigPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String SECURITYCONFIGID = "securityconfigID";
	static public final String COMPANYID = "companyID";
	static public final String MINPASSPWDLENGTH = "minPasswordLength";
	static public final String MAXPASSPWDLENGTH = "maxPasswordLength";
	static public final String NUMBERREQUIRED = "numberRequired";
	static public final String SYMBOLREQUIRED = "symbolRequired";
	static public final String MIXEDCASEREQUIRED = "mixedCaseRequired";
	static public final String ADJACENTNUMBERALLWD = "adjacentNumberAllowed";
	static public final String FAILEDLOGINLOCKOUT = "failedLoginLockout";
	static public final String PASSWDCONTAINSUSERNAMEALLWD = "passwordContainsUserNameAllowed";
	static public final String PASSWDEXPIRESENFORCED = "passwordExpirationEnforced";
	static public final String PASSWDEXPIRESINDAYS = "passwordExpirationInDays";
	static public final String PASSWDEXPIRESWARNING = "passwordExpirationWarning";
	static public final String FAILEDLOGINATTEMPTSALLWD = "failedLoginAttemptsAllowed";
	static public final String LOCKOUTDURATION = "lockoutDuration";
	static public final String PASSWDNOTREPEATED = "passwordNotRepeated";
	
	Logger logger = Logger.getLogger(DbSecurityConfigPeer.class.getName());
	
	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			logger.info("In " + this.getClass() + " getInsertSql() is been called");
			DbSecurityConfig settings = (DbSecurityConfig) p;
			connection = t.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO securityconfig (" + 
					COMPANYID + ", " + MINPASSPWDLENGTH + ", " + 
					MAXPASSPWDLENGTH + ", " + NUMBERREQUIRED + ", " + 
					SYMBOLREQUIRED + ", " + MIXEDCASEREQUIRED + ", " + 
					ADJACENTNUMBERALLWD + ", " + FAILEDLOGINLOCKOUT + ", " + 
					PASSWDCONTAINSUSERNAMEALLWD + ", " + 
					PASSWDEXPIRESENFORCED + ", " + PASSWDEXPIRESINDAYS + ", " + 
					PASSWDEXPIRESWARNING + ", " + FAILEDLOGINATTEMPTSALLWD + ", " + 
					LOCKOUTDURATION + ", " + PASSWDNOTREPEATED + ") " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?)");

			java.sql.PreparedStatement statement = null;
			
			statement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int col = 0;
			statement.setLong(++col, settings.getId());
            statement.setInt(++col, settings.getMinPasswordLength());
            statement.setInt(++col, settings.getMaxPasswordLength());
            statement.setBoolean(++col, settings.isNumberRequired());
            statement.setBoolean(++col, settings.isSymbolRequired());
            statement.setBoolean(++col, settings.isMixedCaseRequired());
            statement.setBoolean(++col, settings.isAdjacentNumberAllowed());
            statement.setBoolean(++col, settings.isFailedLoginLockout());
            statement.setBoolean(++col, settings.isPasswordContainsUserNameAllowed());
            statement.setBoolean(++col, settings.isPasswordExpirationEnforced());
            statement.setInt(++col, settings.getPasswordExpirationInDays());
            statement.setInt(++col, settings.getPasswordExpirationWarningInDays());
            statement.setInt(++col, settings.getFailedLoginAttemptsAllowed());
            statement.setInt(++col, settings.getLockoutDuration());
            statement.setBoolean(++col, settings.isPasswordNotRepeated() );

			return statement;
		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException(
					"DbSecurityConfigPeer.Insert:" + e.getMessage(), e);
		}
	}

	/**
	 * getRemoveSql method comment.
	 */
	protected java.sql.PreparedStatement getRemoveSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.PreparedStatement pstmt = null;
		java.sql.Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection
					.prepareStatement("DELETE FROM securityconfig WHERE " + 
							COMPANYID + "=?");
			pstmt.setInt(1, p.getId());
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException(
					"DbSecurityConfig.Remove", e);
		}
	}
	


	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return "SELECT " + SECURITYCONFIGID + ", " + COMPANYID + ", " + 
			   MINPASSPWDLENGTH + ", " + MAXPASSPWDLENGTH + ", " + 
			   NUMBERREQUIRED + ", " + SYMBOLREQUIRED + ", " + 
			   MIXEDCASEREQUIRED + ", " + ADJACENTNUMBERALLWD + ", " + 
			   FAILEDLOGINLOCKOUT + ", " + PASSWDCONTAINSUSERNAMEALLWD + ", " + 
			   PASSWDEXPIRESENFORCED + ", " + PASSWDEXPIRESINDAYS + ", " + 
			   PASSWDEXPIRESWARNING + ", " + FAILEDLOGINATTEMPTSALLWD + ", " + 
			   LOCKOUTDURATION + ", " + PASSWDNOTREPEATED + " " +
			   "FROM securityconfig where " + COMPANYID + 
			   " = " + p.getId();
	}

	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, HashMap h) {
		
		StringBuffer sql = new StringBuffer ();
		
		sql.append("SELECT " + SECURITYCONFIGID + ", " + COMPANYID + ", " + 
			   MINPASSPWDLENGTH + ", " + MAXPASSPWDLENGTH + ", " + 
			   NUMBERREQUIRED + ", " + SYMBOLREQUIRED + ", " + 
			   MIXEDCASEREQUIRED + ", " + ADJACENTNUMBERALLWD + ", " + 
			   FAILEDLOGINLOCKOUT + ", " + PASSWDCONTAINSUSERNAMEALLWD + ", " + 
			   PASSWDEXPIRESENFORCED + ", " + PASSWDEXPIRESINDAYS + ", " + 
			   PASSWDEXPIRESWARNING + ", " + FAILEDLOGINATTEMPTSALLWD + ", " + 
			   LOCKOUTDURATION + ", " + PASSWDNOTREPEATED + " " +
			   "FROM securityconfig");
		
		if (h.containsKey(COMPANYID)) {
			sql.append(" where " + COMPANYID + "=");
			sql.append(h.get(COMPANYID).toString());
		}
		
		return ( sql.toString() );
	}

	/**
	 * getUpdateSql method comment.
	 */
	protected java.sql.PreparedStatement getUpdateSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbSecurityConfig settings = (DbSecurityConfig) p;
			connection = t.getConnection();
			java.sql.PreparedStatement statement = null;

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE securityconfig SET " +  
					SECURITYCONFIGID + " = ?, " + MINPASSPWDLENGTH + " = ?, " + 
					MAXPASSPWDLENGTH + " = ?, " + NUMBERREQUIRED + " = ?, " + 
					SYMBOLREQUIRED + " = ?, " + MIXEDCASEREQUIRED + " = ?, " + 
					ADJACENTNUMBERALLWD + " = ?, " + FAILEDLOGINLOCKOUT + " = ?, " + 
					PASSWDCONTAINSUSERNAMEALLWD + " = ?, " + PASSWDEXPIRESENFORCED + " = ?, " + 
					PASSWDEXPIRESINDAYS + " = ?, " + PASSWDEXPIRESWARNING + " = ?, " + 
					FAILEDLOGINATTEMPTSALLWD + " = ?, " + LOCKOUTDURATION + " = ?, " + 
					PASSWDNOTREPEATED + " = ? " + "WHERE ("+ COMPANYID + " = ?)");

			statement = connection.prepareStatement(sql.toString());

			int col = 0;
			statement.setLong(++col, settings.getSecureConfigID());
            statement.setInt(++col, settings.getMinPasswordLength());
            statement.setInt(++col, settings.getMaxPasswordLength());
            statement.setBoolean(++col, settings.isNumberRequired());
            statement.setBoolean(++col, settings.isSymbolRequired());
            statement.setBoolean(++col, settings.isMixedCaseRequired());
            statement.setBoolean(++col, settings.isAdjacentNumberAllowed());
            statement.setBoolean(++col, settings.isFailedLoginLockout());
            statement.setBoolean(++col, settings.isPasswordContainsUserNameAllowed());
            statement.setBoolean(++col, settings.isPasswordExpirationEnforced());
            statement.setInt(++col, settings.getPasswordExpirationInDays());
            statement.setInt(++col, settings.getPasswordExpirationWarningInDays());
            statement.setInt(++col, settings.getFailedLoginAttemptsAllowed());
            statement.setInt(++col, settings.getLockoutDuration());
            statement.setBoolean(++col, settings.isPasswordNotRepeated() );
            statement.setLong(++col, settings.getId());

			return statement;
		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException(
					"DbSecurityConfigPeer.Update:" + e.getMessage(), e);
		}

	}
}
