package com.aldorsolutions.webservice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.aldorsolutions.webservice.xsd.comm.*;
import com.aldorsolutions.webservice.xsd.comm.fdde.*;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.*;

public class SCIServiceDTOFactory {
	
	public static final int AtNeedCase = 1;
	public static final int PreNeedNeedServicedCase = 2;
	public static final int PreNeedActiveCase = 3;
	public static final int PreNeedCanceledCase = 4;
	//public static final String PRE_NEED_SERVICED_STR = "PreNeed Serviced";
	public static final String INFORMANT_ID = "Informant";
	public static final String RELATED_PERSON_ID = "related person";
	public static final String BUYER_ID = "Buyer";
	public static final String CO_BUYER_ID = "CoBuyer";
	
	public static String SELECT_CONTRACT_NUMBER_CASE_ID = "SELECT CONCAT(?, VitalId) AS caseId, VitalId ";
	public static int updateSciTPData(SciTPData sciTPData, ResultSet rs, 
					CxlGroupType cxlGroup, StrMax10Len locCode, int sciLocation,
					String[] idStrs, Date today, boolean globalExtract) throws SQLException{
		
		int caseType = -1;
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
//		ConvertorUtil convertor = ConvertorUtil.getInstance();
		
		String value = rs.getString("caseID");
		if(factory.isStringNotNullOrNill(value)){
			sciTPData.setCaseId(factory.createCaseIdType(value.trim()));
		}
		
		sciTPData.setActive(YnType.Y);
		
		if(cxlGroup!=null && CxlGroupType._EnterContract.equals(cxlGroup.getValue())){
			if(idStrs != null){
				if(idStrs[0] == null){
					int locaelId = rs.getInt("LocaleNumber");
					idStrs[0] = getNumberString(null, locaelId, 3)+ locCode.getStrMax10Len();
				}
				sciTPData.setId(idStrs[0]);
				
			}else{
				int locaelId = rs.getInt("LocaleNumber");
				String idPreFix = getNumberString(null, locaelId, 3)+ locCode.getStrMax10Len();
				sciTPData.setId(idPreFix);
			}
		
			value = rs.getString("PNPreneedStatus");
			
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
		}
		sciTPData.setCxlGroup(cxlGroup);
		sciTPData.setUpdateDate(today);
		if(sciLocation>0){
			sciTPData.setLocCd(sciLocation);
		}
		if(!globalExtract){
			value = rs.getString("ContractNo");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
				if(cxlGroup!=null && CxlGroupType._EnterCash.equals(cxlGroup.getValue())){
					value = sciLocation + value;
				}
				sciTPData.setContractNo(value);
			}
		}
		// TODO comment below blank columns
		/*sciTPData.setTestRun("Blank1");
		sciTPData.setMch("Blank2");
		sciTPData.setCaseName("Blank3");*/
		
