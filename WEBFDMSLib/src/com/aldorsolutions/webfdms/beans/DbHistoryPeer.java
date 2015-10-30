package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import com.aldorsolutions.webfdms.util.FormatString;

/**
 * SQL specifics for Tran History class 
 * Creation date: (12/23/2001 4:13:09 PM) modify 030309
 * @author:
 */
public class DbHistoryPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {
    
    static public final String IDENTITY    	= "TranHistID";
    static public final String VITALSID 	= "VitalsMasterKey";
    static public final String TRANNUMBER 	= "TranHistID";
    static public final String CHARGETYPE 	= "ChargeType";
    static public final String DESCRIPTION	= "Description";
    static public final String AMOUNT		= "AmountOfTran";
    static public final String ARACCT		= "ARacct";
    static public final String GLACCT 		= "GLacct";
    static public final String TAXEXEMPTAMT	= "TaxExemptAmount";
    static public final String TAXCODE		= "TaxCode";
    static public final String DATEOFTRAN	= "DateOfTran";
    static public final String SPFCODE		= "SPFcode";
    static public final String ITEMCATEGORY	= "ItemCategory";
    static public final String RECEIPTNO	= "ReceiptNumber";
    static public final String POSTED		= "Postedyn";
    static public final String ORIGINALPOST     = "OriginalPostingYN";
    static public final String DELETECODE	= "DeleteTransaction";
    static public final String INVITEMNAME	= "InventoryItemName";
    static public final String INVMASTERKEY	= "InvMasterKey";
    static public final String INVCOSTSALE	= "InvCostOfSale";
    static public final String PMTMETHOD	= "PaymentMethod";
    static public final String PMTCOMPONENT	= "PaymentComponent";
    static public final String USERINITIALS	= "UserInitials";
    static public final String MANUALRECPT	= "ManualReceiptNo";
    static public final String RECORDVER	= "RecordVersion";
    static public final String INTERFACENO	= "InterfaceSequenceNo";
    static public final String ORIGCHRGAMT	= "OrigChrgAmount";
    static public final String ORIGCHRGDESC	= "OrigChrgDescr";
    static public final String EXPORTED		= "Exported";
    static public final String DATEPAID		= "DatePaid";
    static public final String DEPOSITBATCH	= "DepositBatchNumber";
    static public final String CLAIMNUMBER	= "ClaimNumber";
    static public final String DATEMODIFIED     = "DateModified";
    static public final String TIMEMODIFIED     = "TimeModified";
    static public final String LOCATIONID       = "locationId";
    static public final String INVONHANDID	= "InvOnHandID";
    static public final String TAXAMOUNT 	= "TaxAmount";
    static public final String PRICELISTID = "PriceListID";
    static public final String SERVICEDATE = "ServiceDate";
    static public final String DATETIMEOFTRANS = "DatetimeOfTrans";
    static public final String SEQUENCE = "Sequence";
    static public final String COMMENT = "Comment";
    
