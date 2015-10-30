package com.aldorsolutions.webfdms.util;

/**
 * Library for generic functions dealing with converting numbers Creation date:
 * (11/9/2001 1:58:18 PM)
 * 
 * @author:
 */

public class FormatNumber {

	/**
	 * FormatNumbers constructor comment.
	 */
	public FormatNumber() {
		super();
	}

	/**
	 * Attempt to cast an object to a DOUBLE or if that fails parse the object
	 * string as a double value. If unable to convert to a double this method
	 * logs an error but continues by returning zero instead of throwing an
	 * exception. Creation date: (2/3/2003 2:01:24 PM)
	 * 
	 * @return double
	 * @param param
	 *            Object
	 */
	public static double parseDouble(Object obj) {
		double value = 0;
		if (obj == null) {
			return 0;
		}

		try {
			value = ((java.lang.Double) obj).doubleValue();
			return value;
		} catch (ClassCastException e) {
		}
		try {
			value = Double.parseDouble(obj.toString());
		} catch (Exception e) {
			// logger.error("double Format Exception:", e);
			return 0;
		}
		return value;
	}

	/**
	 * Attempt to parse a string into an floating point number. If unable to
	 * convert to a float this method logs an error but continues by returning
	 * zero instead of throwing an exception. Creation date: (11/9/2001 2:01:24
	 * PM)
	 * 
	 * @return float
	 * @param param
	 *            java.lang.String
	 */
	public static float parseFloat(String theString) {
		float fRV = 0;
		if (theString != null && theString.compareTo(" ") > 0) {
			try {
				fRV = Float.parseFloat(theString);
			} catch (Exception e) {
				// logger.error("Float Format Exception:", e);
				return 0;
			}
		}
		return fRV;
	}

	/**
	 * Attempt to parse a string into an floating point number. If unable to
	 * convert to a float return an exception Creation date: (11/9/2001 2:01:24
	 * PM)
	 * 
	 * @return float
	 * @param param
	 *            java.lang.String
	 */
	public static float parseFloatWithException(String theString)
			throws Exception {
		float fRV = 0;
		if (theString != null) {
			fRV = Float.parseFloat(theString);
		}
		return fRV;
	}

	/**
	 * Attempt to parse a string into an integer. If unable to convert to
	 * integer this method logs an error but continues by returning zero instead
	 * of throwing an exception. Creation date: (11/9/2001 2:01:24 PM)
	 * 
	 * @return int
	 * @param param
	 *            java.lang.String
	 */
	public static int parseInteger(String theString) {
		int iRV = 0;
		if (theString != null) {
			if (theString == "true") {
				return 1;
			} else if (theString == "false") {
				return 0;
			}
			try {
				iRV = Integer.parseInt(theString);
			} catch (Exception e) {
				// fdms.log.AppLog.warning("Integer Format Exception:"+e);
				return 0;
			}
		}
		return iRV;
	}

	/**
	 * @param theString
	 * @return
	 */
	public static long parseLong(String theString) {
		long iRV = 0L;

		if (theString != null) {
			try {
				iRV = Long.parseLong(theString);
			} catch (NumberFormatException e) {
				// unable to parse long from String
			}
		}

		return iRV;
	}

	/**
	 * Attempt to parse a string into an short. If unable to convert to short
	 * this method logs an error but continues by returning zero instead of
	 * throwing an exception. Creation date: (11/9/2001 2:01:24 PM)
	 * 
	 * @return short
	 * @param param
	 *            java.lang.String
	 */
	public static short parseShort(String theString) {
		short iRV = 0;
		if (theString != null) {
			try {
				iRV = Short.parseShort(theString);
			} catch (Exception e) {
				// fdms.log.AppLog.warning("parseShort Format Exception:"+e);
				return 0;
			}
		}
		return iRV;
	}

	/**
	 * Take a floating point number and convert to an integer*100 as used in
	 * FDMS. Creation date: (3/3/2003 10:31:31 AM)
	 * 
	 * @return int
	 * @param floatnumber
	 *            double
	 */
	public static int round(double floatnumber) {
		if (floatnumber > 0) {
			return (int) ((floatnumber * 100) + 0.5);
		} else {
			return (int) ((floatnumber * 100) - 0.5);
		}
	}

	/**
	 * Convert boolean to 1 or zero if true or false respectively Creation date:
	 * (9/24/2002 2:06:15 PM)
	 * 
	 * @return int
	 * @param btest
	 *            boolean
	 */
	public static int translateBool(boolean btest) {
		if (btest)
			return 1;
		return 0;
	}
	
	public static final double roundDoubleDollars(double d) {
        return (roundDouble(d, 2));
    }
	
	public static final double roundDouble(double d, int places) {
        return Math.round(d * Math.pow(10, (double) places)) / Math.pow(10,
            (double) places);
    }

}
