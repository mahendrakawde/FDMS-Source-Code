package com.aldorsolutions.webfdms.beans.comparators;

import java.util.Comparator;

import com.aldorsolutions.webfdms.util.OptionsList;

public class LocaleLocationOptionsComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		
		OptionsList locale1 = null;
		OptionsList locale2 = null;
		
		if ( o1 instanceof OptionsList ) {
			locale1 = (OptionsList) o1;
		}
		
		if ( o2 instanceof OptionsList ) {
			locale2 = (OptionsList) o2;
		}
		
		if ( locale1 == null && locale2 == null ) {
			return ( 0 );
		}
		else if ( locale1 == null && locale2 != null ) {
			return ( -1 );
		}
		else if ( locale1 != null && locale2 == null ) {
			return ( 1 );
		}
		else {
			int comp = locale1.getListLabel().compareTo(locale2.getListLabel());
			
			if ( comp != 0 ) {
				return ( comp );
			}
			else {
				comp = locale1.getListValue().compareTo(locale2.getListValue());

				return ( comp );
			}
		}
		
//		return 0;
	}

}
