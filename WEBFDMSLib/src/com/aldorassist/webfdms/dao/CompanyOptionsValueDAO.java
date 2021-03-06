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

import com.aldorassist.webfdms.model.CompanyOptionsValueDTO;
import com.aldorsolutions.webfdms.util.DAO;


public class CompanyOptionsValueDAO extends DAO {
    
    private Logger logger = Logger.getLogger(CompanyOptionsValueDAO.class.getName());
    
    public CompanyOptionsValueDAO() {
    	super ();
    }
    
    public CompanyOptionsValueDTO getCompanyOptionValue ( long companyOptionValueID ) {
    	CompanyOptionsValueDTO companyOption = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select companyOptionValueID, companyOptionID, companyID, value " +
            		"from companyoptionvalues where companyOptionValueID = ?" );
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyOptionValueID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 0;
            	companyOption = new CompanyOptionsValueDTO();
            	companyOption.setCompanyOptionValueID(rs.getLong(++col));
            	companyOption.setCompanyOptionID(rs.getLong(++col));
            	companyOption.setCompanyID(rs.getLong(++col));
            	companyOption.setValue(rs.getInt(++col));
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getCompanyOptionValue() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getCompanyOptionValue() : ", e);
        } finally {
            closeConnection();
        }
        
        return companyOption;
    }
    
    public ArrayList <CompanyOptionsValueDTO> getCompanyOptionValues ( long companyID ) {
    	ArrayList <CompanyOptionsValueDTO> list = new ArrayList <CompanyOptionsValueDTO> ();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select companyOptionValueID, companyOptionID, companyID, value " +
            		"from companyoptionvalues where companyID = ?" );
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	CompanyOptionsValueDTO companyOption = new CompanyOptionsValueDTO();
            	companyOption.setCompanyOptionValueID(rs.getLong(++col));
            	companyOption.setCompanyOptionID(rs.getLong(++col));
            	companyOption.setCompanyID(rs.getLong(++col));
            	companyOption.setValue(rs.getInt(++col));
            	list.add(companyOption);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getCompanyOptionValues() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getCompanyOptionValues() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }
    
    public ArrayList getCompanyOptionValues ( long companyID, long optionID ) {
    	ArrayList <CompanyOptionsValueDTO> list = new ArrayList <CompanyOptionsValueDTO> ();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select companyOptionValueID, companyOptionID, companyID, value " +
            		"from companyoptionvalues where companyID = ? AND companyOptionID = ?" );
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            statement.setLong(2, optionID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	CompanyOptionsValueDTO companyOption = new CompanyOptionsValueDTO();
            	companyOption.setCompanyOptionValueID(rs.getLong(++col));
            	companyOption.setCompanyOptionID(rs.getLong(++col));
            	companyOption.setCompanyID(rs.getLong(++col));
            	companyOption.setValue(rs.getInt(++col));
            	list.add(companyOption);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getCompanyOptionValues() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getCompanyOptionValues() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }
        
    public boolean addCompanyOptionValue(CompanyOptionsValueDTO companyOptionValue)throws Exception {
        boolean added = false;
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO companyoptionvalues " +
            		"(companyOptionID, companyID, value) ");
            sql.append("VALUES ( ?, ?, ? )");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            statement.setLong(++col, companyOptionValue.getCompanyOptionID());
            statement.setLong(++col, companyOptionValue.getCompanyID());
            statement.setInt(++col, companyOptionValue.getValue());
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
            	rs = statement.getGeneratedKeys();
                if (rs.next()) {
                	companyOptionValue.setCompanyOptionValueID(rs.getInt(1));
                }
                
                //insertAudit(companyOptionValue);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addCompanyOptionValue() : ", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception in addCompanyOptionValue() : ", e);
            throw e;
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    public boolean updateCompanyOptionValue(CompanyOptionsValueDTO companyOptionValue) throws Exception {
        boolean updated = false;
        
        try {
        	
        	CompanyOptionsValueDTO oldComp = getCompanyOptionValue(companyOptionValue.getCompanyOptionValueID());
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE companyoptionvalues set companyOptionID = ?, companyID = ?, value = ? " +
            		"WHERE companyOptionValueID = ?");
    
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());

            statement.setLong(++col, companyOptionValue.getCompanyOptionID());
            statement.setLong(++col, companyOptionValue.getCompanyID());
            statement.setInt(++col, companyOptionValue.getValue());
            statement.setLong(++col, companyOptionValue.getCompanyOptionValueID());
            
            statement.executeUpdate();
            
            //updateAudit(companyOptionValue, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateCompanyOptionValue() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateCompanyOptionValue() : ", e);
            throw e;
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    public boolean deleteCompanyOptionValue(int companyOptionValueID) {
        boolean deleted = false;
        
        try {
        	CompanyOptionsValueDTO oldComp = getCompanyOptionValue(companyOptionValueID);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM companyoptionvalues where companyOptionValueID = ?");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, companyOptionValueID);
            statement.executeUpdate();
            
            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteCompanyOptionValue() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteCompanyOptionValue() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
