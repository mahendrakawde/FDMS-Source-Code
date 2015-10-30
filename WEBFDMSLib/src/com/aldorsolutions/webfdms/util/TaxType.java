package com.aldorsolutions.webfdms.util;

/**
 * This class represents one tax code and the additional information needed
 * to calculate sales tax.
 * Creation date: (2/28/2003 4:54:10 PM)
 * @author: 
 */
public class TaxType {
	private java.lang.String code;
	private java.lang.String description;
	private double localRate;
	private double stateRate;
	private double articleRate;
	private int localLimit;
	private int stateLimit;
	private int articleUpperLimit;
	private int articleLowerLimit;
	private int localSaleAmount;
	private int stateSaleAmount;
	private int articleSaleAmount;
	private char localTaxNotExempt; // Y means do not calculate local sales tax, anything else, go ahead and calculate it
/**
 * TaxType constructor comment.
 */
public TaxType() {
	super();
}
/**
 * TaxType constructor comment.
 */
public TaxType(String acode, String adescr, double alocalRate, double astateRate, double aarticleRate, int alocallimit, int astatelimit, int aarticlelowerlimit, int aarticleupperlimit, String alocalTaxNotExempt) {
	super();
	setCode(		acode);
	setDescription(	adescr);
	setLocalRate(	alocalRate);
	setStateRate(	astateRate);
	setArticleRate( aarticleRate);
	setLocalLimit(	alocallimit);
	setStateLimit(	astatelimit);
	setArticleLowerLimit(aarticlelowerlimit);
	setArticleUpperLimit(aarticleupperlimit);
	localSaleAmount=0;
	stateSaleAmount=0;
	articleSaleAmount=0;
	if (alocalTaxNotExempt.startsWith("Y")) {
		setLocalTaxNotExempt('Y'); 
	}
	else { // calculate exemptions
		setLocalTaxNotExempt('N');
	}
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:55:36 PM)
 * @return java.lang.String
 */
public java.lang.String getCode() {
	return code;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:56:07 PM)
 * @return java.lang.String
 */
public java.lang.String getDescription() {
	return description;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:56:58 PM)
 * @return int
 */
public int getLocalLimit() {
	return localLimit;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:56:33 PM)
 * @return double
 */
public double getLocalRate() {
	return localRate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:58:29 PM)
 * @return int
 */
public int getLocalSaleAmount() {
	return localSaleAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:57:17 PM)
 * @return int
 */
public int getStateLimit() {
	return stateLimit;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:56:45 PM)
 * @return double
 */
public double getStateRate() {
	return stateRate;
}
/**
 * return the articleSaleAmount.
 * Creation date: (7/12/2005 6:55:40 PM)
 * @return int
 */
public int getArticleSaleAmount() {
	return articleSaleAmount;
}
/**
 * Return the Article Tax's lower limit.
 * Creation date: (7/12/2005 6:55:17 PM)
 * @return int
 */
public int getArticleLowerLimit() {
	return articleLowerLimit;
}
/**
 * Return the Article Tax's upper limit.
 * Creation date: (7/12/2005 6:55:17 PM)
 * @return int
 */
public int getArticleUpperLimit() {
	return articleUpperLimit;
}
/**
 * Return the Article Tax's lower limit.
 * Creation date: (7/12/2005 6:56:45 PM)
 * @return double
 */
public double getArticleRate() {
	return articleRate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:58:40 PM)
 * @return int
 */
public int getStateSaleAmount() {
	return stateSaleAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:55:36 PM)
 * @param newCode java.lang.String
 */
public void setCode(java.lang.String newCode) {
	code = newCode;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:56:07 PM)
 * @param newDescription java.lang.String
 */
public void setDescription(java.lang.String newDescription) {
	description = newDescription;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:56:58 PM)
 * @param newLocalLimit int
 */
public void setLocalLimit(int newLocalLimit) {
	localLimit = newLocalLimit;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:56:33 PM)
 * @param newLocalRate double
 */
public void setLocalRate(double newLocalRate) {
	localRate = newLocalRate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:58:29 PM)
 * @param newLocalSaleAmount int
 */
public void setLocalSaleAmount(int newLocalSaleAmount) {
	localSaleAmount = newLocalSaleAmount;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:57:17 PM)
 * @param newStateLimit int
 */
public void setStateLimit(int newStateLimit) {
	stateLimit = newStateLimit;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:56:45 PM)
 * @param newStateRate double
 */
public void setStateRate(double newStateRate) {
	stateRate = newStateRate;
}
/**
 * Insert the method's description here.
 * Creation date: (2/28/2003 4:58:40 PM)
 * @param newStateSaleAmount int
 */
public void setStateSaleAmount(int newStateSaleAmount) {
	stateSaleAmount = newStateSaleAmount;
}
/**
 * Set the lower limit on the Article Tax.
 * Creation date: (7/12/2005 7:01:33 PM)
 * @param newLocalRate double
 */
public void setArticleLowerLimit(int newArticleLowerLimit) {
	articleLowerLimit = newArticleLowerLimit;
}
/**
 * Set the upper limit on the Article Tax.
 * Creation date: (7/12/2005 7:01:33 PM)
 * @param newLocalRate double
 */
public void setArticleUpperLimit(int newArticleUpperLimit) {
	articleUpperLimit = newArticleUpperLimit;
}
/**
 * Set the rate of the Article Tax.
 * Creation date: (7/12/2005 7:02:45 PM)
 * @param newStateRate double
 */
public void setArticleRate(double newArticleRate) {
	articleRate = newArticleRate;
}
/**
 * Set the amount of the Article Sale.
 * Creation date: (7/12/2005 7:02:40 PM)
 * @param newStateSaleAmount int
 */
public void setArticleSaleAmount(int newArticleSaleAmount) {
	articleSaleAmount = newArticleSaleAmount;
}

/**
 * Get whether or not the local tax does not calculate exemptions.
 * Creation date: (7/12/2005 7:02:40 PM)
 * @param newStateSaleAmount int
 */
public char getLocalTaxNotExempt() {
	return localTaxNotExempt;
}

/**
 * Set whether or not the local tax does not calculate exemptions.
 * Creation date: (7/12/2005 7:02:40 PM)
 * @param newStateSaleAmount int
 */
public void setLocalTaxNotExempt(char isExempt){
	localTaxNotExempt = isExempt;
}
}
