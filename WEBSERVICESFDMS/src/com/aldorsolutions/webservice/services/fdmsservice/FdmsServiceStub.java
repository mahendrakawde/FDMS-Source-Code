/**
 * FdmsServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */
package com.aldorsolutions.webservice.services.fdmsservice;

/*
 *  FdmsServiceStub java implementation
 */

public class FdmsServiceStub extends org.apache.axis2.client.Stub {
	protected org.apache.axis2.description.AxisOperation[] _operations;

	// hashmaps to keep the fault mapping
	private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
	private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
	private java.util.HashMap faultMessageMap = new java.util.HashMap();

	private static int counter = 0;

	private static synchronized String getUniqueSuffix() {
		// reset the counter if it is greater than 99999
		if (counter > 99999) {
			counter = 0;
		}
		counter = counter + 1;
		return Long.toString(System.currentTimeMillis()) + "_" + counter;
	}

	private void populateAxisService() throws org.apache.axis2.AxisFault {

		// creating the Service with a unique name
		_service = new org.apache.axis2.description.AxisService("FdmsService"
				+ getUniqueSuffix());
		addAnonymousOperations();

		// creating the operations
		org.apache.axis2.description.AxisOperation __operation;

		_operations = new org.apache.axis2.description.AxisOperation[2];

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName(
				"http://aldorsolutions.com/webservice/services/fdmsservice",
				"getService"));
		_service.addOperation(__operation);

