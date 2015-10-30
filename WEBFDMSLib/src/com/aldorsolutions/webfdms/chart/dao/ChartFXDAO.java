package com.aldorsolutions.webfdms.chart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.DAO;

public class ChartFXDAO extends DAO {

	private Logger logger = Logger.getLogger(ChartFXDAO.class.getName());

	public ChartFXDAO(String usersLookup) {
		super(usersLookup);
	}

	public ChartFXDAO(DbUserSession user) {
		super(user);
	}

	public ArrayList<ArrayList<?>> getCallVolumeChart(String fromDate, String toDate) {
		ArrayList<ArrayList<?>> callVolumeData = new ArrayList<ArrayList<?>>();
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT");
			sql.append(" financialdata.saletype,");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" WHERE  InactiveCode<>'D' ");
			sql.append(" AND (servicedatekey>=" + fromDate);
			sql.append(" AND servicedatekey<=" + toDate);
			sql.append(") AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL') ");
			// sql.append(" AND locales.LocaleID=78");
			sql.append(" AND financialdata.SentToAccounting=1  GROUP BY financialdata.saletype");

			makeConnection(getDbLookup());
			
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();

			/*
			 * CallableStatement cstmt =
			 * conn.prepareCall("{call CallVolumeRevenueAverageChart(?, ?)}"
			 * ,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.FETCH_FORWARD);
			 * 			 * cstmt.setString(1, "20110101"); cstmt.setString(2, "20110901");
			 * makeConnection( DAO.DB_FDMSUSERS);
			 * rs= cstmt.getResultSet();
			 * 
			 */

			ArrayList<String> saleTypeArrayList = new ArrayList<String>();
			ArrayList<Integer> callVolumeArrayList = new ArrayList<Integer>();
			ArrayList<Double> totalVolumeArrayList = new ArrayList<Double>();
			ArrayList<Double> averageVolumeArrayList = new ArrayList<Double>();
			
			callVolumeData.add(saleTypeArrayList);
			callVolumeData.add(callVolumeArrayList);
			callVolumeData.add(totalVolumeArrayList);
			callVolumeData.add(averageVolumeArrayList);

			// String[] saleType = new String[size];
			// int[] callVolume = new int[size];
			// double[] totalVolume = new double[size];
			// double[] averageVolume = new double[size];
			
			while (rs.next()) {
				saleTypeArrayList.add(rs.getString(1));
				// logger.info("# "+rs.getInt(2));
				// logger.info("$ "+rs.getInt(3));
				// logger.info("Avg "+rs.getInt(4));
				callVolumeArrayList.add(rs.getInt(2));
				totalVolumeArrayList.add(rs.getDouble(3));
				averageVolumeArrayList.add(rs.getDouble(4));
			}
			
