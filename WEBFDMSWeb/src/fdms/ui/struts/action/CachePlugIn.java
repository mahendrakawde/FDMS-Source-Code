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


public class CachePlugIn implements PlugIn {

	DatabaseTransaction t = null;

	public void destroy() {

	}

	// This method will be called at application startup time
	public void init(ActionServlet actionServlet, ModuleConfig config) throws ServletException {

		try {

			CompanyManagerBean cmBean = new CompanyManagerBean();
			List list = cmBean.getCompanies(true);
			for (int i = 0; list != null && i < list.size(); i++) {	
				CompanyDTO company = (CompanyDTO) list.get(i);
				t = new DatabaseTransaction (company.getDbLookup(), 0, 0, 0);      
				SpeedDataCache.getInstance().setRefresh(company.getDbLookup());
				SpeedDataCache.getInstance().getCache(company.getDbLookup());
			}

		} catch (Exception ex) {
			throw new ServletException("PersistenceException durig Cache initializaion", ex);
		} finally {
			if (t != null) {
				t.closeConnection();
			}
		}
		
		Timer timer = new Timer("ReportSchedulerThread", true);
		try{
			timer.schedule(ReportSchedulerTask.getInstance(), 0l);
		}catch(Exception ex){}
		
//		Timer smsTimer = new Timer("SMSThread", true);
//		smsTimer.schedule(SMSSchedulerTask.getInstance(), 10);
		

	}

}