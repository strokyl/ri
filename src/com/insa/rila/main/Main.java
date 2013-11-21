/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.main;

import com.insa.rila.index.Index;
import java.util.ArrayList;



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
        ArrayList<String> words = new ArrayList<String>();
        words = Index.wordelimiter(content);
        for(int i=0;i<words.size();i++)
        {
            System.out.println(words.get(i));
        }
        //System.out.println(words.get(0).isEmpty());
    }

}
