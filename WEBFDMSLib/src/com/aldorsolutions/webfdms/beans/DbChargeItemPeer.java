package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * DB Specific constants and SQL for DbChargeItem.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author: 
 */
public class DbChargeItemPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final short  CURRENT_VERSION=10;
	static public final String IDENTITY    	= "AutoIndex";
	static public final String VITALSID 	= "VitalsMasterKey";
	static public final String CHARGETYPE 	= "ChargeType";
	static public final String DESCRIPTION 	= "Description";
	static public final String AMOUNT		= "Amount";
//	static public final String ARACCT		= "ARacct";
	static public final String GLACCT		= "GLacct";
	static public final String TAXEXEMPTAMT	= "TaxExemptAmt";
	static public final String TAXCODE		= "TaxCode";
	static public final String SEQUENCE		= "Sequence";
	static public final String SPFCODE		= "SPFcode";
	static public final String INVENTORYITEM= "InventoryItemName";
	static public final String INVMASTERKEY	= "InvMasterKey";
	static public final String CATEGORYCODE = "CategoryCode";
	static public final String ORIGINALPRICE = "OriginalPrice";
	static public final String FILEVERSION  = "RecordVersion";
	static public final String DATEMODIFIED = "DateModified";
	static public final String TIMEMODIFIED = "TimeModified";
	static public final String PRICELISTID  = "PriceListId";
	static public final String ONHANDID  	= "InvOnHandID";
	static public final String FROMPACKAGE  = "fromPackage";
	static public final String PACKAGEID 	= "PackageId";
	
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {

		java.sql.Connection connection = null;
		//AppLog.trace("DbChargeItemPeer.getInsertSql");
		try {
			DbChargeItem rec=(DbChargeItem)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"INSERT INTO charges ("+
						IDENTITY +","+
						VITALSID +","+
						CHARGETYPE +","+
						DESCRIPTION +","+
						AMOUNT +","+
//						ARACCT +","+
						GLACCT +","+
						TAXEXEMPTAMT +","+
						TAXCODE +","+
						SEQUENCE +","+
						SPFCODE +","+
						INVENTORYITEM +","+
						INVMASTERKEY +","+
						CATEGORYCODE +","+
						ORIGINALPRICE +","+
						PRICELISTID +","+
						FILEVERSION +","+
						ONHANDID +", "+
						FROMPACKAGE +", "+
						PACKAGEID +" "+
						") VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt   (1, rec.getVitalsID());	
			pstmt.setShort (2, (short)rec.getType());
			pstmt.setString(3, rec.getDescription());
			pstmt.setInt   (4, rec.getAmount());
//			pstmt.setString(5, rec.getArAcct());
			pstmt.setString(5, rec.getGlAcct());
			pstmt.setInt   (6, rec.getExemptAmount());
			pstmt.setString(7, rec.getTaxCode());
			pstmt.setInt   (8, rec.getSequenceNumber());
			pstmt.setString(9, com.aldorsolutions.webfdms.util.FormatString.charToString( rec.getSpfCode()) );
			pstmt.setString(10, rec.getInvItemName());
			pstmt.setInt   (11, rec.getInvSeqNo());
			pstmt.setString(12, rec.getItemCategoryType() );
			pstmt.setInt   (13, rec.getOrigPrice());
			pstmt.setInt   (14, rec.getPriceListId());
			pstmt.setShort (15, CURRENT_VERSION);
			pstmt.setInt   (16, rec.getInvOnHandID());
			pstmt.setBoolean(17, rec.isFromPackage());
			pstmt.setInt   (18, rec.getPackageId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbChargeItemPeer.Update:"+e.getMessage(),e);
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
			pstmt = connection.prepareStatement("DELETE FROM charges WHERE AutoIndex=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbChargesPeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return "SELECT * from charges WHERE AutoIndex = "
		+ p.getId();
}

/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		//AppLog.trace("DbChargeItemPeer.getUpdateSql");
		try {
			DbChargeItem rec=(DbChargeItem)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE charges SET "+
						VITALSID +"=?,"+
						CHARGETYPE +"=?,"+
						DESCRIPTION +"=?,"+
						AMOUNT +"=?,"+
//						ARACCT +"=?,"+
						GLACCT +"=?,"+
						TAXEXEMPTAMT +"=?,"+
						TAXCODE +"=?,"+
						SEQUENCE +"=?,"+
						SPFCODE +"=?,"+
						INVENTORYITEM +"=?,"+
						INVMASTERKEY +"=?,"+
						CATEGORYCODE +"=?,"+
						ORIGINALPRICE +"=?,"+
						FILEVERSION +"=?,"+
						PRICELISTID +"=?,"+
						ONHANDID +"=?, "+
						FROMPACKAGE +"=?, "+
						PACKAGEID +"=? "+
						"WHERE "+IDENTITY+" = ?"
			);		
			pstmt.setInt   (1, rec.getVitalsID());	
			pstmt.setShort (2, (short)rec.getType());
			pstmt.setString(3, rec.getDescription());
			pstmt.setInt   (4, rec.getAmount());
//			pstmt.setString(5, rec.getArAcct());
			pstmt.setString(5, rec.getGlAcct());
			pstmt.setInt   (6, rec.getExemptAmount());
			pstmt.setString(7, rec.getTaxCode());
			pstmt.setInt   (8, rec.getSequenceNumber());
			pstmt.setString(9, com.aldorsolutions.webfdms.util.FormatString.charToString( rec.getSpfCode()) );
			pstmt.setString(10, rec.getInvItemName());
			pstmt.setInt   (11, rec.getInvSeqNo());
			pstmt.setString(12, rec.getItemCategoryType() );
			pstmt.setInt   (13, rec.getOrigPrice());
			pstmt.setShort (14, CURRENT_VERSION);
			pstmt.setInt   (15, rec.getPriceListId());
			pstmt.setInt   (16, rec.getInvOnHandID());
			pstmt.setBoolean(17, rec.isFromPackage());
			pstmt.setInt   (18, rec.getPackageId());
			pstmt.setInt   (19,rec.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbChargeItemPeer.Update:"+e.getMessage(),e);
		}
}
}
