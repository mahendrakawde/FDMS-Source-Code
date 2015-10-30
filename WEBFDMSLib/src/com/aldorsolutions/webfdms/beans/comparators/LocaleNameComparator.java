package com.aldorsolutions.webfdms.beans.comparators;

import java.util.Comparator;

import com.aldorassist.webfdms.model.LocaleDTO;


public class LocaleNameComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		
		LocaleDTO locale1 = null;
		LocaleDTO locale2 = null;
		
		if ( o1 instanceof LocaleDTO ) {
			locale1 = (LocaleDTO) o1;
		}
		
		if ( o2 instanceof LocaleDTO ) {
			locale2 = (LocaleDTO) o2;
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
			int comp = locale1.getName().compareTo(locale2.getName());
			
			if ( comp != 0 ) {
				return ( comp );
			}
			else {
				if ( locale1.getCompanyID() > locale2.getCompanyID() ) {
					return ( 1 );
				}
				else if ( locale1.getCompanyID() < locale2.getCompanyID() ) {
					return ( -1 );
				}
				
				if ( locale1.getLocaleID() > locale2.getLocaleID() ) {
					return ( 1 );
				}
				else if ( locale1.getLocaleID() < locale2.getLocaleID() ) {
					return ( -1 );
				} 
				
			}
		}
		
		return 0;
	}

}
