package com.aldorsolutions.webservice;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.resource.cci.ResultSet;

import org.apache.log4j.Logger;

import com.aldorsolutions.fdms.exception.FDDEException;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webservice.xsd.comm.StrMax10Len;
import com.aldorsolutions.webservice.xsd.comm.fdde.CaseIdType;
import com.aldorsolutions.webservice.xsd.comm.fdde.DeAmountType;
import com.aldorsolutions.webservice.xsd.comm.fdde.DeRelatedPersonType;
import com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeIdType;
import com.aldorsolutions.webservice.xsd.comm.fdde.FuneralHomeType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.CxlGroupType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.FinDscntCrTypeType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.LeadSourceType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.LineItemType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.PurchaserType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.PymtSourceType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.PymtTypeType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciARDetailDataType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciCashType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciContractLineItemType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciContractType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciTPData;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.TranDetailType;
import com.aldorsolutions.webservice.xsd.fdde.service.CaseFilterType;
import com.aldorsolutions.webservice.xsd.fdde.service.CriteriaType;
import com.aldorsolutions.webservice.xsd.fdde.service.InvalidRowDetails;

public class SCIFddeServiceDAO extends FddeServiceDAO {
	
	private Logger logger = Logger.getLogger(SCIFddeServiceDAO.class.getName());

	public SCIFddeServiceDAO(DbUserSession user) {
		super(user);
	}

	public SCIFddeServiceDAO(long companyID, long userId) {
		super(companyID, userId);
	}

	public SCIFddeServiceDAO(String dbLookup) {
		super(dbLookup);
	}

	public List<SciContractType> getSciContracts(FuneralHomeIdType funeralHomeId, StrMax10Len locCode, Integer sciLocation, 
			CaseIdType caseIdType, CaseFilterType caseFilter, boolean globalExtract,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows) {
		
		Map<Integer, SciContractType> nonInformantBillToPartys = new HashMap<Integer, SciContractType>();
		Map<Integer, SciContractType> nonBuyerAndNonInformantBillToPartys = new HashMap<Integer, SciContractType>();
		Map<Integer, DeRelatedPersonType> defaultBillToPartys = new HashMap<Integer, DeRelatedPersonType>();
		Map<Integer, SciContractType> vitalIdToContractMap = new HashMap<Integer, SciContractType>();
		//read contracts
		List<SciContractType> contracts = getSciContractsFromVitalstats( funeralHomeId, locCode, sciLocation, caseIdType, caseFilter, 
																	globalExtract, false,reportInvalidRowIds,	includeInvalidRowIds, 
																	invalidRows, vitalIdToContractMap, nonInformantBillToPartys,
																	nonBuyerAndNonInformantBillToPartys, defaultBillToPartys);
		// filter the contracts based on last activity date
		//read purchaser and co-purchaser from bill to party 
		if(nonInformantBillToPartys.size()>0){
			
			updateBillToParty(getVitalIdListAsString(nonInformantBillToPartys.keySet()), nonInformantBillToPartys, true, false,
					reportInvalidRowIds, includeInvalidRowIds, invalidRows);
		}
		
		if(nonBuyerAndNonInformantBillToPartys.size()>0){
			
			updateBillToParty(getVitalIdListAsString(nonBuyerAndNonInformantBillToPartys.keySet()), nonBuyerAndNonInformantBillToPartys, 
					true, true, reportInvalidRowIds, includeInvalidRowIds, invalidRows);
		}
		// there may be cases that have informant is bill to party as No 
		// but manually entered Informant information as bill to party
		String noPurchaserVitalIdList = getNoPurchaserVitalIdList(vitalIdToContractMap);
		if(noPurchaserVitalIdList != null && noPurchaserVitalIdList.length()>0){
			updateBillToParty(noPurchaserVitalIdList, vitalIdToContractMap, false, false, 
					reportInvalidRowIds, includeInvalidRowIds, invalidRows);
		}
		if(defaultBillToPartys.size()>0){
			SCIServiceDTOFactory.setDefaultBillToParty(vitalIdToContractMap, defaultBillToPartys);
		}
		if(vitalIdToContractMap.size()>0){
			String vitalIdList = getVitalIdListAsString(vitalIdToContractMap.keySet());
			
			updateTotalDiscount(vitalIdList, vitalIdToContractMap);
		}
		if((reportInvalidRowIds || includeInvalidRowIds) && contracts != null && contracts.size()>0){
			contracts = validateRequiredFields(contracts, invalidRows);
		}
		if(reportInvalidRowIds){
			contracts.clear();
			contracts = null;
        }
		
		return contracts;
	}

	private StringBuffer validateRequiredFieldsSciTPData(SciTPData tpData){
		StringBuffer errorMsg = null;
		if(tpData != null){
			
			if(tpData.getContractNo()== null){
				errorMsg = addStringToStringList(errorMsg, "ContractNo");
			}
		}
		return errorMsg;
	}

	private List<SciContractType> validateRequiredFields(List<SciContractType> contracts,
			List<InvalidRowDetails> invalidRows) {
		
		List<SciContractType> validContracts = new ArrayList<SciContractType>(contracts.size());
		for(Iterator<SciContractType> it= contracts.iterator(); it.hasNext();){
			SciContractType contract = it.next();
			
			StringBuffer errorMsg = validateRequiredFieldsSciTPData(contract);
			boolean invalid = errorMsg != null && errorMsg.length()>0;
			
			if(contract.getSciSaleType() == null){
				invalid = true;
				errorMsg = addStringToStringList(errorMsg, "SciSaleType");
			}
//			if(contract.getTxnType() == null){
//				invalid = true;
//				errorMsg = addErrorToMsg(errorMsg, "TxnType");
//			}
			if(contract.getSaleDate() == null){
				invalid = true;
				errorMsg = addStringToStringList(errorMsg, "SaleDate");
			}
			if(contract.getLeadSource() == null){
				invalid = true;
				errorMsg = addStringToStringList(errorMsg, "LeadSource");
			}
			if(contract.getInterestMethod() == null){
				invalid = true;
				errorMsg = addStringToStringList(errorMsg, "InterestMethod");
			}
			if(contract.getInterestRate() == null){
				invalid = true;
				errorMsg = addStringToStringList(errorMsg, "InterestRate");
			}
			if(contract.getPurchaser() == null){
				invalid = true;
				errorMsg = addStringToStringList(errorMsg, "Purchaser");
			}else if(contract.getPurchaser().getName()==null){
				invalid = true;
				errorMsg = addStringToStringList(errorMsg, contract.getPurchaser().getPurchaserId()+".Purchaser.Name");
			}	
			if(contract.getBeneficiary() == null){
				invalid = true;
				errorMsg = addStringToStringList(errorMsg, "Beneficiary");
			}			
			if(invalid){
				String parentId = contract.getCaseId().getCaseIdType();
				String contractNo = contract.getContractNo();
				invalidRows.add(getInvalidRowDetails(errorMsg.toString(), "RequiredFieldsMissing", contractNo, parentId ));
			}else{
				validContracts.add(contract);
			}
		}
		return validContracts;
	}
	
	private StringBuffer addStringToStringList(StringBuffer stringList, String stringValue){
		if(stringValue != null){
			if(stringList == null){
				stringList = new StringBuffer();
			}
			if(stringList.length()>0){
				stringList.append(",");
			}
			stringList.append(stringValue);
		}
		return stringList;
	}

	private String getVitalIdListAsString(Set<Integer> vitalIds){
		
		StringBuffer vitalIdsListStr = new StringBuffer(vitalIds.size()*5);
		boolean notFirstValue = false;
		
		for(Integer vitalId: vitalIds){
			
			if(notFirstValue){
				vitalIdsListStr.append(",");
			}else{
				notFirstValue = true;
			}
			vitalIdsListStr.append(vitalId.toString());
		}
		return vitalIdsListStr.toString();
	}
	private String getVitalIdListAsString(List<Integer> vitalIds){
		
		StringBuffer vitalIdsListStr = new StringBuffer(vitalIds.size()*5);
		boolean notFirstValue = false;
		
		for(Integer vitalId: vitalIds){
			
			if(notFirstValue){
				vitalIdsListStr.append(",");
			}else{
				notFirstValue = true;
			}
			vitalIdsListStr.append(vitalId.toString());
		}
		return vitalIdsListStr.toString();
	}
	
	private String getNoPurchaserVitalIdList(
			Map<Integer, SciContractType> vitalIdToContractMap) {
		
		StringBuffer vitalIdList = null;
		if(vitalIdToContractMap != null && vitalIdToContractMap.size()>0){
			
			for(Integer vitalId: vitalIdToContractMap.keySet()){
				SciContractType contract = vitalIdToContractMap.get(vitalId);
				
				if(contract.getPurchaser() == null && contract.getCoPurchaser() == null){
					
					// this contract has no purchaser needs to check again
					vitalIdList = addStringToStringList(vitalIdList, vitalId.toString());
					
				}else if(//contract.getSciCaseType().equals(SCIServiceDTOFactory.PRE_NEED_SERVICED_STR)
						contract.getLeadSource() != null && contract.getLeadSource().getValue().equals(LeadSourceType._value2)
						&& contract.getPurchaser() != null 
						&& contract.getPurchaser().getPurchaserId().equals(SCIServiceDTOFactory.BUYER_ID) 
						&& contract.getPurchaser().getName()== null){
					
					// this contract may be read as pre need serviced 
					// but actually this may be entered initially as at need and from webfdms screen converted 
					// as pre need so this might have informant as purchaser needs to check again
					vitalIdList = addStringToStringList(vitalIdList, vitalId.toString());
					
				}
			}
		}
		
		return vitalIdList != null && vitalIdList.length()>0? vitalIdList.toString(): null;
	}
	
	private static String SELECT_SCI_CONTRACT_FILTER_UNPOSTED_CASES = " AND SentToAccounting = 1 ";
//	private static String SELECT_SCI_CONTRACT_FILTER_NO_BALANCE_CASES = " (TotalCharges-TotalPaidToDate)>0 ";
	private static String SELECT_SCI_CONTRACT_FILTER_PRE = " vitalstats.VitalsMasterKey IN (";
	private static String SELECT_SCI_CONTRACT_FILTER_POST = ") ";
	private static String FROM_SCI_CONTRACT_NUMBER = " JOIN scicontractnumber ON vitalstats.VitalsMasterKey = scicontractnumber.VitalId ";
	
