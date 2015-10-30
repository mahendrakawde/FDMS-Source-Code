
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
 *         &lt;element name="CreditCard_GetSettlementCountResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "creditCardGetSettlementCountResult"
})
@XmlRootElement(name = "CreditCard_GetSettlementCountResponse")
public class CreditCardGetSettlementCountResponse {

    @XmlElement(name = "CreditCard_GetSettlementCountResult")
    protected String creditCardGetSettlementCountResult;

    /**
     * Gets the value of the creditCardGetSettlementCountResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardGetSettlementCountResult() {
        return creditCardGetSettlementCountResult;
    }

    /**
     * Sets the value of the creditCardGetSettlementCountResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardGetSettlementCountResult(String value) {
        this.creditCardGetSettlementCountResult = value;
    }

}
