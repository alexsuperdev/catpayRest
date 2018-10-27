//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.27 at 06:29:04 PM CEST 
//


package de.catpay.domiu.konfipay.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LocalInstrumentCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LocalInstrumentCodeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CORE"/&gt;
 *     &lt;enumeration value="B2B"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "LocalInstrumentCodeType")
@XmlEnum
public enum LocalInstrumentCodeType {


    /**
     * CORE
     * 
     */
    CORE("CORE"),

    /**
     * B2B (Business-To-Business)
     * 
     */
    @XmlEnumValue("B2B")
    B_2_B("B2B");
    private final String value;

    LocalInstrumentCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LocalInstrumentCodeType fromValue(String v) {
        for (LocalInstrumentCodeType c: LocalInstrumentCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
