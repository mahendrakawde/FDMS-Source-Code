package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * SQL specifics for Donations class
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author: 
 */
public class DbDonationsPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final short  FILE_VERSION=10;
	static public final String IDENTITY    	= "SequenceNumber";
	static public final String VITALSID 	= "VitalsID";
	static public final String HONORIFIC 	= "Honorific";
	static public final String FIRSTNAME 	= "FirstName";
	static public final String LASTNAME		= "LastName";
	static public final String ADDR1		= "Address1";
	static public final String ADDR2		= "Address2";
	static public final String ADDR3 		= "Street3";
	static public final String ADDR4		= "Street4";
	static public final String CITY			= "City";
	static public final String STATE		= "State";
	static public final String POSTALCODE	= "PostalCode";
	static public final String CHARITY		= "CharityName";
	static public final String DONATIONAMT	= "DonationAmount";
	static public final String PAYMENTTYPE	= "PaymentType";
	static public final String FILEVERSION  = "FileVersion";
	static public final String HISTTRANNO	= "HistTranNo";
	static public final String DATEMODIFIED = "DateModified";
	static public final String TIMEMODIFIED = "TimeModified";
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbDonations rec=(DbDonations)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"INSERT INTO donations ("+
					IDENTITY +","+
					HONORIFIC +","+
					FIRSTNAME +","+
					LASTNAME +","+
					ADDR1 +","+
					ADDR2 +","+
					ADDR3 +","+
					ADDR4 +","+
					CITY +","+
					STATE +","+
					POSTALCODE +","+
					CHARITY +","+
					DONATIONAMT +","+
					PAYMENTTYPE +","+
					FILEVERSION +","+
					HISTTRANNO +","+
					VITALSID +" "+
					") VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, rec.getHonorific());
			pstmt.setString(2, rec.getFirstName() );
			pstmt.setString(3, rec.getLastName() );
			pstmt.setString(4, rec.getAddr1() );
			pstmt.setString(5, rec.getAddr2() );
			pstmt.setString(6, rec.getAddr3() );
			pstmt.setString(7, rec.getAddr4() );
			pstmt.setString(8, rec.getCity() );
			pstmt.setString(9, rec.getState() );
			pstmt.setString(10, rec.getPostalCode() );
			pstmt.setString(11, rec.getCharityName() );
			pstmt.setInt   (12, rec.getDonationAmount() );
			pstmt.setString(13, rec.getPaymentType() );
			pstmt.setInt   (14, FILE_VERSION);
			pstmt.setInt   (15, rec.getHistTranNo() );
			pstmt.setInt   (16, rec.getVitalsID());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbDonationsPeer.Insert:"+e.getMessage(),e);
		}
}
/**
 * getRemoveSql method comment.
 */
protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.PreparedStatement pstmt=null;
		java.sql.Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection.prepareStatement("DELETE FROM donations WHERE SequenceNumber=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbDonations.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return "SELECT * from donations WHERE SequenceNumber = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbDonations rec=(DbDonations)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE donations SET "+
					HONORIFIC +"=?,"+
					FIRSTNAME +"=?,"+
					LASTNAME +"=?,"+
					ADDR1 +"=?,"+
					ADDR2 +"=?,"+
					ADDR3 +"=?,"+
					ADDR4 +"=?,"+
					CITY +"=?,"+
					STATE +"=?,"+
					POSTALCODE +"=?,"+
					CHARITY +"=?,"+
					DONATIONAMT +"=?,"+
					PAYMENTTYPE +"=?,"+
					FILEVERSION +"=?,"+
					HISTTRANNO +"=? "+
					"WHERE SequenceNumber = ?"
			);		
			pstmt.setString(1, rec.getHonorific());
			pstmt.setString(2, rec.getFirstName() );
			pstmt.setString(3, rec.getLastName() );
			pstmt.setString(4, rec.getAddr1() );
			pstmt.setString(5, rec.getAddr2() );
			pstmt.setString(6, rec.getAddr3() );
			pstmt.setString(7, rec.getAddr4() );
			pstmt.setString(8, rec.getCity() );
			pstmt.setString(9, rec.getState() );
			pstmt.setString(10, rec.getPostalCode() );
			pstmt.setString(11, rec.getCharityName() );
			pstmt.setInt   (12, rec.getDonationAmount() );
			pstmt.setString(13, rec.getPaymentType() );
			pstmt.setInt   (14, FILE_VERSION);
			pstmt.setInt   (15, rec.getHistTranNo() );
			pstmt.setInt   (16, rec.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbDonationsPeer.Update:"+e.getMessage(),e);
		}
}
}
