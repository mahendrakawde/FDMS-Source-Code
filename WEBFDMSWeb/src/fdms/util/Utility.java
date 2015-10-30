package fdms.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;



import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility {
	
	private static XSSFWorkbook writeWorkBook= null;
	private static XSSFSheet sheetWrite= null;
	private static XSSFSheet sheetWrite2= null;
	public static int rowCount=0;
	
	private static SimpleDateFormat c_sdfFormatter = null;
	
	public static void initRejectXLS(){
		 writeWorkBook = new XSSFWorkbook();
		 sheetWrite = writeWorkBook.createSheet("RejectedRecords");
		 sheetWrite2 = writeWorkBook.createSheet("ErrorDescription");
	}	
	
	public static void addErrorRow(XSSFRow srcRow){
		try{
		XSSFRow destRow =  sheetWrite.createRow(rowCount);               
		//XSSFRow destRow =null;
		 for (int j = srcRow.getFirstCellNum(); j <= srcRow.getLastCellNum(); j++) {     
	            XSSFCell oldCell = srcRow.getCell(j);   // ancienne cell  
	            XSSFCell newCell = destRow.getCell(j);  // new cell   
	            if (oldCell != null) {     
	                if (newCell == null) {     
	                    newCell = destRow.createCell(j);     
	                }   
	                copyCell(oldCell, newCell);
	            }
		 }
		}catch(Exception ex){
			System.out.println("Error in writing Row in reject excel file :: "+ ex.getMessage());
			ex.printStackTrace();
		}
	}
	public static void copyCell(XSSFCell oldCell, XSSFCell newCell) { 
		try{
             switch(oldCell.getCellType()) {     
                 case XSSFCell.CELL_TYPE_STRING:     
                     newCell.setCellValue(oldCell.getStringCellValue());     
                     break;     
               case XSSFCell.CELL_TYPE_NUMERIC:     
                     newCell.setCellValue(oldCell.getNumericCellValue());     
                     break;     
                 case XSSFCell.CELL_TYPE_BLANK:     
                     newCell.setCellType(XSSFCell.CELL_TYPE_BLANK);     
                     break;     
                 case XSSFCell.CELL_TYPE_BOOLEAN:     
                     newCell.setCellValue(oldCell.getBooleanCellValue());     
                     break;     
                 case XSSFCell.CELL_TYPE_ERROR:     
                     newCell.setCellErrorValue(oldCell.getErrorCellValue());     
                     break;     
                 case XSSFCell.CELL_TYPE_FORMULA:     
                     newCell.setCellFormula(oldCell.getCellFormula());     
                     break;     
                 default:     
                     break;     
             }     
			}catch(Exception ex){
				System.out.println("Error in writing Cell in reject excel file :: "+ ex.getMessage());
				ex.printStackTrace();
			}   
         }     
	
	public static void addErrorDescriptionRow(int rowNum,String field,String errorDescription){
		try{                                                                                              
			XSSFRow destRow =  sheetWrite2.createRow(rowCount);    
			XSSFCell cell= destRow.createCell(0);
			cell.setCellValue(Integer.toString(rowNum));
			cell= destRow.createCell(1);
			cell.setCellValue(field);
			cell= destRow.createCell(2);
			cell.setCellValue(errorDescription);
		}catch(Exception ex){                                                                             
			System.out.println("Error in writing descriptionRow in reject excel file :: "+ ex.getMessage());         
			ex.printStackTrace();                                                                         
		}                                                                                                 
		
		
	}
	
	public static void createRejectXls(){
		 FileOutputStream outPutStream = null;
	        try {
	            outPutStream = new FileOutputStream("RejectedCases.xlsx");
	            writeWorkBook.write(outPutStream);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (outPutStream != null) {
	                try {
	                    outPutStream.flush();
	                    outPutStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	}
	
	protected static SimpleDateFormat getDateFormatter() {
		if (c_sdfFormatter == null) {
			c_sdfFormatter = new SimpleDateFormat("MMddyyyy");
			c_sdfFormatter.setLenient(false);
		}
		return c_sdfFormatter;
	}
	public static String convertToDateMMDDYYYY(String theString) throws Exception {
		String mydate = new String();
		java.util.Date datRV = null;

		if (theString != null && !theString.trim().equals("") && theString.compareTo("00") > 0) {
			try {
				datRV = getDateFormatter().parse(theString);
				SimpleDateFormat mmddyyyy = new SimpleDateFormat("MMddyyyy");
				mmddyyyy.setLenient(false);
				mydate = mmddyyyy.format(datRV);
			} catch (Exception e) {
				e.printStackTrace();
				Exception niceException = new Exception("Please enter a valid date like mm/dd/yyyy");
				throw niceException;
			}
			// Now make sure the date is after 1800.
			java.util.Date dateMin = getDateFormatter().parse("01011800");
			if (datRV.before(dateMin) == true) {
				throw new Exception("Date must be after 01011800");
			}
		}

		return mydate;
	}

}
