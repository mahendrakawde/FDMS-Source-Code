package com.aldorsolutions.webfdms.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Survivor Peer Class contains SQL for Persistent class
 * Creation date: (11/26/2001 4:13:09 PM)
 * @author: 
 */
public class DbSurvivorPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY		= "AutoIndex";
	static public final String CASE_ID    	= "VitalsMasterKey";
	static public final String SALUTATION 	= "Salutation";
	static public final String FIRSTNAME 	= "FirstName";
	static public final String MIDDLENAME 	= "MiddleName";
	static public final String LASTNAME		= "LastName";
	static public final String SUFFIX	 	= "Suffix";
	static public final String MAIDENNAME 	= "MaidenName";
	static public final String DISPLAYNAME 	= "DisplayName";
	static public final String RELATION		= "Relation";
	static public final String ADDRESS		= "Address";
	static public final String ADDRESS2		= "Address2";
	static public final String CITY 		= "City";
	static public final String STATE		= "State";
	static public final String ZIP			= "ZipCode";
	static public final String PHONE		= "Phone";
	static public final String PHONE2		= "Phone2";
	static public final String EMAIL		= "Email";
	static public final String SEQNUMBER	= "SequenceNumber";
	static public final String VOIDEDCODE	= "VoidedCode";
	static public final String FILEVERSION	= "FileVersion";
	static public final String LIVING		= "Living";
	static public final String PNLEAD		= "PNLead";
	// the following used as a hash table key for selecting only pall bearers
	static public final String PALLBEARERS  = "Pall Bearers";
	public static final String GROUPTYPE = "GroupType";
	public static final String PREFERCONMUNICATE = "PreferComunicate";
/**
 * getInsertSql method comment.
 */
