package fr.eni.ENIEncheres.dal.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;

import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;

public interface DAOArticleVendu {
	List<ArticleVendu> filterByCategory(Categorie categorie) throws DALException;

	public List<ArticleVendu> SelectAllArticlesAvecUtilisateurEtCategorie(int utilisateurId, int categorieId)
			throws DALException;

	public void insertArticle(ArticleVendu article, int utilisateurId, int categorieId)
			throws SQLException, DALException;

	public List<Categorie> SelectAllCategories() throws DALException;

	public List<ArticleVendu> selectAllArticles() throws DALException;

	public List<ArticleVendu> selectAllByEtatVente(int etatVente) throws DALException;

	public List<ArticleVendu> selectAllByEtatVenteUtilisateur(int etatVente, int idUtilisateur) throws DALException;

	public List<ArticleVendu> selectAllByEtatVenteGagne(int etatVente, int idUtilisateur) throws DALException;

	public List<ArticleVendu> SelectAllEncheresByEtat(int idUtilisateur, int etatVente) throws DALException;

	public ArticleVendu SelectEnchereById(int idArticle) throws DALException;

	List<ArticleVendu> filterByString(String filter) throws DALException;

	List<Integer> filterByEtat(String etat) throws DALException;

	List<Integer> getArticlesFromASellerAndState(Utilisateurs utilisateur, String state) throws DALException;

	void updateCurrentPrice(int noArticle, int newPrice) throws DALException;

	void insert(ArticleVendu articleVendu) throws DALException;

	ArrayList<ArticleVendu> rechercheParIdCategories(int idCategorie) throws DALException;

	List<ArticleVendu> selectParEtat(String etat) throws DALException;

	List<ArticleVendu> selectParIdCategorie(int idCategorie) throws DALException;

}