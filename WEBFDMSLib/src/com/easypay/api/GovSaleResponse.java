
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
 *         &lt;element name="Gov_SaleResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "govSaleResult"
})
@XmlRootElement(name = "Gov_SaleResponse")
public class GovSaleResponse {

    @XmlElement(name = "Gov_SaleResult")
    protected String govSaleResult;

    /**
     * Gets the value of the govSaleResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGovSaleResult() {
        return govSaleResult;
    }

    /**
     * Sets the value of the govSaleResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGovSaleResult(String value) {
        this.govSaleResult = value;
    }

}
