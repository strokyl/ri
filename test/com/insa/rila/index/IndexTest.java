/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.index;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adrien
 */
public class IndexTest {

    public IndexTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of toLower method, of class Index.
     */
    @Test
    public void testToLower() {
        System.out.println("toLower");
        String content = "Test De lOwerCase, je ne Suis PAs un HÉros";
        String expResult = "test de lowercase, je ne suis pas un héros";
        String result = Index.toLower(content);
        assertEquals(expResult, result);
        System.out.println(result);
    }

    /**
     * Test of getToken method, of class Index.
     */
    @Test
    public void testGetToken() {
        System.out.println("getToken");
        String content = "Je ne, suis, ; pas_un-héros. Faut|pas/croire ,,: (salut) \\ dieu l'ennuie";
        List<String> expResult = java.util.Arrays.asList(new String[]{"Je", "ne", "suis", "pas", "un", "héros", "Faut", "pas", "croire", "salut", "dieu", "l", "ennuie"});
        List<String> result = Index.getToken(content);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeStopwords method, of class Index.
     */
    @Test
    public void testRemoveStopwords() throws FileNotFoundException, IOException {
        System.out.println("removeStopwords");
        List<String> words = java.util.Arrays.asList(new String[]{"la", "mer", "est", "bleue","à"});
        List<String> expected = java.util.Arrays.asList(new String[]{"mer", "bleue"});
        List<String> real = Index.removeStopwords(words);
        assertEquals(expected, real);
        System.out.println(real);
    }

    /**
     * Test of stemming method, of class Index.
     */
    @Test
    public void testStemming() {
        System.out.println("stemming");
        List<String> words = java.util.Arrays.asList(new String[]{"balader", "baladaient", "balades"});
        List<String> expected = java.util.Arrays.asList(new String[]{"balad", "balad", "balad"});
        List<String> result = Index.stemming(words);
        assertEquals(expected, result);
    }

    /**
     * Test of asciiFolding method, of class Index.
     */
    @Test
    public void testAsciiFolding() {
        System.out.println("asciiFolding");
        List<String> words = java.util.Arrays.asList(new String[]{"là", "mère", "fût", "témeraire"});
        List<String> expected = java.util.Arrays.asList(new String[]{"la", "mere", "fut", "temeraire"});
        List<String> result = Index.asciiFolding(words);
        assertEquals(expected, result);
    }

    /**
     * Test of asciiFolding method, of class Index.
     */
    @Test
    public void testAsciiFoldingOnWord() {
        System.out.println("asciiFoldingOnWord");
        assertEquals("Le poetes e a u e", Index.asciiFoldingOnWord("Le pôëtes é à ù è"));
    }

    /**
     * Test of aggregate method, of class Index.
     */
    @Test
    public void testAggregate() {
        System.out.println("aggregate");
        List<String> words = new LinkedList<String>();
        words.add("salut");
        words.add("salu");
        words.add("salut");
        words.add("Pierre");
        words.add("salut");
        words.add("Alice");
        words.add("salut");
        words.add("Pierre");

        Map<String, Integer> expResult = new HashMap<String, Integer>();
        expResult.put("salut", 4);
        expResult.put("salu", 1);
        expResult.put("Pierre", 2);
        expResult.put("Alice", 1);
        Map<String, Integer> result = Index.aggregate(words);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTokenOccurenceOfString() throws FileNotFoundException, IOException {
        System.out.println("getTokenOccurenceOfString");
        String text = "Shakespeare devine qu'il y a plus de choses sur la terre et dans le ciel qu'il n'en est rêvé dans notre philosophie; Claude Bernard constate que l'homme peut plus de choses qu'il ne croit en pouvoir; et c'est aussi ce qu'enseignent les prêtres. Poésie, science, religion, ces hautes révélations, inspirées et savantes, s'accordent à nous affirmer que nous sommes capables de prendre contact avec l'invisible, avec l'intangible, avec l'insaisissable, avec ce qui dépasse nos sens.";
        System.out.println(Index.getTokenOccurenceOfString(text));
    }
}
