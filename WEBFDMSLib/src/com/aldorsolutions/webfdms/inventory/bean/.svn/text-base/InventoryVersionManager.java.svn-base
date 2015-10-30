package com.aldorsolutions.webfdms.inventory.bean;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.inventory.dao.InvVersionDAO;


/**
 * @author David Rollins
 * Date: Mar 5, 2007
 * File: InventoryVersionManager.java	
 * 
 */
public class InventoryVersionManager {
    
    private Logger logger = Logger.getLogger(InventoryVersionManager.class.getName());
    private long userID = 0;
    private long companyID = 0;
    
    /*
    public InventoryVersionManager() {
    }
     */
    public InventoryVersionManager ( long companyID, long userId ) {
    	this.companyID = companyID;
    	this.userID = userId;
    }

    /**
     * Adds locationIds to user
     * @param userId
     * @param locations (ArrayList of LocationDTO objects)
     */
    public void addVersionLocations(long invVersionID, ArrayList locations) {
        
        try {
        	InvVersionDAO dao = new InvVersionDAO(companyID, userID);
            dao.addVersionLocations(invVersionID, locations);
        } catch (Exception e) {
            logger.error("Exception in addUserLocations() : ", e);
        }
    }
    
    /**
     * Deletes locationIds configured for user
     * @param userId
     */
    public void deleteVersionLocations(long invVersionID) {
        try {
        	InvVersionDAO dao = new InvVersionDAO(companyID, userID);
        	dao.deleteVersionLocations(invVersionID);
        } catch (Exception e) {
            logger.error("Exception in deleteUserLocations() : ", e);
        }        
    }
    
}
