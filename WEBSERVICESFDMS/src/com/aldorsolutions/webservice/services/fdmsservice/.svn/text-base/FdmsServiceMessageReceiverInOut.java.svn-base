/**
 * FdmsServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */
package com.aldorsolutions.webservice.services.fdmsservice;

/**
 * FdmsServiceMessageReceiverInOut message receiver
 */

public class FdmsServiceMessageReceiverInOut extends
		org.apache.axis2.receivers.AbstractInOutMessageReceiver {

	public void invokeBusinessLogic(
			org.apache.axis2.context.MessageContext msgContext,
			org.apache.axis2.context.MessageContext newMsgContext)
			throws org.apache.axis2.AxisFault {

		try {

			// get the implementation class for the Web Service
			Object obj = getTheImplementationObject(msgContext);

			FdmsServiceSkeleton skel = (FdmsServiceSkeleton) obj;
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

				if ("getService".equals(methodName)) {

					com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse getServiceResponse1 = null;
					com.aldorsolutions.webservice.services.fdmsservice.GetServiceRequest wrappedParam = (com.aldorsolutions.webservice.services.fdmsservice.GetServiceRequest) fromOM(
							msgContext.getEnvelope().getBody()
									.getFirstElement(),
							com.aldorsolutions.webservice.services.fdmsservice.GetServiceRequest.class,
							getEnvelopeNamespaces(msgContext.getEnvelope()));

					getServiceResponse1 =

					skel.getService(wrappedParam);

					envelope = toEnvelope(getSOAPFactory(msgContext),
							getServiceResponse1, false);
				} else

				if ("getFdmsInfo".equals(methodName)) {

					com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse getFdmsInfoResponse3 = null;
					com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoRequest wrappedParam = (com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoRequest) fromOM(
							msgContext.getEnvelope().getBody()
									.getFirstElement(),
							com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoRequest.class,
							getEnvelopeNamespaces(msgContext.getEnvelope()));

					getFdmsInfoResponse3 =

					skel.getFdmsInfo(wrappedParam);

					envelope = toEnvelope(getSOAPFactory(msgContext),
							getFdmsInfoResponse3, false);

				} else {
					throw new java.lang.RuntimeException("method not found");
				}

				newMsgContext.setEnvelope(envelope);
			}
		} catch (FdmsFaultException0 e) {

			msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,
					"fdmsFault");
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
			com.aldorsolutions.webservice.services.fdmsservice.GetServiceRequest param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							com.aldorsolutions.webservice.services.fdmsservice.GetServiceRequest.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.aldorsolutions.webservice.services.fdmsservice.FdmsFault param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							com.aldorsolutions.webservice.services.fdmsservice.FdmsFault.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoRequest param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoRequest.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param
					.getOMElement(
							com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse.MY_QNAME,
							org.apache.axiom.om.OMAbstractFactory
									.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
			org.apache.axiom.soap.SOAPFactory factory,
			com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {
		try {
			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory
					.getDefaultEnvelope();

			emptyEnvelope
					.getBody()
					.addChild(
							param
									.getOMElement(
											com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse.MY_QNAME,
											factory));

			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	private com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse wrapgetService() {
		com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse wrappedElement = new com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse();
		return wrappedElement;
	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
			org.apache.axiom.soap.SOAPFactory factory,
			com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {
		try {
			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory
					.getDefaultEnvelope();

			emptyEnvelope
					.getBody()
					.addChild(
							param
									.getOMElement(
											com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse.MY_QNAME,
											factory));

			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
	}

	private com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse wrapgetFdmsInfo() {
		com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse wrappedElement = new com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse();
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

			if (com.aldorsolutions.webservice.services.fdmsservice.GetServiceRequest.class
					.equals(type)) {

				return com.aldorsolutions.webservice.services.fdmsservice.GetServiceRequest.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse.class
					.equals(type)) {

				return com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.aldorsolutions.webservice.services.fdmsservice.FdmsFault.class
					.equals(type)) {

				return com.aldorsolutions.webservice.services.fdmsservice.FdmsFault.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoRequest.class
					.equals(type)) {

				return com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoRequest.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse.class
					.equals(type)) {

				return com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.aldorsolutions.webservice.services.fdmsservice.FdmsFault.class
					.equals(type)) {

				return com.aldorsolutions.webservice.services.fdmsservice.FdmsFault.Factory
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
