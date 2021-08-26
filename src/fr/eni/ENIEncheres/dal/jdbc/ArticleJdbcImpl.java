package fr.eni.ENIEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
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

	private static final String SQL_INSERT_INTO_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, etat_vente, no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?,?,?)";

	//** REQUETES BEN */
	private final static String UPDATE_PRICE = "UPDATE ARTICLES_VENDUS SET prix_vente = ? WHERE no_article = ?";
	private final static String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	private final static String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, no_categorie = ? WHERE no_article = ?";
	
	
 /** REQUETES POUR CHARLES*/
	private final static String LISTER = "SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres, prix_initial, prix_vente FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur;";
	private final static String SELECT_BY_ID_CATEGORIE = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_categorie, pseudo, ARTICLES_VENDUS.no_utilisateur FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE ARTICLES_VENDUS.no_categorie = ?;";
	private final static String SELECT_BY_KEYWORD = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_categorie, pseudo, ARTICLES_VENDUS.no_utilisateur FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE nom_article LIKE ?;";
	private final static String SELECT_BY_ID_AND_KEYWORD = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_categorie, pseudo, ARTICLES_VENDUS.no_utilisateur FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE nom_article LIKE ?  AND no_categorie = ?;";
	private final static String SELECT_BY_ETAT_AND_KEYWORD = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_categorie, pseudo, ARTICLES_VENDUS.no_utilisateur FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE ARTICLES_VENDUS.etat_vente = ? AND nom_article LIKE ?;";
	private final static String SELECT_BY_ETAT_AND_CATEGORY = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_categorie, pseudo, ARTICLES_VENDUS.no_utilisateur FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE ARTICLES_VENDUS.etat_vente = ? AND ARTICLES_VENDUS.no_categorie = ?;";	
	private final static String SELECT_BY_ETAT_AND_CATEG_AND_KEYWORD = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_categorie, pseudo, ARTICLES_VENDUS.no_utilisateur FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE etat_vente = ? AND no_categorie = ? AND nom_article LIKE ?";
	private final static String SELECT_BY_ETAT = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_categorie, pseudo, ARTICLES_VENDUS.no_utilisateur FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE ARTICLES_VENDUS.etat_vente = ?;";
	private final static String SELECT_BY_USER_ID = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_categorie, pseudo, ARTICLES_VENDUS.no_utilisateur FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE UTILISATEURS.no_utilisateur = ?;";
	private final static String SELECT_BY_ETAT_AND_USER_ID = "SELECT ARTICLES_VENDUS.no_article,nom_article,description,date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_categorie, pseudo, ARTICLES_VENDUS.no_utilisateur AS no_vendeur, ENCHERES.no_utilisateur AS no_acheteur FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur LEFT JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article WHERE ARTICLES_VENDUS.etat_vente = ? AND UTILISATEURS.no_utilisateur = ?;";
	private final static String SELECT_BY_ETAT_AND_USER_ID_AND_KEYWORD = SELECT_BY_ETAT_AND_USER_ID + "AND nom_article LIKE ?";
	private final static String SELECT_BY_ETAT_AND_USER_ID_AND_CATEGORY = SELECT_BY_ETAT_AND_USER_ID + "AND ARTICLES_VENDUS.no_categorie = ?";
	private final static String SELECT_BY_ETAT_AND_USER_ID_AND_CATEG_AND_KEYWORD = SELECT_BY_ETAT_AND_USER_ID + "AND no_categorie = ? AND nom_article LIKE ?";
	private final static String SELECT_ACHAT_BY_ETAT_AND_USER_ID = "SELECT ARTICLES_VENDUS.no_article,nom_article,description,date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etat_vente, no_categorie, pseudo, ARTICLES_VENDUS.no_utilisateur AS no_vendeur, ENCHERES.no_utilisateur AS no_acheteur FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur LEFT JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article WHERE ARTICLES_VENDUS.etat_vente = ? AND ENCHERES.no_utilisateur = ?;";
	private final static String SELECT_ACHAT_BY_ETAT_AND_USER_ID_AND_KEYWORD = SELECT_BY_ETAT_AND_USER_ID + "AND nom_article LIKE ?";
	private final static String SELECT_ACHAT_BY_ETAT_AND_USER_ID_AND_CATEGORY = SELECT_BY_ETAT_AND_USER_ID + "AND ARTICLES_VENDUS.no_categorie = ?";
	private final static String SELECT_ACHAT_BY_ETAT_AND_USER_ID_AND_CATEG_AND_KEYWORD = SELECT_BY_ETAT_AND_USER_ID + "AND no_categorie = ? AND nom_article LIKE ?";
	private final static String UPDATE_DATE_FIN_ENCHERES = "UPDATE ARTICLES_VENDUS SET etat_vente = 'TE' WHERE date_fin_encheres < ? AND etat_vente ='EC'";
	private final static String UPDATE_DATE_DEBUT_ENCHERES = "UPDATE ARTICLES_VENDUS SET etat_vente = 'EC' WHERE date_debut_encheres <= ? AND etat_vente='ND'";
	private final static String SELECT_CLOTURE_ENCHERES = "SELECT * from ARTICLES_VENDUS WHERE etat_vente ='EC' AND date_fin_encheres <= ?";
