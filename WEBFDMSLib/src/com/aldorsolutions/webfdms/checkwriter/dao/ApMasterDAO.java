package com.aldorsolutions.webfdms.checkwriter.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.checkwriter.model.ApMasterDTO;
import com.aldorsolutions.webfdms.util.DAO;


/**
 * @author CJongs
 * Date: 030309
 * File: ApMasterDAO.java	
 * 
 */
public class ApMasterDAO extends DAO {
    
    /** Creates a new instance of ApMasterDAO */
    public ApMasterDAO(DbUserSession user) {
    	super(user);
    }

    /** Creates a new instance of ApMasterDAO */
    public ApMasterDAO ( long companyID, long userId ) {
    	super(companyID, userId);
    }
    
    private Logger logger = Logger.getLogger(ApMasterDAO.class.getName());
    public static int ACTIVE = 1;
    public static int DELETED = 0;
    
    public ArrayList <ApMasterDTO> getApMaster() {
        ArrayList <ApMasterDTO> versions = new ArrayList <ApMasterDTO> ();
        ApMasterDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select MasterID, InvoiceID, VendorID, LocationID, LocaleID, UserID, " +
            		"InvoiceNumber, InvoiceDate, DueDate, Memo, Handwritten, " +
            		"CheckToBePrinted, DiscountAmount, CheckNumber, Balance, CheckDate, " +
            		"InvoiceTotal, VoidedCode, ApprovalStatus, ApprovedBy, ApprovedTmstmp, " +
            		"AssociatedVitalsID, Check1099, Check1099Amount, VendorName, VoidedComment " +
            		"from apmaster order by MasterID desc");
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApMasterDTO();
                version.setMasterID(rs.getInt(col++));
                version.setInvoiceID(rs.getInt(col++));
                version.setVendorID(rs.getInt(col++));
                version.setLocationID(rs.getLong(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setUserID(rs.getLong(col++));
                version.setInvoiceNumber(rs.getString(col++));
                version.setInvoiceDate(rs.getDate(col++));
                version.setDueDate(rs.getDate(col++));
                version.setMemo(rs.getString(col++));
                version.setHandwritten(rs.getBoolean(col++));
                version.setCheckToBePrinted(rs.getBoolean(col++));
                version.setDiscountAmount(rs.getLong(col++));
                version.setCheckNumber(rs.getLong(col++));
                version.setBalance(rs.getLong(col++));
                version.setCheckDate(rs.getDate(col++));
                version.setInvoiceTotal(rs.getLong(col++));
                version.setVoidedCode(rs.getString(col++));
                version.setApprovalStatus(rs.getLong(col++));
                version.setApprovedBy(rs.getLong(col++));
                version.setApprovedTmstmp(rs.getTimestamp(col++));
                version.setVitalsID(rs.getInt(col++));
                version.setCheck1099(rs.getBoolean(col++));
                version.setCheck1099Amount(rs.getInt(col++));
                version.setVendorName(rs.getString(col++));
                version.setVoidedComment(rs.getString(col++));
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
    
    public ApMasterDTO getApMaster(long masterID) {
        ApMasterDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select MasterID, InvoiceID, VendorID, LocationID, LocaleID, UserID, " +
            		"InvoiceNumber, InvoiceDate, DueDate, Memo, Handwritten, " +
            		"CheckToBePrinted, DiscountAmount, CheckNumber, Balance, CheckDate, " +
            		"InvoiceTotal, VoidedCode, ApprovalStatus, ApprovedBy, ApprovedTmstmp, " +
            		"AssociatedVitalsID, Check1099, Check1099Amount, VendorName, VoidedComment " +
            		"from apmaster where MasterID = ?");
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, masterID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 1;
                version = new ApMasterDTO();
                version.setMasterID(rs.getInt(col++));
                version.setInvoiceID(rs.getInt(col++));
                version.setVendorID(rs.getInt(col++));
                version.setLocationID(rs.getLong(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setUserID(rs.getLong(col++));
                version.setInvoiceNumber(rs.getString(col++));
                version.setInvoiceDate(rs.getDate(col++));
                version.setDueDate(rs.getDate(col++));
                version.setMemo(rs.getString(col++));
                version.setHandwritten(rs.getBoolean(col++));
                version.setCheckToBePrinted(rs.getBoolean(col++));
                version.setDiscountAmount(rs.getLong(col++));
                version.setCheckNumber(rs.getLong(col++));
                version.setBalance(rs.getLong(col++));
                version.setCheckDate(rs.getDate(col++));
                version.setInvoiceTotal(rs.getLong(col++));
                version.setVoidedCode(rs.getString(col++));
                version.setApprovalStatus(rs.getLong(col++));
                version.setApprovedBy(rs.getLong(col++));
                version.setApprovedTmstmp(rs.getTimestamp(col++));
                version.setVitalsID(rs.getInt(col++));
                version.setCheck1099(rs.getBoolean(col++));
                version.setCheck1099Amount(rs.getInt(col++));
                version.setVendorName(rs.getString(col++));
                version.setVoidedComment(rs.getString(col++));
                return(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApMaster() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApMaster() : ", e);
        } finally {
            closeConnection();
        }
        
        return null;
    }
    
    
    public ApMasterDTO getApMasterByInvoiceID(long invoiceID) {
    ApMasterDTO version = null;
    
    try {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from apmaster where InvoiceID = ?");
        
        makeConnection(this.dbLookup);
        statement = conn.prepareStatement(sql.toString());
        statement.setLong(1, invoiceID);
        
        rs = statement.executeQuery();
        
        if (rs.next()) {
        	int col = 1;
            version = new ApMasterDTO();
            version.setMasterID(rs.getInt(col++));
            version.setInvoiceID(rs.getInt(col++));
            version.setVendorID(rs.getInt(col++));
            version.setLocationID(rs.getLong(col++));
            version.setLocaleID(rs.getLong(col++));
            version.setUserID(rs.getLong(col++));
            version.setInvoiceNumber(rs.getString(col++));
            version.setInvoiceDate(rs.getDate(col++));
            version.setDueDate(rs.getDate(col++));
            version.setMemo(rs.getString(col++));
            version.setHandwritten(rs.getBoolean(col++));
            version.setCheckToBePrinted(rs.getBoolean(col++));
            version.setDiscountAmount(rs.getLong(col++));
            version.setCheckNumber(rs.getLong(col++));
            version.setBalance(rs.getLong(col++));
            version.setCheckDate(rs.getDate(col++));
            version.setInvoiceTotal(rs.getLong(col++));
            version.setVoidedCode(rs.getString(col++));
            version.setApprovalStatus(rs.getLong(col++));
            version.setApprovedBy(rs.getLong(col++));
            version.setApprovedTmstmp(rs.getTimestamp(col++));
            version.setVitalsID(rs.getInt(col++));
            version.setCheck1099(rs.getBoolean(col++));
            version.setCheck1099Amount(rs.getInt(col++));
            version.setVendorName(rs.getString(col++));
            version.setVoidedComment(rs.getString(col++));
            return(version);
        }
        
    } catch (SQLException e) {
        logger.error("SQLException in getApMaster() : ", e);
    } catch (Exception e) {
        logger.error("Exception in getApMaster() : ", e);
    } finally {
        closeConnection();
    }
    
    return null;
}

    public ArrayList <ApMasterDTO> getApMaster(ArrayList <Long> locationIDs, ArrayList <Long> localeIDs, long checkNumber) {
        ArrayList <ApMasterDTO> versions = new ArrayList <ApMasterDTO> ();
        ApMasterDTO version = null;
        
        try {
        	
        	String sLocationIDs = "";
        	for ( Long locationID: locationIDs){
        		sLocationIDs = sLocationIDs+locationID+", ";
        	}
        	if (sLocationIDs.length() > 0) {
        		sLocationIDs = sLocationIDs.substring(0, sLocationIDs.length()-2);
        	}
        	
        	String sLocaleIDs = "";
        	for ( Long localeID: localeIDs){
        		sLocaleIDs = sLocaleIDs+localeID+", ";
        	}
        	if (sLocaleIDs.length()>0){
        		sLocaleIDs = sLocaleIDs.substring(0, sLocaleIDs.length()-2);
        	}
        	
        	
            StringBuffer sql = new StringBuffer();
            if (sLocationIDs.length() <= 0 && sLocaleIDs.length() <= 0) { //find check's record that related to checkNumber only
            	sql.append("select MasterID, InvoiceID, VendorID, LocationID, LocaleID, UserID, " +
                		"InvoiceNumber, InvoiceDate, DueDate, Memo, Handwritten, " +
                		"CheckToBePrinted, DiscountAmount, CheckNumber, Balance, CheckDate, " +
                		"InvoiceTotal, VoidedCode, ApprovalStatus, ApprovedBy, ApprovedTmstmp, " +
                		"AssociatedVitalsID, Check1099, Check1099Amount, VendorName, VoidedComment " +
                		"from apmaster where CheckNumber = "+checkNumber+" order by CheckDate DESC, CheckNumber DESC");
            } else if (sLocationIDs.length() > 0 && sLocaleIDs.length() <= 0){
            	sql.append("select MasterID, InvoiceID, VendorID, LocationID, LocaleID, UserID, " +
                		"InvoiceNumber, InvoiceDate, DueDate, Memo, Handwritten, " +
                		"CheckToBePrinted, DiscountAmount, CheckNumber, Balance, CheckDate, " +
                		"InvoiceTotal, VoidedCode, ApprovalStatus, ApprovedBy, ApprovedTmstmp, " +
                		"AssociatedVitalsID, Check1099, Check1099Amount, VendorName, VoidedComment " +
                		"from apmaster where CheckNumber = "+checkNumber+" and LocationID in ("+sLocationIDs+") order by CheckDate DESC, CheckNumber DESC");
            	
            } else if (sLocationIDs.length() <= 0 && sLocaleIDs.length() > 0){
            	sql.append("select MasterID, InvoiceID, VendorID, LocationID, LocaleID, UserID, " +
                		"InvoiceNumber, InvoiceDate, DueDate, Memo, Handwritten, " +
                		"CheckToBePrinted, DiscountAmount, CheckNumber, Balance, CheckDate, " +
                		"InvoiceTotal, VoidedCode, ApprovalStatus, ApprovedBy, ApprovedTmstmp, " +
                		"AssociatedVitalsID, Check1099, Check1099Amount, VendorName, VoidedComment " +
                		"from apmaster where CheckNumber = "+checkNumber+" and LocaleID in ("+sLocaleIDs+") order by CheckDate DESC, CheckNumber DESC");
            	
        	} else { 
            	sql.append("select MasterID, InvoiceID, VendorID, LocationID, LocaleID, UserID, " +
                		"InvoiceNumber, InvoiceDate, DueDate, Memo, Handwritten, " +
                		"CheckToBePrinted, DiscountAmount, CheckNumber, Balance, CheckDate, " +
                		"InvoiceTotal, VoidedCode, ApprovalStatus, ApprovedBy, ApprovedTmstmp, " +
                		"AssociatedVitalsID, Check1099, Check1099Amount, VendorName, VoidedComment " +
                		"from apmaster where CheckNumber = "+checkNumber+" and LocationID in ("+sLocationIDs+") and LocaleID in ("+sLocaleIDs+") order by CheckDate DESC, CheckNumber DESC");
            }
           
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApMasterDTO();
                version.setMasterID(rs.getInt(col++));
                version.setInvoiceID(rs.getInt(col++));
                version.setVendorID(rs.getInt(col++));
                version.setLocationID(rs.getLong(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setUserID(rs.getLong(col++));
                version.setInvoiceNumber(rs.getString(col++));
                version.setInvoiceDate(rs.getDate(col++));
                version.setDueDate(rs.getDate(col++));
                version.setMemo(rs.getString(col++));
                version.setHandwritten(rs.getBoolean(col++));
                version.setCheckToBePrinted(rs.getBoolean(col++));
                version.setDiscountAmount(rs.getLong(col++));
                version.setCheckNumber(rs.getLong(col++));
                version.setBalance(rs.getLong(col++));
                version.setCheckDate(rs.getDate(col++));
                version.setInvoiceTotal(rs.getLong(col++));
                version.setVoidedCode(rs.getString(col++));
                version.setApprovalStatus(rs.getLong(col++));
                version.setApprovedBy(rs.getLong(col++));
                version.setApprovedTmstmp(rs.getTimestamp(col++));
                version.setVitalsID(rs.getInt(col++));
                version.setCheck1099(rs.getBoolean(col++));
                version.setCheck1099Amount(rs.getInt(col++));
                version.setVendorName(rs.getString(col++));
                version.setVoidedComment(rs.getString(col++));
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
    
    
    public ArrayList <ApMasterDTO> getApMaster(long locationID, long localeID) {
        ArrayList <ApMasterDTO> versions = new ArrayList <ApMasterDTO> ();
        ApMasterDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select MasterID, InvoiceID, VendorID, LocationID, LocaleID, UserID, " +
            		"InvoiceNumber, InvoiceDate, DueDate, Memo, Handwritten, " +
            		"CheckToBePrinted, DiscountAmount, CheckNumber, Balance, CheckDate, " +
            		"InvoiceTotal, VoidedCode, ApprovalStatus, ApprovedBy, ApprovedTmstmp, " +
            		"AssociatedVitalsID, Check1099, Check1099Amount, VendorName, VoidedComment " +
            		"from apmaster where LocaleID = ? and LocationID = ? order by CheckDate DESC, CheckNumber DESC");
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, localeID);
            statement.setLong(2, locationID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApMasterDTO();
                version.setMasterID(rs.getInt(col++));
                version.setInvoiceID(rs.getInt(col++));
                version.setVendorID(rs.getInt(col++));
                version.setLocationID(rs.getLong(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setUserID(rs.getLong(col++));
                version.setInvoiceNumber(rs.getString(col++));
                version.setInvoiceDate(rs.getDate(col++));
                version.setDueDate(rs.getDate(col++));
                version.setMemo(rs.getString(col++));
                version.setHandwritten(rs.getBoolean(col++));
                version.setCheckToBePrinted(rs.getBoolean(col++));
                version.setDiscountAmount(rs.getLong(col++));
                version.setCheckNumber(rs.getLong(col++));
                version.setBalance(rs.getLong(col++));
                version.setCheckDate(rs.getDate(col++));
                version.setInvoiceTotal(rs.getLong(col++));
                version.setVoidedCode(rs.getString(col++));
                version.setApprovalStatus(rs.getLong(col++));
                version.setApprovedBy(rs.getLong(col++));
                version.setApprovedTmstmp(rs.getTimestamp(col++));
                version.setVitalsID(rs.getInt(col++));
                version.setCheck1099(rs.getBoolean(col++));
                version.setCheck1099Amount(rs.getInt(col++));
                version.setVendorName(rs.getString(col++));
                version.setVoidedComment(rs.getString(col++));
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

    public ApMasterDTO getApMasterByInvoice(String invoiceNumber, long vendorID, long locationID, long checkNumber ) {

        ApMasterDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select MasterID, InvoiceID, VendorID, LocationID, LocaleID, UserID, " +
            		"InvoiceNumber, InvoiceDate, DueDate, Memo, Handwritten, " +
            		"CheckToBePrinted, DiscountAmount, CheckNumber, Balance, CheckDate, " +
            		"InvoiceTotal, VoidedCode, ApprovalStatus, ApprovedBy, ApprovedTmstmp, " +
            		"AssociatedVitalsID, Check1099, Check1099Amount,VendorName, VoidedComment " +
            		"from apmaster where InvoiceNumber = ? and VendorID = ? and LocationID = ? and CheckNumber = ?");
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, invoiceNumber);
            statement.setLong(2, vendorID);
            statement.setLong(3,locationID);
            statement.setLong(4,checkNumber);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 1;
                version = new ApMasterDTO();
                version.setMasterID(rs.getInt(col++));
                version.setInvoiceID(rs.getInt(col++));
                version.setVendorID(rs.getInt(col++));
                version.setLocationID(rs.getLong(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setUserID(rs.getLong(col++));
                version.setInvoiceNumber(rs.getString(col++));
                version.setInvoiceDate(rs.getDate(col++));
                version.setDueDate(rs.getDate(col++));
                version.setMemo(rs.getString(col++));
                version.setHandwritten(rs.getBoolean(col++));
                version.setCheckToBePrinted(rs.getBoolean(col++));
                version.setDiscountAmount(rs.getLong(col++));
                version.setCheckNumber(rs.getLong(col++));
                version.setBalance(rs.getLong(col++));
                version.setCheckDate(rs.getDate(col++));
                version.setInvoiceTotal(rs.getLong(col++));
                version.setVoidedCode(rs.getString(col++));
                version.setApprovalStatus(rs.getLong(col++));
                version.setApprovedBy(rs.getLong(col++));
                version.setApprovedTmstmp(rs.getTimestamp(col++));
                version.setVitalsID(rs.getInt(col++));
                version.setCheck1099(rs.getBoolean(col++));
                version.setCheck1099Amount(rs.getInt(col++));
                version.setVendorName(rs.getString(col++));
                version.setVoidedComment(rs.getString(col++));
                return(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApMaster() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApMaster() : ", e);
        } finally {
            closeConnection();
        }
        
        return null;
    }   

    public ArrayList <ApMasterDTO> getApMasterByLocale(long localeID) {
        ArrayList <ApMasterDTO> versions = new ArrayList <ApMasterDTO> ();
        ApMasterDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select MasterID, InvoiceID, VendorID, LocationID, LocaleID, UserID, " +
            		"InvoiceNumber, InvoiceDate, DueDate, Memo, Handwritten, " +
            		"CheckToBePrinted, DiscountAmount, CheckNumber, Balance, CheckDate, " +
            		"InvoiceTotal, VoidedCode, ApprovalStatus, ApprovedBy, ApprovedTmstmp, " +
            		"AssociatedVitalsID, Check1099, Check1099Amount, VendorName, VoidedComment " +
            		"from apmaster where LocaleID = ? order by CheckDate DESC, CheckNumber DESC");
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, localeID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApMasterDTO();
                version.setMasterID(rs.getInt(col++));
                version.setInvoiceID(rs.getInt(col++));
                version.setVendorID(rs.getInt(col++));
                version.setLocationID(rs.getLong(col++));
                version.setLocaleID(rs.getLong(col++));
                version.setUserID(rs.getLong(col++));
                version.setInvoiceNumber(rs.getString(col++));
                version.setInvoiceDate(rs.getDate(col++));
                version.setDueDate(rs.getDate(col++));
                version.setMemo(rs.getString(col++));
                version.setHandwritten(rs.getBoolean(col++));
                version.setCheckToBePrinted(rs.getBoolean(col++));
                version.setDiscountAmount(rs.getLong(col++));
                version.setCheckNumber(rs.getLong(col++));
                version.setBalance(rs.getLong(col++));
                version.setCheckDate(rs.getDate(col++));
                version.setInvoiceTotal(rs.getLong(col++));
                version.setVoidedCode(rs.getString(col++));
                version.setApprovalStatus(rs.getLong(col++));
                version.setApprovedBy(rs.getLong(col++));
                version.setApprovedTmstmp(rs.getTimestamp(col++));
                version.setVitalsID(rs.getInt(col++));
                version.setCheck1099(rs.getBoolean(col++));
                version.setCheck1099Amount(rs.getInt(col++));
                version.setVendorName(rs.getString(col++));
                version.setVoidedComment(rs.getString(col++));
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
    
    public boolean addApMaster(ApMasterDTO checkMaster) {
        boolean added = false;
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO apmaster ( MasterID, InvoiceID, VendorID, LocationID, LocaleID, UserID, " +
            		"InvoiceNumber, InvoiceDate, DueDate, Memo, Handwritten, " +
            		"CheckToBePrinted, DiscountAmount, CheckNumber, Balance, CheckDate, " +
            		"InvoiceTotal, VoidedCode, ApprovalStatus, ApprovedBy, ApprovedTmstmp, " +
            		"AssociatedVitalsID, Check1099, Check1099Amount, VendorName, VoidedComment ) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            int col = 0;
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setLong(++col, checkMaster.getMasterID());
            statement.setLong(++col, checkMaster.getInvoiceID());
            statement.setLong(++col, checkMaster.getVendorID());
            statement.setLong(++col, checkMaster.getLocationID());
            statement.setLong(++col, checkMaster.getLocaleID());
            statement.setLong(++col, checkMaster.getUserID());
            statement.setString(++col, checkMaster.getInvoiceNumber());
            statement.setDate(++col, checkMaster.getInvoiceDate());
            statement.setDate(++col, checkMaster.getDueDate());
            statement.setString(++col, checkMaster.getMemo());
            statement.setBoolean(++col, checkMaster.isHandwritten());
            statement.setBoolean(++col, checkMaster.isCheckToBePrinted());
            statement.setLong(++col, checkMaster.getDiscountAmount());
            statement.setLong(++col, checkMaster.getCheckNumber());
            statement.setLong(++col, checkMaster.getBalance());
            statement.setDate(++col, checkMaster.getCheckDate());
            statement.setLong(++col, checkMaster.getInvoiceTotal());
            statement.setString(++col, checkMaster.getVoidedCode());
            statement.setLong(++col, checkMaster.getApprovalStatus());
            statement.setLong(++col, checkMaster.getApprovedBy());
            statement.setTimestamp(++col, checkMaster.getApprovedTmstmp() );
            statement.setInt(++col, checkMaster.getVitalsID() );
            statement.setBoolean(++col, checkMaster.isCheck1099() );
            statement.setInt(++col, checkMaster.getCheck1099Amount() );
            statement.setString(++col, checkMaster.getVendorName());
            statement.setString(++col, checkMaster.getVoidedComment());
            statement.executeUpdate();
            added = true;
            
            if (added) {
            	rs = statement.getGeneratedKeys();
                if (rs.next()) {
                	checkMaster.setMasterID(rs.getInt(1));
                }
                
                auditDTO.setCompanyId((int) this.companyID);
                auditDTO.setLocaleId((int) checkMaster.getLocaleID());
                auditDTO.setUserId((int) this.userID);
                
                insertAudit(checkMaster);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addApMaster() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addApMaster() : ", e);
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    /**
     * 
     * @param checkMaster
     * @return
     */
    public boolean updateApMaster(ApMasterDTO checkMaster) {
        boolean added = false;
        
        try {
        	ApMasterDTO oldVer = getApMaster(checkMaster.getMasterID());
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE apmaster set InvoiceID = ?, VendorID = ?, LocationID = ?, LocaleID = ?, UserID = ?, " +
            		"InvoiceNumber = ?, InvoiceDate = ?, DueDate = ?, Memo = ?, Handwritten = ?, " +
            		"CheckToBePrinted = ?, DiscountAmount = ?, CheckNumber = ?, Balance = ?, CheckDate = ?, " +
            		"InvoiceTotal = ?, VoidedCode = ?, ApprovalStatus = ?, ApprovedBy = ?, ApprovedTmstmp = ?, " +
            		"AssociatedVitalsID = ?, Check1099 = ?, Check1099Amount = ?, VendorName = ?, VoidedComment = ? " +
            		"WHERE MasterID = ? ");
            
            int col = 0;
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, checkMaster.getInvoiceID());
            statement.setLong(++col, checkMaster.getVendorID());
            statement.setLong(++col, checkMaster.getLocationID());
            statement.setLong(++col, checkMaster.getLocaleID());
            statement.setLong(++col, checkMaster.getUserID());
            statement.setString(++col, checkMaster.getInvoiceNumber());
            statement.setDate(++col, checkMaster.getInvoiceDate());
            statement.setDate(++col, checkMaster.getDueDate());
            statement.setString(++col, checkMaster.getMemo());
            statement.setBoolean(++col, checkMaster.isHandwritten());
            statement.setBoolean(++col, checkMaster.isCheckToBePrinted());
            statement.setLong(++col, checkMaster.getDiscountAmount());
            statement.setLong(++col, checkMaster.getCheckNumber());
            statement.setLong(++col, checkMaster.getBalance());
            statement.setDate(++col, checkMaster.getCheckDate());
            statement.setLong(++col, checkMaster.getInvoiceTotal());
            statement.setString(++col, checkMaster.getVoidedCode());
            statement.setLong(++col, checkMaster.getApprovalStatus());
            statement.setLong(++col, checkMaster.getApprovedBy());
            statement.setTimestamp(++col, checkMaster.getApprovedTmstmp() );
            statement.setInt(++col, checkMaster.getVitalsID() );
            statement.setBoolean(++col, checkMaster.isCheck1099() );
            statement.setInt(++col, checkMaster.getCheck1099Amount() );
            statement.setString(++col, checkMaster.getVendorName());
            statement.setString(++col, checkMaster.getVoidedComment());
            statement.setLong(++col, checkMaster.getMasterID());
            statement.executeUpdate();
            
            auditDTO.setCompanyId((int) this.companyID);
            auditDTO.setLocaleId((int) checkMaster.getLocaleID());
            auditDTO.setUserId((int) this.userID);
            
            updateAudit(oldVer, checkMaster);
            
            
        } catch (SQLException e) {
            logger.error("SQLException in updateApMaster() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateApMaster() : ", e);
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    /**
     * @param versionId
     */
    public void deleteApMaster(long masterID) {
        
        try {
        	ApMasterDTO oldVer = getApMaster(masterID);
        	
            String sql = "delete from apmaster WHERE (MasterID = ?)";
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql);
            statement.setLong(1, masterID);
            statement.executeUpdate();     
            
            auditDTO.setCompanyId((int) companyID);
            auditDTO.setUserId((int) userID);
            auditDTO.setLocaleId((int) oldVer.getLocaleID());            
            
            deleteAudit(oldVer, null);
            
        } catch (SQLException e) {
            logger.error("SQLException in deleteApMaster() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteApMaster() : ", e);
        } finally {
            closeConnection();
        }
        
    }
    
    
}
