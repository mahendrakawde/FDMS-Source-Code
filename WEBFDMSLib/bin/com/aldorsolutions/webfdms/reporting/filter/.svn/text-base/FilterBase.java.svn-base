/**
 * 
 */
package com.aldorsolutions.webfdms.reporting.filter;

import com.aldorsolutions.webfdms.reporting.crystal.model.FormAvailableFilterDTO;
import com.aldorsolutions.webfdms.reporting.crystal.model.FormAvailableFilterTypeDTO;

/**
 * @author David Rollins
 * Date: Jul 19, 2007
 * File: FilterBase.java	
 * 
 */
public class FilterBase {
	
	FormAvailableFilterDTO filter;
	FormAvailableFilterTypeDTO filterType;

	/**
	 * @return the filter
	 */
	public FormAvailableFilterDTO getFilter() {
		return filter;
	}

	/**
	 * @return the filterType
	 */
	public FormAvailableFilterTypeDTO getFilterType() {
		return filterType;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(FormAvailableFilterDTO filter) {
		this.filter = filter;
	}

	/**
	 * @param filterType the filterType to set
	 */
	public void setFilterType(FormAvailableFilterTypeDTO filterType) {
		this.filterType = filterType;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getFilterTypeID ( ) throws FilterException {
		
		if ( filterType == null ) {
			throw new FilterException ( "FilterType not defined for this Interface" );
		}
		else {
			return ( filterType.getFormFilterTypeID() );
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFilterTypeDescription ( ) throws FilterException {
		
		if ( filterType == null ) {
			throw new FilterException ( "FilterType not defined for this Interface" );
		}
		else {
			return ( filterType.getDescription() );
		}
		
	}
	
	public String isParameterSelected (long value) throws FilterException {
		String isSelected = "";
		
		if ( filter == null ) {
			if ( filterType == null ) {
				throw new FilterException ("Filter Type cannot be null");
			} else if ( value == filterType.getDefaultValue() ) {
				return (" SELECTED ");
			}
			
		} else if ( value == filter.getFilterParameter() ) {
			return (" SELECTED ");
		}
		
		return ( isSelected );
	}
	
	
}
