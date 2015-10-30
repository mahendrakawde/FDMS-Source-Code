
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
 *         &lt;element name="EasyPay_ProcessConsentResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "easyPayProcessConsentResult"
})
@XmlRootElement(name = "EasyPay_ProcessConsentResponse")
public class EasyPayProcessConsentResponse {

    @XmlElement(name = "EasyPay_ProcessConsentResult")
    protected String easyPayProcessConsentResult;

    /**
     * Gets the value of the easyPayProcessConsentResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEasyPayProcessConsentResult() {
        return easyPayProcessConsentResult;
    }

    /**
     * Sets the value of the easyPayProcessConsentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEasyPayProcessConsentResult(String value) {
        this.easyPayProcessConsentResult = value;
    }

}
