package com.aldorsolutions.webfdms.beans.custom;

/*
 * InventoryMasterLineItem.java
 *
 * Created on February 8, 2002, 10:41 AM
 */

import com.aldorsolutions.webfdms.beans.DbInvMaster;
import com.aldorsolutions.webfdms.util.FormatNumber;


/**
 *
 * @author  Mark
 * @version 
 */
public class InventoryMasterLineItem {

	private DbInvMaster dbInvMaster;
	
	private java.lang.String itemName;
	
	private java.lang.String itemSupplierCode;
	
	private java.lang.String itemDescription;
	
	private java.lang.String itemId;
	
	
	
	/** Creates new InventoryMasterLineItem */
	public InventoryMasterLineItem() {
	}
	public InventoryMasterLineItem( DbInvMaster dbInvMaster )
	{
		this.dbInvMaster = dbInvMaster;
	}
	public DbInvMaster getDbInvMaster() {
		return dbInvMaster;
	}
	public String getItemDescription()
	{
		
		itemDescription = dbInvMaster.getCDescription();
		return itemDescription;
	}
	public String getItemId()
	{
	   itemId = new Integer(dbInvMaster.getId() ).toString(); 
		return itemId;
	}
	  public String getItemName()
	{
		itemName = dbInvMaster.getCItemName();
		return itemName;
	}
	public String getItemSupplierCode()
	{
		
		itemSupplierCode = dbInvMaster.getCSupplierCode();
		return itemSupplierCode;
	}
	public void setDbInvMaster( DbInvMaster dbInvMaster )
	{
		this.dbInvMaster = dbInvMaster;
	}
	public void setItemDescription( String in )
	{
		itemDescription = in;
		dbInvMaster.setCDescription( itemDescription );
	}
	public void setItemId( String in )
	{ 
		
		itemId = in;
		dbInvMaster.setId( FormatNumber.parseInteger( itemId ) );
	}
	public void setItemName( String in )
	{ 
		
		itemName= in;
		dbInvMaster.setCItemName( itemName );
	}
	public void setItemSupplierCode( String in )
	{
		itemSupplierCode = in;
		dbInvMaster.setCSupplierCode( itemSupplierCode );
	}
}
