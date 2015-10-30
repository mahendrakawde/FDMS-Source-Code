package com.aldorsolutions.webfdms.util;

/**
 * This class is a repository for various editing functions to
 * validate data.
 * Creation date: (4/22/2002 4:53:00 PM)
 * @author: 
 */
public class EditChecks {
/**
 * EditChecks constructor comment.
 */
public EditChecks() {
	super();
}
/**
 * remove dashes from a U.S. style social security number and validate.
 * Creation date: (4/22/2002 4:54:32 PM)
 * @return java.lang.String
 */
public final static String editSocSecNo(String ssn) throws Exception {
	if (ssn==null)
		throw new Exception("Empty social security number.");
	StringBuffer outssn = new StringBuffer();
	for (int i=0; i<ssn.length(); i++){
		if (ssn.charAt(i)!= '-'){
			if (ssn.charAt(i)<'0' || ssn.charAt(i)>'9'){
				throw new Exception("Invalid character in social security number");
			}
			outssn.append(ssn.charAt(i));
		}
	}	
	return outssn.toString();
}
}
