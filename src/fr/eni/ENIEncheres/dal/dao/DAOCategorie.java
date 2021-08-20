package fr.eni.ENIEncheres.dal.dao;

import java.util.List;

import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.dal.DALException;

public interface DAOCategorie extends DAO<Categorie> {
    boolean checkForUniqueCategorieLibelle(String libelleToCheck) throws DALException;
	public List<Categorie> selectAll() throws DALException;
}
