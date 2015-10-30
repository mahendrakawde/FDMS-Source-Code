package fdms.info;

import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;

import fdms.ui.struts.form.FinancialSelectPackagesForm;

public class FinancialInformation {

	private FinancialSuper financeSuper = null;
	
	public FinancialSuper readInfoFromExcel(ReadWriteExcel excel, XSSFRow data, int val) {
		try {
			String[] serviceListValue;
			String[] invItemListValue;
			String[] packageListValue;
			
			financeSuper = new FinancialSuper();
			try {
				financeSuper.getFinancialForm().setVitalsId((int) NumberUtils.toDouble(excel.getCellValue(data, 87 + val)));
				financeSuper.getFinancialForm().setShowFinancingSection(true);

				financeSuper.getFinancialForm().setApplyFinanceCharges((excel.getCellValue(data, 88 + val).equals("Y")) ? true : false);
				financeSuper.getFinancialForm().setInterestRate(excel.getCellValue(data, 89 + val));
				financeSuper.getFinancialForm().setInterestFromDate(excel.getCellValue(data, 90 + val));
				financeSuper.getFinancialForm().setCustomerName(excel.getCellValue(data, 91 + val));
				financeSuper.getFinancialForm().setSendToAccounting(excel.getCellValue(data, 92 + val).equals("Y") ? true : false);
				
				financeSuper.getFinancialForm().setSaleDate(excel.getCellValue(data, 93 + val));
				financeSuper.getFinancialForm().setDueDate(excel.getCellValue(data, 94 + val));
				financeSuper.getFinancialForm().setStmtDate(excel.getCellValue(data, 95 + val));
				
				financeSuper.getFinancialForm().setAmountPaid(excel.getCellValue(data, 96 + val));
				financeSuper.getFinancialForm().setPaymentSource(excel.getCellValue(data, 97 + val));
				financeSuper.getFinancialForm().setDateOfPayment(excel.getCellValue(data, 98 + val));
				financeSuper.getFinancialForm().setDateOfDeposit(excel.getCellValue(data, 99 + val));
				financeSuper.getFinancialForm().setReceiptNumber(excel.getCellValue(data, 100 + val));
				financeSuper.getFinancialForm().setManualReceiptNumber(excel.getCellValue(data, 101 + val));
				financeSuper.getFinancialForm().setNonCashAdjustment(excel.getCellValue(data, 102 + val));
				financeSuper.getFinancialForm().setMethodOfPayment(excel.getCellValue(data, 103 + val));
				
				financeSuper.getFinancialForm().setSalesDescCDID(excel.getSalesType().get(excel.getCellValue(data, 104 + val)));
				financeSuper.getFinancialForm().setSaleType(excel.getCellValue(data, 105 + val));
				financeSuper.getFinancialForm().setSource(excel.getCellValue(data, 106 + val));
				financeSuper.getFinancialForm().setDisposition(excel.getCellValue(data, 107 + val));
				financeSuper.getFinancialForm().setServicePlan(excel.getCellValue(data, 108 + val));
				
				financeSuper.getFinancialForm().setProvidedServices(excel.getCellValue(data, 109 + val));
				financeSuper.getFinancialForm().setPreviousFuneralHomeUsed(excel.getCellValue(data, 110 + val));
				financeSuper.getFinancialForm().setAdvertisingSource(excel.getCellValue(data, 111 + val));
				
				financeSuper.getFinancialForm().setMiscNotes(excel.getCellValue(data, 112 + val));
				
				financeSuper.getFinancialForm().setLineItems(new TreeMap());
				
				ArrayList<String> invItems = new ArrayList<String>();
				ArrayList<String> serviceItem = new ArrayList<String>();
				ArrayList<String> packageItem= new ArrayList<String>();
				
				for (int column = 113 + val; column <= 231 + val; column += 2) {
					if (data.getCell(column) != null && data.getCell(column + 1) != null) {
						if (data.getCell(column).getStringCellValue().equalsIgnoreCase("M")) {
							if (excel.getInvMasters().containsKey(data.getCell(column + 1).getStringCellValue())) {
								invItems.add(excel.getInvMasters().get(data.getCell(column + 1).getStringCellValue()));
							}
						}
						if (data.getCell(column).getStringCellValue().equalsIgnoreCase("S")) {
							if (excel.getServiceItmes().containsKey(data.getCell(column + 1).getStringCellValue())) {
								serviceItem.add(excel.getServiceItmes().get(data.getCell(column + 1).getStringCellValue()));
							}
						}
						if(data.getCell(column).getStringCellValue().equalsIgnoreCase("P")){
							if(excel.getPackageItmes().containsKey(data.getCell(column+1).getStringCellValue())){
								packageItem.add(excel.getPackageItmes().get(data.getCell(column+1).getStringCellValue()));
							}
						}
					}
				}
				
				invItemListValue = new String[invItems.size()];
				serviceListValue = new String[serviceItem.size()];
				packageListValue = new String[packageItem.size()];
				
				int i = 0;
				for (String items : invItems) {
					invItemListValue[i] = items;
					i++;
				}
				i = 0;
				for (String items : serviceItem) {
					serviceListValue[i] = items;
					i++;
				}
				i = 0;
				for (String items : packageItem) {
					packageListValue[i] = items;
					i++;
				}

				financeSuper.getMerchandiseForm().setSubmitButton("save");
				financeSuper.getMerchandiseForm().setListValue(invItemListValue);

				financeSuper.getServiceForm().setSubmitButton("save");
				financeSuper.getServiceForm().setListValue(serviceListValue);
				
				//set line items from Packages
				if(packageListValue.length > 0) {
					FinancialSelectPackagesForm[] fspForm = new FinancialSelectPackagesForm[packageListValue.length]; 
					for(int jIndex=0; jIndex < packageListValue.length; jIndex++){
						fspForm[jIndex] = new FinancialSelectPackagesForm(); 
						fspForm[jIndex].setSubmitButton("save");
						fspForm[jIndex].setFinancialPackage(packageListValue[jIndex]);
						fspForm[jIndex].setPriceListVersion("Pricelist");
					}
					financeSuper.setPackageForm(fspForm);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return financeSuper;
	}
}