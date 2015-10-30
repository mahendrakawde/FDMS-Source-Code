/*
 * SecurityConfigDAO.java
 *
 * Created on June 19, 2006
 */

package com.aldorsolutions.webfdms.security.dao;

/**
 *
 * @author drollins
 */

import com.aldorsolutions.webfdms.company.model.CompanyDTO;


public class SecurityConfigDAOCon extends SecurityConfigDAO {
    
    private CompanyDTO company = null;
    
    /** Creates a new instance of SecurityConfigDAO */
    public SecurityConfigDAOCon(CompanyDTO company) {
    	super(company.getDbLookup());
    	this.company = company; 
    }
    
    public SecurityConfigDAOCon(long companyID, long userID) {
    	super(companyID, userID);
    }
    
   	public SecurityConfigDAOCon ( String dbLookup ) {
    	super (dbLookup);
    }
        
}
