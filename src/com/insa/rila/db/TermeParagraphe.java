/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author strokyl
 */
public class TermeParagraphe {
    private float tf;
    private float tf_robertson;
    private final Paragraphe paragraphe;
    private final Terme terme;
    private Set<Apparition> apparitions;
    private int id;

    /**
     * Créer un terme_paragraphe qui s'ajoute automatiquement
     * dans le terme et le paragraphe correspondant
     * @param paragraphe
     * @param terme
     */
    public TermeParagraphe(Paragraphe paragraphe, Terme terme) {
        this.paragraphe = paragraphe;
        this.terme = terme;
        this.apparitions = new HashSet<Apparition>();
        terme.addTermeParagraphes(this);
        paragraphe.addTermeParagraphes(this);
    }

    public Paragraphe getParagraphe() {
        return paragraphe;
    }

    public void addApparition(Apparition apparition) {
        this.apparitions.add(apparition);
    }

    public Terme getTermeParagraphe() {
        return terme;
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
