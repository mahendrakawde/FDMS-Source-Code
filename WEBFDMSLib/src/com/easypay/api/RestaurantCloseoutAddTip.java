
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
 *         &lt;element name="passkey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecNo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Tip" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Server" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "recNo",
    "tip",
    "server",
    "user"
})
@XmlRootElement(name = "Restaurant_Closeout_AddTip")
public class RestaurantCloseoutAddTip {

    protected String passkey;
    @XmlElement(name = "RecNo")
    protected int recNo;
    @XmlElement(name = "Tip")
    protected float tip;
    @XmlElement(name = "Server")
    protected int server;
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
     * Gets the value of the recNo property.
     * 
     */
    public int getRecNo() {
        return recNo;
    }

    /**
     * Sets the value of the recNo property.
     * 
     */
    public void setRecNo(int value) {
        this.recNo = value;
    }

    /**
     * Gets the value of the tip property.
     * 
     */
    public float getTip() {
        return tip;
    }

    /**
     * Sets the value of the tip property.
     * 
     */
    public void setTip(float value) {
        this.tip = value;
    }

    /**
     * Gets the value of the server property.
     * 
     */
    public int getServer() {
        return server;
    }

    /**
     * Sets the value of the server property.
     * 
     */
    public void setServer(int value) {
        this.server = value;
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
