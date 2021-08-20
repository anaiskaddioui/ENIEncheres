package fr.eni.ENIEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEncheres.bll.BLLException;
import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.Outils;
import fr.eni.ENIEncheres.dal.dao.DAOArticleVendu;

public class ArticleJdbcImpl implements DAOArticleVendu {

	/**
	 * Attributs de classe des requêtes SQL
	 */

	private static final String SQL_SELECT_ALL_ARTICLES = "SELECT no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, etatVente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS ";

	private static final String SQL_INSERT_INTO_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etatVente, no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?,?,?)";

	private static final String SQL_SELECT_ALL_CATEGORIES = "SELECT no_categorie, libelle FROM CATEGORIES";
	private static final String SQL_SELECT_ARTICLES_BY_ETAT = "SELECT * FROM ARTICLES_VENDUS as A INNER JOIN UTILISATEURS as U ON A.no_utilisateur = U.no_utilisateur "
			+ "INNER JOIN ENCHERES as E ON U.no_utilisateur = E.no_utilisateur WHERE A.etatVente = ?";
	private static final String SQL_SELECT_ARTICLES_BY_ETAT_AND_UTILISATEUR = "SELECT A.no_article, nom_article, description, date_debut_encheres, "
			+ "date_fin_encheres, prix_initial, prix_vente, etatVente, U.no_utilisateur, no_categorie FROM ARTICLES_VENDUS as A "
			+ "INNER JOIN UTILISATEURS as U ON A.no_utilisateur = U.no_utilisateur "
			+ "INNER JOIN ENCHERES as E ON U.no_utilisateur = E.no_utilisateur "
			+ "WHERE A.etatVente = ? AND U.no_utilisateur = ?";
	private static final String SQL_SELECT_ARTICLES_BY_ETAT_AND_GAGNE = "SELECT A.no_article, nom_article, description, date_debut_encheres, "
			+ "date_fin_encheres, prix_initial, prix_vente, etatVente, E.no_utilisateur, no_categorie FROM ARTICLES_VENDUS as A "
			+ "INNER JOIN UTILISATEURS as U ON A.no_utilisateur = U.no_utilisateur "
			+ "INNER JOIN ENCHERES as E ON U.no_utilisateur = E.no_utilisateur "
			+ "INNER JOIN UTILISATEURS as ACH ON ACH.no_utilisateur = E.no_utilisateur "
			+ "WHERE A.etatVente = ? AND E.no_utilisateur = ?";
	private static final String SQL_SELECT_ENCHERES_BY_ETAT = "SELECT * FROM ARTICLES_VENDUS as A "
			+ "INNER JOIN ENCHERES as E ON A.no_utilisateur = E.no_utilisateur "
			+ "WHERE A.etatVente = ? AND E.no_utilisateur = ?";
	private static final String SQL_SELECT_ARTICLE_BY_ID = "SELECT no_article, nom_article, description, date_debut_encheres, "
			+ "date_fin_encheres, prix_initial, prix_vente, etatVente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article = ?";

 /** REQUETES POUR CHARLES*/
	private final static String SELECTBYID = "SELECT no_categorie, libelle FROM CATEGORIES WHERE no_categorie = ?;";
	private final static String LISTER = "SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres, prix_initial, prix_vente FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur;";
	private final static String SELECTIDCATEGORIE = "SELECT no_article,nom_article,description,date_fin_encheres,prix_initial, prix_vente, no_categorie, etat_vente FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE ARTICLES_VENDUS.no_categorie = ?;";
	private final static String SELECTBYKEYWORD = "SELECT no_article,nom_article,description, date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE nom_article LIKE ?;";
	private final static String SELECTBYIDANDKEYWORD = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE nom_article LIKE ?  AND no_categorie = ?;";
	private final static String SELECTBYETAT = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, etat_vente FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE ARTICLES_VENDUS.etat_vente = ?;";


	@Override
