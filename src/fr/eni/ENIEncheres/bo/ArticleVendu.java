package fr.eni.ENIEncheres.bo;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ArticleVendu {

	private int idArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private int prixInitial;
	private int prixVente;
	private String etatVente;
	private int idCategorie;
	private int idUtilisateur;
	private String pseudo;


	
	public ArticleVendu(int idArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int prixInitial, int prixVente, String etatVente, int idUtilisateur, int idCategorie) {
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
		this.idUtilisateur = idUtilisateur;

	}
	
	//Constructeur pour filtre article / catégories de vente en cours
	public ArticleVendu(int idArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int prixInitial, int prixVente, String etatVente, int idCategorie, int idUtilsateur, String pseudo) {
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
		this.idUtilisateur = idUtilsateur;
		this.pseudo = pseudo;

	}

	
	
	// Constructeur pour JUDICAEL?
	public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres,
		int	prixInitial, int prixVente, String etatVente, int idUtilisateur, int idCategorie) {

		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.idCategorie = idCategorie;
		this.idUtilisateur = idUtilisateur;
	}
	
	
	
	// Constructeur pour CHARLES
	public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres,
			int prixInitial, int prixVente, String etatVente, int idCategorie, int idUtilisateur, String pseudo) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.idCategorie = idCategorie;
		this.idUtilisateur = idUtilisateur;
		this.pseudo = pseudo;
	}
	

	//Constructeur pour modifier un article
	public ArticleVendu(int idArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int prixInitial, int idCategorie) {
		this.idArticle = idArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.idCategorie = idCategorie;
	}
	
	public ArticleVendu() {
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public static DateTimeFormatter getDateFormat() {
		return dateFormat;
	}

	public static void setDateFormat(DateTimeFormatter dateFormat) {
		ArticleVendu.dateFormat = dateFormat;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public List<ArticleVendu> setEtatVente(String etatVente) {
		this.etatVente = etatVente;
		return null;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilsateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", prixInitial="
				+ prixInitial + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", idCategorie=" + idCategorie
				+ ", idUtilisateur=" + idUtilisateur + "]";
	}

	public Object getRetrait() {
		// TODO Auto-generated method stub
		return null;
	}

}
