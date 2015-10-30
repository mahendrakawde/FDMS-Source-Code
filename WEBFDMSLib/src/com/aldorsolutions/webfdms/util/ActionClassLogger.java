package com.aldorsolutions.webfdms.util;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.admin.user.model.ActionClassLogDTO;


public final class ActionClassLogger extends DAO {
    
    private Logger logger = Logger.getLogger(LoginLogger.class.getName());
    
    /** Creates a new instance of LoginLogger */
    public ActionClassLogger(int userID, int companyID, int localeID) {
    	this.auditDTO.setCompanyId(companyID);
    	this.auditDTO.setUserId(userID);
    	this.auditDTO.setLocaleId(localeID);
    }
    /*
    public LoginLogger() {
    	super ( );
    }
    */
    public void logActionClass(
        String actionClassName,
        int userID,
        long startTime, 
        long stopTime,
        int executeTime
        ) {
          
    	ActionClassLogDTO actionClassLog = new ActionClassLogDTO ();
    	actionClassLog.setActionClassName(actionClassName);
    	actionClassLog.setUserID(userID);
    	actionClassLog.setStartTime(startTime);
    	actionClassLog.setStopTime(stopTime);
    	actionClassLog.setExecuteTime(executeTime);
    	
    	logActionClass ( actionClassLog );
    	
    }
    

    public void logActionClass( ActionClassLogDTO actionClassLog ) {
            
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO actionclasslog (actionclassname, userid, starttime) ");
            sql.append("VALUES (?,?,?)");
            
            makeConnection(DAO.DB_FDMSUSERS);
            int col = 0;
            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setString(++col, actionClassLog.getActionClassName());
            statement.setInt(++col, actionClassLog.getUserID());
            statement.setTimestamp(++col, new java.sql.Timestamp(actionClassLog.getStartTime()));
//            if (actionClassLog.getStopTime()==0){
//            	statement.setTimestamp(++col, null);
//            } else {
//            	statement.setTimestamp(++col, new java.sql.Timestamp(actionClassLog.getStopTime()));
//            }
//            statement.setInt(++col, actionClassLog.getExecuteTime());
            
            statement.executeUpdate();
            
            //insertAudit(actionClassLog);
           
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
            	actionClassLog.setActionClassID(rs.getInt(1));
            }
     
        } catch (SQLException e) {
            logger.error("SQLException in logActionclass() : ", e);
        } catch (Exception e) {
            logger.error("Exception in logActionClass() : ", e);
        } finally {
            closeConnection();
        }

    }
    
    public void updateLogActionClass(ActionClassLogDTO actionClassLog) {
        
        try {
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE actionclasslog set stoptime = ?, executetime = ? WHERE actionclassid = ? ");
            
            int col = 0;
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            
            statement.setTimestamp(++col, new java.sql.Timestamp(actionClassLog.getStopTime()));
            statement.setLong(++col, actionClassLog.getExecuteTime());
            statement.setLong(++col, actionClassLog.getActionClassID());            
            
            statement.executeUpdate();
            
        } catch (SQLException e) {
            logger.error("SQLException in updateLogActionClass() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateLogActionClass() : ", e);
        } finally {
            closeConnection();
        }

    }
    
 
}
