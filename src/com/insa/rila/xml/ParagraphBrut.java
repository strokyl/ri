/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.xml;

import java.util.List;

/**
 *
 * @author strokyl
 */
public class ParagraphBrut {

    private String xmlPath;
    private String xmlUrl;
    private String textParagraph;
    private String textTitre;
    private String textSousTitre;
    private String textDescription;

    public ParagraphBrut() {
        this.xmlPath = "";
        this.xmlUrl = "";
        this.textParagraph = "";
        this.textTitre = "";
        this.textSousTitre = "";
        this.textDescription = "";
    }

    public String getTextDescription() {
        return null;
    }

    public List<String> getTokenDescription() {
        return null;
    }

    public List<String> getTokenTitre() {
        return null;
    }

    public List<String> getTokenSousTitre() {
        return null;
    }

    public List<String> getTokenParagraph() {
        return null;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getTextParagraph() {
        return null;
    }

    public void setTextParagraph(String textParagraph) {
        this.textParagraph = textParagraph;
    }

    public String getTextSousTitre() {
        return null;
    }

    public void setTextSousTitre(String textSousTitre) {
        this.textSousTitre = textSousTitre;
    }

    public String getTextTitre() {
        return null;
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
