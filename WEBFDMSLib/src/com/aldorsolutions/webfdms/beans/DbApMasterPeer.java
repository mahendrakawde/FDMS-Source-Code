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
public class DbApMasterPeer extends DatabasePeer {

	static public final String IDENTITY    	= "MasterID";
	static public final String INVOICEID	= "InvoiceID";
	static public final String VENDORID	 	= "VendorID";
	static public final String LOCALEID		= "LocaleID";
	static public final String LOCATIONID	= "LocationID";
	static public final String USERID	    = "UserID";
	static public final String INVOICENO	= "InvoiceNumber";
	static public final String INVOICEDATE	= "InvoiceDate";
	static public final String DUEDATE		= "DueDate";
	static public final String MEMO			= "Memo";
	static public final String HANDWRITTEN	= "Handwritten";
	static public final String TOBEPRINTED	= "CheckToBePrinted";
	static public final String DISCOUNTAMT	= "DiscountAmount";
	static public final String CHECKNUMBER	= "CheckNumber";
	static public final String BALANCE		= "Balance";
	static public final String CHECKDATE	= "CheckDate";
	static public final String INVOICETOTAL = "InvoiceTotal";
	static public final String VOIDEDCODE	= "VoidedCode";
	static public final String ASSOCIATEDVITALSID = "AssociatedVitalsID";
	static public final String CHECK1099 = "Check1099";
	static public final String CHECK1099AMOUNT = "Check1099Amount";
	static public final String APPROVALSTATUS = "ApprovalStatus";
	static public final String APPROVEDBY = "ApprovedBy";
	static public final String APPROVEDTMSTMP = "ApprovedTmstmp";
	static public final String VENDORNAME = "VendorName";
	static public final String VOIDEDCOMMENT = "VoidedComment";
	static public final String ORDERBY = "OrderBy";
		
	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
			java.sql.Connection connection = null;
			java.sql.PreparedStatement pstmt = null;
			try {
				DbApMaster rec=(DbApMaster)p;
				connection = t.getConnection();
				pstmt = connection.prepareStatement(
				"INSERT INTO apmaster ("+
					INVOICEID +","+
					VENDORID +","+
					LOCATIONID +","+
					USERID +","+
					LOCALEID +","+
					INVOICENO +","+
					INVOICEDATE +","+
					DUEDATE	 +","+
					MEMO	 +","+
					HANDWRITTEN +","+
					TOBEPRINTED +","+
					DISCOUNTAMT +","+
					CHECKNUMBER +","+
					BALANCE +","+
					CHECKDATE +","+
					INVOICETOTAL +","+
					VOIDEDCODE	+", "+
					APPROVALSTATUS	+", "+
					APPROVEDBY	+", "+
					APPROVEDTMSTMP	+", "+
					ASSOCIATEDVITALSID	+", "+
					CHECK1099	+", "+
					CHECK1099AMOUNT	+", "+
					VENDORNAME	+", "+
					VOIDEDCOMMENT	+" "+
					") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
				);		
				pstmt.setInt	(1, rec.getInvoiceID());	
				pstmt.setInt	(2, rec.getVendorID());	
				pstmt.setInt	(3, rec.getLocationID());
				pstmt.setInt   	(4, rec.getUserID());
				pstmt.setInt   	(5, rec.getLocaleID());
				pstmt.setString	(6, rec.getInvoiceNumber());
				if (rec.getInvoiceDate()!=null){
					pstmt.setDate(7, new java.sql.Date(rec.getInvoiceDate().getTime()));
				}
				else { 
					pstmt.setDate(7, null);
				}
				if (rec.getDueDate()!=null){
					pstmt.setDate(8, new java.sql.Date(rec.getDueDate().getTime()));
				}
				else { 
					pstmt.setDate(8, null);
				}
				pstmt.setString	(9, rec.getMemo());
				pstmt.setString	(10, rec.getHandwritten());
				pstmt.setString	(11, rec.getToBePrinted());
				pstmt.setInt	(12, rec.getDiscountAmount());
				pstmt.setLong   	(13, rec.getCheckNumber());
				pstmt.setInt   	(14, rec.getBalance());
				if (rec.getCheckDate()!=null){
					pstmt.setDate	(15, new java.sql.Date(rec.getCheckDate().getTime())); 
				}
				else { pstmt.setDate(15, null);}
				pstmt.setInt	(16, rec.getInvoiceTotal());
				pstmt.setString	(17, rec.getVoidedCode());
				pstmt.setLong	(18, rec.getApprovalStatus());
				pstmt.setLong	(19, rec.getApprovedBy());
				pstmt.setTimestamp	(20, rec.getApprovedTmstmp());
				pstmt.setInt (21, rec.getVitalsID());
				pstmt.setBoolean (22, rec.isCheck1099());
				pstmt.setInt (23, rec.getCheck1099Amount());
				pstmt.setString (24, rec.getVendorName());
				pstmt.setString (25, rec.getVoidedComment());
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
				pstmt = connection.prepareStatement("DELETE FROM  apmaster WHERE MasterID=?");
				pstmt.setInt(1,p.getId());
				return pstmt;
			}
			catch (java.sql.SQLException e){
				throw new PersistenceException("DbApMasterPeer.Remove",e);
			}
	}
	/**
	 * getRestoreSql method comment.
	 */
	protected String getRestoreSql(Persistent p) {
			return 
			"SELECT * from apmaster WHERE MasterID = "
			+ p.getId();
	}
	/**
	 * getUpdateSql method comment.
	 */
	protected java.sql.PreparedStatement getUpdateSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
			java.sql.Connection connection = null;
			java.sql.PreparedStatement pstmt = null;
			try {
				DbApMaster rec=(DbApMaster)p;
				connection = t.getConnection();
				pstmt = connection.prepareStatement(
				"UPDATE apmaster SET "+
					INVOICEID +"=?,"+
					VENDORID +"=?,"+
					LOCATIONID +"=?,"+
					USERID +"=?,"+
					LOCALEID +"=?,"+
					INVOICENO +"=?,"+
					INVOICEDATE +"=?,"+
					DUEDATE	 +"=?,"+
					MEMO	 +"=?,"+
					HANDWRITTEN  +"=?,"+
					TOBEPRINTED  +"=?,"+
					DISCOUNTAMT  +"=?,"+
					CHECKNUMBER  +"=?,"+
					BALANCE      +"=?,"+
					CHECKDATE	 +"=?,"+
					INVOICETOTAL +"=?,"+
					VOIDEDCODE   +"=?, "+
					APPROVALSTATUS   +"=?, "+
					APPROVEDBY   +"=?, "+
					APPROVEDTMSTMP   +"=?, "+
					ASSOCIATEDVITALSID +"=?, "+
					CHECK1099	+"=?, "+
					CHECK1099AMOUNT	+"=?, "+
					VENDORNAME +"=?, "+
					VOIDEDCOMMENT +"=? "+
					" WHERE "+IDENTITY+" = ?"
				);		
				pstmt.setInt	(1, rec.getInvoiceID());	
				pstmt.setInt	(2, rec.getVendorID());	
				pstmt.setInt	(3, rec.getLocationID());
				pstmt.setInt   	(4, rec.getUserID());
				pstmt.setInt   	(5, rec.getLocaleID());
				pstmt.setString	(6, rec.getInvoiceNumber());
				if (rec.getInvoiceDate()!=null){
					pstmt.setDate	(7, new java.sql.Date(rec.getInvoiceDate().getTime()));
				}
				else { pstmt.setDate(7, null);}
				if (rec.getDueDate()!=null){
					pstmt.setDate   (8, new java.sql.Date(rec.getDueDate().getTime()));
				}
				else { pstmt.setDate(8, null);}
				pstmt.setString	(9, rec.getMemo());
				pstmt.setString	(10, rec.getHandwritten());
				pstmt.setString	(11, rec.getToBePrinted());
				pstmt.setInt	(12, rec.getDiscountAmount());
				pstmt.setLong   	(13, rec.getCheckNumber());
				pstmt.setInt   	(14, rec.getBalance());
				if (rec.getCheckDate()!=null){
					pstmt.setDate	(15, new java.sql.Date(rec.getCheckDate().getTime())); 
				}
				else { pstmt.setDate(15, null);}
				pstmt.setInt	(16, rec.getInvoiceTotal());
				pstmt.setString	(17, rec.getVoidedCode());
				pstmt.setLong	(18, rec.getApprovalStatus());
				pstmt.setLong	(19, rec.getApprovedBy());
				pstmt.setTimestamp	(20, rec.getApprovedTmstmp());
				pstmt.setInt (21, rec.getVitalsID());
				pstmt.setBoolean (22, rec.isCheck1099());
				pstmt.setInt (23, rec.getCheck1099Amount());
				pstmt.setString (24, rec.getVendorName());
				pstmt.setString (25, rec.getVoidedComment());
				pstmt.setInt   	(26, rec.getId());
				return pstmt;
			}
			catch (java.sql.SQLException e){
				throw new PersistenceException("DbApMasterPeer.Update:"+e.getMessage(),e);
			}
	}
}
