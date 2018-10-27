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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Initiating party (type)
 * 
 * <p>Java class for InitPartyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InitPartyType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:windata:xsd:fsln:sdxi:sepa:de:ccft.1.3}PartyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Id" type="{urn:windata:xsd:fsln:sdxi:sepa:de:ccft.1.3}ReferenceIdentificationType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InitPartyType", propOrder = {
    "id"
})
@XmlSeeAlso({
    InitPtyCreditorType.class
})
public class InitPartyType
    extends PartyType
{

    @XmlElement(name = "Id", required = true)
    protected String id;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}