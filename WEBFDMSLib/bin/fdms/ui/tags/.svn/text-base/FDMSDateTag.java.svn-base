package fdms.ui.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.log4j.Logger;
import org.apache.struts.taglib.html.BaseFieldTag;


public class FDMSDateTag extends BaseFieldTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5503409905642643801L;

	private static final Logger logger = Logger.getLogger(FDMSDateTag.class.getName());

	private String javascriptFormField = null;

	private String fieldID = null;

	private String fieldValue = null;
	
	private String image = "images-old/calendar.gif";

	
	
	/**
	 * @return the fieldID
	 */
	public String getFieldID() {
		return fieldID;
	}


	/**
	 * @return the fieldValue
	 */
	public String getFieldValue() {
		return fieldValue;
	}


	/**
	 * @return the javascriptFormField
	 */
	public String getJavascriptFormField() {
		return javascriptFormField;
	}


	/**
	 * @param fieldID the fieldID to set
	 */
	public void setFieldID(String fieldID) {
		this.fieldID = fieldID;
	}


	/**
	 * @param fieldValue the fieldValue to set
	 */
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}


	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}


	/**
	 * @param javascriptFormField the javascriptFormField to set
	 */
	public void setJavascriptFormField(String javascriptFormField) {
		this.javascriptFormField = javascriptFormField;
	}

	public int doEndTag() throws JspException {
		
		if (javascriptFormField == null) {
			throw new JspException("The JSP tag requires a 'javascriptFormField' attribute");
		}
		
		if (fieldID == null) {
			throw new JspException("The JSP tag requires a 'fieldID' attribute");
		}
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<script type=\"text/javascript\">");
		sb.append("oDateMask.attach(" + javascriptFormField + ");</script>");
		sb.append("<a onMouseOver=\"window.status='Click to View Calendar'; return true;\" ");
		sb.append("onMouseOut=\"window.status='';\" href=\"javascript:doNothing()\"  ");
		sb.append("onClick=\"calPopUp.select(" + javascriptFormField + ",'"+fieldID+"', ");
		sb.append("'MM/dd/yyyy'," + javascriptFormField + ".value); return false;\"> ");
		sb.append("<img ID=\""+fieldID+"\" name=\""+fieldID+"\" src=\"" + image + "\" ");
		sb.append("width=\"16\" height=\"16\" border=\"0\" /></a> ");
		
		try {
			pageContext.getOut().print(sb.toString());
		} catch (IOException ex) {
			throw new JspException("IOException occurred while writing to client", ex);
		}
		
		sb = null;
		
		return EVAL_PAGE;
	}

	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	public void release() {
		javascriptFormField = null;
		fieldID = null;
		fieldValue = null;
		image = null;

		super.release();
	}
}
