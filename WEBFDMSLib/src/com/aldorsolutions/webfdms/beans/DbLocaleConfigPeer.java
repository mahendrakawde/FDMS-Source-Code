package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import org.apache.log4j.Logger;


/**
 * DbLocaleConfigPeer supplies constants and SQL for Persistent Class. Creation
 * date: (11/14/2001 4:13:09 PM)
 * 
 * @author:
 */
public class DbLocaleConfigPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY = "localeConfigID";

	static public final String LOCALEID = "localeID";

	static public final String LOCALECFGTYPEID = "localeConfigTypeID";
	
	static public final String VALUE = "value";

	Logger logger = Logger.getLogger(DbLocaleConfigPeer.class.getName());
	
	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {

		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;

		try {
			logger.info("In " + this.getClass() + " getInsertSql() is been called");
			DbLocaleConfig localeCfg = (DbLocaleConfig) p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement("INSERT INTO localeconfigs (" + 
					LOCALEID + "," + LOCALECFGTYPEID + "," + VALUE + " ) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, localeCfg.getLocaleID());
			pstmt.setInt(2, localeCfg.getLocaleConfigTypeID());
			pstmt.setInt(3, localeCfg.getValue());

		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbLocaleConfigPeer.Insert:" + e.getMessage(), e);
		}

		return pstmt;
	}

	/**
	 * getRemoveSql method comment.
	 */
	protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		
		java.sql.PreparedStatement pstmt=null;
		java.sql.Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection.prepareStatement("DELETE FROM  localeconfigs WHERE localeconfigID=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbLocaleConfigPeer.Remove",e);
		}

	}

	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return "SELECT * from localeconfigs WHERE localeConfigID = " + p.getId();
	}

	/**
	 * getUpdateSql method comment.
	 */
	protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {

		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			DbLocaleConfig locale = (DbLocaleConfig) p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement("UPDATE localeconfigs SET " + 
					LOCALEID + " = ?," + LOCALECFGTYPEID + " = ?," + VALUE + " = ? " +
					"WHERE localeconfigID = ?");

			pstmt.setInt(1, locale.getLocaleID());
			pstmt.setInt(2, locale.getLocaleConfigTypeID());
			pstmt.setInt(3, locale.getValue());
			pstmt.setInt(4, locale.getId());

		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbLocaleConfigPeer.Update:" + e.getMessage(), e);
		}

		return pstmt;
	}
}
