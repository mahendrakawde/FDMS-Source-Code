
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
 *         &lt;element name="AcctSetup_TurnOnTransactionSummaryReportResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "acctSetupTurnOnTransactionSummaryReportResult"
})
@XmlRootElement(name = "AcctSetup_TurnOnTransactionSummaryReportResponse")
public class AcctSetupTurnOnTransactionSummaryReportResponse {

    @XmlElement(name = "AcctSetup_TurnOnTransactionSummaryReportResult")
    protected String acctSetupTurnOnTransactionSummaryReportResult;

    /**
     * Gets the value of the acctSetupTurnOnTransactionSummaryReportResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctSetupTurnOnTransactionSummaryReportResult() {
        return acctSetupTurnOnTransactionSummaryReportResult;
    }

    /**
     * Sets the value of the acctSetupTurnOnTransactionSummaryReportResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctSetupTurnOnTransactionSummaryReportResult(String value) {
        this.acctSetupTurnOnTransactionSummaryReportResult = value;
    }

}
