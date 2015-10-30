
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
 *         &lt;element name="EasyPay_getScheduleResult" type="{http://localhost}ArrayOfEP_Schedule" minOccurs="0"/>
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
    "easyPayGetScheduleResult"
})
@XmlRootElement(name = "EasyPay_getScheduleResponse")
public class EasyPayGetScheduleResponse {

    @XmlElement(name = "EasyPay_getScheduleResult")
    protected ArrayOfEPSchedule easyPayGetScheduleResult;

    /**
     * Gets the value of the easyPayGetScheduleResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEPSchedule }
     *     
     */
    public ArrayOfEPSchedule getEasyPayGetScheduleResult() {
        return easyPayGetScheduleResult;
    }

    /**
     * Sets the value of the easyPayGetScheduleResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEPSchedule }
     *     
     */
    public void setEasyPayGetScheduleResult(ArrayOfEPSchedule value) {
        this.easyPayGetScheduleResult = value;
    }

}