	public List<SciContractType> getSciContractsFromVitalstats(FuneralHomeIdType funeralHomeId, StrMax10Len locCode, Integer sciLocation, 
			CaseIdType caseIdType, CaseFilterType caseFilter, boolean globalExtract, boolean contractNumberGeneration,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows, 
			Map<Integer, SciContractType> vitalIdToContractMap,
			Map<Integer, SciContractType> nonInformantBillToPartys,
			Map<Integer, SciContractType> nonBuyerAndNonInformantBillToPartys,
			Map<Integer, DeRelatedPersonType> defaultBillToPartys){
		
		if(contractNumberGeneration && !globalExtract){
			throw new FDDEException("Illegal call for SCI Contracts! Only global extract can generate SCI contract numbers.");
		}
		boolean singleCase = caseIdType != null;
		List<SciContractType> contracts = singleCase ? new ArrayList<SciContractType>(1):new ArrayList<SciContractType>();
		
		Map<Integer, Date> lastActivieCases = singleCase ? new HashMap<Integer, Date>(1): new HashMap<Integer, Date>();
		Map<Integer, Float> balancedCases = singleCase ? new HashMap<Integer, Float>(1): new HashMap<Integer, Float>();
		List<Integer> finaceChargedCases = singleCase ? new ArrayList<Integer>(1):new ArrayList<Integer>();
		boolean atNeed = false;
		
		if(singleCase){
			String vitalsId = caseIdType.getCaseIdType().substring(caseIdType.getCaseIdType().indexOf('C')+1);
			getLastActivieCases(vitalsId, lastActivieCases);
			getFinanceChargedCases(vitalsId, finaceChargedCases);
			funeralHomeId = UserLocation.getFuneralHomeIdTypeFromCaseID(caseIdType.getCaseIdType());
			locCode = getFuneralHomeNumber(funeralHomeId, reportInvalidRowIds, includeInvalidRowIds,invalidRows);
			sciLocation = getSciLocation(funeralHomeId);
			funeralHomeId = null;
			atNeed = true;
		}else{
			atNeed = caseFilter != null? caseFilter.getIsAtNeed():true;
			getCasesWithLastActivityDateAndFinanceCharges(funeralHomeId,atNeed, lastActivieCases, balancedCases, finaceChargedCases);
		}
		
		if(locCode != null && (!atNeed || (atNeed && (lastActivieCases.size()>0 || balancedCases.size()>0)))){
			
			CxlGroupType cxlGroup = CxlGroupType.EnterContract;
			// construct SQL
			StringBuffer sql = new StringBuffer(512);
			
			// construct select clause
			sql.append(FddeServiceDTOFactory.SELECT_CASE);
			sql.append(", vitalstats.VitalsMasterKey ");
			sql.append(SCIServiceDTOFactory.SELECT_SCI_CONTRACT);
			if(atNeed && !globalExtract){
				sql.append(SCIServiceDTOFactory.SELECT_SCI_CONTRACT_NUMBER);
			}
			if(!contractNumberGeneration){
				sql.append(FddeServiceDTOFactory.SELECT_BUYER);
				sql.append(FddeServiceDTOFactory.SELECT_INFORMANT);
				sql.append(FddeServiceDTOFactory.SELECT_NEXT_OF_KIN);
				sql.append(SELECT_CASE_SALE_POSTING);
			}
			
			// construct from clause
			sql.append(" FROM ").append(VITALS_TABLE);
			if(atNeed && !globalExtract){
				sql.append(FROM_SCI_CONTRACT_NUMBER);
			}
			sql.append(SELECT_CASE_FINANCIAL_DATA_TABLE);
			
			//construct where clause
			sql.append(SELECT_CASES_SQL_WHERE);
			if(singleCase){
				sql.append(SELECT_SINGLE_CASE_FILTER);
			}else{
				if(atNeed){
					sql.append(SELECT_SCI_CONTRACT_FILTER_UNPOSTED_CASES);
					/*if(lastActivieCases.size()>0 || balancedCases.size()>0){
						sql.append(" AND (").append(SELECT_SCI_CONTRACT_FILTER_NO_BALANCE_CASES);
						sql.append(" OR ").append(SELECT_SCI_CONTRACT_FILTER_PRE);*/
					sql.append(" AND ").append(SELECT_SCI_CONTRACT_FILTER_PRE);
					HashSet<Integer> vitalIDsSet = new HashSet<Integer>(lastActivieCases.size()+balancedCases.size());
					vitalIDsSet.addAll(balancedCases.keySet());
					vitalIDsSet.addAll(lastActivieCases.keySet());
					sql.append(getVitalIdListAsString(vitalIDsSet));
					sql.append(SELECT_SCI_CONTRACT_FILTER_POST);
						//.append(" ) ");
					/*}else{
						sql.append(" And ").append(SELECT_SCI_CONTRACT_FILTER_NO_BALANCE_CASES);
					}*/
				}
			}
			sql.append(getCaseFilterSQL(caseFilter, singleCase,null));
			
			sql.append(SELECT_CASES_SQL_ORDER);
			
			try{
				makeConnection(dbLookup);
				statement = conn.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				setStatementAndReturnFuneralHomeId(funeralHomeId, caseIdType);
				
				rs = statement.executeQuery();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
				SimpleDateFormat keyDateFormat = new SimpleDateFormat("yyyyMMdd");
				
				String[] idStrs = {null};
				Date today = new Date();
				DeAmountType zeroDeAmount = SCIServiceDTOFactory.getZeroDeAmount();
				while(rs.next()){
					SciContractType contract = null;
					try{
						int vitalsId = rs.getInt("VitalsMasterKey");
						int balance = rs.getInt("Balance");
						
						if(!rs.wasNull() && balance>0 && finaceChargedCases.contains(vitalsId)){
								continue;
						}
						
						contract = new SciContractType();
						if(!rs.wasNull()){
							contract.setBalance(balance/100.0f);
							contract.setBalDue(balance/100.0f);
						}
						
						if(lastActivieCases.containsKey(vitalsId)){
							contract.setLastActivityDate(lastActivieCases.get(vitalsId));
						}
						if(!finaceChargedCases.contains(vitalsId)) {
							contract.setFncChgAmt(zeroDeAmount);
							contract.setTotalFinanceChargeInterest(zeroDeAmount);
						}else{
							contract.setHasFinanceCharges(true);
						}
						
						int caseType = SCIServiceDTOFactory.updateSciTPData(contract, rs, cxlGroup, locCode, sciLocation.intValue(), idStrs,today, globalExtract);
						SCIServiceDTOFactory.updateContract(contract, rs, caseType, locCode, dateFormat, keyDateFormat, defaultBillToPartys, contractNumberGeneration);
						
						if(!contractNumberGeneration){
							if(caseType == SCIServiceDTOFactory.AtNeedCase){
								
								nonInformantBillToPartys.put(vitalsId, contract);
								
							}else if(caseType == SCIServiceDTOFactory.PreNeedNeedServicedCase){
								
								if(contract.getPurchaser() == null || contract.getCoPurchaser() == null||
										contract.getCoPurchaser().getPurchaserId().equals(SCIServiceDTOFactory.CO_BUYER_ID)){
									
									nonBuyerAndNonInformantBillToPartys.put(vitalsId, contract);
									
								}
							}
							vitalIdToContractMap.put(vitalsId, contract);
						}
						contracts.add(contract);
						
					}catch (Exception ex) {
			            logger.debug("Exception inside while of getSciContractsFromVitalstats() : ", ex);
			            if(reportInvalidRowIds ||includeInvalidRowIds){
			            		String rowId = contract.getCaseId().getCaseIdType();
				            	invalidRows.add(getInvalidRowDetails(rowId, "contract", null, null));
				            }else{
				            	throw ex;
				            }
			        }
				}
				
			}catch(FDDEException fddeEx){
				throw fddeEx;
			}catch(SQLException sqlEx) {
	            logger.error("SQLException in getSciContractsFromVitalstats() : ", sqlEx);
	            throw new FDDEException(sqlEx.getMessage(),sqlEx);
	        } catch (Exception ex) {
	            logger.error("Exception in getSciContractsFromVitalstats() : ", ex);
	            throw new FDDEException(ex.getMessage(),ex);
	        } finally {
	            closeConnection();
	        }
	        
	        Map<Integer, SciTPData> vitalIdToTPDataMap = new HashMap<Integer, SciTPData>(contracts.size());
			for(SciContractType contract: contracts){
				vitalIdToTPDataMap.put(contract.getSalesId(), contract);
			}
			updateSCILocation(vitalIdToTPDataMap);
		}
		
		if(!contractNumberGeneration && vitalIdToContractMap.size()>0){
			
			if(finaceChargedCases.size()>0){
				updateFinanceCharges(getVitalIdListAsString(finaceChargedCases), vitalIdToContractMap);
			}
		
			updateBaselineReport(getVitalIdListAsString(vitalIdToContractMap.keySet()), vitalIdToContractMap,
								reportInvalidRowIds,includeInvalidRowIds,invalidRows);
		}
		return contracts;
	}
	
	private void updateBaselineReport(String vitalIdListAsString,
			Map<Integer, SciContractType> vitalIdToContractMap,boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows) {
		
		if(vitalIdListAsString != null && vitalIdListAsString.length()>0  
				&& vitalIdToContractMap != null && vitalIdToContractMap.size()>0){
			
			FddeXsdFactory factory = FddeXsdFactory.getInstance();
			
			Map<Integer, Long> taxes = updateFromCharges(vitalIdListAsString,true);
			Map<Integer, Long> sales = updateFromCharges(vitalIdListAsString,false);
			Map<Integer, SumOfTransDetails> vitalsIdToPayments = 
												updatePayments(
														getVitalIdListAsString(vitalIdToContractMap.keySet()), 
														reportInvalidRowIds, includeInvalidRowIds, invalidRows,true);
			Map<Integer, SumOfTransDetails> vitalsIdToAdjustments = 
												updatePayments(
														getVitalIdListAsString(vitalIdToContractMap.keySet()), 
														reportInvalidRowIds, includeInvalidRowIds, invalidRows,false);
			
			DeAmountType zeroDeAmount = SCIServiceDTOFactory.getZeroDeAmount();
									
			for(Integer vitalsId : vitalIdToContractMap.keySet()){	
				SciContractType contract = vitalIdToContractMap.get(vitalsId);
				//set total sale amount
				Long saleAmount = sales.get(vitalsId);
				if(saleAmount == null){
					contract.setTotalSaleAmt(zeroDeAmount);
				}else{
					contract.setTotalSaleAmt(factory.getDeAmountType(saleAmount.longValue()/100.0f));
				}
				// set total tax
				saleAmount = taxes.get(vitalsId);
				if(saleAmount == null){
					contract.setSumOfTaxes(zeroDeAmount);
				}else{
					contract.setSumOfTaxes(factory.getDeAmountType(saleAmount.longValue()/100.0f));
				}
				// set total payment and count of payments
				SumOfTransDetails transDetails = vitalsIdToPayments.get(vitalsId);			
				if(transDetails != null){
					contract.setTotalPaymentAmt(factory.getDeAmountType((transDetails.getAmount())/100.0f));
					contract.setCountOfPaymentsRecieved(transDetails.getNoTrans());
				}else{
					contract.setTotalPaymentAmt(zeroDeAmount);					
				}
				// set the total adjustment amount
				transDetails = vitalsIdToAdjustments.get(vitalsId);
				if(transDetails != null){
					contract.setTotalAdjustmentAmt(factory.getDeAmountType((transDetails.getAmount())/100.0f));
				}else{
					contract.setTotalAdjustmentAmt(zeroDeAmount);
				}				
			}
		}
	}	
	
	private static String SELECT_CHARGES = "SELECT VitalsMasterKey, SUM(Amount) AS TotalAmount FROM charges ";
	private static String WHERE_CHARGES_PRE = "WHERE VitalsMasterKey IN( ";
	private static String WHERE_CHARGES_POST = " ) AND spfcode ";
	private static String BOOLEAN_TRUE_CNDITION =" = 'S' and CategoryCode = 'TAX' ";
	private static String BOOLEAN_FALSE_CNDITION = " IN ('S','F') ";
	private static String GROUP_BY_CHARGES = " GROUP BY VitalsMasterKey ";
	private static String ORDER_BY_CHARGES = " ORDER BY VitalsMasterKey";
	
