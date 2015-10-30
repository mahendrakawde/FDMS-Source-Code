package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * DbCustomPeer supplies constants and SQL for Persistent Class.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author: 
 */
public class DbCustomPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {
	static public final int	   CUSTOM_SHORT_LENGTH=49;
	static public final int	   CUSTOM_LONG_LENGTH=79;
	static public final String IDENTITY    	= "VitalsMasterKey";
	static public final String CASE_ID	 	= "VitalsMasterKey";
	static public final String CUSTOM1 		= "Custom1";
	static public final String CUSTOM2 		= "Custom2";
	static public final String CUSTOM3 		= "Custom3";
	static public final String CUSTOM4 		= "Custom4";
	static public final String CUSTOM5 		= "Custom5";
	static public final String CUSTOM6 		= "Custom6";
	static public final String CUSTOM7 		= "Custom7";
	static public final String CUSTOM8 		= "Custom8";
	static public final String CUSTOM9 		= "Custom9";
	static public final String CUSTOM10		= "Custom10";
	static public final String CUSTOM11		= "Custom11";
	static public final String CUSTOM12		= "Custom12";
	static public final String CUSTOM13		= "Custom13";
	static public final String CUSTOM14		= "Custom14";
	static public final String CUSTOM15		= "Custom15";
	static public final String CUSTOM16		= "Custom16";
	static public final String CUSTOM17		= "Custom17";
	static public final String CUSTOM18		= "Custom18";
	static public final String CUSTOM19		= "Custom19";
	static public final String CUSTOM20		= "Custom20";
	static public final String CUSTOM21		= "Custom21";
	static public final String CUSTOM22		= "Custom22";
	static public final String CUSTOM23		= "Custom23";
	static public final String CUSTOM24		= "Custom24";
	static public final String CUSTOM25		= "Custom25";
	static public final String CUSTOM26		= "Custom26";
	static public final String CUSTOM27		= "Custom27";
	static public final String CUSTOM28		= "Custom28";
	static public final String CUSTOM29		= "Custom29";
	static public final String CUSTOM30		= "Custom30";
	static public final String CUSTOM31		= "Custom31";
	static public final String CUSTOM32		= "Custom32";
	static public final String CUSTOM33		= "Custom33";
	static public final String CUSTOM34		= "Custom34";
	static public final String CUSTOM35		= "Custom35";
	static public final String CUSTOM36		= "Custom36";
	static public final String CUSTOM37		= "Custom37";
	static public final String CUSTOM38		= "Custom38";
	static public final String CUSTOM39		= "Custom39";
	static public final String CUSTOM40		= "Custom40";
	static public final String LONG1 		= "Long1";
	static public final String LONG2 		= "Long2";
	static public final String LONG3 		= "Long3";
	static public final String LONG4 		= "Long4";
	static public final String LONG5 		= "Long5";
	static public final String LONG6 		= "Long6";
	static public final String LONG7 		= "Long7";
	static public final String LONG8 		= "Long8";
	static public final String LONG9 		= "Long9";
	static public final String LONG10		= "Long10";
	static public final String LONG11		= "Long11";
	static public final String LONG12		= "Long12";
	static public final String LONG13		= "Long13";
	static public final String LONG14		= "Long14";
	static public final String LONG15		= "Long15";
	static public final String LONG16		= "Long16";
	static public final String LONG17		= "Long17";
	static public final String LONG18		= "Long18";
	static public final String LONG19		= "Long19";
	static public final String LONG20		= "Long20";
	static public final String LONG21		= "Long21";
	static public final String LONG22		= "Long22";
	static public final String LONG23		= "Long23";
	static public final String LONG24		= "Long24";
	static public final String LONG25		= "Long25";
	static public final String LONG26		= "Long26";
	static public final String LONG27		= "Long27";
	static public final String LONG28		= "Long28";
	static public final String LONG29		= "Long29";
	static public final String LONG30		= "Long30";
	static public final String LONG31		= "Long31";
	static public final String LONG32		= "Long32";
	static public final String LONG33		= "Long33";
	static public final String LONG34		= "Long34";
	static public final String LONG35		= "Long35";
	static public final String LONG36		= "Long36";
	static public final String LONG37		= "Long37";
	static public final String LONG38		= "Long38";
	static public final String LONG39		= "Long39";
	static public final String LONG40		= "Long40";
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbCustom rec=(DbCustom)p;
			connection = t.getConnection();
			StringBuffer mystmtbuff = new StringBuffer("INSERT INTO fdmscust ("+
						CASE_ID +","+
						CUSTOM1 +","+
						CUSTOM2 +","+
						CUSTOM3 +","+
						CUSTOM4 +","+
						CUSTOM5 +","+
						CUSTOM6 +","+
						CUSTOM7 +","+
						CUSTOM8 +","+
						CUSTOM9 +","+
						CUSTOM10 +","+
						CUSTOM11 +","+
						CUSTOM12 +","+
						CUSTOM13 +","+
						CUSTOM14 +","+
						CUSTOM15 +","+
						CUSTOM16 +","+
						CUSTOM17 +","+
						CUSTOM18 +","+
						CUSTOM19 +","+
						CUSTOM20 +","+
						CUSTOM21 +","+
						CUSTOM22 +","+
						CUSTOM23 +","+
						CUSTOM24 +","+
						CUSTOM25 +","+
						CUSTOM26 +","+
						CUSTOM27 +","+
						CUSTOM28 +","+
						CUSTOM29 +","+
						CUSTOM30 +","+
						CUSTOM31 +","+
						CUSTOM32 +","+
						CUSTOM33 +","+
						CUSTOM34 +","+
						CUSTOM35 +","+
						CUSTOM36 +","+
						CUSTOM37 +","+
						CUSTOM38 +","+
						CUSTOM39 +","+
						CUSTOM40 +","+
						LONG1 +","+
						LONG2 +","+
						LONG3 +","+
						LONG4 +","+
						LONG5 +","+
						LONG6 +","+
						LONG7 +","+
						LONG8 +","+
						LONG9 +","+
						LONG10 +","+
						LONG11 +","+
						LONG12 +","+
						LONG13 +","+
						LONG14 +","+
						LONG15 +","+
						LONG16 +","+
						LONG17 +","+
						LONG18 +","+
						LONG19 +","+
						LONG20 +","+
						LONG21 +","+
						LONG22 +","+
						LONG23 +","+
						LONG24 +","+
						LONG25 +","+
						LONG26 +","+
						LONG27 +","+
						LONG28 +","+
						LONG29 +","+
						LONG30 +","+
						LONG31 +","+
						LONG32 +","+
						LONG33 +","+
						LONG34 +","+
						LONG35 +","+
						LONG36 +","+
						LONG37 +","+
						LONG38 +","+
						LONG39 +","+
						LONG40 +""+
					") VALUES (?");
			for (int i=0; i<80; i++) {
				mystmtbuff.append(",?");
			}
			mystmtbuff.append(" )");
			java.sql.PreparedStatement pstmt = connection.prepareStatement(mystmtbuff.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,rec.getMainKey());
			for (int i=0; i<40; i++){	
				pstmt.setString(i+2, rec.getCustom(i));	
			}
			for (int i=0; i<40; i++){
				pstmt.setString(i+42, rec.getCustomLong(i));	
			}
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbCustomPeer.Insert:"+e.getMessage(),e);
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
			pstmt = connection.prepareStatement("DELETE FROM fdmscust WHERE VitalsMasterKey=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbCustomPeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return 
		"SELECT * from fdmscust WHERE VitalsMasterKey = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbCustom rec=(DbCustom)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE fdmscust SET "+
						CUSTOM1 +"=?,"+
						CUSTOM2 +"=?,"+
						CUSTOM3 +"=?,"+
						CUSTOM4 +"=?,"+
						CUSTOM5 +"=?,"+
						CUSTOM6 +"=?,"+
						CUSTOM7 +"=?,"+
						CUSTOM8 +"=?,"+
						CUSTOM9 +"=?,"+
						CUSTOM10 +"=?,"+
						CUSTOM11 +"=?,"+
						CUSTOM12 +"=?,"+
						CUSTOM13 +"=?,"+
						CUSTOM14 +"=?,"+
						CUSTOM15 +"=?,"+
						CUSTOM16 +"=?,"+
						CUSTOM17 +"=?,"+
						CUSTOM18 +"=?,"+
						CUSTOM19 +"=?,"+
						CUSTOM20 +"=?,"+
						CUSTOM21 +"=?,"+
						CUSTOM22 +"=?,"+
						CUSTOM23 +"=?,"+
						CUSTOM24 +"=?,"+
						CUSTOM25 +"=?,"+
						CUSTOM26 +"=?,"+
						CUSTOM27 +"=?,"+
						CUSTOM28 +"=?,"+
						CUSTOM29 +"=?,"+
						CUSTOM30 +"=?,"+
						CUSTOM31 +"=?,"+
						CUSTOM32 +"=?,"+
						CUSTOM33 +"=?,"+
						CUSTOM34 +"=?,"+
						CUSTOM35 +"=?,"+
						CUSTOM36 +"=?,"+
						CUSTOM37 +"=?,"+
						CUSTOM38 +"=?,"+
						CUSTOM39 +"=?,"+
						CUSTOM40 +"=?,"+
						LONG1 +"=?,"+
						LONG2 +"=?,"+
						LONG3 +"=?,"+
						LONG4 +"=?,"+
						LONG5 +"=?,"+
						LONG6 +"=?,"+
						LONG7 +"=?,"+
						LONG8 +"=?,"+
						LONG9 +"=?,"+
						LONG10 +"=?,"+
						LONG11 +"=?,"+
						LONG12 +"=?,"+
						LONG13 +"=?,"+
						LONG14 +"=?,"+
						LONG15 +"=?,"+
						LONG16 +"=?,"+
						LONG17 +"=?,"+
						LONG18 +"=?,"+
						LONG19 +"=?,"+
						LONG20 +"=?,"+
						LONG21 +"=?,"+
						LONG22 +"=?,"+
						LONG23 +"=?,"+
						LONG24 +"=?,"+
						LONG25 +"=?,"+
						LONG26 +"=?,"+
						LONG27 +"=?,"+
						LONG28 +"=?,"+
						LONG29 +"=?,"+
						LONG30 +"=?,"+
						LONG31 +"=?,"+
						LONG32 +"=?,"+
						LONG33 +"=?,"+
						LONG34 +"=?,"+
						LONG35 +"=?,"+
						LONG36 +"=?,"+
						LONG37 +"=?,"+
						LONG38 +"=?,"+
						LONG39 +"=?,"+
						LONG40 +"=? "+
						"WHERE VitalsMasterKey = ?"
			);
			for (int i=0; i<40; i++){	
				pstmt.setString(i+1, rec.getCustom(i));	
			}
			for (int i=0; i<40; i++){
				pstmt.setString(i+41, rec.getCustomLong(i));	
			}
			pstmt.setInt(81,rec.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbCustomPeer.Update:"+e.getMessage(),e);
		}
}
}
