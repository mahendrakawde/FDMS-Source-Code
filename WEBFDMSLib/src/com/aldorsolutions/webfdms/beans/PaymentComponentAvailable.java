package com.aldorsolutions.webfdms.beans;

/**
 * This class represents a payment component to be displayed in the
 * specify components page "FinancialSpecifyComponents.JSP".
 * Creation date: (4/17/2002 3:16:45 PM)
 * @author: 
 */
public class PaymentComponentAvailable {
	private int recID;
	private java.lang.String displayName;
	public java.lang.String code;
	private java.lang.String description;
	private java.lang.String type;
	private java.lang.String dffDefault;
	private float assignmentFeePercent;
	private float assignmentFeeMax;
	private float assignmentFeeMin;
	private java.lang.String autoGplKey;
	private java.lang.String autoChargeModifiable;
	private java.lang.String canPaymentsBeApplied;
	private java.lang.String canFinanceChargesBeApplied;
/**
 * PaymentComponentList constructor comment.
 */
public PaymentComponentAvailable() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:38:50 PM)
 * @return float
 */
public float getAssignmentFeeMax() {
	return assignmentFeeMax;
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:39:07 PM)
 * @return float
 */
public float getAssignmentFeeMin() {
	return assignmentFeeMin;
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:37:15 PM)
 * @return float
 */
public float getAssignmentFeePercent() {
	return assignmentFeePercent;
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:40:38 PM)
 * @return java.lang.String
 */
public java.lang.String getAutoChargeModifiable() {
	return autoChargeModifiable;
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:40:04 PM)
 * @return java.lang.String
 */
public java.lang.String getAutoGplKey() {
	return autoGplKey;
}
/**
 * Insert the method's description here.
 * Creation date: (4/7/2003 3:40:05 PM)
 * @return java.lang.String
 */
public java.lang.String getCanFinanceChargesBeApplied() {
	return canFinanceChargesBeApplied;
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:41:03 PM)
 * @return java.lang.String
 */
public java.lang.String getCanPaymentsBeApplied() {
	return canPaymentsBeApplied;
}
/**
 * Insert the method's description here.
 * Creation date: (4/17/2002 3:19:48 PM)
 * @return java.lang.String
 */
public java.lang.String getCode() {
	return code;
}
/**
 * Insert the method's description here.
 * Creation date: (4/17/2002 3:20:46 PM)
 * @return java.lang.String
 */
public java.lang.String getDescription() {
	return description;
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:35:58 PM)
 * @return java.lang.String
 */
public java.lang.String getDffDefault() {
	return dffDefault;
}
/**
 * Insert the method's description here.
 * Creation date: (4/17/2002 3:18:45 PM)
 * @return java.lang.String
 */
public java.lang.String getDisplayName() {
	return displayName;
}
/**
 * Insert the method's description here.
 * Creation date: (4/17/2002 3:18:16 PM)
 * @return int
 */
public int getRecID() {
	return recID;
}
/**
 * Insert the method's description here.
 * Creation date: (4/17/2002 3:21:01 PM)
 * @return java.lang.String
 */
public java.lang.String getType() {
	return type;
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:38:50 PM)
 * @param newAssignmentFeeMax float
 */
public void setAssignmentFeeMax(float newAssignmentFeeMax) {
	assignmentFeeMax = newAssignmentFeeMax;
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:39:07 PM)
 * @param newAssignmentFeeMin float
 */
public void setAssignmentFeeMin(float newAssignmentFeeMin) {
	assignmentFeeMin = newAssignmentFeeMin;
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:37:15 PM)
 * @param newAssignmentFee float
 */
public void setAssignmentFeePercent(float newAssignmentFee) {
	assignmentFeePercent = newAssignmentFee;
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:40:38 PM)
 * @param newAutoChargeModifiable java.lang.String
 */
public void setAutoChargeModifiable(java.lang.String newAutoChargeModifiable) {
	autoChargeModifiable = newAutoChargeModifiable;
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:40:04 PM)
 * @param newAutoGplKey java.lang.String
 */
public void setAutoGplKey(java.lang.String newAutoGplKey) {
	autoGplKey = newAutoGplKey;
}
/**
 * Insert the method's description here.
 * Creation date: (4/7/2003 3:40:05 PM)
 * @param newCanFinanceChargesBeApplied java.lang.String
 */
public void setCanFinanceChargesBeApplied(java.lang.String newCanFinanceChargesBeApplied) {
	canFinanceChargesBeApplied = newCanFinanceChargesBeApplied;
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:41:03 PM)
 * @param newCanPaymentsBeApplied java.lang.String
 */
public void setCanPaymentsBeApplied(java.lang.String newCanPaymentsBeApplied) {
	canPaymentsBeApplied = newCanPaymentsBeApplied;
}
/**
 * Insert the method's description here.
 * Creation date: (4/17/2002 3:19:48 PM)
 * @param newCode java.lang.String
 */
public void setCode(java.lang.String newCode) {
	code = newCode;
}
/**
 * Insert the method's description here.
 * Creation date: (4/17/2002 3:20:46 PM)
 * @param newDescription java.lang.String
 */
public void setDescription(java.lang.String newDescription) {
	description = newDescription;
}
/**
 * Insert the method's description here.
 * Creation date: (4/18/2002 3:35:58 PM)
 * @param newDffDefault java.lang.String
 */
public void setDffDefault(java.lang.String newDffDefault) {
	dffDefault = newDffDefault;
}
/**
 * Insert the method's description here.
 * Creation date: (4/17/2002 3:18:45 PM)
 * @param newDisplayName java.lang.String
 */
public void setDisplayName(java.lang.String newDisplayName) {
	displayName = newDisplayName;
}
/**
 * Insert the method's description here.
 * Creation date: (4/17/2002 3:18:16 PM)
 * @param newRecID int
 */
public void setRecID(int newRecID) {
	recID = newRecID;
}
/**
 * Insert the method's description here.
 * Creation date: (4/17/2002 3:21:01 PM)
 * @param newType java.lang.String
 */
public void setType(java.lang.String newType) {
	type = newType;
}
}
