package com.aldorsolutions.webfdms.reporting.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.reporting.crystal.model.FormAvailableFilterDTO;
import com.aldorsolutions.webfdms.util.DAO;

/**
 * 
 * @author David Rollins
 * Date: Jul 20, 2007
 * File: FormAvailableFilterDAO.java	
 *
 */
public class FormAvailableFilterDAO extends DAO {
    
    private Logger logger = Logger.getLogger(FormAvailableFilterDAO.class.getName());
    
    /**
     * 
     * @param user
     */
    public FormAvailableFilterDAO(DbUserSession user) {
    	super (user);
    }
    
    /**
     * 
     * @param companyID
     * @param userId
     */
    public FormAvailableFilterDAO(long companyID, long userId) {
    	super (companyID, userId);
    }
    
    /**
     * 
     * @param formFilterID
     * @return
     */
    public FormAvailableFilterDTO getFormAvailableFilter ( long formFilterID ) {
    	FormAvailableFilterDTO formAvailableFilter = null;
        
        try {
            String sql = "select 	FormFilterID, FormID, FilterTypeID, " +
            		"FilterParameter, FilterValue from formsavailablefilter " +
            		"where FormFilterID = ?" ;
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            statement.setLong(1, formFilterID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 0;
            	formAvailableFilter = new FormAvailableFilterDTO();
            	formAvailableFilter.setFormFilterID(rs.getLong(++col));
            	formAvailableFilter.setFormID(rs.getLong(++col));
            	formAvailableFilter.setFilterTypeID(rs.getLong(++col));
            	formAvailableFilter.setFilterParameter( rs.getLong(++col) );
            	formAvailableFilter.setFilterValue( rs.getString(++col) );
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getFormAvailableFilter() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getFormAvailableFilter() : ", e);
        } finally {
            closeConnection();
        }
        
        return formAvailableFilter;
    }
    
    /**
     * 
     * @param formID
     * @return
     */
    public ArrayList <FormAvailableFilterDTO> getFormAvailableFilterByFormID ( long formID ) {
    	ArrayList <FormAvailableFilterDTO> list = new ArrayList <FormAvailableFilterDTO> ();
        
        try {
            String sql = "select FormFilterID, FormID, FilterTypeID, " +
            		"FilterParameter, FilterValue from formsavailablefilter " +
            		"where FormID = ?";
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            statement.setLong(1, formID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	FormAvailableFilterDTO formAvailableFilter = new FormAvailableFilterDTO();
            	formAvailableFilter.setFormFilterID(rs.getLong(++col));
            	formAvailableFilter.setFormID(rs.getLong(++col));
            	formAvailableFilter.setFilterTypeID(rs.getLong(++col));
            	formAvailableFilter.setFilterParameter( rs.getLong(++col) );
            	formAvailableFilter.setFilterValue( rs.getString(++col) );
            	list.add(formAvailableFilter);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getFormAvailableFilter() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getFormAvailableFilter() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }
        
    /**
     * 
     * @param formAvailableFilter
     * @return
     * @throws Exception
     */
    public boolean addFormAvailableFilter(FormAvailableFilterDTO formAvailableFilter)throws Exception {
        boolean added = false;
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            String sql = "insert into formsavailablefilter ( " +
            		"FormID, FilterTypeID, FilterParameter, FilterValue) " +
            		"VALUES ( ?, ?, ?, ? )";
            
            int col = 0;
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(++col, formAvailableFilter.getFormID());
            statement.setLong(++col, formAvailableFilter.getFilterTypeID());
            statement.setLong(++col, formAvailableFilter.getFilterParameter());
            statement.setString(++col, formAvailableFilter.getFilterValue());
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
            	rs = statement.getGeneratedKeys();
                if (rs.next()) {
                	formAvailableFilter.setFormFilterID(rs.getLong(1));
                }
                
                auditDTO.setCompanyId((int)this.companyID);
                
                insertAudit(formAvailableFilter);
            }
            
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    /**
     * 
     * @param formAvailableFilter
     * @return
     */
    public boolean updateFormAvailableFilter(FormAvailableFilterDTO 
    		formAvailableFilter) throws SQLException, Exception {
        boolean updated = false;
        
        try {
        	
        	FormAvailableFilterDTO oldComp = getFormAvailableFilter( formAvailableFilter.getFormFilterID() );
        	
            String sql = "UPDATE formsavailablefilter set FormID = ?, FilterTypeID = ?, " +
            		"FilterParameter = ?, FilterValue = ? WHERE FormFilterID = ?";
    
            int col = 0;
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            statement.setLong(++col, formAvailableFilter.getFormID());
            statement.setLong(++col, formAvailableFilter.getFilterTypeID());
            statement.setLong(++col, formAvailableFilter.getFilterParameter());
            statement.setString(++col, formAvailableFilter.getFilterValue());
            statement.setLong(++col, formAvailableFilter.getFormFilterID());
            
            statement.executeUpdate();
            
            auditDTO.setCompanyId((int)this.companyID);
            updateAudit(formAvailableFilter, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
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
    public boolean deleteFormAvailableFilter(int formFilterID) {
        boolean deleted = false;
        
        try {
        	FormAvailableFilterDTO oldComp = getFormAvailableFilter(formFilterID);
        	
            String sql = "DELETE FROM formsavailablefilter where FormFilterID = ?";
            
            int col = 0;
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
            statement.setLong(++col, formFilterID);
            statement.executeUpdate();
            
            auditDTO.setCompanyId((int)this.companyID);
            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteFormAvailableFilter() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteFormAvailableFilter() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
