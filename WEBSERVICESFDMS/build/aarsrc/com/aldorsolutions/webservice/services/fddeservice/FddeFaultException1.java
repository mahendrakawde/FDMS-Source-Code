/**
 * FddeFaultException1.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */

package com.aldorsolutions.webservice.services.fddeservice;

public class FddeFaultException1 extends java.lang.Exception {

	private com.aldorsolutions.webservice.services.fddeservice.FddeFault faultMessage;

	public FddeFaultException1() {
		super("FddeFaultException1");
	}

	public FddeFaultException1(java.lang.String s) {
		super(s);
	}

	public FddeFaultException1(java.lang.String s, java.lang.Throwable ex) {
		super(s, ex);
	}

	public void setFaultMessage(
			com.aldorsolutions.webservice.services.fddeservice.FddeFault msg) {
		faultMessage = msg;
	}

	public com.aldorsolutions.webservice.services.fddeservice.FddeFault getFaultMessage() {
		return faultMessage;
	}
}