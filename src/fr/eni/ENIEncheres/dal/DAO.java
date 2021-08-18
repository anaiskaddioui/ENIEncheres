package fr.eni.ENIEncheres.dal;

import java.util.List;


	public interface DAO<T> { 

		public void insert(T t) throws Exception;
		public List<T> selectAll() throws Exception;
		public T selectById(int id) throws Exception;
		public T selectByNom(String nom) throws Exception;
		public List<T> selectByMotCle(String motCle) throws Exception;
		public void update(T maj) throws Exception;
		public void delete(int id) throws Exception;
}
