package fdms.info;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;

import fdms.ui.struts.form.VitalsForm;

public class VitalsInformation {
	public VitalsForm getVitalForms(ReadWriteExcel excel, XSSFRow row, int val) {
		// read data from excel rows
		VitalsForm form = new VitalsForm();
		
		try{
			//ServicesForm form1 = new ServicesForm();
			//form1.setTimeOfService(getCellValue(row, 393 + val));
			
			form.setVitalsid((int) NumberUtils.toDouble(excel.getCellValue(row, 87 + val)));
			form.setDirective("change");
			form.setIssueDate("\\N");
			
			form.setIdentificationMarks(excel.getCellValue(row,234+ val));
			form.setDeceasedFirstName(excel.getCellValue(row,235+ val));
			form.setDeceasedMiddleName(excel.getCellValue(row,236+ val));
			form.setDeceasedLastName(excel.getCellValue(row,237+ val));
			form.setDeceasedMaiden(excel.getCellValue(row,238+ val));
			form.setDecPrefix(excel.getCellValue(row,239+ val));
			form.setDeceasedSuffix(excel.getCellValue(row,240+ val));
			form.setAliasFirstName(excel.getCellValue(row,241+ val));
			form.setAliasMiddleName(excel.getCellValue(row,242+ val));
			form.setAliasLastName(excel.getCellValue(row,243+ val));
			form.setAliasPrefix(excel.getCellValue(row,244+ val));
			form.setAliasSuffix(excel.getCellValue(row,245+ val));
			form.setSex(excel.getCellValue(row,246+ val));
			
			form.setDateOfBirth(excel.getCellValue(row,247+ val));
			form.setDateOfDeath(excel.getCellValue(row,248+ val));
			form.setAgeYears(excel.getCellValue(row,249+ val));
			form.setAgeMonths(excel.getCellValue(row,250+ val));
			form.setAgeDays(excel.getCellValue(row,251+ val));
			form.setAgeHours(excel.getCellValue(row,252+ val));
			form.setAgeMinutes(excel.getCellValue(row,253+ val));
			
			form.setLocationOfDeath(excel.getCellValue(row,254+ val));
			form.setLocDeathLicenseNumber(excel.getCellValue(row,255+ val));
			form.setCityOfDeath(excel.getCellValue(row,256+ val));
			form.setCountyOfDeath(excel.getCellValue(row,257+ val));
			form.setDeathInsideCity(excel.getCellValue(row,258+ val));
			form.setLocOfDeathCVT(excel.getCellValue(row,259+ val));
			form.setLoctnOfDeathZip(""); // Need to check
			form.setStateOfDeath(""); // Need to check
			
			form.setDeceasedStreet(excel.getCellValue(row,260+ val));
			form.setLocalityInsideCity(excel.getCellValue(row,261+ val).equals("Y") ? true : false);
			form.setLocalityInsideVillage(excel.getCellValue(row,262+ val).equals("Y") ? true : false);
			form.setLocalityInsideTwp(excel.getCellValue(row,263+ val).equals("Y") ? true : false);
			form.setLocalityUnincorporated(excel.getCellValue(row,264+ val).equals("Y") ? true : false);
			form.setLocalityUnknown(excel.getCellValue(row,265+ val).equals("Y") ? true : false);
			form.setDeceasedCity2(excel.getCellValue(row,266+ val));
			form.setDeceasedCounty(excel.getCellValue(row,267+ val));
			form.setDeceasedState(excel.getCellValue(row,268+ val));
			form.setDeceasedZipCode(excel.getCellValue(row,269+ val));
			form.setDeceasedCountry(excel.getCellValue(row,270+ val));
			form.setDeceasedResLengthTime(excel.getCellValue(row,271+ val));
			
			form.setBirthplaceCity(excel.getCellValue(row,272+ val));
			form.setBirthplaceState(excel.getCellValue(row,273+ val));
			form.setCountyOfBirth(excel.getCellValue(row,274+ val));
			form.setBirthplaceCountry(excel.getCellValue(row,275+ val));
			form.setSocialSecurityNumber(excel.getCellValue(row,276+ val));
			form.setDecEducation(excel.getCellValue(row,277+ val));
			form.setDeceasedElementaryEducation(excel.getCellValue(row,278+ val));
			form.setDeceasedCollegeEducation(excel.getCellValue(row,279+ val));
			
			form.setRace(excel.getCellValue(row,280+ val));
			form.setTribalMember(excel.getCellValue(row,281+ val));
			form.setTribalName(excel.getCellValue(row,282+ val));
			form.setHispanic(excel.getCellValue(row,283+ val));
			form.setAncestry(excel.getCellValue(row,284+ val));
			form.setCitizenship(excel.getCellValue(row,285+ val));
			
			form.setVeteran(excel.getCellValue(row,286+ val));
			form.setVeteranDateEntered(excel.getCellValue(row,287+ val));
			form.setVeteranDateSeparated(excel.getCellValue(row,288+ val));
			form.setDeceasedOccupation(excel.getCellValue(row,289+ val));
			form.setDeceasedKindBusinessOrIndustry(excel.getCellValue(row,290+ val));
			form.setEmployer(excel.getCellValue(row,291+ val));
			
			form.setMaritalStatus(excel.getCellValue(row,292+ val));
			form.setSurvivingSpouse1(excel.getCellValue(row,293+ val));
			form.setSurvivingSpouse2(excel.getCellValue(row,294+ val));
			form.setSurvivingSpouse3(excel.getCellValue(row,295+ val));
			form.setSurvivingSpouseState(""); // Need to check
			form.setSurvivingSpouseZip(""); // Need to check
			
			form.setFatherFirstName(excel.getCellValue(row,296+ val));
			form.setFatherMiddleName(excel.getCellValue(row,297+ val));
			form.setFatherLastName(excel.getCellValue(row,298+ val));
			form.setFatherBirthCity(excel.getCellValue(row,299+ val));
			form.setFatherBirthState(excel.getCellValue(row,300+ val));
			
			form.setMotherFirstName(excel.getCellValue(row,301+ val));
			form.setMotherMiddleName(excel.getCellValue(row,302+ val));
			form.setMotherLastName(excel.getCellValue(row,303+ val));
			form.setMotherMaidenName(excel.getCellValue(row,304+ val));
			form.setMotherBirthCity(excel.getCellValue(row,305+ val));
			form.setMotherBirthState(excel.getCellValue(row,306+ val));
			
			form.setInformantFirstName(excel.getCellValue(row,307+ val));
			form.setInformantMiddleName(excel.getCellValue(row,308+ val));
			form.setInformantLastName(excel.getCellValue(row,309+ val));
			form.setInformantRelation(excel.getCellValue(row,310+ val));
			form.setInformantStreet(excel.getCellValue(row,311+ val));
			form.setInformantCity(excel.getCellValue(row,312+ val));
			form.setInformantState(excel.getCellValue(row,313+ val));
			form.setInformantZipCode(excel.getCellValue(row,314+ val));
			
			form.setDateOfNotifyToDirector(excel.getCellValue(row,315+ val));
			form.setTimeOfNotifyToDirectory(excel.getCellValue(row,316+ val)); // 10:20 AM
			form.setFacilityLicenseNumber(excel.getCellValue(row,317+ val));
			form.setDirectorID(excel.getDirectors().get(excel.getCellValue(row,318+ val)));
			form.setEmbalmerID(excel.getCellValue(row,319+ val));
			form.setFacilityName(excel.getCellValue(row,320+ val));
			form.setFacilityStreet(excel.getCellValue(row,321+ val));
			form.setFacilityCity(excel.getCellValue(row,322+ val));
			form.setFacilityState(excel.getCellValue(row,323+ val));
			form.setFacilityZipCode(excel.getCellValue(row,324+ val));
			form.setFacilityPhone(excel.getCellValue(row,325+ val));
			
			form.setDisposition(excel.getCellValue(row,326+ val));
			form.setDispositionPlace(excel.getCellValue(row,327+ val));
			form.setDispositionPlaceType(excel.getCellValue(row,328+ val));
			form.setDispositionStreet(excel.getCellValue(row,329+ val));
			form.setDispositionCity(excel.getCellValue(row,330+ val));
			form.setDispositionState(excel.getCellValue(row,331+ val));
			form.setDispositionCounty(excel.getCellValue(row,332+ val));
			form.setDispositionZipCode(excel.getCellValue(row,333+ val));
			form.setDateEmbalmed(excel.getCellValue(row,334+ val));
			form.setDateOfDisposition(excel.getCellValue(row,335+ val));
			form.setAuthorizingCoroner(excel.getCellValue(row,336+ val));
			
			form.setNotificationOfExaminerRequired(excel.getCellValue(row,337+ val));
			form.setMedicalExaminerBox1(excel.getCellValue(row,338+ val).equals("Y") ? true : false);
			form.setCertifierDateSigned(excel.getCellValue(row,339+ val));
			form.setMedicalExaminerBox2(excel.getCellValue(row,340+ val).equals("Y") ? true : false);
			form.setMedicalExaminerDateSigned(excel.getCellValue(row,341+ val));
			form.setCompletedCauseOfDeathDoctorLicense(excel.getCellValue(row,342+ val));
			form.setNpCheckBox1(excel.getCellValue(row,343+ val).equals("Y") ? true : false);
			form.setNpDateSigned(excel.getCellValue(row,344+ val));
			form.setNpLicenseNumber(excel.getCellValue(row,345+ val));
			
			form.setTimeOfDeath(excel.getCellValue(row,346+ val));
			form.setMedicalExaminerDeathDate(excel.getCellValue(row,347+ val));
			form.setTimeOfDeath2(excel.getCellValue(row,348+ val));
			form.setReferredToMedicalExaminer(excel.getCellValue(row,349+ val));
			
			form.setActualPlaceDeath(excel.getCellValue(row,350+ val));
			form.setInpatient(excel.getCellValue(row,351+ val));
			form.setMedicalExaminerCaseNumber(excel.getCellValue(row,352+ val));
			form.setAttendingPhysician(excel.getCellValue(row,353+ val));
			
			form.setCompletedCauseOfDeathDoctorName(excel.getCellValue(row,354+ val));
			form.setCompletedCauseOfDeathDoctorStreet(excel.getCellValue(row,355+ val));
			form.setCompletedCauseOfDeathDoctorCity(excel.getCellValue(row,356+ val));
			form.setCompletedCauseOfDeathDoctorState(excel.getCellValue(row,357+ val));
			form.setCompletedCauseOfDeathDoctorZip(excel.getCellValue(row,358+ val));
			form.setCompletedCauseOfDeathDoctorPhone(excel.getCellValue(row,359+ val));
			form.setPhysicianMD(excel.getCellValue(row,360+ val).equals("Y") ? true : false);
			form.setPhysicianME(excel.getCellValue(row,361+ val).equals("Y") ? true : false);
			form.setPhysicianDO(excel.getCellValue(row,362+ val).equals("Y") ? true : false);
			form.setPhysicianHO(excel.getCellValue(row,363+ val).equals("Y") ? true : false);
			form.setPhysDateFrom(excel.getCellValue(row,364+ val));
			form.setPhysDateTo(excel.getCellValue(row,365+ val));
			form.setPhysDateLast(excel.getCellValue(row,366+ val));
			form.setPhysViewedYesNo(excel.getCellValue(row,367+ val));
			form.setRegistrarFileDate(excel.getCellValue(row,368+ val));
			
			form.setCause1(excel.getCellValue(row,369+ val));
			form.setInterval1(excel.getCellValue(row,370+ val));
			form.setCause2(excel.getCellValue(row,371+ val));
			form.setInterval2(excel.getCellValue(row,372+ val));
			form.setCause3(excel.getCellValue(row,373+ val));
			form.setInterval3(excel.getCellValue(row,374+ val));
			form.setCause4(excel.getCellValue(row,375+ val));
			form.setInterval4(excel.getCellValue(row,376+ val));
			form.setTobaccoUser(excel.getCellValue(row,377+ val));
			form.setPregnantAtDeath(excel.getCellValue(row,378+ val));
			
			form.setOtherCondition(excel.getCellValue(row,379+ val));
			form.setMedicalExaminerAccidentSuicide(excel.getCellValue(row,380+ val));
			form.setBodyFoundMore24Hr(excel.getCellValue(row,381+ val));
			form.setAutopsy(excel.getCellValue(row,382+ val));
			form.setFindings(excel.getCellValue(row,383+ val));
			form.setHospiceStatus(excel.getCellValue(row,384+ val));
			
			form.setMedicalExaminerInjuryDate(excel.getCellValue(row,385+ val));
			form.setMedicalExaminerInjuryTime(excel.getCellValue(row,386+ val));
			form.setMedicalExaminerInjuryDescription(excel.getCellValue(row,387+ val));
			form.setMedicalExaminerInjuryAtWork(excel.getCellValue(row,388+ val));
			form.setMedicalExaminerInjuryPlace(excel.getCellValue(row,389+ val));
			form.setMedicalExaminerInjuryTransportation(excel.getCellValue(row,390+ val));
			form.setMedicalExaminerInjuryStreet(excel.getCellValue(row,391+ val));
			form.setMedicalExaminerInjuryCity(excel.getCellValue(row,392+ val));
			form.setMedicalExaminerInjuryState(excel.getCellValue(row,393+ val));

			form.setMedicalExaminerInjuryZipCode(""); // Need to check				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return form;
	}
}
