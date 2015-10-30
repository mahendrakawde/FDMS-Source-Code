package com.aldorsolutions.webfdms.security.util;

import java.security.SecureRandom;

public class PasswordGenerator {
	
	private static char [] alphaLower = {'a','b','c','d','e','f','g','h','i',
										 'j','k','l','m','n','o','p','q','r',
										 's','t','u','v','w','x','y','z'};
	
	private static char [] alphaUpper = {'A','B','C','D','E','F','G','H','I',
										 'J','K','L','M','N','O','P','Q','R',
										 'S','T','U','V','W','X','Y','Z'};
	
	private static char [] numeral = {'0','1','2','3','4','5','6','7','8','9'};
	
	private static char [] symbol = {'!','@','#','$','%','^','&','*','(',
									 ')','{','}',':',';','?'};
	
	public static String generatePassword ( int minLength ) {
		
		if ( minLength == 0 ) {
			minLength = 6;
		}
		
		SecureRandom rand = new SecureRandom ();
		char [] passChars = new char [minLength];
		int i = 0;
		
		while ( i < minLength ) {
			
			if ( i < minLength ) {
				passChars[i] = getPassChar (rand, alphaLower, alphaLower.length); 
				i++;
			}

			// Want last character to be an alpha character
			if ( i < (minLength - 1) ) {
				passChars[i] = getPassChar (rand, symbol, symbol.length); 
				i++;
			}
			
			if ( i < minLength ) {
				passChars[i] = getPassChar (rand, alphaUpper, alphaUpper.length); 
				i++;
			}
			
			// Want last character to be an alpha character
			if ( i < (minLength - 1) ) {
				passChars[i] = getPassChar (rand, numeral, numeral.length); 
				i++;
			}
			
		}
		
		return ( new String (passChars) );
	}
	
	private static char getPassChar ( SecureRandom rand, char [] alpha, int size ) {
		return ( alpha[rand.nextInt(size)]);
	}
	
}