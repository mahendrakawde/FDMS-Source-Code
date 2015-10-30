package com.aldorsolutions.webfdms.beans;

import java.sql.Date;
import java.util.Hashtable;

import com.aldorsolutions.webfdms.database.PersistenceException;
import com.aldorsolutions.webfdms.database.Persistent;
import com.aldorsolutions.webfdms.database.PersistentPeer;
import com.aldorsolutions.webfdms.database.Transaction;
import com.aldorsolutions.webfdms.util.FormatNumber;

public class DbBankAccount extends Persistent {

	static private final DbBankAccountPeer peer = new DbBankAccountPeer();
	
	private int bankAccountId;
	private int localeId;
	private int locationId;
	private String bankName;
	private String accountName;
	private String routingNumber;
	private String accountNumber;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String otherPhone;
	private Date startDate;
	private String status;
	private int balance;
	private String accountingCode;
	private int initialBalance;
	
	@Override
	protected PersistentPeer getPersistentPeer() {
		return peer;
	}

	@Override
	public void restore(Transaction t, Hashtable data)
			throws PersistenceException {
		setId(  FormatNumber.parseInteger(data.get(DbBankAccountPeer.IDENTITY).toString()));
		localeId        = FormatNumber.parseInteger(data.get(DbBankAccountPeer.LOCALEID).toString());
		locationId   = FormatNumber.parseInteger(data.get(DbBankAccountPeer.LOCATIONID).toString());
		bankName  = data.get(DbBankAccountPeer.BANKNAME).toString();
		accountName		= data.get(DbBankAccountPeer.ACCOUNTNAME).toString();
		routingNumber  = data.get(DbBankAccountPeer.ROUTINGNUMBER).toString();
		accountNumber  = data.get(DbBankAccountPeer.ACCOUNTNUMBER).toString();
		street    = data.get(DbBankAccountPeer.STREET).toString();
		city    = data.get(DbBankAccountPeer.CITY).toString();
		state    = data.get(DbBankAccountPeer.STATE).toString();
		zip      = data.get(DbBankAccountPeer.ZIP).toString();
		phone        = data.get(DbBankAccountPeer.PHONE).toString();
		otherPhone = data.get(DbBankAccountPeer.OTHERPHONE).toString();
		try {startDate		= (Date)data.get(DbBankAccountPeer.STARTDATE);}
		catch (ClassCastException e){ startDate = null;}
		status    = data.get(DbBankAccountPeer.STATUS).toString();
		balance   = FormatNumber.parseInteger(data.get(DbBankAccountPeer.BALANCE).toString());
		accountingCode= data.get(DbBankAccountPeer.ACCOUNTINGCODE).toString();
		initialBalance   = FormatNumber.parseInteger(data.get(DbBankAccountPeer.INITIALBALANCE).toString());
	}

	@Override
	public void setId(Hashtable h) {
		setId(((Integer)h.get(DbBankAccountPeer.IDENTITY)).intValue());

	}

	@Override
	public boolean isLocked() {
		return false;
	}

	public int getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
		modify();
	}

	public int getLocaleId() {
		return localeId;
	}

	public void setLocaleId(int localeId) {
		this.localeId = localeId;
		modify();
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
		modify();
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
		modify();
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
		modify();
	}

	public String getRoutingNumber() {
		return routingNumber;
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
		modify();
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
		modify();
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
		modify();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
		modify();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
		modify();
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
		modify();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
		modify();
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
		modify();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		modify();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		modify();
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
		modify();
	}

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
		modify();
	}

	public int getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(int initialBalance) {
		this.initialBalance = initialBalance;
		modify();
	}

	
	
	
}
