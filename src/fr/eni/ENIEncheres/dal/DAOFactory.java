package fr.eni.ENIEncheres.dal;

import fr.eni.ENIEncheres.bo.Article;
import fr.eni.ENIEncheres.bo.Utilisateurs;

public class DAOFactory {
	
	//Génération Utilisateur : 
	public static DAO<Utilisateurs> getUtilisateursDAO() {
			
			return new UtilisateursJdbcImpl();
	}
	
	
	//Génération article : 
	public static DAO<Article> getArticlesDAO() {
		
		return null;
		//return new ArticleJdbcImpl();
	}
}
