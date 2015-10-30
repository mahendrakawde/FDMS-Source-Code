
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
 *         &lt;element name="EasyPay_GetScheduledDueCountResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "easyPayGetScheduledDueCountResult"
})
@XmlRootElement(name = "EasyPay_GetScheduledDueCountResponse")
public class EasyPayGetScheduledDueCountResponse {

    @XmlElement(name = "EasyPay_GetScheduledDueCountResult")
    protected String easyPayGetScheduledDueCountResult;

    /**
     * Gets the value of the easyPayGetScheduledDueCountResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEasyPayGetScheduledDueCountResult() {
        return easyPayGetScheduledDueCountResult;
    }

    /**
     * Sets the value of the easyPayGetScheduledDueCountResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEasyPayGetScheduledDueCountResult(String value) {
        this.easyPayGetScheduledDueCountResult = value;
    }

}