		return caseType;
	}
	public static void updateSaleDate(SciTPData sciTPData, ResultSet rs) throws SQLException{
		Date saleDate = rs.getDate("SalesDate");
		if(!rs.wasNull()&& sciTPData instanceof SciCashType){
			((SciCashType)sciTPData).setBtchDate(saleDate);
		}
	}
	public static DeAmountType getZeroDeAmount(){
		return FddeXsdFactory.getInstance().getDeAmountType(0.0f);
	}
	public static DeAmountType getDeAmount(float amount){
		return FddeXsdFactory.getInstance().getDeAmountType(amount);
	}
	public static String SELECT_SCI_CONTRACT = 
		", LocaleNumber, ChapelNumber, ServiceDateKey, SaleDateKey, PreneedArrngDate, OriginalPNDate, PNSource " +
		", ArrangerId, DirectorName , PNCounselor , SaleType " +
		", FinanceChargeRate, DecMrMrs , DeceasedFirstName , DeceasedMidName , DeceasedLastName " +
			", DeceasedMaidenName , DeceasedSuffix , DateOfBirth , DateOfDeath, (TotalCharges-TotalPaidToDate) AS Balance " ;
	public static String SELECT_SCI_CONTRACT_NUMBER = ", ContractNo, SalesDate ";
	public static void updateContract(SciContractType contract, ResultSet rs, 
			int caseType, StrMax10Len locCode, 
			SimpleDateFormat dateFormat, SimpleDateFormat keyDateFormat,
			Map<Integer, DeRelatedPersonType> defaultBillToPartys,boolean contractNumberGeneration) throws SQLException, ParseException {
		
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		Date date = null;
		
		
		int vitalsId = rs.getInt("VitalsMasterKey");
		contract.setSalesId(vitalsId);
		
		String value = null;
		

		if(caseType == AtNeedCase || caseType == PreNeedNeedServicedCase){
			value = rs.getString("ServiceDateKey");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
				date = keyDateFormat.parse(value);
				contract.setServiceDate(date);					
			}else {
				value = rs.getString("PreneedArrngDate");
				if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
					date = dateFormat.parse(value);
					contract.setServiceDate(date);					
				}
			}
		}
		
		 if(caseType == AtNeedCase || caseType == PreNeedNeedServicedCase){
			 
			 value  = rs.getString("SaleDateKey");
			 if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
					date = keyDateFormat.parse(value);
					contract.setSaleDate(date);					
				}else{
					
				 value  = rs.getString("OriginalPNDate");
				 if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
						date = dateFormat.parse(value);
						contract.setSaleDate(date);					
					}else if(contract.getServiceDate() != null){
						// some preNeed serviced cases don't have OriginalPNDate but have only PreneedArrngDate
						// set service date as sale date in that scenario
						contract.setSaleDate(contract.getServiceDate());
					}
				}
		 }
		 
		 if(contractNumberGeneration){
			 return;
		 }
		
		if(caseType == AtNeedCase || caseType == PreNeedNeedServicedCase){
			
			value = rs.getString("ContractCode");
			if(factory.isStringNotNullOrNill(value)){
				contract.setFullContractNBR(convertor.getStrMax20Len(value.trim()));
				contract.setDescr2(convertor.getStrMax30Len(locCode.getStrMax10Len() + " " + value.trim()));
			}
			contract.setSciSaleType(SciSaleTypeType.value1);
			
		}else{
			
			value = rs.getString("CaseCode");
			if(factory.isStringNotNullOrNill(value)){
				contract.setFullContractNBR(convertor.getStrMax20Len(value.trim()));
				contract.setDescr2(convertor.getStrMax30Len(locCode.getStrMax10Len() + " " + value.trim()));
			}
			contract.setSciSaleType(SciSaleTypeType.value2);
		}
		
		// TODO send blank for caseType
		/*if(caseType == AtNeedCase){
			value = "AtNeed";
		}
		else if(caseType == PreNeedNeedServicedCase){
			value = "PreNeed Serviced";//PRE_NEED_SERVICED_STR;
		}
		else if(caseType == PreNeedActiveCase){
			value = "PreNeed Active";
		}
		else if(caseType == PreNeedCanceledCase){
			value = "PreNeed Cancelled";
		}
		contract.setSciCaseType(value);*/
		
		/*value = rs.getString("SaleType");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			contract.setSciSaleType(convertor.getStrMax50Len(value.trim()));
		}*/
		contract.setTxnType(TxnTypeType.value1);		
		
		 contract.setBatchDate(contract.getSaleDate());
		 contract.setTxnDate(contract.getSaleDate());
		 
		 /*value = rs.getString("PNSource");
		 if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			 contract.setLeadSource(convertor.getStrMax40Len(value.trim()));
		 }*/
		 if(caseType == AtNeedCase ){
			 
			 contract.setLeadSource(LeadSourceType.value1);
			 
		 }else if(caseType == PreNeedNeedServicedCase){
			 
			 contract.setLeadSource(LeadSourceType.value2);
		 } 
		 /*{
			 boolean isArrangerExist = false;
			 int id = rs.getInt("ArrangerId");
				if(rs.wasNull()){
					id = -1;
				}else{
					isArrangerExist = true;
				}
			 value = rs.getString("DirectorName");
			 if(!rs.wasNull() &&factory.isStringNotNullOrNill(value)){
				 value = value.trim();
				 isArrangerExist = true;
			 }else if(isArrangerExist){
				 value = "";
			 }
			 if(isArrangerExist){*/
				 SciArrangerType arranger = new SciArrangerType();
