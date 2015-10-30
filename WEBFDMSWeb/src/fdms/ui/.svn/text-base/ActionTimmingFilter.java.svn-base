/*
 * SessionFilter.java
 *
 * Created on December 17, 2004, 11:26 AM
 */

package fdms.ui;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.delegate.CompanyOptionsManager;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorsolutions.webfdms.admin.user.model.ActionClassLogDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.ActionClassLogger;
import com.aldorsolutions.webfdms.util.SessionValueKeys;


public class ActionTimmingFilter implements Filter {
	
	private Logger logger = Logger.getLogger(ActionTimmingFilter.class.getName());
	
    private FilterConfig filterConfig = null;
    
    /** Creates a new instance of SessionFilter */
    public ActionTimmingFilter() {
    }
    
    
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
    

    public void destroy() {
        this.filterConfig = null;
    }
    
    public void doFilter(
        ServletRequest servletRequest,
        ServletResponse servletResponse,
        FilterChain filterChain) throws IOException, ServletException {
            
            if (filterConfig == null) return;
            
            if ( servletResponse.isCommitted() == false ) {
            	
            	
            	HttpSession session = ((HttpServletRequest) servletRequest).getSession();    
                DbUserSession user =  (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
                
                
                if (user == null) {
                	//if the first time log in  there is no user info yet so  just forward this to the next filter.
                	filterChain.doFilter(servletRequest, servletResponse);
                }
                else {
                	//start to log each action class timing
	            	ActionClassLogDTO actionClassLog = new ActionClassLogDTO();
	            	long startTime = 0;
	            	
	            	CompanyOptionsManager coMgr = new CompanyOptionsManager ();
	                int actionClassLogOption = coMgr.getCompanyOptionValueForCompany(user.getCompanyID(), CompanyOptionsDTO.COMPANY_OPTION_ACCTIONCLASS_LOG);
	                
	                if (actionClassLogOption == 1) {
	            	//log the action timming for the starting time.
		            	startTime = System.currentTimeMillis(); 
		            	String url = ((HttpServletRequest) servletRequest).getRequestURI();
	
		            	actionClassLog.setActionClassName(url);
		            	actionClassLog.setUserID(user.getId());
		            	actionClassLog.setStartTime(startTime);
		            	//actionClassLog.setStopTime(null);
	                	actionClassLog.setExecuteTime(0);
		            	ActionClassLogger actionClassLogger = new ActionClassLogger(user.getId(), user.getCompanyID(), user.getRegion());        
		            	actionClassLogger.logActionClass( actionClassLog );
	                }
            	
               
	            	filterChain.doFilter(servletRequest, servletResponse);
	            	
	            	//log action timing, the stoptime and execute time. this need to update the above database row since log for finish action class.
	            	  
	                if (actionClassLogOption == 1) {
	                	
	                	long stopTime = System.currentTimeMillis(); 
	             
	                	actionClassLog.setStopTime(stopTime);
	                	actionClassLog.setExecuteTime((int)(stopTime-startTime));
	                		
	                	ActionClassLogger actionClassLogger = new ActionClassLogger(user.getId(), user.getCompanyID(), user.getRegion());        
	                	actionClassLogger.updateLogActionClass( actionClassLog );
	                	
	                }
                }
            	
            }
            
    }
 }