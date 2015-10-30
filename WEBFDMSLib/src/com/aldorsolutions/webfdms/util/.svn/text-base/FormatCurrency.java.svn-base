package com.aldorsolutions.webfdms.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Generic formatting of numbers to currency for display purposes Creation date:
 * (7/7/00 11:19:33 AM)
 * 
 * @author:
 */
public class FormatCurrency {
	/**
	 * FormatCurrency constructor comment.
	 */
	public FormatCurrency() {
		super();
	}

	/**
	 * Attempt to parse the string containing a dollar amount and convert to an
	 * integer the way FDMS stores currency. Creation date: (2/12/01 5:02:48 PM)
	 * 
	 * @return int
	 * @param currencyString
	 *            String
	 * @exception Exception
	 *                The exception description.
	 */
	public static int convertToCurrency(String currencyString)
			throws Exception {
		NumberFormat numform;
		DecimalFormat decform = null;
		boolean negative = false;
		if (currencyString == null)
			return 0;
		if (currencyString.length() < 1)
			return 0;
		// check for negative amount
		if (currencyString.charAt(0) == '-' || currencyString.charAt(0) == '(') {
			negative = true;
			currencyString = currencyString.substring(1);
		}

		// Check for '$' currency symbol. Must be present for parse() to work
		if (currencyString.indexOf('$') != 0)
			currencyString = '$' + currencyString;
		numform = NumberFormat.getCurrencyInstance(); // can specify locale
														// like: Locale.US
		decform = (DecimalFormat) numform;
		// Reformat amount to currency
		Number result = decform.parse(currencyString);
		int cents = (int) ((result.doubleValue() * 100) + .5);
		if (negative)
			cents = -cents;

		return cents;
	}

	public static double convertToCurrencyDbl(String currencyString)
			throws Exception {
		int amt = convertToCurrency(currencyString);

		return (double) amt / 100;
	}

	/**
	 * Insert the method's description here. Creation date: (7/7/00 11:22:30 AM)
	 * 
	 * @return String
	 * @param amount
	 *            double
	 */
	public static String toCurrency(double amount) {
		NumberFormat numform;
		DecimalFormat decform = null;
		numform = NumberFormat.getCurrencyInstance(); // can specify locale
														// like: Locale.US
		decform = (DecimalFormat) numform;
		// Reformat amount to currency
		return (decform.format(amount));
	}

	public static String toCurrencyFormatted(double amount) {
		DecimalFormat formatter = new DecimalFormat("0.00");

		return formatter.format(amount / 100.0d);
	}

	/**
	 * Insert the method's description here. Creation date: (7/7/00 11:22:30 AM)
	 * 
	 * @return String
	 * @param amount
	 *            double
	 */
	public static String toCurrency(long value) {

		return (toCurrency((double) value / 100.0));
	}

}