//				 arranger.setArrangerId(id);
				 arranger.setStrMax150Len("'000003");
				 contract.setArranger(arranger);
			 /*}
		 }*/
		
		 /*if(caseType == PreNeedNeedServicedCase){
			 value = rs.getString("PNCounselor");
			 if(!rs.wasNull() && factory.isStringNotNullOrNill(value) 
					 && !(contract.getArranger().toString().equals(value))){
				 contract.setArranger2(convertor.getStrMax40Len(value.trim()));
			 }
		 }*/
		 if(contract.getBalance()>0){
			 contract.setTerms("'Single Payment FA");
		 }else{
			 contract.setTerms("'Cash Sale FA");
		 }
		 
//		 contract.setInterestMethod(YnType.N);	
		 
//		 float interest = rs.getFloat("FinanceChargeRate");
//		 if(!rs.wasNull()){
//			 contract.setInterestRate(factory.getDeAmountType(interest));
//		 }else{
//			 contract.setInterestRate(factory.getDeAmountType(0.0f));
//		 }
		 updatePurchaserInfo(contract, caseType, rs,defaultBillToPartys);
		 
		contract.setBeneficiary(getBasicDeceased(rs,dateFormat));
						
//		setBlankElements(contract);
	}
	// TODO comment setBlankElements method 
	private static void setBlankElements(SciContractType contract){
		
		FddeXsdFactory factory = FddeXsdFactory.getInstance();

		contract.setBatchNumber("Blank4");
		contract.setKitCode("Blank5");
		contract.setArranger_1("Blank6");
		contract.setArranger_2("Blank7");
		contract.setArranger3("Blank8");
		contract.setArranger_3("Blank9");
		contract.setNbrPymts("Blank10");
		
		contract.setCf_ref("Blank11");
		contract.setDpType("Blank12");
		contract.setDpAmt("Blank13");
		contract.setPymtAmt(factory.getDeAmountType(0.0f));
		contract.setPymtStartDate(new Date());
		contract.setSciComment("Blank14");
		contract.setPurchType("Blank15");
		contract.setClaimType("Blank16");
		contract.setClaimRef("Blank17");
		contract.setClaimAmt("Blank18");
		contract.setClaimDueDt("Blank19");
		contract.setAmtRcvd("Blank20");
		contract.setInsuranceItem("Blank21");
		contract.setAmtOfIns("Blank22");
		
		TaxType[] taxes = new TaxType[4];
		int i=0;
		while( i<4){
			taxes[i] = new TaxType();
			taxes[i].setCode("Blank"+ (i*2+23));
			taxes[i].setAmt(factory.getDeAmountType(i*2+24));
			i++;
		}
		contract.setTax(taxes);
		
		contract.setFinAdjType("Blank31");
		contract.setFinAdjAmt(factory.getDeAmountType(0.0f));
		contract.setFinAdjRef("Blank32");
		contract.setFinDscntCrRef("Blank33");
		return;
	}
	private static void updatePurchaserInfo(SciContractType contract, int caseType, ResultSet rs, 
			Map<Integer, DeRelatedPersonType> defaultBillToPartys)throws SQLException{
		
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		
		InformantPerson informant = FddeServiceDTOFactory.getInformantPerson(rs);
		 if(caseType == AtNeedCase){
			 if(informant != null && informant.getBillToParty()){
				 
				 contract.setPurchaser(getPurchaserFromRelatedPerson(informant, INFORMANT_ID));
				 
			 }else{
				 saveDefaultBillToParty(rs, informant, defaultBillToPartys);
			 }
			 
		 }else{
			 
			 BuyerType buyer = FddeServiceDTOFactory.getBuyerType(rs);
			 
			 if(buyer != null && buyer.getName()!= null){
				 contract.setPurchaser(getPurchaserFromBuyer(buyer));
				 if(caseType == PreNeedNeedServicedCase && informant != null && informant.getBillToParty()){
					 contract.setCoPurchaser(getPurchaserFromRelatedPerson(informant, INFORMANT_ID));
				 }else{
					 String coBuyer = buyer.getCoBuyer() != null ? buyer.getCoBuyer().toString() : null;
					 if(factory.isStringNotNullOrNill(coBuyer)){
						 contract.setCoPurchaser(getPurchaserFromCoBuyer(coBuyer.trim()));
					 }
				 }
				 
			 }else if(buyer != null && caseType != PreNeedNeedServicedCase ){
				 // preNeed active/canceled scenario, this may not happen  
				 contract.setPurchaser(getPurchaserFromBuyer(buyer));
				 String coBuyer = buyer.getCoBuyer() != null ? buyer.getCoBuyer().toString() : null;
				 if(factory.isStringNotNullOrNill(coBuyer)){
					 contract.setCoPurchaser(getPurchaserFromCoBuyer(coBuyer.trim()));
				 }
				 
			 }else if(caseType == PreNeedNeedServicedCase ){
				 // this can happen when an AtNeed case converted as PreNeed serviced
				 // set purchaser similarly as AtNeed but check is there any coBuyer information exists
				 
				 if(informant != null && informant.getBillToParty()){
					 
					 contract.setPurchaser(getPurchaserFromRelatedPerson(informant, INFORMANT_ID));
					 String coBuyer = buyer != null && buyer.getCoBuyer() != null ? buyer.getCoBuyer().toString() : null;
					 if(factory.isStringNotNullOrNill(coBuyer)){
						 contract.setCoPurchaser(getPurchaserFromCoBuyer(coBuyer.trim()));
					 }
					 
				 }else{ 
					 
					 saveDefaultBillToParty(rs, informant, defaultBillToPartys);
				 }
			 }
		 }
	}
	
	private static void saveDefaultBillToParty(ResultSet rs, DeRelatedPersonType relatedPerson, 
			Map<Integer, DeRelatedPersonType> defaultBillToPartys)throws SQLException{
		
		if(relatedPerson == null){
			relatedPerson = FddeServiceDTOFactory.getNextOfKin(rs);
			if(relatedPerson == null){
				return;
			}
		}
		int vitalsId = rs.getInt("VitalsMasterKey");
		defaultBillToPartys.put(vitalsId, relatedPerson);
	}
	
	public static void setDefaultBillToParty(Map<Integer, SciContractType> vitalIdToContractMap, 
			Map<Integer, DeRelatedPersonType> defaultBillToPartys) {
		
		if(vitalIdToContractMap != null && vitalIdToContractMap.size()>0 
				&& defaultBillToPartys != null && defaultBillToPartys.size()>0){
			
			for(Integer vitalId : vitalIdToContractMap.keySet()){
				
				SciContractType contract = vitalIdToContractMap.get(vitalId);
				if(contract.getPurchaser() == null){
					
					DeRelatedPersonType relatedPerson = defaultBillToPartys.get(vitalId);
					if(relatedPerson != null){
						contract.setPurchaser(getPurchaserFromRelatedPerson(relatedPerson, RELATED_PERSON_ID));
					}
				}
			}
		}
		
	}
	private static BasicDeceasedType getBasicDeceased(ResultSet rs,
			SimpleDateFormat dateFormat) throws SQLException, ParseException {
		BasicDeceasedType deceased = new BasicDeceasedType();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
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
				
		Date date = null;
		String value = rs.getString("DateOfBirth");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			deceased.setBirthDate(date);
		}
		
		value = rs.getString("DateOfDeath");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			date = dateFormat.parse(value);
			deceased.setDeathDate(date);
		}
		// TODO comment below blank columns
