/**
 * 
 */
package com.aldorsolutions.webfdms.reporting.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.aldorsolutions.webfdms.reporting.crystal.model.FormAvailableFilterDTO;
import com.aldorsolutions.webfdms.reporting.crystal.model.FormAvailableFilterTypeDTO;

/**
 * @author David Rollins
 * Date: Jul 19, 2007
 * File: DateFilter.java	
 * 
 */
public class DateFilter extends FilterBase implements FilterInterface {
	
	public static final int DATE_FILTER_NONE = 0;
	public static final int DATE_FILTER_BETWEEN_DATES = 1;
	public static final int DATE_FILTER_FROM_DATE = 2;
	
	/* (non-Javadoc)
	 * @see FilterInterface#getAdminHTML()
	 */
	public String getAdminHTML() throws FilterException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<select name=\"filterDateParameter\">");
		sb.append("<option value=\"" + DATE_FILTER_NONE + "\"");
		sb.append( isParameterSelected(DATE_FILTER_NONE) );
		sb.append(">None</option>");
		sb.append("<option value=\"" + DATE_FILTER_BETWEEN_DATES + "\"");
		sb.append( isParameterSelected(DATE_FILTER_BETWEEN_DATES) );
		sb.append(">Between Dates</option>");
		sb.append("<option value=\"" + DATE_FILTER_FROM_DATE + "\"");
		sb.append( isParameterSelected(DATE_FILTER_FROM_DATE) );
		sb.append(">From Date</option>");
		sb.append("</select>");
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see FilterInterface#getReportGenerationHTML()
	 */
	public String getReportGenerationHTML() {
		StringBuilder sb = new StringBuilder();
		FormAvailableFilterDTO filter = getFilter();
		FormAvailableFilterTypeDTO filterType = getFilterType();
		
		if ( filter == null ) {
			filter = new FormAvailableFilterDTO();
			filter.setFilterTypeID(FilterInterface.FILTER_DATE);
			filter.setFilterParameter( filterType.getDefaultParameter() );
		}
				
		if ( filter.getFilterParameter() == DATE_FILTER_BETWEEN_DATES ) {
			sb.append("Date From: <input type=text size=\"15\" name=\"filterDateFrom\" >");
			getCalendarJS(sb, "document.forms[0].filterDateFrom", "filterDateFrom1");
			sb.append(" &nbsp; ");
			sb.append("Date To: <input type=text size=\"15\" name=\"filterDateTo\" >");
			getCalendarJS(sb, "document.forms[0].filterDateTo", "filterDateTo1");
			
		} else if ( filter.getFilterParameter() == DATE_FILTER_FROM_DATE ) {
			sb.append("Date From: <input type=text size=\"15\" name=\"filterDateFrom\" >");
			getCalendarJS(sb, "document.forms[0].filterDateFrom", "filterDateFrom1");
		} 
		
		return sb.toString();
	}
	
	private void getCalendarJS (StringBuilder sb, String javascriptFormField, String fieldID) { 
		sb.append("<script type=\"text/javascript\">");
		sb.append("oDateMask.attach(" + javascriptFormField + ");</script>");
		sb.append("<a onMouseOver=\"window.status='Click to View Calendar'; return true;\" ");
		sb.append("onMouseOut=\"window.status='';\" href=\"javascript:doNothing()\"  ");
		sb.append("onClick=\"calPopUp.select(" + javascriptFormField + ",'"+fieldID+"', ");
		sb.append("'MM/dd/yyyy'," + javascriptFormField + ".value); return false;\">");
		sb.append("<img ID=\""+fieldID+"\" name=\""+fieldID+"\" src=\"/dashboard/images/calendar.gif\" ");
		sb.append("width=\"16\" height=\"16\" border=\"0\" /></a> ");
	}

	/* (non-Javadoc)
	 * @see FilterInterface#processAdminSubmit()
	 */
	public FormAvailableFilterDTO processAdminSubmit(HttpServletRequest request) {
		
		FormAvailableFilterDTO filter = getFilter();
		
		if ( filter == null ) {
			filter = new FormAvailableFilterDTO();
			filter.setFilterTypeID(FilterInterface.FILTER_DATE);
		} 
		
		String filterDate = request.getParameter("filterDateParameter");
		
		try {
			int filterDateType = Integer.parseInt(filterDate);
			filter.setFilterParameter(filterDateType);
		} catch ( Exception pe ) {
			pe.printStackTrace();
		}
		
		this.setFilter (filter);
		
		return ( filter );
	}

	/* (non-Javadoc)
	 * @see FilterInterface#processReportGenerationSubmit()
	 */
	public Map <String, Object> processReportGenerationSubmit(HttpServletRequest request) throws FilterException {
		
		String filterDateFrom = request.getParameter("filterDateFrom");
		String filterDateTo = request.getParameter("filterDateTo");
		
		HashMap <String, Object> values = new HashMap <String, Object> ();
		values.put("dateFrom", filterDateFrom);
		values.put("dateTo", filterDateTo);
		
		return ( values );

	}
	
}
