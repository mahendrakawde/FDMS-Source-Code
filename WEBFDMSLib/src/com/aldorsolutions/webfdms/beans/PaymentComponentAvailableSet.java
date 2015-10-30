package com.aldorsolutions.webfdms.beans;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.util.CsvTable;
import com.aldorsolutions.webfdms.util.FormatNumber;
/**
 * This class represents all of the payment components available to each
 * case for a given region.
 * Creation date: (5/23/2002 12:03:47 PM)
 * @author: 
 */
public class PaymentComponentAvailableSet {
	private PaymentComponentAvailable[] comps;
/**
 * PaymentComponentAvailableSet constructor comment.
 */
private PaymentComponentAvailableSet() {
	super();
}
/**
 * Initialize object with payment components for a region.
 * Creation date: (5/23/2002 12:06:01 PM)
 * @param t com.aldorsolutions.webfdms.database.DatabaseTransaction
 * @param region int
 */
public PaymentComponentAvailableSet(int region, String dblookup) 
	throws PersistenceException {
	super();
   	DbSpeedData[]		compdefault	= null;
	compdefault 		= FdmsDb.getInstance().getSpeedData(dblookup, region,"PAYCOMP");
	comps = new PaymentComponentAvailable[compdefault.length];
	for (int i=0; i<comps.length; i++){
		comps[i] = new PaymentComponentAvailable();
		comps[i].setRecID(	0);
		comps[i].setCode(			CsvTable.getField(compdefault[i].getData(),1));
		comps[i].setDisplayName(	CsvTable.getField(compdefault[i].getData(),2));
		comps[i].setDescription(	CsvTable.getField(compdefault[i].getData(),2));
		comps[i].setType(			CsvTable.getField(compdefault[i].getData(),3));
		comps[i].setAssignmentFeePercent(	FormatNumber.parseFloat(CsvTable.getField(compdefault[i].getData(),4)));
		comps[i].setAssignmentFeeMax(		FormatNumber.parseFloat(CsvTable.getField(compdefault[i].getData(),5)));
		comps[i].setAssignmentFeeMin(		FormatNumber.parseFloat(CsvTable.getField(compdefault[i].getData(),6)));
		comps[i].setAutoGplKey(				CsvTable.getField(compdefault[i].getData(),7));
		comps[i].setAutoChargeModifiable(	CsvTable.getField(compdefault[i].getData(),8));
		comps[i].setCanPaymentsBeApplied(	CsvTable.getField(compdefault[i].getData(),9));
		comps[i].setDffDefault(				CsvTable.getField(compdefault[i].getData(),10));
		comps[i].setCanFinanceChargesBeApplied(CsvTable.getField(compdefault[i].getData(),11));
	}
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2002 12:04:57 PM)
 * @return com.aldorsolutions.webfdms.databasePaymentComponentAvailable[]
 */
public PaymentComponentAvailable[] getComps() {
	return comps;
}
/**
 * Return the first component that is flaged as "default"
 * Creation date: (5/23/2002 1:09:45 PM)
 * @return com.aldorsolutions.webfdms.databasePaymentComponentAvailable
 */
public PaymentComponentAvailable getDefaultComponent() {
	if (comps==null){
		return null;
	}
	for (int i=0; i<comps.length; i++){
		if (comps[i].getDffDefault().equalsIgnoreCase("Y")){
			return comps[i];
		}
	}
	return null;
}
/**
 * For specified component code, are finance charges applicable to this component.
 * Creation date: (4/7/2003 3:42:56 PM)
 * @return boolean  TRUE means finance charges can be applied to this componenet.
 * @param compcode java.lang.String
 */
public boolean isFinanceChargesApplicable(String compcode) {
	if (comps==null || compcode==null){
		return false;
	}
	for (int i=0; i<comps.length; i++){
		if (comps[i].getCode().compareToIgnoreCase(compcode)==0){
			// found matching componenent code, now compare finance charge y/n field
			if (comps[i].getCanFinanceChargesBeApplied().compareToIgnoreCase("N") != 0){
				return true;
			}
		}
	}
	return false;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/2002 12:04:57 PM)
 * @param newComps com.aldorsolutions.webfdms.databasePaymentComponentAvailable[]
 */
protected void setComps(PaymentComponentAvailable[] newComps) {
	comps = newComps;
}
}
