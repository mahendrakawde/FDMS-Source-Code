
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
 *         &lt;element name="UTIL_isDebitCardResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "utilIsDebitCardResult"
})
@XmlRootElement(name = "UTIL_isDebitCardResponse")
public class UTILIsDebitCardResponse {

    @XmlElement(name = "UTIL_isDebitCardResult")
    protected String utilIsDebitCardResult;

    /**
     * Gets the value of the utilIsDebitCardResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUTILIsDebitCardResult() {
        return utilIsDebitCardResult;
    }

    /**
     * Sets the value of the utilIsDebitCardResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUTILIsDebitCardResult(String value) {
        this.utilIsDebitCardResult = value;
    }

}
