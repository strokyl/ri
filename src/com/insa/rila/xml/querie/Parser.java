/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.xml.querie;

import com.insa.rila.xml.paragraph.BALADE;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author strokyl
 */
public class Parser {
	private static final String QUERY_URL = "Queries/queries.xml";

	/**
	 * Parse un fichier xml d'une requete
	 *
	 * @param file
	 * @return
	 * @throws JAXBException
	 */
	public static Queries parseFile(File file) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance("com.insa.rila.xml.querie");
		Unmarshaller u = jc.createUnmarshaller();
		Object o = u.unmarshal(file);

		return (Queries) o;
	}

	public static List<com.insa.rila.query.Query> getAllQuery(File file) throws JAXBException, IOException {
		List<com.insa.rila.query.Query> result = new LinkedList<com.insa.rila.query.Query>();
		Queries queries = parseFile(file);

		for (Query q : queries.getQuery()) {
			result.add(new com.insa.rila.query.Query(Integer.parseInt(q.getId().substring(1)), q.getText()));
		}

		return result;
	}

	public static List<com.insa.rila.query.Query> getAllQuery() throws JAXBException, IOException {
		return getAllQuery(new File(QUERY_URL));
	}
}