	private Map<Integer, Long> updateFromCharges(String vitalIdListAsString,boolean isTax){
		
		Map<Integer, Long> amountAndSumOfTaxesMap = new HashMap<Integer, Long>();
		StringBuffer sql = new StringBuffer(512+vitalIdListAsString.length());	
		sql.append(SELECT_CHARGES);
		sql.append(WHERE_CHARGES_PRE);
		sql.append(vitalIdListAsString);
		sql.append(WHERE_CHARGES_POST);
		if(isTax){
			sql.append(BOOLEAN_TRUE_CNDITION);
		}else{
			sql.append(BOOLEAN_FALSE_CNDITION);
		}
		sql.append(GROUP_BY_CHARGES);
		sql.append(ORDER_BY_CHARGES);
		try{
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();
			while(rs.next()){
				int vitalsId = rs.getInt("VitalsMasterKey");
				long amount = rs.getLong("TotalAmount");
					
				amountAndSumOfTaxesMap.put(vitalsId, amount);
			}
		}catch(SQLException sqlEx) {
            logger.error("SQLException in updateFromCharges() : ", sqlEx);
            throw new FDDEException(sqlEx.getMessage(),sqlEx);
        } catch (Exception ex) {
            logger.error("Exception in updateFromCharges() : ", ex);
            throw new FDDEException(ex.getMessage(),ex);
        } finally {
            closeConnection();
        }		
		return amountAndSumOfTaxesMap;
	}
	
	private static String SELECT_CUSTOM33_FROM_FDMSCUST_PRE = " SELECT VitalsMasterKey,Custom33 FROM fdmscust WHERE vitalsmasterkey IN( ";
	private static String SELECT_CUSTOM33_FROM_FDMSCUST_POST = ") AND Custom33 IS NOT NULL AND Custom33 <> '' ORDER BY VitalsMasterKey ";
	private void getResultSetToUpdateSCILocation(Set<Integer> vitalIdSet) throws Exception{
		StringBuffer sql = new StringBuffer(512);
		sql.append(SELECT_CUSTOM33_FROM_FDMSCUST_PRE);
		sql.append(getVitalIdListAsString(vitalIdSet));
		sql.append(SELECT_CUSTOM33_FROM_FDMSCUST_POST);
		makeConnection(dbLookup);
		statement = conn.prepareStatement(sql.toString());
		rs = statement.executeQuery();
	}
	private void updateSCILocation(Map<Integer, SciTPData> vitalIdToTpDataMap) {
		
		if(vitalIdToTpDataMap != null && vitalIdToTpDataMap.size() > 0){			
			try{
				getResultSetToUpdateSCILocation(vitalIdToTpDataMap.keySet());
				while(rs.next()){
					int vitalId = rs.getInt("VitalsMasterKey");
					if(vitalIdToTpDataMap.containsKey(vitalId)){
						String sciLoc = rs.getString("Custom33");
						try{
							int sciLocNo = Integer.parseInt(sciLoc);
							vitalIdToTpDataMap.get(vitalId).setLocCd(sciLocNo);
							if(vitalIdToTpDataMap.get(vitalId) instanceof SciCashType){
								String contractno = vitalIdToTpDataMap.get(vitalId).getContractNo();
								contractno = sciLoc+contractno.substring(4);
								vitalIdToTpDataMap.get(vitalId).setContractNo(contractno);
							}
						}catch(Exception ex){
							logger.error("VitalsMasterKey="+ vitalId +" has problem with sci location defined=" + sciLoc +" in database");
						}
					}
				}
			}catch(SQLException sqlEx) {
	            logger.error("SQLException in updateSCILocation(tpData) : ", sqlEx);
	            throw new FDDEException(sqlEx.getMessage(),sqlEx);
	        } catch (Exception ex) {
	            logger.error("Exception in updateSCILocation(tpData) : ", ex);
	            throw new FDDEException(ex.getMessage(),ex);
	        } finally {
	            closeConnection();
	        }
		}
	}
	private void updateSCILocationARDetails(Map<Integer, SciARDetailDataType> vitalIdToARDetailMap) {
		
		if(vitalIdToARDetailMap != null && vitalIdToARDetailMap.size() > 0){
			
			try{
				getResultSetToUpdateSCILocation(vitalIdToARDetailMap.keySet());
				while(rs.next()){
					int vitalId = rs.getInt("VitalsMasterKey");
					if(vitalIdToARDetailMap.containsKey(vitalId)){
						String sciLoc = rs.getString("Custom33");
						try{
							int sciLocNo = Integer.parseInt(sciLoc);
							vitalIdToARDetailMap.get(vitalId).setLocCd(sciLocNo);
							if(vitalIdToARDetailMap.get(vitalId) instanceof SciARDetailDataType){
								String contractno = vitalIdToARDetailMap.get(vitalId).getSciContractNbr();
								contractno = sciLoc+contractno.substring(4);
								vitalIdToARDetailMap.get(vitalId).setSciContractNbr(contractno);
							}
						}catch(Exception ex){
							logger.error("VitalsMasterKey="+ vitalId +" has problem with sci location defined=" + sciLoc +" in database");
						}
					}
				}
			}catch(SQLException sqlEx) {
	            logger.error("SQLException in updateSCILocationARDetails() : ", sqlEx);
	            throw new FDDEException(sqlEx.getMessage(),sqlEx);
	        } catch (Exception ex) {
	            logger.error("Exception in updateSCILocationARDetails() : ", ex);
	            throw new FDDEException(ex.getMessage(),ex);
	        } finally {
	            closeConnection();
	        }
		}
	}

	private static String SELECT_TOTAL_FINANCED_CHARGES = "SELECT VitalsMasterKey, sum(AmountOfTran)AS totalFinance FROM transactionhistory ";
	private static String SELECT_TOTAL_FINANCED_CHARGES_WHERE_PRE = " WHERE transactionhistory.VitalsMasterKey IN (";
	private static String SELECT_TOTAL_FINANCED_CHARGES_WHERE_POST = ") AND SPFcode = 'F' AND (DeleteTransaction is null or DeleteTransaction = '') ";
	private static String SELECT_TOTAL_FINANCED_CHARGES_GROUP_BY_AND_ORDER_BY = " GROUP BY VitalsMasterKey ORDER BY  VitalsMasterKey";
	private void updateFinanceCharges(String vitalIdListAsString,
			Map<Integer, SciContractType> vitalIdToContractMap) {
		if(vitalIdListAsString != null && vitalIdListAsString.length()>0 
				&& vitalIdToContractMap != null && vitalIdToContractMap.size()>0){
			// construct SQL
			StringBuffer sql = new StringBuffer(512);
			
			// construct SQL
			sql.append(SELECT_TOTAL_FINANCED_CHARGES);
			sql.append(SELECT_TOTAL_FINANCED_CHARGES_WHERE_PRE);
			sql.append(vitalIdListAsString);
			sql.append(SELECT_TOTAL_FINANCED_CHARGES_WHERE_POST);
			sql.append(SELECT_TOTAL_FINANCED_CHARGES_GROUP_BY_AND_ORDER_BY);
			try{
				makeConnection(dbLookup);
				statement = conn.prepareStatement(sql.toString());
				rs = statement.executeQuery();
				while(rs.next()){
					int vitalsId = rs.getInt("VitalsMasterKey");
					SciContractType contract = vitalIdToContractMap.get(vitalsId);
					if(contract != null){
						int totalFinance = rs.getInt("totalFinance");
						contract.setFncChgAmt(SCIServiceDTOFactory.getDeAmount(totalFinance/100.0f));
						contract.setTotalFinanceChargeInterest(contract.getFncChgAmt());
					}
					
				}
			}catch(SQLException sqlEx) {
	            logger.error("SQLException in getSciContractsFromVitalstats() : ", sqlEx);
	            throw new FDDEException(sqlEx.getMessage(),sqlEx);
	        } catch (Exception ex) {
	            logger.error("Exception in getSciContractsFromVitalstats() : ", ex);
	            throw new FDDEException(ex.getMessage(),ex);
	        } finally {
	            closeConnection();
	        }
		}
	}

	private static String SELECT_BIIL_TO_PARTY_AND_VITALSTATS_FROM_TABLE = " FROM billtoparties JOIN vitalstats ON billtoparties.VitalsID = vitalstats.VitalsMasterKey ";
	private static String SELECT_BILL_TO_PARTY_WHERE_PRE = " WHERE VitalsMasterKey IN (";
	private static String SELECT_BILL_TO_PARTY_WHERE_POST = ") AND DeleteCode <> 'D'AND SendInvoice = 'Y' ";
	private static String SELECT_BILL_TO_PARTY_WHERE_NON_INFORMANT = " AND !(FirstName = InformantFirstName AND LastName = InformantLastName) ";
	private static String SELECT_BILL_TO_PARTY_WHERE_NON_BUYER = " AND !(FirstName = PNBuyerFirstName AND LastName = PNBuyerLastName) ";
	private static String SELECT_BILL_TO_PARTY_ORDER_BY = " ORDER BY VitalsID ";
	
	private void updateBillToParty(String vitalIdsList,	Map<Integer, SciContractType> vitalIdToContractMap, 
			boolean nonInformant, boolean nonBuyer, boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows) {
		
		// construct SQL
		StringBuffer sql = new StringBuffer(512+vitalIdsList.length());
		
		// construct select clause
		sql.append(SCIServiceDTOFactory.SELECT_BILL_TO_PARTY);
		// construct from clause
		sql.append(SELECT_BIIL_TO_PARTY_AND_VITALSTATS_FROM_TABLE);
		//construct where clause
		sql.append(SELECT_BILL_TO_PARTY_WHERE_PRE);
		sql.append(vitalIdsList);
		sql.append(SELECT_BILL_TO_PARTY_WHERE_POST);
		if(nonInformant){
			sql.append(SELECT_BILL_TO_PARTY_WHERE_NON_INFORMANT);
		}
		if(nonBuyer){
			sql.append(SELECT_BILL_TO_PARTY_WHERE_NON_BUYER);
		}
		//construct order by clause
		sql.append(SELECT_BILL_TO_PARTY_ORDER_BY);
		
		int oldVitalId = Integer.MIN_VALUE;
		SciContractType contract = null;
		PurchaserType coPurchaser = null;
		try{
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery();
			while(rs.next()){
				
				int newVitalId = rs.getInt("VitalsID");
				
				if(newVitalId != oldVitalId){
					
					// verify backed up coPurcahser is required to reset again
					if(nonBuyer){
						
						if(contract != null && contract.getCoPurchaser() == null && coPurchaser != null){
							// this is PreNeed Serviced scenario that may not exist, better to be safe than sorry 
							// contract of oldVitalId has not found billToParty and coBuyer info exist in coPurchaser  
							contract.setCoPurchaser(coPurchaser);
							contract = null;
						}
						coPurchaser = null;
						
					}else if(!nonInformant && contract != null && coPurchaser != null){
						
						if(contract.getPurchaser() == null){
							contract.setPurchaser(coPurchaser);
						}else if(contract.getCoPurchaser()== null){
							contract.setCoPurchaser(coPurchaser);
						}
						contract = null;
						coPurchaser = null;
					}
					
					contract = vitalIdToContractMap.get(newVitalId);
					
					// verify is this contract needs backup (co)purchaser
					if(nonBuyer && contract.getCoPurchaser() != null 
							&& contract.getCoPurchaser().getPurchaserId().equals(SCIServiceDTOFactory.CO_BUYER_ID)){
						// this is PreNeed Serviced scenario
						// backup coBuyer info into coPurchaser and make room for bill to party
						coPurchaser = contract.getCoPurchaser();
						contract.setCoPurchaser(null);
						
					}else if(!nonInformant && contract.getPurchaser() != null 
							&& contract.getPurchaser().getPurchaserId().equals(SCIServiceDTOFactory.BUYER_ID) 
							&& contract.getPurchaser().getName()==null){
						
						coPurchaser = contract.getPurchaser();
						if(contract.getCoPurchaser()!= null){
							
							contract.setPurchaser(contract.getCoPurchaser());
							contract.setCoPurchaser(null);
							
						}else{
							contract.setPurchaser(null);
						}
					}
					
					//verify is this contract needs to set a purchaser/coPurchaser
					if(contract != null && contract.getPurchaser() != null && contract.getCoPurchaser() != null){
						
						contract = null;
					}
					oldVitalId = newVitalId;
				}
				
				if(contract != null){
					PurchaserType purchaser = new PurchaserType();
					try{
						SCIServiceDTOFactory.updatePurchaserType(purchaser, rs);
						if(contract.getPurchaser() == null){
							contract.setPurchaser(purchaser);
						}else if(contract.getCoPurchaser() == null){
							contract.setCoPurchaser(purchaser);
						}else{
							contract = null;
						}
					}catch (Exception ex) {
			            logger.debug("Exception inside while of updateBillToParty() : ", ex);
			            if(reportInvalidRowIds ||includeInvalidRowIds){
			            	String parentId = contract.getCaseId().getCaseIdType();
		            		String rowId = purchaser.getPurchaserId();
			            	invalidRows.add(getInvalidRowDetails(rowId, "billToParty", parentId, "contract case"));
			            }else{
			            	throw ex;
			            }
			        }
				}
			}
			
		}catch(FDDEException fddeEx){
			throw fddeEx;
		}catch(SQLException sqlEx) {
            logger.error("SQLException in updateBillToParty() : ", sqlEx);
            throw new FDDEException(sqlEx.getMessage(),sqlEx);
        } catch (Exception ex) {
            logger.error("Exception in updateBillToParty() : ", ex);
            throw new FDDEException(ex.getMessage(),ex);
        } finally {
            closeConnection();
        }
	}

