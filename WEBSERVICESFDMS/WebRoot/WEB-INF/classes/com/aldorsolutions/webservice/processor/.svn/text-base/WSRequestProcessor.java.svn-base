package com.aldorsolutions.webservice.processor;

import java.util.List;

import com.aldorsolutions.fdms.exception.*;
import com.aldorsolutions.fdms.service.*;
import com.aldorsolutions.fdms.to.common.Options;
import com.aldorsolutions.fdms.to.common.VitalsIdInfos;
import com.aldorsolutions.fdms.to.service.*;
import com.aldorsolutions.fdms.to.vitals.VitalsIdInfo;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.UserLocation;
import com.aldorsolutions.webservice.services.fddeservice.FddeFaultException0;
import com.aldorsolutions.webservice.services.fdmsservice.FdmsFaultException0;
import com.aldorsolutions.webservice.xsd.fdde.service.*;
import com.aldorsolutions.webservice.xsd.fdms.info.*;
import com.aldorsolutions.webservice.xsd.fdms.service.*;
import com.aldorsolutions.webservice.util.DBDataValidator;
import com.aldorsolutions.webservice.util.InvalidDataException;
import com.aldorsolutions.webservice.util.InvalidRequestException;
import com.aldorsolutions.webservice.util.RequestConverter;
import com.aldorsolutions.webservice.util.ResponseConverter;
import com.aldorsolutions.webservice.util.SoapFaultFactory;
import com.aldorsolutions.webservice.util.WebServiceValidator;

public class WSRequestProcessor {

	public static FdmsInfo processGetInfo(String userName, String passWord,
			InfoType infoInterested, InfoContextType infoContext)
			throws FdmsFaultException0 {
		
		DbUserSession user = validateFdmsUser(userName, passWord);
		FdmsService service = FdmsServiceImpl.getInstance();
		FdmsInfo response = null;
		if (infoInterested != null) {
			if (InfoType._userInfo.equals(infoInterested.getValue())) {
				FdmsUserInfo fdmsUserInfo = service.getUserInfo(user);
				response = createFdmsInfo(fdmsUserInfo);
			}
			else if (InfoType._comboServiceType.equals(infoInterested.getValue())){
				Options options = service.getComboServiceType(user);
				response = createFdmsInfo(options);
				
			} else if (infoContext != null) {
				try {
					if (InfoType._directorsInfoByLocaleId.equals(infoInterested.getValue())
							&& infoContext.getLocaleId() > 0) {
						Options options = service.getDirectors(user, infoContext.getLocaleId());
						response = createFdmsInfo(options);
					}
					else if (InfoType._vitalsByDecedentVitalsId.equals(infoInterested.getValue())
							&& infoContext.getVitalsId() > 0) {
						Options options = service.getDirectors(user, infoContext.getLocaleId());
						response = createFdmsInfo(options);
					} 
					else if (InfoType._vitalsIdByContractCodeAndLocationId.equals(infoInterested.getValue())
							&& infoContext.getContractCode()!= null && infoContext.getLocationId() > 0){
						VitalsIdInfo vitalsInfo = RequestConverter.getVitialsIdInfoRequest(infoContext);
						VitalsIdInfos infos = service.getVitalsIdInfo(user,vitalsInfo);
						response = createFdmsInfo(infos);
					}
					else if (InfoType._vitalsIdByCaseCodeAndLocationId.equals(infoInterested.getValue())
							&& infoContext.getCaseCode()!= null && infoContext.getLocationId() > 0){
						VitalsIdInfo vitalsInfo = RequestConverter.getVitialsIdInfoRequest(infoContext);
						VitalsIdInfos infos = service.getVitalsIdInfo(user,vitalsInfo);
						response = createFdmsInfo(infos);
					}
					else if (InfoType._vitalsIdByLastnameAndLocationId.equals(infoInterested.getValue())
							&& infoContext.getDeceasedLastname()!= null && infoContext.getLocationId() > 0){
						VitalsIdInfo vitalsInfo = RequestConverter.getVitialsIdInfoRequest(infoContext);
						VitalsIdInfos infos = service.getVitalsIdInfo(user,vitalsInfo);
						response = createFdmsInfo(infos);
					}
				} catch (InvalidInfoRequestException ex) {
					throw SoapFaultFactory.createFdmsSoapFault(
							"InvalidInfoContext", ex.getMessage());
				}
			}
		}

		return response;
	}

	

