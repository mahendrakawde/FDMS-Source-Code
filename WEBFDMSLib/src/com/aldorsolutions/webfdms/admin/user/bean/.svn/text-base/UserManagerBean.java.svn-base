/*
 * UserBean.java
 *
 * Created on February 1, 2005, 11:54 AM
 */

package com.aldorsolutions.webfdms.admin.user.bean;

/**
 *
 * @author Guadalupe Garcia
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.text.StringCharacterIterator;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.admin.user.dao.UserDAO;
import com.aldorsolutions.webfdms.admin.user.dao.UserVacationDAO;
import com.aldorsolutions.webfdms.admin.user.model.UserDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocaleDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserLocationDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserSessionDTO;
import com.aldorsolutions.webfdms.admin.user.model.UserVacationDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.checkwriter.dao.ApVendorDAO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorDTO;
import com.aldorsolutions.webfdms.checkwriter.model.ApVendorLocationDTO;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;


public class UserManagerBean {
    
    private Logger logger = Logger.getLogger(UserManagerBean.class.getName());
    
    /** Creates a new instance of UserBean */
    public UserManagerBean() {
    }
    
    public ArrayList  <UserSessionDTO> getUsers() {
        
        ArrayList  <UserSessionDTO> users = new ArrayList <UserSessionDTO> ();
        
        try {
            users = new UserDAO().getUsers();
        } catch (Exception e) {
            logger.error("Exception in getUsers() : ", e);
        }
        
        return users;
        
    }
    
    public ArrayList <UserDTO> getUsersByCompany(long companyID) {
        
        ArrayList <UserDTO> users = new ArrayList <UserDTO> ();
        
        try {
            users = new UserDAO().getUsers(UserDAO.ACTIVE, companyID);
        } catch (Exception e) {
            logger.error("Exception in getUsers() : ", e);
        }
        
        return users;
        
    }
    
    public ArrayList <UserDTO> getUsersByRoleAssigned(long companyID, long roleID) {
        
        ArrayList <UserDTO> users = new ArrayList <UserDTO> ();
        
        try {
            users = new UserDAO().getUsersByRoleAssigned(UserDAO.ACTIVE, companyID, roleID);
        } catch (Exception e) {
            logger.error("Exception in getUsersByRoleAssigned() : ", e);
        }
        
        return users;
        
    }
    
    public ArrayList <UserDTO> getUsers(int status) {

        ArrayList <UserDTO> users = new ArrayList <UserDTO> ();
        
        try {
            users = new UserDAO().getUsers(status);
            
            for (int i = 0; i < users.size(); i++) {
                UserDTO user = (UserDTO) users.get(i);
                user.setDbUrl(getDatabaseName(user.getDbUrl()));
            }
        } catch (Exception e) {
            logger.error("Exception in getUsers() : ", e);
        }
        
        return users;
    }
    
    
    public UserDTO getUser(long userId) {
        UserDTO user = null;
        
        try {
            user = new UserDAO().getUser(userId);
        } catch (Exception e) {
            logger.error("Exception in getUser() : ", e);
        }
        
        return user;
    }
    
    public boolean addUser(UserDTO user)throws Exception {
        boolean added = false;
        
        try {
            added = new UserDAO().addUser(user);
        } catch (Exception e) {
            logger.error("Exception in addUser() : ", e);
        }
        
        return added;
    }
    public boolean addUser(UserDTO user, boolean isGlobal)throws Exception {
        boolean added = false;
        
        try {
            added = new UserDAO().addUser(user, isGlobal);
        } catch (Exception e) {
            logger.error("Exception in addUser() : ", e);
        }
        
        return added;
    }
    
    
    public boolean updateUser(UserDTO user) {
        boolean updated = false;
        
        try {
            UserDAO userDao = new UserDAO();
            updated = userDao.updateUser(user);
        } catch (Exception e) {
            logger.error("Exception in updateUser() : ", e);
        }
        return updated;
    }
    
    public void deleteUser(long userId) {
        
        try {
            new UserDAO().deleteUser(userId);
        } catch (Exception e) {
            logger.error("Exception in deleteUser() : ", e);
        }
        
    }
    
    private String getDatabaseName(String database) {
        
        if (database != null) {
            database = database.substring(database.lastIndexOf("/") + 1);
        }
        
        return database;
    }    
    
    public HashMap getUserLocations(long userId) {
        HashMap map = null;
        
        try {
            UserDAO userDao = new UserDAO();
            ArrayList userLocations = userDao.getUserLocations(userId);
            if (userLocations.size() > 0) {
                map = new HashMap(2);
                map.put("userLocations", userLocations);
                map.put("defaultLocation", getDefaultLocation(userLocations));
            }
        } catch (Exception e) {
            logger.error("Exception in getUserLocations() : ", e);
        }
        
        return map;
    }
    
    private String getDefaultLocation(ArrayList <UserLocationDTO> userLocations) {
        
        String defaultLocation = "";        
        
        Iterator <UserLocationDTO> it = userLocations.iterator();

        while (it.hasNext()) {
            UserLocationDTO userLocationDto = (UserLocationDTO) it.next();            
            
            if ((userLocationDto.getDefaultLocation() != null)
                && (userLocationDto.getDefaultLocation().trim().equals("Y"))) {
                defaultLocation = String.valueOf(userLocationDto.getLocationId());
                break;
            }
            
            if (userLocationDto.getLocationId() == 0) {
            	it.remove();
            }
        }
        
        return defaultLocation;
    }
    
    public void updateUserDefaultLocation(
    		String userIdStr, 
			String locationIdStr) {
        
        long userId = 0L;
        int locationId = 0;
        
        if ((userIdStr != null) && (locationIdStr != null)) {
            try {
                userId = Long.parseLong(userIdStr);
                locationId = Integer.parseInt(locationIdStr);
            } catch (NumberFormatException e) {
                // unable to parse numeric values from Strings
            }
        }
        
        UserDAO userDao = new UserDAO();
        
        if (locationId == 0) {
        	boolean hasAll = userDao.hasAllLocations(userId);
        	if (!hasAll) userDao.addAllLocationId(userId);
        }
        
        if (userId > 0L) {
            try {                
                userDao.updateUserDefaultLocation(userId, locationId);
            } catch (Exception e) {
                logger.error("Exception in updateUserLocations() : ", e);
            }
        }
    }
    
    public ArrayList <UserLocationDTO> getLocations(
    		String dbLookup, 
			int userID, int companyID, int regionID) {
        
        ArrayList <UserLocationDTO> locations = new ArrayList <UserLocationDTO> ();
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        DatabaseTransaction t = null;
        
        try {
        	t = new DatabaseTransaction(dbLookup, userID, companyID, regionID );
            
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT LocationID, Name, Locale ");
            sql.append("FROM locations ");
            sql.append("WHERE ((InactiveCode IS NULL) ");
            sql.append("    OR (InactiveCode != 'D')) ");
            sql.append("	AND locale in ( select LocaleID ");
            sql.append("	from locales where CompanyID = ? )");
            sql.append("ORDER BY Name");
                        
            conn = t.getConnection();
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            rs = statement.executeQuery();
            
            while (rs.next()) {
                UserLocationDTO userLocationDto = new UserLocationDTO();
                userLocationDto.setLocationId(rs.getLong(1));
                userLocationDto.setName(rs.getString(2));
                userLocationDto.setRegionId(rs.getLong(3));
                locations.add(userLocationDto);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception in getLocations() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getLocations() : ", e);
        } finally {

            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing result set : ", e);
            }
            
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing prepared statement : ", e);
            }
            
//            try {
//                if (conn != null) {
//                	conn.close();
//                	conn = null;
//                }
//            } catch (SQLException e) {
//                logger.error("SQL Exception in closing connection : ", e);
//            }

			if ( t != null ) {
        		t.closeConnection();
        		t = null;
        	}
            
        }
        
        return locations;
    }

    public ArrayList <UserLocaleDTO> getLocales(String dbLookup, int userID, 
    		int companyID, int regionID) {
        
        ArrayList <UserLocaleDTO> locales = new ArrayList <UserLocaleDTO> ();
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        DatabaseTransaction t = null;
        
        try {
            t = new DatabaseTransaction(dbLookup, userID, companyID, regionID );
            
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT LocaleID, Name, CompanyID ");
            sql.append("FROM locales ");
            sql.append("WHERE (CompanyID = ?) ");
            sql.append("ORDER BY Name");
                        
            conn = t.getConnection();
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            rs = statement.executeQuery();
            
            while (rs.next()) {
                UserLocaleDTO dtoLocale = new UserLocaleDTO();
                dtoLocale.setLocaleId(rs.getString(1));
                dtoLocale.setName(rs.getString(2));
                dtoLocale.setCompanyId(rs.getString(3));
                locales.add(dtoLocale);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception in getLocales() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getLocales() : ", e);
        } finally {
        	
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing result set : ", e);
            }
            
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing prepared statement : ", e);
            }
            
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing connection : ", e);
            }

			if ( t != null ) {
        		t.closeConnection();
        		t = null;
        	}
            
        }
        
        return locales;
    }
    
    public String getInSQL(String[] strs){
    	String str = "";
    	if (strs.length == 1 ){
    		str = "(" + strs[0] +")";
    	}
    	else {
    		str = "" ;
    		int x=0;
    		for(  x= 0; x < (strs.length - 1); x++) {
    			str = str+ strs[x]+", ";
    		}
    		str = "( "+ str + strs[x] + " )";
    	}
    	return str;
    }
    
    public ArrayList <UserLocaleDTO> getLocalesOfUser(String dbLookup, int userID, 
    		int companyID, int regionID) {
        
    	String [] localeIDs = getUserLocaleIds( userID);
    	String strLocaleIDs = getInSQL(localeIDs);
    	
        ArrayList <UserLocaleDTO> locales = new ArrayList <UserLocaleDTO> ();
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        DatabaseTransaction t = null;
        
        try {
            t = new DatabaseTransaction(dbLookup, userID, companyID, regionID );
            
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT LocaleID, Name, CompanyID ");
            sql.append("FROM locales ");
            sql.append("WHERE (CompanyID = ?)  and LocaleID in "+strLocaleIDs+" ");
            sql.append("ORDER BY Name");
                        
            conn = t.getConnection();
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            rs = statement.executeQuery();
            
            while (rs.next()) {
                UserLocaleDTO dtoLocale = new UserLocaleDTO();
                dtoLocale.setLocaleId(rs.getString(1));
                dtoLocale.setName(rs.getString(2));
                dtoLocale.setCompanyId(rs.getString(3));
                locales.add(dtoLocale);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception in getLocales() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getLocales() : ", e);
        } finally {
        	
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing result set : ", e);
            }
            
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing prepared statement : ", e);
            }
            
//            try {
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                logger.error("SQL Exception in closing connection : ", e);
//            }

			if ( t != null ) {
        		t.closeConnection();
        		t = null;
        	}
            
        }
        
        return locales;
    }
    
    public ArrayList <UserLocaleDTO> getLocaleOfUser(String dbLookup, int userID, 
    		int companyID, int regionID) {
        
    	
    	
        ArrayList <UserLocaleDTO> locales = new ArrayList <UserLocaleDTO> ();
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        DatabaseTransaction t = null;
        
        try {
            t = new DatabaseTransaction(dbLookup, userID, companyID, regionID );
            
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT LocaleID, Name, CompanyID ");
            sql.append("FROM locales ");
            sql.append("WHERE (CompanyID = ?)  and LocaleID = "+regionID+" ");
            sql.append("ORDER BY Name");
                        
            conn = t.getConnection();
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            rs = statement.executeQuery();
            
            while (rs.next()) {
                UserLocaleDTO dtoLocale = new UserLocaleDTO();
                dtoLocale.setLocaleId(rs.getString(1));
                dtoLocale.setName(rs.getString(2));
                dtoLocale.setCompanyId(rs.getString(3));
                locales.add(dtoLocale);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception in getLocales() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getLocales() : ", e);
        } finally {
        	
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing result set : ", e);
            }
            
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing prepared statement : ", e);
            }
            
//            try {
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                logger.error("SQL Exception in closing connection : ", e);
//            }

			if ( t != null ) {
        		t.closeConnection();
        		t = null;
        	}
            
        }
        
        return locales;
    }      
    
    
    public ArrayList <UserLocationDTO> getLocationsOfUser(
    		String dbLookup, 
			int userID, int companyID, int regionID) {
        
    	String [] locationIDs = getUserLocationIds( userID);
    	String strLocationIDs = getInSQL(locationIDs); 	
    	
        ArrayList <UserLocationDTO> locations = new ArrayList <UserLocationDTO> ();
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        DatabaseTransaction t = null;
        
        try {
        	t = new DatabaseTransaction(dbLookup, userID, companyID, regionID );
            
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT LocationID, Name, Locale ");
            sql.append("FROM locations ");
            sql.append("WHERE ((InactiveCode IS NULL) ");
            sql.append("    OR (InactiveCode != 'D')) and LocationID in "+strLocationIDs+" ");
            sql.append("	AND locale in ( select LocaleID ");
            sql.append("	from locales where CompanyID = ? )");
            sql.append("ORDER BY Name");
                        
            conn = t.getConnection();
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            rs = statement.executeQuery();
            
            while (rs.next()) {
                UserLocationDTO userLocationDto = new UserLocationDTO();
                userLocationDto.setLocationId(rs.getLong(1));
                userLocationDto.setName(rs.getString(2));
                userLocationDto.setRegionId(rs.getLong(3));
                locations.add(userLocationDto);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception in getLocations() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getLocations() : ", e);
        } finally {

            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing result set : ", e);
            }
            
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing prepared statement : ", e);
            }
            
        //    try {
       //         if (conn != null) conn.close();
        //    } catch (SQLException e) {
         //       logger.error("SQL Exception in closing connection : ", e);
          //  }

			if ( t != null ) {
        		t.closeConnection();
        		t = null;
        	}
            
        }
        
        return locations;
    }
    
    public ArrayList <UserLocationDTO> getLocationOfUser(
    		String dbLookup, 
			int userID, int companyID, int regionID, long locationID) {
        
        ArrayList <UserLocationDTO> locations = new ArrayList <UserLocationDTO> ();
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        DatabaseTransaction t = null;
        
        try {
        	t = new DatabaseTransaction(dbLookup, userID, companyID, regionID );
            
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT LocationID, Name, Locale ");
            sql.append("FROM locations ");
            sql.append("WHERE ((InactiveCode IS NULL) ");
            sql.append("    OR (InactiveCode != 'D')) and LocationID = "+locationID+" ");
            sql.append("	AND locale in ( select LocaleID ");
            sql.append("	from locales where CompanyID = ? )");
            sql.append("ORDER BY Name");
                        
            conn = t.getConnection();
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            rs = statement.executeQuery();
            
            while (rs.next()) {
                UserLocationDTO userLocationDto = new UserLocationDTO();
                userLocationDto.setLocationId(rs.getLong(1));
                userLocationDto.setName(rs.getString(2));
                userLocationDto.setRegionId(rs.getLong(3));
                locations.add(userLocationDto);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception in getLocations() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getLocations() : ", e);
        } finally {

            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing result set : ", e);
            }
            
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing prepared statement : ", e);
            }
            
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                logger.error("SQL Exception in closing connection : ", e);
            }

			if ( t != null ) {
        		t.closeConnection();
        		t = null;
        	}
            
        }
        
        return locations;
    }
    
    
    public String[] getUserLocaleIds(long userId) {
        
        String[] localeIds = null;
        
        try {
            UserDAO userDao = new UserDAO();
            ArrayList <UserLocaleDTO> userLocales = userDao.getUserLocales(userId);
            
            if (userLocales.size() > 0) {
            	localeIds = new String[userLocales.size()];
            	
                int i = 0;
                Iterator <UserLocaleDTO> it = userLocales.iterator();
                while (it.hasNext()) {
                    UserLocaleDTO userLocaleDto = (UserLocaleDTO) it.next();
                    localeIds[i++] = userLocaleDto.getLocaleId();
                }
            }
        } catch (Exception e) {
            logger.error("Exception in getUserLocaleIds() : ", e);
        }
        
        return localeIds;
    }
    

    public String[] getUserLocationIds(long userId) {
        
        logger.debug("Entering getUserLocationIds()");
        
        String[] locationIds = null;
        
        try {
            UserDAO userDao = new UserDAO();
            ArrayList  <UserLocationDTO> userLocations = userDao.getUserLocations(userId);
            if (userLocations.size() > 0) {
                locationIds = new String[userLocations.size()];
                
                int i = 0;
                Iterator <UserLocationDTO> it = userLocations.iterator();
                while (it.hasNext()) {
                    UserLocationDTO userLocationDto = (UserLocationDTO) it.next();
                    locationIds[i++] = String.valueOf(userLocationDto.getLocationId());
                }
            }
        } catch (Exception e) {
            logger.error("Exception in getUserLocations() : ", e);
        }
        
        return locationIds;
    }
    
    public String[] getUserLocationIds(long userId, int localeID) {
        
        logger.debug("Entering getUserLocationIds()");
        
        String[] locationIds = null;
        
        try {
            UserDAO userDao = new UserDAO();
            ArrayList <UserLocationDTO> userLocations = userDao.getUserLocations(userId, localeID);
            if (userLocations.size() > 0) {
                locationIds = new String[userLocations.size()];
                
                int i = 0;
                Iterator <UserLocationDTO> it = userLocations.iterator();
                while (it.hasNext()) {
                    UserLocationDTO userLocationDto = (UserLocationDTO) it.next();
                    locationIds[i++] = String.valueOf(userLocationDto.getLocationId());
                }
            }
        } catch (Exception e) {
            logger.error("Exception in getUserLocations() : ", e);
        }
        
        return locationIds;
    }

    /**
     * Adds locationIds to user
     * @param userId
     * @param locations (ArrayList of LocationDTO objects)
     */
    public void addUserLocations(long userId, ArrayList <UserLocationDTO> locations)throws Exception {
        
        try {
            UserDAO userDao = new UserDAO();
            userDao.addUserLocations(userId, locations);
        } catch (Exception e) {
            logger.error("Exception in addUserLocations() : ", e);
            throw e;
        }
    }
    
    /**
     * Adds locationIds to user
     * @param userId
     * @param locations (ArrayList of LocationDTO objects)
     */
    public void addUserLocations(long userId, ArrayList <UserLocationDTO> locations, boolean isGlobal) throws Exception{
        
        try {
            UserDAO userDao = new UserDAO();
            userDao.addUserLocations(userId, locations,isGlobal);
        } catch (Exception e) {
            logger.error("Exception in addUserLocations() : ", e);
            throw e;
        }
    }
    
    /**
     * Deletes locationIds configured for user
     * @param userId
     */
    public void deleteUserLocations(long userId) {
        try {
            UserDAO userDao = new UserDAO();
            userDao.deleteUserLocations(userId);
        } catch (Exception e) {
            logger.error("Exception in deleteUserLocations() : ", e);
        }        
    }

    /**
     * 
     * @param userId
     * @return
     */
    public ArrayList <UserVacationDTO> getUserVacations(long userId) {
    	
    	ArrayList <UserVacationDTO> vacations = new ArrayList <UserVacationDTO> ();
        
        try {
        	vacations = new UserVacationDAO().getUserVactions(userId);
        } catch (Exception e) {
            logger.error("Exception in getUserVacations() : ", e);
        }
        
        return vacations;
    }
    
    
    public ArrayList <UserLocaleDTO> getLocales(String dbLookup, String dbUsername,  
			String dbPassword, int companyID) {
        
        ArrayList <UserLocaleDTO> locales = new ArrayList <UserLocaleDTO> ();
        
        Properties dbProps = new Properties();
        dbProps.put("user",dbUsername);
        dbProps.put("password",dbPassword);
        java.sql.Connection conn = null;
        java.sql.PreparedStatement statement = null;
        java.sql.ResultSet rs = null;
        
        try {
            DatabaseTransaction t =
                    new DatabaseTransaction(dbLookup ,0,0,0);
            
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT LocaleID, Name, CompanyID ");
            sql.append("FROM locales ");
            sql.append("WHERE (CompanyID = ?) ");
            sql.append("ORDER BY Name");
                        
            conn = t.getConnection();
            statement = conn.prepareStatement(sql.toString());
            statement.setLong(1, companyID);
            rs = statement.executeQuery();
            
            while (rs.next()) {
                UserLocaleDTO dtoLocale = new UserLocaleDTO();
                dtoLocale.setLocaleId(rs.getString(1));
                dtoLocale.setName(rs.getString(2));
                dtoLocale.setCompanyId(rs.getString(3));
                locales.add(dtoLocale);
            }
        } catch (java.sql.SQLException e) {
            logger.error("SQL Exception in getLocations() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getLocations() : ", e);
        } finally {
            try {
                if (rs != null) {
					rs.close();
                }
                
				if (statement != null) {
					statement.close();
				}
				
				if (conn != null) {
					conn.close();
				}
                
            } catch (java.sql.SQLException e) {
                logger.error("SQL Exception in closing db resources : ", e);
            }
        }
        
        return locales;
    }
    

    public String createLocaleJavascript ( ArrayList <UserLocationDTO> locations, String [] locationIds ) {
    	//return ( createLocaleJavascriptAll (locations, locationIds, false) );	
    	return ( createLocaleJavascript (locations, locationIds, false) );	
	}
    
    public String createLocaleJavascriptAll ( ArrayList <UserLocationDTO> locations, String [] locationIds ) {
    	return ( createLocaleJavascriptAll (locations, locationIds, true) );		
	}
    
