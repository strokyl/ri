/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.main;

import com.insa.rila.index.Index;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



/**
 *
 * @author strokyl
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        String content = "bonjour! tu vas bien? oui et toi! ca va, mais je verrais bien. Ok";
        
        List<String> list = new LinkedList<String>();
        List<String> stemm = new LinkedList<String>();
        list = Index.getToken(content);
        stemm=Index.stemming(list);
        for(int i=0;i<stemm.size();i++)
        {
            System.out.println(stemm.get(i));
        }

    }

}
