package com.aldorsolutions.webfdms.util;

import org.apache.log4j.Logger;
/**
 * Generic formatting of date from FDMS MMDDYYYY stored dates
 * created form fdms.tools.SimpleDateFormat
 * Creation date: (7/5/00 4:48:41 PM)
 * @author: 
 */
public class FormatTime {
    
    private static Logger logger = Logger.getLogger(FormatTime.class.getName());
    
	/**
	 * SimpleDateFormat constructor comment.
	 */
	public FormatTime() {
		super();
	}
	
	public static String convertTimeToAMPM (String militaryTime) {
		String result="";
		if (militaryTime.trim().compareTo("") != 0 && militaryTime != null && militaryTime.trim().compareTo("00:00:00") != 0) {
			int hour = FormatNumber.parseInteger(militaryTime.substring(0,2));
			if (hour > 12) {
				hour -= 12;
				if (hour < 10)
					result = "0" + hour + militaryTime.substring(2,5) + " PM";
				else
					result = hour + militaryTime.substring(2,5) + " PM";
			}
			else if (hour == 12) {
				result = hour + militaryTime.substring(2,5) + " PM";
			}
			else
				result = militaryTime.substring(0,5) + " AM";
		}
		return result;
	}

	public static String convertTimeToAMPMShortHand (String militaryTime) {
		String result="";
		if (militaryTime != null && militaryTime.trim().compareTo("") != 0  && militaryTime.trim().compareTo("00:00:00") != 0) {
			int hour = FormatNumber.parseInteger(militaryTime.substring(0,2));
			if (hour >= 12) {
				if (hour > 12)
					hour -= 12;
				result = hour + militaryTime.substring(2,5) + " PM";
				logger.debug("Hour is: " + hour);
			}
			else
				result = militaryTime.substring(0,5) + " AM";
		}
		return result;
	}
	
	public static String convertTimeToMilitary (String AMPMTime) {
		String result="";
		if (AMPMTime.trim().compareTo("") != 0 && AMPMTime != null) {
			if (AMPMTime.charAt(6) == 'A' || AMPMTime.substring(0,2).compareTo("12") == 0)
				result = AMPMTime.substring(0,5) + ":00";
			else {
				int hour = FormatNumber.parseInteger(AMPMTime.substring(0,2));
				hour += 12;
				result = hour + AMPMTime.substring(2,5) + ":00";
			}
		}

		return result;
	}
	
	/**
	 * @param obj
	 * @return
	 */
	public static java.sql.Time parseTime (Object obj) {
		if ( obj == null ) {
			return ( null );
		}
		
		if ( obj instanceof String ) {
			return ( null );
		}
		else if ( obj instanceof java.sql.Time ) {
			return ( (java.sql.Time) obj);
		}
		
		return ( null );
	}

}
