package com.aldorsolutions.webfdms.security.util;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * @author drollins
 *
 */
public class RegExValidator {

	final private static Logger logger = Logger.getLogger(RegExValidator.class.getName());
    
	private String regEx;

	private Pattern pattern;

	private Matcher matcher;

	private boolean matchFound;

	/**
	 * @param regEx
	 */
	public RegExValidator(String regEx) {
		this.regEx = regEx;
		pattern = Pattern.compile(this.regEx);
	}

	/**
	 * @param input
	 * @return
	 */
	public boolean matchInput(String input) {
		matcher = pattern.matcher(input);
				
		boolean found = false;
		
		while (matcher.find()) {
			logger.debug("Match found: index: (" + 
					matcher.start() + ", " + matcher.end() + ")");
			
			// \"" + matcher.group() + "\" # would display item matched
			
			found = true;
		}
		
		matchFound = found;
		
		return (matchFound);
	}

	/**
	 * @param input
	 * @return
	 */
	public Vector getMatches(String input) {
		matchInput(input);
		
		return (getMatches());
	}

	/**
	 * @return
	 */
	public Vector getMatches() {
		Vector vector = new Vector();
		
		matcher = matcher.reset();
		
		while (matcher.find()) {
			logger.debug("Match Found: index: (" + matcher.start() + ", " + 
					matcher.end() + ")");

			// \"" + matcher.group() + "\" # would display item matched
			vector.add(matcher.group());
		}
		
		if ( !matchFound ) {
			logger.debug("No match found)");
		}

		return (vector);
	}

	/**
	 * @return Returns the matchFound.
	 */
	public boolean isMatchFound() {
		return matchFound;
	}

	/**
	 * @return Returns the matcher.
	 */
	public Matcher getMatcher() {
		return matcher;
	}

	/**
	 * @return Returns the pattern.
	 */
	public Pattern getPattern() {
		return pattern;
	}

	/**
	 * @return Returns the regEx.
	 */
	public String getRegEx() {
		return regEx;
	}

	/**
	 * @param regEx The regEx to set.
	 */
	public void setRegEx(String regEx) {
		this.regEx = regEx;
		pattern = Pattern.compile(this.regEx);
	}

}