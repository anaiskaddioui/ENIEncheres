package fr.eni.ENIEncheres.dal;

import java.util.List;


	public interface DAO<T> { 

		public void insert(T t) throws DALException;
		public List<T> selectAll() throws DALException;
		public T selectById(int id) throws DALException;
		public T selectByName(String name) throws DALException;
		public T selectByPseudo(String pseudo) throws DALException;
		public List<T> selectByMotCle(String motCle) throws DALException;
		public void update(T maj) throws DALException;
		public void delete(int id) throws DALException;
}
