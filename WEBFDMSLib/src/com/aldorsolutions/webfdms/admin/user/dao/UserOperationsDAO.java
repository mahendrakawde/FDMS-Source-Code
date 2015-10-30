/*
 * RolesDAO.java
 *
 * Created on June 19, 2006
 */

package com.aldorsolutions.webfdms.admin.user.dao;

/**
 *
 * @author drollins
 */

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.util.DAO;



public class UserOperationsDAO extends DAO {
    
    private Logger logger = Logger.getLogger(UserOperationsDAO.class.getName());
    
    public UserOperationsDAO() {
    	super ();
    }
    
    public CompanyDTO getCompany ( long companyID ) {
    	CompanyDTO company = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT CompanyID, Name, Address1, Address2, ");
            sql.append("Address3, City, State, Country, PostCode, ");
            sql.append("BillingAddress1, BillingAddress2, ");
            sql.append("BillingAddress3, BillingCity, BillingState, ");
            sql.append("BillingCountry, BillingPostCode, Deleted, ");
            sql.append("DataURL, SQLUser, SQLPass, dbLookup, ");
            sql.append("FDMS_Cemetery, FDMS_Funeral ");
            sql.append("FROM companies where companyID = ?" );
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 0;
            	company = new CompanyDTO();
            	company.setCompanyID(rs.getInt(++col));
            	company.setName(rs.getString(++col));
            	company.setAddress1(rs.getString(++col));
            	company.setAddress2( rs.getString(++col) );
            	company.setAddress3( rs.getString(++col) );
            	company.setCity( rs.getString(++col) );
            	company.setState( rs.getString(++col) );
            	company.setCountry( rs.getString(++col) );
            	company.setPostCode( rs.getString(++col) );
            	company.setBillingAddress1(rs.getString(++col));
            	company.setBillingAddress2( rs.getString(++col) );
            	company.setBillingAddress3( rs.getString(++col) );
            	company.setBillingCity( rs.getString(++col) );
            	company.setBillingState( rs.getString(++col) );
            	company.setBillingCountry( rs.getString(++col) );
            	company.setBillingPostCode( rs.getString(++col) );
            	company.setDeleted( rs.getBoolean(++col) );
            	company.setDataURL(rs.getString(++col));
            	company.setSqlUser( rs.getString(++col) );
            	company.setSqlPass( rs.getString(++col) );
            	company.setDbLookup( rs.getString(++col) );
            	company.setCemeteryClient( rs.getBoolean(++col) );
            	company.setFuneralClient( rs.getBoolean(++col) );
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getCompany() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getCompany() : ", e);
        } finally {
            closeConnection();
        }
        
