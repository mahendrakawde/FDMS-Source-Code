
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
 *         &lt;element name="NoDays" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "", propOrder = {
    "passkey",
    "noDays",
    "merchRec"
})
@XmlRootElement(name = "EasyPay_findExpiredCards")
public class EasyPayFindExpiredCards {

    protected String passkey;
    @XmlElement(name = "NoDays")
    protected int noDays;
    @XmlElement(name = "MerchRec")
    protected int merchRec;

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
     * Gets the value of the noDays property.
     * 
     */
    public int getNoDays() {
        return noDays;
    }

    /**
     * Sets the value of the noDays property.
     * 
     */
    public void setNoDays(int value) {
        this.noDays = value;
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
