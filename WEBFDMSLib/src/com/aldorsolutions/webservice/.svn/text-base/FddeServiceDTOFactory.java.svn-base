package com.aldorsolutions.webservice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aldorsolutions.webfdms.util.FormatString;
import com.aldorsolutions.webservice.xsd.comm.*;
import com.aldorsolutions.webservice.xsd.comm.fdde.*;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.PurchaserType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciAddressType;

public class FddeServiceDTOFactory {

	public static String SELECT_FUNERAL_HOME = "SELECT 	CONCAT('L', Locale,'FH', LocationID) as FHID, Name, " +
	" ManagerName, Addr1, Addr2, Addr3, City, State, ZipCode, County, LocationNum, " +
	" PhoneNumber, OtherPhone, LicenseNumber, Website, Email ";
	public static void updateFuneralHome(ResultSet rs, FuneralHomeType funeralHome) throws SQLException{

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil converter = ConvertorUtil.getInstance();

		String value = rs.getString("FHID");
		funeralHome.setId(UserLocation.getFuneralHomeIdType(value));

		value = rs.getString("Name");
		if(factory.isStringNotNullOrNill(value)){
			funeralHome.setName(converter.getStrMax50Len(value.trim()));
		}

		value = rs.getString("LocationNum");
		if(factory.isStringNotNullOrNill(value)){
			funeralHome.setNumber(converter.getStrMax10Len(value.trim()));
		}

		value = rs.getString("ManagerName");
		if(factory.isStringNotNullOrNill(value)){
			funeralHome.setManagerName(converter.getStrMax150Len(value.trim()));
		}

		funeralHome.setAddress(getFuneralHomeAddressType(rs));

		value = rs.getString("PhoneNumber");
		if(factory.isStringNotNullOrNill(value)){
			funeralHome.setPhone(converter.getStrMax20Len(value.trim()));
		}

		value = rs.getString("OtherPhone");
		if(factory.isStringNotNullOrNill(value)){
			funeralHome.setOtherPhone(converter.getStrMax20Len(value.trim()));
		}

		value = rs.getString("LicenseNumber");
		if(factory.isStringNotNullOrNill(value)){
			funeralHome.setLicenseNumber(converter.getStrMax10Len(value.trim()));
		}

		value = rs.getString("Website");
		if(factory.isStringNotNullOrNill(value)){
			funeralHome.setWebsite(converter.getStrMax200Len(value.trim()));
		}

		value = rs.getString("Email");
		if(factory.isStringNotNullOrNill(value)){
			funeralHome.setEmail(converter.getStrMax100Len(value.trim()));
		}
	}

	private static AddressType getFuneralHomeAddressType(ResultSet rs) throws SQLException{

		FddeXsdFactory factory = FddeXsdFactory.getInstance();

		ArrayList<String> streets = new ArrayList<String>(3);
		for(int i=1; i<4; i++){
			String value = rs.getString("Addr" + i);
			if(!rs.wasNull()&& factory.isStringNotNullOrNill(value)){
				streets.add(value.trim());
			}
		}

		String city = rs.getString("City");
		String state = rs.getString("State");
		String zip = rs.getString("ZipCode");
		String county = rs.getString("County");
		return factory.createAddressType(streets, city, county, state, zip);
	}

	//	Case info
	public static String SELECT_CASE = 
		"SELECT CONCAT('L', LocaleNumber , 'FH', ChapelNumber , 'C', vitalstats.VitalsMasterKey) AS caseID," +
		" VoidedCode , CaseCode , ContractCode , ContractNumber , PNPreneedStatus " ;

	public static String SELECT_CASE_CALL_INFO = ", ArrangerId , DirectorName , DirectorLicenseNo , PNCounselor , MarketingPlan " +
	", ShippingData, CallInfoNote, PNSource " ;
	public static String SELECT_CASE_VITALS_INFO = ",  IdentificationMarks, DispositionDate "  ;
	public static String SELECT_CASE_SERVICES_INFO = ", BurialDate " ;

	static boolean setCaseInfoReturnIsPreNeedCase(ResultSet rs, CaseInfoType fhCase, FuneralHomeIdType funeralHomeId)throws SQLException{
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();

		fhCase.setFuneralHomeId(funeralHomeId);

		String value = rs.getString("caseID");
		if(factory.isStringNotNullOrNill(value)){
			fhCase.setId(factory.createCaseIdType(value.trim()));
		}

		value = rs.getString("VoidedCode");
		fhCase.set_void(factory.isStringNotNullOrNill(value) && value.trim().equals("V"));

		value = rs.getString("CaseCode");
		fhCase.setCaseCode(convertor.getStrMax20Len(value.trim()));

		value = rs.getString("ContractCode");
		if(factory.isStringNotNullOrNill(value)){
			fhCase.setContractCode(convertor.getStrMax20Len(value.trim()));
		}

		int contractNumber = rs.getInt("ContractNumber");
		if(!rs.wasNull()){
			fhCase.setContractNumber(contractNumber);
		}

		value = rs.getString("PNPreneedStatus");
		boolean preNeedCase = !rs.wasNull() && factory.isStringNotNullOrNill(value);
		if(preNeedCase){

			value = value.trim();
			if(value.equals("A")){
				value = "active";
			}else if(value.equals("S")){
				value = "serviced";
			}else if(value.equals("C")){
				value = "canceled";
			}else{
				preNeedCase = false;
			}
		}
		fhCase.setCaseType(factory.createCaseTypeInfo(preNeedCase, value));
		return preNeedCase;
	}

	public static void updateCase(ResultSet rs, CaseType fhCase, FuneralHomeIdType funeralHomeId, boolean[] datasIntrested, SimpleDateFormat dateFormat) throws SQLException, ParseException {

		boolean preNeedCase = setCaseInfoReturnIsPreNeedCase(rs, fhCase, funeralHomeId);

		if(datasIntrested[0]){
			fhCase.setCallInfo(getCallInfoType(rs, preNeedCase, dateFormat));
		}

		if(datasIntrested[1]){
			fhCase.setVitalsInformation(getVitalsInformationType(rs, preNeedCase, dateFormat));
		}

		if(datasIntrested[2]){
			fhCase.setServices(getServicesType(rs, preNeedCase, dateFormat));
		}


	}

	private static CallInfoType getCallInfoType(ResultSet rs, boolean preNeedCase, SimpleDateFormat dateFormat) throws SQLException, ParseException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		CallInfoType callInfo = new CallInfoType();

		if(preNeedCase){
			callInfo.setPreNeedInfo(getPreNeedInfoType(rs, dateFormat));
		}

		callInfo.setArranger(getArrangerType(rs));

		String value = rs.getString("PNCounselor");
		if(factory.isStringNotNullOrNill(value)){
			callInfo.setCounselor(convertor.getStrMax40Len(value.trim()));
		}

		value = rs.getString("MarketingPlan");
		if(factory.isStringNotNullOrNill(value)){
			callInfo.setMarketingPlan(convertor.getStrMax30Len(value.trim()));
		}
		callInfo.setFirstCall(getFirstCallType(rs, dateFormat));

		callInfo.setDeceased(getDeceasedType(rs, dateFormat));

		callInfo.setInformant(getInformantPerson(rs));

		callInfo.setNextOfKin(getNextOfKin(rs));

		callInfo.setExecutor(getExecutor(rs));

		value = rs.getString("ShippingData");
		if(factory.isStringNotNullOrNill(value)){
			callInfo.setShipAndAirlineInfo(convertor.getStrMax150Len(value.trim()));
		}

		value = rs.getString("CallInfoNote");
		if(factory.isStringNotNullOrNill(value)){
			callInfo.setCallInfoNote(convertor.getStrMax500Len(value.trim()));
		}

		value = rs.getString("PNSource");
		if(factory.isStringNotNullOrNill(value)){
			callInfo.setSource(convertor.getStrMax40Len(value.trim()));
		}

		callInfo.setFinancialdata(getFinancialData(rs));

		callInfo.setFamily(getFamily(rs));

