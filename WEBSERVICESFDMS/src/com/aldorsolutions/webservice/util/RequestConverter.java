package com.aldorsolutions.webservice.util;

import java.util.Date;

import com.aldorsolutions.fdms.to.common.*;
import com.aldorsolutions.fdms.to.fdmscase.*;
import com.aldorsolutions.fdms.to.financial.*;
import com.aldorsolutions.fdms.to.service.*;
import com.aldorsolutions.fdms.to.vitals.*;
import com.aldorsolutions.webservice.ConvertorUtil;
import com.aldorsolutions.webservice.xsd.comm.*;
import com.aldorsolutions.webservice.xsd.comm.fdms.*;
import com.aldorsolutions.webservice.xsd.fdms.info.InfoContextType;
import com.aldorsolutions.webservice.xsd.fdms.service.*;


public class RequestConverter {
	
	public static FdmsAtNeedRequest getFdmsAtNeedRequest(RequestContextType requestContext, AtNeedRequest atNeedrequest){
		
		FdmsAtNeedRequest fdmsAtNeedRequest = new FdmsAtNeedRequest();
		populateFdmsCaseRequest(requestContext, atNeedrequest, fdmsAtNeedRequest);
		
		fdmsAtNeedRequest.setFirstCall(getFirstCall(atNeedrequest.getFirstCall()));
		fdmsAtNeedRequest.setDeceased(getDeceasedPerson(atNeedrequest.getDeceased()));
		
		if(atNeedrequest.getInformant() != null){
			fdmsAtNeedRequest.setInformant(getInformant(atNeedrequest.getInformant(), fdmsAtNeedRequest.getDeceased()));
		}
		if(atNeedrequest.getNextOfKin() != null){
			fdmsAtNeedRequest.setNextOfKin(getNextOfKinType(atNeedrequest.getNextOfKin(), fdmsAtNeedRequest.getInformant()));
		}
		if(atNeedrequest.getExecutor()!= null){
			fdmsAtNeedRequest.setExecutor(getNextOfKinType(atNeedrequest.getExecutor(), fdmsAtNeedRequest.getInformant()));
		}
		
		String info = atNeedrequest.getShipAndAirlineInfo();
		info = info != null ? info:"";
		fdmsAtNeedRequest.setShippingInfo(info);
		
		info = atNeedrequest.getCallInfoNote();
		info = info != null ? info:"";
		fdmsAtNeedRequest.setCallInfoNote(info);
		return fdmsAtNeedRequest;
	}
	
	private static void populateFdmsCaseRequest(RequestContextType requestContext, CaseRequest caseRequest, FdmsCaseRequest fdmsCaseRequest){
		
		if(requestContext.getVitalsId()>0){
			fdmsCaseRequest.setVitalsId(requestContext.getVitalsId());
			fdmsCaseRequest.setLocaleId(requestContext.getLocaleId());
			if(requestContext.getLocationId()>0){
				fdmsCaseRequest.setLocationId(requestContext.getLocationId());
			}
			if(requestContext.getDirectorId()>0){
				fdmsCaseRequest.setDirectorId(requestContext.getDirectorId());
			}
		}else{
			fdmsCaseRequest.setLocaleId(requestContext.getLocaleId());
			fdmsCaseRequest.setLocationId(requestContext.getLocationId());
			fdmsCaseRequest.setDirectorId(requestContext.getDirectorId());
		}
		
		fdmsCaseRequest.setCaseInfo(getCaseInfo(caseRequest.getCaseInfo()));
	}

	private static RelatedPerson getNextOfKinType(NextOfKinTypePerson nextOfKin,
			RelatedPerson informant) {
		if(nextOfKin.getSameAsInformant()){
			return informant;
		}
		return getRelatedPerson(nextOfKin, true);
	}

	private static RelatedPerson getInformant(InformantPerson informant,
			DeceasedPerson deceased) {
		
		RelatedPerson person = getRelatedPerson(informant, !informant.getUseDecessedAddr());
		if(person == null){
			person = new RelatedPerson();
		}
		if(informant.getUseDecessedAddr()){
			person.setAddress(deceased.getPlaceOfDeathAddress());
		}
		person.setBillToParty(informant.getBillToParty());
		return person;
	}
	
	private static RelatedPerson getRelatedPerson(MsRelatedPersonType related, boolean setAddress){
		
		RelatedPerson person = null;
		
		if(related != null){
			person = new RelatedPerson();
			boolean noData = true;
			
			noData = !populatePerson(related, person);
			
			if(related.getContact()!= null){
				boolean setStatus = setPersonContact(related.getContact(), person);
				if(noData){
					noData = !setStatus;
				}
			}
			
			person.setRelation(ConvertorUtil.getInstance().getString(related.getRelation()));
			noData = noData ? person.getRelation()==null : false;
			
			if(noData){
				person = null;
			}
		}
		return person;		
	}
	
	private static boolean populatePerson(MsRelatedPersonType related, Person person){
		boolean noData = true;
		
		person.setName(getFdmsName(related.getName()));
		noData = noData ? person.getName() == null : false;
		
		person.setAddress(getAddress(related.getAddress()));
		noData = noData ? person.getAddress() == null : false;
		
		return !noData;
	}
	
	private static boolean setPersonContact(MsContactType contact, RelatedPerson person) {
		boolean notSet = true;
		
		if(contact != null){
			ConvertorUtil util = ConvertorUtil.getInstance();
			FdmsConvertorUtil fdmsUtil = FdmsConvertorUtil.getInstance();
			
			person.setCellPhone(fdmsUtil.getPhoneNumberString(contact.getCellPhone()));
			notSet = notSet ? person.getCellPhone() == null : false;
			
			person.setEmail(util.getString(contact.getEmail()));
			notSet = notSet ? person.getEmail() == null : false;
			
			if(contact.getPhone() != null){
				String phone = fdmsUtil.getPhoneNumberString(contact.getPhone());
				if(phone.length()>0){
					person.addPhone(phone);
					notSet = false;
				}
			}
		}
		return !notSet;
	}

