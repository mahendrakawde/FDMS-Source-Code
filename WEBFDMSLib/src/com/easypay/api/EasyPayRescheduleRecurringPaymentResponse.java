
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
 *         &lt;element name="EasyPay_RescheduleRecurringPaymentResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "easyPayRescheduleRecurringPaymentResult"
})
@XmlRootElement(name = "EasyPay_RescheduleRecurringPaymentResponse")
public class EasyPayRescheduleRecurringPaymentResponse {

    @XmlElement(name = "EasyPay_RescheduleRecurringPaymentResult")
    protected boolean easyPayRescheduleRecurringPaymentResult;

    /**
     * Gets the value of the easyPayRescheduleRecurringPaymentResult property.
     * 
     */
    public boolean isEasyPayRescheduleRecurringPaymentResult() {
        return easyPayRescheduleRecurringPaymentResult;
    }

    /**
     * Sets the value of the easyPayRescheduleRecurringPaymentResult property.
     * 
     */
    public void setEasyPayRescheduleRecurringPaymentResult(boolean value) {
        this.easyPayRescheduleRecurringPaymentResult = value;
    }

}
