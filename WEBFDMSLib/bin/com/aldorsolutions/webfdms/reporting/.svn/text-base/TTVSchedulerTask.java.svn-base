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

public class TTVSchedulerTask extends TimerTask {
	
	private Logger logger = Logger.getLogger(TTVSchedulerTask.class.getName());
	private static TTVSchedulerTask instance = new TTVSchedulerTask();
	
	private TTVSchedulerTask(){
		logger.info("Just now TTVSchedulerTask created");
	}
	public static TTVSchedulerTask getInstance(){
		return instance;
	}
	long nextSchedule = -1l;
	@Override
	public void run() {
		logger.info("Scheduling job start");
		while(true){
			try{
				if(nextSchedule<0){
					logger.info("TTVSchedulerTask intial task started");

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
					
					logger.info("TTVSchedulerTask first schedule : " +cal.toString());
					nextSchedule = cal.getTimeInMillis();
					
					scheduleJobs(0, true);
					logger.info("TTVSchedulerTask first schedule : " +cal.toString()); 
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
				logger.error("TTVSchedulerTask catch InterruptedException ", e);
			}
		}
		

	}
	
	private void scheduleJobs(long scheduleTime, boolean intial){
		logger.info("TTVSchedulerTask schedule : start");
		DatabaseTransaction t = null;
		try {

			SMSSchedulingDAO smsScheDAo = new SMSSchedulingDAO(DAO.DB_FDMSCOMMON);
    		ArrayList <SMSSchedulingDTO> ttvScheList = null;
			//if(intial){
				GregorianCalendar cal = new GregorianCalendar();
				
				CompanyManagerBean cmBean = new CompanyManagerBean();
				
				cal.setTimeInMillis(nextSchedule);
				int startHour = cal.get(GregorianCalendar.HOUR_OF_DAY);
				logger.info("TTVSchedulerTask intial start date : " + getSqlDate(cal) + " hour: " + startHour);
				ttvScheList = smsScheDAo.getSMSSchedulingCommonQ(getSqlDate(cal), startHour,intial,DAO.DB_FDMSCOMMON, "TTV");
//				smsScheList = smsScheDAo.getSMSSchedulingCommonQ();
				logger.info("TTVSchedulerTask intial get number of schedules : " + ttvScheList.size());

			
			if (ttvScheList.size() > 0) {
				for (SMSSchedulingDTO smsScheduling: ttvScheList) {
					if (smsScheduling.getStatus().compareToIgnoreCase("Q") == 0 || smsScheduling.getStatus().compareToIgnoreCase("R") == 0) {
						//
						Timer timerTask = new Timer(true);
						logger.info(Thread.currentThread().getId() + " TTVSchedulerTask.scheduleJobs() company : " + smsScheduling.getCompanyName() + " start running ScheduleId : " + smsScheduling.getSchedulingID());
						timerTask.schedule(new TTVGenerator(smsScheduling.getSchedulingID()), 10);
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