//		deceased.setCol1("BeneCol1");
//		deceased.setAcct("BeneAcct");
		return deceased;
	}
	private static PurchaserType getPurchaserFromRelatedPerson(
			DeRelatedPersonType relatedPerson, String purchaserId) {		
		PurchaserType purchaser = null;		
		if(relatedPerson != null){
			purchaser = new PurchaserType();
			purchaser.setPurchaserId(purchaserId);
			purchaser.setName(relatedPerson.getName());
			purchaser.setAddress(getSCIAddress(relatedPerson.getAddress()));
			if(relatedPerson.getContact() != null){
				purchaser.setPhone(relatedPerson.getContact().getPhone());
			}
			// TODO delete below RelatedAcct
			/*purchaser.setCol1("BlankPurRelCol1");
			purchaser.setAcct("BlankPurRelAcct");*/
		}
		return purchaser;
	}	
	private static PurchaserType getPurchaserFromBuyer(BuyerType buyer) {
		PurchaserType purchaser = null;
		if(buyer != null){
			purchaser = new PurchaserType();
			purchaser.setPurchaserId(BUYER_ID);
			purchaser.setName(buyer.getName());
			purchaser.setAddress(getSCIAddress(buyer.getAddress()));
			purchaser.setPhone(buyer.getPhone());		
			purchaser.setSsn(buyer.getSsn());
			// TODO delete below BuyerAcct
			/*purchaser.setCol1("BlankPurBuyCol1");
			purchaser.setAcct("BlankPurBuyAcct");*/
		}		
		return purchaser;
	}	
	
	private static PurchaserType getPurchaserFromCoBuyer(String coBuyer) {
		
		PurchaserType purchaser = new PurchaserType();
		purchaser.setPurchaserId(CO_BUYER_ID);
		
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		purchaser.setName(factory.createDeNameType(null, null, null, coBuyer));
		// TODO delete below CoPurAcct
		/*purchaser.setCol1("BlankPurCoBuyCol1");
		purchaser.setAcct("BlankPurCoBuyAcct");*/
		return purchaser;
	}
	private static SciAddressType getSCIAddress(AddressType address) {
		SciAddressType sciAddress = null;
		if(address != null){
			sciAddress = new SciAddressType();
			ConvertorUtil convertor = ConvertorUtil.getInstance();
			if(address.getStreet() != null){
				StringBuffer addinfo = new StringBuffer(128);
				StrMax50Len[] streets = new StrMax50Len[address.getStreet().length];
				int i=0;
				for(StrMax50Len street: address.getStreet()){
					if(addinfo.length()>0){
						addinfo.append(" ");
					}
					addinfo.append(street.toString());
					streets[i++] = convertor.getStrMax50Len(street.toString());
				}
				sciAddress.setAddrInfo(convertor.getStrMax200Len(addinfo.toString()));
				sciAddress.setStreet(streets);
			}
			if(address.getCity() != null){
				sciAddress.setCity(address.getCity());
			}
			if(address.getStateAbbreviation() != null){
				sciAddress.setStateAbbreviation(address.getStateAbbreviation());
			}
			if(address.getCounty() != null){
				sciAddress.setCounty(address.getCounty());
			}
			if(address.getZipCode() != null){
				sciAddress.setZipCode(address.getZipCode());
			}
		}
		return sciAddress;
	}
	
	private static SciAddressType getSCIAddress(DeExtendedAddressType address) {
		SciAddressType sciAddress = null;
		
		if(address != null){
			sciAddress = new SciAddressType();
			ConvertorUtil convertor = ConvertorUtil.getInstance();
			
			if(address.getStreet() != null ){
				StringBuffer addinfo = new StringBuffer(128);
				StrMax50Len[] streets = new StrMax50Len[address.getStreet().length];
				int i=0;
				for(StrMax35Len street: address.getStreet()){
					if(addinfo.length()>0){
						addinfo.append(" ");
					}
					addinfo.append(street.toString());
					streets[i++] = convertor.getStrMax50Len(street.toString());
				}
				sciAddress.setAddrInfo(convertor.getStrMax200Len(addinfo.toString()));
				sciAddress.setStreet(streets);
			}
			if(address.getCity() != null){
				sciAddress.setCity(convertor.getStrMax50Len(address.getCity().toString()));
			}
			if(address.getStateAbbreviation() != null){
				sciAddress.setStateAbbreviation(convertor.getStrMax40Len(address.getStateAbbreviation().toString()));
			}
			if(address.getZipCode() != null){
				sciAddress.setZipCode(convertor.getStrMax40Len(address.getZipCode().toString()));
			}
		}		
		return sciAddress;
	}

	public static String SELECT_BILL_TO_PARTY = 
		"SELECT VitalsID, RecordNumber, Honorific, FirstName, LastName, StreetAddr1, StreetAddr2, Street3, Street4 " +
		", City, State, Zip, HomePhone, CellPhone, WorkPhone, SocialSecNo, SendInvoice, ContractSignerYN ";

	public static void updatePurchaserType(PurchaserType purchaser, ResultSet rs) throws SQLException {
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		
		int recordNumber = rs.getInt("RecordNumber");
		if(!rs.wasNull() && recordNumber != 0){
			purchaser.setPurchaserId(Integer.toString(recordNumber));
		}		
		String salutation = rs.getString("Honorific");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(salutation)){
			salutation = salutation.trim();
		}
		String firstName = rs.getString("FirstName");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(firstName)){
			firstName = firstName.trim();
		}
		String lastName = rs.getString("LastName");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(lastName)){
			lastName = lastName.trim();
		}
		purchaser.setName(factory.createDeNameType(salutation, firstName, null, lastName));
		
		purchaser.setAddress(getSCIAddress(rs));
		
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		String value= rs.getString("HomePhone");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			purchaser.setPhone(convertor.getStrMax20Len(value.trim()));
		}else{			
			value= rs.getString("CellPhone");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
				purchaser.setPhone(convertor.getStrMax20Len(value.trim()));
			}else{
				value= rs.getString("WorkPhone");
				if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
					purchaser.setPhone(convertor.getStrMax20Len(value.trim()));
				}
			}
		}
		value= rs.getString("SocialSecNo");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			purchaser.setSsn(convertor.getStrMax14Len(value.trim()));
		}
	}
	private static SciAddressType getSCIAddress(ResultSet rs) throws SQLException {
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		SciAddressType sciAddress = new SciAddressType();
		int i=0;
		String street1 = rs.getString("StreetAddr1");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(street1)){
			street1 = street1.trim();
			i++;
		}else{
			street1 = null;
		}
		String street2 = rs.getString("StreetAddr2");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(street2)){
			street2 = street2.trim();
			i++;
		}else{
			street2 = null;
		}
		String street3 = rs.getString("Street3");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(street3)){
			street3 = street3.trim();
			i++;
		}else{
			street3 = null;
		}
		String street4 = rs.getString("Street4");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(street4)){
			street4 = street4.trim();
			i++;
		}else{
			street4 = null;
		}
		StrMax50Len[] streets = null;
		String addressInfo = null;
		if(i>0){
			StringBuffer addinfo = new StringBuffer(128);
			streets = new StrMax50Len[i<4?i:3];
			i=0;
			if(street1 != null){
				addinfo.append(street1);
				streets[i++] = convertor.getStrMax50Len(street1);
			}
			if(street2 != null){
				if(i>0){
					addinfo.append(" ");
				}
				addinfo.append(street2);
				streets[i++] = convertor.getStrMax50Len(street2);
			}
			if(street3 != null){
				if(i>0){
					addinfo.append(" ");
				}
				addinfo.append(street3);
				streets[i++] = convertor.getStrMax50Len(street3);
			}
			if(street4 != null){
				if(i>0){
					addinfo.append(" ");
				}
				addinfo.append(street4);
				if(i<3){
					streets[i++] = convertor.getStrMax50Len(street4);
				}
			}
			addressInfo = addinfo.toString();
		}
		sciAddress.setAddrInfo(convertor.getStrMax200Len(addressInfo));
		sciAddress.setStreet(streets);
		
		String value = rs.getString("City");
		if(factory.isStringNotNullOrNill(value)){
			sciAddress.setCity(convertor.getStrMax50Len(value.trim()));
		}
		value = rs.getString("State");
		if(factory.isStringNotNullOrNill(value)){
			sciAddress.setStateAbbreviation(convertor.getStrMax40Len(value.trim()));
		}
		value = rs.getString("Zip");
		if(factory.isStringNotNullOrNill(value)){
			sciAddress.setZipCode(convertor.getStrMax40Len(value.trim()));
		}		
		return sciAddress;
	}
	
	/*public static String SELECT_LINE_ITEMS = 
		"SELECT A.VitalsMasterKey, Identity, AutoIndex, CategoryCode, A.Description, Amount, WholesaleCost ";*/
	public static String SELECT_LINE_ITEMS = 
		"SELECT A.VitalsMasterKey, B.MasterID, AutoIndex, CategoryCode, A.Description, Amount, Cost ";

	public static void updateLineItemType(LineItemType lineItem, ResultSet rs, Map<String, String> fdmsToSciItemMap) throws SQLException {
		
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		
		lineItem.setChargesId(rs.getInt("AutoIndex"));
		
		int invId = rs.getInt("MasterID");
		if(!rs.wasNull()){
			lineItem.setInvHistoryId(invId);
		}
		
		lineItem.setQty(1);
		
		String value = rs.getString("CategoryCode");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			
			if((fdmsToSciItemMap.containsKey(value))){
				//set line item with item code
				lineItem.setItemCode(convertor.getStrMax50Len(fdmsToSciItemMap.get(value)));
			}/*else{
				lineItem.setItemCode(convertor.getStrMax50Len(value+" NA in SCI"));
			}*/
		}else{
			lineItem.setItemCode(convertor.getStrMax50Len(""));
		}
		
		/*value = rs.getString("Description");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			lineItem.setDescription(convertor.getStrMax50Len(value.trim()));
		}*/
		
		long amount = rs.getLong("Amount");
		if(!rs.wasNull()){
			
			if(amount>0){
				lineItem.setPrice(factory.getDeAmountType(amount/100.0f));
				// TODO below code to be commented
//				lineItem.setDscntReason("Conversion Discount");
//				lineItem.setDscnt(factory.getDeAmountType(0.0f));
			}else{
				lineItem.setDscntReason("Conversion Discount");
				lineItem.setDscnt(factory.getDeAmountType(-amount/100.0f));
				lineItem.setPrice(factory.getDeAmountType(0.0f));
			}
		}/*else{
			lineItem.setPrice(factory.getDeAmountType(0.0f));
			lineItem.setDscnt(factory.getDeAmountType(0.0f));
		}*/
		
		amount = rs.getLong("Cost");
		if(!rs.wasNull()){
			lineItem.setCost(factory.getDeAmountType(amount/100.0f));
		}else{
			lineItem.setCost(factory.getDeAmountType(0.0f));
		}
		// TODO comment below blank column code
