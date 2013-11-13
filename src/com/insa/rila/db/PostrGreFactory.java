/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

/**
 *
 * @author adrien
 */
public class PostrGreFactory {

    public PostGreConnection getConnect ()
    {
        PostGreConnection post = new PostGreConnection("jdbc:postgresql://127.0.0.1:5432/ri", "ri", "ri");
        post.connect();

        return post;
    }

}
