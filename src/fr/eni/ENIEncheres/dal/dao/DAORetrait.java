package fr.eni.ENIEncheres.dal.dao;

import fr.eni.ENIEncheres.bo.Retrait;
import fr.eni.ENIEncheres.dal.DALException;

public interface DAORetrait {
	
	public void ajouterRetrait(Retrait retrait) throws DALException;
	
	public Retrait selectRetraitParIdArticle(int idArticle) throws DALException;

}


