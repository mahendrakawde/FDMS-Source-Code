
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
 *         &lt;element name="CreditCard_getTransactionRECNOResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "creditCardGetTransactionRECNOResult"
})
@XmlRootElement(name = "CreditCard_getTransactionRECNOResponse")
public class CreditCardGetTransactionRECNOResponse {

    @XmlElement(name = "CreditCard_getTransactionRECNOResult")
    protected int creditCardGetTransactionRECNOResult;

    /**
     * Gets the value of the creditCardGetTransactionRECNOResult property.
     * 
     */
    public int getCreditCardGetTransactionRECNOResult() {
        return creditCardGetTransactionRECNOResult;
    }

    /**
     * Sets the value of the creditCardGetTransactionRECNOResult property.
     * 
     */
    public void setCreditCardGetTransactionRECNOResult(int value) {
        this.creditCardGetTransactionRECNOResult = value;
    }

}