	private static String SELECT_TOTAL_NO_OF_LINE_ITEMS = "SELECT VitalsMasterKey, COUNT(*) AS totalNoOfLineItems FROM charges ";
	private static String SELECT_TOTAL_NO_OF_LINE_ITEMS_WHERE_PRE = " WHERE VitalsMasterKey IN (";
	private static String SELECT_TOTAL_NO_OF_LINE_ITEMS_WHERE_POST = ") AND SPFcode = 'S' AND Amount != 0 AND CategoryCode != 'DIS' ";
	private static String SELECT_TOTAL_NO_OF_LINE_ITEMS_GROUP_BY_ORDER_BY = " GROUP BY VitalsMasterKey ORDER BY VitalsMasterKey ";
	private void updateTotalNumberOfLineItems(String vitalIdList,
			Map<Integer, SciContractLineItemType> vitalIdToContractMap) {
		
		if(vitalIdList!= null && vitalIdList.length()> 0 && vitalIdToContractMap != null && vitalIdToContractMap.size()>0){
			// construct SQL
			StringBuffer sql = new StringBuffer(512+vitalIdList.length());
			sql.append(SELECT_TOTAL_NO_OF_LINE_ITEMS);
			sql.append(SELECT_TOTAL_NO_OF_LINE_ITEMS_WHERE_PRE);
			sql.append(vitalIdList);
			sql.append(SELECT_TOTAL_NO_OF_LINE_ITEMS_WHERE_POST);
			sql.append(SELECT_TOTAL_NO_OF_LINE_ITEMS_GROUP_BY_ORDER_BY);
			try{
				makeConnection(dbLookup);
				statement = conn.prepareStatement(sql.toString());
				rs = statement.executeQuery();
				while(rs.next()){
					
					int vitalId = rs.getInt("VitalsMasterKey");
					SciContractLineItemType contractLineItem = vitalIdToContractMap.get(vitalId);
					if(contractLineItem != null){
						int totalNoOflineItems = rs.getInt("totalNoOfLineItems");
						if(!rs.wasNull()){
							contractLineItem.setLineItemCnt(totalNoOflineItems);
						}
					}
				}
			}catch(SQLException sqlEx) {
	            logger.error("SQLException in updateTotalNumberOfLineItems() : ", sqlEx);
	            throw new FDDEException(sqlEx.getMessage(),sqlEx);
	        } catch (Exception ex) {
	            logger.error("Exception in updateTotalNumberOfLineItems() : ", ex);
	            throw new FDDEException(ex.getMessage(),ex);
	        } finally {
	            closeConnection();
	        }
		}
	}
	
	private static String SELECT_CHARGES_AND_INV_HISTORY_FROM_TABLE_1 = 
		" FROM (SELECT VitalsMasterKey, AutoIndex, CategoryCode, Description, Amount, InvMasterKey,SPFcode,ChargeType " +
		        " FROM charges WHERE VitalsMasterKey IN ( " ;
	private static String SELECT_CHARGES_AND_INV_HISTORY_FROM_TABLE_2 = ") AND SPFcode = 'S'AND Amount != 0  AND CategoryCode != 'DIS') A " +
				" LEFT OUTER JOIN " +
				" (SELECT VitalsMasterKey, MasterID, (SUM(Quantity*WholesaleCost)*-1)/(SUM(Quantity)*-1) AS Cost " +
					" FROM invhistory WHERE VitalsMasterKey IN (";
	private static String SELECT_CHARGES_AND_INV_HISTORY_FROM_TABLE_3 = ") AND TransactionType IN ('S','A') GROUP BY VitalsMasterKey, MasterID) B " +
				" ON ( A.VitalsMasterKey = B.VitalsMasterKey AND A.InvMasterKey = B.MasterID)";
	/*private static String SELECT_CHARGES_AND_INV_HISTORY_FROM_TABLE_2 = ") AND SPFcode = 'S'AND Amount != 0  AND CategoryCode != 'DIS') A " +
		" LEFT OUTER JOIN invmaster B ON ( A.InvMasterKey = B.Identity) ";*/
	private static String SELECT_LINE_ITEMS_ORDER_BY = " ORDER BY A.VitalsMasterKey, ChargeType ";

	private List<SciContractLineItemType> updateLineItems(String vitalIdsList,
			Map<Integer, SciContractLineItemType> vitalIdToContractMap, Map<String, String> fdmsToSciItemMap,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows) {
		List<SciContractLineItemType> SciContractsWithLineItems = null;
		// construct SQL
		StringBuffer sql = new StringBuffer(512+vitalIdsList.length()*2);
		
		// construct select clause
		sql.append(SCIServiceDTOFactory.SELECT_LINE_ITEMS);
		// construct from clause
		sql.append(SELECT_CHARGES_AND_INV_HISTORY_FROM_TABLE_1);
		sql.append(vitalIdsList);
		sql.append(SELECT_CHARGES_AND_INV_HISTORY_FROM_TABLE_2);
		sql.append(vitalIdsList);
		sql.append(SELECT_CHARGES_AND_INV_HISTORY_FROM_TABLE_3);
		//construct order by clause
		sql.append(SELECT_LINE_ITEMS_ORDER_BY);
		
		int oldVitalId = Integer.MIN_VALUE;
		List<LineItemType> lineItems = new ArrayList<LineItemType>(32);
//		Map<Integer, DuplicateLineItems> masterIdToDetails = null;
//		List<DuplicateLineItems> mulitipleLineItems = null;
		SciContractLineItemType contractItem = new SciContractLineItemType();
		try{
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();
			int count = 1;
			SciContractsWithLineItems = new ArrayList<SciContractLineItemType>(vitalIdToContractMap.size()*16);
			while(rs.next()){
				
				int newVitalId = rs.getInt("VitalsMasterKey");
				
				if(newVitalId != oldVitalId){
					
					if(lineItems.size()>0){
						contractItem = updateContractWithLineItems(oldVitalId, vitalIdToContractMap, lineItems);
						SciContractsWithLineItems.add(contractItem);
						count = 1;
					}
					oldVitalId = newVitalId;
				}
				LineItemType lineItem = new LineItemType();
				lineItem.setSeq(count);
				
				try{
//					int invId = rs.getInt("MasterID");
//					if(!rs.wasNull()){
//						
//					}
					
					SCIServiceDTOFactory.updateLineItemType(lineItem, rs, fdmsToSciItemMap);
					lineItems.add(lineItem);
					
					count++;
				}catch (Exception ex) {
		            logger.debug("Exception inside while of updateLineItems() : ", ex);
		            if(reportInvalidRowIds ||includeInvalidRowIds){
		            	SciContractLineItemType contract = vitalIdToContractMap.get(oldVitalId);
		            	String parentId = contract.getCaseId().getCaseIdType();
	            		String rowId = Integer.toString(lineItem.getChargesId());
	            		if(lineItem.getInvHistoryId()>0){
	            			rowId = rowId + " " + lineItem.getInvHistoryId();
	            		}
		            	invalidRows.add(getInvalidRowDetails(rowId, "charges invhistory", parentId, "contract case"));
		            }else{
		            	throw ex;
		            }
		        }
			}
			
			if(lineItems.size()>0){
				SciContractLineItemType contract = updateContractWithLineItems(oldVitalId, vitalIdToContractMap, lineItems);
				SciContractsWithLineItems.add(contract);
			}
			
		}catch(FDDEException fddeEx){
			throw fddeEx;
		}catch(SQLException sqlEx) {
            logger.error("SQLException in updateLineItems() : ", sqlEx);
            throw new FDDEException(sqlEx.getMessage(),sqlEx);
        } catch (Exception ex) {
            logger.error("Exception in updateLineItems() : ", ex);
            throw new FDDEException(ex.getMessage(),ex);
        } finally {
            closeConnection();
        }
        return SciContractsWithLineItems;
	}
	
	private SciContractLineItemType updateContractWithLineItems(int vitalId, Map<Integer, SciContractLineItemType> vitalIdToContractMap,List<LineItemType> lineItems){
		SciContractLineItemType contractLineItem = vitalIdToContractMap.get(vitalId);
		if(contractLineItem.getLineItem() == null){
			contractLineItem.setLineItem(lineItems.toArray(new LineItemType[lineItems.size()]));
		}
		lineItems.clear();
		return contractLineItem;
	}

