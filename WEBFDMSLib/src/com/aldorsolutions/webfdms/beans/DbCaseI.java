package com.aldorsolutions.webfdms.beans;

/**
 * Interface for a CASE object
 * Creation date: (11/1/2001 1:11:05 PM)
 * @author: 
 */
public interface DbCaseI extends com.aldorsolutions.webfdms.database.PersistentI {
	public String getCaseCode();
	public java.lang.String getChapelLocation();
	public int getChapelNumber();
	public String getContractCode();
	public abstract String getFirstName();
	public abstract String getLastName();
	public int getLocale();
	public String getPreneedStatus();
	public abstract Integer getVitalsID();
	public String getVoidedContractCode();
	public String getVoidedFromContr();
	public String getVoidedToContr();
	public void setCaseCode(String lcl_arg0);
	public void setChapelLocation(java.lang.String newChapelLocation);
	public void setChapelNumber(int newchapel);
	public void setContractCode(String lcl_arg0);
	public void setLocale(int newLocale);
	public int getActiveCode();
	public void setActiveCode(int lcl_arg0);
/**
 * Insert the method's description here.
 * Creation date: (11/1/2001 1:16:16 PM)
 * @param name java.lang.String
 */
void setVitalsID(Integer aID);
	public void setVoidedContractCode(String lcl_arg0);
	public void setVoidedFromContr(String lcl_arg0);
	public void setVoidedToContr(String lcl_arg0);
//	public void setActiveCode(int lcl_arg0);
}
