package com.aldorsolutions.webfdms.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabasePeer;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;

/**
 * DbApAccountPeer supplies constants and SQL for Persistent Class.
 * Creation date: (4/30/2001 4:13:09 PM)
 * @author: 
 */
public class DbApAccountPeer extends DatabasePeer {

	static public final String IDENTITY    	= "AccountID";
	static public final String ACCTNO	 	= "AccountNumber";
	static public final String DESCRIPTION	= "Description";
	static public final String LOCALEID	    = "LocaleID";
	static public final String LOCATIONID	= "LocationID";
	static public final String GROUPBYIDNAME = "GroupByIdName";
	
	Logger logger = Logger.getLogger(DbApAccountPeer.class.getName());
	
/**
 * getInsertSql method comment.
 */
protected PreparedStatement getInsertSql(DatabaseTransaction t,
                                         Persistent p)
                                         throws PersistenceException {
        
		java.sql.Connection connection = null;
		try {
			logger.info("In " + this.getClass() + " getInsertSql() is been called");
			DbApAccount rec=(DbApAccount)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"INSERT INTO apaccounts ("+
				ACCTNO 		+","+
				DESCRIPTION +","+
				LOCALEID 	+","+
				LOCATIONID 	+" "+
				") VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS
			);		
			pstmt.setString(1, rec.getAccountNumber());	
			pstmt.setString(2, rec.getDescription());
			pstmt.setInt   (3, rec.getLocaleID());
			pstmt.setInt   (4, rec.getLocationID());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new PersistenceException("DbApAccountPeer.Insert:"+e.getMessage(),e);
		}
}
/**
 * getRemoveSql method comment.
 */
protected java.sql.PreparedStatement getRemoveSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		java.sql.PreparedStatement pstmt=null;
		java.sql.Connection connection = null;
		try {
			connection = t.getConnection();
//			pstmt = connection.prepareStatement("DELETE FROM  apaccounts WHERE AccountID=?");
			pstmt = connection.prepareStatement("update apaccounts set Inactive = 'Y' WHERE AccountID=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new PersistenceException("DbApAccountPeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(Persistent p) {
		return 
		"SELECT * from apaccounts WHERE AccountID = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			DbApAccount rec=(DbApAccount)p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
			"UPDATE apaccounts SET "+
				ACCTNO +"=?,"+
				DESCRIPTION +"=?,"+
				LOCALEID +"=?,"+
				LOCATIONID +"=? "+
				" WHERE "+IDENTITY+" = ?"
			);		
			pstmt.setString(1, rec.getAccountNumber());	
			pstmt.setString(2, rec.getDescription());
			pstmt.setInt   (3, rec.getLocaleID());
			pstmt.setInt   (4, rec.getLocationID());
			pstmt.setInt   (5, rec.getId());
			return pstmt;
		}
		catch (SQLException e){
			throw new PersistenceException("DbApAccountPeer.Update:"+e.getMessage(),e);
		}
}
}