    static public final String STARTDATEOFTRAN = "StartDateOfTran";
    static public final String ENDDATEOFTRAN = "EndDateOfTran";
    /**
     * getInsertSql method comment.
     */
    protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
        java.sql.Connection connection = null;
        //AppLog.trace("DbHistorPeer.getInsertSql");
        try {
            DbHistory rec=(DbHistory)p;
            connection = t.getConnection();
            java.sql.PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO transactionhistory ("+
                    IDENTITY +","+
                    VITALSID +","+
                    CHARGETYPE +","+
                    DESCRIPTION +","+
                    AMOUNT +","+
                    ARACCT +","+
                    GLACCT +","+
                    TAXEXEMPTAMT +","+
                    TAXCODE +","+
                    DATEOFTRAN +","+
                    SPFCODE +","+
                    ITEMCATEGORY +","+
                    RECEIPTNO +","+
                    POSTED +","+
                    ORIGINALPOST +","+
                    DELETECODE +","+
                    INVITEMNAME +","+
                    INVMASTERKEY +","+
                    INVCOSTSALE +","+
                    PMTMETHOD +","+
                    PMTCOMPONENT +","+
                    USERINITIALS +","+
                    MANUALRECPT +","+
                    RECORDVER +","+
                    INTERFACENO +","+
                    ORIGCHRGAMT +","+
                    ORIGCHRGDESC +","+
                    EXPORTED +","+
                    DATEPAID +","+
                    DEPOSITBATCH +","+
                    CLAIMNUMBER +","+
                    LOCATIONID +","+
                    INVONHANDID +", "+
                    TAXAMOUNT + ", " +
                    PRICELISTID + ", " +
                    SERVICEDATE + ", "+
                    DATETIMEOFTRANS + ", "+
                    SEQUENCE + ", "+
                    COMMENT + " " +
                    ") VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt   (1, rec.getLMainKey());
            pstmt.setShort(2, rec.getIHistType());
            pstmt.setString(3, rec.getCHistDesc());
            pstmt.setInt   (4, rec.getLHistAmount());
            pstmt.setString(5, rec.getCHistARacct());
            pstmt.setString(6, rec.getCHistGLAcct());
            pstmt.setInt   (7, rec.getLHistExempt());
            pstmt.setString(8, rec.getCHistTaxCode());
            pstmt.setDate(9, rec.getCHistDate());
            pstmt.setString(10,FormatString.charToString(rec.getCHistSPF()));
            pstmt.setString(11,rec.getCHistItemType());
            pstmt.setInt   (12,rec.getLHistReceiptNo());
            pstmt.setString(13,FormatString.charToString(rec.getCHistPosted()));
            pstmt.setString(14,FormatString.charToString(rec.getCHistOriginalPosting()));
            pstmt.setString(15,FormatString.charToString(rec.getCHistDeleteTran()));
            pstmt.setString(16,rec.getCHistInvItemName());
            pstmt.setInt   (17,rec.getLHistInvSeqNo());
            pstmt.setInt   (18,rec.getLHistCostOfSale());
            pstmt.setString(19,rec.getCHistPayMethod());
            pstmt.setString(20,rec.getCHistPmtComponent());
            pstmt.setString(21,rec.getCHistUserInit());
            pstmt.setString(22,rec.getCHistManualReceipt());
            pstmt.setShort(23,rec.getIHistVersion());
            pstmt.setInt   (24,rec.getLInterfaceSeqNo());
            pstmt.setInt   (25,rec.getOrigChargeAmt());
            pstmt.setString(26,rec.getOrigChargeDescr());
            pstmt.setString(27,FormatString.charToString(rec.getExported()));
            pstmt.setDate(28,rec.getCHistDatePaid());
            pstmt.setString(29,rec.getDepositBatchNumber());
            pstmt.setString(30,rec.getClaimNumber());
            pstmt.setInt   (31,rec.getLocationId());
            pstmt.setInt   (32,rec.getInvOnHandID());
            pstmt.setInt   (33,rec.getTaxAmount());
            pstmt.setInt 	 (34,rec.getPriceListID());
            pstmt.setString	 (35,rec.getServiceDate());
            pstmt.setTimestamp(36, new java.sql.Timestamp(rec.getDatetimeOfTrans()));
            pstmt.setInt 	 (37,rec.getSequence());
            pstmt.setString(38, rec.getComment());
            return pstmt;
        } catch (java.sql.SQLException e){
            throw new com.aldorsolutions.webfdms.database.PersistenceException("DbHistoryPeer.Update:"+e.getMessage(),e);
        }
    
    }
    /**
     * getRemoveSql method comment.
     */
    protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
        
//        throw new com.aldorsolutions.webfdms.database.PersistenceException("DbHistory physical delete not allowed.");
        
