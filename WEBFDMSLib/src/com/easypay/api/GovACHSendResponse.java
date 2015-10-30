
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
 *         &lt;element name="Gov_ACH_SendResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "govACHSendResult"
})
@XmlRootElement(name = "Gov_ACH_SendResponse")
public class GovACHSendResponse {

    @XmlElement(name = "Gov_ACH_SendResult")
    protected String govACHSendResult;

    /**
     * Gets the value of the govACHSendResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGovACHSendResult() {
        return govACHSendResult;
    }

    /**
     * Sets the value of the govACHSendResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGovACHSendResult(String value) {
        this.govACHSendResult = value;
    }

}
