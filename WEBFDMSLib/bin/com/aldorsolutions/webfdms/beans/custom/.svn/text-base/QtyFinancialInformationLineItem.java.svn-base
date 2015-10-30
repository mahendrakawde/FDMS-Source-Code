package com.aldorsolutions.webfdms.beans.custom;

/*
 * FinancialInformationLineItem.java
 *
 * Created on January 17, 2002, 12:27 PM
 */

import com.aldorsolutions.webfdms.beans.DbChargeItem;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatNumber;


/**
 * 
 * @author Mark
 * @version
 */
public class QtyFinancialInformationLineItem extends java.lang.Object {

	private DbChargeItem dbChargeItem;

	private String itemSequenceNumber;

	private String itemTypeCode;

	private String itemPrice;

	private String itemExemptDollars;

	private String itemGLAccount;

	private String itemId;

	private String itemImageURL;

	private int itemDeletion = 0; // set to '1' if item deleted during session

	private int previousAmount; // charge amount at beginning of session

	private int costOfSale = 0; // for inventory items, the cost of this one
								// item

	private java.lang.String itemPicture = new String();

	private java.util.ArrayList serialNumbers = null; // inventory serial#s
														// available for
														// operator to select
														// from

	private java.lang.String stockType = new String(); // inventory stock type,
														// "#" for serial
														// numbered items, empty
														// for all other cases.

	private java.lang.String serialNumber;

	private boolean newItem = false;

	private boolean modifiedItem = false;

	private int serialNumberModifiable = 0; // for struts user interface logic.
											// 0=not modifiable >0 = is
											// modifiable
	
	private boolean fromPackage;

	/**
	 * @return the fromPackage
	 */
	public boolean isFromPackage() {
		return dbChargeItem.isFromPackage();
	}

	/**
	 * @param fromPackage the fromPackage to set
	 */
	public void setFromPackage(boolean fromPackage) {
		this.fromPackage = fromPackage;
	}

	/** Creates new FinancialInformationLineItem */
	public QtyFinancialInformationLineItem() {
	}

	public QtyFinancialInformationLineItem(DbChargeItem dbChargeItem) {
		this.dbChargeItem = dbChargeItem;
		this.previousAmount = dbChargeItem.getAmount();
		this.itemGLAccount = dbChargeItem.getGlAcct();
	}

	/**
	 * Insert the method's description here. Creation date: (4/25/2002 3:22:57
	 * PM)
	 * 
	 * @return int
	 */
	public int getCostOfSale() {
		return costOfSale;
	}

	public DbChargeItem getDbChargeItem() {
		return this.dbChargeItem;

	}

	public int getItemDeletion() {
		return itemDeletion;

	}

	public String getItemExemptDollars() {
		itemExemptDollars = FormatCurrency.toCurrency((long) dbChargeItem.getExemptAmount());
		return itemExemptDollars;
	}

	public String getItemGLAccount() {
		// keep session GL account separate from DbCharge object GL acocunt
		// until saving to DBMS.
		// return dbChargeItem.getGlAcct();
		return itemGLAccount;

	}

	public String getItemId() {
		itemId = (new Integer(dbChargeItem.getId())).toString();
		return itemId;
	}

	/**
	 * This method uses the DbChargeItem's itemid to retrieve the imageURL from
	 * the invmaster. Creation date: (8/23/2002 10:46:23 AM)
	 * 
	 * @return java.lang.String
	 */

	public java.lang.String getItemImageURL() {
		return itemImageURL;
	}

