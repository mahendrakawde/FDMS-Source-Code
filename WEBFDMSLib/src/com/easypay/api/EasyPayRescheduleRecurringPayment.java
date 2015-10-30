
package com.easypay.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="passkey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SchedRecNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NewDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="MerchRec" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "passkey",
    "schedRecNo",
    "newDate",
    "merchRec",
    "user"
})
@XmlRootElement(name = "EasyPay_RescheduleRecurringPayment")
public class EasyPayRescheduleRecurringPayment {

    protected String passkey;
    @XmlElement(name = "SchedRecNo")
    protected int schedRecNo;
    @XmlElement(name = "NewDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar newDate;
    @XmlElement(name = "MerchRec")
    protected int merchRec;
    @XmlElement(name = "User")
    protected String user;

    /**
     * Gets the value of the passkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPasskey() {
        return passkey;
    }

    /**
     * Sets the value of the passkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPasskey(String value) {
        this.passkey = value;
    }

    /**
     * Gets the value of the schedRecNo property.
     * 
     */
    public int getSchedRecNo() {
        return schedRecNo;
    }

    /**
     * Sets the value of the schedRecNo property.
     * 
     */
    public void setSchedRecNo(int value) {
        this.schedRecNo = value;
    }

    /**
     * Gets the value of the newDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNewDate() {
        return newDate;
    }

    /**
     * Sets the value of the newDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNewDate(XMLGregorianCalendar value) {
        this.newDate = value;
    }

    /**
     * Gets the value of the merchRec property.
     * 
     */
    public int getMerchRec() {
        return merchRec;
    }

    /**
     * Sets the value of the merchRec property.
     * 
     */
    public void setMerchRec(int value) {
        this.merchRec = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

}
