package com.aldorsolutions.webfdms.beans.custom;

/*
 * PaymentLineItem.java
 *
 * Created on February 1, 2002, 9:57 AM
 */

import com.aldorsolutions.webfdms.beans.DbComponent;
import com.aldorsolutions.webfdms.util.FormatCurrency;


/**
 *
 * @author  Mark
 * @version 
 */
public class ComponentItem {

	private String itemDescription;

	private String itemAmountDue;

	private String itemAmountPaid;

	private String itemBalance;

	private String itemId;
	
	private String itemSource;

	private DbComponent dbComponent;

	/** Creates new PaymentLineItem */
	public ComponentItem() {
	}

	public ComponentItem(DbComponent dbComponent) {
		this.dbComponent = dbComponent;
	}

	public DbComponent getDbComponent() {
		return dbComponent;
	}

	public String getItemAmountDue() {
		itemAmountDue = FormatCurrency.toCurrency((long) dbComponent.getSaleAmt());
		return itemAmountDue;
	}

	public String getItemAmountPaid() {
		itemAmountPaid = FormatCurrency.toCurrency((long) dbComponent.getPaidAmt());
		return itemAmountPaid;
	}

	public String getItemBalance() {
		itemBalance = FormatCurrency.toCurrency((long) dbComponent.getSaleAmt() - dbComponent.getPaidAmt());
		return itemBalance;
	}

	public String getItemDescription() {

		itemDescription = dbComponent.getDescription();
		return itemDescription;
	}

	public String getItemSource() {

		itemSource = dbComponent.getSource();
		return itemSource;
	}

	public String getItemId() {
		itemId = new Integer(dbComponent.getId()).toString();
		return itemId;
	}

	public void setDbComponent(DbComponent in) {
		this.dbComponent = in;
	}

	public void setItemAmountDue(String in) {
		itemAmountDue = in;
		try {
			dbComponent.setSaleAmt((int) FormatCurrency.convertToCurrency(in));
		} catch (Exception e) {
		}
	}

	public void setItemAmountPaid(String in) {
		itemAmountPaid = in;
		try {
			dbComponent.setPaidAmt((int) FormatCurrency.convertToCurrency(in));
		} catch (Exception e) {
		}
	}

	public void setItemBalance(String in) {
		itemBalance = in;

	}

	public void setItemDescription(String in) {
		itemDescription = in;
	}
	
	public void setItemSource(String in) {
		itemSource = in;
	}

	public void setItemId(String in) {

		itemId = in;
	}
}
