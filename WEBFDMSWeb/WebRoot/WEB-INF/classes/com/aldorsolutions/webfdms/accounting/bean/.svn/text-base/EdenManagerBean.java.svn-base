package com.aldorsolutions.webfdms.accounting.bean;

import java.io.FileWriter;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

import com.aldorassist.webfdms.dao.EdenDAO;
import com.aldorassist.webfdms.model.EdenDTO;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.util.AccountingInterfaceUtil;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatString;


/**
 * Workfile: AccountingInterfaceManagerBean.java
 * Date: Nov 7, 2005 5:58:49 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class EdenManagerBean {
	
	final private static Logger logger = Logger.getLogger(EdenManagerBean.class.getName());

	public EdenManagerBean() { }
	
	public void generateEdenFile(){
	}
	public void createEdenFile(DbUserSession sessionUser, String fullFileName, String deathState, int vitalsId){
		try {
//		FileWriter fw = new FileWriter(fullFileName);
		
		ArrayList <EdenDTO> edens = new ArrayList <EdenDTO>();
		EdenDAO edenDao = new EdenDAO(sessionUser);
		edens = edenDao.getEdens(deathState);
		
		boolean ageYearGT1 = false;
		boolean ageMonthGT1 = false;
		boolean ageDayGT1 = false;
		boolean ageHourGT1 = false;
		boolean ageMinuteGT1 = false;
		
		
		for (EdenDTO eden : edens){
			Object data = "";
			String printData = "";
			
			if (eden.getTableName()!=null && eden.getFieldName()!=null &&
					eden.getTableName().trim().length()> 0 && eden.getFieldName().trim().length()> 0 ){
				data = edenDao.getData(eden,sessionUser,vitalsId);
			}
			if (data!= null){
				if(eden.getFieldFormat().compareToIgnoreCase("alphanumeric") == 0) {
					printData = convertAlpha(eden,data, sessionUser);
				}
				else if(eden.getFieldFormat().compareToIgnoreCase("checkbox") == 0) {
					printData = convertCheckbox(eden,data);
				} 
				else if(eden.getFieldFormat().compareToIgnoreCase("numeric") == 0) {
					printData = convertNumeric(eden,data);
				} 
			}
			if (printData.length()< eden.getLength()){
				for(int i = printData.length() ; i < eden.getLength();i++){
					printData = " "+printData;
				}
			}
			
//			if((eden.getFieldName().compareToIgnoreCase("AgeYears")!= 0) &&
//					(eden.getFieldName().compareToIgnoreCase("AgeMonths")== 0) &&
//					(eden.getFieldName().compareToIgnoreCase("AgeDays")== 0) &&
//					(eden.getFieldName().compareToIgnoreCase("AgeHours")== 0) &&
//					(eden.getFieldName().compareToIgnoreCase("AgeMinutes")== 0)
//			){
		
				if (eden.getUnknowDesc().compareToIgnoreCase("Y")==0 && printData.trim().length()==0){
					printData = "";
					for(int i = 0 ; i < eden.getLength()-1;i++){
						printData = " "+printData;
					}
					printData = "*";
				}
//			}
			if(eden.getFieldName().compareToIgnoreCase("AgeYears")== 0){
				if( (printData.trim().compareToIgnoreCase("*") != 0) && (Integer.valueOf(printData)>0) ){
					ageYearGT1 = true;
					ageMonthGT1 = true;
					ageDayGT1 = true;
					ageHourGT1 = true;
					ageMinuteGT1 = true;
				}
			}
			
			if ((eden.getFieldName().compareToIgnoreCase("AgeMonths")== 0)) {
				if(ageYearGT1 == false){
						if((printData.trim().compareToIgnoreCase("*") != 0) && (Integer.valueOf(printData)>0)){
							ageMonthGT1 = true;
							ageDayGT1 = true;
							ageHourGT1 = true;
							ageMinuteGT1 = true;
						}
				}else {
					printData = "  ";
				}
			}
			
			if (eden.getFieldName().compareToIgnoreCase("AgeDays")== 0) {
				if( (ageYearGT1 == false) && (ageMonthGT1 == false)){
						if((printData.trim().compareToIgnoreCase("*") != 0) && (Integer.valueOf(printData)>0)){
							ageDayGT1 = true;
							ageHourGT1 = true;
							ageMinuteGT1 = true;
						}
				}else {
					printData = "  ";
				}
			}
			if (eden.getFieldName().compareToIgnoreCase("AgeHours")== 0) {
				if((ageYearGT1 == false) && (ageMonthGT1 == false) && (ageDayGT1 == false)){
						if((printData.trim().compareToIgnoreCase("*") != 0) && (Integer.valueOf(printData)>0)){
								ageHourGT1 = true;
								ageMinuteGT1 = true;
						}
				}else 	{
					printData = "  ";
				}
			}
			if (eden.getFieldName().compareToIgnoreCase("AgeMinutes")== 0) {
				if((ageYearGT1 == false) && (ageMonthGT1 == false) && (ageDayGT1 == false) &&(ageHourGT1 == false)){
					if((printData.trim().compareToIgnoreCase("*") != 0) && (Integer.valueOf(printData)>0)){
								ageMinuteGT1 = true;
					}
				}else {
					printData= "  ";
					
				}
			}
			
			AccountingInterfaceUtil.addTranToDisk(fullFileName, printData); //this for concat
//			AccountingInterfaceUtil.addTranToDiskFile(fullFileName, printData); //this one for new line
//			fw.append(printData);
//			fw.append("-"+eden.getFieldName()+" "+eden.getLength()+":");
//			fw.append("\n");
		}
		
//      fw.flush();
//      fw.close();
		
		
		
		}catch (Exception pe){
			logger.error("Error in EdenManagerBean() : ", pe);
		}
	}

	private String convertCheckbox(EdenDTO eden, Object data) {
		String printData = " ";
		String tmpData = data.toString().trim();
		
		if (eden.getFieldName().compareToIgnoreCase("DeceasedSex") == 0){
			if (eden.getDescription().compareToIgnoreCase("Gender M")==0){
				if (tmpData.compareToIgnoreCase("M") == 0){
					printData = "X";
				}
			}
			else if (eden.getDescription().compareToIgnoreCase("Gender F")==0){
				if (tmpData.compareToIgnoreCase("F") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Gender U")==0){
				if (tmpData.compareToIgnoreCase("") == 0){
					printData = "X";
				}
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("Race") == 0){
			if (eden.getDescription().compareToIgnoreCase("Race White")==0){
				if (tmpData.compareToIgnoreCase("White") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Black")==0){
				if (tmpData.compareToIgnoreCase("Black") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Am Indian")==0){
				if (tmpData.compareToIgnoreCase("American Indian") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Chinese")==0){
				if (tmpData.compareToIgnoreCase("Chinese") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Japanese")==0){
				if (tmpData.compareToIgnoreCase("Japanese") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Hawaiian")==0){
				if (tmpData.compareToIgnoreCase("Hawaiian") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Filipino")==0){
				if (tmpData.compareToIgnoreCase("Filipino") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Other Asian")==0){
				if (tmpData.compareToIgnoreCase("Other Asian") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Asian Indian")==0){
				if (tmpData.compareToIgnoreCase("Asian Indian") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Korean")==0){
				if (tmpData.compareToIgnoreCase("Korean") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Samoan")==0){
				if (tmpData.compareToIgnoreCase("Samoan") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Vietnamese")==0){
				if (tmpData.compareToIgnoreCase("Vietnamese") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Guamanian")==0){
				if (tmpData.compareToIgnoreCase("Guamanian") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Pac Islander")==0){
				if (tmpData.compareToIgnoreCase("Pac Islander") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Other")==0){
				if (tmpData.compareToIgnoreCase("Other") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Unknown")==0){
				if (tmpData.compareToIgnoreCase("Unknow") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Race Asian Indian")==0){
				if (tmpData.compareToIgnoreCase("Japanese") == 0){
					printData = "X";
				}
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("HispanicOriginBox") == 0){
			if (eden.getDescription().compareToIgnoreCase("Hispanic Origin Y")==0){
				if (tmpData.compareToIgnoreCase("Y") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Hispanic Origin N")==0){
				if (tmpData.compareToIgnoreCase("N") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Hispanic Unknown")==0){
				if (tmpData.compareToIgnoreCase("") == 0){
					printData = "X";
				}
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("DecSpecifyHispanic") == 0){
			if (eden.getDescription().compareToIgnoreCase("Hispanic Mexican")==0){
				if (tmpData.compareToIgnoreCase("Mexican") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Hispanic Cuban")==0){
				if (tmpData.compareToIgnoreCase("Cuban") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Hispanic Puerto Rico")==0){
				if (tmpData.compareToIgnoreCase("Puerto Rico") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Hispanic Other")==0){
				if (tmpData.compareToIgnoreCase("Other") == 0){
					printData = "X";
				}
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("DecEducation") == 0){
			if (eden.getDescription().compareToIgnoreCase("Education None")==0){
				if (tmpData.indexOf("None") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Ed 8th Grade")==0){
				if (tmpData.indexOf("8") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Ed 9th-12th Grade")==0){
				if (tmpData.indexOf("9") >= 0 || tmpData.indexOf("10") >= 0 || tmpData.indexOf("11") >= 0 || tmpData.indexOf("12") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Ed High School")==0){
				if (tmpData.indexOf("High") >=0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Ed Some College")==0){
				if (tmpData.indexOf("College") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Ed Associate")==0){
				if (tmpData.indexOf("Associate") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Ed Bachelor")==0){
				if (tmpData.indexOf("Bachelor") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Ed Master")==0){
				if (tmpData.indexOf("Master") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Ed Doctorate")==0){
				if (tmpData.indexOf("Doctorate") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Ed Unknown")==0){
				if (tmpData.indexOf("Unknown") >= 0){
					printData = "X";
				}
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("InArmedForces") == 0){
			if (eden.getDescription().compareToIgnoreCase("Armed Forces Y")==0){
				if (tmpData.compareToIgnoreCase("Y") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Armed Forces N")==0){
				if (tmpData.compareToIgnoreCase("N") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Armed Forces U")==0){
				if (tmpData.compareToIgnoreCase("") == 0){
					printData = "X";
				}
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("PregnantAtDeath") == 0){
			if (eden.getDescription().compareToIgnoreCase("Pregnant No")==0){
				if (tmpData.indexOf("Not pregnant") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Pregnant Yes")==0){
				if (tmpData.indexOf("Pregnant at time of death") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Pregnant 42 Days")==0){
				if (tmpData.indexOf("42 days") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Pregnant One Year")==0){
				if (tmpData.indexOf("year") >=0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Pregnant Unknown")==0){
				if (tmpData.indexOf("Unknown") >= 0){
					printData = "X";
				}
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("DecMarried") == 0){
			if (eden.getDescription().compareToIgnoreCase("MS Never Married")==0){
				if (tmpData.indexOf("Never") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("MS Widowed")==0){
				if (tmpData.indexOf("Widowed") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("MS Divorced")==0){
				if (tmpData.indexOf("Divorced") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("MS Separated")==0){
				if (tmpData.indexOf("Separated") >=0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("MS Unknown")==0){
				if (tmpData.indexOf("Unknown") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("MS Married")==0){
				if (tmpData.indexOf("Married") >= 0 && !((tmpData.indexOf("Never") >= 0) ||(tmpData.indexOf("Widowed") >= 0)
						|| (tmpData.indexOf("Divorced") >= 0) || (tmpData.indexOf("Separated") >=0) || (tmpData.indexOf("Unknown") >= 0))){
					printData = "X";
				}
			}
			
		}
		
		if (eden.getFieldName().compareToIgnoreCase("DecResInsideCity") == 0){
			if (eden.getDescription().compareToIgnoreCase("Res In City Limits Y")==0){
				if (tmpData.compareToIgnoreCase("Y") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Res In City Limits N")==0){
				if (tmpData.compareToIgnoreCase("N") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Res In City Limits U")==0){
				if (tmpData.compareToIgnoreCase("") == 0){
					printData = "X";
				}
			}
		}
		
		
		if (eden.getFieldName().compareToIgnoreCase("InpatientDOA") == 0){
			if (eden.getDescription().compareToIgnoreCase("POD Inpatient")==0){
				if (tmpData.indexOf("Inpatient") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("POD ER")==0){
				if (tmpData.indexOf("ER") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("POD DOA")==0){
				if (tmpData.indexOf("DOA") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("POD Dec Home")==0){
				if (tmpData.indexOf("Home") >=0 && !(tmpData.indexOf("Nursing") >= 0)){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("POD Nursing Home")==0){
				if (tmpData.indexOf("Nursing") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("POD Other")==0){
				if (tmpData.indexOf("Other") >= 0 ){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("POD Unknown")==0){
				if (tmpData.indexOf("Unknown") >= 0 ){
					printData = "X";
				}
			}
		}
		if (eden.getFieldName().compareToIgnoreCase("ReferredToME") == 0){
			if (eden.getDescription().compareToIgnoreCase("ME Contacted Y")==0){
				if (tmpData.compareToIgnoreCase("Y") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("ME Contacted N")==0){
				if (tmpData.compareToIgnoreCase("N") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("ME Contacted U")==0){
				if (tmpData.compareToIgnoreCase("") == 0){
					printData = "X";
				}
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("Autopsy") == 0){
			if (eden.getDescription().compareToIgnoreCase("Autopsy Done Y")==0){
				if (tmpData.compareToIgnoreCase("Y") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Autopsy Done N")==0){
				if (tmpData.compareToIgnoreCase("N") == 0){
					printData = "X";
				}
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("AutopsyFindings") == 0){
			if (eden.getDescription().compareToIgnoreCase("Autopsy Available Y")==0){
				if (tmpData.compareToIgnoreCase("Y") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Autopsy Available N")==0){
				if (tmpData.compareToIgnoreCase("N") == 0){
					printData = "X";
				}
			}
		}
		if (eden.getFieldName().compareToIgnoreCase("DispositionMethod") == 0){
			if (eden.getDescription().compareToIgnoreCase("Disposition Entombment")==0){
				if (tmpData.indexOf("Entombment") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Disposition Donation")==0){
				if (tmpData.indexOf("Donation") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Disposition Burial")==0){
				if (tmpData.indexOf("Burial") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Disposition Cremation")==0){
				if (tmpData.indexOf("Cremation") >=0 ){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Disposition Other")==0){
				if (tmpData.indexOf("Other") >= 0){
					printData = "X";
				}
			}
		}
		if (eden.getFieldName().compareToIgnoreCase("AccidSuicideNaturl") == 0){
			if (eden.getDescription().compareToIgnoreCase("Manner Natural")==0){
				if (tmpData.indexOf("Natural") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Manner Accident")==0){
				if (tmpData.indexOf("Accident") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Manner Suicide")==0){
				if (tmpData.indexOf("Suicide") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Manner Homicide")==0){
				if (tmpData.indexOf("Homicide") >=0 ){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Manner Not Determined")==0){
				if (tmpData.indexOf("Not Determined") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Manner Pending")==0){
				if (tmpData.indexOf("Pending") >= 0){
					printData = "X";
				}
			}
		}
		if (eden.getFieldName().compareToIgnoreCase("Tobacco") == 0){
			if (eden.getDescription().compareToIgnoreCase("Tobacco Probable")==0){
				if (tmpData.compareToIgnoreCase("P") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Tobacco Underlying")==0){
				if (tmpData.compareToIgnoreCase("Y") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Tobacco Not Contribute")==0){
				if (tmpData.compareToIgnoreCase("") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Tobacco Unknown")==0){
				if (tmpData.compareToIgnoreCase("U") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Tobacco Non User")==0){
				if (tmpData.compareToIgnoreCase("N") == 0){
					printData = "X";
				}
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("InjuryAtWork") == 0){
			if (eden.getDescription().compareToIgnoreCase("Injury At Work Y")==0){
				if (tmpData.compareToIgnoreCase("Y") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Injury At Work N")==0){
				if (tmpData.compareToIgnoreCase("N") == 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Injury At Work U")==0){
				if (tmpData.compareToIgnoreCase("") == 0){
					printData = "X";
				}
			}
		}
		if (eden.getFieldName().compareToIgnoreCase("InjuryTransportation") == 0){
			if (eden.getDescription().compareToIgnoreCase("Injury Motor Driver")==0){
				if (tmpData.indexOf("Driver") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Injury Motor Passenger")==0){
				if (tmpData.indexOf("Passenger") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Injury Motor Pedestrian")==0){
				if (tmpData.indexOf("Pedestrian") >= 0){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Injury Motor Other")==0){
				if (tmpData.indexOf("Other") >=0 ){
					printData = "X";
				}
			}else if (eden.getDescription().compareToIgnoreCase("Injury Motor Unknown")==0){
				if (tmpData.indexOf("Unknown") >= 0){
					printData = "X";
				}
			}
		}
		
		return printData;
	}

	private String convertAlpha(EdenDTO eden, Object data, DbUserSession sessionUser) {
		String printData = "";
		
		
		
		printData = data.toString().trim();
		
		if (eden.getFieldName().compareToIgnoreCase("DeceasedSuffix") == 0){
			String decSuffix[] = {"Jr","Sr","I","II","III","IV","V","VI","VII","VIII","IX","X"};
			String tmpData = data.toString().trim();
			boolean correctData = false;
			for (int j =0; j< decSuffix.length;j++){
				if (tmpData.compareToIgnoreCase(decSuffix[j])==0){
					correctData = true;
				}
			}
			if (!correctData){
			   printData = "";	
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("SocialSecurityNo") == 0){
			printData = FormatString.formatSocialSecurityNo(sessionUser.getLocaleCountry(), printData);
		}
		
		if (eden.getFieldName().compareToIgnoreCase("DecBirthPlaceCSV") == 0){
			if (eden.getDescription().compareToIgnoreCase("Birth City")==0){
				printData = CsvTable.getField(printData,1);
			} else if (eden.getDescription().compareToIgnoreCase("Birth State")==0){
				printData = CsvTable.getField(printData,2);
			} else if (eden.getDescription().compareToIgnoreCase("Birth Country")==0){
				printData = CsvTable.getField(printData,3);
			} 
		}
		
		if (eden.getFieldName().compareToIgnoreCase("DateOfBirth") == 0){
			if (printData.length()< 8) {
				// do nothing.
			}else {
				if (eden.getDescription().compareToIgnoreCase("Birth CCYY")==0){
					printData =printData.substring(4,8);
				} else if (eden.getDescription().compareToIgnoreCase("Birth MM")==0){
					printData =printData.substring(0,2);
				} else if (eden.getDescription().compareToIgnoreCase("Birth DD")==0){
					printData =printData.substring(2,4);
				} 
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("DeathDateKey") == 0){
			if (printData.length()< 8) {
				// do nothing.
			}else {
				if (eden.getDescription().compareToIgnoreCase("Death CCYY")==0){
					printData =printData.substring(0,4);
				} else if (eden.getDescription().compareToIgnoreCase("Death MM")==0){
					printData =printData.substring(4,6);
				} else if (eden.getDescription().compareToIgnoreCase("Death DD")==0){
					printData =printData.substring(6,8);
				} 
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("TimeOfDeath") == 0){
			if (printData.length()< 8) {
				// do nothing.
			}else {
			String tmpData = printData;
			String timeH[] = tmpData.split(":");
			String hours = timeH[0];
			String timeM[] = timeH[1].trim().split(" ");
			String minutes = timeM[0];
			String amPm = timeM[1];
			int intHour = 0;
			if (amPm.compareToIgnoreCase("PM")==0){
				intHour = Integer.valueOf(hours)+12;
			}
			if (eden.getDescription().compareToIgnoreCase("Time Of Death HH")==0){
				if (amPm.compareToIgnoreCase("PM")==0){
					printData =String.valueOf(intHour);
				}else {
					printData = hours;
				}
			} else if (eden.getDescription().compareToIgnoreCase("Time Of Death MM")==0){
				printData = minutes;
			} 
			}
		}
		
		if (eden.getFieldName().compareToIgnoreCase("DateCertified") == 0){
			if (printData.length() < 8) {
				// do nothing.
			}else {
				if (eden.getDescription().compareToIgnoreCase("Last Seen CCYY")==0){
					printData =printData.substring(4,8);
				} else if (eden.getDescription().compareToIgnoreCase("Last Seen MM")==0){
					printData =printData.substring(0,2);
				} else if (eden.getDescription().compareToIgnoreCase("Last Seen DD")==0){
					printData =printData.substring(2,4);
				} 
			}
		}
		if (eden.getFieldName().compareToIgnoreCase("DispositionDate") == 0){
			if (printData.length() < 8) {
				// do nothing.
			}else {
				if (eden.getDescription().compareToIgnoreCase("Disposition CCYY")==0){
					printData =printData.substring(4,8);
				} else if (eden.getDescription().compareToIgnoreCase("Disposition MM")==0){
					printData =printData.substring(0,2);
				} else if (eden.getDescription().compareToIgnoreCase("Disposition DD")==0){
					printData =printData.substring(2,4);
				} 
			}
		}
		if (eden.getFieldName().compareToIgnoreCase("InjuryDate") == 0){
			if (printData.length() < 8) {
				// do nothing.
			}else {
				if (eden.getDescription().compareToIgnoreCase("Injury CCYY")==0){
					printData =printData.substring(4,8);
				} else if (eden.getDescription().compareToIgnoreCase("Injury MM")==0){
					printData =printData.substring(0,2);
				} else if (eden.getDescription().compareToIgnoreCase("Injury DD")==0){
					printData =printData.substring(2,4);
				} 
			}
		}
		if (eden.getFieldName().compareToIgnoreCase("InjuryTime") == 0){
			if (printData.length() < 8) {
				// do nothing.
			}else {
				String tmpData = printData;
				String timeH[] = tmpData.split(":");
				String hours = timeH[0];
				String timeM[] = timeH[1].trim().split(" ");
				String minutes = timeM[0];
				String amPm = timeM[1];
				int intHour = 0;
				if (amPm.compareToIgnoreCase("PM")==0){
					intHour = Integer.valueOf(hours)+12;
				}
				if (eden.getDescription().compareToIgnoreCase("Injury Time HH")==0){
					if (amPm.compareToIgnoreCase("PM")==0){
						printData =String.valueOf(intHour);
					}else {
						printData = hours;
					}
				} else if (eden.getDescription().compareToIgnoreCase("Injury Time MM")==0){
					printData = minutes;
				} 
			}
		}
		if (eden.getFieldName().compareToIgnoreCase("CauseInterval1") == 0){
			String tmpData = printData;
			String intervals[] = tmpData.split(" ");
			String intervalValue = "";
			String unit = "";
			if (intervals.length>1){
				unit = intervals[intervals.length-1];
				for (int i = 0; i < (intervals.length-1);i++){
					intervalValue =  intervalValue + intervals[i] ;
				}
			} else {
				intervalValue =  intervals[0];
			}
			if (eden.getDescription().compareToIgnoreCase("Immediate Interval")==0){
				printData = intervalValue;
			}else if (eden.getDescription().compareToIgnoreCase("Immediate Interval Unit")==0){
				printData = unit;
			}
		}
		if ((eden.getFieldName().compareToIgnoreCase("CauseInterval2") == 0) ||
				(eden.getFieldName().compareToIgnoreCase("CauseInterval3")==0) ||
				(eden.getFieldName().compareToIgnoreCase("CauseInterval4")==0)		)
			{
			String tmpData = printData;
			String intervals[] = tmpData.split(" ");
			String intervalValue = "";
			String unit = "";
			if (intervals.length>1){
				unit = intervals[intervals.length-1];
				for (int i = 0; i < (intervals.length-1);i++){
					intervalValue =  intervalValue + intervals[i] ;
				}
			} else {
				intervalValue =  intervals[0];
			}
			if (eden.getDescription().compareToIgnoreCase("Immediate Interval")==0){
				printData = intervalValue;
			}else if (eden.getDescription().compareToIgnoreCase("Immediate Interval Unit")==0){
				printData = unit;
			}
		}
		
		int blankLength = eden.getLength()-printData.length();
		for(int i = 0; i < blankLength; i++){
//			printData = " "+printData;
			printData = printData+" ";
		}
		
		
		return printData;
	}
	private String convertNumeric(EdenDTO eden, Object data) {
		String printData = "";
		
		printData = data.toString().trim();
		Integer intData = (Integer) data;
		int blankLength = eden.getLength()-printData.length();
		if (intData > 0){
			for(int i = 0; i < blankLength; i++){
				printData = "0"+printData;
			}
		}else {
			printData = "";
			for(int i = 0; i < eden.getLength(); i++){
				printData = " "+printData;
			}
		}
		
		
		return printData;
	}
	
}
