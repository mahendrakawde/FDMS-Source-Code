package com.aldorsolutions.webfdms.beans;

import java.sql.Statement;

/***
 * DbCemAtneedPeer supplies constants and SQL for Persistent Class.
 * Creation date: (1/21/2002 4:13:09 PM)
 * @author:
 * Cloned and modified by JO - QPRIME - SOW: F030601A Cemetery Management System
 * 
 */
 
 
public class DbCemAtneedPeer extends com.aldorsolutions.webfdms.database.DatabasePeer {

	static public final String IDENTITY    = "Cem_id";
	static public final String PLOTTYPE 	= "Cem_plottype";
	static public final String SECTION  	= "Cem_section";
	static public final String BLOCK	      = "Cem_block";
	static public final String LOTTIER   	= "Cem_lot_tier";
	static public final String GRAVEROW 	= "Cem_grave_row";
	static public final String CEMAMOUNT = "Cem_Amount";
	static public final String CEMANBUYERAPTNO = "Cem_ANBuyerAptNo";  
	static public final String CEMANBUYERCITY = "Cem_ANBuyerCity";  
	static public final String CEMANBUYERMIDNAME =  "Cem_ANBuyerMidName";  
	static public final String CEMANBUYERTITLE = "Cem_ANBuyerTitle";
	static public final String CEMANBUYERPHONE = "Cem_ANBuyerPhone";
	static public final String CEMANBUYERSTATE = "Cem_ANBuyerState";
	static public final String CEMANBUYERSTREET = "Cem_ANBuyerStreet";
	static public final String CEMANBUYERFIRSTNAME = "Cem_ANBuyerFirstName";
	static public final String CEMANBUYERLASTNAME = "Cem_ANBuyerLastName";
	static public final String CEMANBUYERZIP = "Cem_ANBuyerZip";
	static public final String CEMMAPID = "Cem_MapID";
	static public final String CEMRECORD = "Cem_Record";
	static public final String CEMCONTRACTDATE = "Cem_ContractDate" ;
	static public final String CEMMISCDESC = "Cem_MiscDesc";
	static public final String CEMMISCAMOUNT = "Cem_MiscAmount";
   
  
	/**
	 * getInsertSql method comment.
	 */
	protected java.sql.PreparedStatement getInsertSql(
			com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
			throws com.aldorsolutions.webfdms.database.PersistenceException {
	
		//AppLog.trace("DbCemAtneedPeer.getInsertSql");
		java.sql.Connection connection = null;
		try {                    
         		           
			DbCemAtneed an=(DbCemAtneed)p;                                                
                        
			connection = t.getConnection();
			java.sql.PreparedStatement pstmt = connection.prepareStatement(
			"INSERT INTO cem_atneed ("+
						PLOTTYPE+","+
						SECTION+","+
						BLOCK+","+
						LOTTIER+","+
						GRAVEROW +","+
						CEMAMOUNT + ","+
			            CEMANBUYERAPTNO + ","+
			            CEMANBUYERCITY +","+
			            CEMANBUYERMIDNAME + ","+
			            CEMANBUYERTITLE + ","+
			            CEMANBUYERPHONE + ","+
			            CEMANBUYERSTATE + ","+
			            CEMANBUYERSTREET +","+
			            CEMANBUYERFIRSTNAME +","+
			            CEMANBUYERLASTNAME +","+
			            CEMANBUYERZIP +","+ 
			            CEMMAPID  +","+
			            CEMRECORD + ","+
			            CEMCONTRACTDATE + ","+
			            CEMMISCDESC + ","+
			            CEMMISCAMOUNT + 
											
						") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
			);
			
			pstmt.setString(1, an.getCem_plottype());
			pstmt.setString(2, an.getCem_section());
			pstmt.setString(3, an.getCem_block());
			pstmt.setString(4, an.getCem_lot_tier());
			pstmt.setString(5, an.getCem_grave_row());
			pstmt.setInt(6, an.getCem_Amount());
			pstmt.setString(7, an.getCem_ANBuyerAptNo());
			pstmt.setString(8, an.getCem_ANBuyerCity());
			pstmt.setString(9, an.getCem_ANBuyerMidName());
			pstmt.setString(10, an.getCem_ANBuyerTitle());
			pstmt.setString(11, an.getCem_ANBuyerPhone());
			pstmt.setString(12, an.getCem_ANBuyerState());
			pstmt.setString(13, an.getCem_ANBuyerStreet());
			pstmt.setString(14, an.getCem_ANBuyerFirstName());
			pstmt.setString(15, an.getCem_ANBuyerLastName());
			pstmt.setString(16, an.getCem_ANBuyerZip());
			pstmt.setString(17, an.getCem_MapID());
			pstmt.setString(18, an.getCem_Record());
			pstmt.setString(19, an.getCem_ContractDate());
			pstmt.setString(20, an.getCem_MiscDesc());
			pstmt.setInt(21, an.getCem_MiscAmount());
						
			return pstmt;
		}
		catch (java.sql.SQLException e){
			throw new com.aldorsolutions.webfdms.database.PersistenceException("DbCemAtneedPeer.Insert:"+e.getMessage(),e);
		}
}
/**
 * getRemoveSql method comment.
 */
protected java.sql.PreparedStatement getRemoveSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
	throw new com.aldorsolutions.webfdms.database.PersistenceException("DbCemAtneedPeer.Remove Not Implimented");
}
/**
 * getRestoreSql method comment.
 */
protected String getRestoreSql(com.aldorsolutions.webfdms.database.Persistent p) {
		return
			"SELECT 	"+ 	IDENTITY +","+
							PLOTTYPE+","+
							SECTION+","+
							BLOCK+","+
							LOTTIER+","+
							GRAVEROW+","+
							CEMAMOUNT + ","+
				            CEMANBUYERAPTNO + ","+
				            CEMANBUYERCITY +","+
				            CEMANBUYERMIDNAME + ","+
				            CEMANBUYERTITLE + ","+
				            CEMANBUYERPHONE + ","+
				            CEMANBUYERSTATE + ","+
				            CEMANBUYERSTREET +","+
				            CEMANBUYERFIRSTNAME +","+
				            CEMANBUYERLASTNAME +","+
				            CEMANBUYERZIP +","+ 
				            CEMMAPID  +","+
				            CEMRECORD + ","+
				            CEMCONTRACTDATE + ","+
				            CEMMISCDESC + ","+
				            CEMMISCAMOUNT + " " + 			
						"from cem_atneed WHERE Cem_id = "											
		+ p.getId();
}
/**
 * getUpdateSql method comment.
 */
 
  protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
  	//AppLog.trace("DbCemAtneedPeer.getUpdateSql");
  	java.sql.Connection connection = null;
  	try {
  		DbCemAtneed an=(DbCemAtneed)p;
  		connection = t.getConnection();
  		java.sql.PreparedStatement pstmt = connection.prepareStatement(
  		"UPDATE cem_atneed SET "+
  					PLOTTYPE+"=?,"+
  					SECTION+"=?,"+
  					BLOCK+"=?,"+
  					LOTTIER+"=?,"+
  					GRAVEROW+"=?,"+
  					CEMAMOUNT + "=?,"+
  					CEMANBUYERAPTNO + "=?,"+
  					CEMANBUYERCITY +"=?,"+
  					CEMANBUYERMIDNAME + "=?,"+
  					CEMANBUYERTITLE + "=?,"+
  					CEMANBUYERPHONE + "=?,"+
  					CEMANBUYERSTATE + "=?,"+
  					CEMANBUYERSTREET +"=?,"+
  					CEMANBUYERFIRSTNAME +"=?,"+
  					CEMANBUYERLASTNAME +"=?,"+
  					CEMANBUYERZIP +"=?,"+ 
  					CEMMAPID  +"=?,"+
  					CEMRECORD + "=?,"+
  					CEMCONTRACTDATE + "=?,"+
  					CEMMISCDESC + "=?,"+
  					CEMMISCAMOUNT + "=? " + 			  					
  					"WHERE Cem_id = ?"
  		);
  		pstmt.setString(1, an.getCem_plottype());
		pstmt.setString(2, an.getCem_section());
		pstmt.setString(3, an.getCem_block());
		pstmt.setString(4, an.getCem_lot_tier());
		pstmt.setString(5, an.getCem_grave_row());
		pstmt.setInt(6, an.getCem_Amount());
		pstmt.setString(7, an.getCem_ANBuyerAptNo());
		pstmt.setString(8, an.getCem_ANBuyerCity());
		pstmt.setString(9, an.getCem_ANBuyerMidName());
		pstmt.setString(10, an.getCem_ANBuyerTitle());
		pstmt.setString(11, an.getCem_ANBuyerPhone());
		pstmt.setString(12, an.getCem_ANBuyerState());
		pstmt.setString(13, an.getCem_ANBuyerStreet());
		pstmt.setString(14, an.getCem_ANBuyerFirstName());
		pstmt.setString(15, an.getCem_ANBuyerLastName());
		pstmt.setString(16, an.getCem_ANBuyerZip());
		pstmt.setString(17, an.getCem_MapID());
		pstmt.setString(18, an.getCem_Record());
		pstmt.setString(19, an.getCem_ContractDate());
		pstmt.setString(20, an.getCem_MiscDesc());
		pstmt.setInt(21, an.getCem_MiscAmount());  		
  		pstmt.setInt(22,an.getId());                        
                       
  		return pstmt;
  	}
  	catch (java.sql.SQLException e){
  		throw new com.aldorsolutions.webfdms.database.PersistenceException("DbCemAtneedPeer.Update:"+e.getMessage(),e);
  	}
  }
}



