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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Mandate related data for Direct Debit transactions (type)
 * 
 * <p>Java class for MandateDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MandateDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MandateId" type="{urn:windata:xsd:fsln:sdxi:sepa:de:ccft.1.3}MandateIdType"/&gt;
 *         &lt;element name="MandateDate" type="{urn:windata:xsd:fsln:sdxi:sepa:de:ccft.1.3}ISODateType"/&gt;
 *         &lt;element name="Sequence" type="{urn:windata:xsd:fsln:sdxi:sepa:de:ccft.1.3}SequenceCodeType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MandateDataType", propOrder = {
    "mandateId",
    "mandateDate",
    "sequence"
})
public class MandateDataType {

    @XmlElement(name = "MandateId", required = true)
    protected String mandateId;
    @XmlElement(name = "MandateDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar mandateDate;
    @XmlElement(name = "Sequence", required = true)
    @XmlSchemaType(name = "string")
    protected SequenceCodeType sequence;

    /**
     * Gets the value of the mandateId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMandateId() {
        return mandateId;
    }

    /**
     * Sets the value of the mandateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMandateId(String value) {
        this.mandateId = value;
    }

    /**
     * Gets the value of the mandateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMandateDate() {
        return mandateDate;
    }

    /**
     * Sets the value of the mandateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMandateDate(XMLGregorianCalendar value) {
        this.mandateDate = value;
    }

    /**
     * Gets the value of the sequence property.
     * 
     * @return
     *     possible object is
     *     {@link SequenceCodeType }
     *     
     */
    public SequenceCodeType getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link SequenceCodeType }
     *     
     */
    public void setSequence(SequenceCodeType value) {
        this.sequence = value;
    }

}