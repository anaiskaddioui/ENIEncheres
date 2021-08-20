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

	/**
	 * Sélectionne les articles avec les paramètres idUtilistateur && idCategorie
	 */
	public List<ArticleVendu> SelectAllArticlesAvecUtilisateurEtCategorie(int idUtilisateur, int idCategorie)
			throws DALException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();

		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_ARTICLES);
			ArticleVendu article = null;
			while (rs.next()) {

				article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("etatVente"), idUtilisateur,
						idCategorie);

				listeArticles.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticle", e);
		}
		return listeArticles;
	}

	public List<ArticleVendu> selectAllArticles() throws DALException {
		List<ArticleVendu> listArticles = new ArrayList<ArticleVendu>();
		ArticleVendu article = null;
		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_ARTICLES);

			while (rs.next()) {
				article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("etatVente"),
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
				listArticles.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticle", e);
		}

		return listArticles;
	}

	/**
	 * Méthode d'insertion d'un objet en BDD
	 */
	public void insertArticle(ArticleVendu article, int idUtilisateur, int idCategorie) throws SQLException, DALException {
		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_INTO_ARTICLE,
					PreparedStatement.RETURN_GENERATED_KEYS);
			System.out.println("art daoImpl : " + article);

			pstmt.setString(1, article.getNom());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			pstmt.setInt(5, article.getMiseAPrix());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setInt(7, article.getEtatVente());
			pstmt.setInt(8, idUtilisateur);
			pstmt.setInt(9, idCategorie);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de l'insertion d'un nouvel article", e);
		}

	}

	/**
	 *
	 * @throws BLLException Sélectionne toutes les catégories
	 */
	public List<Categorie> SelectAllCategories() throws DALException {
		List<Categorie> listeCategorie = new ArrayList<Categorie>();

		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_CATEGORIES);
			Categorie categorie = null;

			while (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				listeCategorie.add(categorie);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllCategories", e);
		}
		return listeCategorie;
	}

	/**
	 * Sélectionne tous les articles
	 * 
	 * @throws DALException
	 */
	public List<ArticleVendu> SelectAllArticles() throws DALException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();

		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_ARTICLES);

			ArticleVendu article = null;

			LocalDate dateDebutEnchere = null;
			LocalDate dateFinEnchere = null;

			while (rs.next()) {
				dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
				dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
						dateDebutEnchere, dateFinEnchere, rs.getInt("prix_initial"), rs.getInt("prix_vente"),
						rs.getInt("etatVente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
				listeArticles.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}
		return listeArticles;
	}

	public List<ArticleVendu> selectAllByEtatVente(int etatVente) throws DALException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();

		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ARTICLES_BY_ETAT);

			pstmt.setInt(1, etatVente);
			ResultSet rs = pstmt.executeQuery();

			ArticleVendu article = null;
			LocalDate dateDebutEnchere = null;
			LocalDate dateFinEnchere = null;

			while (rs.next()) {
				dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
				dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
						dateDebutEnchere, dateFinEnchere, rs.getInt("prix_initial"), rs.getInt("prix_vente"),
						rs.getInt("etatVente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
				listeArticles.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}

		return listeArticles;
	}

	public List<ArticleVendu> selectAllByEtatVenteUtilisateur(int etatVente, int idUtilisateur) throws DALException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();

		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ARTICLES_BY_ETAT_AND_UTILISATEUR);

			pstmt.setInt(1, etatVente);
			pstmt.setInt(2, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();

			ArticleVendu article = null;
			LocalDate dateDebutEnchere = null;
			LocalDate dateFinEnchere = null;

			while (rs.next()) {
				dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
				dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
						dateDebutEnchere, dateFinEnchere, rs.getInt("prix_initial"), rs.getInt("prix_vente"),
						rs.getInt("etatVente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
				listeArticles.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}

		return listeArticles;
	}

	public List<ArticleVendu> selectAllByEtatVenteGagne(int etatVente, int idUtilisateur) throws DALException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();

		try (Connection conn = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ARTICLES_BY_ETAT_AND_GAGNE);

			pstmt.setInt(1, etatVente);
			pstmt.setInt(2, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();

			ArticleVendu article = null;
			LocalDate dateDebutEnchere = null;
			LocalDate dateFinEnchere = null;

			while (rs.next()) {
				dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
				dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
						dateDebutEnchere, dateFinEnchere, rs.getInt("prix_initial"), rs.getInt("prix_vente"),
						rs.getInt("etatVente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
				listeArticles.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}

		return listeArticles;
	}

	public List<ArticleVendu> SelectAllEncheresByEtat(int idUtilisateur, int etatVente) throws DALException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();

		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ENCHERES_BY_ETAT);

			pstmt.setInt(1, etatVente);
			pstmt.setInt(2, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();

			ArticleVendu article = null;

			LocalDate dateDebutEnchere = null;
			LocalDate dateFinEnchere = null;

			while (rs.next()) {
				dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
				dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
						dateDebutEnchere, dateFinEnchere, rs.getInt("prix_initial"), rs.getInt("prix_vente"),
						rs.getInt("etatVente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
				listeArticles.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}
		return listeArticles;
	}

	public ArticleVendu SelectEnchereById(int idArticle) throws DALException {
		ArticleVendu article = null;

		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ARTICLE_BY_ID);

			pstmt.setInt(1, idArticle);

			ResultSet rs = pstmt.executeQuery();

			LocalDate dateDebutEnchere = null;
			LocalDate dateFinEnchere = null;

			if (rs.next()) {
				dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
				dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
						dateDebutEnchere, dateFinEnchere, rs.getInt("prix_initial"), rs.getInt("prix_vente"),
						rs.getInt("etatVente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
				;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}
		return article;
	}

	@Override
	public List<ArticleVendu> filterByCategory(Categorie categorie) throws DALException {
		return null;
	}

	@Override
	public List<ArticleVendu> filterByString(String filter) throws DALException {
		return null;
	}

	@Override
	public List<Integer> filterByEtat(String etat) throws DALException {
		return null;
	}

	@Override
	public List<Integer> getArticlesFromASellerAndState(Utilisateurs utilisateur, String state) throws DALException {
		return null;
	}

	@Override
	public void updateCurrentPrice(int noArticle, int newPrice) throws DALException {

	}

	@Override
	public void insert(ArticleVendu articleVendu) throws DALException {

	}

	@Override
	public ArrayList<ArticleVendu> rechercheParIdCategories(int idCategorie) throws DALException {
		return null;
	}

	@Override
	public List<ArticleVendu> selectParEtat(String etat) throws DALException {
		return null;
	}

	@Override
	public List<ArticleVendu> selectParIdCategorie(int idCategorie) throws DALException {
		return null;
	}
}