	/**
	 * Insert the method's description here. Creation date: (8/27/2002 9:33:05
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getItemPicture() {
		return itemPicture;
	}

	public String getItemPrice() {
		itemPrice = FormatCurrency.toCurrency((long) dbChargeItem.getAmount());
		return itemPrice;
	}

	public String getItemSequenceNumber() {
		String itemSequenceNumber = new Integer(dbChargeItem.getSequenceNumber()).toString();
		return itemSequenceNumber;
	}

	public String getItemTaxCode() {
		return dbChargeItem.getTaxCode();
	}

	public String getItemTypeCode() {
		Integer i = new Integer(dbChargeItem.getType());
		itemTypeCode = i.toString();
		return itemTypeCode;
	}

	public String getItemTypeDescription() {
		return dbChargeItem.getDescription();
	}

	/**
	 * Insert the method's description here. Creation date: (4/25/2002 11:55:39
	 * AM)
	 * 
	 * @return int
	 */
	public int getPreviousAmount() {
		return previousAmount;
	}

	/**
	 * Insert the method's description here. Creation date: (10/28/2002 3:28:56
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Insert the method's description here. Creation date: (11/11/2002 7:15:14
	 * AM)
	 * 
	 * @return int
	 */
	public int getSerialNumberModifiable() {
		return serialNumberModifiable;
	}

	/**
	 * Insert the method's description here. Creation date: (10/25/2002 4:01:04
	 * PM)
	 * 
	 * @return java.util.List
	 */
	public java.util.ArrayList getSerialNumbers() {
		return serialNumbers;
	}

	/**
	 * Insert the method's description here. Creation date: (10/28/2002 1:47:00
	 * PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getStockType() {
		return stockType;
	}

	/**
	 * Insert the method's description here. Creation date: (11/10/2002 6:16:36
	 * PM)
	 * 
	 * @return boolean
	 */
	public boolean isModifiedItem() {
		return modifiedItem;
	}

	/**
	 * Insert the method's description here. Creation date: (11/10/2002 6:16:07
	 * PM)
	 * 
	 * @return boolean
	 */
	public boolean isNewItem() {
		return newItem;
	}

	/**
	 * Insert the method's description here. Creation date: (4/25/2002 3:22:57
	 * PM)
	 * 
	 * @param newCostOfSale
	 *            int
	 */
	public void setCostOfSale(int newCostOfSale) {
		costOfSale = newCostOfSale;
		setModifiedItem(true);
	}

	public void setDbChargeItem(DbChargeItem dbChargeItem) {
		this.dbChargeItem = dbChargeItem;
		setModifiedItem(true);

	}

	public void setItemDeletion(int deletion) {
		this.itemDeletion = deletion;
		setModifiedItem(true);
	}

	public void setItemExemptDollars(String in) throws java.lang.Exception {
		itemExemptDollars = in;

		int convertedIn = FormatCurrency.convertToCurrency(in);

		dbChargeItem.setExemptAmount(convertedIn);
		setModifiedItem(true);

		/*
		 * String firstChar = in.substring(0,1); String withoutDollarSign = "";
		 * if (firstChar.equals("$") ) { withoutDollarSign = in.substring(1); }
		 * 
		 * else { withoutDollarSign = in; } int dbin =
		 * FormatNumber.parseInteger(withoutDollarSign);
		 * 
		 * dbChargeItem.setExemptAmount( dbin );
		 */
	}

	public void setItemGLAccount(String in) {
		// dbChargeItem.setGlAcct( in );
		// setModifiedItem(true);
		itemGLAccount = in;

	}

	public void setItemId(String in) {
		itemId = in;
		int itemIdint = FormatNumber.parseInteger(itemId);
		dbChargeItem.setId(itemIdint);
		setModifiedItem(true);
	}

	/**
	 * This method sets the FinancialInformationLineItem.ItemImageURL. Creation
	 * date: (8/23/2002 10:46:23 AM)
	 * 
	 * @param newItemImageURL
	 *            java.lang.String
	 */
	public void setItemImageURL(DatabaseTransaction t) {

		try {
			com.aldorsolutions.webfdms.beans.DbInvMaster dbInvMaster = com.aldorsolutions.webfdms.beans.FdmsDb.getInstance().getInvMaster(t,
					this.getDbChargeItem().getPriceListId());
			itemImageURL = dbInvMaster.getImageUrl();
		} catch (Exception e) {
			itemImageURL = "";
		}
	}

