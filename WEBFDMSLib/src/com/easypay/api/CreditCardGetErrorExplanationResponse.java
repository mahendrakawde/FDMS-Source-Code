
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
 *         &lt;element name="CreditCard_getErrorExplanationResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "creditCardGetErrorExplanationResult"
})
@XmlRootElement(name = "CreditCard_getErrorExplanationResponse")
public class CreditCardGetErrorExplanationResponse {

    @XmlElement(name = "CreditCard_getErrorExplanationResult")
    protected String creditCardGetErrorExplanationResult;

    /**
     * Gets the value of the creditCardGetErrorExplanationResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardGetErrorExplanationResult() {
        return creditCardGetErrorExplanationResult;
    }

    /**
     * Sets the value of the creditCardGetErrorExplanationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardGetErrorExplanationResult(String value) {
        this.creditCardGetErrorExplanationResult = value;
    }

}
