/*
 * CemSetSpcManagerBean.java
 *
 * Created on April 28, 2005, 10:55 AM
 */

package fdms.ui.caseset.bean;

/**
 *
 * @author Guadalupe Garcia
 */

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;

import fdms.ui.caseset.dao.CemSpcCaseSetDAO;

public class CemSetSpcManagerBean {
    
    private static Logger logger = Logger.getLogger(CaseSetManagerBean.class.getName());
    
    /** Creates a new instance of CaseSetManagerBean */
    public CemSetSpcManagerBean() {
    }
       
    
    public ArrayList getCaseList(
            String dbLookup,
            DbUserSession user,
            Character spaceType) {
        
        ArrayList cases = new ArrayList();
                
        try {
			CemSpcCaseSetDAO caseSetDao = new CemSpcCaseSetDAO( dbLookup );

			cases = caseSetDao.getCaseList(spaceType);

		} catch (Exception e) {
			logger.error("Exceptin in getCaseList() : ", e);
		}
        
        return cases;
    }
      
}
