/*
 * RolesDAO.java
 *
 * Created on June 19, 2006
 */

package com.aldorassist.webfdms.dao;

/**
 *
 * @author drollins
 */

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.util.DAO;


public class CompanyOptionsDAO extends DAO {
    
    private Logger logger = Logger.getLogger(CompanyOptionsDAO.class.getName());
    
    public CompanyOptionsDAO() {
    	super ();
    }
    
    public CompanyOptionsDTO getCompanyOption ( long companyOptionID ) {
    	CompanyOptionsDTO companyOption = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select companyOptionID, name, description, defaultValue " +
            		"from companyoptions where companyOptionID = ?" );
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyOptionID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 0;
            	companyOption = new CompanyOptionsDTO();
            	companyOption.setCompanyOptionID(rs.getLong(++col));
            	companyOption.setName(rs.getString(++col));
            	companyOption.setDescription(rs.getString(++col));
            	companyOption.setDefaultValue( rs.getInt(++col) );
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getCompanyOption() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getCompanyOption() : ", e);
        } finally {
            closeConnection();
        }
        
        return companyOption;
    }
    
    public ArrayList <CompanyOptionsDTO> getCompanyOptions ( ) {
    	ArrayList <CompanyOptionsDTO> list = new ArrayList <CompanyOptionsDTO> ();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select companyOptionID, name, description, defaultValue " +
            		"from companyoptions" );
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	CompanyOptionsDTO companyOption = new CompanyOptionsDTO();
            	companyOption.setCompanyOptionID(rs.getLong(++col));
            	companyOption.setName(rs.getString(++col));
            	companyOption.setDescription(rs.getString(++col));
            	companyOption.setDefaultValue( rs.getInt(++col) );
            	list.add(companyOption);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getCompanyOptions() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getCompanyOptions() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }
        
    public boolean addCompanyOption(CompanyOptionsDTO companyOption)throws Exception {
        boolean added = false;
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO companyoptions " +
            		"(name, description, defaultValue) ");
            sql.append("VALUES ( ?, ?, ? )");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setString(++col, companyOption.getName());
            statement.setString(++col, companyOption.getDescription());
            statement.setInt(++col, companyOption.getDefaultValue());
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
            	rs = statement.getGeneratedKeys();
                if (rs.next()) {
                	companyOption.setCompanyOptionID(rs.getInt(1));
                }
                
                insertAudit(companyOption);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addCompanyOption() : ", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception in addCompanyOption() : ", e);
            throw e;
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    public boolean updateCompanyOption(CompanyOptionsDTO companyOption) {
        boolean updated = false;
        
        try {
        	
        	CompanyOptionsDTO oldComp = getCompanyOption(companyOption.getCompanyOptionID());
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE companyoptions set name = ?, description = ?, " );
            sql.append("defaultValue = ? WHERE companyOptionID = ?");
    
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setString(++col, companyOption.getName());
            statement.setString(++col, companyOption.getDescription());
            statement.setInt(++col, companyOption.getDefaultValue());
            statement.setLong(++col, companyOption.getCompanyOptionID());
    
            statement.executeUpdate();
            
            updateAudit(companyOption, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateCompanyOption() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateCompanyOption() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    public boolean deleteCompanyOption(int companyOptionID) {
        boolean deleted = false;
        
        try {
        	CompanyOptionsDTO oldComp = getCompanyOption(companyOptionID);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM companyoptions where companyOptionID = ?");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, companyOptionID);
            statement.executeUpdate();
            
            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteCompanyOption() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteCompanyOption() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
