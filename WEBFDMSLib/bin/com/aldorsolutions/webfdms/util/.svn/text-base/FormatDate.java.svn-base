package com.aldorsolutions.webfdms.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.log4j.Logger;

/**
 * Generic formatting of date from FDMS MMDDYYYY stored dates created form
 * fdms.tools.SimpleDateFormat Creation date: (7/5/00 4:48:41 PM)
 * 
 * @author:
 */
public class FormatDate {

	private static Logger logger = Logger.getLogger(FormatDate.class.getName());

	private static SimpleDateFormat c_sdfFormatter = null;

	/**
	 * SimpleDateFormat constructor comment.
	 */
	public FormatDate() {
		super();
	}

	public static java.util.Date addToDate(java.util.Date fromDate, int plusDays, int plusMonths) {
		Calendar cal;
		java.util.Date returnDate = null;
		try {
			cal = Calendar.getInstance();
			cal.setTime(fromDate);
			cal.add(Calendar.MONTH, plusMonths);
			cal.add(Calendar.DAY_OF_MONTH, plusDays);
			returnDate = cal.getTime();
		} catch (Exception e) {
			logger.debug("FormatDate.addToDate parse exception:" + e);
		}
		return returnDate;
	}
	
	
	/**
	 * Take a string date in MMDDYYYY format and add a specified number of days
	 * and/or months to it. Creation date: (1/31/2003 4:05:27 PM)
	 * 
	 * @return java.lang.String MMDDYYYY date that is future date based on
	 *         plus-days and plus-months
	 * @param plusDays
	 *            int number of days in the future to calculate the date for
	 * @param plusMonths
	 *            int number of months in the future to calculate the date for
	 */
	public static String addToDateMMDDYYYY(String fromDate, int plusDays, int plusMonths) {
		SimpleDateFormat formatter;
		java.util.Date saledate;
		Calendar cal;
		String returnDate;
		try {
			if (fromDate == null || fromDate.compareTo("00000000") <= 0 || fromDate.trim().equals("")) {
				return "";
			}
			// Make default due date based on sale date
			formatter = new SimpleDateFormat("MMddyyyy");
			saledate = formatter.parse(fromDate); // gets default sale date if
													// not already present
			// calcualte sale date into the future
			cal = Calendar.getInstance();
			cal.setTime(saledate);
			cal.add(Calendar.MONTH, plusMonths);
			cal.add(Calendar.DAY_OF_MONTH, plusDays);
			returnDate = formatter.format(cal.getTime());
		} catch (java.text.ParseException e) {
			logger.debug("FormatDate.addToDate parse exception:" + e);
			returnDate = "parseerr";
		}
		return returnDate;
	}

	/**
	 * Convert the input parameter date and return it as a displayable date with
	 * delimiters. Creation date: (9/19/2002 7:31:16 PM)
	 * 
	 * @return java.lang.String Current Date
	 */
	public static String convertDateToMM_DD_YY(java.util.Date aDate) {
		if (aDate == null)
			return "";
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("MM/dd/yy");
		return formatter.format(aDate);
	}

	/**
	 * Convert the input parameter date and return it as a displayable date with
	 * delimiters. Creation date: (9/19/2002 7:31:16 PM)
	 * 
	 * @return java.lang.String Current Date
	 */
	public static String convertDateToSHORT(java.sql.Date aDate) {
		if (aDate == null)
			return "";

		String outDate = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US).format(aDate);

