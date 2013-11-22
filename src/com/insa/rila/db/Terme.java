/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author adrien
 */
public class Terme {
    private final String racine;
    private float ipf;
    private int id;

    private List<TermeParagraphe> termeParagraphes;

    public Terme(String racine) {
        this.racine = racine;
        this.termeParagraphes = new LinkedList<TermeParagraphe>();
    }

    public List<TermeParagraphe> getTermeParagraphes() {
        return termeParagraphes;
    }

    public void addTermeParagraphes(TermeParagraphe termeParagraphe) {
        this.termeParagraphes.add(termeParagraphe);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getIpf() {
        return ipf;
    }

    public void setIpf(float ipf) {
        this.ipf = ipf;
    }

    
}
