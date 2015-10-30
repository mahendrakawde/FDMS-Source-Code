package com.aldorsolutions.webfdms.speeddata.comparator;

import java.util.Comparator;


public class SpeedDataRuleNameComparator implements Comparator {

	public int compare ( Object o1, Object o2 ) {
		
		String s1 = null;
		String s2 = null;
		
		if ( o1 instanceof String )
		{
			s1 = (String) o1;
		}
		
		if ( o2 instanceof String )
		{
			s2 = (String) o2;
		}
		
		if ( s1 == null && s2 == null ) {
			return ( 0 );
		}
		else if ( s1 != null && s2 == null ) {
			return ( 1 );
		}
		else if ( s1 == null && s2 != null ) {
			return ( -1 );
		}
		else {
			return ( s1.compareToIgnoreCase(s2) );
		}
	}

}
