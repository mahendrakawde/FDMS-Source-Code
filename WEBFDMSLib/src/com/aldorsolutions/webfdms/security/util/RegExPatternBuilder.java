package com.aldorsolutions.webfdms.security.util;

import java.util.ArrayList;

import com.aldorsolutions.webfdms.beans.DbSecurityConfig;
import com.aldorsolutions.webfdms.security.model.SecurityConfigDTO;


/**
 * @author drollins
 *
 */
public class RegExPatternBuilder {

	/**
	 * @return
	 */
	public static String createAdjacentNumbersPattern () {
		return ("([0-9](?=[0-9]))");
	}
	
	/**
	 * @param settings
	 * @return
	 */
	public static String createComplexityPattern ( SecurityConfigDTO settings ) {
		boolean mixedCase = settings.isMixedCaseRequired();
		boolean numRqd  = settings.isNumberRequired();
		boolean symRqd  = settings.isSymbolRequired();
		int minLength  = settings.getMinPasswordLength();
		int maxLength = settings.getMaxPasswordLength();
				
		return ( createComplexityPattern ( mixedCase, numRqd, symRqd, minLength, maxLength ) );
	}

	/**
	 * @param settings
	 * @return
	 */
	public static String createComplexityPattern ( DbSecurityConfig settings ) {
		boolean mixedCase = settings.isMixedCaseRequired();
		boolean numRqd  = settings.isNumberRequired();
		boolean symRqd  = settings.isSymbolRequired();
		int minLength  = settings.getMinPasswordLength();
		int maxLength = settings.getMaxPasswordLength();
				
		return ( createComplexityPattern ( mixedCase, numRqd, symRqd, minLength, maxLength ) );
	}

	/**
	 * @param settings
	 * @return
	 */
	public static String createComplexityPattern ( boolean mixedCase, boolean numRqd, boolean symRqd, 
												   int minLength, int maxLength ) {
		StringBuffer pattern = new StringBuffer();
		
		pattern.append( "^" );
		pattern.append( createMixedCasePattern(mixedCase)[0] );
		pattern.append( createNumericPattern(numRqd)[0] );
		pattern.append( createSymbolsPattern(symRqd)[0] );
		pattern.append( createMinimumLengthPattern(minLength,maxLength)[0] );
		return ( pattern.toString() );
	}
	

	/**
	 * @param settings
	 * @return
	 */
	public static ArrayList createComplexityPatternDescription ( DbSecurityConfig settings ) {
		
		boolean mixedCase = settings.isMixedCaseRequired();
		boolean numRqd  = settings.isNumberRequired();
		boolean symRqd  = settings.isSymbolRequired();
		int minLength  = settings.getMinPasswordLength();
		int maxLength = settings.getMaxPasswordLength();
		boolean adjNum  = settings.isAdjacentNumberAllowed();
		boolean userName  = settings.isPasswordContainsUserNameAllowed();
				
		return ( createComplexityPatternDescription ( mixedCase, numRqd, symRqd, minLength, maxLength, adjNum, userName ) );
	}
	
	/**
	 * @param settings
	 * @return
	 */
	public static ArrayList createComplexityPatternDescription ( SecurityConfigDTO settings ) {
		
		boolean mixedCase = settings.isMixedCaseRequired();
		boolean numRqd  = settings.isNumberRequired();
		boolean symRqd  = settings.isSymbolRequired();
		int minLength  = settings.getMinPasswordLength();
		int maxLength = settings.getMaxPasswordLength();
		boolean adjNum  = settings.isAdjacentNumberAllowed();
		boolean userName  = settings.isPasswordContainsUserNameAllowed();
				
		return ( createComplexityPatternDescription ( mixedCase, numRqd, symRqd, minLength, maxLength, adjNum, userName ) );
	}
	
	
	/**
	 * @param settings
	 * @return
	 */
	public static ArrayList createComplexityPatternDescription ( boolean mixedCase, boolean numRqd, boolean symRqd, 
			   int minLength, int maxLength, boolean adjNum, boolean userName ) {
		ArrayList list = new ArrayList ();
		
		list.add(createMinimumLengthPattern(minLength,maxLength)[1]);
		list.add(createMixedCasePattern(mixedCase)[1]);
		
		String numericDesc = createNumericPattern(numRqd)[1];
		
		if ( numericDesc.length() > 0 )
		{
			list.add(numericDesc);
		}
		
		String symbolsDesc = createSymbolsPattern(symRqd)[1];

		if ( symbolsDesc.length() > 0 )
		{
			list.add(symbolsDesc);
		}
		
		if ( adjNum == false ) {
			list.add("Adjacent numbers are not allowed ");
		}
		
		if ( userName == false ) {
			list.add("Password cannot contain username ");
		}
		
		return ( list );
	}
	
	/**
	 * @param useMixedCase
	 * @return String Array 
	 * 		[0] - Pattern
	 * 		[1] - Description
	 */
	private static String [] createMixedCasePattern ( boolean useMixedCase )
	{
		String mixedCase = "(?=.*[a-z])(?=.*[A-Z])";
		String anyCase = "(?=.*[a-zA-Z])";
				
		String [] results = {anyCase,"Must contain at least one letter"};
		
		if ( useMixedCase ) {
			results [0] = mixedCase;
			results [1] = "Must contain at least one uppercase and one lowercase letter";
		}
		
		return ( results );
	}	

	/**
	 * @param useNumeric
	 * @return String Array 
	 * 		[0] - Pattern
	 * 		[1] - Description 
	 */
	private static String [] createNumericPattern ( boolean useNumeric )
	{
		String numericPattern = "(?=.*[0-9])";
				
		String [] results = {"",""};
		
		if ( useNumeric ) {
			results [0] = numericPattern;
			results [1] = "Must contain at least one number";
		}
		
		return ( results );
		
	}	
	
	/**
	 * @param useSymbols
	 * @return String Array 
	 * 		[0] - Pattern
	 * 		[1] - Description
	 */
	private static String [] createSymbolsPattern ( boolean useSymbols )
	{
		String symbolPattern = "(?=.*[-+?*$^.\\|`~!@#%&_ ={}:;  ',/])";
		String [] results = {"",""};
		
		if ( useSymbols ) {
			results [0] = symbolPattern;
			results [1] = "Must contain at least one symbol";
		}
		
		return ( results );
	}	
	
	/**
	 * @param minLength
	 * @param maxLength
	 * @return String Array 
	 * 		[0] - Pattern
	 * 		[1] - Description
	 */
	private static String [] createMinimumLengthPattern ( int minLength, int maxLength )
	{
		String minLengthPattern = ".{"+minLength+","+maxLength+"}$";
		
		String [] results = {minLengthPattern,"Must contain at least " + minLength + 
							 " characters, but not more than " + maxLength + " characters"};
		
		return ( results );
	}
	
}
