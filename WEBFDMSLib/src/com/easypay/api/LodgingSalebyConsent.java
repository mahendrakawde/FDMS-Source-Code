
package com.easypay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="passkey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConsentID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Ovrd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="RefID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Folio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ArrivalDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DepartureDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SaleCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ExtraChargeCodes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExtraChargeAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="CustomerData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "passkey",
    "consentID",
    "ovrd",
    "refID",
    "amount",
    "folio",
    "arrivalDate",
    "departureDate",
    "duration",
    "saleCode",
    "extraChargeCodes",
    "extraChargeAmount",
    "customerData",
    "user"
})
@XmlRootElement(name = "Lodging_SalebyConsent")
public class LodgingSalebyConsent {

    protected String passkey;
    @XmlElement(name = "ConsentID")
    protected int consentID;
    @XmlElement(name = "Ovrd")
    protected boolean ovrd;
    @XmlElement(name = "RefID")
    protected String refID;
    @XmlElement(name = "Amount")
    protected float amount;
    @XmlElement(name = "Folio")
    protected String folio;
    @XmlElement(name = "ArrivalDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar arrivalDate;
    @XmlElement(name = "DepartureDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar departureDate;
    @XmlElement(name = "Duration")
    protected int duration;
    @XmlElement(name = "SaleCode")
    protected int saleCode;
    @XmlElement(name = "ExtraChargeCodes")
    protected String extraChargeCodes;
    @XmlElement(name = "ExtraChargeAmount")
    protected float extraChargeAmount;
    @XmlElement(name = "CustomerData")
    protected String customerData;
    @XmlElement(name = "User")
    protected String user;

    /**
     * Gets the value of the passkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPasskey() {
        return passkey;
    }

    /**
     * Sets the value of the passkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPasskey(String value) {
        this.passkey = value;
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
     * Gets the value of the ovrd property.
     * 
     */
    public boolean isOvrd() {
        return ovrd;
    }

    /**
     * Sets the value of the ovrd property.
     * 
     */
    public void setOvrd(boolean value) {
        this.ovrd = value;
    }

    /**
     * Gets the value of the refID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefID() {
        return refID;
    }

    /**
     * Sets the value of the refID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefID(String value) {
        this.refID = value;
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
     * Gets the value of the folio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Sets the value of the folio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolio(String value) {
        this.folio = value;
    }

    /**
     * Gets the value of the arrivalDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Sets the value of the arrivalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setArrivalDate(XMLGregorianCalendar value) {
        this.arrivalDate = value;
    }

    /**
     * Gets the value of the departureDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the value of the departureDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDepartureDate(XMLGregorianCalendar value) {
        this.departureDate = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     */
    public void setDuration(int value) {
        this.duration = value;
    }

    /**
     * Gets the value of the saleCode property.
     * 
     */
    public int getSaleCode() {
        return saleCode;
    }

    /**
     * Sets the value of the saleCode property.
     * 
     */
    public void setSaleCode(int value) {
        this.saleCode = value;
    }

    /**
     * Gets the value of the extraChargeCodes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtraChargeCodes() {
        return extraChargeCodes;
    }

    /**
     * Sets the value of the extraChargeCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtraChargeCodes(String value) {
        this.extraChargeCodes = value;
    }

    /**
     * Gets the value of the extraChargeAmount property.
     * 
     */
    public float getExtraChargeAmount() {
        return extraChargeAmount;
    }

    /**
     * Sets the value of the extraChargeAmount property.
     * 
     */
    public void setExtraChargeAmount(float value) {
        this.extraChargeAmount = value;
    }

    /**
     * Gets the value of the customerData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerData() {
        return customerData;
    }

    /**
     * Sets the value of the customerData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerData(String value) {
        this.customerData = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

}
