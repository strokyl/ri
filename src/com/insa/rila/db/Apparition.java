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
public class Apparition {

    private int id;
    private Set<Position> positions;
    private final ApparitionType apparitionType;
    private final TermeParagraphe termeParagraphe;

    public Apparition(TermeParagraphe termeParagraphe, ApparitionType apparitionType) {
        this.termeParagraphe = termeParagraphe;
        this.positions = new HashSet<Position>();
        this. apparitionType = apparitionType;
        termeParagraphe.addApparition(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombreApp() {
        return this.positions.size();
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void addPositions(Position position) {
        this.positions.add(position);
    }

    public TermeParagraphe getTermeParagraphe() {
        return termeParagraphe;
    }

    public ApparitionType getApparitionType() {
        return  apparitionType;
    }
}
