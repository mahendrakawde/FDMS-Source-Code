package com.aldorsolutions.webfdms.sms.action;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.aldorassist.webfdms.dao.LocationDAO;
import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorassist.webfdms.model.LocationDTO;
import com.aldorassist.webfdms.model.SMSSchedulingDTO;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.locale.bean.LocaleManagerBean;
import com.aldorsolutions.webfdms.reporting.SMSGenerator;
import com.aldorsolutions.webfdms.reporting.dao.SMSSchedulingDAO;

// import com.aldorsolutions.webfdms.sms.dao.SMSSchedulingDAO;
import com.aldorsolutions.webfdms.sms.form.SMSForm;
import com.aldorsolutions.webfdms.util.DAO;
import com.callfire.SMSService.ArrayOfString;
import com.callfire.SMSService.ObjectFactory;
import com.callfire.SMSService.SMSService;

/**
*
* @author drollins
*/
public class RunSMSForm extends Action {
    
    private Logger logger = Logger.getLogger(RunSMSForm.class.getName());
    
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm actionForm,
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        	
    	try{
    		SMSSchedulingDAO smsScheDAo = new SMSSchedulingDAO(DAO.DB_FDMSCOMMON);
    		ArrayList <SMSSchedulingDTO> smsScheList = null;
			//if(intial){
				GregorianCalendar cal = new GregorianCalendar();
//				smsScheList = smsScheDAo.getSMSSchedulingCommonQ();
				
				cal.get(GregorianCalendar.SECOND);
				cal.set(GregorianCalendar.MINUTE, 0); 
				cal.set(GregorianCalendar.SECOND, 0);
				cal.set(GregorianCalendar.MILLISECOND, 0);
				cal.add(GregorianCalendar.HOUR_OF_DAY, 1);
				
				int startHour = cal.get(GregorianCalendar.HOUR_OF_DAY);
//				logger.info("SMSSchedulerTask intial start date : " + getSqlDate(cal) + " hour: " + startHour);
				smsScheList = smsScheDAo.getSMSSchedulingCommonQ(getSqlDate(cal), startHour,true,DAO.DB_FDMSCOMMON,"sms");
				CompanyManagerBean cmBean = new CompanyManagerBean();
			
			if (smsScheList.size() > 0) {
				for (SMSSchedulingDTO smsScheduling: smsScheList) {
					
					CompanyDTO company = cmBean.getCompany(smsScheduling.getCompanyId());
					if (smsScheduling.getStatus().compareToIgnoreCase("Q") == 0 || smsScheduling.getStatus().compareToIgnoreCase("R") == 0) {
						FdmsDb.getInstance().SMSCommonNoTimerGenerator(company, smsScheduling.getSchedulingID());

					}
				}
			}
            

    	 }catch (Exception e){
//         	errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.exception", e.getMessage()) );
// 			saveMessages(request, errors);
         }
    	 
        return mapping.findForward("showSMSDo");
    }
	private Date getSqlDate(GregorianCalendar cal){
		Date date = null;
		if(cal != null){
			cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
			cal.set(GregorianCalendar.MINUTE, 0);
			cal.set(GregorianCalendar.MILLISECOND, 0);
			date = new Date(cal.getTimeInMillis());
		}
		return date;
	}
        
}