//    public String createLocaleJavascript ( ArrayList <UserLocationDTO> locations, String [] locationIds, boolean showAllSelect ) {
//    	
//    	StringBuilder js = new StringBuilder ("\n");
//    	
//    	StringBuilder resetStr = new StringBuilder("\nfunction resetScript () { \n");
//    	resetStr.append("\tif ( confirm ( \"Are you sure you want to reset your locations?\" ) ) {\n");	
//    	js.append("\tlocList = new Array(" + locations.size() + ");\n");
//    	
//    	//showAllSelect 
//    	for ( int j = 0; j < locations.size(); j++ ) {
//    		UserLocationDTO location = (UserLocationDTO) locations.get(j);
//    		
//    		long localeID = location.getRegionId();
//    		long locationID = location.getLocationId();
//    		String locName = location.getName();
//    		boolean selected = false;
//    		
//    		if ( locationIds != null ) {
//				for ( int x = 0; x < locationIds.length; x++ ) {
//					int aLocID = Integer.parseInt( locationIds[x] );
//					
//					if ( aLocID == locationID ) {
//						selected = true;
//					}
//				}
//    		}	
//			
//    		String arrayLine = "\tlocList[" + j + "] = new locations("+ localeID + ", "+ 
//					locationID + ", \"" + locName + "\", " + selected +");\n";
//				
//    		resetStr.append(arrayLine);
//    		js.append(arrayLine);
//    		
//    	}
//    	
//    	resetStr.append("\tselectLocale();\n");
//    	resetStr.append("\t}\n");
//    	resetStr.append("}\n");
//    	
//    	js.append(resetStr);
//    	
//    	return ( js.toString() );
//			
//	}
   
   public String createLocaleJavascript ( ArrayList <UserLocationDTO> locations, String [] locationIds, boolean showAllSelect ) {
    	
    	StringBuilder js = new StringBuilder ("\n");
    	
//    	StringBuilder resetStr = new StringBuilder("\nfunction resetScript () { \n");
//    	resetStr.append("\tif ( confirm ( \"Are you sure you want to reset your locations?\" ) ) {\n");	
    	js.append("\tlocList = new Array(" + locations.size() + ");\n");
    	
       	long initValue = 0;
    	boolean initSelected = false;
    	String initName = "*** ALL ***";
    	String arrayLine = "\tlocList[" + initValue + "] = new locations("+ initValue + ", "+ 
			initValue + ", \"" + initName + "\", " + initSelected +");\n";
		
//    	resetStr.append(arrayLine);
    	js.append(arrayLine);   
    	
    	
 
    	   	
    	
    	
    	for ( int j = 0; j < locations.size(); j++ ) {
    		UserLocationDTO location = (UserLocationDTO) locations.get(j);
    		
    		long localeID = location.getRegionId();
    		long locationID = location.getLocationId();
    		String locName = location.getName();
    		boolean selected = false;
    		
    		if ( locationIds != null ) {
				for ( int x = 0; x < locationIds.length; x++ ) {
					int aLocID = Integer.parseInt( locationIds[x] );
					
					if ( aLocID == locationID ) {
						selected = true;
					}
				}
    		}	
			
    		 arrayLine = "\tlocList[" + (j+1) + "] = new locations("+ localeID + ", "+ 
					locationID + ", \"" + locName + "\", " + selected +");\n";
				
//    		resetStr.append(arrayLine);
    		js.append(arrayLine);
    		
    	}
    	
//    	resetStr.append("\tselectLocale();\n");
//    	resetStr.append("\t}\n");
//    	resetStr.append("}\n");
    	
//    	js.append(resetStr);
    	
    	return ( js.toString() );
			
	}   
    
   public String createLocaleJavascriptAll ( ArrayList <UserLocationDTO> locations, String [] locationIds, boolean showAllSelect ) {
    	
    	StringBuilder js = new StringBuilder ("\n");
    	
//    	StringBuilder resetStr = new StringBuilder("\nfunction resetScript () { \n");
//    	resetStr.append("\tif ( confirm ( \"Are you sure you want to reset your locations?\" ) ) {\n");	
    	js.append("\tlocList = new Array(" + locations.size() + ");\n");
    	
    	//showAllSelect 
    	
       	//showAllSelect 
    	String arrayLine = "";
    	if (showAllSelect){
	       	long initValue = 0;
	    	boolean initSelected = false;
	    	String initName = "*** ALL ***";
	        arrayLine = "\tlocList[" + initValue + "] = new locations("+ initValue + ", "+ 
				initValue + ", \"" + initName + "\", " + initSelected +");\n";
	    	js.append(arrayLine);
    	}

    	
    	
    	for ( int j = 0; j < locations.size(); j++ ) {
    		UserLocationDTO location = (UserLocationDTO) locations.get(j);
    		
    		long localeID = location.getRegionId();
    		long locationID = location.getLocationId();
    		String locName = location.getName();
    		boolean selected = false;
    		
    		if ( locationIds != null ) {
				for ( int x = 0; x < locationIds.length; x++ ) {
					int aLocID = Integer.parseInt( locationIds[x] );
					
					if ( aLocID == locationID ) {
						selected = true;
					}
				}
    		}	
			
    		 arrayLine = "\tlocList[" + (j+1) + "] = new locations("+ localeID + ", "+ 
					locationID + ", \"" + locName + "\", " + selected +");\n";
				
//    		resetStr.append(arrayLine);
    		js.append(arrayLine);
    		
    	}
    	
//    	resetStr.append("\tselectLocale();\n");
//    	resetStr.append("\t}\n");
//    	resetStr.append("}\n");
    	
//    	js.append(resetStr);
    	
    	return ( js.toString() );
			
	}   
    
    
    public String createLocationWithVendorJavascript ( ArrayList <ApVendorLocationDTO> vendorLocations, String [] vendorIds, DbUserSession user ) {
    	return ( createLocationWithVendorJavascript (vendorLocations, vendorIds, user, false) );
		
	}   
    public String createLocationWithVendorJavascript ( ArrayList <ApVendorLocationDTO> vendorLocations, String [] vendorIds, DbUserSession user, ArrayList <ApVendorDTO> vendorList ) {
    	return ( createLocationWithVendorJavascriptAll (vendorLocations, vendorIds, user, true , vendorList ) );
		
	}     
