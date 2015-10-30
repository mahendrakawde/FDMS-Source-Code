/**
 * FddeServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */
package com.aldorsolutions.webservice.services.fddeservice;

import com.aldorsolutions.webservice.processor.WSRequestProcessor;
import com.aldorsolutions.webservice.xsd.fdde.service.DataRequestType;
import com.aldorsolutions.webservice.xsd.fdde.service.DataResponseType;
import com.aldorsolutions.webservice.xsd.fdde.service.ErrorCodeType;
import com.aldorsolutions.webservice.xsd.fdde.service.ResultType;



/**
 * FddeServiceSkeleton java skeleton for the axisService
 */
public class FddeServiceSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param getDataRequest
	 * @throws FddeFaultException0
	 *             :
	 */

	public GetDataResponse getData(GetDataRequest getDataRequest)
			throws FddeFaultException0 {
		if(getDataRequest != null){
			DataRequestType request = getDataRequest.getDataRequest();
			ResultType resultType = WSRequestProcessor.processGetData(getDataRequest.getUserName(), getDataRequest.getPassWord(),
					request);
			
			DataResponseType responseType = new DataResponseType();
			responseType.setDataReturned(request.getDataInterest());
			responseType.setRequestedCriteria(request.getCriteria());
			if(request.getDebug()!=null){
				responseType.setDebug(request.getDebug());
			}
			responseType.setResults(resultType);
			
			GetDataResponse response = new GetDataResponse();
			response.setGetDataResponse(responseType);
			
			return response;
		}
		throw createRequestMissingSoapFault();
	}
	
	private FddeFaultException0 createRequestMissingSoapFault(){
		FddeFault fddeFaultMsg = new FddeFault();
		fddeFaultMsg.setErrorCode(ErrorCodeType.RequestMissing);
		fddeFaultMsg.setCause("getDataRequest element is missing!");
		
		FddeFaultException0 soapFault = new FddeFaultException0();
		soapFault.setFaultMessage(fddeFaultMsg);
		return soapFault;
	}

}
