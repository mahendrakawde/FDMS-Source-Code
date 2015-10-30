
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
 *         &lt;element name="EasyPay_CreateSubscriptionConsentResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "easyPayCreateSubscriptionConsentResult"
})
@XmlRootElement(name = "EasyPay_CreateSubscriptionConsentResponse")
public class EasyPayCreateSubscriptionConsentResponse {

    @XmlElement(name = "EasyPay_CreateSubscriptionConsentResult")
    protected int easyPayCreateSubscriptionConsentResult;

    /**
     * Gets the value of the easyPayCreateSubscriptionConsentResult property.
     * 
     */
    public int getEasyPayCreateSubscriptionConsentResult() {
        return easyPayCreateSubscriptionConsentResult;
    }

    /**
     * Sets the value of the easyPayCreateSubscriptionConsentResult property.
     * 
     */
    public void setEasyPayCreateSubscriptionConsentResult(int value) {
        this.easyPayCreateSubscriptionConsentResult = value;
    }

}
