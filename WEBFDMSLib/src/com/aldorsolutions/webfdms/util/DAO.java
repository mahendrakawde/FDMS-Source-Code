package com.aldorsolutions.webfdms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.AuditDTO;
import com.aldorsolutions.webfdms.audit.client.AuditClient;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;


/**
 * Workfile: DAO.java
 * Date: Nov 18, 2005 10:30:01 AM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public abstract class DAO {
	
    private static Logger logger = Logger.getLogger(DAO.class.getName());
    // added by mahendra for testing
    //public static final String DB_AUDIT = "java:jdbc/AuditDS";
    public static final String DB_AUDIT = "java:jboss/datasources/AuditDS";
    //added by mahendra for testing
    //public static final String DB_FDMSUSERS = "java:jdbc/WEBFDMS";
    public static final String DB_FDMSUSERS = "java:jboss/datasources/WEBFDMS";
    public static final String DB_FDMSCOMMON = "java:jdbc/FDMS_Common";
    public static final String DB_FDMSSHARE = "java:jdbc/FDMS_Share";
    public static final String DB_DATABASEBUILDER = "java:jdbc/FDMS_DataBaseBuilder";
    public static final String DB_LIFE_FILES = "java:jdbc/FDMS_Lifefiles";

    protected Connection conn = null;
    protected PreparedStatement statement = null;
    protected ResultSet rs = null;	
    protected AuditDTO auditDTO;
    protected long userID = 0;
    protected long companyID = 0;
    protected String dbLookup = null;
    
    /** whether the dao should local settings or through the context lookup */
    public static boolean useGlobalSettings = false;
    
    public DAO( ) { 
    	this.auditDTO = new AuditDTO();
    }

    /** Creates a new instance of DAO */
    public DAO(String dbLookup) {
    	this();
    	this.dbLookup = dbLookup;
    }
    
    /** Creates a new instance of DAO */
    public DAO(DbUserSession user) {
    	this();
    	this.companyID = user.getCompanyID();
    	this.userID = user.getId();
    	this.dbLookup = user.getDbLookup();
    }

    /** Creates a new instance of DAO */
    public DAO ( long companyID, long userId ) {
    	this();
    	this.companyID = companyID;
    	this.userID = userId;
    	this.dbLookup = getCompany(companyID).getDbLookup();
    }
    
	private CompanyDTO getCompany ( long companyID ) {
		return ( new CompanyManagerBean().getCompany((int)companyID) );
	}
    
	protected AuditDTO getAuditDTO () {
    	return ( this.auditDTO );
    }
    
    /**
     * @deprecated Please use makeConnection ( String jndiLookup )
     */ 
    protected void makeConnection() throws Exception {
    	makeConnection ( DAO.DB_FDMSUSERS );
    }
    
    protected void makeConnection (String jndiLookup) throws Exception {
    	
    	try {
            conn = UtilSingleton.getInstance().getConnectionFromCache(jndiLookup);
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
        
        if (conn == null) {
            throw new SQLException("Unable to get connection from pool.");
        }
        
    }
    
    protected void makeConnection (String jndiLookup, boolean globalSetting) throws Exception {
    	
    	setUseGlobalSettings(globalSetting);
    	makeConnection (jndiLookup);
        
    }
    
    protected void closeConnection() {
        try {
            if (rs != null) {
            	rs.close();
            	rs = null;
            }
            
        } catch (SQLException e) {
            logger.error("SQL Exception in closing result set : ", e);
        }
        
        try {
            if (statement != null) {
            	statement.close();
            	statement = null;
            }
        } catch (SQLException e) {
            logger.error("SQL Exception in closing prepared statement : ", e);
        }
        
        try {
            if (conn != null) {
            	
            	if(!isUseGlobalSettings()){
	            	if ( conn.getAutoCommit() ) {
	            		conn.setAutoCommit(false);
	            		conn.commit();
	            		conn.close();
	            		conn = null;
	            	} else {
	            		conn.commit();
	            		conn.close();
	            		conn = null;
	            	}
            	}else{
            		conn.close();
            		conn = null;
            	}
            
            	
            }
        } catch (SQLException e) {
            logger.error("SQL Exception in closing connection : " + e.getErrorCode(), e);
        } catch (Exception e) {
        	logger.error("Exception in closing connection : ", e);
        	
        	try {
                if (conn != null) conn.close();
            } catch (SQLException x) {
                logger.error("SQL Exception in closing prepared statement : ", x);
            }
        } 
        finally {         	
        	try {
                if (conn != null) conn.close();
            } catch (SQLException x) {
                logger.error("SQL Exception in closing prepared statement : ", x);
            }
        }
    }
    

    protected void updateAudit ( Object oldObj, Object newObj ) {
		StringBuffer msg = new StringBuffer();
		String objWasStr = null;
		String objIsStr = null;
		String className = null;
		
		if ( oldObj != null ) {
			objWasStr = ReflectionUtil.getFieldValues(oldObj);
			className = oldObj.getClass().getName();
		}
		
		if ( newObj != null ) {
			objIsStr = ReflectionUtil.getFieldValues(newObj);
			className = newObj.getClass().getName();
		}
		
		// Perform Audit Details
		// populate audit obj with details
		msg.append("Object : " + className + "\n");
		msg.append("What obj was : " + objWasStr + "\n");
		msg.append("What it changed to : " + objIsStr );
		
		auditDTO.setMessage(msg.toString());
		logger.debug("Audit Message: " + msg.toString());
		AuditClient auditClient = new AuditClient(auditDTO.getCompanyId());
		auditClient.record(auditDTO); 
    }
    
    protected void insertAudit (Object newObj) {
    	updateAudit ( null, newObj );
    }
    
    protected void deleteAudit ( Object delObj ) {
    	updateAudit ( delObj, null );
    }
    
    protected void deleteAudit ( Object delObj, Object newObj ) {
    	updateAudit ( delObj, newObj );
    }
    
    /**
     * Select the database as default database
     * @param dataBaseName Database Name
     * @param dbLookup JNDI name for connection pool
     * @return true if successful else false
     */
    public boolean selectDataBase(String dataBaseName, String dbLookup ) {
		boolean result = false;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("use `");
			sql.append(dataBaseName);
			sql.append("`;");

			makeConnection(dbLookup);
			Statement statement = conn.createStatement();
			int i = -1;
			
			i = statement.executeUpdate(sql.toString());

			if (i >= 0) {
				result = true;
				insertAudit(sql.toString());
				logger.debug("Successfully selected database: "  + dataBaseName);
			}

		} catch (SQLException e) {
			logger.error("SQLException in selectDataBase() : ", e);
			return result;
		} catch (Exception e) {
			logger.error("Exception in selectDataBase() : ", e);
			return result;
		} finally {
			closeConnection();
		}
		return result;
	}

	/**
	 * @return the useGlobalSettings
	 */
	protected static boolean isUseGlobalSettings() {
		return useGlobalSettings;
	}

	/**
	 * @param useGlobalSettings the useGlobalSettings to set
	 */
	protected static void setUseGlobalSettings(boolean useGlobalSettings) {
		DAO.useGlobalSettings = useGlobalSettings;
	}

	/**
	 * @return the dbLookup
	 */
	protected String getDbLookup() {
		return dbLookup;
	}

	/**
	 * @param dbLookup the dbLookup to set
	 */
	public void setDbLookup(String dbLookup) {
		this.dbLookup = dbLookup;
	}
}