			return callVolumeData;
		} catch (SQLException e) {
			logger.error("SQLException in getCallVolumeChart() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getCallVolumeChart() : ", e);
		} finally {
			closeConnection();
		}
		return null;
	}
	
	public ArrayList<ArrayList<?>> getCallRevAvgBurialCremationChart(String fromDate, String toDate,long localeId){
		ArrayList<ArrayList<?>> burialCremationData = new ArrayList<ArrayList<?>>();
		try{
			ArrayList<Double> callVolume = new ArrayList<Double>();
			ArrayList<Double> revenueVolume = new ArrayList<Double>();
			ArrayList<Double> averageVolume = new ArrayList<Double>();
			
			burialCremationData.add(callVolume);
			burialCremationData.add(revenueVolume);
			burialCremationData.add(averageVolume);
			
			rs= getCallRevAvgForCompanyYearlyBySaleType(fromDate, toDate, "%crem%",localeId);
			while(rs.next()){
				callVolume.add(rs.getDouble(1));
				//logger.info("crem # :: " +rs.getDouble(1));
				revenueVolume.add(rs.getDouble(2));
			//	logger.info("crem $ :: " +rs.getDouble(2));
				averageVolume.add(rs.getDouble(3));
				//logger.info("crem avg :: " +rs.getDouble(3));
			}
			rs= null;
			closeConnection();
			rs= getCallRevAvgForCompanyYearlyBySaleType(fromDate, toDate, "%bur%",localeId);
			while(rs.next()){
				callVolume.add(rs.getDouble(1));
				//logger.info("burial # :: " +rs.getDouble(1));
				revenueVolume.add(rs.getDouble(2));
				//logger.info("burial $ :: " +rs.getDouble(2));
				averageVolume.add(rs.getDouble(3));
				//logger.info("burial avg :: " +rs.getDouble(3));
			}
			closeConnection();
		} catch (SQLException e) {
			logger.error("SQLException in getCallRevAvgBurialCremationChart() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getCallRevAvgBurialCremationChart() : ", e);
		} finally {
			closeConnection();
		}
		
		
		return burialCremationData;
	}

	public ResultSet getCallRevAvgForCompanyYearlyBySaleType(String fromDate,String toDate, String saleType,long localeId) {
		ResultSet rs1= null;
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" LEFT("+toDate+",4) AS SaleYear");
			//sql.append(", @saletype");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			//sql.append(" WHERE (financialdata.saletype LIKE '" + saleType);
			sql.append(" WHERE (financialdata.saletype LIKE '"+saleType+"') AND financialdata.saletype NOT LIKE '%Tri%' AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Matu%' ");
			sql.append(" AND (servicedatekey>=" + fromDate);
			sql.append(" AND servicedatekey<=" + toDate);
			sql.append(") AND financialdata.SentToAccounting=1 ");
			if(localeId>0){
				sql.append(" AND locales.LocaleID="+localeId);
			}
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL')  ");
			sql.append(" UNION ");
			sql.append("SELECT");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" YEAR((DATE_SUB("+fromDate+", INTERVAL 1 YEAR))) AS SaleYear");
			//sql.append(", @saletype");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			//sql.append(" WHERE (financialdata.saletype LIKE '" + saleType);
			sql.append(" WHERE (financialdata.saletype LIKE '"+saleType+"') AND financialdata.saletype NOT LIKE '%Tri%' AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Matu%' ");
			//sql.append("') AND InactiveCode<>'D' ");
			sql.append(" AND (servicedatekey>=DATE_FORMAT(DATE_SUB(" + fromDate);
			sql.append(" , INTERVAL 1 YEAR),'%Y%m%d')");
			sql.append(" AND servicedatekey<=DATE_FORMAT(DATE_SUB(" + toDate);
			sql.append(" , INTERVAL 1 YEAR),'%Y%m%d'))");
			sql.append(" AND financialdata.SentToAccounting=1 ");
			if(localeId>0){
				sql.append(" AND locales.LocaleID="+localeId);
			}
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL')  ");
			
			logger.info("SQL Tab 2:: "+ sql);
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql.toString());
			rs1= statement.executeQuery();
			return rs1;
		} catch (SQLException e) {
			logger.error("SQLException in getCallRevAvgForCompanyYearlyBySaleType() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getCallRevAvgForCompanyYearlyBySaleType() : ", e);
		}
		return rs1;
	}
	
	
	public ArrayList<ArrayList<?>> getARagingByDateBucketAll(String throughDate,long localeId){
		ArrayList<ArrayList<?>> arAgingData =new ArrayList<ArrayList<?>>();
		
		try{
			ArrayList<String> payCode = new ArrayList<String>();
			ArrayList<Double> totalCharges = new ArrayList<Double>();
			ArrayList<String> bucket = new ArrayList<String>();
			
			arAgingData.add(totalCharges);
			arAgingData.add(payCode);
			arAgingData.add(bucket);
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" ABS(SUM(amountoftran/100)) AS totalCharges,");
			sql.append(" spfcode AS PayCode,");
			sql.append(" CASE ");
			sql.append(" WHEN -(DATEDIFF(DATE(vitalstats.servicedatekey),"+throughDate+")) < 31 THEN '00-30'");
			sql.append(" WHEN -(DATEDIFF(DATE(vitalstats.servicedatekey),"+throughDate+")) < 61 THEN '30-60'");
			sql.append(" WHEN -(DATEDIFF(DATE(vitalstats.servicedatekey),"+throughDate+")) < 91 THEN '60-90'");
			sql.append(" WHEN -(DATEDIFF(DATE(vitalstats.servicedatekey),"+throughDate+")) > 90 THEN '90 ++'");
			sql.append(" ELSE 'UNKNOWN' END AS BUCKET");
			sql.append(" FROM transactionhistory");
			sql.append(" JOIN vitalstats ON transactionhistory.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" JOIN financialdata ON vitalstats.vitalsmasterkey=financialdata.vitalsmasterkey");
			sql.append(" WHERE InactiveCode<>'D' AND");
			sql.append(" (servicedatekey<="+throughDate+") AND");
			sql.append(" (dateoftran<="+throughDate+")  AND");
			sql.append(" voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL')");
			sql.append(" AND transactionhistory.DeleteTransaction='' AND spfcode NOT IN ('I','C')");
			sql.append(" AND servicedatekey<>'' ");
			sql.append(" AND -(DATEDIFF(DATE(vitalstats.servicedatekey),"+throughDate+")) >"+0000);
			sql.append(" AND -(DATEDIFF(DATE(vitalstats.servicedatekey),"+throughDate+")) <"+9999);
			sql.append(" AND financialdata.TotalCharges-financialdata.totalpaidtodate<>0 ");
			if(localeId>0){
				sql.append(" AND locales.LocaleID="+localeId);
			}
			sql.append(" GROUP BY  spfcode, BUCKET ORDER BY BUCKET ASC");
			
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();
		
			while(rs.next()){
				totalCharges.add(rs.getDouble(1));
				//logger.info("Total Charges :: " +rs.getDouble(1));
				payCode.add(rs.getString(2));
				//logger.info("PayCode :: " +rs.getString(2));
				bucket.add(rs.getString(3));
				//logger.info("Bucket :: "+ rs.getString(3));
			}
		} catch (SQLException e) {
			logger.error("SQLException in getARagingByDateBucketAll() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getARagingByDateBucketAll() : ", e);
		} finally {
			closeConnection();
		}
		return arAgingData;
	}
	
	public ArrayList<ArrayList<?>> getARagingByDirector(String throughDate,long localeId){
		ArrayList<ArrayList<?>> arAgingData = new ArrayList<ArrayList<?>>();
		
		try{
			ArrayList<String> directorName = new ArrayList<String>();
			ArrayList<Double> revenue = new ArrayList<Double>();
			ArrayList<Double> ar = new ArrayList<Double>();
			
			arAgingData.add(directorName);
			arAgingData.add(revenue);
			arAgingData.add(ar);
						
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT b.arrangerId,b.directorname , ifNull(b.totalCharges,0) as Revenue ,ifNull(a.totalCharges,0) as Payment, ifNull(b.totalCharges,0)+ifNull(a.totalCharges,0) as AR  FROM (");
			
			sql.append(" SELECT");
			sql.append(" arrangerId,");
			sql.append(" CONCAT(LEFT(arrangers.Name,1),'. ',SUBSTRING_INDEX(arrangers.Name, ' ', -1)) as directorname,");
			sql.append(" SUM(amountoftran/100) AS totalCharges,");
			sql.append(" 'Payment' AS Paycode");
			sql.append(" FROM transactionhistory");
			sql.append(" JOIN vitalstats ON transactionhistory.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" JOIN financialdata ON vitalstats.vitalsmasterkey=financialdata.vitalsmasterkey");
			sql.append(" JOIN arrangers ON vitalstats.arrangerID=arrangers.Identity");
			sql.append(" WHERE " );
			sql.append(" financialdata.saletype NOT LIKE '%Tri%' AND financialdata.saletype NOT LIKE '%Matu%' ");
			sql.append(" AND InactiveCode<>'D' AND");
			sql.append(" (servicedatekey<="+throughDate+") AND");
			sql.append(" (dateoftran<="+throughDate+")  AND");
			sql.append(" voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL')");
			sql.append(" AND transactionhistory.DeleteTransaction='' AND spfcode NOT IN ('I','C','S','F')");
			sql.append(" AND servicedatekey<>'' ");
			sql.append(" AND financialdata.TotalCharges-financialdata.totalpaidtodate<>0");
			if(localeId>0){
				sql.append(" AND locales.LocaleID="+localeId);
			}
			sql.append(" GROUP BY arrangerId ORDER BY arrangers.Name ASC ");
			
			sql.append("  ) a RIGHT JOIN (");
			
			sql.append(" SELECT");
			sql.append(" arrangerId,");
			sql.append(" CONCAT(LEFT(arrangers.Name,1),'. ',SUBSTRING_INDEX(arrangers.Name, ' ', -1)) as directorname,");
			sql.append(" SUM(amountoftran/100) AS totalCharges,");
			sql.append(" 'Revenue' AS Paycode");
			sql.append(" FROM transactionhistory");
			sql.append(" JOIN vitalstats ON transactionhistory.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" JOIN financialdata ON vitalstats.vitalsmasterkey=financialdata.vitalsmasterkey");
			sql.append(" JOIN arrangers ON vitalstats.arrangerID=arrangers.Identity");
			sql.append(" WHERE ");
			sql.append(" financialdata.saletype NOT LIKE '%Tri%' AND financialdata.saletype NOT LIKE '%Matu%' ");
			sql.append(" AND InactiveCode<>'D' AND");
			sql.append(" (servicedatekey<="+throughDate+") AND");
			sql.append(" (dateoftran<="+throughDate+")  AND");
			sql.append(" voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL')");
			sql.append(" AND transactionhistory.DeleteTransaction='' AND spfcode NOT IN ('I','C','P')");
			sql.append(" AND servicedatekey<>'' ");
			sql.append(" AND financialdata.TotalCharges-financialdata.totalpaidtodate<>0");
			if(localeId>0){
				sql.append(" AND locales.LocaleID="+localeId);
			}
			sql.append(" GROUP BY  arrangerId ORDER BY arrangers.Name ASC ");
			sql.append(" ) b on  a.arrangerId = b.arrangerId ");
			
			// arrangerId ORDER BY arrangers.Name ASC
			//arrangerId ORDER BY arrangers.Name ASC
			
			logger.info("The SQL payment revenue::  " +sql.toString() );
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();
		
			while(rs.next()){
				directorName.add(rs.getString(2));
				//logger.info("Director name :: " +rs.getString(1));
				
				revenue.add(rs.getDouble(3));
				//logger.info("Total Charges  "+type +" :: " +rs.getDouble(2));
				
				ar.add(rs.getDouble(5));
			}
		} catch (SQLException e) {
			logger.error("SQLException in getARagingByDirector() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getARagingByDirector() : ", e);
		} finally {
			closeConnection();
		}
		return arAgingData;		
	}
	
	public double[] getARagingByDatetDirectorRevColBal(String throughDate,long localeId){
		double[] arAgingData = new double[2];
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" 'Collected from all Director' AS DirectorName,");
			sql.append(" ABS(SUM(amountoftran/100)) AS totalCharges");
			sql.append(" FROM transactionhistory");
			sql.append(" JOIN vitalstats ON transactionhistory.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" WHERE InactiveCode<>'D' AND");
			sql.append(" (servicedatekey<="+throughDate+") AND");
			sql.append(" (dateoftran<="+throughDate+")  AND");
			sql.append("  voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL')");
			sql.append(" AND transactionhistory.DeleteTransaction='' AND spfcode ='P'");
			if(localeId>0){
				sql.append(" AND locales.LocaleID="+localeId);
			}
			sql.append(" AND servicedatekey<>'' ");
			sql.append(" UNION");
			sql.append(" SELECT");
			sql.append(" 'Due from all Director' AS DirectorName,");
			sql.append(" SUM(amountoftran/100) AS totalCharges");
			sql.append(" FROM transactionhistory");
			sql.append(" JOIN vitalstats ON transactionhistory.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" WHERE InactiveCode<>'D' AND");
			sql.append(" (servicedatekey<="+throughDate+") AND");
			sql.append(" (dateoftran<="+throughDate+")  AND");
			sql.append("  voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL')");
			sql.append(" AND transactionhistory.DeleteTransaction='' AND spfcode NOT IN ('I','C')");
			if(localeId>0){
				sql.append(" AND locales.LocaleID="+localeId);
			}
			sql.append(" AND servicedatekey<>'' ");
			
			logger.info("The SQL tab 3 chart 3::  " +sql.toString() );
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();
			
			int i=0;
			while(rs.next()){
				arAgingData[i]=(rs.getDouble(2));
				i++;
				//logger.info("Due   :: " +rs.getDouble(2));
			}
		} catch (SQLException e) {
			logger.error("SQLException in getARagingByDirector() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getARagingByDirector() : ", e);
		} finally {
			closeConnection();
		}
		
		return arAgingData;
	}

	public ArrayList<ArrayList<?>> getCallRevAvgForCompanyYearlyBySale(String fromDate,String toDate,String saleType,String currentYear,String lastYear,long localeId){
		ArrayList<ArrayList<?>> saleTypeData = new ArrayList<ArrayList<?>>();
		
		try{
			ArrayList<String> currentYearSaleType= new ArrayList<String>();
			ArrayList<Double> currentYearData = new ArrayList<Double>();
			ArrayList<String> lastYearSaleType= new ArrayList<String>();
			ArrayList<Double> lastYearData = new ArrayList<Double>();
			ArrayList<Double> currentYearRevData = new ArrayList<Double>();
			ArrayList<Double> lastYearRevData = new ArrayList<Double>();
			ArrayList<Double> currentYearAvgData = new ArrayList<Double>();
			ArrayList<Double> lastYearAvgData = new ArrayList<Double>();
			
			saleTypeData.add(currentYearData);   
			saleTypeData.add(currentYearSaleType);
			saleTypeData.add(lastYearData);
			saleTypeData.add(lastYearSaleType);
			saleTypeData.add(currentYearRevData);
			saleTypeData.add(currentYearAvgData);
			saleTypeData.add(lastYearRevData);
			saleTypeData.add(lastYearAvgData);
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" LEFT("+toDate+",4) AS SaleYear,");
			sql.append(" saletype AS SaleType, financialdata.saletype AS the_sales_type");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" WHERE (financialdata.saletype LIKE '"+saleType+"') AND financialdata.saletype NOT LIKE '%Tri%' AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Matu%' AND");
			sql.append(" (servicedatekey>="+fromDate+" AND servicedatekey<="+toDate+")");
			sql.append(" AND financialdata.SentToAccounting=1");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL') ");
			if(localeId>0){
				sql.append(" AND locales.LocaleID="+localeId);
			}
			sql.append(" GROUP BY locales.LocaleID");
			sql.append(" UNION");
			sql.append(" SELECT");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" YEAR((DATE_SUB("+fromDate+", INTERVAL 1 YEAR))) AS SaleYear,");
			sql.append(" saletype AS SaleType, financialdata.saletype AS the_sales_type");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" WHERE (financialdata.saletype LIKE '"+saleType+"') AND financialdata.saletype NOT LIKE '%Tri%' AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Matu%' AND");
			sql.append("(servicedatekey>=DATE_FORMAT(((DATE_SUB("+fromDate+", INTERVAL 1 YEAR))),'%Y%m%d') AND servicedatekey<= DATE_FORMAT(((DATE_SUB("+toDate+", INTERVAL 1 YEAR))),'%Y%m%d'))");
			sql.append(" AND financialdata.SentToAccounting=1");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL') ");
			if(localeId>0){
				sql.append(" AND locales.LocaleID="+localeId);
			}
			sql.append(" GROUP BY locales.LocaleID");
			
			logger.info("SQL Sale Type Tab-1 " +sql.toString() );
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();
			//String currentYear="2011";
			//String lastYear="2010";
			
			while(rs.next()){
				if(rs.getString(4).equals(currentYear)){
					currentYearData.add(rs.getDouble(1));
					currentYearRevData.add(rs.getDouble(2));
					currentYearAvgData.add(rs.getDouble(3));
					currentYearSaleType.add(rs.getString(6));
					
				}else if(rs.getString(4).equals(lastYear)){
					lastYearData.add(rs.getDouble(1));
					lastYearRevData.add(rs.getDouble(2));
					lastYearAvgData.add(rs.getDouble(3));
					lastYearSaleType.add(rs.getString(6));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getARagingByDirector() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getARagingByDirector() : ", e);
		} finally {
			closeConnection();
		}
		return saleTypeData;
	}
	
	
	public ArrayList<ArrayList<?>> getCallAverageRevenueByDirector(String fromDate,String toDate,String currentYear, String lastYear,long localeId){
		ArrayList<ArrayList<?>> directorData = new ArrayList<ArrayList<?>>();
		try{
			ArrayList<String> currentYearDirectorName= new ArrayList<String>();
			ArrayList<Double> currentYearAverage = new ArrayList<Double>();
			ArrayList<String> lastYearDirectorName= new ArrayList<String>();
			ArrayList<Double> lastYearAverage = new ArrayList<Double>();
			ArrayList<Double> currentYearCalls = new ArrayList<Double>();
			ArrayList<Double> lastYearCalls= new ArrayList<Double>();
			
			directorData.add(currentYearDirectorName);
			directorData.add(lastYearDirectorName);
			directorData.add(currentYearCalls);
			directorData.add(lastYearCalls);
			directorData.add(currentYearAverage);
			directorData.add(lastYearAverage);
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" LEFT("+toDate+",4) AS SaleYear,");
			sql.append(" CONCAT(LEFT(arrangers.Name,1),'. ',SUBSTRING_INDEX(arrangers.Name, ' ', -1)) as directorname,");
			//sql.append(" CONCAT(LEFT(directorname,1),'. ',SUBSTRING_INDEX(directorname, ' ', -1)) as directorname,");
			//sql.append(" directorname,");
			sql.append(" arrangerid");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" JOIN arrangers ON vitalstats.arrangerID=arrangers.Identity");
			sql.append(" WHERE ( financialdata.saletype  LIKE '%bur%' OR financialdata.saletype  LIKE '%crem%' )");
			sql.append(" AND financialdata.saletype NOT LIKE '%Tri%' AND financialdata.saletype NOT LIKE '%Matu%'");
			sql.append(" AND InactiveCode<>'D' ");
			sql.append(" AND (servicedatekey>="+fromDate+" AND servicedatekey<="+toDate+") ");
			sql.append(" AND financialdata.SentToAccounting=1");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL') ");
			if(localeId>0){
				sql.append(" AND locales.LocaleID="+localeId);
			}
			sql.append(" GROUP BY arrangerid");
			sql.append(" UNION");
			sql.append(" SELECT ");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" YEAR((DATE_SUB("+fromDate+", INTERVAL 1 YEAR))) AS SaleYear,");
			sql.append(" CONCAT(LEFT(arrangers.Name,1),'. ',SUBSTRING_INDEX(arrangers.Name, ' ', -1)) as directorname,");
			//sql.append(" CONCAT(LEFT(directorname,1),'. ',SUBSTRING_INDEX(directorname, ' ', -1)) as directorname,");
			//sql.append(" directorname,");
			sql.append(" arrangerid ");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" JOIN arrangers ON vitalstats.arrangerID=arrangers.Identity");
			sql.append(" WHERE ( financialdata.saletype LIKE '%bur%'  OR  financialdata.saletype  LIKE '%crem%' )");
			sql.append(" AND financialdata.saletype NOT LIKE '%Tri%' AND financialdata.saletype NOT LIKE '%Matu%'");
			sql.append(" AND InactiveCode<>'D' AND ");
			sql.append(" (servicedatekey>=DATE_FORMAT(((DATE_SUB("+fromDate+", INTERVAL 1 YEAR))),'%Y%m%d') AND servicedatekey<= DATE_FORMAT(((DATE_SUB("+toDate+", INTERVAL 1 YEAR))),'%Y%m%d')) ");
			sql.append(" AND financialdata.SentToAccounting=1 ");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL')");
			if(localeId>0){
				sql.append(" AND locales.LocaleID="+localeId);
			}
			sql.append(" GROUP BY arrangerid ");
			sql.append(" ORDER BY directorname");
			
			logger.info("SQL Director Tab-4 " +sql.toString() );
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql.toString());
			rs = statement.executeQuery();
			//String currentYear="2011";
			//String lastYear="2010";
			
			while(rs.next()) {
				if(rs.getString(5)!=null && !rs.getString(5).equals("")) {
					if(rs.getString(4).equals(currentYear)){
						currentYearCalls.add(rs.getDouble(1));
						currentYearAverage.add(rs.getDouble(3));
						currentYearDirectorName.add(rs.getString(5));
					} else if(rs.getString(4).equals(lastYear)){
						lastYearCalls.add(rs.getDouble(1));
						lastYearAverage.add(rs.getDouble(3));
						lastYearDirectorName.add(rs.getString(5));
					}
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getCallAverageRevenueByDirector() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getCallAverageRevenueByDirector() : ", e);
		} finally {
			closeConnection();
		}
		return directorData;
	}
		
	public ArrayList<ArrayList<?>> getLocaleReportAllSaleType(String fromDate,String toDate) {
		ArrayList<ArrayList<?>> localeData = new ArrayList<ArrayList<?>>();
		
		try{
			ArrayList<Double> callVolume = new ArrayList<Double>();
			ArrayList<Double> revenueVolume = new ArrayList<Double>();
			ArrayList<Double> averageVolume = new ArrayList<Double>();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" LEFT("+toDate+",4) AS SaleYear");
			//sql.append(", @saletype");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			//sql.append(" WHERE (financialdata.saletype LIKE '" + saleType);
			sql.append(" WHERE (financialdata.saletype LIKE '%crem%' or financialdata.saletype LIKE '%bur%') AND financialdata.saletype NOT LIKE '%Tri%' AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Matu%' ");
			sql.append(" AND (servicedatekey>=" + fromDate);
			sql.append(" AND servicedatekey<=" + toDate);
			sql.append(") AND financialdata.SentToAccounting=1 ");
			// sql.append(" AND locales.LocaleID=78");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL')  ");
			sql.append(" UNION ");
			sql.append("SELECT");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" YEAR((DATE_SUB("+fromDate+", INTERVAL 1 YEAR))) AS SaleYear");
			//sql.append(", @saletype");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			//sql.append(" WHERE (financialdata.saletype LIKE '" + saleType);
			sql.append(" WHERE (financialdata.saletype LIKE '%crem%' or financialdata.saletype LIKE '%bur%') AND financialdata.saletype NOT LIKE '%Tri%' AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Matu%' ");
			//sql.append("') AND InactiveCode<>'D' ");
			sql.append(" AND (servicedatekey>=DATE_FORMAT(DATE_SUB(" + fromDate);
			sql.append(" , INTERVAL 1 YEAR),'%Y%m%d')");
			sql.append(" AND servicedatekey<=DATE_FORMAT(DATE_SUB(" + toDate);
			sql.append(" , INTERVAL 1 YEAR),'%Y%m%d'))");
			sql.append(" AND financialdata.SentToAccounting=1 ");
			// sql.append(" AND locales.LocaleID=78");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL')  ");
				
			logger.info("SQL Locale SaleType Tab 5::  "+ sql);
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql.toString());
			rs= statement.executeQuery();
			//rs= getCallRevAvgForCompanyYearlyBySaleType(fromDate, toDate, "%crem%");
			while(rs.next()){
				callVolume.add(rs.getDouble(1));
				//logger.info("crem # :: " +rs.getDouble(1));
				revenueVolume.add(rs.getDouble(2));
			//	logger.info("crem $ :: " +rs.getDouble(2));
				averageVolume.add(rs.getDouble(3));
				//logger.info("crem avg :: " +rs.getDouble(3));
			}
			localeData.add(callVolume);
			localeData.add(revenueVolume);
			localeData.add(averageVolume);
		} catch (SQLException e) {
			logger.error("SQLException in getLocaleReportAllSaleType() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getLocaleReportAllSaleType() : ", e);
		}  finally {
            closeConnection();
        }
		return localeData;
	}
	
	public ArrayList<ArrayList<?>> getMarketingOneAgeAmountSpent(String fromDate,String toDate,String currentYear, String lastYear){
		ArrayList<ArrayList<?>> bucketData= new ArrayList<ArrayList<?>>();
		
		try{
			ArrayList<Double> currentYearCall = new ArrayList<Double>();
			ArrayList<Double> currentYearAverage = new ArrayList<Double>();
			ArrayList<Double> lastYearCall = new ArrayList<Double>();
			ArrayList<Double> lastYearAverage = new ArrayList<Double>();
			
			bucketData.add(currentYearCall);
			bucketData.add(lastYearCall);
			bucketData.add(currentYearAverage);
			bucketData.add(lastYearAverage);
			
			StringBuilder sql= new StringBuilder();
			
			sql.append(" SELECT");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" LEFT("+toDate+",4) AS SaleYear,");
			sql.append(" CASE");
			sql.append(" WHEN (vitalstats.AgeYears) < 51 THEN '00-50'");
			sql.append(" WHEN (vitalstats.AgeYears) < 66 THEN '51-65'");
			sql.append(" WHEN (vitalstats.AgeYears) < 81 THEN '66-80'");
			sql.append(" WHEN (vitalstats.AgeYears) < 96 THEN '81-95'");
			sql.append(" ELSE '96++' END AS BUCKET");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" JOIN arrangers ON vitalstats.arrangerID=arrangers.Identity");
			sql.append(" WHERE ( financialdata.saletype  LIKE '%bur%' OR  financialdata.saletype  LIKE '%Crem%')");
			sql.append(" AND financialdata.saletype NOT LIKE '%Tri%' AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Matu%'");
			sql.append(" AND (servicedatekey>="+fromDate+" AND servicedatekey<="+toDate+") ");
			sql.append(" AND financialdata.SentToAccounting=1");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL') ");
			// sql.append(" AND locales.LocaleID=78");
			sql.append(" GROUP BY BUCKET");
			sql.append(" UNION");
			sql.append(" SELECT");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" YEAR((DATE_SUB("+fromDate+", INTERVAL 1 YEAR))) AS SaleYear,");
			sql.append(" CASE");
			sql.append(" WHEN (vitalstats.AgeYears) < 51 THEN '00-50'");
			sql.append(" WHEN (vitalstats.AgeYears) < 66 THEN '51-65'");
			sql.append(" WHEN (vitalstats.AgeYears) < 81 THEN '66-80'");
			sql.append(" WHEN (vitalstats.AgeYears) < 96 THEN '81-95'");
			sql.append(" ELSE '96++' END AS BUCKET");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" JOIN arrangers ON vitalstats.arrangerID=arrangers.Identity");
			sql.append(" WHERE ( financialdata.saletype  LIKE '%bur%' OR  financialdata.saletype  LIKE '%Crem%')");
			sql.append(" AND financialdata.saletype NOT LIKE '%Tri%' AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Matu%'");
			sql.append(" AND (servicedatekey>=DATE_FORMAT(((DATE_SUB("+fromDate+", INTERVAL 1 YEAR))),'%Y%m%d') AND servicedatekey<= DATE_FORMAT(((DATE_SUB("+toDate+", INTERVAL 1 YEAR))),'%Y%m%d')) ");
			sql.append(" AND financialdata.SentToAccounting=1");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL') ");
			// sql.append(" AND locales.LocaleID=78");
			sql.append(" GROUP BY BUCKET");
			
			logger.info("SQL Marketing Tab 3 Age::  "+ sql);
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql.toString());
			rs= statement.executeQuery();
			while (rs.next()){
				if(rs.getString(4).equals(currentYear)){
					currentYearCall.add(rs.getDouble(1));
					currentYearAverage.add(rs.getDouble(3));
				}else if(rs.getString(4).equals(lastYear)){
					lastYearCall.add(rs.getDouble(1));
					lastYearAverage.add(rs.getDouble(3));
				}
			}
		}catch (SQLException e) {
			logger.error("SQLException in getMarketingOneAgeAmountSpent() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getMarketingOneAgeAmountSpent() : ", e);
		} finally {
            closeConnection();
        } 
		return bucketData;
	}
	
	
	public ArrayList<ArrayList<?>> getMarketingOneGender(String fromDate,String toDate,String currentYear, String lastYear){
		ArrayList<ArrayList<?>> bucketData;bucketData= new ArrayList<ArrayList<?>>();
		
		try{
			ArrayList<Double> currentYearCall = new ArrayList<Double>();
			ArrayList<Double> currentYearAverage = new ArrayList<Double>();
			ArrayList<Double> lastYearCall = new ArrayList<Double>();
			ArrayList<Double> lastYearAverage = new ArrayList<Double>();
			
			bucketData.add(currentYearCall);
			bucketData.add(lastYearCall);
			bucketData.add(currentYearAverage);
			bucketData.add(lastYearAverage);
			
			StringBuilder sql= new StringBuilder();
			
			sql.append(" SELECT");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" LEFT("+toDate+",4) AS SaleYear,");
			sql.append(" CASE");
			sql.append(" WHEN (vitalstats.DeceasedSex) ='M'THEN 'Male'");
			sql.append(" WHEN (vitalstats.DeceasedSex) ='F'THEN 'Female'");
			sql.append(" WHEN (vitalstats.DeceasedSex) =' 'THEN 'Male'");
			sql.append(" end AS BUCKET");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" JOIN arrangers ON vitalstats.arrangerID=arrangers.Identity");
			sql.append(" WHERE ( financialdata.saletype  LIKE '%bur%' OR  financialdata.saletype  LIKE '%Crem%')");
			sql.append(" AND financialdata.saletype NOT LIKE '%Tri%' AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Matu%'");
			sql.append(" AND (servicedatekey>="+fromDate+" AND servicedatekey<="+toDate+") ");
			sql.append(" AND financialdata.SentToAccounting=1");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL') ");
			// sql.append(" AND locales.LocaleID=78 ");
			sql.append(" GROUP BY BUCKET");
			sql.append(" UNION");
			sql.append(" SELECT");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" YEAR((DATE_SUB("+fromDate+", INTERVAL 1 YEAR))) AS SaleYear,");
			sql.append(" CASE");
			sql.append(" WHEN (vitalstats.DeceasedSex) ='M'THEN 'Male'");
			sql.append(" WHEN (vitalstats.DeceasedSex) ='F'THEN 'Female'");
			sql.append(" WHEN (vitalstats.DeceasedSex) =' 'THEN 'Male'");
			sql.append(" end AS BUCKET");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" JOIN arrangers ON vitalstats.arrangerID=arrangers.Identity");
			sql.append(" WHERE ( financialdata.saletype  LIKE '%bur%' OR  financialdata.saletype  LIKE '%Crem%')");
			sql.append(" AND financialdata.saletype NOT LIKE '%Tri%' AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Matu%'");
			sql.append(" AND (servicedatekey>=DATE_FORMAT(((DATE_SUB("+fromDate+", INTERVAL 1 YEAR))),'%Y%m%d') AND servicedatekey<= DATE_FORMAT(((DATE_SUB("+toDate+", INTERVAL 1 YEAR))),'%Y%m%d')) ");
			sql.append(" AND financialdata.SentToAccounting=1");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL') ");
			// sql.append(" AND locales.LocaleID=78 ");
			sql.append(" GROUP BY BUCKET");
			
			logger.info("SQL Marketing Tab 3 Gender::  "+ sql);
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql.toString());
			rs= statement.executeQuery();
			while (rs.next()){
				if(rs.getString(4).equals(currentYear)){
					currentYearCall.add(rs.getDouble(1));
					currentYearAverage.add(rs.getDouble(3));
				}else if(rs.getString(4).equals(lastYear)){
					lastYearCall.add(rs.getDouble(1));
					lastYearAverage.add(rs.getDouble(3));
				}
			}
		}catch (SQLException e) {
			logger.error("SQLException in getMarketingOneAgeAmountSpent() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getMarketingOneAgeAmountSpent() : ", e);
		}  finally {
            closeConnection();
        }
		return bucketData;
	}
	
	public ArrayList<ArrayList<?>> getMarketingOneRace(String fromDate,String toDate,String currentYear, String lastYear){
		ArrayList<ArrayList<?>> bucketData= new ArrayList<ArrayList<?>>();
		
		try{
			ArrayList<Double> currentYearCall = new ArrayList<Double>();
			ArrayList<Double> currentYearAverage = new ArrayList<Double>();
			ArrayList<Double> lastYearCall = new ArrayList<Double>();
			ArrayList<Double> lastYearAverage = new ArrayList<Double>();
			
			bucketData.add(currentYearCall);
			bucketData.add(lastYearCall);
			bucketData.add(currentYearAverage);
			bucketData.add(lastYearAverage);
			
			StringBuilder sql= new StringBuilder();
			
			sql.append(" SELECT");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" LEFT("+toDate+",4) AS SaleYear,");
			sql.append(" CASE");
			sql.append(" WHEN (vitalstats.race) ='White'THEN 'White'");
			sql.append(" WHEN ((vitalstats.race) ='Black' or (vitalstats.race) ='African' or (vitalstats.race) ='African American')THEN 'Black'");
			sql.append(" WHEN (vitalstats.race) ='Hispanic'THEN 'Hispanic'");
			sql.append(" ELSE 'Other' END AS BUCKET");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" JOIN arrangers ON vitalstats.arrangerID=arrangers.Identity");
			sql.append(" WHERE ( financialdata.saletype  LIKE '%bur%' OR  financialdata.saletype  LIKE '%Crem%')");
			sql.append(" AND financialdata.saletype NOT LIKE '%Tri%' AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Matu%'");
			sql.append(" AND (servicedatekey>="+fromDate+" AND servicedatekey<="+toDate+") ");
			sql.append(" AND financialdata.SentToAccounting=1");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL') ");
			// sql.append(" AND locales.LocaleID=78");;
			sql.append(" GROUP BY BUCKET");
			sql.append(" UNION");
			sql.append(" SELECT");
			sql.append(" COUNT(vitalstats.vitalsmasterkey) AS TotalNumberOfCase,");
			sql.append(" SUM(totalcharges)/100 AS TotalCharges,");
			sql.append(" SUM(totalcharges)/100/COUNT(vitalstats.vitalsmasterkey) AS AverageCaseAmount,");
			sql.append(" YEAR((DATE_SUB("+fromDate+", INTERVAL 1 YEAR))) AS SaleYear,");
			sql.append(" CASE");
			sql.append(" WHEN (vitalstats.race) ='White'THEN 'White'");
			sql.append(" WHEN ((vitalstats.race) ='Black' or (vitalstats.race) ='African' or (vitalstats.race) ='African American')THEN 'Black'");
			sql.append(" WHEN (vitalstats.race) ='Hispanic'THEN 'Hispanic'");
			sql.append(" ELSE 'Other' END AS BUCKET");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" JOIN arrangers ON vitalstats.arrangerID=arrangers.Identity");
			sql.append(" WHERE ( financialdata.saletype  LIKE '%bur%' OR  financialdata.saletype  LIKE '%Crem%')");
			sql.append(" AND financialdata.saletype NOT LIKE '%Tri%' AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Matu%'");
			sql.append(" AND (servicedatekey>=DATE_FORMAT(((DATE_SUB("+fromDate+", INTERVAL 1 YEAR))),'%Y%m%d') AND servicedatekey<= DATE_FORMAT(((DATE_SUB("+toDate+", INTERVAL 1 YEAR))),'%Y%m%d')) ");
			sql.append(" AND financialdata.SentToAccounting=1");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL') ");
			// sql.append(" AND locales.LocaleID=78");
			sql.append(" GROUP BY BUCKET");
			
			logger.info("SQL Marketing Tab 3 Race::  "+ sql);
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql.toString());
			rs= statement.executeQuery();
			while (rs.next()){
				if(rs.getString(4).equals(currentYear)){
					currentYearCall.add(rs.getDouble(1));
					currentYearAverage.add(rs.getDouble(3));
				}else if(rs.getString(4).equals(lastYear)){
					lastYearCall.add(rs.getDouble(1));
					lastYearAverage.add(rs.getDouble(3));
				}
			}
		}catch (SQLException e) {
			logger.error("SQLException in getMarketingOneAgeAmountSpent() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getMarketingOneAgeAmountSpent() : ", e);
		} finally {
            closeConnection();
        }
		return bucketData;
	}
	
	public ArrayList<Double> getSalesAccountType(String fromDate,String toDate,String saleType,long localeId){
		ArrayList<Double> psrData = new ArrayList<Double>();
		try{
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT");
			sql.append(" CASE");
			sql.append(" WHEN (financialdata.saletype) LIKE ('% %') THEN 'Total'");
			sql.append(" WHEN (financialdata.saletype) LIKE ('%Bur%') THEN 'Burial'");
			sql.append(" WHEN (financialdata.saletype) LIKE ('%Crem%') THEN 'Cremation'");
			sql.append(" ELSE 'Total' END AS  SaletypeBurCrem,");
			sql.append(" SUM(amount)/100 AS SellingPrice,");
			sql.append(" CASE");
			sql.append(" WHEN (charges.CategoryCode) IN  ('CAS','SHP') THEN 'Caskets'");
			sql.append(" WHEN (charges.CategoryCode) IN  ('VAU','UVT') THEN 'OuterContainer'");
			sql.append(" WHEN (charges.CategoryCode) IN  ('OTR','CLO','CAO','MKR','EGR','URN','Memory Glass Products') THEN 'OtherMerchadise'");
			sql.append(" WHEN (charges.CategoryCode) IN  ('SRV') THEN 'Professional Service'");
			sql.append(" WHEN (charges.CategoryCode) IN  ('DIS') THEN 'Service Discounts'");
			sql.append(" WHEN (charges.CategoryCode) =  ('ADV') THEN 'CashAdvances'");
			sql.append(" WHEN (charges.CategoryCode) =  ('TAX') THEN 'Sales Tax'");
			sql.append(" ELSE 'Other' END AS  Category");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" JOIN charges ON charges.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" WHERE ( financialdata.saletype  LIKE "+saleType+" )");
			sql.append(" AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Tri%' AND financialdata.saletype NOT LIKE '%Matu%'  ");
			sql.append(" AND (servicedatekey>="+fromDate+" AND servicedatekey<="+toDate+") ");
			sql.append("  AND financialdata.SentToAccounting=1");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL') ");
			if(localeId>0){
				sql.append(" AND locales.LocaleID="+localeId);
			}
			sql.append(" GROUP BY SaletypeBurCrem,Category");
			sql.append(" UNION");
			sql.append(" SELECT");
			sql.append(" 'Total Contract Amount' AS TotalContractAmount,");
			sql.append(" SUM(totalcharges)/100,");
			sql.append(" "+saleType+" AS TotalContractAmount");
			sql.append(" FROM vitalstats");
			sql.append(" JOIN financialdata ON financialdata.vitalsmasterkey=vitalstats.vitalsmasterkey");
			sql.append(" JOIN locales ON locales.LocaleID=vitalstats.localenumber");
			sql.append(" WHERE ( financialdata.saletype  LIKE "+saleType+" )");
			sql.append(" AND InactiveCode<>'D' AND financialdata.saletype NOT LIKE '%Tri%' AND financialdata.saletype NOT LIKE '%Matu%'  ");
			sql.append(" AND (servicedatekey>="+fromDate+" AND servicedatekey<="+toDate+") ");
			sql.append(" AND financialdata.SentToAccounting=1");
			sql.append(" AND voidedcode<>'V' AND (pnpreneedstatus<>'A' AND pnpreneedstatus<>'C' AND pnpreneedstatus<>'NULL') ");
			if(localeId>0){
				sql.append(" AND locales.LocaleID="+localeId);
			}
			sql.append(" GROUP BY locales.LocaleID");			
			
			logger.info("SQL Sales Account Tab 6 "+ saleType +" ::  "+ sql);
			makeConnection(getDbLookup());
			statement = conn.prepareStatement(sql.toString());
			rs= statement.executeQuery();
			while (rs.next()){
				psrData.add(rs.getDouble(2));	
			}
		}catch (SQLException e) {
			logger.error("SQLException in getMarketingOneAgeAmountSpent() : ", e);
		} catch (Exception e) {
			logger.error("Exception in getMarketingOneAgeAmountSpent() : ", e);
		}  finally {
            closeConnection();
        }
		return psrData;
	}
}