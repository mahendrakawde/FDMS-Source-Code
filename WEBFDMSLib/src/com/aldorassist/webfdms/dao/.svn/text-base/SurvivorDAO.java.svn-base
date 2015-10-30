/*
 * SurvivorDAO.java
 *
 * Created on April 26, 2012
 */
package com.aldorassist.webfdms.dao;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.LocaleDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.DAO;

/**
*
* @author Jigar Mistry
*/
public class SurvivorDAO extends DAO {
    private Logger logger = Logger.getLogger(LocaleDAO.class.getName());
    
    public SurvivorDAO() {
    	super ();
    }
    
    public SurvivorDAO(DbUserSession user) {
    	super (user);
    }
    
    public SurvivorDAO(long companyID, long userID) {
    	super (companyID, userID);
    }
  
    public boolean updateRelativesSequanceNumber(String[] autoIndexes, String[] sortOrder, String dbLookup) throws PersistenceException {
        boolean updated = false;
        
        try {
        	
            StringBuffer sql = new StringBuffer();
            sql.append("UPDATE survivors SET SequenceNumber = ? WHERE AutoIndex = ?");
            
            makeConnection(dbLookup);
            
            for (int index = 0; index < autoIndexes.length; index++) {
            	statement = conn.prepareStatement(sql.toString());
                statement.setInt(1, Integer.parseInt(sortOrder[index].trim()));
                statement.setInt(2, Integer.parseInt(autoIndexes[index].trim()));
                statement.executeUpdate();
			}
            
            updated = true;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } catch (Exception e) {
            logger.error("Exception in updateLocale() : ", e);
        } finally {
            closeConnection();
        }
        
        return updated;
    }
}
