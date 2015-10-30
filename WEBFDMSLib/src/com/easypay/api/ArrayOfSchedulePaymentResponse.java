
package com.easypay.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSchedulePaymentResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSchedulePaymentResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SchedulePaymentResponse" type="{http://localhost}SchedulePaymentResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSchedulePaymentResponse", propOrder = {
    "schedulePaymentResponse"
})
public class ArrayOfSchedulePaymentResponse {

    @XmlElement(name = "SchedulePaymentResponse", nillable = true)
    protected List<SchedulePaymentResponse> schedulePaymentResponse;

    /**
     * Gets the value of the schedulePaymentResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the schedulePaymentResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSchedulePaymentResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SchedulePaymentResponse }
     * 
     * 
     */
    public List<SchedulePaymentResponse> getSchedulePaymentResponse() {
        if (schedulePaymentResponse == null) {
            schedulePaymentResponse = new ArrayList<SchedulePaymentResponse>();
        }
        return this.schedulePaymentResponse;
    }

}