// QPRIME (SJD) - CODE - Changed Start 6/26/2006 - F030602W
// QPRIME (SJD) - F030602W - Old Code
// QPRIME (SJD) - CODE - Changed End 6/26/2006 - F030602W
// ORIGINAL CODE - protected java.sql.PreparedStatement getInsertSql(
// ORIGINAL CODE - 	com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p)
// ORIGINAL CODE - 	throws com.aldorsolutions.webfdms.database.PersistenceException {
// ORIGINAL CODE -
// ORIGINAL CODE - //AppLog.trace("DbPreneedPeer.getInsertSql");
// ORIGINAL CODE - java.sql.Connection connection = null;
// ORIGINAL CODE - try {
// ORIGINAL CODE -
// ORIGINAL CODE - 	DbPreneed an=(DbPreneed)p;
// ORIGINAL CODE -
// ORIGINAL CODE - 	connection = t.getConnection();
// ORIGINAL CODE - 	java.sql.PreparedStatement pstmt = connection.prepareStatement(
// ORIGINAL CODE - 	"INSERT INTO VitalStats ("+
// ORIGINAL CODE - 				BUYERFIRST+","+
// ORIGINAL CODE - 				BUYERMIDDLE+","+
// ORIGINAL CODE - 				BUYERLAST+","+
// ORIGINAL CODE - 				BUYERSTREET+","+
// ORIGINAL CODE - 				BUYERCITY+","+
// ORIGINAL CODE - 				BUYERSTATE+","+
// ORIGINAL CODE - 				BUYERZIP+","+
// ORIGINAL CODE - 				COBUYER+","+
// ORIGINAL CODE - 				BUYERSSNO+","+
// ORIGINAL CODE - 				COUNSELOR+","+
// ORIGINAL CODE - 				PNSTATUS+","+
// ORIGINAL CODE - 				FUNDTYPE+","+
// ORIGINAL CODE - 				DEPOSITORY+","+
// ORIGINAL CODE - 				CONTRACTDATE+","+
// ORIGINAL CODE - 				MATURITYDATE+","+
// ORIGINAL CODE - 				FUNDACCTNO+","+
// ORIGINAL CODE - 				INTERESTRATE+","+
// ORIGINAL CODE - 				FUNDSACCESS+","+
// ORIGINAL CODE - 				CONTRACTAMT+","+
// ORIGINAL CODE - 				PAIDYTD+","+
// ORIGINAL CODE - 				PAIDTOTAL+","+
// ORIGINAL CODE - 				YTDINT+","+
// ORIGINAL CODE - 				TOTALINT+","+
// ORIGINAL CODE - 				MGMTFEE+","+
// ORIGINAL CODE - 				COMMISSION+","+
// ORIGINAL CODE - 				FACEVALUE+","+
// ORIGINAL CODE - 				LASTPMTAMT+","+
// ORIGINAL CODE - 				LASTPMTDATE+","+
// ORIGINAL CODE - 				SOURCE+","+
// ORIGINAL CODE - 				CASKETNAME+","+
// ORIGINAL CODE - 				VAULTNAME+","+
// ORIGINAL CODE - 				URNNAME+","+
// ORIGINAL CODE - 				SERVICETYPE+","+
// ORIGINAL CODE - 				SERVICEAMT+","+
// ORIGINAL CODE - 				CASKETAMT+","+
// ORIGINAL CODE - 				VAULTAMT+","+
// ORIGINAL CODE - 				URNAMT+","+
// ORIGINAL CODE - 				GSTAMT+","+
// ORIGINAL CODE - 				OTHERAMT+","+
// ORIGINAL CODE - 				ARRNGDATE+","+
// ORIGINAL CODE - 				BENESAME+","+
// ORIGINAL CODE - 				BUYERTITLE+","+
// ORIGINAL CODE - 				BUYERAPT+","+
// ORIGINAL CODE -                   COMMENTS+","+
// ORIGINAL CODE - 				BUYERPHONE+" "+
// ORIGINAL CODE - 				") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
// ORIGINAL CODE - 	);
// ORIGINAL CODE - 	pstmt.setString(1, an.getBuyerFirst());
// ORIGINAL CODE - 	pstmt.setString(2, an.getBuyerMiddle());
// ORIGINAL CODE - 	pstmt.setString(3, an.getBuyerLast());
// ORIGINAL CODE - 	pstmt.setString(4, an.getBuyerStreet());
// ORIGINAL CODE - 	pstmt.setString(5, an.getBuyerCity());
// ORIGINAL CODE - 	pstmt.setString(6, an.getBuyerState());
// ORIGINAL CODE - 	pstmt.setString(7, an.getBuyerZip());
// ORIGINAL CODE - 	pstmt.setString(8, an.getCobuyer());
// ORIGINAL CODE - 	pstmt.setString(9, an.getBuyerSsNo());
// ORIGINAL CODE - 	pstmt.setString(10, an.getCounselor());
// ORIGINAL CODE - 	pstmt.setString(11, an.getStatus());
// ORIGINAL CODE - 	pstmt.setString(12, an.getFundType());
// ORIGINAL CODE - 	pstmt.setString(13, an.getDepository());
// ORIGINAL CODE - 	pstmt.setString(14, an.getContractDate());
// ORIGINAL CODE - 	pstmt.setString(15, an.getMaturityDate());
// ORIGINAL CODE - 	pstmt.setString(16, an.getFundAcctNo());
// ORIGINAL CODE - 	pstmt.setFloat(17, an.getInterestRate());
// ORIGINAL CODE - 	pstmt.setString(18, an.getAccessable());
// ORIGINAL CODE - 	pstmt.setInt(19, an.getContractAmt());
// ORIGINAL CODE - 	pstmt.setInt(20, an.getPaidYTD());
// ORIGINAL CODE - 	pstmt.setInt(21, an.getPaidTotal());
// ORIGINAL CODE - 	pstmt.setInt(22, an.getInterestYtd());
// ORIGINAL CODE - 	pstmt.setInt(23, an.getInterestTotal());
// ORIGINAL CODE - 	pstmt.setInt(24, an.getMgmtFee());
// ORIGINAL CODE - 	pstmt.setInt(25, an.getCommission());
// ORIGINAL CODE - 	pstmt.setInt(26, an.getFaceValue());
// ORIGINAL CODE - 	pstmt.setInt(27, an.getLastPmtAmt());
// ORIGINAL CODE - 	pstmt.setString(28, an.getLastPmtDate());
// ORIGINAL CODE - 	pstmt.setString(29, an.getSource());
// ORIGINAL CODE - 	pstmt.setString(30, an.getCasketName());
// ORIGINAL CODE - 	pstmt.setString(31, an.getVaultName());
// ORIGINAL CODE - 	pstmt.setString(32, an.getUrnName());
// ORIGINAL CODE - 	pstmt.setString(33, an.getServiceType());
// ORIGINAL CODE - 	pstmt.setInt(34, an.getServiceAmt());
// ORIGINAL CODE - 	pstmt.setInt(35, an.getCasketAmt());
// ORIGINAL CODE - 	pstmt.setInt(36, an.getVaultAmt());
// ORIGINAL CODE - 	pstmt.setInt(37, an.getUrnAmt());
// ORIGINAL CODE - 	pstmt.setInt(38, an.getGSTAmt());
// ORIGINAL CODE - 	pstmt.setInt(39, an.getOtherAmt());
// ORIGINAL CODE - 	pstmt.setString(40, an.getArrangeDate());
// ORIGINAL CODE - 	pstmt.setString(41, an.getBeneSameAsBuyer());
// ORIGINAL CODE - 	pstmt.setString(42, an.getBuyerTitle());
// ORIGINAL CODE - 	pstmt.setString(43, an.getBuyerAptNo());
// ORIGINAL CODE -       pstmt.setString(44, an.getComments());
// ORIGINAL CODE -       pstmt.setString(45, an.getBuyerPhone());
// ORIGINAL CODE - 	return pstmt;
// ORIGINAL CODE - }
// ORIGINAL CODE - catch (java.sql.SQLException e){
// ORIGINAL CODE - 	throw new com.aldorsolutions.webfdms.database.PersistenceException("DbPreneedPeer.Insert:"+e.getMessage(),e);
// ORIGINAL CODE - }