	private static String SELECT_TOTAL_DISCOUNT = "SELECT VitalsMasterKey, CategoryCode, SUM(-Amount) AS totalDiscount FROM charges ";
	private static String SELECT_TOTAL_DISCOUNT_WHERE_PRE = " WHERE VitalsMasterKey IN (";
	private static String SELECT_TOTAL_DISCOUNT_WHERE_POST = ") AND SPFcode = 'S'AND Amount != 0 AND CategoryCode = 'DIS' ";
	private static String SELECT_TOTAL_DISCOUNT_GROUP_BY_ORDER_BY = " GROUP BY VitalsMasterKey, CategoryCode ORDER BY VitalsMasterKey ";
	private void updateTotalDiscount(String vitalIdList,
			Map<Integer, SciContractType> vitalIdToContractMap) {
		
		if(vitalIdList!= null && vitalIdList.length()> 0 && vitalIdToContractMap != null && vitalIdToContractMap.size()>0){
			// construct SQL
			StringBuffer sql = new StringBuffer(512+vitalIdList.length());
			sql.append(SELECT_TOTAL_DISCOUNT);
			sql.append(SELECT_TOTAL_DISCOUNT_WHERE_PRE);
			sql.append(vitalIdList);
			sql.append(SELECT_TOTAL_DISCOUNT_WHERE_POST);
			sql.append(SELECT_TOTAL_DISCOUNT_GROUP_BY_ORDER_BY);
			try{
				makeConnection(dbLookup);
				statement = conn.prepareStatement(sql.toString());
				rs = statement.executeQuery();
				while(rs.next()){
					
					int vitalId = rs.getInt("VitalsMasterKey");
					SciContractType contract = vitalIdToContractMap.get(vitalId);
					if(contract != null){
						int totalDiscount = rs.getInt("totalDiscount");
						if(rs.wasNull()){
							totalDiscount = 0;
						}
						contract.setFinDscntCrAmt(SCIServiceDTOFactory.getDeAmount(totalDiscount/100.0f));
						contract.setSumOfDiscounts(SCIServiceDTOFactory.getDeAmount(totalDiscount/100.0f));
						contract.setFinDscntCrType(FinDscntCrTypeType.value1);
					}
				}
				DeAmountType zeroAmount = SCIServiceDTOFactory.getZeroDeAmount();
				for(SciContractType contract: vitalIdToContractMap.values()){
					if(contract.getFinDscntCrType() == null){
						contract.setFinDscntCrAmt(zeroAmount);
						contract.setFinDscntCrType(FinDscntCrTypeType.value1);
					}
				}
			}catch(SQLException sqlEx) {
	            logger.error("SQLException in updateTotalDiscount() : ", sqlEx);
	            throw new FDDEException(sqlEx.getMessage(),sqlEx);
	        } catch (Exception ex) {
	            logger.error("Exception in updateTotalDiscount() : ", ex);
	            throw new FDDEException(ex.getMessage(),ex);
	        } finally {
	            closeConnection();
	        }
		}
	}

	public List<SciCashType> getSciCash(FuneralHomeIdType funeralHomeId, StrMax10Len locCode, Integer sciLocation,
			CaseIdType caseIdType, CaseFilterType caseFilter,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows) {
		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		Map<Integer, SciCashType> vitalIdToSciCashMap 
									= (Map<Integer, SciCashType>) getSciTPDataList(funeralHomeId, locCode, sciLocation,
																		caseIdType, caseFilter, true,
																		reportInvalidRowIds, includeInvalidRowIds, invalidRows);
		List<SciCashType> sciCash = null;
		if(vitalIdToSciCashMap != null && vitalIdToSciCashMap.size() > 0){
		Map<Integer, SumOfTransDetails> vitalsIdToPayments = 
										updatePayments(
												getVitalIdListAsString(vitalIdToSciCashMap.keySet()), 
												reportInvalidRowIds, includeInvalidRowIds, invalidRows,true);
		Map<Integer, SumOfTransDetails> vitalsIdToAdjustments = 
										updatePayments(
												getVitalIdListAsString(vitalIdToSciCashMap.keySet()), 
												reportInvalidRowIds, includeInvalidRowIds, invalidRows,false);
		sciCash = new ArrayList<SciCashType>(vitalIdToSciCashMap.size());
		for(Integer vitalsId : vitalIdToSciCashMap.keySet()){
			
			SciCashType  cash = vitalIdToSciCashMap.get(vitalsId);
			DeAmountType zeroDeAmount = SCIServiceDTOFactory.getZeroDeAmount();
			cash.setPymtSource(PymtSourceType.Corporate);
			cash.setPymtType(PymtTypeType.value1);
			cash.setFinAdjDescr(FinDscntCrTypeType.value1);
			SumOfTransDetails transDetails = vitalsIdToPayments.get(vitalsId);			
			if(transDetails != null){
				cash.setPymtAmt(factory.getDeAmountType((transDetails.getAmount())/100.0f));
			}else{
				cash.setPymtAmt(zeroDeAmount);
			}
			transDetails = vitalsIdToAdjustments.get(vitalsId);
			if(transDetails != null){
				cash.setFinAdjAmt(factory.getDeAmountType((transDetails.getAmount())/100.0f));
			}else{
				cash.setFinAdjAmt(zeroDeAmount);
			}
			sciCash.add(cash);
		}		
		if(sciCash != null && sciCash.size()>0){
			
			if((reportInvalidRowIds || includeInvalidRowIds)){
				sciCash = validateRequiredFieldsCash(sciCash, invalidRows);
			}			
			if(reportInvalidRowIds){
				sciCash.clear();
				sciCash = null;
	        }
		}
		}
		return sciCash;
	}
	private static String SELECT_TPDATA_FROM_SCI_CONTRACT_NUMBER = " FROM scicontractnumber ";
	private static String SELECT_TPDATA_WHERE_LOCATION_ID = " WHERE FDMSLocationId = ? ";
	private static String SELECT_TPDATA_WHERE_VITAL_ID = " WHERE VitalId = ? ";
	private static String SELECT_TPDATA_ORDER_BY = " ORDER BY VitalId ";
	private Map<Integer, ? extends SciTPData> getSciTPDataList(FuneralHomeIdType funeralHomeId, StrMax10Len locCode, Integer sciLocation,
			CaseIdType caseIdType, CaseFilterType caseFilter, boolean sciCashType,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows){
		
		boolean singleCase = caseIdType != null;
		Map<Integer, SciTPData> vitalIdToSciCashMap = new LinkedHashMap<Integer, SciTPData>();
		if(singleCase){
			funeralHomeId = UserLocation.getFuneralHomeIdTypeFromCaseID(caseIdType.getCaseIdType());
			locCode = getFuneralHomeNumber(funeralHomeId, reportInvalidRowIds, includeInvalidRowIds,invalidRows);
			sciLocation = getSciLocation(funeralHomeId);
			funeralHomeId = null;
		}
		
		if(locCode != null){
			CxlGroupType cxlGroup = null;
			if(sciCashType){
				cxlGroup = CxlGroupType.EnterCash;
			}else{
				cxlGroup = CxlGroupType.EnterLineItem;	
			}
			// construct SQL
			StringBuffer sql = new StringBuffer(512);
			
			// construct select clause
			sql.append(SCIServiceDTOFactory.SELECT_CONTRACT_NUMBER_CASE_ID);
			sql.append(SCIServiceDTOFactory.SELECT_SCI_CONTRACT_NUMBER);
			// construct from clause
			sql.append(SELECT_TPDATA_FROM_SCI_CONTRACT_NUMBER);
			//construct where clause
			if(singleCase){
				sql.append(SELECT_TPDATA_WHERE_VITAL_ID);
			}else{
				sql.append(SELECT_TPDATA_WHERE_LOCATION_ID);
			}
			
			sql.append(SELECT_TPDATA_ORDER_BY);
			
			try{
				makeConnection(dbLookup);
				statement = conn.prepareStatement(sql.toString());
				
				if(singleCase){
					String id = UserLocation.getFuneralHomeIdStr(caseIdType.getCaseIdType());
					statement.setString(1, id+"C");
					id = UserLocation.getVitalsId(caseIdType.getCaseIdType());
					statement.setInt(2,Integer.parseInt(id));
				}else{
					
					statement.setString(1, funeralHomeId.getFuneralHomeIdType()+"C");
					String filterId = UserLocation.getLocationStrFromFuneralHomeId(funeralHomeId.getFuneralHomeIdType());
					statement.setInt(2,Integer.parseInt(filterId));
				}
				
				rs = statement.executeQuery();
				
				Date today = new Date();
				
				while(rs.next()){
					SciTPData sciTPData = sciCashType? new SciCashType():new SciContractLineItemType();
					try{ 
						SCIServiceDTOFactory.updateSciTPData(sciTPData, rs, cxlGroup, locCode, sciLocation.intValue(), null,today,false);
						if(sciCashType){
							SCIServiceDTOFactory.updateSaleDate(sciTPData, rs);
						}
						int vitalsId = rs.getInt("VitalId");
						
						vitalIdToSciCashMap.put(vitalsId, sciTPData);
						
					}catch (Exception ex) {
			            logger.debug("Exception inside while of getSciTPDataList() : ", ex);
			            if(reportInvalidRowIds ||includeInvalidRowIds){
			            		String rowId = sciTPData.getCaseId().getCaseIdType();
				            	invalidRows.add(getInvalidRowDetails(rowId, "contract", null, null));
				            }else{
				            	throw ex;
				            }
			        }
				}
				
			}catch(FDDEException fddeEx){
				throw fddeEx;
			}catch(SQLException sqlEx) {
	            logger.error("SQLException in getSciTPDataList() : ", sqlEx);
	            throw new FDDEException(sqlEx.getMessage(),sqlEx);
	        } catch (Exception ex) {
	            logger.error("Exception in getSciTPDataList() : ", ex);
	            throw new FDDEException(ex.getMessage(),ex);
	        } finally {
	            closeConnection();
	        }
	        updateSCILocation(vitalIdToSciCashMap);
		}
		return vitalIdToSciCashMap;
	}
	private List<SciCashType> validateRequiredFieldsCash(List<SciCashType> sciCash,
			List<InvalidRowDetails> invalidRows) {
		
		List<SciCashType> validSciCash = new ArrayList<SciCashType>(sciCash.size());
		for(Iterator<SciCashType> it= sciCash.iterator(); it.hasNext();){
			SciCashType cash = it.next();
			
			StringBuffer errorMsg = validateRequiredFieldsSciTPData(cash);
			boolean invalid = errorMsg != null && errorMsg.length()>0;
					
			if (invalid) {
				
				String parentId = cash.getCaseId().getCaseIdType();
				invalidRows.add(getInvalidRowDetails(errorMsg.toString(),
						"RequiredFieldsMissing", parentId, "cash"));

			} else {
				validSciCash.add(cash);
			}
		}
		return validSciCash;
	}
	
	public static String SELECT_PAYMENTS = "SELECT VitalsMasterKey,(SUM(AmountOfTran*-1)) AS amount ";
	private static String SELECT_TRANSACTION_HISTRORY_FROM_TABLE = " FROM transactionhistory ";
	private static String SELECT_PAYMENTS_WHERE_PRE = " WHERE VitalsMasterKey IN (";
	private static String SELECT_PAYMENTS_WHERE_POST = ") AND SPFcode = 'P' AND ( deletetransaction =  '  ' OR " +
															" deletetransaction IS NULL ) AND GLacct ";
	private static String SELECT_PAYMENTS_WHERE_ADJUSTMENT =" 10030 ";
	private static String SELECT_PAYMENTS_GROUP_BY = " GROUP BY VitalsMasterKey ";
	private static String SELECT_PAYMENTS_ORDER_BY = " ORDER BY VitalsMasterKey, TranHistID ";
	