		return callInfo;
	}

	private static VitalsInformationType getVitalsInformationType(ResultSet rs, boolean preNeedCase, SimpleDateFormat dateFormat) throws SQLException, ParseException {

		VitalsInformationType vitalsInfo = new VitalsInformationType();

		String value = rs.getString("IdentificationMarks");
		if(!rs.wasNull() && FddeXsdFactory.getInstance().isStringNotNullOrNill(value)){
			vitalsInfo.setIdTagNumber(ConvertorUtil.getInstance().getStrMax30Len(value.trim()));
		}

		vitalsInfo.setLocationOfDeath(getLocationOfDeathType(rs));		
		vitalsInfo.setGeneralHistory(getGeneralHistoryType(rs,dateFormat));
		vitalsInfo.setEthnicity(getEthnicityType(rs,dateFormat));
		vitalsInfo.setOccupationalHistory(getOccupationalHistoryType(rs,dateFormat));
		vitalsInfo.setDispositionInfo(getDispositionInfoType(rs, dateFormat));
		vitalsInfo.setCertification(getCertificationType(rs,dateFormat));
		vitalsInfo.setCauseOfDeath(getCauseOfDeathType(rs, dateFormat));
		vitalsInfo.setMedicalExaminer(getMedicalExaminerType(rs, dateFormat));

		return vitalsInfo;
	}

	private static ServicesType getServicesType(ResultSet rs, boolean preNeedCase, SimpleDateFormat dateFormat) throws SQLException, ParseException {

		ServicesType services = new ServicesType();

		services.setService(getServiceInfoType(rs, dateFormat));
		services.setAdditionalService(getAdditionalServiceType(rs, dateFormat));
		services.setCemetery(getCemeteryType(rs, dateFormat));
		services.setCrematory(getCrematoryType(rs, dateFormat));
		services.setChurch(getChurchType(rs, dateFormat));
		services.setMinisters(getMinistersType(rs, dateFormat));
		services.setFlowerArrangements(getFlowerArrangementsType(rs, dateFormat));
		services.setOtherInfo(getOtherInfoType(rs, dateFormat));
		return services;
	}
	static {
		SELECT_CASE_CALL_INFO += 
			//         pre need info
			", Comments ";
	}

	private static PreNeedInfoType getPreNeedInfoType(ResultSet rs, SimpleDateFormat dateFormat) throws SQLException, ParseException {
		PreNeedInfoType preNeedInfo = new PreNeedInfoType();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		boolean preNeedInfoNotExist = true;

		preNeedInfo.setBuyer(getBuyerType(rs));
		preNeedInfoNotExist = preNeedInfoNotExist ? preNeedInfo.getBuyer() == null : preNeedInfoNotExist;

		preNeedInfo.setSalesInfo(getSalesInfoType(rs, dateFormat));
		preNeedInfoNotExist = preNeedInfoNotExist ? preNeedInfo.getBuyer() == null : preNeedInfoNotExist;

		preNeedInfo.setFundDepository(getFundDepositoryType(rs, dateFormat));
		preNeedInfoNotExist = preNeedInfoNotExist ? preNeedInfo.getFundDepository() == null : preNeedInfoNotExist;

		String value = rs.getString("Comments");
		if(factory.isStringNotNullOrNill(value)){
			preNeedInfo.setNotes(value);
			preNeedInfoNotExist = preNeedInfoNotExist ? preNeedInfo.getNotes() == null : preNeedInfoNotExist;
		}

		if(preNeedInfoNotExist){
			preNeedInfo = null;
		}
		return preNeedInfo;
	}

	private static ArrangerType getArrangerType(ResultSet rs) throws SQLException {

		ArrangerType arranger = new ArrangerType();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		int id = rs.getInt("ArrangerId");
		if(!rs.wasNull()){
			arranger.setId(convertor.getStrMax14Len(""+id));
		}

		String value = rs.getString("DirectorName");
		if(factory.isStringNotNullOrNill(value)){
			arranger.setName(convertor.getStrMax150Len(value.trim()));
		}

		value = rs.getString("DirectorLicenseNo");
		if(factory.isStringNotNullOrNill(value)){
			arranger.setLicenseNo(convertor.getStrMax15Len(value.trim()));
		}
		return arranger;
	}

	public static String SELECT_BUYER = 
		//         pre need Buyer Info
		", PNBuyerTitle , PNBuyerFirstName , PNBuyerMidName , PNBuyerLastName , PNBuyerStreet ," +
		" PNBuyerAptNo , PNBuyerCity , PNBuyerState , PNBuyerZip , PNBuyerPhone , PNBuyerSSno , PNCobuyer";


	static BuyerType getBuyerType(ResultSet rs) throws SQLException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		BuyerType buyer = new BuyerType();
		boolean buyerNotExists = true;
		{
			String salutation = rs.getString("PNBuyerTitle");
			if(!rs.wasNull()){
				salutation = salutation.trim();
			}

			String firstName = rs.getString("PNBuyerFirstName");
			if(!rs.wasNull()){
				firstName = firstName.trim();
			}

			String middleName = rs.getString("PNBuyerMidName");
			if(!rs.wasNull()){
				middleName = middleName.trim();
			}

			String lastName = rs.getString("PNBuyerLastName");
			if(!rs.wasNull()){
				lastName = lastName.trim();
			}

			buyer.setName(factory.createDeNameType(salutation, firstName,	middleName, lastName));
			buyerNotExists = buyerNotExists ? buyer.getName() == null : buyerNotExists;
		}

		{
			ArrayList<String> streets = new ArrayList<String>(1);
			String street = rs.getString("PNBuyerStreet");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(street)){
				String aptNo = rs.getString("PNBuyerAptNo");
				if(!rs.wasNull()){
					street = street.trim() + "," + aptNo.trim();
				}
				streets.add(street.trim());
			}

			String city = rs.getString("PNBuyerCity");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(city)){
				city = city.trim();
			}

			String state = rs.getString("PNBuyerState");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(state)){
				state = state.trim().toUpperCase();
			}

			String zip = rs.getString("PNBuyerZip");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(zip)){
				zip = zip.trim();
			}

			buyer.setAddress(factory.createAddressType(streets, city, null, state, zip));
			buyerNotExists = buyerNotExists ? buyer.getAddress() == null : buyerNotExists;
		}

		String value = rs.getString("PNBuyerPhone");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			buyer.setPhone(convertor.getStrMax20Len(value.trim()));
			buyerNotExists = buyerNotExists ? buyer.getPhone() == null : buyerNotExists;
		}

		value = rs.getString("PNBuyerSSno");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			value = FormatString.formatSocialSecurityNo(null, value.trim());
			buyer.setSsn(convertor.getStrMax14Len(value));
			buyerNotExists = buyerNotExists ? buyer.getSsn() == null : buyerNotExists;
		}

		value = rs.getString("PNCobuyer");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			buyer.setCoBuyer(convertor.getStrMax150Len(value.trim()));
			buyerNotExists = buyerNotExists ? buyer.getCoBuyer() == null : buyerNotExists;
		}

		if(buyerNotExists){
			buyer = null;
		}
		return buyer;
	}

	static{
		SELECT_CASE_CALL_INFO += 
			//	pre-need sales info
			", OriginalPNDate , PreneedServiceType , PreneedCasketName , PreneedVaultName ," +
			" PreneedUrnName , PNServiceAmt , PNCasketAmt , PNUrnAmt , PNVaultAmt , PNOtherAmt ," +
			" GSTAmt ";
	}

	private static SalesInfoType getSalesInfoType(ResultSet rs,
			SimpleDateFormat dateFormat) throws SQLException, ParseException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		SalesInfoType salesInfo = new SalesInfoType();
		boolean salesInfoNotExists = true;
		Date date = null;

		String value = rs.getString("OriginalPNDate");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			salesInfo.setSaleDate(date);
			salesInfoNotExists = false;
		}

		value = rs.getString("PreneedServiceType");
		if(factory.isStringNotNullOrNill(value)){
			salesInfo.setService(convertor.getStrMax29Len(value.trim()));
			salesInfoNotExists = salesInfoNotExists ? salesInfo.getService() == null : salesInfoNotExists;
		}

		value = rs.getString("PreneedCasketName");
		if(factory.isStringNotNullOrNill(value)){
			salesInfo.setCasket(convertor.getStrMax40Len(value.trim()));
			salesInfoNotExists = salesInfoNotExists ? salesInfo.getCasket() == null : salesInfoNotExists;
		}

		value = rs.getString("PreneedVaultName");
		if(factory.isStringNotNullOrNill(value)){
			salesInfo.setVault(convertor.getStrMax29Len(value.trim()));
			salesInfoNotExists = salesInfoNotExists ? salesInfo.getVault() == null : salesInfoNotExists;
		}

		value = rs.getString("PreneedUrnName");
		if(factory.isStringNotNullOrNill(value)){
			salesInfo.setUrn(convertor.getStrMax29Len(value.trim()));
			salesInfoNotExists = salesInfoNotExists ? salesInfo.getUrn() == null : salesInfoNotExists;
		}

		long servAmount = rs.getLong("PNServiceAmt");
		if(!rs.wasNull() && servAmount != 0){
			salesInfo.setServiceSaleAmount(factory.getDeAmountType(servAmount/100));
			salesInfoNotExists = salesInfoNotExists ? salesInfo.getServiceSaleAmount() == null : salesInfoNotExists;
		}

		long casktAmount = rs.getLong("PNCasketAmt");
		if(!rs.wasNull() && casktAmount != 0){
			salesInfo.setCasketSaleAmount(factory.getDeAmountType(casktAmount/100));
			salesInfoNotExists = salesInfoNotExists ? salesInfo.getCasketSaleAmount() == null: salesInfoNotExists;
		}

		long urnAmount = rs.getLong("PNUrnAmt");
		if(!rs.wasNull() && urnAmount != 0){
			salesInfo.setUrnSaleAmount(factory.getDeAmountType(urnAmount/100));
			salesInfoNotExists = salesInfoNotExists ? salesInfo.getUrnSaleAmount() == null : salesInfoNotExists;
		}

		long vaultAmount = rs.getLong("PNVaultAmt");
		if(!rs.wasNull() && vaultAmount != 0){
			salesInfo.setVaultSaleAmount(factory.getDeAmountType(vaultAmount/100));
			salesInfoNotExists = salesInfoNotExists ? salesInfo.getVaultSaleAmount() == null : salesInfoNotExists;
		}

		long otherAmount = rs.getLong("PNOtherAmt");
		if(!rs.wasNull() && otherAmount != 0){
			salesInfo.setOtherSaleAmount(factory.getDeAmountType(otherAmount/100));
			salesInfoNotExists = salesInfoNotExists ? salesInfo.getOtherSaleAmount() == null : salesInfoNotExists;
		}

		long gstAmount = rs.getLong("GSTAmt");
		if(!rs.wasNull() && gstAmount != 0){
			salesInfo.setGSTAmount(factory.getDeAmountType(gstAmount/100));
			salesInfoNotExists = salesInfoNotExists ? salesInfo.getGSTAmount() == null : salesInfoNotExists;
		}

		long totalAmount = servAmount + casktAmount + urnAmount + vaultAmount + otherAmount + gstAmount;
		if(totalAmount != 0){
			salesInfo.setTotalSale(factory.getDeAmountType(totalAmount/100));
			salesInfoNotExists = salesInfoNotExists ? salesInfo.getTotalSale() == null : salesInfoNotExists;
		}

		if(salesInfoNotExists){
			salesInfo = null;
		}

		return salesInfo;
	}

	static{
		SELECT_CASE_CALL_INFO += 
			//	Pre need fund depository
			", PNFundType , PNDepository , PNContractDate , PNMaturityDate , PNFundAcctNo ," +
			" PNInterestRate , PNFundsDepositedDate , PNFaceValue , PNContractAmount ," +
			" PNPaidYTD , PNPaidTotal , PNYTDInterest , PNMgmtFee , PNCommission ," +
			" PNLastPmtDate , PNLastPmtAmount ";
	}

	private static FundDepositoryType getFundDepositoryType(ResultSet rs,
			SimpleDateFormat dateFormat)throws SQLException, ParseException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		FundDepositoryType fundDepository = new FundDepositoryType();
		boolean fundDepositoryNotExists = true;
		Date date = null;

		String value = rs.getString("PNFundType");
		if(factory.isStringNotNullOrNill(value)){
			fundDepository.setFundType(convertor.getStrMax24Len(value.trim()));
			fundDepositoryNotExists = fundDepositoryNotExists ? fundDepository.getFundType() == null : fundDepositoryNotExists;
		}

		value = rs.getString("PNDepository");
		if(factory.isStringNotNullOrNill(value)){
			fundDepository.setFundWith(convertor.getStrMax40Len(value.trim()));
			fundDepositoryNotExists = fundDepositoryNotExists ? fundDepository.getFundWith() == null : fundDepositoryNotExists;
		}
		value = rs.getString("PNContractDate");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			fundDepository.setStartedDate(date);
			fundDepositoryNotExists = false;
		}

		value = rs.getString("PNMaturityDate");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			fundDepository.setMaturityDate(date);
			fundDepositoryNotExists = false;
		}

		value = rs.getString("PNFundAcctNo");
		if(factory.isStringNotNullOrNill(value)){
			fundDepository.setAccountNumber(convertor.getStrMax24Len(value.trim()));
			fundDepositoryNotExists = fundDepositoryNotExists ? fundDepository.getAccountNumber() == null : fundDepositoryNotExists;
		}

		long amount = rs.getLong("PNInterestRate");
		if(!rs.wasNull() && amount != 0){
			fundDepository.setEstimateInterestRate(factory.getDeAmountType(amount));
			fundDepositoryNotExists = fundDepositoryNotExists ? fundDepository.getEstimateInterestRate() == null : fundDepositoryNotExists;
		}

		value = rs.getString("PNFundsDepositedDate");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			fundDepository.setDateFundsDeposited(date);
			fundDepositoryNotExists = false;
		}

		amount = rs.getLong("PNFaceValue");
		if(!rs.wasNull() && amount != 0){
			fundDepository.setFaceValue(factory.getDeAmountType(amount/100));
			fundDepositoryNotExists = fundDepositoryNotExists ? fundDepository.getFaceValue() == null : fundDepositoryNotExists;
		}

		amount = rs.getLong("PNContractAmount");
		if(!rs.wasNull() && amount != 0){
			fundDepository.setContractAmount(factory.getDeAmountType(amount/100));
			fundDepositoryNotExists = fundDepositoryNotExists ? fundDepository.getContractAmount() == null : fundDepositoryNotExists;
		}

		amount = rs.getLong("PNPaidYTD");
		if(!rs.wasNull() && amount != 0){
			fundDepository.setYTDPaid(factory.getDeAmountType(amount/100));
			fundDepositoryNotExists = fundDepositoryNotExists ? fundDepository.getYTDPaid() == null : fundDepositoryNotExists;
		}

		amount = rs.getLong("PNPaidTotal");
		if(!rs.wasNull() && amount != 0){
			fundDepository.setTotalPaid(factory.getDeAmountType(amount/100));
			fundDepositoryNotExists = fundDepositoryNotExists ? fundDepository.getTotalPaid() == null : fundDepositoryNotExists;
		}

		amount = rs.getLong("PNYTDInterest");
		if(!rs.wasNull() && amount != 0){
			fundDepository.setYTDInterest(factory.getDeAmountType(amount/100));
			fundDepositoryNotExists = fundDepositoryNotExists ? fundDepository.getYTDInterest() == null : fundDepositoryNotExists;
		}

		amount = rs.getLong("PNMgmtFee");
		if(!rs.wasNull() && amount != 0){
			fundDepository.setManagementFee(factory.getDeAmountType(amount/100));
			fundDepositoryNotExists = fundDepositoryNotExists ? fundDepository.getManagementFee() == null : fundDepositoryNotExists;
		}

		amount = rs.getLong("PNCommission");
		if(!rs.wasNull() && amount != 0){
			fundDepository.setCommission(factory.getDeAmountType(amount/100));
			fundDepositoryNotExists = fundDepositoryNotExists ? fundDepository.getCommission() == null : fundDepositoryNotExists;
		}

		value = rs.getString("PNLastPmtDate");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			fundDepository.setLastPaymentDate(date);
			fundDepositoryNotExists = false;
		}

		amount = rs.getLong("PNLastPmtAmount");
		if(!rs.wasNull() && amount != 0){
			fundDepository.setLastPaymentAmount(factory.getDeAmountType(amount/100));
			fundDepositoryNotExists = fundDepositoryNotExists ? fundDepository.getLastPaymentAmount() == null : fundDepositoryNotExists;
		}

		if(fundDepositoryNotExists){
			fundDepository = null;
		}

		return fundDepository;
	}

	static{
		SELECT_CASE_CALL_INFO += 
			//	FirstCall Info
			", PreneedArrngDate , AppointmentTime , OriginalPNDate , FacilityName , FacilityStreet ," +
			" FacilityCityStZip , FacilityPhone , FacilityLicenseNo , EmbalmingReason ";
	}
	private static FirstCallType getFirstCallType(ResultSet rs, 
			SimpleDateFormat dateFormat) throws SQLException, ParseException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		FirstCallType firstCall = new FirstCallType();
		boolean firstCallNotExists = true;
		Date date = null;

		String value = rs.getString("PreneedArrngDate");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			firstCall.setArrangeDate(date);
			firstCallNotExists = false;
		}

		value = rs.getString("AppointmentTime");
		if(factory.isStringNotNullOrNill(value)){
			firstCall.setArrangeTime(convertor.getStrMax08Len(value.trim()));
			firstCallNotExists = firstCallNotExists ? firstCall.getArrangeTime() == null : firstCallNotExists;
		}

		value = rs.getString("OriginalPNDate");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			firstCall.setOriginalPreneedDate(date);
			firstCallNotExists = false;
		}

		firstCall.setFacility(getFaciltyType(rs));
		firstCallNotExists = firstCallNotExists ? firstCall.getFacility() == null : firstCallNotExists;

		value = rs.getString("EmbalmingReason");
		if(factory.isStringNotNullOrNill(value)){
			firstCall.setEmbalming(convertor.getStrMax60Len(value.trim()));
			firstCallNotExists = firstCallNotExists ? firstCall.getEmbalming() == null : firstCallNotExists;
		}

		if(firstCallNotExists){
			firstCall = null;
		}
		return firstCall;
	}

	private static FaciltyType getFaciltyType(ResultSet rs) throws SQLException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		FaciltyType facility = new FaciltyType();
		boolean facilityNotExists = true;

		String value = rs.getString("FacilityName");
		if(factory.isStringNotNullOrNill(value)){
			facility.setName(convertor.getStrMax50Len(value.trim()));
			facilityNotExists = facilityNotExists ? facility.getName() == null : facilityNotExists;
		}

		{
			ArrayList<String> streets = new ArrayList<String>(1);
			value = rs.getString("FacilityStreet");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
				streets.add(value.trim());
			}

			String state = null;
			String zip = null;
			String city = null;
			value = rs.getString("FacilityCityStZip");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
				String[] adress = value.split(",");
				if(adress.length>0){
					city = adress[0].trim();
				}
				if(adress.length>1){
					state = adress[1].trim();
				}
				if(adress.length>2){
					zip = adress[2].trim();
				}
			}

			facility.setAddress(factory.createAddressType(streets, city, null, state, zip ));
			facilityNotExists = facilityNotExists ? facility.getAddress() == null : facilityNotExists;
		}

		value = rs.getString("FacilityPhone");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			facility.setPhone(convertor.getStrMax20Len(value.trim()));
			facilityNotExists = facilityNotExists ? facility.getPhone() == null : facilityNotExists;
		}

		value = rs.getString("FacilityLicenseNo");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			facility.setLicense(convertor.getStrMax15Len(value.trim()));
			facilityNotExists = facilityNotExists ? facility.getLicense() == null : facilityNotExists;
		}

		facilityNotExists=false;
		if(facilityNotExists){
			facility = null;
		}
		return facility;
	}

	static{
		SELECT_CASE_CALL_INFO += 
			//	Deceased Info
			", DecMrMrs , DeceasedFirstName , DeceasedMidName , DeceasedLastName , DeceasedSuffix , " +
			" DeceasedMaidenName , DecAliasFirst , DecAliasMiddle , DecAliasLast , DecAliasPrefix ," +
			" DecAliasSuffix , DeceasedFullName , DecResStreet , DecResMailCity , DecResCounty ," +
			" DecResState , DecResZip , DecResCountry , DecCityTWPBox, TimeAtResidence ," +
			" SocialSecurityNo , DeceasedSex , DateOfBirth , DateOfDeath ," +
			" AgeYears , AgeMonths , AgeDays , AgeHours , AgeMinutes , BurialDate ";
	}

	private static DeceasedType getDeceasedType(ResultSet rs, SimpleDateFormat dateFormat) throws SQLException, ParseException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		DeceasedType deceased = new DeceasedType();
		boolean deceasedNotExists = true;

		{
			String salutation = rs.getString("DecMrMrs");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(salutation)){
				salutation = salutation.trim();
			}
			String firstName = rs.getString("DeceasedFirstName");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(firstName)){
				firstName = firstName.trim();
			}
			String middleName = rs.getString("DeceasedMidName");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(middleName)){
				middleName = middleName.trim();
			}
			String lastName = rs.getString("DeceasedLastName");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(lastName)){
				lastName = lastName.trim();
			}

			String suffix = rs.getString("DeceasedSuffix");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(suffix)){
				suffix = suffix.trim();
			}
			String maidenName = rs.getString("DeceasedMaidenName");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(maidenName)){
				maidenName = maidenName.trim();
			}

			deceased.setName(
					factory.createDeCompleteNameType(salutation, firstName, middleName, lastName, null, suffix, maidenName));
			deceasedNotExists = deceasedNotExists ? deceased.getName() == null : deceasedNotExists;


			firstName = rs.getString("DecAliasFirst");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(firstName)){
				firstName = firstName.trim();
			}
			middleName = rs.getString("DecAliasMiddle");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(middleName)){
				middleName = middleName.trim();
			}
			lastName = rs.getString("DecAliasLast");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(lastName)){
				lastName = lastName.trim();
			}
			String prefix = rs.getString("DecAliasPrefix");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(prefix)){
				prefix = prefix.trim();
			}
			suffix = rs.getString("DecAliasSuffix");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(suffix)){
				suffix = suffix.trim();
			}

			deceased.setOtherName(
					factory.createDeCompleteNameType(null, firstName, middleName, lastName, prefix, suffix, null));
			deceasedNotExists = deceasedNotExists ? deceased.getOtherName() == null : deceasedNotExists;
		}

		String value = rs.getString("DeceasedFullName");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			deceased.setMemorialName(convertor.getStrMax150Len(value.trim()));
			deceasedNotExists = deceasedNotExists ? deceased.getMemorialName() == null : deceasedNotExists;
		}
		{
			String street = rs.getString("DecResStreet");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(street)){
				street = street.trim();
			}
			String city = rs.getString("DecResMailCity");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(city)){
				city = city.trim();
			}
			String county = rs.getString("DecResCounty");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(county)){
				county = county.trim();
			}
			String state = rs.getString("DecResState");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(state)){
				state = state.trim();
			}
			String zip = rs.getString("DecResZip");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(zip)){
				zip = zip.trim();
			}
			String country = rs.getString("DecResCountry");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(country)){
				country = country.trim();
			}

			String locality = rs.getString("DecCityTWPBox");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(locality)){
				locality = convertor.getLocalityTypeStringByCode(locality.trim());
			}			

			String residenceLengthOfTime = rs.getString("TimeAtResidence");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(residenceLengthOfTime)){
				residenceLengthOfTime = residenceLengthOfTime.trim();
			}
			deceased.setResidence(factory.createDeResidenceType(street, city, county,  
					state, zip, country, locality , residenceLengthOfTime));
			deceasedNotExists = deceasedNotExists ? deceased.getResidence() == null : deceasedNotExists;

		}


		value = rs.getString("SocialSecurityNo");
		if(factory.isStringNotNullOrNill(value)){
			deceased.setSsn(convertor.getStrMax14Len(value.trim()));
			deceasedNotExists = deceasedNotExists ? deceased.getSsn() == null : deceasedNotExists;
		}

		value = rs.getString("DeceasedSex");
		if(factory.isStringNotNullOrNill(value)){
			value = value.trim();

			if(value.equalsIgnoreCase("M")){

				deceased.setGender(GenderType.Male);
				deceasedNotExists = false;

			}else if(value.equalsIgnoreCase("F")){

				deceased.setGender(GenderType.Female);
				deceasedNotExists = false;

			}
		}

		Date date = null;
		value = rs.getString("DateOfBirth");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			deceased.setBirthDate(date);
			deceasedNotExists = false;
		}

		value = rs.getString("DateOfDeath");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			deceased.setDeathDate(date);                      
			deceasedNotExists = false;
		}

		{
			AgeType age = new AgeType();
			boolean ageSet = false;
			int ageInt = rs.getInt("AgeYears");
			if(!rs.wasNull()){
				age.setYear(ageInt);
				ageSet = true;
			}

			ageInt = rs.getInt("AgeMonths");
			if(!rs.wasNull()){
				age.setMonth(ageInt);
				ageSet = true;
			}

			ageInt = rs.getInt("AgeDays");
			if(!rs.wasNull()){
				age.setDay(ageInt);
				ageSet = true;
			}

			ageInt = rs.getInt("AgeHours");
			if(!rs.wasNull()){
				age.setHour(ageInt);
				ageSet = true;
			}

			ageInt = rs.getInt("AgeMinutes");
			if(!rs.wasNull()){
				age.setMinutes(ageInt);
				ageSet = true;
			}
			if(ageSet){
				deceased.setAge(age);
				deceasedNotExists = false;
			}
		}

		if(deceasedNotExists){
			deceased = null;
		}
		return deceased;
	}

	public static String SELECT_INFORMANT = 
		//	 Informant Info
		", InformantIsBillToParty , Salutation, InformantFirstName , InformantMiddleNam , InformantLastName ," +
		" InformantStreet , InformantRoad2 , InformantRoad3 , InformantCity , InformantState ," +
		" InformantZip , InformantPhone , InformantCellPhone , InformantEmail , InformantRelation ";

	static InformantPerson getInformantPerson(ResultSet rs) throws SQLException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		InformantPerson informant = new InformantPerson();
		boolean informantNotExists = true;

		String value = rs.getString("InformantIsBillToParty");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			informant.setBillToParty("Y".equalsIgnoreCase(value.trim()));
			informantNotExists = false;
		}
		Map<String, String> columnKeys = new HashMap<String, String>(11);

		columnKeys.put("salutation", "Salutation");
		columnKeys.put("firstName", "InformantFirstName");
		columnKeys.put("middleName", "InformantMiddleNam");
		columnKeys.put("lastName", "InformantLastName");

		List<String> streets = new ArrayList<String>(3);
		streets.add("InformantStreet");
		streets.add("InformantRoad2");
		streets.add("InformantRoad3");

		columnKeys.put("city", "InformantCity");
		columnKeys.put("state", "InformantState");
		columnKeys.put("zip", "InformantZip");
		columnKeys.put("phone", "InformantPhone");    
		columnKeys.put("cellPhone", "InformantCellPhone");
		columnKeys.put("email", "InformantEmail");
		columnKeys.put("relation", "InformantRelation");
		informantNotExists = !updateDeRelatedPerson(rs, informant, columnKeys, streets);
		if(informantNotExists){
			informant = null;
		}
		return informant;
	}

	public static String SELECT_NEXT_OF_KIN = 
		//			 Next of Kin Info
		", NextKinSalutation , NextKinFirstName , NextKinMiddleName , NextKinLastName ," +
		" NextOfKinAddress , NextKinRoad2 , NextKinRoad3 , NextKinCity , NextKinState ," +
		"	NextKinZip , NextKinPhone , NextKinCellPhone , NextKinRelation ";

	static DeRelatedPersonType getNextOfKin(ResultSet rs) throws SQLException {

		DeRelatedPersonType nextOfKin = new DeRelatedPersonType();

		Map<String, String> columnKeys = new HashMap<String, String>(10);
		columnKeys.put("salutation", "NextKinSalutation");
		columnKeys.put("firstName", "NextKinFirstName");
		columnKeys.put("middleName", "NextKinMiddleName");
		columnKeys.put("lastName", "NextKinLastName");

		List<String> streets = new ArrayList<String>(3);
		streets.add("NextOfKinAddress");
		streets.add("NextKinRoad2");
		streets.add("NextKinRoad3");

		columnKeys.put("city", "NextKinCity");
		columnKeys.put("state", "NextKinState");
		columnKeys.put("zip", "NextKinZip");
		columnKeys.put("phone", "NextKinPhone");
		columnKeys.put("cellPhone", "NextKinCellPhone");
		columnKeys.put("relation", "NextKinRelation");

		if(!updateDeRelatedPerson(rs, nextOfKin, columnKeys, streets)){
			nextOfKin = null;
		}
		return nextOfKin;
	}
	static{
		//executor info
		SELECT_CASE_CALL_INFO +=
			" , ExecutorFirstName, ExecutorLastName, ExecutorStreet, ExecutorStreet2, ExecutorStreet3, " +
			" ExecutorCity, ExecutorState, ExecutorZip, ExecutorPhone, ExecutorCellPhone, ExecutorEmail, ExecutorRelation " ;
	}

	private static DeRelatedPersonType getExecutor(ResultSet rs) throws SQLException {

		DeRelatedPersonType executor = new DeRelatedPersonType();

		Map<String, String> columnKeys = new HashMap<String, String>(9);
		columnKeys.put("firstName", "ExecutorFirstName");
		columnKeys.put("lastName", "ExecutorLastName");

		List<String> streets = new ArrayList<String>(3);
		streets.add("ExecutorStreet");
		streets.add("ExecutorStreet2");
		streets.add("ExecutorStreet3");

		columnKeys.put("city", "ExecutorCity");
		columnKeys.put("state", "ExecutorState");
		columnKeys.put("zip", "ExecutorZip");
		columnKeys.put("phone", "ExecutorPhone");
		columnKeys.put("cellPhone", "ExecutorCellPhone");
		columnKeys.put("email", "ExecutorEmail");
		columnKeys.put("relation", "ExecutorRelation");

		if(!updateDeRelatedPerson(rs, executor, columnKeys, streets)){
			executor = null;
		}
		return executor;
	}

	static{
		//financial data info 
		SELECT_CASE_CALL_INFO +=
			" , SaleType, FinanceChargeRate, AmountFinanced, TotalPaidToDate, SentToAccounting, " +
			" TotalCharges " ;
	}

	private static FinancialdataType getFinancialData(ResultSet rs) throws SQLException {

		FinancialdataType financialData = new FinancialdataType();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		boolean financialDataNotExist = true;

		String value = rs.getString("SaleType");
		if(factory.isStringNotNullOrNill(value)){
			financialData.setSaleType(convertor.getStrMax50Len(value.trim()));
			financialDataNotExist = financialDataNotExist ? financialData.getSaleType() == null : financialDataNotExist;
		}
		double chargeRate = rs.getDouble("FinanceChargeRate");
		if(!rs.wasNull() && chargeRate != 0.0){
			financialData.setFinanceChargeRate(chargeRate);
			financialDataNotExist = financialDataNotExist ? financialData.getFinanceChargeRate() == 0.0: financialDataNotExist;
		}
		long amount = rs.getLong("AmountFinanced");
		if(!rs.wasNull() && amount != 0){
			financialData.setAmountFinanced(factory.getDeAmountType(amount/100));
			financialDataNotExist = financialDataNotExist ? financialData.getAmountFinanced() == null: financialDataNotExist;
		}
		amount = rs.getLong("TotalPaidToDate");
		if(!rs.wasNull() && amount != 0){
			financialData.setTotalPaidToDate(factory.getDeAmountType(amount/100));
			financialDataNotExist = financialDataNotExist ? financialData.getTotalPaidToDate() == null: financialDataNotExist;
		}
		value = rs.getString("SentToAccounting");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			financialData.setSentToAccounting(value.equals("1"));
			financialDataNotExist = financialDataNotExist ? financialData.getSentToAccounting() == false : financialDataNotExist;
		}
		amount = rs.getLong("TotalCharges");
		if(!rs.wasNull() && amount != 0){
			financialData.setTotalCharges(factory.getDeAmountType(amount/100));
			financialDataNotExist = financialDataNotExist ? financialData.getTotalCharges() == null: financialDataNotExist;
		}

		if(financialDataNotExist){
			financialData = null;
		}
		return financialData;
	}

	static{
		//family info
		SELECT_CASE_CALL_INFO +=
			" , SpouseFirstName, SpouseMiddleName, SpouseLastName, FatherFirstName, FatherMiddleName, " +
			" FatherLastName, FatherBirthCity, FatherBirthState, MotherFirstName, MotherMiddleName, " +
			" MotherLastName, MotherBirthCity, MotherBirthState " ;
	}

	private static FamilyType getFamily(ResultSet rs) throws SQLException  {

		FamilyType family = new FamilyType();
		boolean familyNotExist = true;
		FddeXsdFactory factory = FddeXsdFactory.getInstance();

		String spouseFirstName = rs.getString("SpouseFirstName");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(spouseFirstName)){
			spouseFirstName = spouseFirstName.trim();
		}
		String spouseMiddleName = rs.getString("SpouseMiddleName");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(spouseMiddleName)){
			spouseMiddleName = spouseMiddleName.trim();
		}
		String spouseLastName = rs.getString("SpouseLastName");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(spouseLastName)){
			spouseLastName = spouseLastName.trim();
		}	
		family.setSurvivingSpouseName(factory.createDeNameType(null, spouseFirstName, spouseMiddleName, spouseLastName));
		familyNotExist = familyNotExist ? family.getSurvivingSpouseName() == null : familyNotExist;

		family.setFather(getFather(rs));
		familyNotExist = familyNotExist ? family.getFather() == null : familyNotExist;

		family.setMother(getMother(rs));
		familyNotExist = familyNotExist ? family.getMother() == null : familyNotExist;

		if(familyNotExist){
			family = null;
		}
		return family;
	}

	private static DeParentType getFather(ResultSet rs) throws SQLException {
		Map<String, String> columns = new HashMap<String, String>(5);
		columns.put("firstName", "FatherFirstName");
		columns.put("middleName", "FatherMiddleName");
		columns.put("lastName", "FatherLastName");
		columns.put("city", "FatherBirthCity");
		columns.put("state", "FatherBirthState");
		return getParent(rs,columns);
	}

	private static DeParentType getMother(ResultSet rs)  throws SQLException{
		Map<String, String> columns = new HashMap<String, String>(5);
		columns.put("firstName", "MotherFirstName");
		columns.put("middleName", "MotherMiddleName");
		columns.put("lastName", "MotherLastName");
		columns.put("city", "MotherBirthCity");
		columns.put("state", "MotherBirthState");
		return getParent(rs,columns);
	}

	private static DeParentType getParent(ResultSet rs, Map<String , String> columns) throws SQLException {
		DeParentType parent = new DeParentType();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		boolean parentNotExist = true;

		String key = columns.get("firstName");
		String firstName = key != null ? rs.getString(key): null;
		if(!rs.wasNull() && factory.isStringNotNullOrNill(firstName)){
			firstName = firstName.trim();
		}
		key = columns.get("middleName");
		String middleName = key != null ? rs.getString(key): null;
		if(!rs.wasNull() && factory.isStringNotNullOrNill(middleName)){
			middleName = middleName.trim();
		}
		key = columns.get("lastName");
		String lastName = key != null ? rs.getString(key): null;
		if(!rs.wasNull() && factory.isStringNotNullOrNill(lastName)){
			lastName = lastName.trim();
		}
		parent.setName(factory.createDeNameType(null, firstName, middleName, lastName));
		parentNotExist = parentNotExist ? parent.getName() == null : parentNotExist;

		key = columns.get("city");
		String city = key != null ? rs.getString(key): null;
		if(!rs.wasNull() && factory.isStringNotNullOrNill(city)){
			city = city.trim();
		}
		key = columns.get("state");
		String state = key != null ? rs.getString(key): null;
		if(!rs.wasNull() && factory.isStringNotNullOrNill(state)){
			state = state.trim();
		}
		parent.setBirthPlace(factory.createDeStandardAddressType(null, city, null, state, null, null));
		parentNotExist = parentNotExist ? parent.getBirthPlace() == null : parentNotExist;

		if(parentNotExist){
			parent = null;
		}
		return parent;
	}

	private static boolean updateDeRelatedPerson(ResultSet rs, DeRelatedPersonType person, 
			Map<String, String> columns, List<String> streetKeys) throws SQLException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();

		boolean personExists = false;
		String key = null;

		{
			key = columns.get("salutation");
			String salutation = key != null ? rs.getString(key): null;
			if(!rs.wasNull()&& factory.isStringNotNullOrNill(salutation)){
				salutation = salutation.trim();
			}

			key = columns.get("firstName");
			String firstName = key != null ? rs.getString(key): null;
			if(!rs.wasNull() && factory.isStringNotNullOrNill(firstName)){
				firstName = firstName.trim();
			}

			key = columns.get("middleName");
			String middleName = key != null ? rs.getString(key): null;
			if(!rs.wasNull() && factory.isStringNotNullOrNill(middleName)){
				middleName = middleName.trim();
			}

			key = columns.get("lastName");
			String lastName = key != null ? rs.getString(key): null;
			if(!rs.wasNull() && factory.isStringNotNullOrNill(lastName)){
				lastName = lastName.trim();
			}
			person.setName(factory.createDeNameType(salutation, firstName, middleName, lastName));
			personExists = personExists ? personExists : person.getName() != null ;
		}

		{
			ArrayList<String> streets = new ArrayList<String>(streetKeys.size());
			String street = null;

			for(String streetKey : streetKeys){
				street = rs.getString(streetKey);
				if(!rs.wasNull() && factory.isStringNotNullOrNill(street)){
					streets.add(street.trim());
				}
			}

			key = columns.get("city");
			String city = key != null ? rs.getString(key): null;
			if(!rs.wasNull() && factory.isStringNotNullOrNill(city)){
				city = city.trim();
			}

			key = columns.get("state");
			String state = key != null ? rs.getString(key): null;
			if(!rs.wasNull() && factory.isStringNotNullOrNill(state)){
				state = state.trim();
			}

			key = columns.get("zip");
			String zip = key != null ? rs.getString(key): null;
			if(!rs.wasNull() && factory.isStringNotNullOrNill(zip)){
				zip = zip.trim();
			}
			person.setAddress(factory.createDeExtendedAddressType(streets, city, state, zip, null));
			personExists = personExists ? personExists : person.getAddress() != null ;
		}

		{
			key = columns.get("phone");
			String phone = key != null ? rs.getString(key): null;
			if(!rs.wasNull() && factory.isStringNotNullOrNill(phone)){
				phone = phone.trim();
			}

			key = columns.get("cellPhone");
			String cellPhone= key != null ? rs.getString(key): null;
			if(!rs.wasNull() && factory.isStringNotNullOrNill(cellPhone)){
				cellPhone = cellPhone.trim();
			}

			key = columns.get("email");
			String email= key != null ? rs.getString(key): null;
			if(!rs.wasNull() && factory.isStringNotNullOrNill(email)){
				email = email.trim();
			}

			person.setContact(factory.createDeContactType(phone, cellPhone, email));
			personExists = personExists ? personExists : person.getContact() != null ;
		}

		key = columns.get("relation");
		String relation = key != null ? rs.getString(key): null;
		if(factory.isStringNotNullOrNill(relation)){
			person.setRelation(convertor.getStrMax30Len(relation.trim()));
			personExists = personExists ? personExists : person.getRelation() != null ;
		}

		return personExists;
	}

	static{
		// location of death
		SELECT_CASE_VITALS_INFO += ",  LocationOfDeath , LocOfDeathStreet , CityOfDeath , CountyOfDeath , StateOfDeath ," +
		" LoctnOfDeathZip , DeathInsideCity , LocOfDeathCVT , LocDeathLicenseNumber "  ; 
	}


	private static LocationOfDeathType getLocationOfDeathType(ResultSet rs) throws SQLException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		LocationOfDeathType locationOfDeath = new LocationOfDeathType();
		boolean locationOfDeathNotExist = true;

		String value = rs.getString("LocationOfDeath");
		if(factory.isStringNotNullOrNill(value)){
			locationOfDeath.setPlaceOfDeath(convertor.getStrMax100Len(value.trim()));
			locationOfDeathNotExist = locationOfDeathNotExist ? locationOfDeath.getPlaceOfDeath() == null : locationOfDeathNotExist;
		}

		String street = rs.getString("LocOfDeathStreet");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(street)){
			street = street.trim();
		}
		String city = rs.getString("CityOfDeath");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(city)){
			city = city.trim();
		}
		String county = rs.getString("CountyOfDeath");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(county)){
			county = county.trim();
		}
		String state = rs.getString("StateOfDeath");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(state)){
			state = state.trim();
		}
		String zip = rs.getString("LoctnOfDeathZip");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(zip)){
			zip = zip.trim();
		}
		locationOfDeath.setAddress(factory.createDeStandardAddressType(street, city, 
				county, state, zip, null));
		locationOfDeathNotExist = locationOfDeathNotExist ? locationOfDeath.getAddress() == null : locationOfDeathNotExist;

		value = rs.getString("LocDeathLicenseNumber");
		if(factory.isStringNotNullOrNill(value)){
			locationOfDeath.setLicenseNumber(convertor.getStrMax30Len(value.trim()));
			locationOfDeathNotExist = locationOfDeathNotExist ? locationOfDeath.getLicenseNumber() == null : locationOfDeathNotExist;
		}

		value = rs.getString("DeathInsideCity");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			locationOfDeath.setDeathInsideCity(value.trim().equalsIgnoreCase("Y"));
			locationOfDeathNotExist = false;
		}

		value = rs.getString("LocOfDeathCVT");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			locationOfDeath.setDeathOccurredIn(convertor.getCVTType(value.trim()));
			locationOfDeathNotExist = locationOfDeathNotExist ? locationOfDeath.getDeathOccurredIn() == null : locationOfDeathNotExist;
		}	

		if(locationOfDeathNotExist){
			locationOfDeath = null;
		}

		return locationOfDeath;
	}

	static{
		//general history
		SELECT_CASE_VITALS_INFO += ", DecBirthPlaceCSV , CountyOfBirth , DecEducation , ElementaryEduc , CollegeEducation "  ; 
	}


	private static GeneralHistoryType getGeneralHistoryType(ResultSet rs, SimpleDateFormat dateFormat) throws SQLException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		GeneralHistoryType generalHistory = new GeneralHistoryType();
		boolean generalHistoryNotExist = true;


		{
			String county = rs.getString("CountyOfBirth");
			if(factory.isStringNotNullOrNill(county)){
				county = county.trim();
			}				
			String city = null;
			String state = null;
			String country = null;
			String value = rs.getString("DecBirthPlaceCSV");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(value.trim())){
				String[] adress = value.trim().split(",");
				if(adress.length>0){
					city = adress[0].trim();
				}
				if(adress.length>1){
					state = adress[1].trim().toUpperCase();
				}
				if(adress.length>2){
					country = adress[2].trim();
				}
			}				
			generalHistory.setBirthplace(factory.createDeStandardAddressType(null, city , county , state , null , country));
			generalHistoryNotExist = generalHistoryNotExist ? generalHistory.getBirthplace() == null : generalHistoryNotExist;
		}	


		generalHistory.setDecedentsEducation(getDecedentsEducation(rs));		
		generalHistoryNotExist = generalHistoryNotExist ? generalHistory.getDecedentsEducation() == null : generalHistoryNotExist;

		if(generalHistoryNotExist){
			generalHistory = null;
		}

		return generalHistory;
	}

	private static DecedentsEducationType getDecedentsEducation(ResultSet rs)throws SQLException {

		DecedentsEducationType education = new DecedentsEducationType();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		boolean educationNotExist = true;

		String decEducation = rs.getString("DecEducation");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(decEducation)){
			education.setHighestGradeCompleted(convertor.getStrMax200Len(decEducation.trim()));
			educationNotExist = educationNotExist ? education.getHighestGradeCompleted() == null : educationNotExist;
		}
		String elemEducation = rs.getString("ElementaryEduc");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(elemEducation)){
			education.setYearsOfElementaryOrSecondary(convertor.getStrMax03Len(elemEducation.trim()));
			educationNotExist = educationNotExist ? education.getYearsOfElementaryOrSecondary() == null : educationNotExist;

		}
		String colgEducation = rs.getString("CollegeEducation");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(colgEducation)){
			education.setYearsOfColege(convertor.getStrMax03Len(colgEducation.trim()));
			educationNotExist = educationNotExist ? education.getYearsOfColege() == null : educationNotExist;

		}	

		if(educationNotExist){
			education = null;
		}
		return education;
	}

	static{
		//ethnicity
		SELECT_CASE_VITALS_INFO += ", Race , Ancestry , TribalMember , TribalName , " +
		" HispanicOriginBox, Citizenship "  ; 
	}

	private static EthnicityType getEthnicityType(ResultSet rs,	SimpleDateFormat dateFormat) throws SQLException, ParseException  {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		EthnicityType ethnicity = new EthnicityType();
		boolean ethnicityNotExist = true;

		String value = rs.getString("Race");
		if(factory.isStringNotNullOrNill(value)){
			ethnicity.setRace(value.trim());
			ethnicityNotExist = ethnicityNotExist ? ethnicity.getRace() == null : ethnicityNotExist;
		}

		value = rs.getString("Ancestry");
		if(factory.isStringNotNullOrNill(value)){
			ethnicity.setAncestry(value.trim());
			ethnicityNotExist = ethnicityNotExist ? ethnicity.getRace() == null : ethnicityNotExist;
		}

		ethnicity.setTribal(getTribal(rs));		
		ethnicityNotExist = ethnicityNotExist ? ethnicity.getTribal() == null : ethnicityNotExist;


		value = rs.getString("Citizenship");
		if(factory.isStringNotNullOrNill(value)){
			ethnicity.setCitizenship(value.trim());
			ethnicityNotExist = ethnicityNotExist ? ethnicity.getCitizenship() == null : ethnicityNotExist;
		}

		value = rs.getString("HispanicOriginBox");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			ethnicity.setHispanicOrigin(convertor.getYesNoAnswerByCode(value.trim()));
			ethnicityNotExist = ethnicityNotExist ? ethnicity.getHispanicOrigin() == null : ethnicityNotExist;
		}			
		if(ethnicityNotExist){
			ethnicity = null;
		}				
		return ethnicity;
	}

	private static TribalType getTribal(ResultSet rs)throws SQLException {

		TribalType tribal = new TribalType();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		boolean tribalNotExist = true;

		String name = rs.getString("TribalName");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(name)){
			tribal.setName(convertor.getStrMax30Len(name.trim()));
		}		
		name = rs.getString("TribalMember");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(name)){
			tribal.setMember(convertor.getSimpleAnswerByCode(name.trim()));
			tribalNotExist = tribalNotExist ? tribal.getMember() == null : tribalNotExist;
		}		
		if(tribalNotExist){
			tribal = null;
		}
		return tribal;
	}	

	static{
		//occupational history
		SELECT_CASE_VITALS_INFO += ", InArmedForces , WarFromDate , WarToDate , Occupation , " +
		" KindOfBusiness, Employer "  ; 
	}
	private static OccupationalHistoryType getOccupationalHistoryType(ResultSet rs, SimpleDateFormat dateFormat) throws SQLException, ParseException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil converter = ConvertorUtil.getInstance();

		String wasDecedentEverInUSArmedForcesByCode = rs.getString("InArmedForces");
		if(factory.isStringNotNullOrNill(wasDecedentEverInUSArmedForcesByCode)){
			wasDecedentEverInUSArmedForcesByCode = wasDecedentEverInUSArmedForcesByCode.trim();
		}
		String value = rs.getString("WarFromDate");
		Date armedForcesEntryDate = null;
		if(factory.isStringNotNullOrNill(value)){
			armedForcesEntryDate = dateFormat.parse(value.trim());
		}		 

		value = rs.getString("WarToDate");
		Date armedForcesDepartureDate = null;
		if(factory.isStringNotNullOrNill(value)){
			armedForcesDepartureDate = dateFormat.parse(value.trim());
		}

		String usualOccupation = rs.getString("Occupation");
		if(factory.isStringNotNullOrNill(usualOccupation)){
			usualOccupation = usualOccupation.trim();
		}

		String kindOfBusinessOrIndustry = rs.getString("KindOfBusiness");
		if(factory.isStringNotNullOrNill(kindOfBusinessOrIndustry)){
			kindOfBusinessOrIndustry = kindOfBusinessOrIndustry.trim();
		}

		String currentEmployer = rs.getString("Employer");
		if(factory.isStringNotNullOrNill(currentEmployer)){
			currentEmployer = currentEmployer.trim();
		}

		return converter.getOccupationalHistoryTypeByCode(wasDecedentEverInUSArmedForcesByCode, 
				armedForcesEntryDate, armedForcesDepartureDate, usualOccupation, 
				kindOfBusinessOrIndustry, currentEmployer);

	}

	static{
		//disposition info
		SELECT_CASE_VITALS_INFO += ", DateOfNotifyToDirector , TimeOfNotifyToDirectory , EmbalmerName , DispositionMethod , " +
		" DispositionPlace, DispositionPlaceType, DispositionStreet, DispositionCity, DispositionCounty,  DispositionState, " +
		" DispositionZip, DateEmbalmed, Doctor2Name "  ; 
	}
	private static DispositionInfoType getDispositionInfoType(ResultSet rs, SimpleDateFormat dateFormat) throws SQLException, ParseException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil converter = ConvertorUtil.getInstance();
		DispositionInfoType disposition = new DispositionInfoType();
		boolean dispositionNotexist = true;

		Date date = null;

		String value = rs.getString("DateOfNotifyToDirector");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			disposition.setDateOfDisposition(date);
			dispositionNotexist = false;
		}

		value = rs.getString("TimeOfNotifyToDirectory");
		if(factory.isStringNotNullOrNill(value)){
			disposition.setTimeOfNotifyToDirector(converter.getStrMax08Len(value.trim()));
			dispositionNotexist = dispositionNotexist ? disposition.getTimeOfNotifyToDirector() == null : dispositionNotexist;
		}

		value = rs.getString("EmbalmerName");
		if(factory.isStringNotNullOrNill(value)){
			disposition.setEmbalmer(value.trim());
			dispositionNotexist = dispositionNotexist ? disposition.getEmbalmer() == null : dispositionNotexist;
		}

		value = rs.getString("DispositionMethod");
		if(factory.isStringNotNullOrNill(value)){
			disposition.setMethodOfDisposition(converter.getStrMax27Len(value.trim()));
			dispositionNotexist = dispositionNotexist ? disposition.getMethodOfDisposition() == null : dispositionNotexist;
		}

		value = rs.getString("DispositionPlace");
		if(factory.isStringNotNullOrNill(value)){
			disposition.setDispositionPlace(converter.getStrMax40Len(value.trim()));
			dispositionNotexist = dispositionNotexist ? disposition.getDispositionPlace() == null : dispositionNotexist;
		}

		value = rs.getString("DispositionPlaceType");
		if(factory.isStringNotNullOrNill(value)){
			disposition.setDispositionPlaceType(value.trim());
			dispositionNotexist = dispositionNotexist ? disposition.getDispositionPlaceType() == null : dispositionNotexist;
		}

		String street = rs.getString("DispositionStreet");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(street)){
			street = street.trim();
		}
		String city = rs.getString("DispositionCity");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(city)){
			city = city.trim();
		}
		String county = rs.getString("DispositionCounty");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(county)){
			county = county.trim();
		}
		String state = rs.getString("DispositionState");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(state)){
			state = state.trim();
		}
		String zip = rs.getString("DispositionZip");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(zip)){
			zip = zip.trim();
		}
		disposition.setAddress(factory.createDeStandardAddressType(street, city, 
				county, state, zip, null));
		dispositionNotexist = dispositionNotexist ? disposition.getAddress() == null : dispositionNotexist;

		value = rs.getString("DispositionDate");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			disposition.setDateOfDisposition(date);
			dispositionNotexist = false;
		}

		value = rs.getString("DateEmbalmed");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			disposition.setDateOfDisposition(date);
			dispositionNotexist = false;
		}

		value = rs.getString("Doctor2Name");
		if(factory.isStringNotNullOrNill(value)){
			disposition.setAuthorizingCoroner(converter.getStrMax150Len(value.trim()));
			dispositionNotexist = dispositionNotexist ? disposition.getAuthorizingCoroner() == null : dispositionNotexist;
		}

		if(dispositionNotexist){
			disposition = null;
		}
		return disposition;
	}

	static{
		//certification
		SELECT_CASE_VITALS_INFO += ", NotificationOfExaminerRequired , MEcheckBox , DateSigned , MEdateSigned " +
		", DoctorLicenseNumber, NPCheckBox , NPDateSigned , NPLicenseNumber " +
		", TimeOfDeath , MEpronounDeadDate , MEpronounDeadTime , ReferredToME , ActualPlaceDeath , InpatientDOA " +
		", MEcaseNumber , AttendingPhysician , DoctorName , DoctorStreet , DoctorCity , DoctorState , DoctorZip" +
		", DoctorPhone , DoctorTitle , DoctorAttendFrom , DoctorAttendTo , DoctorAttendLast , DoctorViewedBody" +
		", RegistrarFileDate "  ; 
	}

	private static CertificationInfoType getCertificationType(ResultSet rs,	SimpleDateFormat dateFormat) throws SQLException, ParseException  {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		CertificationInfoType certificationinfo = new CertificationInfoType();
		boolean certificationinfoNotExist = true;
		Date date = null;

		String value = rs.getString("NotificationOfExaminerRequired");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			certificationinfo.setNotificationOfExaminerRequired(value.trim().equalsIgnoreCase("Y"));
			certificationinfoNotExist = certificationinfoNotExist ? certificationinfo.getNotificationOfExaminerRequired() == false : certificationinfoNotExist;
		}

		certificationinfo.setCertificationInfoTypeChoice_type0(getCertificationInfoTypeChoiceType(rs,dateFormat));
		certificationinfoNotExist = certificationinfoNotExist ? certificationinfo.getAtTimeOfDeath() == null : certificationinfoNotExist;

		certificationinfo.setAtTimeOfDeath(getAtTimeOfDeathInfoType(rs,dateFormat));		
		certificationinfoNotExist = certificationinfoNotExist ? certificationinfo.getAtTimeOfDeath() == null : certificationinfoNotExist;

		certificationinfo.setCertifyingPhysician(getCertifyingPhysicianType(rs,dateFormat));
		certificationinfoNotExist = certificationinfoNotExist ? certificationinfo.getAtTimeOfDeath() == null : certificationinfoNotExist;		

		value = rs.getString("RegistrarFileDate");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			certificationinfo.setRegistrarFileDate(date);
			certificationinfoNotExist = false;
		}

		if(certificationinfoNotExist){
			certificationinfo = null;
		}

		return certificationinfo;

	}	
	//MEcheckBox , DateSigned , MEdateSigned " +
	//", DoctorLicenseNumber, NPCheckBox , NPDateSigned , NPLicenseNumber 
	private static CertificationInfoTypeChoice_type0 getCertificationInfoTypeChoiceType(ResultSet rs,
			SimpleDateFormat dateFormat)throws SQLException, ParseException{

		CertificationInfoTypeChoice_type0 choice = new CertificationInfoTypeChoice_type0();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		Date date = null;

		int index = rs.getInt("MEcheckBox");
		if(!rs.wasNull() && index > 0){

			MedicalCertifierInfoType certifier = new MedicalCertifierInfoType();
			String license = rs.getString("DoctorLicenseNumber");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(license)){
				certifier.setLicenseNumber(convertor.getStrMax15Len(license.trim()));
			}
			if(index == 1){
				choice.setPhysician(certifier);
				String physicianDate = rs.getString("DateSigned");
				if(factory.isStringNotNullOrNill(physicianDate)){
					date = dateFormat.parse(physicianDate);
					certifier.setCertifierDateSigned(date);
				}

			}else if(index == 2){
				choice.setMedicalExaminer(certifier);
				String medExmDate = rs.getString("MEdateSigned");
				if(factory.isStringNotNullOrNill(medExmDate)){
					date = dateFormat.parse(medExmDate);
					certifier.setCertifierDateSigned(date);

				}else{
					choice = null;
				}

			}}else{
				index = rs.getInt("NPCheckBox");
				if(!rs.wasNull() && index ==1){
					choice = new CertificationInfoTypeChoice_type0();
					NonPracticeCertifierInfoType nonPractice = new NonPracticeCertifierInfoType();
					String npLicense = rs.getString("NPLicenseNumber");
					if(!rs.wasNull() && factory.isStringNotNullOrNill(npLicense)){
						nonPractice.setLicenseNumber(convertor.getStrMax25Len(npLicense.trim()));
						choice.setNursePractitioner(nonPractice);
					}
					choice.setNursePractitioner(nonPractice);
					String npDate = rs.getString("NPDateSigned");
					if(factory.isStringNotNullOrNill(npDate)){
						date = dateFormat.parse(npDate);
						nonPractice.setCertifierDateSigned(date);
						//NPDateSigned
						// if you have a date set date -> nonPractice & set nonPractice -> choice

						if(choice.getNursePractitioner() == null){
							choice = null;
						}
					}
				}
			}
		return choice;
	}

	private static AtTimeOfDeathInfoType getAtTimeOfDeathInfoType(ResultSet rs, SimpleDateFormat dateFormat)throws SQLException, ParseException {
		AtTimeOfDeathInfoType atTimeOfDeath = new AtTimeOfDeathInfoType();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		boolean atTimeOfDeathNotExists = true;
		Date date = null;

		String value = rs.getString("TimeOfDeath");
		if(factory.isStringNotNullOrNill(value)){
			atTimeOfDeath.setActualOrPresumedTimeOfDeath(convertor.getStrMax10Len(value.trim()));
		}		
		value = rs.getString("MEpronounDeadDate");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			atTimeOfDeath.setPronouncedDeadOn(date);
		}		
		value = rs.getString("MEpronounDeadTime");
		if(factory.isStringNotNullOrNill(value)){
			atTimeOfDeath.setTimePronounced(convertor.getStrMax10Len(value.trim()));
		}
		value = rs.getString("ReferredToME");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			atTimeOfDeath.setMedicalExaminerContacted(value.trim().equalsIgnoreCase("Y"));
		}
		value = rs.getString("ActualPlaceDeath");
		if(factory.isStringNotNullOrNill(value)){
			atTimeOfDeath.setPlaceOfDeath(convertor.getStrMax60Len(value.trim()));
		}
		atTimeOfDeath.setHospital(getHospitalDetailType(rs));
		atTimeOfDeathNotExists = atTimeOfDeathNotExists ? atTimeOfDeath.getHospital() == null : atTimeOfDeathNotExists;

		if(atTimeOfDeathNotExists){
			atTimeOfDeath = null;
		}
		return atTimeOfDeath;
	}

	private static HospitalDetailType getHospitalDetailType(ResultSet rs)throws SQLException {
		HospitalDetailType hospitalDetail = new HospitalDetailType(); 
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		boolean hospitalDetailNotExist = true;

		String value = rs.getString("InpatientDOA");
		if(factory.isStringNotNullOrNill(value)){
			hospitalDetail.setHospitalAccessType(convertor.getStrMax20Len(value.trim()));
			hospitalDetailNotExist = hospitalDetailNotExist ? hospitalDetail.getHospitalAccessType() == null : hospitalDetailNotExist;
		}
		value = rs.getString("MEcaseNumber");
		if(factory.isStringNotNullOrNill(value)){
			hospitalDetail.setCaseNumber(convertor.getStrMax15Len(value.trim()));
			hospitalDetailNotExist = hospitalDetailNotExist ? hospitalDetail.getCaseNumber() == null : hospitalDetailNotExist;
		}
		value = rs.getString("AttendingPhysician");
		if(factory.isStringNotNullOrNill(value)){
			hospitalDetail.setAttendingPhysician(convertor.getStrMax49Len(value.trim()));
			hospitalDetailNotExist = hospitalDetailNotExist ? hospitalDetail.getAttendingPhysician() == null : hospitalDetailNotExist;
		}

		if(hospitalDetailNotExist){
			hospitalDetail = null;
		}

		return hospitalDetail;
	}
	//	DoctorName , DoctorStreet , DoctorCity , DoctorState , DoctorZip" +
	//	", DoctorPhone , DoctorTitle , DoctorAttendFrom , DoctorAttendTo , DoctorAttendLast , DoctorViewedBody"
	private static CertifyingPhysicianInofType getCertifyingPhysicianType(ResultSet rs,
			SimpleDateFormat dateFormat)throws SQLException, ParseException {

		CertifyingPhysicianInofType certifyingPhysician = new CertifyingPhysicianInofType();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		boolean certifyingPhysicianNotExist = true;

		String value = rs.getString("DoctorName");
		if(factory.isStringNotNullOrNill(value)){
			certifyingPhysician.setName(convertor.getStrMax150Len(value.trim()));
		}
		String street = rs.getString("DoctorStreet");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(street)){
			street = street.trim();
		}
		String city = rs.getString("DoctorCity");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(city)){
			city = city.trim();
		}

		String state = rs.getString("DoctorState");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(state)){
			state = state.trim();
		}
		String zip = rs.getString("DoctorZip");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(zip)){
			zip = zip.trim();
		}
		certifyingPhysician.setAddress(factory.createDeStandardAddressType(street, city, 
				null, state, zip, null));

		value = rs.getString("DoctorPhone");
		if(factory.isStringNotNullOrNill(value)){
			certifyingPhysician.setPhone(convertor.getStrMax20Len(value.trim()));
		}
		value = rs.getString("DoctorTitle");
		if(factory.isStringNotNullOrNill(value)){
			certifyingPhysician.setCertifierTitle(convertor.getStrMax10Len(value.trim()));
		}		

		certifyingPhysician.setDatesPhysicianAttended(getDatesPhysicianAttended(rs,dateFormat));
		certifyingPhysicianNotExist = certifyingPhysicianNotExist ? certifyingPhysician.getPhysicianViewedTheBody() == false : certifyingPhysicianNotExist;

		value = rs.getString("DoctorViewedBody");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			certifyingPhysician.setPhysicianViewedTheBody(value.trim().equalsIgnoreCase("Y"));
		}
		if(certifyingPhysicianNotExist){
			certifyingPhysician = null;
		}
		return certifyingPhysician;
	}

	private static PhysicianAttendedDateType getDatesPhysicianAttended(ResultSet rs,
			SimpleDateFormat dateFormat) throws SQLException, ParseException{

		PhysicianAttendedDateType dateAttended = new PhysicianAttendedDateType();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		Date date = null;
		String value = rs.getString("DoctorAttendFrom");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			dateAttended.setFromDate(date);
		}	
		value = rs.getString("DoctorAttendTo");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			dateAttended.setToDate(date);
		}	
		value = rs.getString("DoctorAttendLast");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			dateAttended.setLastDate(date);
		}	
		return dateAttended;
	}


	static{
		//cause of death
		SELECT_CASE_VITALS_INFO += ", CauseOfDeath1, CauseInterval1, CauseOfDeath2, CauseInterval2" +
		", CauseOfDeath3, CauseInterval3, CauseOfDeath4, CauseInterval4, Tobacco, PregnantAtDeath" +
		", OtherConditions, AccidSuicideNaturl, BodyFoundMore24Hr, Autopsy, AutopsyFindings, HospiceStatus "  ; 
	}

	private static CauseOfDeathType getCauseOfDeathType(ResultSet rs,	SimpleDateFormat dateFormat) throws SQLException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		CauseOfDeathType causeOfDeath = new CauseOfDeathType();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		boolean causeOfDeathNotExist = true;


		causeOfDeath.setCause(getCauseType(rs));
		causeOfDeathNotExist = causeOfDeathNotExist ? causeOfDeath.getCause() == null : causeOfDeathNotExist;

		String value = rs.getString("Tobacco");
		if(factory.isStringNotNullOrNill(value)){
			causeOfDeath.setTobaccoUser(convertor.getCompleteAnswerByCode(value.trim()));
		}
		causeOfDeathNotExist = causeOfDeathNotExist ? causeOfDeath.getTobaccoUser() == null : causeOfDeathNotExist;

		value = rs.getString("PregnantAtDeath");
		if(factory.isStringNotNullOrNill(value)){
			causeOfDeath.setPregnantAtDeath(value.trim());
			causeOfDeathNotExist = causeOfDeathNotExist ? causeOfDeath.getTobaccoUser() == null : causeOfDeathNotExist;
		}

		value = rs.getString("OtherConditions");
		if(factory.isStringNotNullOrNill(value)){
			causeOfDeath.setOtherCondition(convertor.getStrMax50Len(value.trim()));
			causeOfDeathNotExist = causeOfDeathNotExist ? causeOfDeath.getOtherCondition() == null : causeOfDeathNotExist;
		}

		value = rs.getString("AccidSuicideNaturl");
		if(factory.isStringNotNullOrNill(value)){
			causeOfDeath.setTypeOfDeath(convertor.getStrMax50Len(value.trim()));
			causeOfDeathNotExist = causeOfDeathNotExist ? causeOfDeath.getTypeOfDeath() == null : causeOfDeathNotExist;
		}

		value = rs.getString("BodyFoundMore24Hr");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			causeOfDeath.setWasBodyFoundMore24Hr(value.trim().equalsIgnoreCase("Y"));
			causeOfDeathNotExist = causeOfDeathNotExist ? causeOfDeath.getWasBodyFoundMore24Hr() == false : causeOfDeathNotExist;
		}

		value = rs.getString("Autopsy");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			causeOfDeath.setWasAnAutopsyPerformed(value.trim().equalsIgnoreCase("Y"));
			causeOfDeathNotExist = causeOfDeathNotExist ? causeOfDeath.getWasAnAutopsyPerformed() == false : causeOfDeathNotExist;
		}

		value = rs.getString("AutopsyFindings");
		if(factory.isStringNotNullOrNill(value)){
			causeOfDeath.setWereAutopsyFindingsAvailablePriorToCauseOfDeath(convertor.getYesNoAnswerByCode(value.trim()));
			causeOfDeathNotExist = causeOfDeathNotExist ? causeOfDeath.getWereAutopsyFindingsAvailablePriorToCauseOfDeath() == null : causeOfDeathNotExist;
		}

		value = rs.getString("HospiceStatus");
		if(factory.isStringNotNullOrNill(value)){
			causeOfDeath.setHospiceStatus(convertor.getYesNoAnswerByCode(value.trim()));
			causeOfDeathNotExist = causeOfDeathNotExist ? causeOfDeath.getHospiceStatus() == null : causeOfDeathNotExist;
		}				

		if(causeOfDeathNotExist){
			causeOfDeath = null;
		}		
		return causeOfDeath;		
	}	

	static{
		//medical Examiner
		SELECT_CASE_VITALS_INFO += ", InjuryDate, InjuryTime, InjuryDescription, InjuryAtWork, InjuryPlace" +
		", InjuryTransportation, InjuryStreet, InjuryCityState "  ; 
	}

	private static MedicalExaminerType getMedicalExaminerType(ResultSet rs,
			SimpleDateFormat dateFormat)throws SQLException, ParseException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		MedicalExaminerType medicalExaminer = new MedicalExaminerType();
		boolean medicalExaminerNotExist = true;
		Date date = null;

		String value = rs.getString("InjuryDate");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			medicalExaminer.setInjuryDate(date);
			medicalExaminerNotExist = medicalExaminerNotExist ? medicalExaminer.getInjuryDate() == null : medicalExaminerNotExist;
		}

		value = rs.getString("InjuryTime");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			medicalExaminer.setInjuryTime(convertor.getStrMax10Len(value.trim()));
			medicalExaminerNotExist = medicalExaminerNotExist ? medicalExaminer.getInjuryDate() == null : medicalExaminerNotExist;
		}

		value = rs.getString("InjuryDescription");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			medicalExaminer.setInjuryDescription(convertor.getStrMax60Len(value.trim()));
			medicalExaminerNotExist = medicalExaminerNotExist ? medicalExaminer.getInjuryDescription() == null : medicalExaminerNotExist;
		}

		value = rs.getString("InjuryAtWork");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			medicalExaminer.setInjuryAtWork(value.trim().equalsIgnoreCase("Y"));
			medicalExaminerNotExist = false;
		}

		value = rs.getString("InjuryPlace");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			medicalExaminer.setInjuryPlace(convertor.getStrMax50Len(value.trim()));
			medicalExaminerNotExist = medicalExaminerNotExist ? medicalExaminer.getInjuryPlace() == null : medicalExaminerNotExist;
		}

		value = rs.getString("InjuryTransportation");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			medicalExaminer.setDecedentPositionForTransportInjury(convertor.getStrMax100Len(value.trim()));
			medicalExaminerNotExist = medicalExaminerNotExist ? medicalExaminer.getDecedentPositionForTransportInjury() == null : medicalExaminerNotExist;
		}

		String street = rs.getString("InjuryStreet");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(street)){
			street = street.trim();
		}
		String city = null;
		String state = null;
		String citystate = rs.getString("InjuryCityState");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(citystate.trim())){
			String[] adress = citystate.trim().split(",");
			if(adress.length>0){
				city = adress[0].trim();
			}
			if(adress.length>1){
				state = adress[1].trim().toUpperCase();
			}
		}	
		medicalExaminer.setInjuryLocation(factory.createDeStandardAddressType(street, city, 
				null, state, null, null));
		medicalExaminerNotExist = medicalExaminerNotExist ? medicalExaminer.getInjuryLocation() == null : medicalExaminerNotExist;

		if(medicalExaminerNotExist){
			medicalExaminer = null;
		}

		return medicalExaminer;
	}

	private static CauseType[] getCauseType(ResultSet rs) throws SQLException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		List<CauseType> casues = new ArrayList<CauseType>(4);

		for(int i = 1; i<5; i++){

			String cause = rs.getString("CauseOfDeath" + i);
			if(factory.isStringNotNullOrNill(cause)){
				cause = cause.trim();
			}else{
				cause = null;
			}

			String interval = rs.getString("CauseInterval" + i);
			if(factory.isStringNotNullOrNill(interval)){
				interval = interval.trim();
			}else{
				interval = null;
			}
			if(cause!=null || interval != null){
				CauseType causeType = new CauseType();
				causeType.setDueTo(convertor.getStrMax49Len(cause));
				causeType.setInterval(convertor.getStrMax14Len(interval));
				casues.add(causeType);
			}
		}
		CauseType[] cause = casues.size()>0 ? casues.toArray(new CauseType[casues.size()]):null;

		return cause;
	}



	static{
		//service info
		SELECT_CASE_SERVICES_INFO += ", TimeOfService, DayOfWeek, PlaceOfService, PlaceOfServStreet " +
		", PlaceOfServCity, PlaceOfServState ";
	}

	private static ServiceInfoType getServiceInfoType(ResultSet rs,	SimpleDateFormat dateFormat) throws SQLException, ParseException {

		Map<String, String> columnKeys = new HashMap<String, String>(7);
		columnKeys.put("time", "TimeOfService");
		columnKeys.put("date", "BurialDate");
		columnKeys.put("dayOfweek", "DayOfWeek");

		List<String> streets = new ArrayList<String>(2);
		streets.add("PlaceOfService");
		streets.add("PlaceOfServStreet");

		columnKeys.put("city", "PlaceOfServCity");
		columnKeys.put("state", "PlaceOfServState");

		ServiceInfoType service = new ServiceInfoType();
		if(!setServiceInfoType(rs, service, columnKeys, streets, dateFormat)){
			return null;
		}
		return service;
	}

	static{
		//additional service info
		SELECT_CASE_SERVICES_INFO += ",AddnlServiceTime, AddnlServiceDate, AddnlServiceDay, " +
		" AddnlServicePlace, AddnlServiceStreet, AddnlServiceCity, AddnlServiceState, AddnlService ";
	}

	private static AdditionalServiceType getAdditionalServiceType(ResultSet rs,	SimpleDateFormat dateFormat) throws SQLException, ParseException {

		ConvertorUtil convertor = ConvertorUtil.getInstance();
		Map<String, String> columnKeys = new HashMap<String, String>();
		columnKeys.put("time", "AddnlServiceTime");
		columnKeys.put("date", "AddnlServiceDate");
		columnKeys.put("dayOfweek", "AddnlServiceDay");

		List<String> streets = new ArrayList<String>(2);
		streets.add("AddnlServicePlace");
		streets.add("AddnlServiceStreet");

		columnKeys.put("city", "AddnlServiceCity");
		columnKeys.put("state", "AddnlServiceState");

		AdditionalServiceType service = new AdditionalServiceType();
		boolean addlServiceNotExist = !setServiceInfoType(rs, service, columnKeys, streets, dateFormat);

		String value = rs.getString("AddnlService");
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			service.setServiceName(convertor.getStrMax30Len(value.trim()));
			addlServiceNotExist = false;
		}

		if(addlServiceNotExist){
			service = null;
		}

		return service;
	}

	static{
		//cemetery info
		SELECT_CASE_SERVICES_INFO += ",CemeteryName, CemeteryStreet, CemeteryCity, CemeteryCounty " +
		",CemeteryState, CemeteryZip, CemeteryPhone, CemeteryVault " +
		", CemeterySection, CemeteryBlockNumber, CemeteryLot, CemeteryGrave" +
		", TentEquipment, SetSeal, OpenCloseBox, CemeteryTime ";
	}

	private static CemeteryType getCemeteryType(ResultSet rs,	SimpleDateFormat dateFormat) throws SQLException, ParseException  {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		CemeteryType cemetery = new CemeteryType();
		boolean cemeteryNotExist = true;

		String value = rs.getString("CemeteryName");
		if(factory.isStringNotNullOrNill(value)){
			cemetery.setName(convertor.getStrMax49Len(value.trim()));
			cemeteryNotExist = cemeteryNotExist ? cemetery.getName() == null : cemeteryNotExist;
		}
		List<String> streets = new ArrayList<String>(1);
		value = rs.getString("CemeteryStreet");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			streets.add(value.trim());
		}

		String city = rs.getString("CemeteryCity");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(city)){
			city = city.trim();
		}
		String county = rs.getString("CemeteryCounty");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(county)){
			county = county.trim();
		}
		String state = rs.getString("CemeteryState");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(state)){
			state = state.trim();
		}
		String zip = rs.getString("CemeteryZip");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(zip)){
			zip = zip.trim();

		}		
		cemetery.setAddress(factory.createAddressType(streets, city,
				county, state, zip));		
		cemeteryNotExist = cemeteryNotExist ? cemetery.getAddress() == null : cemeteryNotExist;

		value = rs.getString("CemeteryPhone");
		if(factory.isStringNotNullOrNill(value)){
			cemetery.setPhone(convertor.getStrMax20Len(value.trim()));
			cemeteryNotExist = cemeteryNotExist ? cemetery.getPhone() == null : cemeteryNotExist;
		}

		value = rs.getString("CemeteryVault");
		if(factory.isStringNotNullOrNill(value)){
			cemetery.setVault(convertor.getStrMax30Len(value.trim()));
			cemeteryNotExist = cemeteryNotExist ? cemetery.getVault() == null : cemeteryNotExist;
		}

		value = rs.getString("CemeterySection");
		if(factory.isStringNotNullOrNill(value)){
			cemetery.setSection(convertor.getStrMax29Len(value.trim()));
			cemeteryNotExist = cemeteryNotExist ? cemetery.getSection() == null : cemeteryNotExist;
		}

		value = rs.getString("CemeteryBlockNumber");
		if(factory.isStringNotNullOrNill(value)){
			cemetery.setBlock(convertor.getStrMax30Len(value.trim()));
			cemeteryNotExist = cemeteryNotExist ? cemetery.getBlock() == null : cemeteryNotExist;
		}

		value = rs.getString("CemeteryLot");
		if(factory.isStringNotNullOrNill(value)){
			cemetery.setLot(convertor.getStrMax20Len(value.trim()));
			cemeteryNotExist = cemeteryNotExist ? cemetery.getLot() == null : cemeteryNotExist;
		}

		value = rs.getString("CemeteryGrave");
		if(factory.isStringNotNullOrNill(value)){
			cemetery.setGraveNo(convertor.getStrMax10Len(value.trim()));
			cemeteryNotExist = cemeteryNotExist ? cemetery.getGraveNo() == null : cemeteryNotExist;
		}
		//TentEquipment, SetSeal

		value = rs.getString("TentEquipment");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			cemetery.setTentEquipment(value.trim().equalsIgnoreCase("Y"));
			cemeteryNotExist = cemeteryNotExist ? cemetery.getTentEquipment() == false : cemeteryNotExist;
		}

		value = rs.getString("SetSeal");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			cemetery.setSetSeal(value.trim().equalsIgnoreCase("Y"));
			cemeteryNotExist = cemeteryNotExist ? cemetery.getSetSeal() == false : cemeteryNotExist;
		}

		value = rs.getString("OpenCloseBox");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			cemetery.setOpenClose(value.trim().equalsIgnoreCase("Y"));
			cemeteryNotExist = cemeteryNotExist ? cemetery.getOpenClose() == false : cemeteryNotExist;
		}

		value = rs.getString("CemeteryTime");
		if(factory.isStringNotNullOrNill(value)){
			cemetery.setTime(convertor.getStrMax10Len(value.trim()));
			cemeteryNotExist = cemeteryNotExist ? cemetery.getTime() == null : cemeteryNotExist;
		}

		if(cemeteryNotExist){
			cemetery = null;
		}

		return cemetery;

	}

	static{
		//crematory info
		//CremationDateOfService,CrematoryName,CrematoryStreet,
		//CrematoryCity,CrematoryState,CrematoryCounty,CrematoryZip,
		SELECT_CASE_SERVICES_INFO +=", CremationDateOfService, servicedata.CrematoryName, CrematoryStreet " +
		", CrematoryCity, CrematoryState, CrematoryCounty, CrematoryZip ";
	}

	private static CrematoryType getCrematoryType(ResultSet rs,	SimpleDateFormat dateFormat) throws SQLException, ParseException  {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		CrematoryType crematory = new CrematoryType();
		boolean crematoryNotExist = true;
		Date date = null;

		String value = rs.getString("CremationDateOfService");
		if(factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			crematory.setDateOfService(date);
			crematoryNotExist = crematoryNotExist ? crematory.getDateOfService() == null : crematoryNotExist;
		}

		value = rs.getString("CrematoryName");
		if(factory.isStringNotNullOrNill(value)){
			crematory.setName(convertor.getStrMax49Len(value.trim()));
			crematoryNotExist = crematoryNotExist ? crematory.getName() == null : crematoryNotExist;
		}

		List<String> streets = new ArrayList<String>(1);
		value = rs.getString("CrematoryStreet");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			streets.add(value.trim());
		}

		String city = rs.getString("CrematoryCity");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(city)){
			city = city.trim();
		}		

		String state = rs.getString("CrematoryState");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(state)){
			state = state.trim();
		}

		String county = rs.getString("CrematoryCounty");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(county)){
			county = county.trim();
		}
		String zip = rs.getString("CrematoryZip");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(zip)){
			zip = zip.trim();

		}		
		crematory.setAddress(factory.createAddressType(streets, city,
				county, state, zip));		
		crematoryNotExist = crematoryNotExist ? crematory.getAddress() == null : crematoryNotExist;

		if(crematoryNotExist){
			crematory = null;
		}

		return crematory;

	}


	static{
		//church  info
		//Church,ChurchStreet,ChurchCityStZip

		SELECT_CASE_SERVICES_INFO +=", Church, ChurchStreet, ChurchCityStZip " ;
	}

	private static ChurchType getChurchType(ResultSet rs,	SimpleDateFormat dateFormat) throws SQLException, ParseException  {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		ChurchType church = new ChurchType();
		boolean churchNotExist = true;

		String value = rs.getString("Church");
		if(factory.isStringNotNullOrNill(value)){
			church.setName(convertor.getStrMax49Len(value.trim()));
			churchNotExist = churchNotExist ? church.getName() == null : churchNotExist;
		}			
		{
			ArrayList<String> streets = new ArrayList<String>(1);
			value = rs.getString("ChurchStreet");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
				streets.add(value.trim());
			}				
			String state = null;
			String zip = null;
			String city = null;
			value = rs.getString("ChurchCityStZip");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
				String[] adress = value.split(",");
				if(adress.length>0){
					city = adress[0].trim();
				}
				if(adress.length>1){
					state = adress[1].trim().toUpperCase();
				}
				if(adress.length>2){
					zip = adress[2].trim();
				}
			}				
			church.setAddress(factory.createAddressType(streets, city, null, state, zip ));
			churchNotExist = churchNotExist ? church.getAddress() == null : churchNotExist;
		}		

		if(churchNotExist){
			church = null;
		}
		return church;			
	}
	static{
		//minister info
		//Minister1,Minister2,Organist,Soloist,JewelryInstructions,Songs,HairStyle
		SELECT_CASE_SERVICES_INFO +=", Minister1, Minister2, Organist " +
		", Soloist, JewelryInstructions, Songs, HairStyle ";
	}

	private static MinistersType getMinistersType(ResultSet rs,	SimpleDateFormat dateFormat) throws SQLException, ParseException  {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		MinistersType minister = new MinistersType();
		boolean ministerNotExist = true;

		String value = rs.getString("Minister1");
		if(factory.isStringNotNullOrNill(value)){
			minister.setMinister1(convertor.getStrMax29Len(value.trim()));
			ministerNotExist = ministerNotExist ? minister.getMinister1() == null : ministerNotExist;
		}

		value = rs.getString("Minister2");
		if(factory.isStringNotNullOrNill(value)){
			minister.setMinister2(convertor.getStrMax29Len(value.trim()));
			ministerNotExist = ministerNotExist ? minister.getMinister2() == null : ministerNotExist;
		}

		value = rs.getString("Organist");
		if(factory.isStringNotNullOrNill(value)){
			minister.setOrganist(convertor.getStrMax49Len(value.trim()));
			ministerNotExist = ministerNotExist ? minister.getOrganist() == null : ministerNotExist;
		}

		value = rs.getString("Soloist");
		if(factory.isStringNotNullOrNill(value)){
			minister.setSoloist(convertor.getStrMax49Len(value.trim()));
			ministerNotExist = ministerNotExist ? minister.getSoloist() == null : ministerNotExist;
		}

		value = rs.getString("JewelryInstructions");
		if(factory.isStringNotNullOrNill(value)){
			minister.setJewelry(convertor.getStrMax100Len(value.trim()));
			ministerNotExist = ministerNotExist ? minister.getJewelry() == null : ministerNotExist;
		}

		value = rs.getString("Songs");
		if(factory.isStringNotNullOrNill(value)){
			minister.setSongs(convertor.getStrMax150Len(value.trim()));
			ministerNotExist = ministerNotExist ? minister.getSongs() == null : ministerNotExist;
		}

		value = rs.getString("HairStyle");
		if(factory.isStringNotNullOrNill(value)){
			minister.setHairStyle(convertor.getStrMax29Len(value.trim()));
			ministerNotExist = ministerNotExist ? minister.getHairStyle() == null : ministerNotExist;
		}

		if(ministerNotExist){
			minister = null;
		}

		return minister;

	}

	static{
		//flower info
		//FlowerDescription,FlowerSupplier,FlowerDelivery,
		SELECT_CASE_SERVICES_INFO +=", FlowerDescription, FlowerSupplier, FlowerDelivery " ;
	}

	private static FlowerArrangements_type0 getFlowerArrangementsType(ResultSet rs,	SimpleDateFormat dateFormat) throws SQLException, ParseException  {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		FlowerArrangements_type0 flower = new FlowerArrangements_type0();
		boolean flowerNotExist = true;

		String value = rs.getString("FlowerDescription");
		if(factory.isStringNotNullOrNill(value)){
			flower.setDescription(convertor.getStrMax200Len(value.trim()));
			flowerNotExist = flowerNotExist ? flower.getDescription() == null : flowerNotExist;
		}

		value = rs.getString("FlowerSupplier");
		if(factory.isStringNotNullOrNill(value)){
			flower.setSupplierInfo(convertor.getStrMax200Len(value.trim()));
			flowerNotExist = flowerNotExist ? flower.getSupplierInfo() == null : flowerNotExist;
		}

		value = rs.getString("FlowerDelivery");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){			
			flower.setIsDelivery(value.trim().equalsIgnoreCase("Y"));
			flowerNotExist = flowerNotExist ? flower.getIsDelivery() == false : flowerNotExist;
		}	
		if(flowerNotExist){
			flower = null;
		}

		return flower;		
	}

	static{
		//other info
		//Visitation,Autos,Donations,SpecialInstructions,SpecialService,
		SELECT_CASE_SERVICES_INFO +=", Visitation, Autos, Donations, SpecialInstructions" +
		", SpecialService " ;
	}

	private static OtherInfoType getOtherInfoType(ResultSet rs,	SimpleDateFormat dateFormat) throws SQLException, ParseException  {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		OtherInfoType otherinfo = new OtherInfoType();
		boolean otherinfoNotExist = true;

		String value = rs.getString("Visitation");
		if(factory.isStringNotNullOrNill(value)){
			otherinfo.setVisitationDetails(convertor.getStrMax200Len(value.trim()));
			otherinfoNotExist = otherinfoNotExist ? otherinfo.getVisitationDetails() == null : otherinfoNotExist;
		}
		value = rs.getString("Autos");
		if(factory.isStringNotNullOrNill(value)){
			otherinfo.setStaffAuto(convertor.getStrMax150Len(value.trim()));
			otherinfoNotExist = otherinfoNotExist ? otherinfo.getStaffAuto() == null : otherinfoNotExist;
		}
		value = rs.getString("Donations");
		if(factory.isStringNotNullOrNill(value)){
			otherinfo.setDonations(convertor.getStrMax200Len(value.trim()));
			otherinfoNotExist = otherinfoNotExist ? otherinfo.getDonations() == null : otherinfoNotExist;
		}
		value = rs.getString("SpecialInstructions");
		if(factory.isStringNotNullOrNill(value)){
			otherinfo.setSpecialInstructions(convertor.getStrMax200Len(value.trim()));
			otherinfoNotExist = otherinfoNotExist ? otherinfo.getSpecialInstructions() == null : otherinfoNotExist;
		}
		value = rs.getString("SpecialService");
		if(factory.isStringNotNullOrNill(value)){
			otherinfo.setNotes(convertor.getStrMax200Len(value.trim()));
			otherinfoNotExist = otherinfoNotExist ? otherinfo.getNotes() == null : otherinfoNotExist;
		}

		if(otherinfoNotExist){
			otherinfo = null;
		}
		return otherinfo;

	}



	private static boolean setServiceInfoType(ResultSet rs, ServiceInfoType service, 
			Map<String, String> columns, List<String> streetKeys, 
			SimpleDateFormat dateFormat)throws SQLException, ParseException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		boolean setData = false;

		service.setDateTimeOfService(getDateTimeOfServiceType(rs, columns, dateFormat));
		setData = setData ? setData : service.getDateTimeOfService() != null; 

		ArrayList<String> streets = new ArrayList<String>(streetKeys.size());
		String street = null;

		for(String streetKey : streetKeys){
			street = rs.getString(streetKey);
			if(!rs.wasNull() && factory.isStringNotNullOrNill(street)){
				streets.add(street.trim());
			}
		}

		String key = columns.get("city");
		String city = key != null ? rs.getString(key): null;
		if(!rs.wasNull() && factory.isStringNotNullOrNill(city)){
			city = city.trim();
		}

		key = columns.get("state");
		String state = key != null ? rs.getString(key): null;
		if(!rs.wasNull() && factory.isStringNotNullOrNill(state)){
			state = state.trim();
		}
		service.setPlaceOfService(factory.createAddressType(streets, city, null, state, null));
		setData = setData ? setData : service.getPlaceOfService() != null ;

		return setData;
	}

	private static DateTimeOfServiceType getDateTimeOfServiceType(ResultSet rs, Map<String, String> columns, SimpleDateFormat dateFormat)throws SQLException, ParseException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();

		DateTimeOfServiceType dateTime = new DateTimeOfServiceType();
		boolean dateTimeNotExist = true;

		String key = columns.get("time");
		String value = key != null ? rs.getString(key): null;
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			dateTime.setTime(convertor.getStrMax14Len(value.trim()));
			dateTimeNotExist = dateTimeNotExist ? dateTime.getTime() == null : dateTimeNotExist;
		}

		key = columns.get("date");
		value = key != null ? rs.getString(key): null;
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			Date date = dateFormat.parse(value.trim());
			dateTime.setDate(date);
			dateTimeNotExist = false;
		}

		key = columns.get("dayOfweek");
		value = key != null ? rs.getString(key): null;
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			dateTime.setDayOfweek(convertor.getStrMax10Len(value.trim()));
			dateTimeNotExist = false;
		}

		if(dateTimeNotExist){
			dateTime = null;
		}
		return dateTime;
	}

	public static String SELECT_MISC_SALES = "SELECT locationId ";
	public static String SELECT_TRANSACTION_HISTORY = ", TranHistID, Description , AmountOfTran " +
	", ARacct , GLacct , TaxCode , DateOfTran , SPFcode , ItemCategory " +
	", OriginalPostingYN , InvCostOfSale , PaymentMethod , ReceiptNumber " +
	", DatetimeOfTrans , ManualReceiptNo";

	public static MiscSaleType updateMiscSaleType(ResultSet rs, FuneralHomeIdType funeralHomeId, MiscSaleType miscSale) throws SQLException{

		miscSale.setFuneralHomeId(funeralHomeId);
		setTransaction(rs, miscSale, true);

		String value = rs.getString("ManualReceiptNo");
		if(FddeXsdFactory.getInstance().isStringNotNullOrNill(value)){
			miscSale.setManualReceiptNo(ConvertorUtil.getInstance().getStrMax10Len(value.trim()));
		}
		return miscSale;
	}

	public static void setTransaction(ResultSet rs, TransactionType transaction, boolean miscSale) throws SQLException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();

		int id = rs.getInt("TranHistID");
		transaction.setTranId(id);

		String value = rs.getString("Description");
		if(factory.isStringNotNullOrNill(value)){
			transaction.setDescription(convertor.getStrMax50Len(value.trim()));
		}

		long amount = rs.getLong("AmountOfTran");
		if(!rs.wasNull() && amount != 0){
			transaction.setAmount(factory.getDeAmountType(amount/100));
		}

		value = rs.getString("ARacct");
		if(factory.isStringNotNullOrNill(value)){
			transaction.setARAccount(convertor.getStrMax30Len(value.trim()));
		}

		value = rs.getString("GLacct");
		if(factory.isStringNotNullOrNill(value)){
			transaction.setGLAccount(convertor.getStrMax30Len(value.trim()));
		}

		value = rs.getString("TaxCode");
		if(factory.isStringNotNullOrNill(value)){
			transaction.setTaxStatus(convertor.getStrMax08Len(value.trim()));
		}

		Date date = rs.getDate("DateOfTran");
		if(!rs.wasNull()){
			transaction.setTransactionDate(date);
		}

		if(miscSale){
			transaction.setTransactionType(TransactionTypeType.Miscellaneous);
		}else{
			value = rs.getString("SPFcode");
			if(factory.isStringNotNullOrNill(value)){
				value = value.trim();
				if(value.equals("S")){
					transaction.setTransactionType(TransactionTypeType.Sale);
				}else if(value.equals("P")){
					transaction.setTransactionType(TransactionTypeType.Payment);
				}else if(value.equals("F")){
					transaction.setTransactionType(TransactionTypeType.Finance);
				}
			}
		}

		value = rs.getString("ItemCategory");
		if(factory.isStringNotNullOrNill(value)){
			transaction.setItemCode(convertor.getStrMax50Len(value.trim()));
		}

		value = rs.getString("OriginalPostingYN");
		if(factory.isStringNotNullOrNill(value)){
			transaction.setInitialSaleflag("Y".equalsIgnoreCase(value.trim()));
		}

		amount = rs.getLong("InvCostOfSale");
		if(!rs.wasNull() && amount != 0){
			transaction.setCostOfItem(factory.getDeAmountType(amount/100));
		}

		value = rs.getString("PaymentMethod");
		if(factory.isStringNotNullOrNill(value)){
			transaction.setPayMethod(convertor.getStrMax30Len(value.trim()));
		}

		id = rs.getInt("ReceiptNumber");
		if(!rs.wasNull() && id != 0){
			transaction.setReceiptNo(id);
		}

		date = rs.getDate("DatetimeOfTrans");
		if(!rs.wasNull()){
			transaction.setDateStamped(date);
		}
	}

	public static String SELECT_UNPOSTED_CHARGES = " SELECT Autoindex, Description , Amount, ARacct " +
	", GLacct , TaxCode , SPFcode , CategoryCode, OriginalPrice ";

	public static ChargeType updateChargeType(ResultSet rs, ChargeType charge) throws SQLException{

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();

		int id = rs.getInt("Autoindex");
		charge.setSaleId(id);

		String value = rs.getString("Description");
		if(factory.isStringNotNullOrNill(value)){
			charge.setDescription(convertor.getStrMax50Len(value.trim()));
		}

		long amount = rs.getLong("Amount");
		if(!rs.wasNull() && amount != 0){
			charge.setAmount(factory.getDeAmountType(amount/100));
		}

		value = rs.getString("ARacct");
		if(factory.isStringNotNullOrNill(value)){
			charge.setARAccount(convertor.getStrMax30Len(value.trim()));
		}

		value = rs.getString("GLacct");
		if(factory.isStringNotNullOrNill(value)){
			charge.setGLAccount(convertor.getStrMax30Len(value.trim()));
		}

		value = rs.getString("TaxCode");
		if(factory.isStringNotNullOrNill(value)){
			charge.setTaxStatus(convertor.getStrMax10Len(value.trim()));
		}

		value = rs.getString("SPFcode");
		if(factory.isStringNotNullOrNill(value)){
			value = value.trim();
			if(value.equals("S")){
				charge.setTransactionType(TransactionTypeType.Sale);
			}else if(value.equals("P")){
				charge.setTransactionType(TransactionTypeType.Payment);
			}else if(value.equals("F")){
				charge.setTransactionType(TransactionTypeType.Finance);
			}
		}

		value = rs.getString("CategoryCode");
		if(factory.isStringNotNullOrNill(value)){
			charge.setItemCode(convertor.getStrMax50Len(value.trim()));
		}

		amount = rs.getLong("OriginalPrice");
		if(!rs.wasNull() && amount != 0){
			charge.setCostOfItem(factory.getDeAmountType(amount/100));
		}

		return charge;
	}

	public static String SELECT_PRENEED_DISPURSEMENTS = 
		" SELECT CONCAT(?, preneeddisbursement.vitalsmasterkey) AS caseId, label, VALUE "; 

	public static void setDisbursements(CaseType fhCase,
			List<DisbursementType> disbursements) {
		CallInfoType callInfo = fhCase.getCallInfo();
		if(callInfo != null){
			PreNeedInfoType preNeddInfo = callInfo.getPreNeedInfo();
			if(preNeddInfo == null){
				preNeddInfo = new PreNeedInfoType();
				callInfo.setPreNeedInfo(preNeddInfo);
			}
			SalesInfoType salesInfo = preNeddInfo.getSalesInfo();
			if(salesInfo==null){
				salesInfo = new SalesInfoType();
				preNeddInfo.setSalesInfo(salesInfo);
			}
			Disbursements_type0 disbursement = new Disbursements_type0();
			disbursement.setEntry(disbursements.toArray(new DisbursementType[disbursements.size()]));
			salesInfo.setDisbursements(disbursement);
		}
	}

	public static DisbursementType getDisbursementType(ResultSet rs) throws SQLException {
		DisbursementType disbursement = new DisbursementType();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();


		String value = rs.getString("label");
		if(factory.isStringNotNullOrNill(value)){			
			disbursement.setDisbursement(convertor.getStrMax30Len(value));
		}
		float amount = rs.getFloat("VALUE");
		if(!rs.wasNull() && amount != 0){
			disbursement.setAmount(factory.getDeAmountType(amount));
		}
		return disbursement;
	}

	public static String SELECT_INVENTORY_ON_HAND = 
		"SELECT Identity, ItemName, SerialNumber, ShowRoom " +
		", Location, DateIn, InvoiceNumber,  Quantity, Cost, Notes ";

	public static InvItemType updateInvItemType(ResultSet rs, SimpleDateFormat dateFormat, InvItemType invItem) throws SQLException, ParseException{

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();

		invItem.setId(rs.getInt("Identity"));

		String value = rs.getString("ItemName");
		if(factory.isStringNotNullOrNill(value)){
			invItem.setName(convertor.getStrMax40Len(value.trim()));
		}

		value = rs.getString("SerialNumber");
		if(factory.isStringNotNullOrNill(value)){
			invItem.setSerialNumber(convertor.getStrMax25Len(value.trim()));
		}

		value = rs.getString("ShowRoom");
		if(factory.isStringNotNullOrNill(value)){
			invItem.setInShowRoom(convertor.getYesNoAnswerByCode(value.trim()));
		}

		value = rs.getString("Location");
		if(factory.isStringNotNullOrNill(value)){
			invItem.setLocation(convertor.getStrMax24Len(value.trim()));
		}

		value = rs.getString("DateIn");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			Date date = dateFormat.parse(value.trim());
			invItem.setDateIn(date);
		}

		value = rs.getString("InvoiceNumber");
		if(factory.isStringNotNullOrNill(value)){
			invItem.setInvoiceNo(convertor.getStrMax10Len(value.trim()));
		}

		int number = rs.getInt("Quantity");
		if(!rs.wasNull()){
			invItem.setQuantity(number);
		}

		long cents = rs.getLong("Cost");
		if(!rs.wasNull() && cents != 0){
			invItem.setCost(factory.getDeAmountType(cents/100));
		}

		value = rs.getString("Notes");
		if(factory.isStringNotNullOrNill(value)){
			invItem.setNotes(convertor.getStrMax100Len(value.trim()));
		}
		return invItem;		
	}


	public static String SELECT_ARFIELDS1 = " ,LocationID, LocationNum, SalesDescCDID " +
	", (combodata.name) AS SalesName , SaleType , TotalCharges , TotalPaidToDate, ServiceDateKey " +
	", DateOfDeath, State, (TotalCharges-TotalPaidToDate) AS Balance " ;

	public static final int AtNeedCase = 1;
	public static final int PreNeedNeedServicedCase = 2;
	public static final int PreNeedActiveCase = 3;
	public static final int PreNeedCanceledCase = 4;

	//identifying the case type as Pre need or At need		
	public static int identifyCase(ResultSet rs) throws SQLException{

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		int caseType = -1;	
		String value = rs.getString("PNPreneedStatus");	
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){		
			value = value.trim();
			if(value.equalsIgnoreCase("A")){
				caseType = PreNeedActiveCase;
			}else if(value.equalsIgnoreCase("S")){
				caseType = PreNeedNeedServicedCase;
			}else if(value.equalsIgnoreCase("C")){
				caseType = PreNeedCanceledCase;
			}else{
				caseType = AtNeedCase;
			}
		}else{
			// atNeed
			caseType = AtNeedCase;
		}
		return caseType;
	}
	// setting the values for web service
	public static AccountsRecievable updateARFieldType(ResultSet rs, SimpleDateFormat dateFormat,
			SimpleDateFormat keyDateFormat, int caseType) throws SQLException, ParseException {

		AccountsRecievable arAccount = new AccountsRecievable();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		Date date = null;
		String value = null;
		boolean arAccountNotExists = true;

		int id = rs.getInt("LocationID");
		arAccount.setLocationId(id);		

		value = rs.getString("caseID");
		if(factory.isStringNotNullOrNill(value)){
			arAccount.setCaseId(factory.createCaseIdType(value.trim()));
			arAccountNotExists = arAccountNotExists ? arAccount.getCaseId() == null : arAccountNotExists;
		}
		if( caseType == AtNeedCase || caseType == PreNeedNeedServicedCase ){
			value = rs.getString("ContractCode");
		}
//		else {
//			value = rs.getString("CaseCode");
//		}
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			arAccount.setContractNumber(convertor.getStrMax20Len(value));
			arAccountNotExists = arAccountNotExists ? arAccount.getContractNumber() == null : arAccountNotExists;
		}

		value = rs.getString("LocationNum");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			arAccount.setLocationNumber(convertor.getStrMax10Len(value));
			arAccountNotExists = arAccountNotExists ? arAccount.getLocationNumber() == null : arAccountNotExists;
		}

		value = rs.getString("SalesName");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			arAccount.setSalesType(convertor.getStrMax50Len(value));
			arAccountNotExists = arAccountNotExists ? arAccount.getSalesType() == null : arAccountNotExists;
		}

		value = rs.getString("SaleType");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			arAccount.setSalesTypeDescription(convertor.getStrMax50Len(value));
			arAccountNotExists = arAccountNotExists ? arAccount.getSalesTypeDescription() == null : arAccountNotExists;
		}

		arAccount.setSalesNeedType(null);

		arAccount.setCreditRating(null);

		//		value = rs.getString("LastPaymentDate");
		//		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
		//			value = value.trim();
		//			date = dateFormat.parse(value);
		//			arAccount.setLastPaymentDate(date);
		//			arAccountNotExists = arAccountNotExists ? arAccount.getLastPaymentDate() == null : arAccountNotExists;
		//		}

		arAccount.setPaymentTerms(null);

		// purchaser is the informant for At Need scenario
		InformantPerson informant = getInformantPerson(rs);
		if( caseType == AtNeedCase || caseType == PreNeedNeedServicedCase ){
			if(informant != null ){
				arAccount.setPurchaser(getPurchaserFromInformant(informant));
				arAccountNotExists = arAccountNotExists ? arAccount.getPurchaser() == null : arAccountNotExists;
			}
		}
		//purchaser is buyer for Pre Need Active and Cancelled scenario
