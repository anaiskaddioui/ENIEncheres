package fr.eni.ENIEncheres.dal.dao;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;

import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;


public interface DAOArticleVendu {
	    List<ArticleVendu> filterByString(String filter) throws DALException;
	    List<Integer> getArticlesFromASellerAndState (Utilisateurs utilisateur, String state) throws DALException;
	    void updateCurrentPrice(int noArticle, int newPrice) throws DALException;
	    public void insert (ArticleVendu articleVendu) throws DALException;
	  	public List<ArticleVendu> selectParEtat(String etat) throws DALException;
		public List <ArticleVendu> selectAllArticles();
		public List<ArticleVendu> selectParIdCategorie(int idCategorie) throws DALException;
		public  ArrayList<ArticleVendu> selectParMotCle(String nomArticle) throws DALException;
		public ArrayList<ArticleVendu> selectParCategEtMotCle(String nomArticle, int idCategorie)throws DALException;
	}


