package fr.eni.ENIEncheres.dal.dao;

import fr.eni.ENIEncheres.dal.jdbc.ArticleJdbcImpl;
import fr.eni.ENIEncheres.dal.jdbc.UtilisateursJdbcImpl;

public class DAOFactory {
	
	//Génération Utilisateur : 
	public static DAOUtilisateur getUtilisateursDAO() {
			
		return new UtilisateursJdbcImpl();
	}
	
	
	public static DAOArticleVendu getArticlesDAO() {
		
		return new ArticleJdbcImpl();
	}
}
