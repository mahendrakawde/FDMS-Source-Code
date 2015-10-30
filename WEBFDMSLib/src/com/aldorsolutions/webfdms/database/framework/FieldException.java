/*
 * Created on Dec 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.aldorsolutions.webfdms.database.framework;

/**
 * @author Ranando
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FieldException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 840592364806195038L;

	/**
	 * 
	 */
	public FieldException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public FieldException(String arg0) {
		super("Field Error :" + arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public FieldException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public FieldException(String arg0, Throwable arg1) {
		super("Field Error :" + arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
