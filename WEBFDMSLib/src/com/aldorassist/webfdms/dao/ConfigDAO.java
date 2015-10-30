package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.ConfigDTO;
import com.aldorsolutions.webfdms.util.DAO;

/**
 * 
 * @author David Rollins
 * Sep 6, 2007
 * ConfigDAO.java
 *
 */
public class ConfigDAO extends DAO {
    
    private Logger logger = Logger.getLogger(ConfigDAO.class.getName());
    
    public ConfigDAO() {
    	super ();
    }
    
    public ConfigDTO getConfig ( long configID ) {
    	ConfigDTO config = null;
        
        try {
            String sql = "select ConfigID, Name, Inactive from config where ConfigID = ?" ;
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql);
            statement.setLong(1, configID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 0;
            	config = new ConfigDTO();
            	config.setConfigID(rs.getLong(++col));
            	config.setName(rs.getString(++col));
            	config.setInactive(rs.getBoolean(++col));
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getConfig() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getConfig() : ", e);
        } finally {
            closeConnection();
        }
        
        return config;
    }
    
    public ArrayList <ConfigDTO> getConfigs ( boolean activeOnly ) {
    	ArrayList <ConfigDTO> list = new ArrayList <ConfigDTO> ();
        
        try {
            String sql = "select ConfigID, Name, Inactive from config";
            
            if ( activeOnly ) {
            	sql += " where Inactive = ?";
            }
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql);
            
            if ( activeOnly ) {
                statement.setBoolean(1, false);
            }
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	ConfigDTO config = new ConfigDTO();
            	config.setConfigID(rs.getLong(++col));
            	config.setName(rs.getString(++col));
            	config.setInactive(rs.getBoolean(++col));
            	list.add(config);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getConfigs() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getConfigs() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }
        
    public boolean addConfig(ConfigDTO config)throws Exception {
        boolean added = false;
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            String sql = "insert into config (Name, Inactive) VALUES ( ?, ? )";
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(++col, config.getName());
            statement.setBoolean(++col, config.isInactive());
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
            	rs = statement.getGeneratedKeys();
                if (rs.next()) {
                	config.setConfigID(rs.getInt(1));
                }
                
                insertAudit(config);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addConfig() : ", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception in addConfig() : ", e);
            throw e;
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    public boolean updateConfig(ConfigDTO config) {
        boolean updated = false;
        
        try {
        	
        	ConfigDTO oldComp = getConfig(config.getConfigID());
        	
            String sql = "UPDATE config set name = ?, Inactive = ? WHERE configID = ?";
    
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setString(++col, config.getName());
            statement.setLong(++col, config.getConfigID());
            statement.setBoolean(++col, config.isInactive());
            statement.executeUpdate();
            
            updateAudit(config, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateConfig() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateConfig() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    public boolean deleteConfig(int configID) {
        boolean deleted = false;
        
        try {
        	ConfigDTO oldComp = getConfig(configID);
        	
        	String sql = "UPDATE config set Inactive = ? WHERE configID = ?";
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, configID);
            statement.setBoolean(++col, true);
            statement.executeUpdate();
            
            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteConfig() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteConfig() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