	private static CaseInfo getCaseInfo(CaseInfoType caseInfo) {
		
		CaseInfo fdmsCaseInfo = null;
		if(caseInfo != null){
			fdmsCaseInfo = new CaseInfo();
			ConvertorUtil util = ConvertorUtil.getInstance();
			boolean noData = true;
			if(caseInfo.getDecedentVitalsId()>0){
				fdmsCaseInfo.setDecedentVitalsId(caseInfo.getDecedentVitalsId());
				noData = false;
			}
			
			fdmsCaseInfo.setDecedentCaseNumber(util.getString(caseInfo.getDecedentCaseNumber()));
			noData = noData ? fdmsCaseInfo.getDecedentCaseNumber() == null : false;
			
			fdmsCaseInfo.setDecedentContractNumber(util.getString(caseInfo.getDecedentContractNumber()));
			noData = noData ? fdmsCaseInfo.getDecedentContractNumber() == null : false;
			
			if(noData){
				fdmsCaseInfo = null;
			}
			
		}
		return fdmsCaseInfo;
	}

	private static DeceasedPerson getDeceasedPerson(DeceasedType deceased) {
		
		DeceasedPerson deceasedPerson = new DeceasedPerson();
		ConvertorUtil util = ConvertorUtil.getInstance();
		deceasedPerson.setName(getFdmsCompleteName(deceased.getName()));
		deceasedPerson.setMemorialName(util.getString(deceased.getMemorialName()));
		deceasedPerson.setPlaceOfDeath(util.getString(deceased.getPlaceOfDeath()));
		if(deceased.getPlaceOfDeathAddress() != null){
			deceasedPerson.setPlaceOfDeathAddress(getAddress(deceased.getPlaceOfDeathAddress()));
		}
		
		deceasedPerson.setLocDeathLicenseNumber(util.getString(deceased.getLocDeathLicenseNumber()));
		
		deceasedPerson.setBirthDate(deceased.getBirthDate());
		deceasedPerson.setDeathDate(deceased.getDeathDate());
		deceasedPerson.setServiceDate(deceased.getServiceDate());
		deceasedPerson.setDispositionDate(deceased.getDispositionDate());
		if(deceased.getAgeYears()>0){
			deceasedPerson.setAgeYears(deceased.getAgeYears());
		}
		return deceasedPerson;
	}
	
	private static Name getFdmsCompleteName(MsCompleteNameType name){
		
		Name fdmsName = null;
		
		if(name != null){
			
			fdmsName = getFdmsName(name);
			boolean noData = (fdmsName == null);
			if(noData){
				fdmsName = new Name();
			}
			
			ConvertorUtil util = ConvertorUtil.getInstance();
			
			fdmsName.setSuffix(util.getString(name.getSuffix()));
			noData = noData ? fdmsName.getSuffix() == null : false;
			
			fdmsName.setMaidenName(util.getString(name.getMiddleName()));
			noData = noData ? fdmsName.getMaidenName() == null : false;
			
			fdmsName.setPrefix(util.getString(name.getPrefix()));
			noData = noData ? fdmsName.getPrefix() == null : false;
			
			if(noData){
				fdmsName = null;
			}
		}
		return fdmsName;
	}
	
	private static Name getFdmsName(MsNameType name){
		Name fdmsName = null;
		
		if(name != null){
			fdmsName = new Name();
			boolean noData = true;
			ConvertorUtil util = ConvertorUtil.getInstance();
			
			fdmsName.setSalutation(util.getString(name.getSalutation()));
			noData = noData ? fdmsName.getSalutation() == null : false;
			
			fdmsName.setFirstName(util.getString(name.getFirstName()));
			noData = noData ? fdmsName.getFirstName() == null : false;
			
			fdmsName.setMiddleName(util.getString(name.getMiddleName()));
			noData = noData ? fdmsName.getMiddleName() == null : false;
			
			fdmsName.setLastName(util.getString(name.getLastName()));
			noData = noData ? fdmsName.getLastName() == null : false;
			
			if(noData){
				fdmsName = null;
			}
		}
		return fdmsName;
	}

	private static Address getAddress(MsStandardAddressType standardAddress) {
		Address address = null;
		
		if(standardAddress != null){
			address = new Address();
			ConvertorUtil util = ConvertorUtil.getInstance();
			FdmsConvertorUtil fdmsUtil = FdmsConvertorUtil.getInstance();
			boolean noData = true;
			
			String street = util.getString(standardAddress.getStreet());
			if(street != null){
				address.addStreet(street);
				noData = false;
			}
			
			address.setCity(util.getString(standardAddress.getCity()));
			noData = noData ? address.getCity() == null : false;
			
			address.setStateCode(util.getString(standardAddress.getStateAbbreviation()));
			noData = noData ? address.getStateCode() == null : false;
			
			address.setZip(fdmsUtil.getString(standardAddress.getZipCode()));
			noData = noData ? address.getZip() == null : false;
			
			address.setCounty(util.getString(standardAddress.getCounty()));
			noData = noData ? address.getCounty() == null : false;
			
			address.setCountry(util.getString(standardAddress.getCountry()));
			noData = noData ? address.getCountry() == null : false;
			
			if(noData){
				address = null;
			}
		}
		return address;
	}

	private static Address getAddress(MsExtendedAddressType address) {
		
		Address contactAddress = null;
		if(address != null){
			contactAddress = new Address();
			ConvertorUtil util = ConvertorUtil.getInstance();
			boolean noData = true;
			
			if(address.getStreet()!=null){
				String streetName = "";
				for(StrMax30Len street : address.getStreet()){
					streetName = util.getString(street);
					if(streetName != null){
						contactAddress.addStreet(streetName);
						noData = false;
					}
				}
			}
			
			contactAddress.setCity(util.getString(address.getCity()));
			noData = noData ? contactAddress.getCity() == null : false;
			
			FdmsConvertorUtil fdmsUtil = FdmsConvertorUtil.getInstance();
			contactAddress.setStateCode(util.getString(address.getStateAbbreviation()));
			noData = noData ? contactAddress.getStateCode() == null : false;
			
			contactAddress.setZip(fdmsUtil.getString(address.getZipCode()));
			noData = noData ? contactAddress.getZip() == null : false;
			
			if(noData){
				address = null;
			}
		}
		return contactAddress;
	}

	private static FirstCall getFirstCall(FirstCallType firstCall) {
		
		FirstCall fdmsFirstCall = new FirstCall();
		ConvertorUtil util = ConvertorUtil.getInstance();
		
		fdmsFirstCall.setArrangeDate(firstCall.getArrangeDate());
		
		if(firstCall.getArrangeTime()!=null){
			fdmsFirstCall.setArrangeTime(FdmsConvertorUtil.getInstance().getTimeString(firstCall.getArrangeTime()));
		}
		
		fdmsFirstCall.setFuneralHome(getFdmsLocation(firstCall.getFuneralHome()));
		
		if(firstCall.getFacility() != null){
			fdmsFirstCall.setFacility(getFacility(firstCall.getFacility(), fdmsFirstCall.getFuneralHome()));
		}
		
		fdmsFirstCall.setOriginalPreneedDate(firstCall.getOriginalPreneedDate());
		
		fdmsFirstCall.setSource(util.getString(firstCall.getSource()));
		fdmsFirstCall.setEmbalming(util.getString(firstCall.getEmbalming()));
		
		return fdmsFirstCall;
	}

