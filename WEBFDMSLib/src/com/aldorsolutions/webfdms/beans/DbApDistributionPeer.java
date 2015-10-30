package com.aldorsolutions.webfdms.beans;

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
public class DbApDistributionPeer extends DatabasePeer {

	static public final String IDENTITY    	= "RecordID";
	static public final String MASTERID	 	= "MasterID";
	static public final String ACCTID		= "DistributionAcctID";
	static public final String AMOUNT	    = "DistributionAmount";
	static public final String TRANTYPE		= "TranType";
	static public final String PRINTEDDATE = "PrintedDate";
	static public final String POSTED		= "Posted";
	static public final String MEMO			= "Memo";
	
	Logger logger = Logger.getLogger(DbApDistributionPeer.class.getName());
	
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			logger.info("In " + this.getClass() + " getInsertSql() is been called");
			DbApDistribution rec=(DbApDistribution)p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
			"INSERT INTO apdistribution ("+
				MASTERID +","+
				ACCTID +","+
				AMOUNT +","+
				TRANTYPE +","+
				PRINTEDDATE + ","+
				POSTED +","+
				MEMO +" "+
				") VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
			);		
			pstmt.setInt	(1, rec.getMasterID());	
			pstmt.setInt	(2, rec.getAccountID());
			pstmt.setInt   	(3, rec.getAmount());
			pstmt.setString	(4, rec.getTranType()); 
			pstmt.setDate	(5, rec.getPrintedDate());
			pstmt.setString	(6, rec.getPosted());
			pstmt.setString	(7, rec.getMemo());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new PersistenceException("DbApDistributionPeer.Insert:"+e.getMessage(),e);
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
			pstmt = connection.prepareStatement("DELETE FROM  apdistribution WHERE RecordID=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new PersistenceException("DbApDistributionPeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(Persistent p) {
		return 
		"SELECT * from apdistribution WHERE RecordID = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			DbApDistribution rec=(DbApDistribution)p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
			"UPDATE apdistribution SET "+
				MASTERID +"=?,"+
				ACCTID +"=?,"+
				AMOUNT +"=?,"+
				TRANTYPE +"=?,"+
				PRINTEDDATE + "=?,"+
				POSTED +"=?,"+
				MEMO +"=? "+
				" WHERE "+IDENTITY+" = ?"
			);		
			pstmt.setInt	(1, rec.getMasterID());	
			pstmt.setInt	(2, rec.getAccountID());
			pstmt.setInt   	(3, rec.getAmount());
			pstmt.setString	(4, rec.getTranType());
			pstmt.setDate		(5, rec.getPrintedDate());
			pstmt.setString	(6, rec.getPosted());
			pstmt.setString	(7, rec.getMemo());
			pstmt.setInt   	(8, rec.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new PersistenceException("DbApDistributionPeer.Update:"+e.getMessage(),e);
		}
	}
}
