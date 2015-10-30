package com.aldorassist.webfdms.model.notpersisted;

import com.aldorassist.webfdms.model.InvoiceItemDTO;

/**
 * 
 * @author David Rollins
 *
 */
public class InvoiceInventoryItemLinePO {
	
	private long invoiceInventoryItemID = 0;
	
	private boolean added = false;
	
	private boolean removed = false;
	
	private boolean merchandise = false;
	
	private String itemCode = null;
	
	private String itemDescription = null;
	
	private double unitCost = 0.0;
	
	private int itemQuant = 0;
	
	private long apAccountID = 0;
	
	private String itemGLAccount = null;
	

	public InvoiceInventoryItemLinePO ( ) {
		super();
	}
	
	public InvoiceInventoryItemLinePO ( InvoiceItemDTO item ) {
		unitCost = item.getCostPerUnit();
		itemGLAccount = item.getGlAcctNumber();
		invoiceInventoryItemID = item.getInvoiceItemID();
		itemCode = item.getItemCode();
		itemDescription = item.getItemDesc();
		itemQuant = item.getQuantity();
		apAccountID = item.getApAccountID();
		merchandise = item.isInventoryItem();
	}
	
	/**
	 * @return the invoiceInventoryItemID
	 */
	public long getInvoiceInventoryItemID() {
		return invoiceInventoryItemID;
	}

	/**
	 * @param invoiceInventoryItemID the invoiceInventoryItemID to set
	 */
	public void setInvoiceInventoryItemID(long invoiceInventoryItemID) {
		this.invoiceInventoryItemID = invoiceInventoryItemID;
	}

	/**
	 * @return the added
	 */
	public boolean isAdded() {
		return added;
	}

	/**
	 * @param added the added to set
	 */
	public void setAdded(boolean added) {
		this.added = added;
	}

	/**
	 * @return the removed
	 */
	public boolean isRemoved() {
		return removed;
	}

	/**
	 * @param removed the removed to set
	 */
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	/**
	 * @return the merchandise
	 */
	public boolean isMerchandise() {
		return merchandise;
	}

	/**
	 * @param merchandise the merchandise to set
	 */
	public void setMerchandise(boolean merchandise) {
		this.merchandise = merchandise;
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
		this.itemCode = itemCode;
	}

	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * @return the itemCost
	 */
	public double getItemCost() {
		return (itemQuant * unitCost);
	}

	/**
	 * @return the itemQuant
	 */
	public int getItemQuant() {
		return itemQuant;
	}

	/**
	 * @param itemQuant the itemQuant to set
	 */
	public void setItemQuant(int itemQuant) {
		this.itemQuant = itemQuant;
	}

	/**
	 * @return the itemGLAccount
	 */
	public String getItemGLAccount() {
		return itemGLAccount;
	}

	/**
	 * @param itemGLAccount the itemGLAccount to set
	 */
	public void setItemGLAccount(String itemGLAccount) {
		this.itemGLAccount = itemGLAccount;
	}

	/**
	 * @return the unitCost
	 */
	public double getUnitCost() {
		return unitCost;
	}

	/**
	 * @param unitCost the unitCost to set
	 */
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	/**
	 * @return the apAccountID
	 */
	public long getApAccountID() {
		return apAccountID;
	}

	/**
	 * @param apAccountID the apAccountID to set
	 */
	public void setApAccountID(long apAccountID) {
		this.apAccountID = apAccountID;
	}
	
}