package com.aldorsolutions.webfdms.util;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.InvoiceDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;



public final class EmailLogger extends DAO {
    
    private Logger logger = Logger.getLogger(LoginLogger.class.getName());
    
    /** Creates a new instance of LoginLogger */
    public EmailLogger(DbUserSession user) {
    	super(user);
    }
    /*
    public LoginLogger() {
    	super ( );
    }
    */
    public void emailLog(
        long startTime, 
        long stopTime,
        int executeTime,
        String emailTo,
        String emailSubject,
        String emailBody
        ) {
          
    	EmailLogDTO emailLog = new EmailLogDTO ();
    	emailLog.setStartTime(startTime);
    	emailLog.setStopTime(stopTime);
    	emailLog.setExecuteTime(executeTime);
    	emailLog.setEmailTo(emailTo);
    	emailLog.setEmailSubject(emailSubject);
    	emailLog.setEmailBody(emailBody);
    	
    	emailLog ( emailLog );
    	
    }
    

    public void emailLog ( EmailLogDTO emailLog ) {
            
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO emaillog (starttime,emailTo,emailSubject,emailBody) ");
            sql.append("VALUES (?,?,?,?)");
            
            makeConnection(getDbLookup());
            int col = 0;
            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(++col,  new java.sql.Timestamp(emailLog.getStartTime()));
            statement.setString(++col, emailLog.getEmailTo());
            statement.setString(++col, emailLog.getEmailSubject());
            statement.setString(++col, emailLog.getEmailBody());
            
            statement.executeUpdate();
            
            //insertAudit(actionClassLog);
           
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
            	emailLog.setLogID(rs.getInt(1));
            }
     
        } catch (SQLException e) {
            logger.error("SQLException in emailLogClass() : ", e);
        } catch (Exception e) {
            logger.error("Exception in emailLogClass() : ", e);
        } finally {
            closeConnection();
        }

    }
    
    public void emailLogAll ( EmailLogDTO emailLog ) {
        
        try {
        	logger.info("In " + this.getClass() + " " + Thread.currentThread().getStackTrace()[1].getMethodName() + " is been called");
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO emaillog (starttime,stoptime, executetime, emailTo, emailCC, emailBCC, emailSubject,emailBody, className) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?)");
            
            makeConnection(getDbLookup());
            int col = 0;
            statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(++col,  new java.sql.Timestamp(emailLog.getStartTime()));
            statement.setTimestamp(++col,  new java.sql.Timestamp(emailLog.getStopTime()));
            statement.setInt(++col, emailLog.getExecuteTime());
            statement.setString(++col, emailLog.getEmailTo());
            statement.setString(++col, emailLog.getEmailCC());
            statement.setString(++col, emailLog.getEmailBCC());
            statement.setString(++col, emailLog.getEmailSubject());
            statement.setString(++col, emailLog.getEmailBody());
            statement.setString(++col, emailLog.getClassName());
            statement.executeUpdate();
            
            //insertAudit(actionClassLog);
           
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
            	emailLog.setLogID(rs.getInt(1));
            }
     
        } catch (SQLException e) {
            logger.error("SQLException in emailLogClass() : ", e);
        } catch (Exception e) {
            logger.error("Exception in emailLogClass() : ", e);
        } finally {
            closeConnection();
        }

    }   
    
    
    public void updateEmailLog(EmailLogDTO emaillog) {
        
        try {
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE emaillog set stoptime = ?, executetime = ?, emailBody = ? WHERE logid = ? ");
            
            int col = 0;
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql.toString());
            
            statement.setTimestamp(++col, new java.sql.Timestamp(emaillog.getStopTime()));
            statement.setLong(++col, emaillog.getExecuteTime());
            statement.setString(++col, emaillog.getEmailBody());            
            statement.setInt(++col, emaillog.getLogID()); 
            statement.executeUpdate();
            
        } catch (SQLException e) {
            logger.error("SQLException in updateLogActionClass() : ", e);
        } catch (Exception e) {
            logger.error("Exception in updateLogActionClass() : ", e);
        } finally {
            closeConnection();
        }

    }
    
    public EmailLogDTO getLog ( ArrayList<Integer> logids, String emailname ) {
    	EmailLogDTO logData = null;
        String sqlLogid = "";
        java.sql.Timestamp tmpStartTime;
        java.sql.Timestamp tmpStopTime;
        try {
        	for (int x=0; x < logids.size(); x++) {
        		sqlLogid += logids.get(x);
            	
            	if (x+1 < logids.size()) {
            		sqlLogid += ",";
            	}
            }
        	
        	
            String sql = "select logid,starttime,stoptime,executetime,emailTo,emailCC,emailBCC,emailSubject,emailBody,className from emaillog where logid in ("+sqlLogid+") and emailTo = ?" ;
            
            makeConnection(getDbLookup());
            statement = conn.prepareStatement(sql);
           // statement.setLong(1, logids);
            statement.setString(1, emailname);
            rs = statement.executeQuery();
            
            if (rs.next()) {
            	int col = 0;
            	logData = new EmailLogDTO();
            	logData.setLogID(rs.getInt(++col));
            	//logData.setStartTime( rs.getTime(++col));
            	tmpStartTime = rs.getTimestamp(++col);
            	if (tmpStartTime == null){
            		logData.setStartTime(0);
            	}else {
            		logData.setStartTime(tmpStartTime.getTime());
            	}
            	tmpStopTime = rs.getTimestamp(++col);
            	if (tmpStopTime == null) {
            		logData.setStopTime(0);
            	} else {
            		logData.setStopTime(tmpStopTime.getTime());
            	}
            	logData.setExecuteTime(rs.getInt(++col));
            	logData.setEmailTo(rs.getString(++col));
            	logData.setEmailCC(rs.getString(++col));
            	logData.setEmailBCC(rs.getString(++col));
            	logData.setEmailSubject(rs.getString(++col));
            	logData.setEmailBody(rs.getString(++col));
            	logData.setClassName(rs.getString(++col));
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getInvoice() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getInvoice() : ", e);
        } finally {
            closeConnection();
        }
        
        return logData;
    }
  
}