package com.aldorsolutions.webfdms.util;

import org.apache.log4j.Logger;

/**
 * Read a comma delimited text file and return in various formats Creation date:
 * (12/20/00 12:02:32 PM)
 * 
 * @author:
 */
public class CsvTable {

	private static Logger logger = Logger.getLogger(CsvTable.class.getName());

	private java.lang.String tableName;

	private java.util.ArrayList tableList = new java.util.ArrayList();

	/**
	 * CsvTable constructor comment.
	 */
	public CsvTable() {
		super();
	}

	/**
	 * Extract specified field from a csv string. Creation date: (12/20/00
	 * 3:07:03 PM)
	 * 
	 * @return java.lang.String
	 * @param String
	 *            aLine - comma seperated values string
	 * @param fieldnumber
	 *            int - field number to extract, starts at 1.
	 */
	public final static String getField(String aLine, int fieldnumber) {
		String resultField;
		// make sure aLine is not null
		if (aLine == null || aLine.length() < 1)
			return new String();
		// make sure fieldnumber > 0
		if (fieldnumber < 1)
			return aLine;
		// position to desired field
		int start = 0;
		int end = -1;
		int curfield = 0;
		while (curfield < fieldnumber) {
			start = end + 1;
			end = aLine.indexOf(',', start);
			curfield++;
			if (end < 0) {
				end = aLine.length();
				break;
			}
		}
		// check if we are positioned correctly
		if (curfield != fieldnumber)
			return new String();

		resultField = aLine.substring(start, end).trim();

		return resultField;
	}

	/**
	 * Return specified field from specified line in objects collection of table
	 * lines. Creation date: (2/7/01 11:36:22 AM)
	 * 
	 * @return java.lang.String
	 * @param line
	 *            int
	 * @param field
	 *            int
	 */
	public String getFieldOnLine(int line, int field) {
		if (line > tableList.size())
			return new String();

		return getField(tableList.get(line).toString(), field);
	}

	/**
	 * Insert the method's description here. Creation date: (2/7/01 11:18:39 AM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return tableName;
	}

	/**
	 * Replace special characters with HTML entities: " to &quot; & to &amp; <
	 * to &lt; > to &gt; Creation date: (12/22/00 11:31:26 AM)
	 * 
	 * @return java.lang.String
	 * @param text
	 *            java.lang.String
	 */
	public final static String replaceWithHtmlEntities(String text) {
		StringBuffer outText = new StringBuffer(text.length());
		for (int i = 0; i < text.length(); i++) {
			char achar = text.charAt(i);
			switch (achar) {
			case '"':
				outText.append("&quot;");
				break;
			case '&':
				outText.append("&amp;");
				break;
			case '<':
				outText.append("&lt;");
				break;
			case '>':
				outText.append("&gt;");
				break;
			default:
				outText.append(achar);
				break;
			}
		}
		return outText.toString();
	}

	/**
	 * Find specified word in objects table collection at field number specified
	 * Creation date: (2/7/01 12:00:31 PM)
	 * 
	 * @return int index of element with search word, -1 if not found
	 * @param searchWord
	 *            java.lang.String
	 * @param searchPosition
	 *            int
	 */
	public int search(String searchWord, int searchPosition) {

		// get iterator for table collection
		// java.util.Iterator tabIt = tableList.iterator();
		// Loop through table
		for (int index = 0; index < tableList.size(); index++) {
			String item = (String) tableList.get(index);
			String column = getField(item, searchPosition);
			if (searchWord.equalsIgnoreCase(column))
				return index;
		}
		// search target not found
		return -1;
	}
}
