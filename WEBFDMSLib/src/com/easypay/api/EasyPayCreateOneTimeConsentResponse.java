
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
 *         &lt;element name="EasyPay_CreateOneTimeConsentResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "easyPayCreateOneTimeConsentResult"
})
@XmlRootElement(name = "EasyPay_CreateOneTimeConsentResponse")
public class EasyPayCreateOneTimeConsentResponse {

    @XmlElement(name = "EasyPay_CreateOneTimeConsentResult")
    protected int easyPayCreateOneTimeConsentResult;

    /**
     * Gets the value of the easyPayCreateOneTimeConsentResult property.
     * 
     */
    public int getEasyPayCreateOneTimeConsentResult() {
        return easyPayCreateOneTimeConsentResult;
    }

    /**
     * Sets the value of the easyPayCreateOneTimeConsentResult property.
     * 
     */
    public void setEasyPayCreateOneTimeConsentResult(int value) {
        this.easyPayCreateOneTimeConsentResult = value;
    }

}
