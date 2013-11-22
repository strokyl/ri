/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

/**
 *
 * @author adrien
 */
class Position {

    private int id;
    private final int position;
    private Apparition app;

    public Position(Apparition app, int position) {
        this.position = position;
        this.app = app;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public Apparition getApp() {
        return app;
    }

    public void setApp(Apparition app) {
        this.app = app;
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }
}
