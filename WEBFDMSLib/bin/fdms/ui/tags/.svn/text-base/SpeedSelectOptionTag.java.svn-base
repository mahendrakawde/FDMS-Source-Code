/*
 * Created on Apr 27, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fdms.ui.tags;

import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts.util.RequestUtils;


/**
 * @author Gregg
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SpeedSelectOptionTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1989981395394044795L;
	/**
	 * Is this option disabled?
	 */
	protected boolean disabled = false;
	private String text;
 
	public boolean getDisabled() {
		return (this.disabled);
	}
 
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
 
	/**
	 * The key used to look up the text displayed to the user for this
	 * option, if any.
	 */
	protected String key = null;
 
	public String getKey() {
		return (this.key);
	}
 
	public void setKey(String key) {
		this.key = key;
	}
 
	/**
	 * The style associated with this tag.
	 */
	private String style = null;
 
	public String getStyle() {
		return style;
	}
 
	public void setStyle(String style) {
		this.style = style;
	}
 
	/**
	 * The named style class associated with this tag.
	 */
	private String styleClass = null;
 
	public String getStyleClass() {
		return styleClass;
	}
 
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
 
	/**
	 * The identifier associated with this tag.
	 */
	protected String styleId = null;
 
	/**
	 * Return the style identifier for this tag.
	 */
	public String getStyleId() {
		return (this.styleId);
	}
 
	/**
	 * Set the style identifier for this tag.
	 *
	 * @param styleId The new style identifier
	 */
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
 
	/**
	 * The server value for this option, also used to match against the
	 * current property value to determine whether this option should be
	 * marked as selected.
	 */
	protected String value = null;
 
	public String getValue() {
		return (this.value);
	}
 
	public void setValue(String value) {
		this.value = value;
	}
 
	// --------------------------------------------------------- Public Methods
 
	/**
	 * Process the start of this tag.
	 *
	 * @exception JspException if a JSP exception has occurred
	 */
	public int doStartTag() throws JspException {
		// Initialize the placeholder for our body content
		this.text = null;
 
		// Do nothing until doEndTag() is called
		return (EVAL_BODY_BUFFERED);
	}
 
	/**
	 * Process the body text of this tag (if any).
	 *
	 * @exception JspException if a JSP exception has occurred
	 */
	public int doAfterBody() throws JspException {
		if (bodyContent != null) {
			String text = bodyContent.getString();
			if (text != null) {
				text = text.trim();
				if (text.length() > 0) {
					this.text = text;
				}
			}
		}
		return (SKIP_BODY);
	}
 
	/**
	 * Process the end of this tag.
	 *
	 * @exception JspException if a JSP exception has occurred
	 */
	public int doEndTag() throws JspException {
		ArrayList options = this.selectTag().getUserOptions();
		options.add(this.renderOptionElement());
		this.selectTag().setUserOptions(options);
		//ResponseUtils.write(pageContext, this.renderOptionElement());
		return (EVAL_PAGE);
	}
 
	/**
	 * Generate an HTML <option> element.
	 * @throws JspException
	 * @since Struts 1.1
	 */
	protected String renderOptionElement() throws JspException {
		StringBuffer results = new StringBuffer("<option value=\"");
 
		results.append(this.value);
		results.append("\"");
		
		if (disabled) {
			results.append(" disabled=\"disabled\"");
		}
		if (style != null) {
			results.append(" style=\"");
			results.append(style);
			results.append("\"");
		}
		if (styleId != null) {
			results.append(" id=\"");
			results.append(styleId);
			results.append("\"");
		}
		if (styleClass != null) {
			results.append(" class=\"");
			results.append(styleClass);
			results.append("\"");
		}
		results.append(">");
		
        results.append(text());
        
        results.append("</option>");
        return results.toString();
	}

	/**
	 * Acquire the select tag we are associated with.
	 * @throws JspException
	 */
	private SpeedSelectTag selectTag() throws JspException {
		SpeedSelectTag selectTag = (SpeedSelectTag)this.getParent();

		if (selectTag == null) {
			JspException e = new JspException("<fdms:option> found without <fdms:speedselect>");
			RequestUtils.saveException(pageContext, e);
            throw e;
		}
		return selectTag;
    }

	/**
	 * Release any acquired resources.
	 */
	public void release() {
		super.release();
		disabled = false;
		key = null;
		style = null;
		styleClass = null;
		text = null;
		value = null;
	}

	// ------------------------------------------------------ Protected Methods

	/**
	 * Return the text to be displayed to the user for this option (if any).
	 *
	 * @exception JspException if an error occurs
	 */
	protected String text() throws JspException {
		String optionText = this.text;

		// no body text
		if (optionText == null) {
			optionText = this.value;    
		}

		return optionText;
	}
}
