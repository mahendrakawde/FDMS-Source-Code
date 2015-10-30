package com.aldorsolutions.webfdms.beans;

/**
 * This class supplies constants and SQL for DbVitalsInformant.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author: 
 */
public class DbVitalsInformantPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String CASE_ID    	= "VitalsMasterKey";
	static public final String CITY 		= "InformantCity";
	static public final String FIRSTNAME 	= "InformantFirstName";
	static public final String MIDDLENAME 	= "InformantMiddleNam";
	static public final String LASTNAME		= "InformantLastName";
	static public final String PHONE		= "InformantPhone";
	static public final String CELLPHONE	= "InformantCellPhone";
	static public final String RELATION		= "InformantRelation";
	static public final String ROAD2		= "InformantRoad2";
	static public final String ROAD3		= "InformantRoad3";
	static public final String SALUTATION   = "Salutation";
	static public final String STATE		= "InformantState";
	static public final String STREET		= "InformantStreet";
	static public final String ZIP			= "InformantZip";
	static public final String INFORMANTEMAIL = "InformantEmail";
	static public final String INFORMANTDATESIGNED = "InformantDateSigned";
	static public final String INFORMANT_IS_BILLTOPARTY = "InformantIsBillToParty";
	static public final String INFORMANT_PREFER_COMMUNICATE = "InformantPreferCommunicate";
	
	
	// 
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {

	throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalInformantPeer.Insert NOT ALLOWED");
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
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalInformantPeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return "SELECT 	VitalsMasterKey,"+
						"InformantCity,"+
						"InformantFirstName,"+
						"InformantMiddleNam,"+
						"InformantLastName,"+
						"InformantPhone,"+
						"InformantCellPhone,"+
						"InformantRelation,"+
						"InformantRoad2,"+
						"InformantRoad3,"+
						"Salutation,"+
						"InformantState,"+
						"InformantStreet,"+
						"InformantZip,"+
						"InformantEmail,"+
						"InformantDateSigned, "+
						"InformantIsBillToParty, "+
						"InformantPreferCommunicate "+
						"from vitalstats WHERE VitalsMasterKey = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbVitalsInformant vitals=(DbVitalsInformant)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE vitalstats SET "+
						"InformantCity=?,"+
						"InformantFirstName=?,"+
						"InformantMiddleNam=?,"+
						"InformantLastName=?,"+
						"InformantPhone=?,"+
						"InformantCellPhone=?,"+
						"InformantRelation=?,"+
						"InformantRoad2=?,"+
						"InformantRoad3=?,"+
						"Salutation=?,"+
						"InformantState=?,"+
						"InformantStreet=?,"+
						"InformantZip=?, "+
						"InformantEmail=?, "+
						"InformantDateSigned=?, "+
						"InformantIsBillToParty=?, " +
						"InformantPreferCommunicate=? "+
						"WHERE VitalsMasterKey = ?"
			);		
			pstmt.setString(1, vitals.getCity());	
			pstmt.setString(2, vitals.getFname());
			pstmt.setString(3, vitals.getMname());
			pstmt.setString(4, vitals.getLname());
			pstmt.setString(5, vitals.getPhone());
			pstmt.setString(6, vitals.getCellPhone());
			pstmt.setString(7, vitals.getRelated());
			pstmt.setString(8, vitals.getRoad2());
			pstmt.setString(9, vitals.getRoad3());
			pstmt.setString(10, vitals.getSalutation());
			pstmt.setString(11,vitals.getState());
			pstmt.setString(12,vitals.getStreet());
			pstmt.setString(13,vitals.getZip());
			pstmt.setString(14,vitals.getInformantEmail());
			pstmt.setString(15,vitals.getInformantDateSigned());
			pstmt.setString(16,vitals.getInformantIsBillToParty());
			pstmt.setString(17,vitals.getInformantPreferCommunicate());
			pstmt.setInt(18,vitals.getId());
			
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsInformantPeer.Update:"+e.getMessage(),e);
		}
}
}
