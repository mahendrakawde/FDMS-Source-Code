package fdms.info;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;

import fdms.ui.struts.form.ServicesVisitationForm;

public class ServicesVisitationInformation {

	public ServicesVisitationForm[] getServicesVisitationForms(ReadWriteExcel excel, XSSFRow row, int startRow, int val) {
		ServicesVisitationForm[] form = new ServicesVisitationForm[1];
		
		try {
			form[0] = new ServicesVisitationForm();
			form[0].setVitalsMasterKey((int) NumberUtils.toDouble(excel.getCellValue(row, 87)));
			form[0].setDirective("addVisitation");
			
			form[0].setDate(excel.getCellValue(row, 468 + val + (startRow * 13)));
			form[0].setPlace(excel.getCellValue(row, 469 + val + (startRow * 13)));
			form[0].setRoom(excel.getCellValue(row, 470 + val + (startRow * 13)));
			form[0].setAddress(excel.getCellValue(row, 471 + val + (startRow * 13)));
			form[0].setAddress2(excel.getCellValue(row, 472 + val + (startRow * 13)));
			form[0].setCity(excel.getCellValue(row, 473 + val + (startRow * 13)));
			form[0].setState(excel.getCellValue(row, 474 + val + (startRow * 13)));
			form[0].setZip(excel.getCellValue(row, 475 + val + (startRow * 13)));
			form[0].setStartTime(excel.getCellValue(row, 476 + val + (startRow * 13)));
			form[0].setEndTime(excel.getCellValue(row, 477 + val + (startRow * 13)));
			form[0].setPrivateVisitation(excel.getCellValue(row, 478 + val + (startRow * 13)));
			form[0].setNotes(excel.getCellValue(row, 479 + val + (startRow * 13)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return form;
	}
}