//				 else{
//					 BuyerType buyer = getBuyerType(rs);
//					 if(buyer != null){
//						 arAccount.setPurchaser(getPurchaserFromBuyer(buyer));
//					}
//				}		 
		arAccount.setPaymentAmount(null);

		arAccount.setNextPaymentDate(null);

		long  amount1 = rs.getLong("TotalCharges");
		if(!rs.wasNull() && amount1 != 0){
			arAccount.setTotalContractSaleAmount(factory.getDeAmountType(amount1));
			arAccountNotExists = arAccountNotExists ? arAccount.getTotalContractSaleAmount() == null : arAccountNotExists;
		}
		long amount2 = rs.getLong("TotalPaidToDate");
		if(!rs.wasNull() && amount2 != 0){
			arAccount.setPaidInAmount(factory.getDeAmountType(amount2));
			arAccountNotExists = arAccountNotExists ? arAccount.getPaidInAmount() == null : arAccountNotExists;
		}	

		long amount  = rs.getLong("Balance");
		if(!rs.wasNull() && amount != 0){
			arAccount.setBalanceDue(factory.getDeAmountType(amount));
			arAccountNotExists = arAccountNotExists ? arAccount.getPaidInAmount() == null : arAccountNotExists;
		}		

		arAccount.setDownPayment(null);

		value = rs.getString("ServiceDateKey");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			value = value.trim();
			date = keyDateFormat.parse(value);
			arAccount.setSalesDate(date);
			arAccountNotExists = arAccountNotExists ? arAccount.getSalesDate() == null : arAccountNotExists;
		}
		value = rs.getString("DateOfDeath");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			value = value.trim();
			date = dateFormat.parse(value);
			arAccount.setDeathDate(date);
			arAccountNotExists = arAccountNotExists ? arAccount.getDeathDate() == null : arAccountNotExists;
		}			
		value = rs.getString("State");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			arAccount.setLocationState(value);
			arAccountNotExists = arAccountNotExists ? arAccount.getLocationState() == null : arAccountNotExists;
		}	
		if(arAccountNotExists){
			arAccount = null;
		}

		return arAccount;		
	}	

	// getting buyer info for pre need case