		_operations[0] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName(
				"http://aldorsolutions.com/webservice/services/fdmsservice",
				"getFdmsInfo"));
		_service.addOperation(__operation);

		_operations[1] = __operation;

	}

	// populates the faults
	private void populateFaults() {

		faultExceptionNameMap
				.put(
						new javax.xml.namespace.QName(
								"http://aldorsolutions.com/webservice/services/fdmsservice",
								"fdmsFault"),
						"com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1");
		faultExceptionClassNameMap
				.put(
						new javax.xml.namespace.QName(
								"http://aldorsolutions.com/webservice/services/fdmsservice",
								"fdmsFault"),
						"com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1");
		faultMessageMap.put(new javax.xml.namespace.QName(
				"http://aldorsolutions.com/webservice/services/fdmsservice",
				"fdmsFault"),
				"com.aldorsolutions.webservice.services.fdmsservice.FdmsFault");

		faultExceptionNameMap
				.put(
						new javax.xml.namespace.QName(
								"http://aldorsolutions.com/webservice/services/fdmsservice",
								"fdmsFault"),
						"com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1");
		faultExceptionClassNameMap
				.put(
						new javax.xml.namespace.QName(
								"http://aldorsolutions.com/webservice/services/fdmsservice",
								"fdmsFault"),
						"com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1");
		faultMessageMap.put(new javax.xml.namespace.QName(
				"http://aldorsolutions.com/webservice/services/fdmsservice",
				"fdmsFault"),
				"com.aldorsolutions.webservice.services.fdmsservice.FdmsFault");

	}

	/**
	 *Constructor that takes in a configContext
	 */

	public FdmsServiceStub(
			org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(configurationContext, targetEndpoint, false);
	}

	/**
	 * Constructor that takes in a configContext and useseperate listner
	 */
	public FdmsServiceStub(
			org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint, boolean useSeparateListener)
			throws org.apache.axis2.AxisFault {
		// To populate AxisService
		populateAxisService();
		populateFaults();

		_serviceClient = new org.apache.axis2.client.ServiceClient(
				configurationContext, _service);

		configurationContext = _serviceClient.getServiceContext()
				.getConfigurationContext();

		_serviceClient.getOptions().setTo(
				new org.apache.axis2.addressing.EndpointReference(
						targetEndpoint));
		_serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

		// Set the soap version
		_serviceClient
				.getOptions()
				.setSoapVersionURI(
						org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);

	}

	/**
	 * Default Constructor
	 */
	public FdmsServiceStub(
			org.apache.axis2.context.ConfigurationContext configurationContext)
			throws org.apache.axis2.AxisFault {

		this(configurationContext,
				"http://webservices.webfdms.com/webservicesfdms/services/FdmsService");

	}

	/**
	 * Default Constructor
	 */
	public FdmsServiceStub() throws org.apache.axis2.AxisFault {

		this(
				"http://webservices.webfdms.com/webservicesfdms/services/FdmsService");

	}

	/**
	 * Constructor taking the target endpoint
	 */
	public FdmsServiceStub(java.lang.String targetEndpoint)
			throws org.apache.axis2.AxisFault {
		this(null, targetEndpoint);
	}

	/**
	 * Auto generated method signature
	 * 
	 * @see com.aldorsolutions.webservice.services.fdmsservice.FdmsService#getService
	 * @param getServiceRequest24
	 * 
	 * @throws com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1
	 *             :
	 */

	public com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse getService(

			com.aldorsolutions.webservice.services.fdmsservice.GetServiceRequest getServiceRequest24)

	throws java.rmi.RemoteException

	, com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1 {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[0].getName());
			_operationClient.getOptions().setAction("urn:getService");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(
					true);

			addPropertyToOperationClient(
					_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
					"&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(
					getFactory(_operationClient.getOptions()
							.getSoapVersionURI()),
					getServiceRequest24,
					optimizeContent(new javax.xml.namespace.QName(
							"http://aldorsolutions.com/webservice/services/fdmsservice",
							"getService")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext
					.getEnvelope();

			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement(),
					com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse.class,
					getEnvelopeNamespaces(_returnEnv));

			return (com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class
								.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass
								.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class
								.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,
								messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod(
								"setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1) {
							throw (com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(
					_messageContext);
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @see com.aldorsolutions.webservice.services.fdmsservice.FdmsService#startgetService
	 * @param getServiceRequest24
	 */
	public void startgetService(

			com.aldorsolutions.webservice.services.fdmsservice.GetServiceRequest getServiceRequest24,

			final com.aldorsolutions.webservice.services.fdmsservice.FdmsServiceCallbackHandler callback)

	throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[0].getName());
		_operationClient.getOptions().setAction("urn:getService");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(
				_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
				"&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(
				getFactory(_operationClient.getOptions().getSoapVersionURI()),
				getServiceRequest24,
				optimizeContent(new javax.xml.namespace.QName(
						"http://aldorsolutions.com/webservice/services/fdmsservice",
						"getService")));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient
				.setCallback(new org.apache.axis2.client.async.AxisCallback() {
					public void onMessage(
							org.apache.axis2.context.MessageContext resultContext) {
						try {
							org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext
									.getEnvelope();

							java.lang.Object object = fromOM(
									resultEnv.getBody().getFirstElement(),
									com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse.class,
									getEnvelopeNamespaces(resultEnv));
							callback
									.receiveResultgetService((com.aldorsolutions.webservice.services.fdmsservice.GetServiceResponse) object);

						} catch (org.apache.axis2.AxisFault e) {
							callback.receiveErrorgetService(e);
						}
					}

					public void onError(java.lang.Exception error) {
						if (error instanceof org.apache.axis2.AxisFault) {
							org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
							org.apache.axiom.om.OMElement faultElt = f
									.getDetail();
							if (faultElt != null) {
								if (faultExceptionNameMap.containsKey(faultElt
										.getQName())) {
									// make the fault by reflection
									try {
										java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
												.get(faultElt.getQName());
										java.lang.Class exceptionClass = java.lang.Class
												.forName(exceptionClassName);
										java.lang.Exception ex = (java.lang.Exception) exceptionClass
												.newInstance();
										// message class
										java.lang.String messageClassName = (java.lang.String) faultMessageMap
												.get(faultElt.getQName());
										java.lang.Class messageClass = java.lang.Class
												.forName(messageClassName);
										java.lang.Object messageObject = fromOM(
												faultElt, messageClass, null);
										java.lang.reflect.Method m = exceptionClass
												.getMethod(
														"setFaultMessage",
														new java.lang.Class[] { messageClass });
										m
												.invoke(
														ex,
														new java.lang.Object[] { messageObject });

										if (ex instanceof com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1) {
											callback
													.receiveErrorgetService((com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1) ex);
											return;
										}

										callback
												.receiveErrorgetService(new java.rmi.RemoteException(
														ex.getMessage(), ex));
									} catch (java.lang.ClassCastException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetService(f);
									} catch (java.lang.ClassNotFoundException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetService(f);
									} catch (java.lang.NoSuchMethodException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetService(f);
									} catch (java.lang.reflect.InvocationTargetException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetService(f);
									} catch (java.lang.IllegalAccessException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetService(f);
									} catch (java.lang.InstantiationException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetService(f);
									} catch (org.apache.axis2.AxisFault e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetService(f);
									}
								} else {
									callback.receiveErrorgetService(f);
								}
							} else {
								callback.receiveErrorgetService(f);
							}
						} else {
							callback.receiveErrorgetService(error);
						}
					}

					public void onFault(
							org.apache.axis2.context.MessageContext faultContext) {
						org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
								.getInboundFaultFromMessageContext(faultContext);
						onError(fault);
					}

					public void onComplete() {
						try {
							_messageContext.getTransportOut().getSender()
									.cleanup(_messageContext);
						} catch (org.apache.axis2.AxisFault axisFault) {
							callback.receiveErrorgetService(axisFault);
						}
					}
				});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[0].getMessageReceiver() == null
				&& _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[0].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature
	 * 
	 * @see com.aldorsolutions.webservice.services.fdmsservice.FdmsService#getFdmsInfo
	 * @param getFdmsInfoRequest26
	 * 
	 * @throws com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1
	 *             :
	 */

	public com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse getFdmsInfo(

			com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoRequest getFdmsInfoRequest26)

	throws java.rmi.RemoteException

	, com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1 {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[1].getName());
			_operationClient.getOptions().setAction("urn:getFdmsInfo");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(
					true);

			addPropertyToOperationClient(
					_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
					"&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(
					getFactory(_operationClient.getOptions()
							.getSoapVersionURI()),
					getFdmsInfoRequest26,
					optimizeContent(new javax.xml.namespace.QName(
							"http://aldorsolutions.com/webservice/services/fdmsservice",
							"getFdmsInfo")));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext
					.getEnvelope();

			java.lang.Object object = fromOM(
					_returnEnv.getBody().getFirstElement(),
					com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse.class,
					getEnvelopeNamespaces(_returnEnv));

			return (com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(faultElt.getQName());
						java.lang.Class exceptionClass = java.lang.Class
								.forName(exceptionClassName);
						java.lang.Exception ex = (java.lang.Exception) exceptionClass
								.newInstance();
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(faultElt.getQName());
						java.lang.Class messageClass = java.lang.Class
								.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt,
								messageClass, null);
						java.lang.reflect.Method m = exceptionClass.getMethod(
								"setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1) {
							throw (com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			_messageContext.getTransportOut().getSender().cleanup(
					_messageContext);
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations
	 * 
	 * @see com.aldorsolutions.webservice.services.fdmsservice.FdmsService#startgetFdmsInfo
	 * @param getFdmsInfoRequest26
	 */
	public void startgetFdmsInfo(

			com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoRequest getFdmsInfoRequest26,

			final com.aldorsolutions.webservice.services.fdmsservice.FdmsServiceCallbackHandler callback)

	throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[1].getName());
		_operationClient.getOptions().setAction("urn:getFdmsInfo");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(
				_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
				"&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(
				getFactory(_operationClient.getOptions().getSoapVersionURI()),
				getFdmsInfoRequest26,
				optimizeContent(new javax.xml.namespace.QName(
						"http://aldorsolutions.com/webservice/services/fdmsservice",
						"getFdmsInfo")));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient
				.setCallback(new org.apache.axis2.client.async.AxisCallback() {
					public void onMessage(
							org.apache.axis2.context.MessageContext resultContext) {
						try {
							org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext
									.getEnvelope();

							java.lang.Object object = fromOM(
									resultEnv.getBody().getFirstElement(),
									com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse.class,
									getEnvelopeNamespaces(resultEnv));
							callback
									.receiveResultgetFdmsInfo((com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoResponse) object);

						} catch (org.apache.axis2.AxisFault e) {
							callback.receiveErrorgetFdmsInfo(e);
						}
					}

					public void onError(java.lang.Exception error) {
						if (error instanceof org.apache.axis2.AxisFault) {
							org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
							org.apache.axiom.om.OMElement faultElt = f
									.getDetail();
							if (faultElt != null) {
								if (faultExceptionNameMap.containsKey(faultElt
										.getQName())) {
									// make the fault by reflection
									try {
										java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
												.get(faultElt.getQName());
										java.lang.Class exceptionClass = java.lang.Class
												.forName(exceptionClassName);
										java.lang.Exception ex = (java.lang.Exception) exceptionClass
												.newInstance();
										// message class
										java.lang.String messageClassName = (java.lang.String) faultMessageMap
												.get(faultElt.getQName());
										java.lang.Class messageClass = java.lang.Class
												.forName(messageClassName);
										java.lang.Object messageObject = fromOM(
												faultElt, messageClass, null);
										java.lang.reflect.Method m = exceptionClass
												.getMethod(
														"setFaultMessage",
														new java.lang.Class[] { messageClass });
										m
												.invoke(
														ex,
														new java.lang.Object[] { messageObject });

										if (ex instanceof com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1) {
											callback
													.receiveErrorgetFdmsInfo((com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException1) ex);
											return;
										}

										callback
												.receiveErrorgetFdmsInfo(new java.rmi.RemoteException(
														ex.getMessage(), ex));
									} catch (java.lang.ClassCastException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetFdmsInfo(f);
									} catch (java.lang.ClassNotFoundException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetFdmsInfo(f);
									} catch (java.lang.NoSuchMethodException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetFdmsInfo(f);
									} catch (java.lang.reflect.InvocationTargetException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetFdmsInfo(f);
									} catch (java.lang.IllegalAccessException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetFdmsInfo(f);
									} catch (java.lang.InstantiationException e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetFdmsInfo(f);
									} catch (org.apache.axis2.AxisFault e) {
										// we cannot intantiate the class -
										// throw the original Axis fault
										callback.receiveErrorgetFdmsInfo(f);
									}
								} else {
									callback.receiveErrorgetFdmsInfo(f);
								}
							} else {
								callback.receiveErrorgetFdmsInfo(f);
							}
						} else {
							callback.receiveErrorgetFdmsInfo(error);
						}
					}

					public void onFault(
							org.apache.axis2.context.MessageContext faultContext) {
						org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
								.getInboundFaultFromMessageContext(faultContext);
						onError(fault);
					}

					public void onComplete() {
						try {
							_messageContext.getTransportOut().getSender()
									.cleanup(_messageContext);
						} catch (org.apache.axis2.AxisFault axisFault) {
							callback.receiveErrorgetFdmsInfo(axisFault);
						}
					}
				});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[1].getMessageReceiver() == null
				&& _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[1].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

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

	private javax.xml.namespace.QName[] opNameArray = null;

	private boolean optimizeContent(javax.xml.namespace.QName opName) {

		if (opNameArray == null) {
			return false;
		}
		for (int i = 0; i < opNameArray.length; i++) {
			if (opName.equals(opNameArray[i])) {
				return true;
			}
		}
		return false;
	}

	// http://webservices.webfdms.com/webservicesfdms/services/FdmsService
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
			com.aldorsolutions.webservice.services.fdmsservice.GetServiceRequest param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory
					.getDefaultEnvelope();
			emptyEnvelope
					.getBody()
					.addChild(
							param
									.getOMElement(
											com.aldorsolutions.webservice.services.fdmsservice.GetServiceRequest.MY_QNAME,
											factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
			org.apache.axiom.soap.SOAPFactory factory,
			com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoRequest param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory
					.getDefaultEnvelope();
			emptyEnvelope
					.getBody()
					.addChild(
							param
									.getOMElement(
											com.aldorsolutions.webservice.services.fdmsservice.GetFdmsInfoRequest.MY_QNAME,
											factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

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

}
