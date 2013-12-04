/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.query;

import com.insa.rila.db.PostGreFactory;
import com.insa.rila.index.Index;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author strokyl
 */
public class Query {

	private final List<String> termes;
	private final int id;
	private final static String EXPECTED_REP_URL = "qrels/qrel%02d.txt";

	private Set<Paragraph> expectedParagraph;
	private List<Paragraph> result;

	public Query(int id, String query) throws IOException {
		this.termes = Index.getTokenList(query);
		this.id = id;
	}

	public Set<Paragraph> getExpectedParagraph() {
		return expectedParagraph;
	}

	public void parseExpectedParagraph() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(String.format(EXPECTED_REP_URL, this.id)));
		String[] line;
		String fileUrl;
		String xpath;
		boolean pertinant;
		expectedParagraph = new HashSet<Paragraph>();
		while(scanner.hasNext()) {
			line = scanner.nextLine().split("\\s+");
			fileUrl = line[0];
			xpath = line[1];
			pertinant = Integer.parseInt(line[2]) == 1;

			if(pertinant) {
				expectedParagraph.add(new Paragraph(xpath, fileUrl, this));
			}
		}
	}

	public List<Paragraph> searchParagraph() throws SQLException {
		List<Paragraph> paragraphsTrie;
		Map<Integer, Paragraph> paragraphs = new HashMap<Integer, Paragraph>();
		Connection connection = PostGreFactory.getConnect();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT paragraphe.paragraphe_id, terme.racine, terme.ipf, terme_paragraphe.tf_robertson,paragraphe.xpath, document.url_xml FROM terme NATURAL JOIN terme_paragraphe NATURAL JOIN paragraphe INNER JOIN document on paragraphe.document_id = document.document_id WHERE racine in (");
		for (int i = 0; i < termes.size() - 1; i++) {
			builder.append("?, ");
		}
		builder.append("? );");
		String requete = builder.toString();
		System.out.println(requete);
		PreparedStatement ps = connection.prepareStatement(requete);
		int id = 1;
		for (String terme : termes) {
			ps.setString(id, terme);
			id++;
		}

		ResultSet result = ps.executeQuery();
		Paragraph paragraph;
		String racine, xpath, url;
		float ipf, tf;

		while (result.next()) {
			id = result.getInt(1);
			racine = result.getString(2);
			ipf = result.getFloat(3);
			tf = result.getFloat(4);
			xpath = result.getString(5);
			url = result.getString(6);

			/*
			 System.out.println(String.format("id : %s, racine : %s, ipf : %f, tf : %f, xpath : %s, url : %s",
			 result.getInt(1), result.getString(2), result.getFloat(3), result.getFloat(4), 
			 result.getString(5), result.getString(6)));
			 */
			paragraph = paragraphs.get(id);
			if (paragraph == null) {
				paragraph = new Paragraph(xpath, url, this);
				paragraph.addTerme(racine, ipf * tf);
			}

			paragraphs.put(id, paragraph);
		}

		paragraphsTrie = new ArrayList<Paragraph>(paragraphs.values());

		Collections.sort(paragraphsTrie, new Comparator<Paragraph>() {

			public int compare(Paragraph o1, Paragraph o2) {
				return (new Float(o2.getPertinance())).compareTo(o1.getPertinance());
			}

		});

		this.result = paragraphsTrie;
		return paragraphsTrie;
	}

	/**
	 *
	 * @param paragraph
	 * @return
	 */
	public float computePertinance(Paragraph paragraph) {
		//TODO a améliorer
		float result = 0.0f;

		for (float p : paragraph.getMapTermePoids().values()) {
			result += p;
		}

		return result;
	}

	public int avoirPrecission(int N) {
		int tot = 0;

		for(int i= 0; i < N ;i++) {
			if(expectedParagraph.contains(result.get(i))){
				tot++;
			}	
		}

		return tot;
	}

	public int getId() {
		return id;
	}
}
