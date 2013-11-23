/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

/**
 *
 * @author adrien
 */
public class Position {

    private int id;
    private final int position;
    private final Apparition app;

    /**
     * Créé une position est s'enregistre automatiquement dans le paragraphe
     * corespondant
     * @param app
     * @param position
     */
    public Position(Apparition app, int position) {
        this.position = position;
        this.app = app;
        this.app.addPositions(this);
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public Apparition getApp() {
        return app;
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }
}
