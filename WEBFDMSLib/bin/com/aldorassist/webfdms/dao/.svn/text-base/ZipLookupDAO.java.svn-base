/**
 * 
 */
package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.util.DAO;

/**
 * @author David Rollins
 * Sep 10, 2007
 * ZipLookupDAO.java
 *
 */
public class ZipLookupDAO extends DAO {
	
	final private static Logger logger = Logger.getLogger(ZipLookupDAO.class.getName());

	private static String getSelectString () {
		return ( "Select ZipCode, City, County, State From zipcodedata Where " +
				"(Deleted=0) and (Country=?) and (ZipCode like ?) Order By City, " +
				"State, County" );
	}
	
	public ArrayList <String> getByZipCode(String zip, String country) {
		ArrayList <String> list = new ArrayList <String> (); 
		
        try {
        	String sql = getSelectString();
        	
            makeConnection(DAO.DB_FDMSCOMMON);
            statement = conn.prepareStatement(sql);
            statement.setString(1, country); // country
            statement.setString(2, zip + "%"); // add % for like lookup
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	String row = rs.getString(1) + "\t" + // Zipcode
            				 rs.getString(2) + "\t" + // City
            				 rs.getString(3) + "\t" + // County
            				 rs.getString(4);         // State
            	list.add(row);
            }
            
            list.trimToSize();
        } catch (SQLException e) {
            logger.error("SQLException in getByZipCode() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getByZipCode() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
	}
}