// ORIGINAL CODE - protected java.sql.PreparedStatement getUpdateSql(com.aldorsolutions.webfdms.database.DatabaseTransaction t, com.aldorsolutions.webfdms.database.Persistent p) throws com.aldorsolutions.webfdms.database.PersistenceException {
// ORIGINAL CODE - 	//AppLog.trace("DbPreneedPeer.getUpdateSql");
// ORIGINAL CODE - 	java.sql.Connection connection = null;
// ORIGINAL CODE - 	try {
// ORIGINAL CODE - 		DbPreneed an=(DbPreneed)p;
// ORIGINAL CODE - 		connection = t.getConnection();
// ORIGINAL CODE - 		java.sql.PreparedStatement pstmt = connection.prepareStatement(
// ORIGINAL CODE - 		"UPDATE VitalStats SET "+
// ORIGINAL CODE - 					BUYERFIRST+"=?,"+
// ORIGINAL CODE - 					BUYERMIDDLE+"=?,"+
// ORIGINAL CODE - 					BUYERLAST+"=?,"+
// ORIGINAL CODE - 					BUYERSTREET+"=?,"+
// ORIGINAL CODE - 					BUYERCITY+"=?,"+
// ORIGINAL CODE - 					BUYERSTATE+"=?,"+
// ORIGINAL CODE - 					BUYERZIP+"=?,"+
// ORIGINAL CODE - 					COBUYER+"=?,"+
// ORIGINAL CODE - 					BUYERSSNO+"=?,"+
// ORIGINAL CODE - 					COUNSELOR+"=?,"+
// ORIGINAL CODE - 					PNSTATUS+"=?,"+
// ORIGINAL CODE - 					FUNDTYPE+"=?,"+
// ORIGINAL CODE - 					DEPOSITORY+"=?,"+
// ORIGINAL CODE - 					CONTRACTDATE+"=?,"+
// ORIGINAL CODE - 					MATURITYDATE+"=?,"+
// ORIGINAL CODE - 					FUNDACCTNO+"=?,"+
// ORIGINAL CODE - 					INTERESTRATE+"=?,"+
// ORIGINAL CODE - 					FUNDSACCESS+"=?,"+
// ORIGINAL CODE - 					CONTRACTAMT+"=?,"+
// ORIGINAL CODE - 					PAIDYTD+"=?,"+
// ORIGINAL CODE - 					PAIDTOTAL+"=?,"+
// ORIGINAL CODE - 					YTDINT+"=?,"+
// ORIGINAL CODE - 					TOTALINT+"=?,"+
// ORIGINAL CODE - 					MGMTFEE+"=?,"+
// ORIGINAL CODE - 					COMMISSION+"=?,"+
// ORIGINAL CODE - 					FACEVALUE+"=?,"+
// ORIGINAL CODE - 					LASTPMTAMT+"=?,"+
// ORIGINAL CODE - 					LASTPMTDATE+"=?,"+
// ORIGINAL CODE - 					SOURCE+"=?,"+
// ORIGINAL CODE - 					CASKETNAME+"=?,"+
// ORIGINAL CODE - 					VAULTNAME+"=?,"+
// ORIGINAL CODE - 					URNNAME+"=?,"+
// ORIGINAL CODE - 					SERVICETYPE+"=?,"+
// ORIGINAL CODE - 					SERVICEAMT+"=?,"+
// ORIGINAL CODE - 					CASKETAMT+"=?,"+
// ORIGINAL CODE - 					VAULTAMT+"=?,"+
// ORIGINAL CODE - 					URNAMT+"=?,"+
// ORIGINAL CODE - 					GSTAMT+"=?,"+
// ORIGINAL CODE - 					OTHERAMT+"=?,"+
// ORIGINAL CODE - 					ARRNGDATE+"=?,"+
// ORIGINAL CODE - 					BENESAME+"=?,"+
// ORIGINAL CODE - 					BUYERTITLE+"=?,"+
// ORIGINAL CODE - 					BUYERAPT+"=?,"+
// ORIGINAL CODE - 					COMMENTS+"=?,"+
// ORIGINAL CODE - 					BUYERPHONE+"=? "+
// ORIGINAL CODE - 					"WHERE VitalsMasterKey = ?"
// ORIGINAL CODE - 		);
// ORIGINAL CODE - 		pstmt.setString(1, an.getBuyerFirst());
// ORIGINAL CODE - 		pstmt.setString(2, an.getBuyerMiddle());
// ORIGINAL CODE - 		pstmt.setString(3, an.getBuyerLast());
// ORIGINAL CODE - 		pstmt.setString(4, an.getBuyerStreet());
// ORIGINAL CODE - 		pstmt.setString(5, an.getBuyerCity());
// ORIGINAL CODE - 		pstmt.setString(6, an.getBuyerState());
// ORIGINAL CODE - 		pstmt.setString(7, an.getBuyerZip());
// ORIGINAL CODE - 		pstmt.setString(8, an.getCobuyer());
// ORIGINAL CODE - 		pstmt.setString(9, an.getBuyerSsNo());
// ORIGINAL CODE - 		pstmt.setString(10, an.getCounselor());
// ORIGINAL CODE - 		pstmt.setString(11, an.getStatus());
// ORIGINAL CODE - 		pstmt.setString(12, an.getFundType());
// ORIGINAL CODE - 		pstmt.setString(13, an.getDepository());
// ORIGINAL CODE - 		pstmt.setString(14, an.getContractDate());
// ORIGINAL CODE - 		pstmt.setString(15, an.getMaturityDate());
// ORIGINAL CODE - 		pstmt.setString(16, an.getFundAcctNo());
// ORIGINAL CODE - 		pstmt.setFloat(17, an.getInterestRate());
// ORIGINAL CODE - 		pstmt.setString(18, an.getAccessable());
// ORIGINAL CODE - 		pstmt.setInt(19, an.getContractAmt());
// ORIGINAL CODE - 		pstmt.setInt(20, an.getPaidYTD());
// ORIGINAL CODE - 		pstmt.setInt(21, an.getPaidTotal());
// ORIGINAL CODE - 		pstmt.setInt(22, an.getInterestYtd());
// ORIGINAL CODE - 		pstmt.setInt(23, an.getInterestTotal());
// ORIGINAL CODE - 		pstmt.setInt(24, an.getMgmtFee());
// ORIGINAL CODE - 		pstmt.setInt(25, an.getCommission());
// ORIGINAL CODE - 		pstmt.setInt(26, an.getFaceValue());
// ORIGINAL CODE - 		pstmt.setInt(27, an.getLastPmtAmt());
// ORIGINAL CODE - 		pstmt.setString(28, an.getLastPmtDate());
// ORIGINAL CODE - 		pstmt.setString(29, an.getSource());
// ORIGINAL CODE - 		pstmt.setString(30, an.getCasketName());
// ORIGINAL CODE - 		pstmt.setString(31, an.getVaultName());
// ORIGINAL CODE - 		pstmt.setString(32, an.getUrnName());
// ORIGINAL CODE - 		pstmt.setString(33, an.getServiceType());
// ORIGINAL CODE - 		pstmt.setInt(34, an.getServiceAmt());
// ORIGINAL CODE - 		pstmt.setInt(35, an.getCasketAmt());
// ORIGINAL CODE - 		pstmt.setInt(36, an.getVaultAmt());
// ORIGINAL CODE - 		pstmt.setInt(37, an.getUrnAmt());
// ORIGINAL CODE - 		pstmt.setInt(38, an.getGSTAmt());
// ORIGINAL CODE - 		pstmt.setInt(39, an.getOtherAmt());
// ORIGINAL CODE - 		pstmt.setString(40, an.getArrangeDate());
// ORIGINAL CODE - 		pstmt.setString(41, an.getBeneSameAsBuyer());
// ORIGINAL CODE - 		pstmt.setString(42, an.getBuyerTitle());
// ORIGINAL CODE - 		pstmt.setString(43, an.getBuyerAptNo());
// ORIGINAL CODE - 		pstmt.setString(44, an.getComments());
// ORIGINAL CODE - 		pstmt.setString(45, an.getBuyerPhone());
// ORIGINAL CODE - 		pstmt.setInt(46,an.getId());                        
// ORIGINAL CODE -                      
// ORIGINAL CODE - 		return pstmt;
// ORIGINAL CODE - 	}
// ORIGINAL CODE - 	catch (java.sql.SQLException e){
// ORIGINAL CODE - 		throw new com.aldorsolutions.webfdms.database.PersistenceException("DbPreneedPeer.Update:"+e.getMessage(),e);
// ORIGINAL CODE - 	}
// ORIGINAL CODE - }

