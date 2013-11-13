/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.xml;

import java.io.File;
import java.util.HashMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.Map;

/**
 *
 * @author strokyl
 */
public class Parser {

    public static BALADE parseFile(File file) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance("com.insa.rila.xml");
        Unmarshaller u = jc.createUnmarshaller();
        Object o = u.unmarshal(file);

        return (BALADE) o;
    }

    public static Map<String, BALADE> parseDirectory(String urlDir) throws JAXBException {
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
}