public String createLocationWithVendorJavascript ( ArrayList <ApVendorLocationDTO> vendorLocations, String [] vendorIds, DbUserSession user,  boolean showAllSelect ) {
    	
    	StringBuilder js = new StringBuilder ("\n");
    	
//    	StringBuilder resetStr = new StringBuilder("\nfunction resetScript () { \n");
//    	resetStr.append("\tif ( confirm ( \"Are you sure you want to reset your vendor?\" ) ) {\n");
    		
    	js.append("\tlocVendorList = new Array(" + (vendorLocations.size()+1) + ");\n");
    	ApVendorDAO vendorDao = new ApVendorDAO(user);
    	//showAllSelect 
    	long initValue = 0;
    	boolean initSelected = false;
    	String initName = "--Select--";
    	String arrayLine = "\tlocVendorList[" + initValue + "] = new vendors("+ initValue + ", "+ 
			initValue + ", \"" + initName + "\", " + initSelected +");\n";
		
//    	resetStr.append(arrayLine);
    	js.append(arrayLine);
    	for ( int j = 0; j < vendorLocations.size(); j++ ) {
    		ApVendorLocationDTO vendorLocation = (ApVendorLocationDTO) vendorLocations.get(j);
    		
    		long locationID = vendorLocation.getLocationID();
    		long vendorID = vendorLocation.getVendorID();
    		
    		ApVendorDTO vendor = vendorDao.getApVendor(vendorID);
    		
    		
    		String vendorName = "";
    		//vendorName = forHTML(vendor.getName());
    		//vendorName = vendor.getName().replace('\"', '\'');
    		vendorName = vendor.getName().replace('\"', ' ');
    		vendorName = vendorName.replace('\'', ' ');
    		
    		
    		boolean selected = false;
    		
    		if ( vendorIds != null ) {
				for ( int x = 0; x < vendorIds.length; x++ ) {
					int aVendorID = Integer.parseInt( vendorIds[x] );
					
					if ( aVendorID == vendorID ) {
						selected = true;
					}
				}
    		}	
			
    		 arrayLine = "\tlocVendorList[" + (j+1) + "] = new vendors("+ locationID + ", "+ 
    			vendorID + ", \"" + vendorName + "\", " + selected +");\n";
				
//    		resetStr.append(arrayLine);
    		js.append(arrayLine);
    		
    	}
    	
//    	resetStr.append("\tselectALocation();\n");
//    	resetStr.append("\t}\n");
//    	resetStr.append("}\n");
//    	
//    	js.append(resetStr);
    	
    	return ( js.toString() );
			
	}
