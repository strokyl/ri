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
public enum ApparitionType
{
    TITRE("titre"),SOUS_TITRE("sous titre"),PARAGRAPHE("paragraphe"),DESCRIPTION("description");

    private String name;
    
    private ApparitionType(String name)
    {
        this.name=name;

    }

}


