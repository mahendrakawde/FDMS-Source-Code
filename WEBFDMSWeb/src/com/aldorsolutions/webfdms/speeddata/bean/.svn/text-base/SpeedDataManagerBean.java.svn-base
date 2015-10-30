package com.aldorsolutions.webfdms.speeddata.bean;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.dao.SpeedDataDAO;
import com.aldorassist.webfdms.model.SpeedDataRuleDTO;

/**
 * Workfile: SpeedDataManagerBean.java
 * Date: Nov 17, 2005 6:31:06 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class SpeedDataManagerBean {
	
	private static Logger logger = Logger.getLogger(SpeedDataManagerBean.class.getName());

	/**
	 * 
	 * @param tabCategory
	 * @param dbUrl
	 * @param dbUsername
	 * @param dbPassword
	 * @return
	 */
	public SpeedDataRuleDTO getSpeedDataRule(
			String tabCategory,
			String dbLookup) {
		SpeedDataRuleDTO speedDataRuleDTO = null;
		
		try {
			SpeedDataDAO speedDataDAO = new SpeedDataDAO(dbLookup);
			speedDataRuleDTO = speedDataDAO.getSpeedDataRule(
					tabCategory,
					dbLookup);
		} catch (Exception e) {
			logger.error("Exception in getSpeedDataRule() : ", e);
		}
		
		return speedDataRuleDTO;
	}
	
	/**
	 * 
	 * @param tabCategory
	 * @param dbUrl
	 * @param dbUsername
	 * @param dbPassword
	 * @return
	 */
	public List getSpeedDataRules(
			String dbLookup) {
		List list = null;
		
		try {
			SpeedDataDAO speedDataDAO = new SpeedDataDAO(dbLookup);
			list = speedDataDAO.getSpeedDataRules(
					dbLookup);
		} catch (Exception e) {
			logger.error("Exception in getSpeedDataRule() : ", e);
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param tabCategory
	 * @param tabData
	 * @param dbUrl
	 * @param dbUsername
	 * @param dbPassword
	 * @return
	 */
	public String getSpeedDataCSVString(
			String tabCategory,
			String tabData,
			String dbLookup) {
		
		StringBuffer sb = new StringBuffer();
		
		try {
			SpeedDataDAO speedDataDAO = new SpeedDataDAO(dbLookup);
			SpeedDataRuleDTO speedDataRuleDTO = speedDataDAO.getSpeedDataRule(
					tabCategory, dbLookup);
			
			if (speedDataRuleDTO != null) {				
				StringTokenizer st = new StringTokenizer(tabData, ",");

				if (speedDataRuleDTO.getLabels() != null) {
					Iterator it = speedDataRuleDTO.getLabels().iterator();
					int i = speedDataRuleDTO.getLabels().size();
					int x = 0;
					while (it.hasNext()) {
						String label = (String) it.next();
						if ((label != null) && (!"".equals(label))) {
							if (x > 0) sb.append(",");
							sb.append(st.nextToken());
							if (++x == i) break;
						} else break;						
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getSpeedDataRule() : ", e);
		}		
		
		return sb.toString();		
	}
}
