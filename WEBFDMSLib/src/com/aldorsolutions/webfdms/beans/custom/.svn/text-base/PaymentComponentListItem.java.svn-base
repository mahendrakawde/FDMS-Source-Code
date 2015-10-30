package com.aldorsolutions.webfdms.beans.custom;

import com.aldorsolutions.webfdms.util.FormatCurrency;

/**
 * This class represents a payment component to be displayed in the specify
 * components page "FinancialSpecifyComponents.JSP". Creation date: (4/17/2002
 * 3:16:45 PM)
 * 
 * @author:
 */
public class PaymentComponentListItem {
	private int recID;

	private String displayName;

	private int salesAmount;

	public String code;

	private String description;

	private String type;

	private String dffDefault;
	
	private String componentSource;

	private float assignmentFeePercent;

	private float assignmentFeeMax;

	private float assignmentFeeMin;

	private String autoGplKey;

	private String autoChargeModifiable;

	private String canPaymentsBeApplied;
	
	/**
	 * PaymentComponentList constructor comment.
	 */
	public PaymentComponentListItem() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:38:50
	 * PM)
	 * 
	 * @return float
	 */
	public float getAssignmentFeeMax() {
		return assignmentFeeMax;
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:39:07
	 * PM)
	 * 
	 * @return float
	 */
	public float getAssignmentFeeMin() {
		return assignmentFeeMin;
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:37:15
	 * PM)
	 * 
	 * @return float
	 */
	public float getAssignmentFeePercent() {
		return assignmentFeePercent;
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:40:38
	 * PM)
	 * 
	 * @return String
	 */
	public String getAutoChargeModifiable() {
		return autoChargeModifiable;
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:40:04
	 * PM)
	 * 
	 * @return String
	 */
	public String getAutoGplKey() {
		return autoGplKey;
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:41:03
	 * PM)
	 * 
	 * @return String
	 */
	public String getCanPaymentsBeApplied() {
		return canPaymentsBeApplied;
	}

	/**
	 * Insert the method's description here. Creation date: (4/17/2002 3:19:48
	 * PM)
	 * 
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Insert the method's description here. Creation date: (4/17/2002 3:20:46
	 * PM)
	 * 
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:35:58
	 * PM)
	 * 
	 * @return String
	 */
	public String getDffDefault() {
		return dffDefault;
	}

	/**
	 * Insert the method's description here. Creation date: (4/17/2002 3:18:45
	 * PM)
	 * 
	 * @return String
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Insert the method's description here. Creation date: (4/17/2002 3:18:16
	 * PM)
	 * 
	 * @return int
	 */
	public int getRecID() {
		return recID;
	}

	/**
	 * Insert the method's description here. Creation date: (4/17/2002 3:19:12
	 * PM)
	 * 
	 * @return int
	 */
	public int getSalesAmount() {
		return salesAmount;
	}

	/**
	 * Insert the method's description here. Creation date: (4/17/2002 3:21:01
	 * PM)
	 * 
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * Assemble a string for select list box by concatenating a description and
	 * a currency amount. Creation date: (4/17/2002 4:03:03 PM)
	 * 
	 * @return String
	 * @param description
	 *            String
	 * @param amount
	 *            int
	 */
	public String makeDisplayName() {
		return makeDisplayName(description, componentSource, salesAmount);
	}

	/**
	 * Assemble a string for select list box by concatenating a description and
	 * a currency amount. Creation date: (4/17/2002 4:03:03 PM)
	 * 
	 * @return String
	 * @param description
	 *            String
	 * @param amount
	 *            int
	 */
	public String makeDisplayName(String description, String source, int amount) {
		StringBuffer display = new StringBuffer();
		display.append(description);
		display.append("  ");
		
		if ( source != null && (source.length() > 0) ) {
			display.append("(" + source + ") ");
		}
		
		display.append(FormatCurrency.toCurrency((long) amount));
		return display.toString();
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:38:50
	 * PM)
	 * 
	 * @param newAssignmentFeeMax
	 *            float
	 */
	public void setAssignmentFeeMax(float newAssignmentFeeMax) {
		assignmentFeeMax = newAssignmentFeeMax;
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:39:07
	 * PM)
	 * 
	 * @param newAssignmentFeeMin
	 *            float
	 */
	public void setAssignmentFeeMin(float newAssignmentFeeMin) {
		assignmentFeeMin = newAssignmentFeeMin;
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:37:15
	 * PM)
	 * 
	 * @param newAssignmentFee
	 *            float
	 */
	public void setAssignmentFeePercent(float newAssignmentFee) {
		assignmentFeePercent = newAssignmentFee;
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:40:38
	 * PM)
	 * 
	 * @param newAutoChargeModifiable
	 *            String
	 */
	public void setAutoChargeModifiable(String newAutoChargeModifiable) {
		autoChargeModifiable = newAutoChargeModifiable;
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:40:04
	 * PM)
	 * 
	 * @param newAutoGplKey
	 *            String
	 */
	public void setAutoGplKey(String newAutoGplKey) {
		autoGplKey = newAutoGplKey;
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:41:03
	 * PM)
	 * 
	 * @param newCanPaymentsBeApplied
	 *            String
	 */
	public void setCanPaymentsBeApplied(String newCanPaymentsBeApplied) {
		canPaymentsBeApplied = newCanPaymentsBeApplied;
	}

	/**
	 * Insert the method's description here. Creation date: (4/17/2002 3:19:48
	 * PM)
	 * 
	 * @param newCode
	 *            String
	 */
	public void setCode(String newCode) {
		code = newCode;
	}

	/**
	 * Insert the method's description here. Creation date: (4/17/2002 3:20:46
	 * PM)
	 * 
	 * @param newDescription
	 *            String
	 */
	public void setDescription(String newDescription) {
		description = newDescription;
	}

	/**
	 * Insert the method's description here. Creation date: (4/18/2002 3:35:58
	 * PM)
	 * 
	 * @param newDffDefault
	 *            String
	 */
	public void setDffDefault(String newDffDefault) {
		dffDefault = newDffDefault;
	}

	/**
	 * Insert the method's description here. Creation date: (4/17/2002 3:18:45
	 * PM)
	 * 
	 * @param newDisplayName
	 *            String
	 */
	public void setDisplayName(String newDisplayName) {
		displayName = newDisplayName;
	}

	/**
	 * Insert the method's description here. Creation date: (4/17/2002 3:18:16
	 * PM)
	 * 
	 * @param newRecID
	 *            int
	 */
	public void setRecID(int newRecID) {
		recID = newRecID;
	}

	/**
	 * Insert the method's description here. Creation date: (4/17/2002 3:19:12
	 * PM)
	 * 
	 * @param newSalesAmount
	 *            int
	 */
	public void setSalesAmount(int newSalesAmount) {
		salesAmount = newSalesAmount;
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (4/17/2002 3:21:01 PM)
	 * @param newType String
	 */
	public void setType(String newType) {
		type = newType;
	}

	/**
	 * @return the componentSource
	 */
	public String getComponentSource() {
		return componentSource;
	}

	/**
	 * @param componentSource the componentSource to set
	 */
	public void setComponentSource(String componentSource) {
		this.componentSource = componentSource;
	}
	
}