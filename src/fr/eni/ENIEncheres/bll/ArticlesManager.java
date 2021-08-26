package fr.eni.ENIEncheres.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.dao.DAOArticleVendu;
import fr.eni.ENIEncheres.dal.dao.DAOFactory;



public class ArticlesManager {

	private DAOArticleVendu articleDAO;
	
	//Constructeur : 
	public ArticlesManager() {
		this.articleDAO=DAOFactory.getDAOArticleVendu();
	}
	
	
	
	
	//_______________ METHODE JUDICAEL _________________________________________________________
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
	
	//______________________________________________________________________________________________
	
	

	public ArticleVendu selectArticleByID(int idArticle) {
		ArticleVendu article = null;
		try {
			article = articleDAO.selectArticleById(idArticle);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public void misAJourPrixVente(int idArticle, int prix) {
		try {
			articleDAO.updateSellPrice(idArticle, prix);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	public void supprimerArticle(int idArticle) {
		try {
			articleDAO.deleteArticle(idArticle);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	public void modifierArticle(ArticleVendu article) {
		try {
			this.articleDAO.updateArticle(article);
		} catch (DALException e) {
			e.printStackTrace();
		}
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
  
	//Méthodes selectParEtatVente : 
	public List<ArticleVendu> selectionnerTousLesArticlesByEtat(String etat) throws DALException {		
		return this.articleDAO.selectParEtat(etat);
	}
	
	//Méthodes selectAllArticles : 
	public List<ArticleVendu> selectionnerTousLesArticles() {		
		return this.articleDAO.selectAllArticles();
	}
	
	//Méthodes selectParIdCategorie : 
	public List<ArticleVendu> selectionnerParIdCategorie(int idCategorie) throws DALException {		
		return this.articleDAO.selectParIdCategorie(idCategorie);
	}
	
	//Méthodes selectParEtatEtUserId  : 	
	public List<ArticleVendu> selectionnerParEtatEtUserId(String etat, int userId) throws DALException {
		return this.articleDAO.selectParEtatEtUserId(etat, userId);
	}
	
	public List<ArticleVendu> selectionnerParEtatEtUserIdEtCategEtMotCle(String etat, int userId, int categorie, String nomArticle) throws DALException {
		return this.articleDAO.selectParEtatEtUserIdEtCategEtMotCle(etat, userId, categorie, nomArticle);
	}

	public List<ArticleVendu> selectionnerParUserId(int userId) throws DALException {
		return this.articleDAO.selectParUserId(userId);
	}	
	
	public ArrayList<ArticleVendu> selectionnerParEtatEtMotCle(String etat, String nomArticle)throws DALException {
		return this.articleDAO.selectParEtatEtMotCle(etat, nomArticle);		
	}
	
	public List<ArticleVendu> selectionnerParEtatEtUserIdEtMotCle(String etat, int userId, String nomArticle) throws DALException {
		return this.articleDAO.selectParEtatEtUserIdEtMotCle(etat,  userId, nomArticle);		
	}	
	
	public ArrayList<ArticleVendu> selectionnerParEtatEtCategorie(String etat, int categorie)throws DALException {
		return this.articleDAO.selectParEtatEtCategorie(etat, categorie);		
	}	

	public List<ArticleVendu> selectionnerParEtatEtUserIdEtCategorie(String etat, int userId, int categorie) throws DALException {
		return this.articleDAO.selectParEtatEtUserIdEtCategorie(etat, userId, categorie);
	}

	public List<ArticleVendu> selectionnerParEtatEtCategEtMotCle(String etat, int categorie, String nomArticle) throws DALException {
		return this.articleDAO.selectParEtatEtCategEtMotCle(etat, categorie, nomArticle);
	}	
	
	public void miseAJourDateFinEnchere(String date) throws DALException {
		this.articleDAO.updateDateFinEnchere(date);
	}
  //______________________________________________________________________________________________
}
