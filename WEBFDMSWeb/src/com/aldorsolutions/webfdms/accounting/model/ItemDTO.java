package com.aldorsolutions.webfdms.accounting.model;

import java.io.Serializable;
import java.sql.Date;

import com.aldorsolutions.webfdms.beans.DbHistory;
import com.aldorsolutions.webfdms.util.FormatCurrency;
import com.aldorsolutions.webfdms.util.FormatDate;
import com.aldorsolutions.webfdms.util.FormatString;


/**
 * Workfile: ItemDTO.java
 * Date: Nov 29, 2005 7:18:37 PM
 * Author: Guadalupe Garcia
 *
 * Copyright 2005. FDMS Network, All Rights Reserved
 */

public class ItemDTO implements Serializable {

	final static long serialVersionUID = 1133313567740L;
	
	private long vitalsMasterKey;
	private int tranHistId;
	private int chargeType;
	private String description;
	private int amountOfTran;
	private String arAcct;
	private String glAcct;
	private int taxExemptAmount;
	private String taxCode;
	private Date dateOfTran;
	private String accountingPeriod;
	private String spfCode;
	private String itemCategory;
	private int receiptNumber;
	private String postedYN;
	private String originalPostingYN;
	private String deleteTransaction;
	private String inventoryItemName;
	private int invMasterKey;
	private int invCostOfSale;
	private String paymentMethod;
	private String paymentComponent;
	private String userInitials;
	private String manualReceiptNo;
	private int recordVersion;
	private int interfaceSequenceNo;
	private int origChrgAmount;
	private String origChrgDescr;
	private String exported;
	private Date dateModified;
	private String timeModified;
	private Date datePaid;
	private String depositBatchNumber;
	private String claimNumber;
	private int locationId;
	private int invOnHandId;	
	private int taxAmount;
	private String locationCode;
	
	public ItemDTO() { }

	/**
	 * @return Returns the accountingPeriod.
	 */
	public String getAccountingPeriod() {
		return accountingPeriod;
	}

	/**
	 * @param accountingPeriod The accountingPeriod to set.
	 */
	public void setAccountingPeriod(String accountingPeriod) {
		this.accountingPeriod = accountingPeriod;
	}

	/**
	 * @return Returns the amountOfTran.
	 */
	public int getAmountOfTran() {
		return amountOfTran;
	}

	/**
	 * @param amountOfTran The amountOfTran to set.
	 */
	public void setAmountOfTran(int amountOfTran) {
		this.amountOfTran = amountOfTran;
	}

	/**
	 * @return Returns the arAcct.
	 */
	public String getArAcct() {
		return arAcct;
	}

	/**
	 * @param arAcct The arAcct to set.
	 */
	public void setArAcct(String arAcct) {
		this.arAcct = arAcct;
	}

	/**
	 * @return Returns the chargeType.
	 */
	public int getChargeType() {
		return chargeType;
	}

	/**
	 * @param chargeType The chargeType to set.
	 */
	public void setChargeType(int chargeType) {
		this.chargeType = chargeType;
	}

	/**
	 * @return Returns the dateModified.
	 */
	public Date getDateModified() {
		return dateModified;
	}

	/**
	 * @param dateModified The dateModified to set.
	 */
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	/**
	 * @return Returns the dateOfTran.
	 */
	public Date getDateOfTran() {
		return dateOfTran;
	}

	/**
	 * @param dateOfTran The dateOfTran to set.
	 */
	public void setDateOfTran(Date dateOfTran) {
		this.dateOfTran = dateOfTran;
	}

	/**
	 * @return Returns the deleteTransaction.
	 */
	public String getDeleteTransaction() {
		return deleteTransaction;
	}