public String createLocationWithVendorJavascriptAll ( ArrayList <ApVendorLocationDTO> vendorLocations, String [] vendorIds, DbUserSession user,  boolean showAllSelect, ArrayList <ApVendorDTO> vendorList ) {
	
	StringBuilder js = new StringBuilder ("\n");
	
//	StringBuilder resetStr = new StringBuilder("\nfunction resetScript () { \n");
//	resetStr.append("\tif ( confirm ( \"Are you sure you want to reset your vendor?\" ) ) {\n");
		
	js.append("\tlocVendorList = new Array(" + (vendorLocations.size()+1) + ");\n");
	ApVendorDAO vendorDao = new ApVendorDAO(user);
	//showAllSelect 
	long initValue = 0;
	boolean initSelected = false;
	String initName = "--Select--";
	String arrayLine = "\tlocVendorList[" + initValue + "] = new vendors("+ initValue + ", "+ 
		initValue + ", \"" + initName + "\", " + initSelected +");\n";
	
//	resetStr.append(arrayLine);
//	String arrayLine = "";
	js.append(arrayLine);

	int k = 0;
	for ( int j = 0; j < vendorLocations.size(); j++ ) {
		ApVendorLocationDTO vendorLocation = (ApVendorLocationDTO) vendorLocations.get(j);
		
		long locationID = vendorLocation.getLocationID();
		long vendorID = vendorLocation.getVendorID();
		
		ApVendorDTO vendor = vendorDao.getApVendor(vendorID);
		
		
		String vendorName = "";
		//vendorName = forHTML(vendor.getName());
//		vendorName = vendor.getName().replace('\"', '\'');
		vendorName = vendor.getName().replace('\"', ' ');
		vendorName = vendorName.replace('\"', ' ');
		
		boolean selected = false;
		
		if ( vendorIds != null ) {
			for ( int x = 0; x < vendorIds.length; x++ ) {
				int aVendorID = Integer.parseInt( vendorIds[x] );
				
				if ( aVendorID == vendorID ) {
					selected = true;
				}
			}
		}	
		
		 arrayLine = "\tlocVendorList[" + (j+1) + "] = new vendors("+ locationID + ", "+ 
			vendorID + ", \"" + vendorName + "\", " + selected +");\n";
			
//		resetStr.append(arrayLine);
		js.append(arrayLine);
		k = j+1;
		
	}
	
	if (showAllSelect) {
		for ( ApVendorDTO vendor: vendorList) {
			k = k+1;
			arrayLine = "\tlocVendorList[" + (k) + "] = new vendors(0, "+ 
			vendor.getVendorID() + ", \"" + vendor.getName() + "\", false);\n";
			
//			resetStr.append(arrayLine);
			js.append(arrayLine);
			
		}
	}
	
	
//	resetStr.append("\tselectALocation();\n");
//	resetStr.append("\t}\n");
//	resetStr.append("}\n");
	
//	js.append(resetStr);
	
	return ( js.toString() );
		
}

