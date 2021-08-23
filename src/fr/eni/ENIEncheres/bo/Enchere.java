package fr.eni.ENIEncheres.bo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Enchere implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -181210324306383886L;
	private int idUtilisateur;
	private int idArticle;
	private Timestamp dateEnchere;
	private int montantEnchere;


	public Enchere() {
		
	}
	
	public Enchere(int no_utilisateur, int no_article, Timestamp dateEnchere, int montantEnchere) {
		super();
		this.idUtilisateur = no_utilisateur;
		this.idArticle = no_article;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public Timestamp getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Timestamp dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int no_utilisateur) {
		this.idUtilisateur = no_utilisateur;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int no_article) {
		this.idArticle = no_article;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", encherisseur="
				+ idUtilisateur + ", getDateEnchere()=" + getDateEnchere() + ", getMontantEnchere()="
				+ getMontantEnchere() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	
	}

}
