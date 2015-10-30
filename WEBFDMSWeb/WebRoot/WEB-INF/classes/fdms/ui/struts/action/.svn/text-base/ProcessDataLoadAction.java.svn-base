package fdms.ui.struts.action;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import com.aldorsolutions.webfdms.admin.dao.LoadDataDAO;
import com.aldorsolutions.webfdms.beans.DbColumn;
import com.aldorsolutions.webfdms.beans.LoadDataDTO;
import com.aldorsolutions.webfdms.beans.cache.MetaTablesCache;
import com.aldorsolutions.webfdms.common.Constants;
import com.aldorsolutions.webfdms.company.bean.CompanyManagerBean;
import com.aldorsolutions.webfdms.company.model.CompanyDTO;
import com.aldorsolutions.webfdms.util.DAO;

public class ProcessDataLoadAction extends Action {

	private Logger logger = Logger.getLogger(ProcessDataLoadAction.class.getName());

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		LoadDataDAO dao = new LoadDataDAO(DAO.DB_DATABASEBUILDER);
		DynaActionForm form = (DynaActionForm) actionForm;
		ActionErrors errors = new ActionErrors();
		String type = request.getParameter("method");
		validate(errors, form);

		FormFile file = (FormFile) form.get("file");
		LoadDataDTO dto = (LoadDataDTO) form.get("loadDataDTO");
		if (errors.size() > 0) {
			saveErrors(request, errors);
			loadTablesAndColumns(form);
			return new ActionForward(mapping.getInput());
		}
		File fNew = new File(System.getProperty("java.io.tmpdir"), file.getFileName());
		FileOutputStream out = new FileOutputStream(fNew);
		out.write(file.getFileData());
		String fullFileName = fNew.getAbsolutePath();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; fullFileName != null && i < fullFileName.length(); i++) {
			if (fullFileName.charAt(i) == '\\') {
				buf.append("\\\\");
			} else {
				buf.append(fullFileName.charAt(i));
			}
		}
		dto.setFileName(buf.toString());

		CompanyManagerBean bean = new CompanyManagerBean();
		CompanyDTO company = bean.getCompany(dto.getDataBaseName());
		List cols = MetaTablesCache.getInstance().getColumns(dto.getDataBaseName(), dto.getTableName(),
				company.getDbLookup());

		List dataRecs = getDataRecords(fNew.getAbsolutePath(), form);
		validateDataRecords(dataRecs, errors, form, cols);
		if (errors.size() > 0) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.EMPTY_ERROR, "Errors for file name: " + form.get("file") ) );
			saveErrors(request, errors);
			loadTablesAndColumns(form);
			return new ActionForward(mapping.getInput());
		}
		
		List records = convertDataRecords(dataRecs, form);

		if (records.size() > 0) {
			request.setAttribute("records", records);
		}

		if ("submit".equalsIgnoreCase(type)) {

			long rows = dao.loadDataFile(dto);
			StringBuffer result = new StringBuffer();
			result.append("Successfully created ");
			result.append(rows);
			result.append(" Rows");
			if (rows > 0) {
				request.setAttribute("rows", result.toString());
			}
		}
		fNew.delete();
		fNew.deleteOnExit();
		
		return mapping.findForward("showDataLoadForm");
	}

	/**
	 * Checks validation errors
	 * 
	 * @param errors
	 *            list of errors
	 * @param form
	 *            loaddataform
	 */
	public void validate(ActionErrors errors, DynaActionForm form) {
		FormFile file = (FormFile) form.get("file");
		String fileName = file.getFileName();
		if (fileName != null) {
			fileName = fileName.toUpperCase();
			fileName = fileName.trim();
		}
		LoadDataDTO dto = (LoadDataDTO) form.get("loadDataDTO");
		String databaseName = dto.getDataBaseName();
		if (databaseName != null) {
			databaseName = databaseName.trim();
		}
		if (databaseName == null || "".equals(databaseName)) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.REQUIRED_ERROR, "DATA BASE"));
		}

		String tableName = dto.getTableName();
		if (tableName != null) {
			tableName.trim();
		}
		if (tableName == null || "".equals(tableName)) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.REQUIRED_ERROR, "TABLE NAME"));
		}

		String[] cols = dto.getColList();
		if (cols == null || cols.length <= 0) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.REQUIRED_ERROR, "Columns"));
		}

		if (fileName == null || "".equals(fileName)) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.REQUIRED_ERROR, "DATA_FILE"));
		} else {
			if (!fileName.endsWith(".TXT") && !fileName.endsWith(".CSV")) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.SUFFIX_ERROR, "DATA_FILE",
						".txt or .csv"));
			}
		}

		if (dto.getLineTerminatedBy() == null || "".equals(dto.getLineTerminatedBy())) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.REQUIRED_ERROR,
					"Lines Terminated By"));
		}
		if (dto.getFiledTerminator() == null || "".equals(dto.getFiledTerminator())) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.REQUIRED_ERROR,
					"Terminated By"));
		}
	}

	/**
	 * Gets data records from file
	 * 
	 * @param fileName
	 *            File name containg data
	 * @param form
	 *            DynaActionForm
	 * @return list of data records
	 */
	public List getDataRecords(String fileName, DynaActionForm form) {
		StringTokenizer tokenizer = null;
		StringBuffer data = new StringBuffer();
		List<String> dataRecs = new ArrayList<String>();
		LoadDataDTO dto = (LoadDataDTO) form.get("loadDataDTO");
		try {
			FileInputStream fstream = new FileInputStream(fileName);

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) {
				if (!"\\r\\n".equals(dto.getLineTerminatedBy())) {
					data.append(strLine);
					data.append(System.getProperty("line.separator"));
				} else {
					dataRecs.add(strLine);
				}
			}

		} catch (IOException e) {
			logger.error("Error reading file", e);
		}

		if (!"\\r\\n".equals(dto.getLineTerminatedBy())) {
			tokenizer = new StringTokenizer(data.toString(), dto.getLineTerminatedBy());
			if (tokenizer.countTokens() > 0) {
				while (tokenizer.hasMoreTokens()) {
					dataRecs.add(tokenizer.nextToken());
				}
			}

			StringBuilder builder = new StringBuilder(data.toString());
			while (builder.indexOf(dto.getLineTerminatedBy()) >= 0) {
				dataRecs.add(builder.substring(0, builder.indexOf(dto.getLineTerminatedBy())));
				builder = new StringBuilder(builder.substring(builder.indexOf(dto.getLineTerminatedBy())
						+ dto.getLineTerminatedBy().length()));
			}
			if (builder.toString().trim().length() > 0) {
				dataRecs.add(builder.toString().trim());
			}
		}
		return dataRecs;
	}

	/**
	 * Extracts columns for given list of data records
	 * 
	 * @param dataRecs
	 *            list of data records
	 * @param form
	 *            DynaActionForm
	 * @return List of DynaBean with colums details.
	 */
	public List convertDataRecords(List dataRecs, DynaActionForm form) {
		BasicDynaClass dynaRec = null;
		DynaBean record = null;
		List<DynaBean> dataElements = new ArrayList<DynaBean>();
		LoadDataDTO dto = (LoadDataDTO) form.get("loadDataDTO");

		DynaProperty[] props = new DynaProperty[dto.getColList().length];
		for (int i = 0; dto.getColList() != null && i < dto.getColList().length; i++) {
			props[i] = new DynaProperty(dto.getColList()[i], String.class);
		}

		dynaRec = new BasicDynaClass("record", null, props);
		try {
			DynaBean recordHeader = dynaRec.newInstance();

			for (int i = 0; dto.getColList() != null && i < dto.getColList().length; i++) {
				recordHeader.set(dto.getColList()[i], dto.getColList()[i]);
			}
			dataElements.add(recordHeader);
		} catch (IllegalAccessException e) {

			logger.error(e.getMessage(), e);
		} catch (InstantiationException e) {

			logger.error(e.getMessage(), e);
		}

		for (int i = 0; dataRecs != null && i < dataRecs.size(); i++) {
			List cols = getColumns((String) dataRecs.get(i), form);
			try {
				record = dynaRec.newInstance();
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage(), e);
			} catch (InstantiationException e) {
				logger.error(e.getMessage(), e);
			}
			if (cols.size() == dto.getColList().length) {
				for (int j = 0; cols != null && j < cols.size(); j++) {
					record.set(dto.getColList()[j], cols.get(j));
				}
			}
			dataElements.add(record);
		}

		return dataElements;
	}

	/**
	 * Gets Column data from a given data reecord
	 * 
	 * @param record
	 *            data record
	 * @param form
	 *            DynaActionForm
	 * @return List of Columns
	 */
	public List getColumns(String record, DynaActionForm form) {

		List list = new ArrayList();
		LoadDataDTO dto = (LoadDataDTO) form.get("loadDataDTO");
		
		char delimiter = ',';
		if ( dto.getFiledTerminator() != null && dto.getFiledTerminator().length() > 0 ) {
			delimiter = dto.getFiledTerminator().charAt(0);
		}
		
		CSVReader reader = new CSVReader (delimiter);
		String [] columns = reader.parseLine(record);
		
		for ( String column : columns ) {
			list.add(column);
		}
		
		return list;

	}

	/**
	 * Validates data records
	 * 
	 * @param dataRecs
	 *            list of data records
	 * @param errors
	 *            list of validation errors
	 * @param form
	 *            DynaActionForm
	 * @param cols
	 *            list of metadata columns
	 */
	public void validateDataRecords(List dataRecs, ActionErrors errors, DynaActionForm form, List cols) {
		if (dataRecs == null || dataRecs.size() == 0) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.EMPTY_ERROR,
					"File does not exist or File does not contain data records"));
		}
		validateRequiredColumns(cols, form, errors);
		for (int i = 0; dataRecs != null && i < dataRecs.size(); i++) {
			validateDataRec(errors, form, (String) dataRecs.get(i), i + 1, cols);
		}
	}

	/**
	 * Validates data record
	 * 
	 * @param errors
	 *            list of errors
	 * @param form
	 *            DynaActionForm
	 * @param record
	 *            data record
	 * @param recNum
	 *            record number
	 * @param cols
	 *            columns metadata
	 */
	public void validateDataRec(ActionErrors errors, DynaActionForm form, String record, long recNum,
			List cols) {

		LoadDataDTO dto = (LoadDataDTO) form.get("loadDataDTO");
		int colSize = dto.getColList().length;
		
		char delimiter = ',';
		char encloseChar = '\"';
		char escapeChar = '\\';
		
		if ( dto.getFiledTerminator() != null && dto.getFiledTerminator().length() > 0 ) {
			delimiter = dto.getFiledTerminator().charAt(0);
		}
		else {
			delimiter = 0;
		}
		
		if ( dto.getEscapeChar() != null && dto.getEscapeChar().length() > 0 ) {
			escapeChar = dto.getEscapeChar().charAt(0);
		}
		else {
			escapeChar = 0;
		}
		
		if ( dto.getFieldEnclouser() != null && dto.getFieldEnclouser().length() > 0 ) {
			encloseChar = dto.getFieldEnclouser().charAt(0);
		}
		else {
			encloseChar = 0;
		}
		
		CSVReader reader = new CSVReader (delimiter, encloseChar, escapeChar);
		String [] columns = reader.parseLine(record);
		
		if ( columns.length > colSize ) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.EMPTY_ERROR, "Record Number : " + recNum + " Number of Cols: "+columns.length+ " more than " + colSize));
		} else if ( columns.length < (colSize) ) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.EMPTY_ERROR, "Record Number : " + recNum + " Number of Cols: "+columns.length+ " less than expected columns " + colSize));
		} 
		
		for (int i = 0; columns != null && i < columns.length; i++) {
			
			String [] colList = dto.getColList();			
			
			if ( colList.length > i ) {
				String col = colList[i];
				DbColumn dbcolumn = getColumn(col, cols);
				validateColumn(columns[i], errors, dbcolumn, recNum);
			}
		}

	}

	/**
	 * Checks table required columns are selected or not
	 * 
	 * @param metaColumns
	 *            list of columns meta data
	 * @param form
	 *            ActionForm
	 * @param errors
	 *            List of validation errors
	 */
	public void validateRequiredColumns(List metaColumns, DynaActionForm form, ActionErrors errors) {
		LoadDataDTO dto = (LoadDataDTO) form.get("loadDataDTO");
		String[] selectedCols = dto.getColList();
		for (int i = 0; metaColumns != null && i < metaColumns.size(); i++) {
			DbColumn metaColumn = (DbColumn) metaColumns.get(i);
			if (metaColumn.isRequired() && !getColumnSelection(metaColumn.getName(), selectedCols)) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.REQUIRED_ERROR, metaColumn.getName() + " column selection"));
			}
		}

	}

	/**
	 * Indicates required column is selected or not
	 * 
	 * @param columnName
	 *            Database Required Column to match
	 * @param cols
	 *            List of columns user selected
	 * @return true if user selected the column else false
	 */
	public boolean getColumnSelection(String columnName, String[] cols) {
		boolean selected = false;
		if (columnName != null) {
			for (int i = 0; cols != null && i < cols.length; i++) {
				if (columnName.equalsIgnoreCase(cols[i])) {
					selected = true;
					break;
				}
			}
		}
		return selected;
	}

	/**
	 * Validates column data
	 * 
	 * @param data
	 *            in the column
	 * @param errors
	 *            list of errors
	 * @param column
	 *            metadata column
	 * @param recnum
	 *            Record Number
	 */
	public void validateColumn(String data, ActionErrors errors, DbColumn column, long recnum) {

		if (data != null) {
			data = data.trim();
		}
		
		if ( column == null ) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.EMPTY_ERROR, "Problem validating column: " + data ));
		}
		
		if ("int".equals(column.getDatatype()) && !"\\N".equalsIgnoreCase(data) ) {
			for (int j = 0; data != null && j < data.length(); j++) {
				if (data.charAt(j) > '9' || data.charAt(j) < '0') {
					if (data.charAt(j) != '-' && data.charAt(j) != '+') {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.EMPTY_ERROR,
								"Record Number : " + recnum + " for column: \"" + column.getName()
										+ "\" Column Type : INTEGER actual Data: " + data));
						break;
					}
				}
			}
		} else if ("VARCHAR".equals(column.getDatatype())) {
			// checkVarChar();
		}
		if ( "\\N".equalsIgnoreCase(data) == false ) {
			if (data != null && !"BIT".equals(column.getDatatype()) && data.length() > column.getDatasize()) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.EMPTY_ERROR, "Record Number : "
						+ recnum + ", Column Name : " + column.getName() + " Data Element: " + data
						+ " Exceeding length. Expected length : " + column.getDatasize()));
			}
		}
	}

	/**
	 * Gets DbColumn from the list for matching column name
	 * 
	 * @param columnName
	 *            column name to match
	 * @param cols
	 *            list of DbColumns
	 * @return matching column
	 */
	public DbColumn getColumn(String columnName, List cols) {
		DbColumn col = null;
		for (int k = 0; cols != null && k < cols.size(); k++) {
			if (columnName.equals(((DbColumn) cols.get(k)).getName())) {
				col = (DbColumn) cols.get(k);
				break;
			}
		}
		return col;
	}

	/**
	 * Popultes required tables and column based on user selection
	 * 
	 * @param form
	 *            DynaActionForm
	 */
	public static void loadTablesAndColumns(DynaActionForm form) {
		
		LoadDataDTO loadData = (LoadDataDTO) form.get("loadDataDTO");
		List tablesList = null;
		List colList = null;
		CompanyManagerBean bean = new CompanyManagerBean();
		CompanyDTO company = bean.getCompany(loadData.getDataBaseName());
		
		if (loadData.getDataBaseName() != null && !"".equals(loadData.getDataBaseName())) {
			 tablesList = MetaTablesCache.getInstance().getAllTables(loadData.getDataBaseName(),
					company.getDbLookup());
			if (loadData.getTableName() != null && !"".equals(loadData.getTableName())) {
				colList = MetaTablesCache.getInstance().getAllColumns(loadData.getDataBaseName(),
						loadData.getTableName(), company.getDbLookup());
			}
		}
		List<LabelValueBean> tablesListBean = new ArrayList<LabelValueBean>();
		for (int i = 0; tablesList != null && i < tablesList.size(); i++) {
			tablesListBean.add(new LabelValueBean((String) tablesList.get(i), (String) tablesList.get(i)));

		}
		List<LabelValueBean> colListBean = new ArrayList<LabelValueBean>();
		for (int i = 0; colList != null && i < colList.size(); i++) {
			colListBean.add(new LabelValueBean(((DbColumn) colList.get(i)).getName(),
						((DbColumn) colList.get(i)).getName()));

		}
		form.set("tablesList", tablesListBean);
		form.set("colList", colListBean);
	}
	
	
	 /*------------------------------------------------------------------------------
	 Name: CSVReader.java
	 Project: jutils.org
	 Comment: reads CSV (Comma Separated Value) files
	 Version: $Id: ProcessDataLoadAction.java,v 1.14 2008/01/08 15:03:35 chad Exp $
	 Author: Roedy Green roedy@mindprod.com, Heinrich Goetzger goetzger@gmx.net
	 ------------------------------------------------------------------------------*/
	 
	 /**
	  * Reads CSV (Comma Separated Value) files.
	  *
	  * This format is mostly used my Microsoft Word and Excel.
	  * Fields are separated by commas, and enclosed in
	  * quotes if they contain commas or quotes.
	  * Embedded quotes are doubled.
	  * Embedded spaces do not normally require surrounding quotes.
	  * The last field on the line is not followed by a comma.
	  * Null fields are represented by two commas in a row.
	  * We ignore leading and trailing spaces on fields, even inside quotes.
	  *
	  * @author copyright (c) 2002 Roedy Green Canadian Mind Products
	  * Roedy posted this code on Newsgroups:comp.lang.java.programmer on 27th March 2002.
	  *
	  * Heinrich added some stuff like comment ability and linewise working.
	  *
	  */
	 
	 class CSVReader {
		/**
		 * Constructor
		 *
		 * @param r input Reader source of CSV Fields to read.
		 * @param separator
		 * field separator character, usually ',' in North America,
		 * ';' in Europe and sometimes '\t' for tab.
		 */
		public CSVReader(char separator) {
			this();
			this.separator = separator;
		} // end of CSVReader
		
		/**
		 * Constructor
		 *
		 * @param r input Reader source of CSV Fields to read.
		 * @param separator
		 * field separator character, usually ',' in North America,
		 * ';' in Europe and sometimes '\t' for tab.
		 */
		public CSVReader(char separator, char enclosedBy, char escapeChar) {
			this.separator = separator;
			this.enclosedBy = enclosedBy;
			this.escapeChar = escapeChar;
		} // end of CSVReader
		
		/**
		 * Constructor with default field separator ','.
		 *
		 * @param r input Reader source of CSV Fields to read.
		 */
		public CSVReader() {
			this.separator = ',';
			this.enclosedBy = '"';
			this.escapeChar = '\\';
		} // end of CSVReader

		private static final boolean debugging = true;

		/*
		 * field separator character, usually ',' in North America,
		 * ';' in Europe and sometimes '\t' for tab.
		 */
		private char separator;
		/*
		 * field enclosed char usually ".
		 */
		private char enclosedBy;
		/*
		 * field enclosed char usually ".
		 */
		private char escapeChar;

		/**
		 * category of end of line char.
		 */
		private static final int EOL = 0;

		/**
		 * category of ordinary character
		 */
		private static final int ORDINARY = 1;

		/**
		 * categotory of the quote mark "
		 */
		private static final int QUOTE = 2;

		/**
		 * category of the separator, e.g. comma, semicolon
		 * or tab.
		 */
		private static final int SEPARATOR = 3;

		/**
		 * category of characters treated as white space.
		 */
		private static final int WHITESPACE = 4;

		/**
		 * categotory of the escape code
		 */
		private static final int ESCAPECODE = 5;

		/**
		 * categorise a character for the finite state machine.
		 *
		 * @param c the character to categorise
		 * @return integer representing the character's category.
		 */
		private int categorise(char c, char cPlus1, char cPlus2) {
			
			switch (c) {
			case ' ':
			case '\r':
			case 0xff:
				return WHITESPACE;
				// case ';':
				// case '!':
			case '#':
				//return EOL;
			case '\n':
				return EOL; /* artificially applied to end of line */
			default:
				if (c == separator) {
					/* dynamically determined so can't use as case label */
					return SEPARATOR;
				} else if ( c == this.enclosedBy ) {
					return QUOTE;
				} else if ( c == this.escapeChar ) {
					
					char empty = 0;
					int catPls1 = categorise (cPlus1,empty,empty);
					int catPls2 = categorise (cPlus2,empty,empty);
					
					if ( catPls1 == SEPARATOR || catPls1 == QUOTE ) {
						return ESCAPECODE;
					} else if  ( cPlus1 == 'N' && ( catPls2 == SEPARATOR || catPls2 == EOL || catPls2 == '\r' ) ) {
						return ESCAPECODE;
					} 
					
					return ORDINARY;
				} else if ('!' <= c && c <= '~') {
					/* do our tests in crafted order, hoping for an early return */
					return ORDINARY;
				} else if (0x00 <= c && c <= 0x20) {
					return WHITESPACE;
				} else if (Character.isWhitespace(c)) {
					return WHITESPACE;
				} else {
					return ORDINARY;
				}
			} // end of switch
		} // end of categorise

		/**
		 * parser: We are in blanks before the field.
		 */
		private static final int SEEKINGSTART = 0;

		/**
		 * parser: We are in the middle of an ordinary field.
		 */
		private static final int INPLAIN = 1;

		/**
		 * parser: e are in middle of field surrounded in quotes.
		 */
		private static final int INQUOTED = 2;

		/**
		 * parser: We have just hit a quote, might be doubled
		 * or might be last one.
		 */
		private static final int AFTERENDQUOTE = 3;

		/**
		 * parser: We are in blanks after the field looking for the separator
		 */
		private static final int SKIPPINGTAIL = 4;

		/**
		 * state of the parser's finite state automaton.
		 */

		/**
		 * The line we are parsing.
		 * null means none read yet.
		 * Line contains unprocessed chars. Processed ones are removed.
		 */
		private String line = null;

		/**
		 * How many lines we have read so far.
		 * Used in error messages.
		 */
		private int lineCount = 0;

		public String[] parseLine(String line) {
			Vector lineArray = new Vector();
			String token = null;
			String returnArray[] = null;
			this.line = line;
			
			if (this.line.endsWith("\n") == false) {
				this.line += "\n";
			}
			
			// reading values from line until null comes
			try {
				while (lineArray.size() == 0) {
					while ((token = get()) != null) {
						lineArray.add(token);
					} // end of while
				} // end of while
			} catch (EOFException e) {
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}

			returnArray = new String[lineArray.size()];

			for (int ii = 0; ii < lineArray.size(); ii++) {
				returnArray[ii] = lineArray.elementAt(ii).toString();
			} // end of for

			return returnArray;
		}

		/**
		 * Read one field from the CSV file
		 *
		 * @return String value, even if the field is numeric. Surrounded
		 * and embedded double quotes are stripped.
		 * possibly "". null means end of line.
		 *
		 * @exception EOFException
		 * at end of file after all the fields have
		 * been read.
		 *
		 * @exception IOException
		 * Some problem reading the file, possibly malformed data.
		 */
		private String get() throws EOFException, IOException {
			StringBuffer field = new StringBuffer(50);
			/* we implement the parser as a finite state automaton with five states. */

			int state = SEEKINGSTART; /* start seeking, even if partway through a line */
			/* don't need to maintain state between fields. */

			/* loop for each char in the line to find a field */
			/* guaranteed to leave early by hitting EOL */
			int i = 0;
			for (i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				char cPlus1 = 0;
				char cPlus2 = 0;
				
				if ( line.length () > i +1 ) {
					cPlus1=line.charAt(i+1);
				}

				if ( line.length () > i +2 ) {
					cPlus2=line.charAt(i+2);
				}
								
				int category = categorise(c, cPlus1, cPlus2);
				switch (state) {
				
				case SEEKINGSTART: {
					/* in blanks before field */
					switch (category) {
					case WHITESPACE:
						/* ignore */
						break;
					case QUOTE:
						state = INQUOTED;
						break;
					case ESCAPECODE:
						field.append(cPlus1);
						i += 1;
						break;
					case SEPARATOR:
						/* end of empty field */
						line = line.substring(i + 1);
						return "";	
					case EOL:
						/* end of line */
						line = null;
						return null;
					case ORDINARY:
						field.append(c);
						state = INPLAIN;
						break;
					}
					break;
				} // end of SEEKINGSTART
				case INPLAIN: {
					/* in middle of ordinary field */
					switch (category) {
					case QUOTE:
						throw new IOException("Malformed CSV stream. Missing quote at start of field on line "
								+ lineCount);
					case ESCAPECODE:
						field.append(cPlus1);
						i += 1;
						break;
					case SEPARATOR:
						/* done */
						line = line.substring(i + 1);
						return field.toString().trim();
					case EOL:
						line = line.substring(i); /* push EOL back */
						return field.toString().trim();
					case WHITESPACE:
						field.append(' ');
						break;
					case ORDINARY:
						field.append(c);
						break;
					}
					break;
				} // end of INPLAIN
				case INQUOTED: {
					/* in middle of field surrounded in quotes */
					switch (category) {
					case QUOTE:
						state = AFTERENDQUOTE;
						break;
					case ESCAPECODE:
						field.append(cPlus1);
						i += 1;
						break;
					case EOL:
						throw new IOException("Malformed CSV stream. Missing quote after field on line " + lineCount);
					case WHITESPACE:
						field.append(' ');
						break;
					case SEPARATOR:
					case ORDINARY:
						field.append(c);
						break;
					}
					break;
				} // end of INQUOTED
				case AFTERENDQUOTE: {
					/* In situation like this "xxx" which may
					 turn out to be xxx""xxx" or "xxx",
					 We find out here. */
					switch (category) {
					case QUOTE:
						field.append(c);
						state = INQUOTED;
						break;
					case SEPARATOR:
						/* we are done.*/
						line = line.substring(i + 1);
						return field.toString().trim();
					case ESCAPECODE:
						break;
					case EOL:
						line = line.substring(i); /* push back eol */
						return field.toString().trim();
					case WHITESPACE:
						/* ignore trailing spaces up to separator */
						state = SKIPPINGTAIL;
						break;
					case ORDINARY:
						throw new IOException("Malformed CSV stream, missing separator after field on line "
								+ lineCount);
					}
					break;
				} // end of AFTERENDQUOTE
				case SKIPPINGTAIL: {
					/* in spaces after field seeking separator */
					switch (category) {
					case SEPARATOR:
						/* we are done.*/
						line = line.substring(i + 1);
						return field.toString().trim();
					case EOL:
						line = line.substring(i); /* push back eol */
						return field.toString().trim();
					case ESCAPECODE:
						break;
					case WHITESPACE:
						/* ignore trailing spaces up to separator */
						break;
					case QUOTE:
					case ORDINARY:
						throw new IOException("Malformed CSV stream, missing separator after field on line "
								+ lineCount);
					} // end of switch
					break;
				} // end of SKIPPINGTAIL
				} // end switch(state)
				
			} // end for
			
			throw new IOException("Program logic bug. Should not reach here. Processing line " + lineCount);
		} // end get

		/**
		 * Skip over fields you don't want to process.
		 *
		 * @param fields How many field you want to bypass reading.
		 * The newline counts as one field.
		 * @exception EOFException
		 * at end of file after all the fields have
		 * been read.
		 * @exception IOException
		 * Some problem reading the file, possibly malformed data.
		 */
		public void skip(int fields) throws EOFException, IOException {
			if (fields <= 0) {
				return;
			}
			for (int i = 0; i < fields; i++) {
				// throw results away
				get();
			}
		} // end of skip

	} // end CSVReader

	// end of file
	 
}