public String createInitialLocale ( ArrayList <UserLocaleDTO> locales ) {
	
	StringBuilder js = new StringBuilder ("\n");
	
	js.append("\tinitLocList = new Array(" + locales.size() + ");\n");
	
   	long initValue = 0;
	boolean initSelected = false;
	String initName = "*** ALL ***";
	String arrayLine = "\tinitLocList[" + initValue + "] = new initLocales("+ initValue + ", \""+ 
		initName + "\", " + initSelected +");\n";
	
	js.append(arrayLine);   	
	
	
	for ( int j = 0; j < locales.size(); j++ ) {
		UserLocaleDTO locale = (UserLocaleDTO) locales.get(j);
		
		String localeID = locale.getLocaleId();
		String name = locale.getName();
		boolean selected = false;
		
		 arrayLine = "\tinitLocList[" + (j+1) + "] = new initLocales("+ localeID + ", \""+ 
				name + "\", " + selected +");\n";
			
		js.append(arrayLine);
		
	}
	
	return ( js.toString() );
		
}   

public String createInitialLocation ( ArrayList <UserLocationDTO> locations, boolean includeAll ) {
	
	StringBuilder js = new StringBuilder ("\n");
	
	js.append("\tinitLocationList = new Array(" + locations.size() + ");\n");
	
//   	long initValue = 0;
//	boolean initSelected = false;
//	String initName = "*** ALL ***";
//	String arrayLine = "\tinitLocationList[" + initValue + "] = new initLocations("+ initValue + ", \""+ 
//		initName + "\", " + initSelected +");\n";
//	
//	js.append(arrayLine);   	
	String arrayLine = "";
	if (includeAll) {
	   	long initValue = 0;
		boolean initSelected = false;
		String initName = "*** ALL ***";
		arrayLine = "\tinitLocationList[" + initValue + "] = new initLocations("+ initValue + ", \""+ 
			initName + "\", " + initSelected +");\n";
		
		js.append(arrayLine);
	}
	
	for ( int j = 0; j < locations.size(); j++ ) {
		UserLocationDTO location = (UserLocationDTO) locations.get(j);
		
		String locationID = String.valueOf(location.getLocationId());
		String name = location.getName();
		boolean selected = false;
		
		 arrayLine = "\tinitLocationList[" + (j+1) + "] = new initLocations("+ locationID + ", \""+ 
				name + "\", " + selected +");\n";
			
		js.append(arrayLine);
		
	}
	
	return ( js.toString() );
		
}   



