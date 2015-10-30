
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
 *         &lt;element name="AcctSetup_TurnOnBatchSummaryReportResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "acctSetupTurnOnBatchSummaryReportResult"
})
@XmlRootElement(name = "AcctSetup_TurnOnBatchSummaryReportResponse")
public class AcctSetupTurnOnBatchSummaryReportResponse {

    @XmlElement(name = "AcctSetup_TurnOnBatchSummaryReportResult")
    protected String acctSetupTurnOnBatchSummaryReportResult;

    /**
     * Gets the value of the acctSetupTurnOnBatchSummaryReportResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctSetupTurnOnBatchSummaryReportResult() {
        return acctSetupTurnOnBatchSummaryReportResult;
    }

    /**
     * Sets the value of the acctSetupTurnOnBatchSummaryReportResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctSetupTurnOnBatchSummaryReportResult(String value) {
        this.acctSetupTurnOnBatchSummaryReportResult = value;
    }

}
