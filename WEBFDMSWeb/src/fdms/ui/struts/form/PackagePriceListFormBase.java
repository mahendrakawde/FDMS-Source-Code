package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class PackagePriceListFormBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -7219133772841954305L;
private String submitbutton;  
  private String directive;
  private String saveButton;
  private String priceListVersion;
  private String pkgPriceListId;
  private int optionSelectedIndex;
  
  // Used to control access to the 'Included' form elements.
  private String serviceIncluded;
  private String serviceIncludedId;
  private String removeIncludedButton;
   
  // Used to control access to the 'Not Included' form elements.
  private String serviceNotIncluded;
  private String removeNotIncludedButton;
  private String serviceNotIncludedId;  
  private String serviceNotIncludedDescription;
  private String saveLineButton;

  public String getDirective () {
	return directive ;
  }  
/**
 * Insert the method's description here.
 * Creation date: (8/9/2002 10:22:16 AM)
 * @return java.lang.String
 */
public int getOptionSelectedIndex() {
	return optionSelectedIndex;
}
/**
 * Insert the method's description here.
 * Creation date: (8/8/2002 10:14:00 AM)
 * @return java.lang.String
 */
public java.lang.String getPkgPriceListId() {
	return pkgPriceListId;
}
/**
 * Insert the method's description here.
 * Creation date: (8/8/2002 9:17:17 AM)
 * @return java.lang.String
 */
public java.lang.String getPriceListVersion() {
	return priceListVersion;
}
/**
 * Insert the method's description here.
 * Creation date: (8/7/2002 10:06:50 AM)
 * @return java.lang.String
 */
public java.lang.String getRemoveIncludedButton() {
	return removeIncludedButton;
}
/**
 * Insert the method's description here.
 * Creation date: (8/7/2002 10:07:11 AM)
 * @return java.lang.String
 */
public java.lang.String getRemoveNotIncludedButton() {
	return removeNotIncludedButton;
}
/**
 * Insert the method's description here.
 * Creation date: (8/7/2002 10:05:58 AM)
 * @return java.lang.String
 */
public java.lang.String getSaveButton() {
	return saveButton;
}
/**
 * Insert the method's description here.
 * Creation date: (8/7/2002 10:06:21 AM)
 * @return java.lang.String
 */
public java.lang.String getSaveLineButton() {
	return saveLineButton;
}
/**
 * Insert the method's description here.
 * Creation date: (8/9/2002 9:27:08 AM)
 * @return java.lang.String
 */
public java.lang.String getServiceIncluded() {
	return serviceIncluded;
}
  public String getServiceIncludedId () {
	return serviceIncludedId ;
  }  
/**
 * Insert the method's description here.
 * Creation date: (8/9/2002 9:27:21 AM)
 * @return java.lang.String
 */
public java.lang.String getServiceNotIncluded() {
	return serviceNotIncluded;
}
  public String getServiceNotIncludedDescription () {
	return serviceNotIncludedDescription ;
  }  
  public String getServiceNotIncludedId () {
	return serviceNotIncludedId ;
  }  
  public String getSubmitbutton () {
	return submitbutton ;
  }  
  public void setDirective (String in) {
	directive = in;
  }  
/**
 * Insert the method's description here.
 * Creation date: (8/9/2002 10:22:16 AM)
 * @param newOptionSelectedIndex java.lang.String
 */
public void setOptionSelectedIndex(int newOptionSelectedIndex) {
	optionSelectedIndex = newOptionSelectedIndex;
}
/**
 * Insert the method's description here.
 * Creation date: (8/8/2002 10:14:00 AM)
 * @param newPkgPriceListId java.lang.String
 */
public void setPkgPriceListId(java.lang.String newPkgPriceListId) {
	pkgPriceListId = newPkgPriceListId;
}
/**
 * Insert the method's description here.
 * Creation date: (8/8/2002 9:17:17 AM)
 * @param newPriceListVersion java.lang.String
 */
public void setPriceListVersion(java.lang.String newPriceListVersion) {
	priceListVersion = newPriceListVersion;
}
/**
 * Insert the method's description here.
 * Creation date: (8/7/2002 10:06:50 AM)
 * @param newRemoveIncludedButton java.lang.String
 */
public void setRemoveIncludedButton(java.lang.String newRemoveIncludedButton) {
	removeIncludedButton = newRemoveIncludedButton;
}
/**
 * Insert the method's description here.
 * Creation date: (8/7/2002 10:07:11 AM)
 * @param newRemoveNotIncludedButton java.lang.String
 */
public void setRemoveNotIncludedButton(java.lang.String newRemoveNotIncludedButton) {
	removeNotIncludedButton = newRemoveNotIncludedButton;
}
/**
 * Insert the method's description here.
 * Creation date: (8/7/2002 10:05:58 AM)
 * @param newSaveButton java.lang.String
 */
public void setSaveButton(java.lang.String newSaveButton) {
	saveButton = newSaveButton;
}
/**
 * Insert the method's description here.
 * Creation date: (8/7/2002 10:06:21 AM)
 * @param newSaveLineButton java.lang.String
 */
public void setSaveLineButton(java.lang.String newSaveLineButton) {
	saveLineButton = newSaveLineButton;
}
/**
 * Insert the method's description here.
 * Creation date: (8/9/2002 9:27:08 AM)
 * @param newServiceIncluded java.lang.String
 */
public void setServiceIncluded(java.lang.String newServiceIncluded) {
	serviceIncluded = newServiceIncluded;
}
  public void setServiceIncludedId (String in) {
	serviceIncludedId = in;
  }  
/**
 * Insert the method's description here.
 * Creation date: (8/9/2002 9:27:21 AM)
 * @param newServiceNotIncluded java.lang.String
 */
public void setServiceNotIncluded(java.lang.String newServiceNotIncluded) {
	serviceNotIncluded = newServiceNotIncluded;
}
  public void setServiceNotIncludedDescription (String in) {
	serviceNotIncludedDescription = in;
  }  
  public void setServiceNotIncludedId (String in) {
	serviceNotIncludedId = in;
  }  
  public void setSubmitbutton (String in) {
	submitbutton = in;
  }  
}