	private Map<Integer, SumOfTransDetails> updatePayments(String vitalIdsList,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows,boolean isPayment) {
		
		Map<Integer, SumOfTransDetails> vitalsIdToPayments = new HashMap<Integer, SumOfTransDetails>();
		// construct SQL
		StringBuffer sql = new StringBuffer(512+vitalIdsList.length());
		
		// construct select clause
		sql.append(SCIServiceDTOFactory.SELECT_PAYMENTS);
		if(isPayment){
			sql.append(" ,COUNT(*) AS noPayments ");
		}
		// construct from clause
		sql.append(SELECT_TRANSACTION_HISTRORY_FROM_TABLE);
		// construct where clause
		sql.append(SELECT_PAYMENTS_WHERE_PRE);
		sql.append(vitalIdsList);
		sql.append(SELECT_PAYMENTS_WHERE_POST);
		if(isPayment){
			sql.append(" = ");
		}else{
			sql.append(" <> ");
		}
		sql.append(SELECT_PAYMENTS_WHERE_ADJUSTMENT);
		//construct group by clause
		sql.append(SELECT_PAYMENTS_GROUP_BY);
		//construct order by clause
		sql.append(SELECT_PAYMENTS_ORDER_BY);
		
		try{
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();
			int vitalId = -1;
			while(rs.next()){
				try{
				vitalId = rs.getInt("VitalsMasterKey");
				SumOfTransDetails transDetails = new SumOfTransDetails();
				transDetails.setAmount(rs.getLong("amount"));
				if(isPayment){
					transDetails.setNoTrans(rs.getInt("noPayments"));
				}
				vitalsIdToPayments.put(vitalId, transDetails);
				}catch (Exception ex) {
		            logger.debug("Exception inside while of updatePayments() : ", ex);
		            if(reportInvalidRowIds ||includeInvalidRowIds){
		            	String rowType = isPayment? "payment" : "adjustment" ;
	            		String rowId = Integer.toString(vitalId);
	            		
		            	invalidRows.add(getInvalidRowDetails(rowId, rowType, null, null));
		            }else{
		            	throw ex;
		            }
		        }
			}
			
		}catch(FDDEException fddeEx){
			throw fddeEx;
		}catch(SQLException sqlEx) {
            logger.error("SQLException in updatePayments() : ", sqlEx);
            throw new FDDEException(sqlEx.getMessage(),sqlEx);
        } catch (Exception ex) {
            logger.error("Exception in updatePayments() : ", ex);
            throw new FDDEException(ex.getMessage(),ex);
        } finally {
            closeConnection();
        }
		return vitalsIdToPayments;
	}
	
	public StrMax10Len getFuneralHomeNumber(FuneralHomeIdType funeralHomeId, 
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows){
		if(reportInvalidRowIds){
			includeInvalidRowIds = true;
			reportInvalidRowIds = false;
		}
		List<FuneralHomeType> funeralHome = getFuneralHomes(funeralHomeId,null, reportInvalidRowIds, includeInvalidRowIds,invalidRows);
		
		if(funeralHome != null && funeralHome.size()==1 && funeralHome.get(0).getNumber() != null){
			return funeralHome.get(0).getNumber();
		}
		
		return null;
	}
	
	public void getCasesWithLastActivityDateAndFinanceCharges(FuneralHomeIdType funeralHomeId, boolean atNeed,
			Map<Integer,Date> lastActivieCases, Map<Integer, Float> balancedCases, List<Integer> finaceChargedCases){
		if(funeralHomeId != null){
			String vitalIdList = getAllVitalIdsForAFuneralHome(funeralHomeId, atNeed, balancedCases);
			if(vitalIdList != null && vitalIdList.length()>0){
				if(lastActivieCases != null){
					getLastActivieCases(vitalIdList, lastActivieCases);
				}
				if(finaceChargedCases != null){
					getFinanceChargedCases(vitalIdList, finaceChargedCases);
				}
			}
		}
	}
	
	private static String SELECT_VITALIDS_FROM_VITALSTATS = "SELECT vitalstats.VitalsMasterKey " ;
	private static String SELECT_BALANCE_FROM_FINANCEDATA =	",(TotalCharges-TotalPaidToDate)/100 AS Balance ";
	private static String FROM_VITALSTATS = " FROM vitalstats ";
	private static String FROM_FINANCEDATA = ", financialdata ";
	private static String WHERE_VITALIDS_FROM_VITALSTATS = " AND vitalstats.VitalsMasterKey = financialdata.VitalsMasterKey AND SentToAccounting = 1 ";
	 
	private String getAllVitalIdsForAFuneralHome(FuneralHomeIdType funeralHomeId, boolean atNeed, Map<Integer, Float> balancedCases) {
		
		if(funeralHomeId != null){
			StringBuffer vitalIds = new StringBuffer();
			vitalIds.append(SELECT_VITALIDS_FROM_VITALSTATS);
			if(atNeed){
				vitalIds.append(SELECT_BALANCE_FROM_FINANCEDATA);
			}
			vitalIds.append(FROM_VITALSTATS);
			if(atNeed){
				vitalIds.append(FROM_FINANCEDATA);
			}
			vitalIds.append(SELECT_CASES_SQL_WHERE);
			if(atNeed){
				vitalIds.append(WHERE_VITALIDS_FROM_VITALSTATS);
				vitalIds.append(SELECT_CASES_SQL_ATNEED_FILTER);
			}else{
				vitalIds.append(SELECT_CASES_SQL_PRENEED_FILTER);
			}
			vitalIds.append(SELECT_CASES_SQL_NOT_VOIDE_FILTER);
			
			try{
				makeConnection(dbLookup);
				statement = conn.prepareStatement(vitalIds.toString());
				setStatementAndReturnFuneralHomeId(funeralHomeId, null);
				
				rs = statement.executeQuery();
				vitalIds.delete(0, vitalIds.capacity());
				// vitalIds = new StringBuffer();
				while(rs.next()){
					if(vitalIds.length()>0){
						vitalIds.append(',');
					}
					int vitalId = rs.getInt("VitalsMasterKey");
					vitalIds.append(vitalId);
					if(atNeed){
						float balance = rs.getFloat("Balance");
						if(!rs.wasNull() && balance>0.0f){
							balancedCases.put(vitalId, balance);
						}
					}
				}
				if(vitalIds.length()>0){
					return vitalIds.toString();
				}
			}catch(SQLException sqlEx) {
	            logger.error("SQLException in getAllVitalIdsForAFuneralHome() : ", sqlEx);
	            throw new FDDEException(sqlEx.getMessage(),sqlEx);
	        } catch (Exception ex) {
	            logger.error("Exception in getAllVitalIdsForAFuneralHome() : ", ex);
	            throw new FDDEException(ex.getMessage(),ex);
	        } finally {
	            closeConnection();
	        }
		}
		return null;
	}

	private static String SELECT_LAST_ACTIVITY_CASES = "SELECT VitalsMasterKey, MAX(DateOfTran) lastActivityDate FROM transactionhistory ";
	private static String SELECT_LAST_ACTIVITY_CASES_WHERE_PRE = "WHERE VitalsMasterKey IN (";
	private static String SELECT_LAST_ACTIVITY_CASES_WHERE_POST = ") AND (DeleteTransaction is null or DeleteTransaction = '') " +
			" GROUP BY VitalsMasterKey HAVING lastActivityDate > '2008-12-31'  ORDER BY  VitalsMasterKey";
	private void getLastActivieCases(String vitalIdList,
			Map<Integer, Date> lastActivieCases) {
		
		if(vitalIdList != null && vitalIdList.length()>0 && lastActivieCases != null){
			StringBuffer sql = new StringBuffer();
			sql.append(SELECT_LAST_ACTIVITY_CASES);
			sql.append(SELECT_LAST_ACTIVITY_CASES_WHERE_PRE);
			sql.append(vitalIdList);
			sql.append(SELECT_LAST_ACTIVITY_CASES_WHERE_POST);
			
			try{
				makeConnection(dbLookup);
				statement = conn.prepareStatement(sql.toString());
				rs = statement.executeQuery();
				
				while(rs.next()){
					
					Integer vitalId = Integer.valueOf(rs.getInt("VitalsMasterKey"));
					Date lastActivityDate = rs.getDate("lastActivityDate");
					lastActivieCases.put(vitalId, lastActivityDate);
				}
				
			}catch(SQLException sqlEx) {
	            logger.error("SQLException in getLastActivieCases() : ", sqlEx);
	            throw new FDDEException(sqlEx.getMessage(),sqlEx);
	        } catch (Exception ex) {
	            logger.error("Exception in getLastActivieCases() : ", ex);
	            throw new FDDEException(ex.getMessage(),ex);
	        } finally {
	            closeConnection();
	        }
		}
		return;
	}

	private static String SELECT_FINANCE_CHARGED_CASES = "SELECT distinct VitalsMasterKey FROM transactionhistory ";
	private static String SELECT_FINANCE_CHARGED_CASES_WHERE_PRE = " WHERE transactionhistory.VitalsMasterKey IN (";
	private static String SELECT_FINANCE_CHARGED_CASES_WHERE_POST = ") AND (DeleteTransaction is null or DeleteTransaction = '') " +
			" AND SPFcode = 'F' ORDER BY  VitalsMasterKey";
	private void getFinanceChargedCases(String vitalIdList,
			List<Integer> finaceChargedCases) {
		StringBuffer sql = new StringBuffer();
		sql.append(SELECT_FINANCE_CHARGED_CASES);
		sql.append(SELECT_FINANCE_CHARGED_CASES_WHERE_PRE);
		sql.append(vitalIdList);
		sql.append(SELECT_FINANCE_CHARGED_CASES_WHERE_POST);
		
		try{
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();
			
			while(rs.next()){
				
				Integer vitalId = Integer.valueOf(rs.getInt("VitalsMasterKey"));
				finaceChargedCases.add(vitalId);
			}
			
		}catch(SQLException sqlEx) {
            logger.error("SQLException in getFinanceChargedCases() : ", sqlEx);
            throw new FDDEException(sqlEx.getMessage(),sqlEx);
        } catch (Exception ex) {
            logger.error("Exception in getFinanceChargedCases() : ", ex);
            throw new FDDEException(ex.getMessage(),ex);
        } finally {
            closeConnection();
        }
	}
	
	public void generateSequenceNumbers(List<? extends SciTPData> tpdataList, boolean globalExtract){
		
		if(tpdataList!= null && tpdataList.size()>0){
			String previousIdString = null;
			int i=1;
			for(SciTPData tpData: tpdataList){
				previousIdString = SCIServiceDTOFactory.getNumberString(previousIdString, i, 5);
				String id = tpData.getId();
				id = id != null? id+previousIdString : previousIdString;
				tpData.setId(id);
				if(globalExtract){
					tpData.setContractNo(Integer.toString(99000000+ i));
				}
				i++;
			}
			if(globalExtract && tpdataList.get(0) instanceof SciContractType){
				updateContractNumbers((List<SciContractType>)tpdataList);
			}
		}
	}
	
