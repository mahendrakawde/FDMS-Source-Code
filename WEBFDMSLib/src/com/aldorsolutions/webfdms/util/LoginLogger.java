package com.aldorsolutions.webfdms.util;

import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.admin.user.model.UserLogDTO;

public final class LoginLogger extends DAO {
    
    private Logger logger = Logger.getLogger(LoginLogger.class.getName());
    
    /** Creates a new instance of LoginLogger */
    public LoginLogger(int userID, int companyID, int localeID) {
    	this.auditDTO.setCompanyId(companyID);
    	this.auditDTO.setUserId(userID);
    	this.auditDTO.setLocaleId(localeID);
    }
    /*
    public LoginLogger() {
    	super ( );
    }
    */
    public void logUser(
        String username,
        int userId,
        int regionId, 
        long time, 
        String ip, 
        String browser,
        String userAgent,
        String valid ) {
          
    	UserLogDTO userLog = new UserLogDTO ();
    	userLog.setUsername(username);
    	userLog.setUserId(userId);
    	userLog.setRegionId(regionId);
    	userLog.setTime(time);
    	userLog.setIp(ip);
    	userLog.setBrowser(browser);
    	userLog.setUserAgent(userAgent);
    	userLog.setValid(valid);
    	
    	logUser ( userLog );
    	
    }
    

    public void logUser( UserLogDTO userLog ) {
            
        try {
            
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO userlog (username, user_id, region_id, ");
            sql.append("date_logged_in, ip, loginServerIP, browser, user_agent, valid) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?)");
            
            makeConnection(DAO.DB_FDMSUSERS);
            int col = 0;
            statement = conn.prepareStatement(sql.toString());
            statement.setString(++col, userLog.getUsername());
            statement.setInt(++col, userLog.getUserId());
            statement.setInt(++col, userLog.getRegionId());
            statement.setTimestamp(++col, new java.sql.Timestamp(userLog.getTime()));
            statement.setString(++col, userLog.getIp());
            statement.setString(++col, InetAddress.getLocalHost().getHostAddress());
            statement.setString(++col, userLog.getBrowser());
            statement.setString(++col, userLog.getUserAgent());
            statement.setString(++col, userLog.getValid());
            
            statement.executeUpdate();
            
            insertAudit(userLog);
            
        } catch (SQLException e) {
            logger.error("SQLException in logUser() : ", e);
        } catch (Exception e) {
            logger.error("Exception in logUser() : ", e);
        } finally {
            closeConnection();
        }
    }
    
    public int getPreviousFailedAttempts(String username) {

		int failedAttempts = 0;

		try {

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT count(*) FROM userlog where username = ? and ");
			sql.append("userlog_id > ( SELECT max(userlog_id) FROM userlog where ");
			sql.append("username = ? and (valid = 'T' or valid='A') ) ");

			makeConnection(DAO.DB_FDMSUSERS);
			int col = 0;
			statement = conn.prepareStatement(sql.toString());
			statement.setString(++col, username);
			statement.setString(++col, username);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				failedAttempts = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("SQLException in getPreviousFailedAttempts() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getPreviousFailedAttempts() : ", e);
		} finally {
			closeConnection();
		}

		return (failedAttempts);

	}
}
