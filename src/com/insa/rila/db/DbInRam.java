/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.db;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author strokyl
 */
public class DbInRam {

    private Set<Terme> termes;
    private Set<Document> documents;
    private Set<Paragraphe> paragraphes;
    private Set<TermeParagraphe> termeParagraphes;
    private Set<Position> positions;
    private Set<Apparition> apparitions;

    public DbInRam() {
        termes = new HashSet<Terme>();
        documents = new HashSet<Document>();
        paragraphes = new HashSet<Paragraphe>();
        termeParagraphes = new HashSet<TermeParagraphe>();
        positions = new HashSet<Position>();
        apparitions = new HashSet<Apparition>();
    }

    public void addTermes(Terme terme) {
        termes.add(terme);
    }

    public void addDocuments(Document doc) {
        documents.add(doc);
    }

    public void addParagraphe(Paragraphe par) {
        paragraphes.add(par);
    }

    public void addTermeParagraphes(TermeParagraphe termePar) {
        termeParagraphes.add(termePar);
    }

    public void addPosition(Position position) {
        positions.add(position);
    }

    public void addApparition(Apparition apparition) {
        apparitions.add(apparition);
    }

    public void printStatistique() {
        System.out.println("nombre de terme : " + termes.size());
        System.out.println("nombre de document : " + documents.size());
        System.out.println("nombre de paragraphe : " + paragraphes.size());
        System.out.println("nombre de termeParagraphe : " + termeParagraphes.size());
        System.out.println("nombre de position : " + positions.size());
        System.out.println("nombre d'apparition : " + apparitions.size());
    }

    public void saveToDb() throws SQLException {
        Connection c = PostGreFactory.getConnect();
        saveApparitionType(c);
        saveDocument(c);
        saveTerme(c);
        saveParagraphe(c);
        saveTermeParagraphe(c);
        saveApparition(c);
        savePosition(c);
    }

    private void saveDocument(Connection c) throws SQLException {
        int id = 0;

        //TODO: ajaouter date
        PreparedStatement ps = c.prepareStatement("INSERT INTO ri.document VALUES (?, ?, ?, NULL)");
        for (Document doc : documents) {
            ps.setInt(1, id);
            doc.setId(id);
            ps.setString(2, doc.getUrlXml());
            ps.setInt(3, doc.getSommeApparitionTermes());
            ps.addBatch();
            id++;
        }

        ps.clearParameters();
        int[] results = ps.executeBatch();
        System.out.println(java.util.Arrays.asList(results));

    }

    private void saveTerme(Connection c) throws SQLException {
        int id = 0;
        PreparedStatement ps = c.prepareStatement("INSERT INTO ri.terme VALUES (?, ?, ?)");
        for (Terme terme : termes) {
            CalcIdf(terme);
            ps.setInt(1, id);
            terme.setId(id);
            ps.setString(2, terme.getRacine());
            ps.setFloat(3, terme.getIpf());
            ps.addBatch();
            id++;
        }

        ps.clearParameters();
        int[] results = ps.executeBatch();
        System.out.println(java.util.Arrays.asList(results));
    }

    private void saveParagraphe(Connection c) throws SQLException {
        int id = 0;

        PreparedStatement ps = c.prepareStatement("INSERT INTO ri.paragraphe VALUES (?, ?, ?, ?)");
        for (Paragraphe par : paragraphes) {
            ps.setInt(1, id);
            par.setId(id);
            ps.setString(2, par.getXpath());
            ps.setInt(3, par.getSommAppTerm());
            ps.setInt(4, par.getDocumentSource().getId());
            ps.addBatch();
            id++;
        }

        ps.clearParameters();
        int[] results = ps.executeBatch();
        System.out.println(java.util.Arrays.asList(results));
    }

    private void saveTermeParagraphe(Connection c) throws SQLException {
        int id = 0;
        PreparedStatement ps = c.prepareStatement("INSERT INTO ri.terme_paragraphe VALUES (?, ?, ?, ?, ?)");
        for (TermeParagraphe termePara : termeParagraphes) {
            calcTfIdf(termePara);
            ps.setInt(1, id);
            termePara.setId(id);
            ps.setFloat(2, termePara.getTf());
            ps.setFloat(3, termePara.getTf_robertson());
            ps.setInt(4, termePara.getTerme().getId());
            ps.setInt(5, termePara.getParagraphe().getId());
            ps.addBatch();
            id++;
        }

        ps.clearParameters();
        int[] results = ps.executeBatch();
        System.out.println(java.util.Arrays.asList(results));
    }

    private void saveApparition(Connection c) throws SQLException {
        int id = 0;
        PreparedStatement ps = c.prepareStatement("INSERT INTO ri.apparition VALUES (?, ?, ?, ?)");
        for (Apparition apparition : apparitions) {
            ps.setInt(1, id);
            apparition.setId(id);
            ps.setInt(2, apparition.getNombreApp());
            ps.setInt(3, apparition.getTermeParagraphe().getId());
            ps.setInt(4, apparition.getApparitionType().getId());
            ps.addBatch();
            id++;
        }

        ps.clearParameters();
        int[] results = ps.executeBatch();
        System.out.println(java.util.Arrays.asList(results));
    }

    private void savePosition(Connection c) throws SQLException {
        int id = 0;
        PreparedStatement ps = c.prepareStatement("INSERT INTO ri.position VALUES (?, ?, ?)");
        for (Position position : positions) {
            ps.setInt(1, id);
            position.setId(id);
            ps.setInt(2, position.getPosition());
            ps.setInt(3, position.getApp().getId());
            ps.addBatch();
            id++;
        }

        ps.clearParameters();
        int[] results = ps.executeBatch();
        System.out.println(java.util.Arrays.asList(results));
    }

    private void saveApparitionType(Connection c) throws SQLException {
        int id = 0;
        PreparedStatement ps = c.prepareStatement("INSERT INTO ri.apparition_type VALUES (?, ?, ?)");
        for (ApparitionType at : ApparitionType.values()) {
            ps.setInt(1, id);
            at.setId(id);
            ps.setString(2, at.getName());
            ps.setFloat(3, at.getPonderation());

            ps.addBatch();
            id++;

        }



        ps.clearParameters();
        int[] results = ps.executeBatch();
        System.out.println(java.util.Arrays.asList(results));
    }

    private void CalcIdf(Terme ter)
    {
        int nbTotalDoc = documents.size();
        int nbDocApp =0;
        Terme terme;
        int i=0;
        boolean apparait =false;
        Iterator iteTermePara;
        Iterator itParaDoc;
        Paragraphe paraDoc;

        for(Document doc :documents)
        {
            itParaDoc = doc.getParagraphes().iterator();
            while(itParaDoc.hasNext() && apparait == false)
            {
                Paragraphe para = (Paragraphe) itParaDoc.next();
                iteTermePara = para.getTermeParagraphes().iterator();
                while(apparait ==false && iteTermePara.hasNext() )
                {
                    TermeParagraphe tempo = (TermeParagraphe) iteTermePara.next();
                    
                    if(tempo.getTerme().equals(ter)) // verif equals si mieux sur la racine ou sur l'ID
                    {
                        apparait=true;
                        nbDocApp++;
                    }

                }

            }

        }

        ter.setIpf((float) Math.log(nbTotalDoc/nbDocApp));

    }

    private void calcTfIdf(TermeParagraphe termePara) {
        termePara.setTf_robertson(termePara.getTerme().getIpf()*termePara.getTf());
    }



}
