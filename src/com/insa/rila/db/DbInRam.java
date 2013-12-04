/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.db;

import java.sql.Connection;
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

	private final Set<Terme> termes;
	private final Set<Document> documents;
	private final Set<Paragraphe> paragraphes;
	private final Set<TermeParagraphe> termeParagraphes;
	private final Set<Position> positions;
	private final Set<Apparition> apparitions;
	public static final int NUM_INSERT_IN_ROW = 10000;
        private float MaxTf = 0;
        private float nbMoyDoc =0;
        private float nbMaxDoc =0;

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
			if (id % NUM_INSERT_IN_ROW == 0) {
				ps.clearParameters();
				ps.executeBatch();
				ps.clearBatch();
			}
		}

		ps.clearParameters();
		ps.executeBatch();
		System.out.println("documents mis en table");

	}

	private void saveTerme(Connection c) throws SQLException {
		int id = 0;
                CalcIdf();
		PreparedStatement ps = c.prepareStatement("INSERT INTO ri.terme VALUES (?, ?, ?)");
		for (Terme terme : termes) {
			
			ps.setInt(1, id);
			terme.setId(id);
			ps.setString(2, terme.getRacine());
			ps.setFloat(3, terme.getIpf());
			ps.addBatch();
			id++;
			if (id % NUM_INSERT_IN_ROW == 0) {
				ps.clearParameters();
				ps.executeBatch();
				ps.clearBatch();
			}
		}

		ps.clearParameters();
		int[] results = ps.executeBatch();
		System.out.println("termes mis en tables");
	}

	private void saveParagraphe(Connection c) throws SQLException {
		int id = 0;
                moyPara();
                nbMoyDoc = nbMaxDoc/paragraphes.size();
                PreparedStatement ps = c.prepareStatement("INSERT INTO ri.paragraphe VALUES (?, ?, ?, ?)");
		for (Paragraphe par : paragraphes) {
			ps.setInt(1, id);
			par.setId(id);
			ps.setString(2, par.getXpath());
			ps.setInt(3, par.getSommAppTerm());
			ps.setInt(4, par.getDocumentSource().getId());
			ps.addBatch();
			id++;
			if (id % NUM_INSERT_IN_ROW == 0) {
				ps.clearParameters();
				ps.executeBatch();
				ps.clearBatch();
			}
		}

		ps.clearParameters();
		int[] results = ps.executeBatch();
		System.out.println("paragraphes mis en table");
	}

	private void saveTermeParagraphe(Connection c) throws SQLException {
		int id = 0;
                calcTfIdf();
                calcTfRobert();
		PreparedStatement ps = c.prepareStatement("INSERT INTO ri.terme_paragraphe VALUES (?, ?, ?, ?, ?)");
		for (TermeParagraphe termePara : termeParagraphes) {
			
			ps.setInt(1, id);
			termePara.setId(id);
			ps.setFloat(2, termePara.getTf());
			ps.setFloat(3, termePara.getTf_robertson());
			ps.setInt(4, termePara.getTerme().getId());
			ps.setInt(5, termePara.getParagraphe().getId());
			ps.addBatch();
			id++;
			if (id % NUM_INSERT_IN_ROW == 0) {
				ps.clearParameters();
				ps.executeBatch();
				ps.clearBatch();
			}
		}

		ps.clearParameters();
		ps.executeBatch();
		System.out.println("terme_paragraphes mis en table");
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
			if (id % NUM_INSERT_IN_ROW == 0) {
				ps.clearParameters();
				ps.executeBatch();
				ps.clearBatch();
			}
		}

		ps.clearParameters();
		ps.executeBatch();
		System.out.println("apparitions mis en table");
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
			if (id % NUM_INSERT_IN_ROW == 0) {
				ps.clearParameters();
				ps.executeBatch();
				ps.clearBatch();
			}
		}

		ps.clearParameters();
		ps.executeBatch();
		System.out.println("positions mis en table");
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
			if (id % NUM_INSERT_IN_ROW == 0) {
				ps.clearParameters();
				ps.executeBatch();
				ps.clearBatch();
			}
		}

		ps.clearParameters();
		ps.executeBatch();
		System.out.println("apapritionTypes mis en table");
	}

	private void CalcIdf() {
		int nbTotalDoc = paragraphes.size();
                int nbDocApp = 0;
                for(Terme ter : termes)
                {
                    nbDocApp = ter.getTermeParagraphes().size();
                    ter.setIpf((float) Math.log(nbTotalDoc / nbDocApp));
                }
	}

	private void calcTfIdf() {
                for(TermeParagraphe termePara : termeParagraphes)
                {
                    termePara.setTf_robertson(termePara.getTerme().getIpf() * termePara.getTf());
                }
	}

        private void calcTfRobert() {

        for(TermeParagraphe termPara : termeParagraphes)
        {
            int longueur_doc = termPara.getParagraphe().getSommAppTerm();
            termPara.setTf_robertson((float) (termPara.getTf() / (0.5 + 1.5 * (longueur_doc / nbMoyDoc)+termPara.getTf())));

        }

    }
         //calcuer la  longueur moyenne du paragraphe : faire la somme des longueures de tous les paragraphes et diviser par le nb de paraphraphes
    private void moyPara()
    {
        for(Paragraphe para: paragraphes)
        {
            nbMaxDoc += para.getSommAppTerm();
        }

    }

}