	private static FdmsLocation getFacility(FaciltyType facility, FdmsLocation funeralHome) {
		if(facility.getSameAsFuneralHome()!=null){
			return funeralHome;
		}
		return getFdmsLocation(facility.getLocation());
	}

	private static FdmsArranger getFdmsArranger(Arranger director) {
		FdmsArranger fdmsArranger = null;
		
		if(director != null){
			fdmsArranger = new FdmsArranger();
			boolean noData = true;
			
			if(director.getId()>0){
				fdmsArranger.setId(director.getId());
				noData =  false;
			}
			
			
			ConvertorUtil util = ConvertorUtil.getInstance();
			fdmsArranger.setName(util.getString(director.getName()));
			noData = noData ? fdmsArranger.getName() == null :false;
			
			fdmsArranger.setLicenseNumber(util.getString(director.getLicenseNumber()));
			noData = noData ? fdmsArranger.getLicenseNumber() == null :false;
			
			fdmsArranger.setPnLicenseNumber(util.getString(director.getPnLicenceNumber()));
			noData = noData ? fdmsArranger.getPnLicenseNumber() == null :false;
			
			fdmsArranger.setBurialLicenseNumber(util.getString(director.getBurialLicenceNumber()));
			noData = noData ? fdmsArranger.getBurialLicenseNumber() == null :false;
			
			fdmsArranger.setEmbalmerLicenseNumber(util.getString(director.getEmbalmerLicenceNumber()));
			noData = noData ? fdmsArranger.getEmbalmerLicenseNumber() == null :false;
			
			fdmsArranger.setCounselor(util.getString(director.getIsCounselor()));
			noData = noData ? fdmsArranger.isCounselor()==null :false;
			if(noData){
				fdmsArranger = null;
			}
		}
		return fdmsArranger;
	}

	private static FdmsLocation getFdmsLocation(Location location) {
		
		FdmsLocation fdmsLocation = null;
		if(location != null){
			fdmsLocation = new FdmsLocation();
			ConvertorUtil util = ConvertorUtil.getInstance();
			boolean noData = true;
			
			if(location.getId()>0){
				fdmsLocation.setId(location.getId());
				noData =  false;
			}
			
			fdmsLocation.setName(util.getString(location.getName()));
			noData = noData ? fdmsLocation.getName() == null : false;
			
			fdmsLocation.setAddress(getAddress(location.getAddress()));
			noData = noData ? fdmsLocation.getAddress() == null : false;
			
			String phone = FdmsConvertorUtil.getInstance().getPhoneNumberString(location.getPhone());
			if(phone.length()>0){
				fdmsLocation.addPhone(phone);
				noData = false;
			}
			
			fdmsLocation.setLicense(util.getString(location.getLicense()));
			noData = noData ? fdmsLocation.getLicense() == null : false;
			
			if(noData){
				fdmsLocation = null;
			}
			
		}
		return fdmsLocation;
	}

	public static FdmsPreNeedRequest getFdmsPreNeedRequest(	RequestContextType requestContext, PreNeedRequest preNeedRequest) {
		
		FdmsPreNeedRequest fdmsPreNeedRequest = new FdmsPreNeedRequest(); 
		populateFdmsCaseRequest(requestContext, preNeedRequest, fdmsPreNeedRequest);
		
		fdmsPreNeedRequest.setBuyer(getBuyer(preNeedRequest.getBuyer()));
		ConvertorUtil util = ConvertorUtil.getInstance();
		fdmsPreNeedRequest.setCoBuyer(
				util.getString(preNeedRequest.getBuyer().getCoBuyer()));
		fdmsPreNeedRequest.setInsured(getInsured(preNeedRequest.getInsuredInfo()));
		fdmsPreNeedRequest.setSalesInfo(getSalesInfo(preNeedRequest.getSalesInfo()));
		fdmsPreNeedRequest.setFundDepository(getFundDepository(preNeedRequest.getFundDepository()));
		
		fdmsPreNeedRequest.setSource(
				ConvertorUtil.getInstance().getString(
						preNeedRequest.getSource()));
		
		if(preNeedRequest.getNotes() != null){
			fdmsPreNeedRequest.setNotes(preNeedRequest.getNotes());
		}
		
		return fdmsPreNeedRequest;
	}

	private static RelatedPerson getBuyer(BuyerInfoType buyer) {
		
		// person always exists, as buyer firstName and lastName are required
		RelatedPerson person = getRelatedPerson(buyer, true);
		
		person.setSSN(FdmsConvertorUtil.getInstance().getString(buyer.getSSN()));
		
		return person;
	}

	private static Person getInsured(InsuredInfoType insuredInfo) {
		
		// person always exists as insuredInfo firstName and lastName are required
		Person person = new Person();
		
		person.setName(getFdmsName(insuredInfo.getName()));
		person.setAddress(getAddress(insuredInfo.getAddress()));
		person.setSSN(FdmsConvertorUtil.getInstance().getString(insuredInfo.getSSN()));
		person.setBirthDate(insuredInfo.getDathOfBirth());
			
		return person;
	}

