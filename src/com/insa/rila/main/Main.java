/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.main;

import com.insa.rila.index.Index;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;



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
        Set<String> test = new HashSet<String>();
        Iterator it ;
        test.add("1");
        test.add("2");
        test.add("3");
        test.add("4");
        test.add("5");
        it=test.iterator();
        while(it.hasNext())
        {
            System.out.println("test : "+it.next());
        }

    }

}
