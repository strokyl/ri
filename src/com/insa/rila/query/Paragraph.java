/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.query;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

/**
 * Cette classe une réponse possible à une requête
 *
 * @author Luc DUZAN
 */
public class Paragraph {

	private final String xpath;
	private final String xmlUrl;
	private final Map<String, Float> mapTermePoids;
	private final Query query;
	private Float pertinance;

	public Paragraph(String xpath, String xmlUrl, Query query) {
		this.xpath = xpath;
		this.xmlUrl = xmlUrl;
		this.mapTermePoids = new HashMap<String, Float>();
		this.query = query;
		this.pertinance = null;
	}

	public String getXpath() {
		return xpath;
	}

	public String getXmlUrl() {
		return xmlUrl;
	}

	public String getText() {
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(xmlUrl);
			XPath xpath = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xpath.compile(this.xpath);
			return  String.format("Tf.Idf  : %s\n", mapTermePoids) + expr.evaluate(doc);
			
		} catch (Exception ex) {
			Logger.getLogger(Paragraph.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

	}

	public Map<String, Float> getMapTermePoids() {
		return mapTermePoids;
	}

	public void computePertinance() {
		pertinance = query.computePertinance(this);
	}

	void addTerme(String racine, float poid) {
		mapTermePoids.put(racine, poid);
	}

	public float getPertinance() {
		if (pertinance == null) {
			computePertinance();
		}

		return pertinance;
	}

	@Override
	public String toString() {
		return "Paragraph{" + "xpath=" + xpath + ", xmlUrl=" + xmlUrl + ", pertinance=" + pertinance + '}';
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 71 * hash + (this.xpath != null ? this.xpath.hashCode() : 0);
		hash = 71 * hash + (this.xmlUrl != null ? this.xmlUrl.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Paragraph other = (Paragraph) obj;
		if ((this.xpath == null) ? (other.xpath != null) : !this.xpath.equals(other.xpath)) {
			return false;
		}
		if ((this.xmlUrl == null) ? (other.xmlUrl != null) : !this.xmlUrl.equals(other.xmlUrl)) {
			return false;
		}
		return true;
	}

}
