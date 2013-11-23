/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author adrien
 */
public class Paragraphe {

    private int id;

    private final String xpath;
    private final Document documentSource;

    private int sommAppTerm;
    private Set<TermeParagraphe> termeParagraphes;

    /**
     * Créer un paragraphe est s'enregistre automatiquement dans le document
     * correspondant
     * @param xpath
     * @param source
     */
    public Paragraphe(String xpath, Document source) {
        this.xpath = xpath;
        this.documentSource = source;
        this.sommAppTerm = 0;
        this.termeParagraphes = new HashSet<TermeParagraphe>();
        this.documentSource.addParagraph(this);
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

    void addTermeParagraphes(TermeParagraphe termeParagraphe) {
        this.termeParagraphes.add(termeParagraphe);
    }

    public Document getDocumentSource() {
        return documentSource;
    }

    public Set<TermeParagraphe> getTermeParagraphes() {
        return termeParagraphes;
    }

    public void incSommeApparitionTermes(int inc) {
        this.sommAppTerm += inc;
    }
}
