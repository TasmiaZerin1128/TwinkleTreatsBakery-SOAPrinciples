//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.07.02 at 09:11:15 PM BDT 
//


package com.wstutorial.ws.commonservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AcknowledgementCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AcknowledgementCode"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OK"/&gt;
 *     &lt;enumeration value="CREATED"/&gt;
 *     &lt;enumeration value="UPDATED"/&gt;
 *     &lt;enumeration value="DELETED"/&gt;
 *     &lt;enumeration value="FAILED"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AcknowledgementCode")
@XmlEnum
public enum AcknowledgementCode {

    OK,
    CREATED,
    UPDATED,
    DELETED,
    FAILED;

    public String value() {
        return name();
    }

    public static AcknowledgementCode fromValue(String v) {
        return valueOf(v);
    }

}
