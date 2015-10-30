
package com.easypay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
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
 *         &lt;element name="UTIL_getMerchantSettingsResult" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;any/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "utilGetMerchantSettingsResult"
})
@XmlRootElement(name = "UTIL_getMerchantSettingsResponse")
public class UTILGetMerchantSettingsResponse {

    @XmlElement(name = "UTIL_getMerchantSettingsResult")
    protected UTILGetMerchantSettingsResponse.UTILGetMerchantSettingsResult utilGetMerchantSettingsResult;

    /**
     * Gets the value of the utilGetMerchantSettingsResult property.
     * 
     * @return
     *     possible object is
     *     {@link UTILGetMerchantSettingsResponse.UTILGetMerchantSettingsResult }
     *     
     */
    public UTILGetMerchantSettingsResponse.UTILGetMerchantSettingsResult getUTILGetMerchantSettingsResult() {
        return utilGetMerchantSettingsResult;
    }

    /**
     * Sets the value of the utilGetMerchantSettingsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link UTILGetMerchantSettingsResponse.UTILGetMerchantSettingsResult }
     *     
     */
    public void setUTILGetMerchantSettingsResult(UTILGetMerchantSettingsResponse.UTILGetMerchantSettingsResult value) {
        this.utilGetMerchantSettingsResult = value;
    }


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
     *         &lt;any/>
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
        "any"
    })
    public static class UTILGetMerchantSettingsResult {

        @XmlAnyElement(lax = true)
        protected Object any;

        /**
         * Gets the value of the any property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getAny() {
            return any;
        }

        /**
         * Sets the value of the any property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setAny(Object value) {
            this.any = value;
        }

    }

}
