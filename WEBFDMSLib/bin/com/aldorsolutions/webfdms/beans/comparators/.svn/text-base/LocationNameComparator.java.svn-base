package com.aldorsolutions.webfdms.beans.comparators;

import java.util.Comparator;

import com.aldorsolutions.webfdms.beans.DbLocation;


public class LocationNameComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		
		DbLocation location1 = null;
		DbLocation location2 = null;
		
		if ( o1 instanceof DbLocation ) {
			location1 = (DbLocation) o1;
		}
		
		if ( o2 instanceof DbLocation ) {
			location2 = (DbLocation) o2;
		}
		
		if ( location1 == null && location2 == null ) {
			return ( 0 );
		}
		else if ( location1 == null && location2 != null ) {
			return ( -1 );
		}
		else if ( location1 != null && location2 == null ) {
			return ( 1 );
		}
		else {
			int comp = location1.getName().compareTo(location2.getName());
			
			if ( comp != 0 ) {
				return ( comp );
			}
			else {
				comp = location1.getCompanyNumber().compareTo(location2.getCompanyNumber() );
				
				if ( comp != 0 ) {
					return ( comp );
				}
								
				if ( location1.getLocaleNumber() > location2.getLocaleNumber() ) {
					return ( 1 );
				}
				else if ( location1.getLocaleNumber() < location2.getLocaleNumber() ) {
					return ( -1 );
				} 
				
				if ( location1.getId() > location2.getId() ) {
					return ( 1 );
				}
				else if ( location1.getId() < location2.getId() ) {
					return ( -1 );
				} 
			}
		}
		
		return 0;
	}

}
