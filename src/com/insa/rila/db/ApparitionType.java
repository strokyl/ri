/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

/**
 *
 * @author adrien
 */
public enum ApparitionType
{
     TITRE("titre", 2.5f),SOUS_TITRE("sous titre",1.5f),PARAGRAPHE("paragraphe", 4.0f),DESCRIPTION("description", 1.25f);

    private final String name;
    private float ponderation;
    private int id;

    private ApparitionType(String name, float ponderation) {
        this.name = name;
        this.ponderation = ponderation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPonderation() {
        return ponderation;
    }

    public void setPonderation(float ponderation) {
        this.ponderation = ponderation;
    }

    public String getName() {
        return name;
    }

}