//-----------------------------------------------------

	private static final String SQL_SELECT_ARTICLE_BY_ID = "SELECT no_article, nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente FROM ARTICLES_VENDUS WHERE ARTICLES_VENDUS.no_article = ?";
	
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
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				newAdd.setIdArticle(rs.getInt(1));
			}
			rs.close();
			
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
			//On donne les parametres de requete
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
				articleVendu.setIdUtilsateur(rs.getInt("no_utilisateur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
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
			ResultSet rs = stmtConsultation.executeQuery(LISTER);
			ArticleVendu articleVendu = null;		
			while(rs.next()) {
			
				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));							
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));				
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getString("pseudo"));
				articleVendu.setIdUtilsateur(rs.getInt("no_utilisateur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
				listeArticles.add(articleVendu);

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
			//On donne les parametres de requete
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
				articleVendu.setPseudo(rs.getString("pseudo"));
				articleVendu.setIdUtilsateur(rs.getInt("no_utilisateur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
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
			//On donne les parametres de requete
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
				articleVendu.setPseudo(rs.getString("pseudo"));
				articleVendu.setIdUtilsateur(rs.getInt("no_utilisateur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
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
			//On donne les parametres de requete
			pstmt.setString(1, "%" + nomArticle + "%");
			pstmt.setInt(2, idCategorie);
			rs = pstmt.executeQuery();
			ArticleVendu articleVendu;
			while (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));							
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));				
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getString("pseudo"));
				articleVendu.setIdUtilsateur(rs.getInt("no_utilisateur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
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
			//On donne les parametres de requete
			pstmt.setString(1, etat);
			pstmt.setString(2, "%" + nomArticle + "%");
			rs = pstmt.executeQuery();
			ArticleVendu articleVendu;
			while (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));							
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));				
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getString("pseudo"));
				articleVendu.setIdUtilsateur(rs.getInt("no_utilisateur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
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
			//On donne les parametres de requete
			pstmt.setString(1, etat);
			pstmt.setInt(2, categorie);
			rs = pstmt.executeQuery();
			ArticleVendu articleVendu;
			while (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));							
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres"));				
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getString("pseudo"));
				articleVendu.setIdUtilsateur(rs.getInt("no_utilisateur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
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
			//On donne les parametres de requete
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
				articleVendu.setIdUtilsateur(rs.getInt("no_utilisateur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
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
			//On donne les parametres de requete
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
				articleVendu.setIdUtilsateur(rs.getInt("no_vendeur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
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
			//On donne les parametres de requete
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
				articleVendu.setIdUtilsateur(rs.getInt("no_utilisateur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
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
			//On donne les parametres de requete
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
				articleVendu.setIdUtilsateur(rs.getInt("no_vendeur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
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
			//On donne les parametres de requete
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
				articleVendu.setIdUtilsateur(rs.getInt("no_vendeur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
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
			//On donne les parametres de requete
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
				articleVendu.setIdUtilsateur(rs.getInt("no_vendeur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de selectParEtatEtUserIdEtCategEtMotCle", e);
		}
		return listeArticles;
	}
	
	
	public List<ArticleVendu> selectAchatParEtatEtUserId(String etat, int userId)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_ACHAT_BY_ETAT_AND_USER_ID);
			//On donne les parametres de requete
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
				articleVendu.setIdUtilsateur(rs.getInt("no_vendeur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de selectParEtatEtUserId", e);
		}
		return listeArticles;
	}
	
	
	public List<ArticleVendu> selectAchatParEtatEtUserIdEtMotCle(String etat, int userId, String nomArticle)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_ACHAT_BY_ETAT_AND_USER_ID_AND_KEYWORD);
			//On donne les parametres de requete
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
				articleVendu.setIdUtilsateur(rs.getInt("no_vendeur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de selectParEtatEtUserIdEtMotCle", e);
		}
		return listeArticles;
	}

	
	public List<ArticleVendu> selectAchatParEtatEtUserIdEtCategorie(String etat, int userId, int categorie)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_ACHAT_BY_ETAT_AND_USER_ID_AND_CATEGORY);
			//On donne les parametres de requete
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
				articleVendu.setIdUtilsateur(rs.getInt("no_vendeur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de selectParEtatEtUserIdEtMotCle", e);
		}
		return listeArticles;
	}
	

	
	public List<ArticleVendu> selectAchatParEtatEtUserIdEtCategEtMotCle(String etat, int userId, int categorie, String nomArticle)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECT_ACHAT_BY_ETAT_AND_USER_ID_AND_CATEG_AND_KEYWORD);
			//On donne les parametres de requete
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
				articleVendu.setIdUtilsateur(rs.getInt("no_vendeur"));
				articleVendu.setEtatVente(rs.getString("etat_vente"));
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
				listeArticles.add(articleVendu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de selectParEtatEtUserIdEtCategEtMotCle", e);
		}
		return listeArticles;
	}
	

	public void updateDateFinEnchere(Date date) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_DATE_FIN_ENCHERES);
			//On donne les parametres de requete
			pstmt.setDate(1, date);	
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de updateDateFinEnchere", e);
		}
	}
	
	
	public void updateDateDebutEnchere(Date date) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(UPDATE_DATE_DEBUT_ENCHERES);
			//On donne les parametres de requete
			pstmt.setDate(1, date);	
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de updateStatutDateDebutEnchere", e);
		}
	}
	
	@Override
	public List<ArticleVendu> selectFinEnchere(Date date) throws DALException {
		List<ArticleVendu> listArticles = null;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement rqt = cnx.prepareStatement(SELECT_CLOTURE_ENCHERES);
			rqt.setDate(1, date);
			ResultSet rs = rqt.executeQuery();
			
			
			while (rs.next()) {
							
				ArticleVendu article = new ArticleVendu(rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"),
						rs.getInt("prix_initial"),	
						rs.getInt("prix_vente"),
						rs.getString("etat_vente"),
						rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"));
				listArticles.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listArticles;
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
