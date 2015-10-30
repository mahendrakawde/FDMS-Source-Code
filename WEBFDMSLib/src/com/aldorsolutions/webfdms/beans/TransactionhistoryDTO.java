package com.aldorsolutions.webfdms.beans;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author David Rollins 
 * Date: Apr 27, 2007 
 * File: ApMaster.java
 * 
 */
public class TransactionhistoryDTO {

	    private int TranHistID;
	    private int VitalsMasterKey;
	    private int ChargeType;
	    private String Description;
	    private int AmountOfTran;
	    private String ARacct;
	    private String GLacct;
	    private int TaxExemptAmount;
	    private String TaxCode;
	    private Date DateOfTran;
	    private String SPFcode;
	    private String ItemCategory;
	    private int ReceiptNumber;
	    private String Postedyn;
	    private String OriginalPostingYN;
	    private String DeleteTransaction;
	    private String InventoryItemName;
	    private int InvMasterKey;
	    private int InvCostOfSale;
	    private String PaymentMethod;
	    private String PaymentComponent;
	    private String UserInitials;
	    private String ManualReceiptNo;
	    private int RecordVersion;
	    private int InterfaceSequenceNo;
	    private int OrigChrgAmount;
	    private String OrigChrgDescr;
	    private String Exported;
	    private Date DatePaid;
	    private String DepositBatchNumber;
	    private String ClaimNumber;
	    private BTRIEVE_DATE DateModified;
	    private BTRIEVE_TIME TimeModified;
	    private int locationId;
	    private int InvOnHandID;
	    private int TaxAmount;
	    private int PriceListID;
	    private String ServiceDate;
	    private long DatetimeOfTrans;
	    private int Sequence;
	    private String AccountingPeriod;
	
	    
		/**
		 * @return the accountingPeriod
		 */
		public String getAccountingPeriod() {
			return AccountingPeriod;
		}

		/**
		 * @param accountingPeriod the accountingPeriod to set
		 */
		public void setAccountingPeriod(String accountingPeriod) {
			AccountingPeriod = accountingPeriod;
		}

		public TransactionhistoryDTO() {
			super();
		} 
	    
	/**
		 * @return the tranHistID
		 */
		public int getTranHistID() {
			return TranHistID;
		}

		/**
		 * @param tranHistID the tranHistID to set
		 */
		public void setTranHistID(int tranHistID) {
			TranHistID = tranHistID;
		}

		/**
		 * @return the vitalsMasterKey
		 */
		public int getVitalsMasterKey() {
			return VitalsMasterKey;
		}

		/**
		 * @param vitalsMasterKey the vitalsMasterKey to set
		 */
		public void setVitalsMasterKey(int vitalsMasterKey) {
			VitalsMasterKey = vitalsMasterKey;
		}

		/**
		 * @return the chargeType
		 */
		public int getChargeType() {
			return ChargeType;
		}

