/*
 * VisitationManagerBean.java
 *
 * Created on April 28, 2005, 10:55 AM
 */

package com.aldorsolutions.webfdms.visitation.bean;

/**
 *
 * @author Guadalupe Garcia
 */

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVisitations;
import com.aldorsolutions.webfdms.visitation.dao.VisitationDAO;

public class VisitationManagerBean {
    
    private static Logger logger = Logger.getLogger(VisitationManagerBean.class.getName());
    
    /** Creates a new instance of CaseSetManagerBean */
    public VisitationManagerBean() {
    }

    public DbVisitations getVisitationsByPK(int id, DbUserSession user) {
        
        DbVisitations visitation = null;
        
        try {
			VisitationDAO visitationsDao = new VisitationDAO(user);
			visitation = visitationsDao.getVisitationsByPK(id);
		} catch (Exception e) {
			logger.error("Exceptin in getVisitationsForID() : ", e);
		}
        
        return (visitation);
    }
    
    public ArrayList <DbVisitations> getVisitationsForID(int vitalsId, DbUserSession user) {
        
        ArrayList <DbVisitations> visitations = new ArrayList <DbVisitations> ();
             
        
        try {
			VisitationDAO visitationsDao = new VisitationDAO(user);

			visitations = visitationsDao.getVisitationsForID(vitalsId);

		} catch (Exception e) {
			logger.error("Exceptin in getVisitationsForID() : ", e);
		}
        
        return visitations;
    }
    
    
  
    public void updateVisitations(ArrayList <DbVisitations> visitations, DbUserSession user, int vitalsId) {
    	
		try {

			VisitationDAO visitationsDao = new VisitationDAO(user);
									
			visitationsDao.updateVisitations(visitations, vitalsId);
	    	
		} catch (Exception e) {
			logger.error("Exceptin in updateVisitations() : ", e);
		}
    }
    
    public DbVisitations getVisitationForCaseList(int vitalsId, DbUserSession user) {
    	
    	DbVisitations visitation = new DbVisitations();
    	
    	try {

			VisitationDAO visitationsDao = new VisitationDAO(user);
									
			visitation = visitationsDao.getVisitationForCaseList(vitalsId);
	    	
		} catch (Exception e) {
			logger.error("Exceptin in getVisitationForCaseList() : ", e);
		}
		
		return visitation;
    }
    
}
