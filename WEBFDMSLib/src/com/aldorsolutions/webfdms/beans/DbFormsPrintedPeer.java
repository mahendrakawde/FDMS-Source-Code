package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * DbFormsPrintedPeer supplies constants and SQL for Persistent Class.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author: 
 */
public class DbFormsPrintedPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY    	= "PrintedId";
	static public final String CASEID		= "CaseId";
	static public final String FORMID		= "FormId";
	static public final String COMPLETED	= "CompletedYN";
	static public final String DATE			= "CompletedDate";
	static public final String TIME			= "CompletedTime";
	
	Logger logger = Logger.getLogger(DbFormsPrintedPeer.class.getName());
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {

		java.sql.Connection connection = null;
		try {
			logger.info("In " + this.getClass() + " getInsertSql() is been called");
			DbFormsPrinted myform=(DbFormsPrinted)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"INSERT INTO formsprinted ("+
						"PrintedId, "+
						"CaseId, " +
						"FormId, " +
						"CompletedYN, " +
						"CompletedDate, " +
						"CompletedTime "+
						") VALUES (0,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
			);		
			pstmt.setInt(1, myform.getCaseId());
			pstmt.setInt(2, myform.getFormId());
			if (myform.isCompleted()) pstmt.setString(3, "Y");
			else pstmt.setString(3,"N");
			pstmt.setDate(4,myform.getCompletedDate());
			pstmt.setTime(5,myform.getCompletedTime());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFormsPrintedPeer.Insert:"+e.getMessage(),e);
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
			pstmt = connection.prepareStatement("DELETE FROM formsprinted WHERE PrintedId=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFormsPrintedPeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return 
		"SELECT * from formsprinted WHERE PrintedId = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbFormsPrinted myform=(DbFormsPrinted)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE formsprinted SET "+
						"CaseId=?, " +
						"FormId=?, " +
						"CompletedYN=?, " +
						"CompletedDate=?, " +
						"CompletedTime=? "+
						"WHERE PrintedId = ?"
			);		
			pstmt.setInt(1, myform.getCaseId());
			pstmt.setInt(2, myform.getFormId());
			if (myform.isCompleted()) pstmt.setString(3, "Y");
			else pstmt.setString(3,"N");
			pstmt.setDate(4,myform.getCompletedDate());
			pstmt.setTime(5,myform.getCompletedTime());
			pstmt.setInt(6,myform.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFormsPrintedPeer.Update:"+e.getMessage(),e);
		}
}
}
