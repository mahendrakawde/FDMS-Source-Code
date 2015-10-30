
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
 *         &lt;element name="EasyPay_UpdateConsentEmailResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "easyPayUpdateConsentEmailResult"
})
@XmlRootElement(name = "EasyPay_UpdateConsentEmailResponse")
public class EasyPayUpdateConsentEmailResponse {

    @XmlElement(name = "EasyPay_UpdateConsentEmailResult")
    protected boolean easyPayUpdateConsentEmailResult;

    /**
     * Gets the value of the easyPayUpdateConsentEmailResult property.
     * 
     */
    public boolean isEasyPayUpdateConsentEmailResult() {
        return easyPayUpdateConsentEmailResult;
    }

    /**
     * Sets the value of the easyPayUpdateConsentEmailResult property.
     * 
     */
    public void setEasyPayUpdateConsentEmailResult(boolean value) {
        this.easyPayUpdateConsentEmailResult = value;
    }

}
