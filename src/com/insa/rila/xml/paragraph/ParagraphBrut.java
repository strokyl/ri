/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.xml.paragraph;

import com.insa.rila.index.Index;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    private List<String> tokenParagraph;
    private List<String> tokenTitre;
    private List<String> tokenSousTitre;
    private List<String> tokenDescription;

    public ParagraphBrut() {
        this.xmlPath = "";
        this.xmlUrl = "";
        this.textParagraph = "";
        this.textTitre = "";
        this.textSousTitre = "";
        this.textDescription = "";
    }

    public void computeToken() throws FileNotFoundException, IOException {
        tokenParagraph = Index.getTokenList(textParagraph);
        tokenTitre = Index.getTokenList(textTitre);
        tokenDescription = Index.getTokenList(textDescription);
        tokenSousTitre = Index.getTokenList(textSousTitre);
    }

    public String getTextDescription() {
        return textDescription;
    }

    public List<String> getTokenDescription() {
        return tokenDescription;
    }

    public List<String> getTokenTitre() {
        return tokenTitre;
    }

    public List<String> getTokenSousTitre() {
        return tokenSousTitre;
    }

    public List<String> getTokenParagraph() {
        return tokenParagraph;
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
