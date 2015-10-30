package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabasePeer;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.util.FormatString;

/**
 * DbApAccountPeer supplies constants and SQL for Persistent Class.
 * Creation date: (4/30/2001 4:13:09 PM)
 * @author: 
 */
public class DbInvoiceTransHistPeer extends DatabasePeer {
	static public final String INVOICETRANSHISTORYID = "invoiceTransHistoryID";
	static public final String INVOICEID = "invoiceID";
	static public final String LOCATIONID = "locationID";
	static public final String TYPE = "type";
	static public final String DESCRIPTION = "description";
	static public final String AMOUNT = "amount";
	static public final String GLACCOUNT = "glAccount";
	static public final String TRANSACTIONDATE = "transactionDate";
	static public final String POSTED = "posted";
	static public final String CREATEDDTS = "createdDTS";
	static public final String UPDATEDDTS = "updatedDTS";

	Logger logger = Logger.getLogger(DbInvoiceTransHistPeer.class.getName());
	
	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			logger.info("In " + this.getClass() + " getInsertSql() is been called");
			DbInvoiceTransHist rec = (DbInvoiceTransHist) p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement("INSERT INTO invoicetranshist (" + 
					INVOICEID + "," + 
					LOCATIONID + "," + 
					TYPE + "," + 
					DESCRIPTION + "," + 
					AMOUNT + "," + 
					GLACCOUNT + "," + 
					TRANSACTIONDATE + "," +
					POSTED + "," + 
					CREATEDDTS + "," + 
					UPDATEDDTS + " " + 
					") VALUES (?,?,?,?,?,?,?,?,NOW(),NULL)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setLong(1, rec.getInvoiceID());
			pstmt.setLong(2, rec.getLocationID());
			pstmt.setInt(3, rec.getType());
			pstmt.setString(4, rec.getDescription());
			pstmt.setDouble(5, rec.getAmount());
			pstmt.setString(6, rec.getGlAccount());
			pstmt.setDate(7, rec.getTransactionDate());
			pstmt.setString(8,FormatString.charToString(rec.getPosted()));
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new PersistenceException("DbInvoiceTransHistPeer.Insert:" + e.getMessage(), e);
		}
	}

	/**
	 * getRemoveSql method comment.
	 */
	protected java.sql.PreparedStatement getRemoveSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		java.sql.PreparedStatement pstmt = null;
		java.sql.Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection.prepareStatement("DELETE FROM invoicetranshist WHERE invoiceTransHistoryID=?");
			pstmt.setInt(1, p.getId());
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new PersistenceException("DbInvoiceTransHistPeer.Remove", e);
		}
	}

	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(Persistent p) {
		return "SELECT * FROM invoicetranshist WHERE invoiceTransHistoryID = " + p.getId();
	}

	/**
	 * getUpdateSql method comment.
	 */
	protected java.sql.PreparedStatement getUpdateSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			DbInvoiceTransHist rec = (DbInvoiceTransHist) p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement("UPDATE invoicetranshist SET " +
					INVOICEID + "=?," + 
					LOCATIONID + "=?," +
					TYPE + "=?," + 
					DESCRIPTION + "=?," + 
					AMOUNT + "=?," + 
					GLACCOUNT + "=?," +
					TRANSACTIONDATE + "=?," +
					POSTED + "=?," + 
					UPDATEDDTS + "=NOW() " +
          "WHERE "+INVOICETRANSHISTORYID+" = ?");
			pstmt.setLong(1, rec.getInvoiceID());
			pstmt.setLong(2, rec.getLocationID());
			pstmt.setInt(3, rec.getType());
			pstmt.setString(4, rec.getDescription());
			pstmt.setDouble(5, rec.getAmount());
			pstmt.setString(6, rec.getGlAccount());
			pstmt.setDate(7, rec.getTransactionDate());
			pstmt.setString(8,FormatString.charToString(rec.getPosted()));
			pstmt.setInt(9, rec.getId());
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new PersistenceException("DbInvoiceTransHistPeer.Update:" + e.getMessage(), e);
		}
	}
}
