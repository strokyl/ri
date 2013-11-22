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
class ApparitionType {

    private int id;
    private String type;
    private int ponderation;
    private List<Apparition> apparitions;

    public List<Apparition> getApparitions() {
        return apparitions;
    }

    public void setApparitions(List<Apparition> apparitions) {
        this.apparitions = apparitions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPonderation() {
        return ponderation;
    }

    public void setPonderation(int ponderation) {
        this.ponderation = ponderation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    


}
