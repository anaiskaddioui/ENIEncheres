package fr.eni.ENIEncheres.bll;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.dao.DAOArticleVendu;
import fr.eni.ENIEncheres.dal.dao.DAOFactory;

public class ArticlesManager {

	private DAOArticleVendu articleDAO;

	// Constructeur :
	public ArticlesManager() {
		this.articleDAO = DAOFactory.getDAOArticleVendu();
	}

	// _______________ METHODE JUDICAEL
	public ArticleVendu insertArticle(ArticleVendu newAdd) throws DALException {

		try {
			this.articleDAO.insertArticle(newAdd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newAdd;
		
	}
	// ______________________________________________________________________________________________

	

	

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

	// ______________________________________________________________________________________________
	// Méthodes de Charles pour Filtre encheres

	// Méthodes selectArticle par nom qui contient :
	public List<ArticleVendu> selectionnerArticlesParMotCle(String nomArticle) throws DALException {
		return this.articleDAO.selectParMotCle(nomArticle);
	}

	// Méthodes selectArticle par categorie et nom qui contient :
	public List<ArticleVendu> selectionnerArticlesParCategEtMotCle(String nomArticle, int idCategorie)
			throws DALException {
		return this.articleDAO.selectParCategEtMotCle(nomArticle, idCategorie);
	}

	// Méthodes selectParEtatVente :
	public List<ArticleVendu> selectionnerTousLesArticlesByEtat(String etat) throws DALException {
		return this.articleDAO.selectParEtat(etat);
	}

	// Méthodes selectAllArticles :
	public List<ArticleVendu> selectionnerTousLesArticles() {
		return this.articleDAO.selectAllArticles();
	}

	// Méthodes selectParIdCategorie :
	public List<ArticleVendu> selectionnerParIdCategorie(int idCategorie) throws DALException {
		return this.articleDAO.selectParIdCategorie(idCategorie);
	}

	// Méthodes selectParEtatEtUserId :
	public List<ArticleVendu> selectionnerParEtatEtUserId(String etat, int userId) throws DALException {
		return this.articleDAO.selectParEtatEtUserId(etat, userId);
	}

	public List<ArticleVendu> selectionnerParEtatEtUserIdEtCategEtMotCle(String etat, int userId, int categorie,
			String nomArticle) throws DALException {
		return this.articleDAO.selectParEtatEtUserIdEtCategEtMotCle(etat, userId, categorie, nomArticle);
	}

	public List<ArticleVendu> selectionnerAchatParEtatEtUserId(String etat, int userId) throws DALException {
		return this.articleDAO.selectAchatParEtatEtUserId(etat, userId);
	}
	
	public List<ArticleVendu> selectionnerAchatParEtatEtUserIdEtCategEtMotCle(String etat, int userId, int categorie,
			String nomArticle) throws DALException {
		return this.articleDAO.selectAchatParEtatEtUserIdEtCategEtMotCle(etat, userId, categorie, nomArticle);
	}

	public List<ArticleVendu> selectionnerParUserId(int userId) throws DALException {
		return this.articleDAO.selectParUserId(userId);
	}
	
	public List<ArticleVendu> selectionnerParEtatEtUserIdEtMotCle(String etat, int userId, String nomArticle) throws DALException {
		return this.articleDAO.selectParEtatEtUserIdEtMotCle(etat,  userId, nomArticle);		
	}	

	public List<ArticleVendu> selectionnerAchatParEtatEtUserIdEtMotCle(String etat, int userId, String nomArticle) throws DALException {
		return this.articleDAO.selectAchatParEtatEtUserIdEtMotCle(etat,  userId, nomArticle);		
	}	
	
	public ArrayList<ArticleVendu> selectionnerParEtatEtCategorie(String etat, int categorie)throws DALException {
		return this.articleDAO.selectParEtatEtCategorie(etat, categorie);		
	}	

	public List<ArticleVendu> selectionnerParEtatEtUserIdEtCategorie(String etat, int userId, int categorie) throws DALException {
		return this.articleDAO.selectParEtatEtUserIdEtCategorie(etat, userId, categorie);
	}

	public List<ArticleVendu> selectionnerAchatParEtatEtUserIdEtCategorie(String etat, int userId, int categorie) throws DALException {
		return this.articleDAO.selectAchatParEtatEtUserIdEtCategorie(etat, userId, categorie);
	}

	public List<ArticleVendu> selectionnerParEtatEtCategEtMotCle(String etat, int categorie, String nomArticle) throws DALException {
		return this.articleDAO.selectParEtatEtCategEtMotCle(etat, categorie, nomArticle);
	}	
	
	public void miseAJourDateFinEnchere(Date date) throws DALException {
		this.articleDAO.updateDateFinEnchere(date);
	}
	
	public ArrayList<ArticleVendu> selectionnerParEtatEtMotCle(String etat, String nomArticle)throws DALException{
		return this.articleDAO.selectParEtatEtMotCle(etat, nomArticle);		
	}
	
	public void miseAJourDateDebutEnchere(Date date) throws DALException {
		this.articleDAO.updateDateDebutEnchere(date);
	}
	
	public List<ArticleVendu> selectionnerEncheresFinies(Date date) throws DALException {
		return this.articleDAO.selectFinEnchere(date);
	}
	
  //______________________________________________________________________________________________

}
