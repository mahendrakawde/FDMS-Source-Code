
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
 *         &lt;element name="CreditCard_getSettlementHistoryResult" type="{http://localhost}ArrayOfSettleHistory" minOccurs="0"/>
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
    "creditCardGetSettlementHistoryResult"
})
@XmlRootElement(name = "CreditCard_getSettlementHistoryResponse")
public class CreditCardGetSettlementHistoryResponse {

    @XmlElement(name = "CreditCard_getSettlementHistoryResult")
    protected ArrayOfSettleHistory creditCardGetSettlementHistoryResult;

    /**
     * Gets the value of the creditCardGetSettlementHistoryResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSettleHistory }
     *     
     */
    public ArrayOfSettleHistory getCreditCardGetSettlementHistoryResult() {
        return creditCardGetSettlementHistoryResult;
    }

    /**
     * Sets the value of the creditCardGetSettlementHistoryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSettleHistory }
     *     
     */
    public void setCreditCardGetSettlementHistoryResult(ArrayOfSettleHistory value) {
        this.creditCardGetSettlementHistoryResult = value;
    }

}
