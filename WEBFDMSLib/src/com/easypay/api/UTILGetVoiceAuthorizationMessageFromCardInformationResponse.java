
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
 *         &lt;element name="UTIL_getVoiceAuthorizationMessageFromCardInformationResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "utilGetVoiceAuthorizationMessageFromCardInformationResult"
})
@XmlRootElement(name = "UTIL_getVoiceAuthorizationMessageFromCardInformationResponse")
public class UTILGetVoiceAuthorizationMessageFromCardInformationResponse {

    @XmlElement(name = "UTIL_getVoiceAuthorizationMessageFromCardInformationResult")
    protected String utilGetVoiceAuthorizationMessageFromCardInformationResult;

    /**
     * Gets the value of the utilGetVoiceAuthorizationMessageFromCardInformationResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUTILGetVoiceAuthorizationMessageFromCardInformationResult() {
        return utilGetVoiceAuthorizationMessageFromCardInformationResult;
    }

    /**
     * Sets the value of the utilGetVoiceAuthorizationMessageFromCardInformationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUTILGetVoiceAuthorizationMessageFromCardInformationResult(String value) {
        this.utilGetVoiceAuthorizationMessageFromCardInformationResult = value;
    }

}