//		private static ArPurchaserType getPurchaserFromBuyer(BuyerType buyer) {
//			ArPurchaserType purchaser = null;
//			if(buyer != null){
//				purchaser = new ArPurchaserType();
//				
//				purchaser.setName(buyer.getName());
//				purchaser.setAddress(buyer.getAddress());
//				purchaser.setPhone(buyer.getPhone());	
//				purchaser.setSsnumber(buyer.getSsn());
//				
//			}		
//			return purchaser;
//		}

	// getting informant info for at need case
	private static ArPurchaserType getPurchaserFromInformant(
			InformantPerson informant) {		
		ArPurchaserType purchaser = null;		
		if(informant != null){
			purchaser = new ArPurchaserType();			
			purchaser.setName(informant.getName());
			purchaser.setAddress(createAddress(informant.getAddress()));
			if(informant.getContact() != null ){
				purchaser.setPhone(informant.getContact().getPhone());
			}
		}
		return purchaser;
	}	

	private static AddressType createAddress( DeExtendedAddressType address) {
		AddressType extAddress = null;
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		if(address != null){
			extAddress = new AddressType();

			if(address.getStreet() != null){
				StrMax50Len[] streets = new StrMax50Len[address.getStreet().length];
				int i = 0;
				for(StrMax35Len street: address.getStreet()){
					streets[i++] = convertor.getStrMax50Len(street.toString());
				}
				extAddress.setStreet(streets);
			}
			if(address.getCity() != null){
				extAddress.setCity(convertor.getStrMax50Len(convertor.getString(address.getCity())));
			}
			if(address.getStateAbbreviation() != null){
				extAddress.setStateAbbreviation(convertor.getStrMax40Len(convertor.getString(address.getStateAbbreviation())));
			}
			if(address.getZipCode() != null ){
				extAddress.setZipCode(convertor.getStrMax40Len(convertor.getString(address.getZipCode())));
			}
		}
		return extAddress;
	}

	public static String SELECT_ARFIELDS2 = " SELECT (th1.AmountOfTran) AS amount , " +
	" (ref.maxdate) AS maxdate, COUNT(ref.countv) AS numberpayments ";

	public static void updateARTransactionInfo(AccountsRecievable ar,
			ResultSet rs) throws SQLException {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		int number = rs.getInt("numberpayments");
		if(!rs.wasNull()){
			ar.setNumberOfPmnts(number);
		}
		
		long lastPmntAmount = rs.getLong("amount");
		if(!rs.wasNull() && lastPmntAmount != 0){
			ar.setLastPaymentAmount(factory.getDeAmountType(lastPmntAmount));
		}

		Date date = rs.getDate("maxdate");
		if(!rs.wasNull()){
			ar.setLastPaymentDate(date);
		}
	}
}
