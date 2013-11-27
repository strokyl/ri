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
public class Terme {
    private final String racine;
    private float ipf;
    private int id;

    public String getRacine() {
        return racine;
    }

    private Set<TermeParagraphe> termeParagraphes;

    public Terme(String racine) {
        this.racine = racine;
        this.termeParagraphes = new HashSet<TermeParagraphe>();
    }

    public Set<TermeParagraphe> getTermeParagraphes() {
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

    public boolean equals(Terme terme)
    {
        return this.racine.equalsIgnoreCase(terme.racine);
    }

    
}
