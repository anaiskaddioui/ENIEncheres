package fr.eni.ENIEncheres.dal;


public class DAOFactory {
	
	//Génération Utilisateur : 
	public static DAOUtilisateur getUtilisateursDAO() {
			
			return new UtilisateursJdbcImpl();
	}
	
	
	/*Génération article : 
	public static DAO<Article> getArticlesDAO() {
		
		//return new ArticleJdbcImpl();
	}*/
}
