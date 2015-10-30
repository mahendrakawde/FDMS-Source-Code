package com.aldorsolutions.webfdms.util;

/**
 * Insert the type's description here.
 * Creation date: (3/14/2002 11:32:22 AM)
 * @author: 
 */
public class Translator {
/**
 * Translator constructor comment.
 */
public Translator() {
	super();
}
/**
 * Return String "city, state" with the "comma space" if state has a value
 * Creation date: (3/15/02 12:13:14 PM)
 * @return java.lang.String
 * @param city String
 * @param state String
 */
public static String appendCityState(String city, String state) {
	StringBuffer cs = new StringBuffer();
	cs.append(city);
	if (state != null && state.length()>0){
		cs.append(", ");
		cs.append(state);
	}
	return cs.toString();
}

// take the city, state and country if they exist and combine them in to one csv string
public static String appendCityStateCountry(String city, String state, String country) {
    String birthPlace = "";
    
	// make sure null values and white space are set to an empty string
    if (city == null) city = ""; else city.trim();
    if (state == null) state = ""; else state.trim();
    if (country == null) country = ""; else country.trim();
    
    if (city.compareTo("")!=0)
    	birthPlace += city + ",";
    else
    	birthPlace += ",";
    if (state.compareTo("")!=0)
    	birthPlace += state + ",";
    else
    	birthPlace += ",";
    if (country.compareTo("")!=0)
    	birthPlace += country;
    
    return birthPlace;    
}

//take the city, state and country if they exist and combine them in to one field for displaying in forms
public static String createDisplayCityStateCountry(String city, String state, String country) {
	String birthPlace = "";
	
	// make sure null values and white space are set to an empty string
    if (city == null) city = ""; //else city.trim();
    if (state == null) state = ""; //else state.trim();
    if (country == null) country = ""; //else country.trim();

	if (city.compareTo("")!=0)
		birthPlace += city;
	if (state.compareTo("")!=0 && city.compareTo("")!=0)
		birthPlace += ", " + state;
	else if (state.compareTo("")!=0)
		birthPlace += state;
	if (country.compareTo("")!=0 && (city.compareTo("")!=0 || state.compareTo("")!=0))
		birthPlace += ", " + country;
	else if (country.compareTo("")!=0)
		birthPlace += country;		
	
	return birthPlace;
}


/**
 * Return String "city, state zip" with the "comma space" if state has a value.
 * Creation date: (3/15/02 12:13:14 PM)
 * @return java.lang.String
 * @param city String
 * @param state String
 * @param zip  String
 */
public static String appendCityStateZip(String city, String state, String zip) {
	StringBuffer csz = new StringBuffer();
	csz.append( appendCityState(city,state));
	if (zip != null && zip.length()>0){
		csz.append(" ");
		csz.append(zip);
	}
	return csz.toString();
}
/**
 * Return String "Male" or "Female" from character parameter
 * Creation date: (12/28/00 12:13:14 PM)
 * @return java.lang.String
 * @param yn char M=Male F=Female
 */
public static String formatMaleFemale(char yn) {
	if (yn == 'M' || yn == 'm')
		return "Male";
	if (yn == 'F' || yn == 'f')
		return "Female";
		
	return "";
}
/**
 * Return String "yes" or "no" or "Unknown" from character parameter
 * Creation date: (12/28/00 12:13:14 PM)
 * @return java.lang.String
 * @param yn char Y=Yes
 */
public static String formatYesNo(char yn) {
	if (yn == 'Y' || yn == 'y')
		return "Yes";
	else if	(yn == 'N' || yn == 'n')
		return "No";
	else if	(yn == 'U' || yn == 'u')
		return "Unknown";
	else if	(yn == 'R' || yn == 'r')
		return "Refused";

	return "";
}
/**
 * Return String "yes" or "no" from character parameter
 * Creation date: (12/28/00 12:13:14 PM)
 * @return java.lang.String
 * @param yn char Y=Yes
 */
public static String formatYesNo(String yn) {
	if (yn == null)
		return "";
	return formatYesNo( FormatString.getFirstChar(yn) );
}
/**
 * Return String "yes" or "no" or "Unknown" from character parameter
 * Creation date: (12/28/00 12:13:14 PM)
 * @return java.lang.String
 * @param yn char Y=Yes
 */
public static String formatYesNo(boolean yn) {
	if (yn)
		return "Yes";

	return "No";
}
/**
 * Break a string "city, state zip" into separate city state zip fields.
 * Creation date: (3/20/2002 1:54:37 PM)
 * @param fullname java.lang.String
 * @param firstName java.lang.String
 * @param lastName java.lang.String
 */
public static void parseCityStateZip(String citystatezip, StringBuffer city, StringBuffer state, StringBuffer zip) {
	
	String csz = citystatezip.trim();
	int lastspace = csz.indexOf(",");
	if (lastspace < 1){
		city.append(citystatezip);
		return;
	}
	city.append(csz.substring(0,lastspace));
	String statezip = csz.substring(lastspace+1);
	statezip = statezip.trim();
	lastspace = statezip.lastIndexOf(" ");
	if (lastspace < 1){
		state.append(statezip);
		return;
	}
	state.append(statezip.substring(0,lastspace-1));
	zip.append(statezip.substring(lastspace+1));
	return;
}
/**
 * Break a name into first and last based on the last space in name.
 * Creation date: (3/20/2002 1:54:37 PM)
 * @param fullname java.lang.String
 * @param firstName java.lang.String
 * @param lastName java.lang.String
 */
public static void parseName(String fullname, StringBuffer firstName, StringBuffer lastName) {
	String name = fullname.trim();
	int lastspace = name.lastIndexOf(" ");
	if (lastspace < 1){
		firstName.append(fullname);
		return;
	}
	firstName.append( name.substring(0,lastspace-1));
	lastName.append( name.substring(lastspace+1));
}
}
