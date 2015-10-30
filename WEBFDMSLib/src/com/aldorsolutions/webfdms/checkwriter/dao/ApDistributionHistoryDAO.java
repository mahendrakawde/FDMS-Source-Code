package com.aldorsolutions.webfdms.checkwriter.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.checkwriter.model.ApDistributionHistoryDTO;
import com.aldorsolutions.webfdms.util.DAO;


/**
 * @author Chai Jongs
 * Date: 
 * File: ApDistributionHistoryDAO.java	
 * 
 */
public class ApDistributionHistoryDAO extends DAO {
    
    /** Creates a new instance of ApDistributionHistoryDAO */
    public ApDistributionHistoryDAO(DbUserSession user) {
    	super(user);
    }

    /** Creates a new instance of ApDistributionHistoryDAO */
    public ApDistributionHistoryDAO ( long companyID, long userId ) {
    	super(companyID, userId);
    }
    
    private Logger logger = Logger.getLogger(ApDistributionHistoryDAO.class.getName());
 
 

    public ArrayList <ApDistributionHistoryDTO> getDistributionHistory(int apMasterID) {
        ArrayList <ApDistributionHistoryDTO> versions = new ArrayList <ApDistributionHistoryDTO> ();
        ApDistributionHistoryDTO version = null;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select apDistHistID, apMasterID, apAccountNumber, amount, discountAmount, type, posted, memo, createdDTS " +
            		" from apdistributionhistory where apMasterID = ? ");
            
            makeConnection(this.dbLookup);
            statement = conn.prepareStatement(sql.toString());
            statement.setInt(1, apMasterID);
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 1;
                version = new ApDistributionHistoryDTO();
                
                version.setApDistHistID(rs.getInt(col++));
                version.setApMasterID(rs.getInt(col++));
                version.setApAccountNumber(rs.getString(col++));
                version.setAmount(rs.getInt(col++));
                version.setDiscountAmount(rs.getInt(col++));
                version.setType(rs.getString(col++));
                version.setPosted(rs.getString(col++));
                version.setMemo(rs.getString(col++));
                version.setCreatedDTS(rs.getLong(col++));
                
                versions.add(version);
            }
            
        } catch (SQLException e) {
            logger.error("SQLException in getApMaster() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getApMaster() : ", e);
        } finally {
            closeConnection();
        }
        
        return versions;
    }
    
 
    
  
    
    
}