	private void updateContractNumbers(List<SciContractType> sciContracts) {
		
		if(sciContracts == null || sciContracts.isEmpty()){
			throw new FDDEException("Contracts can not be null or nill to update SCI contract numbers...");
		}
		if(truncateSciContractNumberTable()){	
			
			Map<Integer, List<SciContractType>> fdmsLocationToContractList= getFDMSLocationToContractsMap(sciContracts);
			
			try{
				
				makeConnection(dbLookup,true);
				conn.setAutoCommit(false);
				
				statement = conn.prepareStatement("INSERT INTO scicontractnumber (VitalId, ContractNo, SalesDate, UpdateDate, FDMSLocationId) values(?, ?, ?, ?, ?)");
				int count=0;
				Timestamp today= new Timestamp(System.currentTimeMillis()); 
				
				for(Integer locationId: fdmsLocationToContractList.keySet()){
					List<SciContractType> contracts = fdmsLocationToContractList.get(locationId);
					
					for(SciContractType contract: contracts){
						int i=1;
						statement.setInt(i++, contract.getSalesId());
						statement.setInt(i++, Integer.parseInt(contract.getContractNo()));
						if(contract.getSaleDate()!= null){
							java.sql.Date saleDate = new java.sql.Date(contract.getSaleDate().getTime());
							statement.setDate(i++, saleDate);
						}else{
							statement.setDate(i++, null);
						}
						statement.setTimestamp(i++, today);
						statement.setInt(i++, locationId.intValue());
						statement.addBatch();
						count++;
						if(count==1000){
							statement.executeBatch();
							count=0;
						}
					}
				}
				if(count>0){
					statement.executeBatch();
				}
				conn.commit();
			
			}catch(FDDEException fddeEx) {
				try {
					if(conn != null){
						conn.rollback();
					}
				} catch (SQLException e) {
					logger.error("Database connection can not rollback", e);
				}
				throw fddeEx;
			}catch(SQLException sqlEx) {
	            logger.error("SQLException in updateContractNumbers() : ", sqlEx);
	            try {
	            	if(conn != null){
	            		conn.rollback();
	            	}
				} catch (SQLException e) {
					logger.error("Database connection can not rollback", e);
				}
	            throw new FDDEException(sqlEx.getMessage(),sqlEx);
	        } catch (Exception ex) {
	            logger.error("Exception in updateContractNumbers() : ", ex);
	            try {
	            	if(conn != null){
	            		conn.rollback();
	            	}
				} catch (SQLException e) {
					logger.error("Database connection can not rollback", e);
				}
	        	
	            throw new FDDEException(ex.getMessage(),ex);
	        } finally {
	        	closeConnection();
	        	canSciContractNumberTruncate = true;
	        }
		}else{
			
			throw new FDDEException("Unable to truncate sci contract table . . .");
		}
	}
	private static boolean canSciContractNumberTruncate = true;
	private boolean truncateSciContractNumberTable(){
		boolean truncated = false;
		synchronized(SCIFddeServiceDAO.class){
			if(canSciContractNumberTruncate){
				try{
					makeConnection(dbLookup);
					Statement truncateStatement = conn.createStatement();
					truncateStatement.execute("TRUNCATE scicontractnumber");
					truncateStatement.close();
					truncated = true;
					canSciContractNumberTruncate = false;
				}catch(SQLException sqlEx) {
		            logger.error("SQLException in truncateSciContractNumberTable() : ", sqlEx);
		            throw new FDDEException(sqlEx.getMessage(),sqlEx);
		        } catch (Exception ex) {
		            logger.error("Exception in truncateSciContractNumberTable() : ", ex);
		            throw new FDDEException(ex.getMessage(),ex);
		        } finally {
		            closeConnection();
		        }
			}
		}
        return truncated;
	}
	private Map<Integer, List<SciContractType>> getFDMSLocationToContractsMap(
			List<SciContractType> sciContracts) {
		
		Map<Integer, List<SciContractType>> fdmsLocationToContractList = null;
		
		if(sciContracts != null && sciContracts.size()>0){
			
			fdmsLocationToContractList = new LinkedHashMap<Integer, List<SciContractType>>(225);
			
			List<SciContractType> contracts = null;
			String oldLocationId = "";
			for(SciContractType contract: sciContracts){
				String newLocationId = UserLocation.getLocationStrFromCaseId(contract.getCaseId().getCaseIdType());
				if(!oldLocationId.equals(newLocationId)){
					if(contracts != null && contracts.size()>0){
						Integer locationId = Integer.valueOf(oldLocationId);
						if(fdmsLocationToContractList.containsKey(locationId)){
							fdmsLocationToContractList.get(locationId).addAll(contracts);
						}else{
							fdmsLocationToContractList.put(locationId, contracts);
						}
					}
					contracts = new ArrayList<SciContractType>();
					oldLocationId = newLocationId;
				}
				contracts.add(contract);
			}
			if(contracts != null && contracts.size()>0){
				Integer locationId = Integer.valueOf(oldLocationId);
				if(fdmsLocationToContractList.containsKey(locationId)){
					fdmsLocationToContractList.get(locationId).addAll(contracts);
				}else{
					fdmsLocationToContractList.put(locationId, contracts);
				}
			}
		}
		return fdmsLocationToContractList;
	}

	public Integer getSciLocation(FuneralHomeIdType funeralHomeId){
		int sciLocation = 0;
		if(funeralHomeId != null){
			UserLocation reqLocation = UserLocation.getLocationFromFuneralHomeID(funeralHomeId);
			String sql = getSciLocationSQL(Integer.toString(reqLocation.getLocale()), Integer.toString(reqLocation.getLocation()));
		try{
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();			
			while(rs.next()){
				int locale = rs.getInt("FDMSLocaleId");
				int location = rs.getInt("FDMSLocationId");
				if(reqLocation.equals(locale, location)){
						sciLocation = rs.getInt("SciLocationId");						
					}else{
						sciLocation = 0;
					}				
			}					
		}catch(SQLException sqlEx) {
            logger.error("SQLException in getSciLocation() : ", sqlEx);
            throw new FDDEException(sqlEx.getMessage(),sqlEx);
        } catch (Exception ex) {
            logger.error("Exception in getSciLocation() : ", ex);
            throw new FDDEException(ex.getMessage(),ex);
        } finally {
            closeConnection();
        }
		}	
	return sciLocation;
	}
	
	public Map<FuneralHomeType, Integer> getSciLocation(FuneralHomeType[] funeralHomes){
		Map<FuneralHomeType, Integer> funeralHomeToSciLocationMap = null;
		if(funeralHomes != null && funeralHomes.length>0){
			Map<UserLocation, FuneralHomeType> fdmsLocationToFuneralHome = new HashMap<UserLocation, FuneralHomeType>(funeralHomes.length);
			List<UserLocation> reqLocations = new ArrayList<UserLocation>(funeralHomes.length);
			int sciLocation = 0;
			for(FuneralHomeType funeralHome : funeralHomes){
				if(funeralHome != null){
					UserLocation reqLocation = UserLocation.getLocationFromFuneralHomeID(funeralHome.getId());
					reqLocations.add(reqLocation);
					fdmsLocationToFuneralHome.put(reqLocation, funeralHome);
				}
			}
			if(reqLocations.size()>0){
				funeralHomeToSciLocationMap = new HashMap<FuneralHomeType, Integer>(reqLocations.size());
				String sql = getSciLocationSQL(UserLocation.getListStr(reqLocations, true), UserLocation.getListStr(reqLocations, false));
				try{
					makeConnection(dbLookup);
					statement = conn.prepareStatement(sql);
					rs = statement.executeQuery();
					
					while(rs.next()){
						int locale = rs.getInt("FDMSLocaleId");
						int location = rs.getInt("FDMSLocationId");
						for(UserLocation reqLocation : reqLocations){
							if(reqLocation.equals(locale, location)){
								sciLocation = rs.getInt("SciLocationId");
								FuneralHomeType funeralHome = fdmsLocationToFuneralHome.get(reqLocation);
								funeralHomeToSciLocationMap.put(funeralHome, sciLocation);
								break;
							}
						}
					}	
					for(FuneralHomeType funeralHome : funeralHomes){
						if(!funeralHomeToSciLocationMap.containsKey(funeralHome)){
							funeralHomeToSciLocationMap.put(funeralHome, 0);
						}
					}
				}catch(SQLException sqlEx) {
		            logger.error("SQLException in getSciLocation() : ", sqlEx);
		            throw new FDDEException(sqlEx.getMessage(),sqlEx);
		        } catch (Exception ex) {
		            logger.error("Exception in getSciLocation() : ", ex);
		            throw new FDDEException(ex.getMessage(),ex);
		        } finally {
		            closeConnection();
		        }
			}
		}
		return funeralHomeToSciLocationMap;
	}
	
	private static String SELECT_SCI_LOCATION = " SELECT SciLocationId , FDMSLocaleId , FDMSLocationId ";
	private static String FROM_SCI_LOCATION = " FROM scilocations ";
	private static String WHERE1_SCI_LOCATION = " WHERE FDMSLocaleId IN (  ";
	private static String WHERE2_SCI_LOCATION = " ) AND FDMSLocationId IN ( ";
	
	private String getSciLocationSQL(String locales, String locations){
		// construct SQL
		StringBuffer sql = new StringBuffer(130+locales.length()+locations.length());
		//select clause
		sql.append(SELECT_SCI_LOCATION);
		//from clause
		sql.append(FROM_SCI_LOCATION);
		//where clause
		sql.append(WHERE1_SCI_LOCATION);
		sql.append(locales);
		sql.append(WHERE2_SCI_LOCATION);
		sql.append(locations);
		sql.append(" ) ");		
		
		return sql.toString();
		
	}
	
	private static String SELECT_SCI_ITEMS = "SELECT FDMSCat , ItemCode FROM sciitems";
	
	public Map<String, String> getFdmsItemToSciItem(){
		Map<String, String> fdmsToSciItemMap = new HashMap<String , String>();
		
		try{
			makeConnection(dbLookup);
			statement = conn.prepareStatement(SELECT_SCI_ITEMS);
			rs = statement.executeQuery();			
			while(rs.next()){ 
				String fdmsCat = rs.getString("FDMSCat");
				String code = rs.getString("ItemCode");
				fdmsToSciItemMap.put(fdmsCat, code);
			}
		}catch(SQLException sqlEx) {
            logger.error("SQLException in getFdmsItemToSciItem() : ", sqlEx);
            throw new FDDEException(sqlEx.getMessage(),sqlEx);
        } catch (Exception ex) {
            logger.error("Exception in getFdmsItemToSciItem() : ", ex);
            throw new FDDEException(ex.getMessage(),ex);
        } finally {
            closeConnection();
        }		
		return fdmsToSciItemMap;
	}

	public List<SciContractLineItemType> getSciLineItems(
			FuneralHomeIdType funeralHomeId, StrMax10Len locCode,
			Integer sciLocation, CaseIdType caseId, CaseFilterType caseFilter,Map<String, String> fdmsToSciItemMap,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows) {
				
		Map<Integer, SciContractLineItemType> vitalIdToSciLineItemMap 
								= (Map<Integer, SciContractLineItemType>) getSciTPDataList(funeralHomeId, 
										locCode, sciLocation,caseId, caseFilter, false,reportInvalidRowIds, 
										includeInvalidRowIds, invalidRows);
		updateTotalNumberOfLineItems(getVitalIdListAsString(vitalIdToSciLineItemMap.keySet()), vitalIdToSciLineItemMap);
		List<SciContractLineItemType> sciLineItems = null;
		if(vitalIdToSciLineItemMap!= null && vitalIdToSciLineItemMap.size()>0){
			sciLineItems = updateLineItems(getVitalIdListAsString(vitalIdToSciLineItemMap.keySet()), 
										vitalIdToSciLineItemMap, fdmsToSciItemMap, reportInvalidRowIds, includeInvalidRowIds, invalidRows);
		}		
				if(sciLineItems != null && sciLineItems.size()>0){					
					if((reportInvalidRowIds || includeInvalidRowIds)){
						sciLineItems = validateRequiredFieldsLineItems(sciLineItems, invalidRows);
					}					
					if(reportInvalidRowIds){
						sciLineItems.clear();
						sciLineItems = null;
			        }					
				}
				return sciLineItems;
			}
	
