
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
 *         &lt;element name="AcctSetup_getAcctSetupResult" type="{http://localhost}AcctSetup" minOccurs="0"/>
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
    "acctSetupGetAcctSetupResult"
})
@XmlRootElement(name = "AcctSetup_getAcctSetupResponse")
public class AcctSetupGetAcctSetupResponse {

    @XmlElement(name = "AcctSetup_getAcctSetupResult")
    protected AcctSetup acctSetupGetAcctSetupResult;

    /**
     * Gets the value of the acctSetupGetAcctSetupResult property.
     * 
     * @return
     *     possible object is
     *     {@link AcctSetup }
     *     
     */
    public AcctSetup getAcctSetupGetAcctSetupResult() {
        return acctSetupGetAcctSetupResult;
    }

    /**
     * Sets the value of the acctSetupGetAcctSetupResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcctSetup }
     *     
     */
    public void setAcctSetupGetAcctSetupResult(AcctSetup value) {
        this.acctSetupGetAcctSetupResult = value;
    }

}
