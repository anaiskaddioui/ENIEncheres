package fr.eni.ENIEncheres.dal.dao;

import java.util.List;

import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.dal.DALException;

public interface DAO<T> {
    void insert(T var) throws DALException;
    T selectById(int id) throws DALException;
    List<T> selectAll() throws DALException;
    void update(T var) throws DALException;
    void delete(T var) throws DALException;
	Categorie selectByIdCategorie(int idCategorie) throws DALException;
}
