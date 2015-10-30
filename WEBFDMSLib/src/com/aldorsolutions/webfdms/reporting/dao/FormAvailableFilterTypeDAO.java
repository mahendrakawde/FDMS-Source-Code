package com.aldorsolutions.webfdms.reporting.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.reporting.crystal.model.FormAvailableFilterTypeDTO;
import com.aldorsolutions.webfdms.util.DAO;

/**
 * 
 * @author David Rollins
 * Date: Jul 20, 2007
 * File: FormAvailableFilterTypeDAO.java	
 *
 */
public class FormAvailableFilterTypeDAO extends DAO {
    
    private Logger logger = Logger.getLogger(FormAvailableFilterTypeDAO.class.getName());
    
    /**
     * 
     * @param user
     */
    public FormAvailableFilterTypeDAO(DbUserSession user) {
    	super (user);
    }
    
    /**
     * 
     * @param companyID
     * @param userId
     */
    public FormAvailableFilterTypeDAO(long companyID, long userId) {
    	super (companyID, userId);
    }
    
    /**
     * 
     * @param formFilterID
     * @return
     */
    public FormAvailableFilterTypeDTO getFormAvailableFilterType ( long formFilterTypeID ) {
    	FormAvailableFilterTypeDTO filterType = null;
        
        try {
            String sql = "select FormFilterTypeID, filtername, description, defaultParameter, " +
            		"defaultValue from formsavailablefiltertype where FormFilterTypeID = ?" ;
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            statement.setLong(1, formFilterTypeID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 0;
            	filterType = new FormAvailableFilterTypeDTO();
            	filterType.setFormFilterTypeID(rs.getInt(++col));
            	filterType.setFilterName(rs.getString(++col));
            	filterType.setDescription(rs.getString(++col));
            	filterType.setDefaultParameter( rs.getInt(++col) );
            	filterType.setDefaultValue( rs.getInt(++col) );
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getFormAvailableFilter() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getFormAvailableFilter() : ", e);
        } finally {
            closeConnection();
        }
        
        return filterType;
    }
    
    /**
     * 
     * @param formID
     * @return
     */
    public ArrayList <FormAvailableFilterTypeDTO> getFormAvailableFilterType ( ) {
    	ArrayList <FormAvailableFilterTypeDTO> list = new ArrayList <FormAvailableFilterTypeDTO> ();
        
        try {
            String sql = "select FormFilterTypeID, filtername, description, defaultParameter, " +
            		"defaultValue from formsavailablefiltertype ";
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	FormAvailableFilterTypeDTO filterType = new FormAvailableFilterTypeDTO();
            	filterType.setFormFilterTypeID(rs.getInt(++col));
            	filterType.setFilterName(rs.getString(++col));
            	filterType.setDescription(rs.getString(++col));
            	filterType.setDefaultParameter( rs.getInt(++col) );
            	filterType.setDefaultValue( rs.getInt(++col) );
            	list.add(filterType);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getFormAvailableFilterType() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getFormAvailableFilterType() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }
        
    /**
     * 
     * @param filterType
     * @return
     * @throws Exception
     */
    public boolean addFormAvailableFilterType(FormAvailableFilterTypeDTO filterType)throws Exception {
        boolean added = false;
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            String sql = "insert into formsavailablefiltertype ( " +
            		"filtername, description, defaultParameter, " +
            		"defaultValue) " +
            		"VALUES ( ?, ?, ?, ? )";
            
            int col = 0;
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(++col, filterType.getFilterName());
            statement.setString(++col, filterType.getDescription());
            statement.setLong(++col, filterType.getDefaultParameter());
            statement.setLong(++col, filterType.getDefaultValue());
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
            	rs = statement.getGeneratedKeys();
                if (rs.next()) {
                	filterType.setFormFilterTypeID(rs.getInt(1));
                }
                
                insertAudit(filterType);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addFormAvailableFilterType() : ", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception in addFormAvailableFilterType() : ", e);
            throw e;
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    /**
     * 
     * @param filterType
     * @return
     */
    public boolean updateFormAvailableFilterType(FormAvailableFilterTypeDTO filterType) {
        boolean updated = false;
        
        try {
        	
        	FormAvailableFilterTypeDTO oldComp = getFormAvailableFilterType( filterType.getFormFilterTypeID() );
        	
            String sql = "UPDATE formsavailablefiltertype set filtername = ?, description = ?, " +
            		"defaultParameter = ?, " +
            		"defaultValue = ? WHERE FormFilterTypeID = ?";
    
            int col = 0;
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            statement.setString(++col, filterType.getFilterName());
            statement.setString(++col, filterType.getDescription());
            statement.setLong(++col, filterType.getDefaultParameter());
            statement.setLong(++col, filterType.getDefaultValue());
            statement.setLong(++col, filterType.getFormFilterTypeID());
            
            statement.executeUpdate();
            
            updateAudit(filterType, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateFormAvailableFilterType() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateFormAvailableFilterType() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    /**
     * 
     * @param formFilterID
     * @return
     */
    public boolean deleteFormAvailableFilterType(int formFilterTypeID) {
        boolean deleted = false;
        
        try {
        	FormAvailableFilterTypeDTO oldComp = getFormAvailableFilterType(formFilterTypeID);
        	
            String sql = "DELETE FROM formsavailablefiltertype where FormFilterTypeID = ?";
            
            int col = 0;
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            statement.setLong(++col, formFilterTypeID);
            statement.executeUpdate();
            
            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteFormAvailableFilterType() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteFormAvailableFilterType() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
