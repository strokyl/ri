/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.index;

//import org.apache.lucene.analysis.*;
//import org.apache.lucene.analysis.core.WhitespaceTokenizer;
//import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilter;
import com.insa.rila.db.Apparition;
import com.insa.rila.db.ApparitionType;
import com.insa.rila.db.DbInRam;
import com.insa.rila.db.Document;
import com.insa.rila.db.Paragraphe;
import com.insa.rila.db.Position;
import com.insa.rila.db.Terme;
import com.insa.rila.db.TermeParagraphe;
import com.insa.rila.xml.paragraph.ParagraphBrut;
import java.text.Normalizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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

    public static final String WORD_DELIMITER_REGEXP = "(\\s|[-!?,.;:()_/\\\\'|\"])+";
    private static Set<String> stopWords;

    public static Set<String> getStopWords() throws FileNotFoundException, IOException {
        if (stopWords == null) {
            stopWords = new HashSet<String>();
            String word;
            Scanner sc = new Scanner(new File("stopliste.txt"), "UTF-8");
            while (sc.hasNextLine()) {
                word = sc.nextLine().trim().toLowerCase();
                stopWords.add(word);
            }

            sc.close();
        }

        return stopWords;
    }

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

        Set<String> stopWords = getStopWords();
        List<String> newList = new LinkedList<String>();
        for (int i = 0; i < words.size(); i++) {
            if (!stopWords.contains(words.get(i))) {
                newList.add(words.get(i));
            }

        }
        return newList;

        // A faire sauver la position du mot 

    }

    /**
     * Take a word and only keep is racine
     * For example : discrimination -> discriminer
     *             : discrinaient   -> discriminaient
     * @param words
     */
    public static List stemming(List<String> words) {
        List<String> test = new LinkedList<String>();
        for (int i = 0; i < words.size(); i++) {
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
    public static DbInRam createDbInRam(List<ParagraphBrut> words) throws FileNotFoundException, IOException {
        DbInRam result = new DbInRam();
        Map<String, Terme> mapTermes = new HashMap<String, Terme>();
        Set<Paragraphe> setParagraphes = new HashSet<Paragraphe>();
        Map<String, Document> mapDocuments = new HashMap<String, Document>();
        Document doc;
        Paragraphe paraDb;
        Map<Terme, TermeParagraphe> mapTermPara;

        for (ParagraphBrut paraBrut : words) {
            paraBrut.computeToken();

            // Create or Update Document
            doc = mapDocuments.get(paraBrut.getXmlUrl());
            if (doc == null) {
                doc = new Document(paraBrut.getXmlUrl());
                result.addDocuments(doc);
                mapDocuments.put(paraBrut.getXmlUrl(), doc);
            }

            //Create Doc
            mapTermPara = new HashMap<Terme, TermeParagraphe>();
            paraDb = new Paragraphe(paraBrut.getXmlPath(), doc);
            result.addParagraphe(paraDb);
            updateSets(result, mapTermes, paraDb, mapTermPara, paraBrut.getTokenTitre(), ApparitionType.TITRE);
            updateSets(result, mapTermes, paraDb, mapTermPara, paraBrut.getTokenParagraph(), ApparitionType.PARAGRAPHE);
            updateSets(result, mapTermes, paraDb, mapTermPara, paraBrut.getTokenSousTitre(), ApparitionType.SOUS_TITRE);
            updateSets(result, mapTermes, paraDb, mapTermPara, paraBrut.getTokenDescription(), ApparitionType.DESCRIPTION);
        }





        return result;
    }

    private static void updateSets(DbInRam result, Map<String, Terme> mapTermes,
            Paragraphe paraDB,Map<Terme, TermeParagraphe> mapTermPara ,List<String> tokens,
            ApparitionType apparitionType) {

        Map<String, Apparition> mapApparition = new HashMap<String, Apparition>();
        Terme terme;
        TermeParagraphe termeParagraphe;
        Apparition apparition;
        Position position;
        int index = 0;
        paraDB.incSommeApparitionTermes(tokens.size());
        for (String token : tokens) {
            terme = mapTermes.get(token);
            if (terme == null) {
                terme = new Terme(token);
                result.addTermes(terme);
                mapTermes.put(token, terme);
            }

            termeParagraphe = mapTermPara.get(terme);
            if (termeParagraphe == null) {
                termeParagraphe = new TermeParagraphe(paraDB, terme);
                result.addTermeParagraphes(termeParagraphe);
                mapTermPara.put(terme, termeParagraphe);
            }

            apparition = mapApparition.get(token);
            if (apparition == null) {
                apparition = new Apparition(termeParagraphe, apparitionType);
                result.addApparition(apparition);
                mapApparition.put(token, apparition);
            }

            position = new Position(apparition, index);
            termeParagraphe.incTf(apparitionType.getPonderation());
            result.addPosition(position);

            index++;
        }

        //result.calculTfMax();

        
    }

    /**
     *
     * @param text
     * @return return a map with all token found in the text and there occurence, token only contain ascii password
     * and are stemmed, stopword are removed
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static List<String> getTokenList(String text) throws FileNotFoundException, IOException {
        String lower = toLower(text);
        List<String> words = removeStopwords(getToken(lower));
        List<String> tokens = asciiFolding(stemming(words));
        return tokens;
    }

    /**
     * Prends un ensemble de map token occurence et le merge avec des coeffs spécifique
     * @param coeffs
     * @param maps
     * @return
     */
    public static Map<String, Float> mergeMapOfToken(float[] coeffs, Map<String, Integer>... maps) {
        if (coeffs.length != maps.length) {
            throw new RuntimeException("coeffs size should be equals to maps size");
        }

        Map<String, Float> result = new HashMap<String, Float>();

        float currentCoeff;
        float value;
        Map<String, Integer> currentMap;
        for (int i = 0; i < coeffs.length; i++) {
            currentCoeff = coeffs[i];
            currentMap = maps[i];

            for (String token : currentMap.keySet()) {
                value = currentMap.get(token).floatValue() * currentCoeff;
                if (result.containsKey(token)) {
                    value += result.get(token);
                }

                result.put(token, value);
            }

        }

        return result;
    }
}
