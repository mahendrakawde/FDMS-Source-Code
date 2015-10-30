package com.aldorsolutions.webfdms.reporting;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.SMSSchedulingDTO;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.reporting.dao.SMSSchedulingDAO;
import com.aldorsolutions.webfdms.util.DAO;

public class SMSSchedulerTask extends TimerTask {
	
	private Logger logger = Logger.getLogger(SMSSchedulerTask.class.getName());
	private static SMSSchedulerTask instance = new SMSSchedulerTask();
	
	private SMSSchedulerTask(){
		logger.info("Just now SMSSchedulerTask created");
	}
	public static SMSSchedulerTask getInstance(){
		return instance;
	}
	long nextSchedule = -1l;
	@Override
	public void run() {
		logger.info("Scheduling job start");
		while(true){
			try{
				if(nextSchedule<0){
					logger.info("SMSSchedulerTask intial task started");

					GregorianCalendar cal = new GregorianCalendar();

					cal.get(GregorianCalendar.SECOND);
					cal.set(GregorianCalendar.MINUTE, 0); 
					cal.set(GregorianCalendar.SECOND, 0);
					cal.set(GregorianCalendar.MILLISECOND, 0);
					cal.add(GregorianCalendar.HOUR_OF_DAY, 1);
					
//					cal.get(GregorianCalendar.SECOND);
//					cal.set(GregorianCalendar.MINUTE, 5); 
//					cal.set(GregorianCalendar.SECOND, 0);
//					cal.set(GregorianCalendar.MILLISECOND, 0);
//					cal.add(GregorianCalendar.HOUR_OF_DAY, 0);
					
					logger.info("SMSSchedulerTask first schedule : " +cal.toString());
					nextSchedule = cal.getTimeInMillis();
					
					scheduleJobs(0, true);
					logger.info("SMSSchedulerTask first schedule : " +cal.toString()); 
					Thread.sleep(nextSchedule-System.currentTimeMillis());
				}
				
				while(true){
					if(nextSchedule>System.currentTimeMillis()){
						Thread.sleep(nextSchedule-System.currentTimeMillis());
					}else{
						long scheduleTime = nextSchedule ;
						nextSchedule += 60*60*1000;
//						nextSchedule += 5*1000;
						scheduleJobs(scheduleTime, false);
					}
				}
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.error("SMSSchedulerTask catch InterruptedException ", e);
			}
		}
		

	}
	
	private void scheduleJobs(long scheduleTime, boolean intial){
		logger.info("SMSSchedulerTask schedule : start");
		DatabaseTransaction t = null;
		try {

			SMSSchedulingDAO smsScheDAo = new SMSSchedulingDAO(DAO.DB_FDMSCOMMON);
    		ArrayList <SMSSchedulingDTO> smsScheList = null;
			//if(intial){
				GregorianCalendar cal = new GregorianCalendar();
				
				CompanyManagerBean cmBean = new CompanyManagerBean();
				
				cal.setTimeInMillis(nextSchedule);
				int startHour = cal.get(GregorianCalendar.HOUR_OF_DAY);
				logger.info("SMSSchedulerTask intial start date : " + getSqlDate(cal) + " hour: " + startHour);
				smsScheList = smsScheDAo.getSMSSchedulingCommonQ(getSqlDate(cal), startHour,intial,DAO.DB_FDMSCOMMON, "SMS");
//				smsScheList = smsScheDAo.getSMSSchedulingCommonQ();
				logger.info("SMSSchedulerTask intial get number of schedules : " + smsScheList.size());

			
			if (smsScheList.size() > 0) {
				for (SMSSchedulingDTO smsScheduling: smsScheList) {
					if (smsScheduling.getStatus().compareToIgnoreCase("Q") == 0 || smsScheduling.getStatus().compareToIgnoreCase("R") == 0) {
						//
						Timer timerTask = new Timer(true);
						logger.info(Thread.currentThread().getId() + " SMSSchedulerTask.scheduleJobs() company : " + smsScheduling.getCompanyName() + " start running ScheduleId : " + smsScheduling.getSchedulingID());
						timerTask.schedule(new SMSGenerator(smsScheduling.getSchedulingID()), 10);
						Thread.sleep(60000L); //delay 1 minute
					}
				}
			}	
			
			
//			CompanyManagerBean cmBean = new CompanyManagerBean();
//			List list = cmBean.getCompanies(true);
//			for (int i = 0; list != null && i < list.size(); i++) {	
//				CompanyDTO company = (CompanyDTO) list.get(i);
//				try{
//					if(company.getName().startsWith("key2")){
//						company = company;
//					}
//					logger.info("SMSSchedulerTask running for company: " + company.getName());
//				t = new DatabaseTransaction (company.getDbLookup(), 0, 0, 0);
//				
//				// let work on the report schedule.
//
//				SMSSchedulingDAO smsScheDAo = new SMSSchedulingDAO(company.getDbLookup());
//				
//				ArrayList <SMSSchedulingDTO> smsScheList = null;
//				//if(intial){
//					GregorianCalendar cal = new GregorianCalendar();
//					cal.setTimeInMillis(nextSchedule);
//					int startHour = cal.get(GregorianCalendar.HOUR_OF_DAY);
//					logger.info("SMSSchedulerTask intial start date : " + getSqlDate(cal) + " hour: " + startHour);
//					smsScheList = smsScheDAo.getSMSSchedulingQ(getSqlDate(cal), startHour,intial);
//					logger.info("SMSSchedulerTask intial get number of schedules : " + smsScheList.size());
//
//				
//				if (smsScheList.size() > 0) {
//					for (SMSSchedulingDTO smsScheduling: smsScheList) {
//						
//						if (smsScheduling.getStatus().compareToIgnoreCase("Q") == 0 || smsScheduling.getStatus().compareToIgnoreCase("R") == 0) {
//
//							Timer timerTask = new Timer(true);
//							logger.info(Thread.currentThread().getId() + " SMSSchedulerTask.scheduleJobs() company : " + company.getName() + " start running ScheduleId : " + smsScheduling.getSchedulingID());
//							timerTask.schedule(new SMSGenerator(company,smsScheduling.getSchedulingID()), 10);
//						}
//					}
//				}
//				}catch(Exception ex){
//					
//				}finally {
//					if (t != null) {
//						t.closeConnection();
//					}
//				}
//			}

		} catch (Exception ex) {
			
		} 

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
