/*
 * RolesDAO.java
 *
 * Created on June 19, 2006
 */

package com.aldorsolutions.webfdms.ums.dao;

/**
 *
 * @author drollins
 */

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.ums.model.RolesOperationsDTO;
import com.aldorsolutions.webfdms.util.DAO;



public class RoleOperationsXrefDAO extends DAO {
    
    private Logger logger = Logger.getLogger(RoleOperationsXrefDAO.class.getName());
    
    public RoleOperationsXrefDAO() {
    	super ();
    }
    
    public ArrayList <RolesOperationsDTO> getRoleOperationsXref ( long roleID ) {
    	ArrayList <RolesOperationsDTO> elements = new ArrayList <RolesOperationsDTO>();
    	
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT ROLE_ID, OPERATION_ID " +
            		"FROM ums_role_operation_xref where ROLE_ID = ?");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, roleID);
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	RolesOperationsDTO rolesMembership = new RolesOperationsDTO();
            	rolesMembership.setRoleID(rs.getLong(++col));
            	rolesMembership.setOperationID(rs.getLong(++col));
            	elements.add(rolesMembership);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getRoleOperationsXref() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getRoleOperationsXref() : ", e);
        } finally {
            closeConnection();
        }
        
        return elements;
    }
    
    public ArrayList <RolesOperationsDTO> getRoleOperationsXref ( long operationID, long roleID ) {
    	ArrayList <RolesOperationsDTO> elements = new ArrayList <RolesOperationsDTO> ();
    	
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT ROLE_ID, OPERATION_ID " +
            		"FROM ums_role_operation_xref where OPERATION_ID = ? and ROLE_ID = ?");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, operationID);
            statement.setLong(2, roleID);
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	RolesOperationsDTO rolesMembership = new RolesOperationsDTO();
            	rolesMembership.setRoleID(rs.getLong(++col));
            	rolesMembership.setOperationID(rs.getLong(++col));
            	elements.add(rolesMembership);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getRoleOperationsXref() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getRoleOperationsXref() : ", e);
        } finally {
            closeConnection();
        }
        
        return elements;
    }
    

    public boolean addRoleOperationsXref(long operationID, long roleID) {
        boolean added = false;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO ums_role_operation_xref (ROLE_ID, OPERATION_ID) ");
            sql.append("VALUES (?, ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, roleID);
            statement.setLong(++col, operationID);
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
//            	rs = statement.getGeneratedKeys();
//                if (rs.next()) {
//                	rolesMembership.setRoleOperationsXrefID(rs.getInt(1));
//                }
                
//                auditDTO.setRoleOperationsXrefId(rolesMembership.getRoleOperationsXrefID());
                
//                insertAudit(rolesMembership);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addRoleOperationsXref() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addRoleOperationsXref() : ", e);
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    public boolean addRoleOperationsXref(RolesOperationsDTO roleOperation) {
        boolean added = false;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO ums_role_operation_xref (ROLE_ID, OPERATION_ID) ");
            sql.append("VALUES (?, ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, roleOperation.getRoleID());
            statement.setLong(++col, roleOperation.getOperationID());
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
//            	rs = statement.getGeneratedKeys();
//                if (rs.next()) {
//                	rolesMembership.setRoleOperationsXrefID(rs.getInt(1));
//                }
                
//                auditDTO.setRoleOperationsXrefId(rolesMembership.getRoleOperationsXrefID());
                
//                insertAudit(rolesMembership);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addRoleOperationsXref() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addRoleOperationsXref() : ", e);
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    public boolean updateRoleOperationsXref(RolesOperationsDTO roleOperation) {
        boolean updated = false;
        
        try {
        	
//        	RolesOperationsDTO oldComp = getRoleOperationsXref(rolesMembership.getRoleOperationsXrefID());
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE ums_role_operation_xref SET ROLE_ID, OPERATION_ID");
            sql.append("WHERE (OPERATION_ID = ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, roleOperation.getRoleID());
            statement.setLong(++col, roleOperation.getOperationID());
            statement.executeUpdate();
            
//            auditDTO.setRoleOperationsXrefId(rolesMembership.getRoleOperationsXrefID());
            
//            updateAudit(rolesMembership, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateRoleOperationsXref() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateRoleOperationsXref() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    public boolean deleteAllRoleOperationsXref(long roleID) {
        boolean deleted = false;
        
        try {
//        	RolesOperationsDTO oldComp = getRoleOperationsXref(rolesMembershipID);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("delete from ums_role_operation_xref ");
            sql.append("WHERE (OPERATION_ID = ?)"); // ROLE_ID, OPERATION_ID
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, roleID);
            statement.executeUpdate();
            
//            auditDTO.setRoleOperationsXrefId(rolesMembershipID);
            
//            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteRoleOperationsXref() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteRoleOperationsXref() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    

    public boolean deleteRoleOperationsXref(long operationID, long roleID) {
        boolean deleted = false;
        
        try {
//        	RolesOperationsDTO oldComp = getRoleOperationsXref(rolesMembershipID);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("delete from ums_role_operation_xref ");
            sql.append("WHERE (OPERATION_ID = ?) and (ROLE_ID = ?)"); // ROLE_ID, OPERATION_ID
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, operationID);
            statement.setLong(++col, roleID);
            statement.executeUpdate();
            
//            auditDTO.setRoleOperationsXrefId(rolesMembershipID);
            
//            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteRoleOperationsXref() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteRoleOperationsXref() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
