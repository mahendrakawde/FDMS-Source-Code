package com.aldorsolutions.webfdms.beans;

/**
 * @author: Guadalupe Garcia
 */


import java.sql.Statement;

import com.aldorsolutions.webfdms.database.Persistent;

public class DbExternalVitalsIdPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String VITALSMASTERKEY = "VitalsMasterKey";
	static public final String EXTERNALAPPID = "ExternalAppId";
	static public final String EXTERNALVITALSID = "ExternalVitalsId";

	protected java.sql.PreparedStatement getInsertSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, 
			Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException{
		
		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt=null;
		try {
			DbExternalVitalsId dbExternalVitalsId = (DbExternalVitalsId) p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
			"INSERT INTO vitalsexternalapp_xref ("
			+ "VitalsMasterKey, ExternalAppId, ExternalVitalsId) "
			+ "VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			System.out.print("SQL : " + pstmt.toString());
			
			pstmt.setLong(1, dbExternalVitalsId.getVitalsId());	
			pstmt.setLong(2, dbExternalVitalsId.getExternalAppId());
			pstmt.setLong(3, dbExternalVitalsId.getExternalVitalsId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbExternalVitalsIdPeer.Insert",e);
		}
	}
	
	public java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t,Persistent p) {
		return null;
	}
	
	public String getRestoreSql(Persistent p) {
		return null;
	}

	public java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, Persistent p) 
		throws com.aldorsolutions.webfdms.database.PersistenceException{

		return null;
	}
}
