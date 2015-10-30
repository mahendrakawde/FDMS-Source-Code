
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
 *         &lt;element name="ACH_GETI_GetTemplateResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "achgetiGetTemplateResult"
})
@XmlRootElement(name = "ACH_GETI_GetTemplateResponse")
public class ACHGETIGetTemplateResponse {

    @XmlElement(name = "ACH_GETI_GetTemplateResult")
    protected String achgetiGetTemplateResult;

    /**
     * Gets the value of the achgetiGetTemplateResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACHGETIGetTemplateResult() {
        return achgetiGetTemplateResult;
    }

    /**
     * Sets the value of the achgetiGetTemplateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACHGETIGetTemplateResult(String value) {
        this.achgetiGetTemplateResult = value;
    }

}
