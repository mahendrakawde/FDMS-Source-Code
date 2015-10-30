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

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		if (filterConfig == null) {
			return;
		}
			
		// Get the inbound URL out so we can look at it.
		String url = ((HttpServletRequest) servletRequest).getRequestURI();

		// Look at what page is being requested. If it is not one of these special cases
		// then we want to check and make sure they are active.  If they are not this
		// code will redirect them to the login page.
		if (url.toLowerCase().indexOf("logon.do") < 0
				&& url.toLowerCase().indexOf("login.do") < 0
				&& url.toLowerCase().indexOf("login.jsp") < 0
				&& url.toLowerCase().indexOf("alderwoods.jsp") < 0
				&& url.toLowerCase().indexOf("termsofservice") < 0) {
			
			// get the session so we can get some data out
			HttpSession session = ((HttpServletRequest) servletRequest).getSession();

			HttpServletResponse response = (HttpServletResponse) servletResponse;
			String path = "login.jsp";

			// Make sure we are not in the admin panel
			if (url.indexOf("/admin/") < 0) {
				// We are in the user panel so get out the user
				DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
				// If we do not have a user then we want to send them to the login page
				if (user == null) {
					response.sendRedirect(path);
				} else {
					// The user is logged in and we we want to check and see if we should display
					// the terms of service.
					String tos = user.getTos();
					if ((tos == null) || "N".equals(tos)) {
						// Redirect the user so they can see the terms of service
						logger.info("Redirecting to termsOfServiceForm.jsp");
						response.sendRedirect("showTermsOfService.do");
					}
				}
			} else {
				// Not sure what this does.  I can only assume that when an admin logs in that
				// this session attribute is created.  So I believe we are checking to see if the
				// admin is logged in.  If they are not logged in then redirect to the login page.
				Boolean admin = (Boolean) session.getAttribute("FDMS_SA");
				if (admin == null) {
					response.sendRedirect(path);
				}
			}
		}

		if (servletResponse.isCommitted() == false) {
			filterChain.doFilter(servletRequest, servletResponse);		
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}