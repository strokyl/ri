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
public class DbInRam {
    private Set<Terme> termes;
    private Set<Document> documents;
    private Set<Paragraphe> paragraphes;
    private Set<TermeParagraphe> termeParagraphes;
    private Set<Position> positions;
    private Set<Apparition> apparitions;
    private Set<ApparitionType> apparitionTypes;

    public DbInRam() {
        termes = new HashSet<Terme>();
        documents = new HashSet<Document>();
        paragraphes = new HashSet<Paragraphe>();
        termeParagraphes = new HashSet<TermeParagraphe>();
        positions = new HashSet<Position>();
        apparitions = new HashSet<Apparition>();
    }

    public void addTermes(Terme terme) {
        termes.add(terme);
    }

    public void addDocuments(Document doc) {
        documents.add(doc);
    }

    public void addParagraphe(Paragraphe par) {
        paragraphes.add(par);
    }

    public void addTermeParagraphes(TermeParagraphe termePar) {
        termeParagraphes.add(termePar);
    }

    public void addPosition(Position position) {
        positions.add(position);
    }

    public void addApparition(Apparition apparition) {
        apparitions.add(apparition);
    }

    public void printStatistique() {
        System.out.println("nombre de terme : " + termes.size());
        System.out.println("nombre de document : " + documents.size());
        System.out.println("nombre de paragraphe : " + paragraphes.size());
        System.out.println("nombre de termeParagraphe : " + termeParagraphes.size());
        System.out.println("nombre de position : " + positions.size());
        System.out.println("nombre d'apparition : " + apparitions.size());
    }
}
