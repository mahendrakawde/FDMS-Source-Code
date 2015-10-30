/**
 * 
 */
package com.aldorsolutions.webfdms.reporting.filter;

import com.aldorsolutions.webfdms.reporting.crystal.model.FormAvailableFilterTypeDTO;

/**
 * @author David Rollins
 * Date: Jul 20, 2007
 * File: FilterFactory.java	
 * 
 */
public class FilterFactory {

	public static FilterInterface createFilter (FormAvailableFilterTypeDTO filterType) {
		
		FilterInterface filter = createFilter(filterType.getFormFilterTypeID());
		filter.setFilterType(filterType);
		return filter;
	}

	private static FilterInterface createFilter (int filterType) {
		
		FilterInterface filter = null;
		
		switch (filterType) {
			case FormAvailableFilterTypeDTO.FILTER_DATE : 
				filter = new DateFilter ();
				break;
			case FormAvailableFilterTypeDTO.FILTER_LOCALE :
				filter = new LocaleFilter ();
				break;
			case FormAvailableFilterTypeDTO.FILTER_LOCATION :
				filter = new LocationFilter ();
				break;	
		}
		
		return filter;
	}
}
