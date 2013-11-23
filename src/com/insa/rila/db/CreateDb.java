/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.db;

import com.insa.rila.index.Index;
import com.insa.rila.xml.ParagraphBrut;
import com.insa.rila.xml.Parser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBException;

/**
 *
 * @author strokyl
 */
public class CreateDb {

    public static void createDb() throws JAXBException, FileNotFoundException, IOException, SQLException {
        //List<ParagraphBrut> paragraphBruts = Parser.parseDirectoryToParagraph("Collection/");

        List<ParagraphBrut> paragraphBruts = Parser.parseDirectoryToParagraph("Collection2/");
        System.out.println("____");
        DbInRam dbInRam = Index.createDbInRam(paragraphBruts);
        dbInRam.printStatistique();
        dbInRam.saveToDb();
    }

    public static void main(String[] argv) throws JAXBException, FileNotFoundException, IOException, SQLException {
        createDb();
    }
}
