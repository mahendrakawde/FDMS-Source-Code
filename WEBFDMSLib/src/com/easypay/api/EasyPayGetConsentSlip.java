
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
 *         &lt;element name="MerchLocID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Copy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "merchLocID",
    "copy"
})
@XmlRootElement(name = "EasyPay_getConsentSlip")
public class EasyPayGetConsentSlip {

    protected String passkey;
    @XmlElement(name = "ConsentID")
    protected int consentID;
    @XmlElement(name = "MerchLocID")
    protected int merchLocID;
    @XmlElement(name = "Copy")
    protected String copy;

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
     * Gets the value of the merchLocID property.
     * 
     */
    public int getMerchLocID() {
        return merchLocID;
    }

    /**
     * Sets the value of the merchLocID property.
     * 
     */
    public void setMerchLocID(int value) {
        this.merchLocID = value;
    }

    /**
     * Gets the value of the copy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopy() {
        return copy;
    }

    /**
     * Sets the value of the copy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopy(String value) {
        this.copy = value;
    }

}