	private static SalesInfo getSalesInfo(SalesInfoType salesInfoType) {
		
		// salesInfo always exists as saleDate is required
		SalesInfo salesInfo = new SalesInfo();
		
		salesInfo.setSaleDate(salesInfoType.getSaleDate());
		
		ConvertorUtil util = ConvertorUtil.getInstance();
		salesInfo.setServices(util.getString(salesInfoType.getService()));
		salesInfo.setCasket(util.getString(salesInfoType.getCasket()));
		salesInfo.setVault(util.getString(salesInfoType.getVault()));
		salesInfo.setUrn(util.getString(salesInfoType.getUrn()));
		
		FdmsConvertorUtil fdmsUtil = FdmsConvertorUtil.getInstance();
		salesInfo.setServiceSale(fdmsUtil.getAmountObject(salesInfoType.getServiceSaleAmount()));
		salesInfo.setCasketSale(fdmsUtil.getAmountObject(salesInfoType.getCasketSaleAmount()));
		salesInfo.setVaultSale(fdmsUtil.getAmountObject(salesInfoType.getVaultSaleAmount()));
		salesInfo.setUrnSale(fdmsUtil.getAmountObject(salesInfoType.getUrnSaleAmount()));
		salesInfo.setOther(fdmsUtil.getAmountObject(salesInfoType.getOtherSaleAmount()));
		salesInfo.setGstAmount(fdmsUtil.getAmountObject(salesInfoType.getGSTAmount()));
		
		if(salesInfoType.getDisbursements() != null){
			
			for(DisbursementType disbursement : salesInfoType.getDisbursements()){
				
				String disub = util.getString(disbursement.getDisbursement());
				float amount = fdmsUtil.getAmount(disbursement.getAmount());
				
				if(disub.length()>0 || amount > 0.00f){
					salesInfo.addDisbursement(disub, amount);
				}
			}
		}
		
		return salesInfo;
	}

	private static FundDepository getFundDepository( FundDepositoryType fundDepository) {
		
		FundDepository depository = null;
		
		if(fundDepository != null){
			
			depository = new FundDepository();
			ConvertorUtil util = ConvertorUtil.getInstance();
			boolean noData = true;
			
			depository.setFundType(util.getString(fundDepository.getFundType()));
			noData = noData ? depository.getFundType() == null : false;
			
			depository.setFundsWith(util.getString(fundDepository.getFundWith()));
			noData = noData ? depository.getFundsWith() == null : false;
			
			depository.setStartedDate(fundDepository.getStartedDate());
			noData = noData ? depository.getStartedDate() == null : false;
			
			depository.setMaturityDate(fundDepository.getMaturityDate());
			noData = noData ? depository.getMaturityDate() == null : false;
			
			depository.setAccount(util.getString(fundDepository.getAccountNumber()));
			noData = noData ? depository.getAccount() == null : false;
			
			FdmsConvertorUtil fdmsUtil = FdmsConvertorUtil.getInstance();
			depository.setEstIntRate(fdmsUtil.getAmountObject(fundDepository.getEstimateInterestRate()));
			noData = noData ? depository.getEstIntRate()== null : false;
			
			depository.setFaceValue(fdmsUtil.getAmountObject(fundDepository.getFaceValue()));
			noData = noData ? depository.getFaceValue()== null : false;
			
			depository.setContractAmount(fdmsUtil.getAmountObject(fundDepository.getContractAmount()));
			noData = noData ? depository.getContractAmount()== null : false;
			
			depository.setYTDPaid(fdmsUtil.getAmountObject(fundDepository.getYTDPaid()));
			noData = noData ? depository.getYTDPaid()== null : false;
			
			depository.setTotalPaid(fdmsUtil.getAmountObject(fundDepository.getTotalPaid()));
			noData = noData ? depository.getTotalPaid()== null : false;
			
			depository.setYTDInterest(fdmsUtil.getAmountObject(fundDepository.getYTDInterest()));
			noData = noData ? depository.getYTDInterest()== null : false;
			
			depository.setManagementFee(fdmsUtil.getAmountObject(fundDepository.getManagementFee()));
			noData = noData ? depository.getManagementFee()== null : false;
			
			depository.setCommission(fdmsUtil.getAmountObject(fundDepository.getCommission()));
			noData = noData ? depository.getCommission()== null : false;

			depository.setLastPaymentDate(fundDepository.getLastPaymentDate());
			noData = noData ? depository.getLastPaymentDate() == null : false;
			
			depository.setLastPaymentAmount(fdmsUtil.getAmountObject(fundDepository.getLastPaymentAmount()));
			noData = noData ? depository.getLastPaymentAmount()== null : false;
			
			if(noData){
				depository = null;
			}
			
		}
		
		return depository;
	}

	public static FdmsAtNeedFinanceRequest getFdmsAtNeedFinanceRequest(	RequestContextType requestContext, AtNeedFinanceRequest atNeedFinanceRequest) {
		
		FdmsAtNeedFinanceRequest request = new FdmsAtNeedFinanceRequest();
		
		request.setVitalsId(requestContext.getVitalsId());
		
		if(atNeedFinanceRequest.getCharges()!= null){
			for(ChargeType charge: atNeedFinanceRequest.getCharges().getCharge()){
				request.addCharge(getCharge(charge));
			}
		}
		request.setFinancialInfo(getFinancialInfo(atNeedFinanceRequest.getFinancialInfo()));
		request.setDepositInfo(getDepositInfo(atNeedFinanceRequest.getDepositInfo()));
		request.setFinanceChargeOption(getFinanceChargeOption(atNeedFinanceRequest.getFinanceChargeOption()));
		request.setSendChargesToAccounting(ConvertorUtil.getInstance().getString(atNeedFinanceRequest.getSendChargesToAccounting()));
		return request;
	}
	
	private static Charge getCharge(ChargeType chargeType){
		
		Charge charge = null;
		
		if(chargeType != null){
			
			charge = new Charge();
			ConvertorUtil util = ConvertorUtil.getInstance();
			
			charge.setContractLineNo(chargeType.getContractLineNo());
			charge.setDescription(util.getString(chargeType.getDescription()));
			charge.setAmount(FdmsConvertorUtil.getInstance().getAmount(chargeType.getAmount()));
			charge.setTaxCode(util.getString(chargeType.getTaxCode()));
			charge.setSequenceNo(chargeType.getSequenceNo());
			charge.setFromPackage(chargeType.getFromPackage());
			if(chargeType.getItem().getMerchandiseItem()!= null){
				charge.setMarchandiseItem(getMarchandiseItem(chargeType.getItem().getMerchandiseItem()));
			}else{
				charge.setServiceItemId(
						new Integer(
								chargeType.getItem().getServiceItem().getId()));
			}
			
		}
		return charge;
	}

	private static InventoryItem getMarchandiseItem(InventoryItemType merchandiseItem) {
		InventoryItem invItem = null;
		if(merchandiseItem != null){
			invItem = new InventoryItem();
			invItem.setInvMasterKey(merchandiseItem.getInvMasterKey());
			invItem.setInvOnHandId(merchandiseItem.getInvOnHandId());
			
			invItem.setSerialNo(
					ConvertorUtil.getInstance().getString(
							merchandiseItem.getSerialNo()));
		}
		return invItem;
	}

