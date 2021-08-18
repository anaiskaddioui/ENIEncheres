package fr.eni.ENIEncheres.dal;

import java.util.List;

import fr.eni.ENIEncheres.bo.Utilisateurs;


	public interface DAOUtilisateur { 


		public void insert(Utilisateurs u) throws DALException;
		public List<Utilisateurs> selectAll() throws DALException;
		public Utilisateurs selectById(int id) throws DALException;
		public Utilisateurs selectByName(String name) throws DALException;
		public Utilisateurs selectByPseudo(String pseudo) throws DALException;
		public List<Utilisateurs> selectByMotCle(String motCle) throws DALException;
		public void update(Utilisateurs maj) throws DALException;
		public void delete(int id) throws DALException;
}
