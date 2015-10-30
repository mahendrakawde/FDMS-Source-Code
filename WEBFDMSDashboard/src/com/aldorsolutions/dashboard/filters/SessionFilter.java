/*
 * SessionFilter.java
 *
 * Created on December 17, 2004, 11:26 AM
 */

package com.aldorsolutions.dashboard.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.SessionValueKeys;


public class SessionFilter implements Filter {
	
	private Logger logger = Logger.getLogger(SessionFilter.class.getName());
	
    private FilterConfig filterConfig = null;
    
    /** Creates a new instance of SessionFilter */
    public SessionFilter() {
    }
    
    public void destroy() {
        this.filterConfig = null;
    }
    
    public void doFilter(
        ServletRequest servletRequest,
        ServletResponse servletResponse,
        FilterChain filterChain) throws IOException, ServletException {
            
            if (filterConfig == null) {
            	return;
            }
            
            String url = ((HttpServletRequest) servletRequest).getRequestURI();
            url = url.toLowerCase();
            
            if ( url.indexOf("dashboardlogin.do") > 0 || url.indexOf("login.jsp") > 0 || 
            	 url.indexOf("lostpassword.jsp") > 0  || url.indexOf("lostpassword.do") > 0 ) {
            	// proceed
            }
            else {
            	
                HttpSession session = ((HttpServletRequest) servletRequest).getSession();                
                
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                String path = "/dashboard/login.jsp";
                
                DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
	            if (user == null) {
	            	if(servletRequest.getParameter("referal") == null && url.indexOf("invoicelist.do") > 0)
	            		session.setAttribute("invoicePath", ((HttpServletRequest) servletRequest).getRequestURI() + "?" + ((HttpServletRequest) servletRequest).getQueryString());
					response.sendRedirect(path);
	            } else if(user != null && session.getAttribute("invoicePath") != null) {
	            	String invoicePath = session.getAttribute("invoicePath").toString();
	            	session.removeAttribute("invoicePath");
	            	response.sendRedirect(invoicePath);
	            }
            }
            
            if ( servletResponse.isCommitted() == false ) {
            	filterChain.doFilter(servletRequest, servletResponse);
            }
            
    }
    
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
    
}