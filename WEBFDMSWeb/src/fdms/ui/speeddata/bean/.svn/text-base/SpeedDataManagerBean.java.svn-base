package fdms.ui.speeddata.bean;

/**
 * SpeedDataDemo
 * Demographic Data - Holds lists of frequently entered people.
 * Creation date: (9/14/2005 6:10 PM)
 * @author: Kier Heyl
 * Adapted from CaseSetManager.java
 */

import java.util.ArrayList;

import org.apache.log4j.Logger;

import fdms.ui.speeddata.dao.SpeedDemoDAO;

public class SpeedDataManagerBean {
	
	private static Logger logger = Logger.getLogger(SpeedDataManagerBean.class.getName());
	
	// creates a new instance of demographic speeddata information
	public SpeedDataManagerBean() {
	}
	
	public ArrayList getDemoData(
            String dbLookup,
            int locationId,
			int localeId,
			int userID,
			int companyID,
            String category) {
        
        ArrayList demoData = new ArrayList();
        
        try {
        	SpeedDemoDAO DbDemoData = new SpeedDemoDAO(
					dbLookup, userID, companyID, localeId);

			demoData = DbDemoData.getDemoData(
					locationId, 
					localeId,
					category
					);

		} catch (Exception e) {
			logger.error("Exceptin in getCaseList() : ", e);
		}
        
        return demoData;
    }
}