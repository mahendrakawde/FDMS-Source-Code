package com.aldorsolutions.webfdms.util;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbObituary;
import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.asimas.asimasBeans.Obituary;
import com.asimas.asimasBeans.ServiceSchedule;

public class ObituaryTranslator {
	
	static private final Logger logger = Logger.getLogger(ObituaryTranslator.class.getName());
	
	public ObituaryTranslator() { }

	/**
	 * 
	 * @param vitalsId
	 * @param domainId
	 * @param deceasedId
	 * @param dbUserSession
	 * @return
	 */
	public HashMap createObituary(
			int vitalsId, 
			long domainId, 
			long deceasedId, 
			DbUserSession dbUserSession) {
		
		logger.debug("Entering createObituary()");
		
		HashMap map = null;
		
		DbVitalsDeceased dbVitalsDeceased = null;
		DbServices dbServices = null;
		DatabaseTransaction t = null;
		try {
			t = (DatabaseTransaction)DatabaseTransaction.getTransaction(dbUserSession);
			
			dbVitalsDeceased = FdmsDb.getInstance().getVitalsDeceased(t,vitalsId);
			dbServices = FdmsDb.getInstance().getServices(t, vitalsId);
			
			if (dbVitalsDeceased != null) {
				logger.debug("DbVitalsDeceased not null");
				logger.debug("DbService not null");
				
				map = new HashMap(2);
				ServiceSchedule serviceSchedule = new ServiceSchedule();
				
				GregorianCalendar calendar = new GregorianCalendar();
				int mo = calendar.get(GregorianCalendar.MONTH) + 1;
				int day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
				int yr = calendar.get(GregorianCalendar.YEAR);
				
				serviceSchedule.setDeceasedID(Long.toString(deceasedId));
				serviceSchedule.setDomainID(Long.toString(domainId));
								
				serviceSchedule.setFirstName(dbVitalsDeceased.getFirstName());
				serviceSchedule.setLastName(dbVitalsDeceased.getLastName());
				
				serviceSchedule.setServiceDate(
						FormatDate.MDYtoMMDDYYYY(dbVitalsDeceased.getDateOfBurial()));
				serviceSchedule.setBorn(
						FormatDate.MDYtoMMDDYYYY(dbVitalsDeceased.getDateOfBirth()));
				serviceSchedule.setDied(
						FormatDate.MDYtoMMDDYYYY(dbVitalsDeceased.getDateOfDeath()));								
				
				if (dbServices != null) {
					serviceSchedule.setServiceLocationName(dbServices.getCSrvChurch());
					serviceSchedule.setServiceTime(dbServices.getCSrvTime());
				}				
				
				serviceSchedule.setServiceLocationID("0");
				serviceSchedule.setLocationID("0");
				serviceSchedule.setStatus("2");
				
				serviceSchedule.setDateCreated(new Timestamp(new java.util.Date().getTime()));
				serviceSchedule.setMonth(mo);
				serviceSchedule.setDay(day);
				serviceSchedule.setYear(yr);
				serviceSchedule.setModifiedDate(new Timestamp(new java.util.Date().getTime()));
				serviceSchedule.setModifiedBy(dbUserSession.getUserName());			
				
				Obituary obituary = new Obituary();
				obituary.setDeceasedID(Long.toString(deceasedId));
				obituary.setDomainID(Long.toString(domainId));
				obituary.setModifiedDate(new Timestamp(new java.util.Date().getTime()));
				obituary.setModifiedBy(dbUserSession.getUserName());
				
				DbObituary dbObituary = FdmsDb.getInstance().getObituary(t, vitalsId);
				if (dbObituary != null) obituary.setObituary(dbObituary.getObitNotice());
				
				map.put("serviceSchedule", serviceSchedule);
				map.put("obituary", obituary);
			}
		} catch (Exception e) {
			logger.error("Exception in createObituary() : ", e);
		} finally {
			if (t != null) {
				t.closeConnection();
				t = null;
			}
		}
		
		return map;
	}
}
