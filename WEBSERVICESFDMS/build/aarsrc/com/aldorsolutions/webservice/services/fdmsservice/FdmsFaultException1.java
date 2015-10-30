/**
 * FdmsFaultException1.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */

package com.aldorsolutions.webservice.services.fdmsservice;

public class FdmsFaultException1 extends java.lang.Exception {

	private com.aldorsolutions.webservice.services.fdmsservice.FdmsFault faultMessage;

	public FdmsFaultException1() {
		super("FdmsFaultException1");
	}

	public FdmsFaultException1(java.lang.String s) {
		super(s);
	}

	public FdmsFaultException1(java.lang.String s, java.lang.Throwable ex) {
		super(s, ex);
	}

	public void setFaultMessage(
			com.aldorsolutions.webservice.services.fdmsservice.FdmsFault msg) {
		faultMessage = msg;
	}

	public com.aldorsolutions.webservice.services.fdmsservice.FdmsFault getFaultMessage() {
		return faultMessage;
	}
}
