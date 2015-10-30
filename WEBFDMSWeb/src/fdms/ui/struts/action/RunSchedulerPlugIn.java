package fdms.ui.struts.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import com.aldorsolutions.webfdms.beans.cache.SpeedDataCache;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.reporting.ReportSchedulerTask;
import com.aldorsolutions.webfdms.reporting.SMSSchedulerTask;
import com.callfire.SMSService.ArrayOfString;
import com.callfire.SMSService.ObjectFactory;
import com.callfire.SMSService.SMSService;


public class RunSchedulerPlugIn implements PlugIn {

	DatabaseTransaction t = null;

	public void destroy() {

	}

	// This method will be called at application startup time
	public void init(ActionServlet actionServlet, ModuleConfig config) throws ServletException {

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
			if (t != null) {
				t.closeConnection();
			}
		}
//		Timer timer = new Timer("ReportSchedulerThread", true);
//		timer.schedule(ReportSchedulerTask.getInstance(), 0l);
		
//		Timer smsTimer = new Timer("SMSThread", true);
//		smsTimer.schedule(SMSSchedulerTask.getInstance(), 10);
		

	}

}