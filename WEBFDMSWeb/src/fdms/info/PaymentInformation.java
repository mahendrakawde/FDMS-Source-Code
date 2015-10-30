package fdms.info;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;

import fdms.ui.struts.form.PaymentAddChangeForm;

public class PaymentInformation {

	public PaymentAddChangeForm[] getPaymentAddChangeForms(ReadWriteExcel excel, XSSFRow row, int val) {
		// read data from excel rows
		PaymentAddChangeForm[] form = new PaymentAddChangeForm[15];
		
		try {
			if(excel.getCellValue(row, 92 + val).equals("Y")) {
				
				for(int index = 0; index < 15; index++) {
					form[index] = new PaymentAddChangeForm();
					form[index].setDirective("save=" + (int) NumberUtils.toDouble(excel.getCellValue(row, 87)));
					
					form[index].setPayment(String.valueOf((int) NumberUtils.toDouble(excel.getCellValue(row, 771 + val))));
					form[index].setPaymentSource(excel.getCellValue(row, 772 + val + (index * 9)));
					
					form[index].setAmountPaid(excel.getCellValue(row, 773 + val + (index * 9)));
					form[index].setDateOfDeposit(excel.getCellValue(row, 774 + val + (index * 9)));
					form[index].setDateOfPayment(excel.getCellValue(row, 775 + val + (index * 9)));
					form[index].setReceiptNumber(excel.getCellValue(row, 776 + val + (index * 9)));
					form[index].setManualReceiptNumber(excel.getCellValue(row, 777 + val + (index * 9)));
					form[index].setNonCashAdjustment(excel.getCellValue(row, 778 + val + (index * 9)));
					form[index].setMethodOfPayment(excel.getCellValue(row, 779 + val + (index * 9)));
				}
			} else
				form = null;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return form;
	}
}
