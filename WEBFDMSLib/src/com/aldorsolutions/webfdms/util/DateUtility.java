package com.aldorsolutions.webfdms.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class DateUtility {

	public static Timestamp getTimestamp(Object value) {
		
		if ( value ==  null ) {
			return null;
		}
		
		String strVal = value.toString();

		if (strVal != null && strVal.equals("")) {
			return (null);
		} else {
			return ((Timestamp) value);
		}

	}

	public static java.util.Date getCurrentDateUtil () {
		return ( new java.util.Date() );
	}
	
	public static java.sql.Date getCurrentDate () {
		return ( new Date ( Calendar.getInstance().getTimeInMillis() ) );
	}
	
	public static java.sql.Time getCurrentTime () {
		return ( new Time ( Calendar.getInstance().getTimeInMillis() ) );
	}
	
	public static Timestamp getCurrentTimestamp() {
		return ( new Timestamp ( Calendar.getInstance().getTimeInMillis() ) );
	}
	
}
