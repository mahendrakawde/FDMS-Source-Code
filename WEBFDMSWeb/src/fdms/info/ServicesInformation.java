package fdms.info;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;

import fdms.ui.struts.form.ServicesForm;

public class ServicesInformation {

	public ServicesForm getServicesForms(ReadWriteExcel excel, XSSFRow row, int val) {
		// read data from excel rows
		ServicesForm form = new ServicesForm();
		
		try {
			//form.set();
			// //getting data from Session in ProcessService
			form.setDirective("change=" + (int) NumberUtils.toDouble(excel.getCellValue(row, 87 + val) + val));

			// Date and Time of Service
			form.setTimeOfService(excel.getCellValue(row,395+ val));
			form.setDateOfService(excel.getCellValue(row,396+ val));
			form.setDayOfService(excel.getCellValue(row,397+ val));
			
			// Additional Service
			form.setAddnlService(excel.getCellValue(row,398+ val));
			form.setAddnlTimeOfService(excel.getCellValue(row,399+ val));
			form.setAddnlDateOfService(excel.getCellValue(row,400+ val));
			form.setAddnlDayOfService(excel.getCellValue(row,401+ val));
			
			// Place of Service
			form.setPlaceOfService(excel.getCellValue(row,402+ val));
			form.setStreetOfService(excel.getCellValue(row,403+ val));
			form.setCityOfService(excel.getCellValue(row,404+ val));
			form.setStateOfService(excel.getCellValue(row,405+ val));
			form.setPhoneOfService(excel.getCellValue(row,406+ val));
			
			// Additional Place of Service
			form.setAddnlPlaceOfService(excel.getCellValue(row,407+ val));
			form.setAddnlStreetOfService(excel.getCellValue(row,408+ val));
			form.setAddnlCityOfService(excel.getCellValue(row,409+ val));
			form.setAddnlStateOfService(excel.getCellValue(row,410+ val));
			form.setAddnlPhoneOfService(excel.getCellValue(row,411+ val));
			
			// Cemetery
			form.setCemeteryName(excel.getCellValue(row,412+ val));
			form.setCemeteryStreet(excel.getCellValue(row,413+ val));
			form.setCemeteryCity(excel.getCellValue(row,414+ val));
			form.setCemeteryState(excel.getCellValue(row,415+ val));
			form.setCemeteryZipCode(excel.getCellValue(row,416+ val));
			form.setCemeteryCounty(excel.getCellValue(row,417+ val));
			form.setCemeteryPhone(excel.getCellValue(row,418+ val));
			form.setCemeteryVault(excel.getCellValue(row,419+ val));
			form.setCemeterySection(excel.getCellValue(row,420+ val));
			form.setCemeteryBlockNumber(excel.getCellValue(row,421+ val));
			form.setCemeteryLot(excel.getCellValue(row,422+ val));
			form.setCemeteryGraveNumber(excel.getCellValue(row,423+ val));
			form.setCemeteryTent(excel.getCellValue(row,424+ val).equals("Y") ? true : false);
			form.setCemeterySetAndSeal(excel.getCellValue(row,425+ val).equals("Y") ? true : false);
			form.setCemeteryOpen(excel.getCellValue(row,426+ val).equals("Y") ? true : false);
			form.setCemeteryTime(excel.getCellValue(row,427+ val));
			form.setCemeteryDisposition(excel.getCellValue(row,428+ val));
			
			// Crematory Information
			form.setCremationDateOfService(excel.getCellValue(row,429+ val));
			form.setCrematoryName(excel.getCellValue(row,430+ val));
			form.setCrematoryStreet(excel.getCellValue(row,431+ val));
			form.setCrematoryCity(excel.getCellValue(row,432+ val));
			form.setCrematoryState(excel.getCellValue(row,433+ val));
			form.setCrematoryCounty(excel.getCellValue(row,434+ val));
			form.setCrematoryZip(excel.getCellValue(row,435+ val));
			
			form.setCemeteryStaffAndAuto(excel.getCellValue(row,436+ val));
			form.setDonations(excel.getCellValue(row,437+ val));
			form.setSpecialInstructions(excel.getCellValue(row,438+ val));
			
			// Thank You Cards
			form.setCardsNumber(excel.getCellValue(row,439+ val));
			form.setCardsStyle(excel.getCellValue(row,440+ val));
			
			// Church
			form.setChurchName(excel.getCellValue(row,441+ val));
			form.setChurchAddress(excel.getCellValue(row,442+ val));
			form.setChurchAddress2(excel.getCellValue(row,443+ val));
			form.setChurchPhone(excel.getCellValue(row,444+ val));
			
			// Minister(s) / Priest(s)
			form.setMinister1(excel.getCellValue(row,445+ val));
			form.setMinister1Email(excel.getCellValue(row,446+ val));
			form.setMinister2(excel.getCellValue(row,447+ val));
			form.setMinister2Email(excel.getCellValue(row,448+ val));
			form.setOrganist(excel.getCellValue(row,449+ val));
			form.setSoloist(excel.getCellValue(row,450+ val));
			form.setJewelry(excel.getCellValue(row,451+ val));
			form.setSongs(excel.getCellValue(row,452+ val));
			form.setHairStyle(excel.getCellValue(row,453+ val));
			form.setRestoration(excel.getCellValue(row,454+ val).equals("Y") ? true : false);
			
			// Flower Arrangements
			form.setFlowerArrangementsDescription(excel.getCellValue(row,455+ val));
			form.setFlowerSupplierAddressAndPhone(excel.getCellValue(row,456+ val));
			form.setFlowerDelivery(excel.getCellValue(row,457+ val).equals("Y") ? true : false);
			form.setFlowerPickup(excel.getCellValue(row,458+ val).equals("Y") ? true : false);
			
			form.setVisitationInformation(excel.getCellValue(row,459+ val));
			
			// Pickup Family
			form.setPickupFamilyAt(excel.getCellValue(row,460+ val));
			form.setPickupFamilyTime(excel.getCellValue(row,461+ val));
			
			// Certified Copies
			form.setCertifiedCopies(excel.getCellValue(row,462+ val));
			form.setCertifiedSendTo(excel.getCellValue(row,463+ val));
			
			form.setSpecialServices(excel.getCellValue(row,464+ val));
			
			// Memorial Folders / Holy Cards
			form.setMemorialsNumber(excel.getCellValue(row,465+ val));
			form.setMemorialStyle(excel.getCellValue(row,466+ val));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return form;
	}
}