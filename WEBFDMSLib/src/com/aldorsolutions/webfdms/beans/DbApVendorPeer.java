package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;


/**
 * DbApVendorPeer supplies constants and SQL for Persistent Class.
 * Creation date: (4/30/2001 4:13:09 PM)
 * @author: 
 */
public class DbApVendorPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY    				= "VendorID";
	static public final String VENDORCODE	 			= "VendorCode";
	static public final String LOCALE					= "Locale";
	static public final String NAME		 				= "Name";
	static public final String ADDR1					= "Addr1";
	static public final String ADDR2	    			= "Addr2";
	static public final String CITYSTATE				= "CityState";
	static public final String POSTALCODE				= "PostalCode";
	static public final String CONTACTNAME				= "ContactName";
	static public final String PHONE					= "Phone";
	static public final String EMAILADDR				= "EmailAddr";
	static public final String DEFAULTACCTID			= "DefaultAcctID";
	static public final String NOTES					= "Notes";
	static public final String DELETECODE				= "DeleteCode";
	static public final String LOCATIONID				= "LocationID";
	static public final String INTERNALVENDOR 			= "InternalVendor";
	static public final String VENDORSTATE 				= "VendorState";	
	static public final String PHONE2 					= "Phone2";	
	static public final String VENDORCOUNTRY			= "VendorCountry";	
	static public final String FAX    					= "Fax";
	static public final String ACCOUNTNUMBER			= "AccountNumber";
	static public final String DISCOUNTPERCENTAGE	    = "DiscountPercentage";
	static public final String DISCOUNTIFINDAYS			= "DiscountIfInDays";
	static public final String DISCOUNTDUEINDAYS		= "DiscountDueInDays";
	static public final String VENDOR1099TYPE			= "Vendor1099Type";
	static public final String VENDOR1099PAYMENT		= "Vendor1099Payment";
	static public final String TAXID					= "TaxID";
	static public final String APPROVEDVENDOR			= "ApprovedVendor";
	static public final String CREDITLIMIT				= "CreditLimit";	
	
	
	
	
	
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			DbApVendor rec=(DbApVendor)p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
			"INSERT INTO apvendors ("+
				VENDORCODE +","+
				NAME +","+
				ADDR1 +","+
				ADDR2 +","+
				CITYSTATE +","+
				POSTALCODE +","+
				CONTACTNAME +","+
				PHONE +","+
				EMAILADDR +","+
				DEFAULTACCTID +","+
				LOCALE +","+
				LOCATIONID +","+
				NOTES +", "+
				INTERNALVENDOR +", "+
				VENDORSTATE +", "+
				PHONE2+", "+
				VENDORCOUNTRY+", "+
				FAX+", "+
				ACCOUNTNUMBER+", "+
				DISCOUNTPERCENTAGE+", "+
				DISCOUNTIFINDAYS+", "+
				DISCOUNTDUEINDAYS+", "+
				VENDOR1099TYPE+", "+
				VENDOR1099PAYMENT+", "+
				TAXID+", "+
				APPROVEDVENDOR+", "+
				CREDITLIMIT+", "+
				DELETECODE+" "+
				") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
			);		
			pstmt.setString(1, rec.getVendorCode());	
			pstmt.setString(2, rec.getName());	
			pstmt.setString(3, rec.getAddr1());
			pstmt.setString(4, rec.getAddr2());
			pstmt.setString(5, rec.getCityState());
			pstmt.setString(6, rec.getPostalCode());	
			pstmt.setString(7, rec.getContactName());	
			pstmt.setString(8, rec.getPhone());	
			pstmt.setString(9, rec.getEmailAddr());	
			pstmt.setInt   (10, rec.getDefaultAcctID());	
			pstmt.setInt   (11,rec.getLocale());
			pstmt.setInt   (12, rec.getLocationID());	
			pstmt.setString(13, rec.getNotes());	
			pstmt.setBoolean(14, rec.isInternalVendor());
			pstmt.setString(15,rec.getVendorState());
			pstmt.setString(16,rec.getPhone2());
			pstmt.setString(17,rec.getVendorCountry());		
			pstmt.setString(18, rec.getFax());	
			pstmt.setString(19, rec.getAccountNumber());
			pstmt.setFloat(20, rec.getDiscountPercentage());	
			pstmt.setInt(21, rec.getDiscountIfInDays());	
			pstmt.setInt(22, rec.getDiscountDueInDays());	
			pstmt.setString(23, rec.getVendor1099Type());	
			pstmt.setFloat(24, rec.getVendor1099Payment());	
			pstmt.setString(25, rec.getTaxID());
			pstmt.setString(26, rec.getApprovedVendor());	
			pstmt.setFloat(27, rec.getCreditLimit());	
			pstmt.setString(28, rec.getDeleteCode());
			
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbApVendorPeer.Insert:"+e.getMessage(),e);
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
			//pstmt = connection.prepareStatement("DELETE FROM  ApVendors WHERE VendorID=?");
			//pstmt.setInt(1,p.getId());
			pstmt = connection.prepareStatement(
			"UPDATE apvendors SET DeleteCode=? where VendorID=?");
			pstmt.setString(1, "D");	
			pstmt.setInt(2,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbApVendorPeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return 
		"SELECT * from apvendors WHERE VendorID = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstmt = null;
		try {
			DbApVendor rec=(DbApVendor)p;
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
			"UPDATE apvendors SET "+
				VENDORCODE 				+"=?,"+
				NAME 							+"=?,"+
				ADDR1 						+"=?,"+
				ADDR2 						+"=?,"+
				CITYSTATE 				+"=?,"+
				POSTALCODE 				+"=?,"+
				CONTACTNAME 			+"=?,"+
				PHONE 						+"=?,"+
				EMAILADDR 				+"=?,"+
				DEFAULTACCTID 			+"=?,"+
				LOCALE 						+"=?,"+
				NOTES 						+"=?,"+
				LOCATIONID 				+"=?,"+
				INTERNALVENDOR 		+"=?,"+
				VENDORSTATE 			+"=?,"+
				PHONE2 						+"=?,"+
				VENDORCOUNTRY 		+"=?,"+
				FAX								+"=?,"+
				ACCOUNTNUMBER			+"=?,"+
				DISCOUNTPERCENTAGE+"=?,"+
				DISCOUNTIFINDAYS	+"=?,"+
				DISCOUNTDUEINDAYS	+"=?,"+
				VENDOR1099TYPE		+"=?,"+
				VENDOR1099PAYMENT	+"=?,"+
				TAXID							+"=?,"+
				APPROVEDVENDOR		+"=?,"+
				CREDITLIMIT				+"=? "+				
				" WHERE "+IDENTITY+" = ?"
			);		
			pstmt.setString(1, rec.getVendorCode());	
			pstmt.setString(2, rec.getName());	
			pstmt.setString(3, rec.getAddr1());
			pstmt.setString(4, rec.getAddr2());
			pstmt.setString(5, rec.getCityState());
			pstmt.setString(6, rec.getPostalCode());	
			pstmt.setString(7, rec.getContactName());	
			pstmt.setString(8, rec.getPhone());	
			pstmt.setString(9, rec.getEmailAddr());	
			pstmt.setInt   (10, rec.getDefaultAcctID());
			pstmt.setInt   (11,rec.getLocale());
			pstmt.setString(12, rec.getNotes());	
			pstmt.setInt   (13, rec.getLocationID());
			pstmt.setBoolean(14, rec.isInternalVendor());
			pstmt.setString(15, rec.getVendorState());	
			pstmt.setString(16, rec.getPhone2());	
			pstmt.setString(17, rec.getVendorCountry());	
			pstmt.setString(18, rec.getFax());	
			pstmt.setString(19, rec.getAccountNumber());
			pstmt.setFloat(20, rec.getDiscountPercentage());	
			pstmt.setInt(21, rec.getDiscountIfInDays());	
			pstmt.setInt(22, rec.getDiscountDueInDays());	
			pstmt.setString(23, rec.getVendor1099Type());	
			pstmt.setFloat(24, rec.getVendor1099Payment());	
			pstmt.setString(25, rec.getTaxID());
			pstmt.setString(26, rec.getApprovedVendor());	
			pstmt.setFloat(27, rec.getCreditLimit());						
			
			pstmt.setInt   (28, rec.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			e.printStackTrace();
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbApVendorPeer.Update:"+e.getMessage(),e);
		}
}
}
