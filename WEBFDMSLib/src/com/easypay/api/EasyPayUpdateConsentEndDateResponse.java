
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
 *         &lt;element name="EasyPay_UpdateConsentEndDateResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "easyPayUpdateConsentEndDateResult"
})
@XmlRootElement(name = "EasyPay_UpdateConsentEndDateResponse")
public class EasyPayUpdateConsentEndDateResponse {

    @XmlElement(name = "EasyPay_UpdateConsentEndDateResult")
    protected boolean easyPayUpdateConsentEndDateResult;

    /**
     * Gets the value of the easyPayUpdateConsentEndDateResult property.
     * 
     */
    public boolean isEasyPayUpdateConsentEndDateResult() {
        return easyPayUpdateConsentEndDateResult;
    }

    /**
     * Sets the value of the easyPayUpdateConsentEndDateResult property.
     * 
     */
    public void setEasyPayUpdateConsentEndDateResult(boolean value) {
        this.easyPayUpdateConsentEndDateResult = value;
    }

}
