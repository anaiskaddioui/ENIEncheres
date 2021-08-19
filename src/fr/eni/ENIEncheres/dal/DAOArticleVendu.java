package fr.eni.ENIEncheres.dal;

import java.util.List;

import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.bo.Utilisateurs;

public interface DAOArticleVendu<ArticleVendu> {
	
	
	    List<ArticleVendu> filterByCategory(Categorie categorie) throws DALException;
	    List<ArticleVendu> filterByString(String filter) throws DALException;
	    List<Integer> filterByEtat(String etat) throws DALException;
	    List<Integer> getArticlesFromASellerAndState (Utilisateurs utilisateur, String state) throws DALException;
	    void updateCurrentPrice(int noArticle, int newPrice) throws DALException;
	    
	    public void insert (ArticleVendu articleVendu) throws DALException;
	}


