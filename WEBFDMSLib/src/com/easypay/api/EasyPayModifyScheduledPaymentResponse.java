
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
 *         &lt;element name="EasyPay_ModifyScheduledPaymentResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "easyPayModifyScheduledPaymentResult"
})
@XmlRootElement(name = "EasyPay_ModifyScheduledPaymentResponse")
public class EasyPayModifyScheduledPaymentResponse {

    @XmlElement(name = "EasyPay_ModifyScheduledPaymentResult")
    protected boolean easyPayModifyScheduledPaymentResult;

    /**
     * Gets the value of the easyPayModifyScheduledPaymentResult property.
     * 
     */
    public boolean isEasyPayModifyScheduledPaymentResult() {
        return easyPayModifyScheduledPaymentResult;
    }

    /**
     * Sets the value of the easyPayModifyScheduledPaymentResult property.
     * 
     */
    public void setEasyPayModifyScheduledPaymentResult(boolean value) {
        this.easyPayModifyScheduledPaymentResult = value;
    }

}
