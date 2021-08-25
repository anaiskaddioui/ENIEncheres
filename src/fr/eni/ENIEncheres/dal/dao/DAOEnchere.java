package fr.eni.ENIEncheres.dal.dao;

import fr.eni.ENIEncheres.bo.Enchere;
import fr.eni.ENIEncheres.dal.DALException;

public interface DAOEnchere {
	
		void insert(Enchere enchere) throws DALException;
		Enchere selectByIdArticle(int idArticle) throws DALException;
		void delete(int idUtilisateur) throws DALException;
	    
	    
	}


