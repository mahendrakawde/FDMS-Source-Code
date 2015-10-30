
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
 *         &lt;element name="EComm_CompleteOrderResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "eCommCompleteOrderResult"
})
@XmlRootElement(name = "EComm_CompleteOrderResponse")
public class ECommCompleteOrderResponse {

    @XmlElement(name = "EComm_CompleteOrderResult")
    protected String eCommCompleteOrderResult;

    /**
     * Gets the value of the eCommCompleteOrderResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getECommCompleteOrderResult() {
        return eCommCompleteOrderResult;
    }

    /**
     * Sets the value of the eCommCompleteOrderResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setECommCompleteOrderResult(String value) {
        this.eCommCompleteOrderResult = value;
    }

}
