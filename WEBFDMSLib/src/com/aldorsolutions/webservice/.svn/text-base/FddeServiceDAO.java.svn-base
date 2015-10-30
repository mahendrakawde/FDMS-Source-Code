package com.aldorsolutions.webservice;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.resource.cci.ResultSet;

import org.apache.log4j.Logger;

import com.aldorsolutions.fdms.exception.FDDEException;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;
import com.aldorsolutions.webservice.xsd.comm.StateCodeType;
import com.aldorsolutions.webservice.xsd.comm.StrMax10Len;
import com.aldorsolutions.webservice.xsd.comm.fdde.*;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.LineItemType;
import com.aldorsolutions.webservice.xsd.comm.fdde.sci.SciContractType;
import com.aldorsolutions.webservice.xsd.fdde.service.*;

public class FddeServiceDAO extends DAO {

	private Logger logger = Logger.getLogger(FddeServiceDAO.class.getName());

	public FddeServiceDAO(DbUserSession user) {
		super(user);
	}

	public FddeServiceDAO(long companyID, long userId) {
		super(companyID, userId);
	}

	public FddeServiceDAO(String dbLookup) {
		super(dbLookup);
	}

	/*private final String SELECT_FUNERAL_HOME_SQL = "SELECT 	CONCAT('L',companyDb.Locale,'FH', companyDb.LocationID) as FHID, companyDb.Name, " +
								" companyDb.ManagerName , companyDb.Addr1, companyDb.Addr2, companyDb.Addr3, " +
								"companyDb.City, companyDb.State, companyDb.ZipCode, companyDb.County, companyDb.LocationNum, " +
								" companyDb.PhoneNumber, companyDb.OtherPhone, companyDb.LicenseNumber, companyDb.Website, companyDb.Email " +
								" FROM fdmsus_key2db.locations companyDb" +
								" WHERE (companyDb.Locale, companyDb.LocationID) in " +
											"(select userDb.locale_Id, userDb.location_Id " +
												" from webfdmsusers.userlocations userDb where userDb.user_id = ?)" +
								" AND (companyDb.InactiveCode <> 'D' or companyDb.InactiveCode is null)"+
								" ORDER BY companyDb.Locale, companyDb.LocationID";*/
	private String SELECT_FUNERAL_HOME_SQL1 = " FROM locations WHERE Locale in (";
	private String SELECT_FUNERAL_HOME_SQL2 = ") AND LocationID in (";
	private String SELECT_FUNERAL_HOME_SQL3 = ") AND (InactiveCode <> 'D' or InactiveCode is null)";
	private String SELECT_FUNERAL_HOME_FILTER_STATE_PRE = " AND State IN (";
	private String SELECT_FUNERAL_HOME_FILTER_STATE_POST = ")";
	private String SELECT_FUNERAL_HOME_ORDER_BY = " ORDER BY Name";
	public List<FuneralHomeType> getFuneralHomes(FuneralHomeIdType funeralHomeId, StateCodeType[] states, boolean reportInvalidRowIds, boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows) {

		List<FuneralHomeType> results = new ArrayList<FuneralHomeType>();
		try{
			List<UserLocation> userLocations = null;
			if(funeralHomeId != null ){
				UserLocation requestedLocation = UserLocation.getLocationFromFuneralHomeID(funeralHomeId);
				userLocations = new ArrayList<UserLocation>(1);
				userLocations.add(requestedLocation);
			}else{
				userLocations = getUserLocations();
			}
			makeConnection(dbLookup);
			{
				StringBuffer sql = new StringBuffer(512);
				sql.append(FddeServiceDTOFactory.SELECT_FUNERAL_HOME);
				sql.append(SELECT_FUNERAL_HOME_SQL1);
				sql.append(UserLocation.getListStr(userLocations, true));
				sql.append(SELECT_FUNERAL_HOME_SQL2);
				sql.append(UserLocation.getListStr(userLocations, false));
				sql.append(SELECT_FUNERAL_HOME_SQL3);
				if(states != null && states.length>0){
					sql.append(SELECT_FUNERAL_HOME_FILTER_STATE_PRE);
					for(int i=0;i< states.length; i++){
						sql.append("?");
						if((i+1)<states.length){
							sql.append(",");
						}
					}
					sql.append(SELECT_FUNERAL_HOME_FILTER_STATE_POST);
				}
				sql.append(SELECT_FUNERAL_HOME_ORDER_BY);
				statement = conn.prepareStatement(sql.toString());
			}
			
			if(states != null && states.length>0){
				int i=1;
				for(StateCodeType state: states){
					statement.setString(i++, state.getStateCodeType());
				}
			}
			rs = statement.executeQuery();

			while (rs.next()) {
				FuneralHomeType funeralHome = new FuneralHomeType();
				try{
					FddeServiceDTOFactory.updateFuneralHome(rs,funeralHome);
				}catch (Exception ex){
					logger.debug("Exception inside while of getFuneralHomes() : ", ex);
					if(reportInvalidRowIds ||includeInvalidRowIds){
						String rowId = funeralHome.getId().getFuneralHomeIdType();
						invalidRows.add(getInvalidRowDetails(rowId, "locations", null, null));
					}else{
						throw ex;
					}
				}
				if(!reportInvalidRowIds){
					results.add(funeralHome);
				}
			}
		}catch(SQLException sqlEx) {
			logger.error("SQLException in getFuneralHomes() : ", sqlEx);
			throw new FDDEException(sqlEx.getMessage(),sqlEx);
		} catch (Exception ex) {
			logger.error("Exception in getFuneralHomes() : ", ex);
			throw new FDDEException(ex.getMessage(),ex);
		} finally {
			closeConnection();
		}
		return results;
	}

	String VITALS_TABLE = " vitalstats ";

	String SELECT_CASES_SQL_WHERE = " WHERE LocaleNumber = ? AND ChapelNumber = ? ";
	String SELECT_CASES_SQL_ORDER = " order by vitalstats.VitalsMasterKey";

	String SELECT_SINGLE_CASE_FILTER = " AND vitalstats.VitalsMasterKey = ? ";

