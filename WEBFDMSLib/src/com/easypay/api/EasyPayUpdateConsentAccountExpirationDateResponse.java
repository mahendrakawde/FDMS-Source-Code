
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
 *         &lt;element name="EasyPay_UpdateConsentAccountExpirationDateResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "easyPayUpdateConsentAccountExpirationDateResult"
})
@XmlRootElement(name = "EasyPay_UpdateConsentAccountExpirationDateResponse")
public class EasyPayUpdateConsentAccountExpirationDateResponse {

    @XmlElement(name = "EasyPay_UpdateConsentAccountExpirationDateResult")
    protected boolean easyPayUpdateConsentAccountExpirationDateResult;

    /**
     * Gets the value of the easyPayUpdateConsentAccountExpirationDateResult property.
     * 
     */
    public boolean isEasyPayUpdateConsentAccountExpirationDateResult() {
        return easyPayUpdateConsentAccountExpirationDateResult;
    }

    /**
     * Sets the value of the easyPayUpdateConsentAccountExpirationDateResult property.
     * 
     */
    public void setEasyPayUpdateConsentAccountExpirationDateResult(boolean value) {
        this.easyPayUpdateConsentAccountExpirationDateResult = value;
    }

}
