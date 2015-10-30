
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
 *         &lt;element name="EasyPay_CreateRecurringConsentByNoPaymentsResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "easyPayCreateRecurringConsentByNoPaymentsResult"
})
@XmlRootElement(name = "EasyPay_CreateRecurringConsentByNoPaymentsResponse")
public class EasyPayCreateRecurringConsentByNoPaymentsResponse {

    @XmlElement(name = "EasyPay_CreateRecurringConsentByNoPaymentsResult")
    protected int easyPayCreateRecurringConsentByNoPaymentsResult;

    /**
     * Gets the value of the easyPayCreateRecurringConsentByNoPaymentsResult property.
     * 
     */
    public int getEasyPayCreateRecurringConsentByNoPaymentsResult() {
        return easyPayCreateRecurringConsentByNoPaymentsResult;
    }

    /**
     * Sets the value of the easyPayCreateRecurringConsentByNoPaymentsResult property.
     * 
     */
    public void setEasyPayCreateRecurringConsentByNoPaymentsResult(int value) {
        this.easyPayCreateRecurringConsentByNoPaymentsResult = value;
    }

}
