
package com.easypay.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfEP_EasyPay complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfEP_EasyPay">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EP_EasyPay" type="{http://localhost}EP_EasyPay" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfEP_EasyPay", propOrder = {
    "epEasyPay"
})
public class ArrayOfEPEasyPay {

    @XmlElement(name = "EP_EasyPay", nillable = true)
    protected List<EPEasyPay> epEasyPay;

    /**
     * Gets the value of the epEasyPay property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the epEasyPay property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEPEasyPay().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EPEasyPay }
     * 
     * 
     */
    public List<EPEasyPay> getEPEasyPay() {
        if (epEasyPay == null) {
            epEasyPay = new ArrayList<EPEasyPay>();
        }
        return this.epEasyPay;
    }

}
