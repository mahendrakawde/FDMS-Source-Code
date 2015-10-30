
package com.easypay.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfExpiredCards complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfExpiredCards">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExpiredCards" type="{http://localhost}ExpiredCards" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfExpiredCards", propOrder = {
    "expiredCards"
})
public class ArrayOfExpiredCards {

    @XmlElement(name = "ExpiredCards", nillable = true)
    protected List<ExpiredCards> expiredCards;

    /**
     * Gets the value of the expiredCards property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the expiredCards property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExpiredCards().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExpiredCards }
     * 
     * 
     */
    public List<ExpiredCards> getExpiredCards() {
        if (expiredCards == null) {
            expiredCards = new ArrayList<ExpiredCards>();
        }
        return this.expiredCards;
    }

}
