package com.aldorsolutions.webservice.util;

import com.aldorsolutions.webservice.xsd.comm.*;
import com.aldorsolutions.webservice.xsd.comm.fdms.*;
import com.aldorsolutions.webservice.xsd.fdms.service.*;

public class WebServiceValidator {
	
	private static WebServiceValidator instance = new WebServiceValidator();
	
	private WebServiceValidator(){}
	
	public static WebServiceValidator getInstance(){
		return instance;
	}

	public void validateAtNeedRequest(RequestContextType requestContext, AtNeedRequest request) throws InvalidDataException, InvalidRequestException{
		
		String errorMessage = "";
		
		errorMessage = validateLocation(requestContext.getLocationId(), request.getFirstCall().getFuneralHome(), "firstCall.funeralHome", errorMessage);
		errorMessage = validateCaseInfo(requestContext.getVitalsId(), request.getCaseInfo(), "caseInfo", errorMessage);
		
		if(errorMessage.length()>0){
			throw new InvalidRequestException(errorMessage);
		}
		errorMessage = validateDeceased(request.getDeceased(), "deceased", errorMessage);
		if(errorMessage.length()>0){
			throw createException(errorMessage);
		}
	}
	
	private String validateCaseInfo(int vitalsId, CaseInfoType caseInfo,
			String prefix, String errorMessage) {
		
		if(vitalsId > 0 && caseInfo != null && caseInfo.getDecedentVitalsId() > 0 && caseInfo.getDecedentVitalsId() != vitalsId){
			errorMessage = addToErrorList(prefix + ".decedentVitalsId is not match with requestContext locationId", errorMessage);
		}else if(vitalsId > 0 && caseInfo != null){
			caseInfo.setDecedentVitalsId(vitalsId);
		}else if(vitalsId <= 0 && caseInfo != null && caseInfo.getDecedentVitalsId() > 0){
			errorMessage = addToErrorList(prefix + ".decedentVitalsId can not find a requestContext vitalsId", errorMessage);
		}
		
		return errorMessage;
	}

	private String validateLocation(int locationId, Location funeralHome, String prefix,
			String errorMessage) {
		if(locationId >0 && funeralHome != null && funeralHome.getId() > 0 && funeralHome.getId() != locationId){
			errorMessage = addToErrorList(prefix + ".id is not match with requestContext locationId", errorMessage);
		}else if(locationId >0 && funeralHome != null ){
			funeralHome.setId(locationId) ; 
		}else if(locationId <= 0 && funeralHome != null && funeralHome.getId() > 0){
			errorMessage = addToErrorList(prefix + ".id can not find a requestContext locationId", errorMessage);
		}
		return errorMessage;
	}

	private String validateDeceased(DeceasedType deceased, String prefix, String errorMessage){

		if(deceased != null){
			errorMessage = validatNameType(deceased.getName(), prefix + ".name", errorMessage);
			
			if(isStringNullOrNill(deceased.getMemorialName())){
				errorMessage = addToErrorList(prefix + ".memorialName", errorMessage);
			}
	
			if(deceased.getServiceDate() == null){
				errorMessage = addToErrorList(prefix + ".serviceDate", errorMessage);
			}
		}else{
			errorMessage = addToErrorList(prefix , errorMessage);
		}
		
		return errorMessage;
	}

	public void validatePreNeedRequest(RequestContextType requestContext,
			PreNeedRequest preNeedRequest) throws InvalidDataException, InvalidRequestException {
		
		String errorMessage = "";
		errorMessage = validateCaseInfo(requestContext.getVitalsId(), preNeedRequest.getCaseInfo(), "caseInfo", errorMessage);
		if(errorMessage.length()>0){
			throw new InvalidRequestException(errorMessage);
		}
		
		errorMessage = validateBuyer(preNeedRequest.getBuyer(), "buyer", errorMessage);
		errorMessage = validateInsuredInfo(preNeedRequest.getInsuredInfo(), "insuredInfo", errorMessage);
		errorMessage = validateSalesInfo(preNeedRequest.getSalesInfo(), "salesInfo", errorMessage);
		
		if(errorMessage.length()>0){
			throw createException(errorMessage);
		}
	}