	/**
	 * Insert the method's description here. Creation date: (8/27/2002 9:33:05
	 * PM)
	 * 
	 * @param newItemPicture
	 *            java.lang.String
	 */
	public void setItemPicture(java.lang.String newItemPicture) {
		itemPicture = newItemPicture;
	}

	public void setItemPrice(String in) throws java.lang.Exception {
		itemPrice = in;

		int convertedIn = FormatCurrency.convertToCurrency(in);

		dbChargeItem.setAmount(convertedIn);
		setModifiedItem(true);
		/*
		 * String firstChar = in.substring(0,1); String withoutDollarSign = "";
		 * if (firstChar.equals("$") ) { withoutDollarSign = in.substring(1); }
		 * 
		 * else { withoutDollarSign = in; } int dbin =
		 * FormatNumber.parseInteger(withoutDollarSign); dbChargeItem.setAmount(
		 * dbin );
		 */
	}

	public void setItemSequenceNumber(String in) {
		itemSequenceNumber = in;
		int dbint = FormatNumber.parseInteger(itemSequenceNumber);
		dbChargeItem.setSequenceNumber(dbint);
		setModifiedItem(true);
	}

	public void setItemTaxCode(String in) {
		dbChargeItem.setTaxCode(in);
		setModifiedItem(true);
	}

	public void setItemTypeCode(String in) {
		itemTypeCode = in;
		dbChargeItem.setType(FormatNumber.parseInteger(itemTypeCode));
		setModifiedItem(true);
	}

	public void setItemTypeDescription(String in) {
		dbChargeItem.setDescription(in);
		setModifiedItem(true);
	}

	/**
	 * Insert the method's description here. Creation date: (11/10/2002 6:16:36
	 * PM)
	 * 
	 * @param newModifiedItem
	 *            boolean
	 */
	public void setModifiedItem(boolean newModifiedItem) {
		modifiedItem = newModifiedItem;
	}

	/**
	 * Insert the method's description here. Creation date: (11/10/2002 6:16:07
	 * PM)
	 * 
	 * @param newNewItem
	 *            boolean
	 */
	public void setNewItem(boolean newNewItem) {
		newItem = newNewItem;
	}

	/**
	 * Insert the method's description here. Creation date: (4/25/2002 11:55:39
	 * AM)
	 * 
	 * @param newPreviousAmount
	 *            int
	 */
	public void setPreviousAmount(int newPreviousAmount) {
		previousAmount = newPreviousAmount;
	}

	/**
	 * Insert the method's description here. Creation date: (10/28/2002 3:28:56
	 * PM)
	 * 
	 * @param newSerialNumber
	 *            java.lang.String
	 */
	public void setSerialNumber(java.lang.String newSerialNumber) {
		serialNumber = newSerialNumber;
	}

	/**
	 * Insert the method's description here. Creation date: (11/11/2002 7:15:14
	 * AM)
	 * 
	 * @param newSerialNumberModifiable
	 *            int
	 */
	public void setSerialNumberModifiable(int newSerialNumberModifiable) {
		serialNumberModifiable = newSerialNumberModifiable;
	}

	/**
	 * Insert the method's description here. Creation date: (10/25/2002 4:01:04
	 * PM)
	 * 
	 * @param newSerialNumbers
	 *            java.util.List
	 */
	public void setSerialNumbers(java.util.ArrayList newSerialNumbers) {
		serialNumbers = newSerialNumbers;
	}

	/**
	 * Insert the method's description here. Creation date: (10/28/2002 1:47:00
	 * PM)
	 * 
	 * @param newStockType
	 *            java.lang.String
	 */
	public void setStockType(java.lang.String newStockType) {
		stockType = newStockType;
	}
	
}
