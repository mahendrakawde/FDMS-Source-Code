package com.aldorsolutions.webfdms.beans.comparators;

import java.util.Comparator;

import com.aldorsolutions.webfdms.beans.DbSpeedData;


public class SpeedDataComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		
		DbSpeedData spd1 = null;
		DbSpeedData spd2 = null;
		
		if ( o1 instanceof DbSpeedData ) {
			spd1 = (DbSpeedData) o1;
		}
		
		if ( o2 instanceof DbSpeedData ) {
			spd2 = (DbSpeedData) o2;
		}
		
		if ( spd1 == null && spd2 == null ) {
			return ( 0 );
		}
		else if ( spd1 == null && spd2 != null ) {
			return ( -1 );
		}
		else if ( spd1 != null && spd2 == null ) {
			return ( 1 );
		}
		else {
			int comp = spd1.getCategory().compareTo(spd2.getCategory());
			
			if ( comp != 0 ) {
				return ( comp );
			}
			else {
				
				if ( spd1.getSortSequence() > spd2.getSortSequence() ) {
					return ( 1 );
				}
				else if ( spd1.getSortSequence() < spd2.getSortSequence() ) {
					return ( -1 );
				}
				
				comp = spd1.getData().compareTo( spd2.getData() );
				if ( comp != 0 ) {
					return ( comp );
				}
				/*
				if ( spd1.getLocale() > spd2.getLocale() ) {
					return ( 1 );
				}
				else if ( spd1.getLocale() < spd2.getLocale() ) {
					return ( -1 );
				}
				
				if ( spd1.getLocationId() > spd2.getLocationId() ) {
					return ( 1 );
				}
				else if ( spd1.getLocationId() < spd2.getLocationId() ) {
					return ( -1 );
				}
				*/
				if ( spd1.getId() > spd2.getId() ) {
					return ( 1 );
				}
				else if ( spd1.getId() < spd2.getId() ) {
					return ( -1 );
				} 
			}
		}
		
		return 0;
	}

}
