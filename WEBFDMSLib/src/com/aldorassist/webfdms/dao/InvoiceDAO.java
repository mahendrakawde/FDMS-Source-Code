package com.aldorassist.webfdms.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.InvoiceDTO;
import com.aldorassist.webfdms.model.InvoiceTransactionHistoryDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.ums.model.RolesMembershipDTO;
import com.aldorsolutions.webfdms.util.DAO;

/**
 * 
 * @author David Rollins
 * Sep 6, 2007
 * InvoiceDAO.java
 *
 */
public class InvoiceDAO extends DAO {
    
    private Logger logger = Logger.getLogger(InvoiceDAO.class.getName());
    
    private Integer totalRows = 0;
    
    /**
	 * @return the totalRows
	 */
	public Integer getTotalRows() {
		return totalRows;
	}

	public InvoiceDAO(DbUserSession user) {
    	super (user);
    }
    
    public InvoiceDAO(long companyID, long userID) {
    	super (companyID, userID);
    }
    
    private static String selectFields () {
    	return ("invoiceID, vendorID, locationID, invoiceNumber, invoiceDate,invoiceDueDate," +
    			"invoicePaid,glcategory,amountOfInvoice,description,1099Type,1099Amount," +
    			"discountFlag, discountAmount,discountSubjectAmount,discountPercent,discountDollars," +
    			"discountDue,discountDueFreqCode,discountDueDate,recurringFlag," +
    			"recurringCount,recurringFreq,recurringFreqCode,checkingAccount,checkingStatus," +
    			"checkCreated,invoiceStatus,apMasterID");
    }
    
