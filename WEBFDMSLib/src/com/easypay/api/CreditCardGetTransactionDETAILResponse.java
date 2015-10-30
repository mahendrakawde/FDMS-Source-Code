
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
 *         &lt;element name="CreditCard_getTransactionDETAILResult" type="{http://localhost}Transaction" minOccurs="0"/>
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
    "creditCardGetTransactionDETAILResult"
})
@XmlRootElement(name = "CreditCard_getTransactionDETAILResponse")
public class CreditCardGetTransactionDETAILResponse {

    @XmlElement(name = "CreditCard_getTransactionDETAILResult")
    protected Transaction creditCardGetTransactionDETAILResult;

    /**
     * Gets the value of the creditCardGetTransactionDETAILResult property.
     * 
     * @return
     *     possible object is
     *     {@link Transaction }
     *     
     */
    public Transaction getCreditCardGetTransactionDETAILResult() {
        return creditCardGetTransactionDETAILResult;
    }

    /**
     * Sets the value of the creditCardGetTransactionDETAILResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Transaction }
     *     
     */
    public void setCreditCardGetTransactionDETAILResult(Transaction value) {
        this.creditCardGetTransactionDETAILResult = value;
    }

}
