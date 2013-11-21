/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.index;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author adrien
 */
public class Index {

    public static final String WORD_DELIMITER_REGEXP = "(\\s|[-,.;:()_/\\\\'#@|\"])+";

    public static String toLower (String content)
    {
        return content.toLowerCase();
    }

    public static List<String> getToken (String content) {
        List<String> result = new LinkedList<String>();

        for(String s : content.split(WORD_DELIMITER_REGEXP) ) {
            result.add(s);
        }

        return result;
    }

    public static void removeStopwords(List<String> words) {
        
    }

    /**
     * Take a word and only keep is racine
     * For example : discrimination -> discriminer
     *             : discrinaient   -> discriminaient
     * @param words
     */
    public static void stemming(List<String> words) {

    }

    /**
     * Replace all accent into correspondig ascii caractere in a token list
     * @param words
     */
    public static void asciiFolding(List<String> words) {

    }

    /**
     * Aggregate duplicate token from a list
     * @param words list of token
     * @return a map that associate a token to the number of time it waw appearing
     * in the input list
     */
    public static Map<String, Integer> aggregate(List<String> words) {
        Map<String, Integer> result = new HashMap<String, Integer>();

        int value;
        for(String w : words) {
            value = 1;

            if(result.containsKey(w)) {
                value += result.get(w);
            }

            result.put(w, value);
        }

        return result;
    }

}
