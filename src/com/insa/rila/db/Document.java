/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

import java.util.Date;
import java.util.List;

/**
 *
 * @author adrien
 */
public class Document {

    private int id;
    private String urlXml;
    private int sommeAppTerme;
    private Date date;
    private List<Paragraphe> paragraphes;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Paragraphe> getParagraphes() {
        return paragraphes;
    }

    public void setParagraphes(List<Paragraphe> paragraphes) {
        this.paragraphes = paragraphes;
    }

    public int getSommeAppTerme() {
        return sommeAppTerme;
    }

    public void setSommeAppTerme(int sommeAppTerme) {
        this.sommeAppTerme = sommeAppTerme;
    }

    public String getUrlXml() {
        return urlXml;
    }

    public void setUrlXml(String urlXml) {
        this.urlXml = urlXml;
    }

    

}
