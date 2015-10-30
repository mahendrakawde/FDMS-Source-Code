package com.aldorsolutions.webfdms.util;

/**
 * Insert the type's description here.
 * Creation date: (1/12/2002 10:37:22 PM)
 * @author: 
 */
public class OptionsList {
	private java.lang.String listValue;
	private java.lang.String listLabel;
/**
 * OptionsList constructor comment.
 */
public OptionsList() {
	super();
}
/**
 * Constructor with "value" initializer.
 * Creation date: (1/15/2002 7:40:01 PM)
 * @param value java.lang.String
 */
public OptionsList(String value) {
	super();
	listValue = value;
}
/**
 * Constructor with "value" and "label" initializer.
 * Creation date: (1/15/2002 7:40:01 PM)
 * @param value java.lang.String
 */
public OptionsList(String value, String label) {
	super();
	listValue = value;
	listLabel = label;
}
/**
 * Insert the method's description here.
 * Creation date: (1/12/2002 10:38:26 PM)
 * @return java.lang.String
 */
public java.lang.String getListLabel() {
	return listLabel;
}
/**
 * Insert the method's description here.
 * Creation date: (1/12/2002 10:37:49 PM)
 * @return java.lang.String
 */
public java.lang.String getListValue() {
	return listValue;
}
/**
 * Insert the method's description here.
 * Creation date: (1/12/2002 10:38:26 PM)
 * @param newListLabel java.lang.String
 */
public void setListLabel(java.lang.String newListLabel) {
	listLabel = newListLabel;
}
/**
 * Insert the method's description here.
 * Creation date: (1/12/2002 10:37:49 PM)
 * @param newListValue java.lang.String
 */
public void setListValue(java.lang.String newListValue) {
	listValue = newListValue;
}
/**
 * Insert the method's description here.
 * Creation date: (3/27/2002 2:16:00 PM)
 * @return java.lang.String
 */
public String toString() {
	return getListValue();
}
}
