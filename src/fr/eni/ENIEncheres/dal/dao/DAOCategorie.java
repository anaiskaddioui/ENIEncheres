package fr.eni.ENIEncheres.dal.dao;

import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.dal.DALException;

public interface DAOCategorie extends DAO<Categorie> {
    boolean checkForUniqueCategorieLibelle(String libelleToCheck) throws DALException;
}
