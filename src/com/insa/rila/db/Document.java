/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author adrien
 */
public class Document {

    private int id;
    private String urlXml;
    private Date date;
    private List<Paragraphe> paragraphes;

    public Document(String urlXml) {

       this.urlXml = urlXml;
       this.paragraphes =new LinkedList<Paragraphe>();

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

    public List<Paragraphe> getParagraphes() {
        return paragraphes;
    }



    public void addParagraph(Paragraphe para)
    {
      this.paragraphes.add(para);

    }


    public String getUrlXml() {
        return urlXml;
    }

    public void setUrlXml(String urlXml) {
        this.urlXml = urlXml;
    }

    

}
