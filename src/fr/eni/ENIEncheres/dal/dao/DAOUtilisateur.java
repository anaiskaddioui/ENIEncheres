package fr.eni.ENIEncheres.dal.dao;

import java.util.List;

import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;


	public interface DAOUtilisateur { 


		public void insert(Utilisateurs u) throws DALException;
		public List<Utilisateurs> selectAll() throws DALException;
		public Utilisateurs selectById(int id) throws DALException;
		public Utilisateurs selectByName(String name) throws DALException;
		public Utilisateurs selectByPseudo(String pseudo) throws DALException;
		public Utilisateurs selectByEmail(String email) throws DALException;
		public List<Utilisateurs> selectByMotCle(String motCle) throws DALException;
		public void delete(int id) throws DALException;
		public void updateWithPassword(Utilisateurs u) throws DALException;
		public void updateWithoutPassword(Utilisateurs u) throws DALException;
		public void updateCredit(int id, int credit) throws DALException;

}