	private String validateBuyer(BuyerInfoType buyer, String prefix, String errorMessage) {
		
		if(buyer != null){
			
			errorMessage = validatNameType(buyer.getName(), prefix + ".name", errorMessage);
			
		} else {
			errorMessage = addToErrorList(prefix , errorMessage);
		}
		
		return errorMessage;
	}

	private String validateInsuredInfo(InsuredInfoType insuredInfo, String prefix, String errorMessage) {
		
		if(insuredInfo != null){
			errorMessage = validatNameType(insuredInfo.getName(), prefix + ".name", errorMessage);
		} else {
			errorMessage = addToErrorList(prefix , errorMessage);
		}

		return errorMessage;
	}

	private String validateSalesInfo(SalesInfoType salesInfo, String prefix, String errorMessage) {
		
		if(salesInfo != null){
			if(salesInfo.getSaleDate() == null){
				errorMessage = addToErrorList(prefix + ".saleDate", errorMessage);
			}
		} else {
			errorMessage = addToErrorList(prefix , errorMessage);
		}
		
		return errorMessage;
	}
	
	private String validatNameType(MsNameType name, String prefix, String errorMessage){
		
		if(name != null){
			if(isStringNullOrNill(name.getFirstName())){
				errorMessage = addToErrorList(prefix + ".FirstName", errorMessage);
			}
	
			if(isStringNullOrNill(name.getLastName())){
				errorMessage = addToErrorList(prefix + ".LastName", errorMessage);
			}
		}else{
			errorMessage = addToErrorList(prefix , errorMessage);
		}
		
		return errorMessage;
	}

	public void validateAtNeedFinanceRequest(AtNeedFinanceRequest request) throws InvalidDataException {
		String errorMessage = "";
		
		// validate charges
		errorMessage = validateCharges(request.getCharges(), "charges",  errorMessage, false);
		
		// validate financialInfo.serviceType
		if(request.getFinancialInfo() != null){
			if(isStringNullOrNill(request.getFinancialInfo().getSalesType())){
				errorMessage = addToErrorList("financialInfo.salesType", errorMessage);
			}
		}else{
			errorMessage = addToErrorList("financialInfo", errorMessage);
		}
		
		errorMessage = validateDepositInfo(request.getDepositInfo(), "depositInfo", errorMessage, false);
		errorMessage = validateFinanceChargeOption(request.getFinanceChargeOption(), "financeChargeOption", errorMessage, false);
		
		if(errorMessage.length()>0){
			throw createException(errorMessage);
		}
	}

