package fdms.ui.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.beans.cache.SpeedDataCache;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.FormatNumber;
import com.aldorsolutions.webfdms.util.SessionValueKeys;

import fdms.ui.struts.form.AddSpeedDataForm;


public class AddSpeedDataAction extends Action {
    
    private Logger logger = Logger.getLogger(AddSpeedDataAction.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
                                          
        logger.debug("Beginning addSpeedDataAction.perform()");
        
        AddSpeedDataForm form = (AddSpeedDataForm) actionForm;
        HttpSession session = request.getSession();
        DbUserSession user =
                (DbUserSession)session.getAttribute(SessionValueKeys.DB_USER);
        
        logger.debug("Got user from session: " + user.getUserName());
        String category = (String)session.getAttribute("popupCategory");
        
        String submitType = form.getSubmitButton();
        DatabaseTransaction t = null;
        
        if (submitType == null || submitType.trim().length() == 0) {
            return mapping.findForward("redisplayTablePopup");
        } else {
            if (submitType.toUpperCase().equals("ADD")) {
                // Add new data to SpeedData for specified category.
                logger.debug("Adding to category: " +category +": value: " +form.getNewdata());
                FdmsDb.getInstance().addSpeedDataRow(
                        user,
                        category,
                        form.getNewdata(), 
                        FormatNumber.parseInteger(form.getSeqnumber()) 
						);
            } else {
                // Edit existing SpeedData.
                try {
                    logger.debug("Editting category: " +category +": value: " +form.getNewdata());
                    t = (DatabaseTransaction)DatabaseTransaction.getTransaction(user);
                    DbSpeedData dbSpeedData = FdmsDb.getInstance().getSpeedDataRow(t, Integer.parseInt(form.getIdentity()));
                    dbSpeedData.setData(form.getNewdata());
                    dbSpeedData.setSortSequence(FormatNumber.parseInteger(form.getSeqnumber()));
                    t.save();
                    SpeedDataCache.getInstance().setRefresh(user.getDbLookup());
                } catch (PersistenceException pe) {
                    logger.error("Error in doPerform() : " + pe);                    
                } finally {
                    if (t != null) {
    					t.closeConnection();
    					t = null;
    				}
                }
            }
        }
        
        // Redisplay table-popup which will include added data
        return mapping.findForward("redisplayTablePopup");
    }
}
