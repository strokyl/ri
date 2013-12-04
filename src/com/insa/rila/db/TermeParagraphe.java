/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.rila.db;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author strokyl
 */
public class TermeParagraphe {

	private float tf;
	private float tf_robertson;
	private float tf_idf;
	private final Paragraphe paragraphe;
	private final Terme terme;
	private final Set<Apparition> apparitions;
	private int id;

	/**
	 * Pas util pour la db, juste util pour le calcul de tf
	 */
	private final Set<ApparitionType> apparitionTypes;

	/**
	 * Créer un terme_paragraphe qui s'ajoute automatiquement dans le terme
	 * et le paragraphe correspondant
	 *
	 * @param paragraphe
	 * @param terme
	 */
	public TermeParagraphe(Paragraphe paragraphe, Terme terme) {
		this.tf = 0f;
		this.paragraphe = paragraphe;
		this.terme = terme;
		this.apparitions = new HashSet<Apparition>();
		this.apparitionTypes = new HashSet<ApparitionType>();
		terme.addTermeParagraphes(this);
		paragraphe.addTermeParagraphes(this);
	}

	public Paragraphe getParagraphe() {
		return paragraphe;
	}

	public void addApparition(Apparition apparition) {
		this.apparitions.add(apparition);
	}

	public void addApparitionTypes(ApparitionType apparitionType) {
		this.apparitionTypes.add(apparitionType);
	}

	public Set<ApparitionType> getApparitionTypes() {
		return apparitionTypes;
	}

	public boolean hasApparitionType(ApparitionType apparitionType) {
		return apparitionTypes.contains(apparitionType);
	}

	public Set<Apparition> getApparition() {
		return this.apparitions;
	}

	public Terme getTerme() {
		return terme;
	}

	public float getTf() {
		return tf;
	}

	public void setTf(float tf) {
		this.tf = tf;
	}

	public float getTf_idf() {
		return tf_idf;
	}

	public void setTf_idf(float tf_idf) {
		this.tf_idf = tf_idf;
	}

	public float getTf_robertson() {
		return tf_robertson;
	}

	public void setTf_robertson(float tf_robertson) {
		this.tf_robertson = tf_robertson;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isTermeCorres(Terme ter) {

		return ter.getId() == this.terme.getId();
	}

	public void incTf() {
		//this.tf+=ponderation;
		this.tf += 1;
	}
}
