
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
 *         &lt;element name="CreditCard_getCVVResponseResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "creditCardGetCVVResponseResult"
})
@XmlRootElement(name = "CreditCard_getCVVResponseResponse")
public class CreditCardGetCVVResponseResponse {

    @XmlElement(name = "CreditCard_getCVVResponseResult")
    protected String creditCardGetCVVResponseResult;

    /**
     * Gets the value of the creditCardGetCVVResponseResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardGetCVVResponseResult() {
        return creditCardGetCVVResponseResult;
    }

    /**
     * Sets the value of the creditCardGetCVVResponseResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardGetCVVResponseResult(String value) {
        this.creditCardGetCVVResponseResult = value;
    }

}
