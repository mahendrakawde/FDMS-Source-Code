
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
 *         &lt;element name="UTIL_isLevelIIResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "utilIsLevelIIResult"
})
@XmlRootElement(name = "UTIL_isLevelIIResponse")
public class UTILIsLevelIIResponse {

    @XmlElement(name = "UTIL_isLevelIIResult")
    protected String utilIsLevelIIResult;

    /**
     * Gets the value of the utilIsLevelIIResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUTILIsLevelIIResult() {
        return utilIsLevelIIResult;
    }

    /**
     * Sets the value of the utilIsLevelIIResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUTILIsLevelIIResult(String value) {
        this.utilIsLevelIIResult = value;
    }

}
