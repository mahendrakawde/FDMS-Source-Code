package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.PersistenceException;

/**
 * DbLocaleConfigTypePeer supplies constants and SQL for Persistent Class. Creation
 * date: (11/14/2001 4:13:09 PM)
 * 
 * @author:
 */
public class DbLocaleConfigTypePeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY = "localeConfigTypeID";

	static public final String NAME = "name";

	static public final String DESCRIPTION = "description";
	
	static public final String DEFAULTVALUE = "defaultValue";
	
	Logger logger = Logger.getLogger(DbLocaleConfigTypePeer.class.getName());
	
	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {

		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;

		try {
			logger.info("In " + this.getClass() + " getInsertSql() is been called");
			DbLocaleConfigType localeCfg = (DbLocaleConfigType) p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement("INSERT INTO localeconfigtypes (" + 
					NAME + ", " + DESCRIPTION + ", " + DEFAULTVALUE + " ) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, localeCfg.getName());
			pstmt.setString(2, localeCfg.getDescription());
			pstmt.setInt(3, localeCfg.getDefaultValue());

		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbLocaleConfigTypePeer.Insert:" + e.getMessage(), e);
		}

		return pstmt;
	}

	/**
	 * getRemoveSql method comment.
	 */
	protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {

		throw new PersistenceException("DbLocaleConfigTypePeer.Remove Not allowed to remove");

	}

	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return "SELECT * from localeconfigtypes WHERE localeConfigTypeID = " + p.getId();
	}

	/**
	 * getUpdateSql method comment.
	 */
	protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {

		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			DbLocaleConfigType locale = (DbLocaleConfigType) p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement("UPDATE localeconfigtypes SET " + NAME + " = ?," + 
					DESCRIPTION + " = ?," + DEFAULTVALUE + " = ? WHERE localeConfigTypeID = ?");

			pstmt.setString(1, locale.getName());
			pstmt.setString(2, locale.getDescription());
			pstmt.setInt(3, locale.getDefaultValue());
			pstmt.setInt(3, locale.getId());

		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbLocaleConfigTypePeer.Update:" + e.getMessage(), e);
		}

		return pstmt;
	}
}
