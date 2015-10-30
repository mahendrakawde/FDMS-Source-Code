
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
 *         &lt;element name="EasyPay_CreateVariableConsentByNoPaymentsResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "easyPayCreateVariableConsentByNoPaymentsResult"
})
@XmlRootElement(name = "EasyPay_CreateVariableConsentByNoPaymentsResponse")
public class EasyPayCreateVariableConsentByNoPaymentsResponse {

    @XmlElement(name = "EasyPay_CreateVariableConsentByNoPaymentsResult")
    protected int easyPayCreateVariableConsentByNoPaymentsResult;

    /**
     * Gets the value of the easyPayCreateVariableConsentByNoPaymentsResult property.
     * 
     */
    public int getEasyPayCreateVariableConsentByNoPaymentsResult() {
        return easyPayCreateVariableConsentByNoPaymentsResult;
    }

    /**
     * Sets the value of the easyPayCreateVariableConsentByNoPaymentsResult property.
     * 
     */
    public void setEasyPayCreateVariableConsentByNoPaymentsResult(int value) {
        this.easyPayCreateVariableConsentByNoPaymentsResult = value;
    }

}