		return (outDate);
	}

	public static String convertDateToSHORT(java.util.Date aDate) {
		if (aDate == null)
			return "";
		return (convertDateToSHORT(new java.sql.Date(aDate.getTime())));
	}

	/**
	 * Convert the input parameter date and return it as a displayable date with
	 * delimiters. Creation date: (9/19/2002 7:31:16 PM)
	 * 
	 * @return java.lang.String Current Date
	 */
	public static String convertDateToMM_DD_YYYY(java.util.Date aDate) {
		if (aDate == null)
			return "";
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(aDate);
	}

	/**
	 * Convert the input parameter date and return it as a displayable date with
	 * delimiters. Creation date: (9/19/2002 7:31:16 PM)
	 * 
	 * @return java.lang.String Current Date
	 */
	public static String convertDateToMM_DD_YYYY(java.sql.Date aDate) {
		if (aDate == null)
			return "";
		return (convertDateToMM_DD_YYYY(new java.util.Date(aDate.getTime())));
	}

	/**
	 * Convert the input parameter date and return it as often stored in FDMS in
	 * MMDDYYYY format. Creation date: (9/19/2002 7:31:16 PM)
	 * 
	 * @return java.lang.String Current Date
	 */
	public static String convertDateToMMDDYYYY(java.util.Date aDate) {

		if (aDate == null) {
			return "";
		}

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("MMddyyyy");
		return formatter.format(aDate);
	}

	/**
	 * Convert the input parameter date and return it as often stored in FDMS in
	 * YYYYMMDD format, as is used for History Table transaction date Creation
	 * date: (4/11/2003 7:31:16 PM)
	 * 
	 * @return java.lang.String Current Date
	 */
	public static String convertDateToYYYYMMDD(java.util.Date aDate) {

		if (aDate == null) {
			return "";
		}

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd");
		return formatter.format(aDate);
	}

	
	public static String convertDateToMonthString(Date aDate) {

		if (aDate == null) {
			return "";
		}

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("MMMMMMMMM");
		return formatter.format(aDate);
	}

	

	/**
	 * Convert the input parameter date and return it as often stored in FDMS in
	 * YYYYMMDD format, as is used for History Table transaction date Creation
	 * date: (4/11/2003 7:31:16 PM)
	 * 
	 * @return java.lang.String Current Date
	 */
	public static String convertDateToYYYY_MM_DD(java.util.Date aDate) {

		if (aDate == null) {
			return "";
		}

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(aDate);
	}

	
	public static String convertDateToYYYYString(Date aDate) {
		if (aDate == null) {
			return "";
		}

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy");
		return formatter.format(aDate);
	}

	/**
	 * Parse and convert a MM/DD/YY date to a java.util.Date
	 * 
	 * @params String dateToParse
	 * 
	 * @returns java.util.Date or null if invalid input string
	 * 
	 * @exception
	 */
	public static java.util.Date convertToDate(String theString) throws Exception {
		java.util.Date datRV = null;
		if (theString != null && !theString.trim().equals("") && theString.compareTo("00") > 0) {
			datRV = getDateFormatter().parse(theString);
			// Now make sure the date is after 1800.
			java.util.Date dateMin = getDateFormatter().parse("1/1/1800");
			if (datRV.before(dateMin) == true) {
				throw new Exception("Date must be after 1/1/1800");
			}
		}
		
		return datRV;
	}
	
	/**
	 * Parse and convert a MM/DD/YY date to a java.sql.Date
	 * 
	 * @params String dateToParse
	 * 
	 * @returns java.sql.Date or null if invalid input string
	 * 
	 * @exception
	 */
	public static java.sql.Date convertToSQLDate(String theString) throws Exception {
		java.util.Date datRV = convertToDate(theString);
		
		java.sql.Date myDate = new java.sql.Date (datRV.getTime());
		
		return myDate;
	}

	/**
	 * Parse and convert a MM/DD/YY date to the style used in FDMS which is
	 * MMDDYYYY
	 * 
	 * @params String dateToParse
	 * 
	 * @returns String dateMMDDYYYY
	 * 
	 * @exception
	 */
	public static String convertToDateMMDDYYYY(String theString) throws Exception {
		String mydate = new String();
		java.util.Date datRV = null;

		if (theString != null && !theString.trim().equals("") && theString.compareTo("00") > 0) {
			try {
				datRV = getDateFormatter().parse(theString);
				SimpleDateFormat mmddyyyy = new SimpleDateFormat("MMddyyyy");
				mmddyyyy.setLenient(false);
				mydate = mmddyyyy.format(datRV);
			} catch (Exception e) {
				logger.debug("Error : " + e);

				Exception niceException = new Exception("Please enter a valid date like mm/dd/yyyy");
				throw niceException;
			}
			// Now make sure the date is after 1800.
			java.util.Date dateMin = getDateFormatter().parse("1/1/1800");
			if (datRV.before(dateMin) == true) {
				throw new Exception("Date must be after 1/1/1800");
			}
		}

		return mydate;
	}

	/**
	 * Parse and convert a YYYYMMDD date to a java.util.Date
	 * 
	 * @params String dateToParse
	 * 
	 * @returns java.util.Date or null if invalid input string
	 * 
	 * @exception
	 */
	public static java.util.Date convertToDateYYMMDD(String theString) {
		java.util.Date datRV = null;
		if (theString != null && !theString.trim().equals("") && theString.compareTo("00") > 0) {
			try {
				SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
				myFormatter.setLenient(false);
				datRV = myFormatter.parse(theString);
			} catch (Exception e) {
				return null;
			}
		}

		return datRV;
	}

	
	
	public static Date convertToDateYYYY_MM_DD(String theString) {
		java.util.Date datRV = null;
		if (theString != null && !theString.trim().equals("") && theString.compareTo("00") > 0) {
			try {
				SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
				myFormatter.setLenient(false);
				datRV = myFormatter.parse(theString);
			} catch (Exception e) {
				return null;
			}
		}

		return datRV;
	}

	
	public static String convertMMddyyyyToYYYY_MM_DD(String theString) throws Exception {
		String mydate = new String();
		Date datRV = null;

		if (theString != null && !theString.trim().equals("") && theString.compareTo("00") > 0) {
			try {
//				datRV = getDateFormatter().parse(theString);
				SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy");
				datRV = formatter.parse(theString); 

				SimpleDateFormat mmddyyyy = new SimpleDateFormat("yyyy-MM-dd");
				mmddyyyy.setLenient(false);
				mydate = mmddyyyy.format(datRV);
			} catch (Exception e) {
				logger.debug("Error : " + e);

				Exception niceException = new Exception("Please enter a valid date like mm/dd/yyyy");
				throw niceException;
			}
			// Now make sure the date is after 1800.
			Date dateMin = getDateFormatter().parse("1/1/1800");
			if (datRV.before(dateMin) == true) {
				throw new Exception("Date must be after 1/1/1800");
			}
		}

		return mydate;
	}

	/**
	 * Parse and convert a MM/DD/YY date to YYYYMMDD
	 * 
	 * @params String dateToParse
	 * 
	 * @returns String date YYYYMMDD
	 * 
	 * @exception
	 */
	public static String convertToDateYYYYMMDD(String theString) throws Exception {
		String mydate = new String();
		java.util.Date datRV = null;
		if (theString != null && !theString.trim().equals("") && theString.compareTo("00") > 0) {
			try {
				datRV = getDateFormatter().parse(theString);
				SimpleDateFormat mmddyyyy = new SimpleDateFormat("yyyyMMdd");
				mmddyyyy.setLenient(false);
				mydate = mmddyyyy.format(datRV);
			} catch (Exception e) {
				Exception niceException = new Exception("Please enter a valid date like mm/dd/yyyy");
				throw niceException;
			}
			// Now make sure the date is after 1800.
			java.util.Date dateMin = getDateFormatter().parse("1/1/1800");
			if (datRV.before(dateMin) == true) {
				throw new Exception("Date must be after 1/1/1800");
			}
		}

		return mydate;
	}

	/**
	 * Take a string in MMDDYYYY format and return Date
	 * @return Date
	 * @param inDate
	 */
	public static Date convertMMDDYYYYToDateYYMMDD(String inDate) {
		return FormatDate.convertToDateYYMMDD(convertUnformattedMDYtoYMD(inDate));
	}

	public static Date convertMMDDYYYYToDateYYYY_MM_DD(String inDate) {
		return FormatDate.convertToDateYYYY_MM_DD(convertUnformattedMDYtoYMD(inDate));
	}

	public static int getMonthFromMMDDYYYY(String inDate) {
		return Integer.parseInt(inDate.substring(0, 2));
	}
	
	public static int getDayFromMMDDYYYY(String inDate) {
		return Integer.parseInt(inDate.substring(2, 4));
	}
	
	public static int getYearFromMMDDYYYY(String inDate) {
		return Integer.parseInt(inDate.substring(4, 8));
	}

	/**
	 * Take a string in MMDDYYYY format and return YYYYMMDD string. Input date
	 * must be 8 characters in length. Creation date: (12/2/02 4:50:47 PM)
	 * 
	 * @return java.lang.String
	 * @param inDate
	 *            java.lang.String
	 */
	public static String convertUnformattedMDYtoYMD(String inDate) {
		StringBuffer workDate = new StringBuffer(10);

		// date parameter should be 8 chars long
		if (inDate == null || inDate.length() != 8) {
			return inDate;
		}
		// extract year
		workDate.append(inDate.charAt(4));
		workDate.append(inDate.charAt(5));
		workDate.append(inDate.charAt(6));
		workDate.append(inDate.charAt(7));
		// extract month
		workDate.append(inDate.charAt(0));
		workDate.append(inDate.charAt(1));
		// extract day
		workDate.append(inDate.charAt(2));
		workDate.append(inDate.charAt(3));

		return workDate.toString();
	}

	/**
	 * Get the current date and return it formatted for display as mm/dd/yyyy.
	 * Creation date: (1/19/2002 7:31:16 PM)
	 * 
	 * @return java.lang.String Current Date
	 */
	public static String getCurrentDateFormatedMMDDYYYY() {
		String currentdate = null;
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("MM/dd/yyyy");
		currentdate = formatter.format(new Date());
		return currentdate;
	}

	/**
	 * This returns the SimpleDateFormat that is used by the format(Date) and
	 * convertToDate(String) methods.
	 */
	protected static SimpleDateFormat getDateFormatter() {
		if (c_sdfFormatter == null) {
			c_sdfFormatter = new SimpleDateFormat("MM/dd/yyyy");
			c_sdfFormatter.setLenient(false);
		}
		return c_sdfFormatter;
	}

	/**
	 * Take a string in MMDDYYYY format and return DateFormat.SHORT form
	 * Creation date: (1/28/00 4:50:47 PM)
	 * 
	 * @return java.lang.String
	 * @param inDate
	 *            java.lang.String
	 */
	public static String MDYtoMMDDYY(String inDate) {
		return MDYtoMMDDYY(inDate, java.text.DateFormat.SHORT);
	}

	/**
	 * Take a string in MMDDYYYY format and return a DateFormat specified by the
	 * "style" parameter Creation date: (1/28/00 4:50:47 PM)
	 * 
	 * @return java.lang.String
	 * @param inDate
	 *            java.lang.String
	 * @param dateStyle
	 *            int
	 */
	public static String MDYtoMMDDYY(String inDate, int dateStyle) {
		String outDate;
		StringBuffer workDate = new StringBuffer(10);
		int month, day, year;
		// date parameter should be 8 chars long
		if (inDate == null || inDate.trim().length() != 8) {
			return "";
		}
		// truncate to length of 8 characters and compare to zeros
		if ((inDate.substring(0, 4)).compareTo("0000") <= 0)
			return "";
		try {
			// extract month
			workDate.append(inDate.charAt(0));
			workDate.append(inDate.charAt(1));
			month = Integer.parseInt(workDate.toString()) - 1; // Calendar
																// class months
																// range 0-11
			// extract day
			workDate.setCharAt(0, inDate.charAt(2));
			workDate.setCharAt(1, inDate.charAt(3));
			day = Integer.parseInt(workDate.toString());
			// extract year
			workDate.setCharAt(0, inDate.charAt(4));
			workDate.setCharAt(1, inDate.charAt(5));
			workDate.append(inDate.charAt(6));
			workDate.append(inDate.charAt(7));
			year = Integer.parseInt(workDate.toString());

			Calendar cal = new GregorianCalendar();
			cal.clear();
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.YEAR, year);

			java.util.Date date = cal.getTime();
			outDate = java.text.DateFormat.getDateInstance(dateStyle, java.util.Locale.US).format(date);
			// if (outDate.length() == 7)
			// outDate = "0"+outDate;
		} catch (java.lang.NumberFormatException e) {
			logger.error("Caught NumberFormatException in FormatDate.MDYtoMMDDYY : " + e);
			logger.error("Invalid date : " + inDate);

			outDate = "";
		}
		return outDate;
	}

	/**
	 * This returns a String formatted as MM/DD/YYYY. The passed in string must
	 * be 8 characters long If the passed string is not 8 charactes long, it is
	 * returned instead of the formatted string.
	 */
	public static String MDYtoMMDDYYYY(String inDate) {
		StringBuffer outDate = new StringBuffer(10);
		if (inDate == null)
			return "";
		if (inDate.length() < 8)
			return inDate;
		if ((inDate.substring(0, 4)).compareTo("0000") <= 0)
			return "";
		outDate.append(inDate.charAt(0));
		outDate.append(inDate.charAt(1));
		outDate.append("/");
		outDate.append(inDate.charAt(2));
		outDate.append(inDate.charAt(3));
		outDate.append("/");
		outDate.append(inDate.charAt(4));
		outDate.append(inDate.charAt(5));
		outDate.append(inDate.charAt(6));
		outDate.append(inDate.charAt(7));

		return outDate.toString();
	}

	/**
	 * This returns a String formatted as YYYY-MM-DD. The passed in string must
	 * be 8 characters long If the passed string is not 8 charactes long, it is
	 * returned instead of the formatted string.
	 */
	public static String MDYtoYYYY_MM_DD(String inDate) {
		StringBuffer outDate = new StringBuffer(10);
		if (inDate == null)
			return "";
		if (inDate.length() < 8)
			return inDate;
		if ((inDate.substring(0, 4)).compareTo("0000") <= 0)
			return "";
		outDate.append(inDate.charAt(4));
		outDate.append(inDate.charAt(5));
		outDate.append(inDate.charAt(6));
		outDate.append(inDate.charAt(7));
		outDate.append("-");
		outDate.append(inDate.charAt(0));
		outDate.append(inDate.charAt(1));
		outDate.append("-");
		outDate.append(inDate.charAt(2));
		outDate.append(inDate.charAt(3));
		
		return outDate.toString();
	}
	
	/**
	 * Take a string in YYYYMMDD format and return DateFormat.SHORT form
	 * Creation date: (1/28/00 4:50:47 PM)
	 * 
	 * @return java.lang.String
	 * @param inDate
	 *            java.lang.String
	 */
	public static String YMDtoMMDDYY(String inDate) {
		String outDate;
		StringBuffer workDate = new StringBuffer(10);
		int month, day, year;

		// date parameter should be 8 chars long
		if (inDate.length() != 8) {
			return " ";
		}
		// truncate to length of 8 characters and compare to zeros
		if ((inDate.substring(0, 8)).compareTo("00000000") <= 0)
			return " ";

		// extract month
		workDate.append(inDate.charAt(4));
		workDate.append(inDate.charAt(5));
		month = Integer.parseInt(workDate.toString()) - 1; // Calendar class
															// months range 0-11
		// extract day
		workDate.setCharAt(0, inDate.charAt(6));
		workDate.setCharAt(1, inDate.charAt(7));
		day = Integer.parseInt(workDate.toString());
		// extract year
		workDate.setCharAt(0, inDate.charAt(0));
		workDate.setCharAt(1, inDate.charAt(1));
		workDate.append(inDate.charAt(2));
		workDate.append(inDate.charAt(3));
		year = Integer.parseInt(workDate.toString());

		Calendar cal = new GregorianCalendar();
		cal.clear();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);

		java.util.Date date = cal.getTime();
		outDate = java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT, java.util.Locale.US).format(date);

		return outDate;
	}

	/**
	 * This returns a String formatted as YYYYMMDD. The passed in string must be
	 * 8 characters long If the passed string is not 8 charactes long, it is
	 * returned instead of the formatted string.
	 */
	public static String YMDtoMMDDYYYY(String inDate) {
		StringBuffer outDate = new StringBuffer(10);
		if (inDate == null)
			return "";
		if (inDate.length() < 8)
			return inDate;
		if ((inDate.substring(0, 4)).compareTo("0000") <= 0)
			return "";
		outDate.append(inDate.charAt(4));
		outDate.append(inDate.charAt(5));
		outDate.append("/");
		outDate.append(inDate.charAt(6));
		outDate.append(inDate.charAt(7));
		outDate.append("/");
		outDate.append(inDate.charAt(0));
		outDate.append(inDate.charAt(1));
		outDate.append(inDate.charAt(2));
		outDate.append(inDate.charAt(3));

		return outDate.toString();
	}

	public static java.sql.Date parseDate(Object obj) {
		if (obj == null) {
			return (null);
		}

		if (obj instanceof String) {
			return (null);
		} else if (obj instanceof java.sql.Date) {
			return ((java.sql.Date) obj);
		} else if (obj instanceof java.util.Date) {
			return ((java.sql.Date) obj);
		}

		return (null);
	}
	
	public static Timestamp parseTimestamp(Object value) {
		
		if ( value ==  null ) {
			return null;
		}
		
		String strVal = value.toString();

		if (strVal != null && strVal.equals("")) {
			return (null);
		} else if (value instanceof java.sql.Timestamp) {
			return ((Timestamp) value);
		}
		
		return ( null );
	}
	
	public static String getDayOfWeek(String dateStr)throws Exception{
		java.util.Date datRV = null;
		String dayOfWeek = "";
		if (dateStr != null && !dateStr.trim().equals("") && dateStr.compareTo("00") > 0) {
			try {
				datRV = getDateFormatter().parse(dateStr);
				
			} catch (Exception e) {
				logger.debug("Error : " + e);

				Exception niceException = new Exception("Please enter a valid date like mm/dd/yyyy");
				throw niceException;
			}
			// Now make sure the date is after 1800.
			java.util.Date dateMin = getDateFormatter().parse("1/1/1800");
			if (datRV.before(dateMin) == true) {
				throw new Exception("Date must be after 1/1/1800");
			}
		}
		int weekday = -1;
		if(datRV != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(datRV);
			weekday = calendar.get(Calendar.DAY_OF_WEEK);
		}
		
		switch(weekday){
            case 1:  dayOfWeek = "Sunday"; break;
            case 2:  dayOfWeek = "Monday"; break;
            case 3:  dayOfWeek = "Tuesday"; break;
            case 4:  dayOfWeek = "Wednesday"; break;
            case 5:  dayOfWeek = "Thursday"; break;
            case 6:  dayOfWeek = "Friday"; break;
            case 7:  dayOfWeek = "Saturday"; break;
            default: dayOfWeek = "";break;
        }
		return dayOfWeek;
	}
	
	public static java.sql.Date parseDateAsNull (String dateStr) {
		
		java.sql.Date date = null;
		
		try {
			date = parseDate(dateStr);
		} catch ( Exception e ) {
//				logger.debug(e.getMessage(), e);
		}
		
		return ( date );		
	}
	
	public static java.sql.Date parseDate (String dateStr) throws Exception {
		
		java.sql.Date date = null;
		
		if ( dateStr != null ) {
			date = FormatDate.convertToSQLDate(dateStr);
		}
		
		return ( date );		
	}
	
}