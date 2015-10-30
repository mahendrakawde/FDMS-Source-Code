
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
 *         &lt;element name="EasyPay_findExpiredCardsResult" type="{http://localhost}ArrayOfExpiredCards" minOccurs="0"/>
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
    "easyPayFindExpiredCardsResult"
})
@XmlRootElement(name = "EasyPay_findExpiredCardsResponse")
public class EasyPayFindExpiredCardsResponse {

    @XmlElement(name = "EasyPay_findExpiredCardsResult")
    protected ArrayOfExpiredCards easyPayFindExpiredCardsResult;

    /**
     * Gets the value of the easyPayFindExpiredCardsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfExpiredCards }
     *     
     */
    public ArrayOfExpiredCards getEasyPayFindExpiredCardsResult() {
        return easyPayFindExpiredCardsResult;
    }

    /**
     * Sets the value of the easyPayFindExpiredCardsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfExpiredCards }
     *     
     */
    public void setEasyPayFindExpiredCardsResult(ArrayOfExpiredCards value) {
        this.easyPayFindExpiredCardsResult = value;
    }

}
