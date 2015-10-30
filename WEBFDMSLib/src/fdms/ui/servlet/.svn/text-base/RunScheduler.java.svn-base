package fdms.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.callfire.SMSService.ArrayOfString;
import com.callfire.SMSService.ObjectFactory;
import com.callfire.SMSService.SMSService;

import fdms.admin.alert.bean.AlertManagerBean;
import fdms.admin.alert.model.AlertDTO;

public class RunScheduler extends HttpServlet {
	
	private static final long serialVersionUID = 6566056644767095917L;

	private Logger logger = Logger.getLogger(RunScheduler.class.getName());
	
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//		doPost(request, response);
//	}
//    
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//    	
//    	int seconds = 0;
//    	
//    	try {
//	    	AlertDTO alert = AlertManagerBean.ALERT_REBOOT;
//	        
//	    	
//	        if (alert != null && alert.getViewable().equals("Y"))
//	            seconds = new Long((alert.getEndTime() - System.currentTimeMillis()) / 1000).intValue();
//	    		    	
//    	} catch (Exception e) {
//    		logger.error("Exception in perform() : ", e);
//    	}
//    	
//    	response.setContentType("text/xml");
//    	response.getWriter().write("<?xml version=\"1.0\"?>\n");
//    	response.getWriter().write("<rebootAlert seconds=\"" + seconds + "\">reboot");
//    	response.getWriter().write("</rebootAlert>");
//
//    }
	public void init() throws ServletException
    {
		try {
//			Thread.sleep(1000*60*5);
			SMSService service = new SMSService();
			ArrayOfString numbers = new ObjectFactory().createArrayOfString();
			numbers.getString().add("9405942322,HI Chai!");
//			numbers.getString().add(myForm.getAreaCode()+myForm.getPhone()+","+myForm.getMessage());
			String key = "d8b7227f26bbdf34fa40498f7aac2e5a1f880320";
			long compaignid = service.getSMSServiceHttpPort().sendSMSCampaign(key, numbers, "hi");
		} catch (Exception ex) {
			throw new ServletException("PersistenceException durig Cache initializaion", ex);
		} finally {
		
		}
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
    	try {

			SMSService service = new SMSService();
			ArrayOfString numbers = new ObjectFactory().createArrayOfString();
			numbers.getString().add("9405942322,HI Chai!");
//			numbers.getString().add(myForm.getAreaCode()+myForm.getPhone()+","+myForm.getMessage());
			String key = "d8b7227f26bbdf34fa40498f7aac2e5a1f880320";
			long compaignid = service.getSMSServiceHttpPort().sendSMSCampaign(key, numbers, "hi");
		} catch (Exception ex) {
			throw new ServletException("PersistenceException durig Cache initializaion", ex);
		} finally {
		
		}
    }

	

}
