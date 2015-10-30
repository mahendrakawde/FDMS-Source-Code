package fdms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aldorsolutions.webfdms.beans.custom.FinancialInformationLineItem;

import fdms.ui.struts.form.FinancialSelectPackagesForm;

public class FinancialInformationExcel {

	private Properties properties = new Properties();
	private FinancialSuper[] financeSuper = null;
	HashMap<String, String> invMasters = new HashMap<String, String>();
	HashMap<String, String> serviceItmes = new HashMap<String, String>();
	HashMap<String,String>  packageItmes = new HashMap<String, String>();
	InputStream myxls;
	XSSFWorkbook wb;

	TreeMap<Integer, FinancialInformationLineItem> chargeSet = new TreeMap<Integer, FinancialInformationLineItem>();

	public FinancialInformationExcel(String templateName) {
		try {
			//properties.load(new FileInputStream(new File("D:\\FDMS\\Code\\FDMS v3.1\\WEBFDMSWeb\\src\\validationFinancialInfo.properties")));
			//myxls = new FileInputStream("D:\\FDMS\\Code\\FDMS v3.1\\WEBFDMSWeb\\src\\" + templateName);
			
			properties.load(new FileInputStream(new File("D:\\project\\AldorSolutions\\source\\FDMS v3.1\\WEBFDMSWeb\\src\\validationFinancialInfo.properties")));
			myxls = new FileInputStream("D:\\project\\AldorSolutions\\source\\FDMS v3.1\\WEBFDMSWeb\\src\\" + templateName);
			wb = new XSSFWorkbook(myxls);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fillInvMasterLineItems() {
		XSSFSheet sheet = wb.getSheet("invitem"); // first sheet

		XSSFRow records;
		for (int row = 1; row <= sheet.getLastRowNum(); row++) {
			records = sheet.getRow(row); // column/field row
			records.getCell(0).setCellType(1);
			invMasters.put(records.getCell(1).getStringCellValue(), records.getCell(0).getStringCellValue());
		}
	}

	public void fillServicesItems() {
		XSSFSheet sheet = wb.getSheet("pricelist"); // first sheet

		XSSFRow records;
		for (int row = 1; row <= sheet.getLastRowNum(); row++) {
			records = sheet.getRow(row); // column/field row
			records.getCell(0).setCellType(1);
			serviceItmes.put(records.getCell(1).getStringCellValue(), records.getCell(0).getStringCellValue());
		}
	}

	public void fillPackages() {
		XSSFSheet sheet = wb.getSheet("Packages"); // first sheet

		XSSFRow records;
		for (int row = 1; row <= sheet.getLastRowNum(); row++) {
			records = sheet.getRow(row); // column/field row
			records.getCell(0).setCellType(1);
			packageItmes.put(records.getCell(1).getStringCellValue(), records.getCell(0).getStringCellValue());
		}
	}
	
	public FinancialSuper[] readInfoFromExcel() {
		try {
			fillInvMasterLineItems();
			fillServicesItems();
			fillPackages();
			
			String fieldValue = "";
			Utility.initRejectXLS();

			XSSFSheet sheet = wb.getSheet("FinancialInformation"); // first
																	// sheet
			XSSFRow columnHeading = sheet.getRow(1); // column/field row

			// Field row
			HashMap<Integer, String> validation = new HashMap<Integer, String>();
			// read column names
			for (int index = 0; index < columnHeading.getLastCellNum(); index++) {
				fieldValue = columnHeading.getCell(index).getStringCellValue();
				if (properties.containsKey(fieldValue)) {
					validation.put(index, fieldValue);
					// System.out.println("added validation "+ index);
				}
			}

			XSSFRow data = null;
			System.out.println("total no of rows :: " + sheet.getLastRowNum());

			String[] serviceListValue;
			String[] invItemListValue;
			String[] packageListValue;
			
			ArrayList<Integer> validRows = checkVailidRows(sheet, validation);
			financeSuper = new FinancialSuper[validRows.size()];
			int index = 0;
			for (int startRow = 0; startRow < validRows.size(); startRow++) {
				try {
					data = sheet.getRow(validRows.get(startRow).intValue());
					financeSuper[startRow] = new FinancialSuper();
					financeSuper[startRow].getFinancialForm().setVitalsId((int) NumberUtils.toDouble(String.valueOf(data.getCell(0).getNumericCellValue()), 0));
					financeSuper[startRow].getFinancialForm().setShowFinancingSection((data.getCell(1).getStringCellValue().equals("Y")) ? true : false);
					financeSuper[startRow].getFinancialForm().setApplyFinanceCharges((data.getCell(2).getStringCellValue().equals("Y")) ? true : false);
					financeSuper[startRow].getFinancialForm().setInterestRate(String.valueOf(data.getCell(3).getNumericCellValue()));
					financeSuper[startRow].getFinancialForm().setInterestFromDate(data.getCell(4).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setCustomerName(data.getCell(5).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setPostedContract(data.getCell(6).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setShowFinancingSection((data.getCell(1).getStringCellValue().equals("Y")) ? true : false);
					financeSuper[startRow].getFinancialForm().setSaleDate(data.getCell(8).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setDueDate(data.getCell(9).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setStmtDate(data.getCell(10).getStringCellValue());
					data.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
					// financeSuper[startRow].getFinancialForm().setAmountPaid(data.getCell(11).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setPaymentSource(data.getCell(12).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setDateOfPayment(data.getCell(13).getStringCellValue());
					// financeSuper[startRow].getFinancialForm().setDateOfDeposit(data.getCell(14).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setReceiptNumber(String.valueOf(data.getCell(15).getNumericCellValue()));
					financeSuper[startRow].getFinancialForm().setManualReceiptNumber(String.valueOf(data.getCell(16).getNumericCellValue()));
					financeSuper[startRow].getFinancialForm().setNonCashAdjustment(String.valueOf(data.getCell(17).getNumericCellValue()));
					financeSuper[startRow].getFinancialForm().setMethodOfPayment(data.getCell(18).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setSalesDescCDID(String.valueOf((int)data.getCell(19).getNumericCellValue()));
					financeSuper[startRow].getFinancialForm().setSaleType(data.getCell(20).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setSource(data.getCell(21).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setDisposition(data.getCell(22).getStringCellValue());

					financeSuper[startRow].getFinancialForm().setServicePlan(data.getCell(23).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setProvidedServices(data.getCell(24).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setPreviousFuneralHomeUsed(data.getCell(25).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setAdvertisingSource(data.getCell(26).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setMiscNotes(data.getCell(27).getStringCellValue());
					financeSuper[startRow].getFinancialForm().setLineItems(new TreeMap());
					
					ArrayList<String> invItems = new ArrayList<String>();
					ArrayList<String> serviceItem = new ArrayList<String>();
					ArrayList<String> packageItem= new ArrayList<String>();
					
					for (int column = 28; column <= data.getLastCellNum(); column += 2) {
						if (data.getCell(column) != null && data.getCell(column + 1) != null) {
							if (data.getCell(column).getStringCellValue().equalsIgnoreCase("M")) {
								if (invMasters.containsKey(data.getCell(column + 1).getStringCellValue())) {
									invItems.add(invMasters.get(data.getCell(column + 1).getStringCellValue()));
								}
							}
							if (data.getCell(column).getStringCellValue().equalsIgnoreCase("S")) {
								if (serviceItmes.containsKey(data.getCell(column + 1).getStringCellValue())) {
									serviceItem.add(serviceItmes.get(data.getCell(column + 1).getStringCellValue()));
								}
							}
							if(data.getCell(column).getStringCellValue().equalsIgnoreCase("P")){
								if(packageItmes.containsKey(data.getCell(column+1).getStringCellValue())){
									packageItem.add(packageItmes.get(data.getCell(column+1).getStringCellValue()));
								}
							}
						}
					}
					
					System.out.println("Total no of Inv Items :: " + invItems.size());
					System.out.println("Total no of Service Items :: " + serviceItem.size());

					invItemListValue = new String[invItems.size()];
					serviceListValue = new String[serviceItem.size()];
					packageListValue = new String[packageItem.size()];
					
					int i = 0;
					for (String items : invItems) {
						invItemListValue[i] = items;
						System.out.println("invItemsID : " + items);
						i++;
					}
					i = 0;
					for (String items : serviceItem) {
						serviceListValue[i] = items;
						System.out.println("ServiceID : " + items);
						i++;
					}
					i = 0;
					for (String items : packageItem) {
						packageListValue[i] = items;
						System.out.println("PackageID : " + items);
						i++;
					}
					/*
					 * invItemListValue = (String[]) invItems.toArray();
					 * serviceListValue = (String[]) serviceItem.toArray();
					 */

					financeSuper[startRow].getMerchandiseForm().setSubmitButton("save");
					financeSuper[startRow].getMerchandiseForm().setListValue(invItemListValue);

					financeSuper[startRow].getServiceForm().setSubmitButton("save");
					financeSuper[startRow].getServiceForm().setListValue(serviceListValue);
					
					//set line items from Packages
					if(packageListValue.length > 0) {
						FinancialSelectPackagesForm[] fspForm = new FinancialSelectPackagesForm[packageListValue.length]; 
						for(int jIndex=0; jIndex < packageListValue.length; jIndex++){
							fspForm[jIndex] = new FinancialSelectPackagesForm(); 
							fspForm[jIndex].setSubmitButton("save");
							fspForm[jIndex].setFinancialPackage(packageListValue[jIndex]);
							fspForm[jIndex].setPriceListVersion("Pricelist");
						}
						financeSuper[startRow].setPackageForm(fspForm);
					}
					
					System.out.println("Done with out line items");

				} catch (Exception ex) {
					System.out.println("Error for reading excel row " + startRow + " and column " + index);
					System.out.println("Error :: " + ex.getMessage());
					ex.printStackTrace();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Utility.createRejectXls();
		return financeSuper;
	}

	public ArrayList<Integer> checkVailidRows(XSSFSheet sheet, HashMap<Integer, String> validation) {
		ArrayList<Integer> validRows = new ArrayList<Integer>();
		XSSFRow data = null;
		for (int startRow = 2; startRow <= sheet.getLastRowNum(); startRow++) {
			try {
				data = sheet.getRow(startRow);
				boolean validRowData = true;
				for (Integer key : validation.keySet()) {
					int colIndex = key.intValue();
					// System.out.println("colINdex :: " + colIndex);
					XSSFCell cell = data.getCell(colIndex);
					// validate data of excel row
					// System.out.println("get Validate on : "+
					// validation.get(key));
					if (performValidation(validation.get(key), data, cell) == true) {
						validRowData = true;
					} else {
						validRowData = false;
						System.out.println("Validation fail on row :: " + data.getRowNum());
						break;
					}
				}
				if (validRowData) {
					// System.out.println("Valid rows no is :: "+ startRow);
					validRows.add(Integer.valueOf(startRow));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return validRows;
	}

	public boolean performValidation(String validationName, XSSFRow row,
			XSSFCell cell) {
		// System.out.println("Validation perform Name :: "+validationName);

		if (validationName.equalsIgnoreCase("salesDescCDID")) {
			if (cell == null || cell.getNumericCellValue() <= 0) {
				Utility.addErrorRow(row);
				Utility.addErrorDescriptionRow(row.getRowNum(), validationName,
						properties.getProperty(validationName));
				Utility.rowCount++;
			} else {
				return true;
			}
		} else if (validationName.equalsIgnoreCase("saleType")) {
			if (cell == null || cell.getStringCellValue().equals("(NULL)")
					|| cell.getStringCellValue().trim().length() <= 0) {
				Utility.addErrorRow(row);
				Utility.addErrorDescriptionRow(row.getRowNum(), validationName, properties.getProperty(validationName));
				Utility.rowCount++;
			} else {
				return true;
			}
		} else if (validationName.equalsIgnoreCase("interestFromDate")) {
			cell.setCellType(1);
			if (cell == null || cell.getStringCellValue().trim().length() <= 0) {
				Utility.addErrorRow(row);
				Utility.addErrorDescriptionRow(row.getRowNum(), validationName, properties.getProperty(validationName));
				Utility.rowCount++;
			} else if (cell.getStringCellValue().equals("(NULL)")) {
				return true;
			} else {
				try {
					Utility.convertToDateMMDDYYYY(cell.getStringCellValue());
					return true;
				} catch (Exception ex) {
					// ex.printStackTrace();
					Utility.addErrorRow(row);
					Utility.addErrorDescriptionRow(row.getRowNum(), validationName, properties.getProperty(validationName));
					Utility.rowCount++;
					return false;
				}
			}
		} else if (validationName.equalsIgnoreCase("saleDate")) {
			cell.setCellType(1);
			if (cell == null || cell.getStringCellValue().trim().length() <= 0) {
				Utility.addErrorRow(row);
				Utility.addErrorDescriptionRow(row.getRowNum(), validationName, properties.getProperty(validationName));
				Utility.rowCount++;
			} else if (cell.getStringCellValue().equals("(NULL)")) {
				return true;
			} else
				try {
					Utility.convertToDateMMDDYYYY(cell.getStringCellValue());
					return true;
				} catch (Exception ex) {
					// ex.printStackTrace();
					Utility.addErrorRow(row);
					Utility.addErrorDescriptionRow(row.getRowNum(), validationName, properties.getProperty(validationName));
					Utility.rowCount++;
					return false;
				}
		} else if (validationName.equalsIgnoreCase("dueDate")) {
			cell.setCellType(1);
			if (cell == null || cell.getStringCellValue().trim().length() <= 0) {
				Utility.addErrorRow(row);
				Utility.addErrorDescriptionRow(row.getRowNum(), validationName, properties.getProperty(validationName));
				Utility.rowCount++;
			} else if (cell.getStringCellValue().equals("(NULL)")) {
				return true;
			} else {
				try {
					Utility.convertToDateMMDDYYYY(cell.getStringCellValue());
					return true;
				} catch (Exception ex) {
					// ex.printStackTrace();
					Utility.addErrorRow(row);
					Utility.addErrorDescriptionRow(row.getRowNum(),
							validationName, properties
									.getProperty(validationName));
					Utility.rowCount++;
					return false;
				}
			}
		} else if (validationName.equalsIgnoreCase("stmtDate")) {
			cell.setCellType(1);
			if (cell == null || cell.getStringCellValue().trim().length() <= 0) {
				Utility.addErrorRow(row);
				Utility.addErrorDescriptionRow(row.getRowNum(), validationName, properties.getProperty(validationName));
				Utility.rowCount++;
			} else if (cell.getStringCellValue().equals("(NULL)")) {
				return true;
			} else {
				try {
					Utility.convertToDateMMDDYYYY(cell.getStringCellValue());
					return true;
				} catch (Exception ex) {
					// ex.printStackTrace();
					Utility.addErrorRow(row);
					Utility.addErrorDescriptionRow(row.getRowNum(), validationName, properties.getProperty(validationName));
					Utility.rowCount++;
					return false;
				}
			}
		} else if (validationName.equalsIgnoreCase("dateOfPayment")) {
			cell.setCellType(1);
			if (cell == null || cell.getStringCellValue().trim().length() <= 0) {
				Utility.addErrorRow(row);
				Utility.addErrorDescriptionRow(row.getRowNum(), validationName, properties.getProperty(validationName));
				Utility.rowCount++;
			} else if (cell.getStringCellValue().equals("(NULL)")) {
				return true;
			} else {
				try {
					Utility.convertToDateMMDDYYYY(cell.getStringCellValue());
					return true;
				} catch (Exception ex) {
					// ex.printStackTrace();
					Utility.addErrorRow(row);
					Utility.addErrorDescriptionRow(row.getRowNum(), validationName, properties.getProperty(validationName));
					Utility.rowCount++;
					return false;
				}
			}
		} else if (validationName.equalsIgnoreCase("dateOfDeposit")) {
			cell.setCellType(1);
			if (cell == null || cell.getStringCellValue().trim().length() <= 0) {
				Utility.addErrorRow(row);
				Utility.addErrorDescriptionRow(row.getRowNum(), validationName, properties.getProperty(validationName));
				Utility.rowCount++;
			} else if (cell.getStringCellValue().equals("(NULL)")) {
				return true;
			} else {
				try {
					Utility.convertToDateMMDDYYYY(cell.getStringCellValue());
					return true;
				} catch (Exception ex) {
					// ex.printStackTrace();
					Utility.addErrorRow(row);
					Utility.addErrorDescriptionRow(row.getRowNum(), validationName, properties.getProperty(validationName));
					Utility.rowCount++;
					return false;
				}
			}
		}
		return false;
	}
}
