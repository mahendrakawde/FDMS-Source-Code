
package com.easypay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AcctSetup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AcctSetup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AUTO_SETTLE" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AUTO_SCHEDULE" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AUTO_SETTLE_HOUR" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AUTO_SETTLE_MINUTE" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EMAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BATCH_REPORT" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TRANSACTION_REPORT" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcctSetup", propOrder = {
    "autosettle",
    "autoschedule",
    "autosettlehour",
    "autosettleminute",
    "email",
    "batchreport",
    "transactionreport"
})
public class AcctSetup {

    @XmlElement(name = "AUTO_SETTLE")
    protected boolean autosettle;
    @XmlElement(name = "AUTO_SCHEDULE")
    protected boolean autoschedule;
    @XmlElement(name = "AUTO_SETTLE_HOUR")
    protected int autosettlehour;
    @XmlElement(name = "AUTO_SETTLE_MINUTE")
    protected int autosettleminute;
    @XmlElement(name = "EMAIL")
    protected String email;
    @XmlElement(name = "BATCH_REPORT")
    protected boolean batchreport;
    @XmlElement(name = "TRANSACTION_REPORT")
    protected boolean transactionreport;

    /**
     * Gets the value of the autosettle property.
     * 
     */
    public boolean isAUTOSETTLE() {
        return autosettle;
    }

    /**
     * Sets the value of the autosettle property.
     * 
     */
    public void setAUTOSETTLE(boolean value) {
        this.autosettle = value;
    }

    /**
     * Gets the value of the autoschedule property.
     * 
     */
    public boolean isAUTOSCHEDULE() {
        return autoschedule;
    }

    /**
     * Sets the value of the autoschedule property.
     * 
     */
    public void setAUTOSCHEDULE(boolean value) {
        this.autoschedule = value;
    }

    /**
     * Gets the value of the autosettlehour property.
     * 
     */
    public int getAUTOSETTLEHOUR() {
        return autosettlehour;
    }

    /**
     * Sets the value of the autosettlehour property.
     * 
     */
    public void setAUTOSETTLEHOUR(int value) {
        this.autosettlehour = value;
    }

    /**
     * Gets the value of the autosettleminute property.
     * 
     */
    public int getAUTOSETTLEMINUTE() {
        return autosettleminute;
    }

    /**
     * Sets the value of the autosettleminute property.
     * 
     */
    public void setAUTOSETTLEMINUTE(int value) {
        this.autosettleminute = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMAIL() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMAIL(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the batchreport property.
     * 
     */
    public boolean isBATCHREPORT() {
        return batchreport;
    }

    /**
     * Sets the value of the batchreport property.
     * 
     */
    public void setBATCHREPORT(boolean value) {
        this.batchreport = value;
    }

    /**
     * Gets the value of the transactionreport property.
     * 
     */
    public boolean isTRANSACTIONREPORT() {
        return transactionreport;
    }

    /**
     * Sets the value of the transactionreport property.
     * 
     */
    public void setTRANSACTIONREPORT(boolean value) {
        this.transactionreport = value;
    }

}