    public InvoiceDTO getInvoice ( long invoiceID ) {
    	InvoiceDTO invoice = null;
        
        try {
            String sql = "select " + selectFields() + " from invoice where InvoiceID = ?" ;
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            statement.setLong(1, invoiceID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 0;
            	invoice = new InvoiceDTO();
            	invoice.setInvoiceID(rs.getInt(++col));
            	invoice.setVendorID(rs.getInt(++col));
            	invoice.setLocationID(rs.getInt(++col));
            	invoice.setInvoiceNumber(rs.getString(++col));
            	invoice.setInvoiceDate(rs.getDate(++col));
            	invoice.setInvoiceDueDate(rs.getDate(++col));
            	invoice.setInvoicePaid(rs.getBoolean(++col));
            	invoice.setGlcategory(rs.getString(++col));
            	invoice.setAmountOfInvoice(rs.getDouble(++col));
            	invoice.setDescription(rs.getString(++col));
            	invoice.setInvoice1099Type(rs.getInt(++col));
            	invoice.setInvoice1099Amount(rs.getDouble(++col));
            	invoice.setDiscountFlag(rs.getBoolean(++col));
            	invoice.setDiscountAmount(rs.getDouble(++col));
            	invoice.setDiscountSubjectAmount(rs.getDouble(++col));
            	invoice.setDiscountPercent(rs.getInt(++col));
            	invoice.setDiscountDollars(rs.getDouble(++col));
            	invoice.setDiscountDue( rs.getInt(++col));
            	invoice.setDiscountDueFreq( rs.getInt(++col));
            	invoice.setDiscountDueDate(rs.getDate(++col));
            	invoice.setRecurringFlag(rs.getBoolean(++col));
            	invoice.setRecurringCount(rs.getInt(++col));
            	invoice.setRecurringFreq(rs.getInt(++col));
            	invoice.setRecurringFreqCode(rs.getInt(++col));
            	invoice.setCheckingAccount(rs.getInt(++col));
            	invoice.setCheckingStatus(rs.getInt(++col));
            	invoice.setCheckCreated(rs.getBoolean(++col));
            	invoice.setInvoiceStatus(rs.getString(++col));
            	invoice.setApMasterID(rs.getInt(++col));
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoice() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoice() : ", e);
        } finally {
            closeConnection();
        }
        
        return invoice;
    }
  
    public ArrayList <InvoiceDTO> getInvoicesByInvoiceNumber ( String invoiceNumber ) {
		ArrayList <InvoiceDTO> list = new ArrayList <InvoiceDTO> ();
    	InvoiceDTO invoice = null;
    	
        try {
            String sql = "select " + selectFields() + " FROM invoice WHERE invoiceNumber = '"+invoiceNumber+"' ORDER BY locationID";
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	invoice = new InvoiceDTO();
            	invoice.setInvoiceID(rs.getInt(++col));
            	invoice.setVendorID(rs.getInt(++col));
            	invoice.setLocationID(rs.getInt(++col));
            	invoice.setInvoiceNumber(rs.getString(++col));
            	invoice.setInvoiceDate(rs.getDate(++col));
            	invoice.setInvoiceDueDate(rs.getDate(++col));
            	invoice.setInvoicePaid(rs.getBoolean(++col));
            	invoice.setGlcategory(rs.getString(++col));
            	invoice.setAmountOfInvoice(rs.getDouble(++col));
            	invoice.setDescription(rs.getString(++col));
            	invoice.setInvoice1099Type(rs.getInt(++col));
            	invoice.setInvoice1099Amount(rs.getDouble(++col));
            	invoice.setDiscountFlag(rs.getBoolean(++col));
            	invoice.setDiscountAmount(rs.getDouble(++col));
            	invoice.setDiscountSubjectAmount(rs.getDouble(++col));
            	invoice.setDiscountPercent(rs.getInt(++col));
            	invoice.setDiscountDollars(rs.getDouble(++col));
            	invoice.setDiscountDue( rs.getInt(++col));
            	invoice.setDiscountDueFreq( rs.getInt(++col));
            	invoice.setDiscountDueDate(rs.getDate(++col));
            	invoice.setRecurringFlag(rs.getBoolean(++col));
            	invoice.setRecurringCount(rs.getInt(++col));
            	invoice.setRecurringFreq(rs.getInt(++col));
            	invoice.setRecurringFreqCode(rs.getInt(++col));
            	invoice.setCheckingAccount(rs.getInt(++col));
            	invoice.setCheckingStatus(rs.getInt(++col));
            	invoice.setCheckCreated(rs.getBoolean(++col));
            	invoice.setInvoiceStatus(rs.getString(++col));
            	invoice.setApMasterID(rs.getInt(++col));
            	list.add(invoice);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoice() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoice() : ", e);
        } finally {
            closeConnection();
        }   	
    	
    	return list;
    }
   
    public ArrayList <InvoiceDTO> getInvoicesByInvoiceNumber ( String invoiceNumber , boolean activeOnly) {
		ArrayList <InvoiceDTO> list = new ArrayList <InvoiceDTO> ();
    	InvoiceDTO invoice = null;
    	
        try {
            String sql = "select " + selectFields() + " FROM invoice WHERE invoiceNumber = '"+invoiceNumber+"' ";
            
            if (activeOnly) {
            	// code comment because user can reuse the denied invoice.
            	//sql = sql + " and invoiceStatus not in ('D','d','V','v') ";
            	sql = sql + " and invoiceStatus not in ('D','V','v') ";
            }
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	invoice = new InvoiceDTO();
            	invoice.setInvoiceID(rs.getInt(++col));
            	invoice.setVendorID(rs.getInt(++col));
            	invoice.setLocationID(rs.getInt(++col));
            	invoice.setInvoiceNumber(rs.getString(++col));
            	invoice.setInvoiceDate(rs.getDate(++col));
            	invoice.setInvoiceDueDate(rs.getDate(++col));
            	invoice.setInvoicePaid(rs.getBoolean(++col));
            	invoice.setGlcategory(rs.getString(++col));
            	invoice.setAmountOfInvoice(rs.getDouble(++col));
            	invoice.setDescription(rs.getString(++col));
            	invoice.setInvoice1099Type(rs.getInt(++col));
            	invoice.setInvoice1099Amount(rs.getDouble(++col));
            	invoice.setDiscountFlag(rs.getBoolean(++col));
            	invoice.setDiscountAmount(rs.getDouble(++col));
            	invoice.setDiscountSubjectAmount(rs.getDouble(++col));
            	invoice.setDiscountPercent(rs.getInt(++col));
            	invoice.setDiscountDollars(rs.getDouble(++col));
            	invoice.setDiscountDue( rs.getInt(++col));
            	invoice.setDiscountDueFreq( rs.getInt(++col));
            	invoice.setDiscountDueDate(rs.getDate(++col));
            	invoice.setRecurringFlag(rs.getBoolean(++col));
            	invoice.setRecurringCount(rs.getInt(++col));
            	invoice.setRecurringFreq(rs.getInt(++col));
            	invoice.setRecurringFreqCode(rs.getInt(++col));
            	invoice.setCheckingAccount(rs.getInt(++col));
            	invoice.setCheckingStatus(rs.getInt(++col));
            	invoice.setCheckCreated(rs.getBoolean(++col));
            	invoice.setInvoiceStatus(rs.getString(++col));
            	invoice.setApMasterID(rs.getInt(++col));
            	list.add(invoice);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoice() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoice() : ", e);
        } finally {
            closeConnection();
        }   	
    	
    	return list;
    }   
    public ArrayList <InvoiceDTO> getInvoicesByMasterID ( String masterID ) {
		ArrayList <InvoiceDTO> list = new ArrayList <InvoiceDTO> ();
    	InvoiceDTO invoice = null;
    	
        try {
            String sql = "select " + selectFields() + " FROM invoice WHERE apMasterID = '"+masterID+"'";
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	invoice = new InvoiceDTO();
            	invoice.setInvoiceID(rs.getInt(++col));
            	invoice.setVendorID(rs.getInt(++col));
            	invoice.setLocationID(rs.getInt(++col));
            	invoice.setInvoiceNumber(rs.getString(++col));
            	invoice.setInvoiceDate(rs.getDate(++col));
            	invoice.setInvoiceDueDate(rs.getDate(++col));
            	invoice.setInvoicePaid(rs.getBoolean(++col));
            	invoice.setGlcategory(rs.getString(++col));
            	invoice.setAmountOfInvoice(rs.getDouble(++col));
            	invoice.setDescription(rs.getString(++col));
            	invoice.setInvoice1099Type(rs.getInt(++col));
            	invoice.setInvoice1099Amount(rs.getDouble(++col));
            	invoice.setDiscountFlag(rs.getBoolean(++col));
            	invoice.setDiscountAmount(rs.getDouble(++col));
            	invoice.setDiscountSubjectAmount(rs.getDouble(++col));
            	invoice.setDiscountPercent(rs.getInt(++col));
            	invoice.setDiscountDollars(rs.getDouble(++col));
            	invoice.setDiscountDue( rs.getInt(++col));
            	invoice.setDiscountDueFreq( rs.getInt(++col));
            	invoice.setDiscountDueDate(rs.getDate(++col));
            	invoice.setRecurringFlag(rs.getBoolean(++col));
            	invoice.setRecurringCount(rs.getInt(++col));
            	invoice.setRecurringFreq(rs.getInt(++col));
            	invoice.setRecurringFreqCode(rs.getInt(++col));
            	invoice.setCheckingAccount(rs.getInt(++col));
            	invoice.setCheckingStatus(rs.getInt(++col));
            	invoice.setCheckCreated(rs.getBoolean(++col));
            	invoice.setInvoiceStatus(rs.getString(++col));
            	invoice.setApMasterID(rs.getInt(++col));
            	list.add(invoice);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoice() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoice() : ", e);
        } finally {
            closeConnection();
        }   	
    	
    	return list;
    }
    
 	public ArrayList <InvoiceDTO> getInvoices ( String [] invoiceNumbers ) {
		ArrayList <InvoiceDTO> list = new ArrayList <InvoiceDTO> ();
    	InvoiceDTO invoice = null;
        String sqlInvoiceNumbers = new String();
        
        	// Take the array of invoice numbers and create a string ="XX,XX,XX" where
        	// XX is equal to one of the invoice numbers.  We are creating the string
        	// so that we can use it in a SQL 'IN' statement
        for (int x=0; x < invoiceNumbers.length; x++) {
        	sqlInvoiceNumbers += invoiceNumbers[x];
        	
        	if (x+1 < invoiceNumbers.length) {
        		sqlInvoiceNumbers += ",";
        	}
        }
    	
        try {
            String sql = "select " + selectFields() + " FROM invoice WHERE InvoiceID in (";
            sql += sqlInvoiceNumbers + ") ORDER BY locationID";
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	invoice = new InvoiceDTO();
            	invoice.setInvoiceID(rs.getInt(++col));
            	invoice.setVendorID(rs.getInt(++col));
            	invoice.setLocationID(rs.getInt(++col));
            	invoice.setInvoiceNumber(rs.getString(++col));
            	invoice.setInvoiceDate(rs.getDate(++col));
            	invoice.setInvoiceDueDate(rs.getDate(++col));
            	invoice.setInvoicePaid(rs.getBoolean(++col));
            	invoice.setGlcategory(rs.getString(++col));
            	invoice.setAmountOfInvoice(rs.getDouble(++col));
            	invoice.setDescription(rs.getString(++col));
            	invoice.setInvoice1099Type(rs.getInt(++col));
            	invoice.setInvoice1099Amount(rs.getDouble(++col));
            	invoice.setDiscountFlag(rs.getBoolean(++col));
            	invoice.setDiscountAmount(rs.getDouble(++col));
            	invoice.setDiscountSubjectAmount(rs.getDouble(++col));
            	invoice.setDiscountPercent(rs.getInt(++col));
            	invoice.setDiscountDollars(rs.getDouble(++col));
            	invoice.setDiscountDue( rs.getInt(++col));
            	invoice.setDiscountDueFreq( rs.getInt(++col));
            	invoice.setDiscountDueDate(rs.getDate(++col));
            	invoice.setRecurringFlag(rs.getBoolean(++col));
            	invoice.setRecurringCount(rs.getInt(++col));
            	invoice.setRecurringFreq(rs.getInt(++col));
            	invoice.setRecurringFreqCode(rs.getInt(++col));
            	invoice.setCheckingAccount(rs.getInt(++col));
            	invoice.setCheckingStatus(rs.getInt(++col));
            	invoice.setCheckCreated(rs.getBoolean(++col));
            	invoice.setInvoiceStatus(rs.getString(++col));
            	invoice.setApMasterID(rs.getInt(++col));
            	list.add(invoice);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoice() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoice() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }
 	
    public ArrayList <InvoiceDTO> getFilterInvoices ( String filterText, String filterValue, Date filterValueDate, String orderBy, 
    		ArrayList <RolesMembershipDTO> roles, String apMasterID, String strVendorIDs, Date earliestDateSearch, 
    		Date latestDateSearch,String invSelectType,int pageNumber,String locationIDs ) {
	ArrayList <InvoiceDTO> list = new ArrayList <InvoiceDTO> ();
    
    try {
    	
    	int eRole = 2;
    	for( RolesMembershipDTO role: roles) {
    		if (Long.toString(role.getRoleID()).compareToIgnoreCase("3")==0){
    			eRole = 3;
    		}
    		else {
    			eRole = 2;
    			break;
    		}
    	}
    	String commonSQL = "";
    	if (eRole == 2){ //this one is for user/admin
    		commonSQL = " (invoiceStatus <> binary 'D' and invoiceStatus <> binary 'P' and invoiceStatus <> binary 'V' and apMasterID = 0) ";
    	}else { // this one for accountant
    		commonSQL = " invoiceStatus in ('S')";
    	}
    	
    	String commonSQLwithoutDV =" (invoiceStatus <> binary 'D' and invoiceStatus <> binary 'V') ";
    	
    	if(invSelectType != null && !"".equals(invSelectType)){
    	            commonSQLwithoutDV = " (invoiceStatus = binary '"+invSelectType+"') ";
    	}
    	
        String sql = "select SQL_CALC_FOUND_ROWS " + selectFields() + " from invoice where ";
        
        /*if (filterText.compareToIgnoreCase("Paid")==0) {
            sql += " invoicePaid = 1 and locationID in " + filterValue + " and vendorID in " + strVendorIDs +" and  ( (invoiceDueDate between '"+earliestDateSearch+"' and '"+latestDateSearch+"')) and " + commonSQLwithoutDV; 
        } else if (filterText.compareToIgnoreCase("notPaid")==0) {
            sql += " invoicePaid = 0 and locationID in " + filterValue + " and vendorID in " + strVendorIDs +" and  ( (invoiceDueDate between '"+earliestDateSearch+"' and '"+latestDateSearch+"')) and " + commonSQLwithoutDV; 
        }else */
        if (filterText.compareToIgnoreCase("invoiceNumber")==0 || filterText.compareToIgnoreCase("vendorID")==0 ){
        	sql += filterText + " like '" + filterValue +"' ";
        }else if (filterText.compareToIgnoreCase("Status")==0) {
            sql += " locationID in " + filterValue + " and vendorID in " + strVendorIDs +" and  ( (invoiceDueDate between '"+earliestDateSearch+
            "' and '"+latestDateSearch+"')) and " + commonSQLwithoutDV; 
        }else if (filterText.compareToIgnoreCase("invoiceDueDate")==0 || filterText.compareToIgnoreCase("discountDueDate")==0 ){
        		sql += filterText + " like '" + filterValueDate +"' ";
        } else if (filterText.compareToIgnoreCase("Location") == 0 || filterText.compareToIgnoreCase("Locale") == 0 ) {
        	sql += " locationID in " + filterValue;
        	if(invSelectType != null && !"".equals(invSelectType) && invSelectType.equals("A"))
        		 sql +=" AND " +commonSQLwithoutDV ;
        	sql +=" AND " + commonSQL;
        } else if (filterText.compareToIgnoreCase("checkNumber") == 0) {
        	sql += " locationID in " + filterValue + " and apMasterID in " + apMasterID;
        } else if (filterText.compareToIgnoreCase("vendorHistory") == 0) {
        	sql += " locationID in " + filterValue + " and vendorID in " + strVendorIDs +" and (invoiceDate between '"+earliestDateSearch+
        	"' and '"+latestDateSearch+"') and " + commonSQLwithoutDV;	
        } else if (filterText.compareToIgnoreCase("unpaidInvoice") == 0) {
        	sql += " locationID in " + filterValue + " and  ( (invoiceDueDate between '"+earliestDateSearch+
        	"' and '"+latestDateSearch+"') or (discountDueDate between '"+earliestDateSearch+
        	"' and '"+latestDateSearch+"')) and "+ commonSQL;	
        } else {
        		sql += commonSQL;
        		sql += " AND locationID in("+ locationIDs + ")";
        	}
                
         	
        //if (orderBy.compareToIgnoreCase("None")== 0) {
        	orderBy = " invoiceID desc ";
        //} 	
        
        sql += " order by " + orderBy;
        sql += (" limit " +  (pageNumber * 20 - 20) + ",20 ");
        
        makeConnection(getDbLookup());
        statement = conn.prepareStatement(sql);             

        System.out.println("Search SQL :: "+sql);
                   
        rs = statement.executeQuery();
        
        while (rs.next()) {
        	int col = 0;
        	InvoiceDTO invoice = new InvoiceDTO();
        	invoice.setInvoiceID(rs.getInt(++col));
        	invoice.setVendorID(rs.getInt(++col));
        	invoice.setLocationID(rs.getInt(++col));
        	invoice.setInvoiceNumber(rs.getString(++col));
        	invoice.setInvoiceDate(rs.getDate(++col));
        	invoice.setInvoiceDueDate(rs.getDate(++col));
        	invoice.setInvoicePaid(rs.getBoolean(++col));
        	invoice.setGlcategory(rs.getString(++col));
        	invoice.setAmountOfInvoice(rs.getDouble(++col));
        	invoice.setDescription(rs.getString(++col));
        	invoice.setInvoice1099Type(rs.getInt(++col));
        	invoice.setInvoice1099Amount(rs.getDouble(++col));
        	invoice.setDiscountFlag(rs.getBoolean(++col));
        	invoice.setDiscountAmount(rs.getDouble(++col));
        	invoice.setDiscountSubjectAmount(rs.getDouble(++col));
        	invoice.setDiscountPercent(rs.getInt(++col));
        	invoice.setDiscountDollars(rs.getDouble(++col));
        	invoice.setDiscountDue( rs.getInt(++col));
        	invoice.setDiscountDueFreq( rs.getInt(++col));
        	invoice.setDiscountDueDate(rs.getDate(++col));
        	invoice.setRecurringFlag(rs.getBoolean(++col));
        	invoice.setRecurringCount(rs.getInt(++col));
        	invoice.setRecurringFreq(rs.getInt(++col));
        	invoice.setRecurringFreqCode(rs.getInt(++col));
        	invoice.setCheckingAccount(rs.getInt(++col));
        	invoice.setCheckingStatus(rs.getInt(++col));
        	invoice.setCheckCreated(rs.getBoolean(++col));
        	invoice.setInvoiceStatus(rs.getString(++col));
        	invoice.setApMasterID(rs.getInt(++col));
        	list.add(invoice);
        }
        
        sql = "select FOUND_ROWS();";
        statement = conn.prepareStatement(sql);
        rs = statement.executeQuery();
        while (rs.next()) {
        	totalRows = rs.getInt(1);
        }
    } catch (SQLException e) {
        logger.error("SQLException in getInvoices() : ", e);
    } catch (Exception e) {
        logger.error("Exception in getInvoices() : ", e);
    } finally {
        closeConnection();
    }
    return list;
}   
    
    public ArrayList <InvoiceDTO> getInvoicesByMasterID ( String filterText, String filterValue, Date filterValueDate, String orderBy, ArrayList <RolesMembershipDTO> roles ) {
    	ArrayList <InvoiceDTO> list = new ArrayList <InvoiceDTO> ();
        
        try {
        	
        	int eRole = 2;
        	for( RolesMembershipDTO role: roles) {
        		if (Long.toString(role.getRoleID()).compareToIgnoreCase("3")==0){
        			eRole = 3;
        		}
        		else {
        			eRole = 2;
        			break;
        		}
        	}
        	String commonSQL = "";
        	if (eRole == 2){ //this one is for user/admin
        		commonSQL = " (invoiceStatus <> binary 'D' and invoiceStatus <> binary 'P' and invoiceStatus <> binary 'V') ";
        	}else { // this one for accountant
        		commonSQL = " invoiceStatus in ('S')";
        	}
        	
            String sql = "select " + selectFields() + " from invoice where ";
            
            if (filterText.compareToIgnoreCase("Paid")==0) {
                sql += " invoicePaid = 1 ";
            } else if (filterText.compareToIgnoreCase("notPaid")==0) {
            	sql += " invoicePaid = 0 ";
            } else if (filterText.compareToIgnoreCase("invoiceNumber")==0 || filterText.compareToIgnoreCase("vendorID")==0 ){
            	sql += filterText + " like '" + filterValue +"' ";
            } else if (filterText.compareToIgnoreCase("invoiceDueDate")==0 || filterText.compareToIgnoreCase("discountDueDate")==0 ){
            		sql += filterText + " like '" + filterValueDate +"' ";
            } else if (filterText.compareToIgnoreCase("Location") == 0) {
            	    sql += " locationID " + "= " + filterValue;
            } else if (filterText.compareToIgnoreCase("Locale") == 0 ) {
            	    sql += " locationID in " + filterValue;
            } else {
            		sql += commonSQL;
            	}
                    
             	
            if (orderBy.compareToIgnoreCase("None")== 0) {
            	orderBy = " invoiceID ";
            } 	
            
            sql += " order by " + orderBy;
           
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);             

                       
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	InvoiceDTO invoice = new InvoiceDTO();
            	invoice.setInvoiceID(rs.getInt(++col));
            	invoice.setVendorID(rs.getInt(++col));
            	invoice.setLocationID(rs.getInt(++col));
            	invoice.setInvoiceNumber(rs.getString(++col));
            	invoice.setInvoiceDate(rs.getDate(++col));
            	invoice.setInvoiceDueDate(rs.getDate(++col));
            	invoice.setInvoicePaid(rs.getBoolean(++col));
            	invoice.setGlcategory(rs.getString(++col));
            	invoice.setAmountOfInvoice(rs.getDouble(++col));
            	invoice.setDescription(rs.getString(++col));
            	invoice.setInvoice1099Type(rs.getInt(++col));
            	invoice.setInvoice1099Amount(rs.getDouble(++col));
            	invoice.setDiscountFlag(rs.getBoolean(++col));
            	invoice.setDiscountAmount(rs.getDouble(++col));
            	invoice.setDiscountSubjectAmount(rs.getDouble(++col));
            	invoice.setDiscountPercent(rs.getInt(++col));
            	invoice.setDiscountDollars(rs.getDouble(++col));
            	invoice.setDiscountDue( rs.getInt(++col));
            	invoice.setDiscountDueFreq( rs.getInt(++col));
            	invoice.setDiscountDueDate(rs.getDate(++col));
            	invoice.setRecurringFlag(rs.getBoolean(++col));
            	invoice.setRecurringCount(rs.getInt(++col));
            	invoice.setRecurringFreq(rs.getInt(++col));
            	invoice.setRecurringFreqCode(rs.getInt(++col));
            	invoice.setCheckingAccount(rs.getInt(++col));
            	invoice.setCheckingStatus(rs.getInt(++col));
            	invoice.setCheckCreated(rs.getBoolean(++col));
            	invoice.setInvoiceStatus(rs.getString(++col));
            	invoice.setApMasterID(rs.getInt(++col));
            	list.add(invoice);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoices() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoices() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }      
    
    public ArrayList <InvoiceDTO> getInvoicesByStatus ( String status ,Date fromDueDate, Date toDueDate,String locationIds ,int pageNumber ) {
    	ArrayList <InvoiceDTO> list = new ArrayList <InvoiceDTO> ();
        
        try {
        	String apMasterID = " ";
        	if ( (status.compareToIgnoreCase("'A'")== 0) || (status.compareToIgnoreCase("'C'")== 0) || (status.compareToIgnoreCase("'S'")== 0)) {
        		apMasterID = " and apMasterID = 0 ";
        	}
        	
        	if(locationIds!=null){
        		apMasterID += " and  locationID in  (" + locationIds +") " ;
        	}
        	
        	
            String sql = "select SQL_CALC_FOUND_ROWS " + selectFields() + " from invoice where invoiceStatus  COLLATE latin1_general_cs in ("+status+") "+ apMasterID ;
            
            if((fromDueDate != null && toDueDate != null) && (!"".equals(fromDueDate) || !"".equals(toDueDate))){
                sql += " and  ( (invoiceDueDate between '"+fromDueDate+"' and '"+toDueDate+"') ) ";
            }
            
            
            
            sql+= " order by invoiceID desc";
            sql+= (" limit " +  (pageNumber * 20 - 20) + ",20 ");

            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);             
                       
            System.out.println("Search SQL 2 :: "+sql);
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	InvoiceDTO invoice = new InvoiceDTO();
            	invoice.setInvoiceID(rs.getInt(++col));
            	invoice.setVendorID(rs.getInt(++col));
            	invoice.setLocationID(rs.getInt(++col));
            	invoice.setInvoiceNumber(rs.getString(++col));
            	invoice.setInvoiceDate(rs.getDate(++col));
            	invoice.setInvoiceDueDate(rs.getDate(++col));
            	invoice.setInvoicePaid(rs.getBoolean(++col));
            	invoice.setGlcategory(rs.getString(++col));
            	invoice.setAmountOfInvoice(rs.getDouble(++col));
            	invoice.setDescription(rs.getString(++col));
            	invoice.setInvoice1099Type(rs.getInt(++col));
            	invoice.setInvoice1099Amount(rs.getDouble(++col));
            	invoice.setDiscountFlag(rs.getBoolean(++col));
            	invoice.setDiscountAmount(rs.getDouble(++col));
            	invoice.setDiscountSubjectAmount(rs.getDouble(++col));
            	invoice.setDiscountPercent(rs.getInt(++col));
            	invoice.setDiscountDollars(rs.getDouble(++col));
            	invoice.setDiscountDue( rs.getInt(++col));
            	invoice.setDiscountDueFreq( rs.getInt(++col));
            	invoice.setDiscountDueDate(rs.getDate(++col));
            	invoice.setRecurringFlag(rs.getBoolean(++col));
            	invoice.setRecurringCount(rs.getInt(++col));
            	invoice.setRecurringFreq(rs.getInt(++col));
            	invoice.setRecurringFreqCode(rs.getInt(++col));
            	invoice.setCheckingAccount(rs.getInt(++col));
            	invoice.setCheckingStatus(rs.getInt(++col));
            	invoice.setCheckCreated(rs.getBoolean(++col));
            	invoice.setInvoiceStatus(rs.getString(++col));
            	invoice.setApMasterID(rs.getInt(++col));
            	list.add(invoice);
            }
            
            sql = "select FOUND_ROWS();";
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
            	totalRows = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoices() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoices() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }   
    public ArrayList <InvoiceDTO> getSearchInvoices ( String filterText, String filterValue, Date filterValueDate, String orderBy, ArrayList <RolesMembershipDTO> roles ) {
    	ArrayList <InvoiceDTO> list = new ArrayList <InvoiceDTO> ();
        
        try {
        	
        	int eRole = 2;
        	for( RolesMembershipDTO role: roles) {
        		if (Long.toString(role.getRoleID()).compareToIgnoreCase("3")==0){
        			eRole = 3;
        		}
        		else {
        			eRole = 2;
        			break;
        		}
        	}
        	String commonSQL = "";
        	if (eRole == 2){ //this one is for user/admin
        		commonSQL = " invoiceStatus <> binary 'D' and invoiceStatus <> binary 'P'";
        	}else { // this one for accountant
        		commonSQL = " invoiceStatus in ('S')";
        	}
        	
            String sql = "select " + selectFields() + " from invoice where ";
            
            if (filterText.compareToIgnoreCase("Paid")==0) {
                sql += " invoicePaid = 1 ";
            } else if (filterText.compareToIgnoreCase("notPaid")==0) {
            	sql += " invoicePaid = 1 ";
            } else if (filterText.compareToIgnoreCase("invoiceNumber")==0 || filterText.compareToIgnoreCase("vendorID")==0 ){
            	sql += filterText + " like '" + filterValue +"' ";
            } else if (filterText.compareToIgnoreCase("invoiceDueDate")==0 || filterText.compareToIgnoreCase("discountDueDate")==0 ){
            		sql += filterText + " like '" + filterValueDate +"' ";
            } else {
            		sql += commonSQL;
            	}
                    
             	
            if (orderBy.compareToIgnoreCase("None")== 0) {
            	orderBy = " invoiceID ";
            } 	
            
            sql += " order by " + orderBy;
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);             
            rs = statement.executeQuery();
            while (rs.next()) {
            	int col = 0;
            	InvoiceDTO invoice = new InvoiceDTO();
            	invoice.setInvoiceID(rs.getInt(++col));
            	invoice.setVendorID(rs.getInt(++col));
            	invoice.setLocationID(rs.getInt(++col));
            	invoice.setInvoiceNumber(rs.getString(++col));
            	invoice.setInvoiceDate(rs.getDate(++col));
            	invoice.setInvoiceDueDate(rs.getDate(++col));
            	invoice.setInvoicePaid(rs.getBoolean(++col));
            	invoice.setGlcategory(rs.getString(++col));
            	invoice.setAmountOfInvoice(rs.getDouble(++col));
            	invoice.setDescription(rs.getString(++col));
            	invoice.setInvoice1099Type(rs.getInt(++col));
            	invoice.setInvoice1099Amount(rs.getDouble(++col));
            	invoice.setDiscountFlag(rs.getBoolean(++col));
            	invoice.setDiscountAmount(rs.getDouble(++col));
            	invoice.setDiscountSubjectAmount(rs.getDouble(++col));
            	invoice.setDiscountPercent(rs.getInt(++col));
            	invoice.setDiscountDollars(rs.getDouble(++col));
            	invoice.setDiscountDue( rs.getInt(++col));
            	invoice.setDiscountDueFreq( rs.getInt(++col));
            	invoice.setDiscountDueDate(rs.getDate(++col));
            	invoice.setRecurringFlag(rs.getBoolean(++col));
            	invoice.setRecurringCount(rs.getInt(++col));
            	invoice.setRecurringFreq(rs.getInt(++col));
            	invoice.setRecurringFreqCode(rs.getInt(++col));
            	invoice.setCheckingAccount(rs.getInt(++col));
            	invoice.setCheckingStatus(rs.getInt(++col));
            	invoice.setCheckCreated(rs.getBoolean(++col));
            	invoice.setInvoiceStatus(rs.getString(++col));
            	invoice.setApMasterID(rs.getInt(++col));
            	list.add(invoice);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoices() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoices() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }      
    
    public ArrayList <InvoiceDTO> getInvoices ( boolean unpaidInvoicesOnly ) {
    	ArrayList <InvoiceDTO> list = new ArrayList <InvoiceDTO> ();
        
        try {
            String sql = "select " + selectFields() + " from invoice";
            
            
            	sql += " where invoicePaid = ?";
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            
            if ( unpaidInvoicesOnly ) {
            	statement.setBoolean(1, false);
            }
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	InvoiceDTO invoice = new InvoiceDTO();
            	invoice.setInvoiceID(rs.getInt(++col));
            	invoice.setVendorID(rs.getInt(++col));
            	invoice.setLocationID(rs.getInt(++col));
            	invoice.setInvoiceNumber(rs.getString(++col));
            	invoice.setInvoiceDate(rs.getDate(++col));
            	invoice.setInvoiceDueDate(rs.getDate(++col));
            	invoice.setInvoicePaid(rs.getBoolean(++col));
            	invoice.setGlcategory(rs.getString(++col));
            	invoice.setAmountOfInvoice(rs.getDouble(++col));
            	invoice.setDescription(rs.getString(++col));
            	invoice.setInvoice1099Type(rs.getInt(++col));
            	invoice.setInvoice1099Amount(rs.getDouble(++col));
            	invoice.setDiscountFlag(rs.getBoolean(++col));
            	invoice.setDiscountAmount(rs.getDouble(++col));
            	invoice.setDiscountSubjectAmount(rs.getDouble(++col));
            	invoice.setDiscountPercent(rs.getInt(++col));
            	invoice.setDiscountDollars(rs.getDouble(++col));
            	invoice.setDiscountDue( rs.getInt(++col));
            	invoice.setDiscountDueFreq( rs.getInt(++col));
            	invoice.setDiscountDueDate(rs.getDate(++col));
            	invoice.setRecurringFlag(rs.getBoolean(++col));
            	invoice.setRecurringCount(rs.getInt(++col));
            	invoice.setRecurringFreq(rs.getInt(++col));
            	invoice.setRecurringFreqCode(rs.getInt(++col));
            	invoice.setCheckingAccount(rs.getInt(++col));
            	invoice.setCheckingStatus(rs.getInt(++col));
            	invoice.setCheckCreated(rs.getBoolean(++col));
            	invoice.setInvoiceStatus(rs.getString(++col));
            	invoice.setApMasterID(rs.getInt(++col));
            	list.add(invoice);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoices() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoices() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }

    public ArrayList <InvoiceDTO> getFilterInvoices ( String filterText, String filterValue, String orderBy ) {
    	ArrayList <InvoiceDTO> list = new ArrayList <InvoiceDTO> ();
        
        try {
            String sql = "select " + selectFields() + " from invoice where ";
            
            
            if (filterText.compareToIgnoreCase("Paid")==0) {
                sql += " invoicePaid = 0 ";
            } else if (filterText.compareToIgnoreCase("notPaid")==0) {
            	sql += " invoicePaid = 1 ";
            } else {
            	if (filterText.compareToIgnoreCase("None") != 0) {
            		sql += filterText + " like '" + filterValue +"' ";
            	} else {
            		sql += " invoiceStatus <> 'D' ";
            	}
            }        
             	
            if (orderBy.compareToIgnoreCase("None")== 0) {
            	orderBy = " invoiceID ";
            } 	
            
            sql += " order by " + orderBy;
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);             

                       
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	InvoiceDTO invoice = new InvoiceDTO();
            	invoice.setInvoiceID(rs.getInt(++col));
            	invoice.setVendorID(rs.getInt(++col));
            	invoice.setLocationID(rs.getInt(++col));
            	invoice.setInvoiceNumber(rs.getString(++col));
            	invoice.setInvoiceDate(rs.getDate(++col));
            	invoice.setInvoiceDueDate(rs.getDate(++col));
            	invoice.setInvoicePaid(rs.getBoolean(++col));
            	invoice.setGlcategory(rs.getString(++col));
            	invoice.setAmountOfInvoice(rs.getDouble(++col));
            	invoice.setDescription(rs.getString(++col));
            	invoice.setInvoice1099Type(rs.getInt(++col));
            	invoice.setInvoice1099Amount(rs.getDouble(++col));
            	invoice.setDiscountFlag(rs.getBoolean(++col));
            	invoice.setDiscountAmount(rs.getDouble(++col));
            	invoice.setDiscountSubjectAmount(rs.getDouble(++col));
            	invoice.setDiscountPercent(rs.getInt(++col));
            	invoice.setDiscountDollars(rs.getDouble(++col));
            	invoice.setDiscountDue( rs.getInt(++col));
            	invoice.setDiscountDueFreq( rs.getInt(++col));
            	invoice.setDiscountDueDate(rs.getDate(++col));
            	invoice.setRecurringFlag(rs.getBoolean(++col));
            	invoice.setRecurringCount(rs.getInt(++col));
            	invoice.setRecurringFreq(rs.getInt(++col));
            	invoice.setRecurringFreqCode(rs.getInt(++col));
            	invoice.setCheckingAccount(rs.getInt(++col));
            	invoice.setCheckingStatus(rs.getInt(++col));
            	invoice.setCheckCreated(rs.getBoolean(++col));
            	invoice.setInvoiceStatus(rs.getString(++col));
            	invoice.setApMasterID(rs.getInt(++col));
            	list.add(invoice);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoices() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoices() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }   
 
// CHAD 8/15/08 - COMMENTED OUT INSERT BECUASE OF TRANSACTION PROBLEMS.    
//    public InvoiceDTO addInvoice(InvoiceDTO invoice)throws Exception {
//        boolean added = false;
//        
//        try {
//            String sql = "insert into invoice (" + selectFields() + ") VALUES " +
//            		"( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
//            		"  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
//            		"  ?, ?, ?, ?, ?, ?, ?, ? )";
//            
//            int col = 0;
//            makeConnection(getDbLookup());
//            statement = conn.prepareStatement(sql);
//            
//            statement.setInt(++col, invoice.getInvoiceID() );
//            statement.setInt(++col, invoice.getVendorID() );
//            statement.setInt(++col, invoice.getLocationID() );
//            statement.setString(++col, invoice.getInvoiceNumber() );
//            statement.setDate(++col, invoice.getInvoiceDate() );
//            statement.setDate(++col, invoice.getInvoiceDueDate() );
//            statement.setBoolean(++col, invoice.isInvoicePaid() );
//            statement.setString(++col, invoice.getGlcategory() );
//            statement.setDouble(++col, invoice.getAmountOfInvoice() );
//            statement.setString(++col, invoice.getDescription() );
//            statement.setInt(++col, invoice.getInvoice1099Type() );
//            statement.setDouble(++col, invoice.getInvoice1099Amount() );
//            statement.setBoolean(++col, invoice.isDiscountFlag() );
//            statement.setDouble(++col, invoice.getDiscountAmount() );
//            statement.setDouble(++col, invoice.getDiscountSubjectAmount() );
//            statement.setDouble(++col, invoice.getDiscountPercent() );
//            statement.setDouble(++col, invoice.getDiscountDollars() );
//            statement.setInt(++col, invoice.getDiscountDue() );
//            statement.setInt(++col, invoice.getDiscountDueFreq() );
//            statement.setDate(++col, invoice.getDiscountDueDate() );
//            statement.setBoolean(++col, invoice.isRecurringFlag() );
//            statement.setInt(++col, invoice.getRecurringCount() );
//            statement.setInt(++col, invoice.getRecurringFreq() );
//            statement.setInt(++col, invoice.getRecurringFreqCode() );
//            statement.setInt(++col, invoice.getCheckingAccount() );
//            statement.setInt(++col, invoice.getCheckingStatus() );
//            statement.setBoolean(++col, invoice.isCheckCreated() );
//            statement.setString(++col, invoice.getInvoiceStatus() );
//            statement.executeUpdate();
//            added = true;
//            
//            if (added) {
//            	rs = statement.getGeneratedKeys();
//                if (rs.next()) {
//                	invoice.setInvoiceID(rs.getInt(1));
//                }
//                
//                insertAudit(invoice);
//            }
//        } catch (SQLException e) {
//            logger.error("SQLException in addInvoice() : ", e);
//            throw e;
//        } catch (Exception e) {
//            logger.error("Exception in addInvoice() : ", e);
//            throw e;
//        } finally {
//            closeConnection();
//        }
//        
//        return (invoice);
//    }
    
    public boolean updateInvoice(InvoiceDTO invoice) throws Exception {
        boolean updated = false;
        
        try {
        	
        	InvoiceDTO oldComp = getInvoice(invoice.getInvoiceID());
        	
            String sql = "UPDATE invoice set vendorID = ?, locationID = ?, invoiceNumber = ?, " +
            		"invoiceDate = ?,invoiceDueDate = ?,invoicePaid = ?,glcategory = ?," +
            		"amountOfInvoice = ?,description = ?,1099Type = ?,1099Amount = ?," +
            		"discountFlag = ?,discountAmount = ?,discountSubjectAmount = ?, " +
            		"discountPercent = ?,discountDollars = ?,discountDue = ?," +
            		"discountDueFreqCode = ?,discountDueDate = ?,recurringFlag = ?," +
            		"recurringCount = ?,recurringFreq = ?,recurringFreqCode = ?,checkingAccount = ?,checkingStatus = ?, " +
            		"checkCreated = ?, invoiceStatus = ?, apMasterID = ? WHERE invoiceID = ?";
    
            int col = 0;
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql.toString());

            statement.setInt(++col, invoice.getVendorID() );
            statement.setInt(++col, invoice.getLocationID() );
            statement.setString(++col, invoice.getInvoiceNumber() );
            statement.setDate(++col, invoice.getInvoiceDate() );
            statement.setDate(++col, invoice.getInvoiceDueDate() );
            statement.setBoolean(++col, invoice.isInvoicePaid() );
            statement.setString(++col, invoice.getGlcategory() );
            statement.setDouble(++col, invoice.getAmountOfInvoice() );
            statement.setString(++col, invoice.getDescription() );
            statement.setInt(++col, invoice.getInvoice1099Type() );
            statement.setDouble(++col, invoice.getInvoice1099Amount() );
            statement.setBoolean(++col, invoice.isDiscountFlag() );
            statement.setDouble(++col, invoice.getDiscountAmount() );
            statement.setDouble(++col, invoice.getDiscountSubjectAmount() );
            statement.setDouble(++col, invoice.getDiscountPercent() );
            statement.setDouble(++col, invoice.getDiscountDollars() );
            statement.setInt(++col, invoice.getDiscountDue() );
            statement.setInt(++col, invoice.getDiscountDueFreq() );
            statement.setDate(++col, invoice.getDiscountDueDate() );
            statement.setBoolean(++col, invoice.isRecurringFlag() );
            statement.setInt(++col, invoice.getRecurringCount() );
            statement.setInt(++col, invoice.getRecurringFreq() );
            statement.setInt(++col, invoice.getRecurringFreqCode() );
            statement.setInt(++col, invoice.getCheckingAccount() );
            statement.setInt(++col, invoice.getCheckingStatus() );
            statement.setBoolean(++col, invoice.isCheckCreated() );
        	statement.setString(++col, invoice.getInvoiceStatus());
        	statement.setInt(++col, invoice.getApMasterID() );
            statement.setInt(++col, invoice.getInvoiceID() );

            statement.executeUpdate();
            
            updateAudit(invoice, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateInvoice() : ", e);
            throw (e);
        } catch (Exception e) {
            logger.error("Exception in updateInvoice() : ", e);
            throw (e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    public boolean deleteInvoice(int InvoiceID) {
        boolean deleted = false;
        
        try {
        	InvoiceDTO oldComp = getInvoice(InvoiceID);
        	
        	String sql = "UPDATE invoice set invoiceStatus = "+ oldComp.INVOICE_DELETE +" WHERE InvoiceID = ?";
            
            int col = 0;
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, InvoiceID);
            statement.executeUpdate();
            
            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteInvoice() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteInvoice() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
