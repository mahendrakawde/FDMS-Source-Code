package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/**
 * Provides SQL for Financial Table
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author: 
 */
public class DbFinancialSummaryPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final int		FILE_VERSION	= 10;
	static public final String CASE_ID    		= "VitalsMasterKey";
	static public final String SALETYPE 		= "SaleType";
	static public final String CALCFINCHARGE	= "CalcFinanceChrgYN";
	static public final String FINCHARGERATE	= "FinanceChargeRate";
	static public final String SALEDATE			= "SaleDate";
	static public final String DUEDATE			= "DueDate";
	static public final String DATEINVOICED		= "DateInvoiceSent";
	static public final String AMOUNTFINANCED	= "AmountFinanced";
	static public final String INTERESTTOTAL	= "InterestTotal";
	static public final String TERM 			= "Term";
	static public final String MONTHLYPMTAMOUNT	= "MonthlyPaymentAmt";
	static public final String LASTFINANCEDPMT	= "LastFinancedPmt";
	static public final String FIRSTPMTDUEDATE	= "FirstPaymentDueDate";
	static public final String TOTALPAID		= "TotalPaidToDate";
	//static public final String FIRSTFINCHRGCALC = "First Fin Charg Calc";
	static public final String FINCHARGESCHEDULE= "FinChargeSchedule";
	static public final String FINCHARGELASTCALC= "FinChargeLastCalc";
	static public final String EAGLEAPPLNO 		= "EagleApplNo";
	static public final String EAGLEAUTHCODE	= "EagleAuthCode";
	static public final String LASTPMTRESP		= "LastPaymentRespons";
	static public final String LASTPMTMEMO		= "LastPaymentMemo";
	static public final String LASTPMTAMOUNT	= "LastPaymentAmount";
	static public final String LASTPMTDATE		= "LastPaymentDate";
	static public final String SENTTOACCOUNTING	= "SentToAccounting";
	static public final String TOTALCHARGES		= "TotalCharges";
	static public final String LASTFINCHARGE	= "LastFinanceCharge";
	static public final String LASTRECEIPTNO	= "LastReceiptNo";
	static public final String FILEVERSION		= "FileVersion";
	static public final String NOTES			= "MiscNotes";
	static public final String PRICELISTTABLE	= "PriceListTable";
	static public final String PKGLISTTABLE		= "PackageTable";
	static public final String FAMILYUSEDBEFORE	= "FamilyUsedUsBefore";
	static public final String FNRLHOMEPREVUSED	= "FnrlHomePrevUsed";
	static public final String ADVSOURCE		= "AdvSource";
	static public final String DATEMODIFIED		= "DateModified";
	static public final String TIMEMODIFIED		= "TimeModified";
	static public final String SALESDESCCDID = "SalesDescCDID";
	static public final String FINCHARGEPREVIOUSCALC = "FinChargePreviousCalc";
	static public final String PREVIOUSFINANCECHARGE = "PreviousFinanceCharge";
	
	
	
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		//AppLog.trace("DbFinancialSummaryPeer.getInsertSql");
		try {
			DbFinancialSummary rec=(DbFinancialSummary)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"INSERT INTO financialdata ("+
						SALETYPE +","+
						CALCFINCHARGE +","+
						FINCHARGERATE +","+
						SALEDATE +","+
						DUEDATE +","+
						DATEINVOICED +","+
						AMOUNTFINANCED +","+
						INTERESTTOTAL +","+
						TERM +","+
						MONTHLYPMTAMOUNT +","+
						LASTFINANCEDPMT +","+
						FIRSTPMTDUEDATE +","+
						TOTALPAID +","+
					//	FIRSTFINCHRGCALC +","+
						FINCHARGESCHEDULE +","+
						FINCHARGELASTCALC +","+
						EAGLEAPPLNO +","+
						EAGLEAUTHCODE +","+
						LASTPMTRESP +","+
						LASTPMTMEMO +","+
						LASTPMTAMOUNT +","+
						LASTPMTDATE +","+
						SENTTOACCOUNTING +","+
						TOTALCHARGES +","+
						LASTFINCHARGE +","+
						LASTRECEIPTNO +","+
						FILEVERSION +","+
						NOTES +","+
						PRICELISTTABLE +","+
						PKGLISTTABLE +","+
						FAMILYUSEDBEFORE +","+
						FNRLHOMEPREVUSED +","+
						ADVSOURCE +","+
						CASE_ID+"," +
						SALESDESCCDID+", "+
						FINCHARGEPREVIOUSCALC+"," +
						PREVIOUSFINANCECHARGE+" "+
					") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
			);		
			pstmt.setString	(1, rec.getCFinSaleType());
			pstmt.setShort	(2, rec.getIFinServiceChargesBox());
			pstmt.setFloat	(3, rec.getFFinServiceChargeRate());
			pstmt.setString	(4, rec.getCFinSaleDate());
			pstmt.setString	(5, rec.getCFinDueDate());
			pstmt.setString	(6, rec.getCFinDateInvoiceSent());
			pstmt.setInt	(7, rec.getLAmtFinanced());
			pstmt.setInt	(8, rec.getLInterest());
			pstmt.setShort	(9, rec.getSTerm());
			pstmt.setInt	(10, rec.getLPaymentAmt());
			pstmt.setInt	(11, rec.getLLastFinancedPmtAmt());
			pstmt.setString	(12, rec.getCFirstPmtDueDate());
			pstmt.setInt	(13, rec.getLTotalPaidToDate());
			pstmt.setString	(14, rec.getFCscheduleDate());
			pstmt.setString	(15, rec.getFClastRunDate());
			pstmt.setString	(16, rec.getCApplNo());
			pstmt.setString	(17, rec.getCAuthCode());
			pstmt.setString	(18, rec.getCLastPmtResp());
			pstmt.setString	(19, rec.getCLastPmtMemo());
			pstmt.setInt	(20, rec.getLLastPmtAmount());
			pstmt.setString	(21, rec.getCLastPmtDate());
			pstmt.setShort	(22, rec.getIARsentBox());
			pstmt.setInt	(23, rec.getLFinTotalCharges());
			pstmt.setInt	(24, rec.getLLastFinanCharge());
			pstmt.setInt	(25, rec.getLLastReceiptNo());
			pstmt.setShort	(26, (short)FILE_VERSION);
			pstmt.setString	(27, rec.getCFinMiscNotes());
			pstmt.setString	(28, rec.getCPriceListTable());
			pstmt.setString	(29, rec.getCPackageTable());
			pstmt.setString	(30, rec.getCFamilyPreviouslyServed());
			pstmt.setString	(31, rec.getCPreviousFhUsed());
			pstmt.setString	(32, rec.getCAdvertisingSource());
			pstmt.setInt	(33, rec.getId());
			pstmt.setLong(34, rec.getSalesDescCDID());
			pstmt.setString	(35, rec.getFinChargePreviousCalc());
			pstmt.setInt	(36, rec.getPreviousFinanceCharge());
			
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFinancialSummaryPeer.Insert",e);
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
			pstmt = connection.prepareStatement("DELETE FROM financialdata WHERE VitalsMasterKey=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFinancialSummaryPeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return "SELECT 	* from financialdata  WHERE VitalsMasterKey = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		//AppLog.trace("DbFinancialSummaryPeer.getUpdateSql");
		try {
			DbFinancialSummary rec=(DbFinancialSummary)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE financialdata SET "+
						SALETYPE +"=?,"+
						CALCFINCHARGE +"=?,"+
						FINCHARGERATE +"=?,"+
						SALEDATE +"=?,"+
						DUEDATE +"=?,"+
						DATEINVOICED +"=?,"+
						AMOUNTFINANCED +"=?,"+
						INTERESTTOTAL +"=?,"+
						TERM +"=?,"+
						MONTHLYPMTAMOUNT +"=?,"+
						LASTFINANCEDPMT +"=?,"+
						FIRSTPMTDUEDATE +"=?,"+
						TOTALPAID +"=?,"+
					//	FIRSTFINCHRGCALC +"=?,"+
						FINCHARGESCHEDULE +"=?,"+
						FINCHARGELASTCALC +"=?,"+
						EAGLEAPPLNO +"=?,"+
						EAGLEAUTHCODE +"=?,"+
						LASTPMTRESP +"=?,"+
						LASTPMTMEMO +"=?,"+
						LASTPMTAMOUNT +"=?,"+
						LASTPMTDATE +"=?,"+
						SENTTOACCOUNTING +"=?,"+
						TOTALCHARGES +"=?,"+
						LASTFINCHARGE +"=?,"+
						LASTRECEIPTNO +"=?,"+
						FILEVERSION +"=?,"+
						NOTES +"=?,"+
						PRICELISTTABLE +"=?,"+
						PKGLISTTABLE +"=?,"+
						FAMILYUSEDBEFORE +"=?,"+
						FNRLHOMEPREVUSED +"=?,"+
						ADVSOURCE +"=?,"+
						SALESDESCCDID +"=?, "+
						FINCHARGEPREVIOUSCALC +"=?,"+
						PREVIOUSFINANCECHARGE +"=? "+
						"WHERE "+CASE_ID+" = ?"
			);		
			pstmt.setString	(1, rec.getCFinSaleType());
			pstmt.setShort	(2, rec.getIFinServiceChargesBox());
			pstmt.setFloat	(3, rec.getFFinServiceChargeRate());
			pstmt.setString	(4, rec.getCFinSaleDate());
			pstmt.setString	(5, rec.getCFinDueDate());
			pstmt.setString	(6, rec.getCFinDateInvoiceSent());
			pstmt.setInt	(7, rec.getLAmtFinanced());
			pstmt.setInt	(8, rec.getLInterest());
			pstmt.setShort	(9, rec.getSTerm());
			pstmt.setInt	(10, rec.getLPaymentAmt());
			pstmt.setInt	(11, rec.getLLastFinancedPmtAmt());
			pstmt.setString	(12, rec.getCFirstPmtDueDate());
			pstmt.setInt	(13, rec.getLTotalPaidToDate());
			pstmt.setString	(14, rec.getFCscheduleDate());
			pstmt.setString	(15, rec.getFClastRunDate());
			pstmt.setString	(16, rec.getCApplNo());
			pstmt.setString	(17, rec.getCAuthCode());
			pstmt.setString	(18, rec.getCLastPmtResp());
			pstmt.setString	(19, rec.getCLastPmtMemo());
			pstmt.setInt	(20, rec.getLLastPmtAmount());
			pstmt.setString	(21, rec.getCLastPmtDate());
			pstmt.setShort	(22, rec.getIARsentBox());
			pstmt.setInt	(23, rec.getLFinTotalCharges());
			pstmt.setInt	(24, rec.getLLastFinanCharge());
			pstmt.setInt	(25, rec.getLLastReceiptNo());
			pstmt.setShort	(26, (short)FILE_VERSION);
			pstmt.setString	(27, rec.getCFinMiscNotes());
			pstmt.setString	(28, rec.getCPriceListTable());
			pstmt.setString	(29, rec.getCPackageTable());
			pstmt.setString	(30, rec.getCFamilyPreviouslyServed());
			pstmt.setString	(31, rec.getCPreviousFhUsed());
			pstmt.setString	(32, rec.getCAdvertisingSource());
			pstmt.setLong(33, rec.getSalesDescCDID());
			pstmt.setString	(34, rec.getFinChargePreviousCalc());
			pstmt.setInt	(35, rec.getPreviousFinanceCharge());
			pstmt.setInt	(36,rec.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbFinancialSummaryPeer.Update",e);
		}
}
}
