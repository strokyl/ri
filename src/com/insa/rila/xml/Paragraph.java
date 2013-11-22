/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.xml;

/**
 *
 * @author strokyl
 */
public class Paragraph {
    private String xmlPath;
    private String xmlUrl;

    private String textParagraph;
    private String textTitre;
    private String textSousTitre;
    private String textDescription;

    public Paragraph() {
        this.xmlPath = "";
        this.xmlUrl = "";
        this.textParagraph = "";
        this.textTitre = "";
        this.textSousTitre = "";
        this.textDescription = "";
    }
    
    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getTextParagraph() {
        return textParagraph;
    }

    public void setTextParagraph(String textParagraph) {
        this.textParagraph = textParagraph;
    }

    public String getTextSousTitre() {
        return textSousTitre;
    }

    public void setTextSousTitre(String textSousTitre) {
        this.textSousTitre = textSousTitre;
    }

    public String getTextTitre() {
        return textTitre;
    }

    public void setTextTitre(String textTitre) {
        this.textTitre = textTitre;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public String getXmlUrl() {
        return xmlUrl;
    }

    public void setXmlUrl(String xmlUrl) {
        this.xmlUrl = xmlUrl;
    }

}
