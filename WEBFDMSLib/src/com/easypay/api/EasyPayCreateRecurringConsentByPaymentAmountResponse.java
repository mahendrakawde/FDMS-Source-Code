
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
 *         &lt;element name="EasyPay_CreateRecurringConsentByPaymentAmountResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "easyPayCreateRecurringConsentByPaymentAmountResult"
})
@XmlRootElement(name = "EasyPay_CreateRecurringConsentByPaymentAmountResponse")
public class EasyPayCreateRecurringConsentByPaymentAmountResponse {

    @XmlElement(name = "EasyPay_CreateRecurringConsentByPaymentAmountResult")
    protected int easyPayCreateRecurringConsentByPaymentAmountResult;

    /**
     * Gets the value of the easyPayCreateRecurringConsentByPaymentAmountResult property.
     * 
     */
    public int getEasyPayCreateRecurringConsentByPaymentAmountResult() {
        return easyPayCreateRecurringConsentByPaymentAmountResult;
    }

    /**
     * Sets the value of the easyPayCreateRecurringConsentByPaymentAmountResult property.
     * 
     */
    public void setEasyPayCreateRecurringConsentByPaymentAmountResult(int value) {
        this.easyPayCreateRecurringConsentByPaymentAmountResult = value;
    }

}
