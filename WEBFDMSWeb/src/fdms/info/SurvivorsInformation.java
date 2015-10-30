package fdms.info;

import java.util.ArrayList;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.aldorsolutions.webfdms.beans.DbSurvivor;

import fdms.ui.struts.form.SurvivorsForm;

public class SurvivorsInformation {
	public SurvivorsForm[] getSurvivorsForms(ReadWriteExcel excel, XSSFRow row, int val) {
		// read data from excel rows
		SurvivorsForm[] form = new SurvivorsForm[20];
		
		for(int index = 0; index < 20; index++) {
			form[index] = new SurvivorsForm();
			
			form[index].setSubmitbutton("save=" + (int) NumberUtils.toDouble(excel.getCellValue(row, 87 + val)));
			form[index].setRelativesList(new ArrayList());
			
			DbSurvivor survivor = new DbSurvivor();
			survivor.setCSurvivorFName(excel.getCellValue(row, 530 + val + (index * 12)));
			survivor.setCSurvivorMName(excel.getCellValue(row, 531 + val + (index * 12)));
			survivor.setCSurvivorLName(excel.getCellValue(row, 532 + val + (index * 12)));

			survivor.setCSurvivorRelated(excel.getCellValue(row, 533 + val + (index * 12)));
			survivor.setCSurvivorLiving(excel.getCellValue(row, 534 + val + (index * 12)));
			survivor.setCPreferConmunicate(excel.getCellValue(row, 535 + val + (index * 12)));
			survivor.setCSurvivorEmail(excel.getCellValue(row, 536 + val + (index * 12)));
			survivor.setCSurvivorPhone(excel.getCellValue(row, 537 + val + (index * 12)));
			
			survivor.setCSurvivorAddr(excel.getCellValue(row, 538 + val + (index * 12)));
			survivor.setCSurvivorCity(excel.getCellValue(row, 539 + val + (index * 12)));
			survivor.setCSurvivorState(excel.getCellValue(row, 540 + val + (index * 12)));
			survivor.setCSurvivorZip(excel.getCellValue(row, 541 + val + (index * 12)));

			form[index].getRelativesList().add(survivor);
		}
			
		return form;
	}
}
