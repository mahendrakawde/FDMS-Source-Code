
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
 *         &lt;element name="CreditCard_getAVSResponseResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "creditCardGetAVSResponseResult"
})
@XmlRootElement(name = "CreditCard_getAVSResponseResponse")
public class CreditCardGetAVSResponseResponse {

    @XmlElement(name = "CreditCard_getAVSResponseResult")
    protected String creditCardGetAVSResponseResult;

    /**
     * Gets the value of the creditCardGetAVSResponseResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardGetAVSResponseResult() {
        return creditCardGetAVSResponseResult;
    }

    /**
     * Sets the value of the creditCardGetAVSResponseResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardGetAVSResponseResult(String value) {
        this.creditCardGetAVSResponseResult = value;
    }

}
