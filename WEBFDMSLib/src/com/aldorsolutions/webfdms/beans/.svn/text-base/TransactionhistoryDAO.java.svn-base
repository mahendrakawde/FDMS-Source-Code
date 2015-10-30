package com.aldorsolutions.webfdms.beans;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.checkwriter.model.ApMasterDTO;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.util.DAO;


/**
 * @author David Rollins
 * Date: 
 * File: ApMasterDAO.java	
 * 
 */
public class TransactionhistoryDAO extends DAO {
    
	
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
	
    /** Creates a new instance of ApMasterDAO */
    public TransactionhistoryDAO(DbUserSession user) {
    	super(user);
    }

    /** Creates a new instance of ApMasterDAO */
    public TransactionhistoryDAO ( long companyID, long userId ) {
    	super(companyID, userId);
    }
    
    private Logger logger = Logger.getLogger(TransactionhistoryDAO.class.getName());
    
    public ArrayList <TransactionhistoryDTO> getTransactionhistory(int vitalsID) {
        ArrayList <TransactionhistoryDTO> versions = new ArrayList <TransactionhistoryDTO> ();
        TransactionhistoryDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
//            sql.append("select VitalsMasterKey,TranHistID,ChargeType,Description,AmountOfTran,ARacct," +
//            		"GLacct,TaxExemptAmount,TaxCode,DateOfTran,AccountingPeriod,SPFcode,ItemCategory," +
//            		"ReceiptNumber,Postedyn,OriginalPostingYN,DeleteTransaction,InventoryItemName," +
//            		"InvMasterKey,InvCostOfSale,PriceListID,PaymentMethod,PaymentComponent,UserInitials," +
//            		"ManualReceiptNo,RecordVersion,InterfaceSequenceNo,OrigChrgAmount,OrigChrgDescr,Exported," +
//            		"DatePaid,DepositBatchNumber,ClaimNumber,locationId,InvOnHandID," +
//            		"TaxAmount,ServiceDate,DatetimeOfTrans,Sequence from transactionhistory where VitalsMasterKey = ?");
            
          sql.append("select VitalsMasterKey,TranHistID,ChargeType,AmountOfTran," +
    		"TaxExemptAmount,TaxCode,DeleteTransaction," +
    		"TaxAmount,Sequence from transactionhistory where VitalsMasterKey = ?");
               
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setInt(1, vitalsID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new TransactionhistoryDTO();
                version.setVitalsMasterKey(rs.getInt(col++));
                version.setTranHistID(rs.getInt(col++));
                version.setChargeType(rs.getInt(col++));
                version.setAmountOfTran(rs.getInt(col++));
                version.setTaxExemptAmount(rs.getInt(col++));
                version.setTaxCode(rs.getString(col++));
                version.setDeleteTransaction(rs.getString(col++));  
                version.setTaxAmount(rs.getInt(col++));
                version.setSequence(rs.getInt(col++));  
                
                versions.add(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApMaster() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApMaster() : ", e);
        } finally {
            closeConnection();
        }
        
        return versions;
    }
    
    public ArrayList <TransactionhistoryDTO> getTransactionhistoryChargeType(int vitalsID, int chargeType) {
        ArrayList <TransactionhistoryDTO> versions = new ArrayList <TransactionhistoryDTO> ();
        TransactionhistoryDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
//            sql.append("select VitalsMasterKey,TranHistID,ChargeType,Description,AmountOfTran,ARacct," +
//            		"GLacct,TaxExemptAmount,TaxCode,DateOfTran,AccountingPeriod,SPFcode,ItemCategory," +
//            		"ReceiptNumber,Postedyn,OriginalPostingYN,DeleteTransaction,InventoryItemName," +
//            		"InvMasterKey,InvCostOfSale,PriceListID,PaymentMethod,PaymentComponent,UserInitials," +
//            		"ManualReceiptNo,RecordVersion,InterfaceSequenceNo,OrigChrgAmount,OrigChrgDescr,Exported," +
//            		"DatePaid,DepositBatchNumber,ClaimNumber,locationId,InvOnHandID," +
//            		"TaxAmount,ServiceDate,DatetimeOfTrans,Sequence from transactionhistory where VitalsMasterKey = ?");
            
          sql.append("select VitalsMasterKey,TranHistID,ChargeType,AmountOfTran,DateOfTran " 
    		+ " from transactionhistory where VitalsMasterKey = ? and ChargeType = ? order by TranHistID desc");
               
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setInt(1, vitalsID);
            statement.setInt(2, chargeType); 
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new TransactionhistoryDTO();
                version.setVitalsMasterKey(rs.getInt(col++));
                version.setTranHistID(rs.getInt(col++));
                version.setChargeType(rs.getInt(col++));
                version.setAmountOfTran(rs.getInt(col++));
                version.setDateOfTran(rs.getDate(col++));
                versions.add(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApMaster() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApMaster() : ", e);
        } finally {
            closeConnection();
        }
        
        return versions;
    }   
    
    
    public TransactionhistoryDTO getPostedDate(int vitalsID) {
       
        TransactionhistoryDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            
          sql.append("select min(DateOfTran) from transactionhistory where VitalsMasterKey = ? and OriginalPostingYN = 'Y' and SPFcode = 'S'" );
    		
               
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setInt(1, vitalsID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 1;
                version = new TransactionhistoryDTO();
                version.setDateOfTran(rs.getDate(col++));
                
                
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApMaster() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApMaster() : ", e);
        } finally {
            closeConnection();
        }
        
        return version;
    }
    
    public boolean updatePostedDate(int vitalsID, Date newDate ) {
        boolean updated = false;
        TransactionhistoryDTO version = null;
        try {
        	
        	
        	
        	StringBuilder sql = new StringBuilder();
            sql.append("update transactionhistory set DateOfTran = ? where VitalsMasterKey = ? and OriginalPostingYN = 'Y'" );
            
            int col = 0;
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setDate(++col, newDate);
            statement.setInt(++col, vitalsID);
            statement.executeUpdate();
            
            
            
            //updateAudit(company, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updatePostedDate() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updatePostedDate() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    public boolean updateInvhistoryTrnasactionDate(int vitalsID, String newDate ) {
        boolean updated = false;
        TransactionhistoryDTO version = null;
        try {
        	
        	
        	
        	StringBuilder sql = new StringBuilder();
            sql.append("UPDATE invhistory, transactionhistory SET " 
            		+ "invhistory.TransactionDate=?" 
            		+ "WHERE invhistory.VitalsMasterKey = ? " 
            		+ "	AND transactionhistory.VitalsMasterKey = invhistory.VitalsMasterKey " 
            		+ "	AND transactionhistory.OriginalPostingYN = 'Y' "
            		+ "	AND invhistory.MasterID=transactionhistory.InvMasterKey;" );
            
            int col = 0;
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setString(++col, newDate);
            statement.setInt(++col, vitalsID);
            statement.executeUpdate();
            
            
            
            //updateAudit(company, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateInvhistoryTrnasactionDate() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateInvhistoryTrnasactionDate() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }
    
    
    public int getTotalCharge(int vitalsID){
    	int total = 0;
    	
    	try {
    		 StringBuffer sql = new StringBuffer();

         sql.append("select sum("+AMOUNT +") from transactionhistory where "+VITALSID+"= ? and (chargetype <> 0 and chargetype <> 9000) and DeleteTransaction <> 'Y'");
              
           makeConnection(this.dbLookup);
           statement = conn.prepareStatement(sql.toString());
           statement.setInt(1, vitalsID);
           
           rs = statement.executeQuery();
           if (rs.next()) {
           		int col = 1;
           		total = rs.getInt(col++);
           }	
    		
    	} catch (SQLException e) {
            logger.error("SQLException in getApMaster() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApMaster() : ", e);
        } finally {
            closeConnection();
        }
    	return total;
    }
    
    public int getTotalPayment(int vitalsID){
    	int total = 0;
    	
    	try {
    		 StringBuffer sql = new StringBuffer();

         sql.append("select sum("+AMOUNT +") from transactionhistory where "+VITALSID+"= ? and (chargetype = 0 or chargetype = 9000) and SPFcode = 'P' and DeleteTransaction <> 'Y'");
              
           makeConnection(this.dbLookup);
           statement = conn.prepareStatement(sql.toString());
           statement.setInt(1, vitalsID);
           
           rs = statement.executeQuery();
           if (rs.next()) {
           		int col = 1;
           		total = rs.getInt(col++);
           }	
    		
    	} catch (SQLException e) {
            logger.error("SQLException in getApMaster() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApMaster() : ", e);
        } finally {
            closeConnection();
        }
    	return total;
    }
}
