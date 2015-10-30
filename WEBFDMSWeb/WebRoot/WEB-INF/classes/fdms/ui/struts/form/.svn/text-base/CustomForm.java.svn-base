package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;


public class CustomForm extends CustomFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1373196928872690469L;
	private java.lang.String directive;
	private java.util.ArrayList shortCustom;
	private java.util.ArrayList longCustom;
	private java.lang.String enableHeadings;
	private com.aldorsolutions.webfdms.util.OptionsList[] shortcusta;
/**
 * Insert the method's description here.
 * Creation date: (7/1/2002 2:12:35 PM)
 * @return java.lang.String
 */
public java.lang.String getDirective() {
	return directive;
}
/**
 * Insert the method's description here.
 * Creation date: (7/8/2002 11:44:27 AM)
 * @return java.lang.String
 */
public java.lang.String getEnableHeadings() {
	return enableHeadings;
}
/**
 * Insert the method's description here.
 * Creation date: (7/8/2002 10:52:26 AM)
 * @return java.util.ArrayList
 */
public java.util.ArrayList getLongCustom() {
	return longCustom;
}
/**
 * Insert the method's description here.
 * Creation date: (7/8/2002 4:48:17 PM)
 * @return fdms.ui.struts.OptionsList[]
 */
public com.aldorsolutions.webfdms.util.OptionsList[] getShortcusta() {
	return shortcusta;
}
/**
 * Insert the method's description here.
 * Creation date: (7/8/2002 4:48:17 PM)
 * @return fdms.ui.struts.OptionsList[]
 */
public com.aldorsolutions.webfdms.util.OptionsList getShortcusta(int x) {
	if (x < 0 || x >39) x=0;
	return shortcusta[x];
}
/**
 * Insert the method's description here.
 * Creation date: (7/8/2002 10:52:01 AM)
 * @return java.util.ArrayList
 */
public java.util.ArrayList getShortCustom() {
	return shortCustom;
}
  public void reset(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	super.reset(actionMapping,  request);
  }  
/**
 * Insert the method's description here.
 * Creation date: (7/1/2002 2:12:35 PM)
 * @param newDirective java.lang.String
 */
public void setDirective(java.lang.String newDirective) {
	directive = newDirective;
}
/**
 * Insert the method's description here.
 * Creation date: (7/8/2002 11:44:27 AM)
 * @param newEnableHeadings java.lang.String
 */
public void setEnableHeadings(java.lang.String newEnableHeadings) {
	enableHeadings = newEnableHeadings;
}
/**
 * Insert the method's description here.
 * Creation date: (7/8/2002 10:52:26 AM)
 * @param newLongCustom java.util.ArrayList
 */
public void setLongCustom(java.util.ArrayList newLongCustom) {
	longCustom = newLongCustom;
}
/**
 * Insert the method's description here.
 * Creation date: (7/8/2002 4:48:17 PM)
 * @param newShortcusta fdms.ui.struts.OptionsList[]
 */
public void setShortcusta(com.aldorsolutions.webfdms.util.OptionsList[] newShortcusta) {
	shortcusta = newShortcusta;
}
/**
 * Insert the method's description here.
 * Creation date: (7/8/2002 4:48:17 PM)
 * @param newShortcusta fdms.ui.struts.OptionsList[]
 */
public void setShortcusta(int index, com.aldorsolutions.webfdms.util.OptionsList newShortcusta) {
	if (index < 0 || index > 39) index=0;
	shortcusta[index] = newShortcusta;
}
/**
 * Insert the method's description here.
 * Creation date: (7/8/2002 10:52:01 AM)
 * @param newShortCustom java.util.ArrayList
 */
public void setShortCustom(java.util.ArrayList newShortCustom) {
	shortCustom = newShortCustom;
}
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	return super.validate(actionMapping,  request);
  }  
}