	//From tables
	private String SELECT_CASE_CALL_INFO_FROM_TABLES = " LEFT OUTER JOIN person " +				
	" ON vitalstats.VitalsMasterKey = person.VitalsMasterKey ) " +
	" LEFT OUTER JOIN financialdata " +				
	" ON vitalstats.VitalsMasterKey = financialdata.VitalsMasterKey ";
	//	private String SELECT_CASE_VITALS_INFO_FROM_TABLES = ", " ;
	private String SELECT_CASE_SERVICES_INFO_FROM_TABLES = " LEFT OUTER JOIN servicedata " +
	" ON vitalstats.VitalsMasterKey = servicedata.VitalsMasterKey " ;

	// where clause conditions to filter
	String SELECT_CASES_SQL_VOIDE_FILTER = " AND VoidedCode = 'V' ";
	String SELECT_CASES_SQL_NOT_VOIDE_FILTER = " AND VoidedCode != 'V' ";
	String SELECT_CASES_SQL_ATNEED_FILTER = " AND PNPreneedStatus NOT IN('A', 'C') ";
	String SELECT_CASES_SQL_PRENEED_FILTER = " AND PNPreneedStatus IN('A', 'C') ";
	//	private String SELECT_CASE_CALL_INFO_FILTER = " ";
	//	private String SELECT_CASE_VITALS_INFO__FILTER = " ";
	//	private String SELECT_CASE_SERVICES_INFO__FILTER = " ";

	String getCaseFilterSQL(CaseFilterType caseFilter, boolean singleCase, CaseSQLDataInterest requiredInfo){

		String caseFilterSQL = SELECT_CASES_SQL_NOT_VOIDE_FILTER;
		if(singleCase){
			caseFilterSQL = "";
			if(requiredInfo != null && (caseFilter == null || caseFilter.getCaseData()!= null)){
				requiredInfo.datasInterested = new boolean[]{true, true, true};
			}
		}else{
			//check user requested filter 
			if(caseFilter != null){

				if(caseFilter.getIsVoid()){

					if(caseFilter.getIsAtNeed() && caseFilter.getIsPreNeed()){
						// request for all cases	
						caseFilterSQL = "";

						if(caseFilter.getCaseData()!=null && caseFilter.getCaseData().length != 1){
							throw new FDDEException(ErrorCodeType.InvalidCriteria,"Multiple case details are not allowed for all cases scenario!");
						}
					}else{

						// request for void case scenario
						caseFilterSQL =SELECT_CASES_SQL_VOIDE_FILTER;
					}

				}else{

					//non-void case scenario
					caseFilterSQL = SELECT_CASES_SQL_NOT_VOIDE_FILTER;

					if(caseFilter.getIsAtNeed() && caseFilter.getIsPreNeed()){

						//Request for all non-void cases scenario
						if(caseFilter.getCaseData()!=null && caseFilter.getCaseData().length != 1){
							throw new FDDEException(ErrorCodeType.InvalidCriteria,"Multiple case details are not allowed for all non-void cases scenario!");
						}

					}else {

						if(caseFilter.getIsAtNeed() && !caseFilter.getIsPreNeed()){

							caseFilterSQL += SELECT_CASES_SQL_ATNEED_FILTER;

						}else if(caseFilter.getIsPreNeed() && !caseFilter.getIsAtNeed()){

							caseFilterSQL += SELECT_CASES_SQL_PRENEED_FILTER;
						}
					}
				}
			}
		}
		return caseFilterSQL;
	}
	CaseSQLDataInterest getCaseSQLAndDataInterest(CriteriaType criteria, CaseFilterType caseFilter, boolean singleCase){
		final int callInfo = 0;
		final int vitalsInfo = 1;
		final int servicesInfo = 2;


		CaseSQLDataInterest requiredInfo = new CaseSQLDataInterest();
		String caseFilterSQL = getCaseFilterSQL(caseFilter, singleCase, requiredInfo);

		if(caseFilter != null && caseFilter.getCaseData()!= null){
			requiredInfo.datasInterested = getCaseDatas(caseFilter.getCaseData());
		}
		// construct SQL
		StringBuffer sql = new StringBuffer(512);

		// construct select clause
		sql.append(FddeServiceDTOFactory.SELECT_CASE);

		if(requiredInfo.datasInterested[callInfo]){
			sql.append(FddeServiceDTOFactory.SELECT_CASE_CALL_INFO);
			sql.append(FddeServiceDTOFactory.SELECT_BUYER);
			sql.append(FddeServiceDTOFactory.SELECT_INFORMANT);
			sql.append(FddeServiceDTOFactory.SELECT_NEXT_OF_KIN);
		}
		if(requiredInfo.datasInterested[vitalsInfo]){
			sql.append(FddeServiceDTOFactory.SELECT_CASE_VITALS_INFO);
		}
		if(requiredInfo.datasInterested[servicesInfo]){
			sql.append(FddeServiceDTOFactory.SELECT_CASE_SERVICES_INFO);
		}

		// construct from clause
		sql.append(" FROM ");
		if(requiredInfo.datasInterested[callInfo]){
			sql.append(" ( ");
			if(requiredInfo.datasInterested[servicesInfo]){
				sql.append(" ( ");
			}
		}

		sql.append(VITALS_TABLE);
		if(requiredInfo.datasInterested[callInfo]){
			sql.append(SELECT_CASE_CALL_INFO_FROM_TABLES);
		}
		if(requiredInfo.datasInterested[callInfo]&& requiredInfo.datasInterested[servicesInfo]){
			sql.append(" ) ");
		}
		//		if(requiredInfo.datasIntrested[vitalsInfo]){
		//			sql.append(FddeServiceDTOFactory.SELECT_CASE_VITALS_INFO);
		//		}
		if(requiredInfo.datasInterested[servicesInfo]){
			sql.append(SELECT_CASE_SERVICES_INFO_FROM_TABLES);
		}

		//construct where clause
		sql.append(SELECT_CASES_SQL_WHERE);
		if(singleCase){
			sql.append(SELECT_SINGLE_CASE_FILTER);
		}
		sql.append(caseFilterSQL);
		sql.append(SELECT_CASES_SQL_ORDER);
		requiredInfo.sql = sql.toString();
		return requiredInfo;
	}

