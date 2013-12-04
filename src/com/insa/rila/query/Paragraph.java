/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.query;

import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe une réponse possible à une requête
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
		if(pertinance == null) {
			computePertinance();
		}
		
		return pertinance;
	}

	@Override
	public String toString() {
		return "Paragraph{" + "xpath=" + xpath + ", xmlUrl=" + xmlUrl + ", pertinance=" + pertinance + '}';
	}


	

}
