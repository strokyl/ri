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
public class Apparition {

    private int id;
    private int nombreApp;
    private List<Position> positions;
    private ApparitionType typeApp;
    private TermeParagraphe termeParagraphe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombreApp() {
        return nombreApp;
    }

    public void setNombreApp(int nombreApp) {
        this.nombreApp = nombreApp;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public TermeParagraphe getTermeParagraphe() {
        return termeParagraphe;
    }

    public void setTermeParagraphe(TermeParagraphe termeParagraphe) {
        this.termeParagraphe = termeParagraphe;
    }

    public ApparitionType getTypeApp() {
        return typeApp;
    }

    public void setTypeApp(ApparitionType typeApp) {
        this.typeApp = typeApp;
    }
    
    

}
