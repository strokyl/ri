/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

/**
 *
 * @author adrien
 */
public class Paragraphe {

    private int id;
    private String xpath;
    private Document source;
    private int sommAppTerm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSommAppTerm() {
        return sommAppTerm;
    }

    public void setSommAppTerm(int sommAppTerm) {
        this.sommAppTerm = sommAppTerm;
    }

    public Document getSource() {
        return source;
    }

    public void setSource(Document source) {
        this.source = source;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    

}
