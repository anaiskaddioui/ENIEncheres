package fr.eni.ENIEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.Outils;
import fr.eni.ENIEncheres.dal.dao.DAOArticleVendu;

public class ArticleJdbcImpl implements DAOArticleVendu {

	/**
	 * Attributs de classe des requêtes SQL
	 */

//	private static final String SQL_SELECT_ALL_ARTICLES = "SELECT no_article, nom_article, description, date_debut_encheres,"
//			+ " date_fin_encheres, prix_initial, prix_vente, etatVente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS ";

	private static final String SQL_INSERT_INTO_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, etatVente, no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?,?,?)";

//	private static final String SQL_SELECT_ALL_CATEGORIES = "SELECT no_categorie, libelle FROM CATEGORIES";
//	private static final String SQL_SELECT_ARTICLES_BY_ETAT = "SELECT * FROM ARTICLES_VENDUS as A INNER JOIN UTILISATEURS as U ON A.no_utilisateur = U.no_utilisateur "
//			+ "INNER JOIN ENCHERES as E ON U.no_utilisateur = E.no_utilisateur WHERE A.etatVente = ?";
//	private static final String SQL_SELECT_ARTICLES_BY_ETAT_AND_UTILISATEUR = "SELECT A.no_article, nom_article, description, date_debut_encheres, "
//			+ "date_fin_encheres, prix_initial, prix_vente, etatVente, U.no_utilisateur, no_categorie FROM ARTICLES_VENDUS as A "
//			+ "INNER JOIN UTILISATEURS as U ON A.no_utilisateur = U.no_utilisateur "
//			+ "INNER JOIN ENCHERES as E ON U.no_utilisateur = E.no_utilisateur "
//			+ "WHERE A.etatVente = ? AND U.no_utilisateur = ?";
//	private static final String SQL_SELECT_ARTICLES_BY_ETAT_AND_GAGNE = "SELECT A.no_article, nom_article, description, date_debut_encheres, "
//			+ "date_fin_encheres, prix_initial, prix_vente, etatVente, E.no_utilisateur, no_categorie FROM ARTICLES_VENDUS as A "
//			+ "INNER JOIN UTILISATEURS as U ON A.no_utilisateur = U.no_utilisateur "
//			+ "INNER JOIN ENCHERES as E ON U.no_utilisateur = E.no_utilisateur "
//			+ "INNER JOIN UTILISATEURS as ACH ON ACH.no_utilisateur = E.no_utilisateur "
//			+ "WHERE A.etatVente = ? AND E.no_utilisateur = ?";
//	private static final String SQL_SELECT_ENCHERES_BY_ETAT = "SELECT * FROM ARTICLES_VENDUS as A "
//			+ "INNER JOIN ENCHERES as E ON A.no_utilisateur = E.no_utilisateur "
//			+ "WHERE A.etatVente = ? AND E.no_utilisateur = ?";
//	private static final String SQL_SELECT_ARTICLE_BY_ID = "SELECT no_article, nom_article, description, date_debut_encheres, "
//			+ "date_fin_encheres, prix_initial, prix_vente, etat_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article = ?";

	//** REQUETES BEN */
	private final static String UPDATE_PRICE = "UPDATE ARTICLES_VENDUS SET prix_vente = ? WHERE no_article = ?";
	private final static String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	private final static String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, no_categorie = ? WHERE no_article = ?";
	
	
 /** REQUETES POUR CHARLES*/
	private final static String LISTER = "SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres, prix_initial, prix_vente FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur;";
	private final static String SELECT_BY_ID_CATEGORIE = "SELECT no_article,nom_article,description,date_fin_encheres,prix_initial, prix_vente, no_categorie, etat_vente FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE ARTICLES_VENDUS.no_categorie = ?;";
	private final static String SELECT_BY_KEYWORD = "SELECT no_article,nom_article,description, date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE nom_article LIKE ?;";
	private final static String SELECT_BY_ID_AND_KEYWORD = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE nom_article LIKE ?  AND no_categorie = ?;";
	private final static String SELECT_BY_ETAT_AND_KEYWORD = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE ARTICLES_VENDUS.etat_vente = ? AND nom_article LIKE ?;";
	private final static String SELECT_BY_ETAT_AND_CATEGORY = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE ARTICLES_VENDUS.etat_vente = ? AND ARTICLES_VENDUS.no_categorie = ?;";	
	private final static String SELECT_BY_ETAT_AND_CATEG_AND_KEYWORD = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, etat_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE etat_vente = ? AND no_categorie = ? AND nom_article LIKE ?";
	private final static String SELECT_BY_ETAT = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, etat_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE ARTICLES_VENDUS.etat_vente = ?;";
	private final static String SELECT_BY_USER_ID = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, etat_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE UTILISATEURS.no_utilisateur = ?;";
	private final static String SELECT_BY_ETAT_AND_USER_ID = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, etat_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE ARTICLES_VENDUS.etat_vente = ? AND UTILISATEURS.no_utilisateur = ?;";
	private final static String SELECT_BY_ETAT_AND_USER_ID_AND_KEYWORD = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, etat_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE etat_vente = ? AND UTILISATEURS.no_utilisateur = ? AND nom_article LIKE ?";
	private final static String SELECT_BY_ETAT_AND_USER_ID_AND_CATEGORY = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, etat_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE etat_vente = ? AND UTILISATEURS.no_utilisateur = ? AND ARTICLES_VENDUS.no_categorie = ?";
	private final static String SELECT_BY_ETAT_AND_USER_ID_AND_CATEG_AND_KEYWORD = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, etat_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE etat_vente = ? AND UTILISATEURS.no_utilisateur = ? AND no_categorie = ? AND nom_article LIKE ?";
