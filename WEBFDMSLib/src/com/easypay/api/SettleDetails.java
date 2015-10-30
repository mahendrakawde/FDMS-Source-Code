
package com.easypay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SettleDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SettleDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BATCH_NO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SEQ_NO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AUTH_MSG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AUTH_RESP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SettleDetails", propOrder = {
    "batchno",
    "seqno",
    "authmsg",
    "authresp",
    "amount"
})
public class SettleDetails {

    @XmlElement(name = "BATCH_NO")
    protected String batchno;
    @XmlElement(name = "SEQ_NO")
    protected String seqno;
    @XmlElement(name = "AUTH_MSG")
    protected String authmsg;
    @XmlElement(name = "AUTH_RESP")
    protected String authresp;
    @XmlElement(name = "AMOUNT")
    protected String amount;

    /**
     * Gets the value of the batchno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBATCHNO() {
        return batchno;
    }

    /**
     * Sets the value of the batchno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBATCHNO(String value) {
        this.batchno = value;
    }

    /**
     * Gets the value of the seqno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSEQNO() {
        return seqno;
    }

    /**
     * Sets the value of the seqno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSEQNO(String value) {
        this.seqno = value;
    }

    /**
     * Gets the value of the authmsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUTHMSG() {
        return authmsg;
    }

    /**
     * Sets the value of the authmsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUTHMSG(String value) {
        this.authmsg = value;
    }

    /**
     * Gets the value of the authresp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUTHRESP() {
        return authresp;
    }

    /**
     * Sets the value of the authresp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUTHRESP(String value) {
        this.authresp = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAMOUNT() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAMOUNT(String value) {
        this.amount = value;
    }

}
