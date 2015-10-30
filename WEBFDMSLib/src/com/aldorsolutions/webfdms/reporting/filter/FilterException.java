/**
 * 
 */
package com.aldorsolutions.webfdms.reporting.filter;

/**
 * @author David Rollins
 * Date: Jul 20, 2007
 * File: FilterException.java	
 * 
 */
public class FilterException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 506529988923876629L;

	public FilterException (){
		super();
	}
	
	public FilterException (String message){
		super(message);
	}
	
	public FilterException (Throwable cause){
		super(cause);
	}
	
	public FilterException (String arg0, Throwable cause){
		super(arg0, cause);
	}
	
}
