package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * DbPnContractsPeer supplies constants and SQL for Persistent Class.
 * Creation date: (2/03/2003 10:13:09 AM)
 * @author: 
 */
public class DbPnContractPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY    	= "ContractID";
	static public final String VITALSID		= "VitalsMasterKey";
	static public final String CONTRACTNO 	= "ContractNumber";
	static public final String CERTIFICATENUMBER = "CertificateNumber";
	static public final String CONTRACTTYPE	= "ContractType";
	static public final String INSTALLMENT	= "Installment";
	static public final String ANNUALSTMT	= "SendAnnualStmt";
	static public final String IRREVOCABLE  = "CertifiedIrrevocable";
	static public final String TAXPAYER		= "Taxpayer";
	static public final String SEND1099TO	= "Send1099To";
	static public final String BANKID		= "BankID";
	static public final String CONTRACTDATE	= "ContractDate";
	static public final String COMMRATE		= "CommissionRate";
	static public final String DISCOUNTRATE	= "DiscountRate";
	static public final String REFUND		= "Refund";
	static public final String PACKAGEID	= "PackageID";
	static public final String TOTALCHARGES	= "TotalCharges";
	static public final String DOWNPAYMENT	= "DownPayment";
	static public final String CONTRNOPMTS	= "ContractNumberOfPmts";
	static public final String DATEFIRSTPMT = "DateFirstPmtDue";
	static public final String INTERESTRATE = "InterestRate";
	static public final String TOTFINCHARGE = "TotalFinanceCharge";
	static public final String MONTHLYPMT	= "MonthlyPmtAmount";
	static public final String NOPMTSMADE	= "NumberPmtsMade";
	static public final String TOTPAIDTODATE= "TotalPaidToDate";
	static public final String LASTPMTDATE 	= "LastPmtDate";
	static public final String LASTPMTAMT	= "LastPmtAmount";
	static public final String LASTPMTCOMM 	= "LastPmtCommAmount";
	static public final String LASTPMTMEMO 	= "LastPmtMemo";
	static public final String LASTPMTFUNDBX= "LastPmtFundsForCode";
	static public final String LASTPMTCOMMBX= "LastPmtCommBox";
	static public final String FCONTACTNAME = "FulfillmentContactName";
	static public final String FCONTACTPHONE= "FulfillmentContactPhone";
	static public final String CANCELDATE 	= "CancellationDate";
	static public final String CANCELTYPE 	= "CancellationType";
	static public final String CANCELACK	= "CancellationAck";
	static public final String WITHDRAWALAMT= "PartialWithdrawalAmount";
	static public final String CANCELNAME 	= "CancellationFundsName";
	static public final String CANCELADDR 	= "CancellationFundsAddr";
	static public final String CANCELCITY 	= "CancellationFundsCity";
	static public final String CANCELSTATE	= "CancellationFundsState";
	static public final String CANCELZIP	= "CancellationFundsZip";
	static public final String NOTES		= "Notes";
	static public final String PRICELISTVER	= "PriceListTable";
	static public final String CUSTOMCONTRACTNUMBER = "CustomContractNumber";
	
	Logger logger = Logger.getLogger(DbPnContractPeer.class.getName());
	
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			logger.info("In " + this.getClass() + " getInsertSql() is been called");
			DbPnContract rec=(DbPnContract)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"INSERT INTO pncontracts ("+
				IDENTITY		+","+
				CONTRACTNO 		+","+
				CONTRACTTYPE 	+","+
				INSTALLMENT 	+","+
				IRREVOCABLE 	+","+
				TAXPAYER 		+","+
				SEND1099TO 		+","+
				BANKID	 		+","+
				CONTRACTDATE	+","+
				COMMRATE		+","+
				DISCOUNTRATE 	+","+
				REFUND 			+","+
				PACKAGEID	 	+","+
				TOTALCHARGES 	+","+
				DOWNPAYMENT		+","+
				CONTRNOPMTS		+","+
				DATEFIRSTPMT	+","+
				INTERESTRATE	+","+
				TOTFINCHARGE	+","+
				MONTHLYPMT		+","+
				NOPMTSMADE		+","+
				TOTPAIDTODATE	+","+
				LASTPMTDATE		+","+
				LASTPMTAMT		+","+
				LASTPMTCOMM		+","+
				LASTPMTMEMO		+","+
				LASTPMTFUNDBX	+","+
				LASTPMTCOMMBX	+","+
				FCONTACTNAME	+","+
				FCONTACTPHONE	+","+
				CANCELDATE		+","+
				CANCELTYPE		+","+
				WITHDRAWALAMT	+","+
				CANCELNAME		+","+
				CANCELADDR		+","+
				CANCELCITY		+","+
				CANCELSTATE		+","+
				CANCELZIP		+","+
				VITALSID		+","+
				NOTES			+","+
				ANNUALSTMT		+","+
				PRICELISTVER	+","+
				CANCELACK		+","+
				CERTIFICATENUMBER + ","+
				CUSTOMCONTRACTNUMBER + " "+
				") VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
			);		
			pstmt.setInt	(1, rec.getContractNumber());	
			pstmt.setInt	(2, rec.getContractType());
			pstmt.setString	(3, rec.getInstallment());
			pstmt.setString	(4, rec.getCertifiedIrrevocable());
			pstmt.setString	(5, rec.getTaxpayer());
			pstmt.setString	(6, rec.getSend1099To());
			pstmt.setInt	(7, rec.getBankID());
			if (rec.getContractDate()!=null){
				pstmt.setDate	(8, new java.sql.Date(rec.getContractDate().getTime()));
			}
			else { pstmt.setDate(8, null);}
			pstmt.setDouble	(9, rec.getCommissionRate());
			pstmt.setDouble	(10, rec.getDiscountRate());
			pstmt.setDouble	(11, rec.getRefundRate());
			pstmt.setInt	(12, rec.getPackageID());
			pstmt.setInt   	(13, rec.getTotalCharges());
			pstmt.setInt   	(14, rec.getDownpayment());
			pstmt.setInt   	(15, rec.getContractNumberPayments());
			if (rec.getDateFirstPmtDue()!=null){
				pstmt.setDate	(16, new java.sql.Date(rec.getDateFirstPmtDue().getTime())); 
			}
			else { pstmt.setDate(16, null);}
			pstmt.setDouble	(17, rec.getInterestRate());
			pstmt.setInt   	(18, rec.getTotalFinanceCharge());
			pstmt.setInt   	(19, rec.getMonthlyPmtAmount());
			pstmt.setInt   	(20, rec.getNumberPmtsMade());
			pstmt.setInt   	(21, rec.getTotalPaidToDate());
			if (rec.getLastPmtDate()!=null){
				pstmt.setDate	(22, new java.sql.Date(rec.getLastPmtDate().getTime())); 
			}
			else { pstmt.setDate(22, null);}
			pstmt.setInt   	(23, rec.getLastPmtAmount());
			pstmt.setInt   	(24, rec.getLastPmtCommAmount());
			pstmt.setString	(25, rec.getLastPmtMemo());
			pstmt.setString	(26, rec.getLastPmtFundsForCode());
			pstmt.setString	(27, rec.getLastPmtCommBox());
			pstmt.setString	(28, rec.getFulfillmentContactName());
			pstmt.setString	(29, rec.getFulfillmentContactPhone());
			if (rec.getCancellationDate()!=null){
				pstmt.setDate	(30, new java.sql.Date(rec.getCancellationDate().getTime())); 
			}
			else { pstmt.setDate(30, null);}
			pstmt.setString (31, rec.getCancellationType());
			pstmt.setInt   	(32, rec.getPartialWithdrawalAmount());
			pstmt.setString (33, rec.getCancellationFundsName());
			pstmt.setString (34, rec.getCancellationFundsAddr());
			pstmt.setString (35, rec.getCancellationFundsCity());
			pstmt.setString (36, rec.getCancellationFundsState());
			pstmt.setString (37, rec.getCancellationFundsZip());
			pstmt.setInt   	(38, rec.getVitalsID());
			pstmt.setString (39, rec.getNotes());
			pstmt.setString	(40, rec.getAnnualStmt());
			pstmt.setString	(41, rec.getPriceListVersion());
			pstmt.setString (42, rec.getCancellationAcknowledge());
			pstmt.setString (43, rec.getCertificateNumber());
			pstmt.setString (44, rec.getCustomContractNumber());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbPnContractPeer.Insert:"+e.getMessage(),e);
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
			pstmt = connection.prepareStatement("DELETE FROM  pncontracts WHERE ContractID=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbPnContractsPeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return 
		"SELECT * from pncontracts WHERE ContractID = "
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbPnContract rec=(DbPnContract)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE pncontracts SET "+
				CONTRACTNO 		+"=?,"+
				CONTRACTTYPE 	+"=?,"+
				INSTALLMENT 	+"=?,"+
				IRREVOCABLE 	+"=?,"+
				TAXPAYER 		+"=?,"+
				SEND1099TO 		+"=?,"+
				BANKID	 		+"=?,"+
				CONTRACTDATE	+"=?,"+
				COMMRATE		+"=?,"+
				DISCOUNTRATE 	+"=?,"+
				REFUND 			+"=?,"+
				PACKAGEID	 	+"=?,"+
				TOTALCHARGES 	+"=?,"+
				DOWNPAYMENT		+"=?,"+
				CONTRNOPMTS		+"=?,"+
				DATEFIRSTPMT	+"=?,"+
				INTERESTRATE	+"=?,"+
				TOTFINCHARGE	+"=?,"+
				MONTHLYPMT		+"=?,"+
				NOPMTSMADE		+"=?,"+
				TOTPAIDTODATE	+"=?,"+
				LASTPMTDATE		+"=?,"+
				LASTPMTAMT		+"=?,"+
				LASTPMTCOMM		+"=?,"+
				LASTPMTMEMO		+"=?,"+
				LASTPMTFUNDBX	+"=?,"+
				LASTPMTCOMMBX	+"=?,"+
				FCONTACTNAME	+"=?,"+
				FCONTACTPHONE	+"=?,"+
				CANCELDATE		+"=?,"+
				CANCELTYPE		+"=?,"+
				WITHDRAWALAMT	+"=?,"+
				CANCELNAME		+"=?,"+
				CANCELADDR		+"=?,"+
				CANCELCITY		+"=?,"+
				CANCELSTATE		+"=?,"+
				CANCELZIP		+"=?,"+
				VITALSID		+"=?,"+
				NOTES			+"=?,"+
				ANNUALSTMT		+"=?,"+
				PRICELISTVER	+"=?,"+
				CANCELACK		+"=?,"+
				CERTIFICATENUMBER +"=?,"+
				CUSTOMCONTRACTNUMBER + "=? "+
				" WHERE "+IDENTITY+" = ?"
			);		
			pstmt.setInt	(1, rec.getContractNumber());	
			pstmt.setInt	(2, rec.getContractType());
			pstmt.setString	(3, rec.getInstallment());
			pstmt.setString	(4, rec.getCertifiedIrrevocable());
			pstmt.setString	(5, rec.getTaxpayer());
			pstmt.setString	(6, rec.getSend1099To());
			pstmt.setInt	(7, rec.getBankID());
			if (rec.getContractDate()!=null){
				pstmt.setDate	(8, new java.sql.Date(rec.getContractDate().getTime()));
			}
			else { pstmt.setDate(8, null);}
			pstmt.setDouble	(9, rec.getCommissionRate());
			pstmt.setDouble	(10, rec.getDiscountRate());
			pstmt.setDouble	(11, rec.getRefundRate());
			pstmt.setInt	(12, rec.getPackageID());
			pstmt.setInt   	(13, rec.getTotalCharges());
			pstmt.setInt   	(14, rec.getDownpayment());
			pstmt.setInt   	(15, rec.getContractNumberPayments());
			if (rec.getDateFirstPmtDue()!=null){
				pstmt.setDate	(16, new java.sql.Date(rec.getDateFirstPmtDue().getTime())); 
			}
			else { pstmt.setDate(16, null);}
			pstmt.setDouble	(17, rec.getInterestRate());
			pstmt.setInt   	(18, rec.getTotalFinanceCharge());
			pstmt.setInt   	(19, rec.getMonthlyPmtAmount());
			pstmt.setInt   	(20, rec.getNumberPmtsMade());
			pstmt.setInt   	(21, rec.getTotalPaidToDate());
			if (rec.getLastPmtDate()!=null){
				pstmt.setDate	(22, new java.sql.Date(rec.getLastPmtDate().getTime())); 
			}
			else { pstmt.setDate(22, null);}
			pstmt.setInt   	(23, rec.getLastPmtAmount());
			pstmt.setInt   	(24, rec.getLastPmtCommAmount());
			pstmt.setString	(25, rec.getLastPmtMemo());
			pstmt.setString	(26, rec.getLastPmtFundsForCode());
			pstmt.setString	(27, rec.getLastPmtCommBox());
			pstmt.setString	(28, rec.getFulfillmentContactName());
			pstmt.setString	(29, rec.getFulfillmentContactPhone());
			if (rec.getCancellationDate()!=null){
				pstmt.setDate	(30, new java.sql.Date(rec.getCancellationDate().getTime())); 
			}
			else { pstmt.setDate(30, null);}
			pstmt.setString (31, rec.getCancellationType());
			pstmt.setInt   	(32, rec.getPartialWithdrawalAmount());
			pstmt.setString (33, rec.getCancellationFundsName());
			pstmt.setString (34, rec.getCancellationFundsAddr());
			pstmt.setString (35, rec.getCancellationFundsCity());
			pstmt.setString (36, rec.getCancellationFundsState());
			pstmt.setString (37, rec.getCancellationFundsZip());
			pstmt.setInt   	(38, rec.getVitalsID());
			pstmt.setString (39, rec.getNotes());
			pstmt.setString	(40, rec.getAnnualStmt());
			pstmt.setString	(41, rec.getPriceListVersion());
			pstmt.setString (42, rec.getCancellationAcknowledge());
			pstmt.setString (43, rec.getCertificateNumber());
			pstmt.setString (44, rec.getCustomContractNumber());
			pstmt.setInt   	(45, rec.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbApMasterPeer.Update:"+e.getMessage(),e);
		}
}
}