	public static FdmsResponse processGetService(String userName,
			String password, FdmsRequest request) throws FdmsFaultException0 {

		FdmsResponse response = null;

		// validate user
		DbUserSession user = validateFdmsUser(userName, password);

		// verify service type requested
		ServiceType serviceType = request.getRequestType();
		if (serviceType != null) {
			
			String serviceTypeName = "";
			try {
				serviceTypeName = validateRequestContext(request.getRequestContext(), serviceType, user);
				FdmsService service = FdmsServiceImpl.getInstance();
				// Create AtNeed Case Service
				if (serviceType.getValue().equals(ServiceType._atNeed)&& request.getFdmsRequestChoice_type0().getAtNeedRequest() != null) {
					
					// get the request
					AtNeedRequest atNeedRequest = request.getFdmsRequestChoice_type0().getAtNeedRequest();
					
					WebServiceValidator.getInstance().validateAtNeedRequest(request.getRequestContext(), atNeedRequest);

					// convert xml TO to fdms To
					FdmsAtNeedRequest fdmsAtNeedRequest = RequestConverter.getFdmsAtNeedRequest(request.getRequestContext(),atNeedRequest);

					// call the service requested
					int vitalsId = service.processCase(user,fdmsAtNeedRequest);
					response = createFdmsResponse(serviceType);
					response.getFdmsResponseChoice_type0().setVitalsId(vitalsId);
				}
				else if (serviceType.getValue().equals(ServiceType._preNeed)&& request.getFdmsRequestChoice_type0().getPreNeedRequest() != null) {
					
					// get the request
					PreNeedRequest preNeedRequest = request.getFdmsRequestChoice_type0().getPreNeedRequest();
					
					WebServiceValidator.getInstance().validatePreNeedRequest(request.getRequestContext(), preNeedRequest);

					// convert xml TO to fdms To
					FdmsPreNeedRequest fdmsPreNeedRequest = RequestConverter.getFdmsPreNeedRequest(request.getRequestContext(),preNeedRequest);

					// call the service requested
					int vitalsId = service.processCase(user,fdmsPreNeedRequest);
					response = createFdmsResponse(serviceType);
					response.getFdmsResponseChoice_type0().setVitalsId(vitalsId);
				}
				// Create AtNeed Finance.
				else if (serviceType.getValue().equals(ServiceType._atNeedFinance)&& request.getFdmsRequestChoice_type0().getAtNeedFinanceRequest() != null) {
					
					// get the request
					AtNeedFinanceRequest atNeedFinanceRequest = request.getFdmsRequestChoice_type0().getAtNeedFinanceRequest();
					
					WebServiceValidator.getInstance().validateAtNeedFinanceRequest(atNeedFinanceRequest);

					// convert xml TO to fdms To
					FdmsAtNeedFinanceRequest fdmsAtNeedFinanceRequest = RequestConverter.getFdmsAtNeedFinanceRequest(request.getRequestContext(), atNeedFinanceRequest);

					// call the service requested
					boolean status = service.processFinance(user, fdmsAtNeedFinanceRequest);
					response = createFdmsSuccessResponse(serviceType, status);
				} 
				// create Vitals Service
				else if (serviceType.getValue().equals(ServiceType._vitals)&& request.getFdmsRequestChoice_type0().getVitalsRequest() != null) {
					
					// get the request
					VitalsRequest vitalsRequest = request.getFdmsRequestChoice_type0().getVitalsRequest();
					
					WebServiceValidator.getInstance().validateVitlsRequest(vitalsRequest);

					// convert xml TO to fdms To
					FdmsVitalsRequest fdmsVitalsRequest = RequestConverter.getFdmsVitalsRequest(request.getRequestContext(), vitalsRequest);

					// call the service requested
					boolean status = service.processVitals(user, fdmsVitalsRequest);
					response = createFdmsSuccessResponse(serviceType, status);
				} else{	
						throw SoapFaultFactory.createFdmsSoapFault(
								"InvalidFdmsRequest", " Request is missing for " + serviceType.getValue() + " service.");
					}
			} catch (InvalidDataException invalidDataEx) {
				throw createInvalidDataFault(serviceTypeName, invalidDataEx);
			} catch (InvalidRequestException invalidRequestEx) {
				throw createInvalidRequestFault(serviceTypeName, invalidRequestEx);
			} catch (FdmsFaultException0 soapEx){
				throw soapEx;
			}
		}
		return response;
	}

