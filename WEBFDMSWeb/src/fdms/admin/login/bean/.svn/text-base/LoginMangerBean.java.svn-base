/*
 * LoginBean.java
 *
 * Created on January 31, 2005, 12:12 PM
 */

package fdms.admin.login.bean;

/**
 *
 * @author Guadalupe Garcia
 */
import org.apache.log4j.Logger;

import fdms.admin.login.dao.LoginDAO;

public class LoginMangerBean {
    
    private Logger logger = Logger.getLogger(LoginMangerBean.class.getName());
    
    /** Creates a new instance of LoginBean */
    public LoginMangerBean() {
    }
    
    public boolean isValid(String username, String password) {
        
        boolean isValid = false;
        
        try {
            LoginDAO dao = new LoginDAO();
            isValid = dao.isValid(username, password);
        } catch (Exception e) {
            logger.error("Exception in isValid() : ", e);
        }
        
        return isValid;
    }
    
}