//public static String forHTML(String aText){ 
//    final StringBuilder result = new StringBuilder(); 
//    final StringCharacterIterator iterator = new StringCharacterIterator(aText); 
//    char character =  iterator.current(); 
//    while (character != StringCharacterIterator.DONE ){ 
//      if (character == '<') { 
//        result.append("&lt;"); 
//      } 
//      else if (character == '>') { 
//        result.append("&gt;"); 
//      } 
//      else if (character == '&') { 
//        result.append("&amp;"); 
//     } 
//      else if (character == '\"') { 
//        result.append("&quot;"); 
//      } 
//      else if (character == '\'') { 
//        result.append("&#039;"); 
//      } 
//      else if (character == '(') { 
//        result.append("&#040;"); 
//      } 
//      else if (character == ')') { 
//        result.append("&#041;"); 
//      } 
//      else if (character == '#') { 
//        result.append("&#035;"); 
//      } 
//      else if (character == '%') { 
//        result.append("&#037;"); 
//      } 
//      else if (character == ';') { 
//        result.append("&#059;"); 
//      } 
//      else if (character == '+') { 
//        result.append("&#043;"); 
//      } 
//      else if (character == '-') { 
//        result.append("&#045;"); 
//      } 
//      else { 
//        //the char is not a special one 
//        //add it to the result as is 
//        result.append(character); 
//      } 
//      character = iterator.next(); 
//    } 
//    return result.toString(); 
// }     
    
}
