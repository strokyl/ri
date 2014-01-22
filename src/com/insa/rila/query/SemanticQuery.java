/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.insa.rila.query;

import com.insa.rila.sparql.SparqlQuery;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.insa.rila.index.Index;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author strokyl
 */
public class SemanticQuery {
	public static final float MAX_SUM_WORD = 1.3f;
	public static final float SUB_ENTITY_COEFF = 1.2f;
	public static final float SOUS_LIEU_COEFF = 1.0f;
	public static final float LIEU_A_COTER = 0.3f;
	public static final float SYNONIM_COEFF = 1.0f;
	private static final float ANIMAL_DANS_COEFF = 0.3f;
	public Map<String, Float> vector;

	public SemanticQuery(String query) throws IOException {
		System.out.println("Query : " + query);
		vector = new HashMap<String, Float>();
		Map<String, Float> additionalLabel;

		List<String> labels = SparqlQuery.getAllLabel();
		//System.out.println("label trouvé");
		
		List<String> foundedLabel = new LinkedList<String>();

		String cleanQuery = Index.toLower(Index.asciiFoldingOnWord(query));
		String cleanLabel;

		for(String label : labels) {
			cleanLabel = Index.toLower(Index.asciiFoldingOnWord(label));

			if(!cleanLabel.equals("balade") && cleanQuery.indexOf(cleanLabel) != -1) {
				System.out.println(cleanLabel);
				foundedLabel.add(label);
			}
		}

		//found subclass
		float coeff;
		for(String label : foundedLabel) {
			additionalLabel = new HashMap<String, Float>();
			coeff = SUB_ENTITY_COEFF;

			for(String newLabel : SparqlQuery.getSubEntity(label))	{
				updateAdditionalLabel(newLabel, coeff, additionalLabel);
				System.out.println("	sous entité : " + newLabel);
			}

			coeff = SOUS_LIEU_COEFF;
			for(String newLabel : SparqlQuery.getSousLieu(label))	{
				updateAdditionalLabel(newLabel, coeff, additionalLabel);
				System.out.println("	sous lieu : " + newLabel);
			}


			coeff = LIEU_A_COTER;
			for(String newLabel : SparqlQuery.getLieuACoter(label))	{
				updateAdditionalLabel(newLabel, coeff, additionalLabel);
				System.out.println("	lieu a coter : " + newLabel);
			}

			

			coeff = SYNONIM_COEFF;
			for(String newLabel : SparqlQuery.getSynonime(label))	{
				updateAdditionalLabel(newLabel, coeff, additionalLabel);
				System.out.println("	synonim : " + newLabel);
			}

			coeff = ANIMAL_DANS_COEFF;
			for(String newLabel : SparqlQuery.getAnimalDansLieu(label))	{
				updateAdditionalLabel(newLabel, coeff, additionalLabel);
				System.out.println("	animale du lieu : " + newLabel);
			}


			float s = 0.0f;

			for(float v : additionalLabel.values()) {
				s += v;
			}

			for(String w : additionalLabel.keySet()) {
				additionalLabel.put(w, additionalLabel.get(w)/s*MAX_SUM_WORD);
			}


			Float previous;
			for(String token : additionalLabel.keySet()) {
				previous = vector.get(token);
				vector.put(token, additionalLabel.get(token) + (previous == null ? 0.0f : previous));
			}
		}

	}

	private void updateAdditionalLabel(String newLabel, float coeff, Map<String, Float> additionalLabel) throws IOException {
		final List<String> tokenList = Index.getTokenList(newLabel);
		for(String token : tokenList)
		{
			if(additionalLabel.containsKey(token)) {
				additionalLabel.put(token, coeff + additionalLabel.get(token));
			}
			else {
				additionalLabel.put(token, coeff);
			}
		} 
	}

	public Map<String, Float> getVector() {
		return vector;
	}
}
