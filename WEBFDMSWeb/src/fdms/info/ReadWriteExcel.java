package fdms.info;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionError;
import org.apache.struts.util.MessageResources;

public class ReadWriteExcel {
	private XSSFWorkbook wb;
	private XSSFSheet sheet;
	
	private CellStyle errorCell = null;
	private XSSFDrawing drawing = null;
	
	private Map<String, String> chapelLocations = new HashMap<String, String>();
	private Map<String, String> directors = new HashMap<String, String>();
	private Map<String, String> invMasters = new HashMap<String, String>();
	private Map<String, String> serviceItmes = new HashMap<String, String>();
	private Map<String, String> packageItmes = new HashMap<String, String>();
	private Map<String, String> salesType = new HashMap<String, String>();
	
	public ReadWriteExcel(String templateName) throws Exception {
		InputStream myxls = new FileInputStream(templateName);
		wb = new XSSFWorkbook(myxls);
		sheet = wb.getSheet("CaseInformation");
		
		chapelLocations = fetchSpeedData("ChapelLocations");
		directors = fetchSpeedData("Dirctors");
		invMasters = fetchSpeedData("InvItem");
		serviceItmes = fetchSpeedData("PriceList");
		packageItmes = fetchSpeedData("Packages");
		salesType = fetchSpeedData("SalesType");
		
		errorCell = wb.createCellStyle();
		errorCell.setFillForegroundColor(IndexedColors.RED.getIndex());
		errorCell.setFillPattern(CellStyle.SOLID_FOREGROUND);
	}
	
	public Integer[] close(String templateName, int val) throws Exception {
		Integer[] records = {0, sheet.getLastRowNum() - 1};
		
		for(int index = 2; index <= sheet.getLastRowNum(); index++) {
			XSSFRow row = sheet.getRow(index);
			
			if((int) NumberUtils.toDouble(getCellValue(row, 87 + val)) > 0) {
				sheet.removeRow(row);
				
				/*if(index < sheet.getLastRowNum())
					sheet.shiftRows(index+1, sheet.getLastRowNum(), -1);
				
				index--;*/
			} else {
				records[0]++;
			}
		}
		
		records[1] = records[1] - records[0]; 
		updateWorkBook(templateName);
		return records;
	}
	
	public void updateSheet(int rowNumber, int colNumber,int vitalId) {
		XSSFRow row = sheet.getRow(rowNumber);
		
		if(row.getCell(colNumber) == null) {
			row.createCell(colNumber);
			row.getCell(colNumber).setCellType(Cell.CELL_TYPE_STRING);
		}
		
		row.getCell(colNumber).setCellValue(vitalId);
	}
	
	private void updateWorkBook(String templateName) throws Exception {
		FileOutputStream fileOut = new FileOutputStream(templateName);
		try {
			wb.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fileOut.close();
		}
		/*wb = new XSSFWorkbook(new FileInputStream(templateName));
		errorCell = wb.createCellStyle();
		errorCell.setFillForegroundColor(IndexedColors.RED.getIndex());
		errorCell.setFillPattern(CellStyle.SOLID_FOREGROUND);*/
	}
	
	public String getCellValue(XSSFRow row, int cellNumber) {
		try {
			if(row.getCell(cellNumber) != null && row.getCell(cellNumber).getCellType() != Cell.CELL_TYPE_STRING)
				row.getCell(cellNumber).setCellType(Cell.CELL_TYPE_STRING);
			
			if(row.getCell(cellNumber) != null) {
				row.getCell(cellNumber).setCellComment(null);
				row.getCell(cellNumber).setCellStyle(null);
				return row.getCell(cellNumber).getStringCellValue();
			}
		} catch (Exception e) {
			return ""; 
		}
		return ""; 
	}
	
	public void markErrorOnCell(int rowNumber, List<String> formErrors, Iterator<ActionError> errors, MessageResources message) {
		XSSFRow row = sheet.getRow(rowNumber);
		
		drawing = sheet.createDrawingPatriarch();
		Comment comment = null;
		
		int index = 0;
		while(errors.hasNext()) {
			ActionError error = errors.next();
			
			int cellNumber = ColumnMapping.getInstance(0).getPreNeedColumnMapping().get(formErrors.get(index));
			
			if(cellNumber > row.getLastCellNum() || row.getCell(cellNumber) == null)
				row.createCell(cellNumber);
			
			comment = drawing.createCellComment((ClientAnchor) wb.getCreationHelper().createClientAnchor());
			//comment.setAuthor("Error:");
			comment.setString(wb.getCreationHelper().createRichTextString(message.getMessage(error.getKey(), error.getValues())));
			comment.setColumn(cellNumber);
			comment.setRow(rowNumber);
			
			row.getCell(cellNumber).setCellStyle(errorCell);
			row.getCell(cellNumber).setCellComment(comment);
			
			index++;
		}
	}
	
	public void markErrorOnCell(int rowNumber, List<String> formErrors, Iterator<ActionError> errors, MessageResources message, boolean isPreNeed) {
		XSSFRow row = sheet.getRow(rowNumber);
		
		drawing = sheet.createDrawingPatriarch();
		Comment comment = null;
		
		int index = 0;
		while(errors.hasNext()) {
			ActionError error = errors.next();
			
			int cellNumber = ColumnMapping.getInstance(isPreNeed ? -20 : 0).getColumnMapping().get(formErrors.get(index));
			
			if(cellNumber > row.getLastCellNum() || row.getCell(cellNumber) == null)
				row.createCell(cellNumber);
			
			comment = drawing.createCellComment((ClientAnchor) wb.getCreationHelper().createClientAnchor());
			//comment.setAuthor("Error:");
			comment.setString(wb.getCreationHelper().createRichTextString(message.getMessage(error.getKey(), error.getValues())));
			comment.setColumn(cellNumber);
			comment.setRow(rowNumber);
			
			row.getCell(cellNumber).setCellStyle(errorCell);
			row.getCell(cellNumber).setCellComment(comment);
			
			index++;
		}
	}
	
	public Map<String, String> fetchSpeedData(String sheetName) {
		XSSFSheet sheet = wb.getSheet(sheetName); // first sheet
		Map<String, String> map = new HashMap<String, String>();
		
		XSSFRow records;
		for (int row = 1; row <= sheet.getLastRowNum(); row++) {
			records = sheet.getRow(row); // column/field row
			records.getCell(0).setCellType(1);
			map.put(records.getCell(1).getStringCellValue(), records.getCell(0).getStringCellValue());
		}
		
		return map;
	}

	/**
	 * @return the chapelLocations
	 */
	public Map<String, String> getChapelLocations() {
		return chapelLocations;
	}

	/**
	 * @return the directors
	 */
	public Map<String, String> getDirectors() {
		return directors;
	}

	/**
	 * @return the invMasters
	 */
	public Map<String, String> getInvMasters() {
		return invMasters;
	}

	/**
	 * @return the serviceItmes
	 */
	public Map<String, String> getServiceItmes() {
		return serviceItmes;
	}

	/**
	 * @return the packageItmes
	 */
	public Map<String, String> getPackageItmes() {
		return packageItmes;
	}

	/**
	 * @return the salesType
	 */
	public Map<String, String> getSalesType() {
		return salesType;
	}

	/**
	 * @return the sheet
	 */
	public XSSFSheet getSheet() {
		return sheet;
	}
}
