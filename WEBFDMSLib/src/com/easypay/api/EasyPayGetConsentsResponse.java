
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
 *         &lt;element name="EasyPay_getConsentsResult" type="{http://localhost}ArrayOfEP_EasyPay" minOccurs="0"/>
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
    "easyPayGetConsentsResult"
})
@XmlRootElement(name = "EasyPay_getConsentsResponse")
public class EasyPayGetConsentsResponse {

    @XmlElement(name = "EasyPay_getConsentsResult")
    protected ArrayOfEPEasyPay easyPayGetConsentsResult;

    /**
     * Gets the value of the easyPayGetConsentsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEPEasyPay }
     *     
     */
    public ArrayOfEPEasyPay getEasyPayGetConsentsResult() {
        return easyPayGetConsentsResult;
    }

    /**
     * Sets the value of the easyPayGetConsentsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEPEasyPay }
     *     
     */
    public void setEasyPayGetConsentsResult(ArrayOfEPEasyPay value) {
        this.easyPayGetConsentsResult = value;
    }

}
