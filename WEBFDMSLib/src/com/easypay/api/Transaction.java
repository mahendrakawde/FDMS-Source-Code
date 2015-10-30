
package com.easypay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Transaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Transaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SETTLE_DETAILS" type="{http://localhost}SettleDetails" minOccurs="0"/>
 *         &lt;element name="RECNO" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ACCT_LAST_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ACCT_FIRST_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CUST_LAST_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CUST_FIRST_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ADDRESS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ZIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SERVICE_DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EMAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PHONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ACCT_NO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ACCOUNTID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EXP_DATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TXN_DATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TXN_TIME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TXN_AUTH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TXN_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REF_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MERCH_REC" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CARD_TYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SALE_TAX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SURCHARGE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CASHBACK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SERVER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PAYMENT_TYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DateLastChanged" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="LastChangedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Transaction", propOrder = {
    "settledetails",
    "recno",
    "acctlastname",
    "acctfirstname",
    "custlastname",
    "custfirstname",
    "address",
    "zip",
    "servicedescription",
    "email",
    "phone",
    "acctno",
    "accountid",
    "expdate",
    "txndate",
    "txntime",
    "txnauth",
    "txncode",
    "status",
    "refid",
    "merchrec",
    "cardtype",
    "saletax",
    "surcharge",
    "cashback",
    "server",
    "tip",
    "paymenttype",
    "dateLastChanged",
    "lastChangedBy"
})
public class Transaction {