		/**
		 * @param chargeType the chargeType to set
		 */
		public void setChargeType(int chargeType) {
			ChargeType = chargeType;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return Description;
		}

		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			Description = description;
		}

		/**
		 * @return the amountOfTran
		 */
		public int getAmountOfTran() {
			return AmountOfTran;
		}

		/**
		 * @param amountOfTran the amountOfTran to set
		 */
		public void setAmountOfTran(int amountOfTran) {
			AmountOfTran = amountOfTran;
		}

		/**
		 * @return the aRacct
		 */
		public String getARacct() {
			return ARacct;
		}

		/**
		 * @param racct the aRacct to set
		 */
		public void setARacct(String racct) {
			ARacct = racct;
		}

		/**
		 * @return the gLacct
		 */
		public String getGLacct() {
			return GLacct;
		}

		/**
		 * @param lacct the gLacct to set
		 */
		public void setGLacct(String lacct) {
			GLacct = lacct;
		}

		/**
		 * @return the taxExemptAmount
		 */
		public int getTaxExemptAmount() {
			return TaxExemptAmount;
		}

		/**
		 * @param taxExemptAmount the taxExemptAmount to set
		 */
		public void setTaxExemptAmount(int taxExemptAmount) {
			TaxExemptAmount = taxExemptAmount;
		}

		/**
		 * @return the taxCode
		 */
		public String getTaxCode() {
			return TaxCode;
		}

		/**
		 * @param taxCode the taxCode to set
		 */
		public void setTaxCode(String taxCode) {
			TaxCode = taxCode;
		}

		/**
		 * @return the dateOfTran
		 */
		public Date getDateOfTran() {
			return DateOfTran;
		}

		/**
		 * @param dateOfTran the dateOfTran to set
		 */
		public void setDateOfTran(Date dateOfTran) {
			DateOfTran = dateOfTran;
		}

		/**
		 * @return the sPFcode
		 */
		public String getSPFcode() {
			return SPFcode;
		}

		/**
		 * @param fcode the sPFcode to set
		 */
		public void setSPFcode(String fcode) {
			SPFcode = fcode;
		}

		/**
		 * @return the itemCategory
		 */
		public String getItemCategory() {
			return ItemCategory;
		}

		/**
		 * @param itemCategory the itemCategory to set
		 */
		public void setItemCategory(String itemCategory) {
			ItemCategory = itemCategory;
		}

		/**
		 * @return the receiptNumber
		 */
		public int getReceiptNumber() {
			return ReceiptNumber;
		}

		/**
		 * @param receiptNumber the receiptNumber to set
		 */
		public void setReceiptNumber(int receiptNumber) {
			ReceiptNumber = receiptNumber;
		}

		/**
		 * @return the postedyn
		 */
		public String getPostedyn() {
			return Postedyn;
		}

		/**
		 * @param postedyn the postedyn to set
		 */
		public void setPostedyn(String postedyn) {
			Postedyn = postedyn;
		}

		/**
		 * @return the originalPostingYN
		 */
		public String getOriginalPostingYN() {
			return OriginalPostingYN;
		}

		/**
		 * @param originalPostingYN the originalPostingYN to set
		 */
		public void setOriginalPostingYN(String originalPostingYN) {
			OriginalPostingYN = originalPostingYN;
		}

		/**
		 * @return the deleteTransaction
		 */
		public String getDeleteTransaction() {
			return DeleteTransaction;
		}

		/**
		 * @param deleteTransaction the deleteTransaction to set
		 */
		public void setDeleteTransaction(String deleteTransaction) {
			DeleteTransaction = deleteTransaction;
		}

		/**
		 * @return the inventoryItemName
		 */
		public String getInventoryItemName() {
			return InventoryItemName;
		}

		/**
		 * @param inventoryItemName the inventoryItemName to set
		 */
		public void setInventoryItemName(String inventoryItemName) {
			InventoryItemName = inventoryItemName;
		}

		/**
		 * @return the invMasterKey
		 */
		public int getInvMasterKey() {
			return InvMasterKey;
		}

		/**
		 * @param invMasterKey the invMasterKey to set
		 */
		public void setInvMasterKey(int invMasterKey) {
			InvMasterKey = invMasterKey;
		}

		/**
		 * @return the invCostOfSale
		 */
		public int getInvCostOfSale() {
			return InvCostOfSale;
		}

		/**
		 * @param invCostOfSale the invCostOfSale to set
		 */
		public void setInvCostOfSale(int invCostOfSale) {
			InvCostOfSale = invCostOfSale;
		}

		/**
		 * @return the paymentMethod
		 */
		public String getPaymentMethod() {
			return PaymentMethod;
		}

		/**
		 * @param paymentMethod the paymentMethod to set
		 */
		public void setPaymentMethod(String paymentMethod) {
			PaymentMethod = paymentMethod;
		}

		/**
		 * @return the paymentComponent
		 */
		public String getPaymentComponent() {
			return PaymentComponent;
		}

		/**
		 * @param paymentComponent the paymentComponent to set
		 */
		public void setPaymentComponent(String paymentComponent) {
			PaymentComponent = paymentComponent;
		}

		/**
		 * @return the userInitials
		 */
		public String getUserInitials() {
			return UserInitials;
		}

		/**
		 * @param userInitials the userInitials to set
		 */
		public void setUserInitials(String userInitials) {
			UserInitials = userInitials;
		}

		/**
		 * @return the manualReceiptNo
		 */
		public String getManualReceiptNo() {
			return ManualReceiptNo;
		}

		/**
		 * @param manualReceiptNo the manualReceiptNo to set
		 */
		public void setManualReceiptNo(String manualReceiptNo) {
			ManualReceiptNo = manualReceiptNo;
		}

		/**
		 * @return the recordVersion
		 */
		public int getRecordVersion() {
			return RecordVersion;
		}

		/**
		 * @param recordVersion the recordVersion to set
		 */
		public void setRecordVersion(int recordVersion) {
			RecordVersion = recordVersion;
		}

		/**
		 * @return the interfaceSequenceNo
		 */
		public int getInterfaceSequenceNo() {
			return InterfaceSequenceNo;
		}

		/**
		 * @param interfaceSequenceNo the interfaceSequenceNo to set
		 */
		public void setInterfaceSequenceNo(int interfaceSequenceNo) {
			InterfaceSequenceNo = interfaceSequenceNo;
		}

		/**
		 * @return the origChrgAmount
		 */
		public int getOrigChrgAmount() {
			return OrigChrgAmount;
		}

		/**
		 * @param origChrgAmount the origChrgAmount to set
		 */
		public void setOrigChrgAmount(int origChrgAmount) {
			OrigChrgAmount = origChrgAmount;
		}

		/**
		 * @return the origChrgDescr
		 */
		public String getOrigChrgDescr() {
			return OrigChrgDescr;
		}

		/**
		 * @param origChrgDescr the origChrgDescr to set
		 */
		public void setOrigChrgDescr(String origChrgDescr) {
			OrigChrgDescr = origChrgDescr;
		}

		/**
		 * @return the exported
		 */
		public String getExported() {
			return Exported;
		}

		/**
		 * @param exported the exported to set
		 */
		public void setExported(String exported) {
			Exported = exported;
		}

		/**
		 * @return the datePaid
		 */
		public Date getDatePaid() {
			return DatePaid;
		}

		/**
		 * @param datePaid the datePaid to set
		 */
		public void setDatePaid(Date datePaid) {
			DatePaid = datePaid;
		}

		/**
		 * @return the depositBatchNumber
		 */
		public String getDepositBatchNumber() {
			return DepositBatchNumber;
		}

		/**
		 * @param depositBatchNumber the depositBatchNumber to set
		 */
		public void setDepositBatchNumber(String depositBatchNumber) {
			DepositBatchNumber = depositBatchNumber;
		}

		/**
		 * @return the claimNumber
		 */
		public String getClaimNumber() {
			return ClaimNumber;
		}

		/**
		 * @param claimNumber the claimNumber to set
		 */
		public void setClaimNumber(String claimNumber) {
			ClaimNumber = claimNumber;
		}

		/**
		 * @return the dateModified
		 */
		public BTRIEVE_DATE getDateModified() {
			return DateModified;
		}

		/**
		 * @param dateModified the dateModified to set
		 */
		public void setDateModified(BTRIEVE_DATE dateModified) {
			DateModified = dateModified;
		}

		/**
		 * @return the timeModified
		 */
		public BTRIEVE_TIME getTimeModified() {
			return TimeModified;
		}

		/**
		 * @param timeModified the timeModified to set
		 */
		public void setTimeModified(BTRIEVE_TIME timeModified) {
			TimeModified = timeModified;
		}

		/**
		 * @return the locationId
		 */
		public int getLocationId() {
			return locationId;
		}

		/**
		 * @param locationId the locationId to set
		 */
		public void setLocationId(int locationId) {
			this.locationId = locationId;
		}

		/**
		 * @return the invOnHandID
		 */
		public int getInvOnHandID() {
			return InvOnHandID;
		}

		/**
		 * @param invOnHandID the invOnHandID to set
		 */
		public void setInvOnHandID(int invOnHandID) {
			InvOnHandID = invOnHandID;
		}

		/**
		 * @return the taxAmount
		 */
		public int getTaxAmount() {
			return TaxAmount;
		}

		/**
		 * @param taxAmount the taxAmount to set
		 */
		public void setTaxAmount(int taxAmount) {
			TaxAmount = taxAmount;
		}

		/**
		 * @return the priceListID
		 */
		public int getPriceListID() {
			return PriceListID;
		}

		/**
		 * @param priceListID the priceListID to set
		 */
		public void setPriceListID(int priceListID) {
			PriceListID = priceListID;
		}

		/**
		 * @return the serviceDate
		 */
		public String getServiceDate() {
			return ServiceDate;
		}

		/**
		 * @param serviceDate the serviceDate to set
		 */
		public void setServiceDate(String serviceDate) {
			ServiceDate = serviceDate;
		}

		/**
		 * @return the datetimeOfTrans
		 */
		public long getDatetimeOfTrans() {
			return DatetimeOfTrans;
		}

		/**
		 * @param datetimeOfTrans the datetimeOfTrans to set
		 */
		public void setDatetimeOfTrans(long datetimeOfTrans) {
			DatetimeOfTrans = datetimeOfTrans;
		}

		/**
		 * @return the sequence
		 */
		public int getSequence() {
			return Sequence;
		}

		/**
		 * @param sequence the sequence to set
		 */
		public void setSequence(int sequence) {
			Sequence = sequence;
		}

	
}