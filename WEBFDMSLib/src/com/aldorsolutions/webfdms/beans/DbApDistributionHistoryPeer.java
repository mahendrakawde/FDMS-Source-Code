package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import com.aldorsolutions.webfdms.database.DatabasePeer;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;

/**
 * DbApAccountPeer supplies constants and SQL for Persistent Class.
 * Creation date: (4/30/2001 4:13:09 PM)
 * @author: 
 */
public class DbApDistributionHistoryPeer extends DatabasePeer {

	static public final String IDENTITY = "apDistHistID";
	static public final String APMASTERID = "apMasterID";
	static public final String APACCOUNTNUMBER = "apAccountNumber";
	static public final String AMOUNT = "amount";
	static public final String DISCOUNT_AMOUNT = "discountAmount";
	static public final String TYPE = "type";
	static public final String POSTED = "posted";
	static public final String MEMO = "memo";
	static public final String CREATEDDTS = "createdDTS";

	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			DbApDistributionHistory rec = (DbApDistributionHistory) p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
					"INSERT INTO apdistributionhistory (" + 
					APMASTERID + "," + 
					APACCOUNTNUMBER + "," +
					AMOUNT + "," + 
					DISCOUNT_AMOUNT + "," +
					TYPE + "," + 
					POSTED + "," + 
					MEMO + "," + 
					CREATEDDTS + " " + 
					") VALUES (?,?,?,?,?,?,?,NOW())", Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, rec.getApMasterID());
			pstmt.setString(2, rec.getApAccountNumber());
			pstmt.setInt(3, rec.getAmount());
			pstmt.setInt(4, rec.getDiscountAmount());
			pstmt.setString(5, rec.getType());
			pstmt.setString(6, rec.getPosted());
			pstmt.setString(7, rec.getMemo());
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new PersistenceException("DbApDistributionHistoryPeer.Insert:" + e.getMessage(), e);
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
			pstmt = connection.prepareStatement("DELETE FROM apdistributionhistory WHERE " + IDENTITY + "=?");
			pstmt.setInt(1, p.getId());
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new PersistenceException("DbApDistributionHistoryPeer.Remove", e);
		}
	}

	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(Persistent p) {
		return "SELECT * from apdistributionhistory WHERE " + IDENTITY + " = " + p.getId();
	}

	/**
	 * getUpdateSql method comment.
	 */
	protected java.sql.PreparedStatement getUpdateSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			DbApDistributionHistory rec = (DbApDistributionHistory) p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
					"UPDATE apdistributionhistory SET " + 
					APMASTERID + "=?," + 
					APACCOUNTNUMBER + "=?," +
					AMOUNT + "=?," + 
					DISCOUNT_AMOUNT + "=?," + 
					TYPE + "=?," + 
					POSTED + "=?," + 
					MEMO + "=? " +
					"WHERE " + IDENTITY + " = ?");
			pstmt.setInt(1, rec.getApMasterID());
			pstmt.setString(2, rec.getApAccountNumber());
			pstmt.setInt(3, rec.getAmount());
			pstmt.setInt(4, rec.getDiscountAmount());
			pstmt.setString(5, rec.getType());
			pstmt.setString(6, rec.getPosted());
			pstmt.setString(7, rec.getMemo());
			pstmt.setInt(8, rec.getId());
			return pstmt;
		} catch (java.sql.SQLException e) {
			throw new PersistenceException("DbApDistributionHistoryPeer.Update:" + e.getMessage(), e);
		}
	}
}