	private String validateCharges(ChargesType charges, String prefix, String errorMessage, boolean required){
		if(charges!= null){
			int i = 0;
			for(ChargeType charge : charges.getCharge()){
				i++;
				if(charge.getContractLineNo() == 0){
					errorMessage = addToErrorList(prefix + ".charge" + i + ".contractLineNo", errorMessage);
				}
				if(isStringNullOrNill(charge.getDescription())){
					errorMessage = addToErrorList(prefix + ".charge" + i + ".description", errorMessage);
				}
				if(isAmountZero(charge.getAmount())){
					errorMessage = addToErrorList(prefix + ".charge" + i + ".amount", errorMessage);
				}
				if(charge.getSequenceNo()== 0){
					errorMessage = addToErrorList(prefix + ".charge" + i + ".sequenceNo", errorMessage);
				}
				if(charge.getItem() != null){
					if(charge.getItem().getMerchandiseItem() != null && charge.getItem().getMerchandiseItem().getInvMasterKey() == 0){
						errorMessage = addToErrorList(prefix + ".charge" + i + ".item.invMasterKey", errorMessage);
					}else if(charge.getItem().getServiceItem()!= null && charge.getItem().getServiceItem().getId() == 0){
						errorMessage = addToErrorList(prefix + ".charge" + i + ".item.serviceItem_id", errorMessage);
					}
				}else{
					errorMessage = addToErrorList(prefix + ".charge" + i + ".item", errorMessage);
				}
			}
		}else if(required){
			errorMessage = addToErrorList(prefix , errorMessage);
		}
		return errorMessage;
	}
	private String validateDepositInfo(DepositInfoType depositInfo, String prefix, String errorMessage, boolean required){
		
		if(depositInfo != null){
			if(isAmountZero(depositInfo.getDepositAmount())){
				errorMessage = addToErrorList(prefix + ".depositAmount", errorMessage);
			}
			if(depositInfo.getDatePaid() == null){
				errorMessage = addToErrorList(prefix + ".datePaid", errorMessage);
			}
			if(isStringNullOrNill(depositInfo.getAdjustmentType())){
				errorMessage = addToErrorList(prefix + ".adjustmentType", errorMessage);
			}
			if(depositInfo.getDepositDate() == null){
				errorMessage = addToErrorList(prefix + ".depositDate", errorMessage);
			}
			if(isStringNullOrNill(depositInfo.getPaymentMethod())){
				errorMessage = addToErrorList(prefix + ".paymentMethod", errorMessage);
			}
		}else if(required){
			errorMessage = addToErrorList(prefix , errorMessage);
		}
		
		return errorMessage;
	}
	private String validateFinanceChargeOption(FinanceChargeOptionType financeChargeOption, 
			String prefix,String errorMessage, boolean required) {
		
		if(financeChargeOption != null){
			prefix += ".applyFinanceCharge";
			if(financeChargeOption.getApplyFinanceCharge().getMonthlyInterestRate()==null){
				errorMessage = addToErrorList(prefix + ".monthlyInterestRate", errorMessage);
			}
			if(financeChargeOption.getApplyFinanceCharge().getInterestFromDate() == null){
				errorMessage = addToErrorList(prefix + ".interestFromDate", errorMessage);
			}
		}else if(required){
			errorMessage = addToErrorList(prefix , errorMessage);
		}
		
		return errorMessage;
	}

	public void validateVitlsRequest(VitalsRequest vitalsRequest) throws InvalidDataException {
		String errorMessage = "";
		
		if(vitalsRequest.getGender() == null ){
			errorMessage = addToErrorList("gender" , errorMessage);
		}
		
		if(errorMessage.length()>0){
			throw createException(errorMessage);
		}
	}
	//*************** utility methods ***********************
	private boolean isAmountZero(MsAmountType amount){
		return amount == null || isStringNullOrNill(amount.getMsAmountType());
	}
	private boolean isStringNullOrNill(StrMax10Len str){
		return str == null || str.getStrMax10Len() == null || str.getStrMax10Len().length() == 0;
	}
	private boolean isStringNullOrNill(StrMax15Len str){
		return str == null || str.getStrMax15Len() == null || str.getStrMax15Len().length() == 0;
	}
	private boolean isStringNullOrNill(StrMax20Len str){
		return str == null || str.getStrMax20Len() == null || str.getStrMax20Len().length() == 0;
	}
	private boolean isStringNullOrNill(StrMax25Len str){
		return str == null || str.getStrMax25Len() == null || str.getStrMax25Len().length() == 0;
	}
	private boolean isStringNullOrNill(StrMax30Len str){
		return str == null || str.getStrMax30Len() == null || str.getStrMax30Len().length() == 0;
	}
	private boolean isStringNullOrNill(StrMax50Len str){
		return str == null || str.getStrMax50Len() == null || str.getStrMax50Len().length() == 0;
	}
	private boolean isStringNullOrNill(StrMax150Len str){
		return str == null || str.getStrMax150Len() == null || str.getStrMax150Len().length() == 0;
	}
	private boolean isStringNullOrNill(String string){
		return string == null || string.trim().length() == 0;
	}
	
	private String addToErrorList(String fieldName, String errList){
		if(errList.length() > 0){
			errList += ", ";
		}
		return errList + fieldName;
	}
	
	private InvalidDataException createException (String errorList){
		
		if(errorList != null){
			if(errorList.contains(",")){
				return new InvalidDataException(errorList + " fields are missing.");
			} else{
				return new InvalidDataException(errorList + " fields is missing.");
			}
		}
		return new InvalidDataException();
	
	}
}
