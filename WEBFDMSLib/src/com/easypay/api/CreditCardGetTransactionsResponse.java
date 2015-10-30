
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
 *         &lt;element name="CreditCard_getTransactionsResult" type="{http://localhost}ArrayOfTransaction" minOccurs="0"/>
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
    "creditCardGetTransactionsResult"
})
@XmlRootElement(name = "CreditCard_getTransactionsResponse")
public class CreditCardGetTransactionsResponse {

    @XmlElement(name = "CreditCard_getTransactionsResult")
    protected ArrayOfTransaction creditCardGetTransactionsResult;

    /**
     * Gets the value of the creditCardGetTransactionsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTransaction }
     *     
     */
    public ArrayOfTransaction getCreditCardGetTransactionsResult() {
        return creditCardGetTransactionsResult;
    }

    /**
     * Sets the value of the creditCardGetTransactionsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTransaction }
     *     
     */
    public void setCreditCardGetTransactionsResult(ArrayOfTransaction value) {
        this.creditCardGetTransactionsResult = value;
    }

}
