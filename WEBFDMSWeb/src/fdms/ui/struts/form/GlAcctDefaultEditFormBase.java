package fdms.ui.struts.form;

import org.apache.struts.action.ActionForm;

public class GlAcctDefaultEditFormBase extends ActionForm {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 2219691546996793119L;
private String category ;  
  private String disposition ;  
  private String revacct ;  
  private String recordID ;  
  private int y ;  
  private String directive ;  
  private int x ;  
  private String location ;  
  private String saletype ;
  
	private java.lang.String assetacct;
	private java.lang.String cogsacct;
/**
 * Insert the method's description here.
 * Creation date: (6/2/2003 3:07:18 PM)
 * @return java.lang.String
 */
public java.lang.String getAssetacct() {
	return assetacct;
}
  public String getCategory () {
	return category ;
  }  
/**
 * Insert the method's description here.
 * Creation date: (6/2/2003 3:07:31 PM)
 * @return java.lang.String
 */
public java.lang.String getCogsacct() {
	return cogsacct;
}
  public String getDirective () {
	return directive ;
  }  
  public String getDisposition () {
	return disposition ;
  }  
  public String getLocation () {
	return location ;
  }  
  public String getRecordID () {
	return recordID ;
  }  
  public String getRevacct () {
	return revacct ;
  }  
  public String getSaletype () {
	return saletype ;
  }  
  public int getX () {
	return x ;
  }  
  public int getY () {
	return y ;
  }  
/**
 * Insert the method's description here.
 * Creation date: (6/2/2003 3:07:18 PM)
 * @param newAssetacct java.lang.String
 */
public void setAssetacct(java.lang.String newAssetacct) {
	assetacct = newAssetacct;
}
  public void setCategory (String in) {
	category = in;
  }  
/**
 * Insert the method's description here.
 * Creation date: (6/2/2003 3:07:31 PM)
 * @param newCogsacct java.lang.String
 */
public void setCogsacct(java.lang.String newCogsacct) {
	cogsacct = newCogsacct;
}
  public void setDirective (String in) {
	directive = in;
  }  
  public void setDisposition (String in) {
	disposition = in;
  }  
  public void setLocation (String in) {
	location = in;
  }  
  public void setRecordID (String in) {
	recordID = in;
  }  
  public void setRevacct (String in) {
	revacct = in;
  }  
  public void setSaletype (String in) {
	saletype = in;
  }  
  public void setX (int in) {
	x = in;
  }  
  public void setY (int in) {
	y = in;
  }  
}
