
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
 *         &lt;element name="Lodging_SaleResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "lodgingSaleResult"
})
@XmlRootElement(name = "Lodging_SaleResponse")
public class LodgingSaleResponse {

    @XmlElement(name = "Lodging_SaleResult")
    protected String lodgingSaleResult;

    /**
     * Gets the value of the lodgingSaleResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLodgingSaleResult() {
        return lodgingSaleResult;
    }

    /**
     * Sets the value of the lodgingSaleResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLodgingSaleResult(String value) {
        this.lodgingSaleResult = value;
    }

}
