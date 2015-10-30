
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
 *         &lt;element name="UTIL_isLineItemsResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "utilIsLineItemsResult"
})
@XmlRootElement(name = "UTIL_isLineItemsResponse")
public class UTILIsLineItemsResponse {

    @XmlElement(name = "UTIL_isLineItemsResult")
    protected String utilIsLineItemsResult;

    /**
     * Gets the value of the utilIsLineItemsResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUTILIsLineItemsResult() {
        return utilIsLineItemsResult;
    }

    /**
     * Sets the value of the utilIsLineItemsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUTILIsLineItemsResult(String value) {
        this.utilIsLineItemsResult = value;
    }

}
