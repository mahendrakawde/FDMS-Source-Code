package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * This class supplies constants and SQL for DbVitalsDeceased.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author:
 */
public class DbFuneralSyncToVitalsPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

  static public final String CASE_ID      = "VitalsMasterKey";
  static public final String MRMRS      = "DecMrMrs";
  static public final String FIRSTNAME  = "DeceasedFirstName";
  static public final String MIDNAME    = "DeceasedMidName";
  static public final String LASTNAME   = "DeceasedLastName";
  static public final String FULLNAME   = "DeceasedFullName";
  static public final String MAIDENNAME = "DeceasedMaidenName";

  static public final String DATEOFBIRTH  = "DateOfBirth";
  static public final String BIRTHPLACE = "DecBirthPlace";
  static public final String BIRTHPLACECSV = "DecBirthPlaceCSV";
  static public final String DATEOFDEATH  = "DateOfDeath";
  static public final String DEATHDATEKEY  = "DeathDateKey";
  
  static public final String DEATHPLACE  = "LocationOfDeath";
  
  static public final String DATEOFBURIAL = "BurialDate";
  static public final String SERVICEDATEKEY = "ServiceDateKey";
  
  static public final String CHAPELLOCATION= "ChapelLocation";
  static public final String LOCALENUMBER= "LocaleNumber";
  static public final String CHAPELNUMBER= "ChapelNumber";
  static public final String CASECODE= "CaseCode";

  Logger logger = Logger.getLogger(DbFuneralSyncToVitalsPeer.class.getName());
  
	private static String selectFields() {
		return ("DecMrMrs,DeceasedFirstName,DeceasedMidName,DeceasedLastName,DeceasedFullName,DeceasedMaidenName," +
				"DateOfBirth,DecBirthPlace,DecBirthPlaceCSV,DateOfDeath,DeathDateKey,LocationOfDeath,BurialDate,ServiceDateKey," +
				"ChapelLocation,LocaleNumber,ChapelNumber,CaseCode");
	}
  
 
  

/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
    java.sql.Connection connection = null;
    java.sql.PreparedStatement pstmt=null;
    try {
    	logger.info("In " + this.getClass() + " getInsertSql() is been called");
      DbFuneralSyncToVitals vitals=(DbFuneralSyncToVitals)p;
      connection = t.getConnection();
      String sql = "INSERT INTO vitalstats ("+selectFields()+" ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      
      pstmt.setString(1, vitals.getDecMrMrs());
      pstmt.setString(2, vitals.getDeceasedFirstName());
      pstmt.setString(3,vitals.getDeceasedMidName());
      pstmt.setString(4, vitals.getDeceasedLastName());
      pstmt.setString(5, vitals.getDeceasedFullName());
      pstmt.setString(6, vitals.getDeceasedMaidenName());
      pstmt.setString(7, vitals.getDateOfBirth());
      pstmt.setString(8, vitals.getDecBirthPlace());
      pstmt.setString(9,vitals.getDecBirthPlaceCSV());
      pstmt.setString(10,vitals.getDateOfDeath());
      pstmt.setString(11,vitals.getDeathDateKey());
      pstmt.setString(12,vitals.getLocationOfDeath());
      pstmt.setString(13,vitals.getBurialDate());
      pstmt.setString(14,vitals.getServiceDateKey());
      pstmt.setString(15,vitals.getChapelLocation());
      pstmt.setInt(16,vitals.getLocaleNumber());
      pstmt.setInt(17,vitals.getChapelNumber());
      pstmt.setString(18,vitals.getCaseCode());
      return pstmt;
      
      
    }
    catch (java.sql.SQLException e){
      throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFuneralSyncToVitalsPeer.Insert",e);
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
      pstmt = connection.prepareStatement("DELETE FROM vitalstats WHERE VitalsMasterKey=?");
      pstmt.setInt(1,p.getId());
      return pstmt;
    }
    catch (java.sql.SQLException e){
      throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFuneralSyncToVitalsPeer.Remove",e);
    }
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
    	
	String sql = "SELECT VitalsMasterKey" + selectFields() +
            "from vitalstats WHERE VitalsMasterKey = " +
    p.getId();
    
    return sql;


}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
    java.sql.Connection connection = null;
    try {
      DbFuneralSyncToVitals vitals=(DbFuneralSyncToVitals)p;
      connection = t.getConnection();
      java.sql.PreparedStatement pstmt = connection.prepareStatement(
      "UPDATE vitalstats SET "+
            "DecMrMrs=?,"+
            "DeceasedFirstName=?,"+
            "DeceasedMidName=?,"+
            "DeceasedLastName=?,"+
            "DeceasedFullName=?,"+
            "DeceasedMaidenName=?,"+
            "DateOfBirth=?,"+
            "DecBirthPlace=?,"+
            "DecBirthPlaceCSV=?,"+
            "DateOfDeath=?,"+
            "DeathDateKey=?,"+
            "LocationOfDeath=?,"+
            "BurialDate=?,"+
            "ServiceDateKey=?,"+
            "ChapelLocation=?,"+
            "LocaleNumber=?,"+
            "ChapelNumber=?,"+
            "CaseCode=?"+
            "WHERE VitalsMasterKey = ?");
      
      pstmt.setString(1, vitals.getDecMrMrs());
      pstmt.setString(2, vitals.getDeceasedFirstName());
      pstmt.setString(3,vitals.getDeceasedMidName());
      pstmt.setString(4, vitals.getDeceasedLastName());
      pstmt.setString(5, vitals.getDeceasedFullName());
      pstmt.setString(6, vitals.getDeceasedMaidenName());
      pstmt.setString(7, vitals.getDateOfBirth());
      pstmt.setString(8, vitals.getDecBirthPlace());
      pstmt.setString(9,vitals.getDecBirthPlaceCSV());
      pstmt.setString(10,vitals.getDateOfDeath());
      pstmt.setString(11,vitals.getDeathDateKey());
      pstmt.setString(12,vitals.getLocationOfDeath());
      pstmt.setString(13,vitals.getBurialDate());
      pstmt.setString(14,vitals.getServiceDateKey());
      pstmt.setString(15,vitals.getChapelLocation());
      pstmt.setInt(16,vitals.getLocaleNumber());
      pstmt.setInt(17,vitals.getChapelNumber());
      pstmt.setString(18,vitals.getCaseCode());
      pstmt.setInt(19,vitals.getId());
          
      return pstmt;
    }
    catch (java.sql.SQLException e){
      throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFuneralSyncToVitals.Update",e);
    }
}
}
