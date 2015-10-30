package com.aldorassist.webfdms.delegate;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import com.aldorassist.webfdms.dao.CompanyOptionsDAO;
import com.aldorassist.webfdms.dao.CompanyOptionsValueDAO;
import com.aldorassist.webfdms.dao.LocationOptionValueDAO;
import com.aldorassist.webfdms.dao.LocationOptionsDAO;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.CompanyOptionsValueDTO;
import com.aldorassist.webfdms.model.LocationOptionValueDTO;
import com.aldorassist.webfdms.model.LocationOptionsDTO;


public class LocationOptionsManager {

	private static Logger logger = Logger.getLogger(LocationOptionsManager.class.getName());

	/**
	 * Get company option value
	 * @param companyID
	 * @param locationId
	 * @param locationOptionValue
	 * @return
	 */
	public int getLocationOptionValue(int companyID, int locationId, int locationOptionValue) {
		
		
		ArrayList<LocationOptionValueDTO> optionValues = getLocationOptionsValue(companyID,locationId);
		ArrayList<LocationOptionsDTO> options = getLocationOptions();
		
		for ( LocationOptionsDTO option : options ) {
			
			boolean found = false;
			
			for ( LocationOptionValueDTO optionValue : optionValues ) {
				if (optionValue.getLocationOptionValue() == locationOptionValue) {
					found = true;
					return (optionValue.getValue());
				}
			}
			
			if ( (found == false) && (locationOptionValue == option.getLocationOptionID()) ) {
				return (option.getDefaultvalue());
			}
		}
		
		return ( -1 );
			
	}
	
	/**
	 * 
	 * @param companyID
	 * @return
	 */
	public ArrayList <LocationOptionValueDTO> getLocationOptionsValue(int companyID,int locationId) {
		try {
			LocationOptionValueDAO optionsDAO = new LocationOptionValueDAO();
			ArrayList <LocationOptionValueDTO> options = optionsDAO.getLocationOptionsValues(String.valueOf(companyID), String.valueOf(locationId));
			return (options);
		} catch (Exception e) {
			logger.error("getCompanyOptionsValue Persistence Exception:", e);
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList <LocationOptionsDTO> getLocationOptions() {
		try {
			LocationOptionsDAO optionsDAO = new LocationOptionsDAO();
			ArrayList <LocationOptionsDTO> options = optionsDAO.getLocationOptions();
			return (options);
		} catch (Exception e) {
			logger.error("getCompanyOptions Persistence Exception:", e);
			return null;
		}
	}
}
