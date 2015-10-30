/*
 * PasswordLogDAO.java
 *
 * Created on June 19, 2006
 */

package com.aldorsolutions.webfdms.security.dao;

/**
 *
 * @author drollins
 */

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.security.model.PasswordLogDTO;
import com.aldorsolutions.webfdms.util.DAO;


public class PasswordLogDAO extends DAO {
    
	private Logger logger = Logger.getLogger(PasswordLogDAO.class.getName());
    
    public PasswordLogDAO () {
    	super ( );
    }
    
    public ArrayList getPasswordLog ( long userID ) {
    	PasswordLogDTO settings = null;
    	ArrayList elements = new ArrayList ();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT passwordLogID, userID, password, tmstmp ");
            sql.append("FROM passwordlog where userid = ?");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, userID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	settings = new PasswordLogDTO();
            	settings.setPasswordLogID(rs.getLong(++col));
            	settings.setUserID(rs.getLong(++col));
            	settings.setPassword(rs.getString(++col));
            	settings.setTmstmp(rs.getTimestamp(++col));
            	elements.add(settings);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getPasswordLog() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getPasswordLog() : ", e);
        } finally {
            closeConnection();
        }
        
        return elements;
    }

    public boolean addPasswordLog( PasswordLogDTO settings, int companyID ) {
        boolean added = false;
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO passwordlog (passwordLogID, userID, ");
            sql.append("password, tmstmp) VALUES (?, ?, ?, ?)");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setLong(++col, settings.getPasswordLogID());
            statement.setLong(++col, settings.getUserID());
            statement.setString(++col, settings.getPassword());
            statement.setTimestamp(++col, settings.getTmstmp());
            
            statement.executeUpdate();
            added = true;
            
            if (added) {
            	rs = statement.getGeneratedKeys();
                if (rs.next()) {
                	settings.setPasswordLogID(rs.getLong(1));
                }
                
                getAuditDTO().setCompanyId(companyID);
                
                insertAudit(settings);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in addPasswordLog() : ", e);
        } catch (Exception e) {
            logger.error("Exception in addPasswordLog() : ", e);
        } finally {
            closeConnection();
        }
        
        return added;
    }

}
