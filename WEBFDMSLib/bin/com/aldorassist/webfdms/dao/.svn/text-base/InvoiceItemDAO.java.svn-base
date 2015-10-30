package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.InvoiceItemDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;

/**
 * 
 * @author David Rollins
 * Sep 6, 2007
 * InvoiceItemDAO.java
 *
 */
public class InvoiceItemDAO extends DAO {
    
    private Logger logger = Logger.getLogger(InvoiceItemDAO.class.getName());
    

    public InvoiceItemDAO(DbUserSession user) {
    	super (user);
    }
    
    public InvoiceItemDAO(long companyID, long userID) {
    	super (companyID, userID);
    }
    
    
    public InvoiceItemDTO getInvoiceItem ( long invoiceItemID ) {
    	InvoiceItemDTO invoiceItem = null;
        
        try {
            String sql = "select invoiceItemID, invoiceID, inventoryItem, itemCode, " +
            		"itemDesc, costPerUnit, quantity, glAcctNum, itemCost, apAccountID " +
            		"from invoiceitems where invoiceItemID = ?" ;
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            statement.setLong(1, invoiceItemID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 0;
            	invoiceItem = new InvoiceItemDTO();
            	invoiceItem.setInvoiceItemID(rs.getInt(++col));
            	invoiceItem.setInvoiceID(rs.getInt(++col));
            	invoiceItem.setInventoryItem(rs.getBoolean(++col));
            	invoiceItem.setItemCode(rs.getString(++col));
            	invoiceItem.setItemDesc(rs.getString(++col));
            	invoiceItem.setCostPerUnit(rs.getDouble(++col));
            	invoiceItem.setQuantity(rs.getInt(++col));
            	invoiceItem.setGlAcctNumber(rs.getString(++col));
            	invoiceItem.setItemCost(rs.getDouble(++col));
            	invoiceItem.setApAccountID(rs.getInt(++col));
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoiceItem() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoiceItem() : ", e);
        } finally {
            closeConnection();
        }
        
        return invoiceItem;
    }
    
    public ArrayList <InvoiceItemDTO> getInvoiceItems ( long invoiceID ) {
    	ArrayList <InvoiceItemDTO> list = new ArrayList <InvoiceItemDTO> ();
        
        try {
            String sql = "select invoiceItemID, invoiceID, inventoryItem, itemCode, " +
            		"itemDesc, costPerUnit, quantity, glAcctNum, itemCost, apAccountID " +
            		"from invoiceitems where invoiceID = ?";
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            
            statement.setLong(1, invoiceID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	InvoiceItemDTO invoiceItem = new InvoiceItemDTO();
            	invoiceItem.setInvoiceItemID(rs.getInt(++col));
            	invoiceItem.setInvoiceID(rs.getInt(++col));
            	invoiceItem.setInventoryItem(rs.getBoolean(++col));
            	invoiceItem.setItemCode(rs.getString(++col));
            	invoiceItem.setItemDesc(rs.getString(++col));
            	invoiceItem.setCostPerUnit(rs.getDouble(++col));
            	invoiceItem.setQuantity(rs.getInt(++col));
            	invoiceItem.setGlAcctNumber(rs.getString(++col));
            	invoiceItem.setItemCost(rs.getDouble(++col));
            	invoiceItem.setApAccountID(rs.getInt(++col));
            	list.add(invoiceItem);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoiceItems() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoiceItems() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }
        
// CHAD 8/15/08 - COMMENTED OUT INSERT BECUASE OF TRANSACTION PROBLEMS.    
//
//    public boolean addInvoiceItem(InvoiceItemDTO invoiceItem, int locationID)throws Exception {
//        boolean added = false;
//       
//        try {
//            String sql = "insert into invoiceitems (invoiceID, inventoryItem, " +
//            		"itemCode, itemDesc, costPerUnit, quantity, glAcctNum, " +
//            		"itemCost, apAccountID) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )";
//            
//            int col = 0;
//            makeConnection(getDbLookup());
//            statement = conn.prepareStatement(sql);
//            statement.setLong(++col, invoiceItem.getInvoiceID());
//            statement.setBoolean(++col, invoiceItem.isInventoryItem());
//            statement.setString(++col, invoiceItem.getItemCode());
//            statement.setString(++col, invoiceItem.getItemDesc());
//            statement.setDouble(++col, invoiceItem.getCostPerUnit());
//            statement.setInt(++col, invoiceItem.getQuantity());
//            statement.setString(++col, invoiceItem.getGlAcctNumber());
//            statement.setDouble(++col, invoiceItem.getItemCost());
//            statement.setLong(++col, invoiceItem.getApAccountID());
//            statement.executeUpdate();
//            added = true;
//            
//            if (added) {
//            	rs = statement.getGeneratedKeys();
//                if (rs.next()) {
//                	invoiceItem.setInvoiceItemID(rs.getInt(1));
//                }
//                
//                insertAudit(invoiceItem);
//            }
//        } catch (SQLException e) {
//            logger.error("SQLException in addInvoiceItem() : ", e);
//            throw e;
//        } catch (Exception e) {
//            logger.error("Exception in addInvoiceItem() : ", e);
//            throw e;
//        } finally {
//            closeConnection();
//        }
//        
//        return added;
//    }
    
    public boolean updateInvoiceItem(InvoiceItemDTO invoiceItem) throws Exception {
        boolean updated = false;
        
        try {
        	
        	InvoiceItemDTO oldComp = getInvoiceItem(invoiceItem.getInvoiceItemID());
        	
            String sql = "UPDATE invoiceitems set invoiceID = ?, inventoryItem = ?, " +
            		"itemCode = ?, itemDesc = ?, costPerUnit = ?, quantity = ?, " +
            		"glAcctNum = ?, itemCost = ?, apAccountID =? " +
            		"WHERE invoiceItemID = ?";
    
            int col = 0;
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql.toString());
            
            statement.setLong(++col, invoiceItem.getInvoiceID());
            statement.setBoolean(++col, invoiceItem.isInventoryItem());
            statement.setString(++col, invoiceItem.getItemCode());
            statement.setString(++col, invoiceItem.getItemDesc());
            statement.setDouble(++col, invoiceItem.getCostPerUnit());
            statement.setInt(++col, invoiceItem.getQuantity());
            statement.setString(++col, invoiceItem.getGlAcctNumber());
            statement.setDouble(++col, invoiceItem.getItemCost());
            statement.setLong(++col, invoiceItem.getApAccountID());
            statement.setLong(++col, invoiceItem.getInvoiceItemID());
            
            statement.executeUpdate();
            
            updateAudit(invoiceItem, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateInvoiceItem() : ", e);
            throw ( e );
        } catch (Exception e) {
            logger.error("Exception in updateInvoiceItem() : ", e);
            throw ( e );
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    public boolean deleteInvoiceItem(int InvoiceItemID) {
        boolean deleted = false;
        
        try {
        	InvoiceItemDTO oldComp = getInvoiceItem(InvoiceItemID);
        	
        	String sql = "delete from invoiceitems WHERE invoiceItemID = ?";
            
            int col = 0;
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, InvoiceItemID);
            statement.executeUpdate();
            
            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteInvoiceItem() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteInvoiceItem() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
