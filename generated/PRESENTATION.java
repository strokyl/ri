//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.11.04 at 07:04:46 PM CET 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "titre",
    "auteur",
    "date",
    "description"
})
@XmlRootElement(name = "PRESENTATION")
public class PRESENTATION {

    @XmlElement(name = "TITRE", required = true)
    protected String titre;
    @XmlElement(name = "AUTEUR")
    protected String auteur;
    @XmlElement(name = "DATE")
    protected String date;
    @XmlElement(name = "DESCRIPTION")
    protected DESCRIPTION description;

    /**
     * Gets the value of the titre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTITRE() {
        return titre;
    }

    /**
     * Sets the value of the titre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTITRE(String value) {
        this.titre = value;
    }

    /**
     * Gets the value of the auteur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUTEUR() {
        return auteur;
    }

    /**
     * Sets the value of the auteur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUTEUR(String value) {
        this.auteur = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDATE() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDATE(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link DESCRIPTION }
     *     
     */
    public DESCRIPTION getDESCRIPTION() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link DESCRIPTION }
     *     
     */
    public void setDESCRIPTION(DESCRIPTION value) {
        this.description = value;
    }

}
