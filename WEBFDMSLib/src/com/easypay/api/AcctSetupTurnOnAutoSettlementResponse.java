
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
 *         &lt;element name="AcctSetup_TurnOn_AutoSettlementResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "acctSetupTurnOnAutoSettlementResult"
})
@XmlRootElement(name = "AcctSetup_TurnOn_AutoSettlementResponse")
public class AcctSetupTurnOnAutoSettlementResponse {

    @XmlElement(name = "AcctSetup_TurnOn_AutoSettlementResult")
    protected String acctSetupTurnOnAutoSettlementResult;

    /**
     * Gets the value of the acctSetupTurnOnAutoSettlementResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctSetupTurnOnAutoSettlementResult() {
        return acctSetupTurnOnAutoSettlementResult;
    }

    /**
     * Sets the value of the acctSetupTurnOnAutoSettlementResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctSetupTurnOnAutoSettlementResult(String value) {
        this.acctSetupTurnOnAutoSettlementResult = value;
    }

}
