package fr.eni.ENIEncheres.bo;

import java.io.Serializable;
import java.util.Date;

public class Enchere implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -181210324306383886L;
	private Date dateEnchere;
	private int montantEnchere;
	private Utilisateurs encherisseur;

	public Enchere(Date dateEnchere, int montantEnchere, Utilisateurs encherisseur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.encherisseur = encherisseur;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public Utilisateurs getEncherisseur() {
		return encherisseur;
	}

	public void setEncherisseur(Utilisateurs encherisseur) {
		this.encherisseur = encherisseur;
	}

	public int getIdUtilisateur() {
		return 0;
	}

	public int getIdArticle() {
		return 0;

	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", encherisseur="
				+ encherisseur + ", getDateEnchere()=" + getDateEnchere() + ", getMontantEnchere()="
				+ getMontantEnchere() + ", getEncherisseur()=" + getEncherisseur() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
