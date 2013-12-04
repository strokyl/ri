/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.xml.paragraph;

import com.insa.rila.xml.paragraph.Parser;
import com.insa.rila.xml.paragraph.BALADE;
import com.insa.rila.xml.paragraph.ParagraphBrut;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author strokyl
 */
public class ParserTest {

    private class ParagraphIdentifier {

        private String xmlUrl;
        private String xmlPath;

        public ParagraphIdentifier(ParagraphBrut p) {
            this.xmlPath = p.getXmlPath();
            this.xmlUrl = p.getXmlUrl();
        }

        public ParagraphIdentifier(String line) {
            String field[] = line.split("\\s+");
            //System.out.println(field[0]);
            this.xmlPath = field[1];
            this.xmlUrl = "./" + field[0];
            //System.out.println(xmlPath);
            //System.out.println(xmlUrl);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o instanceof ParagraphIdentifier) {
                ParagraphIdentifier other = (ParagraphIdentifier) o;
                return other.xmlUrl.equals(this.xmlUrl) && other.xmlPath.equals(this.xmlPath);
            } else {
                return false;
            }

        }

        @Override
        public int hashCode() {
            return this.xmlPath.hashCode() + this.xmlUrl.hashCode();
        }

        @Override
        public String toString() {
            return this.xmlUrl + " " + this.xmlPath;
        }
    }

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
    public void testParseDirectoryToBalades() throws Exception {
        System.out.println("parseDirectoryToBalades");
        Map<String, BALADE> result = Parser.parseDirectoryToBalades("./Collection");
        assertEquals(result.size(), 100);
    }

    @Test
    public void testParseDirectoryToParagraph() throws Exception {
        System.out.println("parseDirectoryToParagraphs");
        Set<ParagraphIdentifier> result = new HashSet<ParagraphIdentifier>();
        Set<ParagraphIdentifier> expected = new HashSet<ParagraphIdentifier>();

        ParagraphIdentifier pi;
        for (ParagraphBrut p : Parser.parseDirectoryToParagraph("./Collection")) {
            pi = new ParagraphIdentifier(p);
            result.add(pi);
        }

        Scanner sc = new Scanner(new File("qrels/qrel01.txt"));

        while (sc.hasNextLine()) {
            pi = new ParagraphIdentifier(sc.nextLine());
            expected.add(pi);
        }
        
        System.out.println("result : " + result.size());
        System.out.println("expected : " + expected.size());

        expected.removeAll(result);

        for (ParagraphIdentifier pi_ : expected) {
            System.out.println(pi_);
        }

        System.out.println("num of difference : " + expected.size());

        //assertEquals(expected, result);
    }
}
