package fr.eni.ENIEncheres.dal.dao;

import java.util.List;

import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.dal.DALException;

public interface DAOCategorie {
    
	boolean checkForUniqueCategorieLibelle(String libelleToCheck) throws DALException;
	public List<Categorie> selectAll() throws DALException;
	
	//********** Nouvelles méthodes ****************
	public Categorie selectByIdCategorie(int idCategorie) throws DALException;
	public void insert(Categorie categorie) throws DALException;
	
	public int selectIdByLibelle(String libelle) throws DALException;
	
}