	private static FinancialInfo getFinancialInfo(FinancialInfoType financialInfoType) {
		
		FinancialInfo financialInfo = null;
		
		if(financialInfoType != null){
			
			financialInfo = new FinancialInfo();
			ConvertorUtil util = ConvertorUtil.getInstance();
			
			financialInfo.setInvoiceDate(financialInfoType.getInvoiceDate());
			financialInfo.setDueDate(financialInfoType.getDueDate());
			financialInfo.setLastStatementDate(financialInfoType.getLastStatementDate());
			
			financialInfo.setSalesType(util.getString(financialInfoType.getSalesType()));
			financialInfo.setServiceType(util.getString(financialInfoType.getServiceType()));
			financialInfo.setSource(util.getString(financialInfoType.getSource()));
			
			financialInfo.setDisposition(util.getString(financialInfoType.getDisposition()));
			financialInfo.setServicePlan(util.getString(financialInfoType.getServicePlan()));
			
			financialInfo.setHaveWeEverProvidedServicesForThisFamily(
					util.getString(financialInfoType.getHaveWeEverProvidedServicesForThisFamily()));
			
			financialInfo.setWhatFuneralHomeDidFamilyPreviouslyUse(
					util.getString(
							financialInfoType.getWhatFuneralHomeDidFamilyPreviouslyUse()));
			
			financialInfo.setHowDidFamilyHearAboutUs(
					util.getString(
							financialInfoType.getHowDidFamilyHearAboutUs()));
			
			financialInfo.setMiscNotes(util.getString(financialInfoType.getMiscNotes()));
		}
		
		return financialInfo;
	}

	private static DepositInfo getDepositInfo(DepositInfoType depositInfoType) {
		
		DepositInfo depositInfo = null;
		
		if(depositInfoType != null){
			
			depositInfo = new DepositInfo();
			ConvertorUtil util = ConvertorUtil.getInstance();
			
			depositInfo.setDepositAmount(FdmsConvertorUtil.getInstance().getAmount(depositInfoType.getDepositAmount()));
			depositInfo.setDatePaid(depositInfoType.getDatePaid());
			depositInfo.setAdjustmentType(util.getString(depositInfoType.getAdjustmentType()));
			
			depositInfo.setPaymentSource(util.getString(depositInfoType.getPaymentSource()));
			depositInfo.setDepositDate(depositInfoType.getDepositDate());
			depositInfo.setManualReceiptNo(util.getString(depositInfoType.getManualReceiptno()));
			depositInfo.setPaymentMethod(util.getString(depositInfoType.getPaymentMethod()));
		}
		
		return depositInfo;
	}
	
	private static FinanceChargeOption getFinanceChargeOption(FinanceChargeOptionType financeChargeOptionType) {
		
		FinanceChargeOption financeChargeOption = null;
		
		if(financeChargeOptionType != null){
			
			financeChargeOption = new FinanceChargeOption();
			
			financeChargeOption.setMonthlyInterestRate(
					FdmsConvertorUtil.getInstance().getAmount(
							financeChargeOptionType.getApplyFinanceCharge().getMonthlyInterestRate()));
			
			financeChargeOption.setInterestFromDate(
					financeChargeOptionType.getApplyFinanceCharge().getInterestFromDate());
		}
		
		return financeChargeOption;
	}

	public static FdmsVitalsRequest getFdmsVitalsRequest(RequestContextType requestContext, VitalsRequest vitalsRequest) {
		
		FdmsVitalsRequest fdmsVitalRequest = new FdmsVitalsRequest();
		ConvertorUtil util = ConvertorUtil.getInstance();
		
		fdmsVitalRequest.setVitalsid(requestContext.getVitalsId());
		fdmsVitalRequest.setIdTagNumber(util.getString(vitalsRequest.getIdTagNumber()));
		fdmsVitalRequest.setDeceased(getDeceasedPerson(vitalsRequest));
		
		if(vitalsRequest.getGeneralHistory() != null 
				&& vitalsRequest.getGeneralHistory().getDecedentsEducation()!=null){
			
			fdmsVitalRequest.setDecedentsEducation(
					getDecedentsEducation(
							vitalsRequest.getGeneralHistory().getDecedentsEducation()));
			
		}
		
		if(vitalsRequest.getEthnicity() != null){
			fdmsVitalRequest.setEthnicity(
					getEthnicity(vitalsRequest.getEthnicity()));
		}
		
		if(vitalsRequest.getOccupationalHistory() != null){
			fdmsVitalRequest.setOccupationalHistory(
					getOccupationalHistory(vitalsRequest.getOccupationalHistory()));
		}
		
		if(vitalsRequest.getSurvivingSpouseName() != null){
			fdmsVitalRequest.setSurvivingSpouseName(
					getFdmsName(vitalsRequest.getSurvivingSpouseName())); 
		}
		
		if(vitalsRequest.getFather() != null){
			fdmsVitalRequest.setFather(
					getPerson(vitalsRequest.getFather()));
		}
		
		if(vitalsRequest.getMother() != null ){
			fdmsVitalRequest.setMother(
					getPerson(vitalsRequest.getMother()));
		}
		
		if(vitalsRequest.getInformantInfo() != null){
			fdmsVitalRequest.setInformantInfo(
					getRelatedPerson(vitalsRequest.getInformantInfo(), true));
		}
		
		if(vitalsRequest.getDispositionInfo() != null){
			fdmsVitalRequest.setDispositionInfo(
					getDispositionInfo(vitalsRequest.getDispositionInfo())); 
		}
		
		if(vitalsRequest.getCertification() != null){
			fdmsVitalRequest.setCertificationInfo(
					getCertificationInfo(vitalsRequest.getCertification())); 
		}
		
		if(vitalsRequest.getCauseOfDeath() != null){
			fdmsVitalRequest.setCauseOfDeath(getCauseOfDeath(vitalsRequest.getCauseOfDeath())); 
		}
		
		if(vitalsRequest.getMedicalExaminer() != null){
			fdmsVitalRequest.setInjuryReport(getMedicalExaminerReport(vitalsRequest.getMedicalExaminer())); 
		}
		
		return fdmsVitalRequest;
	}

