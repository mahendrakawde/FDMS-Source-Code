package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import com.aldorsolutions.webfdms.util.FormatString;
/**
 * DbInvOnHandPeer supplies SQL for Persistent Class
 * Creation date: (12/30/2001 4:13:09 PM)
 * @author: 
 */
public class DbInvOnHandPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY    	= "Identity";
	static public final String ITEMNAME	 	= "ItemName";
	static public final String ITEMSEQNO	= "ItemSequenceNo";
	static public final String SERIALNUMBER = "SerialNumber";
	static public final String SHOWROOM		= "ShowRoom";
	static public final String DATEIN		= "DateIn";
	static public final String INVOICENUMBER= "InvoiceNumber";
	static public final String LOCATION		= "Location";
	static public final String QUANTITY		= "Quantity";
	static public final String COST			= "Cost";
	static public final String NOTES		= "Notes";
	static public final String DATEMODIFIED	= "DateModified";
	static public final String TIMEMODIFIED	= "TimeModified";
	static public final String MASTERID		= "MasterID";
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbInvOnHand rec=(DbInvOnHand)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"INSERT INTO invonhand ("+
				ITEMNAME	+","+
				ITEMSEQNO	+","+
				SERIALNUMBER	+","+
				SHOWROOM	+","+
				DATEIN	+","+
				INVOICENUMBER	+","+
				LOCATION	+","+
				QUANTITY	+","+
				COST	+","+
				NOTES	+","+
				MASTERID	+" "+
				") VALUES (?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, rec.getCItemName());	
			pstmt.setInt   (2, rec.getSequenceNumber());
			pstmt.setString(3, rec.getCSerialNumber());
			pstmt.setString(4, FormatString.charToString(rec.getCShowRoom()));
			pstmt.setString(5, rec.getCDateIn());
			pstmt.setString(6, rec.getCInvoiceNumber());
			pstmt.setString(7, rec.getCLocation());
			pstmt.setInt   (8, rec.getQuantity());
			pstmt.setInt   (9, rec.getCost());
			pstmt.setString(10,rec.getCNotes());
			pstmt.setInt   (11,rec.getMasterId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbInvOnHandPeer.Insert:"+e.getMessage(),e);
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
			pstmt = connection.prepareStatement("DELETE FROM invonhand WHERE Identity=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbInvOnHandPeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return 
		"SELECT * FROM invonhand WHERE Identity = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbInvOnHand rec=(DbInvOnHand)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE invonhand SET "+
				ITEMNAME	+"=?,"+
				ITEMSEQNO	+"=?,"+
				SERIALNUMBER	+"=?,"+
				SHOWROOM	+"=?,"+
				DATEIN	+"=?,"+
				INVOICENUMBER	+"=?,"+
				LOCATION	+"=?,"+
				QUANTITY	+"=?,"+
				COST	+"=?,"+
				NOTES	+"=?,"+
				MASTERID	+"=? "+
				" WHERE "+IDENTITY+" = ?"
			);		
			pstmt.setString(1, rec.getCItemName());	
			pstmt.setInt   (2, rec.getSequenceNumber());
			pstmt.setString(3, rec.getCSerialNumber());
			pstmt.setString(4, FormatString.charToString(rec.getCShowRoom()));
			pstmt.setString(5, rec.getCDateIn());
			pstmt.setString(6, rec.getCInvoiceNumber());
			pstmt.setString(7, rec.getCLocation());
			pstmt.setInt   (8, rec.getQuantity());
			pstmt.setInt   (9, rec.getCost());
			pstmt.setString(10,rec.getCNotes());
			pstmt.setInt   (11,rec.getMasterId());
			pstmt.setInt   (12,rec.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbInvOnHandPeer.Update:"+e.getMessage(),e);
		}
}
}
