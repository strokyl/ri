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
    private int position;
    private Apparition app;

    public Apparition getApp() {
        return app;
    }

    public void setApp(Apparition app) {
        this.app = app;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    

}
