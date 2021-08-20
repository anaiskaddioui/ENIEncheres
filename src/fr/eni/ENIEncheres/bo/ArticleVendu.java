package fr.eni.ENIEncheres.bo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ArticleVendu {
	private int idArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private int prixInitial;
	private int prixVente;
	private int etatVente;
	private Utilisateurs utilisateur;
	private Categorie categorie;
	private int utilisateurId;
	private int idCategorie;
	private Retrait retrait;
	private String utilisateurPseudo;
	
	


	public ArticleVendu(int idArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int prixInitial, int prixVente, String etatVente, int idCategorie, int idUtilsateur) {
		super();
		this.idArticle = idArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.utilisateurId = utilisateurId;
		this.idCategorie = categorieId;
		this.utilisateurPseudo = utilisateurPseudo;
	}


	public void Article(int id, String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
	int miseAPrix, int prixVente, int etatVente, int utilisateurId, int idCategorie) {
	this.idArticle = id;
	this.nomArticle = nom;
	this.description = description;
	this.dateDebutEncheres = dateDebutEncheres;
	this.dateFinEncheres = dateFinEncheres;
	this.miseAPrix = miseAPrix;
	this.prixVente = prixVente;
	this.etatVente = etatVente;
	this.utilisateurId = utilisateurId;
	this.idCategorie = idCategorie;
	}
		
	
	public void Article(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, int etatVente, int utilisateurId, int categorieId) {
		
		this.nomArticle = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateurId = utilisateurId;
		this.idCategorie = categorieId;
		
	}

	public void Article(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix,
		int prixVente, int etatVente, Utilisateurs utilisateur, Categorie categorie, int utilisateurId,
		int categorieId) {
	
	this.nomArticle = nom;
	this.description = description;
	this.dateDebutEncheres = dateDebutEncheres;
	this.dateFinEncheres = dateFinEncheres;
	this.miseAPrix = miseAPrix;
	this.prixVente = prixVente;
	this.etatVente = etatVente;
	this.utilisateur = utilisateur;
	this.categorie = categorie;
	this.utilisateurId = utilisateurId;
	this.idCategorie = categorieId;
}
	
	public void Article(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix) {

		this.nomArticle = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
	
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

	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	
	public String getStrDateFin() {
		return getDateFinEncheres()==null? "" : dateFormat.format(getDateFinEncheres());
	}
	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public int getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(int etatVente) {
		this.etatVente = etatVente;
	}
	

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Utilisateurs getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateurs utilisateur) {
		this.utilisateur = utilisateur;
	}

	public int getUtilisateurId() {
		return utilisateurId;
	}


	public void setUtilisateurId(int utilisateurId) {
		this.utilisateurId = utilisateurId;
	}


	public int getCategorieId() {
		return idCategorie;
	}


	public void setCategorieId(int categorieId) {
		this.idCategorie = categorieId;
	}


	public Retrait getRetrait() {
		return retrait;
	}


	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	

	public String getUtilisateurPseudo() {
		return utilisateurPseudo;
	}

	public void setUtilisateurPseudo(String utilisateurPseudo) {
		this.utilisateurPseudo = utilisateurPseudo;
	}
	

	public ArticleVendu(int int1, String string, String string2, LocalDate localDate, LocalDate localDate2, int int2,
			int int3, int int4, int idUtilisateur, int idCategorie2) {
	}

	


	@Override
	public String toString() {
		return "Article [id=" + idArticle + ", nom=" + nomArticle + ", description=" + description + ", dateDebutEncheres="
				+ dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix=" + miseAPrix
				+ ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", utilisateur=" + utilisateur
				+ ", categorie=" + categorie + ", utilisateurId=" + utilisateurId + ", categorieId=" + idCategorie
				+ ", retrait=" + retrait + "]";
	}


}