//-----------------------------------------------------

	private static final String SQL_SELECT_ARTICLE_BY_ID = null;

	
	
//_______________________________________METHODES POUR JUDICAEL____________________________________________________
	
	
	
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
	public ArticleVendu  insertArticle(ArticleVendu newAdd) throws SQLException, DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			Outils o = new Outils();

			PreparedStatement pstmt = cnx.prepareStatement(SQL_INSERT_INTO_ARTICLE,
					PreparedStatement.RETURN_GENERATED_KEYS);
			System.out.println("art daoImpl : " + newAdd);
				 
			pstmt.setString(1, newAdd.getNomArticle());
			pstmt.setString(2, newAdd.getDescription());
			pstmt.setDate(3, o.dateJavaUtilEnDateSQL(newAdd.getDateDebutEncheres()));
			pstmt.setDate(4, o.dateJavaUtilEnDateSQL(newAdd.getDateFinEncheres()));
			pstmt.setInt(5, newAdd.getPrixInitial());
			pstmt.setInt(6, newAdd.getPrixVente());
			pstmt.setString(7, newAdd.getEtatVente());
			pstmt.setInt(8, newAdd.getIdUtilisateur());
			pstmt.setInt(9, newAdd.getIdCategorie());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de l'insertion d'un nouvel article", e);
		}
		return newAdd;

	}

//_________________________________________________________________________________________________________
	
	
	
	
	

