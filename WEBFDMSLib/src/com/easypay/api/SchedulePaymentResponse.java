
package com.easypay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SchedulePaymentResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SchedulePaymentResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CONSENTID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CONSENT_TYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SCHEDRECNO" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SCHEDULE_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="AMOUNT" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="FIRST_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LAST_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REF_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CARD_TYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CARD_LAST4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EXP_DATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TXN_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="TXN_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EXPLANATION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SchedulePaymentResponse", propOrder = {
    "consentid",
    "consenttype",
    "schedrecno",
    "scheduledate",
    "amount",
    "firstname",
    "lastname",
    "refid",
    "cardtype",
    "cardlast4",
    "expdate",
    "txnid",
    "txncode",
    "message",
    "explanation",
    "status"
})
public class SchedulePaymentResponse {

    @XmlElement(name = "CONSENTID")
    protected int consentid;
    @XmlElement(name = "CONSENT_TYPE")
    protected String consenttype;
    @XmlElement(name = "SCHEDRECNO")
    protected int schedrecno;
    @XmlElement(name = "SCHEDULE_DATE", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar scheduledate;
    @XmlElement(name = "AMOUNT")
    protected float amount;
    @XmlElement(name = "FIRST_NAME")
    protected String firstname;
    @XmlElement(name = "LAST_NAME")
    protected String lastname;
    @XmlElement(name = "REF_ID")
    protected String refid;
    @XmlElement(name = "CARD_TYPE")
    protected String cardtype;
    @XmlElement(name = "CARD_LAST4")
    protected String cardlast4;
    @XmlElement(name = "EXP_DATE")
    protected String expdate;
    @XmlElement(name = "TXN_ID")
    protected int txnid;
    @XmlElement(name = "TXN_CODE")
    protected String txncode;
    @XmlElement(name = "MESSAGE")
    protected String message;
    @XmlElement(name = "EXPLANATION")
    protected String explanation;
    @XmlElement(name = "STATUS")
    protected String status;

    /**
     * Gets the value of the consentid property.
     * 
     */
    public int getCONSENTID() {
        return consentid;
    }

    /**
     * Sets the value of the consentid property.
     * 
     */
    public void setCONSENTID(int value) {
        this.consentid = value;
    }

    /**
     * Gets the value of the consenttype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONSENTTYPE() {
        return consenttype;
    }

    /**
     * Sets the value of the consenttype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONSENTTYPE(String value) {
        this.consenttype = value;
    }

    /**
     * Gets the value of the schedrecno property.
     * 
     */
    public int getSCHEDRECNO() {
        return schedrecno;
    }

    /**
     * Sets the value of the schedrecno property.
     * 
     */
    public void setSCHEDRECNO(int value) {
        this.schedrecno = value;
    }

    /**
     * Gets the value of the scheduledate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSCHEDULEDATE() {
        return scheduledate;
    }

    /**
     * Sets the value of the scheduledate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSCHEDULEDATE(XMLGregorianCalendar value) {
        this.scheduledate = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     */
    public float getAMOUNT() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAMOUNT(float value) {
        this.amount = value;
    }

    /**
     * Gets the value of the firstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFIRSTNAME() {
        return firstname;
    }

    /**
     * Sets the value of the firstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFIRSTNAME(String value) {
        this.firstname = value;
    }

    /**
     * Gets the value of the lastname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLASTNAME() {
        return lastname;
    }

    /**
     * Sets the value of the lastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLASTNAME(String value) {
        this.lastname = value;
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
     * Gets the value of the cardlast4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCARDLAST4() {
        return cardlast4;
    }

    /**
     * Sets the value of the cardlast4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCARDLAST4(String value) {
        this.cardlast4 = value;
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
     * Gets the value of the txnid property.
     * 
     */
    public int getTXNID() {
        return txnid;
    }

    /**
     * Sets the value of the txnid property.
     * 
     */
    public void setTXNID(int value) {
        this.txnid = value;
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
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMESSAGE() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMESSAGE(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the explanation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEXPLANATION() {
        return explanation;
    }

    /**
     * Sets the value of the explanation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEXPLANATION(String value) {
        this.explanation = value;
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

}
