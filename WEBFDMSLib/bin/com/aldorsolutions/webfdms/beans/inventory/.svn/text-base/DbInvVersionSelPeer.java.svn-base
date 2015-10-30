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
 * File: DbInvVersionSelPeer.java	
 * 
 */
public class DbInvVersionSelPeer extends DatabasePeer {
	
	static public final String INVVERSIONSELID = "InvVersionSelID";
	static public final String INVVERSIONID	= "InvVersionID";
	static public final String LOCALEID	= "LocaleID";
	static public final String LOCATIONID = "LocationID";
	static public final String COMPANYID = "CompanyID";
	
	/**
	 * getInsertSql method comment.
	 */
	protected PreparedStatement getInsertSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
			Connection connection = null;
			PreparedStatement pstmt = null;
			try {
				DbInvVersionSel rec=(DbInvVersionSel)p;
				connection = t.getConnection();
				pstmt = connection.prepareStatement("INSERT INTO invversionsel ("+
						INVVERSIONID +","+ LOCALEID +","+ LOCATIONID +","+ 
						COMPANYID + " ) " +
						"VALUES (?,?,?,?)");
				
				pstmt.setLong(1, rec.getInvVersionID() );	
				pstmt.setLong(2, rec.getLocaleID() );	
				pstmt.setLong(3, rec.getLocationID() );	
				pstmt.setLong(4, rec.getCompanyID() );	
				
				return pstmt;
			}
			catch (SQLException e){
				throw new PersistenceException("DbInvVersionSelPeer.Insert:"+e.getMessage(),e);
			}
	}
	/**
	 * getRemoveSql method comment.
	 */
	protected PreparedStatement getRemoveSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		throw new PersistenceException("DbInvVersionSelPeer.Remove: NOT ALLOWED. User delete code instead.");
	}
	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(Persistent p) {
			return ( "SELECT * FROM invversionsel WHERE Identity = " + p.getId() );
	}
	/**
	 * getUpdateSql method comment.
	 */
	protected PreparedStatement getUpdateSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
			Connection connection = null;
			try {
				DbInvVersionSel rec=(DbInvVersionSel)p;
				connection = t.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(
				"UPDATE invversionsel SET "+ INVVERSIONID +"=?,"+
				LOCALEID +"=?,"+ LOCATIONID +"=?,"+ COMPANYID +"=? "+
					" WHERE "+INVVERSIONSELID+" = ?" );

				pstmt.setLong(1, rec.getInvVersionID() );	
				pstmt.setLong(2, rec.getLocaleID() );	
				pstmt.setLong(3, rec.getLocationID() );	
				pstmt.setLong(4, rec.getCompanyID() );	
				pstmt.setInt (5, rec.getId() );
				return pstmt;
			}
			catch (SQLException e){
				throw new PersistenceException("DbInvVersionSelPeer.Update:"+e.getMessage(),e);
			}
	}
}
