package com.aldorsolutions.webfdms.beans;

/**
 * DbVitalsSchedulePeer supplies constants and SQL for Persistent Class.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author: 
 */
public class DbVitalsSchedulePeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY    	= "VitalsMasterKey";
	static public final String DESCRIPTION1 = "MiscCheckBxDesc1";
	static public final String DESCRIPTION2 = "MiscCheckBxDesc2";
	static public final String DESCRIPTION3 = "MiscCheckBxDesc3";
	static public final String DESCRIPTION4 = "MiscCheckBxDesc4";
	static public final String DESCRIPTION5 = "MiscCheckBxDesc5";
	static public final String DESCRIPTION6 = "MiscCheckBxDesc6";
	static public final String DESCRIPTION7 = "MiscCheckBxDesc7";
	static public final String DESCRIPTION8 = "MiscCheckBxDesc8";
	static public final String DESCRIPTION9 = "MiscCheckBxDesc9";
	static public final String DESCRIPTION10 = "MiscCheckBxDesc10";
	static public final String DESCRIPTION11 = "MiscCheckBxDesc11";
	static public final String DESCRIPTION12 = "MiscCheckBxDesc12";
	static public final String DESCRIPTION13 = "MiscCheckBxDesc13";
	static public final String DESCRIPTION14 = "MiscCheckBxDesc14";
	static public final String DESCRIPTION15 = "MiscCheckBxDesc15";
	static public final String DESCRIPTION16 = "MiscCheckBxDesc16";
	static public final String CHECKED1 	= "MiscChecked1";
	static public final String CHECKED2 	= "MiscChecked2";
	static public final String CHECKED3 	= "MiscChecked3";
	static public final String CHECKED4 	= "MiscChecked4";
	static public final String CHECKED5 	= "MiscChecked5";
	static public final String CHECKED6 	= "MiscChecked6";
	static public final String CHECKED7 	= "MiscChecked7";
	static public final String CHECKED8 	= "MiscChecked8";
	static public final String CHECKED9 	= "MiscChecked9";
	static public final String CHECKED10 	= "MiscChecked10";
	static public final String CHECKED11 	= "MiscChecked11";
	static public final String CHECKED12 	= "MiscChecked12";
	static public final String CHECKED13 	= "MiscChecked13";
	static public final String CHECKED14 	= "MiscChecked14";
	static public final String CHECKED15 	= "MiscChecked15";
	static public final String CHECKED16 	= "MiscChecked16";
	static public final String DATE1 		= "CheckListDate1";
	static public final String DATE2 		= "CheckListDate2";
	static public final String DATE3 		= "CheckListDate3";
	static public final String DATE4 		= "CheckListDate4";
	static public final String DATE5 		= "CheckListDate5";
	static public final String DATE6 		= "CheckListDate6";
	static public final String DATE7 		= "CheckListDate7";
	static public final String DATE8 		= "CheckListDate8";
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
	throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsSchedulePeer.Insert NOT ALLOWED");
}
/**
 * getRemoveSql method comment.
 */
protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
	throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsSchedulePeer. Delete NOT ALLOWED");
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return 
			"SELECT "+ IDENTITY +","+
						DESCRIPTION1+","+
						DESCRIPTION2+","+
						DESCRIPTION3+","+
						DESCRIPTION4+","+
						DESCRIPTION5+","+
						DESCRIPTION6+","+
						DESCRIPTION7+","+
						DESCRIPTION8+","+
						DESCRIPTION9+","+
						DESCRIPTION10+","+
						DESCRIPTION11+","+
						DESCRIPTION12+","+
						DESCRIPTION13+","+
						DESCRIPTION14+","+
						DESCRIPTION15+","+
						DESCRIPTION16+","+
						CHECKED1+","+
						CHECKED2+","+
						CHECKED3+","+
						CHECKED4+","+
						CHECKED5+","+
						CHECKED6+","+
						CHECKED7+","+
						CHECKED8+","+
						CHECKED9+","+
						CHECKED10+","+
						CHECKED11+","+
						CHECKED12+","+
						CHECKED13+","+
						CHECKED14+","+
						CHECKED15+","+
						CHECKED16+","+
						DATE1+","+
						DATE2+","+
						DATE3+","+
						DATE4+","+
						DATE5+","+
						DATE6+","+
						DATE7+","+
						DATE8+" "+
						"from vitalstats WHERE VitalsMasterKey = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbVitalsSchedule sched=(DbVitalsSchedule)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE vitalstats SET "+
						DESCRIPTION1+"=?,"+
						DESCRIPTION2+"=?,"+
						DESCRIPTION3+"=?,"+
						DESCRIPTION4+"=?,"+
						DESCRIPTION5+"=?,"+
						DESCRIPTION6+"=?,"+
						DESCRIPTION7+"=?,"+
						DESCRIPTION8+"=?,"+
						DESCRIPTION9+"=?,"+
						DESCRIPTION10+"=?,"+
						DESCRIPTION11+"=?,"+
						DESCRIPTION12+"=?,"+
						DESCRIPTION13+"=?,"+
						DESCRIPTION14+"=?,"+
						DESCRIPTION15+"=?,"+
						DESCRIPTION16+"=?,"+
						CHECKED1+"=?,"+
						CHECKED2+"=?,"+
						CHECKED3+"=?,"+
						CHECKED4+"=?,"+
						CHECKED5+"=?,"+
						CHECKED6+"=?,"+
						CHECKED7+"=?,"+
						CHECKED8+"=?,"+
						CHECKED9+"=?,"+
						CHECKED10+"=?,"+
						CHECKED11+"=?,"+
						CHECKED12+"=?,"+
						CHECKED13+"=?,"+
						CHECKED14+"=?,"+
						CHECKED15+"=?,"+
						CHECKED16+"=?,"+
						DATE1+"=?,"+
						DATE2+"=?,"+
						DATE3+"=?,"+
						DATE4+"=?,"+
						DATE5+"=?,"+
						DATE6+"=?,"+
						DATE7+"=?,"+
						DATE8+"=? "+
						"WHERE VitalsMasterKey = ?"
			);
			for (int i=0; i<16; i++){
				pstmt.setString(i+1, sched.getDescription(i));
			}
			for (int i=0; i<16; i++){
				pstmt.setShort(i+17, (short)sched.getChecked(i));
			}
			for (int i=0; i<8; i++){
				pstmt.setString(i+33, sched.getDate(i));
			}
			pstmt.setInt(41,sched.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbVitalsSchedulePeer.Update:"+e.getMessage(),e);
		}
}
}
