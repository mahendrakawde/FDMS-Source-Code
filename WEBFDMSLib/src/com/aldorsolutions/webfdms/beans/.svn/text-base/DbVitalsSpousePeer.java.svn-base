package com.aldorsolutions.webfdms.beans;

/**
 * DbPreneedPeer supplies constants and SQL for Persistent Class.
 * Creation date: (1/21/2002 4:13:09 PM)
 * @author: 
 */
public class DbVitalsSpousePeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY    	= "VitalsMasterKey";
	static public final String SPOUSEMAIDEN	= "SurvSpouseMaiden";
	static public final String SPOUSEFIRST 	= "SpouseFirstName";
	static public final String SPOUSEMIDDLE	= "SpouseMiddleName";
	static public final String SPOUSELAST	= "SpouseLastName";
	static public final String SPOUSEAGE 	= "SpouseAge";
	static public final String SURVIVINGSPOUSESTREET = "SurvivingSpouseStreet";
	static public final String SURVIVINGSPOUSECITY    = "SurvivingSpouseCity";
	static public final String SURVIVINGSPOUSESTATE   = "SurvivingSpouseState";  
	static public final String SURVIVINGSPOUSEZIP     = "SurvivingSpouseZip"; 
	static public final int    SPOUSEMAIDEN_LENGTH	= 49;
	static public final int	   SPOUSEFIRST_LENGTH 	= 14;
	static public final int    SPOUSEMIDDLE_LENGTH	= 14;
	static public final int    SPOUSELAST_LENGTH	= 19;
	static public final int    SPOUSEAGE_LENGTH 	= 5;
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
	throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsSpousePeer.Insert Not Implimented.");
}
/**
 * getRemoveSql method comment.
 */
protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
	throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsSpousePeer.Remove Not Implimented");
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return 
			"SELECT "+ IDENTITY +","+
						SPOUSEMAIDEN+","+
						SPOUSEFIRST+","+
						SPOUSEMIDDLE+","+
						SPOUSELAST+","+
						SPOUSEAGE+","+
						SURVIVINGSPOUSESTREET+","+
						SURVIVINGSPOUSECITY+","+
						SURVIVINGSPOUSESTATE+","+
						SURVIVINGSPOUSEZIP+" "+
						"from vitalstats WHERE VitalsMasterKey = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbVitalsSpouse pn=(DbVitalsSpouse)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE vitalstats SET "+
						SPOUSEMAIDEN+"=?,"+
						SPOUSEFIRST+"=?,"+
						SPOUSEMIDDLE+"=?,"+
						SPOUSELAST+"=?,"+
						SPOUSEAGE+"=?,"+
						SURVIVINGSPOUSESTREET+"=?,"+
						SURVIVINGSPOUSECITY+"=?,"+
						SURVIVINGSPOUSESTATE+"=?,"+
						SURVIVINGSPOUSEZIP+"=? "+
						
						"WHERE VitalsMasterKey = ?"
			);
			pstmt.setString(1, pn.getMaiden());
			pstmt.setString(2, pn.getFname());
			pstmt.setString(3, pn.getMname());
			pstmt.setString(4, pn.getLname());
			pstmt.setString(5, pn.getAge());
			
			
			
			pstmt.setString(6, pn.getSurvivingSpouseStreet());
			pstmt.setString(7, pn.getSurvivingSpouseCity());
			pstmt.setString(8, pn.getSurvivingSpouseState());
			pstmt.setString(9, pn.getSurvivingSpouseZip());
			pstmt.setInt(10,pn.getId());
			
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVialsSpousePeer.Update:"+e.getMessage(),e);
		}
}
}
