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

import com.aldorassist.webfdms.model.ReportSchedulingDTO;
import com.aldorassist.webfdms.model.SMSSchedulingDTO;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.reporting.dao.ReportSchedulingDAO;
import com.aldorsolutions.webfdms.reporting.dao.SMSSchedulingDAO;

public class ReportSchedulerTask extends TimerTask {
	
	private Logger logger = Logger.getLogger(ReportSchedulerTask.class.getName());
	private static ReportSchedulerTask instance = new ReportSchedulerTask();
	
	private ReportSchedulerTask(){
		logger.info("Just now ReportSchedulerTask created");
	}
	public static ReportSchedulerTask getInstance(){
		return instance;
	}
	long nextSchedule = -1l;
	@Override
	public void run() {
		logger.info("Scheduling job start");
		while(true){
			try{
				if(nextSchedule<0){
					logger.info("ReportSchedulerTask intial task started");
					//logger.error("SQLException in deleteReportScheduling() : ");
					GregorianCalendar cal = new GregorianCalendar();
					//cal.setTimeInMillis(System.currentTimeMillis());
					cal.get(GregorianCalendar.SECOND);
					cal.set(GregorianCalendar.MINUTE, 0); 
					cal.set(GregorianCalendar.SECOND, 0);
					cal.set(GregorianCalendar.MILLISECOND, 0);
					cal.add(GregorianCalendar.HOUR_OF_DAY, 1);
					logger.info("ReportSchedulerTask first schedule : " +cal.toString());
					nextSchedule = cal.getTimeInMillis();
					
					scheduleJobs(0, true);
					logger.info("ReportSchedulerTask first schedule : " +cal.toString()); 
					Thread.sleep(nextSchedule-System.currentTimeMillis());
				}
				
				while(true){
					if(nextSchedule>System.currentTimeMillis()){
						Thread.sleep(nextSchedule-System.currentTimeMillis());
					}else{
						long scheduleTime = nextSchedule ;
						nextSchedule += 60*60*1000;
						scheduleJobs(scheduleTime, false);
					}
				}
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.error("ReportSchedulerTask catch InterruptedException ", e);
			}
		}
		

	}
	
	private void scheduleJobs(long scheduleTime, boolean intial){
		logger.info("ReportSchedulerTask schedule : start");
		DatabaseTransaction t = null;
		try {

			CompanyManagerBean cmBean = new CompanyManagerBean();
			List list = cmBean.getCompanies(true);
			for (int i = 0; list != null && i < list.size(); i++) {	
				CompanyDTO company = (CompanyDTO) list.get(i);
				try{
					if(company.getName().startsWith("Key2")){
						company = company;
					}
					logger.info("ReportSchedulerTask running for company: " + company.getName());
				t = new DatabaseTransaction (company.getDbLookup(), 0, 0, 0);
				
				// let work on the report schedule.
//				ReportSchedulingDTO reportScheduling = new ReportSchedulingDTO();
				ReportSchedulingDAO reportScheDAo = new ReportSchedulingDAO(company.getDbLookup());
				
				ArrayList <ReportSchedulingDTO> rptScheList = null;
				//if(intial){
					GregorianCalendar cal = new GregorianCalendar();
					cal.setTimeInMillis(nextSchedule);
					int startHour = cal.get(GregorianCalendar.HOUR_OF_DAY);
					logger.info("ReportSchedulerTask intial start date : " + getSqlDate(cal) + " hour: " + startHour);
					rptScheList = reportScheDAo.getReportSchedulingQ(getSqlDate(cal), startHour,intial);
					logger.info("ReportSchedulerTask intial get number of schedules : " + rptScheList.size());
				/*}else{
					GregorianCalendar startCal = new GregorianCalendar();
					startCal.setTimeInMillis(scheduleTime);
					int startHour = startCal.get(GregorianCalendar.HOUR_OF_DAY);
					GregorianCalendar endCal = new GregorianCalendar();
					endCal.setTimeInMillis(nextSchedule);
					int endHour = endCal.get(GregorianCalendar.HOUR_OF_DAY);
					logger.info("ReportSchedulerTask start date : " + getSqlDate(startCal) + " hour: " + startHour);
					logger.info("ReportSchedulerTask end date : " + getSqlDate(endCal) + " hour: " + endHour);
					rptScheList = reportScheDAo.getReportSchedulingQ(getSqlDate(startCal), startHour, getSqlDate(endCal), endHour);
					logger.info("ReportSchedulerTask get number of schedules : " + rptScheList.size());
				}*/
				
				if (rptScheList.size() > 0) {
					for (ReportSchedulingDTO reportScheduling: rptScheList) {
						
						if (reportScheduling.getStatus().compareToIgnoreCase("Q") == 0 || reportScheduling.getStatus().compareToIgnoreCase("R") == 0) {
//							ReportSchedulingDTO aSchedule = new ReportSchedulingDTO();
//							copyObj(reportScheduling,aSchedule);
							Timer timerTask = new Timer(true);
//							Calendar date = Calendar.getInstance();
//							date.setTime((java.util.Date) aSchedule.getRunDate());
//							//date.add(Calendar.MINUTE, 5);
//							date.set(Calendar.HOUR_OF_DAY, aSchedule.getRunTimeHH()); //0-23
//							date.set(Calendar.MINUTE, aSchedule.getRunTimeMM()); //0-59
							logger.info(Thread.currentThread().getId() + " ReportSchedulerTask.scheduleJobs() company : " + company.getName() + " start running ScheduleId : " + reportScheduling.getSchedulingID());
							timerTask.schedule(new ReportGenerator(company,reportScheduling.getSchedulingID()), 0l);
						}
					}
				}
				
				}catch(Exception ex){
					
				}finally {
					if (t != null) {
						t.closeConnection();
					}
				}
			}

		} catch (Exception ex) {
			
		} 

	}
	private void copyObj(ReportSchedulingDTO reportScheduling,
			ReportSchedulingDTO schedule) {
		schedule.setSchedulingID(reportScheduling.getSchedulingID());
		schedule.setFormID(reportScheduling.getFormID());
		schedule.setLocale(reportScheduling.getLocale());
		schedule.setLocation(reportScheduling.getLocation());
		schedule.setFromDate(reportScheduling.getFromDate());
		schedule.setToDate(reportScheduling.getToDate());
		schedule.setRunDate(reportScheduling.getRunDate());
		schedule.setEmailTo(reportScheduling.getEmailTo());
		schedule.setEmailCC(reportScheduling.getEmailCC());
		schedule.setRepeatType(reportScheduling.getRepeatType());
		schedule.setRepeatNumber(reportScheduling.getRepeatNumber());
		schedule.setDatetime(reportScheduling.getDatetime());
		schedule.setReportName(reportScheduling.getReportName());
		schedule.setStatus(reportScheduling.getStatus());
		schedule.setReportType(reportScheduling.getReportType());
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
