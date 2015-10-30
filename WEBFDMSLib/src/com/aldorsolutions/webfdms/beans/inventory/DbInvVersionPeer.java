package com.aldorsolutions.webfdms.beans.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.aldorsolutions.webfdms.database.DatabasePeer;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;


/**
 * @author David Rollins
 * Date: Feb 23, 2007
 * File: DbInvVersionPeer.java	
 * 
 */
public class DbInvVersionPeer extends DatabasePeer {
	
	static public final String  INVVERSIONID = "InvVersionID";
	static public final String  PRICELISTID = "PriceListID";
	static public final String  COMPANYID = "CompanyID";
	static public final String  NAME = "Name";
	static public final String  DESCRIPTION = "Description";
	static public final String  INVFROMDATE = "InvFromDate";
	static public final String  INVTODATE = "InvToDate";
	static public final String  ACTIVE = "Active";
	static public final String  TIMESTAMP = "Timestamp"; 
	
	/* (non-Javadoc)
	 * @see com.aldorsolutions.webfdms.database.DatabasePeer#getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction, com.aldorsolutions.webfdms.database.Persistent)
	 */
	protected PreparedStatement getInsertSql(DatabaseTransaction t, Persistent p)
			throws PersistenceException {
		Connection connection = null;
		try {
			DbInvVersion rec = (DbInvVersion) p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement("INSERT INTO invversion (" + 
					PRICELISTID + "," + COMPANYID + "," + 
					NAME + "," + DESCRIPTION + "," + INVFROMDATE + "," + 
					INVTODATE + "," + ACTIVE + "," + TIMESTAMP + 
					" ) VALUES (?,?,?,?,?,?,?,?)");
			
			int col = 0;
			pstmt.setString(++col, rec.getPriceListID() );
			pstmt.setLong(++col, rec.getCompanyID() );
			pstmt.setString(++col, rec.getName() );
			pstmt.setString(++col, rec.getDescription() );
			pstmt.setDate(++col, rec.getInvFromDate() );
			pstmt.setDate(++col, rec.getInvToDate() );
			pstmt.setBoolean(++col, rec.isActive() );
			pstmt.setTimestamp(++col, rec.getTimestamp() );
			
			return pstmt;
		} catch (SQLException e) {
			throw new PersistenceException("DbInvVersionPeer.Insert:" + e.getMessage(), e);
		}
	}

	/**
	 * @param t
	 * @param p
	 * @return
	 * @throws PersistenceException
	 */
	protected PreparedStatement getRemoveSql(DatabaseTransaction t, Persistent p)
			throws PersistenceException {
		throw new PersistenceException("DbInvVersionPeer.Remove: NOT ALLOWED. User delete code instead.");
	}

	/**
	 * @param p
	 * @return
	 */
	protected String getRestoreSql(Persistent p) {
		return "SELECT * FROM invversion WHERE " + INVVERSIONID + " = " + p.getId();
	}

	/**
	 * @param t
	 * @param p
	 * @return
	 * @throws PersistenceException
	 */
	protected PreparedStatement getUpdateSql(DatabaseTransaction t, Persistent p)
			throws PersistenceException {
		Connection connection = null;
		try {
			DbInvVersion rec = (DbInvVersion) p;
			connection = t.getConnection();
			PreparedStatement pstmt = connection.prepareStatement("UPDATE invversion SET " + 
					PRICELISTID + "=?," + COMPANYID + "=?," + 
					NAME + "=?," + DESCRIPTION + "=?," + INVFROMDATE + "=?," + 
					INVTODATE + "=?," + ACTIVE + "=?," + TIMESTAMP + "=? " + 
					" WHERE " + INVVERSIONID + " = ?");

			int col = 0;
			
			pstmt.setString(++col, rec.getPriceListID() );
			pstmt.setLong(++col, rec.getCompanyID() );
			pstmt.setString(++col, rec.getName() );
			pstmt.setString(++col, rec.getDescription() );
			pstmt.setDate(++col, rec.getInvFromDate() );
			pstmt.setDate(++col, rec.getInvToDate() );
			pstmt.setBoolean(++col, rec.isActive() );
			pstmt.setTimestamp(++col, rec.getTimestamp() );
			pstmt.setLong(++col, rec.getId() );
			
			return pstmt;
		} catch (SQLException e) {
			throw new PersistenceException("DbInvVersionPeer.Update:" + e.getMessage(), e);
		}
	}
}
