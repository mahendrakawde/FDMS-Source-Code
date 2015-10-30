/**
 * FdmsServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */
package com.aldorsolutions.webservice.services.fdmsservice;

import com.aldorsolutions.webservice.processor.WSRequestProcessor;
import com.aldorsolutions.webservice.xsd.fdms.service.FdmsResponse;
import com.aldorsolutions.webservice.xsd.fdms.info.FdmsInfo;
/**
 * FdmsServiceSkeleton java skeleton for the axisService
 */
public class FdmsServiceSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param getServiceRequest
	 * @throws FdmsFaultException0
	 *             :
	 */

	public GetServiceResponse getService(GetServiceRequest getServiceRequest)
			throws FdmsFaultException0 {
		if(getServiceRequest != null){
			FdmsResponse result = WSRequestProcessor.processGetService(getServiceRequest.getUserName(), 
					getServiceRequest.getPassWord(), getServiceRequest.getServiceRequest());
			GetServiceResponse response = new GetServiceResponse();
			response.setServiceResponse(result);
			return response;
		}
		throw createRequestMissingSoapFault("getServiceRequest");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getFdmsInfoRequest
	 * @throws FdmsFaultException0
	 *             :
	 */

	public GetFdmsInfoResponse getFdmsInfo(GetFdmsInfoRequest getFdmsInfoRequest)
			throws FdmsFaultException0 {
		
		if (getFdmsInfoRequest != null) {
				FdmsInfo fdmsInfo = WSRequestProcessor.processGetInfo(
						getFdmsInfoRequest.getUserName(), getFdmsInfoRequest.getPassWord(), getFdmsInfoRequest.getInfoInterested(), getFdmsInfoRequest.getRequestContext());
			
				GetFdmsInfoResponse response = new GetFdmsInfoResponse();
				response.setGetFdmsInfoResponse(fdmsInfo);
				
				return response;
		}

		throw createRequestMissingSoapFault("getUserInfoRequest");
	}
	
	private FdmsFaultException0 createRequestMissingSoapFault(String requestType){
		FdmsFault fdmsFaultMsg = new FdmsFault();
		fdmsFaultMsg.setFaultCode("RequestMissing");
		fdmsFaultMsg.setFaultReason(requestType + " is missing!");
		
		FdmsFaultException0 soapFault = new FdmsFaultException0();
		soapFault.setFaultMessage(fdmsFaultMsg);
		return soapFault;
	}

}
