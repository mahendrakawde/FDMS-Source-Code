package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class MiscCashReceiptsFormBase extends ActionForm {

  /**
	 * 
	 */
	private static final long serialVersionUID = 4751885689255313582L;
private int x;
  private int y;
  private String submitButton;
  private String receiptNumber;
  private String glAcct;
  private String amountOfTran;
  private String locationId;
  private String dateOfTran;
  private String payMethod;
  private String description;
  private String arAcct;
  private String manualReceiptNo;
  private String formId;
  private String previewFile;
  public String getAmountOfTran () {
	return amountOfTran ;
  }  
  public String getArAcct () {
	return arAcct ;
  }  
  public String getDateOfTran () {
	return dateOfTran ;
  }  
  public String getDescription () {
	return description ;
  }  
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 2:23:06 PM)
 * @return java.lang.String
 */
public java.lang.String getFormId() {
	return formId;
}
  public String getGlAcct () {
	return glAcct ;
  }  
  public String getLocationId () {
	return locationId ;
  }  
  public String getManualReceiptNo () {
	return manualReceiptNo ;
  }  
  public String getPayMethod () {
	return payMethod ;
  }  
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 2:32:14 PM)
 * @return java.lang.String
 */
public java.lang.String getPreviewFile() {
	return previewFile;
}
  public String getReceiptNumber () {
	return receiptNumber ;
  }  
  public String getSubmitButton () {
	return submitButton ;
  }  
  public int getX () {
	return x ;
  }  
  public int getY () {
	return y ;
  }  
  public void setAmountOfTran (String in) {
	amountOfTran = in;
  }  
  public void setArAcct (String in) {
	arAcct = in;
  }  
  public void setDateOfTran (String in) {
	dateOfTran = in;
  }  
  public void setDescription (String in) {
	description = in;
  }  
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 2:23:06 PM)
 * @param newFormId java.lang.String
 */
public void setFormId(java.lang.String newFormId) {
	formId = newFormId;
}
  public void setGlAcct (String in) {
	glAcct = in;
  }  
  public void setLocationId (String in) {
	locationId = in;
  }  
  public void setManualReceiptNo (String in) {
	manualReceiptNo = in;
  }  
  public void setPayMethod (String in) {
	payMethod = in;
  }  
/**
 * Insert the method's description here.
 * Creation date: (11/21/2002 2:32:14 PM)
 * @param newPreviewFile java.lang.String
 */
public void setPreviewFile(java.lang.String newPreviewFile) {
	previewFile = newPreviewFile;
}
  public void setReceiptNumber (String in) {
	receiptNumber = in;
  }  
  public void setSubmitButton (String in) {
	submitButton = in;
  }  
  public void setX (int in) {
	x = in;
  }  
  public void setY (int in) {
	y = in;
  }  
}
