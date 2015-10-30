
package com.easypay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ovrd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "amount",
    "user",
    "ovrd"
})
@XmlRootElement(name = "EasyPay_ProcessConsent")
public class EasyPayProcessConsent {

    protected String passkey;
    @XmlElement(name = "ConsentID")
    protected int consentID;
    @XmlElement(name = "Amount")
    protected float amount;
    @XmlElement(name = "User")
    protected String user;
    protected boolean ovrd;

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

}
