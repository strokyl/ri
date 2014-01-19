/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.sparql;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author strokyl
 */
public class SparqlQuery {
	public static final String QUERY_HEAD = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n\n";
	
	private static List<String> makeQuery(String query, String keyword, String name) {
		StringBuilder st = new StringBuilder();

		st.append(QUERY_HEAD);

		st.append(String.format(query, keyword));

		List<String> result = new LinkedList<String>();

		Iterable<Map<String, String>> results = SparqlClient.getSparqlClient().select(st.toString());

		for(Map<String, String> map : results) {
			result.add(map.get(name));
		}

		return result;
	}	

	public static List<String> getSubEntity(String keyword) {
		String query = "SELECT ?otherkeyword WHERE \n" +
"{?object rdfs:label \"%s\"@fr.\n" +
"?subclass rdfs:subClassOf\n" +
"?object.\n" +
"?subclass rdfs:label ?otherkeyword}";
		return makeQuery(query, keyword, "otherkeyword");
	}

	public static List<String> getSousLieu(String keyword) {
		String query = "SELECT ?labelInside WHERE \n" +
"{ ?seSitueDans rdfs:label \"se situe dans\"@fr. \n" +
"?object rdfs:label \"%s\"@fr.\n" +
"?inside ?seSitueDans ?object.\n" +
"?inside rdfs:label ?labelInside}";
		return makeQuery(query, keyword, "labelInside");
	}

	public static List<String> getLieuACoter(String keyword) {
		String query = "SELECT ?insideLabel WHERE { ?seSituePresDe rdfs:label \"se situe près de\"@fr. \n" +
"?object rdfs:label \"France\"@fr.\n" +
"?inside ?seSituePresDe ?object.\n" +
"?inside rdfs:label ?insideLabel\n" +
"}";
		return makeQuery(query, keyword, "insideLabel");
	}

	public static List<String> getSynonime(String keyword) {
		String query = "SELECT ?other_label\n" +
"WHERE {\n" +
"  ?object rdfs:label \"balade\"@fr.\n" +
"  ?object rdfs:label ?other_label\n" +
"}";
		return makeQuery(query, null, "other_label");

	}
	public static List<String> getAllLabel() {
		String query = "SELECT ?label WHERE { ?object rdfs:label ?label}";
		return makeQuery(query, null, "label");
	}

	
}