//		lineItem.setSrvType("SrvType");
	}
	
	public static String SELECT_PAYMENTS = 
		"SELECT VitalsMasterKey,(SUM(AmountOfTran*-1)) AS amount ";
//	"SELECT VitalsMasterKey,TranHistID,Description,(SUM(AmountOfTran*-1)) AS amount,DateOfTran ";

	/*public static void updateSciPaymentType(SciCashType cash, ResultSet rs) throws SQLException {
		
		FddeXsdFactory factory = FddeXsdFactory.getInstance();		
//		copyTpData(tpData, cash);
		cash.setTranId(rs.getInt("TranHistID"));		
//		String value = rs.getString("Description");
//		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
//			cash.setComment(convertor.getStrMax50Len(value.trim()));
//		}	
		
		long amount = rs.getLong("amount");
		if(!rs.wasNull()){
			cash.setPymtAmt(factory.getDeAmountType(amount/100));
		}
		cash.setPymtSource(PymtSourceType.Corporate);
		
		cash.setPymtType(PymtTypeType.value1);
		
//		Date date = rs.getDate("DateOfTran");
//		if(!rs.wasNull()){
//			cash.setBtchDate(date);
//		}		
		getBlankCashValues(cash);		
	}*/
	private static void getBlankCashValues(SciCashType cash) {
		// TODO Auto-generated method stub
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		cash.setComment(convertor.getStrMax50Len("Comment"));
		cash.setRcptsInBtch("RcptsInBtch");
		cash.setCashBatch("CashBatch");
		cash.setRunReverseCR("RunReverseCR");
		cash.setApplyNSF("ApplyNSF");
		cash.setReverseCRBatch("ReverseCRBatch");
		cash.setCashRefNbr("CashRefNbr");
		cash.setFinAdjRef("FinAdjRef");
	}
	/*private static void copyTpData(SciTPData tpData, SciCashType cash) {
		cash.setCaseId(tpData.getCaseId());
		cash.setActive(tpData.getActive());
		cash.setId(tpData.getId());
		cash.setTestRun(tpData.getTestRun());
		cash.setMch(tpData.getMch());
		cash.setCaseName(tpData.getCaseName());
		cash.setCxlGroup(tpData.getCxlGroup());
		cash.setUpdateDate(tpData.getUpdateDate());
		cash.setLocCd(tpData.getLocCd());
		cash.setContractNo(tpData.getContractNo());
	}*/
	
	public static String getNumberString(String previoueStr, int number, int lengthOfString){
		
		if(previoueStr == null){
			return convertToString(number, lengthOfString);
			
		}else{
			
			if(number%10>0){
				String lastDigit = Integer.toString(number%10);
				previoueStr = previoueStr.substring(0, previoueStr.length()-1)+lastDigit;
				
			}else if((number%100)/10>0){
				String last2Digits = Integer.toString(number%100);
				previoueStr = previoueStr.substring(0, previoueStr.length()-2)+last2Digits;
			}else{
				previoueStr = convertToString(number, lengthOfString);
			}
			return previoueStr;
		}
	}
	private static String convertToString(int number, int lengthOfString){
		
		if(lengthOfString>0){
			
			boolean negativeNumber = number<0;
			if(negativeNumber){
				number = -number;
				lengthOfString--;
			}
			int i = number;
			int numberOfdigits = 0;
			while(i>0){
				i = i/10;
				numberOfdigits++;
			}
			if(number == 0){
				numberOfdigits++;
			}
			if(lengthOfString > numberOfdigits){
				StringBuffer numbStr = new StringBuffer(negativeNumber?lengthOfString+1:lengthOfString);
				if(negativeNumber){
					numbStr.append("-");
				}
				i= lengthOfString - numberOfdigits;
				while(i>0){
					numbStr.append("0");
					i--;
				}
				numbStr.append(number);
				return numbStr.toString();
			}else{ 
				if(lengthOfString == numberOfdigits){
					if(negativeNumber){
						number *= -1;
					}
					return Integer.toString(number);
				}
				String numbStr = Integer.toString(number);
				i = numberOfdigits-lengthOfString;
				if(negativeNumber){
					i++;
				}
				return numbStr.substring(i);
			}
		}
		
		if(lengthOfString == 0){
			return "";
		}
		return null;
	}
	
	public static String SCI_AR_DETAIL_SELECT_POST = ", SciLocationId , KSDeptID , ContractNo " +
												", FDMSLocaleId , (scilocations.FDMSLocationId) AS LocationId " +
												", ContractCode ";
	public static void updateSciARDetailData(SciARDetailDataType arDetail,
												ResultSet rs ) throws SQLException {
		// TODO Auto-generated method stub
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
						
		int sciId = rs.getInt("SciLocationId");
		if(!rs.wasNull()){
			arDetail.setLocCd(sciId);
		}		
		String value = rs.getString("KSDeptID");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			arDetail.setDeptIdKNA(value.trim());
		}
		value = rs.getString("ContractNo");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			arDetail.setSciContractNbr(sciId + value);
		}
		int id = rs.getInt("FDMSLocaleId");
		if(!rs.wasNull()){
			arDetail.setLocaleKNA(id);
		}
		id = rs.getInt("LocationId");
		if(!rs.wasNull()){
			arDetail.setLocIdKNA(id);
		}
		value = rs.getString("ContractCode");
		if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
			arDetail.setContractNbrKNA(value.trim());
		}
	}
	
	public static String AR_TRANS_DETAILS_SELECT = "SELECT TranHistID ,VitalsMasterKey ,GLacct=10030 AS payment" +
			",DatePaid ,((AmountOfTran*-1)/100) AS Amount ,ReceiptNumber ,PaymentMethod " +
			",ManualReceiptNo ,Description ";
	public static TranDetailType updateTransDetails(ResultSet rs, SimpleDateFormat keyDateFormat, Map<String, String> pymtTypeCodeToDescMap) 
							throws SQLException, ParseException {	
		
		TranDetailType trans = new TranDetailType();
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		Date date = null;
		ConvertorUtil convertor = ConvertorUtil.getInstance();
		int id = rs.getInt("TranHistID");
		if(!rs.wasNull()){
			trans.setTranId(id);
		}
		trans.setComment(convertor.getStrMax50Len("Comment"));
		
		int validate = rs.getInt("payment");
		if(validate == 1){
			// setting payment values
			String value = rs.getString("DatePaid");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
				date = keyDateFormat.parse(value);
				trans.setPymtDate(date);
			}	
			float amount = rs.getFloat("Amount");
			if(!rs.wasNull()){
				trans.setPymtAmt(factory.getDeAmountType(amount));
			}
			String receipt = rs.getString("ReceiptNumber");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
				String manualreceipt = rs.getString("ManualReceiptNo");
				if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
					trans.setPymtSource(receipt+ " "+manualreceipt);
				}else{
					trans.setPymtSource(receipt);
				}
				trans.setCashRefNbr(receipt);
			}
			value = rs.getString("PaymentMethod");
			value = pymtTypeCodeToDescMap.get(value);
			if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
				trans.setPymtType(value);
			}
			if(amount < 0){
			trans.setRunReverseCR(true);
			}
		}else{
			// setting adjustment values
			float amount = rs.getFloat("Amount");
			if(!rs.wasNull()){
				trans.setFinAdjAmt(factory.getDeAmountType(amount));
			}
			String value = rs.getString("Description");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
				trans.setFinAdjDescr(value.trim());
			}
			String receipt = rs.getString("ReceiptNumber");
			if(!rs.wasNull() && factory.isStringNotNullOrNill(value)){
				trans.setFinAdjRef(receipt.trim());
			}
		}		
		return trans;
	}
	
}
