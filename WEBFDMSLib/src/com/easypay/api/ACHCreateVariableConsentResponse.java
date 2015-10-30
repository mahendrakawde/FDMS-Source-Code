
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
 *         &lt;element name="ACH_CreateVariableConsentResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "achCreateVariableConsentResult"
})
@XmlRootElement(name = "ACH_CreateVariableConsentResponse")
public class ACHCreateVariableConsentResponse {

    @XmlElement(name = "ACH_CreateVariableConsentResult")
    protected String achCreateVariableConsentResult;

    /**
     * Gets the value of the achCreateVariableConsentResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACHCreateVariableConsentResult() {
        return achCreateVariableConsentResult;
    }

    /**
     * Sets the value of the achCreateVariableConsentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACHCreateVariableConsentResult(String value) {
        this.achCreateVariableConsentResult = value;
    }

}
