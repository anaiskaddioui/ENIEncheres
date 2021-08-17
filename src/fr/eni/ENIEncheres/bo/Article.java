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
	private int etatVente;
	private Categorie categorie;

	public Article(int id, String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int prixInitial, int prixVente, int etatVente, Utilisateur utilisateur, Categorie categorie,
			int utilisateurId, int categorieId, String utilisateurPseudo) {
		super();
		this.idArticle = id;
		this.nomArticle = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.categorie = categorie;

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
			int prixInitial, int prixVente, int etatVente, Utilisateur utilisateur, Categorie categorie,
			int utilisateurId, int categorieId) {
		super();
		this.nomArticle = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.categorie = categorie;

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

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
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
				+ prixInitial + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ",categorie=" + categorie
				+ "]";

	}
}
