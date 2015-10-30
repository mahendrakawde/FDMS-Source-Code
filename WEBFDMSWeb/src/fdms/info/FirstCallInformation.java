package fdms.info;

import org.apache.poi.xssf.usermodel.XSSFRow;

import fdms.ui.struts.form.FirstCallInformationForm;

public class FirstCallInformation {
	public FirstCallInformationForm getAtNeedCases(ReadWriteExcel excel, XSSFRow row){
		FirstCallInformationForm form = null;
		try{
			// read data from excel rows
			String vitalId = excel.getCellValue(row, 87);
			
			if(!vitalId.equals("") && !vitalId.equals("0")) {
				return null;
			}
				
			form = new FirstCallInformationForm();
			form.setDirective("add");
			
			form.setPreneedDate(excel.getCellValue(row, 0));
			form.setContractNumber(excel.getCellValue(row, 1));
			form.setCaseNumber(excel.getCellValue(row, 2));
			form.setArrangeDate(excel.getCellValue(row, 3));
			form.setTime(excel.getCellValue(row, 4));
			form.setChapel(excel.getChapelLocations().get(excel.getCellValue(row, 5)));
			form.setFacility(excel.getCellValue(row, 6));
			form.setDirector(excel.getDirectors().get(excel.getCellValue(row, 7)));
			form.setSource(excel.getCellValue(row, 8));
			form.setEmbalming(excel.getCellValue(row, 9));
			
			form.setPrefix(excel.getCellValue(row, 10));
			form.setFirstName(excel.getCellValue(row, 11));
			form.setMiddleName(excel.getCellValue(row, 12));
			form.setLastName(excel.getCellValue(row, 13));
			form.setSuffix(excel.getCellValue(row, 14));
			form.setMaidenName(excel.getCellValue(row, 15));
			form.setMemorialName(excel.getCellValue(row, 16));
			form.setPlaceDeath(excel.getCellValue(row, 17));
			form.setLocationDeceased(excel.getCellValue(row, 18));
			form.setPlaceDeathCity(excel.getCellValue(row, 19));
			form.setPlaceDeathState(excel.getCellValue(row, 20));
			form.setPlaceDeathZip(excel.getCellValue(row, 21));
			form.setLocDeathLicenseNumber(excel.getCellValue(row, 22));
			form.setBirthDate(excel.getCellValue(row, 23));
			form.setDeathDate(excel.getCellValue(row, 24));
			form.setAge(excel.getCellValue(row, 25));
			form.setServiceDate(excel.getCellValue(row, 26));
			form.setDispDate(excel.getCellValue(row, 27));
			
			form.setChapelName(excel.getCellValue(row, 28));
			form.setChapelStreet(excel.getCellValue(row, 29));
			form.setChapelCity(excel.getCellValue(row, 30));
			form.setChapelState(excel.getCellValue(row, 31));
			form.setChapelZip(excel.getCellValue(row, 32));
			form.setChapelPhone(excel.getCellValue(row, 33));
			form.setChapelLicense(excel.getCellValue(row, 34));
			
			form.setFacilityName(excel.getCellValue(row, 35));
			form.setFacilityStreet(excel.getCellValue(row, 36));
			form.setFacilityCity(excel.getCellValue(row, 37));
			form.setFacilityState(excel.getCellValue(row, 38));
			form.setFacilityZip(excel.getCellValue(row, 39));
			form.setFacilityPhone(excel.getCellValue(row, 40));
			form.setFacilityLicense(excel.getCellValue(row, 41));
			
			form.setInformantSalutation(excel.getCellValue(row, 42));
			form.setInformantFirst(excel.getCellValue(row, 43));
			form.setInformantMiddle(excel.getCellValue(row, 44));
			form.setInformantLast(excel.getCellValue(row, 45));
			form.setInformantRelation(excel.getCellValue(row, 46));
			form.setInformantStreet(excel.getCellValue(row, 47));
			form.setInformantStreet2(excel.getCellValue(row, 48));
			form.setInformantStreet3(excel.getCellValue(row, 49));
			form.setInformantCity(excel.getCellValue(row, 50));
			form.setInformantState(excel.getCellValue(row, 51));
			form.setInformantZip(excel.getCellValue(row, 52));
			form.setInformantPhone(excel.getCellValue(row, 53));
			form.setInformantCellPhone(excel.getCellValue(row, 54));
			form.setInformantEmail(excel.getCellValue(row, 55));
			form.setInformantPreferCommunicate(excel.getCellValue(row, 56));
			form.setInformantBillToParty(excel.getCellValue(row, 57).equals("Y") ? true : false);
			
			form.setNextKinSalutation(excel.getCellValue(row, 58));
			form.setNextKinFirst(excel.getCellValue(row, 59));
			form.setNextKinMiddle(excel.getCellValue(row, 60));
			form.setNextKinLast(excel.getCellValue(row, 61));
			form.setNextKinRelation(excel.getCellValue(row, 62));
			form.setNextKinStreet(excel.getCellValue(row, 63));
			form.setNextKinStreet2(excel.getCellValue(row, 64));
			form.setNextKinStreet3(excel.getCellValue(row, 65));
			form.setNextKinCity(excel.getCellValue(row, 66));
			form.setNextKinState(excel.getCellValue(row, 67));
			form.setNextKinZip(excel.getCellValue(row, 68));
			form.setNextKinPhone(excel.getCellValue(row, 69));
			form.setNextKinCellPhone(excel.getCellValue(row, 70));
			form.setNextKinEmail(excel.getCellValue(row, 71));
			form.setNextKinPreferCommunicate(excel.getCellValue(row, 72));
			
			form.setExecutorFirstName(excel.getCellValue(row, 73));
			form.setExecutorLastName(excel.getCellValue(row, 74));
			form.setExecutorRelation(excel.getCellValue(row, 75));
			form.setExecutorStreet(excel.getCellValue(row, 76));
			form.setExecutorStreet2(excel.getCellValue(row, 77));
			form.setExecutorStreet3(excel.getCellValue(row, 78));
			form.setExecutorCity(excel.getCellValue(row, 79));
			form.setExecutorState(excel.getCellValue(row, 80));
			form.setExecutorZip(excel.getCellValue(row, 81));
			form.setExecutorPhone(excel.getCellValue(row, 82));
			form.setExecutorCellPhone(excel.getCellValue(row, 83));
			form.setExecutorEmail(excel.getCellValue(row, 84));
			
			form.setShippingInfo(excel.getCellValue(row, 85));
			form.setCallInfoNote(excel.getCellValue(row, 86));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return form;
	}
}