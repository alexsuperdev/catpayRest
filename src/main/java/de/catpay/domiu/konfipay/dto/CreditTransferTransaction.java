//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.27 at 06:29:04 PM CEST 
//


package de.catpay.domiu.konfipay.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Credit Transfer Transaction (type)
 * 
 * <p>Java class for CreditTransferTransaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditTransferTransaction"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:windata:xsd:fsln:sdxi:sepa:de:ccft.1.3}CoreTransactionType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OthrPtyCreditor" type="{urn:windata:xsd:fsln:sdxi:sepa:de:ccft.1.3}PartyType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditTransferTransaction", propOrder = {
    "othrPtyCreditor"
})
public class CreditTransferTransaction
    extends CoreTransactionType
{

    @XmlElement(name = "OthrPtyCreditor", required = true)
    protected PartyType othrPtyCreditor;

    /**
     * Gets the value of the othrPtyCreditor property.
     * 
     * @return
     *     possible object is
     *     {@link PartyType }
     *     
     */
    public PartyType getOthrPtyCreditor() {
        return othrPtyCreditor;
    }

    /**
     * Sets the value of the othrPtyCreditor property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyType }
     *     
     */
    public void setOthrPtyCreditor(PartyType value) {
        this.othrPtyCreditor = value;
    }

}
