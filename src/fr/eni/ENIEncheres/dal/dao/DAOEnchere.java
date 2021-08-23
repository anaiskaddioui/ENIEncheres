package fr.eni.ENIEncheres.dal.dao;

import java.util.List;

import fr.eni.ENIEncheres.bo.Enchere;
import fr.eni.ENIEncheres.dal.DALException;

public interface DAOEnchere {
	
		void insert(Enchere enchere) throws DALException;
		Enchere selectById(int id) throws DALException;
		List<Enchere> selectAll() throws DALException;
		void update(Enchere enchere) throws DALException;
	    
	    
	}


