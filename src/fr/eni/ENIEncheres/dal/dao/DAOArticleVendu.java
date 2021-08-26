package fr.eni.ENIEncheres.dal.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.dal.DALException;

public interface DAOArticleVendu {
		public void insertArticle(ArticleVendu article, int idUtilisateur, int idCategorie) throws SQLException, DALException;
		public List<ArticleVendu> selectParEtat(String etat) throws DALException;
		public List <ArticleVendu> selectAllArticles();
		public List<ArticleVendu> selectParIdCategorie(int idCategorie) throws DALException;
		public  ArrayList<ArticleVendu> selectParMotCle(String nomArticle) throws DALException;
		public ArrayList<ArticleVendu> selectParCategEtMotCle(String nomArticle, int idCategorie)throws DALException;
		ArticleVendu selectArticleById(int idArticle) throws DALException;
		public void updateSellPrice (int idArticle, int prix) throws DALException;
		public void deleteArticle(int idArticle) throws DALException;
		public void updateArticle(ArticleVendu article) throws DALException;
		public ArrayList<ArticleVendu> selectParEtatEtMotCle(String etat, String nomArticle)throws DALException;
		public List<ArticleVendu> selectParEtatEtCategEtMotCle(String etat, int categorie, String nomArticle) throws DALException;
		public List<ArticleVendu> selectParEtatEtUserId(String etat, int userId) throws DALException;
		public ArrayList<ArticleVendu> selectParEtatEtCategorie(String etat, int categorie)throws DALException;
		public List<ArticleVendu> selectParUserId(int userId) throws DALException;
		public List<ArticleVendu> selectParEtatEtUserIdEtMotCle(String etat, int userId, String nomArticle) throws DALException;
		public List<ArticleVendu> selectParEtatEtUserIdEtCategorie(String etat, int userId, int categorie)throws DALException;
		public List<ArticleVendu> selectParEtatEtUserIdEtCategEtMotCle(String etat, int userId, int categorie, String nomArticle) throws DALException;
		public void updateDateFinEnchere(String date) throws DALException;
}