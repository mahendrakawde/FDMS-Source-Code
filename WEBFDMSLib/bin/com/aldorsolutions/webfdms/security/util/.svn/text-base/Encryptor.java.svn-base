package com.aldorsolutions.webfdms.security.util;

import java.security.MessageDigest;

/**
 * @author drollins
 * 
 * Encrypt source and return the encrypted hex string equivalent
 *
 */
public class Encryptor {

	/**
	 * @param source
	 * @return
	 */
	public static String encrypt ( String source ) {
		String algorithm = "SHA";  // Also available MD5
		
		return ( encrypt ( algorithm, source ) );
	}
	
	/**
	 * @param algorithm  
	 * 	Typical algorithms are going to be SHA for MD5.  
	 * @param source
	 * @return
	 */
	public static String encrypt ( String algorithm, String source ) {
        try {
            MessageDigest md = MessageDigest.getInstance( algorithm );
            md.reset();
            
            byte[] bytes = md.digest( source.getBytes() );
            return getHexString( bytes );
        } catch( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }
	
    /**
     * @param bytes
     * @return
     */
    private static String getHexString( byte[] bytes ) {
        StringBuffer sb = new StringBuffer();
        for( int i=0; i<bytes.length; i++ ) {
            byte b = bytes[ i ];
            sb.append(Integer.toHexString(0xFF & b));
        }
        
        return sb.toString();
    }
    
    public static void main ( String args [] ) {
    	System.out.println ( "demo:     " + Encryptor.encrypt("demo") );
    	System.out.println ( "joe:      " + Encryptor.encrypt("joe") );
    	System.out.println ( "test:     " + Encryptor.encrypt("test") );
    	System.out.println ( "falcon:   " + Encryptor.encrypt("falcon") );
    	System.out.println ( "apricot:  " + Encryptor.encrypt("apricot") );
    	System.out.println ( "talcum:   " + Encryptor.encrypt("talcum") );
    	System.out.println ( "joey:     " + Encryptor.encrypt("joey") );
    	System.out.println ( "alpha:    " + Encryptor.encrypt("alpha") );
    	System.out.println ( "beta:     " + Encryptor.encrypt("beta") );
    	System.out.println ( "charlie:  " + Encryptor.encrypt("charlie") );
    	System.out.println ( "delta:    " + Encryptor.encrypt("delta") );
    	
    }
    
}
