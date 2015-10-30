
package com.easypay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EP_EasyPay complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EP_EasyPay">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReferenceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConsentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="TotalAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="PaymentAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="LastPayment" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Days" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NoPayments" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AcctNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcctFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcctLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConsentID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MerchRec" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EP_EasyPay", propOrder = {
    "firstName",
    "lastName",
    "referenceID",
    "consentType",
    "status",
    "startDate",
    "endDate",
    "totalAmount",
    "paymentAmount",
    "lastPayment",
    "days",
    "noPayments",
    "acctNo",
    "acctFirstName",
    "acctLastName",
    "serviceDesc",
    "consentID",
    "merchRec"
})
public class EPEasyPay {

    @XmlElement(name = "FirstName")
    protected String firstName;
    @XmlElement(name = "LastName")
    protected String lastName;
    @XmlElement(name = "ReferenceID")
    protected String referenceID;
    @XmlElement(name = "ConsentType")
    protected String consentType;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "StartDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "EndDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "TotalAmount")
    protected float totalAmount;
    @XmlElement(name = "PaymentAmount")
    protected float paymentAmount;
    @XmlElement(name = "LastPayment")
    protected float lastPayment;
    @XmlElement(name = "Days")
    protected int days;
    @XmlElement(name = "NoPayments")
    protected int noPayments;
    @XmlElement(name = "AcctNo")
    protected String acctNo;
    @XmlElement(name = "AcctFirstName")
    protected String acctFirstName;
    @XmlElement(name = "AcctLastName")
    protected String acctLastName;
    @XmlElement(name = "ServiceDesc")
    protected String serviceDesc;
    @XmlElement(name = "ConsentID")
    protected int consentID;
    @XmlElement(name = "MerchRec")
    protected int merchRec;

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the referenceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceID() {
        return referenceID;
    }

    /**
     * Sets the value of the referenceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceID(String value) {
        this.referenceID = value;
    }

    /**
     * Gets the value of the consentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsentType() {
        return consentType;
    }

    /**
     * Sets the value of the consentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsentType(String value) {
        this.consentType = value;
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

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the totalAmount property.
     * 
     */
    public float getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the value of the totalAmount property.
     * 
     */
    public void setTotalAmount(float value) {
        this.totalAmount = value;
    }

    /**
     * Gets the value of the paymentAmount property.
     * 
     */
    public float getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the value of the paymentAmount property.
     * 
     */
    public void setPaymentAmount(float value) {
        this.paymentAmount = value;
    }

    /**
     * Gets the value of the lastPayment property.
     * 
     */
    public float getLastPayment() {
        return lastPayment;
    }

    /**
     * Sets the value of the lastPayment property.
     * 
     */
    public void setLastPayment(float value) {
        this.lastPayment = value;
    }

    /**
     * Gets the value of the days property.
     * 
     */
    public int getDays() {
        return days;
    }

    /**
     * Sets the value of the days property.
     * 
     */
    public void setDays(int value) {
        this.days = value;
    }

    /**
     * Gets the value of the noPayments property.
     * 
     */
    public int getNoPayments() {
        return noPayments;
    }

    /**
     * Sets the value of the noPayments property.
     * 
     */
    public void setNoPayments(int value) {
        this.noPayments = value;
    }

    /**
     * Gets the value of the acctNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * Sets the value of the acctNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctNo(String value) {
        this.acctNo = value;
    }

    /**
     * Gets the value of the acctFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctFirstName() {
        return acctFirstName;
    }

    /**
     * Sets the value of the acctFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctFirstName(String value) {
        this.acctFirstName = value;
    }

    /**
     * Gets the value of the acctLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctLastName() {
        return acctLastName;
    }

    /**
     * Sets the value of the acctLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctLastName(String value) {
        this.acctLastName = value;
    }

    /**
     * Gets the value of the serviceDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDesc() {
        return serviceDesc;
    }

    /**
     * Sets the value of the serviceDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDesc(String value) {
        this.serviceDesc = value;
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

}
