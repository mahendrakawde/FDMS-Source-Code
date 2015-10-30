package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.database.DatabasePeer;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;




/**
 * Insert the type's description here.
 * Creation date: (11/14/2001 4:13:09 PM)
 * @author: 
 */
public class DbXmlReportPeer extends DatabasePeer {
	private Logger logger = Logger.getLogger(DbXmlReportPeer.class.getName());
	static public final String PAYMETHOD   	= "PaymentMethod";
	static public final String IDENTITY    	= "RecordNumber";
	static public final String VITALSID 	= "VitalsID";
	static public final String HONORIFIC 	= "Honorific";
	static public final String FIRSTNAME 	= "FirstName";
	static public final String LASTNAME		= "LastName";
	static public final String STREET1		= "StreetAddr1";
	static public final String STREET2		= "StreetAddr2";
	static public final String CITY			= "City";
	static public final String STATE		= "State";
	static public final String ZIP   		= "Zip";
	static public final String HOMEPHONE	= "HomePhone";
	static public final String SSNO			= "SocialSecNo";
	static public final String RELATION		= "Relation";
	static public final String CONTRACTSIGNER = "ContractSignerYN";
	static public final String MODIFIEDFLAG = "ModifiedFlag";
	static public final String LANGUAGE 	= "Language";
	static public final String CASHSALE 	= "CashSale";
	static public final String REFUSED 		= "Refused";
	static public final String SENDINVOICE 	= "SendInvoice";
	static public final String SEQNUMBER 	= "SequenceNumber";
	static public final String DELETECODE 	= "DeleteCode";
	static public final String WORKPHONE 	= "WorkPhone";
	static public final String FILEVERSION	= "FileVersion";
	static public final String COUNTY 		= "County";
	static public final String STREET3 		= "Street3";
	static public final String STREET4 		= "Street4";
	static public final String DATEMODIFIED = "DateModified";
	static public final String TIMEMODIFIED = "TimeModified";
	static public final String EMAILADDR 	= "EmailAddress";
	static public final String TAXCODE		= "TaxCode";
	static public final String DESCRIPTION	= "Description";
	static public final String AMOUNT		= "AmountOfTran";
	static public final String DATEOFTRAN	= "DateOfTran";
	static public final String POSTEDYN 	= "PostedYn";
	static public final String TRANHISTID 	= "TranHistID";
	static public final String INVMASTERKEY	= "InvMasterKey";
	
/**
 * getInsertSql method comment.
 */
protected java.sql.PreparedStatement getInsertSql(DatabaseTransaction t, Persistent p) throws PersistenceException {

	java.sql.Connection connection = null;
	java.sql.PreparedStatement pstmt=null;
	try {
		logger.info("In " + this.getClass() + " getInsertSql() is been called");
		DbBillto billto=(DbBillto)p;
		connection = t.getConnection();
		pstmt = connection.prepareStatement(
		"INSERT INTO billtoparties ("+
					IDENTITY +","+
					VITALSID +","+
					HONORIFIC +","+
					FIRSTNAME +","+
					LASTNAME +","+
					STREET1 +","+
					STREET2 +","+
					CITY +","+
					STATE +","+
					ZIP +","+
					HOMEPHONE +","+
					SSNO +","+
					RELATION +","+
					CONTRACTSIGNER +","+
					MODIFIEDFLAG +","+
					LANGUAGE +","+
					CASHSALE +","+
					REFUSED +","+
					SENDINVOICE +","+
					SEQNUMBER +","+
					DELETECODE +","+
					WORKPHONE +","+
					FILEVERSION +","+
					COUNTY +","+
					STREET3 +","+
					STREET4 +","+
					EMAILADDR +" "+
					") VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt   (1, billto.getVitalsID());	
			pstmt.setString(2, billto.getHonorific());	
			pstmt.setString(3, billto.getFirstName());	
			pstmt.setString(4, billto.getLastName());	
			pstmt.setString(5, billto.getStreet1());	
			pstmt.setString(6, billto.getStreet2());	
			pstmt.setString(7, billto.getCity());	
			pstmt.setString(8, billto.getState());	
			pstmt.setString(9, billto.getZip());	
			pstmt.setString(10, billto.getHomePhone());	
			pstmt.setString(11, billto.getSocialSecurityNo());	
			pstmt.setString(12, billto.getRelation());	
			pstmt.setString(13, billto.getContractSigner());	
			pstmt.setString(14, billto.getModified());	
			pstmt.setString(15, billto.getLanguage());	
			pstmt.setString(16, billto.getCashSale());	
			pstmt.setString(17, billto.getRefused());	
			pstmt.setString(18, billto.getSendInvoice());	
			pstmt.setShort (19, billto.getSeqNo());	
			pstmt.setString(20, billto.getDeleteCode());	
			pstmt.setString(21, billto.getWorkPhone());	
			pstmt.setInt   (22, billto.getFileVersion());	
			pstmt.setString(23, billto.getCounty());	
			pstmt.setString(24, billto.getStreet3());	
			pstmt.setString(25, billto.getStreet4());	
			pstmt.setString(26, billto.getEmailAddress());	
		return pstmt;
	}
	catch (java.sql.SQLException e){
		throw new PersistenceException("DbbilltoPeer.Insert",e);
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
			pstmt = connection.prepareStatement("DELETE FROM billtoparties WHERE RecordNumber=?");
			pstmt.setInt(1,p.getId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new PersistenceException("DbBilltoPeer.Remove",e);
		}
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(Persistent p) {
	
		return "SELECT * from billtoparties WHERE RecordNumber = "
			+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
protected java.sql.PreparedStatement getUpdateSql(DatabaseTransaction t, Persistent p) throws PersistenceException {
		java.sql.Connection connection = null;
		try {
			DbXmlReport billto=(DbXmlReport)p;
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"UPDATE transactionhistory SET "+
						POSTEDYN +"=? "+
						"WHERE "+ TRANHISTID +" = ?"
			
			);		
			
			logger.debug("TransHistId : " + billto.getTranHistId());
	
			pstmt.setString   (1, billto.getPostedYn());
			pstmt.setInt      (2, billto.getTranHistId());
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new PersistenceException("DbBillToPeer.Update:"+e.getMessage(),e);
		}
}
}
