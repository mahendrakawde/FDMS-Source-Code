
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
 *         &lt;element name="AcctSetup_RequestBatchSummaryReportResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "acctSetupRequestBatchSummaryReportResult"
})
@XmlRootElement(name = "AcctSetup_RequestBatchSummaryReportResponse")
public class AcctSetupRequestBatchSummaryReportResponse {

    @XmlElement(name = "AcctSetup_RequestBatchSummaryReportResult")
    protected String acctSetupRequestBatchSummaryReportResult;

    /**
     * Gets the value of the acctSetupRequestBatchSummaryReportResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctSetupRequestBatchSummaryReportResult() {
        return acctSetupRequestBatchSummaryReportResult;
    }

    /**
     * Sets the value of the acctSetupRequestBatchSummaryReportResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctSetupRequestBatchSummaryReportResult(String value) {
        this.acctSetupRequestBatchSummaryReportResult = value;
    }

}
