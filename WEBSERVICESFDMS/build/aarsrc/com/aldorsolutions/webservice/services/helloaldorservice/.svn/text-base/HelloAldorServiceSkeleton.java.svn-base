/**
 * HelloAldorServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */
package com.aldorsolutions.webservice.services.helloaldorservice;

import com.aldorsolutions.webservice.processor.WSRequestProcessor;

/**
 * HelloAldorServiceSkeleton java skeleton for the axisService
 */
public class HelloAldorServiceSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param helloAldor
	 */

	public AldorGreeting helloAldor(HelloAldor helloAldor) {

		AldorGreeting response = null;

		if (helloAldor != null) {

			String greeting = helloAldor.getHelloAldor();

			if (greeting != null && greeting.trim().length() > 0) {
				WSRequestProcessor.processHelloAldor(greeting.trim());
				greeting = "Hello " + greeting.trim();
			} else {
				greeting = "Hello World";
			}

			response = new AldorGreeting();
			response.setAldorGreeting(greeting);
		}

		return response;
	}

}
