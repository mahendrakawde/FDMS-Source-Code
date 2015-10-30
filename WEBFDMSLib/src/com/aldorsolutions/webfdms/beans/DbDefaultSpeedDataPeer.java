package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * DbDefaultSpeedDataPeer supplies SQL for Persistent Class
 * Creation date: (12/30/2001 4:13:09 PM)
 * @author: 
 */
public class DbDefaultSpeedDataPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY    	= "Identity";
	static public final String CATEGORY	 	= "TabCategory";
	static public final String LOCALE		= "Locale";
	static public final String DATA	    	= "TabData";
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {

		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt=null;
		try {
			DbDefaultSpeedData item=(DbDefaultSpeedData)p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
			"INSERT INTO defaultspeeddata (Identity, TabCategory, Locale, TabData) VALUES (0,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, item.getCategory());
			pstmt.setInt   (2, item.getLocale());
			pstmt.setString (3, item.getData());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbDefaultSpeedDataPeer.Insert",e);
		}
}
/**
 * getRemoveSql method comment.
 */
protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.PreparedStatement pstmt=null;
		java.sql.Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection.prepareStatement("DELETE FROM defaultspeeddata WHERE Identity=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbDefaultSpeedData.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return 
		"SELECT * FROM defaultspeeddata WHERE Identity = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt=null;
		try {
			DbDefaultSpeedData item=(DbDefaultSpeedData)p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
			"UPDATE defaultspeeddata SET TabCategory=?, Locale=?, TabData=? WHERE Identity=?");
			pstmt.setString(1, item.getCategory());
			pstmt.setInt   (2, item.getLocale());
			pstmt.setString (3, item.getData());
			pstmt.setInt	(4, item.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbDefaultSpeedDataPeer.Update",e);
		}
}
}
