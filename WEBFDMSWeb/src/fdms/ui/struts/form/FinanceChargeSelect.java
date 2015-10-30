package fdms.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;


public class FinanceChargeSelect extends FinanceChargeSelectBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1398562803549146304L;
	private java.lang.String fromDate;
	private java.lang.String thruDate;
	private java.lang.String directive;
	private com.aldorsolutions.webfdms.util.TempFinanceCharges chargesBunch;
/**
 * Insert the method's description here.
 * Creation date: (4/10/2003 10:36:58 AM)
 * @return fdms.ui.struts.TempFinanceCharges
 */
public com.aldorsolutions.webfdms.util.TempFinanceCharges getChargesBunch() {
	return chargesBunch;
}
/**
 * Insert the method's description here.
 * Creation date: (4/10/2003 10:14:44 AM)
 * @return java.lang.String
 */
public java.lang.String getDirective() {
	return directive;
}
/**
 * Insert the method's description here.
 * Creation date: (4/10/2003 10:14:10 AM)
 * @return java.lang.String
 */
public java.lang.String getFromDate() {
	return fromDate;
}
/**
 * Insert the method's description here.
 * Creation date: (4/10/2003 10:14:23 AM)
 * @return java.lang.String
 */
public java.lang.String getThruDate() {
	return thruDate;
}
  public void reset(ActionMapping actionMapping, HttpServletRequest request) {
	com.aldorsolutions.webfdms.beans.custom.FinanceCharge aCharge = null;
	super.reset(actionMapping,  request);
	java.util.Iterator myIterator = chargesBunch.getFcList().iterator();
	while (myIterator.hasNext() ){
		aCharge  = (com.aldorsolutions.webfdms.beans.custom.FinanceCharge)myIterator.next();
		aCharge.setSelected(false);
	}
}
/**
 * Insert the method's description here.
 * Creation date: (4/10/2003 10:36:58 AM)
 * @param newChargesBunch fdms.ui.struts.TempFinanceCharges
 */
public void setChargesBunch(com.aldorsolutions.webfdms.util.TempFinanceCharges newChargesBunch) {
	chargesBunch = newChargesBunch;
}
/**
 * Insert the method's description here.
 * Creation date: (4/10/2003 10:14:44 AM)
 * @param newDirective java.lang.String
 */
public void setDirective(java.lang.String newDirective) {
	directive = newDirective;
}
/**
 * Insert the method's description here.
 * Creation date: (4/10/2003 10:14:10 AM)
 * @param newFromDate java.lang.String
 */
public void setFromDate(java.lang.String newFromDate) {
	fromDate = newFromDate;
}
/**
 * Insert the method's description here.
 * Creation date: (4/10/2003 10:14:23 AM)
 * @param newThruDate java.lang.String
 */
public void setThruDate(java.lang.String newThruDate) {
	thruDate = newThruDate;
}
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
	/**@todo: Override this org.apache.struts.action.ActionForm method*/
	return super.validate(actionMapping,  request);
  }  
}
