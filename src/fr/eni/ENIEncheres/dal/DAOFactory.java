package fr.eni.ENIEncheres.dal;


public class DAOFactory {
	
	//Génération Utilisateur : 
	public static DAOUtilisateur getUtilisateursDAO() {
			
		return new UtilisateursJdbcImpl();
	}
	
	
	public static DAOArticleVendu getArticlesDAO() {
		
		return new ArticleJdbcImpl();
	}
}
