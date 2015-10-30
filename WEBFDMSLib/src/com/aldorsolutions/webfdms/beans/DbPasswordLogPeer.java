package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import org.apache.log4j.Logger;

public class DbPasswordLogPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String PASSWORDLOGID = "passwordLogID";

	static public final String USERID = "userID";

	static public final String PASSWORD = "password";

	static public final String TMSTMP = "tmstmp";
	
	Logger logger = Logger.getLogger(DbPasswordLogPeer.class.getName());

	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			logger.info("In " + this.getClass() + " getInsertSql() is been called");
			DbPasswordLog passLog = (DbPasswordLog) p;
			connection = t.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO passwordlog (userID, ");
			sql.append("password, tmstmp) VALUES (?, ?, ?)");

			java.sql.PreparedStatement statement = null;
			
			statement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int col = 0;
			statement.setLong(++col, passLog.getUserID());
			statement.setString(++col, passLog.getPassword());
			statement.setTimestamp(++col, passLog.getTmstmp());

			return statement;
		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException(
					"DbPasswordLogPeer.Insert:" + e.getMessage(), e);
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
			pstmt = connection.prepareStatement("DELETE FROM passwordlog WHERE passwordlogID=?");
			pstmt.setInt(1, p.getId());
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException(
					"DbPasswordLog.Remove", e);
		}
	}

	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return "SELECT " + PASSWORDLOGID + ", " + USERID + ", " + PASSWORD + ", " 
				+ TMSTMP + " FROM passwordlog where passwordlogid = " + p.getId();
		        
	}

	/**
	 * getUpdateSql method comment.
	 */
	protected java.sql.PreparedStatement getUpdateSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbPasswordLog passLog = (DbPasswordLog) p;
			connection = t.getConnection();
			java.sql.PreparedStatement statement = null;

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE passwordlog SET userID = ?, password = ?, ");
			sql.append("tmstmp = ? WHERE (passwordLogID = ?)");

			statement = connection.prepareStatement(sql.toString());

			int col = 0;
			statement.setLong(++col, passLog.getUserID());
			statement.setString(++col, passLog.getPassword());
			statement.setTimestamp(++col, passLog.getTmstmp());
			statement.setLong(++col, passLog.getPasswordLogID());

			return statement;
		} catch (java.sql.SQLException e) {
			throw new com.aldorsolutions.webfdms.database.PersistenceException(
					"DbPasswordLogPeer.Update:" + e.getMessage(), e);
		}

	}
}
