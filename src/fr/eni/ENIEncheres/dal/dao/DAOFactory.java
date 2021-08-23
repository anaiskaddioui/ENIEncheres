package fr.eni.ENIEncheres.dal.dao;

import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.dal.jdbc.ArticleJdbcImpl;
import fr.eni.ENIEncheres.dal.jdbc.CategorieJdbcImpl;
import fr.eni.ENIEncheres.dal.jdbc.EnchereJdbcImpl;
import fr.eni.ENIEncheres.dal.jdbc.RetraitJdbcImpl;
import fr.eni.ENIEncheres.dal.jdbc.UtilisateursJdbcImpl;

public class DAOFactory {

	public static ArticleVendu getArticleVendu() {
		return null;
	}

	public static DAOUtilisateur getDAOUtilisateur() {
		return new UtilisateursJdbcImpl();
	}

	public static DAOArticleVendu getDAOArticleVendu() {
		return new ArticleJdbcImpl();
	}

	public static DAOCategorie getDAOCategorie() {
		return new CategorieJdbcImpl();
	}

	public static DAOEnchere getDAOEnchere() {
		return new EnchereJdbcImpl();
	}

	public static DAORetrait getDAORetrait() {
		return new RetraitJdbcImpl();
	}

}