	private static String validateRequestContext(RequestContextType requestContext, ServiceType serviceType, DbUserSession user) throws FdmsFaultException0 {
		String serviceTypeName = "";
		if(requestContext != null){
			try{
				if(ServiceType._atNeed.equals(serviceType.getValue()) || ServiceType._preNeed.equals(serviceType.getValue())){
					serviceTypeName = ServiceType._atNeed.equals(serviceType.getValue())? "AtNeedRequest" : "PreNeedRequest";
					if(requestContext.getVitalsId()>0){
						int localeId  = DBDataValidator.getInstance().validateVitalsId(requestContext.getVitalsId(), user);
						if(localeId <= 0){
							throw SoapFaultFactory.createFdmsSoapFault("InvalidRequestContext", "Given vitalsId is not valid.");
						}else{
							if(requestContext.getLocaleId() > 0 && requestContext.getLocaleId() != localeId){
								throw SoapFaultFactory.createFdmsSoapFault("InvalidRequestContext", "Given localeId is not valid for the given vitalsId.");
							}
							requestContext.setLocaleId(localeId);
							if(requestContext.getLocationId()>0){
								DBDataValidator.getInstance().validateLocaleAndLocation(localeId, requestContext.getLocationId(), user);
							}
							if(requestContext.getDirectorId()>0){
								DBDataValidator.getInstance().validateLocaleAndDirector(localeId, requestContext.getDirectorId(), user);
							}
						}
					}else{
						DBDataValidator.getInstance().
								validateLocaleLocationAndDirector(requestContext.getLocaleId(), 
										requestContext.getLocationId(), 
										requestContext.getDirectorId(), user);
					}
					
				}
				else if (ServiceType._atNeedFinance.equals(serviceType.getValue()) || ServiceType._vitals.equals(serviceType.getValue())){
					serviceTypeName = serviceType.getValue().equals(ServiceType._atNeedFinance) ? "AtNeedFinanceRequest" : "VitalsRequest";
					int localeId  = DBDataValidator.getInstance().validateVitalsId(requestContext.getVitalsId(), user);
					if(localeId <= 0){
						throw SoapFaultFactory.createFdmsSoapFault("InvalidRequestContext", "Given vitalsId is not valid.");
					}
				}

			}catch(InvalidDataException invDataEx){
				throw createInvalidDataFault(serviceTypeName, invDataEx);
			}catch(FdmsFaultException0 fdmsFaultEx){
				throw fdmsFaultEx;
			}
		}
		return serviceTypeName;		
	}
	

	private static FdmsFaultException0 createMissingUserInfoFdmsSoapFault(
			boolean userName, boolean password) {
		String reason = createErrorMsgForMissingUserInfo(userName, password);
		return SoapFaultFactory.createFdmsSoapFault("ParameterMissing", reason);
	}
	

	private static FddeFaultException0 createMissingUserInfoFddeSoapFault(
			boolean userName, boolean password) {
		String reason = createErrorMsgForMissingUserInfo(userName, password);
		return SoapFaultFactory.createFddeSoapFault(ErrorCodeType.Others, reason);
	}
	
	private static String createErrorMsgForMissingUserInfo(boolean userName, boolean password){
		String errMsg = "";
		if (userName & password) {
			errMsg = "Parameters userName and passWord are missing.";
		} else if (userName) {
			errMsg = "Parameter userName is missing.";
		} else if (password) {
			errMsg = "Parameter passWord is missing.";
		}
		return errMsg;
	}

	private static FdmsFaultException0 createInvalidDataFault(
			String requestType, InvalidDataException ex) {
		return SoapFaultFactory.createFdmsSoapFault("Invalid" + requestType, ex
				.getMessage());
	}

