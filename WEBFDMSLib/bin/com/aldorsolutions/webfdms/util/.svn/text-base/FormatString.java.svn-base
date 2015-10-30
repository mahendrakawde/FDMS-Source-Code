package com.aldorsolutions.webfdms.util;

/**
 * Library for string manipulation routines
 * Creation date: (11/20/2001 5:46:49 AM)
 * @author: 
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.common.Constants;

public class FormatString {

	static Logger logger = Logger.getLogger(FormatString.class.getName());

	/**
	 * FormatString constructor comment.
	 */

	public FormatString() {
		super();
	}

	/**
	 * Conversion method to make a single char into a String object. Creation
	 * date: (1/29/2002 9:12:14 PM)
	 * 
	 * @return java.lang.String
	 * @param inchar
	 *            char
	 */
	public static String charToString(char inchar) {
		char[] mychararray = new char[1];

		if (Character.isLetter(inchar))
			mychararray[0] = inchar;
		else
			mychararray[0] = ' ';

		return new String(mychararray);
	}

	/**
	 * Add backslash in front of any quote marks contained in input string.
	 * Creation date: (4/2/2003 10:03:27 AM)
	 * 
	 * @return java.lang.String
	 * @param instr
	 *            java.lang.String
	 */
	public static String escapeQuotes(String instr) {
		Pattern pattern = Pattern.compile("\'");
		Matcher matcher = pattern.matcher(instr);
		String escapedStr = instr;

		if (matcher.find()) {
			escapedStr = matcher.replaceAll("\\\\'");
		}

		return escapedStr;
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static String escapeFileName(String fileName) {
		Pattern pattern = Pattern.compile("\\W");
		Matcher matcher = pattern.matcher(fileName);
		String escapedFileName = fileName;

		if (matcher.find())
			escapedFileName = matcher.replaceAll("_");

		return escapedFileName;
	}

	/**
	 * Add dashes to a string in appropriate spot for displaying a Social
	 * Security Number. Creation date: (10/14/2002 5:48:14 AM)
	 * 
	 * @return java.lang.String
	 * @param str
	 *            java.lang.String - String of length 9 or 10
	 */

	public static String escapeSingleQuotes(String str) {
		return replace("'", "\\'", str);
	}

	public static String formatSocialSecurityNo(String locale, String str) {
		
		if (str.length() < 9) {
			return str;
		}
		
		StringBuffer ssno = new StringBuffer(12);
		
		if ( locale == null ) {
			locale = Constants.LOCALE_US;
		}
		
		if (locale.equals(Constants.LOCALE_US)) {
			ssno.append(str.substring(0, 3));
			ssno.append("-");
			ssno.append(str.substring(3, 5));
			ssno.append("-");
			ssno.append(str.substring(5));
		} else {
			ssno.append(str.substring(0, 3));
			ssno.append(" ");
			ssno.append(str.substring(3, 6));
			ssno.append(" ");
			ssno.append(str.substring(6));
		}
		
		return ssno.toString();
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2001 5:48:14
	 * AM)
	 * 
	 * @return byte
	 * @param str
	 *            java.lang.String
	 */
	public static char getFirstChar(String str) {
		char firstchar = ' ';
		if (str.length() > 0) {
			firstchar = str.charAt(0);
		}
		return firstchar;
	}

	/**
	 * Pad a string to a specified maximum length with a space character.
	 * Creation date: (1/24/2002 4:01:49 PM)
	 * 
	 * @return java.lang.String
	 * @param stringToPad
	 *            java.lang.String
	 * @param maxlength
	 *            int
	 */
	public static String pad(String stringToPad, int maxlength) {
		String padchar = " ";
		StringBuffer mybuff = new StringBuffer(stringToPad);
		for (int i = mybuff.length(); mybuff.length() < maxlength; i++) {
			mybuff.append(padchar);
		}
		return mybuff.toString();
	}

	/**
	 * Pad a string to a specified maximum length with HTML entity "&nbsp;".
	 * Creation date: (2/4/2003 4:01:49 PM)
	 * 
	 * @return java.lang.String
	 * @param stringToPad
	 *            java.lang.String
	 * @param maxlength
	 *            int
	 */
	public static String pad(String stringToPad, int maxlength, boolean rightJustify) {
		String padchar = "&nbsp;";
		int neededspace = maxlength - stringToPad.length();
		StringBuffer padspace = new StringBuffer();

		for (int i = 0; i < neededspace; i++) {
			padspace.append(padchar);
		}

		if (rightJustify)
			return (padspace.toString() + stringToPad);

		return stringToPad + padspace.toString();
	}

	/**
	 * Remove dashes from a Social Security Number string. Creation date:
	 * (10/14/2002 5:48:14 AM)
	 * 
	 * @return java.lang.String
	 * @param str
	 *            java.lang.String - String of length 11
	 */
	public static String removeDashes(String str) {

		Pattern pattern = Pattern.compile("\\D");
		Matcher matcher = pattern.matcher(str);

		if (matcher.find()) {
			str = matcher.replaceAll("");
		}

		return str;
	}

	/**
	 * Replace the first occurrence of subString with the parameter "withString"
	 * in the paramter sourceString. Creation date: (2/10/2003 10:38:22 AM)
	 * 
	 * @return java.lang.String
	 * @param subString
	 *            java.lang.String
	 * @param withString
	 *            java.lang.String
	 * @param sourceString
	 *            java.lang.String
	 */
	public static String replace(String subString, String withString, String sourceString) {
		StringBuffer resultString = new StringBuffer();

		if (sourceString == null)
			return null;
		if (subString == null)
			return null;
		if (withString == null)
			return null;

		int startlocation = sourceString.indexOf(subString);
		if (startlocation < 0 || startlocation >= sourceString.length())
			return sourceString;

		resultString.append(sourceString.substring(0, startlocation));
		resultString.append(withString);
		resultString.append(sourceString.substring(startlocation + subString.length()));

		return resultString.toString();
	}

	public static String formatPhone(String string) {

		// logger.debug("** Formatting phone # : " + string + " **");

		if (string != null && string.trim().length() > 0) {

			Pattern pattern = Pattern.compile("\\D");
			Matcher matcher = pattern.matcher(string);

			if (matcher.find())
				string = matcher.replaceAll("");

			StringBuffer sb = new StringBuffer();

			if (string.length() > 10) {
				if (string.substring(0, 1).equals("1")) {
					sb.append("(" + string.substring(1, 4) + ") ");
					sb.append(string.substring(4, 7) + "-");
					sb.append(string.substring(7));
				} else {
					sb.append("(" + string.substring(0, 3) + ") ");
					sb.append(string.substring(3, 6) + "-");
					sb.append(string.substring(6));
				}
			} else if (string.length() == 10) {
				sb.append("(" + string.substring(0, 3) + ") ");
				sb.append(string.substring(3, 6) + "-");
				sb.append(string.substring(6));

			} else if (string.length() > 3) {
				sb.append("(   ) ");
				sb.append(string.substring(0, 3) + "-");
				sb.append(string.substring(3));

			}

			string = sb.toString();
		}

		// logger.debug("** Formatted phone # : " + string + " **");

		return string;
	}

	public static String formatZip(String locale, String zip) {

		if ( locale == null ) {
			locale = Constants.LOCALE_US;
		}
		
		if (zip != null && zip.length() > 0 && locale.equals(Constants.LOCALE_CANADA)) {

			try {
				Pattern pattern = Pattern.compile("^a-z^A-Z^0-9");
				Matcher matcher = pattern.matcher(zip);

				if (matcher.find()) {
					zip = matcher.replaceAll("");
				}

			} catch (Exception e) {
				logger.error("Exception in formatZip() : " + e.getMessage());
			}

			if (zip.length() > 3) {
				zip = (zip.substring(0, 3) + " " + zip.substring(3)).toUpperCase();
			}

		}

		return zip;
	}

	public static String booleanToYN(boolean bool) {
		if (bool)
			return "Y";
		else
			return "N";
	}

	public static boolean ynToBoolean(String yn) {
		boolean bool = false;

		if ((yn != null) && ("Y".equals(yn)))
			bool = true;

		return bool;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String blankNull(String str) {

		if ((str == null) || (str.length() == 0))
			str = "";
		str = str.trim();

		return str;
	}

	public static String nullNull(String str) {
		if ((str == null) || ("".equals(str.trim())))
			str = "null";
		return str;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String blankNullToken(String str) {

		if ((str == null) || (str.length() == 0))
			str = " ";

		return str;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeXML(String str) {
		logger.debug("EscapingXML String : " + str);
		String newString = "";

		if (str != null) {
			newString = str.trim();

			Pattern pattern = Pattern.compile("&");
			Matcher matcher = pattern.matcher(str);
			if (matcher.find())
				newString = matcher.replaceAll("&amp;");

			pattern = Pattern.compile("<");
			matcher = pattern.matcher(newString);
			if (matcher.find())
				newString = matcher.replaceAll("&lt;");

			pattern = Pattern.compile(">");
			matcher = pattern.matcher(newString);
			if (matcher.find())
				newString = matcher.replaceAll("&gt;");
		}

		return newString;
	}
	
	public static String formatHTMLString(Object s) {
		// Convert problem characters to JavaScript Escaped values
		if (s == null) {
			return "";
		}

		String t = (String) s;
		t = replaceString(t, "\\", "&#92;"); // replace backslash with \\
		t = replaceString(t, "'", "&#39;"); // replace an single quote with \'
		t = replaceString(t, "\"", "&#34;"); // replace a double quote with \"
		t = replaceString(t, "\n", "&#10;"); // replace LF with \n;

		return t;
	}
	
	public static String formatJavaScriptString(Object s) {
		// Convert problem characters to JavaScript Escaped values
		if (s == null) {
			return "";
		}

		String t = (String) s;
		t = replaceString(t, "\\", "\\\\"); // replace backslash with \\
		t = replaceString(t, "'", "\\\'"); // replace an single quote with \'
		t = replaceString(t, "\"", "\\\""); // replace a double quote with \"
		t = replaceString(t, "\r", "\\r"); // replace CR with \r;
		t = replaceString(t, "\n", "\\n"); // replace LF with \n;

		return t;
	}

	public static String replaceString(String s, String one, String another) {
		//	 In a string replace one substring with another
		if (s.equals(""))
			return "";
		String res = "";
		int i = s.indexOf(one, 0);
		int lastpos = 0;
		while (i != -1) {
			res += s.substring(lastpos, i) + another;
			lastpos = i + one.length();
			i = s.indexOf(one, lastpos);
		}
		res += s.substring(lastpos); // the rest
		return res;
	}

}