        return company;
    }


    public ArrayList getCompanies ( boolean activeOnly ) {
    	ArrayList elements = new ArrayList();
    	
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT CompanyID, Name, Address1, Address2, ");
            sql.append("Address3, City, State, Country, PostCode, ");
            sql.append("BillingAddress1, BillingAddress2, ");
            sql.append("BillingAddress3, BillingCity, BillingState, ");
            sql.append("BillingCountry, BillingPostCode, Deleted, ");
            sql.append("DataURL, SQLUser, SQLPass, dbLookup, ");
            sql.append("FDMS_Cemetery, FDMS_Funeral FROM companies ");
            
            if ( activeOnly ) {
            	sql.append ( "where deleted = '0'" );
            }
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	CompanyDTO company = new CompanyDTO();
            	company.setCompanyID(rs.getInt(++col));
            	company.setName(rs.getString(++col));
            	company.setAddress1(rs.getString(++col));
            	company.setAddress2( rs.getString(++col) );
            	company.setAddress3( rs.getString(++col) );
            	company.setCity( rs.getString(++col) );
            	company.setState( rs.getString(++col) );
            	company.setCountry( rs.getString(++col) );
            	company.setPostCode( rs.getString(++col) );
            	company.setBillingAddress1(rs.getString(++col));
            	company.setBillingAddress2( rs.getString(++col) );
            	company.setBillingAddress3( rs.getString(++col) );
            	company.setBillingCity( rs.getString(++col) );
            	company.setBillingState( rs.getString(++col) );
            	company.setBillingCountry( rs.getString(++col) );
            	company.setBillingPostCode( rs.getString(++col) );
            	company.setDeleted( rs.getBoolean(++col) );
            	company.setDataURL(rs.getString(++col));
            	company.setSqlUser( rs.getString(++col) );
            	company.setSqlPass( rs.getString(++col) );
            	company.setDbLookup( rs.getString(++col) );
            	company.setCemeteryClient( rs.getBoolean(++col) );
            	company.setFuneralClient( rs.getBoolean(++col) );
            	elements.add(company);
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
    
    public boolean addCompany(CompanyDTO company) {
        boolean added = false;
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO companies (Name, ");
            sql.append("Address1, Address2, Address3, City, State, ");
            sql.append("Country, PostCode, BillingAddress1, ");
            sql.append("BillingAddress2, BillingAddress3, ");
            sql.append("BillingCity, BillingState, BillingCountry, ");
            sql.append("BillingPostCode, Deleted, DataURL, SQLUser, ");
            sql.append("SQLPass, dbLookup, FDMS_Cemetery, FDMS_Funeral ) ");
            sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " );
            sql.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setString(++col, company.getName());
            statement.setString(++col, company.getAddress1());
            statement.setString(++col, company.getAddress2());
            statement.setString(++col, company.getAddress3());
            statement.setString(++col, company.getCity());
            statement.setString(++col, company.getState());
            statement.setString(++col, company.getCountry());
            statement.setString(++col, company.getPostCode());
            statement.setString(++col, company.getBillingAddress1());
            statement.setString(++col, company.getBillingAddress2());
            statement.setString(++col, company.getBillingAddress3());
            statement.setString(++col, company.getBillingCity());
            statement.setString(++col, company.getBillingState());
            statement.setString(++col, company.getBillingCountry());
            statement.setString(++col, company.getBillingPostCode());
            statement.setBoolean(++col, company.isDeleted());
            statement.setString(++col, company.getDataURL());
            statement.setString(++col, company.getSqlUser());
            statement.setString(++col, company.getSqlPass());
            statement.setString(++col, company.getDbLookup());
            statement.setBoolean(++col, company.isCemeteryClient());
            statement.setBoolean(++col, company.isFuneralClient());
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
            	rs = statement.getGeneratedKeys();
                if (rs.next()) {
                	company.setCompanyID(rs.getInt(1));
                }
                
                auditDTO.setCompanyId(company.getCompanyID());
                
                insertAudit(company);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addCompany() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addCompany() : ", e);
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    public boolean updateCompany(CompanyDTO company) {
        boolean updated = false;
        
        try {
        	
        	CompanyDTO oldComp = getCompany(company.getCompanyID());
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE companies SET Name = ?, ");
            sql.append("Address1 = ?, Address2 = ?, Address3 = ?, ");
			sql.append("City = ?, State = ?, Country = ?, PostCode = ?, ");
			sql.append("BillingAddress1 = ?, BillingAddress2 = ?, ");
			sql.append("BillingAddress3 = ?, BillingCity = ?, ");
			sql.append("BillingState = ?, BillingCountry = ?, ");
			sql.append("BillingPostCode = ?, Deleted = ?, ");
			sql.append("DataURL = ?, SQLUser = ?, SQLPass = ?, ");
            sql.append("dbLookup = ?, FDMS_Cemetery = ?, FDMS_Funeral = ? ");
            sql.append("WHERE (CompanyID = ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setString(++col, company.getName());
            statement.setString(++col, company.getAddress1());
            statement.setString(++col, company.getAddress2());
            statement.setString(++col, company.getAddress3());
            statement.setString(++col, company.getCity());
            statement.setString(++col, company.getState());
            statement.setString(++col, company.getCountry());
            statement.setString(++col, company.getPostCode());
            statement.setString(++col, company.getBillingAddress1());
            statement.setString(++col, company.getBillingAddress2());
            statement.setString(++col, company.getBillingAddress3());
            statement.setString(++col, company.getBillingCity());
            statement.setString(++col, company.getBillingState());
            statement.setString(++col, company.getBillingCountry());
            statement.setString(++col, company.getBillingPostCode());
            statement.setBoolean(++col, company.isDeleted());
            statement.setString(++col, company.getDataURL());
            statement.setString(++col, company.getSqlUser());
            statement.setString(++col, company.getSqlPass());
            statement.setString(++col, company.getDbLookup());
            statement.setBoolean(++col, company.isCemeteryClient());
            statement.setBoolean(++col, company.isFuneralClient());
            statement.setInt(++col, company.getCompanyID());
            statement.executeUpdate();
            
            auditDTO.setCompanyId(company.getCompanyID());
            
            updateAudit(company, oldComp);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateCompany() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateCompany() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    public boolean deleteCompany(int companyID) {
        boolean deleted = false;
        
        try {
        	CompanyDTO oldComp = getCompany(companyID);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE companies SET Deleted = ? ");
            sql.append("WHERE (CompanyID = ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setBoolean(++col, true);
            statement.setLong(++col, companyID);
            statement.executeUpdate();
            
            auditDTO.setCompanyId(companyID);
            
            deleteAudit(oldComp);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteCompany() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteCompany() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
