
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
 *         &lt;element name="CreditCard_Refund_PartialAmountResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "creditCardRefundPartialAmountResult"
})
@XmlRootElement(name = "CreditCard_Refund_PartialAmountResponse")
public class CreditCardRefundPartialAmountResponse {

    @XmlElement(name = "CreditCard_Refund_PartialAmountResult")
    protected String creditCardRefundPartialAmountResult;

    /**
     * Gets the value of the creditCardRefundPartialAmountResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardRefundPartialAmountResult() {
        return creditCardRefundPartialAmountResult;
    }

    /**
     * Sets the value of the creditCardRefundPartialAmountResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardRefundPartialAmountResult(String value) {
        this.creditCardRefundPartialAmountResult = value;
    }

}
