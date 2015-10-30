package fdms.ui.tags;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.taglib.html.BaseFieldTag;

import com.aldorsolutions.webfdms.beans.DbCase;
import com.aldorsolutions.webfdms.beans.DbSpeedData;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.SessionValueKeys;


public class SpeedSelectTag extends BaseFieldTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5972964879580225730L;

	private static final Logger logger = Logger.getLogger(SpeedSelectTag.class.getName());

	private String mCategory = null;

	private Integer mIndex = null;

	private Integer mColumn = null;

	private boolean mCombo = false;

	/** Used to display full value of in combo option box field */
	private boolean mDisplayValueForOptions = false;

	private String mOnChange = null;

	private Integer mSize = null;

	private Integer mTextSize = null;

	private ArrayList userOptions = null;

	private Integer mIndexId = null;

	private boolean debug = false;

	public String getCategory() {
		return mCategory;
	}

	public void setCategory(String category) {
		mCategory = category;
	}

	public String getColumn() {
		String columnStr = null;
		if (mColumn != null)
			columnStr = mColumn.toString();
		return columnStr;
	}

	public void setColumn(String column) {
		try {
			mColumn = new Integer(column);
			if (mColumn.intValue() < 1)
				mColumn = null;
		} catch (NumberFormatException ex) {
			mColumn = null;
		}
	}

	public String getCombo() {
		return Boolean.toString(mCombo);
	}

	public void setCombo(String combo) {
		mCombo = Boolean.valueOf(combo).booleanValue();
	}

	public String getDisplayValueForOptions() {
		return Boolean.toString(mDisplayValueForOptions);
	}

	public void setDisplayValueForOptions(String displayValueForOptions) {
		mDisplayValueForOptions = Boolean.valueOf(displayValueForOptions).booleanValue();
	}

	public String getIndex() {
		String indexStr = null;
		if (mIndex != null)
			indexStr = mIndex.toString();
		return indexStr;
	}

	public void setIndex(String index) {
		try {
			mIndex = new Integer(index);
			if (mIndex.intValue() < 1)
				mIndex = null;
		} catch (NumberFormatException ex) {
			mIndex = null;
		}
	}

	public String getOnchange() {
		return mOnChange;
	}

	public void setOnchange(String onchange) {
		mOnChange = onchange;
	}

	public String getSize() {
		String sizeStr = null;
		if (mSize != null)
			sizeStr = mSize.toString();
		return sizeStr;
	}

	public void setSize(String size) {
		try {
			mSize = new Integer(size);
			if (mSize.intValue() < 1)
				mSize = null;
		} catch (NumberFormatException ex) {
			mSize = null;
		}
	}

	public String getTextsize() {
		String textSizeStr = null;
		if (mTextSize != null)
			textSizeStr = mTextSize.toString();
		return textSizeStr;
	}

	public void setTextsize(String textsize) {
		try {
			mTextSize = new Integer(textsize);
			if (mTextSize.intValue() < 1)
				mTextSize = null;
		} catch (NumberFormatException ex) {
			mTextSize = null;
		}
	}

	public ArrayList getUserOptions() {
		return userOptions;
	}

	public void setUserOptions(ArrayList uo) {
		userOptions = uo;
	}

	public String getIndexId() {
		String indexIdStr = null;
		if (mIndexId != null)
			indexIdStr = mIndexId.toString();

		if (debug) {
			logger.debug("mIndexId: " + indexIdStr);
		}

		return indexIdStr;
	}

	public void setIndexId(String indexId) {
		try {
			if (debug) {
				logger.debug("indexId: " + indexId);
			}

			mIndexId = new Integer(indexId);
			if (mIndexId.intValue() < 0)
				mIndexId = null;
		} catch (NumberFormatException ex) {
			mIndexId = null;
		}
	}
	
	
	private void populateEndTags (DbUserSession user) throws JspException {

		DatabaseTransaction dbTrans = null;
		try {
			dbTrans = (DatabaseTransaction) DatabaseTransaction.getTransaction(user);
			

			if (dbTrans == null)
				throw new JspException("The JSP tag couldn't initiate a database transaction");

			int vitalsId = user.getCurrentCaseID();
			int locationId = 0;

			DbSpeedData[] speedDataOptions = null;

			if (!user.getLocalizedSpeedData()) {
				speedDataOptions = FdmsDb.getInstance().getSpeedData(user.getDbLookup(), user.getRegion(), mCategory);
			} else {
				if (vitalsId > 0) {
					DbCase dbCase = FdmsDb.getInstance().getCase(dbTrans, vitalsId);
					locationId = dbCase.getChapelNumber();

					if (debug) {
						logger.debug("VitalsId : " + vitalsId);
						logger.debug("LocationId : " + locationId);
						logger.debug("IndexID : " + getIndexId() );
					}

				} else {
					locationId = user.getLocationId();

					if (debug) {
						logger.debug("LocationId : " + locationId);
						logger.debug("IndexID : " + getIndexId() );
					}
				}

				speedDataOptions = FdmsDb.getInstance().getSpeedData(user.getDbLookup(), user.getRegion(), mCategory);

				DbSpeedData[] speedDataOptionsLocation = FdmsDb.getInstance().getSpeedDataLocation(user.getDbLookup(),
						user.getRegion(), locationId, mCategory, null);
				speedDataOptions = getSpeedData(speedDataOptions, speedDataOptionsLocation);
				
				
			}

			if (debug) {
				logger.debug("SpeedDataOptions length : " + speedDataOptions.length);
			}

			String selectStr;

			selectStr = printComboBox(speedDataOptions);

			try {
				pageContext.getOut().print(selectStr);
			} catch (IOException ex) {
				throw new JspException("IOException occurred while writing to client", ex);
			}
			
			
		} catch (PersistenceException ex) {
			throw new JspException("PersistenceException while initiating a database transaction", ex);
		} finally {
			if ( dbTrans != null ) {
				dbTrans.closeConnection();
				dbTrans = null;
			}
		}

	}
	

	public int doEndTag() throws JspException {
		if (mCategory == null) {
			throw new JspException("The JSP tag requires a 'category' attribute");
		}

		HttpSession session = pageContext.getSession();
		if (session == null) {
			throw new JspException("The JSP tag requires a J2EE session");
		}

		DbUserSession user = (DbUserSession) session.getAttribute(SessionValueKeys.DB_USER);
		if (user == null) {
			throw new JspException("The JSP tag requires a session user");
		}

		populateEndTags(user);
		
		// Shouldn't be necessary, since this happens in release()
		removeValue("linkOptions");
		removeValue("targetFields");

		return EVAL_PAGE;
	}

	public int doStartTag() throws JspException {
		userOptions = new ArrayList();
		return EVAL_BODY_INCLUDE;
	}

	private String buildAutoFocusScript(String id, String targets, String columns, String linkOptionsScriptStr) {
		StringBuilder sb = new StringBuilder("focusAutoSelect(document.getElementById('" +id + "'), ");

		String col;
		if (getColumn() == null) {
			col = "null";
		}
		else {
			col = getColumn().toString();
		}

		sb.append(col);
		sb.append(", '" + targets + "', '" + columns + "', '");
		sb.append(linkOptionsScriptStr + "');");

		String focus;
		if (getOnfocus() == null) {
			focus = "";
		}
		else {
			focus = getOnfocus();
		}

		sb.append(focus);
			
		return ( sb.toString() );
	}
	
	public void release() {
		mCategory = null;
		mCombo = false;
		mOnChange = null;
		mSize = null;
		mTextSize = null;

		removeValue("linkOptions");
		removeValue("targetFields");

		super.release();
	}

	private String printComboBox(DbSpeedData[] speedDataOptions) throws JspException {
		String optionsStr = "";
		int optionCount = 0;

		String value = getValue();
		if (value == null) {
			Object bean = pageContext.findAttribute(name);
			if (bean == null)
				throw new JspException("Bean '" + name + "' not found");
			try {
				if (mIndex != null)
					value = BeanUtils.getIndexedProperty(bean, property, mIndex.intValue());
				else
					value = BeanUtils.getProperty(bean, property);
				if (value == null)
					value = "";
			} catch (IllegalAccessException ex) {
				throw new JspException("Bean '" + name + "' not found", ex);
			} catch (InvocationTargetException ex) {
				throw new JspException("Property '" + property + "' not found in bean '" + name + "'", ex
						.getTargetException());
			} catch (NoSuchMethodException ex) {
				throw new JspException("Property '" + property + "' not found in bean '" + name + "'", ex);
			}
		}

		int valueOptionCount = 0;

		for (valueOptionCount = 0; valueOptionCount < userOptions.size(); valueOptionCount++) {
			optionsStr += " " + userOptions.get(valueOptionCount).toString();
			logger.debug(userOptions.get(valueOptionCount).toString());
		}
		
		

		if (mCategory != null && mCategory.compareToIgnoreCase("Invimages-old")==0) {
			
			ArrayList imagesList = new ArrayList();
			
			String curDir;
			curDir = this.pageContext.getServletContext().getRealPath("/pictures");
			File mydir = new File(curDir);
			String[] children = mydir.list();
			for (int i=0; i<children.length; i++){
			    if (children[i].indexOf(".jpg") > -1 || children[i].indexOf(".bmp") > -1 ||
				    children[i].indexOf(".gif") > -1 || children[i].indexOf(".img") > -1) {
				       String filename = "pictures\\"+children[i];
				       File testfile = new File(curDir+"\\"+children[i]);
				       if (!testfile.isDirectory()){
				    	   
				    	  DbSpeedData image = new DbSpeedData();
				    	  image.setCategory(mCategory);
				    	  image.setData(filename);
				    	  
				    	  imagesList.add(image);
					   } 
				}
			}
			
			speedDataOptions = new DbSpeedData [imagesList.size()]; 
			for ( int i = 0; i < imagesList.size(); i++ ) {
				speedDataOptions[i] = (DbSpeedData) imagesList.get(i);
			}
			
		}
		
		if ( speedDataOptions != null ) {

			for (optionCount = 0; optionCount < speedDataOptions.length; optionCount++) {
				
				String optionText = speedDataOptions[optionCount].getData();
				
				if (mColumn == null) {
					mColumn = new Integer(0);
				}
				
				String optionValue = formatValue(CsvTable.getField(optionText, 0));
				optionText = formatValue(CsvTable.getField(optionText, mColumn.intValue()));

				if ((optionText == null) || (optionValue == null)) {
					continue;
				}

				String selectedAttr = ((value != null) && (optionValue.equals(value))) ? " selected" : "";

				optionsStr += createOptionString(optionValue, optionText, selectedAttr);

			}

		}

		valueOptionCount += optionCount;

		String linkOptionsScriptStr = "";
		String linkOptionsStr = "";

		if (getValue("linkOptions") != null) {
			Iterator linkOptions = ((List) getValue("linkOptions")).iterator();
			for (; linkOptions.hasNext(); optionCount++) {
				Hashtable linkOption = (Hashtable) linkOptions.next();

				String features = (String) linkOption.get("features");
				String script = (String) linkOption.get("script");
				String target = (String) linkOption.get("target");
				String text = (String) linkOption.get("text");
				String url = (String) linkOption.get("url");
				String loss = "";

				linkOptionsStr += "  <option value=\"\">" + text + "</option>\n";

				if (script != null)
					loss += escapeQuotes(script);
				else if (url != null) {
					if (target != null) {
						loss += "open('" + escapeQuotes(url) + "', '" + escapeQuotes(target) + "'";
						if (features != null)
							loss += ", '" + escapeQuotes(features) + "'";
						loss += ");";
					} else
						loss += "location = '" + escapeQuotes(url) + "';";
				}

				if (linkOptionsScriptStr.length() > 0) {
					linkOptionsScriptStr += "#";
				}
				linkOptionsScriptStr += loss;
			}
		}

		int selectSize = (mSize != null) ? mSize.intValue() : 1;
		if (selectSize < 2)
			selectSize = 2;
		selectSize = (selectSize < 10) ? selectSize : 10;
		String sizeAttr = formatAttribute("size", Integer.toString(selectSize));

		String targets = "";
		String columns = "";
		if (getValue("targetFields") != null) {
			Iterator targetFields = ((List) getValue("targetFields")).iterator();
			while (targetFields.hasNext()) {
				Hashtable targetField = (Hashtable) targetFields.next();
				if (targets.length() > 0) {
					targets += "|";
					columns += "|";
				}
				targets += (String) targetField.get("property");
				columns += (String) targetField.get("column");
			}
		}

		String styleClass = "";
		if (getStyleClass() != null)
			styleClass += " " + getStyleClass();

		StringBuilder divStrBuf = new StringBuilder();
		String id = getProperty();

		// generate a name with an index if the iterator tag is set
		String speedSelectName = id;
		
		if ( debug ) {
			logger.debug(" getIndexID: " + getIndexId() );
		}
		
		if (getIndexId() != null) {
			// convert property to be capitalized
			// add the name of the tag before the property and then place the
			// index. This is the name.
			// This is done because a category is necisary as well as an index
			speedSelectName = (speedSelectName.substring(0, 1)).toUpperCase() + speedSelectName.substring(1);
			speedSelectName = getName() + speedSelectName + "[" + getIndexId() + "]";
			id += "[" + getIndexId() + "]";
		}
		String nameAttr = formatAttribute("name", speedSelectName);
		String idAttr = formatAttribute("id", id + "div");

		divStrBuf.append("<div style=\"display:inline; position:relative; padding:0px; margin:0px;\">");
		divStrBuf.append("<div " + idAttr);
		divStrBuf.append(formatAttribute("title", getTitle()) + " style=\"");
		divStrBuf.append("position:relative; top:0px; left:0px; display:inline; ");
		divStrBuf.append("border-width:1px; border-color:#7F9DB9; border-style:solid; ");
		divStrBuf.append("white-space:nowrap; margin:0px 0px 0px 0px; padding:0px 0px 0px 4px; font-size:18px;\">");

		int textsz = mTextSize.intValue();

		if (mCombo && (linkOptionsScriptStr.length() > 0)) {
			textsz -= 3;
		}

		String textSizeAttr = formatAttribute("size", Integer.toString(textsz));
		String maxLengthAttr = formatAttribute("maxlength", getMaxlength());

		StringBuilder textBoxStrBuf = new StringBuilder();
		idAttr = formatAttribute("id", id);
		textBoxStrBuf.append("<input type=\"text\"");
		textBoxStrBuf.append(formatAttribute("id", id));
		textBoxStrBuf.append(nameAttr);
		textBoxStrBuf.append(textSizeAttr);
		textBoxStrBuf.append(maxLengthAttr);
		textBoxStrBuf.append(formatAttribute("title", getTitle()));
		textBoxStrBuf.append(formatAttribute("value", formatValue(value)));
		if (mCombo == false) {
			textBoxStrBuf.append(" readonly=\"true\"");
		}
			
		if (getStyle() == null) {
			setStyle("");
		}
		
		textBoxStrBuf.append(" style=\"" + getStyle()
				+ "vertical-align:top; margin:2px 0px 0px 0px; border:0px;\"");
		
		textBoxStrBuf.append(" onfocus=\"" + buildAutoFocusScript(id, targets, columns, 
				linkOptionsScriptStr) + "\" " );
		
		textBoxStrBuf.append(" onBlur=\"this.autoSelect.hideComboList();\" " );
		
		textBoxStrBuf.append(formatAttribute("onblur", getOnblur()));
		textBoxStrBuf.append(formatAttribute("onclick", getOnclick()));
		textBoxStrBuf.append(formatAttribute("ondblclick", getOndblclick()));
		textBoxStrBuf.append(formatAttribute("onkeydown", getOnkeydown()));
		textBoxStrBuf.append(formatAttribute("onkeypress", getOnkeypress()));
		textBoxStrBuf.append(formatAttribute("onkeyup", getOnkeyup()));
		textBoxStrBuf.append(formatAttribute("onmousedown", getOnmousedown()));
		textBoxStrBuf.append(formatAttribute("onmousemove", getOnmousemove()));
		textBoxStrBuf.append(formatAttribute("onmouseout", getOnmouseout()));
		textBoxStrBuf.append(formatAttribute("onmouseover", getOnmouseover()));
		textBoxStrBuf.append(formatAttribute("onmouseup", getOnmouseup()));
		textBoxStrBuf.append(formatAttribute("onchange", getOnchange()));
		textBoxStrBuf.append(" />");

		StringBuilder editBtnStrBuf = new StringBuilder();
		idAttr = formatAttribute("id", id + "edit");
		editBtnStrBuf.append("<img" + idAttr);
		editBtnStrBuf.append(" onclick=\"" 
				+ buildAutoFocusScript(id, targets, columns, linkOptionsScriptStr)
				+ " firstAutoSelectClick(document.getElementById('" +id + "'), '" + id + "'); evalScript (this.autoSelect.scripts);\"");
		editBtnStrBuf.append(" style=\"");
		
		if ((linkOptionsScriptStr.length() <= 0) || !mCombo) {
			editBtnStrBuf.append("display:none;");
		}
		
		editBtnStrBuf.append(" vertical-align:top; margin:1px 0px 0px 0px; padding:0px;\"");
		editBtnStrBuf.append(" src=\"images/editbtn.gif\" />");

		StringBuilder imageStrBuf = new StringBuilder();
		idAttr = formatAttribute("id", id + "btn");
		imageStrBuf.append("<img" + idAttr);
		imageStrBuf.append(" onclick=\"" 
				+ buildAutoFocusScript(id, targets, columns, linkOptionsScriptStr)
				+ " firstAutoSelectClick(document.getElementById('" +id + "'), '" + id + "');\"");
		
		imageStrBuf.append(" style=\"vertical-align:top; margin:1px 1px 0px 0px; padding:0px;\"");
		imageStrBuf.append(" src=\"images/combobtn.gif\" /></div>      ");

		imageStrBuf.append("</div>"); // outer Div
		
		

		StringBuilder selectStrBuf = new StringBuilder();
		
		idAttr = formatAttribute("id", id + "combo");
		selectStrBuf.append("<div" + idAttr);
		selectStrBuf.append(" style=\"border:1px; position:absolute; border-color:#000000; border-style:solid; " +
				"visibility: hidden; display: none; z-index: 600;\">");
		
		idAttr = formatAttribute("id", id + "select");
		selectStrBuf.append("<select" + idAttr);
		selectStrBuf.append(sizeAttr);
		selectStrBuf.append(maxLengthAttr + " ");
		selectStrBuf.append(formatAttribute("onchange", getOnchange()));
		selectStrBuf.append(" onclick=\"" + 
				buildAutoFocusScript(id, targets, columns, linkOptionsScriptStr) +
				" this.autoSelect.toggleComboList();\" >");
		selectStrBuf.append(optionsStr);
		selectStrBuf.append("</select></div>\n");
		
		
		StringBuilder frameStrBuf = new StringBuilder();
		
		frameStrBuf.append("<iframe" + formatAttribute("id", id + "ifrm") );
		frameStrBuf.append(formatAttribute("src", "javascript:false;") );
		frameStrBuf.append(formatAttribute("frameBorder", "0") );
		frameStrBuf.append(formatAttribute("scrolling", "no") );
		frameStrBuf.append(formatAttribute("style", "position:absolute; top:0px; left:0px; z-index: 599; display:none;") + ">" );
		frameStrBuf.append("</iframe>\n");
		
		frameStrBuf.append("<script type='text/javascript'>\n");
		frameStrBuf.append(buildAutoFocusScript(id, targets, columns, linkOptionsScriptStr) );
		frameStrBuf.append("</script>\n");

		StringBuilder comboBoxStrBuf = new StringBuilder();
		comboBoxStrBuf.append(divStrBuf);
		comboBoxStrBuf.append(textBoxStrBuf);
		comboBoxStrBuf.append(editBtnStrBuf);
		comboBoxStrBuf.append(imageStrBuf);
		comboBoxStrBuf.append(selectStrBuf);
		comboBoxStrBuf.append(frameStrBuf);

		return comboBoxStrBuf.toString();
	}

	private String createOptionString(String optionValue, String optionText, String selectedAttr) {

		String optionStr = null;

		if (mDisplayValueForOptions) {
			StringTokenizer sToken = new StringTokenizer(optionValue, ",");
			StringBuilder buffer = new StringBuilder();
			List targetList = (List) getValue("targetFields");
			long targetCount = 0;

			if (targetList != null) {
				targetCount = targetList.size();
			}

			long index = 0;
			while (sToken.hasMoreTokens()) {
				String token = (String) sToken.nextToken();

				if ((targetCount > 0) && (index > targetCount)) {
					continue;
				}

				if (token != null) {
					buffer.append(token);
				}

				if (sToken.hasMoreTokens()) {
					buffer.append(" ");
				}

				index++;
			}

			optionStr = "  <option" + selectedAttr + " value=\"" + optionValue + "\">" + buffer.toString()
					+ "</option>\n";
		} else {
			optionStr = "  <option" + selectedAttr + " value=\"" + optionValue + "\">" + optionText
					+ "</option>\n";
		}

		return (optionStr);
	}

	private String formatAttribute(String htmlAttributeName, String attributeValue) {
		String result = "";
		if (attributeValue != null)
			result = " " + htmlAttributeName + "=\"" + attributeValue + "\"";
		return result;
	}
	public static String escapeQuotes(String x) {
		StringBuilder sb = new StringBuilder(x.length());
		int length = x.length();
		char c;

		for (int i = 0; i < length; i++) {
			c = x.charAt(i);
			if (c == '\'')
				sb.append("\\'");
			else if (c == '\"')
				sb.append("\\\"");
			else
				sb.append(c);
		}
		return sb.toString();
	}

	public static String formatValue(String string) {
		if (string == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder(string.length());
		// true if last char was blank
		boolean lastWasBlankChar = false;
		int len = string.length();
		char c;

		for (int i = 0; i < len; i++) {

			c = string.charAt(i);
			if (c == ' ') {
				// blank gets extra work,
				// this solves the problem you get if you replace all
				// blanks with &nbsp;, if you do that you loss
				// word breaking
				if (lastWasBlankChar) {
					lastWasBlankChar = false;
					sb.append("&nbsp;");
				} else {
					lastWasBlankChar = true;
					sb.append(' ');
				}
			} else {
				lastWasBlankChar = false;
				// HTML Special Chars
				if (c == '"') {
					sb.append("&quot;");
				} else if (c == '&') {
					sb.append("&amp;");
				} else if (c == '<') {
					sb.append("&lt;");
				} else if (c == '>') {
					sb.append("&gt;");
				} else if (c == '\n') {
					// Handle Newline
					sb.append("&lt;br/&gt;");
				} else {
					int ci = 0xffff & c;
					if (ci < 160) {
						// nothing special only 7 Bit
						sb.append(c);
					} else {
						// Not 7 Bit use the unicode system
						sb.append("&#");
						sb.append(new Integer(ci).toString());
						sb.append(';');
					}
				}
			}
		}
		return sb.toString();
	}

	public DbSpeedData[] getSpeedData(DbSpeedData[] speedDataOptions, DbSpeedData[] speedDataOptionsLocation) {

		if (speedDataOptionsLocation.length > 0) {
			return speedDataOptionsLocation;
		} else {
			return speedDataOptions;
		}

		/*
		 * if ((speedDataOptions != null) && (speedDataOptionsLocation != null)) {
		 * int y = 0; speedData = new DbSpeedData[speedDataOptions.length +
		 * speedDataOptionsLocation.length];
		 * 
		 * for (int i = 0; i < speedDataOptions.length; i++) { speedData[y++] =
		 * speedDataOptions[i]; }
		 * 
		 * for (int i = 0; i < speedDataOptionsLocation.length; i++) {
		 * speedData[y++] = speedDataOptionsLocation[i]; } }
		 * 
		 * return speedData;
		 */
	}
}
