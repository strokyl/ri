/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.index;

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
        String content = "Test De lOwerCase";
        String expResult = "test de lowercase";
        String result = Index.toLower(content);
        assertEquals(expResult, result);
        System.out.println(result);
    }

}