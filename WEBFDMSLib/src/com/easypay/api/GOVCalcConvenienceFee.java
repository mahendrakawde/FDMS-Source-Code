
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
 *         &lt;element name="CardNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Taxable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "cardNo",
    "amount",
    "taxable"
})
@XmlRootElement(name = "GOV_CalcConvenienceFee")
public class GOVCalcConvenienceFee {

    protected String passkey;
    @XmlElement(name = "CardNo")
    protected String cardNo;
    @XmlElement(name = "Amount")
    protected float amount;
    @XmlElement(name = "Taxable")
    protected boolean taxable;

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
     * Gets the value of the cardNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * Sets the value of the cardNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNo(String value) {
        this.cardNo = value;
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
     * Gets the value of the taxable property.
     * 
     */
    public boolean isTaxable() {
        return taxable;
    }

    /**
     * Sets the value of the taxable property.
     * 
     */
    public void setTaxable(boolean value) {
        this.taxable = value;
    }

}
