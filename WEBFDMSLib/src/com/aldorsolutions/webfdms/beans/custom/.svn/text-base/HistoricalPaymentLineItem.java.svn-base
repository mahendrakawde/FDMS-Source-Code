package com.aldorsolutions.webfdms.beans.custom;

/*
 * HistoricalPaymentLineItem.java
 *
 * Created on February 1, 2002, 10:22 AM
 */

import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;



/**
 *
 * @author  cjongs
 * @version 
 */
public class HistoricalPaymentLineItem {

	private com.aldorsolutions.webfdms.beans.DbHistory dbHistory;
	
	private java.lang.String itemId;
	
	private java.lang.String itemPayMethod;
	
	private java.lang.String itemDescription;
	
	private java.lang.String itemDate;
	
	private java.lang.String itemAmount;
	
	private java.lang.String itemPaymentComponent;
	
	private java.lang.String itemDeleteTran;
	
	/** Creates new HistoricalPaymentLineItem */
	public HistoricalPaymentLineItem() {
	}
	public HistoricalPaymentLineItem( DbHistory dbHistory ) { 
		this.dbHistory = dbHistory;
	}
	  public DbHistory getDbHistory(){
		return dbHistory;
	}
	 public String getItemAmount()
	{
		// reverse sign since a normal payment is stored as negative amount.
		itemAmount =  FormatCurrency.toCurrency((long) -(dbHistory.getLHistAmount()) );
		return itemAmount;
	 } 
/**
* Reformat YYYYMMDD history transaction date to MM/DD/YY for presentation purposes
*/
public String getItemDate()
	{
		
		itemDate = FormatDate.convertDateToSHORT(dbHistory.getCHistDate());

		return itemDate;
	 } 
	 public String getItemDeleteTran()
	{
		itemDeleteTran = String.valueOf( dbHistory.getCHistDeleteTran() );
				
		return itemDeleteTran;
	 } 
	 public String getItemDescription()
	{
		if (dbHistory.getComment().length() > 0) {
			itemDescription = dbHistory.getCHistDesc() +": "+dbHistory.getComment();
		} else {
			itemDescription = dbHistory.getCHistDesc();
		}
		return itemDescription;
	}
	  public String getItemId()
	{
	   itemId = new Integer(dbHistory.getId() ).toString(); 
		return itemId;
	}
	 public String getItemPaymentComponent()
	{
		
		itemPaymentComponent = dbHistory.getCHistPmtComponent();
		return itemPaymentComponent;
	 } 
	public String getItemPayMethod()
	{
		
		itemPayMethod = dbHistory.getCHistPayMethod();
		return itemPayMethod;
	}
	public void setDbHistory( DbHistory in )
	{
		this.dbHistory = in;
	}
	public void setItemAmount( String in )
	{
		itemAmount = in;
		 try{ 
		dbHistory.setLHistAmount((int) FormatCurrency.convertToCurrency( in ) );
		 }catch (java.lang.Exception e) {}
	}
	public void setItemDate( String in )
	{
		itemDate = in;
	}
	public void setItemDeleteTran( String in )
	{
		itemDeleteTran = in;
	}
	public void setItemDescription( String in )
	{
		itemDescription = in;
	}
	public void setItemId( String in )
	{ 
		
		itemId = in;
	}
	public void setItemPaymentComponent( String in )
	{
		itemPaymentComponent = in;
	}
	public void setItemPayMethod( String in )
	{
		itemPayMethod = in;
	}
}