//	public List<ArticleVendu> insert(ArticleVendu articleVendu) throws DALException {
//		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
//		try (Connection conn = ConnectionProvider.getConnection()) {
//			Statement stmt = conn.createStatement();
//
//			
//			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_ARTICLES);
//			ArticleVendu article = null;
//			while (rs.next()) {
//
//				article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
//						rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
//						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("etatVente"), idUtilisateur,
//						idCategorie);
//
//				listeArticles.add(article);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DALException("Echec de SelectAllArticle", e);
//		}
//		return listeArticles;
//	}


	/**
	 * Méthode d'insertion d'un objet en BDD
	 */
	public void insertArticle(ArticleVendu article, int idUtilisateur, int idCategorie) throws SQLException, DALException {
		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_INTO_ARTICLE,
					PreparedStatement.RETURN_GENERATED_KEYS);
			System.out.println("art daoImpl : " + article);

			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			pstmt.setInt(5, article.getPrixInitial());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setString(7, article.getEtatVente());
			pstmt.setInt(8, idUtilisateur);
			pstmt.setInt(9, idCategorie);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de l'insertion d'un nouvel article", e);
		}

	}


	
	public List<ArticleVendu> selectParEtat(String etat)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECTBYETAT);
			//On donne l'id catégorie au 1er argument de la requete
			pstmt.setString(1, etat);
			ResultSet rs = pstmt.executeQuery();
			Outils o = new Outils();
			ArticleVendu articleVendu = null;
			
			while (rs.next()) {

				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}
		return listeArticles;
	}

	
	public List <ArticleVendu> selectAllArticles() {

		Statement stmtConsultation = null;
		List <ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();

		try {
			Connection cnx = ConnectionProvider.getConnection();
			stmtConsultation = cnx.createStatement();
			ResultSet rsConsultation = stmtConsultation.executeQuery(LISTER);
			
			ArticleVendu article = null;
			
	
			
			while(rsConsultation.next()) {
			
					article=new ArticleVendu();
					
					article.setNomArticle(rsConsultation.getString("nom_article"));
					article.setIdArticle(rsConsultation.getInt("no_article"));
					article.setNomArticle(rsConsultation.getString("nom_article"));
					article.setDescription(rsConsultation.getString("description"));
					article.setDateFinEncheres(rsConsultation.getDate("date_fin_encheres"));
					article.setPrixInitial(rsConsultation.getInt("prix_initial"));
					article.setPrixVente(rsConsultation.getInt("prix_vente"));

					listeArticles.add(article);

			}
		} catch (SQLException e) {
			System.out.println("Erreur connexion SQL");
			e.printStackTrace();
		}
		
		return listeArticles;
		
	}
	
	
	
	public List<ArticleVendu> selectParIdCategorie(int idCategorie)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECTIDCATEGORIE);
			//On donne l'id catégorie au 1er argument de la requete
			pstmt.setInt(1, idCategorie);
			ResultSet rs = pstmt.executeQuery();
			Outils o = new Outils();

			ArticleVendu articleVendu = null;
			
			while (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				listeArticle.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}

		return listeArticle;
	}

	

	public  ArrayList<ArticleVendu> selectParMotCle(String nomArticle) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECTBYKEYWORD);
			pstmt.setString(1, "%" + nomArticle + "%");
			rs = pstmt.executeQuery();
			Outils o = new Outils();
			ArticleVendu articleVendu = null;
			while (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				//articleVendu.setPseudo(rs.getInt("pseudo"));
				listeArticle.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}

		return listeArticle;
	}
	


	public ArrayList<ArticleVendu> selectParCategEtMotCle(String nomArticle, int idCategorie)throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECTBYIDANDKEYWORD);
			pstmt.setString(1, "%" + nomArticle + "%");
			pstmt.setInt(2, idCategorie);
			rs = pstmt.executeQuery();
			Outils o = new Outils();
			ArticleVendu articleVendu;
			while (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				//articleVendu.setPseudo(rs.getInt("pseudo"));
				listeArticle.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}

			return listeArticle;
	}
	
	

	@Override
	public List<ArticleVendu> filterByString(String filter) throws DALException {
		return null;
	}


	@Override
	public List<Integer> getArticlesFromASellerAndState(Utilisateurs utilisateur, String state) throws DALException {
		return null;
	}

	@Override
	public void updateCurrentPrice(int noArticle, int newPrice) throws DALException {

	}

}