        java.sql.PreparedStatement pstmt=null;
		java.sql.Connection connection = null;
		try {
			connection = t.getConnection();
			pstmt = connection.prepareStatement(
			"UPDATE transactionhistory SET DeleteTransaction=? where TranHistID=?");
			pstmt.setString(1, "Y");	
			pstmt.setInt(2,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbHistory.Remove",e);
		}
        
    }
    /**
     * getRestoreSql method comment.
     */
    protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
			// I am not sure why they are not looking at deleted transactions.  Deleted transactions
			// can occur because of Voided cases.  When a case is voided we need to reverse the voided 
			// information.  This bug was fixed because of Keystone.  If you have a problem with
			// this then know you could be affecting other customers.  What should really happen is
			// that the interface file should deal with deleted transactions.  If you don't want to 
			// export deleted transactions then that accounting interface should ignore those records.
    	//
    	// I had to make a change here because it would not updated the posted flag on deleted transactions
    	// once it was exported.
			// Chad Lehnert 12/26/07
//        return "SELECT * from transactionhistory  WHERE DeleteTransaction <> \"Y\" and TranHistID = " + p.getId();
        return "SELECT * from transactionhistory  WHERE TranHistID = " + p.getId();
    }
    
    /**
     * getUpdateSql method comment.
     */
    protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
        java.sql.Connection connection = null;
        //AppLog.trace("DbHistorPeer.getUpdateSql");
        try {
            DbHistory rec=(DbHistory)p;
            connection = t.getConnection();
            java.sql.PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE transactionhistory SET "+
                    VITALSID +"=?,"+
                    CHARGETYPE +"=?,"+
                    DESCRIPTION +"=?,"+
                    AMOUNT +"=?,"+
                    ARACCT +"=?,"+
                    GLACCT +"=?,"+
                    TAXEXEMPTAMT +"=?,"+
                    TAXCODE +"=?,"+
                    DATEOFTRAN +"=?,"+
                    SPFCODE +"=?,"+
                    ITEMCATEGORY +"=?,"+
                    RECEIPTNO +"=?,"+
                    POSTED +"=?,"+
                    ORIGINALPOST +"=?,"+
                    DELETECODE +"=?,"+
                    INVITEMNAME +"=?,"+
                    INVMASTERKEY +"=?,"+
                    INVCOSTSALE +"=?,"+
                    PMTMETHOD +"=?,"+
                    PMTCOMPONENT +"=?,"+
                    USERINITIALS +"=?,"+
                    MANUALRECPT +"=?,"+
                    RECORDVER +"=?,"+
                    INTERFACENO +"=?,"+
                    ORIGCHRGAMT +"=?,"+
                    ORIGCHRGDESC +"=?,"+
                    EXPORTED +"=?,"+
                    DATEPAID +"=?,"+
                    DEPOSITBATCH +"=?,"+
                    CLAIMNUMBER +"=?,"+
                    LOCATIONID +"=?,"+
                    INVONHANDID +"=?, "+
                    TAXAMOUNT + "=?, "+
                    PRICELISTID + "=?, "+
                    SERVICEDATE + "=?, "+
                    DATETIMEOFTRANS + "=?, "+
                    SEQUENCE + "=?, "+
                    COMMENT + "=? "+
                    "WHERE "+IDENTITY+" = ?"
                    );
            pstmt.setInt   (1, rec.getLMainKey());
            pstmt.setShort(2, rec.getIHistType());
            pstmt.setString(3, rec.getCHistDesc());
            pstmt.setInt   (4, rec.getLHistAmount());
            pstmt.setString(5, rec.getCHistARacct());
            pstmt.setString(6, rec.getCHistGLAcct());
            pstmt.setInt   (7, rec.getLHistExempt());
            pstmt.setString(8, rec.getCHistTaxCode());
            pstmt.setDate(9, rec.getCHistDate());
            pstmt.setString(10,FormatString.charToString(rec.getCHistSPF()));
            pstmt.setString(11,rec.getCHistItemType());
            pstmt.setInt   (12,rec.getLHistReceiptNo());
            pstmt.setString(13,FormatString.charToString(rec.getCHistPosted()));
            pstmt.setString(14,FormatString.charToString(rec.getCHistOriginalPosting()));
            pstmt.setString(15,FormatString.charToString(rec.getCHistDeleteTran()));
            pstmt.setString(16,rec.getCHistInvItemName());
            pstmt.setInt   (17,rec.getLHistInvSeqNo());
            pstmt.setInt   (18,rec.getLHistCostOfSale());
            pstmt.setString(19,rec.getCHistPayMethod());
            pstmt.setString(20,rec.getCHistPmtComponent());
            pstmt.setString(21,rec.getCHistUserInit());
            pstmt.setString(22,rec.getCHistManualReceipt());
            pstmt.setShort(23,rec.getIHistVersion());
            pstmt.setInt   (24,rec.getLInterfaceSeqNo());
            pstmt.setInt   (25,rec.getOrigChargeAmt());
            pstmt.setString(26,rec.getOrigChargeDescr());
            pstmt.setString(27,FormatString.charToString(rec.getExported()));
            pstmt.setDate(28,rec.getCHistDatePaid());
            pstmt.setString(29,rec.getDepositBatchNumber());
            pstmt.setString(30,rec.getClaimNumber());
            pstmt.setInt   (31,rec.getLocationId());
            pstmt.setInt   (32,rec.getInvOnHandID());
            pstmt.setInt   (33,rec.getTaxAmount());
            pstmt.setInt	 (34,rec.getPriceListID());
            pstmt.setString(35, rec.getServiceDate());
            pstmt.setTimestamp(36, new java.sql.Timestamp(rec.getDatetimeOfTrans()));
            pstmt.setInt	 (37,rec.getSequence());
            pstmt.setString (38,rec.getComment());
            pstmt.setInt   (39,rec.getId());
            return pstmt;
        } catch (java.sql.SQLException e){
            throw new com.aldorsolutions.webfdms.database.PersistenceException("DbHistoryPeer.Update:"+e.getMessage(),e);
        }
    }
}
