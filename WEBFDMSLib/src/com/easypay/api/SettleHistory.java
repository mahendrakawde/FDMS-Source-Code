
package com.easypay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SettleHistory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SettleHistory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="REC_NO" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BATCH_NO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SETTLEMENT_RESP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SETTLEMENT_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="SETTLEMENT_STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SETTLEMENT_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SettleHistory", propOrder = {
    "recno",
    "batchno",
    "settlementresp",
    "settlementdate",
    "settlementstatus",
    "settlementamount"
})
public class SettleHistory {

    @XmlElement(name = "REC_NO")
    protected int recno;
    @XmlElement(name = "BATCH_NO")
    protected String batchno;
    @XmlElement(name = "SETTLEMENT_RESP")
    protected String settlementresp;
    @XmlElement(name = "SETTLEMENT_DATE", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar settlementdate;
    @XmlElement(name = "SETTLEMENT_STATUS")
    protected String settlementstatus;
    @XmlElement(name = "SETTLEMENT_AMOUNT")
    protected float settlementamount;

    /**
     * Gets the value of the recno property.
     * 
     */
    public int getRECNO() {
        return recno;
    }

    /**
     * Sets the value of the recno property.
     * 
     */
    public void setRECNO(int value) {
        this.recno = value;
    }

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
     * Gets the value of the settlementresp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSETTLEMENTRESP() {
        return settlementresp;
    }

    /**
     * Sets the value of the settlementresp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSETTLEMENTRESP(String value) {
        this.settlementresp = value;
    }

    /**
     * Gets the value of the settlementdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSETTLEMENTDATE() {
        return settlementdate;
    }

    /**
     * Sets the value of the settlementdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSETTLEMENTDATE(XMLGregorianCalendar value) {
        this.settlementdate = value;
    }

    /**
     * Gets the value of the settlementstatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSETTLEMENTSTATUS() {
        return settlementstatus;
    }

    /**
     * Sets the value of the settlementstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSETTLEMENTSTATUS(String value) {
        this.settlementstatus = value;
    }

    /**
     * Gets the value of the settlementamount property.
     * 
     */
    public float getSETTLEMENTAMOUNT() {
        return settlementamount;
    }

    /**
     * Sets the value of the settlementamount property.
     * 
     */
    public void setSETTLEMENTAMOUNT(float value) {
        this.settlementamount = value;
    }

}
