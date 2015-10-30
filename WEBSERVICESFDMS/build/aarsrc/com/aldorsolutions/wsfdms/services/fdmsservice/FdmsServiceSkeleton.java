/**
 * FdmsServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */
package com.aldorsolutions.wsfdms.services.fdmsservice;

import com.aldorsolutions.wsfdms.processor.WSRequestProcessor;
import com.aldorsolutions.wsfdms.to.xsd.serviceinfo.FdmsResponse;
import com.aldorsolutions.wsfdms.to.xsd.fdmsinfo.FdmsInfo;
import com.aldorsolutions.wsfdms.to.xsd.fdmsinfo.OptionsType;

/**
 * FdmsServiceSkeleton java skeleton for the axisService
 */
public class FdmsServiceSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param getServiceRequest
	 * @throws GetFdmsFaultException0
	 *             :
	 */

	public GetServiceResponse getService(GetServiceRequest getServiceRequest)
			throws GetFdmsFaultException0 {
		if(getServiceRequest != null){
			FdmsResponse result = WSRequestProcessor.processGetService(getServiceRequest.getUsrName(), 
					getServiceRequest.getPassWord(), getServiceRequest.getSerrviceRequest());
			GetServiceResponse response = new GetServiceResponse();
			response.setServiceResponse(result);
			return response;
		}
		throw createRequestMissingSoapFault("getServiceRequest");
	}

	public GetFdmsIntroductionResponse getFdmsIntroduction(
			GetFdmsIntroductionRequest getFdmsIntroductionRequest) throws GetFdmsFaultException0 {
		if (getFdmsIntroductionRequest != null) {
			OptionsType optionsType = WSRequestProcessor.processGetIntroduction();
		
			GetFdmsIntroductionResponse response = new GetFdmsIntroductionResponse();
			response.setGetFdmsIntroductionResponse(optionsType);
			
			return response;
		} else {
			OptionsType optionsType = WSRequestProcessor.processGetIntroduction();
		
			GetFdmsIntroductionResponse response = new GetFdmsIntroductionResponse();
			response.setGetFdmsIntroductionResponse(optionsType);
			return response;
		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getFdmsInfoRequest
	 * @throws GetFdmsFaultException0
	 *             :
	 */

	public GetFdmsInfoResponse getFdmsInfo(GetFdmsInfoRequest getFdmsInfoRequest)
			throws GetFdmsFaultException0 {
		
		if (getFdmsInfoRequest != null) {
				FdmsInfo fdmsInfo = WSRequestProcessor.processGetInfo(
						getFdmsInfoRequest.getUserName(), getFdmsInfoRequest.getPassWord(), getFdmsInfoRequest.getInfoInterested(), getFdmsInfoRequest.getRequestContext());
			
				GetFdmsInfoResponse response = new GetFdmsInfoResponse();
				response.setGetFdmsInfoResponse(fdmsInfo);
				
				return response;
		}

		throw createRequestMissingSoapFault("getUserInfoRequest");
	}
	
	private GetFdmsFaultException0 createRequestMissingSoapFault(String requestType){
		GetFdmsFault fdmsFaultMsg = new GetFdmsFault();
		fdmsFaultMsg.setFaultCode("RequestMissing");
		fdmsFaultMsg.setFaultReason(requestType + " is missing!");
		
		GetFdmsFaultException0 soapFault = new GetFdmsFaultException0();
		soapFault.setFaultMessage(fdmsFaultMsg);
		return soapFault;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param helloAldor
	 */

	public AldorGreeting helloAldor(HelloAldor helloAldor) {
		
		AldorGreeting response = null;
		
		if(helloAldor != null){
			
			String greeting = helloAldor.getHelloAldor();
			
			if(greeting != null && greeting.trim().length() > 0){
				WSRequestProcessor.processHelloAldor(greeting.trim());
				greeting = "Hello " + greeting.trim();
			}else{
				greeting = "Hello World";
			}
			
			response = new AldorGreeting();
			response.setAldorGreeting(greeting);
		}
		
		return response;
	}

}
