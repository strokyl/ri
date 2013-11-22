/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.xml;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.Map;

/**
 *
 * @author strokyl
 */
public class Parser {

    /**
     * Parse un fichier xml d'une balade
     * @param file
     * @return
     * @throws JAXBException
     */
    public static BALADE parseFile(File file) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance("com.insa.rila.xml");
        Unmarshaller u = jc.createUnmarshaller();
        Object o = u.unmarshal(file);

        return (BALADE) o;
    }

    /**
     * Parse tout les fichiers xml d'un repertoire
     * @param urlDir
     * @return un map qui associe relatif par rapport au current dir d'un fichier
     * xml à son parsaage
     * @throws JAXBException
     */
    public static Map<String, BALADE> parseDirectoryToBalades(String urlDir) throws JAXBException {
        File dir = new File(urlDir);
        String xmlPatternName = ".*\\.xml$";
        Map<String, BALADE> result = new HashMap<String, BALADE>();

        for (File f : dir.listFiles()) {
            if (f.getName().matches(xmlPatternName)) {
                result.put(new File(dir.getPath(), f.getName()).toString(), parseFile(f));
            }
        }

        return result;
    }

    /**
     * Parse tout les fichiers xml d'un repertoire et en extirpe tout les paragraphs
     * @param urlDir
     * @return
     * @throws JAXBException
     */
    public static List<ParagraphBrut> parseDirectoryToParagraph(String urlDir) throws JAXBException {
        List<ParagraphBrut> result = new LinkedList<ParagraphBrut>();
        List<ParagraphBrut> current;
        Map<String, BALADE> map = parseDirectoryToBalades(urlDir);

        for(String urlPath : map.keySet()) {
            current = map.get(urlPath).getParagrah(urlPath);
            result.addAll(current);
        }

        return result;
    }
}
