
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
 *         &lt;element name="Restaurant_Closeout_AddTipResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "restaurantCloseoutAddTipResult"
})
@XmlRootElement(name = "Restaurant_Closeout_AddTipResponse")
public class RestaurantCloseoutAddTipResponse {

    @XmlElement(name = "Restaurant_Closeout_AddTipResult")
    protected String restaurantCloseoutAddTipResult;

    /**
     * Gets the value of the restaurantCloseoutAddTipResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestaurantCloseoutAddTipResult() {
        return restaurantCloseoutAddTipResult;
    }

    /**
     * Sets the value of the restaurantCloseoutAddTipResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestaurantCloseoutAddTipResult(String value) {
        this.restaurantCloseoutAddTipResult = value;
    }

}
