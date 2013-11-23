/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author adrien
 */
public class Document {

    private int id;
    private final String urlXml;
    private Date date;
    private Set<Paragraphe> paragraphes;

    public Document(String urlXml) {

       this.urlXml = urlXml;
       this.paragraphes =new HashSet<Paragraphe>();

    }



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

    public Set<Paragraphe> getParagraphes() {
        return paragraphes;
    }



    public void addParagraph(Paragraphe para)
    {
      this.paragraphes.add(para);

    }


    public String getUrlXml() {
        return urlXml;
    }
}
