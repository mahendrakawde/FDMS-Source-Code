package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * DbMemorialPeer supplies constants and SQL for Persistent Class.
 * Creation date: (10/08/2001 4:13:09 PM)
 * @author: 
 */
public class DbMemorialPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY    	= "memorialID";
	static public final String LOCALE	    = "Locale";
	static public final String DESCRIPTION	= "description";
	static public final String VERSE		= "verse";
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbMemorial rec=(DbMemorial)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"INSERT INTO memorial ("+
				DESCRIPTION +","+
				LOCALE +","+
				VERSE +" "+
				") VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS
			);		
			pstmt.setString(1, rec.getDescription());	
			pstmt.setInt   (2, rec.getLocaleID());
			pstmt.setString(3, rec.getVerse());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbMemorialPeer.Insert:"+e.getMessage(),e);
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
			pstmt = connection.prepareStatement("DELETE FROM  memorial WHERE memorialID=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbMemorialPeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return 
		"SELECT * from memorial WHERE memorialID = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbMemorial rec=(DbMemorial)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE memorial SET "+
				DESCRIPTION +"=?,"+
				LOCALE +"=?,"+
				VERSE +"=? "+
				" WHERE "+IDENTITY+" = ?"
			);		
			pstmt.setString(1, rec.getDescription());	
			pstmt.setInt   (2, rec.getLocaleID());
			pstmt.setString(3, rec.getVerse());
			pstmt.setInt   (4, rec.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbMemorial.Update:"+e.getMessage(),e);
		}
}
}
