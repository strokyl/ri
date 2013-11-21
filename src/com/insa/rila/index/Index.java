/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.index;

import java.util.ArrayList;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilter;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author adrien
 */
public class Index {

    public static String toLower (String content)
    {
        return content.toLowerCase();
    }
    
    public static ArrayList<String> wordelimiter(String content)
    {

        ArrayList<String> words = new ArrayList<String>();
        String tab[] = content.split("[\\W]");
        for(int i =0;i<tab.length;i++)
        {
            if(!tab[i].isEmpty())
               words.add(tab[i]);
        }

       return words;

    }





}