	private static DeceasedPerson getDeceasedPerson(VitalsRequest vitalsRequest){
		DeceasedPerson deceasedPerson = new DeceasedPerson();
		ConvertorUtil util = ConvertorUtil.getInstance();
		
		deceasedPerson.setName(
				getFdmsCompleteName(vitalsRequest.getDeceasedName()));
		
		deceasedPerson.setOtherName(
				getFdmsCompleteName(vitalsRequest.getOtherName()));
		
		deceasedPerson.setGender(util.getString(vitalsRequest.getGender()));
		deceasedPerson.setBirthDate(vitalsRequest.getDateOfBirth());
		deceasedPerson.setDeathDate(vitalsRequest.getDateOfDeath());
		deceasedPerson.setMaritalStatus(util.getString(vitalsRequest.getMaritalStatus()));
		
		if(vitalsRequest.getAge() != null){
			AgeType age = vitalsRequest.getAge();
			
			deceasedPerson.setAgeYears(age.getYear());
			deceasedPerson.setAgeMonths(age.getMonth());
			deceasedPerson.setAgeDays(age.getDay());
			deceasedPerson.setAgeHrs(age.getHour());
			deceasedPerson.setAgeMinutes(age.getMinutes());
		}
		
		if(vitalsRequest.getLocationOfDeath() != null){
			LocationOfDeathType locationOfDeath = vitalsRequest.getLocationOfDeath();
			
			deceasedPerson.setPlaceOfDeath(util.getString(locationOfDeath.getName()));
			deceasedPerson.setLocDeathLicenseNumber(util.getString(locationOfDeath.getLicenseNumber()));
			deceasedPerson.setLocalityOfDeath(util.getString(locationOfDeath.getLocality()));
			deceasedPerson.setPlaceOfDeathAddress(getAddress(locationOfDeath.getAddress()));
		}
		
		if(vitalsRequest.getCurrentResidence() != null){
			MsResidenceType residence = vitalsRequest.getCurrentResidence();
			
			deceasedPerson.setAddress(getAddress(residence));
			deceasedPerson.setLocalityOfResidence(util.getString(residence.getLocality()));
			deceasedPerson.setResidenceLengthOfTime(util.getString(residence.getResidenceLengthOfTime()));
		}
		
		if(vitalsRequest.getGeneralHistory() != null){
			GeneralHistoryType generalHistory = vitalsRequest.getGeneralHistory();
			
			deceasedPerson.setBirthPlace(getAddress(generalHistory.getBirthplace()));
			deceasedPerson.setSSN(FdmsConvertorUtil.getInstance().getString(generalHistory.getSSN()));
		}
		
		return deceasedPerson;
	}
	
	private static Education getDecedentsEducation(DecedentsEducationType decedentsEducation) {
		Education education = null;
		
		if(decedentsEducation != null){
			education = new Education();
			ConvertorUtil util = ConvertorUtil.getInstance();
			boolean noData = true;
			
			education.setHighestGradeCompleted(util.getString(decedentsEducation.getHighestGradeCompleted()));
			noData = noData ? education.getHighestGradeCompleted() == null : false;
			
			education.setYearsOfElementaryOrSecondary(util.getString(decedentsEducation.getYearsOfElementaryOrSecondary()));
			noData = noData ? education.getYearsOfElementaryOrSecondary() == null : false;
			
			education.setYearsOfColege(util.getString(decedentsEducation.getYearsOfColege()));
			noData = noData ? education.getYearsOfColege() == null : false;
			
			if(noData){
				education = null;
			}
		}
		
		return education;
	}

	private static Ethnicity getEthnicity(EthnicityType ethnicityType) {
		Ethnicity ethnicity = null;
		
		if(ethnicityType != null){
			ethnicity = new Ethnicity();
			ConvertorUtil util = ConvertorUtil.getInstance();
			boolean noData = true;
			
			ethnicity.setRace(util.getString(ethnicityType.getRace()));
			noData = noData ? ethnicity.getRace() == null : false;
			
			ethnicity.setAncestry(util.getString(ethnicityType.getAncestry()));
			noData = noData ? ethnicity.getAncestry() == null : false;
			
			if(ethnicityType.getTribal() != null){
				TribalType tribe = ethnicityType.getTribal();
				String member = util.getString(tribe.getMember());
				String name = util.getString(tribe.getName());
				if(member.length()>0 || name.length()>0){
					ethnicity.setTribal(member, name);
					noData = false;
				}
			}
			
			ethnicity.setHispanicOrigin(util.getString(ethnicityType.getHispanicOrigin()));
			noData = noData ? ethnicity.isHispanicOrigin()==null : false;
			
			ethnicity.setCitizenship(util.getString(ethnicityType.getCitizenship()));
			noData = noData ? ethnicity.getCitizenship() == null : false;
			
			if(noData){
				ethnicity = null;
			}
		}		
		return ethnicity;
	}

	private static OccupationalHistory getOccupationalHistory(
			OccupationalHistoryType occupationalHistoryType) {
		OccupationalHistory occupationalHistory = null;
		
		if(occupationalHistoryType != null){
			occupationalHistory = new OccupationalHistory();
			boolean noData = true;
			ConvertorUtil util = ConvertorUtil.getInstance();
			occupationalHistory.setWasDecedentEverInUSArmedForces(util.getString(
					occupationalHistoryType.getWasDecedentEverInUSArmedForces()));
			noData = noData ? occupationalHistory.wasDecedentEverInUSArmedForces()==null : false;
			
			occupationalHistory.setArmedForcesEntryDate(
					occupationalHistoryType.getArmedForcesEntryDate());
			noData = noData ? occupationalHistory.getArmedForcesEntryDate()==null : false;
			
			occupationalHistory.setArmedForcesDepartureDate(
					occupationalHistoryType.getArmedForcesDepartureDate());
			noData = noData ? occupationalHistory.getArmedForcesDepartureDate()==null : false;
			
			
			occupationalHistory.setUsualOccupation(util.getString(
					occupationalHistoryType.getUsualOccupation()));
			noData = noData ? occupationalHistory.getUsualOccupation() == null : false;
			
			occupationalHistory.setKindOfBusinessOrIndustry(
					util.getString(occupationalHistoryType.getKindOfBusinessOrIndustry()));
			noData = noData ? occupationalHistory.getKindOfBusinessOrIndustry() == null : false;
			
			occupationalHistory.setCurrentEmployer(
					util.getString(occupationalHistoryType.getCurrentEmployer()));
			noData = noData ? occupationalHistory.getCurrentEmployer() == null : false;
			
			if(noData){
				occupationalHistory = null;
			}
		}
		
		return occupationalHistory;
	}

