
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
 *         &lt;element name="EasyPay_CancelPaymentResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "easyPayCancelPaymentResult"
})
@XmlRootElement(name = "EasyPay_CancelPaymentResponse")
public class EasyPayCancelPaymentResponse {

    @XmlElement(name = "EasyPay_CancelPaymentResult")
    protected boolean easyPayCancelPaymentResult;

    /**
     * Gets the value of the easyPayCancelPaymentResult property.
     * 
     */
    public boolean isEasyPayCancelPaymentResult() {
        return easyPayCancelPaymentResult;
    }

    /**
     * Sets the value of the easyPayCancelPaymentResult property.
     * 
     */
    public void setEasyPayCancelPaymentResult(boolean value) {
        this.easyPayCancelPaymentResult = value;
    }

}
