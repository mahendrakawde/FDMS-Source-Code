/**
 * 
 */
package com.aldorsolutions.webfdms.reporting.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.aldorsolutions.webfdms.reporting.crystal.model.FormAvailableFilterDTO;
import com.aldorsolutions.webfdms.reporting.crystal.model.FormAvailableFilterTypeDTO;

/**
 * @author David Rollins
 * Date: Jul 19, 2007
 * File: FilterInterface.java	
 * 
 */
public interface FilterInterface {
	
	FormAvailableFilterDTO filter = null;
	FormAvailableFilterTypeDTO filterType = null;
	
	public static final int FILTER_DATE = 1;
	public static final int FILTER_LOCATION = 2;
	public static final int FILTER_LOCALE = 3;
	
	/**
	 * 
	 * @return
	 */
	public String getAdminHTML() throws FilterException;
	
	/**
	 * 
	 * @return
	 */
	public String getReportGenerationHTML() throws FilterException ;
	
	/**
	 * 
	 * @param request
	 */
	public FormAvailableFilterDTO processAdminSubmit(HttpServletRequest request) throws FilterException;
	
	/**
	 * 
	 * @param request
	 */
	public Map <String, Object> processReportGenerationSubmit(HttpServletRequest request) throws FilterException;
	
	/**
	 * @return the filter
	 */
	public FormAvailableFilterDTO getFilter();

	/**
	 * @return the filterType
	 */
	public FormAvailableFilterTypeDTO getFilterType();

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(FormAvailableFilterDTO filter);

	/**
	 * 
	 * @param filterType
	 */
	public void setFilterType(FormAvailableFilterTypeDTO filterType);
	
	/**
	 * 
	 * @return
	 * @throws FilterException
	 */
	public int getFilterTypeID ( ) throws FilterException;
	
	/**
	 * 
	 * @return
	 * @throws FilterException
	 */
	public String getFilterTypeDescription ( ) throws FilterException;
	
}