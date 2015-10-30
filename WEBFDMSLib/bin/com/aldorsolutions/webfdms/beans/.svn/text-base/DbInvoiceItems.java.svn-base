package com.aldorsolutions.webfdms.beans;

import java.sql.Date;
import java.util.Hashtable;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;

/**
 * DbInvoice - This class represents the information for one invoice Creation
 * date: (12/27/2002 2:42:35 PM)
 * 
 * @author:
 */
public class DbInvoiceItems extends Persistent {
	static private final DbInvoiceItemsPeer peer = new DbInvoiceItemsPeer();

	private int invoiceID;
	private boolean inventoryItem;
	private String itemCode;
	private String itemDesc;
	private double costPerUnit;
	private double itemCost;
	private int quantity;
	private int apAccountID;
	private String glAcctNum;
	

	protected PersistentPeer getPersistentPeer() {
	return peer;
	}

	public boolean isLocked() {
	return false;
	}

	/**
	 * Move data from hashtable and copy into class variables Peer object restores
	 * from database to hashtable.
	 */
	public void restore(Transaction t, Hashtable data)
			throws PersistenceException {

	setId(Integer.parseInt(data.get(DbInvoiceItemsPeer.INVOICEITEMID).toString()));
	invoiceID = Integer.parseInt(data.get(DbInvoiceItemsPeer.INVOICEID).toString());
	inventoryItem = Boolean.parseBoolean(data.get(DbInvoiceItemsPeer.INVENTORYITEM).toString());
	itemCode = data.get(DbInvoiceItemsPeer.ITEMCODE).toString();
	itemDesc = data.get(DbInvoiceItemsPeer.ITEMDESC).toString();
	costPerUnit = Double.parseDouble(data.get(DbInvoiceItemsPeer.COSTPERUNIT).toString());
	quantity = Integer.parseInt(data.get(DbInvoiceItemsPeer.QUANTITY).toString());
	glAcctNum = data.get(DbInvoiceItemsPeer.GLACCTNUM).toString();
	itemCost = Double.parseDouble(data.get(DbInvoiceItemsPeer.ITEMCOST).toString());
	apAccountID = Integer.parseInt(data.get(DbInvoiceItemsPeer.APACCOUNTID).toString());
	}

	public void setId(Hashtable h) {
	modify();
	setId(((Integer) h.get(DbInvoiceItemsPeer.INVOICEITEMID)).intValue());
	}

	/**
	 * @return the invoiceID
	 */
	public int getInvoiceID() {
		return invoiceID;
	}

	/**
	 * @param invoiceID the invoiceID to set
	 */
	public void setInvoiceID(int invoiceID) {
		modify();
		this.invoiceID = invoiceID;
	}

	/**
	 * @return the inventoryItem
	 */
	public boolean isInventoryItem() {
		return inventoryItem;
	}

	/**
	 * @param inventoryItem the inventoryItem to set
	 */
	public void setInventoryItem(boolean inventoryItem) {
		modify();
		this.inventoryItem = inventoryItem;
	}

	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		modify();
		this.itemCode = itemCode;
	}

	/**
	 * @return the itemDesc
	 */
	public String getItemDesc() {
		return itemDesc;
	}

	/**
	 * @param itemDesc the itemDesc to set
	 */
	public void setItemDesc(String itemDesc) {
		modify();
		this.itemDesc = itemDesc;
	}

	/**
	 * @return the costPerUnit
	 */
	public double getCostPerUnit() {
		return costPerUnit;
	}

	/**
	 * @param costPerUnit the costPerUnit to set
	 */
	public void setCostPerUnit(double costPerUnit) {
		modify();
		this.costPerUnit = costPerUnit;
	}

	/**
	 * @return the itemCost
	 */
	public double getItemCost() {
		return itemCost;
	}

	/**
	 * @param itemCost the itemCost to set
	 */
	public void setItemCost(double itemCost) {
		modify();
		this.itemCost = itemCost;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		modify();
		this.quantity = quantity;
	}

	/**
	 * @return the apAccountID
	 */
	public int getApAccountID() {
		return apAccountID;
	}

	/**
	 * @param apAccountID the apAccountID to set
	 */
	public void setApAccountID(int apAccountID) {
		modify();
		this.apAccountID = apAccountID;
	}

	/**
	 * @return the glAcctNumber
	 */
	public String getGlAcctNum() {
		return glAcctNum;
	}

	/**
	 * @param glAcctNumber the glAcctNumber to set
	 */
	public void setGlAcctNum(String glAcctNum) {
		modify();
		this.glAcctNum = glAcctNum;
	}



}
