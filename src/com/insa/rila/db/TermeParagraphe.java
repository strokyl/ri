/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

/**
 *
 * @author strokyl
 */
public class TermeParagraphe {
    private float tf;
    private float tf_robertson;
    private Paragraphe paragraphe;
    private TermeParagraphe termeParagraphe;
    private int id;

    public TermeParagraphe() {
    }

    public Paragraphe getParagraphe() {
        return paragraphe;
    }

    public void setParagraphe(Paragraphe paragraphe) {
        this.paragraphe = paragraphe;
    }

    public TermeParagraphe getTermeParagraphe() {
        return termeParagraphe;
    }

    public void setTermeParagraphe(TermeParagraphe termeParagraphe) {
        this.termeParagraphe = termeParagraphe;
    }

    public float getTf() {
        return tf;
    }

    public void setTf(float tf) {
        this.tf = tf;
    }

    public float getTf_robertson() {
        return tf_robertson;
    }

    public void setTf_robertson(float tf_robertson) {
        this.tf_robertson = tf_robertson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
