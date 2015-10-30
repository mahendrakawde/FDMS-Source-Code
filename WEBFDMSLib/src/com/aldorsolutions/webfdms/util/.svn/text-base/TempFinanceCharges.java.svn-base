package com.aldorsolutions.webfdms.util;

import com.aldorsolutions.webfdms.beans.custom.FinanceCharge;
/**
 * This class maintains a list of calculated finance charges and the
 * cases to which they apply.
 * Creation date: (3/10/2003 5:18:52 PM)
 * @author: 
 */
public class TempFinanceCharges {
	private java.util.ArrayList fcList;
/**
 * TempFinanceCharges constructor comment.
 */
public TempFinanceCharges() {
	super();
	setFcList(new java.util.ArrayList());
}
/**
 * Add a finance charge object to the complete collection in this class.
 * Creation date: (4/9/2003 10:29:09 AM)
 * @param aCharge fdms.ui.struts.custom.FinanceCharge
 */
public void add(FinanceCharge aCharge) {
	
	getFcList().add(aCharge);
}
/**
 * add a collection of FinanceCharge objects to the complete list stored in this class.
 * Creation date: (4/9/2003 10:22:13 AM)
 * @param param java.util.ArrayList
 */
public void add(java.util.ArrayList fcList) {

	FinanceCharge aCharge = null;
	
	// Loop through collection of finance charges
	java.util.Iterator myIterator = fcList.iterator();
	while (myIterator.hasNext() ){
		aCharge  = (FinanceCharge)myIterator.next();
		add( aCharge );
	}
}
/**
 * Insert the method's description here.
 * Creation date: (3/10/2003 5:22:51 PM)
 * @return java.util.ArrayList
 */
public java.util.ArrayList getFcList() {
	return fcList;
}
/**
 * Return array of FianceCharge objects for Struts nested tags.
 * Creation date: (3/10/2003 5:35:28 PM)
 * @return java.lang.Object[]
 */
public Object[] getFinChgArray() {
	return getFcList().toArray();
}
/**
 * Insert the method's description here.
 * Creation date: (3/10/2003 5:22:51 PM)
 * @param newFcList java.util.ArrayList
 */
public void setFcList(java.util.ArrayList newFcList) {
	fcList = newFcList;
}
}