	private static FdmsFaultException0 createInvalidRequestFault(String requestType,
			InvalidRequestException invalidRequestEx) {
		return SoapFaultFactory.createFdmsSoapFault("Invalid" + requestType, invalidRequestEx.getMessage());
	}

	private static DbUserSession validateFdmsUser(String userName, String password)
			throws FdmsFaultException0 {
		if (userName != null && password != null) {
			try {

				DbUserSession user = validateUser(userName, password);

				if (user != null) {
					
					if (user.getSecurityFdmsWebservice() == 0  ) {
						throw SoapFaultFactory.createFdmsSoapFault("InvalidFdmsUser",
						"User has no access to this services.");
			        }
					return user;
				}
				throw SoapFaultFactory
						.createFdmsSoapFault("UserInfoMissing",
								" System does not have any information for the given user.");

			} catch (InvalidFdmsUserException invalEx) {
				throw SoapFaultFactory.createFdmsSoapFault("InvalidFdmsUser",
						"User not exist with given user name and password");
			} catch (UserLockedException lockedEx) {
				throw SoapFaultFactory.createFdmsSoapFault("UserLocked",
						"User account has been locked.");
			} catch (UserExpiredException expiredUserEx) {
				throw SoapFaultFactory.createFdmsSoapFault("UserExpired",
						"User account has been expired.");
			}
		}
		throw createMissingUserInfoFdmsSoapFault(userName == null,
				password == null);
	}


	private static DbUserSession validateFddeUser(String userName, String password)
			throws FddeFaultException0 {
		if (userName != null && password != null) {
			try {

				DbUserSession user = validateUser(userName, password);

				if (user != null) {
					
					if (user.getSecurityFddeWebservice() == 0  ) {
						throw SoapFaultFactory.createFddeSoapFault(ErrorCodeType.InvalidUser,
						"User has no access to this services.");
			        }
					
					return user;
				}
				throw SoapFaultFactory
						.createFddeSoapFault(ErrorCodeType.Others,
								" System does not have any information for the given user.");

			} catch (InvalidFdmsUserException invalEx) {
				throw SoapFaultFactory.createFddeSoapFault(ErrorCodeType.InvalidUser,
						"User not exist with given user name and password");
			} catch (UserLockedException lockedEx) {
				throw SoapFaultFactory.createFddeSoapFault(ErrorCodeType.UserLocked,
						"User account has been locked.");
			} catch (UserExpiredException expiredUserEx) {
				throw SoapFaultFactory.createFddeSoapFault(ErrorCodeType.UserExpired,
						"User account has been expired.");
			}
		}
		throw createMissingUserInfoFddeSoapFault(userName == null,
				password == null);
	}
	


	private static DbUserSession validateUser(String userName, String password)
			throws InvalidFdmsUserException, UserLockedException, UserExpiredException {
		
		FdmsService service = FdmsServiceImpl.getInstance();
		return service.checkValidUser(userName, password);
	}
	
	private static FdmsInfo createFdmsInfo(Options options) {
		
		return ResponseConverter.getFdmsInfo(options);
	}

	
	private static FdmsInfo createFdmsInfo(FdmsUserInfo fdmsUserInfo) {
		
		return ResponseConverter.getFdmsInfo(fdmsUserInfo);
	}

	private static FdmsInfo createFdmsInfo(VitalsIdInfos infos) {
		return ResponseConverter.getFdmsInfo(infos);
	}
	
	private static FdmsResponse createFdmsResponse(ServiceType serviceType){
		FdmsResponse response = new FdmsResponse();
		response.setResponseType(serviceType);
		FdmsResponseChoice_type0 choice = new FdmsResponseChoice_type0();
		response.setFdmsResponseChoice_type0(choice);
		return response;
	}
	
	private static FdmsResponse createFdmsSuccessResponse(ServiceType serviceType, boolean status){
		FdmsResponse response = createFdmsResponse(serviceType);
		
		FdmsStatusResponseType statusResponse = new FdmsStatusResponseType();
		statusResponse.setStatus(status);
		
		response.getFdmsResponseChoice_type0().setFdmsStatusResponse(statusResponse);
		return response;
	}



