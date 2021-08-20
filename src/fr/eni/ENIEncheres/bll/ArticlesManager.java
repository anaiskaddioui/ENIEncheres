package fr.eni.ENIEncheres.bll;

import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.dao.DAOArticleVendu;
import fr.eni.ENIEncheres.dal.dao.DAOFactory;

public class ArticlesManager {
	
	private DAOArticleVendu articleDAO;
	
	//Constructeur : 
	public ArticlesManager() {
		this.articleDAO=DAOFactory.getArticlesDAO();
	}
	
	
	//Méthodes selectArtByCategories : 
	public List<ArticleVendu> selectionnerTousLesArticlesById(int id) throws DALException {
		
		return this.articleDAO.rechercheParIdCategories(id);
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
	
}
