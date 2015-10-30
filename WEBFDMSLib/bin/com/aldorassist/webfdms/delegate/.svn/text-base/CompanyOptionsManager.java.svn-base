package com.aldorassist.webfdms.delegate;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorassist.webfdms.dao.CompanyOptionsDAO;
import com.aldorassist.webfdms.dao.CompanyOptionsValueDAO;
import com.aldorassist.webfdms.model.CompanyOptionsDTO;
import com.aldorassist.webfdms.model.CompanyOptionsValueDTO;

/**
 * 
 * @author David Rollins
 * Date: Jul 10, 2007
 * File: CompanyOptionsManager.java	
 *
 */
public class CompanyOptionsManager {

	private static Logger logger = Logger.getLogger(CompanyOptionsManager.class.getName());

	/**
	 * Get company option value
	 * @param t
	 * @param companyID
	 * @param optionID
	 * @return
	 */
	public int getCompanyOptionValueForCompany(long companyID, int optionID) {
		
		
		ArrayList <CompanyOptionsValueDTO> optionValues = getCompanyOptionsValue(companyID);
		ArrayList <CompanyOptionsDTO> options = getCompanyOptions();
		
		for ( CompanyOptionsDTO option : options ) {
			
			boolean found = false;
			
			for ( CompanyOptionsValueDTO optionValue : optionValues ) {
				if (optionValue.getCompanyOptionID() == optionID) {
					found = true;
					return (optionValue.getValue());
				}
			}
			
			if ( (found == false) && (optionID == option.getCompanyOptionID()) ) {
				return (option.getDefaultValue());
			}
		}
		
		return ( -1 );
			
	}
	
	/**
	 * 
	 * @param companyID
	 * @return
	 */
	public ArrayList <CompanyOptionsValueDTO> getCompanyOptionsValue(long companyID) {
		try {
			CompanyOptionsValueDAO optionsDAO = new CompanyOptionsValueDAO();
			ArrayList <CompanyOptionsValueDTO> options = optionsDAO.getCompanyOptionValues(companyID);
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
	public ArrayList <CompanyOptionsDTO> getCompanyOptions() {
		try {
			CompanyOptionsDAO optionsDAO = new CompanyOptionsDAO();
			ArrayList <CompanyOptionsDTO> options = optionsDAO.getCompanyOptions();
			return (options);
		} catch (Exception e) {
			logger.error("getCompanyOptions Persistence Exception:", e);
			return null;
		}
	}
}
