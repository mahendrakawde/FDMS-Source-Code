package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * DbInvMasterPeer supplies constants and SQL for Persistent Class.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author: 
 */
public class DbInvMasterPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {
	static public final int	   FILE_VERSION=10;
	static public final String IDENTITY    	= "Identity";
	static public final String ITEMNAME	 	= "ItemName";
	static public final String DESCRIPTION	= "Description";
	static public final String STOCKTYPE    = "StockType";
	static public final String PRODUCTLINE	= "ProductLine";
	static public final String INSHOWROOM	= "Showroom";
	static public final String SUPPLIERNAME	= "Supplier";
	static public final String COST			= "Cost";
	static public final String PRICE		= "Price";
	static public final String MINIMUMQTY	= "MinimumOnHand";
	static public final String REORDERQTY	= "ReorderQuantity";
	static public final String TAXEXEMPTAMT	= "TaxExempt";
	static public final String TAXCODE		= "TaxCode";
	static public final String RECORDVERSION= "RecordVersion";
	static public final String DELETECODE	= "DeleteCode";
	static public final String CASKETTYPE	= "CasketTypeCode";
	static public final String CASKETUSE	= "CasketUseCategory";
	static public final String INTERIORDESC	= "InteriorDescription";
	static public final String EXTERIORDESC	= "ExteriorDescription";
	static public final String EXTERIORCODE	= "ExteriorCode";
	static public final String NOTES		= "Notes";
	static public final String GLCATEGORY	= "GLacctCategory";
	static public final String DATEMODIFIED	= "DateModified";
	static public final String TIMEMODIFIED	= "TimeModified";
	static public final String LOCALE		= "Locale";
	static public final String SEQUENCENO   = "SequenceNo";
	static public final String CONTRACTLINE = "ContractLineNo";
	static public final String IMAGEURL 	= "ImageUrl";
	static public final String GLSALES 		= "SalesGLAcct";
	static public final String GLASSET	 	= "AssetGLAcct";
	static public final String GLCOST	 	= "CostGLAcct";
	static public final String INVVERSIONID = "InvVersionID";
	static public final String ACCOUNTDESCCDID = "AccountDescCDID";
	static public final String COMMISSIONABLE	= "Commissionable";
	
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbInvMaster rec=(DbInvMaster)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"INSERT INTO invmaster ("+
				ITEMNAME	+","+
				DESCRIPTION +","+
				STOCKTYPE +","+
				PRODUCTLINE +","+
				INSHOWROOM +","+
				SUPPLIERNAME +","+
				COST		 +","+
				PRICE		 +","+
				MINIMUMQTY +","+
				REORDERQTY +","+
				TAXEXEMPTAMT +","+
				TAXCODE +","+
				RECORDVERSION +","+
				DELETECODE +","+
				CASKETTYPE +","+
				CASKETUSE +","+
				INTERIORDESC +","+
				EXTERIORDESC +","+
				EXTERIORCODE +","+
				NOTES +","+
				GLCATEGORY +","+
				LOCALE +","+ 
				SEQUENCENO +","+ 
				CONTRACTLINE +","+ 
				IMAGEURL +","+ 
				GLSALES +","+
				GLCOST +","+
				GLASSET +", "+
				INVVERSIONID +", "+
				DATEMODIFIED+", "+
				TIMEMODIFIED+", "+
				ACCOUNTDESCCDID+", "+
				COMMISSIONABLE+" "+
				") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, rec.getCItemName() );	
			pstmt.setString(2, rec.getCDescription() );	
			pstmt.setString(3, rec.getCStockType() );	
			pstmt.setString(4, rec.getCProductLine() );	
			pstmt.setString(5, rec.getCShowRoom() );	
			pstmt.setString(6, rec.getCSupplierCode() );	
			pstmt.setInt   (7, rec.getLCost() );	
			pstmt.setInt   (8, rec.getLPrice() );	
			pstmt.setShort (9, rec.getIMinimumOnHand() );	
			pstmt.setInt   (10, rec.getLReorderQuantity() );	
			pstmt.setFloat (11, rec.getFTaxExempt() );	
			pstmt.setString(12, rec.getCTaxCode() );	
			pstmt.setShort (13, (short)FILE_VERSION );	
			pstmt.setString(14, rec.getCDeleteCode() );	
			pstmt.setString(15, rec.getCasketType());	
			pstmt.setString(16, rec.getCasketUse() );
			pstmt.setString(17, rec.getInterior() );	
			pstmt.setString(18, rec.getExterior() );	
			pstmt.setString(19, rec.getExtCode() );	
			pstmt.setString(20, rec.getNotes() );	
			pstmt.setString(21, rec.getGlAcctCode() );	
			pstmt.setInt   (22, rec.getLocale() );	
			pstmt.setInt   (23, rec.getSequenceNo());
			pstmt.setShort (24, rec.getContractLineNo());
			pstmt.setString(25, rec.getImageUrl() );	
			pstmt.setString(26, rec.getCGLsalesAcct() );
			pstmt.setString(27, rec.getCGLcostAcct() );
			pstmt.setString(28, rec.getCGLassetAcct() );
			pstmt.setInt   (29, rec.getInvVersionID() );	
			pstmt.setDate  (30, rec.getDateModified());
			pstmt.setTime	 (31, rec.getTimeModified());
			pstmt.setLong	 (32, rec.getAccountDescCDID());
			pstmt.setString(33, rec.getCommissionable() );
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbInvMasterPeer.Insert:"+e.getMessage(),e);
		}
}
/**
 * getRemoveSql method comment.
 */
protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
	throw new com.aldorsolutions.webfdms.database.PersistenceException("DbInvMasterPeer.Remove: NOT ALLOWED. User delete code instead.");
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return "SELECT * FROM invmaster WHERE Identity = " + p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbInvMaster rec=(DbInvMaster)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE invmaster SET "+
				ITEMNAME	+"=?,"+
				DESCRIPTION +"=?,"+
				STOCKTYPE +"=?,"+
				PRODUCTLINE +"=?,"+
				INSHOWROOM +"=?,"+
				SUPPLIERNAME +"=?,"+
				COST		 +"=?,"+
				PRICE		 +"=?,"+
				MINIMUMQTY +"=?,"+
				REORDERQTY +"=?,"+
				TAXEXEMPTAMT +"=?,"+
				TAXCODE +"=?,"+
				RECORDVERSION +"=?,"+
				DELETECODE +"=?,"+
				CASKETTYPE +"=?,"+
				CASKETUSE +"=?,"+
				INTERIORDESC +"=?,"+
				EXTERIORDESC +"=?,"+
				EXTERIORCODE +"=?,"+
				NOTES +"=?,"+
				GLCATEGORY +"=?,"+
				LOCALE +"=?,"+ 
				SEQUENCENO +"=?,"+
				CONTRACTLINE +"=?,"+
				IMAGEURL +"=?,"+
				GLSALES +"=?,"+
				GLCOST +"=?,"+
				GLASSET +"=?, "+
				INVVERSIONID +"=?, "+
				DATEMODIFIED+"=?,"+
				TIMEMODIFIED+"=?, "+
				ACCOUNTDESCCDID+"=?, "+
				COMMISSIONABLE+"=? "+
				" WHERE "+IDENTITY+" = ?"
			);		
			pstmt.setString(1, rec.getCItemName() );	
			pstmt.setString(2, rec.getCDescription() );	
			pstmt.setString(3, rec.getCStockType() );	
			pstmt.setString(4, rec.getCProductLine() );	
			pstmt.setString(5, rec.getCShowRoom() );	
			pstmt.setString(6, rec.getCSupplierCode() );	
			pstmt.setInt   (7, rec.getLCost() );	
			pstmt.setInt   (8, rec.getLPrice() );	
			pstmt.setShort (9, rec.getIMinimumOnHand() );	
			pstmt.setInt   (10, rec.getLReorderQuantity() );	
			pstmt.setFloat (11, rec.getFTaxExempt() );	
			pstmt.setString(12, rec.getCTaxCode() );	
			pstmt.setShort (13, (short)FILE_VERSION );	
			pstmt.setString(14, rec.getCDeleteCode() );	
			pstmt.setString(15, rec.getCasketType());	
			pstmt.setString(16, rec.getCasketUse() );	
			pstmt.setString(17, rec.getInterior() );	
			pstmt.setString(18, rec.getExterior() );	
			pstmt.setString(19, rec.getExtCode() );	
			pstmt.setString(20, rec.getNotes() );	
			pstmt.setString(21, rec.getGlAcctCode() );
			pstmt.setInt   (22, rec.getLocale() );
			pstmt.setInt   (23, rec.getSequenceNo());
			pstmt.setShort (24, rec.getContractLineNo());
			pstmt.setString(25, rec.getImageUrl() );
			pstmt.setString(26, rec.getCGLsalesAcct() );
			pstmt.setString(27, rec.getCGLcostAcct() );
			pstmt.setString(28, rec.getCGLassetAcct() );
			pstmt.setInt   (29, rec.getInvVersionID() );
			pstmt.setDate  (30, rec.getDateModified());
			pstmt.setTime  (31, rec.getTimeModified());
			pstmt.setLong  (32, rec.getAccountDescCDID());
			pstmt.setString(33, rec.getCommissionable() );
			pstmt.setInt   (34, rec.getId());
			
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbInvMasterPeer.Update:"+e.getMessage(),e);
		}
}
}
