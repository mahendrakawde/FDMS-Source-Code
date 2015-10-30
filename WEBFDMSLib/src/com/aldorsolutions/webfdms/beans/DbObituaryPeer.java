package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * DbObituaryPeer supplies constants and SQL for Persistent Class.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author: 
 */
public class DbObituaryPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY    	= "VitalsId";
	static public final String CASE_ID	 	= "VitalsId";
	static public final String OBITNOTICE 	= "ObitNotice";
	static public final String ASIMASID		= "AsimasId";
	static public final String OBITUARY_LINK = "ObituaryLink";
	
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
	java.sql.Connection connection = null;
	try {
		DbObituary obit=(DbObituary)p;
		connection = t.getConnection();
		java.sql.PreparedStatement pstmt = connection.prepareStatement(
		"INSERT INTO obituary (VitalsId, ObitNotice, ObituaryLink) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS
		);
		pstmt.setInt   (1, obit.getId() );
		pstmt.setString(2, obit.getObitNotice());	
		pstmt.setString(3, obit.getObituaryLink());	
		return pstmt;
	}
	catch (java.sql.SQLException e){
		throw new com.aldorsolutions.webfdms.database.PersistenceException("DbObituaryPeer.Insert:"+e.getMessage(),e);
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
			pstmt = connection.prepareStatement("DELETE FROM obituary WHERE VitalsId=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbObituary.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return 
		"SELECT "
		+ CASE_ID
		+"," + OBITNOTICE
		+"," + ASIMASID
		+"," + OBITUARY_LINK
		+" from obituary WHERE VitalsId = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbObituary obit=(DbObituary)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE obituary SET "+
						"ObitNotice=?, "+
						"ObituaryLink=?, "+
						"ASIMASID=? "+
						"WHERE VitalsId = ?"
			);		
			pstmt.setString(1, obit.getObitNotice());
			pstmt.setString(2, obit.getObituaryLink());
			pstmt.setLong(3, obit.getAsimasId());
			pstmt.setInt   (4, obit.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbObituaryPeer.Update:"+e.getMessage(),e);
		}
}
}
