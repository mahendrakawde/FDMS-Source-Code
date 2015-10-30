
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
 *         &lt;element name="EasyPay_DeActivateConsentsResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "easyPayDeActivateConsentsResult"
})
@XmlRootElement(name = "EasyPay_DeActivateConsentsResponse")
public class EasyPayDeActivateConsentsResponse {

    @XmlElement(name = "EasyPay_DeActivateConsentsResult")
    protected boolean easyPayDeActivateConsentsResult;

    /**
     * Gets the value of the easyPayDeActivateConsentsResult property.
     * 
     */
    public boolean isEasyPayDeActivateConsentsResult() {
        return easyPayDeActivateConsentsResult;
    }

    /**
     * Sets the value of the easyPayDeActivateConsentsResult property.
     * 
     */
    public void setEasyPayDeActivateConsentsResult(boolean value) {
        this.easyPayDeActivateConsentsResult = value;
    }

}
