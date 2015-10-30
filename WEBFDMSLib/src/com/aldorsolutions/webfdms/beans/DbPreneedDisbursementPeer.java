package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * Workfile: DbPreneedDisbursementPeer.java
 * Date: Nov 11, 2005 3:48:18 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class DbPreneedDisbursementPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY = "DisbursementId";
	static public final String VITALSMASTERKEY = "VitalsMasterKey";
	static public final String LABEL = "Label";
	static public final String VALUE = "Value";

	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
	
		java.sql.Connection connection = null;
		try {                    
                    
			DbPreneedDisbursement pn=(DbPreneedDisbursement)p;                                                
                        
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"INSERT INTO preneeddisbursement (" +
						VITALSMASTERKEY + "," +
						LABEL + "," +
						VALUE + ") VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS
			);
			pstmt.setInt(1, pn.getVitalsId());
			pstmt.setString(2, pn.getLabel());
			pstmt.setDouble(3, pn.getValue());
			return pstmt;
			
		} catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException(
					"DbPreneedDisbursementPeer.Insert : " + e.getMessage(), e);
		}
	}
	
	/**
	 * getRemoveSql method comment.
	 */
	protected java.sql.PreparedStatement getRemoveSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, 
			com.aldorsolutions.webfdms.database.Persistent p) 
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		
		java.sql.PreparedStatement pstmt = null;
		java.sql.Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
					"DELETE FROM preneeddisbursement WHERE (DisbursementId=?)");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException(
					"DbPreneedDisbursementPeer.Remove",e);
		}
	}
	
	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * ");
		sb.append(" FROM preneeddisbursement ");
		sb.append("WHERE (DisbursementId = " + p.getId() + ")");
		
		return sb.toString();
	}
	
	/**
	 * getUpdateSql method comment.
	 */
	protected java.sql.PreparedStatement getUpdateSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, 
			com.aldorsolutions.webfdms.database.Persistent p) 
			throws com.aldorsolutions.webfdms.database.PersistenceException {
		
		java.sql.Connection connection = null;
		
		try {
			DbPreneedDisbursement pn = (DbPreneedDisbursement) p;
			connection = t.getConnection();
			
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE preneeddisbursement ");
			sb.append("SET " + LABEL + "=?, ");
			sb.append(VALUE + "=? ");
			sb.append("WHERE (" + IDENTITY + "=?)");
			
			java.sql.PreparedStatement pstmt = connection.prepareStatement(sb.toString());
			
			pstmt.setString(1, pn.getLabel());
			pstmt.setDouble(2, pn.getValue());
			pstmt.setInt(3, pn.getId());                        
                        
			return pstmt;
			
		} catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException(
					"DbPreneedDisbursementPeer.Update:"+e.getMessage(),e);
		}
	}
}