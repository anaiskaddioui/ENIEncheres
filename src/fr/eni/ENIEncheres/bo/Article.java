package fr.eni.ENIEncheres.bo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Article {

	private int idArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private int prixInitial;
	private int prixVente;
	private String etatVente;
	private  int idCategorie;
	private int idUtilsateur;
	


	public Article(int idArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int prixInitial, int prixVente, String etatVente, int idCategorie,
			int idUtilsateur) {
		super();
		this.idArticle = idArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.idCategorie = idCategorie;
		this.idUtilsateur = idUtilsateur;
	}

	

	public Article(int id, String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int prixInitial, int prixVente, int etatVente, int utilisateurId, int categorieId) {
		this.idArticle = id;
		this.nomArticle = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;

	}

	public Article(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int prixInitial, int prixVente, int etatVente, int utilisateurId, int categorieId) {
		super();
		this.nomArticle = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;

	}

	public Article(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int prixInitial, int prixVente, String etatVente, int idUtilisateur , int idCategorie ,
			 int categorieId) {
		super();
		this.nomArticle = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.idCategorie = idCategorie;
		this.etatVente = etatVente;
		this.idUtilsateur = idUtilisateur;
		
		

	}

	public int getIdUtilsateur() {
		return idUtilsateur;
	}



	public void setIdUtilsateur(int idUtilsateur) {
		this.idUtilsateur = idUtilsateur;
	}



	public Article(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int prixInitial) {

		this.nomArticle = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;

	}

	public int getId() {
		return idArticle;
	}

	public void setId(int id) {
		this.idArticle = id;
	}

	public String getNom() {
		return nomArticle;
	}

	public void setNom(String nom) {
		this.nomArticle = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public String getStrDateDebut() {
		return getDateDebutEncheres() == null ? "" : dateFormat.format(getDateDebutEncheres());
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public String getStrDateFin() {
		return getDateFinEncheres() == null ? "" : dateFormat.format(getDateFinEncheres());
	}

	public int getMiseAPrix() {
		return prixInitial;
	}

	public void setMiseAPrix(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	
	public int getUtilisateurId() {
		return idArticle;
	}

	public void setUtilisateurId(int utilisateurId) {
		this.idArticle = utilisateurId;
	}

	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", prixInitial="
				+ prixInitial + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ",idCategorie=" + idCategorie
				+ "]";

	}
}
