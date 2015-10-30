package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * DbLocalePeer supplies constants and SQL for Persistent Class.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author:
 */
public class DbFormsAvailablePeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

  static public final String IDENTITY     = "FormId";
  static public final String LOCALE   = "Locale";
  static public final String DESCRIPTION  = "Description";
  static public final String REPORTNAME = "ReportName";
  static public final String SELFORMULA   = "SelectionFormula";
  static public final String CATEGORY   = "Category";
  static public final String EXPORTTYPE   = "exportType";
  static public final String MARGINLEFT   = "MarginLeft";
  static public final String MARGINRIGHT  = "MarginRight";
  static public final String MARGINTOP    = "MarginTop";
  static public final String MARGINBOTTOM = "MarginBottom";
  static public final String CHAINTO    = "ChainToReport";
  static public final String USERDEFINED = "UserDefined";
  
  static public final String DATAPULL   = "Datapull";
  static public final String STOREDPROC   = "StoredProc";
  static public final String XMLFILE   = "XmlFile"; 
  
  static public final String ADDLOCALIDANDLOCATIONIDREPORTFOLDER = "AddLocalIDAndLocationIDReportFolder";
  
  Logger logger = Logger.getLogger(DbFormsAvailablePeer.class.getName());
  
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {

    java.sql.Connection connection = null;
    try {
    	logger.info("In " + this.getClass() + " getInsertSql() is been called");
      DbFormsAvailable myforms=(DbFormsAvailable)p;
      connection = t.getConnection();
      java.sql.PreparedStatement pstmt = connection.prepareStatement(
      "INSERT INTO formsavailable ("+
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
            "MarginBottom," +
            "ChainToReport, " +
            "UserDefined, " +
            "Datapull," +
            "StoredProc, " +
            "XmlFile, " +
            "AddLocalIDAndLocationIDReportFolder " +
            ") VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
      );
      pstmt.setInt(1, myforms.getLocaleNumber());
      pstmt.setString(2,myforms.getDescription());
      pstmt.setString(3,myforms.getReportName());
      pstmt.setString(4,myforms.getSelectionFormula());
      pstmt.setInt   (5,myforms.getCategory());
      pstmt.setString(6,myforms.getExportType());
      pstmt.setInt  (7,myforms.getMarginLeft());
      pstmt.setInt  (8,myforms.getMarginRight());
      pstmt.setInt  (9,myforms.getMarginTop());
      pstmt.setInt  (10,myforms.getMarginBottom());
      pstmt.setInt  (11,myforms.getChainToReport());
      pstmt.setBoolean  (12,myforms.getUserDefined());
      pstmt.setString(13,myforms.getDatapull());
      pstmt.setString(14,myforms.getStoredProc());
      pstmt.setString(15,myforms.getXmlFile());
      pstmt.setString(16,myforms.getAddLocalIDAndLocationIDReportFolder());
      return pstmt;
    }
    catch (java.sql.SQLException e){
      throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFormsAvailablePeer.Insert:"+e.getMessage(),e);
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
      pstmt = connection.prepareStatement("DELETE FROM formsavailable WHERE FormId=?");
      pstmt.setInt(1,p.getId());
      return pstmt;
    }
    catch (java.sql.SQLException e){
      throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFormsAvailablePeer.Remove",e);
    }
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
    return
    "SELECT * from formsavailable WHERE FormId = "
    + p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
    java.sql.Connection connection = null;
    try {
      DbFormsAvailable myforms=(DbFormsAvailable)p;
      connection = t.getConnection();
      java.sql.PreparedStatement pstmt = connection.prepareStatement(
      "UPDATE formsavailable SET "+
            "Locale=?, "+
            "Description=?, "+
            "ReportName=?, "+
            "SelectionFormula=?, "+
            "Category=?," +
            "exportType=?," +
            "MarginLeft=?," +
            "MarginRight=?," +
            "MarginTop=?," +
            "MarginBottom=?," +
            "ChainToReport=?, "+
            "UserDefined=?, "+
            "Datapull=?," +
            "StoredProc=?, " +
            "XmlFile=?, " +  
            "AddLocalIDAndLocationIDReportFolder=? " +
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
      pstmt.setInt(11,myforms.getChainToReport());
      pstmt.setBoolean  (12,myforms.getUserDefined());
      pstmt.setString(13,myforms.getDatapull());
      pstmt.setString(14,myforms.getStoredProc());
      pstmt.setString(15,myforms.getXmlFile());    
      pstmt.setString(16,myforms.getAddLocalIDAndLocationIDReportFolder());
      pstmt.setInt(17,myforms.getId());
      return pstmt;
    }
    catch (java.sql.SQLException e){
      throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFormsAvailablePeer.Update:"+e.getMessage(),e);
    }
}
}
