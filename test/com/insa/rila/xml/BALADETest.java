/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.xml;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBException;
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
public class BALADETest {

    public BALADETest() {
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
     * Test of getParagrah method, of class BALADE.
     */
    @Test
    public void testGetParagrah() throws JAXBException {
        System.out.println("getParagrah");
        String xmlUrl = "Collection/d001.xml";
        BALADE instance = Parser.parseFile(new File(xmlUrl));
        List<Paragraph> result = instance.getParagrah(xmlUrl);
        assertEquals(32, result.size());

        for (Paragraph p : result) {
            assertEquals("Voyage aux Pyrénées", p.getTextTitre());
            assertEquals(xmlUrl, p.getXmlUrl());
        }

        assertEquals("/BALADE[1]/PRESENTATION[1]/DESCRIPTION[1]/P[1]", result.get(0).getXmlPath());
        assertEquals("/BALADE[1]/PRESENTATION[1]/DESCRIPTION[1]/P[2]", result.get(1).getXmlPath());

        assertEquals("/BALADE[1]/RECIT[1]/SEC[1]/P[10]", result.get(11).getXmlPath());
        assertEquals("UNE VISITE À LOURDES", result.get(2).getTextSousTitre().trim());
        assertEquals("UNE VISITE À LOURDES", result.get(3).getTextSousTitre().trim());

        assertTrue(result.get(11).getTextDescription().contains("daté de février 1922"));
        assertTrue(result.get(11).getTextDescription().contains("un énorme scandale et des écrivains catholiques militeront pour la réhabilitation de la cité mariale"));

        assertEquals("", result.get(0).getTextDescription());
        assertEquals("", result.get(1).getTextDescription());

        assertTrue(result.get(0).getTextParagraph().contains("daté de février 1922"));
        assertTrue(result.get(1).getTextParagraph().contains("un énorme scandale et des écrivains catholiques militeront pour la réhabilitation de la cité mariale"));

        xmlUrl = "Collection/d002.xml";
        instance = Parser.parseFile(new File(xmlUrl));
        result = instance.getParagrah(xmlUrl);
        assertEquals(30, result.size());

        for (Paragraph p : result) {
            assertEquals("Voyage aux Pyrénées", p.getTextTitre());
            assertEquals(xmlUrl, p.getXmlUrl());
        }

        assertEquals("/BALADE[1]/RECIT[1]/P[1]", result.get(1).getXmlPath());
        assertEquals("", result.get(1).getTextSousTitre());
    }
}
