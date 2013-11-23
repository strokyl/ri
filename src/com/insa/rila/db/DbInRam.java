/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.db;

import com.sun.tools.javadoc.resources.javadoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
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
        //saveDocument(c);
        //saveTerme(c);
        //saveParagraphe(c);
        //saveTermeParagraphe(c);
        //saveApparition(c);
        //savePosition(c);
    }

    private void saveDocument(Connection c) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void saveTerme(Connection c) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void saveParagraphe(Connection c) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void saveTermeParagraphe(Connection c) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void saveApparition(Connection c) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void savePosition(Connection c) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void saveApparitionType(Connection c) throws SQLException {
        int id = 0;
        PreparedStatement ps = c.prepareStatement("INSERT INTO ri.apparition_type VALUES (?, ?, ?)");
        for(ApparitionType at : ApparitionType.values()) {
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
}