	private List<SciContractLineItemType> validateRequiredFieldsLineItems(List<SciContractLineItemType> sciLineItems,
																		   List<InvalidRowDetails> invalidRows){
		List<SciContractLineItemType> validContractLineItems = new ArrayList<SciContractLineItemType>(sciLineItems.size());
		for(Iterator<SciContractLineItemType> it= sciLineItems.iterator(); it.hasNext();){
			SciContractLineItemType sciLineItem = it.next();
			
			StringBuffer errorMsg = validateRequiredFieldsSciTPData(sciLineItem);
			boolean invalid = errorMsg != null && errorMsg.length()>0;	
			if(sciLineItem.getLineItem()!=null){
				int count = 0;
				int index=0;
				for(LineItemType lineItem : sciLineItem.getLineItem()){					
					boolean invalidLineItem = false;					
					if(lineItem.getItemCode()==null){
						invalidLineItem = true;
						errorMsg = addStringToStringList(errorMsg, lineItem.getChargesId()+".LineItem.ItemCode");
					}					
					if(lineItem.getPrice()==null){
						invalidLineItem = true;
						errorMsg = addStringToStringList(errorMsg, lineItem.getChargesId()+".LineItem.Price");
					}	
					if(!invalid && invalidLineItem){
						sciLineItem.getLineItem()[index]=null;
						count++;
					}
				}
				if(!invalid && count>0){
					String parentId = sciLineItem.getCaseId().getCaseIdType();
					invalidRows.add(getInvalidRowDetails(errorMsg.toString(), "RequiredFieldsMissing", parentId, "contract"));
					LineItemType[] lineItems = new LineItemType[sciLineItem.getLineItem().length - count];
					index=0;
					for(LineItemType lineItem : sciLineItem.getLineItem()){
						if(lineItem != null && index<lineItems.length){
							lineItems[index++] = lineItem; 
						}
					}
					sciLineItem.setLineItem(lineItems);
				}
			}			
			if(invalid){
				String parentId = sciLineItem.getCaseId().getCaseIdType();
				String contractNo = sciLineItem.getContractNo();
				invalidRows.add(getInvalidRowDetails(errorMsg.toString(), "RequiredFieldsMissing", contractNo, parentId ));
			}else{
				validContractLineItems.add(sciLineItem);
			}
			
		}
		return validContractLineItems;
	}

	private static String SCI_AR_DETAIL_SELECT_PRE = "SELECT CONCAT(?, VitalId) AS caseId, Vitalid ";
	private static String SCI_AR_DETAIL_FROM = " FROM scicontractnumber,vitalstats,scilocations ";
	private static String SCI_AR_DETAIL_WHERE_PRE = " WHERE scicontractnumber.Vitalid = vitalstats.VitalsMasterKey " +
												" AND scicontractnumber.FDMSLocationId = scilocations.FDMSLocationId ";
	private static String SCI_AR_DETAIL_WHERE_POST = " AND vitalstats.localenumber = ? AND vitalstats.chapelnumber = ? ";
	private static String SCI_AR_DETAIL_ORDER_BY = " ORDER BY scicontractnumber.Vitalid ";
	
	public List<SciARDetailDataType> getSciARDetails(
			FuneralHomeIdType funeralHomeId, CaseIdType caseId , CaseFilterType caseFilter,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows) {

		FddeXsdFactory factory = FddeXsdFactory.getInstance();
		List<SciARDetailDataType> sciARDetailData = null;
		boolean singleCase = caseId != null;
		Map<Integer, SciARDetailDataType> vitalIdToSciARMap = new LinkedHashMap<Integer, SciARDetailDataType>();
		if(singleCase){
			funeralHomeId = UserLocation.getFuneralHomeIdTypeFromCaseID(caseId.getCaseIdType());
		}
		StringBuffer sql = new StringBuffer(512);
		sql.append(SCI_AR_DETAIL_SELECT_PRE);
		sql.append(SCIServiceDTOFactory.SCI_AR_DETAIL_SELECT_POST);
		sql.append(SCI_AR_DETAIL_FROM);
		sql.append(SCI_AR_DETAIL_WHERE_PRE);
		sql.append(SCI_AR_DETAIL_WHERE_POST);
		if(singleCase){
			sql.append(SELECT_SINGLE_CASE_FILTER);
		}
		sql.append(SCI_AR_DETAIL_ORDER_BY);
		SciARDetailDataType arDetail = null;
		try{
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, funeralHomeId.getFuneralHomeIdType()+"C");
			UserLocation requestedLoc = null;
			int vitalsId = -1;
			if(singleCase){
				Map<Integer, UserLocation>  map = UserLocation.getVitalsToLocationMap(caseId);
				for(Integer vital: map.keySet()){
					vitalsId = vital.intValue();
					requestedLoc = map.get(vital);
				}
			}else{
				requestedLoc = UserLocation.getLocationFromFuneralHomeID(funeralHomeId);
			}
			statement.setInt(2, requestedLoc.getLocale());
			statement.setInt(3, requestedLoc.getLocation());
			if(singleCase){
				statement.setInt(4, vitalsId);
			}
			rs = statement.executeQuery();
			
			sciARDetailData = new ArrayList<SciARDetailDataType>(vitalIdToSciARMap.size());
			while(rs.next()){
				arDetail = new SciARDetailDataType();
				try{		
					String caseValue = rs.getString("caseId");
					if(!rs.wasNull() && factory.isStringNotNullOrNill(caseValue)){
						arDetail.setCaseId(factory.createCaseIdType(caseValue));
					}
					SCIServiceDTOFactory.updateSciARDetailData(arDetail, rs );
					vitalsId = rs.getInt("Vitalid");
					vitalIdToSciARMap.put(vitalsId, arDetail);						
					sciARDetailData.add(arDetail);

				}catch (Exception ex) {
					logger.debug("Exception inside while of getSciARDetails() : ", ex);
					if(reportInvalidRowIds ||includeInvalidRowIds){
						String rowId = arDetail.getCaseId().getCaseIdType();
						invalidRows.add(getInvalidRowDetails(rowId, "contract", null, null));
					}else{
						throw ex;
					}
				}					
			}
		}catch(FDDEException fddeEx){
			throw fddeEx;
		}catch(SQLException sqlEx) {
			logger.error("SQLException in getSciARDetails() : ", sqlEx);
			throw new FDDEException(sqlEx.getMessage(),sqlEx);
		} catch (Exception ex) {
			logger.error("Exception in getSciARDetails() : ", ex);
			throw new FDDEException(ex.getMessage(),ex);
		} finally {
			closeConnection();
		}	
		updateSCILocationARDetails(vitalIdToSciARMap);
		updateTransactionDetails(arDetail,vitalIdToSciARMap,reportInvalidRowIds,
									includeInvalidRowIds,invalidRows );
		
		return sciARDetailData;
	}
	
	private static String AR_TRANS_DETAILS_FROM = " FROM transactionhistory ";
	private static String AR_TRANS_DETAILS_WHERE_PRE = " WHERE SPFcode = 'P' AND (DeleteTransaction IS NULL " +
														" OR DeleteTransaction = '')AND VitalsMasterKey IN (";
	private static String AR_TRANS_DETAILS_WHERE_POST = ")";
	private static String AR_TRANS_DETAILS_ORDERBY = "ORDER BY VitalsMasterKey ,DatePaid ";

	private void updateTransactionDetails(SciARDetailDataType arDetail,
			Map<Integer, SciARDetailDataType> vitalIdToSciARMap,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows) {
		
		List<TranDetailType> transDetails = new ArrayList<TranDetailType>(); 
		
		String value= null;
		if(vitalIdToSciARMap != null && vitalIdToSciARMap.size() > 0){ 
			Map<String, String> pymtTypeCodeToDescMap = null;
			{
				int locale = 0;
				for(SciARDetailDataType contract: vitalIdToSciARMap.values()){
					locale=contract.getLocaleKNA();
					break;
				}
				pymtTypeCodeToDescMap = getPymtTypeMap(locale);
			}
			TranDetailType trans = null;
			String vitalIds = getVitalIdListAsString(vitalIdToSciARMap.keySet());
			StringBuffer sql = new StringBuffer(512);
			sql.append(SCIServiceDTOFactory.AR_TRANS_DETAILS_SELECT);
			sql.append(AR_TRANS_DETAILS_FROM);
			sql.append(AR_TRANS_DETAILS_WHERE_PRE);
			sql.append(vitalIds);
			sql.append(AR_TRANS_DETAILS_WHERE_POST);
			sql.append(AR_TRANS_DETAILS_ORDERBY);			
			try{
				makeConnection(dbLookup);
				statement = conn.prepareStatement(sql.toString());
				rs = statement.executeQuery();
				
				int oldVitalId= -1;
				SimpleDateFormat keyDateFormat = new SimpleDateFormat("yyyyMMdd");
				while(rs.next()){
					int newVitalId = rs.getInt("VitalsMasterKey");
					if(oldVitalId != newVitalId){
						if(transDetails.size()> 0){
							SciARDetailDataType contract = vitalIdToSciARMap.get(oldVitalId);
							contract.setTranDetail(transDetails.toArray(new TranDetailType[transDetails.size()]));
							transDetails.clear();
						}
						oldVitalId = newVitalId;
					}
					trans = SCIServiceDTOFactory.updateTransDetails(rs,keyDateFormat, pymtTypeCodeToDescMap);
					
					transDetails.add(trans);
				}
				
				if(transDetails.size()> 0){
					SciARDetailDataType contract = vitalIdToSciARMap.get(oldVitalId);
					contract.setTranDetail(transDetails.toArray(new TranDetailType[transDetails.size()]));
				}
				
			}catch(FDDEException fddeEx){
				throw fddeEx;
			}catch(SQLException sqlEx) {
				logger.error("SQLException in updateTransactionDetails() : ", sqlEx);
				throw new FDDEException(sqlEx.getMessage(),sqlEx);
			} catch (Exception ex) {
				logger.error("Exception in updateTransactionDetails() : ", ex);
				throw new FDDEException(ex.getMessage(),ex);
			} finally {
				closeConnection();
			}
		}		
	}

	private static String FETCH_SPEEDDATA = "SELECT tabdata FROM speeddata WHERE tabcategory = 'PAYMETHOD' " +
											"AND locale = ? ORDER BY Identity";
	private Map<String, String> getPymtTypeMap(int locale) {
		
		Map<String, String> pymtTypeCodeToDescMap = new LinkedHashMap<String, String>();
				
			StringBuffer sql = new StringBuffer(512);
			sql.append(FETCH_SPEEDDATA);
			try{
				makeConnection(dbLookup);
				statement = conn.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				statement.setInt(1, locale);
				
				rs = statement.executeQuery();
				while(rs.next()){
					String value = rs.getString("tabdata");					
					pymtTypeCodeToDescMap.put((value.substring(0, 2)), (value.substring(value.indexOf("-")+1)));
				}
				
			}catch(FDDEException fddeEx){
				throw fddeEx;
			}catch(SQLException sqlEx) {
				logger.error("SQLException in updatePymtType() : ", sqlEx);
				throw new FDDEException(sqlEx.getMessage(),sqlEx);
			} catch (Exception ex) {
				logger.error("Exception in updatePymtType() : ", ex);
				throw new FDDEException(ex.getMessage(),ex);
			} finally {
				closeConnection();
			}
			
		return pymtTypeCodeToDescMap;
	}
}