	private class CaseSQLDataInterest{
		String sql;
		boolean[] datasInterested = {true, false, false};		
	}

	public List<CaseType> getCases(CriteriaType criteria, CaseFilterType caseFilter, boolean reportInvalidRowIds, boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows){

		boolean singleCase = criteria.getCaseId() != null;
		List<CaseType> results = singleCase? new ArrayList<CaseType>(1):new ArrayList<CaseType>();
		try{
			makeConnection(dbLookup);
			CaseSQLDataInterest sqlAndDataInterest = getCaseSQLAndDataInterest(criteria, caseFilter, singleCase);
			statement = conn.prepareStatement(sqlAndDataInterest.sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			UserLocation requstedLocation = setStatementAndReturnFuneralHomeId(criteria.getFuneralHomeId(), criteria.getCaseId());
			FuneralHomeIdType funeralHomeId = singleCase ? requstedLocation.getFuneralHomeIdType() : criteria.getFuneralHomeId();

			rs = statement.executeQuery();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");

			while (rs.next()) {

				CaseType fhCase = new CaseType();
				try{
					FddeServiceDTOFactory.updateCase(rs, fhCase, funeralHomeId, sqlAndDataInterest.datasInterested, dateFormat);
				}catch (Exception ex) {
					logger.debug("Exception inside while of getCases() : ", ex);
					if(reportInvalidRowIds ||includeInvalidRowIds){
						String rowId = fhCase.getId().getCaseIdType();
						invalidRows.add(getInvalidRowDetails(rowId, "case", null, null));
					}else{
						throw ex;
					}
				}

				results.add(fhCase);
			}

			if(sqlAndDataInterest.datasInterested[0]){
				/*if(caseFilter != null && caseFilter.getIsPreNeed() 
            				&& (!caseFilter.getIsVoid() || (caseFilter.getIsVoid() && caseFilter.getIsAtNeed()))){*/
            				
            	if(!singleCase){
            	
            		getDispursements(results , requstedLocation, caseFilter.getIsVoid(), 
            				reportInvalidRowIds, includeInvalidRowIds, invalidRows);

				}else if(singleCase && results.size()== 1 && results.get(0).getCaseType()!=null 
						&& results.get(0).getCaseType().getPreNeed()!=null){

					boolean isVoid = results.get(0).get_void(); 
					getDispursements(results , requstedLocation, isVoid, 
							reportInvalidRowIds, includeInvalidRowIds, invalidRows);

				}
			}

		}catch(FDDEException fddeEx){
			throw fddeEx;
		}catch(SQLException sqlEx) {
			logger.error("SQLException in getCases() : ", sqlEx);
			throw new FDDEException(sqlEx.getMessage(),sqlEx);
		} catch (Exception ex) {
			logger.error("Exception in getCases() : ", ex);
			throw new FDDEException(ex.getMessage(),ex);
		} finally {
			closeConnection();
		}
		if(reportInvalidRowIds){
			results.clear();
			results = null;
		}
		return results;
	}

	private String SELECT_PRENEED_DISPURSEMENTS = " preneeddisbursement, vitalstats ";
	private String SELECT_PRENEED_DISPURSEMENTS_WHERE = " WHERE vitalstats.VitalsMasterKey = preneeddisbursement.VitalsMasterKey " +
	" AND LocaleNumber = ? AND ChapelNumber = ? ";
	private String SELECT_PRENEED_DISPURSEMENTS_ORDER = " ORDER BY preneeddisbursement.vitalsmasterkey, DisbursementId ";

	private void getDispursements(List<CaseType> results, UserLocation requstedLocation, boolean voidedCase, 
			boolean reportInvalidRowIds, boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows){

		int indexOfCase = 0;
		String oldId = "";
		List<DisbursementType> disbursements = new ArrayList<DisbursementType>(4);
		CaseType fhCase = null;
		try {
			// construct SQL
			StringBuffer sql = new StringBuffer(512);

			// construct select clause
			sql.append(FddeServiceDTOFactory.SELECT_PRENEED_DISPURSEMENTS);
			sql.append(", DisbursementId ");

			// construct from clause
			sql.append(" FROM ").append(SELECT_PRENEED_DISPURSEMENTS);

			// construct where clause
			sql.append(SELECT_PRENEED_DISPURSEMENTS_WHERE);
			if (!voidedCase) {
				sql.append(SELECT_CASES_SQL_NOT_VOIDE_FILTER);
			}
			// construct order by clause
			sql.append(SELECT_PRENEED_DISPURSEMENTS_ORDER);
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			statement
			.setString(1, requstedLocation.getFuneralHomeIdStr() + "C");
			statement.setInt(2, requstedLocation.getLocale());
			statement.setInt(3, requstedLocation.getLocation());

			rs = statement.executeQuery();

			while (rs.next()) {

				String newId = rs.getString("caseId");

				if (!newId.equals(oldId)) {
					// found a new case disbursement
					if (fhCase != null && disbursements.size() > 0) {
						// set old values to the case
						FddeServiceDTOFactory.setDisbursements(fhCase,
								disbursements);
						fhCase = null;
					}
					// clear the list
					disbursements.clear();
					// find new case for the existing row
					while (fhCase == null && indexOfCase < results.size()) {
						CaseType newCase = results.get(indexOfCase++);
						if (newId.equals(newCase.getId().getCaseIdType())) {
							fhCase = newCase;
						}
					}
					oldId = newId;
				}
				try{
					disbursements.add(FddeServiceDTOFactory.getDisbursementType(rs));
				}catch (Exception ex) {
					logger.debug("Exception inside while of getDispursements() : ", ex);
					if(reportInvalidRowIds ||includeInvalidRowIds){
						String rowId = rs.getInt("DisbursementId") + "";
						invalidRows.add(getInvalidRowDetails(rowId, "preneeddisbursement", oldId, null));
					}else{
						throw ex;
					}
				}
			}

			if (fhCase != null && disbursements.size() > 0) {
				// set old values to the case
				FddeServiceDTOFactory.setDisbursements(fhCase, disbursements);
				fhCase = null;
			}
		} catch(SQLException sqlEx){
			logger.error("SQLException in getCases() : ", sqlEx);
			throw new FDDEException(sqlEx.getMessage(), sqlEx);
		}catch (Exception ex) {
			logger.error("Exception in getCases() : ", ex);
			throw new FDDEException(ex.getMessage(), ex);
		} finally {
			closeConnection();
		}
	}

	UserLocation setStatementAndReturnFuneralHomeId(FuneralHomeIdType funeralHomeId, CaseIdType caseIdType) throws SQLException{
		UserLocation requstedLocation = null;
		if(caseIdType != null){
			Map<Integer, UserLocation> vitalsToLocationMap = UserLocation.getVitalsToLocationMap(caseIdType);
			if(!vitalsToLocationMap.isEmpty()){
				Integer vitalKey = null;
				for(Integer vitals :vitalsToLocationMap.keySet()){
					vitalKey = vitals;
					break;
				}
				requstedLocation = vitalsToLocationMap.get(vitalKey);
				statement.setInt(1, requstedLocation.getLocale());
				statement.setInt(2, requstedLocation.getLocation());
				statement.setInt(3, vitalKey.intValue());
			}
		}else{
			requstedLocation = UserLocation.getLocationFromFuneralHomeID(funeralHomeId);
			statement.setInt(1, requstedLocation.getLocale());
			statement.setInt(2, requstedLocation.getLocation());
		}
		return requstedLocation;
	}

	private boolean[] getCaseDatas(CaseDataType[] caseDatas){
		boolean[] datas = {false, false, false};
		if(caseDatas != null && caseDatas.length>0){
			for(CaseDataType caseData: caseDatas){
				if(CaseDataType._callInfo.equals(caseData.getValue())){
					datas[0] = true;
				} else if(CaseDataType._vitals.equals(caseData.getValue())){
					datas[1] = true;
				}else if(CaseDataType._services.equals(caseData.getValue())){
					datas[2] = true;
				}
			}
		}
		return datas;
	}

	public FinancialInfoType getFinancialInfo(CriteriaType criteria, FinancialFilterType financialFilter, 
			boolean reportInvalidRowIds, boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows){
		FinancialInfoType financialInfo = new FinancialInfoType();
		try{
			boolean salesInterested[] = {true,true};
			if(financialFilter != null && financialFilter.getSaleInterest()!= null){
				salesInterested = new boolean[] {false, false};

				for(SaleInterestType sale : financialFilter.getSaleInterest()){
					if(sale.getValue().equals(SaleInterestType._caseSales)){
						salesInterested[0]= true;
					}else if(sale.getValue().equals(SaleInterestType._miscSales)){
						salesInterested[1]= true;
					}
				}
			}

			if(salesInterested[0]){
				getCaseSales(criteria, financialInfo, reportInvalidRowIds
						, includeInvalidRowIds, invalidRows);
			}

			if(salesInterested[1]){
				getMiscSales(criteria, financialInfo, reportInvalidRowIds
						, includeInvalidRowIds, invalidRows);
			}

		}catch(FDDEException fddeEx){
			throw fddeEx;
		}
		if(financialInfo.getCaseSale()==null && financialInfo.getMiscSale()==null){
			financialInfo = null;
		}
		return financialInfo;
	}

	static String SELECT_CASE_SALE_POSTING = ", SentToAccounting ";
	static String SELECT_CASE_FINANCIAL_DATA_TABLE = " LEFT OUTER JOIN financialdata ON " +
	" vitalstats.VitalsMasterKey = financialdata.VitalsMasterKey ";
	private static String SELECT_CASE_SALE_ORDER_BY = " ORDER BY vitalstats.VitalsMasterKey ";

	public FinancialInfoType getCaseSales(CriteriaType criteria, FinancialInfoType financialInfo, 
			boolean reportInvalidRowIds, boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows){

		boolean singleCase = criteria.getCaseId() != null;
		List<CaseSaleType> caseSales = singleCase ? new ArrayList<CaseSaleType>(1) : new ArrayList<CaseSaleType>();

		Map<Integer, CaseSaleType> postedSales = new HashMap<Integer, CaseSaleType>();
		String postedVitalIds = "";

		Map<Integer, CaseSaleType> unpostedSales = new HashMap<Integer, CaseSaleType>();
		String unpostedVitalIds = "";

		try{
			// construct SQL
			StringBuffer sql = new StringBuffer(512);

			// construct select clause
			sql.append(FddeServiceDTOFactory.SELECT_CASE);
			sql.append(", vitalstats.VitalsMasterKey ");
			sql.append(SELECT_CASE_SALE_POSTING);

			//construct from clause
			sql.append(" FROM ").append(VITALS_TABLE);
			sql.append(SELECT_CASE_FINANCIAL_DATA_TABLE);

			//construct where clause
			sql.append(SELECT_CASES_SQL_WHERE);
			if(singleCase){
				sql.append(SELECT_SINGLE_CASE_FILTER);
			}

			sql.append(SELECT_CASE_SALE_ORDER_BY);
			//			long start = System.currentTimeMillis();
			//			System.out.println("Start of reading cases. . . ");
			// create database connections
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			sql = new StringBuffer(512);
			StringBuffer unPostedvitalIdList = new StringBuffer(512);
			// set parameters
			UserLocation requstedLocation = setStatementAndReturnFuneralHomeId(criteria.getFuneralHomeId(), criteria.getCaseId());
			FuneralHomeIdType funeralHomeId = singleCase ? requstedLocation.getFuneralHomeIdType() : criteria.getFuneralHomeId();
			rs = statement.executeQuery();

			CaseSaleType caseSale = null;
			while(rs.next()){

				caseSale = new CaseSaleType();
				try{
					FddeServiceDTOFactory.setCaseInfoReturnIsPreNeedCase(rs,caseSale, funeralHomeId);
					caseSales.add(caseSale);

					int vitalId = rs.getInt("VitalsMasterKey");
					int posted = rs.getInt("SentToAccounting"); 
					if(!rs.wasNull()&& posted == 1){
						postedSales.put(vitalId, caseSale);
						if(sql.length()>0){
							sql.append(",");
						}
						sql.append(vitalId);
					}else{
						unpostedSales.put(vitalId, caseSale);
						if(unPostedvitalIdList.length()>0){
							unPostedvitalIdList.append(",");
						}
						unPostedvitalIdList.append(vitalId);
					}
				}catch (Exception ex) {
					logger.debug("Exception inside while of getCaseSales() : ", ex);
					if(reportInvalidRowIds ||includeInvalidRowIds){
						String rowId = caseSale.getId().getCaseIdType();
						invalidRows.add(getInvalidRowDetails(rowId, "caseSale", null, null));
					}else{
						throw ex;
					}
				}

			}
			//			long end = System.currentTimeMillis();
			//			System.out.println("End of reading cases: time in ms = " +(end-start));
			postedVitalIds = sql.toString();
			unpostedVitalIds = unPostedvitalIdList.toString();

		}catch(FDDEException fddeEx){
			throw fddeEx;
		}catch(SQLException sqlEx) {
			logger.error("SQLException in getCaseSales() : ", sqlEx);
			throw new FDDEException(sqlEx.getMessage(),sqlEx);
		} catch (Exception ex) {
			logger.error("Exception in getCaseSales() : ", ex);
			throw new FDDEException(ex.getMessage(),ex);
		} finally {
			closeConnection();
		}

		if(postedSales.size()>0){
			//			long start = System.currentTimeMillis();
			//			System.out.println("Start of reading transactions. . . ");
			getTransactions(postedVitalIds,postedSales, reportInvalidRowIds
					, includeInvalidRowIds, invalidRows);
			//			long end = System.currentTimeMillis();
			//			System.out.println("End of reading transactions: time in ms = " +(end-start));
		}

		if(unpostedSales.size()>0){
			//			long start = System.currentTimeMillis();
			//			System.out.println("Start of reading transactions. . . ");
			getCharges(unpostedVitalIds,unpostedSales, reportInvalidRowIds
					, includeInvalidRowIds, invalidRows);
			//			long end = System.currentTimeMillis();
			//			System.out.println("End of reading transactions: time in ms = " +(end-start));
		}

		if(!reportInvalidRowIds && caseSales.size()>0){
			financialInfo = financialInfo == null ? new FinancialInfoType() : financialInfo;
			financialInfo.setCaseSale(caseSales.toArray(new CaseSaleType[caseSales.size()]));
		}
		return financialInfo;
	}

	private static String SELECT_CASE_SALE_TRANSACTION_FROM = " FROM transactionhistory " ;
	private static String SELECT_CASE_SALE_WHERE = " WHERE VitalsMasterKey IN ( ";
	private static String SELECT_CASE_SALE_FILTER = " ) AND SPFcode IN ('S', 'P', 'F') ";
	private static String SELECT_CASE_SALE_TRANSACTION_ORDER_BY = " ORDER BY VitalsMasterKey, TranHistID ";

	private void getTransactions(String vitalIds, Map<Integer, CaseSaleType> caseSales, boolean reportInvalidRowIds, boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows){

		// construct SQL
		StringBuffer sql = new StringBuffer(512);

		// construct select clause
		sql.append(FddeServiceDTOFactory.SELECT_MISC_SALES);
		sql.append(FddeServiceDTOFactory.SELECT_TRANSACTION_HISTORY);
		sql.append(", VitalsMasterKey ");
		//construct from clause
		sql.append(SELECT_CASE_SALE_TRANSACTION_FROM);

		//construct where clause
		sql.append(SELECT_CASE_SALE_WHERE);
		sql.append(vitalIds);
		sql.append(SELECT_CASE_SALE_FILTER);

		sql.append(SELECT_CASE_SALE_TRANSACTION_ORDER_BY);
		try{
			//			long startProcess = System.currentTimeMillis();
			//			System.out.println("Start of reading transaction process. . . ");
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			List<TransactionType> transactions = new ArrayList<TransactionType>();

			rs = statement.executeQuery();
			int oldVitalId = Integer.MIN_VALUE;
			while(rs.next()){
				int newVitalId = rs.getInt("VitalsMasterKey");
				if(newVitalId != oldVitalId){
					if(transactions.size()>0){
						CaseSaleType caseSale = caseSales.get(oldVitalId);
						caseSale.setTransaction(transactions.toArray(new TransactionType[transactions.size()]));
						transactions.clear();
					}
					oldVitalId = newVitalId;
				}
				TransactionType transaction = new TransactionType();
				try{
					FddeServiceDTOFactory.setTransaction(rs,transaction,false);

				}catch (Exception ex) {
					logger.debug("Exception inside while of getTransactions() : ", ex);
					if(reportInvalidRowIds ||includeInvalidRowIds){
						String parentId = caseSales.get(oldVitalId).getId().getCaseIdType();
						String rowId = Integer.toString(transaction.getTranId());
						invalidRows.add(getInvalidRowDetails(rowId, "transactionhistory", parentId, null));
					}else{
						throw ex;
					}
				}
				if(!reportInvalidRowIds){
					transactions.add(transaction);
				}
			}

			if(transactions.size()>0){
				CaseSaleType caseSale = caseSales.get(oldVitalId);
				caseSale.setTransaction(transactions.toArray(new TransactionType[transactions.size()]));
			}
			//	        long endProcess = System.currentTimeMillis();
			//			System.out.println("End of reading transaction process: time in ms = " +(endProcess-startProcess));

		}catch(FDDEException fddeEx){
			throw fddeEx;
		}catch(SQLException sqlEx) {
			logger.error("SQLException in getTransactions() : ", sqlEx);
			throw new FDDEException(sqlEx.getMessage(),sqlEx);
		} catch (Exception ex) {
			logger.error("Exception in getTransactions() : ", ex);
			throw new FDDEException(ex.getMessage(),ex);
		} finally {
			closeConnection();
		}
	}
	private static String SELECT_CASE_SALE_CHARGES_FROM = " FROM charges " ;
	//	private static String SELECT_CASE_SALE_WHERE = " WHERE VitalsMasterKey IN ( ";
	//	private static String SELECT_CASE_SALE_FILTER = " ) AND SPFcode IN ('S', 'P', 'F') ";
	private static String SELECT_CASE_SALE_CHARGES_ORDER_BY = " ORDER BY VitalsMasterKey, Autoindex ";

	private void getCharges(String vitalIds, Map<Integer, CaseSaleType> caseSales, boolean reportInvalidRowIds, boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows){

		// construct SQL
		StringBuffer sql = new StringBuffer(512);

		// construct select clause
		sql.append(FddeServiceDTOFactory.SELECT_UNPOSTED_CHARGES);
		sql.append(", VitalsMasterKey ");
		//construct from clause
		sql.append(SELECT_CASE_SALE_CHARGES_FROM);

		//construct where clause
		sql.append(SELECT_CASE_SALE_WHERE);
		sql.append(vitalIds);
		sql.append(SELECT_CASE_SALE_FILTER);

		sql.append(SELECT_CASE_SALE_CHARGES_ORDER_BY);
		try{
			//			long startProcess = System.currentTimeMillis();
			//			System.out.println("Start of reading charges process. . . ");
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString()); 
			List<ChargeType> charges = new ArrayList<ChargeType>();

			rs = statement.executeQuery();
			int oldVitalId = Integer.MIN_VALUE;
			while(rs.next()){
				int newVitalId = rs.getInt("VitalsMasterKey");
				if(newVitalId != oldVitalId){
					if(charges.size()>0){
						CaseSaleType caseSale = caseSales.get(oldVitalId);
						caseSale.setUnPostedCharge(charges.toArray(new ChargeType[charges.size()]));
						charges.clear();
					}
					oldVitalId = newVitalId;
				}
				ChargeType charge = new ChargeType();
				try{
					charge = FddeServiceDTOFactory.updateChargeType(rs, charge);

				}catch (Exception ex) {
					logger.debug("Exception inside while of getCharges() : ", ex);
					if(reportInvalidRowIds ||includeInvalidRowIds){
						String parentId = caseSales.get(oldVitalId).getId().getCaseIdType();
						String rowId = Integer.toString(charge.getSaleId());
						invalidRows.add(getInvalidRowDetails(rowId, "charges", parentId, null));
					}else{
						throw ex;
					}
				}
				if(!reportInvalidRowIds){
					charges.add(charge);
				}
			}

			if(charges.size()>0){
				CaseSaleType caseSale = caseSales.get(oldVitalId);
				caseSale.setUnPostedCharge(charges.toArray(new ChargeType[charges.size()]));
			}
			//	        long endProcess = System.currentTimeMillis();
			//			System.out.println("End of reading charges process: time in ms = " +(endProcess-startProcess));

		}catch(FDDEException fddeEx){
			throw fddeEx;
		}catch(SQLException sqlEx) {
			logger.error("SQLException in getCharges() : ", sqlEx);
			throw new FDDEException(sqlEx.getMessage(),sqlEx);
		} catch (Exception ex) {
			logger.error("Exception in getCharges() : ", ex);
			throw new FDDEException(ex.getMessage(),ex);
		} finally {
			closeConnection();
		}
	}
	private static String SELECT_CASE_SALE_TRANSACTION_HISTORY_FROM = " FROM transactionhistory ";
	private static String SELECT_CASE_SALE_TRANSACTION_HISTORY_WHERE = " WHERE locationId = ? AND SPFcode = 'R' ";
	private FinancialInfoType getMiscSales(CriteriaType criteria, FinancialInfoType financialInfo, 
			boolean reportInvalidRowIds, boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows){

		List<MiscSaleType> miscSales = new ArrayList<MiscSaleType>();
		try{
			// construct SQL
			StringBuffer sql = new StringBuffer(512);

			// construct select clause
			sql.append(FddeServiceDTOFactory.SELECT_MISC_SALES);
			sql.append(FddeServiceDTOFactory.SELECT_TRANSACTION_HISTORY);

			//construct from clause
			sql.append(SELECT_CASE_SALE_TRANSACTION_HISTORY_FROM);

			//construct where clause
			sql.append(SELECT_CASE_SALE_TRANSACTION_HISTORY_WHERE);

			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());

			int locationId = Integer.parseInt(UserLocation.getLocationStrFromFuneralHomeId(criteria.getFuneralHomeId().getFuneralHomeIdType()));
			statement.setInt(1, locationId);
			rs = statement.executeQuery();

			while(rs.next()){
				MiscSaleType miscSale = new MiscSaleType();
				try{
					miscSale = FddeServiceDTOFactory.updateMiscSaleType(rs, criteria.getFuneralHomeId(), miscSale);
				}catch (Exception ex) {
					logger.debug("Exception inside while of getMiscSales() : ", ex);
					if(reportInvalidRowIds ||includeInvalidRowIds){
						String parentId = criteria.getFuneralHomeId().getFuneralHomeIdType();
						String rowId = Integer.toString(miscSale.getTranId());
						invalidRows.add(getInvalidRowDetails(rowId, "transactionhistory", parentId, null));
					}else{
						throw ex;
					}
				}
				if(!reportInvalidRowIds){
					miscSales.add(miscSale);
				}
			}
			if(miscSales.size()>0){
				financialInfo = financialInfo == null ? new FinancialInfoType() : financialInfo;
				financialInfo.setMiscSale(miscSales.toArray(new MiscSaleType[miscSales.size()]));
			}

		}catch(FDDEException fddeEx){
			throw fddeEx;
		}catch(SQLException sqlEx) {
			logger.error("SQLException in getCases() : ", sqlEx);
			throw new FDDEException(sqlEx.getMessage(),sqlEx);
		} catch (Exception ex) {
			logger.error("Exception in getCases() : ", ex);
			throw new FDDEException(ex.getMessage(),ex);
		} finally {
			closeConnection();
		}
		return financialInfo;
	}

	private static String SELECT_USER_LOCATIONS_SQL = "select locale_Id, location_Id from userlocations where user_id = ?";

	public List<UserLocation> getUserLocations() {

		List<UserLocation> result = new ArrayList<UserLocation>();
		try{
			makeConnection(DAO.DB_FDMSUSERS);
			statement = conn.prepareStatement(SELECT_USER_LOCATIONS_SQL);
			statement.setInt(1, (int)userID);
			rs = statement.executeQuery();

			while(rs.next()){
				UserLocation userLocation = new UserLocation();
				userLocation.setLocale(rs.getInt("locale_Id"));
				userLocation.setLocation(rs.getInt("location_Id"));
				result.add(userLocation);
			}
		}catch(SQLException sqlEx) {
			logger.error("SQLException in getUserLocations() : ", sqlEx);
			throw new FDDEException(sqlEx.getMessage(),sqlEx);
		} catch (Exception ex) {
			logger.error("Exception in getUserLocations() : ", ex);
			throw new FDDEException(ex.getMessage(),ex);
		} finally {
			closeConnection();
		}
		return result;
	}

	private String SELECT_INVENTORY_ON_HAND_FROM = " FROM invonhand , invchapelindex ";
	private String SELECT_INVENTORY_ON_HAND_WHERE = " WHERE LocationNumber = ? AND MasterID=itemnumber AND Quantity>0 ";
	private String SELECT_INVENTORY_ON_HAND_ORDER_BY = " ORDER BY Identity ";
	public List<InvItemType> getInventoryItems(CriteriaType criteria, boolean reportInvalidRowIds
			,boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows) {

		List<InvItemType> invItems = reportInvalidRowIds ? 
				null 
				: new ArrayList<InvItemType>();

		// construct SQL
		StringBuffer sql = new StringBuffer(256);

		// construct select clause
		sql.append(FddeServiceDTOFactory.SELECT_INVENTORY_ON_HAND);

		//construct from clause
		sql.append(SELECT_INVENTORY_ON_HAND_FROM);

		//construct where clause
		sql.append(SELECT_INVENTORY_ON_HAND_WHERE);

		//construct order by clause
		sql.append(SELECT_INVENTORY_ON_HAND_ORDER_BY);
		try{
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());

			UserLocation requestedLocation = UserLocation.getLocationFromFuneralHomeID(criteria.getFuneralHomeId());
			statement.setInt(1, requestedLocation.getLocation());
			rs = statement.executeQuery();

			SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
			while(rs.next()){
				InvItemType invItem = new InvItemType();
				try{
					FddeServiceDTOFactory.updateInvItemType(rs, dateFormat, invItem);

				}catch (Exception ex) {
					logger.debug("Exception inside while of getInventoryItems() : ", ex);
					if(reportInvalidRowIds ||includeInvalidRowIds){
						String parentId = criteria.getFuneralHomeId().getFuneralHomeIdType();
						String rowId = Integer.toString(invItem.getId());
						invalidRows.add(getInvalidRowDetails(rowId, "invonhand", parentId, null));
					}else{
						throw ex;
					}
				}
				if(!reportInvalidRowIds){
					invItems.add(invItem);
				}
			}

		}catch(SQLException sqlEx) {
			logger.error("SQLException in getInventoryItems() : ", sqlEx);
			throw new FDDEException(sqlEx.getMessage(),sqlEx);
		} catch (Exception ex) {
			logger.error("Exception in getInventoryItems() : ", ex);
			throw new FDDEException(ex.getMessage(),ex);
		} finally {
			closeConnection();
		}

		return invItems;
	}

	public InvalidRowDetails getInvalidRowDetails(String rowId, String rowIdType, 
			String parentId, String parentIdType){
		InvalidRowDetails invalidRow = null;
		if(rowId != null){
			invalidRow = new InvalidRowDetails();
			invalidRow.setRowId(getRowIdType(rowId, rowIdType));
			invalidRow.setParentId(getRowIdType(parentId, parentIdType));
		}
		return invalidRow;
	}

	private RowIdType getRowIdType(String rowId, String rowIdType) {
		RowIdType rowIdElement = null;
		if(rowId!=null){
			rowIdElement = new RowIdType();
			rowIdElement.setString(rowId);
			rowIdElement.setType(rowIdType);
		}
		return rowIdElement;
	}

	//get the Accounts Recievable and Global Report

	public List<AccountsRecievable> getARReport(CriteriaType criteria, boolean reportInvalidRowIds, 
			boolean includeInvalidRowIds, List<InvalidRowDetails> invalidRows){

		Map<Integer, AccountsRecievable> generalAndTranMap = new HashMap<Integer, AccountsRecievable>();

		List<AccountsRecievable> accounts = getARFields(criteria, reportInvalidRowIds, includeInvalidRowIds,
				invalidRows, generalAndTranMap);

		//update AR fields with transaction table data
		if(generalAndTranMap.size()>0){
			updateTransInfo(getVitalIdsList(generalAndTranMap.keySet()), generalAndTranMap, 
					reportInvalidRowIds, includeInvalidRowIds, invalidRows);
		}
		return accounts;
	}

	//from clause
	private String SELECT_ARFIELDS1_FROM = " LEFT OUTER JOIN financialdata " +				
	" ON vitalstats.VitalsMasterKey = financialdata.VitalsMasterKey ) ";
	private String SELECT_ARFIELDS1_FROM1 = " JOIN locations " +
	" ON vitalstats.ChapelNumber = locations.LocationID ";
	private String SELECT_ARFIELDS1_FROM2 = " JOIN combodata " +
	" ON financialdata .SalesDescCDID = combodata.value ";
	//where clause
	private String SELECT_ARFIELDS1_WHERE1 = " WHERE SentToAccounting = 1 AND VoidedCode <> 'V' " +
	" AND TYPE = 2 AND ((TotalCharges-TotalPaidToDate)>0) " +
	" AND PNPreneedStatus NOT IN('A', 'C') AND localenumber IN (  " ;
	private String SELECT_ARFIELDS1_WHERE2 = "  ) AND chapelnumber IN ( " ;

	// Getting results for AR fields from database
	private List<AccountsRecievable> getARFields(CriteriaType criteria,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows, 
			Map<Integer, AccountsRecievable> generalAndTranMap) {		

		List<AccountsRecievable> accounts = new ArrayList<AccountsRecievable>();		
		FuneralHomeIdType funeralHomeId = null;
		if(criteria != null ){
			funeralHomeId = criteria.getFuneralHomeId();
		}

		List<UserLocation> userLocations = null;
		//condition if Funral Home is selected
		if(funeralHomeId != null ){
			UserLocation requestedLocation = UserLocation.getLocationFromFuneralHomeID(funeralHomeId);
			userLocations = new ArrayList<UserLocation>(1);
			userLocations.add(requestedLocation);
		}
		//condition for Global Report
		else{
			userLocations = getUserLocations();
		}
		// construct SQL
		StringBuffer sql = new StringBuffer(512);

		// construct select clause
		sql.append(FddeServiceDTOFactory.SELECT_CASE);
		sql.append(FddeServiceDTOFactory.SELECT_ARFIELDS1);
		sql.append(", vitalstats.VitalsMasterKey ");
		sql.append(FddeServiceDTOFactory.SELECT_BUYER);
		sql.append(FddeServiceDTOFactory.SELECT_INFORMANT);

		// construct from clause
		sql.append(" FROM ( ");
		sql.append(VITALS_TABLE);
		sql.append(SELECT_ARFIELDS1_FROM);
		sql.append(SELECT_ARFIELDS1_FROM1);
		sql.append(SELECT_ARFIELDS1_FROM2);
		//construct where clause
		sql.append(SELECT_ARFIELDS1_WHERE1);
		sql.append(UserLocation.getListStr(userLocations, true));
		sql.append(SELECT_ARFIELDS1_WHERE2);
		sql.append(UserLocation.getListStr(userLocations, false));
		sql.append(" ) ");
		//construct order by clause
		sql.append(SELECT_CASES_SQL_ORDER);
		try{
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
			SimpleDateFormat keyDateFormat = new SimpleDateFormat("yyyyMMdd");
			while(rs.next()){
				AccountsRecievable arFields = null;
				try{
					//identifying the At-need or Pre-need scenario
					int caseType = FddeServiceDTOFactory.identifyCase( rs);
					//setting values for webservices
					arFields = FddeServiceDTOFactory.updateARFieldType(rs, dateFormat, keyDateFormat, caseType);
					if(arFields != null){
						int vitalsId = rs.getInt("VitalsMasterKey");
						accounts.add(arFields);
						generalAndTranMap.put(vitalsId, arFields);
					}
				}catch (Exception ex) {
					logger.debug("Exception inside while of getARFields() : ", ex);
					if(reportInvalidRowIds ||includeInvalidRowIds){
						String rowId = arFields.getCaseId().getCaseIdType();
						//			            		String rowId = arFields.getCreditRating();
						invalidRows.add(getInvalidRowDetails(rowId, "account", null, null));
					}else{
						throw ex;
					}
				}
			}				
		}catch(FDDEException fddeEx){
			throw fddeEx;
		}catch(SQLException sqlEx) {
			logger.error("SQLException in getARFields() : ", sqlEx);
			throw new FDDEException(sqlEx.getMessage(),sqlEx);
		} catch (Exception ex) {
			logger.error("Exception in getARFields() : ", ex);
			throw new FDDEException(ex.getMessage(),ex);
		} finally {
			closeConnection();
		}
		return accounts;
	}

	//from clause for transaction History table data
	private String SELECT_ARFIELDS2_FROM1 = " FROM transactionhistory th1 ,	" +
	"( SELECT (th2.VitalsMasterKey) AS countv, MAX(DateOfTran) AS maxdate	" +
	" FROM transactionhistory th2 " +
	" WHERE spfcode='p' AND GLacct = 10030 " +
	" AND ( deletetransaction =  '  ' OR deletetransaction IS NULL ) " +
	" AND vitalsmasterkey IN( ";
	private String SELECT_ARFIELDS2_FROM2 = " ) GROUP BY VitalsMasterKey)ref ";
	// where clause
	private String SELECT_ARFIELDS2_WHERE = " WHERE th1.DateOfTran = ref.maxdate AND spfcode='p' " +
	" AND ( deletetransaction =  '  ' OR deletetransaction IS NULL ) " +
	" AND th1.vitalsmasterkey IN( ";
	//group by clause
	private String SELECT_ARFIELDS2_GROUPBY = " GROUP BY th1.VitalsMasterKey  ";

	//updating AR Fields with transactionhistory data as per the vitalsmasterkey Map
	private void updateTransInfo(String vitalIdsList,
			Map<Integer, AccountsRecievable> generalAndTranMap,
			boolean reportInvalidRowIds, boolean includeInvalidRowIds,
			List<InvalidRowDetails> invalidRows) {
		// construct SQL
		StringBuffer sql = new StringBuffer(512+vitalIdsList.length()*2);

		// construct select clause
		sql.append(FddeServiceDTOFactory.SELECT_ARFIELDS2);
		sql.append(" , th1.VitalsMasterKey ");
		// construct from clause
		sql.append(SELECT_ARFIELDS2_FROM1);
		sql.append(vitalIdsList);
		sql.append(SELECT_ARFIELDS2_FROM2);
		//construct where clause
		sql.append(SELECT_ARFIELDS2_WHERE);
		sql.append(vitalIdsList);		
		sql.append(" ) ");
		//construct group by clause
		sql.append(SELECT_ARFIELDS2_GROUPBY);
		AccountsRecievable ar = null;
		try{
			makeConnection(dbLookup);
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();
			while(rs.next()){				
				int newVitalId = rs.getInt("VitalsMasterKey");	
				try{
					ar = generalAndTranMap.get(newVitalId);					
					if(ar != null ){
						//setting value for web service
						FddeServiceDTOFactory.updateARTransactionInfo(ar, rs);
					}					
				}catch (Exception ex) {
					logger.debug("Exception inside while of updateTransInfo() : ", ex);            
				}
			}			
		}catch(FDDEException fddeEx){
			throw fddeEx;
		}catch(SQLException sqlEx) {
			logger.error("SQLException in updateTransInfo() : ", sqlEx);
			throw new FDDEException(sqlEx.getMessage(),sqlEx);
		} catch (Exception ex) {
			logger.error("Exception in updateTransInfo() : ", ex);
			throw new FDDEException(ex.getMessage(),ex);
		} finally {
			closeConnection();
		}
	}

	// method to generate the list of vital Ids
	private String getVitalIdsList(Set<Integer> vitalIds){		
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
}
