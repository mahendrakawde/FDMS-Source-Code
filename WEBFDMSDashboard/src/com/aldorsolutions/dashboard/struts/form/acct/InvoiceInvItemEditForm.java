/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.aldorsolutions.dashboard.struts.form.acct;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.aldorassist.webfdms.delegate.InvoiceManager;
import com.aldorassist.webfdms.model.notpersisted.InvoiceInventoryItemLinePO;
import com.aldorsolutions.webfdms.beans.DbApAccount;
import com.aldorsolutions.webfdms.database.PersistenceException;

/** 
 * MyEclipse Struts
 * Creation date: 05-16-2007
 * 
 * XDoclet definition:
 * @struts.form name="invoiceInvItemEditForm"
 */
public class InvoiceInvItemEditForm extends ActionForm {
	
	private Logger logger = Logger.getLogger(InvoiceInvItemEditForm.class.getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3624259526590871997L;

	private long invoiceInventoryItemID = 0;
	
	//private boolean added = false;
	private boolean added = true;
	
	private boolean removed = false;
	
	private boolean merchandise = false;
	
	private String itemCode = null;
	
	private String itemDescription = null;
	
	private double itemCost = 0.0;
	
	private double unitCost = 0.0;
	
	private int itemQuant = 0;
	
	private long apAccountID = 0;
	
	private String itemGLAccount = null;
	
	private boolean taxable = false;
	
	private String submitButton = null;
	
	private double invoiceTotal = 0.0;
	
	private double itemTotal = 0.0;
	
	private double taxPercent = 0.0;
	
	private ArrayList <InvoiceInventoryItemLinePO> invoiceInvItems = null;
	
	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		if ( submitButton.equals("Submit") == false ) {
			return ( null );
		}
		
		ActionErrors errors = new ActionErrors();
		InvoiceManager imvMgr = new InvoiceManager();
		DbApAccount apAccount = null; 
		
		try {
			apAccount = imvMgr.getAccount(request, apAccountID);

			if ( apAccount != null ) {
				itemGLAccount = apAccount.getAccountNumber();
			}
		} catch ( PersistenceException pe ) {
			logger.debug(pe.getMessage(), pe);
		}
		
		if ( apAccount == null ) {
			errors.add ( ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.required", "GL Account Number") );
		}
		
		return errors;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
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
		return itemCost;
	}

	/**
	 * @param itemCost the itemCost to set
	 */
	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
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
	 * @return the taxable
	 */
	public boolean isTaxable() {
		return taxable;
	}

	/**
	 * @param taxable the taxable to set
	 */
	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
	}

	/**
	 * @return the submitButton
	 */
	public String getSubmitButton() {
		return submitButton;
	}

	/**
	 * @param submitButton the submitButton to set
	 */
	public void setSubmitButton(String submitButton) {
		this.submitButton = submitButton;
	}
	
	/**
	 * @return the invoiceTotal
	 */
	public double getInvoiceTotal() {
		return invoiceTotal;
	}

	/**
	 * @param invoiceTotal the invoiceTotal to set
	 */
	public void setInvoiceTotal(double invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}

	/**
	 * @return the taxPercent
	 */
	public double getTaxPercent() {
		return taxPercent;
	}

	/**
	 * @param taxPercent the taxPercent to set
	 */
	public void setTaxPercent(double taxPercent) {
		this.taxPercent = taxPercent;
	}

	/**
	 * @return the invoiceInvItems
	 */
	public ArrayList<InvoiceInventoryItemLinePO> getInvoiceInvItems() {
		return invoiceInvItems;
	}

	/**
	 * @param invoiceInvItems the invoiceInvItems to set
	 */
	public void setInvoiceInvItems(
			ArrayList<InvoiceInventoryItemLinePO> invoiceInvItems) {
		this.invoiceInvItems = invoiceInvItems;
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
	 * @return the itemTotal
	 */
	public double getItemTotal() {
		return itemTotal;
	}

	/**
	 * @param itemTotal the itemTotal to set
	 */
	public void setItemTotal(double itemTotal) {
		this.itemTotal = itemTotal;
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