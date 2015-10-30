package com.aldorassist.webfdms.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.model.LocationOptionsDTO;
import com.aldorsolutions.webfdms.util.DAO;

public class LocationOptionsDAO extends DAO{
	private Logger logger = Logger.getLogger(LocationOptionsDAO.class.getName());
	
	public ArrayList <LocationOptionsDTO> getLocationOptions( ) {
    	ArrayList <LocationOptionsDTO> list = new ArrayList <LocationOptionsDTO> ();
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("select locationOptionID, name, description, defaultValue " +
            		"from locationoptions" );
            
            makeConnection(DAO.DB_FDMSUSERS);
            statement = conn.prepareStatement(sql.toString());
            
            rs = statement.executeQuery();
            
            while (rs.next()) {
            	int col = 0;
            	LocationOptionsDTO locationOptionsDTO = new LocationOptionsDTO();
            	
            	locationOptionsDTO.setLocationOptionID(rs.getInt(++col));
            	locationOptionsDTO.setName(rs.getString(++col));
            	locationOptionsDTO.setDescription(rs.getString(++col));
            	locationOptionsDTO.setDefaultvalue(rs.getInt(++col) );
            	list.add(locationOptionsDTO);
            }           
        } catch (SQLException e) {
            logger.error("SQLException in getLocationOptions() : ", e);
        } catch (Exception e) {
            logger.error("Exception in getLocationOptions() : ", e);
        } finally {
            closeConnection();
        }
        
        return list;
    }
	
}
