package fdms.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fdms.admin.alert.bean.AlertManagerBean;
import fdms.admin.alert.model.AlertDTO;

public class RebootMessageXML extends HttpServlet {
	
	private static final long serialVersionUID = 6566056644767095917L;

	private Logger logger = Logger.getLogger(RebootMessageXML.class.getName());
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doPost(request, response);
	}
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
    	
    	int seconds = 0;
    	
    	try {
	    	AlertDTO alert = AlertManagerBean.ALERT_REBOOT;
	        
	    	
	        if (alert != null && alert.getViewable().equals("Y"))
	            seconds = new Long((alert.getEndTime() - System.currentTimeMillis()) / 1000).intValue();
	    		    	
    	} catch (Exception e) {
    		logger.error("Exception in perform() : ", e);
    	}
    	
    	response.setContentType("text/xml");
    	response.getWriter().write("<?xml version=\"1.0\"?>\n");
    	response.getWriter().write("<rebootAlert seconds=\"" + seconds + "\">reboot");
    	response.getWriter().write("</rebootAlert>");

    }

}