	private static Person getPerson(MsParentType parent) {
		Person person = null;
		
		if(parent != null){
			person = new Person();
			boolean noData = true;
			
			person.setName(getFdmsName(parent.getName()));
			noData = noData ? person.getName() == null : false;
			
			person.setBirthPlace(getAddress(parent.getBirthPlace()));
			noData = noData ? person.getBirthPlace() == null : false;
			
			if(noData){
				person = null;
			}
		}
		
		return person;
	}

	private static DispositionInfo getDispositionInfo(
			DispositionInfoType dispositionInfo) {
		
		DispositionInfo fdmsDisposition = null;
		
		if (dispositionInfo != null){
			fdmsDisposition = new DispositionInfo();
			ConvertorUtil util = ConvertorUtil.getInstance();
			boolean noData = true;
			
			if (dispositionInfo.getFacilityInfo() != null){
				
				FacilityInfoType facilityInfo = new FacilityInfoType();
				facilityInfo = dispositionInfo.getFacilityInfo();
				
				fdmsDisposition.setDateOfNotifyToDirector(facilityInfo.getDateOfNotifyToDirector());
				noData = noData ? fdmsDisposition.getDateOfNotifyToDirector() == null : false;
				
				fdmsDisposition.setTimeOfNotifyToDirector(FdmsConvertorUtil.getInstance().getTimeString(facilityInfo.getTimeOfNotifyToDirector()));
				noData = noData ? fdmsDisposition.getTimeOfNotifyToDirector() == null : false;

				fdmsDisposition.setFacility(getFdmsLocation(facilityInfo.getFacility()));
				noData = noData ? fdmsDisposition.getFacility()== null : false;
				
				fdmsDisposition.setDirector(getFdmsArranger(facilityInfo.getDirector()));
				noData = noData ? fdmsDisposition.getDirector() == null : false;
				
				fdmsDisposition.setEmbalmer(getFdmsArranger(facilityInfo.getEmbalmer()));
				noData = noData ? fdmsDisposition.getEmbalmer() == null : false;
				
			}
			
			fdmsDisposition.setMethodOfDisposition(util.getString(dispositionInfo.getMethodOfDisposition()));
			noData = noData ? fdmsDisposition.getMethodOfDisposition() == null : false;
			
			fdmsDisposition.setDispositionPlace(util.getString(dispositionInfo.getDispositionPlace()));
			noData = noData ? fdmsDisposition.getDispositionPlace() == null : false;
			
			fdmsDisposition.setDispositionPlaceType(util.getString(dispositionInfo.getDispositionPlaceType()));
			noData = noData ? fdmsDisposition.getDispositionPlaceType() == null : false;
			
			fdmsDisposition.setLocation(getAddress(dispositionInfo.getLocation()));
			noData = noData ? fdmsDisposition.getLocation() == null : false;
			
			fdmsDisposition.setDateEmbalmed(dispositionInfo.getDateEmbalmed());
			noData = noData ? fdmsDisposition.getDateEmbalmed() == null : false;
			
			fdmsDisposition.setDateOfDisposition(dispositionInfo.getDateOfDisposition());
			noData = noData ? fdmsDisposition.getDateOfDisposition() == null : false;
			
			fdmsDisposition.setAuthorizingCoroner(util.getString(dispositionInfo.getAuthorizingCoroner()));
			noData = noData ? fdmsDisposition.getAuthorizingCoroner() == null : false;
			
			if(noData){
				fdmsDisposition = null;
			}
			
		}
		
		return fdmsDisposition;
	}

	private static CertificationInfo getCertificationInfo(
			CertificationInfoType certification) {
		
		CertificationInfo fdmsCertificationInfo = null;
		if (certification != null){
			fdmsCertificationInfo = new CertificationInfo();
			ConvertorUtil util = ConvertorUtil.getInstance();
			
			fdmsCertificationInfo.setNotificationOfExaminerRequired(certification.getNotificationOfExaminerRequired());
			
			if (certification.getCertificationInfoTypeChoice_type0().getPhysician() != null) {
				fdmsCertificationInfo.setCertifierType(CertificationInfo.CertifierType.PHYSICIAN);
				fdmsCertificationInfo.setCertifierDateSigned(certification.getCertificationInfoTypeChoice_type0().getPhysician().getCertifierDateSigned());
				fdmsCertificationInfo.setLicenseNumber(util.getString(certification.getCertificationInfoTypeChoice_type0().getPhysician().getLicenseNumber()));
			} else if (certification.getCertificationInfoTypeChoice_type0().getMedicalExaminer() != null) {
				fdmsCertificationInfo.setCertifierType(CertificationInfo.CertifierType.MEDICALEXAMINER);
				fdmsCertificationInfo.setCertifierDateSigned(certification.getCertificationInfoTypeChoice_type0().getMedicalExaminer().getCertifierDateSigned());
				fdmsCertificationInfo.setLicenseNumber(util.getString(certification.getCertificationInfoTypeChoice_type0().getMedicalExaminer().getLicenseNumber()));
			} else if (certification.getCertificationInfoTypeChoice_type0().getNursePractitioner() != null) {
				fdmsCertificationInfo.setCertifierType(CertificationInfo.CertifierType.NURSEPRACTITIONER);
				fdmsCertificationInfo.setCertifierDateSigned(certification.getCertificationInfoTypeChoice_type0().getNursePractitioner().getCertifierDateSigned());
				fdmsCertificationInfo.setLicenseNumber(util.getString(certification.getCertificationInfoTypeChoice_type0().getNursePractitioner().getLicenseNumber()));
			}
			
			if (certification.getAtTimeOfDeath() != null){
				AtTimeOfDeathInfo fdmsAtTimeOfDeath = new AtTimeOfDeathInfo();
				AtTimeOfDeathInfoType atTimeOfDeathRequest =  certification.getAtTimeOfDeath();
				
				FdmsConvertorUtil fdmsUtil = FdmsConvertorUtil.getInstance();
				fdmsAtTimeOfDeath.setActualOrPresumedTimeOfDeath(fdmsUtil.getTimeString(atTimeOfDeathRequest.getActualOrPresumedTimeOfDeath()));
				fdmsAtTimeOfDeath.setPronouncedDeadOn(atTimeOfDeathRequest.getPronouncedDeadOn());
				fdmsAtTimeOfDeath.setTimePronounced(fdmsUtil.getTimeString(atTimeOfDeathRequest.getTimePronounced()));
				fdmsAtTimeOfDeath.setMedicalExaminerContacted(atTimeOfDeathRequest.getMedicalExaminerContacted());
				fdmsAtTimeOfDeath.setPlaceOfDeath(util.getString(atTimeOfDeathRequest.getPlaceOfDeath()));
				
				
				if (atTimeOfDeathRequest.getHospital() != null) {
					
					HospitalDetailType hospitalRequest =  atTimeOfDeathRequest.getHospital();
				
					String hospitalAccessType = util.getString(hospitalRequest.getHospitalAccessType());
					String caseNumber = util.getString(hospitalRequest.getCaseNumber());
					String attendingPhysician = util.getString(hospitalRequest.getAttendingPhysician());
					fdmsAtTimeOfDeath.setHospitalDetails(hospitalAccessType, caseNumber, attendingPhysician);
				}
				
				fdmsCertificationInfo.setAtTimeOfDeathInfo(fdmsAtTimeOfDeath);
				
			}
			
			if(certification.getCertifyingPhysician() != null){
				CertifyingPhysicianInofType physicianRequest = certification.getCertifyingPhysician();
				CertifyingPhysician fdmsCertifyingPhysician = new CertifyingPhysician();
				
				fdmsCertifyingPhysician.setName(util.getString(physicianRequest.getName()));
				
				fdmsCertifyingPhysician.setAddress(getAddress(physicianRequest.getAddress()));
				
				fdmsCertifyingPhysician.setPhone(FdmsConvertorUtil.getInstance().getPhoneNumberString(physicianRequest.getPhone()));
				fdmsCertifyingPhysician.setTitle(util.getString(physicianRequest.getCertifierTitle()));
				
				if (physicianRequest.getDatesPhysicianAttended() != null) {
					PhysicianAttendedDateType datesPhysicianAttended = physicianRequest.getDatesPhysicianAttended();
					Date fromDate = datesPhysicianAttended.getFromDate();
					Date toDate = datesPhysicianAttended.getToDate();
					Date lastDate = datesPhysicianAttended.getLastDate();
					fdmsCertifyingPhysician.setDatesAttended(fromDate, toDate, lastDate);
				}
				
				fdmsCertifyingPhysician.setViewedTheBody(physicianRequest.getPhysicianViewedTheBody());
				fdmsCertificationInfo.setCertifyingPhysician(fdmsCertifyingPhysician);
			}
			
		}
		
		return fdmsCertificationInfo;
	}

