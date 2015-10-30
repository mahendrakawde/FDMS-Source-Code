package fdms.info;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;

import fdms.ui.struts.form.PallBearersForm;

public class PallBearerInformation {

	public PallBearersForm getPallBearersForms(ReadWriteExcel excel, XSSFRow row, int val) {
		PallBearersForm form = new PallBearersForm();
		try {
			form.setVitalsId(String.valueOf((int) NumberUtils.toDouble(excel.getCellValue(row, 87 - val))));
			form.setDirective("change");
			
			form.setPallBearer1Name(excel.getCellValue(row, 481 - val));
			form.setPallBearer1Street(excel.getCellValue(row, 482 - val));
			form.setPallBearer1CityStateZip(excel.getCellValue(row, 483 - val));
			
			form.setPallBearer2Name(excel.getCellValue(row, 484 - val));
			form.setPallBearer2Street(excel.getCellValue(row, 485 - val));
			form.setPallBearer2CityStateZip(excel.getCellValue(row, 486 - val));
			
			form.setPallBearer3Name(excel.getCellValue(row, 487 - val));
			form.setPallBearer3Street(excel.getCellValue(row, 488 - val));
			form.setPallBearer3CityStateZip(excel.getCellValue(row, 489 - val));
			
			form.setPallBearer4Name(excel.getCellValue(row, 490 - val));
			form.setPallBearer4Street(excel.getCellValue(row, 491 - val));
			form.setPallBearer4CityStateZip(excel.getCellValue(row, 492 - val));
			
			form.setPallBearer5Name(excel.getCellValue(row, 493 - val));
			form.setPallBearer5Street(excel.getCellValue(row, 494 - val));
			form.setPallBearer5CityStateZip(excel.getCellValue(row, 495 - val));
			
			form.setPallBearer6Name(excel.getCellValue(row, 496 - val));
			form.setPallBearer6Street(excel.getCellValue(row, 497 - val));
			form.setPallBearer6CityStateZip(excel.getCellValue(row, 498 - val));
			
			form.setPallBearer7Name(excel.getCellValue(row, 499 - val));
			form.setPallBearer7Street(excel.getCellValue(row, 500 - val));
			form.setPallBearer7CityStateZip(excel.getCellValue(row, 501 - val));
			
			form.setPallBearer8Name(excel.getCellValue(row, 502 - val));
			form.setPallBearer8Street(excel.getCellValue(row, 503 - val));
			form.setPallBearer8CityStateZip(excel.getCellValue(row, 504 - val));
			
			form.setHonoraryPallBearer1Name(excel.getCellValue(row, 505 - val));
			form.setHonoraryPallBearer1Street(excel.getCellValue(row, 506 - val));
			form.setHonoraryPallBearer1CityStateZip(excel.getCellValue(row, 507 - val));
			
			form.setHonoraryPallBearer2Name(excel.getCellValue(row, 508 - val));
			form.setHonoraryPallBearer2Street(excel.getCellValue(row, 509 - val));
			form.setHonoraryPallBearer2CityStateZip(excel.getCellValue(row, 510 - val));
			
			form.setHonoraryPallBearer3Name(excel.getCellValue(row, 511 - val));
			form.setHonoraryPallBearer3Street(excel.getCellValue(row, 512 - val));
			form.setHonoraryPallBearer3CityStateZip(excel.getCellValue(row, 513 - val));
			
			form.setHonoraryPallBearer4Name(excel.getCellValue(row, 514 - val));
			form.setHonoraryPallBearer4Street(excel.getCellValue(row, 515 - val));
			form.setHonoraryPallBearer4CityStateZip(excel.getCellValue(row, 516 - val));
			
			form.setHonoraryPallBearer5Name(excel.getCellValue(row, 517 - val));
			form.setHonoraryPallBearer5Street(excel.getCellValue(row, 518 - val));
			form.setHonoraryPallBearer5CityStateZip(excel.getCellValue(row, 519 - val));
			
			form.setHonoraryPallBearer6Name(excel.getCellValue(row, 520 - val));
			form.setHonoraryPallBearer6Street(excel.getCellValue(row, 521 - val));
			form.setHonoraryPallBearer6CityStateZip(excel.getCellValue(row, 522 - val));
			
			form.setHonoraryPallBearer7Name(excel.getCellValue(row, 523 - val));
			form.setHonoraryPallBearer7Street(excel.getCellValue(row, 524 - val));
			form.setHonoraryPallBearer7CityStateZip(excel.getCellValue(row, 525 - val));
			
			form.setHonoraryPallBearer8Name(excel.getCellValue(row, 526 - val));
			form.setHonoraryPallBearer8Street(excel.getCellValue(row, 527 - val));
			form.setHonoraryPallBearer8CityStateZip(excel.getCellValue(row, 528 - val));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return form;
	}
}
