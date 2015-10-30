
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
 *         &lt;element name="EasyPay_ScheduledPaymentResponseResult" type="{http://localhost}ArrayOfSchedulePaymentResponse" minOccurs="0"/>
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
    "easyPayScheduledPaymentResponseResult"
})
@XmlRootElement(name = "EasyPay_ScheduledPaymentResponseResponse")
public class EasyPayScheduledPaymentResponseResponse {

    @XmlElement(name = "EasyPay_ScheduledPaymentResponseResult")
    protected ArrayOfSchedulePaymentResponse easyPayScheduledPaymentResponseResult;

    /**
     * Gets the value of the easyPayScheduledPaymentResponseResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSchedulePaymentResponse }
     *     
     */
    public ArrayOfSchedulePaymentResponse getEasyPayScheduledPaymentResponseResult() {
        return easyPayScheduledPaymentResponseResult;
    }

    /**
     * Sets the value of the easyPayScheduledPaymentResponseResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSchedulePaymentResponse }
     *     
     */
    public void setEasyPayScheduledPaymentResponseResult(ArrayOfSchedulePaymentResponse value) {
        this.easyPayScheduledPaymentResponseResult = value;
    }

}
