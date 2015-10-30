/*
 * ShowMain.java
 *
 * Created on February 1, 2005, 11:57 AM
 */

package fdms.admin.user.action;

/**
 *
 * @author Guadalupe Garcia
 */

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.aldorsolutions.webfdms.admin.user.bean.UserManagerBean;
import com.aldorsolutions.webfdms.admin.user.model.UserSessionDTO;

import fdms.admin.user.form.UserSessionForm;

public class ShowMain extends Action {
    
    private Logger logger = Logger.getLogger(ShowMain.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
                        
        try {
            HttpSession session = request.getSession();                    
            
            UserSessionForm form = (UserSessionForm) session.getAttribute(mapping.getName());
            if (form != null) {
                String id = form.getId();
                int seconds = 0;
                if (id != null) {
                    try {
                        seconds = Integer.parseInt(id) * 60;
                    } catch(NumberFormatException e) {
                        // unable to parse int from String
                    }
                }
                form.setSec(Integer.toString(seconds));
                session.setAttribute(mapping.getName(), form);
            }
            
            ArrayList users = new UserManagerBean().getUsers();
            ArrayList userSessions = new ArrayList();
            UserSessionDTO user = null;            
            SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy hh:mm:aa");                
            
            for (int i = 0; i < users.size(); i++) {
                user = (UserSessionDTO) users.get(i);
                session = (HttpSession) getServlet().getServletContext().getAttribute(user.getUsername());
                
                if (session != null) {
                	
                	try {
                		user.setLastAccessed(df.format(new java.util.Date(session.getLastAccessedTime())));
                        user.setLoggedIn(df.format(new java.util.Date(session.getCreationTime())));
                        user.setDatabase(getDatabaseName(user.getDatabase()));
                        
                        userSessions.add(user);
                	} catch ( Exception e ) {
                		logger.error( "Error getting user session.", e );
                	}
                    
                }
            }
            
            int sessions = 0;
            if (userSessions.size() > 0) {               
                sessions = userSessions.size();
                request.setAttribute("users", userSessions);
            }
            
            request.setAttribute("sessions", Integer.toString(sessions));
            
        } catch (Exception e) {
            logger.error("Exception in perform() : ", e);
        }
        
        return mapping.findForward("main");
    }
    
    private String getDatabaseName(String database) {
        
        if (database != null) {
            database = database.substring(database.lastIndexOf("/") + 1);
        }
        
        return database;
    }
    
}