    @XmlElement(name = "SETTLE_DETAILS")
    protected SettleDetails settledetails;
    @XmlElement(name = "RECNO")
    protected int recno;
    @XmlElement(name = "ACCT_LAST_NAME")
    protected String acctlastname;
    @XmlElement(name = "ACCT_FIRST_NAME")
    protected String acctfirstname;
    @XmlElement(name = "CUST_LAST_NAME")
    protected String custlastname;
    @XmlElement(name = "CUST_FIRST_NAME")
    protected String custfirstname;
    @XmlElement(name = "ADDRESS")
    protected String address;
    @XmlElement(name = "ZIP")
    protected String zip;
    @XmlElement(name = "SERVICE_DESCRIPTION")
    protected String servicedescription;
    @XmlElement(name = "EMAIL")
    protected String email;
    @XmlElement(name = "PHONE")
    protected String phone;
    @XmlElement(name = "ACCT_NO")
    protected String acctno;
    @XmlElement(name = "ACCOUNTID")
    protected int accountid;
    @XmlElement(name = "EXP_DATE")
    protected String expdate;
    @XmlElement(name = "TXN_DATE")
    protected String txndate;
    @XmlElement(name = "TXN_TIME")
    protected String txntime;
    @XmlElement(name = "TXN_AUTH")
    protected String txnauth;
    @XmlElement(name = "TXN_CODE")
    protected String txncode;
    @XmlElement(name = "STATUS")
    protected String status;
    @XmlElement(name = "REF_ID")
    protected String refid;
    @XmlElement(name = "MERCH_REC")
    protected int merchrec;
    @XmlElement(name = "CARD_TYPE")
    protected String cardtype;
    @XmlElement(name = "SALE_TAX")
    protected String saletax;
    @XmlElement(name = "SURCHARGE")
    protected String surcharge;
    @XmlElement(name = "CASHBACK")
    protected String cashback;
    @XmlElement(name = "SERVER")
    protected String server;
    @XmlElement(name = "TIP")
    protected String tip;
    @XmlElement(name = "PAYMENT_TYPE")
    protected String paymenttype;
    @XmlElement(name = "DateLastChanged", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateLastChanged;
    @XmlElement(name = "LastChangedBy")
    protected String lastChangedBy;

    /**
     * Gets the value of the settledetails property.
     * 
     * @return
     *     possible object is
     *     {@link SettleDetails }
     *     
     */
    public SettleDetails getSETTLEDETAILS() {
        return settledetails;
    }

    /**
     * Sets the value of the settledetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link SettleDetails }
     *     
     */
    public void setSETTLEDETAILS(SettleDetails value) {
        this.settledetails = value;
    }

    /**
     * Gets the value of the recno property.
     * 
     */
    public int getRECNO() {
        return recno;
    }

    /**
     * Sets the value of the recno property.
     * 
     */
    public void setRECNO(int value) {
        this.recno = value;
    }

    /**
     * Gets the value of the acctlastname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACCTLASTNAME() {
        return acctlastname;
    }

    /**
     * Sets the value of the acctlastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACCTLASTNAME(String value) {
        this.acctlastname = value;
    }

    /**
     * Gets the value of the acctfirstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACCTFIRSTNAME() {
        return acctfirstname;
    }

    /**
     * Sets the value of the acctfirstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACCTFIRSTNAME(String value) {
        this.acctfirstname = value;
    }

    /**
     * Gets the value of the custlastname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUSTLASTNAME() {
        return custlastname;
    }

    /**
     * Sets the value of the custlastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUSTLASTNAME(String value) {
        this.custlastname = value;
    }

    /**
     * Gets the value of the custfirstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUSTFIRSTNAME() {
        return custfirstname;
    }

    /**
     * Sets the value of the custfirstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCUSTFIRSTNAME(String value) {
        this.custfirstname = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADDRESS() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADDRESS(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZIP() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZIP(String value) {
        this.zip = value;
    }

    /**
     * Gets the value of the servicedescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVICEDESCRIPTION() {
        return servicedescription;
    }

    /**
     * Sets the value of the servicedescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVICEDESCRIPTION(String value) {
        this.servicedescription = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMAIL() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMAIL(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPHONE() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPHONE(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the acctno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACCTNO() {
        return acctno;
    }

    /**
     * Sets the value of the acctno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACCTNO(String value) {
        this.acctno = value;
    }

    /**
     * Gets the value of the accountid property.
     * 
     */
    public int getACCOUNTID() {
        return accountid;
    }

    /**
     * Sets the value of the accountid property.
     * 
     */
    public void setACCOUNTID(int value) {
        this.accountid = value;
    }

    /**
     * Gets the value of the expdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEXPDATE() {
        return expdate;
    }

    /**
     * Sets the value of the expdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEXPDATE(String value) {
        this.expdate = value;
    }

    /**
     * Gets the value of the txndate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTXNDATE() {
        return txndate;
    }

    /**
     * Sets the value of the txndate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTXNDATE(String value) {
        this.txndate = value;
    }

    /**
     * Gets the value of the txntime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTXNTIME() {
        return txntime;
    }

    /**
     * Sets the value of the txntime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTXNTIME(String value) {
        this.txntime = value;
    }

    /**
     * Gets the value of the txnauth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTXNAUTH() {
        return txnauth;
    }

    /**
     * Sets the value of the txnauth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTXNAUTH(String value) {
        this.txnauth = value;
    }

    /**
     * Gets the value of the txncode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTXNCODE() {
        return txncode;
    }

    /**
     * Sets the value of the txncode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTXNCODE(String value) {
        this.txncode = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATUS() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTATUS(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the refid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFID() {
        return refid;
    }

    /**
     * Sets the value of the refid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFID(String value) {
        this.refid = value;
    }

    /**
     * Gets the value of the merchrec property.
     * 
     */
    public int getMERCHREC() {
        return merchrec;
    }

    /**
     * Sets the value of the merchrec property.
     * 
     */
    public void setMERCHREC(int value) {
        this.merchrec = value;
    }

    /**
     * Gets the value of the cardtype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCARDTYPE() {
        return cardtype;
    }

    /**
     * Sets the value of the cardtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCARDTYPE(String value) {
        this.cardtype = value;
    }

    /**
     * Gets the value of the saletax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSALETAX() {
        return saletax;
    }

    /**
     * Sets the value of the saletax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSALETAX(String value) {
        this.saletax = value;
    }

    /**
     * Gets the value of the surcharge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSURCHARGE() {
        return surcharge;
    }

    /**
     * Sets the value of the surcharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSURCHARGE(String value) {
        this.surcharge = value;
    }

    /**
     * Gets the value of the cashback property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCASHBACK() {
        return cashback;
    }

    /**
     * Sets the value of the cashback property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCASHBACK(String value) {
        this.cashback = value;
    }

    /**
     * Gets the value of the server property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVER() {
        return server;
    }

    /**
     * Sets the value of the server property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVER(String value) {
        this.server = value;
    }

    /**
     * Gets the value of the tip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIP() {
        return tip;
    }

    /**
     * Sets the value of the tip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIP(String value) {
        this.tip = value;
    }

    /**
     * Gets the value of the paymenttype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAYMENTTYPE() {
        return paymenttype;
    }

    /**
     * Sets the value of the paymenttype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAYMENTTYPE(String value) {
        this.paymenttype = value;
    }

    /**
     * Gets the value of the dateLastChanged property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateLastChanged() {
        return dateLastChanged;
    }

    /**
     * Sets the value of the dateLastChanged property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateLastChanged(XMLGregorianCalendar value) {
        this.dateLastChanged = value;
    }

    /**
     * Gets the value of the lastChangedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastChangedBy() {
        return lastChangedBy;
    }

    /**
     * Sets the value of the lastChangedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastChangedBy(String value) {
        this.lastChangedBy = value;
    }

}
