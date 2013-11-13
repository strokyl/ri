/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adrien
 */
public class PostGreConnection {

   private String url,user,passwd;

    public PostGreConnection(String url, String username, String password) {
        this.url=url;
        this.user=username;
        this.passwd=password;
    }

    public void connect()
    {
        Connection connection =null;
        try {

                Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

                System.out.println("Where is your PostgreSQL JDBC Driver? "
                                + "Include in your library path!");
                e.printStackTrace();
                return;

        }
        try {
            connection = DriverManager.getConnection(this.url, this.user, this.passwd);
        } catch (SQLException ex) {
            Logger.getLogger(PostGreConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(connection != null)
        {
            System.out.println("Conenction granted !!");
        }
                
    }



}
