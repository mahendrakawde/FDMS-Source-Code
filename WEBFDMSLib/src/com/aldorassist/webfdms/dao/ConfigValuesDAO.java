package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.ConfigValueDTO;
import com.aldorsolutions.webfdms.util.DAO;

/**
 * 
 * @author David Rollins
 * Sep 6, 2007
 * ConfigValuesDAO.java
 *
 */
public class ConfigValuesDAO extends DAO {
    
    private Logger logger = Logger.getLogger(ConfigValuesDAO.class.getName());
    
    public ConfigValuesDAO() {
    	super ();
    }
    
    public ConfigValueDTO getConfigValue ( long configValueID ) {
    	ConfigValueDTO configValue = null;
        
        try {
            String sql = "select ConfigValueID, ConfigID, ConfigCode, Value from configvalues where ConfigValueID = ?";
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql);
            statement.setLong(1, configValueID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 0;
            	configValue = new ConfigValueDTO();
            	configValue.setConfigValueID(rs.getLong(++col));
            	configValue.setConfigID(rs.getLong(++col));
            	configValue.setConfigCode(rs.getString(++col));
            	configValue.setValue( rs.getString(++col) );
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getConfigValue() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getConfigValue() : ", e);
        } finally {
            closeConnection();
        }
        
        return configValue;
    }
    
    public ArrayList <ConfigValueDTO> getConfigValues ( long configID ) {
    	ArrayList <ConfigValueDTO> list = new ArrayList <ConfigValueDTO> ();
        
        try {
        	String sql = "select ConfigValueID, ConfigID, ConfigCode, Value from configvalues where ConfigID = ?";
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql);
            statement.setLong(1, configID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	ConfigValueDTO configValue = new ConfigValueDTO();
            	configValue.setConfigValueID(rs.getLong(++col));
            	configValue.setConfigID(rs.getLong(++col));
            	configValue.setConfigCode(rs.getString(++col));
            	configValue.setValue( rs.getString(++col) );
            	list.add(configValue);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getConfigValue() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getConfigValue() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }

    public ArrayList <ConfigValueDTO> getConfigValues ( ) {
    	ArrayList <ConfigValueDTO> list = new ArrayList <ConfigValueDTO> ();
        
        try {
        	String sql = "select ConfigValueID, ConfigID, ConfigCode, Value from configvalues";
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	ConfigValueDTO configValue = new ConfigValueDTO();
            	configValue.setConfigValueID(rs.getLong(++col));
            	configValue.setConfigID(rs.getLong(++col));
            	configValue.setConfigCode(rs.getString(++col));
            	configValue.setValue( rs.getString(++col) );
            	list.add(configValue);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getConfigValue() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getConfigValue() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }
        
    public boolean addConfigValue(ConfigValueDTO configValue)throws Exception {
        boolean added = false;
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            String sql = "insert into configvalues (ConfigID, ConfigCode, Value) " +
            		"values (?, ?, ?)";
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setLong(++col, configValue.getConfigID());
            statement.setString(++col, configValue.getConfigCode());
            statement.setString(++col, configValue.getValue());
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
            	rs = statement.getGeneratedKeys();
                if (rs.next()) {
                	configValue.setConfigValueID(rs.getInt(1));
                }
                
                insertAudit(configValue);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addConfigValue() : ", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception in addConfigValue() : ", e);
            throw e;
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    public boolean updateConfigValue(ConfigValueDTO configValue) {
        boolean updated = false;
        
        try {
        	
        	ConfigValueDTO oldComp = getConfigValue(configValue.getConfigValueID());
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE configvalues set ConfigID = ?, ConfigCode = ?, Value = ? WHERE configValueID = ?");
    
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, configValue.getConfigID());
            statement.setString(++col, configValue.getConfigCode());
            statement.setString(++col, configValue.getValue());
            statement.setLong(++col, configValue.getConfigValueID());
            
            statement.executeUpdate();
            
            updateAudit(configValue, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateConfigValue() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateConfigValue() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    public boolean deleteConfigValue(int configValueID) {
        boolean deleted = false;
        
        try {
        	ConfigValueDTO oldComp = getConfigValue(configValueID);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM configvalues where configValueID = ?");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, configValueID);
            statement.executeUpdate();
            
            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteConfigValue() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteConfigValue() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
