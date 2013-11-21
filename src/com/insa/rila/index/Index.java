/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.index;

//import org.apache.lucene.analysis.*;
//import org.apache.lucene.analysis.core.WhitespaceTokenizer;
//import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilter;
import java.text.Normalizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tartarus.snowball.FrenchSnowballStemmerFactory;
import org.tartarus.snowball.util.StemmerException;




/**
 *
 * @author adrien
 */
public class Index {

    public static final String WORD_DELIMITER_REGEXP = "(\\s|[-,.;:()_/\\\\'|\"])+";

    public static String toLower(String content) {
        return content.toLowerCase();
    }

    public static List<String> getToken(String content) {
        List<String> result = new LinkedList<String>();

        for (String s : content.split(WORD_DELIMITER_REGEXP)) {
            result.add(s);
        }

        return result;
    }

    public static List<String> removeStopwords(List<String> words) throws FileNotFoundException, IOException {
        Set<String> stopWords = new HashSet<String>();
        BufferedReader SW = new BufferedReader(new FileReader("stopliste.txt"));
        for (String line; (line = SW.readLine()) != null;) {
            stopWords.add(line.trim().toLowerCase());
        }
        SW.close();

        List<String> newList = new LinkedList<String>();
        for (int i = 0; i < words.size(); i++) {
            if (!stopWords.contains(words.get(i))) {
                newList.add(words.get(i));
            }

        }
        return newList;

    }

    /**
     * Take a word and only keep is racine
     * For example : discrimination -> discriminer
     *             : discrinaient   -> discriminaient
     * @param words
     */

    public static List stemming(List<String> words) {
        List<String> test = new LinkedList<String>();
        for(int i=0;i<words.size();i++)
        {
            try {
                test.add(FrenchSnowballStemmerFactory.getInstance().process(words.get(i)));
            } catch (StemmerException ex) {
                Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return test;
    }

    /**
     * Replace all accent into correspondig ascii caractere in a token list
     * @param words
     */
    public static List<String> asciiFolding(List<String> words) {
        List<String> result = new LinkedList<String>();

        for (String word : words) {
            result.add(asciiFoldingOnWord(word));
        }

        return result;
    }

    public static String asciiFoldingOnWord(String word) {
        String result = Normalizer.normalize(word, Normalizer.Form.NFD);
        result = result.replaceAll("[^\\p{ASCII}]", "");
        return result;
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
        for (String w : words) {
            value = 1;

            if (result.containsKey(w)) {
                value += result.get(w);
            }

            result.put(w, value);
        }

        return result;
    }
}
