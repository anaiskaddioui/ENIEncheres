package fr.eni.ENIEncheres.dal.dao;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;

import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;


public interface DAOArticleVendu {
	    List<ArticleVendu> filterByCategory(Categorie categorie) throws DALException;
	    List<ArticleVendu> filterByString(String filter) throws DALException;
	    List<Integer> filterByEtat(String etat) throws DALException;
	    List<Integer> getArticlesFromASellerAndState (Utilisateurs utilisateur, String state) throws DALException;
	    void updateCurrentPrice(int noArticle, int newPrice) throws DALException;
	    
	    public void insert (ArticleVendu articleVendu) throws DALException;

	    public ArrayList<ArticleVendu> rechercheParIdCategories(int idCategorie) throws DALException;
	  	public List<ArticleVendu> selectParEtat(String etat) throws DALException;
		  public List <ArticleVendu> selectAllArticles();
		  public List<ArticleVendu> selectParIdCategorie(int idCategorie) throws DALException;
	}


