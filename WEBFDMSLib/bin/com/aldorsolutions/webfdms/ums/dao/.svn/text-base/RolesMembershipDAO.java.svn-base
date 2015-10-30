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

import com.aldorsolutions.webfdms.ums.model.RolesMembershipDTO;
import com.aldorsolutions.webfdms.util.DAO;



public class RolesMembershipDAO extends DAO {
    
    private Logger logger = Logger.getLogger(RolesMembershipDAO.class.getName());
    
    public RolesMembershipDAO() {
    	super ();
    }
    
    public ArrayList <RolesMembershipDTO> getRolesMembership ( long userID ) {
    	ArrayList <RolesMembershipDTO> elements = new ArrayList <RolesMembershipDTO> ();
    	
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT roleID, userID " +
            		"FROM ums_rolesmembership where userID = ?");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, userID);
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	RolesMembershipDTO rolesMembership = new RolesMembershipDTO();
            	rolesMembership.setRoleID(rs.getLong(++col));
            	rolesMembership.setUserID(rs.getLong(++col));
            	elements.add(rolesMembership);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getCompanies() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getCompanies() : ", e);
        } finally {
            closeConnection();
        }
        
        return elements;
    }
    
    public ArrayList <RolesMembershipDTO> getRolesMembership ( long userID, long roleID ) {
    	ArrayList <RolesMembershipDTO> elements = new ArrayList <RolesMembershipDTO> ();
    	
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT roleID, userID " +
            		"FROM ums_rolesmembership where userID = ? and roleID = ?");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, userID);
            statement.setLong(2, roleID);
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	RolesMembershipDTO rolesMembership = new RolesMembershipDTO();
            	rolesMembership.setRoleID(rs.getLong(++col));
            	rolesMembership.setUserID(rs.getLong(++col));
            	elements.add(rolesMembership);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getCompanies() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getCompanies() : ", e);
        } finally {
            closeConnection();
        }
        
        return elements;
    }

    public ArrayList <RolesMembershipDTO> getMembershipByRole ( long companyID, long roleID ) {
    	ArrayList <RolesMembershipDTO> elements = new ArrayList <RolesMembershipDTO> ();
    	
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT `ums_rolesmembership`.`roleID`, `usersecurity`.`UserID` " +
            		"FROM `usersecurity`, `ums_rolesmembership` " +
            		"WHERE ( `usersecurity`.`UserID` = `ums_rolesmembership`.`userID` ) AND " +
            		"( ( `ums_rolesmembership`.`roleID` = ? AND `usersecurity`.`companyID` = ? ) )");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, roleID);
            statement.setLong(2, companyID);
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	RolesMembershipDTO rolesMembership = new RolesMembershipDTO();
            	rolesMembership.setRoleID(rs.getLong(++col));
            	rolesMembership.setUserID(rs.getLong(++col));
            	elements.add(rolesMembership);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getCompanies() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getCompanies() : ", e);
        } finally {
            closeConnection();
        }
        
        return elements;
    }
    
    public boolean addRolesMembership(long userID, long roleID) {
        boolean added = false;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO ums_rolesmembership (roleID, userID) ");
            sql.append("VALUES (?, ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, roleID);
            statement.setLong(++col, userID);
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
//            	rs = statement.getGeneratedKeys();
//                if (rs.next()) {
//                	rolesMembership.setRolesMembershipID(rs.getInt(1));
//                }
                
//                auditDTO.setRolesMembershipId(rolesMembership.getRolesMembershipID());
                
//                insertAudit(rolesMembership);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addRolesMembership() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addRolesMembership() : ", e);
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    public boolean addRolesMembership(RolesMembershipDTO rolesMembership) {
        boolean added = false;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO ums_rolesmembership (roleID, userID) ");
            sql.append("VALUES (?, ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, rolesMembership.getRoleID());
            statement.setLong(++col, rolesMembership.getUserID());
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
//            	rs = statement.getGeneratedKeys();
//                if (rs.next()) {
//                	rolesMembership.setRolesMembershipID(rs.getInt(1));
//                }
                
//                auditDTO.setRolesMembershipId(rolesMembership.getRolesMembershipID());
                
//                insertAudit(rolesMembership);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addRolesMembership() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addRolesMembership() : ", e);
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    public boolean updateRolesMembership(RolesMembershipDTO rolesMembership) {
        boolean updated = false;
        
        try {
        	
//        	RolesMembershipDTO oldComp = getRolesMembership(rolesMembership.getRolesMembershipID());
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE ums_rolesmembership SET roleID = ?");
            sql.append("WHERE (userID = ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, rolesMembership.getRoleID());
            statement.setLong(++col, rolesMembership.getUserID());
            statement.executeUpdate();
            
//            auditDTO.setRolesMembershipId(rolesMembership.getRolesMembershipID());
            
//            updateAudit(rolesMembership, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateRolesMembership() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateRolesMembership() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    public boolean deleteAllRolesMembership(long userID) {
        boolean deleted = false;
        
        try {
//        	RolesMembershipDTO oldComp = getRolesMembership(rolesMembershipID);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("delete from ums_rolesmembership ");
            sql.append("WHERE (userID = ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, userID);
            statement.executeUpdate();
            
//            auditDTO.setRolesMembershipId(rolesMembershipID);
            
//            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteRolesMembership() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteRolesMembership() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    

    public boolean deleteRolesMembership(long userID, long roleID) {
        boolean deleted = false;
        
        try {
//        	RolesMembershipDTO oldComp = getRolesMembership(rolesMembershipID);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("delete from ums_rolesmembership ");
            sql.append("WHERE (userID = ?) and (roleID = ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, userID);
            statement.setLong(++col, roleID);
            statement.executeUpdate();
            
//            auditDTO.setRolesMembershipId(rolesMembershipID);
            
//            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteRolesMembership() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteRolesMembership() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
