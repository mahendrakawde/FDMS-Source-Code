
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
 *         &lt;element name="EasyPay_ChangeNextScheduledPaymentDateResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "easyPayChangeNextScheduledPaymentDateResult"
})
@XmlRootElement(name = "EasyPay_ChangeNextScheduledPaymentDateResponse")
public class EasyPayChangeNextScheduledPaymentDateResponse {

    @XmlElement(name = "EasyPay_ChangeNextScheduledPaymentDateResult")
    protected boolean easyPayChangeNextScheduledPaymentDateResult;

    /**
     * Gets the value of the easyPayChangeNextScheduledPaymentDateResult property.
     * 
     */
    public boolean isEasyPayChangeNextScheduledPaymentDateResult() {
        return easyPayChangeNextScheduledPaymentDateResult;
    }

    /**
     * Sets the value of the easyPayChangeNextScheduledPaymentDateResult property.
     * 
     */
    public void setEasyPayChangeNextScheduledPaymentDateResult(boolean value) {
        this.easyPayChangeNextScheduledPaymentDateResult = value;
    }

}