//_____________________________________METHODES POUR CHARLES_______________________________________________
	
	public List<ArticleVendu> selectParEtat(String etat)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ETAT);
			//On donne l'etat vente au 1er argument de la requete
			pstmt.setString(1, etat);
			ResultSet rs = pstmt.executeQuery();
			ArticleVendu articleVendu = null;
			
			while (rs.next()) {

				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getString("pseudo"));
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de selectParEtat", e);
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
			pstmt = cnx.prepareStatement(SELECT_BY_ID_CATEGORIE);
			//On donne l'id catégorie au 1er argument de la requete
			pstmt.setInt(1, idCategorie);
			ResultSet rs = pstmt.executeQuery();
			

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
			throw new DALException("Echec de selectParIdCategorie", e);
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
			pstmt = cnx.prepareStatement(SELECT_BY_KEYWORD);
			//On donne les lettres du nom article au 1er argument de la requete
			pstmt.setString(1, "%" + nomArticle + "%");
			rs = pstmt.executeQuery();
			
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
			throw new DALException("Echec de selectParMotCle", e);
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
			pstmt = cnx.prepareStatement(SELECT_BY_ID_AND_KEYWORD);
			//On donne les lettres du nom article au 1er argument de la requete et le l'id de catégorie au 2eme
			pstmt.setString(1, "%" + nomArticle + "%");
			pstmt.setInt(2, idCategorie);
			rs = pstmt.executeQuery();
			
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
			throw new DALException("Echec de selectParCategEtMotCle", e);
		}

			return listeArticle;
	}
	
	
	public ArrayList<ArticleVendu> selectParEtatEtMotCle(String etat, String nomArticle)throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ETAT_AND_KEYWORD);
			//On donne l'etat au 1er argument de la requete et les lettres du nom article au 2eme
			pstmt.setString(1, etat);
			pstmt.setString(2, "%" + nomArticle + "%");
			rs = pstmt.executeQuery();
			
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
			throw new DALException("Echec de selectParCategEtMotCle", e);
		}

			return listeArticle;
	}
	

	public ArrayList<ArticleVendu> selectParEtatEtCategorie(String etat, int categorie)throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ETAT_AND_CATEGORY);
			//On donne l'etat au 1er argument de la requete et les lettres du nom article au 2eme
			pstmt.setString(1, etat);
			pstmt.setInt(2, categorie);
			rs = pstmt.executeQuery();
			
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
			throw new DALException("Echec de selectParCategEtMotCle", e);
		}

			return listeArticle;
	}
	
	
	public List<ArticleVendu> selectParEtatEtCategEtMotCle(String etat, int categorie, String nomArticle)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ETAT_AND_CATEG_AND_KEYWORD);
			//On donne l'etat au 1er argument de la requete et le l'userID au deuxieme
			pstmt.setString(1, etat);	
			pstmt.setInt(2, categorie);
			pstmt.setString(3, "%" + nomArticle + "%");
			ResultSet rs = pstmt.executeQuery();
			ArticleVendu articleVendu = null;
			
			while (rs.next()) {

				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getString("pseudo"));
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de selectParEtatEtCategEtMotCle", e);
		}
		return listeArticles;
	}
	
	
	
	public List<ArticleVendu> selectParEtatEtUserId(String etat, int userId)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ETAT_AND_USER_ID);
			//On donne l'etat au 1er argument de la requete et le l'userID au deuxieme
			pstmt.setString(1, etat);
			pstmt.setInt(2, userId);
			ResultSet rs = pstmt.executeQuery();
			ArticleVendu articleVendu = null;
			
			while (rs.next()) {

				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getString("pseudo"));
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de selectParEtatEtUserId", e);
		}
		return listeArticles;
	}
	
	public List<ArticleVendu> selectParUserId(int userId)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_USER_ID);
			//On donne user ID au 1er argument de la requete
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			ArticleVendu articleVendu = null;
			
			while (rs.next()) {

				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getString("pseudo"));
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de selectParEtatEtUserId", e);
		}
		return listeArticles;
	}

	
	public List<ArticleVendu> selectParEtatEtUserIdEtMotCle(String etat, int userId, String nomArticle)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ETAT_AND_USER_ID_AND_KEYWORD);
			//On donne l'etat au 1er argument de la requete et le l'userID au deuxieme
			pstmt.setString(1, etat);
			pstmt.setInt(2, userId);
			pstmt.setString(3, "%" + nomArticle + "%");
			ResultSet rs = pstmt.executeQuery();
			ArticleVendu articleVendu = null;
			
			while (rs.next()) {

				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getString("pseudo"));
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de selectParEtatEtUserIdEtMotCle", e);
		}
		return listeArticles;
	}

	
	public List<ArticleVendu> selectParEtatEtUserIdEtCategorie(String etat, int userId, int categorie)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ETAT_AND_USER_ID_AND_CATEGORY);
			//On donne l'etat au 1er argument de la requete et le l'userID au deuxieme
			pstmt.setString(1, etat);
			pstmt.setInt(2, userId);
			pstmt.setInt(3, categorie);
			ResultSet rs = pstmt.executeQuery();
			ArticleVendu articleVendu = null;
			
			while (rs.next()) {

				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getString("pseudo"));
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de selectParEtatEtUserIdEtMotCle", e);
		}
		return listeArticles;
	}
	

	
	public List<ArticleVendu> selectParEtatEtUserIdEtCategEtMotCle(String etat, int userId, int categorie, String nomArticle)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_BY_ETAT_AND_USER_ID_AND_CATEG_AND_KEYWORD);
			//On donne l'etat au 1er argument de la requete et le l'userID au deuxieme
			pstmt.setString(1, etat);	
			pstmt.setInt(2, userId);
			pstmt.setInt(3, categorie);
			pstmt.setString(4, "%" + nomArticle + "%");
			ResultSet rs = pstmt.executeQuery();
			ArticleVendu articleVendu = null;
			
			while (rs.next()) {

				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getString("pseudo"));
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de selectParEtatEtUserId", e);
		}
		return listeArticles;
	}
//_______________________________________________________________________________________________________________
	






	@Override
	public ArticleVendu selectArticleById(int idArticle) throws DALException {
		ArticleVendu article = null;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement rqt = cnx.prepareStatement(SQL_SELECT_ARTICLE_BY_ID);
			rqt.setInt(1, idArticle);
			ResultSet rs = rqt.executeQuery();
			
			
			while (rs.next()) {
							
				article = new ArticleVendu(rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"),
						rs.getInt("prix_initial"),	
						rs.getInt("prix_vente"),
						rs.getString("etat_vente"),
						rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"));
	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	@Override
	public void updateSellPrice(int idArticle, int prix) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement rqt = cnx.prepareStatement(UPDATE_PRICE);
			rqt.setInt(1, prix);
			rqt.setInt(2, idArticle);
			rqt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void deleteArticle(int idArticle) throws DALException {
	PreparedStatement rqt = null;
		
		try (Connection cnx = ConnectionProvider.getConnection())
		{		
			cnx.setAutoCommit(false);
			rqt = cnx.prepareStatement(DELETE);
			rqt.setInt(1, idArticle);
			rqt.executeUpdate();			
			cnx.commit();
			rqt.close();
			
		} catch (SQLException e) {

			  throw new DALException("Delete article failed - id=" + idArticle, e);
		} 		
	}

	@Override
	public void updateArticle(ArticleVendu article) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement rqt = cnx.prepareStatement(UPDATE_ARTICLE);
			rqt.setString(1, article.getNomArticle());
			rqt.setString(2, article.getDescription());
			rqt.setDate(3, article.getDateDebutEncheres());
			rqt.setDate(4, article.getDateFinEncheres());
			rqt.setInt(5, article.getPrixInitial());
			rqt.setInt(6, article.getIdCategorie());
			rqt.setInt(7, article.getIdArticle());
			rqt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

}
