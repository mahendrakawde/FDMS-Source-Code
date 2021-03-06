package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * This class supplies constants and SQL for DbVitalsDeceased.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author:
 */
public class DbFuneralSyncToServicedataPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

  static public final String CASE_ID      = "VitalsMasterKey";
  static public final String DAYOFWEEK      = "DayOfWeek";
  static public final String TIMEOFSERVICE  = "TimeOfService";
  
  Logger logger = Logger.getLogger(DbFuneralSyncToServicedataPeer.class.getName());

	private static String selectFields() {
		return ("VitalsMasterKey,DayOfWeek,TimeOfService");
	}
  
 
  

/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
    java.sql.Connection connection = null;
    java.sql.PreparedStatement pstmt=null;
    try {
    	logger.info("In " + this.getClass() + " getInsertSql() is been called");
      DbFuneralSyncToServicedata servicedata=(DbFuneralSyncToServicedata)p;
      connection = t.getConnection();
      String sql = "INSERT INTO servicedata ("+selectFields()+" ) VALUES (?,?,?)";
      pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      
      pstmt.setInt(1, servicedata.getCaseId());
      pstmt.setString(2, servicedata.getDayofweek());
      pstmt.setString(3, servicedata.getTimeOfService());
     
      return pstmt;
      
      
    }
    catch (java.sql.SQLException e){
      throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFuneralSyncToServicedataPeer.Insert",e);
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
      pstmt = connection.prepareStatement("DELETE FROM servicedata WHERE VitalsMasterKey=?");
      pstmt.setInt(1,p.getId());
      return pstmt;
    }
    catch (java.sql.SQLException e){
      throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFuneralSyncToServicedataPeer.Remove",e);
    }
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
    	
	String sql = "SELECT VitalsMasterKey" + selectFields() +
            "from servicedata WHERE VitalsMasterKey = " +
    p.getId();
    
    return sql;


}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
    java.sql.Connection connection = null;
    try {
      DbFuneralSyncToServicedata servicedata=(DbFuneralSyncToServicedata)p;
      connection = t.getConnection();
      java.sql.PreparedStatement pstmt = connection.prepareStatement(
      "UPDATE servicedata SET "+
            "DayOfWeek=?,"+
            "TimeOfService=?"+
            "WHERE VitalsMasterKey = ?");
      
      pstmt.setString(1, servicedata.getDayofweek());
      pstmt.setString(2, servicedata.getTimeOfService());
      pstmt.setInt(3,servicedata.getId());
          
      return pstmt;
    }
    catch (java.sql.SQLException e){
      throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFuneralSyncToServicedata.Update",e);
    }
}
}
