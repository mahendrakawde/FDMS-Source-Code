/*
 * Created on Dec 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.aldorsolutions.webfdms.database.framework;

/**
 * @author Gregg
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -997657696841992088L;

	/**
	 * 
	 */
	public TableException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public TableException(String arg0) {
		super("Table Error: " + arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public TableException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TableException(String arg0, Throwable arg1) {
		super("Table Error: " + arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
