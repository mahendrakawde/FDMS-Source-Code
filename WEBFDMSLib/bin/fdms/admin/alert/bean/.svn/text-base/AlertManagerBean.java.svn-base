/*
 * AlertBean.java
 *
 * Created on February 1, 2005, 8:19 AM
 */

package fdms.admin.alert.bean;

/**
 *
 * @author Guadalupe Garcia
 */

import org.apache.log4j.Logger;

import fdms.admin.alert.dao.AlertDAO;
import fdms.admin.alert.model.AlertDTO;
import fdms.ui.tags.AlertMessage;

public class AlertManagerBean {
    
    private Logger logger = Logger.getLogger(AlertManagerBean.class.getName());
    public static AlertDTO ALERT_MESSAGE;
    public static AlertDTO ALERT_REBOOT;
    
    /** Creates a new instance of AlertBean */
    public AlertManagerBean() {
    }
    
    static {
        if (ALERT_MESSAGE == null) {
            AlertDAO dao = new AlertDAO();
            ALERT_MESSAGE = dao.getAlert(AlertMessage.MESSAGE_TYPE_MESSAGE);
        }
        
        if (ALERT_REBOOT == null) {
            AlertDAO dao = new AlertDAO();
            ALERT_REBOOT = dao.getAlert(AlertMessage.MESSAGE_TYPE_REBOOT);
            if (ALERT_REBOOT.getViewable().equals("Y")) {
                ALERT_REBOOT.setViewable("N");
                dao.updateAlert(ALERT_REBOOT);
            }            
        }
    }
    
    public AlertDTO getAlert(String messageType) {
        AlertDTO alert = new AlertDTO();
        
        try {
            alert = new AlertDAO().getAlert(messageType);
        } catch (Exception e) {
            logger.error("Exception in getAlert() : ", e);
        }
        
        return alert;
    }
    
    public void updateAlert(AlertDTO alert) {
        
        try {
            new AlertDAO().updateAlert(alert);
            if (alert.getMessageType().equals(AlertMessage.MESSAGE_TYPE_REBOOT)) { 
                if (alert.getViewable().equals("Y")) {
                    long endTime = System.currentTimeMillis() + (alert.getSeconds() * 1000);
                    alert.setEndTime(endTime);
                }
                ALERT_REBOOT = alert;
            } else ALERT_MESSAGE = alert;
            
        } catch (Exception e) {
            logger.error("Exception in updateAlert() : ", e);
        }
    }
    
}
