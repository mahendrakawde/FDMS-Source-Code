package fdms.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fdms.ui.struts.form.ServicesForm;

public class ServicesInformation {

	public ServicesInformation(String templateName) throws Exception {
		//super(templateName);
	}

	public List<ServicesForm> getVitalForms() {
		List<ServicesForm> serviceForms = new ArrayList<ServicesForm>();
		XSSFWorkbook wb=null;//edit Rupali
		try{
			
			XSSFSheet sheet = wb.getSheet("CaseInformation");       // first sheet

			// read data from excel rows
			ServicesForm form;
			for(int startRow = 2; startRow <= sheet.getLastRowNum(); startRow++ ) {
				XSSFRow row = sheet.getRow(startRow);
				form = new ServicesForm();
			
				// form.set((int) NumberUtils.toDouble(getCellValue(row, 87))); //getting data from Session in ProcessService
				form.setDirective("change");
				
				//Date and Time of Service		
				form.setTimeOfService(getCellValue(row, 393));
				form.setDateOfService(getCellValue(row, 394));
				form.setDayOfService(getCellValue(row, 395));
				
				//Additional Service			
				form.setAddnlService(getCellValue(row, 396));
				form.setAddnlTimeOfService(getCellValue(row, 397));
				form.setAddnlDateOfService(getCellValue(row, 398));
				form.setAddnlDayOfService(getCellValue(row, 399));
				
				// Place of Service
				form.setPlaceOfService(getCellValue(row, 400));
				form.setStreetOfService(getCellValue(row, 401));
				form.setCityOfService(getCellValue(row, 402));
				form.setStateOfService(getCellValue(row, 403));
				form.setPhoneOfService(getCellValue(row, 404));

				
				//Additional Place of Service				
				form.setAddnlPlaceOfService(getCellValue(row, 405));
				form.setAddnlStreetOfService(getCellValue(row, 406));
				form.setAddnlCityOfService(getCellValue(row, 407));
				form.setAddnlStateOfService(getCellValue(row, 408));
				form.setAddnlPhoneOfService(getCellValue(row, 409));

				//Cemetery
				form.setCemeteryName(getCellValue(row, 410));
				form.setCemeteryStreet(getCellValue(row, 411));
				form.setCemeteryCity(getCellValue(row, 412));
				form.setCemeteryState(getCellValue(row, 413));
				form.setCemeteryZipCode(getCellValue(row, 414));
				form.setCemeteryCounty(getCellValue(row, 415));
				form.setCemeteryPhone(getCellValue(row, 416));
				form.setCemeteryVault(getCellValue(row, 417));
				form.setCemeterySection(getCellValue(row, 418));
				form.setCemeteryBlockNumber(getCellValue(row, 419));
				form.setCemeteryLot(getCellValue(row, 420));
				form.setCemeteryGraveNumber(getCellValue(row, 421));
				form.setCemeteryTent(getCellValues(row, 422));//edit Rupali
				form.setCemeterySetAndSeal(getCellValues(row, 423));//edit Rupali
				form.setCemeteryOpen(getCellValues(row, 424));//edit Rupali
				form.setCemeteryTime(getCellValue(row, 425));
				form.setCemeteryDisposition(getCellValue(row, 426));
				
				//Crematory Information						
				form.setCremationDateOfService(getCellValue(row, 427));
				form.setCrematoryName(getCellValue(row, 428));
				form.setCrematoryStreet(getCellValue(row, 429));
				form.setCrematoryCity(getCellValue(row, 430));
				form.setCrematoryState(getCellValue(row, 431));
				form.setCrematoryCounty(getCellValue(row, 432));
				form.setCrematoryZip(getCellValue(row, 433));
				
				form.setCemeteryStaffAndAuto(getCellValue(row, 434));
				form.setDonations(getCellValue(row, 435));
				form.setSpecialInstructions(getCellValue(row, 436));
				
				//Thank You Cards	
				form.setCardsNumber(getCellValue(row, 437));
				form.setCardsStyle(getCellValue(row, 438));
				
				//Church
				form.setChurchName(getCellValue(row, 439));
				form.setChurchAddress(getCellValue(row, 440));
				form.setChurchAddress2(getCellValue(row, 441));
				form.setChurchPhone(getCellValue(row, 442));
								
				//Minister(s) / Priest(s)									
				form.setMinister1(getCellValue(row, 443));
				form.setMinister1Email(getCellValue(row, 444));
				form.setMinister2(getCellValue(row, 445));
				form.setMinister2Email(getCellValue(row, 446));
				form.setOrganist(getCellValue(row, 448));
				form.setSoloist(getCellValue(row, 449));
				form.setJewelry(getCellValue(row, 450));
				form.setSongs(getCellValue(row, 451));
				form.setHairStyle(getCellValue(row, 452));
				
				
				//Flower Arrangements			
				form.setFlowerArrangementsDescription(getCellValue(row, 453));
				form.setFlowerSupplierAddressAndPhone(getCellValue(row, 454));
				form.setFlowerPickup(getCellValues(row, 456));//edit Rupali
				
				form.setVisitationInformation(getCellValue(row, 457));

				//Pickup Family	
				form.setPickupFamilyAt(getCellValue(row, 458));
				form.setPickupFamilyTime(getCellValue(row, 459));
				
				//Certified Copies	
				form.setCertifiedCopies(getCellValue(row, 460));
				form.setCertifiedSendTo(getCellValue(row, 461));
				
				form.setSpecialServices(getCellValue(row, 462));

				//Memorial Folders / Holy Cards	
				form.setMemorialsNumber(getCellValue(row, 463));
				form.setMemorialStyle(getCellValue(row, 464));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			return serviceForms;
		}

	private String getCellValue(XSSFRow row, int i) {
		// TODO Auto-generated method stub //edit Rupali
		return "";
	}
	
	private Boolean getCellValues(XSSFRow row, int i) {
		// TODO Auto-generated method stub //edit Rupali
		return false;
	}
}
