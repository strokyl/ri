/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.xml;

import java.io.File;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author strokyl
 */
public class ParserTest {

    public ParserTest() {
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
     * Test of parseFile method, of class Parser.
     */
    @Test
    public void testParseFile() throws Exception {
        System.out.println("parseFile");
        BALADE result = Parser.parseFile(new File("./Collection/d001.xml"));
        result.getRECIT();
        assertEquals("Voyage aux Pyrénées", result.getPRESENTATION().getTITRE());
    }

    @Test
    public void testParseDirectory() throws Exception {
        System.out.println("parseDirectory");
        Map<String, BALADE> result = Parser.parseDirectory("./Collection");
        assertEquals(result.size(), 100);
    }

}