	/**
	 * @param deleteTransaction The deleteTransaction to set.
	 */
	public void setDeleteTransaction(String deleteTransaction) {
		this.deleteTransaction = deleteTransaction;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the exported.
	 */
	public String getExported() {
		return exported;
	}

	/**
	 * @param exported The exported to set.
	 */
	public void setExported(String exported) {
		this.exported = exported;
	}

	/**
	 * @return Returns the glAcct.
	 */
	public String getGlAcct() {
		return glAcct;
	}

	/**
	 * @param glAcct The glAcct to set.
	 */
	public void setGlAcct(String glAcct) {
		this.glAcct = glAcct;
	}

	/**
	 * @return Returns the interfaceSequenceNo.
	 */
	public int getInterfaceSequenceNo() {
		return interfaceSequenceNo;
	}

	/**
	 * @param interfaceSequenceNo The interfaceSequenceNo to set.
	 */
	public void setInterfaceSequenceNo(int interfaceSequenceNo) {
		this.interfaceSequenceNo = interfaceSequenceNo;
	}

	/**
	 * @return Returns the invCostOfSale.
	 */
	public int getInvCostOfSale() {
		return invCostOfSale;
	}

	/**
	 * @param invCostOfSale The invCostOfSale to set.
	 */
	public void setInvCostOfSale(int invCostOfSale) {
		this.invCostOfSale = invCostOfSale;
	}

	/**
	 * @return Returns the inventoryItemName.
	 */
	public String getInventoryItemName() {
		return inventoryItemName;
	}

	/**
	 * @param inventoryItemName The inventoryItemName to set.
	 */
	public void setInventoryItemName(String inventoryItemName) {
		this.inventoryItemName = inventoryItemName;
	}

	/**
	 * @return Returns the invMasterKey.
	 */
	public int getInvMasterKey() {
		return invMasterKey;
	}

	/**
	 * @param invMasterKey The invMasterKey to set.
	 */
	public void setInvMasterKey(int invMasterKey) {
		this.invMasterKey = invMasterKey;
	}

	/**
	 * @return Returns the itemCategory.
	 */
	public String getItemCategory() {
		return itemCategory;
	}

	/**
	 * @param itemCategory The itemCategory to set.
	 */
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	/**
	 * @return Returns the manualReceiptNo.
	 */
	public String getManualReceiptNo() {
		return manualReceiptNo;
	}

	/**
	 * @param manualReceiptNo The manualReceiptNo to set.
	 */
	public void setManualReceiptNo(String manualReceiptNo) {
		this.manualReceiptNo = manualReceiptNo;
	}

	/**
	 * @return Returns the origChrgAmount.
	 */
	public int getOrigChrgAmount() {
		return origChrgAmount;
	}

	/**
	 * @param origChrgAmount The origChrgAmount to set.
	 */
	public void setOrigChrgAmount(int origChrgAmount) {
		this.origChrgAmount = origChrgAmount;
	}

	/**
	 * @return Returns the origChrgDescr.
	 */
	public String getOrigChrgDescr() {
		return origChrgDescr;
	}

	/**
	 * @param origChrgDescr The origChrgDescr to set.
	 */
	public void setOrigChrgDescr(String origChrgDescr) {
		this.origChrgDescr = origChrgDescr;
	}

	/**
	 * @return Returns the originalPostingYN.
	 */
	public String getOriginalPostingYN() {
		return originalPostingYN;
	}

	/**
	 * @param originalPostingYN The originalPostingYN to set.
	 */
	public void setOriginalPostingYN(String originalPostingYN) {
		this.originalPostingYN = originalPostingYN;
	}

	/**
	 * @return Returns the paymentComponent.
	 */
	public String getPaymentComponent() {
		return paymentComponent;
	}

	/**
	 * @param paymentComponent The paymentComponent to set.
	 */
	public void setPaymentComponent(String paymentComponent) {
		this.paymentComponent = paymentComponent;
	}

	/**
	 * @return Returns the paymentMethod.
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod The paymentMethod to set.
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return Returns the postedYN.
	 */
	public String getPostedYN() {
		return postedYN;
	}

	/**
	 * @param postedYN The postedYN to set.
	 */
	public void setPostedYN(String postedYN) {
		this.postedYN = postedYN;
	}

	/**
	 * @return Returns the receiptNumber.
	 */
	public int getReceiptNumber() {
		return receiptNumber;
	}

	/**
	 * @param receiptNumber The receiptNumber to set.
	 */
	public void setReceiptNumber(int receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	/**
	 * @return Returns the recordVersion.
	 */
	public int getRecordVersion() {
		return recordVersion;
	}

	/**
	 * @param recordVersion The recordVersion to set.
	 */
	public void setRecordVersion(int recordVersion) {
		this.recordVersion = recordVersion;
	}

	/**
	 * @return Returns the spfCode.
	 */
	public String getSpfCode() {
		return spfCode;
	}

	/**
	 * @param spfCode The spfCode to set.
	 */
	public void setSpfCode(String spfCode) {
		this.spfCode = spfCode;
	}

	/**
	 * @return Returns the taxExemptAmount.
	 */
	public int getTaxExemptAmount() {
		return taxExemptAmount;
	}

	/**
	 * @param taxExemptAmount The taxExemptAmount to set.
	 */
	public void setTaxExemptAmount(int taxExemptAmount) {
		this.taxExemptAmount = taxExemptAmount;
	}

	/**
	 * @return Returns the tranHistId.
	 */
	public int getTranHistId() {
		return tranHistId;
	}

	/**
	 * @param tranHistId The tranHistId to set.
	 */
	public void setTranHistId(int tranHistId) {
		this.tranHistId = tranHistId;
	}

	/**
	 * @return Returns the userInitials.
	 */
	public String getUserInitials() {
		return userInitials;
	}

	/**
	 * @param userInitials The userInitials to set.
	 */
	public void setUserInitials(String userInitials) {
		this.userInitials = userInitials;
	}

	/**
	 * @return Returns the vitalsMasterKey.
	 */
	public long getVitalsMasterKey() {
		return vitalsMasterKey;
	}

	/**
	 * @param vitalsMasterKey The vitalsMasterKey to set.
	 */
	public void setVitalsMasterKey(long vitalsMasterKey) {
		this.vitalsMasterKey = vitalsMasterKey;
	}
	
	/**
	 * @return Returns the taxCode.
	 */
	public String getTaxCode() {
		return taxCode;
	}

	/**
	 * @param taxCode The taxCode to set.
	 */
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	/**
	 * @return Returns the claimNumber.
	 */
	public String getClaimNumber() {
		return claimNumber;
	}

	/**
	 * @param claimNumber The claimNumber to set.
	 */
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	/**
	 * @return Returns the datePaid.
	 */
	public Date getDatePaid() {
		return datePaid;
	}

	/**
	 * @param datePaid The datePaid to set.
	 */
	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
	}

	/**
	 * @return Returns the depositBatchNumber.
	 */
	public String getDepositBatchNumber() {
		return depositBatchNumber;
	}

	/**
	 * @param depositBatchNumber The depositBatchNumber to set.
	 */
	public void setDepositBatchNumber(String depositBatchNumber) {
		this.depositBatchNumber = depositBatchNumber;
	}

	/**
	 * @return Returns the invOnHandId.
	 */
	public int getInvOnHandId() {
		return invOnHandId;
	}

	/**
	 * @param invOnHandId The invOnHandId to set.
	 */
	public void setInvOnHandId(int invOnHandId) {
		this.invOnHandId = invOnHandId;
	}

	/**
	 * @return Returns the locationId.
	 */
	public int getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId The locationId to set.
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return Returns the timeModified.
	 */
	public String getTimeModified() {
		return timeModified;
	}

	/**
	 * @param timeModified The timeModified to set.
	 */
	public void setTimeModified(String timeModified) {
		this.timeModified = timeModified;
	}

	/**
	 * @return Returns the taxAmount.
	 */
	public int getTaxAmount() {
		return taxAmount;
	}

	/**
	 * @param taxAmount The taxAmount to set.
	 */
	public void setTaxAmount(int taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	/**
	 * @return the locationCode
	 */
	public String getLocationCode() {
		return locationCode;
	}

	/**
	 * @param locationCode the locationCode to set
	 */
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String toXML() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("    <Item>\n");
		sb.append("      <VitalsId>" + vitalsMasterKey + "</VitalsId>\n");
		sb.append("      <TranHistId>" + tranHistId + "</TranHistId>\n");
		sb.append("      <ChargeType>" + chargeType + "</ChargeType>\n");
		sb.append("      <Description>" + FormatString.nullNull(FormatString.escapeXML(description)) + "</Description>\n");
		
		String amountFormatted = "0.00";
		try {
			amountFormatted = FormatCurrency.toCurrencyFormatted((double)amountOfTran);
		} catch (Exception e) {
		}
		
		sb.append("      <AmountOfTran>" + FormatString.nullNull(amountFormatted) + "</AmountOfTran>\n");
		sb.append("      <ARacct>" + FormatString.nullNull(arAcct) + "</ARacct>\n");
		sb.append("      <GLacct>" + FormatString.nullNull(glAcct) + "</GLacct>\n");
		
		try {
			amountFormatted = FormatCurrency.toCurrencyFormatted((double)taxExemptAmount);
		} catch (Exception e) {
		}
		
		sb.append("      <TaxExemptAmount>" + FormatString.nullNull(amountFormatted) + "</TaxExemptAmount>\n");
		sb.append("      <TaxCode>" + FormatString.nullNull(taxCode) + "</TaxCode>\n");
		sb.append("      <DateOfTran>" + FormatDate.convertDateToMM_DD_YYYY(dateOfTran) + "</DateOfTran>\n");
		sb.append("      <AccountingPeriod>" + FormatString.nullNull(accountingPeriod) + "</AccountingPeriod>\n");
		sb.append("      <SPFCode>" + FormatString.nullNull(spfCode) + "</SPFCode>\n");
		sb.append("      <ItemCategory>" + FormatString.nullNull(FormatString.escapeXML(itemCategory)) + "</ItemCategory>\n");
		sb.append("      <ReceiptNumber>" + receiptNumber + "</ReceiptNumber>\n");
		sb.append("      <PostedYN>" + FormatString.nullNull(postedYN) + "</PostedYN>\n");
		sb.append("      <OriginalPostingYN>" + FormatString.nullNull(originalPostingYN) + "</OriginalPostingYN>\n");
		sb.append("      <DeleteTransaction>" + FormatString.nullNull(deleteTransaction) + "</DeleteTransaction>\n");
		sb.append("      <InventoryItemName>" + FormatString.nullNull(FormatString.escapeXML(inventoryItemName)) + "</InventoryItemName>\n");
		sb.append("      <InvMasterKey>" + invMasterKey + "</InvMasterKey>\n");
		
		try {
			amountFormatted = FormatCurrency.toCurrencyFormatted((double)invCostOfSale);
		} catch (Exception e) {
		}
		
		sb.append("      <InvCostOfSale>" + FormatString.nullNull(amountFormatted) + "</InvCostOfSale>\n");
		sb.append("      <PaymentMethod>" + FormatString.nullNull(paymentMethod) + "</PaymentMethod>\n");
		sb.append("      <PaymentComponent>" + FormatString.nullNull(paymentComponent) + "</PaymentComponent>\n");
		sb.append("      <UserInitials>" + FormatString.nullNull(userInitials) + "</UserInitials>\n");
		sb.append("      <ManualReceiptNo>" + FormatString.nullNull(manualReceiptNo) + "</ManualReceiptNo>\n");
		sb.append("      <RecordVersion>" + recordVersion + "</RecordVersion>\n");
		sb.append("      <InterfaceSequenceNo>" + interfaceSequenceNo + "</InterfaceSequenceNo>\n");
		
		try {
			amountFormatted = FormatCurrency.toCurrencyFormatted((double)origChrgAmount);
		} catch (Exception e) {
		}
		
		String datePaidStr = FormatDate.convertDateToMM_DD_YYYY(datePaid);
		
		sb.append("      <OrigChrgAmount>" + FormatString.nullNull(amountFormatted) + "</OrigChrgAmount>\n");
		sb.append("      <OrigChrgDescr>" + FormatString.nullNull(FormatString.escapeXML(origChrgDescr)) + "</OrigChrgDescr>\n");
		sb.append("      <Exported>" + FormatString.nullNull(FormatString.escapeXML(exported)) + "</Exported>\n");
		
		sb.append("      <DateModified>" + FormatString.nullNull(FormatDate.convertDateToMM_DD_YYYY(dateModified)) + "</DateModified>\n");
		sb.append("      <TimeModified>" + FormatString.nullNull(timeModified) + "</TimeModified>\n");
		sb.append("      <DatePaid>" + FormatString.nullNull(datePaidStr) + "</DatePaid>\n");
		sb.append("      <DepositBatchNumber>" + FormatString.nullNull(depositBatchNumber) + "</DepositBatchNumber>\n");
		sb.append("      <ClaimNumber>" + FormatString.nullNull(claimNumber) + "</ClaimNumber>\n");
		sb.append("      <LocationId>" + locationId + "</LocationId>\n");
		sb.append("      <InvOnHandId>" + invOnHandId + "</InvOnHandId>\n");
		
		try {
			amountFormatted = FormatCurrency.toCurrencyFormatted((double)taxAmount);
		} catch (Exception e) {
		}
		
		sb.append("      <TaxAmount>" + FormatString.nullNull(amountFormatted) + "</TaxAmount>\n");
		sb.append("    </Item>\n");
		
		return sb.toString();
	}
	
	public String toQuickbooksXML() {
		StringBuffer sb = new StringBuffer();
		// if this is a payment
		if (spfCode.compareTo(DbHistory.HIST_SPF_PAYMENT) == 0 
				|| spfCode.compareTo(DbHistory.HIST_SPF_VOIDCNTRT) == 0) {
			// if this is a new payment
			if (amountOfTran <= 0) {
				// negate the transaction amount such that payments are positive
				amountOfTran = 0 - amountOfTran;
				sb.append("    <payment>\n");
				sb.append("      <paymentreceivedfrom>" + FormatString.nullNull(FormatString.escapeXML(description)) + "</paymentreceivedfrom>\n");
				sb.append("      <paymentmethod>" + FormatString.nullNull(paymentMethod) + "</paymentmethod>\n");
				String amountFormatted = "0.00";
				try {
					amountFormatted = FormatCurrency.toCurrencyFormatted((double)amountOfTran);
				} catch (Exception e) {
				}
				sb.append("      <paymentamount>" + FormatString.nullNull(amountFormatted) + "</paymentamount>\n");
				sb.append("      <paymentchecknumber>null</paymentchecknumber>\n"); 
				sb.append("      <paymentcreditcardnumber>null</paymentcreditcardnumber>\n"); 
				sb.append("      <paymentcreditcardexpirationdate>null</paymentcreditcardexpirationdate>\n");  
				sb.append("    </payment>\n");
			}
			// if this is a voided payment
			else {
				sb.append("    <item>\n");
				glAcct="FDMS_VOIDED_PAYMENT";
				sb.append("      <accountid>" + FormatString.nullNull(FormatString.escapeXML(glAcct)) + "</accountid>\n");
				sb.append("      <description>" + FormatString.nullNull(FormatString.escapeXML(description)) + "</description>\n");
				String amountFormatted = "0.00";
				try {
					amountFormatted = FormatCurrency.toCurrencyFormatted((double)amountOfTran);
				} catch (Exception e) {
				}
				sb.append("      <amount>" + FormatString.nullNull(amountFormatted) + "</amount>\n");
	  			sb.append("      <quantity>1</quantity>\n"); 
				sb.append("    </item>\n");
			/*	sb.append("    <credit>\n");
				String amountFormatted = "0.00";
				try {
					amountFormatted = FormatCurrency.toCurrencyFormatted((double)amountOfTran);
				} catch (Exception e) {
				}
				sb.append("      <creditacctid>" + FormatString.nullNull(glAcct) + "</creditacctid>\n");
				sb.append("      <creditamount>" + FormatString.nullNull(amountFormatted) + "</creditamount>\n");
				sb.append("      <description>" + FormatString.nullNull(FormatString.escapeXML(description)) + "</description>\n");
				sb.append("      <creditdate>" + FormatDate.convertDateToMM_DD_YYYY(dateOfTran) + "</creditdate>\n");
				sb.append("    </credit>\n");*/
			}
		}
		// if this is an item or a credit
		else if (spfCode.compareTo(DbHistory.HIST_SPF_SALE) == 0 
				|| spfCode.compareTo(DbHistory.HIST_SPF_FINCHRG) == 0
				|| spfCode.compareTo(DbHistory.HIST_SPF_PRICEADJ) == 0) {
			// if this is a new item
			if (amountOfTran >= 0) {
				sb.append("    <item>\n");
				if (glAcct.compareTo("") == 0) {
					glAcct="FDMS_UNKNOWN_ITEM";
				}
				sb.append("      <accountid>" + FormatString.nullNull(FormatString.escapeXML(glAcct)) + "</accountid>\n");
				sb.append("      <description>" + FormatString.nullNull(FormatString.escapeXML(description)) + "</description>\n");
				String amountFormatted = "0.00";
				try {
					amountFormatted = FormatCurrency.toCurrencyFormatted((double)amountOfTran);
				} catch (Exception e) {
				}
				sb.append("      <amount>" + FormatString.nullNull(amountFormatted) + "</amount>\n");
	  			sb.append("      <quantity>1</quantity>\n"); 
				sb.append("    </item>\n");
			}
			// if this is a discount or a refunded item
			else {
				sb.append("    <credit>\n");
				amountOfTran = 0 - amountOfTran;
				String amountFormatted = "0.00";
				try {
					amountFormatted = FormatCurrency.toCurrencyFormatted((double)amountOfTran);
				} catch (Exception e) {
				}
				sb.append("      <creditacctid>" + FormatString.nullNull(glAcct) + "</creditacctid>\n");
				sb.append("      <creditamount>" + FormatString.nullNull(amountFormatted) + "</creditamount>\n");
				sb.append("      <description>" + FormatString.nullNull(FormatString.escapeXML(description)) + "</description>\n");
				sb.append("      <creditdate>" + FormatDate.convertDateToMM_DD_YYYY(dateOfTran) + "</creditdate>\n");
				sb.append("    </credit>\n");	
			}
		}
		
		return sb.toString();
	}
}