protected PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {

	Connection connection = null;
	PreparedStatement pstmt=null;
	try {
		DbSurvivor myrow=(DbSurvivor)p;
		connection = t.getConnection();
		// Check for special case where sequence number is -9999
		// and replace with next highest sequence number between 1 and 999.
		// get a list of survivors for this case.
		//short highest=2;
		short highest=0;
		if (myrow.getISeqKey()== -9999){
			//DbSurvivor[] survs = FdmsDb.getInstance().getSurvivorsForID(t,myrow.getLSurvivorMainKey());
			//for (int i=0; i< survs.length; i++){
			//	if (survs[i].getISeqKey()> highest && survs[i].getISeqKey()< 1000)
			//		highest=survs[i].getISeqKey();
			//}
			PreparedStatement stmtmax = connection.prepareStatement("SELECT max(SequenceNumber) FROM survivors where VitalsMasterKey=? and SequenceNumber<1000");
			stmtmax.setInt(1,myrow.getLSurvivorMainKey());
			ResultSet rs = stmtmax.executeQuery();
			if (rs.next()){
				if (rs.getShort(1) > highest)
					highest=rs.getShort(1);
			}
			myrow.setISeqKey((short)(highest+1));
			stmtmax.close();
		}
		pstmt = connection.prepareStatement(
		"INSERT INTO survivors ("+
					IDENTITY +","+
					CASE_ID +","+
					SALUTATION +","+
					FIRSTNAME +","+
					MIDDLENAME +","+
					LASTNAME +","+
					SUFFIX +","+
					MAIDENNAME +","+
					DISPLAYNAME +","+
					RELATION +","+
					ADDRESS +","+
					ADDRESS2 +","+
					CITY +","+
					STATE +","+
					ZIP +","+
					PHONE +","+
					PHONE2 +","+
					EMAIL +","+
					VOIDEDCODE +","+
					SEQNUMBER +","+
					LIVING +","+
					PNLEAD +","+
					FILEVERSION +", "+
					GROUPTYPE +", "+
					PREFERCONMUNICATE+" "+
					") VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt   (1, myrow.getLSurvivorMainKey());	
			pstmt.setString(2, myrow.getCSurvivorSalutation());		
			pstmt.setString(3, myrow.getCSurvivorFName());		
			pstmt.setString(4, myrow.getCSurvivorMName());	
			pstmt.setString(5, myrow.getCSurvivorLName());	
			pstmt.setString(6, myrow.getCSurvivorSuffix());		
			pstmt.setString(7, myrow.getCSurvivorMaidenName());		
			pstmt.setString(8, myrow.getCSurvivorDisplayName());		
			pstmt.setString(9, myrow.getCSurvivorRelated());	
			pstmt.setString(10, myrow.getCSurvivorAddr());		
			pstmt.setString(11, myrow.getCSurvivorAddr2());	
			pstmt.setString(12, myrow.getCSurvivorCity());	
			pstmt.setString(13, myrow.getCSurvivorState());	
			pstmt.setString(14, myrow.getCSurvivorZip());	
			pstmt.setString(15, myrow.getCSurvivorPhone());
			pstmt.setString(16, myrow.getCSurvivorPhone2());
			pstmt.setString(17, myrow.getCSurvivorEmail());
			pstmt.setString(18, myrow.getCSurvivorVoided());
			pstmt.setShort(19, myrow.getISeqKey());	
			pstmt.setString(20, myrow.getCSurvivorLiving());
			pstmt.setString(21, myrow.getCSurvivorPNLead());
			pstmt.setInt  (22, myrow.getIFileVersion());	
			pstmt.setString(23, myrow.getCGroupType());
			pstmt.setString(24, myrow.getCPreferConmunicate());
		return pstmt;
	}
	catch (SQLException e){
		throw new com.aldorsolutions.webfdms.database.PersistenceException("DbSurvivorPeer.Insert",e);
	}
}
/**
 * getRemoveSql method comment.
 */
protected PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		PreparedStatement pstmt=null;
		Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection.prepareStatement("DELETE FROM survivors WHERE AutoIndex=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbSurvivorPeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return "SELECT 	* from survivors WHERE AutoIndex = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		Connection connection = null;
		try {
			DbSurvivor myrow=(DbSurvivor)p;
			connection = t.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE survivors SET "+
						SALUTATION +"=?,"+
						FIRSTNAME +"=?,"+
						MIDDLENAME +"=?,"+
						LASTNAME +"=?,"+
						SUFFIX +"=?,"+
						MAIDENNAME +"=?,"+
						DISPLAYNAME +"=?,"+
						RELATION +"=?,"+
						ADDRESS +"=?,"+
						ADDRESS2 +"=?,"+
						CITY +"=?,"+
						STATE +"=?,"+
						ZIP +"=?,"+
						PHONE +"=?,"+
						PHONE2 +"=?,"+
						EMAIL +"=?,"+
						VOIDEDCODE  +"=?,"+
						SEQNUMBER +"=?,"+
						LIVING +"=?,"+
						PNLEAD +"=?,"+
						FILEVERSION +"=?, "+
						GROUPTYPE+"=?, "+
						PREFERCONMUNICATE+"=? "+
						"WHERE "+IDENTITY+" = ?"
			);		
			pstmt.setString(1, myrow.getCSurvivorSalutation());
			pstmt.setString(2, myrow.getCSurvivorFName());	
			pstmt.setString(3, myrow.getCSurvivorMName());
			pstmt.setString(4, myrow.getCSurvivorLName());	
			pstmt.setString(5, myrow.getCSurvivorSuffix());
			pstmt.setString(6, myrow.getCSurvivorMaidenName());
			pstmt.setString(7, myrow.getCSurvivorDisplayName());
			pstmt.setString(8, myrow.getCSurvivorRelated());	
			pstmt.setString(9, myrow.getCSurvivorAddr());		
			pstmt.setString(10, myrow.getCSurvivorAddr2());	
			pstmt.setString(11, myrow.getCSurvivorCity());	
			pstmt.setString(12, myrow.getCSurvivorState());	
			pstmt.setString(13, myrow.getCSurvivorZip());	
			pstmt.setString(14, myrow.getCSurvivorPhone());
			pstmt.setString(15, myrow.getCSurvivorPhone2());
			pstmt.setString(16, myrow.getCSurvivorEmail());
			pstmt.setString(17, myrow.getCSurvivorVoided());
			pstmt.setShort(18, myrow.getISeqKey());	
			pstmt.setString(19, myrow.getCSurvivorLiving());		
			pstmt.setString(20, myrow.getCSurvivorPNLead());	
			pstmt.setInt   (21, myrow.getIFileVersion());
			pstmt.setString   (22, myrow.getCGroupType());
			pstmt.setString   (23, myrow.getCPreferConmunicate());
			pstmt.setInt   (24, myrow.getId());	
			return pstmt;
		}
		catch (SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbSurvivorPeer.Update:"+e.getMessage(),e);
		}
}
}
