
package com.easypay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EP_Schedule complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EP_Schedule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SchedRecNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SchedID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ConsentID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MerchRec" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PaymentNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ScheduleDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="PaymentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EP_Schedule", propOrder = {
    "schedRecNo",
    "schedID",
    "consentID",
    "merchRec",
    "paymentNo",
    "scheduleDate",
    "paymentDate",
    "amount",
    "status"
})
public class EPSchedule {

    @XmlElement(name = "SchedRecNo")
    protected int schedRecNo;
    @XmlElement(name = "SchedID")
    protected int schedID;
    @XmlElement(name = "ConsentID")
    protected int consentID;
    @XmlElement(name = "MerchRec")
    protected int merchRec;
    @XmlElement(name = "PaymentNo")
    protected int paymentNo;
    @XmlElement(name = "ScheduleDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar scheduleDate;
    @XmlElement(name = "PaymentDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar paymentDate;
    @XmlElement(name = "Amount")
    protected float amount;
    @XmlElement(name = "Status")
    protected String status;

    /**
     * Gets the value of the schedRecNo property.
     * 
     */
    public int getSchedRecNo() {
        return schedRecNo;
    }

    /**
     * Sets the value of the schedRecNo property.
     * 
     */
    public void setSchedRecNo(int value) {
        this.schedRecNo = value;
    }

    /**
     * Gets the value of the schedID property.
     * 
     */
    public int getSchedID() {
        return schedID;
    }

    /**
     * Sets the value of the schedID property.
     * 
     */
    public void setSchedID(int value) {
        this.schedID = value;
    }

    /**
     * Gets the value of the consentID property.
     * 
     */
    public int getConsentID() {
        return consentID;
    }

    /**
     * Sets the value of the consentID property.
     * 
     */
    public void setConsentID(int value) {
        this.consentID = value;
    }

    /**
     * Gets the value of the merchRec property.
     * 
     */
    public int getMerchRec() {
        return merchRec;
    }

    /**
     * Sets the value of the merchRec property.
     * 
     */
    public void setMerchRec(int value) {
        this.merchRec = value;
    }

    /**
     * Gets the value of the paymentNo property.
     * 
     */
    public int getPaymentNo() {
        return paymentNo;
    }

    /**
     * Sets the value of the paymentNo property.
     * 
     */
    public void setPaymentNo(int value) {
        this.paymentNo = value;
    }

    /**
     * Gets the value of the scheduleDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScheduleDate() {
        return scheduleDate;
    }

    /**
     * Sets the value of the scheduleDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScheduleDate(XMLGregorianCalendar value) {
        this.scheduleDate = value;
    }

    /**
     * Gets the value of the paymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPaymentDate() {
        return paymentDate;
    }

    /**
     * Sets the value of the paymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPaymentDate(XMLGregorianCalendar value) {
        this.paymentDate = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(float value) {
        this.amount = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
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
    public void setStatus(String value) {
        this.status = value;
    }

}
