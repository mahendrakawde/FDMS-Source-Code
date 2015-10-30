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

import com.aldorassist.webfdms.model.LocationEmailDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;


public class LocationEmailDAO extends DAO {
    
    private Logger logger = Logger.getLogger(LocationEmailDAO.class.getName());
    
    public LocationEmailDAO(DbUserSession user) {
    	super (user);
    }
    
    public LocationEmailDTO getLocationEmail ( long locationEmailID ) {
    	LocationEmailDTO notify = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            
            sql.append("select LocationEmailID, CompanyID, LocaleID, LocationID, " +
            		"EmailType, EmailAddr from locationemail " +
            		"where LocationEmailID = ?" );
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, locationEmailID);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 0;
            	notify = new LocationEmailDTO();
            	notify.setLocationEmailID(rs.getLong(++col));
            	notify.setCompanyID(rs.getLong(++col));
            	notify.setLocaleID(rs.getLong(++col));
            	notify.setLocationID(rs.getLong(++col));
            	notify.setEmailType(rs.getLong(++col));
            	notify.setEmailAddress(rs.getString(++col));
            	
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getLocationEmail() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getLocationEmail() : ", e);
        } finally {
            closeConnection();
        }
        
        return notify;
    }

    public ArrayList <LocationEmailDTO> getLocationEmailByCompany ( long companyID ) {
    	ArrayList <LocationEmailDTO> elements = new ArrayList <LocationEmailDTO> ();
    	
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select LocationEmailID, CompanyID, LocaleID, LocationID, " +
            		"EmailType, EmailAddr from locationemail where CompanyID = ? ");
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	LocationEmailDTO notify = new LocationEmailDTO();
            	notify.setLocationEmailID(rs.getLong(++col));
            	notify.setCompanyID(rs.getLong(++col));
            	notify.setLocaleID(rs.getLong(++col));
            	notify.setLocationID(rs.getLong(++col));
            	notify.setEmailType(rs.getLong(++col));
            	notify.setEmailAddress(rs.getString(++col));
            	elements.add(notify);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getLocationEmailByCompany() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getLocationEmailByCompany() : ", e);
        } finally {
            closeConnection();
        }
        
        return elements;
    }
    

    public ArrayList <LocationEmailDTO> getLocationEmailByLocation ( long locationID ) {
    	ArrayList <LocationEmailDTO> elements = new ArrayList <LocationEmailDTO> ();
    	
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select LocationEmailID, CompanyID, LocaleID, LocationID, " +
            		"EmailType, EmailAddr from locationemail where LocationID = ? ");
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, locationID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	LocationEmailDTO notify = new LocationEmailDTO();
            	notify.setLocationEmailID(rs.getLong(++col));
            	notify.setCompanyID(rs.getLong(++col));
            	notify.setLocaleID(rs.getLong(++col));
            	notify.setLocationID(rs.getLong(++col));
            	notify.setEmailType(rs.getLong(++col));
            	notify.setEmailAddress(rs.getString(++col));
            	elements.add(notify);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getLocationEmailByCompany() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getLocationEmailByCompany() : ", e);
        } finally {
            closeConnection();
        }
        
        return elements;
    }
    
    public boolean addLocationEmail(LocationEmailDTO notify) {
        boolean added = false;
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            StringBuffer sql = new StringBuffer();
            
            sql.append("insert into locationemail (CompanyID, " +
            		"LocaleID, LocationID, EmailType, EmailAddr ) values " +
            		"(?, ?, ?, ?, ?)"); 
            
            int col = 0;
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            
            statement.setLong(++col, notify.getCompanyID());
            statement.setLong(++col, notify.getLocaleID());
            statement.setLong(++col, notify.getLocationID());
            statement.setLong(++col, notify.getEmailType());
            statement.setString(++col, notify.getEmailAddress());
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
            	rs = statement.getGeneratedKeys();
                if (rs.next()) {
                	notify.setLocationEmailID(rs.getInt(1));
                }
                
                auditDTO.setCompanyId((int)notify.getCompanyID());
                auditDTO.setLocaleId((int)notify.getLocaleID());
                
                insertAudit(notify);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addLocationEmail() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addLocationEmail() : ", e);
        } finally {
            closeConnection();
        }
        
        return added;
    }
    
    public boolean updateLocationEmail(LocationEmailDTO notify) {
        boolean updated = false;
        
        try {
        	
        	LocationEmailDTO oldObj = getLocationEmail(notify.getLocationEmailID());
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE locationemail SET CompanyID = ?, LocaleID = ?, LocationID = ?, " +
            		"EmailType = ?, EmailAddr = ? WHERE LocationEmailID = ?");
            
            int col = 0;
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, notify.getCompanyID());
            statement.setLong(++col, notify.getLocaleID());
            statement.setLong(++col, notify.getLocationID());
            statement.setLong(++col, notify.getEmailType());
            statement.setString(++col, notify.getEmailAddress());
            statement.setLong(++col, notify.getLocationEmailID());
            
            statement.executeUpdate();
            
            auditDTO.setCompanyId((int)notify.getCompanyID());
            auditDTO.setLocaleId((int)notify.getLocaleID());
            
            updateAudit(notify, oldObj);
            
            updated = true;
        } catch (SQLException e) {
            logger.error("SQLException in updateLocationEmail() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateLocationEmail() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }

    public boolean deleteLocationEmail(long locationEmailID) {
        boolean deleted = false;
        
        try {
        	LocationEmailDTO oldObj = getLocationEmail(locationEmailID);
        	
            StringBuffer sql = new StringBuffer();
            sql.append("delete from locationemail ");
            sql.append("WHERE (LocationEmailID = ?)");
            
            int col = 0;
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(++col, locationEmailID);
            statement.executeUpdate();

            auditDTO.setCompanyId((int)oldObj.getCompanyID());
            auditDTO.setLocaleId((int)oldObj.getLocaleID());
            
            deleteAudit(oldObj);
            
            deleted = true;
        } catch (SQLException e) {
            logger.error("SQLException in deleteLocationEmail() : ", e);
        } catch (Exception e) {
            logger.error("Exception in deleteLocationEmail() : ", e);
        } finally {
            closeConnection();
        }
        
        return deleted;
    }
    
}
