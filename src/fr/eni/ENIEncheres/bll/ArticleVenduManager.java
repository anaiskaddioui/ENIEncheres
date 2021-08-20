package fr.eni.ENIEncheres.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.dal.DAOFactory;



public class ArticleVenduManager {

	private ArticleVendu articleDAO;

	public ArticleVenduManager() {
		articleDAO = DAOFactory.getArticleVendu();
	}

	
	public List<ArticleVendu> SelectAllArticlesAndUserEtCategorie(int idUtilisateur, int idCategorie) throws BLLException {
		List<ArticleVendu> listeArticle = null;
		listeArticle = articleDAO.SelectAllArticlesAvecUtilisateurEtCategorie(idUtilisateur, idCategorie);
		return listeArticle;

	}
	
	 
	public void  insertArticle ( ArticleVendu article, int idUtilisateur, int idCategorie) throws BLLException, SQLException {
		System.out.println("la bll" + article);
		 articleDAO.insertArticle ( article, idUtilisateur, idCategorie );
	}
	
	public List<Categorie> SelectAllCategories() throws BLLException {
		
		List<Categorie> listeCategorie = null;
		
		listeCategorie = articleDAO.SelectAllCategories();
		return listeCategorie;	
		
	}
	

	public List<ArticleVendu> SelectAllArticles() throws BLLException {
		List<ArticleVendu> listeArticle = null;
		listeArticle = articleDAO.selectAll();
		return listeArticle;

	}
	
	public List<ArticleVendu> selectAllByEtatVente(String etatVente) throws BLLException{
		List<ArticleVendu> listeArticle = null;
		listeArticle = articleDAO.setEtatVente(etatVente);
		return listeArticle;
	}
	
	public List<ArticleVendu> selectAllByEtatVenteUtilisateur(int etatVente, int idUtilisateur) throws BLLException{
		List<ArticleVendu> listeArticle = null;
		listeArticle = articleDAO.selectAllByEtatVenteUtilisateur(etatVente, idUtilisateur);
		return listeArticle;
	}
	
	public List<ArticleVendu> selectAllByEtatVenteGagne(int etatVente, int idUtilisateur) throws BLLException{
		List<ArticleVendu> listeArticle = null;
		listeArticle = articleDAO.selectAllByEtatVenteUtilisateur(etatVente, idUtilisateur);
		return listeArticle;
	}
	
	public List<ArticleVendu> SelectAllEncheresByEtat(int idUtilisateur, int etatVente) throws BLLException{
		List<ArticleVendu> listeArticle = null;
		listeArticle = articleDAO.SelectAllEncheresByEtat(idUtilisateur, etatVente);
		return listeArticle;
	}

	
	public ArticleVendu selectArticleByID(int idArticle) {
		ArticleVendu article = null;
		article = articleDAO.SelectEnchereById(idArticle);
		return article;

	}
}