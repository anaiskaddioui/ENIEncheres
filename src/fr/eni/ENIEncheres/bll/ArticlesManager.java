package fr.eni.ENIEncheres.bll;


import java.sql.SQLException;
import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.dao.DAOArticleVendu;



public class ArticlesManager {

	private DAOArticleVendu articleDAO;

	
	//Constructeur : 
	public ArticlesManager() {
		this.articleDAO=DAOFactory.getDAOArticleVendu();
	}
	
	
	/**
	 * Selectionne tout les articles avec Utilisateur et catégorie
	 */
	public List<ArticleVendu> SelectAllArticlesAvecUtilisateurEtCategorie(int utilisateurId, int categorieId) throws BLLException {
		List<ArticleVendu> listeArticle = null;
		try {
			listeArticle = articleDAO.SelectAllArticlesAvecUtilisateurEtCategorie(utilisateurId, categorieId);
		} catch (DALException e) {
			System.out.println("erreur manager SelectAllArticlesAvecUtilisateurEtCategorie");
			throw new BLLException();
		}
		return listeArticle;

	}
	/**
	 * méthode d'insertion d'article en bdd
	 */
	public void  insertArticle ( ArticleVendu article, int utilisateurId, int categorieId) throws BLLException, SQLException {
		try {
			System.out.println("là ! bll" + article);
			 articleDAO.insertArticle ( article, utilisateurId, categorieId );


		} catch (DALException e) {
			System.out.println("erreur lors de l'insertion de l'article");
			throw new BLLException();
		}
	}
	/**
	 * Selectionne toutes les catégories
	 */
	public List<Categorie> SelectAllCategories() throws BLLException {
		
		List<Categorie> listeCategorie = null;
		
		try {
			listeCategorie = articleDAO.SelectAllCategories();
		} catch (DALException e) {
			System.out.println("Erreur manager SelectAllCategorie");
			throw new BLLException();
		}
		return listeCategorie;	
		
	}
	

	/**
	 * Selectionne tout les articles avec Utilisateur et catégorie
	 */
	public List<ArticleVendu> SelectAllArticles() throws BLLException {
		List<ArticleVendu> listeArticle = null;
		try {
			listeArticle = articleDAO.selectAllArticles();
		} catch (DALException e) {
			System.out.println("erreur manager SelectAllArticles");
			throw new BLLException();
		}
		return listeArticle;

	}
	
	public List<ArticleVendu> selectAllByEtatVente(int etatVente) throws BLLException{
		List<ArticleVendu> listeArticle = null;
		try {
			listeArticle = articleDAO.selectAllByEtatVente(etatVente);
		} catch (DALException e) {
			System.out.println("erreur manager selectAllByEtatVente");
			throw new BLLException();
		}
		return listeArticle;
	}
	

	public List<ArticleVendu> selectAllByEtatVenteUtilisateur(int etatVente, int idUtilisateur) throws BLLException{
		List<ArticleVendu> listeArticle = null;
		try {
			listeArticle = articleDAO.selectAllByEtatVenteUtilisateur(etatVente, idUtilisateur);
		} catch (DALException e) {
			System.out.println("erreur manager selectAllByEtatVenteUtilisateur");
			throw new BLLException();
		}
		return listeArticle;
	}
	
	public List<ArticleVendu> selectAllByEtatVenteGagne(int etatVente, int idUtilisateur) throws BLLException{
		List<ArticleVendu> listeArticle = null;
		try {
			listeArticle = articleDAO.selectAllByEtatVenteGagne(etatVente, idUtilisateur);
		} catch (DALException e) {
			System.out.println("erreur manager selectAllByEtatVenteGagne");
			throw new BLLException();
		}
		return listeArticle;
	}
	
	public List<ArticleVendu> SelectAllEncheresByEtat(int idUtilisateur, int etatVente) throws BLLException{
		List<ArticleVendu> listeArticle = null;
		try {
			listeArticle = articleDAO.SelectAllEncheresByEtat(idUtilisateur, etatVente);
		} catch (DALException e) {
			System.out.println("erreur manager SelectAllEncheresByEtat");
			throw new BLLException();
		}
		return listeArticle;
	}

	
	public ArticleVendu selectArticleByID(int idArticle) {
		ArticleVendu article = null;
		try {
			article = articleDAO.SelectEnchereById(idArticle);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return article;

	}
  
  //______________________________________________________________________________________________
  //Méthodes de Charles pour Filtre encheres
  
  	//Méthodes selectArticle par nom qui contient : 
	public List<ArticleVendu> selectionnerArticlesParMotCle(String nomArticle) throws DALException {
		
		return this.articleDAO.selectParMotCle(nomArticle);
	}
	
	//Méthodes selectArticle par categorie et nom qui contient : 
	public List<ArticleVendu> selectionnerArticlesParCategEtMotCle(String nomArticle, int idCategorie) throws DALException {
		
		return this.articleDAO.selectParCategEtMotCle(nomArticle, idCategorie);
	}	
	
  
	//Méthodes selectArtByEtatVente : 
	public List<ArticleVendu> selectionnerTousLesArticlesByEtat(String etat) throws DALException {
		
		return this.articleDAO.selectParEtat(etat);
	}
	
	//Méthodes selectAllArticles : 
	public List<ArticleVendu> selectionnerTousLesArticles() {
		
		return this.articleDAO.selectAllArticles();
	}
	
	//Méthodes selectAllArticles : 
	public List<ArticleVendu> selectionnerParIdCategorie(int idCategorie) throws DALException {
		
		return this.articleDAO.selectParIdCategorie(idCategorie);
	}
  //______________________________________________________________________________________________
}
