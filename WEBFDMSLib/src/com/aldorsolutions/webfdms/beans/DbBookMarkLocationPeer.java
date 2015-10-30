package com.aldorsolutions.webfdms.beans;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.aldorsolutions.webfdms.database.DatabasePeer;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;


public class DbBookMarkLocationPeer extends DatabasePeer {
	
	
	static public final String ID 				= "bookmark_location_id";
	static public final String BOOKMARKID    	= "bookmark_id";
	static public final String LOCATIONID 		= "location_id";
	static public final String LOCALEID 		= "locale_id";

	protected PreparedStatement getInsertSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		Connection connection = null;
		PreparedStatement pstmt=null;
		try {
			DbBookMarkLocation myrow=(DbBookMarkLocation)p;
			connection = t.getConnection();
			
			pstmt = connection.prepareStatement(
			"INSERT INTO bookmarkxref ("+
						BOOKMARKID +","+
						LOCATIONID +","+
						LOCALEID +
						") VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
					
				pstmt.setInt(1, myrow.getBookMarkId());		
				pstmt.setInt(2, myrow.getLocationId());		
				pstmt.setInt(3, myrow.getLocaleId());		
				return pstmt;
		}
		catch (SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbBookMarkLocationPeer.Insert",e);
		}

	}

	protected PreparedStatement getRemoveSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		PreparedStatement pstmt=null;
		Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection.prepareStatement("DELETE FROM bookmarkxref WHERE bookmark_location_id =?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbBookMarkLocationPeer.Remove",e);
		}
	}

	protected String getRestoreSql(Persistent p) {
		return "SELECT 	* from bookmarkxref WHERE bookmark_location_id = "
		+ p.getId();
	}
	

	protected PreparedStatement getUpdateSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		Connection connection = null;
		try {
			DbBookMarkLocation myrow=(DbBookMarkLocation)p;
			connection = t.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE survivors SET "+
						BOOKMARKID +"=?,"+
						LOCATIONID +"=?,"+
						LOCALEID +"=?,"+
						"WHERE "+ID+" = ?"   );		
			pstmt.setInt(1, myrow.getBookMarkId());
			pstmt.setInt(2, myrow.getLocationId());	
			pstmt.setInt(3, myrow.getLocaleId());	
			pstmt.setInt(4, myrow.getId());	
			return pstmt;
			}
			catch (SQLException e){
				throw new com.aldorsolutions.webfdms.database.PersistenceException("DbBookMarkLocationPeer.Update:"+e.getMessage(),e);
			}
	}

}