/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.db;

import java.sql.Connection;

/**
 *
 * @author adrien
 */
public class PostGreFactory {

    private static Connection post;

    public static Connection getConnect() {
        if (post == null) {
            post = new PostGreConnection("jdbc:postgresql://127.0.0.1:5432/ri", "ri", "ri").connect();
        }

        return post;
    }
}
