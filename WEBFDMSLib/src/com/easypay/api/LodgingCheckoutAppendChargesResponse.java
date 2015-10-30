
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
 *         &lt;element name="Lodging_Checkout_AppendChargesResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "lodgingCheckoutAppendChargesResult"
})
@XmlRootElement(name = "Lodging_Checkout_AppendChargesResponse")
public class LodgingCheckoutAppendChargesResponse {

    @XmlElement(name = "Lodging_Checkout_AppendChargesResult")
    protected String lodgingCheckoutAppendChargesResult;

    /**
     * Gets the value of the lodgingCheckoutAppendChargesResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLodgingCheckoutAppendChargesResult() {
        return lodgingCheckoutAppendChargesResult;
    }

    /**
     * Sets the value of the lodgingCheckoutAppendChargesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLodgingCheckoutAppendChargesResult(String value) {
        this.lodgingCheckoutAppendChargesResult = value;
    }

}