	private static CauseOfDeath getCauseOfDeath(CauseOfDeathType causeOfDeath) {
		CauseOfDeath fdmsCauseOfDeath = null;
		if (causeOfDeath != null){
			fdmsCauseOfDeath = new CauseOfDeath();
			ConvertorUtil util = ConvertorUtil.getInstance();
			
			if (causeOfDeath.getCause() != null) {
				for (CauseType cause : causeOfDeath.getCause()){
					String dueTo = util.getString(cause.getDueTo());
					String interval = util.getString(cause.getInterval());
					fdmsCauseOfDeath.addCause(dueTo, interval);
				}
			}
			fdmsCauseOfDeath.setTobaccoUser(util.getString(causeOfDeath.getTobaccoUser()));
			fdmsCauseOfDeath.setPregnantAtDeath(causeOfDeath.getPregnantAtDeath());
			fdmsCauseOfDeath.setOtherCondition(util.getString(causeOfDeath.getOtherCondition()));
			fdmsCauseOfDeath.setTypeOfDeath(util.getString(causeOfDeath.getTypeOfDeath()));
			fdmsCauseOfDeath.setBodyFoundMore24Hr(causeOfDeath.getWasBodyFoundMore24Hr());
			fdmsCauseOfDeath.setAnAutopsyPerformed(causeOfDeath.getWasAnAutopsyPerformed());
			fdmsCauseOfDeath.setAutopsyFindingsAvailablePriorToCauseOfDeath(util.getString(causeOfDeath.getWereAutopsyFindingsAvailablePriorToCauseOfDeath()));
			fdmsCauseOfDeath.setHospiceStatus(util.getString(causeOfDeath.getHospiceStatus()));
			
		}
		return fdmsCauseOfDeath;
	}

	private static MedicalExaminerReport getMedicalExaminerReport(MedicalExaminerType medicalExaminer) {
		
		MedicalExaminerReport medicalExaminerReport = null;
		
		if (medicalExaminer != null) {
			medicalExaminerReport = new MedicalExaminerReport();
			ConvertorUtil util = ConvertorUtil.getInstance();
			
			medicalExaminerReport.setInjuryDate(medicalExaminer.getInjuryDate());
			medicalExaminerReport.setInjuryTime(FdmsConvertorUtil.getInstance().getTimeString(medicalExaminer.getInjuryTime()));
			medicalExaminerReport.setInjuryDescription(util.getString(medicalExaminer.getInjuryDescription()));
			medicalExaminerReport.setInjuryAtWork(medicalExaminer.getInjuryAtWork());
			medicalExaminerReport.setInerInjuryPlace(util.getString(medicalExaminer.getInjuryPlace()));
			medicalExaminerReport.setDecedentPositionForTransportInjury(util.getString(medicalExaminer.getDecedentPositionForTransportInjury()));
			
			medicalExaminerReport.setLocation(getAddress(medicalExaminer.getInjuryLocation()));
		}
	
		return medicalExaminerReport;
	}

	public static VitalsIdInfo getVitialsIdInfoRequest(
			InfoContextType infoContext) {
		VitalsIdInfo vitalsIdInfo = new VitalsIdInfo();
		ConvertorUtil util = ConvertorUtil.getInstance();
		if (infoContext.getContractCode() != null) {
			vitalsIdInfo.setContractCode(util.getString(infoContext.getContractCode()));
		}else {
			vitalsIdInfo.setContractCode("");
		}
		if (infoContext.getCaseCode() != null) {
			vitalsIdInfo.setCaseCode(util.getString(infoContext.getCaseCode()));
		}else {
			vitalsIdInfo.setCaseCode("");
		}
		if (infoContext.getDeceasedLastname() != null) {
			vitalsIdInfo.setLastName(util.getString(infoContext.getDeceasedLastname()));
		}else {
			vitalsIdInfo.setLastName("");
		}
		if (infoContext.getLocaleId() > 0) {
			vitalsIdInfo.setLocaleId(infoContext.getLocaleId());
		}else {
			vitalsIdInfo.setLocaleId(0);
		}
		if (infoContext.getLocationId() > 0) {
			vitalsIdInfo.setLocationId(infoContext.getLocationId());
		}else {
			vitalsIdInfo.setLocationId(0);
		}
		return vitalsIdInfo;
	}
	
}
