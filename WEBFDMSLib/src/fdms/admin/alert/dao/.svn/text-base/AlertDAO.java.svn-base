/*
 * AlertDAO.java
 *
 * Created on February 1, 2005, 8:08 AM
 */

package fdms.admin.alert.dao;

/**
 *
 * @author Guadalupe Garcia
 */

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.util.DAO;

import fdms.admin.alert.model.AlertDTO;

public class AlertDAO extends DAO {
    
    private Logger logger = Logger.getLogger(AlertDAO.class.getName());
    
    /** Creates a new instance of AlertDAO */
    public AlertDAO() {
    }
    
    public AlertDTO getAlert(String messageType) {
        AlertDTO alert = new AlertDTO();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT message, viewable, ");
            sql.append("    seconds ");
            sql.append("FROM alert WHERE (message_type = ?)");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, messageType);
            rs = statement.executeQuery();
            
            if (rs.next()) {
                int col = 0;
                alert.setMessageType(messageType);
                alert.setMessage(rs.getString(++col));
                alert.setViewable(rs.getString(++col));
                alert.setSeconds(rs.getInt(++col));
            }
        } catch (SQLException e) {
            logger.error("SQLException in getAlert() : ", e);            
        } catch (Exception e) {
            logger.error("Exception in getAlert() : ", e);
        } finally {
            closeConnection();
        }
        
        return alert;
    }
    
    public void updateAlert(AlertDTO alert) {
        
        try {
        	AlertDTO oldAlert = getAlert(alert.getMessageType());
        	        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE alert SET message = ?, viewable = ?, ");
            sql.append("    seconds = ? ");
            sql.append("WHERE (message_type = ?)");
            
            makeConnection(DAO.DB_FDMSUSERS);
            int col = 0;
            statement = conn.prepareStatement(sql.toString());
            statement.setString(++col, alert.getMessage());
            statement.setString(++col, alert.getViewable());      
            statement.setInt(++col, alert.getSeconds());
            statement.setString(++col, alert.getMessageType());
            statement.executeUpdate();
            
            this.updateAudit(oldAlert, alert);
            
        } catch (SQLException e) {
            logger.error("SQLException in updateAlert() : ", e);            
        } catch (Exception e) {
            logger.error("Exception in updateAlert() : ", e);
        } finally {
            closeConnection();
        }
    }
}
