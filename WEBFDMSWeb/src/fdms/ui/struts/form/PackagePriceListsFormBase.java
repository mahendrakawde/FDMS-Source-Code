package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class PackagePriceListsFormBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = -1187465538144447842L;
private int x;
  private int y;
  private String priceListVersion;
  private String pkgPriceListId;
  private String submitbutton;
  private String directive;
  private String priceListDescription;
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 1:37:05 PM)
 * @return java.lang.String
 */
public java.lang.String getDirective() {
	return directive;
}
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 1:36:22 PM)
 * @return java.lang.String
 */
public java.lang.String getPkgPriceListId() {
	return pkgPriceListId;
}
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 2:36:02 PM)
 * @return java.lang.String
 */
public java.lang.String getPriceListDescription() {
	return priceListDescription;
}
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 1:34:33 PM)
 * @return java.lang.String
 */
public java.lang.String getPriceListVersion() {
	return priceListVersion;
}
  public String getSubmitbutton () {
	return submitbutton ;
  }  
  public int getX () {
	return x ;
  }  
  public int getY () {
	return y ;
  }  
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 1:37:05 PM)
 * @param newDirective java.lang.String
 */
public void setDirective(java.lang.String newDirective) {
	directive = newDirective;
}
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 1:36:22 PM)
 * @param newPkgPriceListId java.lang.String
 */
public void setPkgPriceListId(java.lang.String newPkgPriceListId) {
	pkgPriceListId = newPkgPriceListId;
}
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 2:36:02 PM)
 * @param newPriceListDescription java.lang.String
 */
public void setPriceListDescription(java.lang.String newPriceListDescription) {
	priceListDescription = newPriceListDescription;
}
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 1:34:33 PM)
 * @param newPriceListVersion java.lang.String
 */
public void setPriceListVersion(java.lang.String newPriceListVersion) {
	priceListVersion = newPriceListVersion;
}
  public void setSubmitbutton (String in) {
	submitbutton = in;
  }  
  public void setX (int in) {
	x = in;
  }  
  public void setY (int in) {
	y = in;
  }  
}
