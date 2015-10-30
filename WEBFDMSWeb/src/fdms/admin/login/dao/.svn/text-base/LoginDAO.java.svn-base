/*
 * LoginDAO.java
 *
 * Created on January 31, 2005, 12:13 PM
 */

package fdms.admin.login.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.util.DAO;


public class LoginDAO extends DAO {
    
    private Logger logger = Logger.getLogger(LoginDAO.class.getName());
    
    /** Creates a new instance of AlertDAO */
    public LoginDAO() {
    }

    public boolean isValid(String username, String password) {
        boolean isValid = false;
        long adminId = 0L;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT admin_id FROM admin ");
            sql.append("WHERE (username = ?) AND (password = ?)");
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            statement.setString(1, username);
            statement.setString(2, password);
            
            rs = statement.executeQuery();
            
            if (rs.next()) adminId = rs.getLong(1);
            
            if (adminId > 0L) isValid = true;
                        
        } catch (SQLException e) {
            logger.error("SQLException in isValid() : ", e);
        } catch (Exception e) {
            logger.error("Exception in isValid() : ", e);
        } finally {
            closeConnection();
        }
        
        return isValid;
    }
    
}
