package fr.eni.ENIEncheres.bo;

import java.util.ArrayList;

public class Categorie {

	private int idCategorie;
	private String libelle;
	private ArrayList<ArticleVendu> categorieArticle = null;

	public Categorie() {
		super();
	}

	public Categorie(int id, String libelle) {
		this.idCategorie = id;
		this.libelle = libelle;
	}

	public Categorie(String libelle) {
		this.libelle = libelle;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public ArrayList<ArticleVendu> getCategorieArticle() {
		return categorieArticle;
	}

	public void setCategorieArticle(ArrayList<ArticleVendu> categorieArticle) {
		this.categorieArticle = categorieArticle;
	}

	@Override
	public String toString() {
		return "Categorie [idCategorie=" + getIdCategorie() + ", Libelle=" + getLibelle() + "]";
	
	}
}
