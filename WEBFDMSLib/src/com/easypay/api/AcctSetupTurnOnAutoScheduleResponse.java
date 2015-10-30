
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
 *         &lt;element name="AcctSetup_TurnOnAuto_ScheduleResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "acctSetupTurnOnAutoScheduleResult"
})
@XmlRootElement(name = "AcctSetup_TurnOnAuto_ScheduleResponse")
public class AcctSetupTurnOnAutoScheduleResponse {

    @XmlElement(name = "AcctSetup_TurnOnAuto_ScheduleResult")
    protected String acctSetupTurnOnAutoScheduleResult;

    /**
     * Gets the value of the acctSetupTurnOnAutoScheduleResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctSetupTurnOnAutoScheduleResult() {
        return acctSetupTurnOnAutoScheduleResult;
    }

    /**
     * Sets the value of the acctSetupTurnOnAutoScheduleResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctSetupTurnOnAutoScheduleResult(String value) {
        this.acctSetupTurnOnAutoScheduleResult = value;
    }

}
