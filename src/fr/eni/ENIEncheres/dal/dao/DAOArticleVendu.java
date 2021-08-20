package fr.eni.ENIEncheres.dal.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;

import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;

public interface DAOArticleVendu {

	    List<ArticleVendu> filterByString(String filter) throws DALException;
	    List<Integer> getArticlesFromASellerAndState(Utilisateurs utilisateur, String state) throws DALException;
	    void updateCurrentPrice(int noArticle, int newPrice) throws DALException;
	    public List<ArticleVendu> insert(ArticleVendu articleVendu) throws DALException;
		public List <ArticleVendu> selectAllArticles();
	  	public List<ArticleVendu> selectParEtat(String etat) throws DALException;
		public List<ArticleVendu> selectParIdCategorie(int idCategorie) throws DALException;
		public  ArrayList<ArticleVendu> selectParMotCle(String nomArticle) throws DALException;
		public ArrayList<ArticleVendu> selectParCategEtMotCle(String nomArticle, int idCategorie)throws DALException;


	  //Qui a créé cette méthode? A Checker si cette methode n'est pas à déplacer dans DAOCatégories
	//	List<ArticleVendu> filterByCategory(Categorie categorie) throws DALException;
	
		public List<ArticleVendu> SelectAllArticlesAvecUtilisateurEtCategorie(int utilisateurId, int categorieId)
				throws DALException;
	
		public void insertArticle(ArticleVendu article, int utilisateurId, int categorieId)
				throws SQLException, DALException;
	
	//Qui a créé cette méthode? A Checker si cette methode n'est pas à déplacer dans DAOCatégories
	//	public List<Categorie> SelectAllCategories() throws DALException;
	
	
		public List<ArticleVendu> selectAllByEtatVenteUtilisateur(int etatVente, int idUtilisateur) throws DALException;
	
		public List<ArticleVendu> selectAllByEtatVenteGagne(int etatVente, int idUtilisateur) throws DALException;
	
		public List<ArticleVendu> SelectAllEncheresByEtat(int idUtilisateur, int etatVente) throws DALException;
	
		public ArticleVendu SelectEnchereById(int idArticle) throws DALException;
	
	//Qui a créé cette méthode? A Checker si cette methode est utilisée car doublon avec la mienne MERCI Charles
	//	List<ArticleVendu> filterByString(String filter) throws DALException;
	
	//Qui a créé cette méthode? A Checker si cette methode est utilisée car doublon avec la mienne MERCI Charles
	//	List<Integer> filterByEtat(String etat) throws DALException;
	
	
	//Qui a créé cette méthode? A Checker si cette methode est utilisée car doublon avec la mienne MERCI Charles
	//	ArrayList<ArticleVendu> rechercheParIdCategories(int idCategorie) throws DALException;

}