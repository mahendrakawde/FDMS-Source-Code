
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
 *         &lt;element name="CreditCard_voidTransactionResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "creditCardVoidTransactionResult"
})
@XmlRootElement(name = "CreditCard_voidTransactionResponse")
public class CreditCardVoidTransactionResponse {

    @XmlElement(name = "CreditCard_voidTransactionResult")
    protected String creditCardVoidTransactionResult;

    /**
     * Gets the value of the creditCardVoidTransactionResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardVoidTransactionResult() {
        return creditCardVoidTransactionResult;
    }

    /**
     * Sets the value of the creditCardVoidTransactionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardVoidTransactionResult(String value) {
        this.creditCardVoidTransactionResult = value;
    }

}
