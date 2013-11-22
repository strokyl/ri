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

    private final String xpath;
    private Document documentSource;

    private int sommAppTerm;

    public Paragraphe(String xpath, Document source) {
        this.xpath = xpath;
        this.documentSource = source;
        this.sommAppTerm = 0;
    }



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
        return documentSource;
    }


    public String getXpath() {
        return xpath;
    }
}
