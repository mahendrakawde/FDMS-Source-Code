
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
 *         &lt;element name="Restaurant_Closeout_ModifyAmount_AddTipResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "restaurantCloseoutModifyAmountAddTipResult"
})
@XmlRootElement(name = "Restaurant_Closeout_ModifyAmount_AddTipResponse")
public class RestaurantCloseoutModifyAmountAddTipResponse {

    @XmlElement(name = "Restaurant_Closeout_ModifyAmount_AddTipResult")
    protected String restaurantCloseoutModifyAmountAddTipResult;

    /**
     * Gets the value of the restaurantCloseoutModifyAmountAddTipResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestaurantCloseoutModifyAmountAddTipResult() {
        return restaurantCloseoutModifyAmountAddTipResult;
    }

    /**
     * Sets the value of the restaurantCloseoutModifyAmountAddTipResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestaurantCloseoutModifyAmountAddTipResult(String value) {
        this.restaurantCloseoutModifyAmountAddTipResult = value;
    }

}
