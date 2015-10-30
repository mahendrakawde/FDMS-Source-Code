package com.aldorsolutions.webfdms.beans.custom;

/*
 * FinancialBillToPartiesLineItem.java
 *
 * Created on January 24, 2002, 10:08 AM
 */

import com.aldorsolutions.webfdms.beans.DbBillto;
import com.aldorsolutions.webfdms.util.FormatNumber;




/**
 *
 * @author  Mark
 * @version 
 */
public class FinancialInformationBillToPartiesLineItem {
	
	private DbBillto dbBillTo;

	private java.lang.String itemId;
	
	private java.lang.String itemRelation;
	
	private java.lang.String itemFirstName;
	
	private java.lang.String itemLastName;
	
	private java.lang.String itemStreet1;
	
	private java.lang.String itemContractSigner;
	
	/** Creates new FinancialBillToPartiesLineItem */
	public FinancialInformationBillToPartiesLineItem() {
	}
	public FinancialInformationBillToPartiesLineItem(com.aldorsolutions.webfdms.beans.DbBillto dbBillTo) {

		this.dbBillTo = dbBillTo;
	}
	public DbBillto getDbBillTo(){
		return dbBillTo;
	}
	  public String getItemContractSigner(){
		itemContractSigner = String.valueOf( dbBillTo.getContractSigner() );
		return itemContractSigner;
	}
	  public String getItemFirstName(){
		itemFirstName = dbBillTo.getFirstName();
		return itemFirstName;
	}
	 public String  getItemId() {
		itemId = String.valueOf( dbBillTo.getId() );
		return itemId;
	}
	 public String getItemLastName(){
		itemLastName = dbBillTo.getLastName();
		return itemLastName;
	}
	 public String getItemRelation(){
		itemRelation = dbBillTo.getRelation();
		return itemRelation;
	}
	  public String getItemStreet1(){
		itemStreet1 = dbBillTo.getStreet1();
		return itemStreet1;
	}
	public void setDbBillTo( DbBillto dbBillTo )
	{
		this.dbBillTo = dbBillTo;
	}
	public void setItemContractSigner( String in)
	{
		itemContractSigner = in;
//		char[] itemContractSignerChar = itemContractSigner.toCharArray();
		dbBillTo.setContractSigner( in );//( itemContractSignerChar[0] );
	}
	public void setItemFirstName( String in)
	{
		itemFirstName = in;
		dbBillTo.setFirstName( itemFirstName );
	}
	public void setItemId( String  in ) {
		   itemId = in;
		   dbBillTo.setId( FormatNumber.parseInteger( itemId ) );
		 
		
		
	}
	public void setItemLastName( String in)
	{
		itemLastName = in;
		dbBillTo.setLastName( itemLastName );
	}
	public void setItemRelation( String in)
	{
		itemRelation = in;
		dbBillTo.setRelation( itemRelation );
	}
	public void setItemStreet1( String in)
	{
		itemStreet1 = in;
		dbBillTo.setStreet1( itemStreet1 );
	}
}
