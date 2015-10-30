/*
 * CaseSetManagerBean.java
 *
 * Created on April 28, 2005, 10:55 AM
 */

package fdms.ui.caseset.bean;
//JO - F030601A - Added dor Debugging
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;

import fdms.ui.caseset.dao.CemOwnCaseSetDAO;

public class CemOwnSetManagerBean {
    
    private static Logger logger = Logger.getLogger(CaseSetManagerBean.class.getName());
    
    /** Creates a new instance of CaseSetManagerBean */
    public CemOwnSetManagerBean() {
    }
    
    public int getCaseCount(
            String dbLookup,
            String locationIdStr,
            String [] locationIds,
            String sortBy,
            String searchParam,
            int active,
            String caseListDisplayPreneed,
            String caseListDisplayVoided,
            DbUserSession user) {
    	
        int count = 0;
        int locationId = 0;
        
        if (locationIdStr != null) {
            try {
                locationId = Integer.parseInt(locationIdStr);
            } catch (NumberFormatException e) {
                // unable to parse int from String
            }
        }
        
        try {
            CemOwnCaseSetDAO caseSetDao = new CemOwnCaseSetDAO( dbLookup );
            
            count = caseSetDao.getCaseCount(
                    locationId,
                    locationIds,
                    sortBy,
                    searchParam,
                    active,
                    caseListDisplayPreneed,
                    caseListDisplayVoided);
        } catch (Exception e) {
            logger.error("Exceptin in getCaseCount() : ", e);
        }
        
        return count;
    }
    
    
    public ArrayList getCaseList(
            String dbLookup,
            String locationIdStr,
            String [] locationIds,
            String sortBy,
            int startIndex,
            int limit,
            String searchParam,
            int active,
            String caseListDisplayPreneed,
            String caseListDisplayVoided,
            DbUserSession user) {
        
        ArrayList cases = new ArrayList();
        int locationId = 0;
        
        if (locationIdStr != null) {
            try {
                locationId = Integer.parseInt(locationIdStr);
            } catch (NumberFormatException e) {
                // unable to parse int from String
            }
        }        
        
        try {
			CemOwnCaseSetDAO caseSetDao = new CemOwnCaseSetDAO( dbLookup );

			cases = caseSetDao.getCaseList(
					locationId, 
					locationIds,
					sortBy,
					((startIndex - 1) * limit), 
					limit, 
					searchParam,
					active,
					caseListDisplayPreneed,
					caseListDisplayVoided);

		} catch (Exception e) {
			logger.error("Exceptin in getCaseList() : ", e);
		}
        
        return cases;
    }
    
    
    public int getEndIndex(int records, int limit) {
        int endIndex = 1;
        
        if (records > 0 && limit > 0) {
            endIndex = records/limit;
            
            int extra = records%limit;
            if (extra > 0) endIndex++;
        }
        
        return endIndex;
    }
    
}