	public static void processHelloAldor(String greeting) {
		FdmsService service = FdmsServiceImpl.getInstance();
		service.saveGreeting(greeting);
	}



	public static ResultType processGetData(String userName, String passWord,
			DataRequestType dataRequest) throws FddeFaultException0 {
		
		DbUserSession user = validateFddeUser(userName, passWord);
		
		String requestedData = dataRequest.getDataInterest().getValue();
		boolean isSciRequest = requestedData != null && requestedData.startsWith("sci");
		CriteriaType criteria = dataRequest.getCriteria();
		
		if(isSciRequest){
			//validate user privileges to access SCI FddeService
		}
		
		try{
			FdmsService service = FdmsServiceImpl.getInstance();
			
			// validate criteria
			if (DataInterestType._casesByFuneralHome.equals(requestedData)||
					DataInterestType._caseById.equals(requestedData)||
					DataInterestType._financialInfoByFuneralHome.equals(requestedData)||
					DataInterestType._caseSaleByCaseId.equals(requestedData)||
					DataInterestType._invOnHandByFuneralHome.equals(requestedData)			
//					||DataInterestType._sciContracts.equals(requestedData)
//					||DataInterestType._sciCash.equals(requestedData) 
//					|| DataInterestType._sciLineItem.equals(requestedData)
//					|| DataInterestType._sciARDetailData.equals(requestedData)
					){
				
				if((DataInterestType._casesByFuneralHome.equals(requestedData) 
						|| DataInterestType._financialInfoByFuneralHome.equals(requestedData)
						|| DataInterestType._invOnHandByFuneralHome.equals(requestedData)
//						|| DataInterestType._sciContracts.equals(requestedData)
//						|| DataInterestType._sciCash.equals(requestedData)
//						|| DataInterestType._sciLineItem.equals(requestedData)
//						|| DataInterestType._sciARDetailData.equals(requestedData)
						) 
						&& (criteria == null || criteria.getFuneralHomeId() == null) ){	
					
					throw SoapFaultFactory.createFddeSoapFault(
							ErrorCodeType.InvalidCriteria,
							"'FuneralHomeId' is required for " + requestedData);
					
				}else if((DataInterestType._caseById.equals(requestedData) 
						|| DataInterestType._caseSaleByCaseId.equals(requestedData))
						&& (criteria == null || criteria.getCaseId() == null)){
					
					throw SoapFaultFactory.createFddeSoapFault(
							ErrorCodeType.InvalidCriteria,
							"'CaseId' is required for " + requestedData);
					
				}
			}
			if(criteria != null && (criteria.getFuneralHomeId()!=null || criteria.getCaseId() != null)){
//				validate user privileges to access requested data based on criteria
				List<UserLocation> userLocations = service.getUserLocations(user);
				UserLocation requstedLocation = null;
				if(criteria.getFuneralHomeId() != null){
					requstedLocation = UserLocation.getLocationFromFuneralHomeID(criteria.getFuneralHomeId());
				}else{
					requstedLocation = UserLocation.getLocationFromCaseID(criteria.getCaseId());
				}
				boolean userNotAuthorized = true;
				for(UserLocation userLocation : userLocations){
					if(userLocation.equals(requstedLocation)){
						userNotAuthorized = false;
						break;
					}
				}
				if(userNotAuthorized){
					throw SoapFaultFactory.createFddeSoapFault(
							ErrorCodeType.NotAuthorizedToAccessData,
							"User has no permission to requested location : " + 
							criteria.getFuneralHomeId() != null? 
									criteria.getFuneralHomeId().getFuneralHomeIdType()
									:requstedLocation.getFuneralHomeIdStr());
				}
			}
			
			return service.getData(user, requestedData, criteria, 
					dataRequest.getFilter(), dataRequest.getDebug(), isSciRequest);
			
		}catch(FddeFaultException0 fddeFault){
			
			throw fddeFault;
			
		}catch(FDDEException fddeEx){
			
			throw SoapFaultFactory.createFddeSoapFault(
					fddeEx.getErrorCode(),fddeEx.getMessage());
			
		}catch(Exception ex){
			
			throw SoapFaultFactory.createFddeSoapFault(
					ErrorCodeType.Others,ex.getMessage());
			
		}
	}

}
