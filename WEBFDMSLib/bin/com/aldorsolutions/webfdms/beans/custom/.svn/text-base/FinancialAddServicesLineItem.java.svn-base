package com.aldorsolutions.webfdms.beans.custom;

/*
 * FinancialAddServicesLineItem.java
 *
 * Created on January 22, 2002, 2:47 PM
 */

import com.aldorsolutions.webfdms.beans.DbPriceList;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 * 
 * @author Mark
 * @version
 */
public class FinancialAddServicesLineItem extends Object {

	private DbPriceList dbPriceList;

	private String itemDescription;

	private String itemPrice;

	private String itemId;
	
	/** Creates new FinancialAddServicesLineItem */
	public FinancialAddServicesLineItem() {
	}

	public FinancialAddServicesLineItem(DbPriceList dbPriceList) {
		this.dbPriceList = dbPriceList;
	}

	public DbPriceList getDbPriceList() {
		return this.dbPriceList;
	}

	public String getItemDescription() {
		itemDescription = dbPriceList.getContrDescr();
		return itemDescription;

	}

	public String getItemId() {
		itemId = String.valueOf(dbPriceList.getId());
		return itemId;
	}

	public String getItemPrice() {
		itemPrice = FormatCurrency.toCurrency((long) dbPriceList.getPrice());
		return itemPrice;
	}

	public void setDbPriceList(DbPriceList dbPriceList) {
		this.dbPriceList = dbPriceList;
	}

	public void setItemDescription(String in) {
		itemDescription = in;
		dbPriceList.setContrDescr(itemDescription);
	}

	public void setItemId(String in) {
		itemId = in;
		dbPriceList.setId(FormatNumber.parseInteger(itemId));

	}

	public void setItemPrice(String in) {
		itemPrice = in;
		dbPriceList.setPrice(FormatNumber.parseInteger(itemPrice));
	}
	
	public String getFinancialItemDescription () {
		return ( getItemDescription() + " " + getItemPrice() );
	}
}
