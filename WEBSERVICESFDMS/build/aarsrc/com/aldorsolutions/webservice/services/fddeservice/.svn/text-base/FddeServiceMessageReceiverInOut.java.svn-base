/**
 * FddeServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */
package com.aldorsolutions.webservice.services.fddeservice;

/**
 * FddeServiceMessageReceiverInOut message receiver
 */

public class FddeServiceMessageReceiverInOut extends
		org.apache.axis2.receivers.AbstractInOutMessageReceiver {

	public void invokeBusinessLogic(
			org.apache.axis2.context.MessageContext msgContext,
			org.apache.axis2.context.MessageContext newMsgContext)
			throws org.apache.axis2.AxisFault {

		try {

			// get the implementation class for the Web Service
			Object obj = getTheImplementationObject(msgContext);

			FddeServiceSkeleton skel = (FddeServiceSkeleton) obj;
			// Out Envelop
			org.apache.axiom.soap.SOAPEnvelope envelope = null;
			// Find the axisOperation that has been set by the Dispatch phase.
			org.apache.axis2.description.AxisOperation op = msgContext
					.getOperationContext().getAxisOperation();
			if (op == null) {
				throw new org.apache.axis2.AxisFault(
						"Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
			}

			java.lang.String methodName;
			if ((op.getName() != null)
					&& ((methodName = org.apache.axis2.util.JavaUtils
							.xmlNameToJava(op.getName().getLocalPart())) != null)) {

				if ("getData".equals(methodName)) {

					com.aldorsolutions.webservice.services.fddeservice.GetDataResponse getDataResponse1 = null;
					com.aldorsolutions.webservice.services.fddeservice.GetDataRequest wrappedParam = (com.aldorsolutions.webservice.services.fddeservice.GetDataRequest) fromOM(
							msgContext.getEnvelope().getBody()
									.getFirstElement(),
							com.aldorsolutions.webservice.services.fddeservice.GetDataRequest.class,
							getEnvelopeNamespaces(msgContext.getEnvelope()));

					getDataResponse1 =

					skel.getData(wrappedParam);

					envelope = toEnvelope(getSOAPFactory(msgContext),
							getDataResponse1, false);

				} else {
					throw new java.lang.RuntimeException("method not found");
				}

				newMsgContext.setEnvelope(envelope);
			}
		} catch (FddeFaultException0 e) {

			msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,
					"fddeFault");
			org.apache.axis2.AxisFault f = createAxisFault(e);
			if (e.getFaultMessage() != null) {
				f.setDetail(toOM(e.getFaultMessage(), false));
			}
			throw f;
		}

		catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	//
	private org.apache.axiom.om.OMElement toOM(
			com.aldorsolutions.webservice.services.fddeservice.GetDataRequest param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							com.aldorsolutions.webservice.services.fddeservice.GetDataRequest.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.aldorsolutions.webservice.services.fddeservice.GetDataResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							com.aldorsolutions.webservice.services.fddeservice.GetDataResponse.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.aldorsolutions.webservice.services.fddeservice.FddeFault param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							com.aldorsolutions.webservice.services.fddeservice.FddeFault.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
			org.apache.axiom.soap.SOAPFactory factory,
			com.aldorsolutions.webservice.services.fddeservice.GetDataResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {
		try {
			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory
					.getDefaultEnvelope();

			emptyEnvelope
					.getBody()
					.addChild(
							param
									.getOMElement(
											com.aldorsolutions.webservice.services.fddeservice.GetDataResponse.MY_QNAME,
											factory));

			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	private com.aldorsolutions.webservice.services.fddeservice.GetDataResponse wrapgetData() {
		com.aldorsolutions.webservice.services.fddeservice.GetDataResponse wrappedElement = new com.aldorsolutions.webservice.services.fddeservice.GetDataResponse();
		return wrappedElement;
	}

	/**
	 * get the default envelope
	 */
	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
			org.apache.axiom.soap.SOAPFactory factory) {
		return factory.getDefaultEnvelope();
	}

	private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
			java.lang.Class type, java.util.Map extraNamespaces)
			throws org.apache.axis2.AxisFault {

		try {

			if (com.aldorsolutions.webservice.services.fddeservice.GetDataRequest.class
					.equals(type)) {

				return com.aldorsolutions.webservice.services.fddeservice.GetDataRequest.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.aldorsolutions.webservice.services.fddeservice.GetDataResponse.class
					.equals(type)) {

				return com.aldorsolutions.webservice.services.fddeservice.GetDataResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.aldorsolutions.webservice.services.fddeservice.FddeFault.class
					.equals(type)) {

				return com.aldorsolutions.webservice.services.fddeservice.FddeFault.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}

	/**
	 * A utility method that copies the namepaces from the SOAPEnvelope
	 */
	private java.util.Map getEnvelopeNamespaces(
			org.apache.axiom.soap.SOAPEnvelope env) {
		java.util.Map returnMap = new java.util.HashMap();
		java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
		while (namespaceIterator.hasNext()) {
			org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator
					.next();
			returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
		}
		return returnMap;
	}

	private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
		org.apache.axis2.AxisFault f;
		Throwable cause = e.getCause();
		if (cause != null) {
			f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
		} else {
			f = new org.apache.axis2.AxisFault(e.getMessage());
		}

		return f;
	}

}// end of class
