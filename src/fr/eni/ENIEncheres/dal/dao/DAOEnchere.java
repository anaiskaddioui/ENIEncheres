package fr.eni.ENIEncheres.dal.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.bo.Enchere;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;

public interface DAOEnchere {
	
	  List<Integer> getidArticlesByUtilisateurAndEtat(Utilisateurs utilisateur, String state) throws DALException;
	    List<Integer> getidArticlesWonByUtilisateur(Utilisateurs utilisateur) throws DALException;
	    HashMap<Integer, Integer> getAmountAndPseudoOfBestOffer(ArticleVendu articleVendu) throws DALException, SQLException;
		void insert(Enchere enchere) throws DALException;
		Enchere selectById(int id) throws DALException;
		List<Enchere> selectAll() throws DALException;
		void update(Enchere enchere) throws DALException;
		void delete(Enchere enchere) throws DALException;
		List<Integer> getNoArticlesByUtilisateurAndEtat(Utilisateurs utilisateur, String state) throws DALException;
		List<Integer> getNoArticlesWonByUtilisateur(Utilisateurs utilisateur) throws DALException;
	    
	    
	}


