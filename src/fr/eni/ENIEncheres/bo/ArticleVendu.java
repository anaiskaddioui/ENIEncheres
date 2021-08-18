package fr.eni.ENIEncheres.bo;

import java.sql.Date;
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
	private String etatVente;
	private int idCategorie;
	private int idUtilsateur;
	private int pseudo;
	
	
	public ArticleVendu(int idArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int prixInitial, int prixVente, String etatVente, int idCategorie,
			int idUtilsateur, int pseudo) {
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
		this.pseudo = pseudo;


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


	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}


	public int getIdCategorie() {
		return idCategorie;
	}


	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}


	public int getIdUtilsateur() {
		return idUtilsateur;
	}


	public void setIdUtilsateur(int idUtilsateur) {
		this.idUtilsateur = idUtilsateur;
	}


	public int getPseudo() {
		return pseudo;
	}


	public void setPseudo(int pseudo) {
		this.pseudo = pseudo;
	}




	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", prixInitial="
				+ prixInitial + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", idCategorie=" + idCategorie
				+ ", idUtilsateur=" + idUtilsateur + ", pseudo=" + pseudo + "]";
	}

	
	
}

