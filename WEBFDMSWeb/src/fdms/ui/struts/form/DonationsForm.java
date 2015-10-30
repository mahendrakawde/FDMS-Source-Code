package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;


public class DonationsForm extends DonationsFormBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5020769067085156114L;
	private java.lang.String donationLetter;
	private java.lang.String previewFile;
/**
 * Insert the method's description here.
 * Creation date: (7/12/2002 2:53:11 PM)
 * @return java.lang.String
 */
public java.lang.String getDonationLetter() {
	return donationLetter;
}
/**
 * Insert the method's description here.
 * Creation date: (7/12/2002 2:57:30 PM)
 * @return java.lang.String
 */
public java.lang.String getPreviewFile() {
	return previewFile;
}
  public void reset(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	super.reset(actionMapping,  request);
  }  
/**
 * Insert the method's description here.
 * Creation date: (7/12/2002 2:53:11 PM)
 * @param newDonationLetter java.lang.String
 */
public void setDonationLetter(java.lang.String newDonationLetter) {
	donationLetter = newDonationLetter;
}
/**
 * Insert the method's description here.
 * Creation date: (7/12/2002 2:57:30 PM)
 * @param newPreviewFile java.lang.String
 */
public void setPreviewFile(java.lang.String newPreviewFile) {
	previewFile = newPreviewFile;
}
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	return super.validate(actionMapping,  request);
  }  
}
