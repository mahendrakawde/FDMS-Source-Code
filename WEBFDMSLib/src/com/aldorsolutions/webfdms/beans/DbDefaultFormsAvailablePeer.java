package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * DbLocalePeer supplies constants and SQL for Persistent Class.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author: 
 */
public class DbDefaultFormsAvailablePeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY    	= "FormId";
	static public final String LOCALE		= "Locale";
	static public final String DESCRIPTION	= "Description";
	static public final String REPORTNAME	= "ReportName";
	static public final String SELFORMULA   = "SelectionFormula";
	static public final String CATEGORY		= "Category";
	static public final String EXPORTTYPE  	= "exportType";
	static public final String MARGINLEFT  	= "MarginLeft";
	static public final String MARGINRIGHT 	= "MarginRight";
	static public final String MARGINTOP  	= "MarginTop";
	static public final String MARGINBOTTOM	= "MarginBottom";
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {

		java.sql.Connection connection = null;
		try {
			DbDefaultFormsAvailable myforms=(DbDefaultFormsAvailable)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"INSERT INTO defaultformsavailable ("+
						"FormId, "+
						"Locale, "+
						"Description, "+
						"ReportName, "+
						"SelectionFormula, " +
						"Category, " +
						"exportType, " +
						"MarginLeft, " +
						"MarginRight, " +
						"MarginTop, " +
						"MarginBottom " +
						") VALUES (0,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
			);		
			pstmt.setInt(1, myforms.getLocaleNumber());
			pstmt.setString(2,myforms.getDescription());
			pstmt.setString(3,myforms.getReportName());
			pstmt.setString(4,myforms.getSelectionFormula());
			pstmt.setInt   (5,myforms.getCategory());
			pstmt.setString(6,myforms.getExportType());
			pstmt.setInt	(7,myforms.getMarginLeft());
			pstmt.setInt	(8,myforms.getMarginRight());
			pstmt.setInt	(9,myforms.getMarginTop());
			pstmt.setInt	(10,myforms.getMarginBottom());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbDefaultFormsAvailablePeer.Insert:"+e.getMessage(),e);
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
			pstmt = connection.prepareStatement("DELETE FROM defaultformsavailable WHERE FormId=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbDefaultFormsAvailablePeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return 
		"SELECT * from defaultformsavailable WHERE FormId = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbDefaultFormsAvailable myforms=(DbDefaultFormsAvailable)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE defaultformsavailable SET "+
						"Locale=?, "+
						"Description=?, "+
						"ReportName=?, "+
						"SelectionFormula=?, "+
						"Category=?," +
						"exportType=?," +
						"MarginLeft=?," +
						"MarginRight=?," +
						"MarginTop=?," +
						"MarginBottom=? " +
						"WHERE FormId = ?"
			);		
			pstmt.setInt(1, myforms.getLocaleNumber());
			pstmt.setString(2,myforms.getDescription());
			pstmt.setString(3,myforms.getReportName());
			pstmt.setString(4,myforms.getSelectionFormula());
			pstmt.setInt   (5,myforms.getCategory());
			pstmt.setString(6,myforms.getExportType());
			pstmt.setInt(7,myforms.getMarginLeft());
			pstmt.setInt(8,myforms.getMarginRight());
			pstmt.setInt(9,myforms.getMarginTop());
			pstmt.setInt(10,myforms.getMarginBottom());
			pstmt.setInt(11,myforms.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbDefaultFormsAvailablePeer.Update:"+e.getMessage(),e);
		}
}
}
