/*
 * OperationsDAO.java
 *
 * Created on June 19, 2006
 */

package com.aldorsolutions.webfdms.ums.dao;

/**
 *
 * @author drollins
 */

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.ums.model.OperationsDTO;
import com.aldorsolutions.webfdms.util.DAO;



public class OperationsDAO extends DAO {
    
    private Logger logger = Logger.getLogger(OperationsDAO.class.getName());
    
    public OperationsDAO() {
    	super ();
    }
    
    public OperationsDTO getOperation ( long operationID ) {
    	OperationsDTO role = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT OPERATION_ID, NAME, TOKEN, RESOURCE_ID, STATUS ");
            sql.append("FROM ums_operation where OPERATION_ID = ?" );
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, operationID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 0;
            	role = new OperationsDTO();
            	role.setOperationID(rs.getLong(++col));
            	role.setName(rs.getString(++col));
            	role.setToken( rs.getString(++col));
            	role.setResourceID(rs.getLong(++col));
            	role.setStatus( rs.getBoolean(++col) );
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getOperation() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getOperation() : ", e);
        } finally {
            closeConnection();
        }
        
        return role;
    }


    public ArrayList <OperationsDTO> getOperations ( boolean activeOnly ) {
    	ArrayList <OperationsDTO> elements = new ArrayList <OperationsDTO> ();
    	
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT OPERATION_ID, NAME, TOKEN, RESOURCE_ID, STATUS from ums_operation");
            
            if ( activeOnly ) {
            	sql.append ( " where STATUS = '1'" );
            }
            
            sql.append(" order by name");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	OperationsDTO role = new OperationsDTO();
            	role.setOperationID(rs.getInt(++col));
            	role.setName(rs.getString(++col));
            	role.setToken(rs.getString(++col));
            	role.setResourceID(rs.getLong(++col));
            	role.setStatus( rs.getBoolean(++col) );
            	elements.add(role);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getOperations() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getOperations() : ", e);
        } finally {
            closeConnection();
        }
        
        return elements;
    }
    
    public boolean addOperation(OperationsDTO role) {
        boolean added = false;
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO ums_operation (NAME, TOKEN, RESOURCE_ID, STATUS) ");
            sql.append("VALUES (?, ?, ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setString(++col, role.getName());
            statement.setString(++col, role.getToken());
            statement.setLong(++col, role.getResourceID());
            statement.setBoolean(++col, role.isStatus());
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
            	rs = statement.getGeneratedKeys();
                if (rs.next()) {
                	role.setOperationID(rs.getInt(1));
                }
                
                //auditDTO.setOperationId(role.getOperationID());
                
                //insertAudit(role);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addOperation() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addOperation() : ", e);
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    public boolean updateOperation(OperationsDTO role) {
        boolean updated = false;
        
        try {
        	
//        	OperationsDTO oldComp = getOperation(role.getOperationID());
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE ums_operation SET NAME = ?, TOKEN = ?, RESOURCE_ID = ?, STATUS = ? ");
            sql.append("WHERE (OPERATION_ID = ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setString(++col, role.getName());
            statement.setString(++col, role.getToken());
            statement.setLong(++col, role.getResourceID());
            statement.setBoolean(++col, role.isStatus());
            statement.executeUpdate();
            
//            auditDTO.setOperationId(role.getOperationID());
            
//            updateAudit(role, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateOperation() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateOperation() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    public boolean deleteOperation(int roleID) {
        boolean deleted = false;
        
        try {
//        	OperationsDTO oldComp = getOperation(roleID);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE ums_operation SET Status = ? ");
            sql.append("WHERE (OPERATION_ID = ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setBoolean(++col, true);
            statement.setLong(++col, roleID);
            statement.executeUpdate();
            
//            auditDTO.setOperationId(roleID);
            
//            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteOperation() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteOperation() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
