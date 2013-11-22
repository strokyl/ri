/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

import java.util.List;

/**
 *
 * @author adrien
 */
public class Terme {
    private final String racine;
    private float ipf;
    private int id;

    private List<TermeParagraphe> TermeParagraphes;

    public Terme(String racine) {
        this.racine = racine;
    }

    public List<TermeParagraphe> getTermeParagraphes() {
        return TermeParagraphes;
    }

    public void setTermeParagraphes(List<TermeParagraphe> TermeParagraphes) {
        this.TermeParagraphes = TermeParagraphes;
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
