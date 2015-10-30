package com.aldorsolutions.webfdms.beans.custom;

/*
 * FinancialInformationAddMerchandiseLineItem.java
 *
 * Created on January 23, 2002, 3:49 PM
 */

import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;

/**
 *
 * @author  Mark
 * @version 
 */
public class FinancialInformationAddMerchandiseLineItem {

	/** Creates new FinancialInformationAddMerchandiseLineItem */
	
	com.aldorsolutions.webfdms.beans.DbInvMaster dbInvMaster;
	java.lang.String itemName;
	java.lang.String itemDescription;
	java.lang.String itemPrice;
	java.lang.String itemId;
 
	
//int getLPrice() 

//String getCDescription() 

//String getCItemName() 

	public FinancialInformationAddMerchandiseLineItem() {
	}
	public FinancialInformationAddMerchandiseLineItem(com.aldorsolutions.webfdms.beans.DbInvMaster inv){
		dbInvMaster = inv;
	}
	public java.lang.String getItemDescription() {
		itemDescription = dbInvMaster.getCDescription();
		return itemDescription; 
		   
	}
	 public java.lang.String  getItemId() {
		itemId = String.valueOf( dbInvMaster.getId() );
		return itemId;
	}
	public java.lang.String getItemName(){
		itemName = dbInvMaster.getCItemName();
		return itemName;
	}
	  public java.lang.String getItemPrice() {
		 itemPrice = FormatCurrency.toCurrency((long) dbInvMaster.getLPrice() ); 
		return itemPrice;
	}
	public void setItemDescription( java.lang.String in ) {
		itemDescription = in;
		dbInvMaster.setCDescription( itemDescription ); 
	}
	public void setItemId(java.lang.String  in ) {
		   itemId = in;
		   dbInvMaster.setId( FormatNumber.parseInteger( itemId ) );
		
	}
	public void setItemName( java.lang.String in)
	{
		itemName = in;
		dbInvMaster.setCItemName( itemName );
	}
	public void setItemPrice( java.lang.String in) {
		 itemPrice = in;
		dbInvMaster.setLPrice( FormatNumber.parseInteger( itemPrice ) );
	}
	
	public String getItemDisplayDescription () {
		return ( getItemName() + " " + getItemDescription() + " " + getItemPrice() );
	}
}
