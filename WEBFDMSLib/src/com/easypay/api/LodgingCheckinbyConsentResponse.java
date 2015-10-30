
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
 *         &lt;element name="Lodging_CheckinbyConsentResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "lodgingCheckinbyConsentResult"
})
@XmlRootElement(name = "Lodging_CheckinbyConsentResponse")
public class LodgingCheckinbyConsentResponse {

    @XmlElement(name = "Lodging_CheckinbyConsentResult")
    protected String lodgingCheckinbyConsentResult;

    /**
     * Gets the value of the lodgingCheckinbyConsentResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLodgingCheckinbyConsentResult() {
        return lodgingCheckinbyConsentResult;
    }

    /**
     * Sets the value of the lodgingCheckinbyConsentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLodgingCheckinbyConsentResult(String value) {
        this.lodgingCheckinbyConsentResult = value;
    }

}
